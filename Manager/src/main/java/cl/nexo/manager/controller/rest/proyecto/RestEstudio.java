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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.nexo.manager.access.agenda.AgendaAccess;
import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.access.login.AccessPerfil;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.proyecto.AccessCotizacion;
import cl.nexo.manager.access.proyecto.AccessEstudio;
import cl.nexo.manager.access.traza.AccessTraza;
import cl.nexo.manager.obj.agenda.ObjPersonaAgenda;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.login.ObjPerfilLogin;
import cl.nexo.manager.obj.proyecto.ObjDataListEstudio;
import cl.nexo.manager.obj.proyecto.ObjEstudio;
import cl.nexo.manager.obj.proyecto.ObjEstudioDetalle;
import cl.nexo.manager.obj.proyecto.ObjEstudioFiltros;
import cl.nexo.manager.obj.proyecto.ObjResultCreaCotOp;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;
import cl.nexo.manager.obj.tools.ObjGeneralResultInt;
import cl.nexo.manager.obj.traza.ObjTrazaManager;



@RestController
@RequestMapping("/RestEstudio")
public class RestEstudio {
	private static final Logger logger = Logger.getLogger(RestEstudio.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	

	
	@RequestMapping(value = "/getEstudioById", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjEstudio getEstudioById(@RequestParam("id") int id)
	{
		 ObjEstudio estudio = new ObjEstudio();
		 
		 SecurityContext securityContext = SecurityContextHolder.getContext();
	     Authentication authentication = securityContext.getAuthentication();
	     String user = authentication.getName();
		 
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 AccessEstudio estudios = (AccessEstudio) context.getBean("AccessEstudio");
		 
		 estudio = estudios.getEstudioById(id);
		 
		 return estudio;
		 
	}
	@RequestMapping(value = "/getListEstudio", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjDataListEstudio getListEstudio(@RequestParam("filtro") int filtro,
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
		 fil.setActiva_operacion(1);
		 
		 list = lists.getListEstudioByFilter(fil);
		 
		 est.setData(list);
		 
		 return est;
		 
	}
	@RequestMapping(value = "/getListEstudioAsignado", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjDataListEstudio getListEstudioAsignado(@RequestParam("id") int id,
											         @RequestParam("lang") String lang)
	{
		ObjDataListEstudio est = new ObjDataListEstudio();
		ArrayList<ObjEstudio> list =  new ArrayList<ObjEstudio>();
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessEstudio lists = (AccessEstudio) context.getBean("AccessEstudio");
		 logger.debug("**********REQUEST PARAM FILTRO GET LIST ASIGN********************************************* "); 
		 	logger.debug("id: "+ id);
		 	logger.debug("lang: "+ lang);
		 	logger.debug("url: http://localhost:8080/Manager/RestEstudio/getListEstudioAsignado?id="+id+"&lang="+lang+ " ");
		 logger.debug("**********END REQUEST PARAM FILTRO GET LIST ASIGN********************************************* ");
		
		 
		 list = lists.getListEstudioByUser(id, lang);
		 
		 est.setData(list);
		 
		 return est;
		 
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
			 
			 result = estudio.getFullEstudioByUid(id);
			 
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
}
