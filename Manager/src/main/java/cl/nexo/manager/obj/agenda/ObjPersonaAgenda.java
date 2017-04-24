package cl.nexo.manager.obj.agenda;

import cl.nexo.manager.obj.login.ObjLoginUser;

public class ObjPersonaAgenda {
	
    int id_agenda;
    ObjLoginUser usuario;
	String email;
	int id_usuario;
	String asiste;
	
	
	public int getid_agenda() {
		return id_agenda;
	}
	public void setid_agenda(int id_agenda) {
		this.id_agenda = id_agenda;
	}
	public ObjLoginUser getUsuario() {
		return usuario;
	}
	public void setUsuario(ObjLoginUser usuario) {
		this.usuario = usuario;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	public String getAsiste() {
		return asiste;
	}
	public void setAsiste(String asiste) {
		this.asiste = asiste;
	}	

	
	
}
