package cl.nexo.manager.controller.rest.workflow.actividad;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.access.general.tools.AccessUploadFile;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.proyecto.AccessCuestionario;
import cl.nexo.manager.access.proyecto.AccessEstudio;
import cl.nexo.manager.access.proyecto.AccessProyecto;
import cl.nexo.manager.access.traza.AccessTraza;
import cl.nexo.manager.access.workflow.AccessWorkflow15;
import cl.nexo.manager.controller.rest.proyecto.RestCotizacion;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.proyecto.ObjDataListEstudio;
import cl.nexo.manager.obj.proyecto.ObjEstudio;
import cl.nexo.manager.obj.proyecto.ObjEstudioAnalisis;
import cl.nexo.manager.obj.proyecto.ObjEstudioBbCc;
import cl.nexo.manager.obj.proyecto.ObjEstudioBussnessCase;
import cl.nexo.manager.obj.proyecto.ObjEstudioCodificacion;
import cl.nexo.manager.obj.proyecto.ObjEstudioCuestionario;
import cl.nexo.manager.obj.proyecto.ObjEstudioDetalle;
import cl.nexo.manager.obj.proyecto.ObjEstudioDigitacion;
import cl.nexo.manager.obj.proyecto.ObjEstudioFiltros;
import cl.nexo.manager.obj.proyecto.ObjEstudioRecoleccion;
import cl.nexo.manager.obj.proyecto.ObjEstudioTabulacion;
import cl.nexo.manager.obj.proyecto.ObjProyectoEstandar;
import cl.nexo.manager.obj.proyecto.ObjResultCreaCotOp;
import cl.nexo.manager.obj.traza.ObjTrazaManager;

@RestController
@RequestMapping("/RestWorkflow15")
public class RestWorkflow15 {
	
	private static final Logger logger = Logger.getLogger(RestWorkflow15.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	@RequestMapping(value = "/getListWorkflow15", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjDataListEstudio getListWorkflow15(@RequestParam("id") int id,
											    @RequestParam("lang") String lang)
	{
		ObjDataListEstudio est = new ObjDataListEstudio();
		ArrayList<ObjEstudio> list =  new ArrayList<ObjEstudio>();
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessWorkflow15 lists = (AccessWorkflow15) context.getBean("AccessWorkflow15");
		
		ObjLoginUser user = logins.getUserByLogin(authentication.getName());
		logger.debug("**********REQUEST PARAM FILTRO GET LIST ASIGN********************************************* "); 
		 	logger.debug("id: "+ id);
		 	logger.debug("lang: "+ lang);
		 	logger.debug("user: "+ user.getId_user());
		 	logger.debug("url: http://localhost:8080/Manager/RestWorkflow15/getListWorkflow15?id="+id+"&lang="+lang+ " ");
		 logger.debug("**********END REQUEST PARAM FILTRO GET LIST ASIGN********************************************* ");
		
		 
		 list = lists.getListEstudioByUser(user.getId_user(), lang);
		 
		 est.setData(list);
		 
		 return est;
		 
	}
	
	
	@RequestMapping(value = "/getListWorkflow16", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjDataListEstudio getListWorkflow16(@RequestParam("id") int id,
											    @RequestParam("lang") String lang)
	{
		ObjDataListEstudio est = new ObjDataListEstudio();
		ArrayList<ObjEstudio> list =  new ArrayList<ObjEstudio>();
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessWorkflow15 lists = (AccessWorkflow15) context.getBean("AccessWorkflow15");
		
		ObjLoginUser user = logins.getUserByLogin(authentication.getName());
		logger.debug("**********REQUEST PARAM FILTRO GET LIST ASIGN********************************************* "); 
		 	logger.debug("id: "+ id);
		 	logger.debug("lang: "+ lang);
		 	logger.debug("user: "+ user.getId_user());
		 	logger.debug("url: http://localhost:8080/Manager/RestWorkflow15/getListWorkflow15?id="+id+"&lang="+lang+ " ");
		 logger.debug("**********END REQUEST PARAM FILTRO GET LIST ASIGN********************************************* ");
		
		 
		 list = lists.getListEstudioByUserKick(user.getId_user(), lang);
		 
		 est.setData(list);
		 
		 return est;
		 
	}
	
	@RequestMapping(value = "/updateActiveWorkflow15", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjResultCreaCotOp updateActiveWorkflow15(@RequestParam("id") int id,
											         @RequestParam("crm") String crm,
											         @RequestParam("sap") String sap,
											         @RequestParam("nombre") String nombre,
											         @RequestParam("industria") int industria,
											         @RequestParam("tipo_estudio") String tipo_estudio,
											         @RequestParam("tipo_entrevista") String tipo_entrevista,
											         @RequestParam("muestra") int muestra,
											         @RequestParam("res01") int res01,
											         @RequestParam("res02") int res02,
											         @RequestParam("res03") int res03,
											         @RequestParam("set_up_1") String set_up_1,
											         @RequestParam("set_up_2") String set_up_2,
											         @RequestParam("set_up_3") String set_up_3,
											         @RequestParam("set_up_4") String set_up_4,
											         @RequestParam("set_up_5") String set_up_5,
											         @RequestParam("set_up_6") String set_up_6,
											         @RequestParam("set_up_7") String set_up_7,
											         @RequestParam("set_up_8") String set_up_8,
											         @RequestParam("set_up_9") String set_up_9,
											         @RequestParam("set_up_10") String set_up_10,
											         @RequestParam("set_up_11") String set_up_11,
											         @RequestParam("por_incidencia") int por_incidencia,
											         @RequestParam("por_irebate") int por_irebate,
											         @RequestParam("libro_codigo") String libro_codigo,
											         @RequestParam("verbatim") String verbatim,
											         @RequestParam("pre_proceso") String pre_proceso,
											         @RequestParam("abierta_codi") int abierta_codi,
											         @RequestParam("otra_codi") int otra_codi,
											         @RequestParam("tmo") int tmo,
											         @RequestParam("sector_medicion") int sector_medicion,
													 @RequestParam("id_geografia") String id_geografia,
													 @RequestParam("cant_paises") int cant_paises,
													 @RequestParam("producto") String producto,
													 @RequestParam("booking_legal_entity") String booking_legal_entity,
													 @RequestParam("centro_costo_op") String centro_costo_op,
													 @RequestParam("por_ejec_estudio") int por_ejec_estudio,
													 @RequestParam("digital_op") String digital_op,
													 @RequestParam("moneda_op") String moneda_op,
													 @RequestParam("date_prob_in_est_op") String date_prob_in_est_op,
													 @RequestParam("date_prob_entre_est_op") String date_prob_entre_est_op,
													 @RequestParam("ddate_pres_of_equipo_op") String ddate_pres_of_equipo_op,
													 @RequestParam("ddate_pres_gps_op") String ddate_pres_gps_op,
													 @RequestParam("ddate_pres_clie_op") String ddate_pres_clie_op,
													 @RequestParam("obj_obs_op") String obj_obs_op,
													 @RequestParam("desc_op") String desc_op,
													 
													 @RequestParam("lenguaje") String lenguaje,
													 @RequestParam("frecuencia") String frecuencia,
													 @RequestParam("frecuencia_ola") String frecuencia_ola,
													 @RequestParam("frecuencia_otro") String frecuencia_otro,
													 @RequestParam("frecuencia_cant_olas") int frecuencia_cant_olas,
													 @RequestParam("modo") String modo,
													 @RequestParam("sub_modo") String sub_modo,
													 @RequestParam("dur_intro_entrevista") int dur_intro_entrevista,
													 @RequestParam("dur_cuest_entrevista") int dur_cuest_entrevista,
													 @RequestParam("dur_total_entrevista") int dur_total_entrevista,
													 @RequestParam("req_prueba_producto") String req_prueba_producto,
													 @RequestParam("req_compra_producto") String req_compra_producto,
													 @RequestParam("tipo_test") String tipo_test,
													 @RequestParam("req_retorno_producto") String req_retorno_producto,
													 @RequestParam("descrip_producto") String descrip_producto,
													 @RequestParam("req_reclutamiento") String req_reclutamiento,
													 @RequestParam("modo_reclutamiento") String modo_reclutamiento,
													 @RequestParam("sub_cuota") String sub_cuota,
													 @RequestParam("desc_reclutamiento") String desc_reclutamiento,
													 
													 @RequestParam("tipo_entrevistado") String tipo_entrevistado,
													 @RequestParam("des_entrevistado") String des_entrevistado,
													 @RequestParam("ident_cliente_entrevistado") String ident_cliente_entrevistado,
													 @RequestParam("como_entrevistado") String como_entrevistado,
													 @RequestParam("req_piloto") String req_piloto,
													 @RequestParam("desc_piloto") String desc_piloto,
													 @RequestParam("ej_diseno") String ej_diseno,
													 @RequestParam("esp_diseno") String esp_diseno,
													 @RequestParam("metodo_random") String metodo_random,
													 @RequestParam("random_route") String random_route,
													 @RequestParam("adress_route") String adress_route,
													 @RequestParam("adm_route") String adm_route,
													 @RequestParam("metodo_seleccion") String metodo_seleccion,
													 @RequestParam("numero_preg") int numero_preg,
													 @RequestParam("obj_respuesta") String obj_respuesta,
													 @RequestParam("inf_probalis") String inf_probalis,
													 
													 @RequestParam("req_materiales") String req_materiales,
													 @RequestParam("esp_materiales") String esp_materiales,
													 
													 @RequestParam("req_sesioninfo") String req_sesioninfo,
													 @RequestParam("req_insentivo_sesioninfo") String req_insentivo_sesioninfo,
													 @RequestParam("respon_sesioninfo") int respon_sesioninfo,
													 @RequestParam("desc_sesioninfo") String desc_sesioninfo,
													 
													 @RequestParam("rrd_cati") String rrd_cati,
													 @RequestParam("rrdcel_cati") String rrdcel_cati,
													 @RequestParam("target_cati") String target_cati,
													 @RequestParam("clientfone_cati") String clientfone_cati,
													 @RequestParam("otros_cati") String otros_cati,
													 @RequestParam("defej_cati") String defej_cati,
													 @RequestParam("descdef_cati") String descdef_cati,
													 @RequestParam("descdefotro_cati") String descdefotro_cati,
													 @RequestParam("defobj_cati") String defobj_cati,
													 @RequestParam("porespc_cati") int porespc_cati,
													 @RequestParam("numcel_cati") String numcel_cati,
													 @RequestParam("deldupli_cati") String deldupli_cati,
													 @RequestParam("totalnumfile_cati") int totalnumfile_cati,
													 @RequestParam("totalnumrec_cati") int totalnumrec_cati,
													 @RequestParam("poruseable_cati") int poruseable_cati,
													 @RequestParam("freqfile_cati") String freqfile_cati,
													 @RequestParam("freqfilecual_cati") String freqfilecual_cati,
													 
													 @RequestParam("precod_cod") String precod_cod,
													 @RequestParam("pregabierta_cod") int pregabierta_cod,
													 @RequestParam("pregotro_cod") int pregotro_cod,
													 @RequestParam("codabierto_cod") String codabierto_cod,
													 @RequestParam("tradcod_cod") String tradcod_cod,
													 @RequestParam("esptrad_cod") String esptrad_cod,
													 @RequestParam("editcod_cod") String editcod_cod,
													 @RequestParam("nivedicion_cod") String nivedicion_cod,
													 @RequestParam("otrainfo_cod") String otrainfo_cod,
													 
													 @RequestParam("verbatim_cod") String verbatim_cod,
													 
													 @RequestParam("esp_datos_tab") String esp_datos_tab,
													 @RequestParam("formato_entreg_tab") String formato_entreg_tab,
													 @RequestParam("tablapre_tab") String tablapre_tab,
													 @RequestParam("cuadroten_tab") String cuadroten_tab,
													 @RequestParam("tend_tabla_tab") String tend_tabla_tab,
													 @RequestParam("num_banner_tab") int num_banner_tab,
													 @RequestParam("anexar_datos_tab") String anexar_datos_tab,
													 @RequestParam("esp_req_tab") String esp_req_tab,
													 @RequestParam("otra_info_tab") String otra_info_tab,
													 @RequestParam("prueba_stat_tab") String prueba_stat_tab,
													 @RequestParam("ponder_tab") String ponder_tab,
													 @RequestParam("freq_archivo_tab") String freq_archivo_tab,
													 @RequestParam("freq_tabla_tab") String freq_tabla_tab,
													 
													 @RequestParam("msience_ana") String msience_ana,
													 @RequestParam("tomamuestra_ana") String tomamuestra_ana,
													 @RequestParam("desp_linea_ana") String desp_linea_ana,
													 @RequestParam("fono_fuente_ana") String fono_fuente_ana,
													 @RequestParam("pond_necms_ana") String pond_necms_ana,
													 @RequestParam("pond_complej_ana") String pond_complej_ana,
													 @RequestParam("pond_calib_ana") String pond_calib_ana,
													 @RequestParam("pond_calibel_ana") String pond_calibel_ana,
													 @RequestParam("univ_datos_ana") String univ_datos_ana,
													 @RequestParam("puntos_ref_ana") String puntos_ref_ana,
													 @RequestParam("wave_frec_ana") String wave_frec_ana,
													 @RequestParam("met_enc_ana") String met_enc_ana,
													 
													 @RequestParam("entreg_1_ana") String entreg_1_ana,
													 @RequestParam("entreg_2_ana") String entreg_2_ana,
													 @RequestParam("entreg_3_ana") String entreg_3_ana,
													 @RequestParam("entreg_4_ana") String entreg_4_ana,
													 @RequestParam("entreg_5_ana") String entreg_5_ana,
													 @RequestParam("entreg_6_ana") String entreg_6_ana,
													 
													  @RequestParam("bc_01") String bc_01,
													  @RequestParam("bc_02") String bc_02,
													  @RequestParam("bc_03") String bc_03,
													  @RequestParam("bc_04") String bc_04,
													  @RequestParam("bc_05") String bc_05,
													  @RequestParam("bc_06") String bc_06,
													  @RequestParam("bc_07") String bc_07,
													  @RequestParam("bc_08") String bc_08,
													  @RequestParam("bc_09") String bc_09,
													  @RequestParam("bc_10") String bc_10,
													  @RequestParam("bc_11") String bc_11,
													  @RequestParam("bc_12") String bc_12,
													  @RequestParam("bc_13") String bc_13,
													  @RequestParam("bc_14") String bc_14,
													  @RequestParam("bc_15") String bc_15,
													  @RequestParam("bc_16") String bc_16,
													  @RequestParam("bc_17") String bc_17,
													  @RequestParam("bc_18") String bc_18,
													  @RequestParam("bc_19") String bc_19,
													  @RequestParam("bc_20") String bc_20,
													  @RequestParam("bc_21") String bc_21,
													  
													  @RequestParam("cuest_01") int cuest_01,
													  @RequestParam("cuest_02") int cuest_02,
													  @RequestParam("cuest_03") int cuest_03,
													  @RequestParam("cuest_04") String cuest_04,
													  @RequestParam("cuest_05") int cuest_05,
													  @RequestParam("cuest_06") int cuest_06,
													  @RequestParam("cuest_07") String cuest_07,
													  @RequestParam("cuest_08") String cuest_08,
													  @RequestParam("cuest_09") String cuest_09,
													  @RequestParam("cuest_10") String cuest_10,
													  @RequestParam("cuest_11") int cuest_11,
													  @RequestParam("cuest_12") String cuest_12,
													  @RequestParam("cuest_13") String cuest_13,
													  @RequestParam("cuest_14") int cuest_14,
													  @RequestParam("cuest_15") String cuest_15,
													  @RequestParam("cuest_16") String cuest_16,
													  @RequestParam("cuest_17") String cuest_17,
													  @RequestParam("cuest_18") String cuest_18,
													  @RequestParam("cuest_19") String cuest_19
													)
	{
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessWorkflow15 lists = (AccessWorkflow15) context.getBean("AccessWorkflow15");
		AccessEstudio ests = (AccessEstudio) context.getBean("AccessEstudio");
		AccessCuestionario cuest = (AccessCuestionario) context.getBean("AccessCuestionario");
		AccessProyecto proys = (AccessProyecto) context.getBean("AccessProyecto");
		AccessTraza trz = (AccessTraza) context.getBean("AccessTraza");
		AccessUploadFile upl = (AccessUploadFile) context.getBean("AccessUploadFile");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		
		
		ObjLoginUser user = logins.getUserByLogin(authentication.getName());
		logger.debug("**********REQUEST PARAM FILTRO GET LIST ASIGN********************************************* "); 
		 	logger.debug("id: "+ id);
		 	logger.debug("crm: "+ crm);
		 	logger.debug("sap: "+ sap);
		 	
		 	logger.debug("user: "+ user.getId_user());
		 	
		 logger.debug("**********END REQUEST PARAM FILTRO GET LIST ASIGN********************************************* ");
		 
		 logger.debug("**********REQUEST PARAM GENERAL********************************************* "); 
			logger.debug("sector_medicion: "+ sector_medicion);
			logger.debug("industria_medicion: "+ industria);
			logger.debug("id_tipo_entrevista: "+ tipo_entrevista);
			logger.debug("nombre_operacion: "+ nombre);
			logger.debug("id_geografia: "+ id_geografia);
			logger.debug("cant_paises: "+ cant_paises);
			logger.debug("producto: "+ producto);
			logger.debug("tipo_estudio: "+ tipo_estudio);
			logger.debug("booking_legal_entity: "+ booking_legal_entity);
			logger.debug("centro_costo_op: "+ centro_costo_op);
			logger.debug("por_ejec_estudio: "+ por_ejec_estudio);
			logger.debug("digital_op: "+ digital_op);
			logger.debug("moneda_op: "+ moneda_op);
			logger.debug("date_prob_in_est_op: "+ date_prob_in_est_op);
			logger.debug("date_prob_entre_est_op: "+ date_prob_entre_est_op);
			logger.debug("ddate_pres_of_equipo_op: "+ ddate_pres_of_equipo_op);
			logger.debug("ddate_pres_gps_op: "+ ddate_pres_gps_op);
			logger.debug("ddate_pres_clie_op: "+ ddate_pres_clie_op);
			logger.debug("res_us1_op: "+ res01);
			logger.debug("res_us2_op: "+ res02);
			logger.debug("res_us3_op: "+ res03);
			logger.debug("obj_obs_op: "+ obj_obs_op);
			logger.debug("desc_op: "+ desc_op);
			logger.debug("set_up_1: "+ set_up_1);
			logger.debug("set_up_2: "+ set_up_2);
			logger.debug("set_up_3: "+ set_up_3);
			logger.debug("set_up_4: "+ set_up_4);
			logger.debug("set_up_5: "+ set_up_5);
			logger.debug("set_up_6: "+ set_up_6);
			logger.debug("set_up_7: "+ set_up_7);
			logger.debug("set_up_8: "+ set_up_8);
			logger.debug("set_up_9: "+ set_up_9);
			logger.debug("set_up_10: "+ set_up_10);
			logger.debug("set_up_11: "+ set_up_11);
		 logger.debug("**********END REQUEST PARAM GENERAL********************************************* ");
		 
		 logger.debug("**********REQUEST PARAM  BUSSNESS CASE********************************************* "); 
			logger.debug("id: "+ id);
			logger.debug("bc_01: "+ bc_01);
			logger.debug("bc_02: "+ bc_02);
			logger.debug("bc_03: "+ bc_03);
			logger.debug("bc_04: "+ bc_04);
			logger.debug("bc_05: "+ bc_05);
			logger.debug("bc_06: "+ bc_06);
			logger.debug("bc_07: "+ bc_07);
			logger.debug("bc_08: "+ bc_08);
			logger.debug("bc_09: "+ bc_09);
			logger.debug("bc_10: "+ bc_10);
			logger.debug("bc_11: "+ bc_11);
			logger.debug("bc_12: "+ bc_12);
			logger.debug("bc_13: "+ bc_13);
			logger.debug("bc_14: "+ bc_14);
			logger.debug("bc_15: "+ bc_15);
			logger.debug("bc_16: "+ bc_16);
			logger.debug("bc_17: "+ bc_17);
			logger.debug("bc_18: "+ bc_18);
			logger.debug("bc_19: "+ bc_18);
			logger.debug("bc_20: "+ bc_20);
			logger.debug("bc_21: "+ bc_21);
			
		 logger.debug("**********END REQUEST PARAM BUSSNESS CASE********************************************* ");
		 String fechaNow = format3.format(new Date());
		 
		 int aux_codop = 1;
		 
		 ObjProyectoEstandar opr = new ObjProyectoEstandar();
		 opr.setCodigo_proyectom("0");
		 opr.setNombre_proyectop(nombre);
		 opr.setEstado_proyectop(1);
		 opr.setFcreacion_proyectom(fechaNow);
		 opr.setScreacion_proyectom(user.getId_user());
		 opr.setFmod_proyectom(fechaNow);
		 opr.setSmod_proyectom(user.getId_user());
		 opr.setElimina_proyectom(0);
		 
		 int idProyecto = proys.createProyectoPadreInt(opr);
		 
		 logger.debug("Id Proyecto: "+ idProyecto);
		 
		 ObjResultCreaCotOp res = new ObjResultCreaCotOp();
		 ObjEstudio est = new ObjEstudio();
		 	est.setId_operacion(id);
		 	est.setId_crm(crm);
		 	est.setId_sap(sap);
		 	//est.setId_cliente(cliente);
			est.setSector_medicion(sector_medicion);
			est.setIndustria_medicion(industria);
			est.setId_tipo_entrevista(tipo_entrevista);
			est.setNombre_operacion(nombre);
			est.setId_proyectom(idProyecto);
			est.setCod_operacion(aux_codop);
		 
		 ests.updateOperacion(est);
		 
		 logger.info("SE ACTUALIZA ESTUDIO ID: "+ id );
		 
		 ObjEstudioDetalle det = new ObjEstudioDetalle();
			det.setId_operacion(id); 
		 	det.setId_geografia(id_geografia);
			 det.setCant_paises(cant_paises);
			 //det.setId_clie_facturar(facturar);
			 det.setTipo_estudio(tipo_estudio);
			 det.setBooking_legal_entity(booking_legal_entity);
			 det.setCentro_costo_op(centro_costo_op);
			 det.setPor_ejec_estudio(por_ejec_estudio);
			 det.setDigital_op(digital_op);
			 det.setMoneda_op(moneda_op);
			 det.setDate_prob_in_est_op(date_prob_in_est_op);
			 det.setDate_prob_entre_est_op(date_prob_entre_est_op);
			 det.setDdate_pres_of_equipo_op(ddate_pres_of_equipo_op);
			 det.setDdate_pres_gps_op(ddate_pres_gps_op);
			 det.setDdate_pres_clie_op(ddate_pres_clie_op);
			 det.setRes_us1_op(res01);
			 det.setRes_us2_op(res02);
			 det.setRes_us3_op(res03);
			 det.setObj_obs_op(obj_obs_op);
			 det.setDesc_op(desc_op);
			 det.setSet_up_1(set_up_1);
			 det.setSet_up_2(set_up_2);
			 det.setSet_up_3(set_up_3);
			 det.setSet_up_4(set_up_4);
			 det.setSet_up_5(set_up_5);
			 det.setSet_up_6(set_up_6);
			 det.setSet_up_7(set_up_7);
			 det.setSet_up_8(set_up_8);
			 det.setSet_up_9(set_up_9);
			 det.setSet_up_10(set_up_10);
			 det.setSet_up_11(set_up_11);
			 
			 det.setEntre_1(entreg_1_ana);
			 det.setEntre_2(entreg_2_ana);
			 det.setEntre_3(entreg_3_ana);
			 det.setEntre_4(entreg_4_ana);
			 det.setEntre_5(entreg_5_ana);
			 det.setEntre_6(entreg_6_ana);
			 
			 ests.updateDetalleOperacion(det);
			
			 logger.info("SE ACTUALIZA DETALLE ESTUDIO ID: "+ id );
			 
			 
			ObjEstudioRecoleccion rec = new ObjEstudioRecoleccion();
				rec.setId_operacion(id);
				rec.setLang_reco(lenguaje);
				rec.setFrec_reco(frecuencia);
				rec.setFrec_wave_reco(frecuencia_ola);
				rec.setCant_wave_reco(frecuencia_cant_olas);
				rec.setModo_reco(modo);
				rec.setSub_modo_reco(sub_modo);
				rec.setDur_intro_reco(dur_intro_entrevista);
				rec.setDur_cuest_reco(dur_cuest_entrevista);
				rec.setTotal_entr_reco(dur_total_entrevista);
				rec.setReq_piloto_entrev_reco(req_piloto);
				rec.setReq_compra_prod_test_reco(req_compra_producto);
				rec.setTipo__test_reco(tipo_test);
				rec.setReq_retorno_test_reco(req_retorno_producto);
				rec.setDes_req_piloto_entrev_reco(desc_piloto);
				rec.setReq_reclutamiento_test_reco(req_reclutamiento);
				rec.setModo_reclutamiento_test_reco(modo_reclutamiento);
				rec.setSub_cuota_test_reco(sub_cuota);
				rec.setDescr_recluta_test_reco(desc_reclutamiento);
				rec.setTipo_entrev_reco(tipo_entrevistado);
				rec.setDescrip_entrev_reco(des_entrevistado);
				rec.setIdent_clie_respond_entrev_reco(ident_cliente_entrevistado);
				rec.setComo_entrev_reco(como_entrevistado);
				rec.setReq_piloto_entrev_reco(req_piloto);
				rec.setDes_req_piloto_entrev_reco(desc_piloto);
				rec.setEjemplo_diseno_reco(ej_diseno);
				rec.setEjemplo_diseno_espe_reco(esp_diseno);
				rec.setMet_random_entrev_reco(metodo_random);
				rec.setRandom_rute_entrev_reco(random_route);
				rec.setAdres_rute_entrev_reco(adress_route);
				rec.setAdm_route_entrev_reco(adm_route);
				rec.setMet_sel_respon_entrev_reco(metodo_seleccion);
				rec.setNum_preg__entrev_reco(numero_preg);
				rec.setObj_taza_resp_entrev_reco(obj_respuesta);
				rec.setInfo_met_prob_entrev_reco(inf_probalis);
				
				rec.setNec_mat_entrev_reco(req_materiales);
				rec.setEsp_mater_entrev_reco(esp_materiales);
				
				rec.setReq_sesion_info_entrev_reco(req_sesioninfo);
				rec.setClie_entr_inse_entrev_reco(req_insentivo_sesioninfo);
				rec.setRespon_entrev_reco(respon_sesioninfo);
				rec.setDeescr_entrev_reco(desc_sesioninfo);
				
				rec.setRdd_land_cati(rrd_cati);
				rec.setRdd_cell_cati(rrdcel_cati);
				rec.setTarget_cati(target_cati);
				rec.setClient_fone_cati(clientfone_cati);
				rec.setOtros_cati(otros_cati);
				rec.setDef_eje_cati(defej_cati);
				rec.setDefi_cati(descdef_cati);
				rec.setOtros_cati(otros_cati);
				rec.setDef_ej_ob_cati(defobj_cati);
				rec.setPor_cell_clie_cati(porespc_cati);
				rec.setNum_cell_ded_cati(numcel_cati);
				rec.setReq_el_dupli_cati(deldupli_cati);
				
				ests.setRecoleccionOperacion(rec);
			 
				logger.info("SE ACTUALIZA RECOLECCION ESTUDIO ID: "+ id );
				
			ObjEstudioCodificacion cod = new ObjEstudioCodificacion();
				cod.setId_operacion(id);
				cod.setPre_codigo_codi(precod_cod);
				cod.setPreg_ab_codi(pregabierta_cod);
				cod.setPreg_otro_codi(pregotro_cod);
				cod.setCod_ab_ent_clie_codi(codabierto_cod);
				cod.setTraducir_codi(tradcod_cod);
				cod.setLang_codi(esptrad_cod);
				cod.setEditar_codi(editcod_cod);
				cod.setNivel_edi_codi(nivedicion_cod);
				cod.setOtra_info_codi(otrainfo_cod);
			
				ests.setCodificacionOperacion(cod);
				
				logger.info("SE ACTUALIZA CODIFICACION DE ESTUDIO ID: "+ id );
				
			ObjEstudioDigitacion dig = new ObjEstudioDigitacion();
				dig.setId_operacion(id);
				dig.setVerbatim_capture(verbatim_cod);
				dig.setPre_proceso(pre_proceso);
				
				ests.updateDigitacionOperacion(dig);
				
			
				logger.info("SE ACTUALIZA DIGITACION DE ESTUDIO ID: "+ id );
				
			ObjEstudioTabulacion tab = new ObjEstudioTabulacion();
				tab.setId_operacion(id);
				tab.setEsp_datos_entr_tab(esp_datos_tab);
				tab.setFormat_file_tab(formato_entreg_tab);
				tab.setTabla_prem_tab(tablapre_tab);
				tab.setCuadro_ten_tab(cuadroten_tab);
				tab.setTend_tabla_perio_tab(tend_tabla_tab);
				tab.setNum_est_banner_tab(num_banner_tab);
				tab.setAnexar_datotos_tab(anexar_datos_tab);
				tab.setEsp_datos_entr_tab(esp_datos_tab);
				tab.setOtra_info_tab(otra_info_tab);
				tab.setPrueba_stat_tab(prueba_stat_tab);
				tab.setPondera_tab(ponder_tab);
				tab.setFrec_entrega_datos_tab(freq_archivo_tab);
				tab.setFrec_tabla_datos_tab(freq_tabla_tab);
			
				ests.updateTabulacionOperacion(tab);
				
				logger.info("SE ACTUALIZA TABULACION ESTUDIO ID: "+ id );
				
			ObjEstudioAnalisis ana = new ObjEstudioAnalisis();
				ana.setId_operacion(id);
				ana.setDis_muestra_ana(msience_ana);
				ana.setTom_mue_mez_pan_ana(tomamuestra_ana);
				ana.setDesp_lin_fij_cel_rdd_nec_ana(desp_linea_ana);
				ana.setTel_fuente_eje_ana(fono_fuente_ana);
				ana.setPond_nec_mark_muest_est_ana(pond_necms_ana);
				ana.setCompl_pond_ana(pond_complej_ana);
				ana.setPondera_cal_nec_est_ana(pond_calib_ana);
				ana.setPondera_cal_nec_est_ana(pond_calibel_ana);
				ana.setUni_nec_ana(univ_datos_ana);
				ana.setPunto_ref_nec_ana(puntos_ref_ana);
				
				ests.updateAnalisisOperacion(ana);
				
				logger.info("SE ACTUALIZA ANALISIS ESTUDIO ID: "+ id );
				
				ObjEstudioBussnessCase bc = new ObjEstudioBussnessCase();
				 bc.setId_operacion(id);
				 bc.setB_case_1_ceps_sam(bc_01);
				 bc.setB_case_1_text_ceps_sam(bc_02);
				 bc.setB_case_2_ceps_sam(bc_03);
				 bc.setB_case_2_text_ceps_sam(bc_04);
				 bc.setB_case_3_ceps_sam(bc_05);
				 bc.setB_case_4_ceps_sam(bc_06);
				 bc.setB_case_4_text_ceps_sam(bc_07);
				 bc.setB_case_5_ceps_sam(bc_08);
				 bc.setB_case_5_text_ceps_sam(bc_09);
				 bc.setB_case_6_text_ceps_sam(bc_10);
				 bc.setB_case_7_ceps_sam(bc_11);
				 bc.setB_case_7_text_ceps_sam(bc_12);
				 bc.setB_case_8_ceps_sam(bc_13);
				 bc.setB_case_9_ceps_sam(bc_14);
				 bc.setB_case_9_text_ceps_sam(bc_15);
				 bc.setB_case_10_text_ceps_sam(bc_16);
				 bc.setB_case_11_text_ceps_sam(bc_17);
				 bc.setB_case_12_ceps_sam(bc_18);
				 bc.setB_case_13_ceps_sam(bc_19);
				 bc.setB_case_13_text_ceps_sam(bc_20);
				 bc.setB_case_14_text_ceps_sam(bc_21);
			
				 ests.updateBussnessCaseOperacion(bc);
				 
				 logger.info("SE ACTUALIZA BC ESTUDIO ID: "+ id ); 
				 
				ObjEstudioCuestionario cu = new ObjEstudioCuestionario();
					cu.setId_operacion(id);
					cu.setNum_cuest_unic_cuest(cuest_01);
					cu.setPor_cuest_sup_cuest(cuest_02);
					cu.setPor_cuest_camb_cuest(cuest_03);
					cu.setDes_camb_cuest(cuest_04);
					cu.setNum_resp_ab_cuest(cuest_05);
					cu.setPor_resp_open_end_cuest(cuest_06);
					cu.setEsp_num_otra_cuest(cuest_07);
					cu.setEsp_por_otro_men_cuest(cuest_08);
					cu.setCust_trad_req_cuest(cuest_09);
					cu.setOtra_info_esp_cuest(cuest_10);
					cu.setNum_total_preg_prog_cuest(cuest_11);
					cu.setEsp_req_exp_cuest(cuest_12);
					cu.setOtro_cati_cuest(cuest_13);
					cu.setNum_tot_preg_prog_cati_cuest(cuest_14);
					cu.setVideo_cuest(cuest_15);
					cu.setFile_cuest(cuest_16);
					cu.setAudio_cuest(cuest_17);
					cu.setEsp_req_exp2_cuest(cuest_18);
					cu.setOtro_cawi_capi_cuest(cuest_19);
				
				cuest.setCuestionario(cu);
				
				logger.info("SE ACTUALIZA CUSTIONARIO ESTUDIO ID: "+ id );
				
				
				
				//Activa Operacion
				
				ObjEstudio acti = new ObjEstudio();
				 acti.setId_operacion(id);
				 acti.setActiva_operacion(1);
				 acti.setFactivacion_medicion(fechaNow);
				 acti.setFmod_proyectom(fechaNow);
				 acti.setSmod_proyectom(user.getId_user());
				 acti.setCola_operacion(3);
				 acti.setEstado_medicion(4);
				
				ests.updateActivateOperacion(acti);
				
				logger.info("SE ACTIVA ESTUDIO ID: "+ id ); 
				
				ests.updateColaEstadoOperacion(acti);
				
				ObjEstudio fetapa = new ObjEstudio();
					fetapa.setId_operacion(id);
					fetapa.setFingreso_puesta_marcha_operacion(fechaNow);
				
				ests.updateOperacionFechasEtapa(fetapa);
				
				logger.info("SE ACTUALIZA ESTADO ESTUDIO ID: "+ id );
				
				String urlBasePrivada = tools.getValorConfigById(13);
				String urlTmpExcelUser = "estudios/";
				
				upl.existDirectorio(urlBasePrivada + urlTmpExcelUser + id + "-"+aux_codop, 1);
				
				logger.info("SE CREA DIRECTORIO DE ESTUDIO: "+ urlBasePrivada + urlTmpExcelUser + id + "-"+aux_codop );
				
				
				
				trz.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), idProyecto, id, 15, 2, 0, "ACTIVACION DE ESTUDIO", "USUARIO ACTIVA ESTUDIO "+ nombre,4));
				
				res.setId_operacion(id);
				res.setId_proyecto(idProyecto);
				res.setText("Estudio "+nombre+" esta Activado");
				
		 return res;
		 
	}
}
