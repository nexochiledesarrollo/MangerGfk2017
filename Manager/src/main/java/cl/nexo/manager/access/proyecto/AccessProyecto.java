package cl.nexo.manager.access.proyecto;



import java.util.ArrayList;

import cl.nexo.manager.obj.proyecto.ObjProyectoEstandar;

public interface AccessProyecto {

	public String getKeyProyecto();
	
	public int getCodOperacion(Long op);

	public Long createProyecto(ObjProyectoEstandar proy);

	public Long createProyectoPadre(ObjProyectoEstandar proy);

	public Long createOperacion(ObjProyectoEstandar proy);

	public Long createProyectoDetalle(ObjProyectoEstandar proy);

	public ObjProyectoEstandar getOperacion(Long id);

	public ObjProyectoEstandar getProyectoPadre(Long id);

	public ObjProyectoEstandar getOperacionDetalle(Long id);

	public ObjProyectoEstandar getProyecto(Long id, int tipo);

	//public ArrayList<ObjProyectoEstandar> getProyecto(int tipo, String id);

	public ArrayList<ObjProyectoEstandar> getProyecto(int tipo, String id, int estado,	int valorEstado);

	public int createProyectoPadreInt(ObjProyectoEstandar proy);

	

	

}
