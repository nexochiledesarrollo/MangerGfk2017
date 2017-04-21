package cl.nexo.manager.controller.rest.tipo.contrato;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cl.nexo.manager.access.area.AccessArea;
import cl.nexo.manager.access.tipo.contrato.AccessTipoContrato;
import cl.nexo.manager.controller.rest.area.RestArea;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;

@RestController
@RequestMapping("/RestServiceTipoContrato")
public class RestTipoContrato {
	private static final Logger logger = Logger.getLogger(RestArea.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");

	
	@RequestMapping(value = "/getCombo", method = RequestMethod.GET,headers="Accept=application/json")
	public ArrayList<ObjComboSelectValueInt> getCombo()
	{
		 ArrayList<ObjComboSelectValueInt> combo =  new ArrayList<ObjComboSelectValueInt>();
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessTipoContrato combos = (AccessTipoContrato) context.getBean("AccessTipoContrato");
		 
		 combo = combos.getCombo();
		 
		 return combo;
		 
	}
}
