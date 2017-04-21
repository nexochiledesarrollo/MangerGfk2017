package cl.nexo.manager.obj.asignacion.usuario;

public class ObjAsignUserDetalle {
	int id_operacion;
	int id_usuario;
	String fecha;
	int cantidad;
	
	
	public int getId_operacion() {
		return id_operacion;
	}
	public void setId_operacion(int id_operacion) {
		this.id_operacion = id_operacion;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	
}
