package sim.manager;


import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import sim.entity.ItemEstoque;
import sim.entity.Material;
import sim.persistence.ItemEstoqueDAO;
import sim.persistence.MaterialDAO;
import sim.persistence.factory.DAOFactory;

@ManagedBean(name="estoqueMB")
@RequestScoped
public class EstoqueMB {
	
	private ItemEstoque estoque;
	private ItemEstoqueDAO estoqueDao;
	private MaterialDAO materialDao;
	private Material material;
	List<ItemEstoque> listaEstoque;
	
	public EstoqueMB() {
		estoqueDao = DAOFactory.criarItemEstoqueDAO();
		materialDao = DAOFactory.criarMaterialDAO();
		this.estoque = new ItemEstoque();
	}
	
	public String gravar() {
		  FacesContext fc = FacesContext.getCurrentInstance();
		  try {
			 material = materialDao.buscarMaterialPorSimbolo(estoque.getMaterial().getSimbolo());
			 if(material != null)	{
				 estoque.setMaterial(material);
				 this.estoqueDao.salvar(this.estoque);
			 }
			 else	{
				 fc.addMessage(null, new FacesMessage("Não é possível cadastrar item. Símbolo inexistente."));
				 return "admestoque";
			 }
				 
			 this.estoque = new ItemEstoque(); 
			 fc.addMessage(null, new FacesMessage("Dados gravados"));
		  }catch(Exception ex) {
			 fc.addMessage(null, new FacesMessage("Error:" + ex.getMessage()));  
		  }
		  return "";
	  }
	
	public void editar() {
		  FacesContext fc = FacesContext.getCurrentInstance();
		  try {
			  this.estoqueDao.atualizar(this.estoque);
			 this.estoque = new ItemEstoque(); 
			 fc.addMessage(null, new FacesMessage("Dados modificados"));
		  }catch(Exception ex) {
			 fc.addMessage(null, new FacesMessage("Error:" + ex.getMessage()));  
		  }
	  }
	
	  public void excluir() {
		 FacesContext fc = FacesContext.getCurrentInstance(); 
		 try {
			 this.estoqueDao.excluir(this.estoque);
			 fc.addMessage(null, new FacesMessage("Dados Excluídos ..."));
			 
		 }catch(Exception ex){
			 ex.printStackTrace();
		 }
		  
	  }


	public ItemEstoque getEstoque() {
		return estoque;
	}

	public void setEstoque(ItemEstoque estoque) {
		this.estoque = estoque;
	}

	public ItemEstoqueDAO getEstoqueDao() {
		return estoqueDao;
	}

	public void setEstoqueDao(ItemEstoqueDAO estoqueDao) {
		this.estoqueDao = estoqueDao;
	}

	public List<ItemEstoque> getListaEstoque() {
		this.listaEstoque = estoqueDao.listar();
		return listaEstoque;
	}

	public void setListaEstoque(List<ItemEstoque> listaEstoque) {
		this.listaEstoque = listaEstoque;
	}

	public MaterialDAO getMaterialDao() {
		return materialDao;
	}

	public void setMaterialDao(MaterialDAO materialDao) {
		this.materialDao = materialDao;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

}
