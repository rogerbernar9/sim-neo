package sim.util.email;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EnvioEmail {
	
	public static final String HOSTNAME= "smtp.gmail.com";
	public static final String EMISSOR= "abcd@gmail.com";
	public static final String SENHA= "";
	
	public static Email conecta() throws EmailException	{
		Email mail = new SimpleEmail();
		mail.setHostName(HOSTNAME);
		mail.setSmtpPort(465);
		mail.setAuthentication(EMISSOR, SENHA);
		mail.setTLS(true);
		mail.setSSL(true);
		mail.setFrom(EMISSOR);
		return mail;
	}
	
	public static String enviarEmail(String destinatario, String assunto, String mensagem) throws EmailException	{
		try {
			Email mail = new SimpleEmail();
			mail = conecta();
			mail.setSubject(assunto);
			mail.addTo(destinatario);
			mail.setMsg(mensagem);
			mail.send();
		} catch (Exception e) {
			return "erro "+e.getMessage();
		}
		return "email enviado com sucesso";
	}

}
