package cl.nexo.manager.imp.cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import cl.nexo.manager.access.cliente.AccessCliente;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.obj.cliente.ObjCliente;
import cl.nexo.manager.obj.tools.ObjComboSelect2ValueInt;
import static org.apache.commons.lang.StringEscapeUtils.*;

public class Cliente implements AccessCliente {
	private static final Logger logger = Logger.getLogger(Cliente.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	@Override
	public ArrayList<ObjComboSelect2ValueInt> getCombo(int id, String place){
		
		Connection conn = null;
		ArrayList<ObjComboSelect2ValueInt> combos = new ArrayList<ObjComboSelect2ValueInt>();
		
		String query="SELECT id_cliente "
					     +" ,nombre_cliente "
					     +" ,rut_cliente "
					  +" FROM man_cliente "
					  +" WHERE " 
					  +" estado_cliente = 1  ";
					  if(id >= 1){
						  query = query+" AND id_industria = "+id+" ";
					  } 
					  query = query +" ORDER BY nombre_cliente ";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();
			   ObjComboSelect2ValueInt combo2 = new ObjComboSelect2ValueInt();
			   combo2.setId(0);
			   combo2.setText(place);
			   combos.add(combo2);
				 while(rs.next()) {
					  ObjComboSelect2ValueInt combo = new ObjComboSelect2ValueInt();
					  combo.setId(rs.getInt("id_cliente"));
					  combo.setText(escapeHtml(rs.getString("nombre_cliente") + " - "+ rs.getString("rut_cliente")));
					  combos.add(combo);
				  }
			 
			  return combos;
			  
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
	public ObjCliente getClienteById(int id){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		
		ObjCliente cliente = new ObjCliente();
		Connection conn = null;
		
		String query = " SELECT id_cliente "
					  +"    ,id_cliente_sam "
					  +"    ,id_cliente_manager "
					  +"    ,id_crm "
					  +"    ,id_sap "
					  +"    ,id_global_client "
					  +"    ,scope_id_client "
					  +"    ,id_industria "
					  +"    ,duns_client "
					  +"    ,id_group_client "
					  +"    ,nombre_cliente "
					  +"    ,tipo_cuenta "
					  +"    ,rut_cliente "
					  +"    ,statussam_cliente "
					  +"    ,rfcsam_cliente "
					  +"    ,direccion_cliente "
					  +"    ,id_comuna "
					  +"    ,id_region "
					  +"    ,id_pais "
					  +"    ,fono_cliente "
					  +"    ,id_giroComercial "
					  +"    ,id_tipocliente "
					  +"    ,falta_cliente "
					  +"    ,salta_cliente "
					  +"    ,fmod_cliente "
					  +"    ,smod_cliente "
					  +"    ,felimina_cliente "
					  +"    ,selimina_cliente "
					  +"    ,elimina_cliente "
					  +"    ,estado_cliente "
					  +" FROM man_cliente "
					  +" WHERE id_cliente = " + id;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();
			   	if(rs.next()) {
			   		cliente.setId_cliente(rs.getInt("id_cliente"));
			   		cliente.setId_cliente_sam(rs.getInt("id_cliente_sam"));
			   		cliente.setId_cliente_manager(rs.getInt("id_cliente_manager"));
			   		cliente.setId_crm(rs.getString("id_crm"));
			   		cliente.setId_sap(rs.getString("id_sap"));
			   		cliente.setId_global_client(rs.getString("id_global_client"));
			   		cliente.setScope_id_client(rs.getString("scope_id_client"));
			   		cliente.setId_industria(rs.getInt("id_industria"));
			   		cliente.setDuns_client(rs.getString("duns_client"));
			   		cliente.setId_group_client(rs.getInt("id_group_client"));
			   		cliente.setNombre_cliente(rs.getString("nombre_cliente"));
			   		cliente.setTipo_cuenta(rs.getInt("tipo_cuenta"));
			   		cliente.setRut_cliente(rs.getString("rut_cliente"));
			   		cliente.setStatussam_cliente(rs.getInt("statussam_cliente"));
			   		cliente.setRfcsam_cliente(rs.getString("rfcsam_cliente"));
			   		cliente.setDireccion_cliente(rs.getString("direccion_cliente"));
			   		cliente.setId_comuna(rs.getInt("id_comuna"));
			   		cliente.setId_region(rs.getInt("id_region"));
			   		cliente.setId_pais(rs.getInt("id_pais"));
			   		cliente.setFono_cliente(rs.getString("fono_cliente"));
			   		cliente.setId_giroComercial(rs.getInt("id_giroComercial"));
			   		cliente.setId_tipocliente(rs.getInt("id_tipocliente"));
			   		int aux_salta=rs.getInt("salta_cliente");
			   		if(aux_salta > 0){
			   			cliente.setSalta_cliente(aux_salta);
			   			cliente.setFalta_cliente(rs.getString("falta_cliente"));
			   			cliente.setStr_salta_cliente(logins.getStrLoginById(aux_salta));
			   		}
			   		int aux_smod = rs.getInt("smod_cliente");
			   		if(aux_smod > 0){
			   			cliente.setSmod_cliente(aux_smod);
			   			cliente.setStr_smod_cliente(logins.getStrLoginById(aux_smod));
			   			cliente.setFmod_cliente(rs.getString("fmod_cliente"));
			   			
			   		}
			   		int aux_seli = rs.getInt("selimina_cliente");
			   		if(aux_seli > 0){
			   			cliente.setSelimina_cliente(aux_seli);
			   			cliente.setStr_selimina_cliente(logins.getStrLoginById(aux_seli));
			   			cliente.setFelimina_cliente(rs.getString("felimina_cliente"));
			   		}
			   		cliente.setElimina_cliente(rs.getInt("elimina_cliente"));
			   		cliente.setEstado_cliente(rs.getInt("estado_cliente"));
			   		
				}
			 
				return cliente;
			  
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
