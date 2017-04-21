package cl.nexo.manager.controller.rest.traza;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.nexo.manager.access.proyecto.AccessEstudio;
import cl.nexo.manager.access.traza.AccessTraza;
import cl.nexo.manager.obj.proyecto.ObjDataListEstudio;
import cl.nexo.manager.obj.proyecto.ObjEstudio;
import cl.nexo.manager.obj.proyecto.ObjEstudioFiltros;
import cl.nexo.manager.obj.traza.ObjDataListTraza;
import cl.nexo.manager.obj.traza.ObjTrazaManagerEdit;



@RestController
@RequestMapping("/RestTraza")
public class RestTraza {
	private static final Logger logger = Logger.getLogger(RestTraza.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");


	@RequestMapping(value = "/getListTrazaByIdOp", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjDataListTraza getListTrazaByIdOp(@RequestParam("id") int id)
	{
		ObjDataListTraza trz = new ObjDataListTraza();
		 ArrayList<ObjTrazaManagerEdit> list =  new ArrayList<ObjTrazaManagerEdit>();
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessTraza lists = (AccessTraza) context.getBean("AccessTraza");
		 logger.debug("**********REQUEST PARAM FILTRO getListTrazaByIdOp********************************************* "); 
		 	logger.debug("Id Operacion: "+ id);
		 	
		 logger.debug("**********END REQUEST PARAM FILTRO getListTrazaByIdOp********************************************* ");
		 
		 list = lists.getListTrazaByIdOp(id);
		 
		 trz.setData(list);
		 
		 return trz;
		 
	}



}
