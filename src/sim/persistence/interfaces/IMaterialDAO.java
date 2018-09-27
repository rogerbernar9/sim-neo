package sim.persistence.interfaces;

import java.util.List;

import sim.entity.Material;


public interface IMaterialDAO {
	
	public void salvar(Material material);
	
	public void atualizar(Material material);
	
	public void excluir(Material material);
	
	public Material carregar(Integer codigo);
	
	public List<Material> buscarMaterialPorDescricao(String descricao);

	public Material buscarMaterialPorSimbolo(String simbolo);
	
	public List<Material> listar();

}
