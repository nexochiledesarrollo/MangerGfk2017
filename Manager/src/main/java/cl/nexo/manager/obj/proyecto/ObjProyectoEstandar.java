package cl.nexo.manager.obj.proyecto;

import java.math.BigInteger;

public class ObjProyectoEstandar {
	
	/*exite proyecto padre*/
	
	int proyecto_padre; //0 - Existe  //  1 - Nuevo
	String link_id_operacion;
	
	/* Detalle Operacion */
	Long id_operacion;
	Long id_proyectom;
	String codigo_proyectom;
	int cod_operacion;
	String cod_area;
	String nombre_area;
	String cod_sam;
	String cod_manager;
	int area_medicion;
	String nombre_operacion;
	int estado_medicion;
	String str_estado_medicion;
	String nombre_estado;
	int orden_medicion;
	int cola_operacion;
	String nombre_cola;
	int priori_operacion;
	String fcrea_operacion;
	int screa_operacion;
	String str_screa;
	String factivacion_operacion;
	int sactivacion_operacion;
	String str_sactivacion;
	String fmod_operacion;
	int smod_operacion;
	String str_smod;
	String felimina_operacion;
	int selimina_operacion;
	String str_selimina_operacion;
	int elimina_operacion;
	
	/* Detalle Proyecto Principal */
	String nombre_proyectop;
	int estado_proyectop;
	String str_estado_proyectop;
	int id_cliente;
	String nombre_cliente;
	
	/* Detalle proyecto */
	Long id_manager;
	int id_cliente_factura;
	String nombre_cliente_factura;
	Long precio_venta_manager;
	Long precio_costo_manager;
	Float precio_venta_uf_manager;
	int id_tipo_estudio;
	String str_tipo_estudio;
	int id_tipo_entrevista;
	String str_tipo_entrevista;
	int muestra_manager;
	int canal_manager;
	String str_canal;
	int id_user_coordinador_manager;
	String str_user_coordinador_manager;
	int id_user_director_estudio_manager;
	String str_user_director_estudio_manager;
	int id_user_jefe_estudio_manager;
	String str_user_jefe_estudio_manager;
	int id_area_recopilacion;
	String str_area_recopilacion;
	int id_canal_recopilacion;
	String str_canal_recopilacion;
	int id_sub_modo_recopilacion;
	String str_sub_modo_recopilacion;
	int revision_pd_manager;
	int supervision_pd_manager;
	int codificacion_pd_manager;
	int digitacion_pd_manager;
	int procesamiento_pd_manager;
	int porcentaje_supervicion_ra_manager;
	int porcentaje_re_digitacion_ra_manager;
	int libro_codigo_ra_manager;
	int verbatims_ra_manager;
	int pre_proceso_ra_manager;
	int abiertas_codificar_od_manager;
	int otros_codificar_od_manager;
	int tmo_od_manager;
	int id_industria;
	String str_industria;
	int id_producto;
	String str_producto;
	int id_tipo_producto1;
	String str_tipo_producto1;
	int id_tipo_producto2;
	String str_tipo_producto2;
	int id_digital;
	String str_digital;
	int weekly_semana;
	int weekly_ano;
	
	/* BEGIN traza operacion **********************/
	
	String fcreacion_proyectom;
	int screacion_proyectom;
	String str_screacion;
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
	/* END traza operacion **********************/
	
	/* BEGIN DATE PROYECTO **********************/
	
	String fingreso_implementacion_proyecto;
	String fsalida_implementacion_proyecto;
	
	String fingreso_campo_proyecto;
	String fsalida_campo_proyecto;
	
	String fentrada_base_proyecto;
	String fsalida_base_proyecto;
	
	String fentrada_presentacion;
	
	/* END DATE PROYECTO **********************/
	
	
	public Long getId_operacion() {
		return id_operacion;
	}

	public Long getPrecio_costo_manager() {
		return precio_costo_manager;
	}

	public void setPrecio_costo_manager(Long precio_costo_manager) {
		this.precio_costo_manager = precio_costo_manager;
	}

	public int getId_tipo_entrevista() {
		return id_tipo_entrevista;
	}

	public void setId_tipo_entrevista(int id_tipo_entrevista) {
		this.id_tipo_entrevista = id_tipo_entrevista;
	}

	public String getStr_tipo_entrevista() {
		return str_tipo_entrevista;
	}

	public void setStr_tipo_entrevista(String str_tipo_entrevista) {
		this.str_tipo_entrevista = str_tipo_entrevista;
	}

	public String getLink_id_operacion() {
		return link_id_operacion;
	}

	public void setLink_id_operacion(String link_id_operacion) {
		this.link_id_operacion = link_id_operacion;
	}

	public String getNombre_cliente_factura() {
		return nombre_cliente_factura;
	}

	public void setNombre_cliente_factura(String nombre_cliente_factura) {
		this.nombre_cliente_factura = nombre_cliente_factura;
	}

	public String getStr_estado_medicion() {
		return str_estado_medicion;
	}

	public void setStr_estado_medicion(String str_estado_medicion) {
		this.str_estado_medicion = str_estado_medicion;
	}

	public String getStr_estado_proyectop() {
		return str_estado_proyectop;
	}

	public void setStr_estado_proyectop(String str_estado_proyectop) {
		this.str_estado_proyectop = str_estado_proyectop;
	}

	public String getStr_selimina_operacion() {
		return str_selimina_operacion;
	}

	public void setStr_selimina_operacion(String str_selimina_operacion) {
		this.str_selimina_operacion = str_selimina_operacion;
	}

	public int getWeekly_semana() {
		return weekly_semana;
	}

	public void setWeekly_semana(int weekly_semana) {
		this.weekly_semana = weekly_semana;
	}

	public int getWeekly_ano() {
		return weekly_ano;
	}

	public void setWeekly_ano(int weekly_ano) {
		this.weekly_ano = weekly_ano;
	}

	public int getProyecto_padre() {
		return proyecto_padre;
	}

	public void setProyecto_padre(int proyecto_padre) {
		this.proyecto_padre = proyecto_padre;
	}

	public void setId_operacion(Long id_operacion) {
		this.id_operacion = id_operacion;
	}

	public Long getId_proyectom() {
		return id_proyectom;
	}

	public void setId_proyectom(Long id_proyectom) {
		this.id_proyectom = id_proyectom;
	}

	public String getCodigo_proyectom() {
		return codigo_proyectom;
	}

	public void setCodigo_proyectom(String codigo_proyectom) {
		this.codigo_proyectom = codigo_proyectom;
	}

	public int getCod_operacion() {
		return cod_operacion;
	}

	public void setCod_operacion(int cod_operacion) {
		this.cod_operacion = cod_operacion;
	}

	public String getCod_area() {
		return cod_area;
	}

	public void setCod_area(String cod_area) {
		this.cod_area = cod_area;
	}

	public String getNombre_area() {
		return nombre_area;
	}

	public void setNombre_area(String nombre_area) {
		this.nombre_area = nombre_area;
	}

	public String getCod_sam() {
		return cod_sam;
	}

	public void setCod_sam(String cod_sam) {
		this.cod_sam = cod_sam;
	}

	public String getCod_manager() {
		return cod_manager;
	}

	public void setCod_manager(String cod_manager) {
		this.cod_manager = cod_manager;
	}

	public int getArea_medicion() {
		return area_medicion;
	}

	public void setArea_medicion(int area_medicion) {
		this.area_medicion = area_medicion;
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

	public String getNombre_estado() {
		return nombre_estado;
	}

	public void setNombre_estado(String nombre_estado) {
		this.nombre_estado = nombre_estado;
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

	public String getNombre_cola() {
		return nombre_cola;
	}

	public void setNombre_cola(String nombre_cola) {
		this.nombre_cola = nombre_cola;
	}

	public int getPriori_operacion() {
		return priori_operacion;
	}

	public void setPriori_operacion(int priori_operacion) {
		this.priori_operacion = priori_operacion;
	}

	public String getFcrea_operacion() {
		return fcrea_operacion;
	}

	public void setFcrea_operacion(String fcrea_operacion) {
		this.fcrea_operacion = fcrea_operacion;
	}

	public int getScrea_operacion() {
		return screa_operacion;
	}

	public void setScrea_operacion(int screa_operacion) {
		this.screa_operacion = screa_operacion;
	}

	public String getStr_screa() {
		return str_screa;
	}

	public void setStr_screa(String str_screa) {
		this.str_screa = str_screa;
	}

	public String getFactivacion_operacion() {
		return factivacion_operacion;
	}

	public void setFactivacion_operacion(String factivacion_operacion) {
		this.factivacion_operacion = factivacion_operacion;
	}

	public int getSactivacion_operacion() {
		return sactivacion_operacion;
	}

	public void setSactivacion_operacion(int sactivacion_operacion) {
		this.sactivacion_operacion = sactivacion_operacion;
	}

	public String getStr_sactivacion() {
		return str_sactivacion;
	}

	public void setStr_sactivacion(String str_sactivacion) {
		this.str_sactivacion = str_sactivacion;
	}

	public String getFmod_operacion() {
		return fmod_operacion;
	}

	public void setFmod_operacion(String fmod_operacion) {
		this.fmod_operacion = fmod_operacion;
	}

	public int getSmod_operacion() {
		return smod_operacion;
	}

	public void setSmod_operacion(int smod_operacion) {
		this.smod_operacion = smod_operacion;
	}

	public String getStr_smod() {
		return str_smod;
	}

	public void setStr_smod(String str_smod) {
		this.str_smod = str_smod;
	}

	public String getFelimina_operacion() {
		return felimina_operacion;
	}

	public void setFelimina_operacion(String felimina_operacion) {
		this.felimina_operacion = felimina_operacion;
	}

	public int getSelimina_operacion() {
		return selimina_operacion;
	}

	public void setSelimina_operacion(int selimina_operacion) {
		this.selimina_operacion = selimina_operacion;
	}

	public int getElimina_operacion() {
		return elimina_operacion;
	}

	public void setElimina_operacion(int elimina_operacion) {
		this.elimina_operacion = elimina_operacion;
	}

	public String getNombre_proyectop() {
		return nombre_proyectop;
	}

	public void setNombre_proyectop(String nombre_proyectop) {
		this.nombre_proyectop = nombre_proyectop;
	}

	public int getEstado_proyectop() {
		return estado_proyectop;
	}

	public void setEstado_proyectop(int estado_proyectop) {
		this.estado_proyectop = estado_proyectop;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getNombre_cliente() {
		return nombre_cliente;
	}

	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}

	public Long getId_manager() {
		return id_manager;
	}

	public void setId_manager(Long id_manager) {
		this.id_manager = id_manager;
	}

	public int getId_cliente_factura() {
		return id_cliente_factura;
	}

	public void setId_cliente_factura(int id_cliente_factura) {
		this.id_cliente_factura = id_cliente_factura;
	}

	public Long getPrecio_venta_manager() {
		return precio_venta_manager;
	}

	public void setPrecio_venta_manager(Long precio_venta_manager) {
		this.precio_venta_manager = precio_venta_manager;
	}

	public Float getPrecio_venta_uf_manager() {
		return precio_venta_uf_manager;
	}

	public void setPrecio_venta_uf_manager(Float precio_venta_uf_manager) {
		this.precio_venta_uf_manager = precio_venta_uf_manager;
	}

	public int getId_tipo_estudio() {
		return id_tipo_estudio;
	}

	public void setId_tipo_estudio(int id_tipo_estudio) {
		this.id_tipo_estudio = id_tipo_estudio;
	}

	public String getStr_tipo_estudio() {
		return str_tipo_estudio;
	}

	public void setStr_tipo_estudio(String str_tipo_estudio) {
		this.str_tipo_estudio = str_tipo_estudio;
	}

	public int getMuestra_manager() {
		return muestra_manager;
	}

	public void setMuestra_manager(int muestra_manager) {
		this.muestra_manager = muestra_manager;
	}

	public int getCanal_manager() {
		return canal_manager;
	}

	public void setCanal_manager(int canal_manager) {
		this.canal_manager = canal_manager;
	}

	public String getStr_canal() {
		return str_canal;
	}

	public void setStr_canal(String str_canal) {
		this.str_canal = str_canal;
	}

	public int getId_user_coordinador_manager() {
		return id_user_coordinador_manager;
	}

	public void setId_user_coordinador_manager(int id_user_coordinador_manager) {
		this.id_user_coordinador_manager = id_user_coordinador_manager;
	}

	public String getStr_user_coordinador_manager() {
		return str_user_coordinador_manager;
	}

	public void setStr_user_coordinador_manager(String str_user_coordinador_manager) {
		this.str_user_coordinador_manager = str_user_coordinador_manager;
	}

	public int getId_user_director_estudio_manager() {
		return id_user_director_estudio_manager;
	}

	public void setId_user_director_estudio_manager(
			int id_user_director_estudio_manager) {
		this.id_user_director_estudio_manager = id_user_director_estudio_manager;
	}

	public String getStr_user_director_estudio_manager() {
		return str_user_director_estudio_manager;
	}

	public void setStr_user_director_estudio_manager(
			String str_user_director_estudio_manager) {
		this.str_user_director_estudio_manager = str_user_director_estudio_manager;
	}

	public int getId_user_jefe_estudio_manager() {
		return id_user_jefe_estudio_manager;
	}

	public void setId_user_jefe_estudio_manager(int id_user_jefe_estudio_manager) {
		this.id_user_jefe_estudio_manager = id_user_jefe_estudio_manager;
	}

	public String getStr_user_jefe_estudio_manager() {
		return str_user_jefe_estudio_manager;
	}

	public void setStr_user_jefe_estudio_manager(
			String str_user_jefe_estudio_manager) {
		this.str_user_jefe_estudio_manager = str_user_jefe_estudio_manager;
	}

	public int getId_area_recopilacion() {
		return id_area_recopilacion;
	}

	public void setId_area_recopilacion(int id_area_recopilacion) {
		this.id_area_recopilacion = id_area_recopilacion;
	}

	public String getStr_area_recopilacion() {
		return str_area_recopilacion;
	}

	public void setStr_area_recopilacion(String str_area_recopilacion) {
		this.str_area_recopilacion = str_area_recopilacion;
	}

	public int getId_canal_recopilacion() {
		return id_canal_recopilacion;
	}

	public void setId_canal_recopilacion(int id_canal_recopilacion) {
		this.id_canal_recopilacion = id_canal_recopilacion;
	}

	public String getStr_canal_recopilacion() {
		return str_canal_recopilacion;
	}

	public void setStr_canal_recopilacion(String str_canal_recopilacion) {
		this.str_canal_recopilacion = str_canal_recopilacion;
	}

	public int getId_sub_modo_recopilacion() {
		return id_sub_modo_recopilacion;
	}

	public void setId_sub_modo_recopilacion(int id_sub_modo_recopilacion) {
		this.id_sub_modo_recopilacion = id_sub_modo_recopilacion;
	}

	public String getStr_sub_modo_recopilacion() {
		return str_sub_modo_recopilacion;
	}

	public void setStr_sub_modo_recopilacion(String str_sub_modo_recopilacion) {
		this.str_sub_modo_recopilacion = str_sub_modo_recopilacion;
	}

	public int getRevision_pd_manager() {
		return revision_pd_manager;
	}

	public void setRevision_pd_manager(int revision_pd_manager) {
		this.revision_pd_manager = revision_pd_manager;
	}

	public int getSupervision_pd_manager() {
		return supervision_pd_manager;
	}

	public void setSupervision_pd_manager(int supervision_pd_manager) {
		this.supervision_pd_manager = supervision_pd_manager;
	}

	public int getCodificacion_pd_manager() {
		return codificacion_pd_manager;
	}

	public void setCodificacion_pd_manager(int codificacion_pd_manager) {
		this.codificacion_pd_manager = codificacion_pd_manager;
	}

	public int getDigitacion_pd_manager() {
		return digitacion_pd_manager;
	}

	public void setDigitacion_pd_manager(int digitacion_pd_manager) {
		this.digitacion_pd_manager = digitacion_pd_manager;
	}

	public int getProcesamiento_pd_manager() {
		return procesamiento_pd_manager;
	}

	public void setProcesamiento_pd_manager(int procesamiento_pd_manager) {
		this.procesamiento_pd_manager = procesamiento_pd_manager;
	}

	public int getPorcentaje_supervicion_ra_manager() {
		return porcentaje_supervicion_ra_manager;
	}

	public void setPorcentaje_supervicion_ra_manager(
			int porcentaje_supervicion_ra_manager) {
		this.porcentaje_supervicion_ra_manager = porcentaje_supervicion_ra_manager;
	}

	public int getPorcentaje_re_digitacion_ra_manager() {
		return porcentaje_re_digitacion_ra_manager;
	}

	public void setPorcentaje_re_digitacion_ra_manager(
			int porcentaje_re_digitacion_ra_manager) {
		this.porcentaje_re_digitacion_ra_manager = porcentaje_re_digitacion_ra_manager;
	}

	public int getLibro_codigo_ra_manager() {
		return libro_codigo_ra_manager;
	}

	public void setLibro_codigo_ra_manager(int libro_codigo_ra_manager) {
		this.libro_codigo_ra_manager = libro_codigo_ra_manager;
	}

	public int getVerbatims_ra_manager() {
		return verbatims_ra_manager;
	}

	public void setVerbatims_ra_manager(int verbatims_ra_manager) {
		this.verbatims_ra_manager = verbatims_ra_manager;
	}

	public int getPre_proceso_ra_manager() {
		return pre_proceso_ra_manager;
	}

	public void setPre_proceso_ra_manager(int pre_proceso_ra_manager) {
		this.pre_proceso_ra_manager = pre_proceso_ra_manager;
	}

	public int getAbiertas_codificar_od_manager() {
		return abiertas_codificar_od_manager;
	}

	public void setAbiertas_codificar_od_manager(int abiertas_codificar_od_manager) {
		this.abiertas_codificar_od_manager = abiertas_codificar_od_manager;
	}

	public int getOtros_codificar_od_manager() {
		return otros_codificar_od_manager;
	}

	public void setOtros_codificar_od_manager(int otros_codificar_od_manager) {
		this.otros_codificar_od_manager = otros_codificar_od_manager;
	}

	public int getTmo_od_manager() {
		return tmo_od_manager;
	}

	public void setTmo_od_manager(int tmo_od_manager) {
		this.tmo_od_manager = tmo_od_manager;
	}

	public int getId_industria() {
		return id_industria;
	}

	public void setId_industria(int id_industria) {
		this.id_industria = id_industria;
	}

	public String getStr_industria() {
		return str_industria;
	}

	public void setStr_industria(String str_industria) {
		this.str_industria = str_industria;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public String getStr_producto() {
		return str_producto;
	}

	public void setStr_producto(String str_producto) {
		this.str_producto = str_producto;
	}

	public int getId_tipo_producto1() {
		return id_tipo_producto1;
	}

	public void setId_tipo_producto1(int id_tipo_producto1) {
		this.id_tipo_producto1 = id_tipo_producto1;
	}

	public String getStr_tipo_producto1() {
		return str_tipo_producto1;
	}

	public void setStr_tipo_producto1(String str_tipo_producto1) {
		this.str_tipo_producto1 = str_tipo_producto1;
	}

	public int getId_tipo_producto2() {
		return id_tipo_producto2;
	}

	public void setId_tipo_producto2(int id_tipo_producto2) {
		this.id_tipo_producto2 = id_tipo_producto2;
	}

	public String getStr_tipo_producto2() {
		return str_tipo_producto2;
	}

	public void setStr_tipo_producto2(String str_tipo_producto2) {
		this.str_tipo_producto2 = str_tipo_producto2;
	}

	public int getId_digital() {
		return id_digital;
	}

	public void setId_digital(int id_digital) {
		this.id_digital = id_digital;
	}

	public String getStr_digital() {
		return str_digital;
	}

	public void setStr_digital(String str_digital) {
		this.str_digital = str_digital;
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

	public String getFingreso_implementacion_proyecto() {
		return fingreso_implementacion_proyecto;
	}

	public void setFingreso_implementacion_proyecto(
			String fingreso_implementacion_proyecto) {
		this.fingreso_implementacion_proyecto = fingreso_implementacion_proyecto;
	}

	public String getFsalida_implementacion_proyecto() {
		return fsalida_implementacion_proyecto;
	}

	public void setFsalida_implementacion_proyecto(
			String fsalida_implementacion_proyecto) {
		this.fsalida_implementacion_proyecto = fsalida_implementacion_proyecto;
	}

	public String getFingreso_campo_proyecto() {
		return fingreso_campo_proyecto;
	}

	public void setFingreso_campo_proyecto(String fingreso_campo_proyecto) {
		this.fingreso_campo_proyecto = fingreso_campo_proyecto;
	}

	public String getFsalida_campo_proyecto() {
		return fsalida_campo_proyecto;
	}

	public void setFsalida_campo_proyecto(String fsalida_campo_proyecto) {
		this.fsalida_campo_proyecto = fsalida_campo_proyecto;
	}

	public String getFentrada_base_proyecto() {
		return fentrada_base_proyecto;
	}

	public void setFentrada_base_proyecto(String fentrada_base_proyecto) {
		this.fentrada_base_proyecto = fentrada_base_proyecto;
	}

	public String getFsalida_base_proyecto() {
		return fsalida_base_proyecto;
	}

	public void setFsalida_base_proyecto(String fsalida_base_proyecto) {
		this.fsalida_base_proyecto = fsalida_base_proyecto;
	}

	public String getFentrada_presentacion() {
		return fentrada_presentacion;
	}

	public void setFentrada_presentacion(String fentrada_presentacion) {
		this.fentrada_presentacion = fentrada_presentacion;
	}
	
	
	
	
	
	
	
	
	
	
}
