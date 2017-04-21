package cl.nexo.manager.controller.rest.proyecto;

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

import cl.nexo.manager.access.combo.box.AccessComboBox;
import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.access.login.AccessPerfil;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.producto.AccessProducto;
import cl.nexo.manager.access.proyecto.AccessCotizacion;
import cl.nexo.manager.access.proyecto.AccessEstudio;
import cl.nexo.manager.access.traza.AccessTraza;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.login.ObjPerfilLogin;
import cl.nexo.manager.obj.proyecto.ObjDataListEstudio;
import cl.nexo.manager.obj.proyecto.ObjEstudio;
import cl.nexo.manager.obj.proyecto.ObjEstudioAnalisis;
import cl.nexo.manager.obj.proyecto.ObjEstudioBussnessCase;
import cl.nexo.manager.obj.proyecto.ObjEstudioDetalle;
import cl.nexo.manager.obj.proyecto.ObjEstudioDigitacion;
import cl.nexo.manager.obj.proyecto.ObjEstudioFiltros;
import cl.nexo.manager.obj.proyecto.ObjEstudioProducto;
import cl.nexo.manager.obj.proyecto.ObjEstudioTabulacion;
import cl.nexo.manager.obj.proyecto.ObjResultCreaCotOp;
import cl.nexo.manager.obj.tools.ObjComboSelect2ValueInt;
import cl.nexo.manager.obj.tools.ObjGeneralResultInt;
import cl.nexo.manager.obj.traza.ObjTrazaManager;

@RestController
@RequestMapping("/RestCotizacion")
public class RestCotizacion {
	
	private static final Logger logger = Logger.getLogger(RestCotizacion.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	@RequestMapping(value = "/getCombo", method = RequestMethod.GET,headers="Accept=application/json")
	public ArrayList<ObjComboSelect2ValueInt> getCombo(@RequestParam("id") int id,
													   @RequestParam("place") String place)
	{
		 ArrayList<ObjComboSelect2ValueInt> combo =  new ArrayList<ObjComboSelect2ValueInt>();
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessCotizacion combos = (AccessCotizacion) context.getBean("AccessCotizacion");
		 
		 combo = combos.getCombo(id,place);
		 
		 return combo;
		 
	}
	@RequestMapping(value = "/getListCotiza", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjDataListEstudio getListCotiza(@RequestParam("filtro") int filtro,
											   @RequestParam("id") String id,
											   @RequestParam("industria") int industria,
											   @RequestParam("nombre") String nombre,
											   @RequestParam("cliente") int cliente,
											   @RequestParam("producto") String producto,
											   @RequestParam("digitacion") String digitacion,
											   @RequestParam("lang") String lang)
	{
		ObjDataListEstudio est = new ObjDataListEstudio();
		 ArrayList<ObjEstudio> list =  new ArrayList<ObjEstudio>();
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessEstudio lists = (AccessEstudio) context.getBean("AccessEstudio");
		 logger.debug("**********REQUEST PARAM FILTRO GET LIST COTIZA********************************************* "); 
		 	logger.debug("filtro: "+ filtro);
		 	logger.debug("id: "+ id);
		 	logger.debug("industria: "+ industria);
		 	logger.debug("nombre: "+ nombre);
		 	logger.debug("cliente: "+ cliente);
		 	logger.debug("producto: "+ producto);
		 	logger.debug("digitacion: "+ digitacion);
		 	logger.debug("lang: "+ lang);
		 logger.debug("**********END REQUEST PARAM FILTRO GET LIST COTIZA********************************************* ");
		 ObjEstudioFiltros fil = new ObjEstudioFiltros();
		 fil.setFiltra(filtro);
		 fil.setId_cotizacion(id);
		 fil.setId_idustria(industria);
		 fil.setNombre_operacion(nombre);
		 fil.setId_cliente(cliente);
		 fil.setProductos(producto);
		 fil.setDigitacion(digitacion);
		 fil.setLang(lang);
		 fil.setActiva_operacion(0);
		 
		 list = lists.getListEstudioByFilter(fil);
		 
		 est.setData(list);
		 
		 return est;
		 
	}
	@RequestMapping(value = "/setCotizacion1", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjResultCreaCotOp setCotizacion1(@RequestParam("id") int id,
											  @RequestParam("canal_venta") int canal_venta,
											  @RequestParam(value="cod_sam", required=false) String cod_sam,
											  @RequestParam(value="tipo_sam", required=false) String tipo_sam,
											  @RequestParam(value="id_sap", required=false) String id_sap,
											  @RequestParam(value="cod_manager", required=false) String cod_manager,
											  @RequestParam(value="cod_crm", required=false) String cod_crm,
											  @RequestParam("id_cliente") int id_cliente,
											  @RequestParam(value="area_medicion", required=false) int area_medicion,
											  @RequestParam("sector_medicion") int sector_medicion,
											  @RequestParam("industria_medicion") int industria_medicion,
											  @RequestParam("id_tipo_entrevista") String id_tipo_entrevista,
											  @RequestParam("nombre_operacion") String nombre_operacion,
											  @RequestParam("id_geografia") String id_geografia,
											  @RequestParam("cant_paises") int cant_paises,
											  @RequestParam("id_clie_facturar") int id_clie_facturar,
											  @RequestParam("producto") String producto,
											  @RequestParam("tipo_estudio") String tipo_estudio,
											  @RequestParam("booking_legal_entity") String booking_legal_entity,
											  @RequestParam(value="centro_costo_op", required=false) String centro_costo_op,
											  @RequestParam(value="por_ejec_estudio", required=false) int por_ejec_estudio,
											  @RequestParam(value="digital_op", required=false) String digital_op,
											  @RequestParam(value="moneda_op", required=false) String moneda_op,
											  @RequestParam("date_prob_in_est_op") String date_prob_in_est_op,
											  @RequestParam("date_prob_entre_est_op") String date_prob_entre_est_op,
											  @RequestParam("ddate_pres_of_equipo_op") String ddate_pres_of_equipo_op,
											  @RequestParam("ddate_pres_gps_op") String ddate_pres_gps_op,
											  @RequestParam("ddate_pres_clie_op") String ddate_pres_clie_op,
											  @RequestParam("res_us1_op") int res_us1_op,
											  @RequestParam("res_us2_op") int res_us2_op,
											  @RequestParam("res_us3_op") int res_us3_op,
											  @RequestParam(value="obj_obs_op", required=false) String obj_obs_op,
											  @RequestParam(value="desc_op", required=false) String desc_op,
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
											  @RequestParam("set_up_11") String set_up_11
											  )
	{
		ObjResultCreaCotOp result =  new ObjResultCreaCotOp();
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessCotizacion cots = (AccessCotizacion) context.getBean("AccessCotizacion");
		 LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		 AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		 AccessGeneralTools generals = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		 AccessProducto prods = (AccessProducto) context.getBean("AccessProducto");
		 AccessTraza trazas = (AccessTraza) context.getBean("AccessTraza");
		 SecurityContext securityContext = SecurityContextHolder.getContext();
		 Authentication authentication = securityContext.getAuthentication();
		
		 logger.debug("**********REQUEST PARAM CREATE COTIZACION********************************************* "); 
			logger.debug("id: "+ id);
			logger.debug("canal_venta: "+ canal_venta);
			logger.debug("id_cliente: "+ id_cliente);
			logger.debug("sector_medicion: "+ sector_medicion);
			logger.debug("industria_medicion: "+ industria_medicion);
			logger.debug("id_tipo_entrevista: "+ id_tipo_entrevista);
			logger.debug("nombre_operacion: "+ nombre_operacion);
			logger.debug("id_geografia: "+ id_geografia);
			logger.debug("cant_paises: "+ cant_paises);
			logger.debug("id_clie_facturar: "+ id_clie_facturar);
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
			logger.debug("res_us1_op: "+ res_us1_op);
			logger.debug("res_us2_op: "+ res_us2_op);
			logger.debug("res_us3_op: "+ res_us3_op);
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
		  logger.debug("**********END REQUEST PARAM CREATE COTIZACION********************************************* ");
		 
		 
		 
		 
		 ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
		 ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
		 
		 int permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 16);
		 String fechaNow = format3.format(new Date());
		 
		 if(permisoModulo == 1 || permisoModulo == 2){
			 String uuid = generals.getNewUuId();
			 ObjEstudio estudio = new ObjEstudio();
			 ArrayList<ObjEstudioProducto> prod = new ArrayList<ObjEstudioProducto>();
			 ObjEstudioDetalle det = new ObjEstudioDetalle();
			 
			 //Operacion
			 estudio.setId_cotizacion(id);
			 estudio.setId_proyectom(1); //por defecto las cotizaciones se crean sobre el contenedor SAM luego de aprobar se relaciona con estudios
			 estudio.setCanal_venta(1);
			 estudio.setCod_sam("");
			 estudio.setTipo_sam("");
			 estudio.setId_sap("");
			 estudio.setCod_manager("");
			 estudio.setCodigo_cotizacion(String.valueOf(cots.getCodCotizacionByUid(id)));
			 estudio.setCod_operacion(0);
			 estudio.setId_crm("");
			 estudio.setUid_operacion(uuid);
			 estudio.setId_cliente(id_cliente);
			 estudio.setArea_medicion(area_medicion);
			 estudio.setSector_medicion(sector_medicion);
			 estudio.setIndustria_medicion(industria_medicion);
			 estudio.setId_tipo_entrevista(id_tipo_entrevista);
			 estudio.setNombre_operacion(nombre_operacion);
			 estudio.setEstado_medicion(1);
			 estudio.setOrden_medicion(1);
			 estudio.setCola_operacion(0);
			 estudio.setPriori_operacion(100);
			 estudio.setActiva_operacion(0);
			 estudio.setFcreacion_proyectom(fechaNow);
			 estudio.setScreacion_proyectom(user.getId_user());
			 estudio.setFmod_proyectom(fechaNow);
			 estudio.setSmod_proyectom(user.getId_user());
			 estudio.setElimina_proyectom(0);
			 
			 //Detalle estudio
			 
			 int cant_pais = 1;
			 if(id_geografia.equals("1") || id_geografia.equals("2")){
				 cant_pais = 1;
			 }else{
				 cant_pais = cant_paises;
			 }
			 
			 det.setId_geografia(id_geografia);
			 det.setCant_paises(cant_pais);
			 det.setId_clie_facturar(id_clie_facturar);
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
			 det.setRes_us1_op(res_us1_op);
			 det.setRes_us2_op(res_us2_op);
			 det.setRes_us3_op(res_us3_op);
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
			 det.setEntre_1("false");
			 det.setEntre_2("false");
			 det.setEntre_3("false");
			 det.setEntre_4("false");
			 det.setEntre_5("false");
			 det.setEntre_6("false");
			 
			 //Producto
			 prod = prods.getArrayProducto(producto, uuid, 0);
			 logger.debug("Size Prod: "+ prod.size());
			 
			 //Set Adicional Estudio
			 estudio.setDetalle(det);
			 estudio.setProductos(prod);
			 
			 result = cots.newCotizacion(estudio); 
			 
			 logger.info("Cotizacion Fase 1 creada con codigo: "+ result.getCodigo_cotizacion());
			 trazas.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, result.getId_operacion(), 16, 0, 0, "ADD NEW COTIZACION", "Se Crea con Exito! ",7));
			 
			 
			 result.setText("Se ha Creado Cotizaci&oacute;n Correctamente: <strong>"+ result.getCodigo_cotizacion() +"</strong> ");
		 
			 
		 }else{
			 result.setId_cotizacion(0);
			 result.setCodigo_cotizacion("0-0"); 
			 logger.info("Sin Permiso de crear Cotizacion Fase 1 user: "+ user.getId_user() + " -- Fecha: "+ fechaNow);
			 trazas.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, 0, 16, 0, 0, "ADD NEW COTIZACION", "Se Bloquea acceso a creacion ya que no tiene privilegios de acceso! ",7));
			 
			 result.setText("Permiso de Acceso");
		 }
		 
		 return result;
		 
	}
	@RequestMapping(value = "/setCotizacion2", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjResultCreaCotOp setCotizacion2(@RequestParam("id") int id,
											  @RequestParam("modo") String modo,
											  @RequestParam("sub_modo") String sub_modo,
											  @RequestParam("dur_intro") int dur_intro,
											  @RequestParam("dur_cuest") int dur_cuest,
											  @RequestParam("dur_total") int dur_total
											  )
	{
		ObjResultCreaCotOp result =  new ObjResultCreaCotOp();
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessCotizacion cots = (AccessCotizacion) context.getBean("AccessCotizacion");
		 LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		 AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		 AccessGeneralTools generals = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		 AccessEstudio ests = (AccessEstudio) context.getBean("AccessEstudio");
		 AccessTraza trazas = (AccessTraza) context.getBean("AccessTraza");
		 SecurityContext securityContext = SecurityContextHolder.getContext();
		 Authentication authentication = securityContext.getAuthentication();
		
		 logger.debug("**********REQUEST PARAM CREATE COTIZACION********************************************* "); 
			logger.debug("id: "+ id);
			logger.debug("modo: "+ modo);
			logger.debug("sub_modo: "+ sub_modo);
			logger.debug("dur_intro: "+ dur_intro);
			logger.debug("dur_cuest: "+ dur_cuest);
			logger.debug("dur_total: "+ dur_total);
		  logger.debug("**********END REQUEST PARAM CREATE COTIZACION********************************************* ");
		 
		 
		 
		 
		 ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
		 ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
		 
		 int permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 16);
		 String fechaNow = format3.format(new Date());
		 
		 if(permisoModulo == 1 || permisoModulo == 2){
			 ObjEstudioDetalle det = new ObjEstudioDetalle();
			 int id_manager = ests.getIdOperacionDetalleByUid(id);
			 
			 det.setId_manager(id_manager);
			 det.setId_operacion(id);
			 det.setModo_cot(modo);
			 det.setSubmodo_cot(sub_modo);
			 det.setDur_intro_reco(dur_intro);
			 det.setDur_cuest_reco(dur_cuest);
			 det.setTotal_entr_reco(dur_total);
			 
			 //Detalle estudio
			 
			 
			 
			 logger.info("Cotizacion Fase 2 Actualiza Operacion Id: "+ id);
			 
			 ests.updateDetalleOperacion(det);
			 
			 trazas.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, result.getId_operacion(), 16, 0, 0, "ADD NEW COTIZACION FASE 2", "Se Actualiza estudio datos Recoleccion ",7));
			 
			 result.setId_operacion(id);
			 result.setText("Se ha Creado Cotizaci&oacute;n Correctamente: <strong>"+ result.getId_operacion() +"</strong> ");
		 
			 
		 }else{
			 result.setId_cotizacion(0);
			 result.setCodigo_cotizacion("0-0"); 
			 logger.info("Sin Permiso de crear Cotizacion Fase 1 user: "+ user.getId_user() + " -- Fecha: "+ fechaNow);
			 trazas.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, 0, 16, 0, 0, "ADD NEW COTIZACION", "Se Bloquea acceso a creacion ya que no tiene privilegios de acceso! ",7));
			 
			 result.setText("Permiso de Acceso");
		 }
		 
		 return result;
		 
	}
	@RequestMapping(value = "/setCotizacion3", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjResultCreaCotOp setCotizacion3(@RequestParam("id") int id,
											  @RequestParam("dig") String dig,
											  @RequestParam("ver_capture") String ver_capture,
											  @RequestParam("tab") String tab,
											  @RequestParam("datos_entrega") String datos_entrega,
											  @RequestParam("formato_entrega") String formato_entrega,
											  @RequestParam("tabla_pre") String tabla_pre,
											  @RequestParam("analisis") String analisis,
											  @RequestParam("diseno_muestra") String diseno_muestra,
											  @RequestParam("entrega") String entrega,
											  @RequestParam("entre_1") String entre_1,
											  @RequestParam("entre_2") String entre_2,
											  @RequestParam("entre_3") String entre_3,
											  @RequestParam("entre_4") String entre_4,
											  @RequestParam("entre_5") String entre_5,
											  @RequestParam("entre_6") String entre_6,
											  @RequestParam("estado_setup") int estado_setup
											  )
	{
		ObjResultCreaCotOp result =  new ObjResultCreaCotOp();
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessCotizacion cots = (AccessCotizacion) context.getBean("AccessCotizacion");
		 LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		 AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		 AccessGeneralTools generals = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		 AccessEstudio ests = (AccessEstudio) context.getBean("AccessEstudio");
		 AccessTraza trazas = (AccessTraza) context.getBean("AccessTraza");
		 SecurityContext securityContext = SecurityContextHolder.getContext();
		 Authentication authentication = securityContext.getAuthentication();
		
		 logger.debug("**********REQUEST PARAM CREATE COTIZACION SET UP 2********************************************* "); 
			logger.debug("id: "+ id);
			logger.debug("dig: "+ dig);
			logger.debug("ver_capture: "+ ver_capture);
			logger.debug("tab: "+ tab);
			logger.debug("datos_entrega: "+ datos_entrega);
			logger.debug("formato_entrega: "+ formato_entrega);
			logger.debug("tabla_pre: "+ tabla_pre);
			logger.debug("analisis: "+ analisis);
			logger.debug("diseno_muestra: "+ diseno_muestra);
			logger.debug("entrega: "+ entrega);
			logger.debug("entre_1: "+ entre_1);
			logger.debug("entre_2: "+ entre_2);
			logger.debug("entre_3: "+ entre_3);
			logger.debug("entre_4: "+ entre_4);
			logger.debug("entre_5: "+ entre_5);
			logger.debug("entre_6: "+ entre_6);
			logger.debug("estado_setup: "+ estado_setup);
		  logger.debug("**********END REQUEST PARAM CREATE COTIZACION********************************************* ");
		 
		 
		 
		 
		 ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
		 ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
		 
		 int permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 16);
		 String fechaNow = format3.format(new Date());
		 
		 if(permisoModulo == 1 || permisoModulo == 2){
			 ObjEstudioDigitacion digi = new ObjEstudioDigitacion();
			 digi.setId_operacion(id);
			 digi.setVerbatim_capture(ver_capture);
			 
			 ObjEstudioTabulacion tabu = new ObjEstudioTabulacion();
			 tabu.setId_operacion(id);
			 tabu.setEsp_datos_entr_tab(datos_entrega);
			 tabu.setFormat_file_tab(formato_entrega);
			 tabu.setTabla_prem_tab(tabla_pre);
			 
			 ObjEstudioAnalisis ani = new ObjEstudioAnalisis();
			 ani.setId_operacion(id);
			 ani.setDis_muestra_ana(diseno_muestra);
			 
			 ObjEstudioDetalle dets = new ObjEstudioDetalle();
			 dets.setId_operacion(id);
			 dets.setEntre_1(entre_1);
			 dets.setEntre_2(entre_2);
			 dets.setEntre_3(entre_3);
			 dets.setEntre_4(entre_4);
			 dets.setEntre_5(entre_5);
			 dets.setEntre_6(entre_6);
			 
			 
			 
			 
			 if(estado_setup == 1){
				 ests.setDigitacionOperacion(digi);
				 ests.setTabulacionOperacion(tabu);
				 ests.setAnalisisOperacion(ani);
			 }else{
				 ests.updateDigitacionOperacion(digi);
				 ests.updateTabulacionOperacion(tabu);
				 ests.updateAnalisisOperacion(ani);
			 }
			  
			 ests.updateDetalleOperacion(dets);
			 
			 
			 logger.info("Cotizacion Fase 3 Actualiza Operacion Id: "+ id);
			 
			 
			 
			 trazas.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, id, 16, 0, 0, "UPDATE NEW COTIZACION FASE 3", "Se Actualiza estudio datos Set Up 2 ",7));
			 
			 result.setId_operacion(id);
			 result.setText("Se Actualiza estudio datos Set Up 2: <strong>"+ id +"</strong> ");
			 
			 
		 }else{
			 result.setId_cotizacion(0);
			 result.setCodigo_cotizacion("0-0"); 
			 logger.info("Sin Permiso de crear Cotizacion Fase 3 user: "+ user.getId_user() + " -- Fecha: "+ fechaNow);
			 trazas.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, 0, 16, 0, 0, "UPDATE NEW COTIZACION 3", "Se Bloquea acceso a creacion ya que no tiene privilegios de acceso! ",7));
			 
			 result.setText("Permiso de Acceso");
		 }
		 
		 return result;
		 
	}
	@RequestMapping(value = "/setCotizacion4", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjResultCreaCotOp setCotizacion4(@RequestParam("id") int id,
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
											  @RequestParam("estado_setup") int estado_setup
											 )
	{
		ObjResultCreaCotOp result =  new ObjResultCreaCotOp();
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessCotizacion cots = (AccessCotizacion) context.getBean("AccessCotizacion");
		 LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		 AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		 AccessGeneralTools generals = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		 AccessEstudio ests = (AccessEstudio) context.getBean("AccessEstudio");
		 AccessTraza trazas = (AccessTraza) context.getBean("AccessTraza");
		 SecurityContext securityContext = SecurityContextHolder.getContext();
		 Authentication authentication = securityContext.getAuthentication();
		
		 logger.debug("**********REQUEST PARAM CREATE COTIZACION BUSSNESS CASE********************************************* "); 
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
			logger.debug("estado_setup: "+ estado_setup);
		 logger.debug("**********END REQUEST PARAM BUSSNESS CASE********************************************* ");
		 
		 
		 
		 
		 ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
		 ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
		 
		 int permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 16);
		 String fechaNow = format3.format(new Date());
		 
		 if(permisoModulo == 1 || permisoModulo == 2){
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
			 
			 if(estado_setup == 1){
				 ests.setBussnessCaseOperacion(bc);
				
			 }else{
				 ests.updateBussnessCaseOperacion(bc);
				 
			 }
			  
			 
			 
			 
			 logger.info("Cotizacion Fase BUSSNESS CASE Actualiza Operacion Id: "+ id);
			 
			 
			 
			 trazas.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, id, 16, 0, 0, "UPDATE NEW COTIZACION BUSSNESS CASE", "Se Actualiza estudio datos BUSSNESS CASE ",7));
			 result.setId_operacion(id);
			 result.setText("Se Actualiza estudio  BUSSNESS CASE: <strong>"+ id +"</strong> ");
		 
			 
		 }else{
			 result.setId_cotizacion(0);
			 result.setCodigo_cotizacion("0-0"); 
			 logger.info("Sin Permiso de crear Cotizacion Fase 3 user: "+ user.getId_user() + " -- Fecha: "+ fechaNow);
			 trazas.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, 0, 16, 0, 0, "UPDATE NEW COTIZACION 3", "Se Bloquea acceso a creacion ya que no tiene privilegios de acceso! ",7));
			 
			 result.setText("Permiso de Acceso");
		 }
		 
		 return result;
		 
	}
	@RequestMapping(value = "/setCotizacion5", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjResultCreaCotOp setCotizacion5(@RequestParam("id") int id,
											  @RequestParam("pventa") int pventa,
											  @RequestParam("num_entre") int num_entre,
											  @RequestParam("tipo_entre") String tipo_entre,
											  @RequestParam("dur_entre") int dur_entre,
											  @RequestParam("por_inc") int por_inc,
											  @RequestParam("rebate") int rebate,
											  @RequestParam("modo") String modo,
											  @RequestParam("submodo") String submodo
											 )
	{
		ObjResultCreaCotOp result =  new ObjResultCreaCotOp();
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessCotizacion cots = (AccessCotizacion) context.getBean("AccessCotizacion");
		 LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		 AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		 AccessGeneralTools generals = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		 AccessEstudio ests = (AccessEstudio) context.getBean("AccessEstudio");
		 AccessTraza trazas = (AccessTraza) context.getBean("AccessTraza");
		 SecurityContext securityContext = SecurityContextHolder.getContext();
		 Authentication authentication = securityContext.getAuthentication();
		
		 logger.debug("**********REQUEST PARAM CREATE COTIZACION BUSSNESS CASE********************************************* "); 
			logger.debug("id: "+ id);
			logger.debug("pventa: "+ pventa);
			logger.debug("num_entre"+ num_entre);
			logger.debug("tipo_entre: "+ tipo_entre);
			logger.debug("dur_entre: "+ dur_entre);
			logger.debug("por_inc: "+ por_inc);
			logger.debug("rebate: "+ rebate);
			logger.debug("modo: "+ modo);
			logger.debug("submodo: "+ submodo);
		logger.debug("**********END REQUEST PARAM BUSSNESS CASE********************************************* ");
		 
		 
		 
		 
		 ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
		 ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
		 
		 int permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 16);
		 String fechaNow = format3.format(new Date());
		 
		 if(permisoModulo == 1 || permisoModulo == 2){
			 ObjEstudioDetalle dt = new ObjEstudioDetalle();
			 
			 dt.setId_operacion(id);
			 dt.setPrecio_venta(pventa);
			 dt.setNum_entrevistas_op(num_entre);
			 dt.setCot_por_inc(por_inc);
			 dt.setPor_rebate(rebate);
			 ests.updateDetalleOperacion(dt); 
			 
			 
			 ObjEstudio es = new ObjEstudio();
			 
			 es.setId_operacion(id);
			 es.setFmod_proyectom(fechaNow);
			 es.setSmod_proyectom(user.getId_user());
			 es.setCola_operacion(1); //Pte. Desarrollo Materiales y  Organizacion   -- Por mientras que se define cotizador y aprobacion
			 es.setEstado_medicion(10);
			 ests.updateColaEstadoOperacion(es);
			 
			 //ests.updateActivateOperacion(es); //Activacion de Estudio 
			 ests.updateLastUpdateOperacion(es);
			 
			 logger.info("Cotizacion Fase Cotizador FINAL Actualiza Operacion Id: "+ id + " -- Estado: Estudio Aprobado" );
			 
			 
			 
			 trazas.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, id, 16, 0, 0, "UPDATE NEW COTIZACION BUSSNESS CASE", "Se Actualiza estudio datos BUSSNESS CASE ",7));
			 
			 result.setText("Se Actualiza estudio  Cotizador FINAL: <strong>"+ id +"</strong> ");
		 
			 
		 }else{
			 result.setId_cotizacion(0);
			 result.setCodigo_cotizacion("0-0"); 
			 logger.info("Sin Permiso de crear Cotizacion Fase 3 user: "+ user.getId_user() + " -- Fecha: "+ fechaNow);
			 trazas.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, 0, 16, 0, 0, "UPDATE NEW COTIZACION Cotizador", "Se Bloquea acceso a creacion ya que no tiene privilegios de acceso! ",7));
			 
			 result.setText("Permiso de Acceso");
		 }
		 
		 return result;
		 
	}
	
	@RequestMapping(value = "/getFullDetalleOperacion", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjEstudio getFullDetalleOperacion(@RequestParam("id") int id,
											  @RequestParam("tipo") int tipo
											 )
	{
		ObjEstudio result =  new ObjEstudio();
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessCotizacion cots = (AccessCotizacion) context.getBean("AccessCotizacion");
		 LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		 AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		 AccessGeneralTools generals = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		 AccessEstudio ests = (AccessEstudio) context.getBean("AccessEstudio");
		 AccessTraza trazas = (AccessTraza) context.getBean("AccessTraza");
		 SecurityContext securityContext = SecurityContextHolder.getContext();
		 Authentication authentication = securityContext.getAuthentication();
		 AccessEstudio estudio = (AccessEstudio) context.getBean("AccessEstudio");
		 
		 logger.debug("**********REQUEST PARAM getFullDetalleOperacion********************************************* "); 
			logger.debug("id: "+ id);
			logger.debug("tipo: "+ tipo);
			
		logger.debug("**********END REQUEST PARAM getFullDetalleOperacion********************************************* ");
		 
		 String str_tipo = "";
		 if(tipo == 1){
			 str_tipo = "Cotización";
		 }else if(tipo == 2){
			 str_tipo = "Estudio";
		 }
		 
		 
		 
		 ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
		 ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
		 
		 int permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 16);
		 String fechaNow = format3.format(new Date());
		 
		 if(permisoModulo == 1 || permisoModulo == 2 || permisoModulo == 3){
			 
			 result = estudio.getFullEstudioReunionByUid(id);
			 
			 logger.info("Se busca Información de "+str_tipo+": "+ id + " -- Por usuario id: "+user.getId_user() );
			 
			 trazas.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, id, 16, 0, 0, "SEARCH "+str_tipo+" ", "Se despliega Información de Operación ",0));
			 
			 result.setText("Se Encuentra "+str_tipo+" Id Operacion: <strong>"+ id +"</strong> ");
		 
			 
		 }else{
			 result.setId_cotizacion(0);
			 result.setCodigo_cotizacion("0-0"); 
			 logger.info("Sin Permiso de Buscar "+str_tipo+" para usuario user: "+ user.getId_user() + " -- Fecha: "+ fechaNow);
			 trazas.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, 0, 16, 0, 0, "SEARCH "+str_tipo+" ", "Se Bloquea acceso a Busqueda ya que no tiene privilegios de acceso! ",0));
			 
			 result.setText("Permiso de Acceso");
		 }
		 
		 return result;
		 
	}
	@RequestMapping(value = "/getDetalleOperacion", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjEstudio getDetalleOperacion(@RequestParam("id") int id,
										  @RequestParam("tipo") int tipo
										  )
	{
		ObjEstudio result =  new ObjEstudio();
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessCotizacion cots = (AccessCotizacion) context.getBean("AccessCotizacion");
		 LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		 AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		 AccessGeneralTools generals = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		 AccessEstudio ests = (AccessEstudio) context.getBean("AccessEstudio");
		 AccessTraza trazas = (AccessTraza) context.getBean("AccessTraza");
		 SecurityContext securityContext = SecurityContextHolder.getContext();
		 Authentication authentication = securityContext.getAuthentication();
		 AccessEstudio estudio = (AccessEstudio) context.getBean("AccessEstudio");
		 
		 logger.debug("**********REQUEST PARAM getFullDetalleOperacion********************************************* "); 
			logger.debug("id: "+ id);
			logger.debug("tipo: "+ tipo);
			
		logger.debug("**********END REQUEST PARAM getFullDetalleOperacion********************************************* ");
		 
		 String str_tipo = "";
		 if(tipo == 1){
			 str_tipo = "Cotización";
		 }else if(tipo == 2){
			 str_tipo = "Estudio";
		 }
		 
		 
		 
		 ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
		 ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
		 
		 int permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 5);
		 String fechaNow = format3.format(new Date());
		 
		 if(permisoModulo == 1 || permisoModulo == 2 || permisoModulo == 3){
			 
			 result = estudio.getEstudioById(id);
			 
			 logger.info("Se busca Información de "+str_tipo+": "+ id + " -- Por usuario id: "+user.getId_user() );
			 
			 
			 
			 trazas.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, id, 16, 0, 0, "SEARCH "+str_tipo+" ", "Se despliega Información de Operación ",0));
			 
			 result.setText("Se Encuentra "+str_tipo+" Id Operacion: <strong>"+ id +"</strong> ");
		 
			 
		 }else{
			 result.setId_cotizacion(0);
			 result.setCodigo_cotizacion("0-0"); 
			 logger.info("Sin Permiso de Buscar "+str_tipo+" para usuario user: "+ user.getId_user() + " -- Fecha: "+ fechaNow);
			 trazas.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, 0, 16, 0, 0, "SEARCH "+str_tipo+" ", "Se Bloquea acceso a Busqueda ya que no tiene privilegios de acceso! ",0));
			 
			 result.setText("Permiso de Acceso");
		 }
		 
		 return result;
		 
	}
	@RequestMapping(value = "/setActiveCotizacion", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjEstudio setActiveCotizacion(@RequestParam("id") int id,
										  @RequestParam("sam") String sam,
										  @RequestParam("manager") String manager,
										  @RequestParam("nombre") String nombre,
										  @RequestParam("industria") String industria,
										  @RequestParam("tipo_estudio") String tipo_estudio,
										  @RequestParam("tipo_entrevista") String tipo_entrevista,
										  @RequestParam("muestra") int muestra,
										  @RequestParam("resp1") int resp1,
										  @RequestParam("resp2") int resp2,
										  @RequestParam("resp3") int resp3,
										  @RequestParam("setup1") String setup1,
										  @RequestParam("setup2") String setup2,
										  @RequestParam("setup3") String setup3,
										  @RequestParam("setup4") String setup4,
										  @RequestParam("setup5") String setup5,
										  @RequestParam("setup6") String setup6,
										  @RequestParam("setup7") String setup7,
										  @RequestParam("setup8") String setup8,
										  @RequestParam("setup9") String setup9,
										  @RequestParam("setup10") String setup10,
										  @RequestParam("setup11") String setup11,
										  @RequestParam("por_inc") int por_inc,
										  @RequestParam("por_reb") int por_reb,
										  @RequestParam("libro_codigo") String libro_codigo,
										  @RequestParam("verbatim") String verbatim,
										  @RequestParam("pre_proceso") String pre_proceso,
										  @RequestParam("ab_cod") int ab_cod,
										  @RequestParam("otro_cod") int otro_cod,
										  @RequestParam("tmo") int tmo,
										  @RequestParam("fechac_ini_campo") String fechac_ini_campo,
										  @RequestParam("fechac_fin_campo") String fechac_fin_campo,
										  @RequestParam("fechac_ini_bbdd") String fechac_ini_bbdd,
										  @RequestParam("fechac_fin_bbdd") String fechac_fin_bbdd,
										  @RequestParam("fechac_entrega") String fechac_entrega,
										  
										  
										  @RequestParam("canal_venta") int canal_venta,
										  @RequestParam(value="cod_sam", required=false) String cod_sam,
										  @RequestParam(value="tipo_sam", required=false) String tipo_sam,
										  @RequestParam(value="id_sap", required=false) String id_sap,
										  @RequestParam(value="cod_manager", required=false) String cod_manager,
										  @RequestParam(value="cod_crm", required=false) String cod_crm,
										  @RequestParam("id_cliente") int id_cliente,
										  @RequestParam(value="area_medicion", required=false) int area_medicion,
										  @RequestParam("sector_medicion") int sector_medicion,
										  @RequestParam("industria_medicion") int industria_medicion,
										  @RequestParam("id_tipo_entrevista") String id_tipo_entrevista,
										  @RequestParam("nombre_operacion") String nombre_operacion,
										  @RequestParam("id_geografia") String id_geografia,
										  @RequestParam("cant_paises") int cant_paises,
										  @RequestParam("id_clie_facturar") int id_clie_facturar,
										  @RequestParam("producto") String producto,
										  @RequestParam("booking_legal_entity") String booking_legal_entity,
										  @RequestParam(value="centro_costo_op", required=false) String centro_costo_op,
										  @RequestParam(value="por_ejec_estudio", required=false) int por_ejec_estudio,
										  @RequestParam(value="digital_op", required=false) String digital_op,
										  @RequestParam(value="moneda_op", required=false) String moneda_op,
										  @RequestParam("date_prob_in_est_op") String date_prob_in_est_op,
										  @RequestParam("date_prob_entre_est_op") String date_prob_entre_est_op,
										  @RequestParam("ddate_pres_of_equipo_op") String ddate_pres_of_equipo_op,
										  @RequestParam("ddate_pres_gps_op") String ddate_pres_gps_op,
										  @RequestParam("ddate_pres_clie_op") String ddate_pres_clie_op,
										  @RequestParam(value="obj_obs_op", required=false) String obj_obs_op,
										  @RequestParam(value="desc_op", required=false) String desc_op
											 )
	{
		ObjEstudio result =  new ObjEstudio();
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessCotizacion cots = (AccessCotizacion) context.getBean("AccessCotizacion");
		 LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		 AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		 AccessGeneralTools generals = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		 AccessEstudio ests = (AccessEstudio) context.getBean("AccessEstudio");
		 AccessTraza trazas = (AccessTraza) context.getBean("AccessTraza");
		 SecurityContext securityContext = SecurityContextHolder.getContext();
		 Authentication authentication = securityContext.getAuthentication();
		 AccessEstudio estudio = (AccessEstudio) context.getBean("AccessEstudio");
		 
		 logger.debug("**********REQUEST PARAM getFullDetalleOperacion********************************************* "); 
			logger.debug("id: "+ id);
			
			
		logger.debug("**********END REQUEST PARAM getFullDetalleOperacion********************************************* ");
		 
		 
		 
		 
		 
		 ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
		 ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
		 
		 int permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 16);
		 String fechaNow = format3.format(new Date());
		 
		 if(permisoModulo == 1 || permisoModulo == 2 || permisoModulo == 3){
			 
			 result = estudio.getFullEstudioByUid(id);
			 
			 //logger.info("Se busca Información de "+str_tipo+": "+ id + " -- Por usuario id: "+user.getId_user() );
			 
			 
			 
			 //trazas.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, id, 16, 0, 0, "SEARCH "+str_tipo+" ", "Se despliega Información de Operación ",0));
			 
			 //result.setText("Se Encuentra "+str_tipo+" Id Operacion: <strong>"+ id +"</strong> ");
		 
			 
		 }else{
			 result.setId_cotizacion(0);
			 result.setCodigo_cotizacion("0-0"); 
			 //logger.info("Sin Permiso de Buscar "+str_tipo+" para usuario user: "+ user.getId_user() + " -- Fecha: "+ fechaNow);
			 //trazas.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, 0, 16, 0, 0, "SEARCH "+str_tipo+" ", "Se Bloquea acceso a Busqueda ya que no tiene privilegios de acceso! ",0));
			 
			 result.setText("Permiso de Acceso");
		 }
		 
		 return result;
		 
	}
}
