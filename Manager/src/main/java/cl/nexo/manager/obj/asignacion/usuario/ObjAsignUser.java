package cl.nexo.manager.obj.asignacion.usuario;

import java.util.ArrayList;

public class ObjAsignUser {
	
	int id_operacion;
	int id_usuario;
	String str_usuario;
	int cargo_usuario;
	String str_cargo;
	int total_asignado;
	ArrayList<ObjAsignUserDetalle> detalle;
	
	
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
	public String getStr_usuario() {
		return str_usuario;
	}
	public void setStr_usuario(String str_usuario) {
		this.str_usuario = str_usuario;
	}
	public int getCargo_usuario() {
		return cargo_usuario;
	}
	public void setCargo_usuario(int cargo_usuario) {
		this.cargo_usuario = cargo_usuario;
	}
	public String getStr_cargo() {
		return str_cargo;
	}
	public void setStr_cargo(String str_cargo) {
		this.str_cargo = str_cargo;
	}
	public int getTotal_asignado() {
		return total_asignado;
	}
	public void setTotal_asignado(int total_asignado) {
		this.total_asignado = total_asignado;
	}
	public ArrayList<ObjAsignUserDetalle> getDetalle() {
		return detalle;
	}
	public void setDetalle(ArrayList<ObjAsignUserDetalle> detalle) {
		this.detalle = detalle;
	}
	
	
	
}
