package cl.nexo.manager.imp.server.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.access.server.mail.AccessServerMail;

public class ServerMail implements AccessServerMail{
	
	private static final Logger logger = Logger.getLogger(ServerMail.class);
	
	
	@Override
	public void sendMail(String mailFrom, String mailTo, String asunto, String body , String typeBody){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		
		String aux_username = tools.getValorConfigById(5);
		String aux_password = tools.getValorConfigById(6);
		String aux_smtpAuth = tools.getValorConfigById(3);
		String aux_tls = tools.getValorConfigById(4);
		String aux_host = tools.getValorConfigById(1);
		String aux_port = tools.getValorConfigById(2);
		
		logger.info("Host: "+ aux_host);
		
		final String username = aux_username;
		final String password = aux_password;

		Properties props = new Properties();
		props.put("mail.smtp.auth", aux_smtpAuth);
		props.put("mail.smtp.starttls.enable", aux_tls);
	 	props.put("mail.smtp.host", aux_host);
		props.put("mail.smtp.port", aux_port);
		
		Session session = Session.getInstance(props,  new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
		});
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mailFrom));
			message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(mailTo));
			message.setSubject(asunto);
			message.setSentDate(new Date());
			if(typeBody.equals("txt")){
				message.setText(body);
			}else if(typeBody.equals("html")){
				message.setContent(body, "text/html");
			}
				
			Transport.send(message);
			logger.info("Mail enviado");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
