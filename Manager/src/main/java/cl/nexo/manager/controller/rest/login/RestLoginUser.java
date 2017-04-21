package cl.nexo.manager.controller.rest.login;

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
import org.springframework.web.client.RestTemplate;






import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.password.AccessPassword;
import cl.nexo.manager.access.server.mail.AccessServerMail;
import cl.nexo.manager.access.server.mail.plantillas.AccessPlantillasLogin;
import cl.nexo.manager.access.tipo.contrato.AccessTipoContrato;
import cl.nexo.manager.obj.login.ObjDataLogin;
import cl.nexo.manager.obj.login.ObjListManUser;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.tools.ObjComboSelect2ValueInt;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;
import cl.nexo.manager.obj.tools.ObjGeneralResultInt;



@RestController
@RequestMapping("/RestLoginUser")
public class RestLoginUser {
	
	private static final Logger logger = Logger.getLogger(RestLoginUser.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("yyyyMMddHHmmss");
	
	@RequestMapping(value = "/getCombo", method = RequestMethod.GET,headers="Accept=application/json")
	public ArrayList<ObjComboSelect2ValueInt> getCombo(@RequestParam("tipo") int tipo,
													  @RequestParam("id") int id,
													  @RequestParam("place") String place)
	{
		 ArrayList<ObjComboSelect2ValueInt> combo =  new ArrayList<ObjComboSelect2ValueInt>();
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 
		 LoginAccess combos = (LoginAccess) context.getBean("LoginAccess");
		 
		 combo = combos.getCombo(tipo, id, place);
		 
		 return combo;
		 
	}
	
	@RequestMapping(value = "/getListUSerByCliente/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjDataLogin getListUserByCliente(@PathVariable("id") int id){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		
		ObjDataLogin result = new ObjDataLogin();
		
		ArrayList<ObjListManUser> list = logins.getListUserByCliente(id);
		result.setData(list);
		
		logger.info("***********" + list);
		logger.info("***********" + result);
		return result;
	}
	
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
	
	@RequestMapping(value = "/getExistRun/{run}", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt getExistRun(@PathVariable("run") String run){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		
		int value = logins.getExistLoginByRun(run);
		
		ObjGeneralResultInt result = new ObjGeneralResultInt();
		
		result.setResult(value);
		result.setText("Val RUN Login -- 1: Existe -- 0: No Existe");
		
		return result;
		
	} 
	
	@RequestMapping(value = "/getExistLogin/{login}", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt getExistLogin(@PathVariable("login") String login){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		
		int value = logins.getExistLoginById(login);
		
		ObjGeneralResultInt result = new ObjGeneralResultInt();
		
		result.setResult(value);
		result.setText("Val Login -- 1: Existe -- 0: No Existe");
		
		return result;
		
	}
	
	@RequestMapping(value = "/setUserLogin", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt setUserLogin(@RequestParam("run") String run,
							@RequestParam("login") String login,
							@RequestParam("nombre") String nombre,
							@RequestParam("app") String app,
							@RequestParam("mail") String mail,
							@RequestParam("nacimiento") String nacimiento,
							@RequestParam("perfil") int perfil,
							@RequestParam("division") int division,
							@RequestParam("area") int area,
							@RequestParam("cargo") int cargo,
							@RequestParam("estado") int estado,
							@RequestParam("tcontrato") int tcontrato,
							@RequestParam("tpago") int tpago,
							@RequestParam("tjornada") int tjornada,
							@RequestParam("pais") int pais,
							@RequestParam("region") int region,
							@RequestParam("ciudad") int ciudad,
							@RequestParam("comuna") int comuna,
							@RequestParam("anexo") String anexo,
							@RequestParam("direccion") String direccion,
							@RequestParam("idsam") int idsam,
							@RequestParam("delivery") String delivery,
							@RequestParam("subdiv") int subdiv,
							@RequestParam("ifuser") int ifuser,
							@RequestParam("lang") String lang,
							@RequestParam("numero") String numero,
							@RequestParam("dpto") String dpto,
							@RequestParam("postal") String postal
							){
		//--------BEGIN debug ----------------------------
		logger.debug("run: "+run);
		logger.debug("login: "+login);
		logger.debug("nombre: "+nombre);
		logger.debug("app: "+app);
		logger.debug("mail: "+mail);
		logger.debug("nacimiento: "+nacimiento);
		logger.debug("perfil: "+perfil);
		logger.debug("division: "+division);
		logger.debug("area: "+area);
		logger.debug("estado: "+estado);
		logger.debug("tcontrato: "+tcontrato);
		logger.debug("tpago: "+tpago);
		logger.debug("tjornada: "+tjornada);
		logger.debug("pais: "+pais);
		logger.debug("region: "+region);
		logger.debug("ciudad: "+ciudad);
		logger.debug("anexo: "+anexo);
		logger.debug("direccion: "+direccion);
		logger.debug("comuna: "+ comuna);
		logger.debug("idsam: "+idsam);
		logger.debug("delivery: "+delivery);
		logger.debug("subdiv: "+subdiv);
		logger.debug("ifuser: "+ifuser);
		logger.debug("lang: "+lang);
		logger.debug("numero: "+numero);
		logger.debug("dpto: "+dpto);
		logger.debug("postal: "+postal);
		//--------END debug ----------------------------
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessServerMail serverMail = (AccessServerMail) context.getBean("AccessServerMail");
    	AccessPlantillasLogin bodyTemplate = (AccessPlantillasLogin) context.getBean("AccessPlantillasLogin");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		AccessPassword genPass = (AccessPassword) context.getBean("AccessPassword");
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName());
	    
	    String fechaNow = format3.format(new Date());
	    String fechaNow2 = format4.format(new Date());
	    String activeCode = UUID.randomUUID().toString();
	    
	    ObjGeneralResultInt result = new ObjGeneralResultInt();
		
		ObjLoginUser ologin = new ObjLoginUser();
		ologin.setRun(run);
		ologin.setLogin_user(login);
		ologin.setNombre_user(nombre);
		ologin.setApp_user(app);
		ologin.setMail_user(mail);
		ologin.setFnacimiento_user(nacimiento);
		ologin.setId_perfil(perfil);
		ologin.setId_division(division);
		ologin.setId_area(area);
		ologin.setId_cargo(cargo);
		ologin.setEstado_user(estado);
		ologin.setId_tipo_contrato(tcontrato);
		ologin.setId_tipo_pago(tpago);
		ologin.setId_jornada(tjornada);
		ologin.setId_pais(pais);
		ologin.setId_region(region);
		ologin.setId_ciudad(ciudad);
		ologin.setId_comuna(comuna);
		ologin.setAnexo_user(anexo);
		ologin.setDireccion_user(direccion);
		ologin.setId_sam_user(idsam);
		ologin.setExiste_delivery(delivery);
		ologin.setKeyactiveotp_user(activeCode);
		//Optional atrivute
		ologin.setId_cliente(1);
		ologin.setId_puesto_cotizacion(0);
		ologin.setId_global(0);
		ologin.setFcreacion_user(fechaNow);
		ologin.setScreacion_user(user.getId_user());
		ologin.setFmod_user(fechaNow);
		ologin.setSmod_user(user.getId_user());
		ologin.setElimina_user(0); 
		ologin.setOtp_user(0);
		ologin.setNumero_user(numero);
		ologin.setDpto_user(dpto);
		ologin.setCasilla_user(postal);
		
		if(ifuser == 1){
			ologin.setRol_user("ROLE_ADMIN");
		}else{
			ologin.setRol_user("ROLE_EXT");
		}
		ologin.setId_sdiv(subdiv);
		ologin.setIf_user(ifuser);
		ologin.setLang_user(lang);
		int ins = logins.setUserManager(ologin);
		int idUser = logins.getIdMaxInsertId(user.getId_user());
		
		
		if(ifuser == 1){
			String urlHome = tools.getValorConfigById(7);
	    	String managerCliente = tools.getValorConfigById(8);
	    	String correoFrom = tools.getValorConfigById(5);
	    	String asuntoLogin	 = tools.getValorConfigById(9);
	    	ObjLoginUser newUser = logins.getUserById(idUser);
	    	String body = bodyTemplate.sendNewLogin(newUser.getNombre_user() + " " + newUser.getApp_user(), newUser.getLogin_user(), ologin.getPass_user(), activeCode); 
	    	serverMail.sendMail(correoFrom, ologin.getMail_user(), asuntoLogin, body, "html");
	    	
			if(delivery.equals("SI")){
				String srvDelivery = tools.getValorConfigById(16);
				String urlRestServiceDelivery = tools.getValorConfigById(15);
				String serviceSetUserDelivery = tools.getValorConfigById(19);
				String wsUser = tools.getValorConfigById(22);
				String wsKey = tools.getValorConfigById(23);
				if(srvDelivery.equals("SI")){
					logger.info("Se ejecuta creación de usuario en Delivery vía Ws para usuario: "+ login);
					String param ="?idmanager="+idUser
											   +"&run="+run
											   +"&login="+login
											   +"&nombre="+nombre
											   +"&app="+app
											   +"&mail="+mail
											   +"&nacimiento="+nacimiento
											   +"&perfil="+perfil
											   +"&division="+division
											   +"&area="+area
											   +"&cargo="+cargo
											   +"&estado="+estado
											   +"&tcontrato="+tcontrato
											   +"&tpago="+tpago
											   +"&tjornada="+tjornada
											   +"&pais="+pais
											   +"&region="+region
											   +"&ciudad="+ciudad
											   +"&anexo="+anexo
											   +"&direccion="+direccion
											   +"&idsam="+idsam
											   +"&cliente=1"
											   +"&userws="+wsUser
											   +"&keyws="+wsKey
											   +"&subdiv="+subdiv
											   +"&ifuser="+ifuser
											   ;
					/*
					 * Rest Service
					 * http://localhost:8080/spring-rest-1/data/fetchjson/200
						{
						  "id" : 200,
						  "name" : "Ram",
						  "address" : {
						    "village" : "Dhananjaypur",
						    "district" : "Varanasi",
						    "state" : "UP"
						  }
						}  
						
						Client code.
						public class GetForObjectDemoWithJSON {
						    public static void main(String args[]) {
						        RestTemplate restTemplate = new RestTemplate();
						        Person person = restTemplate.getForObject("http://localhost:8080/spring-rest-1/data/fetchjson/{id}", Person.class, 200);
						        System.out.println("ID: " + person.getId());
						        System.out.println("Name: " + person.getName());
						        System.out.println("Village Name: " + person.getAddress().getVillage());
						    }
						} 
						
						Output.
						ID: 200
						Name: Ram
						Village Name: Dhananjaypur
					 * */
					
					RestTemplate restTemplate = new RestTemplate();
					ObjGeneralResultInt response = restTemplate.getForObject(urlRestServiceDelivery+serviceSetUserDelivery+param, ObjGeneralResultInt.class);
					/*
					 Response data  result:
					 1- Se crea nuevo usuario
					 2- Usuario ya existe en el sistema, si usuario no tiene idManager este actualiza campo
					 3- Usuario existe ya en sistema y no se genera modificacion
					 4- Se genera un tipo de error
					 */
					logger.info("Se consume servicio: "+urlRestServiceDelivery+serviceSetUserDelivery+" , el resultado es: "+ response.getResult() + ", detalle: "+response.getText());
					
				}else{
					logger.info("No se ejecuta CREATE de usuario en Delivery vía Ws para usuario: "+ login + ", ya que configuración esta desactivada ");
				}
			}
		}
		
		result.setResult(1);
		result.setText("Usuario <strong>"+login+"</strong> a sido <strong>CREADO</strong> en el sistema! ");
		
		return result;
		
	}
	
	@RequestMapping(value = "/getDetailUserById/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjLoginUser getDetailUserById(@PathVariable("id") int id){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		
		String fechaNow2 = format3.format(new Date());
		logger.info("Hoy: "+fechaNow2);
		
		ObjLoginUser user = new ObjLoginUser();
		user = logins.getUserById(id);
		
		
		return user;
		
	}
	
	@RequestMapping(value = "/updateUserLogin", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt updateUserLogin(
							@RequestParam("id") int id,
							@RequestParam("run") String run,
							@RequestParam("login") String login,
							@RequestParam("nombre") String nombre,
							@RequestParam("app") String app,
							@RequestParam("mail") String mail,
							@RequestParam("nacimiento") String nacimiento,
							@RequestParam("perfil") int perfil,
							@RequestParam("division") int division,
							@RequestParam("area") int area,
							@RequestParam("cargo") int cargo,
							@RequestParam("estado") int estado,
							@RequestParam("tcontrato") int tcontrato,
							@RequestParam("tpago") int tpago,
							@RequestParam("tjornada") int tjornada,
							@RequestParam("pais") int pais,
							@RequestParam("region") int region,
							@RequestParam("ciudad") int ciudad,
							@RequestParam("comuna") int comuna,
							@RequestParam("anexo") String anexo,
							@RequestParam("direccion") String direccion,
							@RequestParam("idsam") int idsam,
							@RequestParam("delivery") String delivery,
							@RequestParam("subdiv") int subdiv,
							@RequestParam("ifuser") int ifuser,
							@RequestParam("lang") String lang,
							@RequestParam("numero") String numero,
							@RequestParam("dpto") String dpto,
							@RequestParam("postal") String postal
							){
		//--------BEGIN debug ----------------------------
		logger.debug("id: "+id);
		logger.debug("run: "+run);
		logger.debug("login: "+login);
		logger.debug("nombre: "+nombre);
		logger.debug("app: "+app);
		logger.debug("mail: "+mail);
		logger.debug("nacimiento: "+nacimiento);
		logger.debug("perfil: "+perfil);
		logger.debug("division: "+division);
		logger.debug("area: "+area);
		logger.debug("estado: "+estado);
		logger.debug("tcontrato: "+tcontrato);
		logger.debug("tpago: "+tpago);
		logger.debug("tjornada: "+tjornada);
		logger.debug("pais: "+pais);
		logger.debug("region: "+region);
		logger.debug("ciudad: "+ciudad);
		logger.debug("anexo: "+anexo);
		logger.debug("direccion: "+direccion);
		logger.debug("idsam: "+idsam);
		logger.debug("delivery: "+delivery);
		logger.debug("subdiv: "+subdiv);
		logger.debug("ifuser: "+ifuser);
		logger.debug("lang: "+lang);
		//--------END debug ----------------------------
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessServerMail serverMail = (AccessServerMail) context.getBean("AccessServerMail");
    	AccessPlantillasLogin bodyTemplate = (AccessPlantillasLogin) context.getBean("AccessPlantillasLogin");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		AccessPassword genPass = (AccessPassword) context.getBean("AccessPassword");
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName());
	    
	    String fechaNow = format3.format(new Date());
	    String fechaNow2 = format4.format(new Date());
	   
	    
	    ObjGeneralResultInt result = new ObjGeneralResultInt();
		
		ObjLoginUser ologin = new ObjLoginUser();
		ologin.setId_user(id);
		ologin.setRun(run);
		ologin.setLogin_user(login);
		ologin.setNombre_user(nombre);
		ologin.setApp_user(app);
		ologin.setMail_user(mail);
		ologin.setFnacimiento_user(nacimiento);
		ologin.setId_perfil(perfil);
		ologin.setId_division(division);
		ologin.setId_area(area);
		ologin.setId_cargo(cargo);
		ologin.setEstado_user(estado);
		ologin.setId_tipo_contrato(tcontrato);
		ologin.setId_tipo_pago(tpago);
		ologin.setId_jornada(tjornada);
		ologin.setId_pais(pais);
		ologin.setId_region(region);
		ologin.setId_ciudad(ciudad);
		ologin.setAnexo_user(anexo);
		ologin.setDireccion_user(direccion);
		ologin.setId_sam_user(idsam);
		ologin.setExiste_delivery(delivery);
		//Optional atrivute
		ologin.setId_cliente(1);
		ologin.setId_puesto_cotizacion(0);
		ologin.setId_global(0);
		ologin.setFmod_user(fechaNow);
		ologin.setSmod_user(user.getId_user());
		ologin.setFmod_user(fechaNow);
		ologin.setSmod_user(user.getId_user());
		ologin.setElimina_user(0); 
		ologin.setOtp_user(0);
		ologin.setNumero_user(numero);
		ologin.setDpto_user(dpto);
		ologin.setCasilla_user(postal);
		if(ifuser == 1){
			ologin.setRol_user("ROLE_ADMIN");
		}else{
			ologin.setRol_user("ROLE_EXT");
		}
		ologin.setId_sdiv(subdiv);
		ologin.setIf_user(ifuser);
		ologin.setLang_user(lang);
		int ins = logins.updateLogin(ologin);
		
		
		
		
		if(delivery.equals("SI")){
			String srvDelivery = tools.getValorConfigById(16);
			String urlRestServiceDelivery = tools.getValorConfigById(15);
			String serviceUserDelivery = tools.getValorConfigById(20);
			String wsUser = tools.getValorConfigById(22);
			String wsKey = tools.getValorConfigById(23);
			if(srvDelivery.equals("SI")){
				logger.info("Se ejecuta UPDATE de usuario en Delivery vía Ws para usuario: "+ login);
				String param ="?idmanager="+id
										   +"&run="+run
										   +"&login="+login
										   +"&nombre="+nombre
										   +"&app="+app
										   +"&mail="+mail
										   +"&nacimiento="+nacimiento
										   +"&perfil="+perfil
										   +"&division="+division
										   +"&area="+area
										   +"&cargo="+cargo
										   +"&estado="+estado
										   +"&tcontrato="+tcontrato
										   +"&tpago="+tpago
										   +"&tjornada="+tjornada
										   +"&pais="+pais
										   +"&region="+region
										   +"&ciudad="+ciudad
										   +"&anexo="+anexo
										   +"&direccion="+direccion
										   +"&idsam="+idsam
										   +"&cliente=1"
										   +"&userws="+wsUser
										   +"&keyws="+wsKey
										   +"&subdiv="+subdiv
										   +"&ifuser="+ifuser
										   ;
				/*
				 * Rest Service
				 * http://localhost:8080/spring-rest-1/data/fetchjson/200
					{
					  "id" : 200,
					  "name" : "Ram",
					  "address" : {
					    "village" : "Dhananjaypur",
					    "district" : "Varanasi",
					    "state" : "UP"
					  }
					}  
					
					Client code.
					public class GetForObjectDemoWithJSON {
					    public static void main(String args[]) {
					        RestTemplate restTemplate = new RestTemplate();
					        Person person = restTemplate.getForObject("http://localhost:8080/spring-rest-1/data/fetchjson/{id}", Person.class, 200);
					        System.out.println("ID: " + person.getId());
					        System.out.println("Name: " + person.getName());
					        System.out.println("Village Name: " + person.getAddress().getVillage());
					    }
					} 
					
					Output.
					ID: 200
					Name: Ram
					Village Name: Dhananjaypur
				 * */
				
				RestTemplate restTemplate = new RestTemplate();
				ObjGeneralResultInt response = restTemplate.getForObject(urlRestServiceDelivery+serviceUserDelivery+param, ObjGeneralResultInt.class);
				/*
				 Response data  result:
				 1- Success Solicitud
				 4- Error Solicitud
				 */
				logger.info("Se consume servicio: "+urlRestServiceDelivery+serviceUserDelivery+" , el resultado es: "+ response.getResult() + ", detalle: "+response.getText());
				
			}else{
				logger.info("No se ejecuta UPDATE de usuario en Delivery vía Ws para usuario: "+ login + ", ya que configuración esta desactivada ");
			}
		}
		
		
		result.setResult(1);
		result.setText("Usuario <strong>"+login+"</strong> a sido <strong>ACTUALIZADO</strong> en el sistema! ");
		
		return result;
		
	}
	
	@RequestMapping(value = "/deleteUserLogin", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt deleteUserLogin(
							@RequestParam("id") int id,
							@RequestParam("login") String login,
							@RequestParam("delivery") String delivery
							){
		//--------BEGIN debug ----------------------------
		logger.debug("id: "+id);
		logger.debug("login: "+login);
		logger.debug("delivery: "+delivery);
		//--------END debug ----------------------------
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		AccessServerMail serverMail = (AccessServerMail) context.getBean("AccessServerMail");
    	AccessPlantillasLogin bodyTemplate = (AccessPlantillasLogin) context.getBean("AccessPlantillasLogin");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		AccessPassword genPass = (AccessPassword) context.getBean("AccessPassword");
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName());
	    
	    String fechaNow = format3.format(new Date());
	    String fechaNow2 = format4.format(new Date());
	    
	    
	    ObjGeneralResultInt result = new ObjGeneralResultInt();
		
		int ins = logins.deleteUser(id);
		
		
		
		
		if(delivery.equals("SI")){
			String srvDelivery = tools.getValorConfigById(16);
			String urlRestServiceDelivery = tools.getValorConfigById(15);
			String serviceUserDelivery = tools.getValorConfigById(21);
			String wsUser = tools.getValorConfigById(22);
			String wsKey = tools.getValorConfigById(23);
			if(srvDelivery.equals("SI")){
				logger.info("Se ejecuta DELETE de usuario en Delivery vía Ws para usuario: "+ login);
				String param ="?idmanager="+id
										   +"&login="+login
										   +"&cliente=1"
										   +"&userws="+wsUser
										   +"&keyws="+wsKey
										   ;
				/*
				 * Rest Service
				 * http://localhost:8080/spring-rest-1/data/fetchjson/200
					{
					  "id" : 200,
					  "name" : "Ram",
					  "address" : {
					    "village" : "Dhananjaypur",
					    "district" : "Varanasi",
					    "state" : "UP"
					  }
					}  
					
					Client code.
					public class GetForObjectDemoWithJSON {
					    public static void main(String args[]) {
					        RestTemplate restTemplate = new RestTemplate();
					        Person person = restTemplate.getForObject("http://localhost:8080/spring-rest-1/data/fetchjson/{id}", Person.class, 200);
					        System.out.println("ID: " + person.getId());
					        System.out.println("Name: " + person.getName());
					        System.out.println("Village Name: " + person.getAddress().getVillage());
					    }
					} 
					
					Output.
					ID: 200
					Name: Ram
					Village Name: Dhananjaypur
				 * */
				
				RestTemplate restTemplate = new RestTemplate();
				ObjGeneralResultInt response = restTemplate.getForObject(urlRestServiceDelivery+serviceUserDelivery+param, ObjGeneralResultInt.class);
				/*
				 Response data  result:
				 1- Success Solicitud
				 4- Error Solicitud
				 */
				logger.info("Se consume servicio: "+urlRestServiceDelivery+serviceUserDelivery+" , el resultado es: "+ response.getResult() + ", detalle: "+response.getText());
				
			}else{
				logger.info("No se ejecuta DELETE de usuario en Delivery vía Ws para usuario: "+ login + ", ya que configuración esta desactivada ");
			}
		}
		
		
		result.setResult(1);
		result.setText("Usuario <strong>"+login+"</strong> a sido <strong>ELIMINADO</strong> en el sistema! ");
		
		return result;
		
	}
}
