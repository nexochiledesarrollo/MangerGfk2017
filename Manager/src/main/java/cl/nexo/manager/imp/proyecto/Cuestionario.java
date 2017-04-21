package cl.nexo.manager.imp.proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.proyecto.AccessCuestionario;
import cl.nexo.manager.obj.proyecto.ObjEstudio;
import cl.nexo.manager.obj.proyecto.ObjEstudioCuestionario;
import cl.nexo.manager.obj.proyecto.ObjResultCreaCotOp;


public class Cuestionario implements AccessCuestionario {
	private static final Logger logger = Logger.getLogger(Cuestionario.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	
	@Override
	public ObjEstudioCuestionario getCuestionarioByIdOperacion(int id){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		
		
		ObjEstudioCuestionario cu = new ObjEstudioCuestionario();
		
		Connection conn = null;
		
		String query="SELECT id_cuest "
					  +"    ,id_operacion "
					  +"    ,num_cuest_unic_cuest "
					  +"    ,por_cuest_sup_cuest "
					  +"    ,por_cuest_camb_cuest "
					  +"    ,des_camb_cuest "
					  +"    ,num_resp_ab_cuest "
					  +"    ,por_resp_open_end_cuest "
					  +"    ,esp_num_otra_cuest "
					  +"    ,esp_por_otro_men_cuest "
					  +"    ,cust_trad_req_cuest "
					  +"    ,otra_info_esp_cuest "
					  +"    ,num_total_preg_prog_cuest "
					  +"    ,esp_req_exp_cuest "
					  +"    ,otro_cati_cuest "
					  +"    ,num_tot_preg_prog_cati_cuest "
					  +"    ,video_cuest "
					  +"    ,file_cuest "
					  +"    ,audio_cuest "
					  +"    ,esp_req_exp2_cuest "
					  +"    ,otro_cawi_capi_cuest "
					 +" FROM man_proyecto_manager_cuestionario "
				  +" WHERE  "
				  +" id_operacion = " + id;
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  logger.debug(query);
			  ResultSet rs = ps.executeQuery();
			  if (rs.next()) {
				  
				  cu.setId_cuest(rs.getInt("id_cuest"));
				  cu.setId_operacion(rs.getInt("id_operacion"));
				  cu.setNum_cuest_unic_cuest(rs.getInt("num_cuest_unic_cuest"));
				  cu.setPor_cuest_sup_cuest(rs.getInt("por_cuest_sup_cuest"));
				  cu.setPor_cuest_camb_cuest(rs.getInt("por_cuest_camb_cuest"));
				  cu.setDes_camb_cuest(rs.getString("des_camb_cuest"));
				  cu.setNum_resp_ab_cuest(rs.getInt("num_resp_ab_cuest"));
				  cu.setPor_resp_open_end_cuest(rs.getInt("por_resp_open_end_cuest"));
				  cu.setEsp_num_otra_cuest(rs.getString("esp_num_otra_cuest"));
				  cu.setEsp_por_otro_men_cuest(rs.getString("esp_por_otro_men_cuest"));
				  cu.setCust_trad_req_cuest(rs.getString("cust_trad_req_cuest"));
				  cu.setOtra_info_esp_cuest(rs.getString("num_total_preg_prog_cuest"));
				  cu.setEsp_req_exp_cuest(rs.getString("esp_req_exp_cuest"));
				  cu.setOtro_cati_cuest(rs.getString("otro_cati_cuest"));
				  cu.setNum_tot_preg_prog_cati_cuest(rs.getInt("num_tot_preg_prog_cati_cuest"));
				  cu.setVideo_cuest(rs.getString("video_cuest"));
				  cu.setFile_cuest(rs.getString("file_cuest"));
				  cu.setAudio_cuest(rs.getString("audio_cuest"));
				  cu.setEsp_req_exp2_cuest(rs.getString("esp_req_exp2_cuest"));
				  cu.setOtro_cawi_capi_cuest(rs.getString("otro_cawi_capi_cuest"));
				  
			  }else{
				  cu.setId_operacion(id);
				  cu.setId_cuest(0);
			  }
			  
			  return cu;
			  
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
	public int getIdCuestionarioByIdOperacion(int id){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		
		
		int cu = 0;
		
		Connection conn = null;
		
		String query="SELECT id_cuest "
					  +"    ,id_operacion "
					 +" FROM man_proyecto_manager_cuestionario "
				  +" WHERE  "
				  +" id_operacion = " + id;
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  logger.debug(query);
			  ResultSet rs = ps.executeQuery();
			  if (rs.next()) {
				  cu = rs.getInt("id_cuest");
			  
			  }else{
				  
				  cu = 0;
			  }
			  
			  return cu;
			  
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
	public ObjResultCreaCotOp setCuestionario(ObjEstudioCuestionario cu){
		
		ObjResultCreaCotOp result = new ObjResultCreaCotOp();
		
		Connection conn = null;
		String query=" INSERT INTO man_proyecto_manager_cuestionario "
				     +"      (id_operacion "
				     +"      ,num_cuest_unic_cuest "
				     +"      ,por_cuest_sup_cuest "
				     +"      ,por_cuest_camb_cuest "
				     +"      ,des_camb_cuest "
				     +"      ,num_resp_ab_cuest "
				     +"      ,por_resp_open_end_cuest "
				     +"      ,esp_num_otra_cuest "
				     +"      ,esp_por_otro_men_cuest "
				     +"      ,cust_trad_req_cuest "
				     +"      ,otra_info_esp_cuest "
				     +"      ,num_total_preg_prog_cuest "
				     +"      ,esp_req_exp_cuest "
				     +"      ,otro_cati_cuest "
				     +"      ,num_tot_preg_prog_cati_cuest "
				     +"      ,video_cuest "
				     +"      ,file_cuest "
				     +"      ,audio_cuest "
				     +"      ,esp_req_exp2_cuest "
				     +"      ,otro_cawi_capi_cuest)  "
				     +" VALUES  "
				     +"      ("+cu.getId_operacion()+"  "
				     +"      ,"+cu.getNum_cuest_unic_cuest()+"  "
				     +"      ,"+cu.getPor_cuest_sup_cuest()+"  "
				     +"      ,"+cu.getPor_cuest_camb_cuest()+"  "
				     +"      ,'"+cu.getDes_camb_cuest()+"'  "
				     +"      ,"+cu.getNum_resp_ab_cuest()+" "
				     +"      ,"+cu.getPor_resp_open_end_cuest()+" "
				     +"      ,'"+cu.getEsp_num_otra_cuest()+"' "
				     +"      ,'"+cu.getEsp_por_otro_men_cuest()+"' "
				     +"      ,'"+cu.getCust_trad_req_cuest()+"' "
				     +"      ,'"+cu.getOtra_info_esp_cuest()+"' "
				     +"      ,"+cu.getNum_total_preg_prog_cuest()+" "
				     +"      ,'"+cu.getEsp_req_exp_cuest()+"' "
				     +"      ,'"+cu.getOtro_cati_cuest()+"' "
				     +"      ,"+cu.getNum_tot_preg_prog_cati_cuest()+" "
				     +"      ,'"+cu.getVideo_cuest()+"' "
				     +"      ,'"+cu.getFile_cuest()+"' "
				     +"      ,'"+cu.getAudio_cuest()+"' "
				     +"      ,'"+cu.getEsp_req_exp2_cuest()+"' "
				     +"      ,'"+cu.getOtro_cawi_capi_cuest()+"' ) ";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			
			
			result.setId_operacion(cu.getId_operacion());
			result.setId_cuestionario(this.getIdCuestionarioByIdOperacion(cu.getId_operacion()));
			
			return result;
			
    	} catch (SQLException e) {
    		//return 2;
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
	public ObjResultCreaCotOp updateCuestionario(ObjEstudioCuestionario cu){
		
		ObjResultCreaCotOp result = new ObjResultCreaCotOp();
		
		Connection conn = null;
		String query=" UPDATE man_proyecto_manager_cuestionario "
					 +"  SET id_operacion = "+cu.getId_operacion()+" "
					 +"     ,num_cuest_unic_cuest = "+cu.getNum_cuest_unic_cuest()+" "
					 +"     ,por_cuest_sup_cuest = "+cu.getPor_cuest_sup_cuest()+" "
					 +"     ,por_cuest_camb_cuest = "+cu.getPor_cuest_camb_cuest()+" "
					 +"     ,des_camb_cuest = '"+cu.getDes_camb_cuest()+"' "
					 +"     ,num_resp_ab_cuest = "+cu.getNum_resp_ab_cuest()+" "
					 +"     ,por_resp_open_end_cuest = "+cu.getPor_resp_open_end_cuest()+" "
					 +"     ,esp_num_otra_cuest = '"+cu.getEsp_num_otra_cuest()+"' "
					 +"     ,esp_por_otro_men_cuest = '"+cu.getEsp_por_otro_men_cuest()+"' "
					 +"     ,cust_trad_req_cuest = '"+cu.getCust_trad_req_cuest()+"' "
					 +"     ,otra_info_esp_cuest = '"+cu.getOtra_info_esp_cuest()+"' "
					 +"     ,num_total_preg_prog_cuest = "+cu.getNum_total_preg_prog_cuest()+" "
					 +"     ,esp_req_exp_cuest = '"+cu.getEsp_req_exp_cuest()+"' "
					 +"     ,otro_cati_cuest = '"+cu.getOtro_cati_cuest()+"' "
					 +"     ,num_tot_preg_prog_cati_cuest = "+cu.getNum_tot_preg_prog_cati_cuest()+" "
					 +"     ,video_cuest = '"+cu.getVideo_cuest()+"' "
					 +"     ,file_cuest = '"+cu.getFile_cuest()+"' "
					 +"     ,audio_cuest = '"+cu.getAudio_cuest()+"' "
					 +"     ,esp_req_exp2_cuest = '"+cu.getEsp_req_exp2_cuest()+"' "
					 +"     ,otro_cawi_capi_cuest = '"+cu.getOtro_cawi_capi_cuest()+"' "
					+" WHERE  "
					+"	id_operacion = "+cu.getId_operacion();
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			
			
			result.setId_operacion(cu.getId_operacion());
			result.setId_cuestionario(cu.getId_cuest());
			
			return result;
			
    	} catch (SQLException e) {
    		//return 2;
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
	public int deleteCuestionario(int id){
		
		int result = 0;
		
		Connection conn = null;
		String query=" DELETE FROM man_proyecto_manager_cuestionario "
					 +"WHERE id_operacion = "+id +" ";


		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ps.executeUpdate();
			
			
			result=1;
			
			
			return result;
			
    	} catch (SQLException e) {
    		//return 2;
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
