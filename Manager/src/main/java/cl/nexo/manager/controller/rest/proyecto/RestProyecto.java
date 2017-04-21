package cl.nexo.manager.controller.rest.proyecto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

import cl.nexo.manager.access.login.AccessPerfil;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.proyecto.AccessProyecto;
import cl.nexo.manager.obj.login.ObjDataLogin;
import cl.nexo.manager.obj.login.ObjListManUser;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.login.ObjPerfilLogin;
import cl.nexo.manager.obj.proyecto.ObjDataProyecto;
import cl.nexo.manager.obj.proyecto.ObjProyectoEstandar;



@RestController
@RequestMapping("/RestProyecto")
public class RestProyecto {
	private static final Logger logger = Logger.getLogger(RestProyecto.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("yyyyMMddHHmmss");
	
	@RequestMapping(value = "/getListProyectos", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjDataProyecto getListProyectos(){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessProyecto proys = (AccessProyecto) context.getBean("AccessProyecto");
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
		
	    int permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 3);
		
		ObjDataProyecto result = new ObjDataProyecto();
		
		if(permisoModulo != 0){
			
			int aux_tipo = 0;
			String aux_id = "";
			
			if(perfil.getEs_admin() == 1){
				aux_tipo = 0;
				aux_id = "0";
			}else{
				//Logica de filtro
			}
			
			ArrayList<ObjProyectoEstandar> list = proys.getProyecto(aux_tipo, aux_id,0,0);
			
			logger.info("tamaño de array: " + list.size());
			
			result.setData(list);
		}
		return result;
	}
	
	@RequestMapping(value = "/getListProyectosSam", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjDataProyecto getListProyectosSam(){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessProyecto proys = (AccessProyecto) context.getBean("AccessProyecto");
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
		
	    int permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 3);
		
		ObjDataProyecto result = new ObjDataProyecto();
		
		if(permisoModulo != 0){
			
			int aux_tipo = 0;
			String aux_id = "";
			
			if(perfil.getEs_admin() == 1){
				aux_tipo = 0;
				aux_id = "0";
			}else{
				//Logica de filtro
			}
			
			ArrayList<ObjProyectoEstandar> list = proys.getProyecto(aux_tipo, aux_id,1,1);
			
			logger.info("tamaño de array: " + list.size());
			
			result.setData(list);
		}
		return result;
	}
	
	@RequestMapping(value = "/getDetailProyectoById", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjProyectoEstandar getDetailProyectoById(@RequestParam("id") Long id){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		AccessProyecto proys = (AccessProyecto) context.getBean("AccessProyecto");
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName()); 
	    ObjPerfilLogin perfil = perfils.getPerfilById(user.getId_perfil());
		
	    int permisoModulo = perfils.getPermisoPerfilModulo(perfil.getId_perfil(), 3);
		
	    ObjProyectoEstandar result = new ObjProyectoEstandar();
		
		if(permisoModulo != 0){
			
			int aux_tipo = 0;
			String aux_id = "";
			
			if(perfil.getEs_admin() == 1){
				aux_tipo = 0;
				aux_id = "0";
			}else{
				//Logica de filtro
			}
			
			result = proys.getProyecto(id, 1);
			
		}
		return result;
	}
	
	
	
	
}
