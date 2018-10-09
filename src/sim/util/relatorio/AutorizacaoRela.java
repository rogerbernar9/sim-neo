package sim.util.relatorio;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import sim.persistence.relatorio.jdbc.AutorizacaoDao;
import sim.persistence.relatorio.jdbc.AutorizacaoFornecimento;

public class AutorizacaoRela extends RelatorioUtils {

	@Override
	public Collection preencheObjeto(Integer codigo) {
		AutorizacaoDao dao = new AutorizacaoDao();
		List<AutorizacaoFornecimento> lista = new ArrayList<AutorizacaoFornecimento>();
		try {
			lista.add(dao.findByCode(codigo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (Collection) lista;
	}

}
