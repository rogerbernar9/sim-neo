package sim.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sim.entity.Departamento;
import sim.persistence.interfaces.IDepartamentoDAO;

public class DepartamentoDAO implements IDepartamentoDAO {
	
	private Session session;


	@Override
	public void salvar(Departamento depto) {
		this.session.save(depto);
		
	}

	@Override
	public void atualizar(Departamento depto) {
		this.session.update(depto);
	}

	@Override
	public void excluir(Departamento depto) {
		this.session.delete(depto);		
	}

	@Override
	public Departamento carregar(Integer codigo) {
		return (Departamento) 
				this.session.get(Departamento.class, codigo);
	}

	@Override
	public Departamento buscarDepto(String depto) {
		String hsql = "select o from Departamento o where o.nome= :nome";
		Query q = this.session.createQuery(hsql);
		q.setString("nome", depto);
		return (Departamento) q.uniqueResult();
	}

	@Override
	public List<Departamento> listar() {
		return 
				this.session.createCriteria(Departamento.class).list();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
