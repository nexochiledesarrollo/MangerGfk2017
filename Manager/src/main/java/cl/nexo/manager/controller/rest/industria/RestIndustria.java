package cl.nexo.manager.controller.rest.industria;

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
import cl.nexo.manager.access.industria.AccessIndustria;
import cl.nexo.manager.controller.rest.division.RestDivision;
import cl.nexo.manager.obj.tools.ObjComboSelect2GroupValueInt;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;


@RestController
@RequestMapping("/RestIndustria")
public class RestIndustria {
	private static final Logger logger = Logger.getLogger(RestIndustria.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	@RequestMapping(value = "/getCombo", method = RequestMethod.GET,headers="Accept=application/json")
	public ArrayList<ObjComboSelect2GroupValueInt> getCombo(@RequestParam("lang") String lang,
															@RequestParam("place") String place)
	{
		 ArrayList<ObjComboSelect2GroupValueInt> combo =  new ArrayList<ObjComboSelect2GroupValueInt>();
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessIndustria combos = (AccessIndustria) context.getBean("AccessIndustria");
		 
		 combo = combos.getIndustria(lang, place);
		 
		 return combo;
		 
	}
}
