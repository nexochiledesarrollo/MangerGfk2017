package cl.nexo.manager.access.server.mail;

public interface AccessServerMail {

	public void sendMail(String mailFrom, String mailTo, String asunto, String body, String typeBody);

}
