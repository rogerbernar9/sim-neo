package sim.persistence;

import java.util.List;

import org.hibernate.Session;

import sim.entity.Endereco;
import sim.persistence.interfaces.IEnderecoDAO;

public class EnderecoDAO implements IEnderecoDAO {

	private Session session;
	
	@Override
	public void salvar(Endereco endereco) {
		this.session.save(endereco);
	}

	@Override
	public void atualizar(Endereco endereco) {
		this.session.update(endereco);
	}

	@Override
	public void excluir(Endereco endereco) {
		this.session.delete(endereco);
	}

	@Override
	public Endereco carregar(Integer codigo) {
		return (Endereco) 
				this.session.get(Endereco.class, codigo);
	}

	@Override
	public List<Endereco> listar() {
		return this.session
				.createCriteria(Endereco.class)
					.list();
	}

	@Override
	public Endereco buscarDepto(String depto) {
		// TODO Auto-generated method stub
		return null;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
