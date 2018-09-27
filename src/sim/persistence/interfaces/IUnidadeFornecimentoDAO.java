package sim.persistence.interfaces;

import java.util.List;
import sim.entity.UnidadeFornecimento;


public interface IUnidadeFornecimentoDAO {
	
	public void salvar(UnidadeFornecimento uf);
	
	public void atualizar(UnidadeFornecimento uf);
	
	public void excluir(UnidadeFornecimento uf);
	
	public UnidadeFornecimento carregar(Integer codigo);
	
	public UnidadeFornecimento buscarUfPorDescricao(String descricao);

	public UnidadeFornecimento buscarUfPorSimbolo(String simbolo);
	
	public List<UnidadeFornecimento> listar();
	

}
