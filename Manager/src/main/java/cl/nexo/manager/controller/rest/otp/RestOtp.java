package cl.nexo.manager.controller.rest.otp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

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

import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.password.AccessPassword;
import cl.nexo.manager.access.server.mail.AccessServerMail;
import cl.nexo.manager.access.server.mail.plantillas.AccessPlantillasLogin;
import cl.nexo.manager.obj.login.ObjDataLogin;
import cl.nexo.manager.obj.login.ObjListManUser;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.tools.ObjGeneralResultInt;



@RestController
@RequestMapping("/RestOtpUser")
public class RestOtp {
	private static final Logger logger = Logger.getLogger(RestOtp.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("yyyyMMddHHmmss");
	
	
	@RequestMapping(value = "/getExistLoginByMail", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt getExistLoginByMail(@RequestParam("mail") String mail){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		
		int value = logins.getExistLoginByMail(mail);
		
		ObjGeneralResultInt result = new ObjGeneralResultInt();
		
		result.setResult(value);
		result.setText("Val Mail Login -- 1: Existe -- 0: No Existe");
		
		return result;
		
	}
	
	@RequestMapping(value = "/updateOtpLoginByMail", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt getListUserByCliente(@RequestParam("mail") String mail){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPassword genPass = (AccessPassword) context.getBean("AccessPassword");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		AccessPlantillasLogin bodyTemplate = (AccessPlantillasLogin) context.getBean("AccessPlantillasLogin");
		AccessServerMail serverMail = (AccessServerMail) context.getBean("AccessServerMail");
		
		logger.info("Inicia proceso de updateOtpLoginByMail para mail: "+ mail);
		
		ObjLoginUser user = logins.getUserByMail(mail);
		String fechaNow = format3.format(new Date());
		String fechaNow2 = format4.format(new Date());
		
		String newPassAux = genPass.getPassword();
		String keyOtp = UUID.randomUUID().toString();
		
		ObjLoginUser us = new ObjLoginUser();
		us.setId_user(user.getId_user());
		us.setPass_user(newPassAux);
		us.setFmod_user(fechaNow);
		us.setSmod_user(3);
		us.setKeyactiveotp_user(keyOtp);
		
		
		int aux = logins.updatePasswordByUser(us);
		
		logger.debug("Se actualiza OTP para usuario: "+ user.getLogin_user());
		
		if(aux == 1 ){
			String urlHome = tools.getValorConfigById(7);
			String managerCliente = tools.getValorConfigById(8);
			String correoFrom = tools.getValorConfigById(5);
			String asuntoLogin	 = tools.getValorConfigById(10);
			String body = bodyTemplate.updatePassLogin(user.getNombre_user() + " " + user.getApp_user(), user.getLogin_user(), keyOtp, keyOtp); 
			serverMail.sendMail(correoFrom, user.getMail_user(), asuntoLogin, body, "html");
		}
    	logger.debug("Send mail a mail: "+ user.getMail_user() + ",  con info para activar cuenta.");
		
    	ObjGeneralResultInt result = new ObjGeneralResultInt();
    	
    	result.setResult(aux);
    	result.setText("Se a generado updateOtpLoginByMail");
    	
		return result;
	}
	
	@RequestMapping(value = "/activeOtpLoginById", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt activeOtpLoginById(@RequestParam("id") int id,
												  @RequestParam("pass")	String pass,
												  @RequestParam("pass2") String pass2
													){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessPassword genPass = (AccessPassword) context.getBean("AccessPassword");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		AccessPlantillasLogin bodyTemplate = (AccessPlantillasLogin) context.getBean("AccessPlantillasLogin");
		AccessServerMail serverMail = (AccessServerMail) context.getBean("AccessServerMail");
		
		logger.info("Inicia proceso de activeOtpLoginById para id: "+ id);
		
		ObjLoginUser user = logins.getUserById(id);
		String fechaNow = format3.format(new Date());
		String fechaNow2 = format4.format(new Date());
		
		String newPassAux = genPass.getEncriptaPass(pass);
		String keyOtp = UUID.randomUUID().toString();
		
		ObjLoginUser us = new ObjLoginUser();
		us.setId_user(user.getId_user());
		us.setPass_user(newPassAux);
		us.setFmod_user(fechaNow);
		us.setSmod_user(3);
		us.setKeyactiveotp_user(keyOtp);
		
		
		int aux = logins.activePasswordByUser(us);
		
		logger.debug("Se Activa OTP para usuario: "+ user.getLogin_user());
		
		if(aux == 9 ){
			String urlHome = tools.getValorConfigById(7);
			String managerCliente = tools.getValorConfigById(8);
			String correoFrom = tools.getValorConfigById(5);
			String asuntoLogin	 = tools.getValorConfigById(10);
			String body = bodyTemplate.updatePassLogin(user.getNombre_user() + " " + user.getApp_user(), user.getLogin_user(), keyOtp, keyOtp); 
			serverMail.sendMail(correoFrom, user.getMail_user(), asuntoLogin, body, "html");
			logger.debug("Send mail a mail: "+ user.getMail_user() + ",  con info de cuenta activada.");
		}
    	
		
    	ObjGeneralResultInt result = new ObjGeneralResultInt();
    	
    	result.setResult(aux);
    	result.setText("Se activa Password de usuario");
    	
		return result;
	}
	
}
