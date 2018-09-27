package sim.manager;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import sim.entity.Departamento;
import sim.persistence.DepartamentoDAO;
import sim.persistence.factory.DAOFactory;

@ManagedBean(name="deptoMB")
@RequestScoped
public class DeptoMB {
	
	private Departamento depto;
	private DepartamentoDAO deptoDao;
	private List<Departamento> deptos;
	
	public DeptoMB() {
		depto = new Departamento();
		deptoDao = DAOFactory.criarDeptoDAO();
	}
	
	
	public void gravar() {
		  FacesContext fc = FacesContext.getCurrentInstance();
		  try {
			 this.deptoDao.salvar(this.depto);
			 this.depto = new Departamento(); 
			 fc.addMessage(null, new FacesMessage("Dados gravados"));
		  }catch(Exception ex) {
			 fc.addMessage(null, new FacesMessage("Error:" + ex.getMessage()));  
		  }
	  }
	
	public void editar() {
		  FacesContext fc = FacesContext.getCurrentInstance();
		  try {
			  this.deptoDao.atualizar(this.depto);
			 this.depto = new Departamento(); 
			 fc.addMessage(null, new FacesMessage("Dados modificados"));
		  }catch(Exception ex) {
			 fc.addMessage(null, new FacesMessage("Error:" + ex.getMessage()));  
		  }
	  }
	
	  public void excluir() {
		 FacesContext fc = FacesContext.getCurrentInstance(); 
		 try {
			 this.deptoDao.excluir(this.depto);
			 fc.addMessage(null, new FacesMessage("Dados Excluídos ..."));
			 
		 }catch(Exception ex){
			 ex.printStackTrace();
		 }
		  
	  }

	

	public Departamento getDepto() {
		return depto;
	}
	public void setDepto(Departamento depto) {
		this.depto = depto;
	}
	public DepartamentoDAO getDeptoDao() {
		return deptoDao;
	}
	public void setDeptoDao(DepartamentoDAO deptoDao) {
		this.deptoDao = deptoDao;
	}
	public List<Departamento> getDeptos() {
		this.deptos = deptoDao.listar();
		return deptos;
	}
	public void setDeptos(List<Departamento> deptos) {
		this.deptos = deptos;
	}
	
	

}
