package sim.manager;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import sim.entity.Material;
import sim.entity.UnidadeFornecimento;
import sim.persistence.MaterialDAO;
import sim.persistence.UnidadeFornecimentoDAO;
import sim.persistence.factory.DAOFactory;

@ManagedBean(name="materialMB")
@RequestScoped
public class MaterialMB {
	
	private Material material;
	private MaterialDAO materialDao;
	private UnidadeFornecimentoDAO ufDao;
	private List<Material> materiais;
	private List<Material> materiaisFiltrados;
	private List<UnidadeFornecimento> unidades;
	
	public MaterialMB() {
		this.material = new Material();
		this.ufDao = DAOFactory.criarUfDAO();
		this.materialDao = DAOFactory.criarMaterialDAO();
		
	}
	
	public void gravar()	{
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			this.material.setSimbolo(this.material.getSimbolo().toUpperCase());
			this.materialDao.salvar(material);
			this.material = new Material();
			fc.addMessage(null, new FacesMessage("Dados gravados"));
		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage("Error:" + ex.getMessage()));
		}				
	}
	
	public void editar()	{
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			this.material.setSimbolo(this.material.getSimbolo().toUpperCase());
			this.materialDao.atualizar(material);
			this.material = new Material();
			fc.addMessage(null, new FacesMessage("Dados modificados"));
		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage("Error:" + ex.getMessage()));
		}				
	}
	
	public void excluir()	{
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			this.materialDao.excluir(material);
			this.material = new Material();
			fc.addMessage(null, new FacesMessage("Dados excluídos"));
		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage("Error:" + ex.getMessage()));
		}				
	}


	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public MaterialDAO getMaterialDao() {
		return materialDao;
	}

	public void setMaterialDao(MaterialDAO materialDao) {
		this.materialDao = materialDao;
	}

	public List<Material> getMateriais() {
		this.materiais = materialDao.listar();
		return materiais;
	}

	public void setMateriais(List<Material> materiais) {
		this.materiais = materiais;
	}

	public List<UnidadeFornecimento> getUnidades() {
		this.unidades = this.ufDao.listar();
		return unidades;
	}

	public void setUnidades(List<UnidadeFornecimento> unidades) {
		this.unidades = unidades;
	}

	public List<Material> getMateriaisFiltrados() {
		return materiaisFiltrados;
	}

	public void setMateriaisFiltrados(List<Material> materiaisFiltrados) {
		this.materiaisFiltrados = materialDao.listar();
		this.materiaisFiltrados = materiaisFiltrados;
	}

	

}
