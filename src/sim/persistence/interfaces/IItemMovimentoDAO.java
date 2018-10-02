package sim.persistence.interfaces;

import java.util.List;

import sim.entity.ItemMovimento;

public interface IItemMovimentoDAO {
	
	public void salvar(ItemMovimento itemmov);
	
	public void atualizar(ItemMovimento itemmov);
	
	public void excluir(ItemMovimento itemmov);
	
	public ItemMovimento carregar(Integer codigo);
	
	public List<ItemMovimento> listar();

}
