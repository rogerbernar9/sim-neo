package sim.persistence.interfaces;

import java.util.List;

import sim.entity.Perfil;

public interface IPerfilDAO {
	
	public void salvar(Perfil perfil);
	
	public void atualizar(Perfil perfil);
	
	public void excluir(Perfil perfil);
	
	public Perfil carregar(Integer codigo);
	
	public Perfil buscarPerfil(String perfil);
	
	public List<Perfil> listar();
	
}
