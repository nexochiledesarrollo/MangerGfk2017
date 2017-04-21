package cl.nexo.manager.controller.rest.cliente;

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





import cl.nexo.manager.access.cliente.AccessCliente;
import cl.nexo.manager.obj.cliente.ObjCliente;
import cl.nexo.manager.obj.tools.ObjComboSelect2ValueInt;

@RestController
@RequestMapping("/RestCliente")
public class RestCliente {
	private static final Logger logger = Logger.getLogger(RestCliente.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("yyyyMMddHHmmss");
	
	@RequestMapping(value = "/getCombo", method = RequestMethod.GET,headers="Accept=application/json")
	public ArrayList<ObjComboSelect2ValueInt> getCombo(@RequestParam("id") int id,
													   @RequestParam("place") String place)
	{
		 ArrayList<ObjComboSelect2ValueInt> combo =  new ArrayList<ObjComboSelect2ValueInt>();
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessCliente combos = (AccessCliente) context.getBean("AccessCliente");
		 
		 combo = combos.getCombo(id, place);
		 
		 return combo;
		 
	}
	@RequestMapping(value = "/getClienteById", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjCliente getClienteById(@RequestParam("id") int id)
	{
		 ObjCliente result =  new ObjCliente();
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessCliente combos = (AccessCliente) context.getBean("AccessCliente");
		 
		 result = combos.getClienteById(id);
		 
		 return result;
		 
	}

}
