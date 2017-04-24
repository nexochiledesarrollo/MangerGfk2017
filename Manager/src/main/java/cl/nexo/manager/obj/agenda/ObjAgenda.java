package cl.nexo.manager.obj.agenda;

public class ObjAgenda {
	
	int id_agenda;
    int id_operacion;
    String fecha;
	String lugar;
	String hora;
	int id_usuario;
	int id_estado_agenda;


	
	public int getId_agenda() {
		return id_agenda;
	}

	public void setId_agenda(int id_agenda) {
		this.id_agenda = id_agenda;
	}

	public int getId_operacion() {
		return id_operacion;
	}
	
	public void setId_operacion(int id_operacion) {
		this.id_operacion = id_operacion;
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

	public int getId_estado_agenda() {
		return id_estado_agenda;
	}

	public void setId_estado_agenda(int id_estado_agenda) {
		this.id_estado_agenda = id_estado_agenda;
	}
	
	
	
	

}
