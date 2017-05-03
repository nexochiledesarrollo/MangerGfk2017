package cl.nexo.manager.controller.rest.agenda;

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
import cl.nexo.manager.constantes.Constantes;
import cl.nexo.manager.obj.agenda.ObjAgenda;
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
							@RequestParam("id_agenda") int agenda_select
			
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
        agenda.setid_agenda(agenda_select);
       
        boolean estaAgendado = agendar.getExistUserAgenda(id_user, agenda_select);
        	
        if (estaAgendado){
       
        	logger.info("NO AGENDO ---- " + estaAgendado);
    		result.setResult(1);
    		result.setText("El Usuario <strong>  " + agenda.getUsuario().getNombre_user() + "</strong> ya se encuenta Agendado ! ");
        }else {
        	
        	logger.info("SI AGENDO ---- " + estaAgendado);
        	agendar.setAgendado(agenda);   
        	result.setResult(1);
    		result.setText("Se Agendo el usuario<strong> " + agenda.getUsuario().getNombre_user() + "</strong> en el sistema! ");
        	
        }

		return result;
		
	}	
	
	
	
	
	@RequestMapping(value = "/createAgenda", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjAgenda createAgenda(
			                @RequestParam("fecha") String fecha,
							@RequestParam("lugar") String lugar,
							@RequestParam("hora") String hora,
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
	    ObjGeneralResultInt result = new ObjGeneralResultInt();
        ObjAgenda agenda = new ObjAgenda();
        
        agenda.setId_usuario(user.getId_user());
        agenda.setFecha(fecha);
        agenda.setLugar(lugar);
        agenda.setHora(hora);
        agenda.setId_operacion(id_oper);
        int id_generado = agendar.createAgenda(agenda);
        agenda.setId_agenda(id_generado);
        
		return agenda;
		
	}	
	
	@RequestMapping(value = "/modificaAgenda", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjAgenda modificaAgenda(
			                @RequestParam("fecha") String fecha,
							@RequestParam("lugar") String lugar,
							@RequestParam("hora") String hora,
							@RequestParam("id_oper") int id_oper,
							@RequestParam("agenda") int agenda_id
						
			
			){

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName());
	    
	    AgendaAccess agendar = (AgendaAccess) context.getBean("AgendaAccess");
	    ObjGeneralResultInt result = new ObjGeneralResultInt();
        ObjAgenda agenda = new ObjAgenda();
        
        agenda.setId_usuario(user.getId_user());
        agenda.setFecha(fecha);
        agenda.setLugar(lugar);
        agenda.setHora(hora);
        agenda.setId_operacion(id_oper);
        agenda.setId_agenda(agenda_id);
        agendar.modificaAgenda(agenda);
        return agenda;
		
	}	
	

	
	@RequestMapping(value = "/getListAgendadosById/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjDataAgenda getListAgendadosById(@PathVariable("id") int id){
	   logger.info("ENTRO A LLENAR");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AgendaAccess agen = (AgendaAccess) context.getBean("AgendaAccess");
		ObjDataAgenda result = new ObjDataAgenda();
		
		ArrayList<ObjPersonaAgenda> list = agen.getListAgendadosByid(id);
        result.setData(list);

		return result;
	}
	
	
	
	@RequestMapping(value = "/deleteAgendado", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt deleteAgendado(
			                @RequestParam("id_user") int id_user,
			              	@RequestParam("id_agenda") int id_agenda
			
			){

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName());
	    
	    AgendaAccess agendar = (AgendaAccess) context.getBean("AgendaAccess");

        ObjGeneralResultInt result = new ObjGeneralResultInt();
        
        agendar.DeleteAgendado(id_user, id_agenda);   
		
        
		result.setResult(1);
		result.setText("<strong>Usuario Eliminado de la Agenda</strong> ");
		
		return result;
		
	}	
	
	
	
	@RequestMapping(value = "/aceptarAgenda", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt aceptarAgenda(@RequestParam("id_oper") int id_oper,
											 @RequestParam("id_agenda") int id_agenda
			){
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
	    AgendaAccess agen = (AgendaAccess) context.getBean("AgendaAccess");
	    
        ObjGeneralResultInt result = new ObjGeneralResultInt();
        agen.aceptarAgenda(id_agenda);
          
        //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        int nuevo_estado=Constantes.Estado_Agenda_enviada;
        int actividad = Constantes.Actividad_Agenda_KickOff ;  // debe corresponder al id de la tarea de agendado
        int id_workFlow;
        String observacion="AGENDADO CON EXITO";
        //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        
        //REGISTRO DE WORKFLOW Y BITACORA
        agendar.genericWorkActividad(id_oper, actividad, observacion, nuevo_estado, user.getId_user()); 

	    	
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
	    	
	    	
	    	int nueva_cola_estudio=Constantes.Cola_Pdte_kickOff; // Cola Pendiente KickOff    	
	    	est.updateColaEstudio(nueva_cola_estudio, id_oper);
        
		result.setResult(1);
		result.setText("<strong>Agenda Enviada con exito</strong> ");
		
		return result;
		
	}	


	@RequestMapping(value = "/setAsistencia", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt setAsistencia(
			                @RequestParam("id_user") int id_user,
			              	@RequestParam("id_agenda") int id_agenda,
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
        
        agendar.SetAsistencia(id_user, id_agenda, accion);  
		
        
		result.setResult(1);
		
		if (accion==1){
			result.setText("<strong>Usuario Asistente en la Reunion</strong> ");
		}else{
			result.setText("<strong>Usuario Eliminado de la Agenda</strong> ");
		}
	
		
		return result;
		
	}	
	
	
	
}
