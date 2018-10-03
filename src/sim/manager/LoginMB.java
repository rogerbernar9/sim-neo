package sim.manager;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import sim.entity.Usuario;
import sim.persistence.UsuarioDAO;
import sim.persistence.factory.DAOFactory;
import sim.util.criptografia.Criptografia;

@ManagedBean(name="loginMB")
@SessionScoped
public class LoginMB {
	
	private String nomeUsuario;
	private String senhaUsuario;
	private Usuario usuario;
	private UsuarioDAO usuarioDao;
	private boolean logado = false;
	

	public LoginMB() {
		
	}
	
	public String logar()	{
		this.usuarioDao = DAOFactory.criarUsuarioDAO();
		FacesContext fc = FacesContext.getCurrentInstance();
		this.usuario = this.usuarioDao
							.buscarPorLogin(nomeUsuario, Criptografia.criptografia(senhaUsuario));
		
		if(usuario != null)	{
			fc.addMessage(null, new FacesMessage("Usuário logado com sucesso"));
			this.logado = true;
			return "/faces/restrict/pedido.xhtml";
		}	else	{
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuário não encontrado!",""));  
			return "";

		}
	}
	
	public String logout()	{
		this.usuario = null;
		this.logado = false;
		return "/faces/public/login.xhtml";
	}
	

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public UsuarioDAO getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDAO usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
