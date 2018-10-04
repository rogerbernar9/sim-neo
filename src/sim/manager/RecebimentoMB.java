package sim.manager;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import sim.entity.Pedido;
import sim.persistence.PedidoDAO;
import sim.persistence.factory.DAOFactory;

@ManagedBean(name="entregaMB")
@RequestScoped
public class RecebimentoMB {
	
	private Pedido pedido;
	private PedidoDAO pedidoDao;
	private List<Pedido> pedidosEntrega;
	
	public RecebimentoMB() {
		pedidoDao = DAOFactory.criarPedidoDAO();

	}
	
	public void efetuar()	{
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			pedido.setStatus(Pedido.STATUS_FINAL);
			pedidoDao.atualizar(pedido);
			fc.addMessage(null, new FacesMessage("Requisição atendida!"));
		} catch (Exception e) {
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Impossível realizar operação. erro "+e.getMessage(),""));
		}
		
	}
	


	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public PedidoDAO getPedidoDao() {
		return pedidoDao;
	}
	public void setPedidoDao(PedidoDAO pedidoDao) {
		this.pedidoDao = pedidoDao;
	}
	public List<Pedido> getPedidosEntrega() {
		pedidosEntrega = pedidoDao.listarPedidoEmEntrega();
		return pedidosEntrega;
	}
	public void setPedidosEntrega(List<Pedido> pedidosEntrega) {
		this.pedidosEntrega = pedidosEntrega;
	}

}
