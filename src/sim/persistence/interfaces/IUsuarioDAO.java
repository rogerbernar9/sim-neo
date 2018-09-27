package sim.persistence.interfaces;

import java.util.List;

import sim.entity.Usuario;

public interface IUsuarioDAO {
	
	public void salvar(Usuario usuario);
	
	public void atualizar(Usuario usuario);
	
	public void excluir(Usuario usuario);
	
	public Usuario carregar(Integer codigo);
	
	public Usuario buscarUsuarioPorEmail(String email);
	
	public List<Usuario> listar();
	
	public List<Usuario> listarUsuarioComDepto();
	
	public Usuario buscarPorLogin(String nome, String senha);

}
