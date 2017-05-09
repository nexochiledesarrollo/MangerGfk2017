package cl.nexo.manager.controller.rest.asignaUsuario;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import cl.nexo.manager.constantes.Constantes;

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
import cl.nexo.manager.access.division.AccessDivision;
import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.manejoworkflow.ManejoWorkflowAccess;
import cl.nexo.manager.access.password.AccessPassword;
import cl.nexo.manager.access.proyecto.AccessEstudio;
import cl.nexo.manager.access.reunion.AccessReunion;
import cl.nexo.manager.access.server.mail.AccessServerMail;
import cl.nexo.manager.access.server.mail.plantillas.AccessPlantillasLogin;
import cl.nexo.manager.access.tipo.contrato.AccessTipoContrato;
import cl.nexo.manager.constantes.Constantes;
import cl.nexo.manager.obj.apoderado.ObjDataApoderado;
import cl.nexo.manager.obj.apoderado.ObjListApoderado;
import cl.nexo.manager.obj.login.ObjDataAsignacionUser;
import cl.nexo.manager.obj.login.ObjDataLogin;
import cl.nexo.manager.obj.login.ObjListManUser;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.login.ObjLoginUserHoras;
import cl.nexo.manager.obj.reunion.ObjReunionKickOff;
import cl.nexo.manager.obj.tools.ObjComboSelect2ValueInt;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;
import cl.nexo.manager.obj.tools.ObjComboSelectValueString;
import cl.nexo.manager.obj.tools.ObjGeneralResultInt;



@RestController
@RequestMapping("/RestAsignaUsuario")
public class RestAsignaUsuario {
	
	private static final Logger logger = Logger.getLogger(RestAsignaUsuario.class);
	
	@RequestMapping(value = "/getUsuariosTotalHoras/{desde}/{hasta}/{div}/{subD}", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjDataAsignacionUser getUsuariosTotalHoras(@PathVariable("desde") String desde,
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
	
	
	
	@RequestMapping(value = "/cargaComboRangoFechas", method = RequestMethod.GET,headers="Accept=application/json")
	public ArrayList<ObjComboSelectValueString> cargaComboRangoFechas(
			@RequestParam("desde") String desde,
			@RequestParam("hasta") String hasta
	){
		
	
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessAsignacionPersonal asigna = (AccessAsignacionPersonal) context.getBean("AccessAsignacionPersonal");
		
		
		  ArrayList<ObjComboSelectValueString> combo =  new ArrayList<ObjComboSelectValueString>();
	        
		  combo = asigna.getListRangoFechas(desde, hasta);
		  
		
		return combo;
      
        
	}
	
	
	@RequestMapping(value = "/buscaHorasDipsUserDia", method = RequestMethod.GET,headers="Accept=application/json")
	public int buscaHorasDipsUserDia(@RequestParam("dia") String dia,
									 @RequestParam("user") int user){
		
	
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessAsignacionPersonal asigna = (AccessAsignacionPersonal) context.getBean("AccessAsignacionPersonal");
	
	        
		int horasDia = asigna.buscaHorasDipsUserDia(dia,user);
		   
		
	    return horasDia;
      
        
	}
	
	
	@RequestMapping(value = "/buscaHorasDipsUserDiaEstudio", method = RequestMethod.GET,headers="Accept=application/json")
	public int buscaHorasDipsUserDiaEstudio(@RequestParam("dia") String dia,
									 @RequestParam("user") int user,
									 @RequestParam("estudio") int estudio){
		
	
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessAsignacionPersonal asigna = (AccessAsignacionPersonal) context.getBean("AccessAsignacionPersonal");
	
	        
		int horasDia = asigna.buscaHorasDipsUserDiaEstudio(dia,user,estudio);
		   
		
	    return horasDia;
      
        
	}
	
	
	
	
	
	
	@RequestMapping(value = "/AsignaHorasUserEstudio", method = RequestMethod.GET,headers="Accept=application/json")
	public void AsignaHorasUserEstudio(@RequestParam("horas") int horas,
									   @RequestParam("dia") String dia,
									   @RequestParam("estudio") int estudio,
									   @RequestParam("user") int user,
									   @RequestParam("accion") int accion
			){
		

		
	
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessAsignacionPersonal asigna = (AccessAsignacionPersonal) context.getBean("AccessAsignacionPersonal");
	
	    asigna.asignaHorasUserEstudio(horas, dia, estudio, user, accion);

	}

	
	
	@RequestMapping(value = "/getAsignadosEstudio/{estudio}", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjDataAsignacionUser getAsignadosEstudio(@PathVariable("estudio") int estudio){
		
	
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessAsignacionPersonal asigna = (AccessAsignacionPersonal) context.getBean("AccessAsignacionPersonal");
		
		ObjDataAsignacionUser result = new ObjDataAsignacionUser();
	
	        
		   ArrayList<ObjLoginUserHoras> list = asigna.getAsignadosEstudio(estudio);
		   logger.info("LISTA DE USURIOS "  + list);
		   result.setData(list);
		
		return result;
      
        
	}
	
	@RequestMapping(value = "/getAsignadosEstudioDias/{estudio}/{usuario}", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjDataAsignacionUser getAsignadosEstudioDias(@PathVariable("estudio") int estudio,
			                                             @PathVariable("usuario") int usuario){
		
	
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessAsignacionPersonal asigna = (AccessAsignacionPersonal) context.getBean("AccessAsignacionPersonal");
		
		ObjDataAsignacionUser result = new ObjDataAsignacionUser();
	
	        
		   ArrayList<ObjLoginUserHoras> list = asigna.getAsignadosEstudioDias(estudio,usuario);
		   
		   result.setData(list);
		
		return result;
      
        
	}
	
	
	@RequestMapping(value = "/aceptarAsignacion", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt aceptarReunion(
			@RequestParam("id_oper") int id_operacion
			){
		//--------BEGIN debug ----------------------------
		
		//--------END debug ----------------------------

		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName());
	    ObjGeneralResultInt result = new ObjGeneralResultInt();
	    
	    ManejoWorkflowAccess agendar = (ManejoWorkflowAccess) context.getBean("ManejoWorkflowAccess");
	    AccessEstudio est = (AccessEstudio) context.getBean("AccessEstudio");
	    
	    
        //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	    int nuevo_estado=Constantes.Estado_Asignacion_perso_aceptada;
        int actividad = Constantes.Actividad_Asig_personal;  // debe corresponder al id de la actividad Asignacion de Personal
        String observacion="ASIGNACION DE PERSONAL ACEPTADA";
        int usuario=user.getId_user();
        //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        
        
        int nueva_cola_estudio=0; 
    	
	    //REGISTRO DE WORKFLOW Y BITACORA
	      agendar.genericWorkActividad(id_operacion, actividad, observacion, nuevo_estado, usuario);  
	      
	      
	   int status_import_doc = agendar.buscarStatusActividadEstudio(id_operacion, Constantes.Actividad_Subir_cuestionario);
	   int status_instructivo = agendar.buscarStatusActividadEstudio(id_operacion, Constantes.Actividad_Carga_Instructivo);
	   
	   if ((status_import_doc==Constantes.Estado_ImportDoc_Aceptado) && (status_instructivo==Constantes.Estado_Instructivo_Cargado) ){
		   nueva_cola_estudio=Constantes.Cola_Pdte_agenda_kickOff; /// Pendiente Agenda KickOff
	   }else{
		   nueva_cola_estudio=Constantes.Cola_En_proceso_desarrollo_org; /// En Proceso Desarrollo Materiales y  Organizacion

	   }

	 
	      

	   // Cola en proceso Desarrollo Materiales y Organizacion 	
   	   est.updateColaEstudio(nueva_cola_estudio, id_operacion);
	   
	   
	   
		result.setResult(1);
		result.setText("<strong>Personal Asignado con Exito</strong> ");
		
		return result;
		
	}	
	
	
	
	
	

}
