package sim.manager;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import sim.entity.Endereco;
import sim.entity.Fornecedor;
import sim.persistence.EnderecoDAO;
import sim.persistence.FornecedorDAO;
import sim.persistence.factory.DAOFactory;

@ManagedBean(name="fornecedorMB")
@RequestScoped
public class FornecedorMB {
	
	private Fornecedor fornecedor;
	private FornecedorDAO fornecedorDao;
	private Endereco endereco;
	private EnderecoDAO enderecoDao;
	private List<Fornecedor> fornecedores;
	
	
	public FornecedorMB() {
		this.fornecedor = new Fornecedor();
		this.endereco = new Endereco();
		this.fornecedorDao = DAOFactory.criarFornecedorDAO();
		this.enderecoDao = DAOFactory.criarEnderecoDAO();
	}
	
	
	public void gravar() {
		  FacesContext fc = FacesContext.getCurrentInstance();
		  try {
			 this.enderecoDao.salvar(endereco);
			 this.fornecedor.setEndereco(this.endereco);
			 this.fornecedorDao.salvar(this.fornecedor);
			 this.fornecedor = new Fornecedor(); 
			 this.endereco = new Endereco(); 
			 fc.addMessage(null, new FacesMessage("Dados gravados"));
		  }catch(Exception ex) {
			 fc.addMessage(null, new FacesMessage("Error:" + ex.getMessage()));  
		  }
	  }
	
	public void editar() {
		  FacesContext fc = FacesContext.getCurrentInstance();
		  try {
			  this.enderecoDao.atualizar(this.fornecedor.getEndereco());
			  this.fornecedorDao.atualizar(this.fornecedor);
			 this.fornecedor = new Fornecedor(); 
			 fc.addMessage(null, new FacesMessage("Dados modificados"));
		  }catch(Exception ex) {
			 fc.addMessage(null, new FacesMessage("Error:" + ex.getMessage()));  
		  }
	  }
	
	  public void excluir() {
		 FacesContext fc = FacesContext.getCurrentInstance(); 
		 try {
			 this.fornecedorDao.excluir(this.fornecedor);
			 fc.addMessage(null, new FacesMessage("Dados Excluídos ..."));
			 
		 }catch(Exception ex){
			 ex.printStackTrace();
		 }
		  
	  }

	
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	public FornecedorDAO getFornecedorDao() {
		return fornecedorDao;
	}
	public void setFornecedorDao(FornecedorDAO fornecedorDao) {
		this.fornecedorDao = fornecedorDao;
	}
	public List<Fornecedor> getFornecedores() {
		this.fornecedores = fornecedorDao.listar();
		return fornecedores;
	}
	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}


	public Endereco getEndereco() {
		return endereco;
	}


	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}


	public EnderecoDAO getEnderecoDao() {
		return enderecoDao;
	}


	public void setEnderecoDao(EnderecoDAO enderecoDao) {
		this.enderecoDao = enderecoDao;
	}

}
