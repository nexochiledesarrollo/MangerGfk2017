package cl.nexo.manager.controller.otp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.obj.login.ObjLoginUser;



@Controller
@RequestMapping("/otpService")
public class OtpController {
	private static final Logger logger = Logger.getLogger(OtpController.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("yyyyMMddHHmmss");
	
	@RequestMapping(value = { "/activeUser/{code}" }, method = RequestMethod.GET)
	public ModelAndView  activeUser( @PathVariable("code") String code) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    LoginAccess login = (LoginAccess) context.getBean("LoginAccess");
	    ObjLoginUser loginUser = login.getUserByActiveCode(code);
	    
	    logger.info("Se consulta activacion de cuenta retornando id: "+ loginUser.getId_user());
	    
	    int fechaAhora = Integer.parseInt(format1.format(new Date()));
		String horaAhora = format2.format(new Date());
		
		ModelAndView model = new ModelAndView();
		
		if(loginUser.getId_user() == 0){
			
			model.setViewName("login");
		
		}else{
			
			model.addObject("user",loginUser);
			model.setViewName("activeUser");
			
		}
		return model;
	}
	
}
