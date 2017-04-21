package cl.nexo.manager.obj.reunion;

import cl.nexo.manager.obj.login.ObjLoginUser;

public class ObjReunionKickOff {
	
   int id_reunion;
   int id_operacion;
   String f_ini_campo;
   String f_fin_campo;
   String f_ini_bbdd;
   String f_fin_bbdd;
   String f_entrega;
   ObjLoginUser director_campo;
   ObjLoginUser jefe_calidad;
   ObjLoginUser procesamiemto;
   ObjLoginUser scripting;
   
   
public int getId_reunion() {
	return id_reunion;
}
public void setId_reunion(int id_reunion) {
	this.id_reunion = id_reunion;
}
public int getId_operacion() {
	return id_operacion;
}
public void setId_operacion(int id_operacion) {
	this.id_operacion = id_operacion;
}
public String getF_ini_campo() {
	return f_ini_campo;
}
public void setF_ini_campo(String f_ini_campo) {
	this.f_ini_campo = f_ini_campo;
}
public String getF_fin_campo() {
	return f_fin_campo;
}
public void setF_fin_campo(String f_fin_campo) {
	this.f_fin_campo = f_fin_campo;
}
public String getF_ini_bbdd() {
	return f_ini_bbdd;
}
public void setF_ini_bbdd(String f_ini_bbdd) {
	this.f_ini_bbdd = f_ini_bbdd;
}
public String getF_fin_bbdd() {
	return f_fin_bbdd;
}
public void setF_fin_bbdd(String f_fin_bbdd) {
	this.f_fin_bbdd = f_fin_bbdd;
}
public String getF_entrega() {
	return f_entrega;
}
public void setF_entrega(String f_entrega) {
	this.f_entrega = f_entrega;
}
public ObjLoginUser getDirector_campo() {
	return director_campo;
}
public void setDirector_campo(ObjLoginUser director_campo) {
	this.director_campo = director_campo;
}
public ObjLoginUser getJefe_calidad() {
	return jefe_calidad;
}
public void setJefe_calidad(ObjLoginUser jefe_calidad) {
	this.jefe_calidad = jefe_calidad;
}
public ObjLoginUser getProcesamiemto() {
	return procesamiemto;
}
public void setProcesamiemto(ObjLoginUser procesamiemto) {
	this.procesamiemto = procesamiemto;
}
public ObjLoginUser getScripting() {
	return scripting;
}
public void setScripting(ObjLoginUser scripting) {
	this.scripting = scripting;
}
   
   
	
	
}
