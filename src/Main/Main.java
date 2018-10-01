package Main;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import sim.entity.Departamento;
import sim.entity.ItemEstoque;
import sim.entity.Perfil;
import sim.persistence.DepartamentoDAO;
import sim.persistence.HibernateUtil;
import sim.persistence.ItemEstoqueDAO;
import sim.persistence.PerfilDAO;
import sim.persistence.factory.DAOFactory;

public class Main {
	
	public static void main(String[] args) {
		
		Date data = new Date();
		data.toString();
		System.out.println(data.toString());
		ItemEstoqueDAO ieDAO = DAOFactory.criarItemEstoqueDAO();
//		ItemEstoque ie = ieDAO.buscarItemEstoquePorSimbolo("34-a-3453-454");
//			System.out.println(ie.toString());
		
//		DepartamentoDAO deptoDAO = DAOFactory.criarDeptoDAO();
//		
//		Departamento d = deptoDAO.carregar(2);
//		System.out.println(d);
		//perfilDAO.salvar(new Perfil(null,"Administrador",1));
		
	}

}
