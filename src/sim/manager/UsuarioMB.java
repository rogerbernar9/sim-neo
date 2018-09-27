package sim.manager;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import sim.entity.Departamento;
import sim.entity.Perfil;
import sim.entity.Usuario;
import sim.persistence.DepartamentoDAO;
import sim.persistence.PerfilDAO;
import sim.persistence.UsuarioDAO;
import sim.persistence.factory.DAOFactory;

@ManagedBean(name="usuarioMB")
@RequestScoped
public class UsuarioMB {
	
	private Usuario usuario;
	private UsuarioDAO usuarioDao;
	private DepartamentoDAO deptoDao;
	private PerfilDAO perfilDAO;
	private List<Usuario> usuarios;
	private List<Departamento> deptos;
	private List<Perfil> perfis;
	
	public UsuarioMB() {
		this.usuario = new Usuario();
		this.usuarioDao = DAOFactory.criarUsuarioDAO();
		this.deptoDao = DAOFactory.criarDeptoDAO();
		this.perfilDAO = DAOFactory.criarPerfilDAO();				
	}
	
	
	public void gravar() {
		  FacesContext fc = FacesContext.getCurrentInstance();
		  try {
			 this.usuarioDao.salvar(this.usuario);
			 this.usuario = new Usuario(); 
			 fc.addMessage(null, new FacesMessage("Dados gravados"));
		  }catch(Exception ex) {
			 fc.addMessage(null, new FacesMessage("Error:" + ex.getMessage()));  
		  }
	  }
	
	public void editar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			this.usuarioDao.atualizar(this.usuario);
			this.usuario = new Usuario(); 
			fc.addMessage(null, new FacesMessage("Dados modificados"));
		}catch(Exception ex) {
			fc.addMessage(null, new FacesMessage("Error:" + ex.getMessage()));  
		}
	}
	
	  public void excluir() {
		 FacesContext fc = FacesContext.getCurrentInstance(); 
		 try {
			 this.usuarioDao.excluir(this.usuario);
			 fc.addMessage(null, new FacesMessage("Dados Excluídos ..."));
			 
		 }catch(Exception ex){
			 ex.printStackTrace();
		 }
		  
	  }


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioDAO getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDAO usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public List<Usuario> getUsuarios() {
		this.usuarios = usuarioDao.listar();
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	public DepartamentoDAO getDeptoDao() {
		return deptoDao;
	}


	public void setDeptoDao(DepartamentoDAO deptoDao) {
		this.deptoDao = deptoDao;
	}


	public PerfilDAO getPerfilDAO() {
		return perfilDAO;
	}


	public void setPerfilDAO(PerfilDAO perfilDAO) {
		this.perfilDAO = perfilDAO;
	}


	public List<Departamento> getDeptos() {
		this.deptos = deptoDao.listar();
		return deptos;
	}


	public void setDeptos(List<Departamento> deptos) {
		this.deptos = deptos;
	}


	public List<Perfil> getPerfis() {
		this.perfis = perfilDAO.listar();
		return perfis;
	}


	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

}
