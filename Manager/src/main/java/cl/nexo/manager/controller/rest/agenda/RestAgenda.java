package cl.nexo.manager.controller.rest.agenda;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import cl.nexo.manager.access.agenda.AgendaAccess;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.manejoworkflow.ManejoWorkflowAccess;
import cl.nexo.manager.access.proyecto.AccessEstudio;
import cl.nexo.manager.access.tarea.AccessTarea;
import cl.nexo.manager.obj.agenda.ObjDataAgenda;
import cl.nexo.manager.obj.agenda.ObjPersonaAgenda;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.tarea.ObjTarea;
import cl.nexo.manager.obj.tools.ObjGeneralResultInt;


@RestController
@RequestMapping("/RestAgenda")
public class RestAgenda {

	private static final Logger logger = Logger.getLogger(RestAgenda.class);

	@RequestMapping(value = "/setAgendado", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt setAgendado(
			                @RequestParam("id_user") int id_user,
			                @RequestParam("fecha") String fecha,
							@RequestParam("lugar") String lugar,
							@RequestParam("id_oper") int id_oper
			
			){
		//--------BEGIN debug ----------------------------
		
		//--------END debug ----------------------------
		
		logger.info("POR AQUI PASO");
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName());
	    
	    AgendaAccess agendar = (AgendaAccess) context.getBean("AgendaAccess");
	    ObjLoginUser userAgenda = logins.getUserById(id_user);
	    ObjGeneralResultInt result = new ObjGeneralResultInt();
        ObjPersonaAgenda agenda = new ObjPersonaAgenda();
        
        agenda.setUsuario(userAgenda);
        agenda.setFecha(fecha);
        agenda.setLugar(lugar);
        agenda.setId_operacion(id_oper);
        
        boolean estaAgendado = agendar.getExistUserAgenda(id_user, id_oper);
        	
        if (estaAgendado){
       
        	logger.info("NO AGENDO ---- " + estaAgendado);
    		result.setResult(1);
    		result.setText("El Usuario <strong>" + agenda.getUsuario().getNombre_user() + "</strong> ya se encuenta Agendado ! ");
        }else {
        	
        	logger.info("SI AGENDO ---- " + estaAgendado);
        	agendar.setAgendado(agenda);   
        	result.setResult(1);
    		result.setText("Se Agendo el usuario<strong>" + agenda.getUsuario().getNombre_user() + "</strong> en el sistema! ");
        	
        }

		return result;
		
	}	
	

	
	@RequestMapping(value = "/getListAgendadosById/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjDataAgenda getListAgendadosById(@PathVariable("id") int id){
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AgendaAccess agen = (AgendaAccess) context.getBean("AgendaAccess");
		ObjDataAgenda result = new ObjDataAgenda();
		
		ArrayList<ObjPersonaAgenda> list = agen.getListAgendadosByidOperacion(id);
		logger.info("LISTADO" + list);
		
		
	     result.setData(list);
		
		
		
		return result;
	}
	
	
	
	@RequestMapping(value = "/deleteAgendado", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt deleteAgendado(
			                @RequestParam("id_user") int id_user,
			              	@RequestParam("id_oper") int id_oper
			
			){

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName());
	    
	    AgendaAccess agendar = (AgendaAccess) context.getBean("AgendaAccess");

        ObjGeneralResultInt result = new ObjGeneralResultInt();
        ObjPersonaAgenda agenda = new ObjPersonaAgenda();
        
        agendar.DeleteAgendado(id_user, id_oper);   
		
        
		result.setResult(1);
		result.setText("<strong>Usuario Eliminado de la Agenda</strong> ");
		
		return result;
		
	}	
	
	
	
	@RequestMapping(value = "/aceptarAgenda", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt aceptarAgenda(@RequestParam("id_oper") int id_oper){
		//--------BEGIN debug ----------------------------
		
		//--------END debug ----------------------------

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName());
	    
	    ManejoWorkflowAccess agendar = (ManejoWorkflowAccess) context.getBean("ManejoWorkflowAccess");
	    AccessTarea tareas = (AccessTarea) context.getBean("AccessTarea");
	    AccessEstudio est = (AccessEstudio) context.getBean("AccessEstudio");
	    
        ObjGeneralResultInt result = new ObjGeneralResultInt();
             
        //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        int nuevo_estado=11;
        int actividad = 19;  // debe corresponder al id de la tarea de agendado
        int id_workFlow;
        String observacion="AGENDADO CON EXITO";
        //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        
        ///Buscar si Existe el Registro de WorkFlow para esa Actividad
        id_workFlow= agendar.buscarIdWorkPorActividad(id_oper, actividad);
        
        if (id_workFlow==0){ /// no existe 
            // Inicio Workflow para esa actividad
        	agendar.createWorkActividad(id_oper, actividad, nuevo_estado, user.getId_user());
        	 // Busco el Nuevo Codigo
        	id_workFlow= agendar.buscarIdWorkPorActividad(id_oper, actividad);
        }
        
        
	        // Regstro el Ultimo Estado
	    	agendar.SetUltimoEstado(id_oper, actividad, nuevo_estado, user.getId_user()); 
	    	// Resgistro la Bitacora
	    	agendar.SetBitacoraActividad(id_workFlow, observacion, user.getId_user());
	    	
	    	ObjTarea tarea = new ObjTarea();

	    	tarea.setId_operacion(id_oper);
	    	tarea.setId_actividad(actividad);
	    	tarea.setUser_asigna(user.getId_user());
	    	tarea.setId_user(2); /// ----------------------------------- &&&&
	    	tarea.setTipo_asignacion(1);
	    	tarea.setGrupo_asignacion(1);
	    	tarea.setFecha_inicio("");
	    	tarea.setFecha_fin("");
	    	tarea.setEstado_tarea(1);
	    	tarea.setAsunto_tarea("Agenda Kick Off");
	    	tarea.setAsunto_tarea("Descripcion Tarea");
	    	tarea.setTipo_tarea(1);
	    	
	    	// Registro la Tarea
	    	tareas.setTarea(tarea);
	    	
	    	
	    	int nueva_cola_estudio=20; // Cola Pendiente KickOff    	
	    	est.updateColaEstudio(nueva_cola_estudio, id_oper);
        
		result.setResult(1);
		result.setText("<strong>Agenda Enviada con exito</strong> ");
		
		return result;
		
	}	
	
	
	
	
	@RequestMapping(value = "/rechazarAgenda", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt rechazarAgenda(@RequestParam("id_oper") int id_oper){
		//--------BEGIN debug ----------------------------
		
		//--------END debug ----------------------------

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName());
	    
	    ManejoWorkflowAccess agendar = (ManejoWorkflowAccess) context.getBean("ManejoWorkflowAccess");
	    AccessTarea tareas = (AccessTarea) context.getBean("AccessTarea");
	    
	    
        ObjGeneralResultInt result = new ObjGeneralResultInt();
             
        //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        int nuevo_estado=16; /// debe corresponder al ESTADO RECHAZADO
        int actividad = 5;  // debe corresponder al id de la tarea de agendado
        int id_workFlow;
        String observacion="AGENDA RECHAZADA";
        int usuario= user.getId_user();
        //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        
        //REGISTRO DE WORKFLOW Y BITACORA
        agendar.genericWorkActividad(id_oper, actividad, observacion, nuevo_estado, usuario);  	
	    	
        
		result.setResult(1);
		result.setText("<strong>Agenda Rechazada</strong> ");
		
		return result;
		
	}	
	
	
	
	
	
	
	
	@RequestMapping(value = "/setAsistencia", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt setAsistencia(
			                @RequestParam("id_user") int id_user,
			              	@RequestParam("id_oper") int id_oper,
			              	@RequestParam("accion") int accion
			){

		
		logger.info("ENTRO A REGISTRAR ASISTENCIA");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName());
	    
	    AgendaAccess agendar = (AgendaAccess) context.getBean("AgendaAccess");

        ObjGeneralResultInt result = new ObjGeneralResultInt();
        
        agendar.SetAsistencia(id_user, id_oper, accion);  
		
        
		result.setResult(1);
		
		if (accion==1){
			result.setText("<strong>Usuario Asistente en la Reunion</strong> ");
		}else{
			result.setText("<strong>Usuario Eliminado de la Agenda</strong> ");
		}
	
		
		return result;
		
	}	
	
	
	
}
