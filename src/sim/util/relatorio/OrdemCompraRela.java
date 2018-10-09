package sim.util.relatorio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import sim.persistence.relatorio.jdbc.OrdemCompra;
import sim.persistence.relatorio.jdbc.OrdemCompraDao;


public class OrdemCompraRela extends RelatorioUtils {

	@Override
	public Collection preencheObjeto(Integer codigo) {
		OrdemCompraDao dao = new OrdemCompraDao();
		List<OrdemCompra> lista = new ArrayList<OrdemCompra>();
		try {
			lista.add(dao.findByCode(codigo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Collection) lista;
	}
	
}
