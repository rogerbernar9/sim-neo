package sim.manager;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import sim.entity.UnidadeFornecimento;
import sim.persistence.UnidadeFornecimentoDAO;
import sim.persistence.factory.DAOFactory;

@ManagedBean(name="ufMB")
@RequestScoped
public class UnidadeFornecimentoMB {
	
	private UnidadeFornecimentoDAO ufDao;
	private UnidadeFornecimento uf;
	private List<UnidadeFornecimento> unidades;
	
	
	public UnidadeFornecimentoMB() {
		this.ufDao = DAOFactory.criarUfDAO();
		this.uf = new UnidadeFornecimento();
	}
	
	public void gravar()	{
		FacesContext fc = FacesContext.getCurrentInstance();
		try	{
			this.uf.setSimboloUnidade(this.getUf().getSimboloUnidade().toUpperCase());
			this.uf.setDescricao(this.getUf().getDescricao().toUpperCase());

			this.ufDao.salvar(this.uf);
			this.uf = new UnidadeFornecimento();
			fc.addMessage(null, new FacesMessage("Dados gravados"));
		} catch(Exception ex)	{
			fc.addMessage(null, new FacesMessage("Erro na gravacao: "+ex.getMessage()));
		}
	}
	
	public void editar()	{
		FacesContext fc = FacesContext.getCurrentInstance();
		try	{
			this.uf.setSimboloUnidade(this.getUf().getSimboloUnidade().toUpperCase());
			this.uf.setDescricao(this.getUf().getDescricao().toUpperCase());
			this.ufDao.atualizar(this.uf);
			this.uf = new UnidadeFornecimento();
			fc.addMessage(null, new FacesMessage("Dados modificados"));
		} catch(Exception ex)	{
			fc.addMessage(null, new FacesMessage("Erro: "+ex.getMessage()));
		}
	}
	
	public void excluir()	{
		FacesContext fc = FacesContext.getCurrentInstance();
		try	{
			this.ufDao.excluir(this.uf);
			fc.addMessage(null, new FacesMessage("Dados excluídos"));
		} catch(Exception ex)	{
			fc.addMessage(null, new FacesMessage("Erro: "+ex.getMessage()));
		}
	}
	
	
	public List<UnidadeFornecimento> getUnidades() {
		this.unidades = this.ufDao.listar();
		return unidades;
	}
	public void setUnidades(List<UnidadeFornecimento> unidades) {
		this.unidades = unidades;
	}

	public UnidadeFornecimento getUf() {
		return uf;
	}

	public void setUf(UnidadeFornecimento uf) {
		this.uf = uf;
	}

}
