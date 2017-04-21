package cl.nexo.manager.obj.proyecto;

public class ObjResultCreaCotOp {
	int id_cotizacion;
	int id_operacion;
	int id_detalle_operacion;
	int id_proyecto;
	String uuid;
	String codigo_cotizacion;
	int codigo_operacion;
	int id_cuestionario;
	
	String text;
	
	public int getId_cotizacion() {
		return id_cotizacion;
	}
	public void setId_cotizacion(int id_cotizacion) {
		this.id_cotizacion = id_cotizacion;
	}
	public int getId_operacion() {
		return id_operacion;
	}
	public void setId_operacion(int id_operacion) {
		this.id_operacion = id_operacion;
	}
	public int getId_proyecto() {
		return id_proyecto;
	}
	public void setId_proyecto(int id_proyecto) {
		this.id_proyecto = id_proyecto;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getCodigo_cotizacion() {
		return codigo_cotizacion;
	}
	public void setCodigo_cotizacion(String codigo_cotizacion) {
		this.codigo_cotizacion = codigo_cotizacion;
	}
	public int getCodigo_operacion() {
		return codigo_operacion;
	}
	public void setCodigo_operacion(int codigo_operacion) {
		this.codigo_operacion = codigo_operacion;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getId_detalle_operacion() {
		return id_detalle_operacion;
	}
	public void setId_detalle_operacion(int id_detalle_operacion) {
		this.id_detalle_operacion = id_detalle_operacion;
	}
	public int getId_cuestionario() {
		return id_cuestionario;
	}
	public void setId_cuestionario(int id_cuestionario) {
		this.id_cuestionario = id_cuestionario;
	}

	
}
