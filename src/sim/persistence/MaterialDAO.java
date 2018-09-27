package sim.persistence;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Query;
import sim.entity.Material;
import sim.persistence.interfaces.IMaterialDAO;

public class MaterialDAO implements IMaterialDAO {
	
	private Session session;

	@Override
	public void salvar(Material material) {
		session.save(material);		
	}

	@Override
	public void atualizar(Material material) {
		session.update(material);
	}

	@Override
	public void excluir(Material material) {
		session.delete(material);
	}

	@Override
	public Material carregar(Integer codigo) {
		return (Material) 
				this.session.get(Material.class, codigo);
	}

	@Override
	public List<Material> buscarMaterialPorDescricao(String descricao) {
		String hsql = "select m from Material m where m.descricao=:descricao order by m.descricao asc";
		Query q = this.session.createQuery(hsql);
		q.setString("descricao", descricao);
		return q.list();
	}

	@Override
	public Material buscarMaterialPorSimbolo(String simbolo) {
		String hsql = "select m from Material m where m.simbolo=:simbolo";
		Query q = this.session.createQuery(hsql);
		q.setString("simbolo", simbolo);
		return (Material) q.uniqueResult();
	}

	@Override
	public List<Material> listar() {
		return this.session
				.createCriteria(Material.class)
					.list();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
