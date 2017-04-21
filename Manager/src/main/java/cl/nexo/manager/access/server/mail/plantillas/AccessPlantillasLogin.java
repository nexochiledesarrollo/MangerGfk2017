package cl.nexo.manager.access.server.mail.plantillas;

public interface AccessPlantillasLogin {

	
	public String updatePassByAdmin(String nombre, String usuario, String clave, int id);

	public String sendNewLogin(String nombre, String usuario, String clave, String id);

	public String updatePassLogin(String nombre, String usuario, String clave,	String id);

}
