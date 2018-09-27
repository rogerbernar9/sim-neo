package sim.persistence;

import java.util.List;

import org.hibernate.Session;

import sim.entity.Fornecedor;
import sim.persistence.interfaces.IFornecedorDAO;

public class FornecedorDAO implements IFornecedorDAO {
	
	private Session session;

	@Override
	public void salvar(Fornecedor fornecedor) {
		this.session.save(fornecedor);
		
	}

	@Override
	public void atualizar(Fornecedor fornecedor) {
		this.session.update(fornecedor);
	}

	@Override
	public void excluir(Fornecedor fornecedor) {
		this.session.delete(fornecedor);
	}

	@Override
	public Fornecedor carregar(Integer codigo) {
		return (Fornecedor) 
				this.session.get(Fornecedor.class, codigo);
	}

	@Override
	public Fornecedor buscarFornecedorPorNome(String nome) {
		return (Fornecedor) 
				this.session.get(Fornecedor.class, nome);
	}

	@Override
	public List<Fornecedor> listar() {
		return this.session
				.createCriteria(Fornecedor.class)
					.list();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
