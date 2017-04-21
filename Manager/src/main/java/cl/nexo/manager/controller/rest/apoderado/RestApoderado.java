package cl.nexo.manager.controller.rest.apoderado;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

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
import org.springframework.web.client.RestTemplate;













import cl.nexo.manager.access.apoderado.ApoderadoAccess;
import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.password.AccessPassword;
import cl.nexo.manager.access.server.mail.AccessServerMail;
import cl.nexo.manager.access.server.mail.plantillas.AccessPlantillasLogin;
import cl.nexo.manager.access.tipo.contrato.AccessTipoContrato;
import cl.nexo.manager.obj.apoderado.ObjDataApoderado;
import cl.nexo.manager.obj.apoderado.ObjListApoderado;
import cl.nexo.manager.obj.login.ObjDataLogin;
import cl.nexo.manager.obj.login.ObjListManUser;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.tools.ObjComboSelect2ValueInt;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;
import cl.nexo.manager.obj.tools.ObjGeneralResultInt;



@RestController
@RequestMapping("/RestApoderado")
public class RestApoderado {
	
	private static final Logger logger = Logger.getLogger(RestApoderado.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("yyyyMMddHHmmss");
	

	
	@RequestMapping(value = "/getListApoderado", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjDataApoderado getListUserByCliente(){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		
		ApoderadoAccess apoderado = (ApoderadoAccess) context.getBean("ApoderadoAccess");
		
		ObjDataApoderado result = new ObjDataApoderado();
		
		ArrayList<ObjListApoderado> list = apoderado.getListApoderados();
		logger.info("----------------" + list);
		
		
	     result.setData(list);
		
		
		
		return result;
	}
	
	
	
	@RequestMapping(value = "/getDetailApoderadoById/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjListApoderado getDetailApoderadoById(@PathVariable("id") int id){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		
		ApoderadoAccess apoderado = (ApoderadoAccess) context.getBean("ApoderadoAccess");
		
		
		ObjListApoderado apd = new ObjListApoderado();
		apd = apoderado.getApoderadoById(id);
		
		logger.info("NOMBRE  " + apd.getNombres());
		
			
		return apd;
		
	}
	
	
	@RequestMapping(value = "/updateApoderado", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt updateUserLogin(
							@RequestParam("cedula") String cedula,
							@RequestParam("nombre") String nombres,
							@RequestParam("apellido") String apellidos,
							@RequestParam("email") String email,
							@RequestParam("cargo") String cargo
							){
		//--------BEGIN debug ----------------------------
		logger.debug("cedula: "+cedula);
		logger.debug("nombres: "+nombres);
		logger.debug("apellidos: "+apellidos);
		logger.debug("cargo: "+ email);
		logger.debug("email: "+ cargo);
		
		//--------END debug ----------------------------
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		//LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		ApoderadoAccess apoderado = (ApoderadoAccess) context.getBean("ApoderadoAccess");
		    
		//AccessServerMail serverMail = (AccessServerMail) context.getBean("AccessServerMail");
    	//AccessPlantillasLogin bodyTemplate = (AccessPlantillasLogin) context.getBean("AccessPlantillasLogin");
		//AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		//AccessPassword genPass = (AccessPassword) context.getBean("AccessPassword");
		//SecurityContext securityContext = SecurityContextHolder.getContext();
	    //Authentication authentication = securityContext.getAuthentication();
	    //ObjLoginUser user = logins.getUserByLogin(authentication.getName());
	    
	    ObjListApoderado apd =new ObjListApoderado();  
	    
	    apd.setCedula(cedula);
	    apd.setNombres(nombres);
	    apd.setApellido(apellidos);
	    apd.setCargo(cargo);
	    apd.setEmail(email);
	    
	    apoderado.updateApoderado(apd);
	   
	    ObjGeneralResultInt result = new ObjGeneralResultInt();
		
		
		result.setResult(1);
		result.setText("Usuario <strong>"+nombres+"</strong> a sido <strong>ACTUALIZADO</strong> en el sistema! ");
		
		return result;
		
	}
	
	@RequestMapping(value = "/deleteApoderado", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt deleteUserLogin(
							@RequestParam("cedula") String cedula
							
							){
		//--------BEGIN debug ----------------------------
		logger.debug("cedula: "+cedula);
		
		//--------END debug ----------------------------
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessServerMail serverMail = (AccessServerMail) context.getBean("AccessServerMail");
    	AccessPlantillasLogin bodyTemplate = (AccessPlantillasLogin) context.getBean("AccessPlantillasLogin");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		AccessPassword genPass = (AccessPassword) context.getBean("AccessPassword");
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName());
    
	    ObjGeneralResultInt result = new ObjGeneralResultInt();
		
		ApoderadoAccess apoderado = (ApoderadoAccess) context.getBean("ApoderadoAccess");
		
		apoderado.deleteApoderado(cedula);

		
		result.setResult(1);
		result.setText("Usuario <strong>"+cedula+"</strong> a sido <strong>ELIMINADO</strong> en el sistema! ");
		
		return result;
		
	}
	

	
	@RequestMapping(value = "/setApoderado", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt setUserLogin(@RequestParam("cedula") String cedula,
							@RequestParam("nombre") String nombres,
							@RequestParam("apellido") String apellidos,
							@RequestParam("email") String email,
							@RequestParam("cargo") String cargo
							
							){
		//--------BEGIN debug ----------------------------
		
		//--------END debug ----------------------------
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessServerMail serverMail = (AccessServerMail) context.getBean("AccessServerMail");
    	AccessPlantillasLogin bodyTemplate = (AccessPlantillasLogin) context.getBean("AccessPlantillasLogin");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		AccessPassword genPass = (AccessPassword) context.getBean("AccessPassword");
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName());
	    
	    
	    
	    ObjGeneralResultInt result = new ObjGeneralResultInt();
		
        ObjListApoderado apd =new ObjListApoderado();  
        ApoderadoAccess apoderado = (ApoderadoAccess) context.getBean("ApoderadoAccess");
        
        
	    apd.setCedula(cedula);
	    apd.setNombres(nombres);
	    apd.setApellido(apellidos);
	    apd.setCargo(cargo);
	    apd.setEmail(email);
	    
	    apoderado.crearApoderado(apd);   
		
		result.setResult(1);
		result.setText("Usuario <strong>"+nombres+"</strong> a sido <strong>CREADO</strong> en el sistema! ");
		
		return result;
		
	}	
	
	
	
	
	
	
	
	
	
	
	
}
