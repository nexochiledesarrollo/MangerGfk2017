package cl.nexo.manager.controller.rest.tool;

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

import cl.nexo.manager.access.combo.box.AccessComboBox;
import cl.nexo.manager.access.tipo.entrevista.AccessTipoEntrevista;
import cl.nexo.manager.obj.tools.ObjComboSelect2ValueInt;
import cl.nexo.manager.obj.tools.ObjComboSelect2ValueString;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;



@RestController
@RequestMapping("/RestToolComboBox")
public class RestToolComboBox {
	private static final Logger logger = Logger.getLogger(RestToolComboBox.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");

	@RequestMapping(value = "/getCombo", method = RequestMethod.GET,headers="Accept=application/json")
	public ArrayList<ObjComboSelect2ValueString> getCombo(@RequestParam("id") int id,
													  @RequestParam("lang") String lang,
													  @RequestParam("depende")int depende,
													  @RequestParam("place") String place,
													  @RequestParam("comboDepende") int comboDepende)
	{
		 ArrayList<ObjComboSelect2ValueString> combo =  new ArrayList<ObjComboSelect2ValueString>();
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessComboBox combos = (AccessComboBox) context.getBean("AccessComboBox");
		 
		 combo = combos.getCombo(id,lang,depende,place,comboDepende);
		 
		 return combo;
		 
	}
	
	@RequestMapping(value = "/getValueComboDetalle", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjComboSelect2ValueInt getValueComboDetalle(@RequestParam("id") String id,
			 		                   @RequestParam("combo") int combo,
			 		                   @RequestParam("tipo") int tipo,
			 		                   @RequestParam("lang") String lang)
	{
		 ObjComboSelect2ValueInt combo2 =  new ObjComboSelect2ValueInt();
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 AccessComboBox combos = (AccessComboBox) context.getBean("AccessComboBox");
		 
		 combo2 = combos.getValueComboDetalle(id, combo, lang);
		 
		 return combo2;
		 
	}
}
