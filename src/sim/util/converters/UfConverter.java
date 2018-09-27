package sim.util.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import sim.entity.UnidadeFornecimento;
import sim.persistence.UnidadeFornecimentoDAO;
import sim.persistence.factory.DAOFactory;

@FacesConverter(value="UfConverter")
public class UfConverter implements Converter {
 
    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String key) {
		if (key != null && key.trim().length() > 0) {
			Integer codigo = Integer.valueOf(key);
			try {
				UnidadeFornecimentoDAO ufDao = DAOFactory.criarUfDAO();
				return ufDao.carregar(codigo);
			} catch (Exception e) {
				e.printStackTrace();

				throw new ConverterException("Não foi possível encontrar a categoria de código " + key + "." + e.getMessage());
			}
		}
		return null;
    }
 
    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		if (arg2 != null) {
			UnidadeFornecimento uf = (UnidadeFornecimento) arg2;
			return uf.getCodigo().toString();
		}
		return "";
        	
    }
}