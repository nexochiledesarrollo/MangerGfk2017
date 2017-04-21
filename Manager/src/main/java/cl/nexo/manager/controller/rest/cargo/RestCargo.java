package cl.nexo.manager.controller.rest.cargo;

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

import cl.nexo.manager.access.cargo.AccessCargo;
import cl.nexo.manager.access.division.AccessDivision;
import cl.nexo.manager.controller.rest.division.RestDivision;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;

@RestController
@RequestMapping("/RestServiceCargo")
public class RestCargo {
	private static final Logger logger = Logger.getLogger(RestDivision.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");

	
	@RequestMapping(value = "/getCombo", method = RequestMethod.GET,headers="Accept=application/json")
	public ArrayList<ObjComboSelectValueInt> getCombo(@RequestParam("div") int div)
	{
		 ArrayList<ObjComboSelectValueInt> combo =  new ArrayList<ObjComboSelectValueInt>();
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessCargo combos = (AccessCargo) context.getBean("AccessCargo");
		 
		 combo = combos.getCombo(div);
		 
		 return combo;
		 
	}
}
