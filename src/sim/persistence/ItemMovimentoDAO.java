package sim.persistence;

import java.util.List;

import org.hibernate.Session;

import sim.entity.ItemMovimento;
import sim.persistence.interfaces.IItemMovimentoDAO;

public class ItemMovimentoDAO implements IItemMovimentoDAO {

	private Session session;
	
	@Override
	public void salvar(ItemMovimento itemmov) {
		this.session.save(itemmov);
	}

	@Override
	public void atualizar(ItemMovimento itemmov) {
		this.session.update(itemmov);
	}

	@Override
	public void excluir(ItemMovimento itemmov) {
		this.session.delete(itemmov);
	}

	@Override
	public ItemMovimento carregar(Integer codigo) {
		return (ItemMovimento) 				
				this
				.session
					.get(ItemMovimento.class, codigo);
	}

	@Override
	public List<ItemMovimento> listar() {
		return this
					.session
						.createCriteria(ItemMovimento.class)
							.list();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
