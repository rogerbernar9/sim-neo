package sim.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import sim.entity.ItemEstoque;
import sim.persistence.interfaces.IItemEstoqueDAO;

public class ItemEstoqueDAO implements IItemEstoqueDAO {
	
	private Session session;

	@Override
	public void salvar(ItemEstoque iestoque) {
		this.session.save(iestoque);
	}

	@Override
	public void atualizar(ItemEstoque iestoque) {
		this.session.update(iestoque);
	}

	@Override
	public void excluir(ItemEstoque iestoque) {
		this.session.update(iestoque);
	}

	@Override
	public ItemEstoque carregar(Integer codigo) {

		return (ItemEstoque)
				this
					.session
						.get(ItemEstoque.class, codigo);
	}

	@Override
	public List<ItemEstoque> buscarItemEstoquePorSimbolo(String simbolo) {
		String hsql = "select ie from ItemEstoque ie inner join ie.material as m where m.simbolo = :simbolo";
		Query q = this.session.createQuery(hsql);
		q.setString("simbolo", simbolo);
		return q.list();
	}

	@Override
	public List<ItemEstoque> listar() {
		return this
					.session
						.createCriteria(ItemEstoque.class)
							.list();
	}


	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
