package cl.nexo.manager.controller.rest.ubicacion;

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

import cl.nexo.manager.access.division.AccessDivision;
import cl.nexo.manager.access.ubicacion.AccessUbicacion;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;




@RestController
@RequestMapping("/RestServiceComboUbicacion")
public class RestUbicacionCombo {
	
	private static final Logger logger = Logger.getLogger(RestUbicacionCombo.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	@RequestMapping(value = "/getComboPais", method = RequestMethod.GET,headers="Accept=application/json")
	public ArrayList<ObjComboSelectValueInt> getComboPais()
	{
		 ArrayList<ObjComboSelectValueInt> combo =  new ArrayList<ObjComboSelectValueInt>();
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessUbicacion combos = (AccessUbicacion) context.getBean("AccessUbicacion");
		 
		 combo = combos.getPais();
		 
		 return combo;
		 
	}
	
	@RequestMapping(value = "/getComboRegion", method = RequestMethod.GET,headers="Accept=application/json")
	public ArrayList<ObjComboSelectValueInt> getComboRegion(@RequestParam("pais") int pais)
	{
		 ArrayList<ObjComboSelectValueInt> combo =  new ArrayList<ObjComboSelectValueInt>();
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessUbicacion combos = (AccessUbicacion) context.getBean("AccessUbicacion");
		 
		 combo = combos.getRegion(pais);
		 
		 return combo;
		 
	}
	
	@RequestMapping(value = "/getComboCiudad", method = RequestMethod.GET,headers="Accept=application/json")
	public ArrayList<ObjComboSelectValueInt> getComboCiudad(@RequestParam("region") int region)
	{
		 ArrayList<ObjComboSelectValueInt> combo =  new ArrayList<ObjComboSelectValueInt>();
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessUbicacion combos = (AccessUbicacion) context.getBean("AccessUbicacion");
		 
		 combo = combos.getCiudad(region);
		 
		 return combo;
		 
	}
	
	@RequestMapping(value = "/getComboComuna", method = RequestMethod.GET,headers="Accept=application/json")
	public ArrayList<ObjComboSelectValueInt> getComboComuna(@RequestParam("ciudad") int ciudad)
	{
		 ArrayList<ObjComboSelectValueInt> combo =  new ArrayList<ObjComboSelectValueInt>();
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessUbicacion combos = (AccessUbicacion) context.getBean("AccessUbicacion");
		 
		 combo = combos.getComuna(ciudad);
		 
		 return combo;
		 
	}
	
	
}
