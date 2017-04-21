package cl.nexo.manager.obj.menu;

import java.util.ArrayList;

public class ObjMenuManager {
	int id_menu;
	int id_cliente;
	int tipo_menu;
	String nombre_menu;
	int estado_menu;
	ArrayList<ObjUnidadMenuManager> unidades;
	
	public int getId_menu() {
		return id_menu;
	}
	public void setId_menu(int id_menu) {
		this.id_menu = id_menu;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public int getTipo_menu() {
		return tipo_menu;
	}
	public void setTipo_menu(int tipo_menu) {
		this.tipo_menu = tipo_menu;
	}
	public String getNombre_menu() {
		return nombre_menu;
	}
	public void setNombre_menu(String nombre_menu) {
		this.nombre_menu = nombre_menu;
	}
	public int getEstado_menu() {
		return estado_menu;
	}
	public void setEstado_menu(int estado_menu) {
		this.estado_menu = estado_menu;
	}
	public ArrayList<ObjUnidadMenuManager> getUnidades() {
		return unidades;
	}
	public void setUnidades(ArrayList<ObjUnidadMenuManager> unidades) {
		this.unidades = unidades;
	}

	
}
