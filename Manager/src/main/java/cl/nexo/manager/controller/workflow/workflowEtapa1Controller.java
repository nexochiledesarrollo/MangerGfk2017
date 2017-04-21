package cl.nexo.manager.controller.workflow;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.access.login.AccessPerfil;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.menu.AccessMenu;
import cl.nexo.manager.access.proyecto.AccessAsignUsers;
import cl.nexo.manager.access.traza.AccessTraza;
import cl.nexo.manager.obj.asignacion.usuario.ObjAsignUser;
import cl.nexo.manager.obj.asignacion.usuario.ObjAsignUserDetalle;
import cl.nexo.manager.obj.asignacion.usuario.ObjFiltroAsignUser;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.login.ObjPerfilLogin;
import cl.nexo.manager.obj.menu.ObjMenuManager;
import cl.nexo.manager.obj.tools.ObjConfigTools;
import cl.nexo.manager.obj.traza.ObjTrazaManager;



@Controller
@RequestMapping("/workflowEtapa1")
public class workflowEtapa1Controller {
	private static final Logger logger = Logger.getLogger(workflowEtapa1Controller.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	@RequestMapping(value = { "/ActivarEstudio" }, method = RequestMethod.GET)
	public ModelAndView  ActivarEstudio() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessMenu menus = (AccessMenu) context.getBean("AccessMenu");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		
		int fechaAhora = Integer.parseInt(format1.format(new Date()));
		String horaAhora = format2.format(new Date());
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
	    int permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 15);
	    ObjMenuManager menu = menus.getMenuSistema(1, user.getId_cliente(), perfil.getId_perfil());
	    
	    ObjConfigTools tol = new ObjConfigTools();
	    
	    tol.setRefresco_table(Integer.parseInt(tools.getValorConfigById(36)));
	    tol.setRefersco_dashboard(Integer.parseInt(tools.getValorConfigById(38)));
	    tol.setTipo_menu(Integer.parseInt(tools.getValorConfigById(37)));
	    tol.setLang(user.getLang_user());
	    
		ModelAndView model = new ModelAndView();
		
		if(user.getOtp_user() == 1 && permisoModulo != 0){
			model.addObject("login", user);
		    model.addObject("perfil", perfil);
		    model.addObject("menu", menu);
		    model.addObject("unidad_access", "5");
		    model.addObject("modulo_access", "15");
		    model.addObject("permisoModulo", Integer.toString(permisoModulo));
		    model.addObject("lang",user.getLang_user());
		    model.addObject("tol",tol);
			
			model.setViewName("/modulos/work1/activar-estudio/modulo");
		}else{
	    	
	    	model.setViewName("login");
	    }
		return model;
	
	}
	@RequestMapping(value = { "/ActivarEstudioDetalle" }, method = RequestMethod.GET)
	public ModelAndView  detalleProy(@RequestParam("id") int id,
									@RequestParam("nombre") String nombre,
									@RequestParam("tipo") int tipo) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessMenu menus = (AccessMenu) context.getBean("AccessMenu");
		AccessTraza traza = (AccessTraza) context.getBean("AccessTraza");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		String urlRestServiceDelivery = tools.getValorConfigById(15);
		
		int fechaAhora = Integer.parseInt(format1.format(new Date()));
		String horaAhora = format2.format(new Date());
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
	    int permisoModulo = 0;
	    ObjMenuManager menu = menus.getMenuSistema(1, user.getId_cliente(), perfil.getId_perfil());
	    String fechaNow = format3.format(new Date());
	    
	    ObjConfigTools tol = new ObjConfigTools();
	    
	    tol.setRefresco_table(Integer.parseInt(tools.getValorConfigById(36)));
	    tol.setRefersco_dashboard(Integer.parseInt(tools.getValorConfigById(38)));
	    tol.setTipo_menu(Integer.parseInt(tools.getValorConfigById(37)));
		
	    String nombre_aux = "";
	    
	    if(nombre != null){
	    	try {
				nombre_aux = URLDecoder.decode(nombre, "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	    if(tipo == 1){
	    	permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 16);
	    }
	    if(tipo == 2){
	    	permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 3);
	    }
	    
	    ModelAndView model = new ModelAndView();
		
		if(user.getOtp_user() == 1){
			if(permisoModulo != 0){
				
				traza.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, id, 3, 0, 0, "CONSULTA ACTIVACION ESTUDIO", "USUARIO CONSULTA ESTUDIO "+ nombre_aux,8));
				
				
				model.addObject("login", user);
			    model.addObject("perfil", perfil);
			    model.addObject("menu", menu);
			    model.addObject("unidad_access", "4");
			    if(tipo == 1){
			    	model.addObject("modulo_access", "16");
			    }
			    if(tipo == 2){
			    	model.addObject("modulo_access", "3");
			    }
			    model.addObject("urlRestServiceDelivery", urlRestServiceDelivery);
			    model.addObject("permisoModulo", Integer.toString(permisoModulo));
			    model.addObject("id_operacion", String.valueOf(id));
			    model.addObject("tipo", String.valueOf(tipo));
			    model.addObject("lang",user.getLang_user());
			    model.addObject("tol",tol);
				model.setViewName("/modulos/proyecto/activa");
				
				
				
			}else{
				model.setViewName("error-permiso");
				
			}
		}else{
	    	
	    	model.setViewName("login");
	    }
		return model;
	
	}
	@RequestMapping(value = { "/UploadCuestionario" }, method = RequestMethod.GET)
	public ModelAndView  UploadCuestionario() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessMenu menus = (AccessMenu) context.getBean("AccessMenu");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		
		int fechaAhora = Integer.parseInt(format1.format(new Date()));
		String horaAhora = format2.format(new Date());
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
	    int permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 5);
	    ObjMenuManager menu = menus.getMenuSistema(1, user.getId_cliente(), perfil.getId_perfil());
	    ObjConfigTools tol = new ObjConfigTools();
	    
	    tol.setRefresco_table(Integer.parseInt(tools.getValorConfigById(36)));
	    tol.setRefersco_dashboard(Integer.parseInt(tools.getValorConfigById(38)));
	    tol.setTipo_menu(Integer.parseInt(tools.getValorConfigById(37)));
	    tol.setLang(user.getLang_user());
		
		ModelAndView model = new ModelAndView();
		
		if(user.getOtp_user() == 1 && permisoModulo != 0){
			model.addObject("login", user);
		    model.addObject("perfil", perfil);
		    model.addObject("menu", menu);
		    model.addObject("unidad_access", "5");
		    model.addObject("modulo_access", "5");
		    model.addObject("permisoModulo", Integer.toString(permisoModulo));
		    model.addObject("lang",user.getLang_user());
		    model.addObject("tol",tol);
			
			model.setViewName("/modulos/work1/upload-cuestionario/modulo");
		}else{
	    	
	    	model.setViewName("login");
	    }
		return model;
	
	}
	@RequestMapping(value = { "/detalleUploadCuestionario" }, method = RequestMethod.GET)
	public ModelAndView  detalleUploadCuestionario(@RequestParam("id") int id,
									@RequestParam("nombre") String nombre
									) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessMenu menus = (AccessMenu) context.getBean("AccessMenu");
		AccessTraza traza = (AccessTraza) context.getBean("AccessTraza");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		String urlRestServiceDelivery = tools.getValorConfigById(15);
		
		int fechaAhora = Integer.parseInt(format1.format(new Date()));
		String horaAhora = format2.format(new Date());
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
	    int permisoModulo = 0;
	    ObjMenuManager menu = menus.getMenuSistema(1, user.getId_cliente(), perfil.getId_perfil());
	    String fechaNow = format3.format(new Date());
	    
	    ObjConfigTools tol = new ObjConfigTools();
	    
	    tol.setRefresco_table(Integer.parseInt(tools.getValorConfigById(36)));
	    tol.setRefersco_dashboard(Integer.parseInt(tools.getValorConfigById(38)));
	    tol.setTipo_menu(Integer.parseInt(tools.getValorConfigById(37)));
		
	    String nombre_aux = "";
	    
	    if(nombre != null){
	    	try {
				nombre_aux = URLDecoder.decode(nombre, "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	   
	    permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 5);
	    
	    
	    ModelAndView model = new ModelAndView();
		
		if(user.getOtp_user() == 1){
			if(permisoModulo != 0){
				
				traza.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, id, 3, 0, 0, "CONSULTA ESTUDIO UPLOAD CUESTIONARIO", "USUARIO CONSULTA OPERACION "+ nombre_aux,8));
				
				
				model.addObject("login", user);
			    model.addObject("perfil", perfil);
			    model.addObject("menu", menu);
			    model.addObject("unidad_access", "5");
			    
			    model.addObject("modulo_access", "5");
			    
			    model.addObject("urlRestServiceDelivery", urlRestServiceDelivery);
			    model.addObject("permisoModulo", Integer.toString(permisoModulo));
			    model.addObject("id_operacion", String.valueOf(id));
			    model.addObject("lang",user.getLang_user());
			    model.addObject("tol",tol);
				model.setViewName("/modulos/work1/upload-cuestionario/detalle");
				
				
				
			}else{
				model.setViewName("error-permiso");
				
			}
		}else{
	    	
	    	model.setViewName("login");
	    }
		return model;
	
	}
	@RequestMapping(value = { "/Instructivo" }, method = RequestMethod.GET)
	public ModelAndView  Instructivo() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessMenu menus = (AccessMenu) context.getBean("AccessMenu");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		
		int fechaAhora = Integer.parseInt(format1.format(new Date()));
		String horaAhora = format2.format(new Date());
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
	    int permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 7);
	    ObjMenuManager menu = menus.getMenuSistema(1, user.getId_cliente(), perfil.getId_perfil());
	    ObjConfigTools tol = new ObjConfigTools();
	    
	    tol.setRefresco_table(Integer.parseInt(tools.getValorConfigById(36)));
	    tol.setRefersco_dashboard(Integer.parseInt(tools.getValorConfigById(38)));
	    tol.setTipo_menu(Integer.parseInt(tools.getValorConfigById(37)));
	    tol.setLang(user.getLang_user());
		
		ModelAndView model = new ModelAndView();
		
		if(user.getOtp_user() == 1 && permisoModulo != 0){
			model.addObject("login", user);
		    model.addObject("perfil", perfil);
		    model.addObject("menu", menu);
		    model.addObject("unidad_access", "5");
		    model.addObject("modulo_access", "7");
		    model.addObject("permisoModulo", Integer.toString(permisoModulo));
		    model.addObject("lang",user.getLang_user());
		    model.addObject("tol",tol);
			
			model.setViewName("/modulos/work1/instructivo/modulo");
		}else{
	    	
	    	model.setViewName("login");
	    }
		return model;
	
	}
	@RequestMapping(value = { "/detalleInstructivo" }, method = RequestMethod.GET)
	public ModelAndView  detalleInstructivo(@RequestParam("id") int id,
									@RequestParam("nombre") String nombre
									) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessMenu menus = (AccessMenu) context.getBean("AccessMenu");
		AccessTraza traza = (AccessTraza) context.getBean("AccessTraza");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		String urlRestServiceDelivery = tools.getValorConfigById(15);
		
		int fechaAhora = Integer.parseInt(format1.format(new Date()));
		String horaAhora = format2.format(new Date());
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
	    int permisoModulo = 0;
	    ObjMenuManager menu = menus.getMenuSistema(1, user.getId_cliente(), perfil.getId_perfil());
	    String fechaNow = format3.format(new Date());
	    
	    ObjConfigTools tol = new ObjConfigTools();
	    
	    tol.setRefresco_table(Integer.parseInt(tools.getValorConfigById(36)));
	    tol.setRefersco_dashboard(Integer.parseInt(tools.getValorConfigById(38)));
	    tol.setTipo_menu(Integer.parseInt(tools.getValorConfigById(37)));
		
	    String nombre_aux = "";
	    
	    if(nombre != null){
	    	try {
				nombre_aux = URLDecoder.decode(nombre, "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	   
	    permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 7);
	    
	    
	    ModelAndView model = new ModelAndView();
		
		if(user.getOtp_user() == 1){
			if(permisoModulo != 0){
				
				traza.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, id, 3, 0, 0, "CONSULTA ESTUDIO CREACION INSTRUCTIVO", "USUARIO CONSULTA OPERACION "+ nombre_aux,8));
				
				
				model.addObject("login", user);
			    model.addObject("perfil", perfil);
			    model.addObject("menu", menu);
			    model.addObject("unidad_access", "5");
			    
			    model.addObject("modulo_access", "7");
			    
			    model.addObject("urlRestServiceDelivery", urlRestServiceDelivery);
			    model.addObject("permisoModulo", Integer.toString(permisoModulo));
			    model.addObject("id_operacion", String.valueOf(id));
			    model.addObject("lang",user.getLang_user());
			    model.addObject("tol",tol);
				model.setViewName("/modulos/work1/instructivo/detalle");
				
				
				
			}else{
				model.setViewName("error-permiso");
				
			}
		}else{
	    	
	    	model.setViewName("login");
	    }
		return model;
	
	}
	
	@RequestMapping(value = { "/RevisionFondos" }, method = RequestMethod.GET)
	public ModelAndView  RevisionFondos() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessMenu menus = (AccessMenu) context.getBean("AccessMenu");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		
		int fechaAhora = Integer.parseInt(format1.format(new Date()));
		String horaAhora = format2.format(new Date());
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
	    int permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 8);
	    ObjMenuManager menu = menus.getMenuSistema(1, user.getId_cliente(), perfil.getId_perfil());
	    ObjConfigTools tol = new ObjConfigTools();
	    
	    tol.setRefresco_table(Integer.parseInt(tools.getValorConfigById(36)));
	    tol.setRefersco_dashboard(Integer.parseInt(tools.getValorConfigById(38)));
	    tol.setTipo_menu(Integer.parseInt(tools.getValorConfigById(37)));
	    tol.setLang(user.getLang_user());
		
		ModelAndView model = new ModelAndView();
		
		if(user.getOtp_user() == 1 && permisoModulo != 0){
			model.addObject("login", user);
		    model.addObject("perfil", perfil);
		    model.addObject("menu", menu);
		    model.addObject("unidad_access", "5");
		    model.addObject("modulo_access", "8");
		    model.addObject("permisoModulo", Integer.toString(permisoModulo));
		    model.addObject("lang",user.getLang_user());
		    model.addObject("tol",tol);
			
			model.setViewName("/modulos/work1/rev1/modulo");
		}else{
	    	
	    	model.setViewName("login");
	    }
		return model;
	
	}
	@RequestMapping(value = { "/DetalleRevisionFondos" }, method = RequestMethod.GET)
	public ModelAndView  DetalleRevisionFondos(@RequestParam("id") int id,
									@RequestParam("nombre") String nombre,
									@RequestParam("tipo") int tipo
									) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessMenu menus = (AccessMenu) context.getBean("AccessMenu");
		AccessTraza traza = (AccessTraza) context.getBean("AccessTraza");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		String urlRestServiceDelivery = tools.getValorConfigById(15);
		
		int fechaAhora = Integer.parseInt(format1.format(new Date()));
		String horaAhora = format2.format(new Date());
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
	    int permisoModulo = 0;
	    ObjMenuManager menu = menus.getMenuSistema(1, user.getId_cliente(), perfil.getId_perfil());
	    String fechaNow = format3.format(new Date());
	    
	    ObjConfigTools tol = new ObjConfigTools();
	    
	    tol.setRefresco_table(Integer.parseInt(tools.getValorConfigById(36)));
	    tol.setRefersco_dashboard(Integer.parseInt(tools.getValorConfigById(38)));
	    tol.setTipo_menu(Integer.parseInt(tools.getValorConfigById(37)));
		
	    String nombre_aux = "";
	    
	    if(nombre != null){
	    	try {
				nombre_aux = URLDecoder.decode(nombre, "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	   
	    permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 8);
	    
	    
	    
	    ModelAndView model = new ModelAndView();
		
		if(user.getOtp_user() == 1){
			if(permisoModulo != 0){
				
				traza.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, id, 3, 0, 0, "CONSULTA ESTUDIO REVISION FONDOS", "USUARIO CONSULTA OPERACION "+ nombre_aux,8));
				
				
				model.addObject("login", user);
			    model.addObject("perfil", perfil);
			    model.addObject("menu", menu);
			    model.addObject("unidad_access", "5");
			    
			    model.addObject("modulo_access", "8");
			    model.addObject("tipo", String.valueOf(tipo));
			    model.addObject("urlRestServiceDelivery", urlRestServiceDelivery);
			    model.addObject("permisoModulo", Integer.toString(permisoModulo));
			    model.addObject("id_operacion", String.valueOf(id));
			    model.addObject("lang",user.getLang_user());
			    model.addObject("tol",tol);
				model.setViewName("/modulos/work1/rev1/detalle");
				
				
				
			}else{
				model.setViewName("error-permiso");
				
			}
		}else{
	    	
	    	model.setViewName("login");
	    }
		return model;
	
	}
	
	@RequestMapping(value = { "/RevisionProducto" }, method = RequestMethod.GET)
	public ModelAndView  RevisionProducto() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessMenu menus = (AccessMenu) context.getBean("AccessMenu");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		
		int fechaAhora = Integer.parseInt(format1.format(new Date()));
		String horaAhora = format2.format(new Date());
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
	    int permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 9);
	    ObjMenuManager menu = menus.getMenuSistema(1, user.getId_cliente(), perfil.getId_perfil());
	    ObjConfigTools tol = new ObjConfigTools();
	    
	    tol.setRefresco_table(Integer.parseInt(tools.getValorConfigById(36)));
	    tol.setRefersco_dashboard(Integer.parseInt(tools.getValorConfigById(38)));
	    tol.setTipo_menu(Integer.parseInt(tools.getValorConfigById(37)));
	    tol.setLang(user.getLang_user());
		
		ModelAndView model = new ModelAndView();
		
		if(user.getOtp_user() == 1 && permisoModulo != 0){
			model.addObject("login", user);
		    model.addObject("perfil", perfil);
		    model.addObject("menu", menu);
		    model.addObject("unidad_access", "5");
		    model.addObject("modulo_access", "9");
		    model.addObject("permisoModulo", Integer.toString(permisoModulo));
		    model.addObject("lang",user.getLang_user());
		    model.addObject("tol",tol);
			
			model.setViewName("/modulos/work1/rev2/modulo");
		}else{
	    	
	    	model.setViewName("login");
	    }
		return model;
	
	}
	@RequestMapping(value = { "/DetalleRevisionProducto" }, method = RequestMethod.GET)
	public ModelAndView  DetalleRevisionProducto(@RequestParam("id") int id,
									@RequestParam("nombre") String nombre,
									@RequestParam("tipo") int tipo
									) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessMenu menus = (AccessMenu) context.getBean("AccessMenu");
		AccessTraza traza = (AccessTraza) context.getBean("AccessTraza");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		String urlRestServiceDelivery = tools.getValorConfigById(15);
		
		int fechaAhora = Integer.parseInt(format1.format(new Date()));
		String horaAhora = format2.format(new Date());
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
	    int permisoModulo = 0;
	    ObjMenuManager menu = menus.getMenuSistema(1, user.getId_cliente(), perfil.getId_perfil());
	    String fechaNow = format3.format(new Date());
	    
	    ObjConfigTools tol = new ObjConfigTools();
	    
	    tol.setRefresco_table(Integer.parseInt(tools.getValorConfigById(36)));
	    tol.setRefersco_dashboard(Integer.parseInt(tools.getValorConfigById(38)));
	    tol.setTipo_menu(Integer.parseInt(tools.getValorConfigById(37)));
		
	    String nombre_aux = "";
	    
	    if(nombre != null){
	    	try {
				nombre_aux = URLDecoder.decode(nombre, "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	   
	    permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 9);
	    
	    
	    
	    ModelAndView model = new ModelAndView();
		
		if(user.getOtp_user() == 1){
			if(permisoModulo != 0){
				
				traza.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, id, 3, 0, 0, "CONSULTA ESTUDIO REVISION PRODUCTOS", "USUARIO CONSULTA OPERACION "+ nombre_aux,8));
				
				
				model.addObject("login", user);
			    model.addObject("perfil", perfil);
			    model.addObject("menu", menu);
			    model.addObject("unidad_access", "5");
			    
			    model.addObject("modulo_access", "9");
			    model.addObject("tipo", String.valueOf(tipo));
			    model.addObject("urlRestServiceDelivery", urlRestServiceDelivery);
			    model.addObject("permisoModulo", Integer.toString(permisoModulo));
			    model.addObject("id_operacion", String.valueOf(id));
			    model.addObject("lang",user.getLang_user());
			    model.addObject("tol",tol);
				model.setViewName("/modulos/work1/rev2/detalle");
				
				
				
			}else{
				model.setViewName("error-permiso");
				
			}
		}else{
	    	
	    	model.setViewName("login");
	    }
		return model;
	
	}
	@RequestMapping(value = { "/RevisionInstalaciones" }, method = RequestMethod.GET)
	public ModelAndView  RevisionInstalaciones() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessMenu menus = (AccessMenu) context.getBean("AccessMenu");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		
		int fechaAhora = Integer.parseInt(format1.format(new Date()));
		String horaAhora = format2.format(new Date());
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
	    int permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 10);
	    ObjMenuManager menu = menus.getMenuSistema(1, user.getId_cliente(), perfil.getId_perfil());
	    ObjConfigTools tol = new ObjConfigTools();
	    
	    tol.setRefresco_table(Integer.parseInt(tools.getValorConfigById(36)));
	    tol.setRefersco_dashboard(Integer.parseInt(tools.getValorConfigById(38)));
	    tol.setTipo_menu(Integer.parseInt(tools.getValorConfigById(37)));
	    tol.setLang(user.getLang_user());
		
		ModelAndView model = new ModelAndView();
		
		if(user.getOtp_user() == 1 && permisoModulo != 0){
			model.addObject("login", user);
		    model.addObject("perfil", perfil);
		    model.addObject("menu", menu);
		    model.addObject("unidad_access", "5");
		    model.addObject("modulo_access", "10");
		    model.addObject("permisoModulo", Integer.toString(permisoModulo));
		    model.addObject("lang",user.getLang_user());
		    model.addObject("tol",tol);
			
			model.setViewName("/modulos/work1/rev3/modulo");
		}else{
	    	
	    	model.setViewName("login");
	    }
		return model;
	
	}
	@RequestMapping(value = { "/DetalleRevisionInstalaciones" }, method = RequestMethod.GET)
	public ModelAndView  DetalleRevisionInstalaciones(@RequestParam("id") int id,
									@RequestParam("nombre") String nombre,
									@RequestParam("tipo") int tipo
									) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessMenu menus = (AccessMenu) context.getBean("AccessMenu");
		AccessTraza traza = (AccessTraza) context.getBean("AccessTraza");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		String urlRestServiceDelivery = tools.getValorConfigById(15);
		
		int fechaAhora = Integer.parseInt(format1.format(new Date()));
		String horaAhora = format2.format(new Date());
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
	    int permisoModulo = 0;
	    ObjMenuManager menu = menus.getMenuSistema(1, user.getId_cliente(), perfil.getId_perfil());
	    String fechaNow = format3.format(new Date());
	    
	    ObjConfigTools tol = new ObjConfigTools();
	    
	    tol.setRefresco_table(Integer.parseInt(tools.getValorConfigById(36)));
	    tol.setRefersco_dashboard(Integer.parseInt(tools.getValorConfigById(38)));
	    tol.setTipo_menu(Integer.parseInt(tools.getValorConfigById(37)));
		
	    String nombre_aux = "";
	    
	    if(nombre != null){
	    	try {
				nombre_aux = URLDecoder.decode(nombre, "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	   
	    permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 10);
	    
	    
	    
	    ModelAndView model = new ModelAndView();
		
		if(user.getOtp_user() == 1){
			if(permisoModulo != 0){
				
				traza.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, id, 3, 0, 0, "CONSULTA ESTUDIO REVISION INSTALACIONES", "USUARIO CONSULTA OPERACION "+ nombre_aux,8));
				
				
				model.addObject("login", user);
			    model.addObject("perfil", perfil);
			    model.addObject("menu", menu);
			    model.addObject("unidad_access", "5");
			    
			    model.addObject("modulo_access", "10");
			    model.addObject("tipo", String.valueOf(tipo));
			    model.addObject("urlRestServiceDelivery", urlRestServiceDelivery);
			    model.addObject("permisoModulo", Integer.toString(permisoModulo));
			    model.addObject("id_operacion", String.valueOf(id));
			    model.addObject("lang",user.getLang_user());
			    model.addObject("tol",tol);
				model.setViewName("/modulos/work1/rev3/detalle");
				
				
				
			}else{
				model.setViewName("error-permiso");
				
			}
		}else{
	    	
	    	model.setViewName("login");
	    }
		return model;
	
	}
	
	@RequestMapping(value = { "/RevisionDispositivo" }, method = RequestMethod.GET)
	public ModelAndView  RevisionDispositivo() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessMenu menus = (AccessMenu) context.getBean("AccessMenu");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		
		int fechaAhora = Integer.parseInt(format1.format(new Date()));
		String horaAhora = format2.format(new Date());
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
	    int permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 11);
	    ObjMenuManager menu = menus.getMenuSistema(1, user.getId_cliente(), perfil.getId_perfil());
	    ObjConfigTools tol = new ObjConfigTools();
	    
	    tol.setRefresco_table(Integer.parseInt(tools.getValorConfigById(36)));
	    tol.setRefersco_dashboard(Integer.parseInt(tools.getValorConfigById(38)));
	    tol.setTipo_menu(Integer.parseInt(tools.getValorConfigById(37)));
	    tol.setLang(user.getLang_user());
		
		ModelAndView model = new ModelAndView();
		
		if(user.getOtp_user() == 1 && permisoModulo != 0){
			model.addObject("login", user);
		    model.addObject("perfil", perfil);
		    model.addObject("menu", menu);
		    model.addObject("unidad_access", "5");
		    model.addObject("modulo_access", "11");
		    model.addObject("permisoModulo", Integer.toString(permisoModulo));
		    model.addObject("lang",user.getLang_user());
		    model.addObject("tol",tol);
			
			model.setViewName("/modulos/work1/rev4/modulo");
		}else{
	    	
	    	model.setViewName("login");
	    }
		return model;
	
	}
	@RequestMapping(value = { "/DetalleRevisionDispositivo" }, method = RequestMethod.GET)
	public ModelAndView  DetalleRevisionDispositivo(@RequestParam("id") int id,
									@RequestParam("nombre") String nombre,
									@RequestParam("tipo") int tipo
									) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessMenu menus = (AccessMenu) context.getBean("AccessMenu");
		AccessTraza traza = (AccessTraza) context.getBean("AccessTraza");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		String urlRestServiceDelivery = tools.getValorConfigById(15);
		
		int fechaAhora = Integer.parseInt(format1.format(new Date()));
		String horaAhora = format2.format(new Date());
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
	    int permisoModulo = 0;
	    ObjMenuManager menu = menus.getMenuSistema(1, user.getId_cliente(), perfil.getId_perfil());
	    String fechaNow = format3.format(new Date());
	    
	    ObjConfigTools tol = new ObjConfigTools();
	    
	    tol.setRefresco_table(Integer.parseInt(tools.getValorConfigById(36)));
	    tol.setRefersco_dashboard(Integer.parseInt(tools.getValorConfigById(38)));
	    tol.setTipo_menu(Integer.parseInt(tools.getValorConfigById(37)));
		
	    String nombre_aux = "";
	    
	    if(nombre != null){
	    	try {
				nombre_aux = URLDecoder.decode(nombre, "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	   
	    permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 11);
	    
	    
	    
	    ModelAndView model = new ModelAndView();
		
		if(user.getOtp_user() == 1){
			if(permisoModulo != 0){
				
				traza.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, id, 3, 0, 0, "CONSULTA ESTUDIO REVISION DISPOSITIVO", "USUARIO CONSULTA OPERACION "+ nombre_aux,8));
				
				
				model.addObject("login", user);
			    model.addObject("perfil", perfil);
			    model.addObject("menu", menu);
			    model.addObject("unidad_access", "5");
			    
			    model.addObject("modulo_access", "11");
			    model.addObject("tipo", String.valueOf(tipo));
			    model.addObject("urlRestServiceDelivery", urlRestServiceDelivery);
			    model.addObject("permisoModulo", Integer.toString(permisoModulo));
			    model.addObject("id_operacion", String.valueOf(id));
			    model.addObject("lang",user.getLang_user());
			    model.addObject("tol",tol);
				model.setViewName("/modulos/work1/rev4/detalle");
				
				
				
			}else{
				model.setViewName("error-permiso");
				
			}
		}else{
	    	
	    	model.setViewName("login");
	    }
		return model;
	
	}
	
	@RequestMapping(value = { "/AsignacionUsuario" }, method = RequestMethod.GET)
	public ModelAndView  AsignacionUsuario() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessMenu menus = (AccessMenu) context.getBean("AccessMenu");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		
		int fechaAhora = Integer.parseInt(format1.format(new Date()));
		String horaAhora = format2.format(new Date());
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
	    int permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 12);
	    ObjMenuManager menu = menus.getMenuSistema(1, user.getId_cliente(), perfil.getId_perfil());
	    ObjConfigTools tol = new ObjConfigTools();
	    
	    tol.setRefresco_table(Integer.parseInt(tools.getValorConfigById(36)));
	    tol.setRefersco_dashboard(Integer.parseInt(tools.getValorConfigById(38)));
	    tol.setTipo_menu(Integer.parseInt(tools.getValorConfigById(37)));
	    tol.setLang(user.getLang_user());
		
		ModelAndView model = new ModelAndView();
		
		if(user.getOtp_user() == 1 && permisoModulo != 0){
			model.addObject("login", user);
		    model.addObject("perfil", perfil);
		    model.addObject("menu", menu);
		    model.addObject("unidad_access", "5");
		    model.addObject("modulo_access", "12");
		    model.addObject("permisoModulo", Integer.toString(permisoModulo));
		    model.addObject("lang",user.getLang_user());
		    model.addObject("tol",tol);
			
			model.setViewName("/modulos/work1/act12/modulo");
		}else{
	    	
	    	model.setViewName("login");
	    }
		return model;
	
	}
	@RequestMapping(value = { "/DetalleAsignacionUsuario" }, method = RequestMethod.GET)
	public ModelAndView  DetalleAsignacionUsuario(@RequestParam("id") int id,
									@RequestParam("nombre") String nombre,
									@RequestParam("tipo") int tipo
									) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessMenu menus = (AccessMenu) context.getBean("AccessMenu");
		AccessTraza traza = (AccessTraza) context.getBean("AccessTraza");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		String urlRestServiceDelivery = tools.getValorConfigById(15);
		
		int fechaAhora = Integer.parseInt(format1.format(new Date()));
		String horaAhora = format2.format(new Date());
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
	    int permisoModulo = 0;
	    ObjMenuManager menu = menus.getMenuSistema(1, user.getId_cliente(), perfil.getId_perfil());
	    String fechaNow = format3.format(new Date());
	    
	    ObjConfigTools tol = new ObjConfigTools();
	    
	    tol.setRefresco_table(Integer.parseInt(tools.getValorConfigById(36)));
	    tol.setRefersco_dashboard(Integer.parseInt(tools.getValorConfigById(38)));
	    tol.setTipo_menu(Integer.parseInt(tools.getValorConfigById(37)));
		
	    String nombre_aux = "";
	    
	    if(nombre != null){
	    	try {
				nombre_aux = URLDecoder.decode(nombre, "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	   
	    permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 12);
	    
	    
	    
	    ModelAndView model = new ModelAndView();
		
		if(user.getOtp_user() == 1){
			if(permisoModulo != 0){
				
				traza.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, id, 3, 0, 0, "CONSULTA ESTUDIO REVISION ASIGNACION DE USUARIOS", "USUARIO CONSULTA OPERACION "+ nombre_aux,8));
				
				
				model.addObject("login", user);
			    model.addObject("perfil", perfil);
			    model.addObject("menu", menu);
			    model.addObject("unidad_access", "5");
			    
			    model.addObject("modulo_access", "12");
			    model.addObject("tipo", String.valueOf(tipo));
			    model.addObject("urlRestServiceDelivery", urlRestServiceDelivery);
			    model.addObject("permisoModulo", Integer.toString(permisoModulo));
			    model.addObject("id_operacion", String.valueOf(id));
			    model.addObject("lang",user.getLang_user());
			    model.addObject("tol",tol);
				model.setViewName("/modulos/work1/act12/detalle");
				
				
				
			}else{
				model.setViewName("error-permiso");
				
			}
		}else{
	    	
	    	model.setViewName("login");
	    }
		return model;
	
	}
	
	@RequestMapping(value = { "/ListDetalleAsignacionUsuario" }, method = RequestMethod.GET)
	public ModelAndView  ListDetalleAsignacionUsuario(@RequestParam("id") int id,
									@RequestParam("desde") String desde,
									@RequestParam("hasta") String hasta,
									@RequestParam("div") int div,
									@RequestParam("sub") int sub
									) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessAsignUsers asigs = (AccessAsignUsers) context.getBean("AccessAsignUsers");
		AccessTraza traza = (AccessTraza) context.getBean("AccessTraza");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		String urlRestServiceDelivery = tools.getValorConfigById(15);
		
		int fechaAhora = Integer.parseInt(format1.format(new Date()));
		String horaAhora = format2.format(new Date());
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
	    int permisoModulo = 0;
	    
	    String fechaNow = format3.format(new Date());
	    
	    
	    
	    
	    String nombre_aux = "";
	    
	    ObjFiltroAsignUser fil = new ObjFiltroAsignUser();
	    fil.setDesde(desde);
	    fil.setHasta(hasta);
	    fil.setDiv(div);
	    fil.setSub(sub);
	    
	    ArrayList<ObjAsignUser> list = asigs.getListAsignUser(fil);
	    
	   
	    permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 12);
	    
	    
	    
	    ModelAndView model = new ModelAndView();
		
		if(user.getOtp_user() == 1){
			if(permisoModulo != 0){
				
				//traza.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, id, 3, 0, 0, "CONSULTA ESTUDIO REVISION ASIGNACION DE USUARIOS", "USUARIO CONSULTA OPERACION "+ nombre_aux,8));
				
				
				model.addObject("login", user);
			    model.addObject("perfil", perfil);
			    
			    model.addObject("unidad_access", "5");
			    
			    model.addObject("modulo_access", "12");
			    model.addObject("permisoModulo", Integer.toString(permisoModulo));
			    model.addObject("id_operacion", String.valueOf(id));
			    model.addObject("lang",user.getLang_user());
			    model.addObject("list",list);
			    model.addObject("fil",fil);
				model.setViewName("/modulos/work1/act12/detalle_asignacion");
				
				
				
			}else{
				model.setViewName("error-permiso");
				
			}
		}else{
	    	
	    	model.setViewName("login");
	    }
		return model;
	
	}
	
	@RequestMapping(value = { "/AgendarKickOff" }, method = RequestMethod.GET)
	public ModelAndView  AgendarKickOff() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessMenu menus = (AccessMenu) context.getBean("AccessMenu");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		
		int fechaAhora = Integer.parseInt(format1.format(new Date()));
		String horaAhora = format2.format(new Date());
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
	    int permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 13);
	    ObjMenuManager menu = menus.getMenuSistema(1, user.getId_cliente(), perfil.getId_perfil());
	    ObjConfigTools tol = new ObjConfigTools();
	    
	    tol.setRefresco_table(Integer.parseInt(tools.getValorConfigById(36)));
	    tol.setRefersco_dashboard(Integer.parseInt(tools.getValorConfigById(38)));
	    tol.setTipo_menu(Integer.parseInt(tools.getValorConfigById(37)));
	    tol.setLang(user.getLang_user());
		
		ModelAndView model = new ModelAndView();
		
		if(user.getOtp_user() == 1 && permisoModulo != 0){
			model.addObject("login", user);
		    model.addObject("perfil", perfil);
		    model.addObject("menu", menu);
		    model.addObject("unidad_access", "5");
		    model.addObject("modulo_access", "13");
		    model.addObject("permisoModulo", Integer.toString(permisoModulo));
		    model.addObject("lang",user.getLang_user());
		    model.addObject("tol",tol);
			
			model.setViewName("/modulos/work1/act13/modulo");
		}else{
	    	
	    	model.setViewName("login");
	    }
		return model;
	
	}
	
	
	
	
	@RequestMapping(value = { "/DetalleAgendarKickOff" }, method = RequestMethod.GET)
	public ModelAndView  DetalleAgendarKickOff(@RequestParam("id") int id,
									@RequestParam("nombre") String nombre,
									@RequestParam("tipo") int tipo
									) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessMenu menus = (AccessMenu) context.getBean("AccessMenu");
		AccessTraza traza = (AccessTraza) context.getBean("AccessTraza");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		String urlRestServiceDelivery = tools.getValorConfigById(15);
		
		int fechaAhora = Integer.parseInt(format1.format(new Date()));
		String horaAhora = format2.format(new Date());
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
	    int permisoModulo = 0;
	    ObjMenuManager menu = menus.getMenuSistema(1, user.getId_cliente(), perfil.getId_perfil());
	    String fechaNow = format3.format(new Date());
	    
	    ObjConfigTools tol = new ObjConfigTools();
	    
	    tol.setRefresco_table(Integer.parseInt(tools.getValorConfigById(36)));
	    tol.setRefersco_dashboard(Integer.parseInt(tools.getValorConfigById(38)));
	    tol.setTipo_menu(Integer.parseInt(tools.getValorConfigById(37)));
		
	    String nombre_aux = "";
	    
	    if(nombre != null){
	    	try {
				nombre_aux = URLDecoder.decode(nombre, "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	   
	    permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 13);
	    
	    
	    
	    ModelAndView model = new ModelAndView();
		
		if(user.getOtp_user() == 1){
			if(permisoModulo != 0){
				
				traza.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, id, 3, 0, 0, "CONSULTA ESTUDIO REVISION AGENDAR KICK OFF", "USUARIO CONSULTA OPERACION "+ nombre_aux,8));
				
				
				model.addObject("login", user);
			    model.addObject("perfil", perfil);
			    model.addObject("menu", menu);
			    model.addObject("unidad_access", "5");
			    
			    model.addObject("modulo_access", "13");
			    model.addObject("tipo", String.valueOf(tipo));
			    model.addObject("urlRestServiceDelivery", urlRestServiceDelivery);
			    model.addObject("permisoModulo", Integer.toString(permisoModulo));
			    model.addObject("id_operacion", String.valueOf(id));
			    model.addObject("lang",user.getLang_user());
			    model.addObject("tol",tol);
				model.setViewName("/modulos/work1/act13/detalle");
				
				
				
			}else{
				model.setViewName("error-permiso");
				
			}
		}else{
	    	
	    	model.setViewName("login");
	    }
		return model;
	
	}
	
	@RequestMapping(value = { "/KickOff" }, method = RequestMethod.GET)
	public ModelAndView  KickOff() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessMenu menus = (AccessMenu) context.getBean("AccessMenu");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		
		int fechaAhora = Integer.parseInt(format1.format(new Date()));
		String horaAhora = format2.format(new Date());
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
	    int permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 14);
	    ObjMenuManager menu = menus.getMenuSistema(1, user.getId_cliente(), perfil.getId_perfil());
	    ObjConfigTools tol = new ObjConfigTools();
	    
	    tol.setRefresco_table(Integer.parseInt(tools.getValorConfigById(36)));
	    tol.setRefersco_dashboard(Integer.parseInt(tools.getValorConfigById(38)));
	    tol.setTipo_menu(Integer.parseInt(tools.getValorConfigById(37)));
	    tol.setLang(user.getLang_user());
		
		ModelAndView model = new ModelAndView();
		
		if(user.getOtp_user() == 1 && permisoModulo != 0){
			model.addObject("login", user);
		    model.addObject("perfil", perfil);
		    model.addObject("menu", menu);
		    model.addObject("unidad_access", "5");
		    model.addObject("modulo_access", "14");
		    model.addObject("permisoModulo", Integer.toString(permisoModulo));
		    model.addObject("lang",user.getLang_user());
		    model.addObject("tol",tol);
			
			model.setViewName("/modulos/work1/act14/modulo");
		}else{
	    	
	    	model.setViewName("login");
	    }
		return model;
	
	}
	@RequestMapping(value = { "/DetalleKickOff" }, method = RequestMethod.GET)
	public ModelAndView  DetalleKickOff(@RequestParam("id") int id,
									@RequestParam("nombre") String nombre,
									@RequestParam("tipo") int tipo
									) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessMenu menus = (AccessMenu) context.getBean("AccessMenu");
		AccessTraza traza = (AccessTraza) context.getBean("AccessTraza");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		String urlRestServiceDelivery = tools.getValorConfigById(15);
		
		int fechaAhora = Integer.parseInt(format1.format(new Date()));
		String horaAhora = format2.format(new Date());
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
	    int permisoModulo = 0;
	    ObjMenuManager menu = menus.getMenuSistema(1, user.getId_cliente(), perfil.getId_perfil());
	    String fechaNow = format3.format(new Date());
	    
	    ObjConfigTools tol = new ObjConfigTools();
	    
	    tol.setRefresco_table(Integer.parseInt(tools.getValorConfigById(36)));
	    tol.setRefersco_dashboard(Integer.parseInt(tools.getValorConfigById(38)));
	    tol.setTipo_menu(Integer.parseInt(tools.getValorConfigById(37)));
		
	    String nombre_aux = "";
	    
	    if(nombre != null){
	    	try {
				nombre_aux = URLDecoder.decode(nombre, "ISO-8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    
	   
	    permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 14);
	    
	    
	    
	    ModelAndView model = new ModelAndView();
		
		if(user.getOtp_user() == 1){
			if(permisoModulo != 0){
				
				traza.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, id, 3, 0, 0, "CONSULTA ESTUDIO REVISION AGENDAR KICK OFF", "USUARIO CONSULTA OPERACION "+ nombre_aux,8));
				
				
				model.addObject("login", user);
			    model.addObject("perfil", perfil);
			    model.addObject("menu", menu);
			    model.addObject("unidad_access", "5");
			    
			    model.addObject("modulo_access", "14");
			    model.addObject("tipo", String.valueOf(tipo));
			    model.addObject("urlRestServiceDelivery", urlRestServiceDelivery);
			    model.addObject("permisoModulo", Integer.toString(permisoModulo));
			    model.addObject("id_operacion", String.valueOf(id));
			    model.addObject("lang",user.getLang_user());
			    model.addObject("tol",tol);
				model.setViewName("/modulos/work1/act14/detalle");
				
				
				
			}else{
				model.setViewName("error-permiso");
				
			}
		}else{
	    	
	    	model.setViewName("login");
	    }
		return model;
	
	}
}
