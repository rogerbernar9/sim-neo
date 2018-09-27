package sim.manager;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import sim.entity.Perfil;
import sim.persistence.PerfilDAO;
import sim.persistence.factory.DAOFactory;

@ManagedBean(name="perfilMB")
@RequestScoped
public class PerfilMB {
	
	private Perfil perfil;
	private PerfilDAO perfilDao;	
	private List<Perfil> perfis;
	
	public PerfilMB() {
		this.perfil = new Perfil();
		this.perfilDao = DAOFactory.criarPerfilDAO();
	}
	
	
	public void gravar() {
		  FacesContext fc = FacesContext.getCurrentInstance();
		  try {
			 this.perfilDao.salvar(this.perfil);
			 this.perfil = new Perfil(); 
			 fc.addMessage(null, new FacesMessage("Dados gravados"));
		  }catch(Exception ex) {
			 fc.addMessage(null, new FacesMessage("Error:" + ex.getMessage()));  
		  }
	  }
	
	public void editar() {
		  FacesContext fc = FacesContext.getCurrentInstance();
		  try {
			  this.perfilDao.atualizar(this.perfil);
			 this.perfil = new Perfil(); 
			 fc.addMessage(null, new FacesMessage("Dados modificados"));
		  }catch(Exception ex) {
			 fc.addMessage(null, new FacesMessage("Error:" + ex.getMessage()));  
		  }
	  }
	
	  public void excluir() {
		 FacesContext fc = FacesContext.getCurrentInstance(); 
		 try {
			 this.perfilDao.excluir(this.perfil);
			 fc.addMessage(null, new FacesMessage("Dados Excluídos ..."));
			 
		 }catch(Exception ex){
			 fc.addMessage(null, new FacesMessage("Error:" + ex.getMessage()));  
			 ex.printStackTrace();
		 }
		  
	  }
	  		

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Perfil> getPerfis() {
		this.perfis = this.perfilDao.listar();
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}
	
	

}
