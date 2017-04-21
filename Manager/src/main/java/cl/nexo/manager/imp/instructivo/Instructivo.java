package cl.nexo.manager.imp.instructivo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import cl.nexo.manager.access.apoderado.ApoderadoAccess;
import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.access.instructivo.InstructivoAccess;
import cl.nexo.manager.access.login.AccessPerfil;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.password.AccessPassword;
import cl.nexo.manager.access.server.mail.AccessServerMail;
import cl.nexo.manager.access.server.mail.plantillas.AccessPlantillasLogin;
import cl.nexo.manager.obj.apoderado.ObjListApoderado;
import cl.nexo.manager.obj.instructivo.ObjInstructivo;
import cl.nexo.manager.obj.login.ObjListManUser;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.tools.ObjComboSelect2ValueInt;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;


public class Instructivo implements InstructivoAccess {
	
	private static final Logger logger = Logger.getLogger(Instructivo.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	


	@Override
	public int crearInstructivo(ObjInstructivo inst) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		
		Connection conn = null;
    	
    	String query = "INSERT INTO man_instructivo "
    				+"	(id_operacion "
			        +"	 ,rep_ola_estudio "
			        +"	 ,carta_pre_anuncio "
			        +"	 ,loi "
			        +"   ,incidencia "
			        + "  ,tasa_productividad "
					+ "  ,proposito_invest "
					+ "  ,transfodo "
					+ "  ,definicion_grupo "
					+ "  ,grupo_objetivo2 "
					+ "  ,estructura "
					+ "  ,glosario "
					+ "  ,quest_papi_1 "
					+ "  ,quest_papi_2 "
					+ "  ,quest_capi_1 "
					+ "  ,quest_capi_2 "
					+ "  ,quest_capi_3 "
					+ "  ,manejo_muestra_1 "
					+ "  ,manejo_muestra_2 "
					+ "  ,manejo_muestra_3 "
					+ "  ,manejo_muestra_4 "
					+ "  ,manejo_muestra_5 "
					+ "  ,revelar_cliente "
					+ "  ,cuando_revelar ";
                    if(!inst.getP_temporal().equals("")){
						query = query  +"   ,p_temporal ";
					}	
						
					if(!inst.getP_final().equals("")){
						query = query  +"   ,p_final ";
					}

					query = query+"   ,desc_obj "
				
			
					+ "  ,dia_mes "
					+ "  ,planificacion "
					+ "  ,plazo_papi_01 "
					+ "  ,plazo_papi_02 "
					+ "  ,plazo_papi_03 "
					+ "  ,plazo_papi_04 "
					+ "  ,plazo_capi_01 "
					+ "  ,plazo_capi_02 "
					+ "  ,plazo_capi_03 "
					+ "  ,remun_01 "
					+ "  ,remun_02 "
					+ "  ,pack_01 "
					+ "  ,contacto_01 "
					+ "  ,contacto_02 "
					+ "  ,contacto_03 "
					+ "  ,contacto_04 "
					+ "  ,contacto_05 "
					
					+ "  ,incent_dinero "
					+ "  ,incent_voucher "
					+ "  ,incent_regalo "
					+ "  ,incent_especif "
			        
					+ "  ,que_papi "
					+ "  ,que_capi "
					+ "  ,que_movil "
					+ "  ,que_mixto "
					+ "  ,que_otro "
								  
					+ "  ,donde_casa "
					+ "  ,donde_central "
					+ "  ,donde_calle "
					+ "  ,donde_tienda "			        
					+ "  ,donde_otro )"			        
			        
			        
			        +" VALUES "
			        +"   ("+inst.getId_operacion() +" "
			        +"   ,'"+inst.isRep_ola()  +"' "
			        +"   ,'"+inst.isCarta() +"' "
			        +"   ,"+inst.getLoi() +" "
			        +"   ,"+inst.getIncidencia() +" "
			        +"   ,"+inst.getTasa() + ""	
			        +"   ,'"+inst.getVista_1() + "'"
			    	+"   ,'"+inst.getVista_2() + "'"
			    	+"   ,'"+inst.getGrupo_1() + "'"
			    	+"   ,'"+inst.getGrupo_2() + "'"
			    	+"   ,'"+inst.getEstructura() + "'"
			    	+"   ,'"+inst.getGlosario() + "'"
			    	+"   ,'"+inst.getQuest_papi1() + "'"
			    	+"   ,'"+inst.getQuest_papi2() + "'"
			    	+"   ,'"+inst.getQuest_capi1() + "'"
			    	+"   ,'"+inst.getQuest_capi2() + "'"
			    	+"   ,'"+inst.getQuest_capi3() + "'"
			    	+"   ,'"+inst.getCuota1() + "'"
			    	+"   ,'"+inst.isCuota2() + "'"
			    	+"   ,'"+inst.getCuota3() + "'"
			    	+"   ,'"+inst.getCuota4() + "'"
			    	+"   ,'"+inst.getCuota5() + "'"
			    	+"   ,'" +inst.isCuota6() + "'"
			    	+"   ," +inst.getCuota7() + "";
			    	//+"   ,'"+inst.getT_campo_desde() + "'"
			    	//+"   ,'"+inst.getT_campo_hasta() + "'"
		    	
			    	if(!inst.getP_temporal().equals("")){    
	    				query  = query +"   ,'"+inst.getP_temporal()+"'  ";
	    			}    
			    	
			    	if(!inst.getP_final().equals("")){    
	    				query  = query +"   ,'"+inst.getP_final()+"'  ";
	    			} 

			    	query = query +"   ,'"+inst.getDesc_obj() + "'"
			    	+"   ,'"+inst.getDia_mes() + "'"
			    	+"   ,'"+inst.getPlanificacion() + "'"
			    	+"   ,'"+inst.getPlazo_papi_01() + "'"
			    	+"   ,'"+inst.getPlazo_papi_02() + "'"
			    	+"   ,'"+inst.getPlazo_papi_03() + "'"
			    	+"   ,'"+inst.getPlazo_papi_04() + "'"
			    	+"   ,'"+inst.getPlazo_capi_01() + "'"
			    	+"   ,'"+inst.getPlazo_capi_02() + "'"
			    	+"   ,'"+inst.getPlazo_capi_03() + "'"
			    	+"   ,'"+inst.getRemun_01() + "'"
			    	+"   ,'"+inst.getRemun_02() + "'"
			    	+"   ,'"+inst.getPack_01() + "'"
			    	+"   ,'"+inst.getContacto_01() + "'"
			    	+"   ,'"+inst.getContacto_02() + "'"
			    	+"   ,'"+inst.getContacto_03() + "'"
			    	+"   ,'"+inst.getContacto_04() + "'"
			    	+"   ,'"+inst.getContacto_05() + "'"
			    	+"   ,'"+inst.isIncent_dinero() + "'"
			    	+"   ,'"+inst.isIncent_voucher() + "'"
			    	+"   ,'"+inst.isIncent_regalo() + "'"
			    	+"   ,'"+inst.getIncent_especif() + "' "
    	            +"   ,'"+inst.isQue_papi() + "'"
			    	+"   ,'"+inst.isQue_capi()+ "'"
			    	+"   ,'"+inst.isQue_movil() + "'"
			    	+"   ,'"+inst.isQue_mixto() + "'"
			    	+"   ,'"+inst.getQue_otro() + "' "
			    	+"   ,'"+inst.isDonde_casa() + "'"
			    	+"   ,'"+inst.isDonde_central()+ "'"
			    	+"   ,'"+inst.isDonde_calle() + "'"
			    	+"   ,'"+inst.isDonde_tienda() + "'"
			    	+"   ,'"+inst.getDonde_otro() + "' )";
			    	

    	
    	logger.info(query);
    	
    	try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.info(query);
			ps.executeUpdate();
			return 1;
    	} catch (SQLException e) {
    		
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
    	

	}



	@Override
	public int crearInstructivoCapi(ObjInstructivo inst) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		
		Connection conn = null;
    	
    	String query = "INSERT INTO man_instructivo "
    				+"	(id_operacion "
    			    +"	,nomb_guion "
                    +"	,nomb_servidor "
 					+"  ,tasa_productividad "
                    +"  ,paises "
					+"	,loi "
					+"  ,incidencia " 
					+"	,rep_ola_estudio "
					+"	,carta_pre_anuncio "
                    +"  ,proposito_invest "
					+"  ,transfodo "
					+"  ,b2b "
					+"  ,b2c "
					+"  ,b2b_porc "
					+"  ,b2c_porc "
					+"  ,modo_mixto "
					+"  ,coment_adic "
					+"  ,definicion_grupo "
					+"  ,grupo_objetivo2 "
					+"  ,estructura "
					+"  ,glosario "
					+"  ,muestra_gfk "
					+"  ,especif_muest_gfk "
					+"  ,rdd "
					+"  ,muestra_cliente "
					+"  ,base_datos "
					+"  ,otro "
					+"  ,especificar_otro "
					+"  ,incluye_nom_contact "
					+"  ,porcent_ambos "
					+"  ,permit_recom "
					+"  ,reglas_recom "
					+"  ,num_recom "
					+"  ,comemt_adic_muestra "
                    +"  ,revelar_cliente "
					+"  ,cuando_revelar "
    	            +"  ,instruc_sup1 "
                    +"  ,instruc_sup2 ";
   	      
    	
    	
                    if(!inst.getP_temporal().equals("")){
						query = query  +"   ,p_temporal ";
					}	
						
					if(!inst.getP_final().equals("")){
						query = query  +"   ,p_final ";
					}

					query = query+"   ,desc_obj "
				
			
					+ "  ,dia_mes "
					+ "  ,planificacion "
					
					+ "  ,remun_01 "

			  
					+"  ,incent_dinero "
					+"  ,incent_voucher "
					+"  ,incent_regalo "
					+"  ,incent_especif "
                    +"  ,realiza_cita "
					+"  ,coment_incent )"
    	          

			        
			        +" VALUES "
			        +"   ("+inst.getId_operacion() +" "
			        +"   ,'"+inst.getNombre_guion()  +"' "
			        +"   ,'"+inst.getNombre_servidor()  +"' "
			        +"   ,"+inst.getTasa() + ""				        
			        +"   ,'"+inst.getPaises() +"' "			        
			        +"   ,"+inst.getLoi() +" "
			        +"   ,"+inst.getIncidencia() +" "			        
			        +"   ,'"+inst.isRep_ola()  +"' "
			        +"   ,'"+inst.isCarta() +"' "			        
			        +"   ,'"+inst.getVista_1() + "'"
			    	+"   ,'"+inst.getVista_2() + "'"
					+"   ,'"+inst.isB2B()  +"' "
					+"   ,'"+inst.isB2C()  +"' "
					+"   ,'"+inst.getPorc_B2B()  +"' "
					+"   ,'"+inst.getPorc_B2C()  +"' "	
					+"   ,'"+inst.getMet_mixto()  +"' "
					+"   ,'"+inst.getComent()  +"' "
                    +"   ,'"+inst.getGrupo_1() + "'"
			    	+"   ,'"+inst.getGrupo_2() + "'"
			    	+"   ,'"+inst.getEstructura() + "'"
			    	+"   ,'"+inst.getGlosario() + "'"
                    +"   ,'"+inst.isMuestra_ent()  +"' "
					+"   ,'"+inst.getEspecif()  +"' "	
					+"   ,'"+inst.isRdd()  +"' "
					+"   ,'"+inst.isMuestra_cliente()  +"' "
                    +"   ,'"+inst.isBase_datos() + "'"
			    	+"   ,'"+inst.isOtro() + "'"
			    	+"   ,'"+inst.getEspecif_otro() + "'"
			    	+"   ,'"+inst.isNombre_contact() + "'"	
			    	+"   ,'"+inst.getPorc_nombre() + "'"
			    	+"   ,'"+inst.isPermit_recom() + "'"
			    	+"   ,'"+inst.getEspecif_recom() + "'"
			    	+"   ,'"+inst.getN_recom() + "'"
			    	+"   ,'"+inst.getComent_adic() + "'"
			    	+"   ,'" +inst.isCuota14() + "'"
			    	+"   ," +inst.getCuota15() + ""
					+"   ,'"+inst.getInst_sup1() + "'"
			    	+"   ,'"+inst.getInst_sup2() + "'";					
					//+"   ,'"+inst.getT_campo_desde() + "'"
			    	//+"   ,'"+inst.getT_campo_hasta() + "'"
		    	
			    	if(!inst.getP_temporal().equals("")){    
	    				query  = query +"   ,'"+inst.getP_temporal()+"'  ";
	    			}    
			    	
			    	if(!inst.getP_final().equals("")){    
	    				query  = query +"   ,'"+inst.getP_final()+"'  ";
	    			} 

			    	query = query +"   ,'"+inst.getDesc_obj() + "'"
			    	+"   ,'"+inst.getDia_mes() + "'"
			    	+"   ,'"+inst.getPlanificacion() + "'"
			    	
			    	
			    	+"   ,'"+inst.getRemun_01() + "'"
			    	
			    	+"   ,'"+inst.isIncent_dinero() + "'"
			    	+"   ,'"+inst.isIncent_voucher() + "'"
			    	+"   ,'"+inst.isIncent_regalo() + "'"
			    	
			    	+"   ,'"+inst.getIncent_especif() + "'"
			    	+"   ,'"+inst.isRealiza_cita() + "'"
			    	+"   ,'"+inst.getComent_incent() + "' )";
			    	

    	
    	logger.info(query);
    	
    	try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.info(query);
			ps.executeUpdate();
			return 1;
    	} catch (SQLException e) {
    		
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
    	

	}


	
	@Override
	public int updateInstructivoCapi(ObjInstructivo inst) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		
		Connection conn = null;

    	
    	String query = "UPDATE man_instructivo "    			
    			    +"	SET nomb_guion = '" +inst.getNombre_guion()  +"'"
                    +"	,nomb_servidor ='" +inst.getNombre_servidor()  +"'"
 					+"  ,tasa_productividad ="+inst.getTasa() + ""
                    +"  ,paises ='"+inst.getPaises() +"' "	
					+"	,loi ="+inst.getLoi() +" "
					+"  ,incidencia ="+inst.getIncidencia() +" "
					+"	,rep_ola_estudio ='"+inst.isRep_ola()  +"' "
					+"	,carta_pre_anuncio ='"+inst.isCarta() +"' "
                    +"  ,proposito_invest ='"+inst.getVista_1() + "'"
					+"  ,transfodo ='"+inst.getVista_2() + "'"
					+"  ,b2b ='"+inst.isB2B()  +"' "
					+"  ,b2c ='"+inst.isB2C()  +"' "
					+"  ,b2b_porc ='"+inst.getPorc_B2B()  +"' "
					+"  ,b2c_porc ='"+inst.getPorc_B2C()  +"' "
					+"  ,modo_mixto ='"+inst.getMet_mixto()  +"' "
					+"  ,coment_adic ='"+inst.getComent()  +"' "
					+"  ,definicion_grupo ='"+inst.getGrupo_1() + "'"
					+"  ,grupo_objetivo2 ='"+inst.getGrupo_2() + "'"
					+"  ,estructura ='"+inst.getEstructura() + "'"
					+"  ,glosario ='"+inst.getGlosario() + "'"
					+"  ,muestra_gfk ='"+inst.isMuestra_ent()  +"' "
					+"  ,especif_muest_gfk ='"+inst.getEspecif()  +"' "
					+"  ,rdd ='"+inst.isRdd()  +"' "
					+"  ,muestra_cliente ='"+inst.isMuestra_cliente()  +"' "
					+"  ,base_datos ='"+inst.isBase_datos() + "'"
					+"  ,otro ='"+inst.isOtro() + "'"
					+"  ,especificar_otro ='"+inst.getEspecif_otro() + "'"
					+"  ,incluye_nom_contact ='"+inst.isNombre_contact() + "'"	
					+"  ,porcent_ambos ='"+inst.getPorc_nombre() + "'"
					+"  ,permit_recom ='"+inst.isPermit_recom() + "'"
					+"  ,reglas_recom ='"+inst.getEspecif_recom() + "'"
					+"  ,num_recom ='"+inst.getN_recom() + "'"
					+"  ,comemt_adic_muestra ='"+inst.getComent_adic() + "'"
                    +"  ,revelar_cliente ='" +inst.isCuota14() + "'"
					+"  ,cuando_revelar =" +inst.getCuota15() + ""
    	            +"  ,instruc_sup1 ='"+inst.getInst_sup1() + "'"
                    +"  ,instruc_sup2 ='"+inst.getInst_sup2() + "'";
   	      
    	
    	
                    if(!inst.getP_temporal().equals("")){
						query = query  +"   ,p_temporal ='"+inst.getP_temporal()+"'  ";
					}	
						
					if(!inst.getP_final().equals("")){
						query = query  +"   ,p_final ='"+inst.getP_final()+"'  ";
					}

					query = query+"   ,desc_obj ='"+inst.getDesc_obj() + "'"
				
			
					+ "  ,dia_mes ='"+inst.getDia_mes() + "'"
					+ "  ,planificacion ='"+inst.getPlanificacion() + "'"
					
					+ "  ,remun_01 ='"+inst.getRemun_01() + "'"

			  
					+"  ,incent_dinero ='"+inst.isIncent_dinero() + "'"
					+"  ,incent_voucher ='"+inst.isIncent_voucher() + "'"
					+"  ,incent_regalo ='"+inst.isIncent_regalo() + "'"
					+"  ,incent_especif ='"+inst.getIncent_especif() + "'"
                    +"  ,realiza_cita ='"+inst.isRealiza_cita() + "'"
					+"  ,coment_incent ='"+inst.getComent_incent() + "'  WHERE id_operacion ="+inst.getId_operacion() +" ";
    	          

			        
			        

    	
    	logger.info(query);
    	
    	try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.info(query);
			ps.executeUpdate();
			return 1;
    	} catch (SQLException e) {
    		
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
    	

	}
	
	
	
	

	@Override
	public ObjInstructivo getDetailInstructivoCatiById(int id) {

		
		Connection conn = null;
	
		String query = "SELECT * "  
			  
			     +" FROM man_instructivo tb where id_operacion="+ id + "";
		
		logger.info("SQL " + query);
		
		  try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  logger.debug(query);
			  ResultSet rs = ps.executeQuery();
			
			      ObjInstructivo inst = new ObjInstructivo();
				 
				  while (rs.next()) { 
					
				    inst.setNombre_guion(rs.getString("nomb_guion"));
				    
			        inst.setNombre_servidor(rs.getString("nomb_servidor"));
			        
			        inst.setTasa(rs.getInt("tasa_productividad"));
			        
			        inst.setPaises(rs.getString("paises"));
			        
			        inst.setLoi(rs.getInt("loi"));
			        inst.setIncidencia(rs.getInt("incidencia"));
			        
			        inst.setRep_ola(rs.getBoolean("rep_ola_estudio"));
			        
			        inst.setCarta(rs.getBoolean("carta_pre_anuncio"));
			        
			        inst.setIncent_dinero(rs.getBoolean("incent_dinero"));
			        
			        inst.setIncent_voucher(rs.getBoolean("incent_voucher"));
			        
			        inst.setIncent_regalo(rs.getBoolean("incent_regalo"));
			        inst.setIncent_especif(rs.getString("incent_especif"));
			        inst.setRealiza_cita(rs.getBoolean("realiza_cita"));
			        inst.setComent_incent(rs.getString("coment_incent"));
			        
			        inst.setVista_1(rs.getString("proposito_invest"));
			        inst.setVista_2(rs.getString("transfodo"));
			        
			        inst.setB2B(rs.getBoolean("b2b"));
			        inst.setB2C(rs.getBoolean("b2c"));
			        inst.setPorc_B2B(rs.getString("b2b_porc"));
			        inst.setPorc_B2C(rs.getString("b2c_porc"));
			        
			        inst.setMet_mixto(rs.getString("modo_mixto"));
			        inst.setComent(rs.getString("coment_adic"));
			        
			        inst.setGrupo_1(rs.getString("definicion_grupo"));
			        inst.setGrupo_2(rs.getString("grupo_objetivo2"));
			        
			        inst.setEstructura(rs.getString("estructura"));
			        inst.setGlosario(rs.getString("glosario"));
			        
			        inst.setMuestra_ent(rs.getBoolean("muestra_gfk"));
			        inst.setEspecif(rs.getString("especif_muest_gfk"));
			        inst.setRdd(rs.getBoolean("rdd"));
			        inst.setMuestra_cliente(rs.getBoolean("muestra_cliente"));
			        inst.setBase_datos(rs.getBoolean("base_datos"));
			        inst.setOtro(rs.getBoolean("otro"));
			        inst.setEspecif_otro(rs.getString("especificar_otro"));
			        inst.setNombre_contact(rs.getBoolean("incluye_nom_contact"));
			        inst.setPorc_nombre(rs.getString("porcent_ambos"));
			        inst.setPermit_recom(rs.getBoolean("permit_recom"));
			        inst.setEspecif_recom(rs.getString("reglas_recom"));
			        inst.setN_recom(rs.getString("num_recom").trim());
			        inst.setComent_adic(rs.getString("comemt_adic_muestra"));
			        inst.setCuota14(rs.getBoolean("revelar_cliente"));
			        inst.setCuota15(rs.getInt("cuando_revelar"));
			       
			        inst.setInst_sup1(rs.getString("instruc_sup1"));
			        inst.setInst_sup2(rs.getString("instruc_sup2"));
			       
			        //inst.setT_campo_hasta(t_campo_hasta);
			        //inst.setT_campo_desde(t_campo_desde);
			        inst.setP_temporal(rs.getString("p_temporal"));
			        inst.setP_final(rs.getString("p_final"));
			        inst.setDesc_obj(rs.getString("desc_obj"));
			        inst.setDia_mes(rs.getString("dia_mes"));
			        inst.setPlanificacion(rs.getString("planificacion"));
			        
			        inst.setRemun_01(rs.getString("remun_01"));
			        
			        logger.info(inst.getRemun_01());

				  } 
			  return inst;
			  
		} catch (Exception e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					
					
				}
			}
		}       
		
		

	
	}		
		
	
	
	
	
	@Override
	public ObjInstructivo getDetailInstructivoById(int id) {

		
		Connection conn = null;
	
		String query = "SELECT * "  
			  
			     +" FROM man_instructivo tb where id_operacion="+ id + "";
		
		logger.info("SQL " + query);
		
		  try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  logger.debug(query);
			  ResultSet rs = ps.executeQuery();
			
			      ObjInstructivo inst = new ObjInstructivo();
				 
				  while (rs.next()) { 
					
					    inst.setRep_ola(rs.getBoolean("rep_ola_estudio"));
				        inst.setCarta(rs.getBoolean("carta_pre_anuncio"));
				        inst.setLoi(rs.getInt("loi"));
				        inst.setIncidencia(rs.getInt("incidencia"));
				        inst.setTasa(rs.getInt("tasa_productividad"));
				        inst.setVista_1(rs.getString("proposito_invest"));
				        inst.setVista_2(rs.getString("transfodo"));
				        inst.setGrupo_1(rs.getString("definicion_grupo"));
				        inst.setGrupo_2(rs.getString("grupo_objetivo2"));
				        inst.setEstructura(rs.getString("estructura"));
				        inst.setGlosario(rs.getString("glosario"));
				        inst.setQuest_papi1(rs.getString("quest_papi_1"));
				        inst.setQuest_papi2(rs.getString("quest_papi_2"));
				        inst.setQuest_capi1(rs.getString("quest_capi_1"));
				        inst.setQuest_capi2(rs.getString("quest_capi_2"));
				        inst.setQuest_capi3(rs.getString("quest_capi_3"));
				        inst.setCuota1(rs.getString("manejo_muestra_1"));
				        inst.setCuota2(rs.getBoolean("manejo_muestra_2"));
				        inst.setCuota3(rs.getString("manejo_muestra_3"));
				        inst.setCuota4(rs.getString("manejo_muestra_4"));
				        inst.setCuota5(rs.getString("manejo_muestra_5"));
				        inst.setCuota6(rs.getBoolean("revelar_cliente"));
				        inst.setCuota7(rs.getInt("cuando_revelar"));
				        inst.setT_campo_hasta(rs.getString("t_campo_hasta"));
				        inst.setT_campo_desde(rs.getString("t_campo_desde"));
				        inst.setP_temporal(rs.getString("p_temporal"));
				        inst.setP_final(rs.getString("p_final"));
				        inst.setDesc_obj(rs.getString("desc_obj"));
				        inst.setDia_mes(rs.getString("dia_mes"));
				        inst.setPlanificacion(rs.getString("planificacion"));
				        inst.setPlazo_papi_01(rs.getString("plazo_papi_01"));
				        inst.setPlazo_papi_02(rs.getString("plazo_papi_02"));
				        inst.setPlazo_papi_03(rs.getString("plazo_papi_03"));
				        inst.setPlazo_papi_04(rs.getString("plazo_papi_04"));
				        inst.setPlazo_capi_01(rs.getString("plazo_capi_01"));
				        inst.setPlazo_capi_02(rs.getString("plazo_capi_02"));
				        inst.setPlazo_capi_03(rs.getString("plazo_capi_03"));
				        inst.setRemun_01(rs.getString("remun_01"));
				        inst.setRemun_02(rs.getString("remun_02"));
				        inst.setPack_01(rs.getString("pack_01"));
				        inst.setContacto_01(rs.getString("contacto_01"));
				        inst.setContacto_02(rs.getString("contacto_02"));
				        inst.setContacto_03(rs.getString("contacto_03"));
				        inst.setContacto_04(rs.getString("contacto_04"));
				        inst.setContacto_05(rs.getString("contacto_05"));
				        inst.setIncent_dinero(rs.getBoolean("incent_dinero"));
				        inst.setIncent_voucher(rs.getBoolean("incent_voucher"));
				        inst.setIncent_regalo(rs.getBoolean("incent_regalo"));
				        inst.setIncent_especif(rs.getString("incent_especif"));
				        inst.setQue_papi(rs.getBoolean("que_papi"));
				        inst.setQue_capi(rs.getBoolean("que_capi"));
				        inst.setQue_movil(rs.getBoolean("que_movil"));
				        inst.setQue_mixto(rs.getBoolean("que_mixto"));
				        inst.setQue_otro(rs.getString("que_otro"));
				        inst.setDonde_casa(rs.getBoolean("donde_casa"));
				        inst.setDonde_central(rs.getBoolean("donde_central"));
				        inst.setDonde_calle(rs.getBoolean("donde_calle"));
				        inst.setDonde_tienda(rs.getBoolean("donde_tienda"));
				        inst.setDonde_otro(rs.getString("donde_otro"));

				  } 
			  return inst;
			  
		} catch (Exception e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					
					
				}
			}
		}       

	
	}	
	

	@Override
	public int updateInstructivo(ObjInstructivo inst) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		
		Connection conn = null;
		

		String query = "UPDATE man_instructivo "
				
		        +"	 SET rep_ola_estudio ='"+inst.isRep_ola()  +"' "
		        +"	 ,carta_pre_anuncio ='"+inst.isCarta() +"' "
		        +"	 ,loi ="+inst.getLoi() +" "
		        +"   ,incidencia ="+inst.getIncidencia() +" "
		        + "  ,tasa_productividad ="+inst.getTasa() + ""	
				+ "  ,proposito_invest ='"+inst.getVista_1() + "'"
				+ "  ,transfodo ='"+inst.getVista_2() + "'"
				+ "  ,definicion_grupo ='"+inst.getGrupo_1() + "'"
				+ "  ,grupo_objetivo2 ='"+inst.getGrupo_2() + "'"
				+ "  ,estructura ='"+inst.getEstructura() + "'"
				+ "  ,glosario ='"+inst.getGlosario() + "'"
				+ "  ,quest_papi_1 ='"+inst.getQuest_papi1() + "'"
				+ "  ,quest_papi_2 ='"+inst.getQuest_papi2() + "'"
				+ "  ,quest_capi_1 ='"+inst.getQuest_capi1() + "'"
				+ "  ,quest_capi_2 ='"+inst.getQuest_capi2() + "'"
				+ "  ,quest_capi_3 ='"+inst.getQuest_capi3() + "'"
				+ "  ,manejo_muestra_1 ='"+inst.getCuota1() + "'"
				+ "  ,manejo_muestra_2 ='"+inst.isCuota2() + "'"
				+ "  ,manejo_muestra_3 ='"+inst.getCuota3() + "'"
				+ "  ,manejo_muestra_4 ='"+inst.getCuota4() + "'"
				+ "  ,manejo_muestra_5 ='"+inst.getCuota5() + "'"
				+ "  ,revelar_cliente ='" +inst.isCuota6() + "'"
				+ "  ,cuando_revelar =" +inst.getCuota7() + "";
                if(!inst.getP_temporal().equals("")){
					query = query  +"   ,p_temporal ='"+inst.getP_temporal()+"'  ";
				}	
					
				if(!inst.getP_final().equals("")){
					query = query  +"   ,p_final ='"+inst.getP_final()+"'  ";
				}

				query = query+"   ,desc_obj ='"+inst.getDesc_obj() + "'"
			
		
				+ "  ,dia_mes ='"+inst.getDia_mes() + "'"
				+ "  ,planificacion ='"+inst.getPlanificacion() + "'"
				+ "  ,plazo_papi_01 ='"+inst.getPlazo_papi_01() + "'"
				+ "  ,plazo_papi_02 ='"+inst.getPlazo_papi_02() + "'"
				+ "  ,plazo_papi_03 ='"+inst.getPlazo_papi_03() + "'"
				+ "  ,plazo_papi_04 ='"+inst.getPlazo_papi_04() + "'"
				+ "  ,plazo_capi_01 ='"+inst.getPlazo_capi_01() + "'"
				+ "  ,plazo_capi_02 ='"+inst.getPlazo_capi_02() + "'"
				+ "  ,plazo_capi_03 ='"+inst.getPlazo_capi_03() + "'"
				+ "  ,remun_01 ='"+inst.getRemun_01() + "'"
				+ "  ,remun_02 ='"+inst.getRemun_02() + "'"
				+ "  ,pack_01 ='"+inst.getPack_01() + "'"
				+ "  ,contacto_01 ='"+inst.getContacto_01() + "'"
				+ "  ,contacto_02 ='"+inst.getContacto_02() + "'"
				+ "  ,contacto_03 ='"+inst.getContacto_03() + "'"
				+ "  ,contacto_04 ='"+inst.getContacto_04() + "'"
				+ "  ,contacto_05 ='"+inst.getContacto_05() + "'"
				
				+ "  ,incent_dinero ='"+inst.isIncent_dinero() + "'"
				+ "  ,incent_voucher ='"+inst.isIncent_voucher() + "'"
				+ "  ,incent_regalo ='"+inst.isIncent_regalo() + "'"
				+ "  ,incent_especif ='"+inst.getIncent_especif() + "' "
		        
				+ "  ,que_papi ='"+inst.isQue_papi() + "'"
				+ "  ,que_capi ='"+inst.isQue_capi()+ "'"
				+ "  ,que_movil ='"+inst.isQue_movil() + "'"
				+ "  ,que_mixto ='"+inst.isQue_mixto() + "'"
				+ "  ,que_otro ='"+inst.getQue_otro() + "' "
							  
				+ "  ,donde_casa ='"+inst.isDonde_casa() + "'"
				+ "  ,donde_central ='"+inst.isDonde_central()+ "'"
				+ "  ,donde_calle ='"+inst.isDonde_calle() + "'"
				+ "  ,donde_tienda ='"+inst.isDonde_tienda() + "'"			        
				+ "  ,donde_otro ='"+inst.getDonde_otro() + "' WHERE id_operacion ="+inst.getId_operacion() +" ";


    	
    	logger.info(query);
    	
    	try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.info(query);
			ps.executeUpdate();
			return 1;
    	} catch (SQLException e) {
    		
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
    	

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


	
}
