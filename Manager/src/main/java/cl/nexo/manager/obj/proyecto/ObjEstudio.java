package cl.nexo.manager.obj.proyecto;

import java.util.ArrayList;

import cl.nexo.manager.obj.reunion.ObjReunionKickOff;

public class ObjEstudio {
	//Proyecto
	int id_proyectom;
	String codigo_proyectom;
	int estado_proyectom;
	//Cotizacion
	int id_cotizacion;
	String codigo_cotizacion;
	
	//Estudio ****************************************
	int id_operacion;
	int canal_venta;
	String cod_sam;
	String tipo_sam;
	String id_sap;
	String id_crm;
	String uid_operacion;
	int cod_operacion;
	String cod_manager;
	int id_cliente;
	String str_cliente;
	String rut_cliente;
	int area_medicion;
	String str_area_medicion;
	int sector_medicion;
	String str_sector_medicion;
	int industria_medicion;
	String str_industria_medicion;
	String id_tipo_entrevista;
	String str_id_tipo_entrevista;
	String nombre_operacion;
	int id_etapa;
	String nombre_etapa;
	int estado_medicion;
	String str_estado_medicion;
	int orden_medicion;
	int cola_operacion;
	String str_cola_operacion;
	int priori_operacion;
	int activa_operacion;
	
	/* BEGIN Adicional ****************************/
	int res_us1_op;
	String str_res_us1_op;
	int res_us2_op;
	String str_res_us2_op;
	int res_us3_op;
	String str_res_us3_op;
	int num_entrevistas_op;
	
	/* BEGIN traza operacion **********************/
	
	String fcreacion_proyectom;
	int screacion_proyectom;
	String str_screacion;
	
	String factivacion_medicion;
	int sactivacion_medicion;
	String str_sactivacion_medicion;
	
	String fmod_proyectom;
	int smod_proyectom;
	String str_smodp;
	String felimina_proyectom;
	int selimina_proyectom;
	String str_seliminap;
	int elimina_proyectom;
	
	String fingreso_puesta_marcha_operacion;
	String fsalida_puesta_marcha_operacion;
	
	String fingreso_implementacion_operacion;
	String fsalida_implementacion_operacion;
	
	String fingreso_recoleccion_operacion;
	String fsalida_recoleccion_operacion;
	
	String fingreso_codificacion_operacion;
	String fsalida_codificacion_operacion;
	
	String fingreso_digitacion_operacion;
	String fsalida_digitacion_operacion;
	
	String fingreso_depuracion_operacion;
	String fsalida_depuracion_operacion;
	
	String fingreso_tabulacion_operacion;
	String fsalida_tabulacion_operacion;
	
	String fingreso_entrega_operacion;
	String fsalida_entrega_operacion;
	
	String fcom_ini_campo;
	String fcom_fin_campo;
	String fcom_ini_bbdd;
	String fcom_fin_bbdd;
	String fcom_entrega;
	int end_operacion;
	
	/* END traza operacion **********************/
	
	ObjEstudioDetalle detalle;
	ArrayList<ObjEstudioRecoleccion> recoleccion;
	ObjEstudioBussnessCase bbcc;
	ArrayList<ObjEstudioProducto> productos;
	ObjEstudioCuestionario cuestionario;
	ObjEstudioCodificacion codificacion;
	ObjEstudioDigitacion digitacion;
	ObjEstudioTabulacion tabulacion;
	ObjEstudioAnalisis analisis;
	boolean existReunionEstudio; 
	
	
	
	//Result --------------------------------------
	String text;
	
	
	public int getId_proyectom() {
		return id_proyectom;
	}
	public void setId_proyectom(int id_proyectom) {
		this.id_proyectom = id_proyectom;
	}
	public String getCodigo_proyectom() {
		return codigo_proyectom;
	}
	public void setCodigo_proyectom(String codigo_proyectom) {
		this.codigo_proyectom = codigo_proyectom;
	}
	public int getEstado_proyectom() {
		return estado_proyectom;
	}
	public void setEstado_proyectom(int estado_proyectom) {
		this.estado_proyectom = estado_proyectom;
	}
	public int getId_cotizacion() {
		return id_cotizacion;
	}
	public void setId_cotizacion(int id_cotizacion) {
		this.id_cotizacion = id_cotizacion;
	}
	public String getCodigo_cotizacion() {
		return codigo_cotizacion;
	}
	public void setCodigo_cotizacion(String codigo_cotizacion) {
		this.codigo_cotizacion = codigo_cotizacion;
	}
	public int getId_operacion() {
		return id_operacion;
	}
	public void setId_operacion(int id_operacion) {
		this.id_operacion = id_operacion;
	}
	public int getCanal_venta() {
		return canal_venta;
	}
	public void setCanal_venta(int canal_venta) {
		this.canal_venta = canal_venta;
	}
	public String getCod_sam() {
		return cod_sam;
	}
	public void setCod_sam(String cod_sam) {
		this.cod_sam = cod_sam;
	}
	public String getTipo_sam() {
		return tipo_sam;
	}
	public void setTipo_sam(String tipo_sam) {
		this.tipo_sam = tipo_sam;
	}
	public String getId_sap() {
		return id_sap;
	}
	public void setId_sap(String id_sap) {
		this.id_sap = id_sap;
	}
	public String getId_crm() {
		return id_crm;
	}
	public void setId_crm(String id_crm) {
		this.id_crm = id_crm;
	}
	public int getCod_operacion() {
		return cod_operacion;
	}
	public void setCod_operacion(int cod_operacion) {
		this.cod_operacion = cod_operacion;
	}
	public String getCod_manager() {
		return cod_manager;
	}
	public void setCod_manager(String cod_manager) {
		this.cod_manager = cod_manager;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getStr_cliente() {
		return str_cliente;
	}
	public void setStr_cliente(String str_cliente) {
		this.str_cliente = str_cliente;
	}
	
	public String getRut_cliente() {
		return rut_cliente;
	}
	public void setRut_cliente(String rut_cliente) {
		this.rut_cliente = rut_cliente;
	}
	public int getArea_medicion() {
		return area_medicion;
	}
	public void setArea_medicion(int area_medicion) {
		this.area_medicion = area_medicion;
	}
	public String getStr_area_medicion() {
		return str_area_medicion;
	}
	public void setStr_area_medicion(String str_area_medicion) {
		this.str_area_medicion = str_area_medicion;
	}
	public int getSector_medicion() {
		return sector_medicion;
	}
	public void setSector_medicion(int sector_medicion) {
		this.sector_medicion = sector_medicion;
	}
	public String getStr_sector_medicion() {
		return str_sector_medicion;
	}
	public void setStr_sector_medicion(String str_sector_medicion) {
		this.str_sector_medicion = str_sector_medicion;
	}
	public int getIndustria_medicion() {
		return industria_medicion;
	}
	public void setIndustria_medicion(int industria_medicion) {
		this.industria_medicion = industria_medicion;
	}
	public String getStr_industria_medicion() {
		return str_industria_medicion;
	}
	public void setStr_industria_medicion(String str_industria_medicion) {
		this.str_industria_medicion = str_industria_medicion;
	}
	public String getId_tipo_entrevista() {
		return id_tipo_entrevista;
	}
	public void setId_tipo_entrevista(String id_tipo_entrevista) {
		this.id_tipo_entrevista = id_tipo_entrevista;
	}
	public String getStr_id_tipo_entrevista() {
		return str_id_tipo_entrevista;
	}
	public void setStr_id_tipo_entrevista(String str_id_tipo_entrevista) {
		this.str_id_tipo_entrevista = str_id_tipo_entrevista;
	}
	public String getNombre_operacion() {
		return nombre_operacion;
	}
	public void setNombre_operacion(String nombre_operacion) {
		this.nombre_operacion = nombre_operacion;
	}
	public int getEstado_medicion() {
		return estado_medicion;
	}
	public void setEstado_medicion(int estado_medicion) {
		this.estado_medicion = estado_medicion;
	}
	public String getStr_estado_medicion() {
		return str_estado_medicion;
	}
	public void setStr_estado_medicion(String str_estado_medicion) {
		this.str_estado_medicion = str_estado_medicion;
	}
	public int getOrden_medicion() {
		return orden_medicion;
	}
	public void setOrden_medicion(int orden_medicion) {
		this.orden_medicion = orden_medicion;
	}
	public int getCola_operacion() {
		return cola_operacion;
	}
	public void setCola_operacion(int cola_operacion) {
		this.cola_operacion = cola_operacion;
	}
	public String getStr_cola_operacion() {
		return str_cola_operacion;
	}
	public void setStr_cola_operacion(String str_cola_operacion) {
		this.str_cola_operacion = str_cola_operacion;
	}
	public int getPriori_operacion() {
		return priori_operacion;
	}
	public void setPriori_operacion(int priori_operacion) {
		this.priori_operacion = priori_operacion;
	}
	public int getRes_us1_op() {
		return res_us1_op;
	}
	public void setRes_us1_op(int res_us1_op) {
		this.res_us1_op = res_us1_op;
	}
	public String getStr_res_us1_op() {
		return str_res_us1_op;
	}
	public void setStr_res_us1_op(String str_res_us1_op) {
		this.str_res_us1_op = str_res_us1_op;
	}
	public int getRes_us2_op() {
		return res_us2_op;
	}
	public void setRes_us2_op(int res_us2_op) {
		this.res_us2_op = res_us2_op;
	}
	public String getStr_res_us2_op() {
		return str_res_us2_op;
	}
	public void setStr_res_us2_op(String str_res_us2_op) {
		this.str_res_us2_op = str_res_us2_op;
	}
	public int getRes_us3_op() {
		return res_us3_op;
	}
	public void setRes_us3_op(int res_us3_op) {
		this.res_us3_op = res_us3_op;
	}
	public String getStr_res_us3_op() {
		return str_res_us3_op;
	}
	public void setStr_res_us3_op(String str_res_us3_op) {
		this.str_res_us3_op = str_res_us3_op;
	}
	public int getNum_entrevistas_op() {
		return num_entrevistas_op;
	}
	public void setNum_entrevistas_op(int num_entrevistas_op) {
		this.num_entrevistas_op = num_entrevistas_op;
	}
	public String getFcreacion_proyectom() {
		return fcreacion_proyectom;
	}
	public void setFcreacion_proyectom(String fcreacion_proyectom) {
		this.fcreacion_proyectom = fcreacion_proyectom;
	}
	public int getScreacion_proyectom() {
		return screacion_proyectom;
	}
	public void setScreacion_proyectom(int screacion_proyectom) {
		this.screacion_proyectom = screacion_proyectom;
	}
	public String getStr_screacion() {
		return str_screacion;
	}
	public void setStr_screacion(String str_screacion) {
		this.str_screacion = str_screacion;
	}
	public String getFactivacion_medicion() {
		return factivacion_medicion;
	}
	public void setFactivacion_medicion(String factivacion_medicion) {
		this.factivacion_medicion = factivacion_medicion;
	}
	public int getSactivacion_medicion() {
		return sactivacion_medicion;
	}
	public void setSactivacion_medicion(int sactivacion_medicion) {
		this.sactivacion_medicion = sactivacion_medicion;
	}
	public String getStr_sactivacion_medicion() {
		return str_sactivacion_medicion;
	}
	public void setStr_sactivacion_medicion(String str_sactivacion_medicion) {
		this.str_sactivacion_medicion = str_sactivacion_medicion;
	}
	public String getFmod_proyectom() {
		return fmod_proyectom;
	}
	public void setFmod_proyectom(String fmod_proyectom) {
		this.fmod_proyectom = fmod_proyectom;
	}
	public int getSmod_proyectom() {
		return smod_proyectom;
	}
	public void setSmod_proyectom(int smod_proyectom) {
		this.smod_proyectom = smod_proyectom;
	}
	public String getStr_smodp() {
		return str_smodp;
	}
	public void setStr_smodp(String str_smodp) {
		this.str_smodp = str_smodp;
	}
	public String getFelimina_proyectom() {
		return felimina_proyectom;
	}
	public void setFelimina_proyectom(String felimina_proyectom) {
		this.felimina_proyectom = felimina_proyectom;
	}
	public int getSelimina_proyectom() {
		return selimina_proyectom;
	}
	public void setSelimina_proyectom(int selimina_proyectom) {
		this.selimina_proyectom = selimina_proyectom;
	}
	public String getStr_seliminap() {
		return str_seliminap;
	}
	public void setStr_seliminap(String str_seliminap) {
		this.str_seliminap = str_seliminap;
	}
	public int getElimina_proyectom() {
		return elimina_proyectom;
	}
	public void setElimina_proyectom(int elimina_proyectom) {
		this.elimina_proyectom = elimina_proyectom;
	}
	public String getFingreso_puesta_marcha_operacion() {
		return fingreso_puesta_marcha_operacion;
	}
	public void setFingreso_puesta_marcha_operacion(
			String fingreso_puesta_marcha_operacion) {
		this.fingreso_puesta_marcha_operacion = fingreso_puesta_marcha_operacion;
	}
	public String getFsalida_puesta_marcha_operacion() {
		return fsalida_puesta_marcha_operacion;
	}
	public void setFsalida_puesta_marcha_operacion(
			String fsalida_puesta_marcha_operacion) {
		this.fsalida_puesta_marcha_operacion = fsalida_puesta_marcha_operacion;
	}
	public String getFingreso_implementacion_operacion() {
		return fingreso_implementacion_operacion;
	}
	public void setFingreso_implementacion_operacion(
			String fingreso_implementacion_operacion) {
		this.fingreso_implementacion_operacion = fingreso_implementacion_operacion;
	}
	public String getFsalida_implementacion_operacion() {
		return fsalida_implementacion_operacion;
	}
	public void setFsalida_implementacion_operacion(
			String fsalida_implementacion_operacion) {
		this.fsalida_implementacion_operacion = fsalida_implementacion_operacion;
	}
	public String getFingreso_recoleccion_operacion() {
		return fingreso_recoleccion_operacion;
	}
	public void setFingreso_recoleccion_operacion(
			String fingreso_recoleccion_operacion) {
		this.fingreso_recoleccion_operacion = fingreso_recoleccion_operacion;
	}
	public String getFsalida_recoleccion_operacion() {
		return fsalida_recoleccion_operacion;
	}
	public void setFsalida_recoleccion_operacion(
			String fsalida_recoleccion_operacion) {
		this.fsalida_recoleccion_operacion = fsalida_recoleccion_operacion;
	}
	public String getFingreso_codificacion_operacion() {
		return fingreso_codificacion_operacion;
	}
	public void setFingreso_codificacion_operacion(
			String fingreso_codificacion_operacion) {
		this.fingreso_codificacion_operacion = fingreso_codificacion_operacion;
	}
	public String getFsalida_codificacion_operacion() {
		return fsalida_codificacion_operacion;
	}
	public void setFsalida_codificacion_operacion(
			String fsalida_codificacion_operacion) {
		this.fsalida_codificacion_operacion = fsalida_codificacion_operacion;
	}
	public String getFingreso_digitacion_operacion() {
		return fingreso_digitacion_operacion;
	}
	public void setFingreso_digitacion_operacion(
			String fingreso_digitacion_operacion) {
		this.fingreso_digitacion_operacion = fingreso_digitacion_operacion;
	}
	public String getFsalida_digitacion_operacion() {
		return fsalida_digitacion_operacion;
	}
	public void setFsalida_digitacion_operacion(String fsalida_digitacion_operacion) {
		this.fsalida_digitacion_operacion = fsalida_digitacion_operacion;
	}
	public String getFingreso_depuracion_operacion() {
		return fingreso_depuracion_operacion;
	}
	public void setFingreso_depuracion_operacion(
			String fingreso_depuracion_operacion) {
		this.fingreso_depuracion_operacion = fingreso_depuracion_operacion;
	}
	public String getFsalida_depuracion_operacion() {
		return fsalida_depuracion_operacion;
	}
	public void setFsalida_depuracion_operacion(String fsalida_depuracion_operacion) {
		this.fsalida_depuracion_operacion = fsalida_depuracion_operacion;
	}
	public String getFingreso_tabulacion_operacion() {
		return fingreso_tabulacion_operacion;
	}
	public void setFingreso_tabulacion_operacion(
			String fingreso_tabulacion_operacion) {
		this.fingreso_tabulacion_operacion = fingreso_tabulacion_operacion;
	}
	public String getFsalida_tabulacion_operacion() {
		return fsalida_tabulacion_operacion;
	}
	public void setFsalida_tabulacion_operacion(String fsalida_tabulacion_operacion) {
		this.fsalida_tabulacion_operacion = fsalida_tabulacion_operacion;
	}
	public String getFingreso_entrega_operacion() {
		return fingreso_entrega_operacion;
	}
	public void setFingreso_entrega_operacion(String fingreso_entrega_operacion) {
		this.fingreso_entrega_operacion = fingreso_entrega_operacion;
	}
	public String getFsalida_entrega_operacion() {
		return fsalida_entrega_operacion;
	}
	public void setFsalida_entrega_operacion(String fsalida_entrega_operacion) {
		this.fsalida_entrega_operacion = fsalida_entrega_operacion;
	}
	public int getId_etapa() {
		return id_etapa;
	}
	public void setId_etapa(int id_etapa) {
		this.id_etapa = id_etapa;
	}
	public String getNombre_etapa() {
		return nombre_etapa;
	}
	public void setNombre_etapa(String nombre_etapa) {
		this.nombre_etapa = nombre_etapa;
	}
	public ObjEstudioDetalle getDetalle() {
		return detalle;
	}
	public void setDetalle(ObjEstudioDetalle detalle) {
		this.detalle = detalle;
	}
	public ArrayList<ObjEstudioRecoleccion> getRecoleccion() {
		return recoleccion;
	}
	public void setRecoleccion(ArrayList<ObjEstudioRecoleccion> recoleccion) {
		this.recoleccion = recoleccion;
	}
	public ObjEstudioBussnessCase getBbcc() {
		return bbcc;
	}
	public void setBbcc(ObjEstudioBussnessCase bbcc) {
		this.bbcc = bbcc;
	}
	public ArrayList<ObjEstudioProducto> getProductos() {
		return productos;
	}
	public void setProductos(ArrayList<ObjEstudioProducto> productos) {
		this.productos = productos;
	}
	public ObjEstudioCuestionario getCuestionario() {
		return cuestionario;
	}
	public void setCuestionario(ObjEstudioCuestionario cuestionario) {
		this.cuestionario = cuestionario;
	}
	public ObjEstudioCodificacion getCodificacion() {
		return codificacion;
	}
	public void setCodificacion(ObjEstudioCodificacion codificacion) {
		this.codificacion = codificacion;
	}
	public ObjEstudioDigitacion getDigitacion() {
		return digitacion;
	}
	public void setDigitacion(ObjEstudioDigitacion digitacion) {
		this.digitacion = digitacion;
	}
	public ObjEstudioTabulacion getTabulacion() {
		return tabulacion;
	}
	public void setTabulacion(ObjEstudioTabulacion tabulacion) {
		this.tabulacion = tabulacion;
	}
	public ObjEstudioAnalisis getAnalisis() {
		return analisis;
	}
	public void setAnalisis(ObjEstudioAnalisis analisis) {
		this.analisis = analisis;
	}
	public String getUid_operacion() {
		return uid_operacion;
	}
	public void setUid_operacion(String uid_operacion) {
		this.uid_operacion = uid_operacion;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getActiva_operacion() {
		return activa_operacion;
	}
	public void setActiva_operacion(int activa_operacion) {
		this.activa_operacion = activa_operacion;
	}
	public String getFcom_ini_campo() {
		return fcom_ini_campo;
	}
	public void setFcom_ini_campo(String fcom_ini_campo) {
		this.fcom_ini_campo = fcom_ini_campo;
	}
	public String getFcom_fin_campo() {
		return fcom_fin_campo;
	}
	public void setFcom_fin_campo(String fcom_fin_campo) {
		this.fcom_fin_campo = fcom_fin_campo;
	}
	public String getFcom_ini_bbdd() {
		return fcom_ini_bbdd;
	}
	public void setFcom_ini_bbdd(String fcom_ini_bbdd) {
		this.fcom_ini_bbdd = fcom_ini_bbdd;
	}
	public String getFcom_fin_bbdd() {
		return fcom_fin_bbdd;
	}
	public void setFcom_fin_bbdd(String fcom_fin_bbdd) {
		this.fcom_fin_bbdd = fcom_fin_bbdd;
	}
	public String getFcom_entrega() {
		return fcom_entrega;
	}
	public void setFcom_entrega(String fcom_entrega) {
		this.fcom_entrega = fcom_entrega;
	}
	public int getEnd_operacion() {
		return end_operacion;
	}
	public void setEnd_operacion(int end_operacion) {
		this.end_operacion = end_operacion;
	}
	public boolean isExistReunionEstudio() {
		return existReunionEstudio;
	}
	public void setExistReunionEstudio(boolean existReunionEstudio) {
		this.existReunionEstudio = existReunionEstudio;
	}
	
	
	
	
	
	
}
