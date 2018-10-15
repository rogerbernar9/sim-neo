package sim.util.relatorio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.engine.query.JRHibernateQueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRLoader;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;


public abstract class RelatorioUtils {
	
	public StreamedContent geraRelatorio(HashMap parametrosRelatorio, String nomeRelatorioJasper, String nomeRelatorioSaida, Integer codigo)	{
			StreamedContent arquivoRetorno = null;

			try {
				FacesContext context = FacesContext.getCurrentInstance();
				String caminhoRelatorio = context.getExternalContext().getRealPath("relatorio");
				String caminhoArquivoJasper = caminhoRelatorio + File.separator + nomeRelatorioJasper;
				String caminhoArquivoRelatorio = null;
				
				
				JasperReport relatorioJasper = (JasperReport) JRLoader.loadObject(new File(caminhoArquivoJasper));
				//JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, new HashMap(), new JRBeanCollectionDataSource(preencheObjeto(codigo)));
				JasperPrint impressoraJasper = JasperFillManager.fillReport(relatorioJasper, new HashMap(), new JRBeanCollectionDataSource(preencheObjeto(codigo), false));

				JRExporter tipoArquivoExportado = null;
				String extensaoArquivoExportado = "";
				File arquivoGerado = null;
				
				tipoArquivoExportado = new JRPdfExporter();
				extensaoArquivoExportado = "pdf";

				caminhoArquivoRelatorio = caminhoRelatorio + File.separator + nomeRelatorioSaida + "." + extensaoArquivoExportado;
				arquivoGerado = new java.io.File(caminhoArquivoRelatorio);
				tipoArquivoExportado.setParameter(JRExporterParameter.JASPER_PRINT, impressoraJasper);
				tipoArquivoExportado.setParameter(JRExporterParameter.OUTPUT_FILE, arquivoGerado);
				tipoArquivoExportado.exportReport();
				arquivoGerado.deleteOnExit();

				InputStream conteudoRelatorio = new FileInputStream(arquivoGerado);
				arquivoRetorno = new DefaultStreamedContent(conteudoRelatorio, "application/" + extensaoArquivoExportado, nomeRelatorioSaida + "." + extensaoArquivoExportado);
			} catch (JRException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (Exception e)	{
				e.printStackTrace();
			}
			return arquivoRetorno;
		}	
	
	public void executaRelatorio(HashMap parametrosRelatorio, String nomeRelatorioJasper, Integer codigo) throws Exception	{
		FacesContext context = FacesContext.getCurrentInstance();
		try	{
			HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
			InputStream reportStream = context.getExternalContext().getResourceAsStream("/relatorio/"+nomeRelatorioJasper);
			
			response.setContentType("application/pdf");
			
			ServletOutputStream servletOutputStream = response.getOutputStream();
			
			JRBeanCollectionDataSource fonteDados = new JRBeanCollectionDataSource(preencheObjeto(codigo), false);
			
			JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, parametrosRelatorio, fonteDados);  
			
			servletOutputStream.flush();
			servletOutputStream.close();
			
		} catch(Exception e)	{
			e.printStackTrace();
		} finally	{
			context.responseComplete();
		}
	}

	
	
	public abstract Collection preencheObjeto(Integer codigo);



}
