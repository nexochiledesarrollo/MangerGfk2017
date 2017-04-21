package cl.nexo.manager.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.ObjDoubleConsumer;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cl.nexo.manager.access.dao.AccessDao;
import cl.nexo.manager.access.dashboard.AccessDashboard;
import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.access.login.AccessPerfil;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.menu.AccessMenu;
import cl.nexo.manager.obj.dashboard.ObjWidjetAccessAdmin;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.login.ObjPerfilLogin;
import cl.nexo.manager.obj.menu.ObjMenuManager;
import cl.nexo.manager.obj.menu.ObjModuloMenuManager;
import cl.nexo.manager.obj.tools.ObjConfigTools;



@Controller
public class HomeController {
	private static final Logger logger = Logger.getLogger(HomeController.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public ModelAndView  home(){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessMenu menus = (AccessMenu) context.getBean("AccessMenu");
		AccessDashboard dash  = (AccessDashboard) context.getBean("AccessDashboard");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		
		
		int fechaAhora = Integer.parseInt(format1.format(new Date()));
		String horaAhora = format2.format(new Date());
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
	    ObjMenuManager menu = menus.getMenuSistema(1, user.getId_cliente(), perfil.getId_perfil());
	    ObjWidjetAccessAdmin obDash = new ObjWidjetAccessAdmin();
	    
	    //String aux_refresco = tools.getValorConfigById(36);
	    
	    ObjConfigTools tol = new ObjConfigTools();
	    
	    tol.setRefresco_table(Integer.parseInt(tools.getValorConfigById(36)));
	    tol.setRefersco_dashboard(Integer.parseInt(tools.getValorConfigById(38)));
	    tol.setTipo_menu(Integer.parseInt(tools.getValorConfigById(37)));
	    tol.setLang(user.getLang_user());
	    
	    if(perfil.getEs_admin() == 1){
	    	obDash = dash.getValueDashAdmin();
	    }
	    
	    ModelAndView model = new ModelAndView();
	    
	    if(user.getOtp_user() == 1){
	    
		    model.addObject("login", user);
		    model.addObject("perfil", perfil);
		    model.addObject("menu", menu);
		    model.addObject("WidjetAccessAdmin", obDash);
		    model.addObject("unidad_access", "1");
		    model.addObject("modulo_access", "1");
		    model.addObject("tol",tol);
		    
		    model.setViewName("home");
	    }else{
	    	
	    	model.setViewName("login");
	    }
		
		return model;
	}
    
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {

	  ModelAndView model = new ModelAndView();
	  if (error != null) {
		model.addObject("error", "Invalid username and password!");
	  }

	  if (logout != null) {
		model.addObject("msg", "You've been logged out successfully.");
	  }
	  model.setViewName("login");

	  return model;

	}
	
	@RequestMapping(value = "/olvidoClave", method = RequestMethod.GET)
	public ModelAndView olvidoClave() {

	  ModelAndView model = new ModelAndView();
	  model.setViewName("olvido_clave");

	  return model;

	}
	//for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

	  ModelAndView model = new ModelAndView();
		
	  //check if user is login
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if (!(auth instanceof AnonymousAuthenticationToken)) {
		UserDetails userDetail = (UserDetails) auth.getPrincipal();	
		model.addObject("username", userDetail.getUsername());
	  }
		
	  model.setViewName("403");
	  return model;

	}
}
