package sim.manager;

import java.sql.Timestamp;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import sim.entity.Material;
import sim.entity.Pedido;
import sim.persistence.ItemEstoqueDAO;
import sim.persistence.ItemMovimentoDAO;
import sim.persistence.PedidoDAO;
import sim.persistence.factory.DAOFactory;
import sim.entity.ItemEstoque;
import sim.entity.ItemMovimento;
import org.primefaces.event.FlowEvent;


@ManagedBean(name="movimentoMB")
@ViewScoped
public class MovimentoEstoqueMB {
	
	private ItemMovimento movimento;
	private ItemMovimentoDAO movimentoDao;
	private ItemEstoque estoque;
	private List<ItemEstoque> listaItemEstoque;
	private ItemEstoqueDAO estoqueDao;
	private Pedido pedido;
	private PedidoDAO pedidoDao;
	private Material material;
	private String codigoPedido;
	private String operacao;
	
	public MovimentoEstoqueMB() {
		movimentoDao = DAOFactory.criarItemMovimentoDAO();
		estoqueDao = DAOFactory.criarItemEstoqueDAO();
		pedidoDao = DAOFactory.criarPedidoDAO();
		this.material = new Material();
		this.movimento = new ItemMovimento();
		this.estoque = new ItemEstoque();
	}
	
	public void verificar()	{

	}
	
	public String processaMovimento(FlowEvent event) throws Exception	{
		inicializaObjetos();
		FacesContext fc = FacesContext.getCurrentInstance();
		
		//next
		if(event.getOldStep().equals("ent1") &&
		   event.getNewStep().equals("ent2"))	{
			
			this.pedido = this.pedidoDao.carregar(
							Integer.parseInt(codigoPedido));
			if(this.pedido != null)	{
				this.listaItemEstoque = 
							this.estoqueDao
								.buscarItemEstoquePorSimbolo(this.pedido.getMaterial().getSimbolo());
		
				if(this.listaItemEstoque.isEmpty() ||
				   this.listaItemEstoque == null)
					fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Não existem itens no estoque para este material",""));
			}
			else	{
				fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Requisição de material inexistente","")); 
				limpaObjetos();
				return "ent1";
			}
		}
		//back
		if(event.getOldStep().equals("ent2") &&
		   event.getNewStep().equals("ent1"))	{
			limpaObjetos();
			return "ent1";
		}



        return event.getNewStep();

		
	}
	
	private void inicializaObjetos() {
		movimentoDao = DAOFactory.criarItemMovimentoDAO();
		estoqueDao = DAOFactory.criarItemEstoqueDAO();
		pedidoDao = DAOFactory.criarPedidoDAO();
		this.material = new Material();
		this.movimento = new ItemMovimento();
		
	}

	private void limpaObjetos() {
		this.material = null;
		this.movimento = null;
		this.estoque = null;
		
	}

	public String confirmar() throws Exception	{
		inicializaObjetos();
		FacesContext fc = FacesContext.getCurrentInstance();

		if(estoque == null)	{
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Não foi possível efetuar a operação",""));
			limpaObjetos();
			return "entradaestoque";
		}
		movimento.setDataMovimento(new Timestamp(new java.util.Date().getTime()));
		movimento.setOperacao(operacao);
		movimento.setPedido(pedido);
		movimento.setQuantidade(pedido.getQuantidade());
		movimento.setItemEstoque(this.estoque);
		pedido.setStatus(Pedido.STATUS_FINAL);
		atualizaSaldo(movimento, estoque, pedido);
		try {
			pedidoDao.atualizar(pedido);
			movimentoDao.salvar(movimento);
			estoqueDao.atualizar(this.estoque);
		} catch (Exception e) {
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Não foi possível efetuar a operação",""));
		}
		finally	{
			limpaObjetos();
		}
			return "entradaestoque";
	}
	
	public void atualizaSaldo(ItemMovimento itemMovimento, ItemEstoque itemEstoque, Pedido pedido)		{
		FacesContext fc = FacesContext.getCurrentInstance();
		
		if(itemMovimento.getOperacao().equals("entrada"))	{
			itemEstoque.setSaldo(itemEstoque.getSaldo()+pedido.getQuantidade());
			fc.addMessage(null, new FacesMessage("Saldo atualizado."));
		}
		if(itemMovimento.getOperacao().equals("saida"))	{
			if(itemEstoque.getSaldo()<pedido.getQuantidade())
				fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Estoque insuficiente para atender o pedido.","")); 
			else	{
				itemEstoque.setSaldo(itemEstoque.getSaldo()-pedido.getQuantidade());
				fc.addMessage(null, new FacesMessage("Saldo atualizado."));
			}
		}
	}

	public ItemMovimento getMovimento() {
		return movimento;
	}
	public void setMovimento(ItemMovimento movimento) {
		this.movimento = movimento;
	}
	public ItemEstoque getEstoque() {
		return estoque;
	}
	public void setEstoque(ItemEstoque estoque) {
		this.estoque = estoque;
	}
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	public Material getMaterial() {
		return material;
	}
	public void setMaterial(Material material) {
		this.material = material;
	}

	public ItemMovimentoDAO getMovimentoDao() {
		return movimentoDao;
	}

	public void setMovimentoDao(ItemMovimentoDAO movimentoDao) {
		this.movimentoDao = movimentoDao;
	}

	public ItemEstoqueDAO getEstoqueDao() {
		return estoqueDao;
	}

	public void setEstoqueDao(ItemEstoqueDAO estoqueDao) {
		this.estoqueDao = estoqueDao;
	}

	public PedidoDAO getPedidoDao() {
		return pedidoDao;
	}

	public void setPedidoDao(PedidoDAO pedidoDao) {
		this.pedidoDao = pedidoDao;
	}

	public String getCodigoPedido() {
		return codigoPedido;
	}

	public void setCodigoPedido(String codigoPedido) {
		this.codigoPedido = codigoPedido;
	}

	public List<ItemEstoque> getListaItemEstoque() {
		return listaItemEstoque;
	}

	public void setListaItemEstoque(List<ItemEstoque> listaItemEstoque) {
		this.listaItemEstoque = listaItemEstoque;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
	
	

}
