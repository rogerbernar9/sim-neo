package sim.persistence;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Query;
import sim.entity.UnidadeFornecimento;
import sim.persistence.interfaces.IUnidadeFornecimentoDAO;

public class UnidadeFornecimentoDAO implements IUnidadeFornecimentoDAO {
	
	private Session session;

	@Override
	public void salvar(UnidadeFornecimento uf) {
		this.session.save(uf);	
	}

	@Override
	public void atualizar(UnidadeFornecimento uf) {
		this.session.update(uf);
	}

	@Override
	public void excluir(UnidadeFornecimento uf) {
		this.session.delete(uf);
	}

	@Override
	public UnidadeFornecimento carregar(Integer codigo) {
		return
				(UnidadeFornecimento) this.session.get(UnidadeFornecimento.class, codigo);
	}

	@Override
	public UnidadeFornecimento buscarUfPorDescricao(String descricao) {
		String hsql = "select u from UnidadeFornecimento u where u.descricao= :descricao";
		Query q = this.session.createQuery(hsql);
		q.setString("descricao", descricao);
		return (UnidadeFornecimento) q.uniqueResult();
	}

	@Override
	public UnidadeFornecimento buscarUfPorSimbolo(String simbolo) {
		String hsql = "select u from UnidadeFornecimento u where u.simboloUnidade= :simbolo";
		Query q = this.session.createQuery(hsql);
		q.setString("simbolo", simbolo);
		return (UnidadeFornecimento) q.uniqueResult();
	}

	@Override
	public List<UnidadeFornecimento> listar() {
		return
				this.session.createCriteria(UnidadeFornecimento.class).list();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
