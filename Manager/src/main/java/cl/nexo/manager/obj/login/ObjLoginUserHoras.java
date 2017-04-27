package cl.nexo.manager.obj.login;

public class ObjLoginUserHoras {
	
	ObjLoginUser usuario;
	String Fecha;
	int horas_ocupadas;
	int id_operacion;
	int horas_disponibles;
	String Asigna;
	
	public ObjLoginUser getUsuario() {
		return usuario;
	}
	public void setUsuario(ObjLoginUser usuario) {
		this.usuario = usuario;
	}
	
	public int getId_operacion() {
		return id_operacion;
	}
	public void setId_operacion(int id_operacion) {
		this.id_operacion = id_operacion;
	}
	public int getHoras_ocupadas() {
		return horas_ocupadas;
	}
	public void setHoras_ocupadas(int horas_ocupadas) {
		this.horas_ocupadas = horas_ocupadas;
	}
	public int getHoras_disponibles() {
		return horas_disponibles;
	}
	public void setHoras_disponibles(int horas_disponibles) {
		this.horas_disponibles = horas_disponibles;
	}
	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	public String getAsigna() {
		return Asigna;
	}
	public void setAsigna(String asigna) {
		Asigna = asigna;
	}
	
	
	
	
}
