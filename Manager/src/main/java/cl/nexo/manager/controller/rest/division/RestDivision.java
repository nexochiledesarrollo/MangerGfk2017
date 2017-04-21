package cl.nexo.manager.controller.rest.division;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import cl.nexo.manager.access.division.AccessDivision;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;




@RestController
@RequestMapping("/RestServiceDivision")
public class RestDivision {
	
	private static final Logger logger = Logger.getLogger(RestDivision.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");

	
	@RequestMapping(value = "/getCombo", method = RequestMethod.GET,headers="Accept=application/json")
	public ArrayList<ObjComboSelectValueInt> getCombo()
	{
		 ArrayList<ObjComboSelectValueInt> combo =  new ArrayList<ObjComboSelectValueInt>();
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessDivision combos = (AccessDivision) context.getBean("AccessDivision");
		 
		 combo = combos.getComboDivision();
		 
		 return combo;
		 
	}
	
	@RequestMapping(value = "/getSubDivCombo", method = RequestMethod.GET,headers="Accept=application/json")
	public ArrayList<ObjComboSelectValueInt> getSubDivCombo(@RequestParam("div") int div)
	{
		 ArrayList<ObjComboSelectValueInt> combo =  new ArrayList<ObjComboSelectValueInt>();
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessDivision combos = (AccessDivision) context.getBean("AccessDivision");
		 
		 combo = combos.getComboSubDivision(div);
		 
		 return combo;
		 
	}
}
