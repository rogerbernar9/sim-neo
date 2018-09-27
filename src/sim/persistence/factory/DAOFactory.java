package sim.persistence.factory;

import sim.persistence.DepartamentoDAO;
import sim.persistence.EnderecoDAO;
import sim.persistence.FornecedorDAO;
import sim.persistence.HibernateUtil;
import sim.persistence.MaterialDAO;
import sim.persistence.PerfilDAO;
import sim.persistence.UnidadeFornecimentoDAO;
import sim.persistence.UsuarioDAO;

public class DAOFactory {
	
	public static PerfilDAO criarPerfilDAO()	{
		PerfilDAO perfilDAO = new PerfilDAO();
		perfilDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		
		return perfilDAO;
	}
	
	public static DepartamentoDAO criarDeptoDAO()	{
		DepartamentoDAO deptoDAO = new DepartamentoDAO();
		deptoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		
		return deptoDAO;
	}
	
	public static UsuarioDAO criarUsuarioDAO()	{
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		
		return usuarioDAO;
	}
	
	public static UnidadeFornecimentoDAO criarUfDAO()	{
		UnidadeFornecimentoDAO ufDAO = new UnidadeFornecimentoDAO();
		ufDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		
		return ufDAO;
	}
	
	public static MaterialDAO criarMaterialDAO()	{
		MaterialDAO materialDAO = new MaterialDAO();
		materialDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		
		return materialDAO;
	}
	
	public static FornecedorDAO criarFornecedorDAO()	{
		FornecedorDAO fornecedorDAO = new FornecedorDAO();
		fornecedorDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		
		return fornecedorDAO;
	}
	
	public static EnderecoDAO criarEnderecoDAO()	{
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		enderecoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
		
		return enderecoDAO;
	}

}
