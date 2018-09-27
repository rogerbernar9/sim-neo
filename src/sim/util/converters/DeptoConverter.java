package sim.util.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import sim.entity.Departamento;
import sim.persistence.DepartamentoDAO;
import sim.persistence.factory.DAOFactory;

@FacesConverter(value="DeptoConverter")
public class DeptoConverter implements Converter {
 
    @Override
    public Object getAsObject(FacesContext arg0, UIComponent arg1, String key) {
		if (key != null && key.trim().length() > 0) {
			Integer codigo = Integer.valueOf(key);
			try {
				DepartamentoDAO deptoDao = DAOFactory.criarDeptoDAO();
				return deptoDao.carregar(codigo);
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
			Departamento depto = (Departamento) arg2;
			return depto.getCodigo().toString();
		}
		return "";
        	
    }
}