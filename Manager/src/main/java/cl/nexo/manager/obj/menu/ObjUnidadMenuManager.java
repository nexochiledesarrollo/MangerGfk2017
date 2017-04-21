package cl.nexo.manager.obj.menu;

import java.util.ArrayList;

public class ObjUnidadMenuManager {
	int id_unidad;
	int id_menu;
	String nombre_unidad;
	String icono_unidad;
	int orden_unidad;
	int estado_unidad;
	int box_unidad;
	ArrayList<ObjModuloMenuManager> modulos;
	public int getId_unidad() {
		return id_unidad;
	}
	public void setId_unidad(int id_unidad) {
		this.id_unidad = id_unidad;
	}
	public int getId_menu() {
		return id_menu;
	}
	public void setId_menu(int id_menu) {
		this.id_menu = id_menu;
	}
	public String getNombre_unidad() {
		return nombre_unidad;
	}
	public void setNombre_unidad(String nombre_unidad) {
		this.nombre_unidad = nombre_unidad;
	}
	public String getIcono_unidad() {
		return icono_unidad;
	}
	public void setIcono_unidad(String icono_unidad) {
		this.icono_unidad = icono_unidad;
	}
	public int getOrden_unidad() {
		return orden_unidad;
	}
	public void setOrden_unidad(int orden_unidad) {
		this.orden_unidad = orden_unidad;
	}
	public int getEstado_unidad() {
		return estado_unidad;
	}
	public void setEstado_unidad(int estado_unidad) {
		this.estado_unidad = estado_unidad;
	}
	public int getBox_unidad() {
		return box_unidad;
	}
	public void setBox_unidad(int box_unidad) {
		this.box_unidad = box_unidad;
	}
	public ArrayList<ObjModuloMenuManager> getModulos() {
		return modulos;
	}
	public void setModulos(ArrayList<ObjModuloMenuManager> modulos) {
		this.modulos = modulos;
	}
	
	
	
}
