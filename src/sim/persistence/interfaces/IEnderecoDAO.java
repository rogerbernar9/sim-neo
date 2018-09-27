package sim.persistence.interfaces;

import java.util.List;

import sim.entity.Endereco;


public interface IEnderecoDAO {
	
	public void salvar(Endereco endereco);
	
	public void atualizar(Endereco endereco);
	
	public void excluir(Endereco endereco);
	
	public Endereco carregar(Integer codigo);
	
	public Endereco buscarDepto(String depto);
	
	public List<Endereco> listar();

}
