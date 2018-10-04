package sim.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import sim.entity.OrdemCompra;
import sim.persistence.interfaces.IOrdemCompraDAO;

public class OrdemCompraDAO implements IOrdemCompraDAO {
	
	private Session session;

	@Override
	public void salvar(OrdemCompra oc) {
		this.session.save(oc);		
	}

	@Override
	public void atualizar(OrdemCompra oc) {
		this.session.update(oc);
	}

	@Override
	public void excluir(OrdemCompra oc) {
		this.session.delete(oc);		
	}

	@Override
	public OrdemCompra carregar(Integer codigo) {
		return (OrdemCompra) 
				this
					.session
						.get(OrdemCompra.class, codigo);	
	}

	@Override
	public OrdemCompra carregarPorNumPedido(Integer numPedido) {
		String hsql = "select oc from OrdemCompra oc inner join oc.pedido as p where oc.id_pedido = :numeroPedido";
		Query q = this.session.createQuery(hsql);
		return (OrdemCompra) q.uniqueResult();
	}
	
	@Override
	public List<OrdemCompra> listarPorSetor(String setor) {
		String hsql = "select oc from OrdemCompra oc inner join oc.usuario as u where u.setor = :setor";
		Query q = this.session.createQuery(hsql);
		q.setString("setor", setor);
		return q.list();
	}

	@Override
	public List<OrdemCompra> listar() {
		return this
					.session
						.createCriteria(OrdemCompra.class)
							.list();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
