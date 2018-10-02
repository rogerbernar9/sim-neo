package sim.manager;


import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import sim.entity.Pedido;
import sim.persistence.PedidoDAO;
import sim.persistence.factory.DAOFactory;

@ManagedBean(name="manterPedidoMB")
@RequestScoped
public class MantemPedidoMB {
	
	private PedidoDAO pedidoDao;
	private List<Pedido> listaPorSetorPendentes;
	private List<Pedido> listaPedidosSelecionados;
	
	@ManagedProperty("#{loginMB}")
	private LoginMB loginMB;
	
	public MantemPedidoMB() {
		this.pedidoDao = DAOFactory.criarPedidoDAO();

	}
	
	public void cancelar()	{
		FacesContext fc = FacesContext.getCurrentInstance();

		for (Pedido pedido : listaPedidosSelecionados) {
			pedido.setStatus(Pedido.STATUS_REJEITADO);
			try {
				this.pedidoDao.atualizar(pedido);
				fc.addMessage(null, new FacesMessage("Pedido(s) cancelado(s)."));
			} catch (Exception e) {
				e.printStackTrace();
				fc.addMessage(null, new FacesMessage("Não foi possível cancelar o pedido. Erro: "+e.getMessage()));
			}
		}
	}
	
	public void aprovar()	{
		FacesContext fc = FacesContext.getCurrentInstance();

		for (Pedido pedido : listaPedidosSelecionados) {
			try {
				pedido.setStatus(Pedido.STATUS_ANALISE_ESTUDO_MATERIAL);
				if(pedido.getMaterial().getSimbolo() != null)
					pedido.setStatus(Pedido.STATUS_OBTENCAO);
				this.pedidoDao.atualizar(pedido);
				fc.addMessage(null, new FacesMessage("Pedido(s) autorizado(s)."));
			} catch (Exception e) {
				e.printStackTrace();
				fc.addMessage(null, new FacesMessage("Não foi possível cancelar o pedido. Erro: "+e.getMessage()));
			}
		}
	}

	public PedidoDAO getPedidoDao() {
		return pedidoDao;
	}

	public void setPedidoDao(PedidoDAO pedidoDao) {
		this.pedidoDao = pedidoDao;
	}

	public List<Pedido> getListaPorSetorPendentes() {
		System.out.println(this.loginMB.getNomeUsuario());
		this.listaPorSetorPendentes = pedidoDao.listarPorSetorPendentes(loginMB.getUsuario());
		return listaPorSetorPendentes;
	}

	public void setListaPorSetorPendentes(List<Pedido> listaPorSetorPendentes) {
		this.listaPorSetorPendentes = listaPorSetorPendentes;
	}

	public List<Pedido> getListaPedidosSelecionados() {
		return listaPedidosSelecionados;
	}

	public void setListaPedidosSelecionados(List<Pedido> listaPedidosSelecionados) {
		this.listaPedidosSelecionados = listaPedidosSelecionados;
	}

	public LoginMB getLoginMB() {
		return loginMB;
	}

	public void setLoginMB(LoginMB loginMB) {
		this.loginMB = loginMB;
	}

}
