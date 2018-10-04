package sim.manager;


import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import sim.entity.ItemEstoque;
import sim.entity.Pedido;
import sim.persistence.ItemEstoqueDAO;
import sim.persistence.PedidoDAO;
import sim.persistence.factory.DAOFactory;

@ManagedBean(name="manterPedidoMB")
@RequestScoped
public class MantemPedidoMB {
	
	private PedidoDAO pedidoDao;
	private ItemEstoqueDAO estoqueDao;
	private ItemEstoque itemEstoque;
	private List<Pedido> listaPorSetorPendentes;
	private List<Pedido> listaPedidosSelecionados;
	private List<Pedido> listaPedidosEmEstudo;
	
	
	@ManagedProperty("#{loginMB}")
	private LoginMB loginMB;
	
	public MantemPedidoMB() {
		this.pedidoDao = DAOFactory.criarPedidoDAO();
		this.estoqueDao = DAOFactory.criarItemEstoqueDAO();

	}
	
	//ESTUDO DE MATERIAL
	public void avaliar()	{
		FacesContext fc = FacesContext.getCurrentInstance();
		

		for (Pedido pedido : listaPedidosSelecionados) {
			
			try {
				if(pedido.getMaterial().getSimbolo() != null)	{
					pedido.setStatus(Pedido.STATUS_OBTENCAO);
					this.pedidoDao.atualizar(pedido);
					fc.addMessage(null, new FacesMessage("Avaliação Técnica do(s) Pedido(s) realizada."));
				}
				else
					fc.addMessage(null, new FacesMessage("Não foi possível aprovar o pedido sem um símbolo."));
					
			} catch (Exception e) {
				e.printStackTrace();
				fc.addMessage(null, new FacesMessage("Não foi possível aprovar o pedido. Erro: "+e.getMessage()));
			}
		}
	}
	
	public void devolver()	{
		FacesContext fc = FacesContext.getCurrentInstance();

		for (Pedido pedido : listaPedidosSelecionados) {
			pedido.setStatus(Pedido.STATUS_PRIMARIO);
			try {
				this.pedidoDao.atualizar(pedido);
				fc.addMessage(null, new FacesMessage("Pedido(s) devolvido(s) ao emissor."));
			} catch (Exception e) {
				e.printStackTrace();
				fc.addMessage(null, new FacesMessage("Não foi possível cancelar o pedido. Erro: "+e.getMessage()));
			}
		}
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
		boolean possuiEstoque = false;
		for (Pedido pedido : listaPedidosSelecionados) {
			try {
				List<ItemEstoque> listaEstoque = 
						estoqueDao
							.buscarItemEstoquePorSimbolo(pedido.getMaterial().getSimbolo());
				for(ItemEstoque ie: listaEstoque)	{
					if(pedido.getQuantidade()<=ie.getSaldo())	{
						possuiEstoque = true;
					}
				}
				if(possuiEstoque)
					pedido.setStatus(Pedido.STATUS_COM_SALDO_ESTOQUE);
				else if(!possuiEstoque && pedido.getMaterial().getSimbolo() != null)
					pedido.setStatus(Pedido.STATUS_OBTENCAO);
				else 
					pedido.setStatus(Pedido.STATUS_ANALISE_ESTUDO_MATERIAL);

				this.pedidoDao.atualizar(pedido);
				fc.addMessage(null, new FacesMessage("Pedido(s) autorizado(s)."));
			} catch (Exception e) {
				e.printStackTrace();
				fc.addMessage(null, new FacesMessage("Não foi possível cancelar o pedido. Erro: "+e.getMessage()));
			} finally	{
				possuiEstoque = false;
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

	public List<Pedido> getListaPedidosEmEstudo() {
		this.listaPedidosEmEstudo = pedidoDao.listarPedidoEmEstudo();
		return listaPedidosEmEstudo;
	}

	public void setListaPedidosEmEstudo(List<Pedido> listaPedidosEmEstudo) {
		this.listaPedidosEmEstudo = listaPedidosEmEstudo;
	}

	public ItemEstoqueDAO getEstoqueDao() {
		return estoqueDao;
	}

	public void setEstoqueDao(ItemEstoqueDAO estoqueDao) {
		this.estoqueDao = estoqueDao;
	}

	public ItemEstoque getItemEstoque() {
		return itemEstoque;
	}

	public void setItemEstoque(ItemEstoque itemEstoque) {
		this.itemEstoque = itemEstoque;
	}

}
