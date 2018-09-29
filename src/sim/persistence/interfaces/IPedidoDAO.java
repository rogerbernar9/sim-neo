package sim.persistence.interfaces;

import java.util.List;
import sim.entity.Pedido;
import sim.entity.Usuario;

public interface IPedidoDAO {

	public void salvar(Pedido pedido);
	
	public void atualizar(Pedido pedido);
	
	public void excluir(Pedido pedido);
	
	public Pedido carregar(Integer codigo);
	
	public List<Pedido> listar();
	
	public List<Pedido> listarPorUsuario(Usuario u);
	
	public List<Pedido> listarPorSetor(Usuario u);
	
	public List<Pedido> listarPorSetorPendentes(Usuario u);
	
	public List<Pedido> listarPedidoEmEstudo();

}
