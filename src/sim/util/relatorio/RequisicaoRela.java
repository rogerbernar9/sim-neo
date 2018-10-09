package sim.util.relatorio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import sim.persistence.jdbc.Pedido;
import sim.persistence.jdbc.PedidoDao;


public class RequisicaoRela extends RelatorioUtils {

	@Override
	public Collection preencheObjeto(Integer codigo) {
		PedidoDao dao = new PedidoDao();
		List<Pedido> lista = new ArrayList<Pedido>();
		try {
			lista.add(dao.findByCode(codigo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (Collection) lista;
	}

}
