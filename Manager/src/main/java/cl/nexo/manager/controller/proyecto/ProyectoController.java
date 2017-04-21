package cl.nexo.manager.controller.proyecto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

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
import cl.nexo.manager.access.traza.AccessTraza;
import cl.nexo.manager.controller.login.LoginController;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.login.ObjPerfilLogin;
import cl.nexo.manager.obj.menu.ObjMenuManager;
import cl.nexo.manager.obj.tools.ObjConfigTools;
import cl.nexo.manager.obj.traza.ObjTrazaManager;

@Controller
@RequestMapping("/proyectoService")
public class ProyectoController {
	private static final Logger logger = Logger.getLogger(LoginController.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	@RequestMapping(value = { "/manProy" }, method = RequestMethod.GET)
	public ModelAndView  manProy() {
		
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
	    int permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 3);
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
		    model.addObject("unidad_access", "4");
		    model.addObject("modulo_access", "3");
		    model.addObject("permisoModulo", Integer.toString(permisoModulo));
		    model.addObject("lang",user.getLang_user());
		    model.addObject("tol",tol);
			
			model.setViewName("/modulos/proyecto/modulo");
		}else{
	    	
	    	model.setViewName("login");
	    }
		return model;
	
	}
	
	@RequestMapping(value = { "/detalleProy" }, method = RequestMethod.GET)
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
				
				traza.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, id, 3, 0, 0, "CONSULTA PROYECTO", "USUARIO CONSULTA OPERACION "+ nombre_aux,8));
				
				
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
				model.setViewName("/modulos/proyecto/detalle");
				
				
				
			}else{
				model.setViewName("error-permiso");
				
			}
		}else{
	    	
	    	model.setViewName("login");
	    }
		return model;
	
	}
	@RequestMapping(value = { "/manHabSam" }, method = RequestMethod.GET)
	public ModelAndView  manHabSam() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessMenu menus = (AccessMenu) context.getBean("AccessMenu");
		
		int fechaAhora = Integer.parseInt(format1.format(new Date()));
		String horaAhora = format2.format(new Date());
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
	    int permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 3);
	    ObjMenuManager menu = menus.getMenuSistema(1, user.getId_cliente(), perfil.getId_perfil());
		
		
		ModelAndView model = new ModelAndView();
		
		if(user.getOtp_user() == 1){
			model.addObject("login", user);
		    model.addObject("perfil", perfil);
		    model.addObject("menu", menu);
		    model.addObject("unidad_access", "4");
		    model.addObject("modulo_access", "4");
		    model.addObject("permisoModulo", Integer.toString(permisoModulo));
			
			
			model.setViewName("/modulos/proyecto-habilitacion/modulo");
		}else{
	    	
	    	model.setViewName("login");
	    }
		return model;
	
	}
	@RequestMapping(value = { "/newProy" }, method = RequestMethod.GET)
	public ModelAndView  newProy() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessMenu menus = (AccessMenu) context.getBean("AccessMenu");
		
		int fechaAhora = Integer.parseInt(format1.format(new Date()));
		String horaAhora = format2.format(new Date());
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
	    int permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 3);
	    ObjMenuManager menu = menus.getMenuSistema(1, user.getId_cliente(), perfil.getId_perfil());
		
		
		ModelAndView model = new ModelAndView();
		
		if(user.getOtp_user() == 1 && (permisoModulo == 1 || permisoModulo == 2 )){
			model.addObject("login", user);
		    model.addObject("perfil", perfil);
		    model.addObject("menu", menu);
		    model.addObject("unidad_access", "4");
		    model.addObject("modulo_access", "16 ");
		    model.addObject("lang",user.getLang_user());
		    model.addObject("permisoModulo", Integer.toString(permisoModulo));
			
			
			model.setViewName("/modulos/proyecto/nuevo-estudio");
		}else{
	    	
	    	model.setViewName("login");
	    }
		return model;
	
	}
	
}
