package cl.nexo.manager.controller.rest.asignaUsuario;

import java.text.DateFormat;
import java.text.ParseException;
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
import cl.nexo.manager.access.asignacionPersonal.AccessAsignacionPersonal;
import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.password.AccessPassword;
import cl.nexo.manager.access.server.mail.AccessServerMail;
import cl.nexo.manager.access.server.mail.plantillas.AccessPlantillasLogin;
import cl.nexo.manager.access.tipo.contrato.AccessTipoContrato;
import cl.nexo.manager.obj.apoderado.ObjDataApoderado;
import cl.nexo.manager.obj.apoderado.ObjListApoderado;
import cl.nexo.manager.obj.login.ObjDataAsignacionUser;
import cl.nexo.manager.obj.login.ObjDataLogin;
import cl.nexo.manager.obj.login.ObjListManUser;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.login.ObjLoginUserHoras;
import cl.nexo.manager.obj.tools.ObjComboSelect2ValueInt;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;
import cl.nexo.manager.obj.tools.ObjGeneralResultInt;



@RestController
@RequestMapping("/RestAsignaUsuario")
public class RestAsignaUsuario {
	
	private static final Logger logger = Logger.getLogger(RestAsignaUsuario.class);
	
	@RequestMapping(value = "/getUsuariosTotalHoras/{desde}/{hasta}/{div}/{subD}", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjDataAsignacionUser getListUserByCliente(@PathVariable("desde") String desde,
			                                          @PathVariable("hasta") String hasta,
			                                          @PathVariable("div") int div,
			                                          @PathVariable("subD") int subD){
		
	
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessAsignacionPersonal asigna = (AccessAsignacionPersonal) context.getBean("AccessAsignacionPersonal");
		
		ObjDataAsignacionUser result = new ObjDataAsignacionUser();
	
	        
		   ArrayList<ObjLoginUserHoras> list = asigna.getListUserHorasByFechas(desde, hasta, div, subD);
		   result.setData(list);
		
		return result;
      
        
	}
	
	
	
	
	
	
	
	
	
	
	
}
