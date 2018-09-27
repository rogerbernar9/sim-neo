package Main;

import sim.entity.Departamento;
import sim.entity.Perfil;
import sim.persistence.DepartamentoDAO;
import sim.persistence.PerfilDAO;
import sim.persistence.factory.DAOFactory;

public class Main {
	
	public static void main(String[] args) {
		
		PerfilDAO perfilDAO = DAOFactory.criarPerfilDAO();
		DepartamentoDAO deptoDAO = DAOFactory.criarDeptoDAO();
		
		Departamento d = deptoDAO.carregar(2);
		System.out.println(d);
		//perfilDAO.salvar(new Perfil(null,"Administrador",1));
		
	}

}
