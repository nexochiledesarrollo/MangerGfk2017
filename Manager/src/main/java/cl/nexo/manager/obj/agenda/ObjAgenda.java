package cl.nexo.manager.obj.agenda;

public class ObjAgenda {
	
    int id_operacion;
    String fecha;
	String lugar;
	String hora;
	int id_usuario;

	
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

}
