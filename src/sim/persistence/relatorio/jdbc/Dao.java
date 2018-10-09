package sim.persistence.relatorio.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



public class Dao {

	Connection con;
	PreparedStatement stmt;
	ResultSet rs;
	
	public Connection open() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/simneo", "root", "");
		return con;
	}
	
	public void close() throws Exception{
		con.close();
	}
}
