package cl.nexo.manager.access.manejoworkflow;

public interface ManejoWorkflowAccess {

	 public int SetUltimoEstado(int operacion, int actividad,int estado,int usuario);
	 public int buscarIdWorkPorActividad(int operacion, int actividad);
	 public int SetBitacoraActividad(int id_workflow, String observacion,int usuario);
	 public int createWorkActividad(int operacion, int actividad,int estado,int usuario);
	 
	 public void genericWorkActividad(int operacion, int actividad, String observacion,
				int nuevo_estado,int id_user);
}
