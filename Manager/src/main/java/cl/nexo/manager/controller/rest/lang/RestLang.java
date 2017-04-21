package cl.nexo.manager.controller.rest.lang;

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

import cl.nexo.manager.access.lang.AccessLang;
import cl.nexo.manager.access.tipo.entrevista.AccessTipoEntrevista;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;
import cl.nexo.manager.obj.tools.ObjComboSelectValueString;


@RestController
@RequestMapping("/RestServiceLang")
public class RestLang {
	private static final Logger logger = Logger.getLogger(RestLang.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	@RequestMapping(value = "/getCombo", method = RequestMethod.GET,headers="Accept=application/json")
	public ArrayList<ObjComboSelectValueString> getCombo()
	{
		 ArrayList<ObjComboSelectValueString> combo =  new ArrayList<ObjComboSelectValueString>();
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessLang combos = (AccessLang) context.getBean("AccessLang");
		 
		 combo = combos.getCombo();
		 
		 return combo;
		 
	}
	
}
