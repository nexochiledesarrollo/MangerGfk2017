package cl.nexo.manager.controller.rest.setup;

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

import cl.nexo.manager.access.login.AccessPerfil;
import cl.nexo.manager.access.sector.AccessSector;
import cl.nexo.manager.access.setup.AccessSetUp;
import cl.nexo.manager.controller.rest.sector.RestSector;
import cl.nexo.manager.obj.setup.ObjComboDetalle;
import cl.nexo.manager.obj.setup.ObjSetUpBaseGen;
import cl.nexo.manager.obj.tools.ObjComboSelect2ValueInt;
import cl.nexo.manager.obj.tools.ObjGeneralResultInt;

@RestController
@RequestMapping("/RestSetUp")
public class RestSetUp {
	
	private static final Logger logger = Logger.getLogger(RestSetUp.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("yyyyMMddHHmmss");
	
	@RequestMapping(value = "/getComboPadre", method = RequestMethod.GET,headers="Accept=application/json")
	public ArrayList<ObjComboSelect2ValueInt> getCombo(@RequestParam("pace") String pace)
	{
		 ArrayList<ObjComboSelect2ValueInt> combo =  new ArrayList<ObjComboSelect2ValueInt>();
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessSetUp combos = (AccessSetUp) context.getBean("AccessSetUp");
		 
		 combo = combos.getListBase(pace);
		 
		 return combo;
		 
	}
	
	@RequestMapping(value = "/getDetCombo", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjSetUpBaseGen getDetCombo(@RequestParam("lang") String lang,
												  @RequestParam("id") int id)
	{
		ObjSetUpBaseGen res = new ObjSetUpBaseGen(); 
		
		ArrayList<ObjComboDetalle> det =  new ArrayList<ObjComboDetalle>();
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessSetUp combos = (AccessSetUp) context.getBean("AccessSetUp");
		 
		 det = combos.getListDetalle(id, lang);
		 res.setData(det);
		 
		 return res;
		 
	}
	
	@RequestMapping(value = "/deleteDetCombo", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt deletePerfilLogin(@RequestParam("id") int id)
	{
		ObjGeneralResultInt result =  new ObjGeneralResultInt();
		 
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessSetUp setUp = (AccessSetUp) context.getBean("AccessPerfil");
		
		int result_action = setUp.deleteDetalleCombo(id);
		 
		 result.setResult(result_action);
		 result.setText("Option <strong>"+ id + "</strong> a sido Eliminado del sistema.");
		 
		 return result;
		 
	}
}
