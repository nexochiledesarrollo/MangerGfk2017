package cl.nexo.manager.controller.rest.login;

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





import cl.nexo.manager.access.login.AccessPerfil;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.imp.login.LoginUser;
import cl.nexo.manager.obj.login.ObjDataAccess;
import cl.nexo.manager.obj.login.ObjDataAccessSubDiv;
import cl.nexo.manager.obj.login.ObjDataLogin;
import cl.nexo.manager.obj.login.ObjDataPerfil;
import cl.nexo.manager.obj.login.ObjListManPerfil;
import cl.nexo.manager.obj.login.ObjListManUser;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.login.ObjMenuPermisosModulosPerfil;
import cl.nexo.manager.obj.login.ObjPerfilLogin;
import cl.nexo.manager.obj.login.ObjPermisosModuloPerfil;
import cl.nexo.manager.obj.login.ObjPermisosSubDivisionPerfil;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;
import cl.nexo.manager.obj.tools.ObjGeneralResultInt;



@RestController
@RequestMapping("/RestPerfilUser")
public class RestPerfilUser {
	private static final Logger logger = Logger.getLogger(RestPerfilUser.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	@RequestMapping(value = "/getComboPerfil/{cliente}", method = RequestMethod.GET,headers="Accept=application/json")
	public ArrayList<ObjComboSelectValueInt> getComboMenu(@PathVariable("cliente") int id,
														  @RequestParam("div") int div
														  )
	{
		 ArrayList<ObjComboSelectValueInt> combo =  new ArrayList<ObjComboSelectValueInt>();
		 
		 SecurityContext securityContext = SecurityContextHolder.getContext();
	     Authentication authentication = securityContext.getAuthentication();
	     String user = authentication.getName();
		 
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		 
		 ObjLoginUser sesion = logins.getUserByLogin(user);
		 
		 AccessPerfil perfiles = (AccessPerfil) context.getBean("AccessPerfil");
		 
		 
		 combo = perfiles.getComboPerfilCliente(id, div);
		 
		 return combo;
		 
	}
	
	@RequestMapping(value = "/getListPerfilByCliente/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjDataPerfil getListUserByCliente(@PathVariable("id") int id){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		
		ObjDataPerfil result = new ObjDataPerfil();
		
		ArrayList<ObjListManPerfil> list = perfils.getListManPerfilByCliente(id);
		result.setData(list);
		
		return result;
	}
	
	@RequestMapping(value = "/getExistPerfil/{perfil}", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt getExistRun(@PathVariable("perfil") String perfil){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		
		int value = perfils.getExistPerfilByName(perfil);
		
		ObjGeneralResultInt result = new ObjGeneralResultInt();
		
		result.setResult(value);
		result.setText("Val NAME Perfil -- 1: Existe -- 0: No Existe");
		
		return result;
		
	}
	@RequestMapping(value = "/getDetailUserById/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjPerfilLogin getDetailUserById(@PathVariable("id") int id){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		
		ObjPerfilLogin result = perfils.getPerfilById(id);
		
		return result;
		
	}
	@RequestMapping(value = "/getListAccessPerfilById/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjDataAccess getListAccessPerfilById(@PathVariable("id") int id){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		
		ObjDataAccess result = new ObjDataAccess();
		
		ArrayList<ObjMenuPermisosModulosPerfil> list = perfils.getListModulosPermisosPerfil(1, id);
		result.setData(list);
		
		return result;
	}
	
	@RequestMapping(value = "/getIdPerfilUserByName/{perfil}", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt getIdPerfilUserByName(@PathVariable("perfil") String perfil)
	{
		ObjGeneralResultInt result =  new ObjGeneralResultInt();
		 
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessPerfil perfiles = (AccessPerfil) context.getBean("AccessPerfil");
		 
		int result_action = perfiles.getIdPerfilUserByName(perfil);
		 result.setResult(result_action);
		 result.setText("Get Id Perfil");
		 
		 return result;
		 
	}
	
	@RequestMapping(value = "/setPerfilModuloById", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt setPerfilModuloById(@RequestParam("tipo") int tipo,
												   @RequestParam("modulo") int modulo,
												   @RequestParam("perfil") int perfil)
	{
		ObjGeneralResultInt result =  new ObjGeneralResultInt();
		 
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		
		 
		 logger.info("Se solicita Set Modulo perfil: "+ perfil + " -- modulo: "+modulo + " -- tipo: "+tipo);
		 
		 AccessPerfil perfiles = (AccessPerfil) context.getBean("AccessPerfil");
		 ObjPermisosModuloPerfil perPerfil = new ObjPermisosModuloPerfil();
		 perPerfil.setModulo_id(modulo);
		 perPerfil.setPerfil_id(perfil);
		 perPerfil.setPermiso_id(tipo);
		 int result_action = 0;
		 if(tipo == 1 || tipo == 2 || tipo == 3){
			 result_action = perfiles.setAccesoPerfilModulo(perPerfil);
		 }else{
			 result_action = perfiles.deletePerfilModulo(perPerfil);
		 }
		 
		 result.setResult(result_action);
		 result.setText("SET PERMISO");
		 
		 return result;
		 
	}
	
	@RequestMapping(value = "/setPerfilSubDivById", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt setPerfilSubDivById(@RequestParam("tipo") int tipo,
												   @RequestParam("sdiv") int sdiv,
												   @RequestParam("perfil") int perfil)
	{
		ObjGeneralResultInt result =  new ObjGeneralResultInt();
		 
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		
		 
		 logger.info("Se solicita Set Modulo perfil: "+ perfil + " -- Sub Div: "+sdiv + " -- tipo: "+ tipo);
		 
		 AccessPerfil perfiles = (AccessPerfil) context.getBean("AccessPerfil");
		 ObjPermisosModuloPerfil perPerfil = new ObjPermisosModuloPerfil();
		 perPerfil.setModulo_id(sdiv);
		 perPerfil.setPerfil_id(perfil);
		 perPerfil.setPermiso_id(tipo);
		 int result_action = 0;
		 if(tipo == 1 || tipo == 2 || tipo == 3){
			 logger.debug("Inserta con permiso: " + tipo);
			 result_action = perfiles.setAccesoPerfilSubDiv(perPerfil);
		 }else if(tipo == 0){
			 logger.debug("Delete con permiso: " + tipo);
			 result_action = perfiles.deletePerfilSubDiv(perPerfil);
		 }
		 
		 result.setResult(result_action);
		 result.setText("SET PERMISO");
		 
		 return result;
		 
	}
	
	@RequestMapping(value = "/setPerfilLogin", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt setPerfilLogin(@RequestParam("nombre") String nombre,
											  @RequestParam("admin") int admin,
											  @RequestParam("estado") int estado,
											  @RequestParam("codigo") int codigo
											  )
	{
		ObjGeneralResultInt result =  new ObjGeneralResultInt();
		 
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessPerfil perfiles = (AccessPerfil) context.getBean("AccessPerfil");
		
		ObjPerfilLogin per = new ObjPerfilLogin();
		per.setNombre_perfil(nombre);
		per.setEs_admin(admin);
		per.setEstado_perfil(estado);
		per.setCodigo_perfil(codigo);
		per.setId_cliente(1);
		
		int result_action = perfiles.setPerfilUser(per);
		 
		 result.setResult(result_action);
		 result.setText("Perfil <strong>"+ nombre + "</strong> a sido creado en el sistema.<br/> Favor continuar con el proceso de asignaci&oacute;n de acceso a modulos.");
		 
		 return result;
		 
	}
	
	@RequestMapping(value = "/updatePerfilLogin", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt updatePerfilLogin(
											  @RequestParam("id") int id,
											  @RequestParam("nombre") String nombre,
											  @RequestParam("admin") int admin,
											  @RequestParam("estado") int estado,
											  @RequestParam("codigo") int codigo
											  )
	{
		ObjGeneralResultInt result =  new ObjGeneralResultInt();
		 
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessPerfil perfiles = (AccessPerfil) context.getBean("AccessPerfil");
		
		ObjPerfilLogin per = new ObjPerfilLogin();
		per.setId_perfil(id);
		per.setNombre_perfil(nombre);
		per.setEs_admin(admin);
		per.setEstado_perfil(estado);
		per.setCodigo_perfil(codigo);
		per.setId_cliente(1);
		
		int result_action = perfiles.updatePerfilUser(per);
		 
		 result.setResult(result_action);
		 result.setText("Perfil <strong>"+ nombre + "</strong> a sido Actualizado en el sistema.");
		 
		 return result;
		 
	}
	
	@RequestMapping(value = "/deletePerfilLogin", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt deletePerfilLogin(
											  @RequestParam("id") int id,
											  @RequestParam("nombre") String nombre
											  )
	{
		ObjGeneralResultInt result =  new ObjGeneralResultInt();
		 
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessPerfil perfiles = (AccessPerfil) context.getBean("AccessPerfil");
		
		int result_action = perfiles.deletePerfilUser(id);
		 
		 result.setResult(result_action);
		 result.setText("Perfil <strong>"+ nombre + "</strong> a sido Eliminado del sistema.");
		 
		 return result;
		 
	}
	
	@RequestMapping(value = "/getListAccessDivPerfilById/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjDataAccessSubDiv getListAccessDivPerfilById(@PathVariable("id") int id){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessPerfil perfils = (AccessPerfil) context.getBean("AccessPerfil");
		
		ObjDataAccessSubDiv result = new ObjDataAccessSubDiv();
		
		ArrayList<ObjPermisosSubDivisionPerfil> list = perfils.getListSubDivisionesPermisosPerfil(id);
		result.setData(list);
		
		return result;
	}
	
}
