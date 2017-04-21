package cl.nexo.manager.obj.traza;

public class ObjTrazaManager {
	
	int id_traza;
	String fecha_traza;
	int id_usuario;
	String str_usuario;
	int id_proyectop;
	String codigo_proyectop;
	int id_operacion;
	int cod_operacion;
	int id_modulo;
	String nombre_modulo;
	int id_flujo;
	String nombre_flujo;
	int id_tarea;
	String nombre_tarea;
	String nombre_traza;
	String detalle_traza;
	int id_estado;
	
	
	public ObjTrazaManager( int id_traza,
							String fecha_traza,
							int id_usuario,
							int id_proyectop,
							int id_operacion,
							int id_modulo,
							int id_flujo,
							int id_tarea,
							String nombre_traza,
							String detalle_traza,
							int id_estado){
		
		this.id_traza = id_traza;
		this.fecha_traza = fecha_traza;
		this.id_usuario = id_usuario;
		this.id_proyectop = id_proyectop;
		this.id_operacion = id_operacion;
		this.id_modulo = id_modulo;
		this.id_flujo = id_flujo;
		this.id_tarea = id_tarea ;
		this.nombre_traza = nombre_traza;
		this.detalle_traza = detalle_traza;
		this.id_estado = id_estado;
	}
	
	public int getId_traza() {
		return id_traza;
	}
	public void setId_traza(int id_traza) {
		this.id_traza = id_traza;
	}
	public String getFecha_traza() {
		return fecha_traza;
	}
	public void setFecha_traza(String fecha_traza) {
		this.fecha_traza = fecha_traza;
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
	public int getId_proyectop() {
		return id_proyectop;
	}
	public void setId_proyectop(int id_proyectop) {
		this.id_proyectop = id_proyectop;
	}
	public String getCodigo_proyectop() {
		return codigo_proyectop;
	}
	public void setCodigo_proyectop(String codigo_proyectop) {
		this.codigo_proyectop = codigo_proyectop;
	}
	public int getId_operacion() {
		return id_operacion;
	}
	public void setId_operacion(int id_operacion) {
		this.id_operacion = id_operacion;
	}
	public int getCod_operacion() {
		return cod_operacion;
	}
	public void setCod_operacion(int cod_operacion) {
		this.cod_operacion = cod_operacion;
	}
	public int getId_modulo() {
		return id_modulo;
	}
	public void setId_modulo(int id_modulo) {
		this.id_modulo = id_modulo;
	}
	public String getNombre_modulo() {
		return nombre_modulo;
	}
	public void setNombre_modulo(String nombre_modulo) {
		this.nombre_modulo = nombre_modulo;
	}
	public int getId_flujo() {
		return id_flujo;
	}
	public void setId_flujo(int id_flujo) {
		this.id_flujo = id_flujo;
	}
	public String getNombre_flujo() {
		return nombre_flujo;
	}
	public void setNombre_flujo(String nombre_flujo) {
		this.nombre_flujo = nombre_flujo;
	}
	public int getId_tarea() {
		return id_tarea;
	}
	public void setId_tarea(int id_tarea) {
		this.id_tarea = id_tarea;
	}
	public String getNombre_tarea() {
		return nombre_tarea;
	}
	public void setNombre_tarea(String nombre_tarea) {
		this.nombre_tarea = nombre_tarea;
	}
	public String getNombre_traza() {
		return nombre_traza;
	}
	public void setNombre_traza(String nombre_traza) {
		this.nombre_traza = nombre_traza;
	}
	public String getDetalle_traza() {
		return detalle_traza;
	}
	public void setDetalle_traza(String detalle_traza) {
		this.detalle_traza = detalle_traza;
	}

	public int getId_estado() {
		return id_estado;
	}

	public void setId_estado(int id_estado) {
		this.id_estado = id_estado;
	}
	
	
	
	
	
	
	
	
	
}
