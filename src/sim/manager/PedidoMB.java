package sim.manager;

import java.sql.Timestamp;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FlowEvent;

import sim.entity.Material;
import sim.entity.Pedido;
import sim.entity.UnidadeFornecimento;
import sim.persistence.MaterialDAO;
import sim.persistence.PedidoDAO;
import sim.persistence.UnidadeFornecimentoDAO;
import sim.persistence.factory.DAOFactory;

@ManagedBean(name="pedidoMB")
@ViewScoped
public class PedidoMB {
	
	private Pedido pedido = new Pedido();
	private Material material = new Material();
	private PedidoDAO pedidoDao;
	private MaterialDAO materialDao;
	private UnidadeFornecimentoDAO ufDao;
	
	private List<UnidadeFornecimento> listaUnidades;
	private List<UnidadeFornecimento> listaUf;
	private String simboloMaterial;
	private boolean pulo;
	private boolean urgencia;
	
	@ManagedProperty("#{loginMB}")
	private LoginMB loginMB;
	
	
	public PedidoMB() {
		this.materialDao = DAOFactory.criarMaterialDAO();
		this.pedidoDao = DAOFactory.criarPedidoDAO();
		this.ufDao = DAOFactory.criarUfDAO();
	}
	
	
    public String processaPedido(FlowEvent event) throws Exception {
		inicializaObjetosDoPedido();
    	
		//voltando a tela anterior
    	if(event.getOldStep().equals("ped2") &&
    	   event.getNewStep().equals("ped1"))	{
    		limpaObjetos();
    	}
    	
    	//voltado da terceira tela
    	if(event.getOldStep().equals("ped3") &&
    			event.getNewStep().equals("ped2"))	{
    		limpaObjetos();
    		return "ped1";
    	}
    	//busca material se tiver símbolo ou prossegue pedido sem símbolo
    	if(event.getOldStep().equals("ped1") &&
    	   event.getNewStep().equals("ped2"))	{
    		
        	if(this.getSimboloMaterial() == null ||
        	   this.getSimboloMaterial().equals(""))	{
        		limpaObjetos();
        		return event.getNewStep();
        	}
        	else	{ 	//processa pedido com símbolo informado
	        		try {
	            		this.material = materialDao.buscarMaterialPorSimbolo(simboloMaterial);
	            		if(this.material == null)	{
	            			limpaObjetos();
	            			FacesContext fc = FacesContext.getCurrentInstance();
	            			fc.addMessage(null, new FacesMessage("Não existe material para este símbolo!"));
	            			return event.getOldStep();
	            		}
	            		
					} catch (Exception e) {
						e.printStackTrace();
					}
        	}
    	}

        return event.getNewStep();

    }


	private void inicializaObjetosDoPedido() {
		this.materialDao = DAOFactory.criarMaterialDAO();
		this.pedidoDao = DAOFactory.criarPedidoDAO();
		this.ufDao = DAOFactory.criarUfDAO();
		this.listaUf = ufDao.listar();
		this.listaUnidades = ufDao.listar();
	}


	private void limpaObjetos() {
		this.material = new Material();
		this.pedido = new Pedido();
		this.simboloMaterial = null;
	}
    
    public String emitirPedido()	{
		inicializaObjetosDoPedido();
    	FacesContext fc = FacesContext.getCurrentInstance();
    	this.pedido.setUsuario(this.loginMB.getUsuario());
		this.pedido.setStatus(Pedido.STATUS_PRIMARIO);
    	this.pedido.setDataEmissao(new Timestamp(new java.util.Date().getTime()));
    	
    	try	{	//setar id material no pedido quando emitido sem simbolo
    		if(this.simboloMaterial ==null)	{
    			this.materialDao.salvar(material);
    			this.pedido.setMaterial(this.material);
    		}
    		else	{
    			this.pedido.setMaterial(this.material);
    		}
    		this.pedidoDao.salvar(pedido);
    		fc.addMessage(null, new FacesMessage("Pedido emitido com sucesso!"));
    		
    	} catch(Exception ex)	{
    		ex.printStackTrace();
    		fc.addMessage(null, new FacesMessage("Error:" + ex.getMessage()));  

    	} finally {
			this.material = new Material();
			this.pedido = new Pedido();
		}
		return "pedido";

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
	public PedidoDAO getPedidoDao() {
		return pedidoDao;
	}
	public void setPedidoDao(PedidoDAO pedidoDao) {
		this.pedidoDao = pedidoDao;
	}
	public MaterialDAO getMaterialDao() {
		return materialDao;
	}
	public void setMaterialDao(MaterialDAO materialDao) {
		this.materialDao = materialDao;
	}

	public boolean isPulo() {
		return pulo;
	}

	public void setPulo(boolean pulo) {
		this.pulo = pulo;
	}


	public String getSimboloMaterial() {
		return simboloMaterial;
	}

	public void setSimboloMaterial(String simboloMaterial) {
		this.simboloMaterial = simboloMaterial;
	}


	public UnidadeFornecimentoDAO getUfDao() {
		return ufDao;
	}


	public void setUfDao(UnidadeFornecimentoDAO ufDao) {
		this.ufDao = ufDao;
	}


	public List<UnidadeFornecimento> getListaUnidades() {
		this.ufDao = DAOFactory.criarUfDAO();

		this.listaUnidades = ufDao.listar();
		return listaUnidades;
	}


	public void setListaUnidades(List<UnidadeFornecimento> listaUnidades) {
		this.listaUnidades = listaUnidades;
	}


	public boolean isUrgencia() {
		return urgencia;
	}


	public void setUrgencia(boolean urgencia) {
		this.urgencia = urgencia;
	}


	public List<UnidadeFornecimento> getListaUf() {
		this.ufDao = DAOFactory.criarUfDAO();

		this.listaUf = ufDao.listar();
		return listaUf;
	}


	public void setListaUf(List<UnidadeFornecimento> listaUf) {
		this.listaUf = listaUf;
	}


	public LoginMB getLoginMB() {
		return loginMB;
	}


	public void setLoginMB(LoginMB loginMB) {
		this.loginMB = loginMB;
	}

}
