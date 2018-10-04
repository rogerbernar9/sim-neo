package sim.manager;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import sim.entity.Fornecedor;
import sim.entity.OrdemCompra;
import sim.entity.Pedido;
import sim.persistence.FornecedorDAO;
import sim.persistence.OrdemCompraDAO;
import sim.persistence.PedidoDAO;
import sim.persistence.factory.DAOFactory;

@ManagedBean(name="ordemCompraMB")
@RequestScoped
public class OrdemCompraMB {
	
	private OrdemCompraDAO ordemDao;
	private PedidoDAO pedidoDao;
	private FornecedorDAO fornecedorDao;
	private OrdemCompra ordemCompra;
	private Fornecedor fornecedor;
	private Pedido pedido;
	private List<OrdemCompra> listaOrdemCompra;
	private List<Pedido> listaPedidoObtencao;
	private List<Fornecedor> fornecedores;
	private List<SelectItem> fornecedoresTela;
	private String numPedido;
	private Float preco;
	
	@ManagedProperty("#{loginMB}")
	private LoginMB loginMB;
	
	
	public OrdemCompraMB() {
		this.ordemDao = DAOFactory.criarOrdemCompraDAO();
		this.ordemCompra = new OrdemCompra();
		this.pedido = new Pedido();
		this.pedidoDao = DAOFactory.criarPedidoDAO();
		this.fornecedorDao = DAOFactory.criarFornecedorDAO();
		this.fornecedoresTela = new ArrayList<SelectItem>();
	}
	
	public String enviar()	{
		FacesContext fc = FacesContext.getCurrentInstance();
		ordemCompra.setFornecedor(fornecedor);
		ordemCompra.setPreco(preco);
		try {
			pedido = pedidoDao.carregar(Integer.parseInt(numPedido));
			if(pedido != null && pedido.getStatus().equals(Pedido.STATUS_OBTENCAO))	{
				ordemCompra.setPedido(pedido);
				ordemCompra.setUsuario(loginMB.getUsuario());
				ordemCompra.setDataEmissao(new Timestamp(new Date().getTime()));
				pedido.setStatus(Pedido.STATUS_AGUARDA_ENTREGA);
				ordemDao.salvar(ordemCompra);
				pedidoDao.atualizar(pedido);
				fc.addMessage(null, new FacesMessage("Ordem de compra emitida. Número: "+ordemCompra.getCodigo()));
			}	else
				fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Requisição de material inexistente!",""));  

		} catch (Exception e) {
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Impossível realizar operação. erro "+e.getMessage(),""));  
			
		} finally	{
		}
		
		return "ordemcompra";
	}
	
	
	public void gravar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			this.ordemDao.salvar(this.ordemCompra);
			this.ordemCompra = new OrdemCompra(); 
			fc.addMessage(null, new FacesMessage("Dados gravados"));
		}catch(Exception ex) {
			fc.addMessage(null, new FacesMessage("Error:" + ex.getMessage()));  
		}
	}

	public void editar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			this.ordemDao.atualizar(this.ordemCompra);
			this.ordemCompra = new OrdemCompra(); 
			fc.addMessage(null, new FacesMessage("Dados modificados"));
		}catch(Exception ex) {
			fc.addMessage(null, new FacesMessage("Error:" + ex.getMessage()));  
		}
	}

	public void excluir() {
		FacesContext fc = FacesContext.getCurrentInstance(); 
		try {
			this.ordemDao.excluir(this.ordemCompra);
			fc.addMessage(null, new FacesMessage("Dados Excluídos ..."));

		}catch(Exception ex){
			ex.printStackTrace();
		}

	}

	
	
	public OrdemCompraDAO getOrdemDao() {
		return ordemDao;
	}
	public void setOrdemDao(OrdemCompraDAO ordemDao) {
		this.ordemDao = ordemDao;
	}
	public OrdemCompra getOrdemCompra() {
		return ordemCompra;
	}
	public void setOrdemCompra(OrdemCompra ordemCompra) {
		this.ordemCompra = ordemCompra;
	}
	public List<OrdemCompra> getListaOrdemCompra() {
		this.listaOrdemCompra = 
				ordemDao.listarPorSetor(loginMB.getUsuario().getSetor());
		return listaOrdemCompra;
	}
	public void setListaOrdemCompra(List<OrdemCompra> listaOrdemCompra) {
		this.listaOrdemCompra = listaOrdemCompra;
	}


	public List<Pedido> getListaPedidoObtencao() {
		this.listaPedidoObtencao = pedidoDao.listarPedidoEmObtencao();
		return listaPedidoObtencao;
	}


	public void setListaPedidoObtencao(List<Pedido> listaPedidoObtencao) {
		this.listaPedidoObtencao = listaPedidoObtencao;
	}


	public PedidoDAO getPedidoDao() {
		return pedidoDao;
	}


	public void setPedidoDao(PedidoDAO pedidoDao) {
		this.pedidoDao = pedidoDao;
	}


	public FornecedorDAO getFornecedorDao() {
		return fornecedorDao;
	}


	public void setFornecedorDao(FornecedorDAO fornecedorDao) {
		this.fornecedorDao = fornecedorDao;
	}


	public Fornecedor getFornecedor() {
		return fornecedor;
	}


	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}


	public List<Fornecedor> getFornecedores() {
		this.fornecedores = fornecedorDao.listar();
		return fornecedores;
	}


	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}


	public String getNumPedido() {
		return numPedido;
	}


	public void setNumPedido(String numPedido) {
		this.numPedido = numPedido;
	}


	public List<SelectItem> getFornecedoresTela() {
		this.fornecedores = fornecedorDao.listar();
		if(this.fornecedoresTela.isEmpty())	{
			for(Fornecedor forn : this.fornecedores)	{
				SelectItem item = new SelectItem(forn);
				item.setLabel(forn.getNomeFantasia() +" - " +forn.getCnpj());
				this.fornecedoresTela.add(item);
			}
		}
		return fornecedoresTela;
	}


	public void setFornecedoresTela(List<SelectItem> fornecedoresTela) {
		this.fornecedoresTela = fornecedoresTela;		
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public LoginMB getLoginMB() {
		return loginMB;
	}

	public void setLoginMB(LoginMB loginMB) {
		this.loginMB = loginMB;
	}

}
