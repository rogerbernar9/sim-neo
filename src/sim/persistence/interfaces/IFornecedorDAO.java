package sim.persistence.interfaces;

import java.util.List;

import sim.entity.Fornecedor;

public interface IFornecedorDAO {
	
	public void salvar(Fornecedor fornecedor);
	
	public void atualizar(Fornecedor fornecedor);
	
	public void excluir(Fornecedor fornecedor);
	
	public Fornecedor carregar(Integer codigo);
	
	public Fornecedor buscarFornecedorPorNome(String nome);
	
	public List<Fornecedor> listar();

}
