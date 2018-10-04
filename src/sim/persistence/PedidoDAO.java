package sim.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import sim.entity.Pedido;
import sim.entity.Usuario;
import sim.persistence.interfaces.IPedidoDAO;

public class PedidoDAO implements IPedidoDAO {
	
	private Session session;

	@Override
	public void salvar(Pedido pedido) {
		this.session.save(pedido);
	}

	@Override
	public void atualizar(Pedido pedido) {
		this.session.update(pedido);		
	}

	@Override
	public void excluir(Pedido pedido) {
		this.session.delete(pedido);
	}

	@Override
	public Pedido carregar(Integer codigo) {
		return
				(Pedido) this.session
					.get(Pedido.class, codigo);
	}

	@Override
	public List<Pedido> listar() {
		return this.session
				.createCriteria(Pedido.class)
					.list();
	}

	@Override
	public List<Pedido> listarPorUsuario(Usuario u) {
		String hsql="select p from Pedido p inner join p.usuario as u where u.codigo = :codigo and "
				+ "p.status not in ('Atendida','Aguardando entrega','Aguardando retirada','Cancelado');";
		Query q = this.session.createQuery(hsql);
		q.setInteger("codigo", u.getCodigo());
		return q.list();
	}

	@Override
	public List<Pedido> listarPorSetor(Usuario u) {
		String hsql = "select p from Pedido p inner join p.usuario as u where u.setor = :setor ";
		
		Query q = this.session.createQuery(hsql);
		q.setString("setor", u.getSetor());
		return q.list();
	}

	@Override
	public List<Pedido> listarPorSetorPendentes(Usuario u) {
		String hsql = "select p from Pedido p inner join p.usuario as up where up.setor = :setor and p.status in ('Emitido')";
		
		Query q = this.session.createQuery(hsql);
		q.setString("setor", u.getSetor());
		return q.list();
	}

	@Override
	public List<Pedido> listarPedidoEmEstudo() {
		String hsql = "select p from Pedido p where p.status = 'Em estudo de material'";
		Query q = this.session.createQuery(hsql);
		
		return q.list();
	}
	
	@Override
	public List<Pedido> listarPedidoEmObtencao() {
		String hsql = "select p from Pedido p where p.status = 'Em obtenção'";
		Query q = this.session.createQuery(hsql);
		
		return q.list();
	}
	
	@Override
	public List<Pedido> listarPedidoEmEntrega() {
		String hsql = "select p from Pedido p where p.status = 'Aguardando entrega'";
		Query q = this.session.createQuery(hsql);
		
		return q.list();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
