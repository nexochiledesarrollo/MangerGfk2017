package cl.nexo.manager.controller.practica;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import org.springframework.web.servlet.ModelAndView;

import cl.nexo.manager.access.apoderado.ApoderadoAccess;
import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.access.login.AccessPerfil;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.menu.AccessMenu;
import cl.nexo.manager.controller.login.LoginController;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.login.ObjPerfilLogin;
import cl.nexo.manager.obj.menu.ObjMenuManager;
import cl.nexo.manager.obj.tools.ObjConfigTools;



@Controller
@RequestMapping("/apoderadoPrueba")
public class ApoderadoController {
	//private static final Logger logger = Logger.getLogger(LoginController.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	

	
	@RequestMapping(value = { "/listaApoderados" }, method = RequestMethod.GET)
	public ModelAndView  listApoderado() {
		
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
	    int permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 2);
	    ObjMenuManager menu = menus.getMenuSistema(1, user.getId_cliente(), perfil.getId_perfil());
		
	    ObjConfigTools tol = new ObjConfigTools();
	    
	    tol.setRefresco_table(Integer.parseInt(tools.getValorConfigById(36)));
	    tol.setRefersco_dashboard(Integer.parseInt(tools.getValorConfigById(38)));
	    tol.setTipo_menu(Integer.parseInt(tools.getValorConfigById(37)));
		
		ModelAndView model = new ModelAndView();
		
		if(user.getOtp_user() == 1){
			model.addObject("login", user);
		    model.addObject("perfil", perfil);
		    model.addObject("menu", menu);
		    model.addObject("unidad_access", "2");
		    model.addObject("modulo_access", "2");
		    model.addObject("permisoModulo", Integer.toString(permisoModulo));
		    model.addObject("title_plataform", tools.getValorConfigById(35));
		    model.addObject("tol",tol);
			
			
			model.setViewName("/modulos/apoderado/modulo");
			//model.setViewName("/modulos/usuario/modulo");
		}else{
	    	
	    	model.setViewName("login");
	    }
		return model;
	}
	
	

}
