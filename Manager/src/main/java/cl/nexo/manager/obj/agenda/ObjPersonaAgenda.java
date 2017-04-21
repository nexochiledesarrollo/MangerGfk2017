package cl.nexo.manager.obj.agenda;

import cl.nexo.manager.obj.login.ObjLoginUser;

public class ObjPersonaAgenda {
	
    int id_operacion;
    ObjLoginUser usuario;
	String fecha;
	String lugar;
	String email;
	String hora;
	int id_usuario;
	String asiste;
	
	
	public int getId_operacion() {
		return id_operacion;
	}
	public void setId_operacion(int id_operacion) {
		this.id_operacion = id_operacion;
	}
	public ObjLoginUser getUsuario() {
		return usuario;
	}
	public void setUsuario(ObjLoginUser usuario) {
		this.usuario = usuario;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
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
