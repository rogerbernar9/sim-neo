package sim.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import sim.entity.Usuario;
import sim.persistence.interfaces.IUsuarioDAO;

public class UsuarioDAO implements IUsuarioDAO {
	
	private Session session;

	@Override
	public void salvar(Usuario usuario) {
		// TODO Auto-generated method stub
		this.session.save(usuario);
		
	}

	@Override
	public void atualizar(Usuario usuario) {
		// TODO Auto-generated method stub
		this.session.update(usuario);
		
	}

	@Override
	public void excluir(Usuario usuario) {
		// TODO Auto-generated method stub
		this.session.delete(usuario);
		
	}

	@Override
	public Usuario carregar(Integer codigo) {
		return
				(Usuario) this.session.get(Usuario.class, codigo);
	}

	@Override
	public Usuario buscarUsuarioPorEmail(String email) {
		String hsql= "select u from usuario u where u.email=:email";
		Query q = this.session.createQuery(hsql);
		q.setString(email, "email");
		return (Usuario) q.uniqueResult();
	}

	@Override
	public List<Usuario> listar() {
		return 
				this.session.createCriteria(Usuario.class).list();
	}
	
	@Override
	public Usuario buscarPorLogin(String nome, String senha) {
		String hql = "select u from Usuario u where u.nome = :nome and u.senha = :senha";
		Query q = this.session.createQuery(hql);
		q.setString("nome", nome);
		q.setString("senha", senha);
		return (Usuario) q.uniqueResult();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public List<Usuario> listarUsuarioComDepto() {
		String hsql= "select u from usuario u join departarmento d on u.id_departamento=d.codigo join perfil p on u.id_perfil=p.codigo";
		Query q = this.session.createQuery(hsql);
		return q.list();
	}

}
