package cl.nexo.manager.controller.rest.workflow.actividad;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.workflow.AccessWorkflow15;
import cl.nexo.manager.access.workflow.AccessWorkflow5;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.proyecto.ObjDataListEstudio;
import cl.nexo.manager.obj.proyecto.ObjEstudio;

@RestController
@RequestMapping("/RestWorkflow5")
public class RestWorkflow5 {
	private static final Logger logger = Logger.getLogger(RestWorkflow15.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	@RequestMapping(value = "/getListWorkflow", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjDataListEstudio getListWorkflow(@RequestParam("id") int id,
											    @RequestParam("lang") String lang)
	{
		ObjDataListEstudio est = new ObjDataListEstudio();
		ArrayList<ObjEstudio> list =  new ArrayList<ObjEstudio>();
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		SecurityContext securityContext = SecurityContextHolder.getContext();
		Authentication authentication = securityContext.getAuthentication();
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessWorkflow5 lists = (AccessWorkflow5) context.getBean("AccessWorkflow5");
		
		ObjLoginUser user = logins.getUserByLogin(authentication.getName());
		logger.debug("**********REQUEST PARAM FILTRO GET LIST ASIGN********************************************* "); 
		 	logger.debug("id: "+ id);
		 	logger.debug("lang: "+ lang);
		 	logger.debug("user: "+ user.getId_user());
		 	logger.debug("url: http://localhost:8080/Manager/RestWorkflow15/getListWorkflow15?id="+id+"&lang="+lang+ " ");
		 logger.debug("**********END REQUEST PARAM FILTRO GET LIST ASIGN********************************************* ");
		
		 
		 list = lists.getListEstudioByUser(user.getId_user(), lang);
		 
		 est.setData(list);
		 
		 return est;
		 
	}
	
	
}
