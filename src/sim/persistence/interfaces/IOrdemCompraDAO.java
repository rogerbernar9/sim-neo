package sim.persistence.interfaces;

import java.util.List;

import sim.entity.OrdemCompra;

public interface IOrdemCompraDAO {
	
	public void salvar(OrdemCompra oc);
	
	public void atualizar(OrdemCompra oc);
	
	public void excluir(OrdemCompra oc);
	
	public OrdemCompra carregar(Integer codigo);
	
	public OrdemCompra carregarPorNumPedido(Integer numPedido);
	
	public List<OrdemCompra> listar();
	
	public List<OrdemCompra> listarPorSetor(String setor);

}
