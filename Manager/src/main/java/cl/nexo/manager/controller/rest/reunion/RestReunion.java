package cl.nexo.manager.controller.rest.reunion;





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

import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.manejoworkflow.ManejoWorkflowAccess;
import cl.nexo.manager.access.proyecto.AccessEstudio;
import cl.nexo.manager.access.reunion.AccessReunion;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.reunion.ObjReunionKickOff;
import cl.nexo.manager.obj.tools.ObjGeneralResultInt;


@RestController
@RequestMapping("/RestReunion")
public class RestReunion {

	private static final Logger logger = Logger.getLogger(RestReunion.class);

	@RequestMapping(value = "/aceptarReunion", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt aceptarReunion(
			@RequestParam("id_operacion") int id_operacion,
			@RequestParam("f_ini_campo") String f_ini_campo,
			@RequestParam("f_fin_campo") String f_fin_campo,
			@RequestParam("f_ini_bbdd") String f_ini_bbdd,
			@RequestParam("f_fin_bbdd") String f_fin_bbdd,
			@RequestParam("f_entrega") String f_entrega,
			@RequestParam("id_director") int id_director,
			@RequestParam("id_jefe") int id_jefe,
			@RequestParam("id_procesamiento") int id_procesamiento,
			@RequestParam("id_scripting") int id_scripting

			){
		//--------BEGIN debug ----------------------------
		
		//--------END debug ----------------------------

		logger.info("ENTRO A ACEPTAR LA REUNION");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName());
	    ObjGeneralResultInt result = new ObjGeneralResultInt();
	    
	    ManejoWorkflowAccess agendar = (ManejoWorkflowAccess) context.getBean("ManejoWorkflowAccess");
	    AccessReunion reunion = (AccessReunion) context.getBean("AccessReunion");
	    AccessEstudio est = (AccessEstudio) context.getBean("AccessEstudio");
	    
	    ObjReunionKickOff re = new ObjReunionKickOff();
	    
	    re.setId_operacion(id_operacion);
	    re.setF_ini_campo(f_ini_campo);
	    re.setF_fin_campo(f_fin_campo);
	    re.setF_ini_bbdd(f_ini_bbdd);
	    re.setF_fin_bbdd(f_fin_bbdd);
	    re.setF_entrega(f_entrega);
	    re.setDirector_campo(logins.getUserById(id_director));
	    re.setJefe_calidad(logins.getUserById(id_jefe));
	    re.setProcesamiemto(logins.getUserById(id_procesamiento));
	    re.setScripting(logins.getUserById(id_scripting));
	    
	    reunion.aceptarReunion(re);
	    

        //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        int nuevo_estado=11;
        int actividad = 21;  // debe corresponder al id de la tarea de Reunion
        int id_workFlow;
        String observacion="REUNION ACEPTADA";
        int usuario=user.getId_user();
        //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        
        
        int nueva_cola_estudio=22; // Cola Pendiente KickOff    	
    	est.updateColaEstudio(nueva_cola_estudio, id_operacion);
        
    	
	    //REGISTRO DE WORKFLOW Y BITACORA
	      agendar.genericWorkActividad(id_operacion, actividad, observacion, nuevo_estado, usuario);  		

		result.setResult(1);
		result.setText("<strong>Reunion Aceptada con exito</strong> ");
		
		return result;
		
	}	
	
	@RequestMapping(value = "/rechazarReunion", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt rechazarReunion(
			@RequestParam("id_operacion") int id_operacion,
			@RequestParam("f_ini_campo") String f_ini_campo,
			@RequestParam("f_fin_campo") String f_fin_campo,
			@RequestParam("f_ini_bbdd") String f_ini_bbdd,
			@RequestParam("f_fin_bbdd") String f_fin_bbdd,
			@RequestParam("f_entrega") String f_entrega,
			@RequestParam("id_director") int id_director,
			@RequestParam("id_jefe") int id_jefe,
			@RequestParam("id_procesamiento") int id_procesamiento,
			@RequestParam("id_scripting") int id_scripting

			){
		//--------BEGIN debug ----------------------------
		
		//--------END debug ----------------------------

		logger.info("ENTRO A RECHAZAR LA REUNION");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName());
	    ObjGeneralResultInt result = new ObjGeneralResultInt();
	    
	    ManejoWorkflowAccess agendar = (ManejoWorkflowAccess) context.getBean("ManejoWorkflowAccess");
	    AccessEstudio est = (AccessEstudio) context.getBean("AccessEstudio");
	    

        //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        int nuevo_estado=13;
        int actividad = 21;  // debe corresponder al id de la tarea de Reunion
        int id_workFlow;
        String observacion="REUNION RECHAZADA";
        int usuario=user.getId_user();
        //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        
        
        int nueva_cola_estudio=20; // Cola Pendiente KickOff    	
    	est.updateColaEstudio(nueva_cola_estudio, id_operacion);
        
    	
	    //REGISTRO DE WORKFLOW Y BITACORA
	      agendar.genericWorkActividad(id_operacion, actividad, observacion, nuevo_estado, usuario);  		

		result.setResult(1);
		result.setText("<strong>Reunion Rechazada</strong> ");
		
		return result;
		
	}	
	
	
	
	

	
	
}
