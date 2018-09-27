package sim.persistence;

import java.util.List;

import org.hibernate.Session;

import org.hibernate.Query;

import sim.entity.Perfil;
import sim.persistence.interfaces.IPerfilDAO;

public class PerfilDAO implements IPerfilDAO {
	
	private Session session;

	@Override
	public void salvar(Perfil perfil) {
		this.session.save(perfil);
	}

	@Override
	public void atualizar(Perfil perfil) {
		this.session.update(perfil);
		
	}

	@Override
	public void excluir(Perfil perfil) {
		this.session.delete(perfil);
		
	}

	@Override
	public Perfil carregar(Integer codigo) {
		return (Perfil) 
				this.session.get(Perfil.class, codigo);

	}

	@Override
	public Perfil buscarPerfil(String nome) {
		String hsql = "select p from Perfil p where p.nome= :nome";
		Query q = this.session.createQuery(hsql);
		q.setString("nome", nome);
		return (Perfil) q.uniqueResult();
	}

	@Override
	public List<Perfil> listar() {
		return
				this.session.createCriteria(Perfil.class).list();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
