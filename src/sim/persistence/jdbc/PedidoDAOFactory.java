package sim.persistence.jdbc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PedidoDAOFactory {
	
	public static Collection createBeanCollection() {
		PedidoDao dao = new PedidoDao();
		List<Pedido> lista = new ArrayList<Pedido>();
		try {
			lista.add(dao.findByCode(33));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (Collection) lista;
	}

}
