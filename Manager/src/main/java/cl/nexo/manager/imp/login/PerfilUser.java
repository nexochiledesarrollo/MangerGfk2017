package cl.nexo.manager.imp.login;

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

import cl.nexo.manager.access.login.AccessPerfil;
import cl.nexo.manager.obj.login.ObjExistePermisoModuloPerfil;
import cl.nexo.manager.obj.login.ObjExistePermisoSubDivisionPerfil;
import cl.nexo.manager.obj.login.ObjListManPerfil;
import cl.nexo.manager.obj.login.ObjMenuPermisosModulosPerfil;
import cl.nexo.manager.obj.login.ObjPerfilLogin;
import cl.nexo.manager.obj.login.ObjPermisosModuloPerfil;
import cl.nexo.manager.obj.login.ObjPermisosSubDivisionPerfil;
import cl.nexo.manager.obj.tools.ObjComboSelectValueInt;

public class PerfilUser implements AccessPerfil {
	private static final Logger logger = Logger.getLogger(PerfilUser.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	
	
	@Override
	public int getExistPerfilByName(String perfil){
		int result = 0;
		Connection conn = null;
		
		String query = " SELECT nombre_perfil "
				  +"	 FROM man_perfil_login "
				  +"	 WHERE "
				  +" nombre_perfil = '" + perfil+"'";
				  
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
			ResultSet rs = ps.executeQuery();	
			if(rs.next()) {

				result = 1;
				
			}else{
				
				result = 0;
			}
			
			return result;
			
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
	public ObjPerfilLogin getPerfilById(int id){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		
		
		ObjPerfilLogin perfil = new ObjPerfilLogin();
		Connection conn = null;
		
		String query="SELECT p.id_perfil "
				+"	      ,p.codigo_perfil "
				+"	      ,p.nombre_perfil "
				+"	      ,p.estado_perfil "
				+"	      ,p.id_cliente"
				+"	      ,p.es_admin"
				+"	FROM man_perfil_login p "
				+"	WHERE "
				+"	p.id_perfil = " + id ;
				
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();  
			
			if(rs.next()) {
				  perfil.setId_perfil(rs.getInt("id_perfil"));
				  perfil.setCodigo_perfil(rs.getInt("codigo_perfil"));
				  perfil.setNombre_perfil(rs.getString("nombre_perfil"));
				  perfil.setEstado_perfil(rs.getInt("estado_perfil"));
				  perfil.setId_cliente(rs.getInt("id_cliente"));
				  perfil.setEs_admin(rs.getInt("es_admin"));
			  }
			
			return perfil;
			
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
	public String getStrPerfilById(int id){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		
		
		String perfil = null;
		Connection conn = null;
		
		String query="SELECT p.id_perfil "
				+"	      ,p.codigo_perfil "
				+"	      ,p.nombre_perfil "
				+"	      ,p.estado_perfil "
				+"	      ,p.id_cliente"
				+"	      ,p.es_admin"
				+"	FROM man_perfil_login p "
				+"	WHERE "
				+"	p.id_perfil = " + id ;
				
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();  
			
			if(rs.next()) {
				 
				perfil =   rs.getString("nombre_perfil");
				  
			  }
			
			return perfil;
			
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
	public int getIdPerfilUserByName(String perfil){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		
		
		int id = 0;
		Connection conn = null;
		
		String query="SELECT p.id_perfil "
				+"	      ,p.codigo_perfil "
				+"	      ,p.nombre_perfil "
				+"	      ,p.estado_perfil "
				+"	      ,p.id_cliente"
				+"	      ,p.es_admin"
				+"	FROM man_perfil_login p "
				+"	WHERE "
				+"	p.nombre_perfil = '" + perfil +"'" ;
				
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();  
			
			if(rs.next()) {
				 
				id =   rs.getInt("id_perfil");
				  
			  }
			
			return id;
			
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
	public ArrayList<ObjPerfilLogin> getListPerfilByCliente(int id){
		
		Connection conn = null;
		
		ArrayList<ObjPerfilLogin> perfiles = new ArrayList<ObjPerfilLogin>();
		
		
		String query="SELECT p.id_perfil "
				+"	      ,p.codigo_perfil "
				+"	      ,p.nombre_perfil "
				+"	      ,p.estado_perfil "
				+"	      ,p.id_cliente "
				+"	      ,p.es_admin "
				+"	FROM man_perfil_login p ";
				if(id != 0){
					query =	query +"	WHERE "
								  +"	p.perfil_id = " + id ;
				}
				query =	query +"	ORDER BY  nombre_perfil ";
				
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();  
			while(rs.next()) {
				  ObjPerfilLogin perfil = new ObjPerfilLogin();
				  perfil.setId_perfil(rs.getInt("id_perfil"));
				  perfil.setCodigo_perfil(rs.getInt("codigo_perfil"));
				  perfil.setNombre_perfil(rs.getString("nombre_perfil"));
				  perfil.setEstado_perfil(rs.getInt("estado_perfil"));
				  perfil.setId_cliente(rs.getInt("id_cliente"));
				  perfil.setEs_admin(rs.getInt("es_admin"));
				  perfiles.add(perfil);
			  }
			
			return perfiles;
			
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
	public ArrayList<ObjListManPerfil> getListManPerfilByCliente(int id){
		
		Connection conn = null;
		
		ArrayList<ObjListManPerfil> perfiles = new ArrayList<ObjListManPerfil>();
		
		
		String query="SELECT p.id_perfil "
				+"	      ,p.codigo_perfil "
				+"	      ,p.nombre_perfil "
				+"	      ,p.estado_perfil "
				+"	      ,p.id_cliente "
				+"	      ,p.es_admin "
				+"	FROM man_perfil_login p ";
				if(id != 0){
					query =	query +"	WHERE "
								  +"	p.perfil_id = " + id ;
				}
				query =	query +"	ORDER BY  nombre_perfil ";
				
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();  
			while(rs.next()) {
				ObjListManPerfil perfil = new ObjListManPerfil();
				  perfil.setPerfil("<a href='JavaScript: handleDetallePerfil("+rs.getInt("id_perfil")+");'><strong>"+rs.getString("nombre_perfil")+"</strong></a>");
				  if(rs.getInt("es_admin") == 1){
					 perfil.setEsAdmin("SI"); 
				  }else{
					  perfil.setEsAdmin("NO"); 
				  }
				  if(rs.getInt("estado_perfil") == 1){
					  perfil.setEstado("ACTIVADO");
				  }else{
					  perfil.setEstado("DESACTIVADO");
				  }
				  perfiles.add(perfil);
			  }
			
			return perfiles;
			
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
	public int setPerfilUser(ObjPerfilLogin perfil){
		Connection conn = null;
		String query = "INSERT INTO man_perfil_login "
					   +"        (codigo_perfil "
					   +"        ,nombre_perfil "
					   +"        ,estado_perfil "
					   +"        ,id_cliente "
					   +"        ,es_admin) "
					   +"  VALUES "
					   +"        ("+perfil.getCodigo_perfil()+" "
					   +"        ,'"+perfil.getNombre_perfil()+"' "
					   +"        ,"+perfil.getEstado_perfil()+" "
					   +"        ,"+perfil.getId_cliente()+" "
					   +"        ,"+perfil.getEs_admin()+") ";
					
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
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
	public int updatePerfilUser(ObjPerfilLogin perfil){
		Connection conn = null;
		
		String query = "UPDATE man_perfil_login "
					  +"   SET codigo_perfil = "+perfil.getCodigo_perfil()+" "
					  +"      ,nombre_perfil = '"+perfil.getNombre_perfil()+"' "
					  +"      ,estado_perfil = "+perfil.getEstado_perfil()+" "
					  +"      ,id_cliente = "+perfil.getId_cliente()+" "
					  +"      ,es_admin = "+perfil.getEs_admin()+" "
					  +"	 WHERE id_perfil = "+ perfil.getId_perfil() ;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
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
	public int deletePerfilUser(int perfil){
		Connection conn = null;
		String query = " DELETE FROM man_nav_perfil_modulo "
					  +" WHERE id_perfil = "+ perfil;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.executeUpdate();
			
			String query2 = " DELETE FROM man_perfil_login "
					        +"  WHERE id_perfil = "+ perfil;
			
			try {
				PreparedStatement ps2 = conn.prepareStatement(query2);
				ps2.executeUpdate();
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
	/////////////////////////////////////////////////////////////////////////////////////////////
	//////////PERMISOS PERFIL///////////////////////////////////////////////////////////////////
	@Override
	public int setAccesoPerfilModulo(ObjPermisosModuloPerfil permiso){
		Connection conn = null;
		String query = "INSERT INTO man_nav_perfil_modulo "
					   +"        (id_perfil "
					   +"        ,id_modulo "
					   +"        ,id_permiso) "
					   +"  VALUES "
					   +"        ("+permiso.getPerfil_id()+" "
					   +"        ,"+permiso.getModulo_id()+" "
					   +"        ,"+permiso.getPermiso_id()+")";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
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
	public int setAccesoPerfilSubDiv(ObjPermisosModuloPerfil permiso){
		Connection conn = null;
		String query = "INSERT INTO man_nav_perfil_sdiv "
					   +"        (id_perfil "
					   +"        ,id_sdiv "
					   +"        ,id_permiso) "
					   +"  VALUES "
					   +"        ("+permiso.getPerfil_id()+" "
					   +"        ,"+permiso.getModulo_id()+" "
					   +"        ,"+permiso.getPermiso_id()+")";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
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
	public int deletePerfilModulo(ObjPermisosModuloPerfil permiso){
		Connection conn = null;
		String query = "DELETE FROM man_nav_perfil_modulo "
				      +"WHERE id_perfil = "+permiso.getPerfil_id()+" "
					  +"AND id_modulo = "+permiso.getModulo_id()+" ";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
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
	public int deletePerfilSubDiv(ObjPermisosModuloPerfil permiso){
		Connection conn = null;
		String query = "DELETE FROM man_nav_perfil_sdiv "
				      +"WHERE id_perfil = "+permiso.getPerfil_id()+" "
					  +"AND id_sdiv = "+permiso.getModulo_id()+" ";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
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
	public ObjExistePermisoModuloPerfil existePermisoPerfilModulo(int perfil, int modulo){
		Connection conn = null;
		ObjExistePermisoModuloPerfil result = new ObjExistePermisoModuloPerfil();
		
		String query = "  SELECT nv.id_perfil "
				  +"      ,nv.id_modulo "
				  +"	  ,nv.id_permiso "
				  +"      ,p.nombre_permiso "
				  +"	FROM man_nav_perfil_modulo nv "
				  +"    INNER JOIN man_permiso_modulo p ON nv.id_permiso = p.id_permiso "
				  +"	WHERE "
				  +"	nv.id_perfil = "+perfil+" "
				  +"	AND nv.id_modulo = "+modulo+" ";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				 result.setExiste_permiso(1);
				 result.setModulo_id(modulo);
				 result.setPerfil_id(perfil);
				 result.setPermiso_id(rs.getInt("id_permiso"));
				 result.setPermiso_nombre(rs.getString("nombre_permiso"));
				
				//result = "SI";
				
			}else{
				result.setExiste_permiso(0);
				result.setModulo_id(modulo);
				result.setPerfil_id(perfil);
			}  
			
			return result;
			
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
	public ObjExistePermisoSubDivisionPerfil existePermisoPerfilSubDivision(int perfil, int subDiv){
		Connection conn = null;
		ObjExistePermisoSubDivisionPerfil result = new ObjExistePermisoSubDivisionPerfil();
		
		String query = "  SELECT "
						+"	nv.id_perfil "
						+"	,nv.id_sdiv "
						+"	,nv.id_permiso "
						+"	,p.nombre_permiso "
						+" FROM man_nav_perfil_sdiv nv "
						+" INNER JOIN man_permiso_modulo p ON nv.id_permiso = p.id_permiso "
						+" WHERE nv.id_perfil = "+perfil+" "
						+" AND nv.id_sdiv = "+subDiv+" ";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				 result.setExiste_permiso(1);
				 result.setId_sdiv(subDiv);
				 result.setId_perfil(perfil);
				 result.setId_permiso(rs.getInt("id_permiso"));
				 result.setNombre_permiso(rs.getString("nombre_permiso"));
				
				//result = "SI";
				
			}else{
				result.setExiste_permiso(0);
				result.setId_sdiv(subDiv);
				result.setId_perfil(perfil);
			}  
			
			return result;
			
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
	/*
	 * Combo Perfil
	 */
	@Override
	public ArrayList<ObjComboSelectValueInt> getComboPerfilCliente(int id, int div){
		Connection conn = null;
		ArrayList<ObjComboSelectValueInt> combo = new ArrayList<ObjComboSelectValueInt>();
		String query="";
		
		if(div == 0){
		 query="SELECT p.id_perfil "
				+"	      ,p.nombre_perfil "
				+"	FROM man_perfil_login p "
				+"	INNER JOIN man_cliente c ON p.id_cliente = c.id_cliente "
				+"	WHERE "
				+"	p.id_cliente = ? ";
		}else{
			query="SELECT p.id_perfil "
					+"	      ,p.nombre_perfil "
					+"	FROM man_perfil_login p "
					+"	INNER JOIN man_cliente c ON p.id_cliente = c.id_cliente "
					+"  INNER JOIN man_nav_perfil_sdiv sd ON sd.id_perfil = p.id_perfil "
					+"	WHERE "
					+"	p.id_cliente = ? "
					+"  AND sd.id_sdiv = " + div;
		}
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  ObjComboSelectValueInt perfil = new ObjComboSelectValueInt();
				  perfil.setValue(rs.getInt("id_perfil"));
				  perfil.setName(rs.getString("nombre_perfil"));
				  combo.add(perfil);
			  }
			  
			  return combo;
			  
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
	public int getPermisoPerfilModulo(int perfil, int modulo){
		Connection conn = null;
		int result = 0;
		
		String query="SELECT id_permiso "
			     +"	  FROM man_nav_perfil_modulo "
			     +"		WHERE id_perfil = "+perfil+" "
				 +"	AND id_modulo = "+modulo+" ";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getInt("id_permiso");	
			}else{
				result = 0;
			}  
			
			return result;
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
	/*
	 * ACCESOS MENU PERFIL
	 */
	
	@Override
	public ArrayList<ObjMenuPermisosModulosPerfil> getListModulosPermisosPerfil(int menu, int perfil){
		Connection conn = null;
		ArrayList<ObjMenuPermisosModulosPerfil> modulos = new ArrayList<ObjMenuPermisosModulosPerfil>();
		
		String query="SELECT "
				+"	   u.id_menu "
				+"	  ,u.id_unidad "
				+"    ,u.nombre_unidad "
				+"	  ,m.id_modulo "
				+"    ,m.nombre_modulo "
				+"  FROM man_menu_modulo m "
				+"  INNER JOIN man_menu_unidad u ON m.id_unidad = u.id_unidad "
				+"  WHERE "
				+"  u.id_menu = ? "
				+"  ORDER BY "
				+"  u.orden_unidad, "
				+"  m.orden_modulo";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, menu);
			ResultSet rs = ps.executeQuery();  
			while(rs.next()) {
				  ObjMenuPermisosModulosPerfil modulo = new ObjMenuPermisosModulosPerfil();
					modulo.setMenu_id(rs.getInt("id_menu"));
					modulo.setUnidad_id(rs.getInt("id_unidad"));
					modulo.setUnidad_nombre(rs.getString("nombre_unidad"));
					modulo.setModulo_id(rs.getInt("id_modulo"));
					modulo.setModulo_nombre(rs.getString("nombre_modulo"));
					ObjExistePermisoModuloPerfil existe = this.existePermisoPerfilModulo(perfil, rs.getInt("id_modulo"));
					modulo.setPerfil_id(perfil);
					modulo.setExist_permiso(existe.getExiste_permiso());
					if(existe.getExiste_permiso() == 1){
						modulo.setPermiso_id(existe.getPermiso_id());
						modulo.setPermiso_nombre(existe.getPermiso_nombre());
						modulo.setButons("<a class='btn btn-danger btn-icon btn-circle btn-lg' href='JavaScript: getPermisoModulo(0,"+rs.getInt("id_modulo")+","+perfil+");' ><i class='fa fa-times'></i></a>");
						
					}else{
						modulo.setPermiso_id(0);
						modulo.setPermiso_nombre("SIN ACCESO");
						modulo.setButons("<a class='btn btn-success btn-icon btn-circle btn-lg' href='JavaScript: getPermisoModulo(1,"+rs.getInt("id_modulo")+","+perfil+");' ><i class='fa fa-times'></i></a> "
								       + "<a class='btn btn-primary btn-icon btn-circle btn-lg' href='JavaScript: getPermisoModulo(2,"+rs.getInt("id_modulo")+","+perfil+");' ><i class='fa fa-times'></i></a> "
								       + "<a class='btn btn-warning btn-icon btn-circle btn-lg' href='JavaScript: getPermisoModulo(3,"+rs.getInt("id_modulo")+","+perfil+");' ><i class='fa fa-times'></i></a>");
					}
					modulos.add(modulo);
			  }
			return modulos;
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
	public ArrayList<ObjPermisosSubDivisionPerfil> getListSubDivisionesPermisosPerfil(int perfil){
		Connection conn = null;
		ArrayList<ObjPermisosSubDivisionPerfil> lists = new ArrayList<ObjPermisosSubDivisionPerfil>();
		
		String query="SELECT  "
					+"	sd.id_sdiv "
					+"		,d.nombre_div "
					+"		,sd.nombre_sdiv "
					+"	FROM man_sub_division sd "
					+"	INNER JOIN man_divisiones d ON sd.id_div = d.id_div "
					+"	ORDER BY d.nombre_div,sd.nombre_sdiv ";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();  
			while(rs.next()) {
				ObjPermisosSubDivisionPerfil list = new ObjPermisosSubDivisionPerfil();
					list.setId_sdiv(rs.getInt("id_sdiv"));
					list.setNombre_div(rs.getString("nombre_div"));
					list.setNombre_sdiv(rs.getString("nombre_sdiv"));
					ObjExistePermisoSubDivisionPerfil existe = this.existePermisoPerfilSubDivision(perfil, rs.getInt("id_sdiv"));
					list.setPerfil_id(perfil);
					list.setExist_permiso(existe.getExiste_permiso());
					if(existe.getExiste_permiso() == 1){
						list.setPermiso_id(existe.getId_permiso());
						list.setPermiso_nombre(existe.getNombre_permiso());
						list.setButons("<a class='btn btn-danger btn-icon btn-circle btn-lg' href='JavaScript: getPermisoSdiv(0,"+rs.getInt("id_sdiv")+","+perfil+");' ><i class='fa fa-times'></i></a>");
						
					}else{
						list.setPermiso_id(0);
						list.setPermiso_nombre("SIN ACCESO");
						list.setButons("<a class='btn btn-success btn-icon btn-circle btn-lg' href='JavaScript: getPermisoSdiv(1,"+rs.getInt("id_sdiv")+","+perfil+");' ><i class='fa fa-times'></i></a> ");
					}
					lists.add(list);
			  }
			return lists;
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
	public ArrayList<ObjComboSelectValueInt> getComboMenuByPerfil(int cliente){
		Connection conn = null;
		ArrayList<ObjComboSelectValueInt> combo =  new ArrayList<ObjComboSelectValueInt>();
		
		String query="SELECT m.id_menu "
				 +"		  ,m.nombre_menu " 
				 +"	FROM man_menu_sistema m "
				 +"	WHERE m.id_cliente = ? "
				 +"	ORDER BY m.nombre_menu";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				  ObjComboSelectValueInt perfil = new ObjComboSelectValueInt();
					perfil.setValue(rs.getInt("id_menu"));
					perfil.setName(rs.getString("nombre_menu"));
			  }
			return combo;
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
