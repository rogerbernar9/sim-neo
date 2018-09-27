package sim.persistence.interfaces;

import java.util.List;

import sim.entity.Departamento;

public interface IDepartamentoDAO {
	
	public void salvar(Departamento depto);
	
	public void atualizar(Departamento depto);
	
	public void excluir(Departamento depto);
	
	public Departamento carregar(Integer codigo);
	
	public Departamento buscarDepto(String depto);
	
	public List<Departamento> listar();

}
