package sim.persistence.interfaces;

import java.util.List;

import sim.entity.ItemEstoque;


public interface IItemEstoqueDAO {
	
	public void salvar(ItemEstoque iestoque);
	
	public void atualizar(ItemEstoque iestoque);
	
	public void excluir(ItemEstoque iestoque);
	
	public ItemEstoque carregar(Integer codigo);
	
	public List<ItemEstoque> buscarItemEstoquePorSimbolo(String simbolo);
	
	public List<ItemEstoque> listar();


}
