package cl.nexo.manager.controller.rest.instructivo;

import java.text.DateFormat;
import java.text.ParseException;
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

import cl.nexo.manager.access.apoderado.ApoderadoAccess;
import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.access.instructivo.InstructivoAccess;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.password.AccessPassword;
import cl.nexo.manager.access.server.mail.AccessServerMail;
import cl.nexo.manager.access.server.mail.plantillas.AccessPlantillasLogin;
import cl.nexo.manager.obj.apoderado.ObjDataApoderado;
import cl.nexo.manager.obj.apoderado.ObjListApoderado;
import cl.nexo.manager.obj.instructivo.ObjInstructivo;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.tools.ObjGeneralResultInt;



@RestController
@RequestMapping("/RestInstructivo")
public class RestInstructivo {
	
	private static final Logger logger = Logger.getLogger(RestInstructivo.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("yyyyMMddHHmmss");
	

	
	
	
	@RequestMapping(value = "/crearInstructivo", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt crearInstructivo(
			                @RequestParam("id_oper") int id_oper,
			                @RequestParam("rep_ola") boolean rep_ola,
							@RequestParam("carta") boolean carta,
							@RequestParam("loi") int loi,
							@RequestParam("incidencia") int incidencia,
							@RequestParam("tasa") int tasa,	
							
							@RequestParam("vista_1") String vista_1,	
							@RequestParam("vista_2") String vista_2,
							
							@RequestParam("grupo_1") String grupo_1,	
							@RequestParam("grupo_2") String grupo_2,
							
							@RequestParam("estructura") String estructura,	
							@RequestParam("glosario") String glosario,
							
							@RequestParam("quest_papi1") String quest_papi1,	
							@RequestParam("quest_papi2") String quest_papi2,
							
							@RequestParam("quest_capi1") String quest_capi1,	
							@RequestParam("quest_capi2") String quest_capi2,
							@RequestParam("quest_capi3") String quest_capi3,

							@RequestParam("cuota1") String cuota1,	
							@RequestParam("cuota2") boolean cuota2,
							@RequestParam("cuota3") String cuota3,
							@RequestParam("cuota4") String cuota4,	
							@RequestParam("cuota5") String cuota5,
							@RequestParam("cuota6") boolean cuota6,
							
							@RequestParam("cuota7") int cuota7,
							
							@RequestParam("t_campo_desde") String t_campo_desde,	
							@RequestParam("t_campo_hasta") String t_campo_hasta,
							@RequestParam("p_temporal") String p_temporal,	
							@RequestParam("p_final") String p_final,
							@RequestParam("desc_obj") String desc_obj,	
							@RequestParam("dia_mes") String dia_mes,
							@RequestParam("planificacion") String planificacion,
							
							@RequestParam("txt_plazo_papi_01") String plazo_papi_01,	
							@RequestParam("txt_plazo_papi_02") String plazo_papi_02,
							@RequestParam("txt_plazo_papi_03") String plazo_papi_03,	
							@RequestParam("txt_plazo_papi_04") String plazo_papi_04,
							@RequestParam("txt_plazo_capi_01") String plazo_capi_01,	
							@RequestParam("txt_plazo_capi_02") String plazo_capi_02,
							@RequestParam("txt_plazo_capi_03") String plazo_capi_03,
						
							@RequestParam("txt_remun_01") String remun_01,	
							@RequestParam("txt_remun_02") String remun_02,
							@RequestParam("txt_pack_01") String pack_01,

							
							@RequestParam("txt_contacto_01") String contacto_01,
							@RequestParam("txt_contacto_02") String contacto_02,
						    @RequestParam("txt_contacto_03") String contacto_03,	
							@RequestParam("txt_contacto_04") String contacto_04,
							@RequestParam("txt_contacto_05") String contacto_05,
							
							@RequestParam("incent_dinero") boolean incent_dinero,
							@RequestParam("incent_voucher") boolean incent_voucher,
							@RequestParam("incent_regalo") boolean incent_regalo,
							@RequestParam("incent_especif") String incent_especif,
							
							@RequestParam("que_papi") boolean que_papi,
							@RequestParam("que_capi") boolean que_capi,
							@RequestParam("que_movil") boolean que_movil,
							@RequestParam("que_mixto") boolean que_mixto,
							@RequestParam("que_otro") String que_otro,
							
							@RequestParam("donde_casa") boolean donde_casa,
							@RequestParam("donde_central") boolean donde_central,
							@RequestParam("donde_calle") boolean donde_calle,
							@RequestParam("donde_tienda") boolean donde_tienda,
							@RequestParam("donde_otro") String donde_otro

	

			
			){
		//--------BEGIN debug ----------------------------
		
		//--------END debug ----------------------------
		
		logger.info("POR AQUI PASO");
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName());
	    
        ObjGeneralResultInt result = new ObjGeneralResultInt();
		
        ObjInstructivo inst = new ObjInstructivo();  
        InstructivoAccess instructivo = (InstructivoAccess) context.getBean("InstructivoAccess");
        
        
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");
        String strFecha1 = t_campo_hasta;
        String strFecha2 = t_campo_desde;
        
        try {
        
			t_campo_hasta=format.parse(strFecha1).toString();
			t_campo_desde=format.parse(strFecha2).toString();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        inst.setId_operacion(id_oper);
        inst.setRep_ola(rep_ola);
        inst.setCarta(carta);
        inst.setLoi(loi);
        inst.setIncidencia(incidencia);
        inst.setTasa(tasa);
        inst.setVista_1(vista_1);
        inst.setVista_2(vista_2);
        inst.setGrupo_1(grupo_1);
        inst.setGrupo_2(grupo_2);
        inst.setEstructura(estructura);
        inst.setGlosario(glosario);
        inst.setQuest_papi1(quest_papi1);
        inst.setQuest_papi2(quest_papi2);
        inst.setQuest_capi1(quest_capi1);
        inst.setQuest_capi2(quest_capi2);
        inst.setQuest_capi3(quest_capi3);
        inst.setCuota1(cuota1);
        inst.setCuota2(cuota2);
        inst.setCuota3(cuota3);
        inst.setCuota4(cuota4);
        inst.setCuota5(cuota5);
        inst.setCuota6(cuota6);
        inst.setCuota7(cuota7);
        inst.setT_campo_hasta(t_campo_hasta);
        inst.setT_campo_desde(t_campo_desde);
        inst.setP_temporal(p_temporal);
        inst.setP_final(p_final);
        inst.setDesc_obj(desc_obj);
        inst.setDia_mes(dia_mes);
        inst.setPlanificacion(planificacion);
        inst.setPlazo_papi_01(plazo_papi_01);
        inst.setPlazo_papi_02(plazo_papi_02);
        inst.setPlazo_papi_03(plazo_papi_03);
        inst.setPlazo_papi_04(plazo_papi_04);
        inst.setPlazo_capi_01(plazo_capi_01);
        inst.setPlazo_capi_02(plazo_capi_02);
        inst.setPlazo_capi_03(plazo_capi_03);
        inst.setRemun_01(remun_01);
        inst.setRemun_02(remun_02);
        inst.setPack_01(pack_01);
        inst.setContacto_01(contacto_01);
        inst.setContacto_02(contacto_02);
        inst.setContacto_03(contacto_03);
        inst.setContacto_04(contacto_04);
        inst.setContacto_05(contacto_05);
        inst.setIncent_dinero(incent_dinero);
        inst.setIncent_voucher(incent_voucher);
        inst.setIncent_regalo(incent_regalo);
        inst.setIncent_especif(incent_especif);
        inst.setQue_papi(que_papi);
        inst.setQue_capi(que_capi);
        inst.setQue_movil(que_movil);
        inst.setQue_mixto(que_mixto);
        inst.setQue_otro(que_otro);
        inst.setDonde_casa(donde_casa);
        inst.setDonde_central(donde_central);
        inst.setDonde_calle(donde_calle);
        inst.setDonde_tienda(donde_tienda);
        inst.setDonde_otro(donde_otro);

        
	    instructivo.crearInstructivo(inst);   
		
        
		result.setResult(1);
		result.setText("Instructivo <strong>2</strong> a sido <strong>CREADO</strong> en el sistema! ");
		
		return result;
		
	}	
	
	
	
	@RequestMapping(value = "/crearInstructivoCati", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt crearInstructivoCati(
			                @RequestParam("id_oper") int id_oper,
							@RequestParam("nombre_guion") String nombre_guion,	
							@RequestParam("nombre_servidor") String nombre_servidor,
							@RequestParam("tasa") int tasa,
							@RequestParam("paises") String paises,
							@RequestParam("loi") int loi,
							@RequestParam("incidencia") int incidencia,
							@RequestParam("rep_ola") boolean rep_ola,
							@RequestParam("carta") boolean carta,
							
							
							@RequestParam("vista_1") String vista_1,	
							@RequestParam("vista_2") String vista_2,
							
							
							@RequestParam("B2B") boolean B2B,
							@RequestParam("B2C") boolean B2C,
							@RequestParam("Porc_B2B") String Porc_B2B,	
							@RequestParam("Porc_B2C") String Porc_B2C,
							@RequestParam("met_mixto") String met_mixto,	
							@RequestParam("coment") String coment,
							
							
							@RequestParam("grupo_1") String grupo_1,	
							@RequestParam("grupo_2") String grupo_2,
							
							@RequestParam("estructura") String estructura,	
							@RequestParam("glosario") String glosario,

							@RequestParam("muestra_ent") boolean muestra_ent,	
							@RequestParam("especif") String especif,
							@RequestParam("rdd") boolean rdd,
							@RequestParam("muestra_cliente") boolean muestra_cliente,	
							@RequestParam("base_datos") boolean base_datos,
							@RequestParam("otro") boolean otro,
							@RequestParam("especif_otro") String especif_otro,
							
							@RequestParam("nombre_contact") boolean nombre_contact,
							@RequestParam("porc_nombre") String porc_nombre,
							
							@RequestParam("permit_recom") boolean permit_recom,
							@RequestParam("especif_recom") String especif_recom,
							
							@RequestParam("n_recom") String n_recom,
							@RequestParam("coment_adic") String coment_adic,
							
							@RequestParam("cuota14") boolean cuota14,
							@RequestParam("cuota15") int cuota15,
							
							
							@RequestParam("t_campo_desde") String t_campo_desde,	
							@RequestParam("t_campo_hasta") String t_campo_hasta,
							@RequestParam("p_temporal") String p_temporal,	
							@RequestParam("p_final") String p_final,
							@RequestParam("desc_obj") String desc_obj,	
							@RequestParam("dia_mes") String dia_mes,
							@RequestParam("planificacion") String planificacion,
							
							@RequestParam("inst_sup1") String inst_sup1,	
							@RequestParam("inst_sup2") String inst_sup2,
							
							@RequestParam("txt_remun_01") String remun_01,
							
							@RequestParam("incent_dinero") boolean incent_dinero,
							@RequestParam("incent_voucher") boolean incent_voucher,
							@RequestParam("incent_regalo") boolean incent_regalo,
							@RequestParam("incent_especif") String incent_especif,
							@RequestParam("realiza_cita") boolean realiza_cita,
							@RequestParam("coment_incent") String coment_incent
						
	

			
			){
		//--------BEGIN debug ----------------------------
		
		//--------END debug ----------------------------
		

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName());
	    
        ObjGeneralResultInt result = new ObjGeneralResultInt();
		
        ObjInstructivo inst = new ObjInstructivo();  
        InstructivoAccess instructivo = (InstructivoAccess) context.getBean("InstructivoAccess");
        
        
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");
        String strFecha1 = t_campo_hasta;
        String strFecha2 = t_campo_desde;
        
        try {
        
			t_campo_hasta=format.parse(strFecha1).toString();
			t_campo_desde=format.parse(strFecha2).toString();
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        inst.setId_operacion(id_oper);
        
        inst.setNombre_guion(nombre_guion);
        inst.setNombre_servidor(nombre_servidor);
        inst.setTasa(tasa);
        inst.setPaises(paises);
        inst.setLoi(loi);
        inst.setIncidencia(incidencia);
        inst.setRep_ola(rep_ola);
        inst.setCarta(carta);
        inst.setVista_1(vista_1);
        inst.setVista_2(vista_2);
        inst.setB2B(B2B);
        inst.setB2C(B2C);
        inst.setPorc_B2B(Porc_B2B);
        inst.setPorc_B2C(Porc_B2C);
        inst.setMet_mixto(met_mixto);
        inst.setComent(coment);
        inst.setGrupo_1(grupo_1);
        inst.setGrupo_2(grupo_2);
        
        inst.setEstructura(estructura);
        inst.setGlosario(glosario);
        
        inst.setMuestra_ent(muestra_ent);
        inst.setEspecif(especif);
        inst.setRdd(rdd);
        inst.setMuestra_cliente(muestra_cliente);
        inst.setBase_datos(base_datos);
        inst.setOtro(otro);
        inst.setEspecif_otro(especif_otro);
        inst.setNombre_contact(nombre_contact);
        inst.setPorc_nombre(porc_nombre);
        inst.setPermit_recom(permit_recom);
        inst.setEspecif_recom(especif_recom);
        inst.setN_recom(n_recom);
        inst.setComent_adic(coment_adic);
        inst.setCuota14(cuota14);
        inst.setCuota15(cuota15);
       
        inst.setInst_sup1(inst_sup1);
        inst.setInst_sup2(inst_sup2);
       
        inst.setT_campo_hasta(t_campo_hasta);
        inst.setT_campo_desde(t_campo_desde);
        inst.setP_temporal(p_temporal);
        inst.setP_final(p_final);
        inst.setDesc_obj(desc_obj);
        inst.setDia_mes(dia_mes);
        inst.setPlanificacion(planificacion);
        
        inst.setRemun_01(remun_01);
        
        inst.setIncent_dinero(incent_dinero);
        inst.setIncent_voucher(incent_voucher);
        inst.setIncent_regalo(incent_regalo);
        inst.setIncent_especif(incent_especif);
        inst.setRealiza_cita(realiza_cita);
        inst.setComent_incent(coment_incent);
        
        
       
	    instructivo.crearInstructivoCapi(inst);   
		
        
		result.setResult(1);
		result.setText("Instructivo <strong>2</strong> a sido <strong>CREADO</strong> en el sistema! ");
		
		return result;
		
	}	
	
	
	
	
	@RequestMapping(value = "/getDetailInstructivoCatiById/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjInstructivo getDetailInstructivoCatiById(@PathVariable("id") int id){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		
		 InstructivoAccess instructivo = (InstructivoAccess) context.getBean("InstructivoAccess");
		
		
		ObjInstructivo inst = new ObjInstructivo();
		inst = instructivo.getDetailInstructivoCatiById(id);
		
		//logger.info("NOMBRE  " + apd.getNombres());
		
			
		return inst;
		
	}
	
	
	@RequestMapping(value = "/getDetailInstructivoById/{id}", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjInstructivo getDetailInstructivoById(@PathVariable("id") int id){
		logger.info("IDENTIFICADOR  " + id);
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		
		 InstructivoAccess instructivo = (InstructivoAccess) context.getBean("InstructivoAccess");
		
		logger.info("IDENTIFICADOR  " + id);
		ObjInstructivo inst = new ObjInstructivo();
		inst = instructivo.getDetailInstructivoById(id);
		
		
		
			
		return inst;
		
	}
	
	
	
	
	@RequestMapping(value = "/updateInstructivoCati", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt updateInstructivoCati(
			                @RequestParam("id_oper") int id_oper,
							@RequestParam("nombre_guion") String nombre_guion,	
							@RequestParam("nombre_servidor") String nombre_servidor,
							@RequestParam("tasa") int tasa,
							@RequestParam("paises") String paises,
							@RequestParam("loi") int loi,
							@RequestParam("incidencia") int incidencia,
							@RequestParam("rep_ola") boolean rep_ola,
							@RequestParam("carta") boolean carta,
							
							
							@RequestParam("vista_1") String vista_1,	
							@RequestParam("vista_2") String vista_2,
							
							
							@RequestParam("B2B") boolean B2B,
							@RequestParam("B2C") boolean B2C,
							@RequestParam("Porc_B2B") String Porc_B2B,	
							@RequestParam("Porc_B2C") String Porc_B2C,
							@RequestParam("met_mixto") String met_mixto,	
							@RequestParam("coment") String coment,
							
							
							@RequestParam("grupo_1") String grupo_1,	
							@RequestParam("grupo_2") String grupo_2,
							
							@RequestParam("estructura") String estructura,	
							@RequestParam("glosario") String glosario,

							@RequestParam("muestra_ent") boolean muestra_ent,	
							@RequestParam("especif") String especif,
							@RequestParam("rdd") boolean rdd,
							@RequestParam("muestra_cliente") boolean muestra_cliente,	
							@RequestParam("base_datos") boolean base_datos,
							@RequestParam("otro") boolean otro,
							@RequestParam("especif_otro") String especif_otro,
							
							@RequestParam("nombre_contact") boolean nombre_contact,
							@RequestParam("porc_nombre") String porc_nombre,
							
							@RequestParam("permit_recom") boolean permit_recom,
							@RequestParam("especif_recom") String especif_recom,
							
							@RequestParam("n_recom") String n_recom,
							@RequestParam("coment_adic") String coment_adic,
							
							@RequestParam("cuota14") boolean cuota14,
							@RequestParam("cuota15") int cuota15,
							
							
							@RequestParam("t_campo_desde") String t_campo_desde,	
							@RequestParam("t_campo_hasta") String t_campo_hasta,
							@RequestParam("p_temporal") String p_temporal,	
							@RequestParam("p_final") String p_final,
							@RequestParam("desc_obj") String desc_obj,	
							@RequestParam("dia_mes") String dia_mes,
							@RequestParam("planificacion") String planificacion,
							
							@RequestParam("inst_sup1") String inst_sup1,	
							@RequestParam("inst_sup2") String inst_sup2,
							
							@RequestParam("txt_remun_01") String remun_01,
							
							@RequestParam("incent_dinero") boolean incent_dinero,
							@RequestParam("incent_voucher") boolean incent_voucher,
							@RequestParam("incent_regalo") boolean incent_regalo,
							@RequestParam("incent_especif") String incent_especif,
							@RequestParam("realiza_cita") boolean realiza_cita,
							@RequestParam("coment_incent") String coment_incent
						
	

			
			){
		//--------BEGIN debug ----------------------------
		
		//--------END debug ----------------------------
		

		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName());
	    
        ObjGeneralResultInt result = new ObjGeneralResultInt();
		
        ObjInstructivo inst = new ObjInstructivo();  
        InstructivoAccess instructivo = (InstructivoAccess) context.getBean("InstructivoAccess");
        
 
        inst.setId_operacion(id_oper);
        
        inst.setNombre_guion(nombre_guion);
        inst.setNombre_servidor(nombre_servidor);
        inst.setTasa(tasa);
        inst.setPaises(paises);
        inst.setLoi(loi);
        inst.setIncidencia(incidencia);
        inst.setRep_ola(rep_ola);
        inst.setCarta(carta);
        inst.setVista_1(vista_1);
        inst.setVista_2(vista_2);
        inst.setB2B(B2B);
        inst.setB2C(B2C);
        inst.setPorc_B2B(Porc_B2B);
        inst.setPorc_B2C(Porc_B2C);
        inst.setMet_mixto(met_mixto);
        inst.setComent(coment);
        inst.setGrupo_1(grupo_1);
        inst.setGrupo_2(grupo_2);
        
        inst.setEstructura(estructura);
        inst.setGlosario(glosario);
        
        inst.setMuestra_ent(muestra_ent);
        inst.setEspecif(especif);
        inst.setRdd(rdd);
        inst.setMuestra_cliente(muestra_cliente);
        inst.setBase_datos(base_datos);
        inst.setOtro(otro);
        inst.setEspecif_otro(especif_otro);
        inst.setNombre_contact(nombre_contact);
        inst.setPorc_nombre(porc_nombre);
        inst.setPermit_recom(permit_recom);
        inst.setEspecif_recom(especif_recom);
        inst.setN_recom(n_recom);
        inst.setComent_adic(coment_adic);
        inst.setCuota14(cuota14);
        inst.setCuota15(cuota15);
       
        inst.setInst_sup1(inst_sup1);
        inst.setInst_sup2(inst_sup2);
       
        inst.setT_campo_hasta(t_campo_hasta);
        inst.setT_campo_desde(t_campo_desde);
        inst.setP_temporal(p_temporal);
        inst.setP_final(p_final);
        inst.setDesc_obj(desc_obj);
        inst.setDia_mes(dia_mes);
        inst.setPlanificacion(planificacion);
        
        inst.setRemun_01(remun_01);
        
        inst.setIncent_dinero(incent_dinero);
        inst.setIncent_voucher(incent_voucher);
        inst.setIncent_regalo(incent_regalo);
        inst.setIncent_especif(incent_especif);
        inst.setRealiza_cita(realiza_cita);
        inst.setComent_incent(coment_incent);
        
        
       
	    instructivo.updateInstructivoCapi(inst);   
		
        
		result.setResult(1);
		result.setText("El Instructivo<strong>2</strong> a sido <strong>ACTUALIZADO</strong> en el sistema! ");
		
		return result;
		
	}	
	
	
	

	@RequestMapping(value = "/updateInstructivo", method = RequestMethod.GET,headers="Accept=application/json")
	public ObjGeneralResultInt updateInstructivo(
			                @RequestParam("id_oper") int id_oper,
			                @RequestParam("rep_ola") boolean rep_ola,
							@RequestParam("carta") boolean carta,
							@RequestParam("loi") int loi,
							@RequestParam("incidencia") int incidencia,
							@RequestParam("tasa") int tasa,	
							
							@RequestParam("vista_1") String vista_1,	
							@RequestParam("vista_2") String vista_2,
							
							@RequestParam("grupo_1") String grupo_1,	
							@RequestParam("grupo_2") String grupo_2,
							
							@RequestParam("estructura") String estructura,	
							@RequestParam("glosario") String glosario,
							
							@RequestParam("quest_papi1") String quest_papi1,	
							@RequestParam("quest_papi2") String quest_papi2,
							
							@RequestParam("quest_capi1") String quest_capi1,	
							@RequestParam("quest_capi2") String quest_capi2,
							@RequestParam("quest_capi3") String quest_capi3,

							@RequestParam("cuota1") String cuota1,	
							@RequestParam("cuota2") boolean cuota2,
							@RequestParam("cuota3") String cuota3,
							@RequestParam("cuota4") String cuota4,	
							@RequestParam("cuota5") String cuota5,
							@RequestParam("cuota6") boolean cuota6,
							
							@RequestParam("cuota7") int cuota7,
							
							@RequestParam("t_campo_desde") String t_campo_desde,	
							@RequestParam("t_campo_hasta") String t_campo_hasta,
							@RequestParam("p_temporal") String p_temporal,	
							@RequestParam("p_final") String p_final,
							@RequestParam("desc_obj") String desc_obj,	
							@RequestParam("dia_mes") String dia_mes,
							@RequestParam("planificacion") String planificacion,
							
							@RequestParam("txt_plazo_papi_01") String plazo_papi_01,	
							@RequestParam("txt_plazo_papi_02") String plazo_papi_02,
							@RequestParam("txt_plazo_papi_03") String plazo_papi_03,	
							@RequestParam("txt_plazo_papi_04") String plazo_papi_04,
							@RequestParam("txt_plazo_capi_01") String plazo_capi_01,	
							@RequestParam("txt_plazo_capi_02") String plazo_capi_02,
							@RequestParam("txt_plazo_capi_03") String plazo_capi_03,
						
							@RequestParam("txt_remun_01") String remun_01,	
							@RequestParam("txt_remun_02") String remun_02,
							@RequestParam("txt_pack_01") String pack_01,

							
							@RequestParam("txt_contacto_01") String contacto_01,
							@RequestParam("txt_contacto_02") String contacto_02,
						    @RequestParam("txt_contacto_03") String contacto_03,	
							@RequestParam("txt_contacto_04") String contacto_04,
							@RequestParam("txt_contacto_05") String contacto_05,
							
							@RequestParam("incent_dinero") boolean incent_dinero,
							@RequestParam("incent_voucher") boolean incent_voucher,
							@RequestParam("incent_regalo") boolean incent_regalo,
							@RequestParam("incent_especif") String incent_especif,
							
							@RequestParam("que_papi") boolean que_papi,
							@RequestParam("que_capi") boolean que_capi,
							@RequestParam("que_movil") boolean que_movil,
							@RequestParam("que_mixto") boolean que_mixto,
							@RequestParam("que_otro") String que_otro,
							
							@RequestParam("donde_casa") boolean donde_casa,
							@RequestParam("donde_central") boolean donde_central,
							@RequestParam("donde_calle") boolean donde_calle,
							@RequestParam("donde_tienda") boolean donde_tienda,
							@RequestParam("donde_otro") String donde_otro

	

			
			){
		//--------BEGIN debug ----------------------------
		
		//--------END debug ----------------------------
		
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName());
	    
        ObjGeneralResultInt result = new ObjGeneralResultInt();
		
        ObjInstructivo inst = new ObjInstructivo();  
        InstructivoAccess instructivo = (InstructivoAccess) context.getBean("InstructivoAccess");

        inst.setId_operacion(id_oper);
        inst.setRep_ola(rep_ola);
        inst.setCarta(carta);
        inst.setLoi(loi);
        inst.setIncidencia(incidencia);
        inst.setTasa(tasa);
        inst.setVista_1(vista_1);
        inst.setVista_2(vista_2);
        inst.setGrupo_1(grupo_1);
        inst.setGrupo_2(grupo_2);
        inst.setEstructura(estructura);
        inst.setGlosario(glosario);
        inst.setQuest_papi1(quest_papi1);
        inst.setQuest_papi2(quest_papi2);
        inst.setQuest_capi1(quest_capi1);
        inst.setQuest_capi2(quest_capi2);
        inst.setQuest_capi3(quest_capi3);
        inst.setCuota1(cuota1);
        inst.setCuota2(cuota2);
        inst.setCuota3(cuota3);
        inst.setCuota4(cuota4);
        inst.setCuota5(cuota5);
        inst.setCuota6(cuota6);
        inst.setCuota7(cuota7);
        inst.setT_campo_hasta(t_campo_hasta);
        inst.setT_campo_desde(t_campo_desde);
        inst.setP_temporal(p_temporal);
        inst.setP_final(p_final);
        inst.setDesc_obj(desc_obj);
        inst.setDia_mes(dia_mes);
        inst.setPlanificacion(planificacion);
        inst.setPlazo_papi_01(plazo_papi_01);
        inst.setPlazo_papi_02(plazo_papi_02);
        inst.setPlazo_papi_03(plazo_papi_03);
        inst.setPlazo_papi_04(plazo_papi_04);
        inst.setPlazo_capi_01(plazo_capi_01);
        inst.setPlazo_capi_02(plazo_capi_02);
        inst.setPlazo_capi_03(plazo_capi_03);
        inst.setRemun_01(remun_01);
        inst.setRemun_02(remun_02);
        inst.setPack_01(pack_01);
        inst.setContacto_01(contacto_01);
        inst.setContacto_02(contacto_02);
        inst.setContacto_03(contacto_03);
        inst.setContacto_04(contacto_04);
        inst.setContacto_05(contacto_05);
        inst.setIncent_dinero(incent_dinero);
        inst.setIncent_voucher(incent_voucher);
        inst.setIncent_regalo(incent_regalo);
        inst.setIncent_especif(incent_especif);
        inst.setQue_papi(que_papi);
        inst.setQue_capi(que_capi);
        inst.setQue_movil(que_movil);
        inst.setQue_mixto(que_mixto);
        inst.setQue_otro(que_otro);
        inst.setDonde_casa(donde_casa);
        inst.setDonde_central(donde_central);
        inst.setDonde_calle(donde_calle);
        inst.setDonde_tienda(donde_tienda);
        inst.setDonde_otro(donde_otro);

        
	    instructivo.updateInstructivo(inst);   
		
        
		result.setResult(1);
		result.setText("Instructivo <strong>2</strong> a sido <strong>MODIFICADO</strong> en el sistema! ");
		
		return result;
		
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
