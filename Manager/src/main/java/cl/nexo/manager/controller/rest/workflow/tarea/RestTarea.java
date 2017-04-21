package cl.nexo.manager.controller.rest.workflow.tarea;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.nexo.manager.access.proyecto.AccessEstudio;
import cl.nexo.manager.access.tarea.AccessTarea;
import cl.nexo.manager.obj.nota.ObjNota;
import cl.nexo.manager.obj.proyecto.ObjDataListEstudio;
import cl.nexo.manager.obj.proyecto.ObjEstudio;
import cl.nexo.manager.obj.proyecto.ObjEstudioFiltros;
import cl.nexo.manager.obj.tarea.ObjDataTarea;
import cl.nexo.manager.obj.tarea.ObjTarea;
import cl.nexo.manager.obj.tools.ObjGeneralResultInt;
import cl.nexo.manager.obj.traza.ObjDataListTraza;



@RestController
@RequestMapping("/RestTarea")
public class RestTarea {
	private static final Logger logger = Logger.getLogger(RestTarea.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
/////////////////////////////WORKFLOW//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	@RequestMapping(value = "/getListTarea", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjDataTarea getListTarea(@RequestParam("idUser") int idUser,
											  @RequestParam("lang") String lang)
	{
		ObjDataTarea est = new ObjDataTarea();
		 ArrayList<ObjTarea> list =  new ArrayList<ObjTarea>();
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessTarea tareas = (AccessTarea) context.getBean("AccessTarea");
		 logger.debug("**********REQUEST PARAM FILTRO GET LIST TAREA********************************************* "); 
		 	logger.debug("idUser: "+ idUser);
		 	logger.debug("lang: "+ lang);
		 logger.debug("**********END REQUEST PARAM FILTRO GET LIST TAREA********************************************* ");
		 		 
		 list = tareas.getListTareasDashboard(idUser, lang);
		 
		 est.setData(list);
		 
		 return est;
		 
	}
/////////////////////////////NOTAS/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value = "/getListNota", method = RequestMethod.GET,headers="Accept=application/json")
	public ArrayList<ObjNota> getListNota(@RequestParam("idUser") int idUser)
	{
		 ArrayList<ObjNota> est = new ArrayList<ObjNota>();
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessTarea tareas = (AccessTarea) context.getBean("AccessTarea");
		 logger.debug("**********REQUEST PARAM FILTRO GET LIST NOTA********************************************* "); 
		 	logger.debug("idUser: "+ idUser);
		 	
		 logger.debug("**********END REQUEST PARAM FILTRO GET LIST NOTA********************************************* ");
		 		 
		 est = tareas.getListTareasPropias(idUser);
		 
		 
		 
		 return est;
		 
	}
	
	@RequestMapping(value = "/deleteNota", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt deleteNota(@RequestParam("id") int id)
	{
		 ObjGeneralResultInt result = new ObjGeneralResultInt();
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessTarea tareas = (AccessTarea) context.getBean("AccessTarea");
		 logger.debug("**********REQUEST PARAM FILTRO deleteNota********************************************* "); 
		 	logger.debug("id: "+ id);
		 	
		 logger.debug("**********END REQUEST PARAM FILTRO deleteNota********************************************* ");
		 		 
		 tareas.deleteTareaPropia(id);
		 
		 result.setResult(1);
		 result.setText("Se ha eliminado nota id: "+ id);
		 
		 
		 return result;
		 
	}
	@RequestMapping(value = "/setNota", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt setNota(@RequestParam("id") int id,
									   @RequestParam("nota") String nota)
	{
		 ObjGeneralResultInt result = new ObjGeneralResultInt();
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessTarea tareas = (AccessTarea) context.getBean("AccessTarea");
		 logger.debug("**********REQUEST PARAM FILTRO deleteNota********************************************* "); 
		 	logger.debug("id: "+ id);
		 	logger.debug("nota: "+ nota);
		 	
		 logger.debug("**********END REQUEST PARAM FILTRO deleteNota********************************************* ");
		 
		 String fechaNow = format3.format(new Date());
		 ObjNota not = new ObjNota();
		 not.setId_user(id);
		 not.setTarea_nota(nota);
		 not.setFecha_nota(fechaNow);
		 
		 tareas.setTareaPropia(not);
		 
		 result.setResult(1);
		 result.setText("Se ha Agregado Nota id: "+ id);
		 
		 
		 return result;
		 
	}
}
