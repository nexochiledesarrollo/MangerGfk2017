package cl.nexo.manager.imp.tarea;

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

import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.tarea.AccessTarea;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.nota.ObjNota;
import cl.nexo.manager.obj.tarea.ObjTarea;
import cl.nexo.manager.obj.tarea.ObjTareaPropia;


public class TareaWorkflow implements AccessTarea {
	
	private static final Logger logger = Logger.getLogger(TareaWorkflow.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	@Override
	public ObjNota getTareasPropias(int id){
		ObjNota list = new ObjNota();
		
		Connection conn = null;
		String query= "SELECT id_nota "
					+"	      ,tarea_nota "
					+"	      ,fecha_nota "
					+"	      ,id_user "
					+"	FROM man_nota "
					+"	WHERE  id_nota = "+ id
					+"	ORDER BY fecha_nota DESC ";
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  ResultSet rs = ps.executeQuery();
			  if (rs.next()) {
				  
				  list.setId_nota(rs.getInt("id_nota"));
				  list.setTarea_nota(rs.getString("tarea_nota"));
				  list.setFecha_nota(format3.format(rs.getTime("fecha_nota")));
				  list.setId_user(rs.getInt("id_user"));
				  
			  }
			  
			  return list;
			  
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
	public ArrayList<ObjNota> getListTareasPropias(int user){
		ArrayList<ObjNota> lists = new ArrayList<ObjNota>();
		
		Connection conn = null;
		String query= "SELECT id_nota "
				+"	      ,tarea_nota "
				+"	      ,fecha_nota "
				+"	      ,id_user "
				+"	FROM man_nota "
				+"	WHERE  id_user = "+ user
				+"	ORDER BY fecha_nota DESC ";
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  ResultSet rs = ps.executeQuery();
			  while (rs.next()) {
				  ObjNota list = new ObjNota();
				  list.setId_nota(rs.getInt("id_nota"));
				  list.setTarea_nota(rs.getString("tarea_nota"));
				  list.setFecha_nota(format3.format(rs.getTime("fecha_nota")));
				  list.setId_user(rs.getInt("id_user"));
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
	public int setTareaPropia(ObjNota nota){
		
		Connection conn = null;
		
		String query = "INSERT INTO man_nota "
					  +"         (tarea_nota "
					  +"         ,fecha_nota "
					  +"         ,id_user) "
					  +"   VALUES "
					  +"  ('"+nota.getTarea_nota()+"'"
					  + ",'"+nota.getFecha_nota()+"'"
					  + ","+nota.getId_user()+")";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
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
	public int updateTareaPropia(ObjNota nota){
		Connection conn = null;
		String query = "UPDATE man_nota "
					+"   SET tarea_nota = '"+nota.getTarea_nota()+"' "
					+"	    ,fecha_nota = '"+nota.getFecha_nota()+"' "
					+"	 WHERE id_tareap = "+nota.getId_user()+" ";
	
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
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
	public int deleteTareaPropia(int id){
		Connection conn = null;
		String query = " DELETE FROM  man_nota "
					+"	WHERE id_nota ="+id+" ";
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
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
///////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////// tareas worflow //////////////////////////////////////////

	@Override
	public ObjTarea getTarea(int id){
		ObjTarea lis = new ObjTarea();
		Connection conn = null;
		String query = " SELECT t.id_tarea "
				+"	      ,t.id_operacion "
				+"		  ,p.id_proyectom "
				+"		  ,m.cod_operacion "
				+"		  ,m.nombre_operacion "
				+"	      ,t.id_actividad "
				+"		  ,f.nombre_flujo "
				+"	      ,t.id_asigna "
				+"        ,(l.login_user)as nlogin "
				+"	      ,t.id_grupo "
				+"		  ,c.nombre_cargo "
				+"	      ,t.id_usuario "
				+"		  ,l.login_user "
				+"	      ,t.tipo_asignacion "
				+"		  ,ti.valor_ttarea "
				+"	      ,t.grupo_asignacion "
				+"	      ,cb.des_detalle "
				+"	      ,t.finicio_asig_tarea "
				+"	      ,t.ffin_asig_tarea "
				+"	      ,t.finicio_tarea "
				+"	      ,t.ffin_tarea "
				+"	      ,t.estado_tarea "
				+"        ,e.valor_estadot "
				+"	      ,t.asunto_tarea "
				+"	      ,t.desc_tarea "
				+"	      ,t.tipo_tarea "
				+"	      ,(cb1.des_detalle)as strtipo_tarea "
				+"	  FROM man_tarea t "
				+"	  INNER JOIN man_proyecto_manager_medicion m ON t.id_operacion = m.id_operacion "
				+"	  INNER JOIN man_proyecto_manager_principal p ON m.id_proyectom = p.id_proyectom "
				+"	  INNER JOIN man_flujo f ON f.cod_flujo = t.id_actividad "
				+"	  LEFT JOIN man_cargo c ON c.id_cargo = t.id_grupo   "
				+"	  LEFT JOIN man_login_user l ON t.id_usuario = l.id_user "
				+"	  LEFT JOIN man_login_user l1 ON t.id_asigna = l1.id_user "
				+"	  INNER JOIN man_tarea_tipo ti on t.tipo_asignacion = ti.cod_ttarea  "
				+"	  INNER JOIN man_tarea_estado e ON e.id_estadot = t.estado_tarea  "
				+"	  INNER JOIN man_combo_box_detalle cb ON t.grupo_asignacion = cb.valor_detalle AND cb.id_combo = 42  "
				+"	  INNER JOIN man_combo_box_detalle cb1 ON t.tipo_tarea = cb1.valor_detalle AND cb1.id_combo = 43  ";
	        
	        query = query +"	WHERE t.id_tarea = "+id+" "; 
	         
				
	
			try {
				  conn = dataSource.getConnection();
				  PreparedStatement ps = conn.prepareStatement(query);
				  logger.debug(query);
				  ResultSet rs = ps.executeQuery();	
				  while (rs.next()) {  
					  
					  lis.setId_tarea(rs.getInt("id_tarea"));
					  lis.setId_operacion(rs.getInt("id_operacion"));
					  lis.setCodigo_operacion(rs.getInt("id_proyectom")+"-"+rs.getInt("cod_operacion"));
					  lis.setNombre_proyecto(rs.getString("nombre_operacion"));
					  lis.setId_actividad(rs.getInt("id_actividad"));
					  lis.setNombre_actividad(rs.getString("nombre_flujo"));
					  lis.setUser_asigna(rs.getInt("id_asigna"));
					  lis.setNombre_asigna(rs.getString("nlogin"));
					  lis.setId_grupo(rs.getInt("id_grupo"));
					  lis.setNombre_grupo(rs.getString("nombre_cargo"));
					  lis.setId_user(rs.getInt("id_usuario"));
					  lis.setNombre_usuario(rs.getString("login_user"));
					  lis.setTipo_asignacion(rs.getInt("tipo_asignacion"));
					  lis.setStr_tipo_asignacion(rs.getString("des_detalle"));
					  lis.setStr_tipo_asignacion(rs.getString("valor_ttarea"));
					  
					  if(rs.getDate("finicio_asig_tarea") != null){
					  	lis.setFinicio_asig_tarea(format4.format(rs.getDate("finicio_asig_tarea")));
					  }
					  if(rs.getDate("ffin_asig_tarea") != null){
						lis.setFfin_asig_tarea(format4.format(rs.getDate("ffin_asig_tarea")));
					  }
					  if(rs.getDate("finicio_tarea") != null){
					  	lis.setFecha_inicio(format4.format(rs.getDate("finicio_tarea")));
					  }
					  if(rs.getDate("ffin_tarea") != null){
					  	lis.setFecha_fin(format4.format(rs.getDate("ffin_tarea")));
					  }
					  
					  lis.setEstado_tarea(rs.getInt("estado_tarea"));
					  lis.setStr_estado_tarea(rs.getString("valor_estadot"));
					  lis.setAsunto_tarea(rs.getString("asunto_tarea"));
					  lis.setDescr_tarea(rs.getString("desc_tarea"));
					  lis.setTipo_tarea(rs.getInt("tipo_tarea"));
					  lis.setStr_tipo_tarea(rs.getString("strtipo_tarea"));
					  
					  
					  
				  }
				  
				  return lis;
				  
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
	public int getCountTareas(int ttarea, int user, int grupo){
		/*
		 * tarea = 0 -- todo
		 * tarea = 1 -- Nueva
		 * tarea = 2 -- En Proceso
		 * tarea = 3 -- Terminada
		 * tarea = 4 -- Incidencia
		 * tarea = 5 -- En Revision
		 * tarea = 6 abiertas
		 * */
		int id2 = 0;
		
		Connection conn = null;
		
		String query = " SELECT COUNT(id_tarea)as result "
					+"	  FROM man_tarea  ";
		        if(ttarea != 0 || user != 0 || grupo != 0 ){
		        	query = query +"	WHERE "; 
		        } 
				if(ttarea != 0){
					if(ttarea == 6 ){
						query = query  +"	 estado_tarea IN(1,2,4,5)  ";
					}else{
						query = query  +"	 estado_tarea =  " + ttarea + " ";
						
					}
		         }
				if((ttarea != 0 && user != 0 ) || (ttarea != 0 && grupo != 0 )){
					query = query +"	AND ";
				}
				if(user != 0){
					query = query  +"	 id_usuario =  " + user + " ";
				}
				if(grupo != 0 && user != 0 ){
					query = query +"	AND ";
				}
				if(grupo != 0){
					query = query  +"	 id_grupo =  " + grupo + " ";
				}	
		
		try {
			  String aux = "";
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  logger.debug(query);
			  ResultSet rs = ps.executeQuery();	
			  if (rs.next()) {  
				  
				  id2 = rs.getInt("result");
				  logger.debug("Count: "+ id2);
			  }
			  
			  return id2;
			  
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
	public ArrayList<ObjTarea> getListTareasDashboard(int user , String lang){
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		ObjLoginUser login = logins.getUserById(user);
		
		/*
		 * tarea = 0 -- todo
		 * tarea = 1 -- Nueva
		 * tarea = 2 -- En Proceso
		 * tarea = 3 -- Terminada
		 * tarea = 4 -- Incidencia
		 * tarea = 5 -- En Revision
		 * tarea = 6 abiertas
		 * 
		 * Tareas Grupales son asociados al Cargo del usuario
		 * */
		ArrayList<ObjTarea> res = new ArrayList<ObjTarea>();
		ArrayList<ObjTarea> res1 = this.getListTareas(6, user, 0, lang);
		ArrayList<ObjTarea> res2 = this.getListTareas(6, 0, login.getId_cargo(), lang);
		
		for( ObjTarea i1 : res1){
			res.add(i1);
		}
		for( ObjTarea i2 : res2){
			res.add(i2);
		}
		
		return res;
	}
	
	@Override
	public ArrayList<ObjTarea> getListTareas(int ttarea, int user, int grupo, String lang){
		/*
		 * tarea = 0 -- todo
		 * tarea = 1 -- Nueva
		 * tarea = 2 -- En Proceso
		 * tarea = 3 -- Terminada
		 * tarea = 4 -- Incidencia
		 * tarea = 5 -- En Revision
		 * tarea = 6 abiertas
		 * */
		ArrayList<ObjTarea> lists = new ArrayList<ObjTarea>();
		
		Connection conn = null;
		
		String query = " SELECT t.id_tarea "
				+"	      ,t.id_operacion "
				+"		  ,p.id_proyectom "
				+"		  ,m.cod_operacion "
				+"		  ,m.nombre_operacion "
				+"	      ,t.id_actividad "
				+"		  ,f.nombre_flujo "
				+"	      ,t.id_asigna "
				+"        ,(l.login_user)as nlogin "
				+"	      ,t.id_grupo "
				+"		  ,c.nombre_cargo "
				+"	      ,t.id_usuario "
				+"		  ,l.login_user "
				+"	      ,t.tipo_asignacion "
				+"		  ,ti.valor_ttarea "
				+"	      ,t.grupo_asignacion "
				+"	      ,cb.des_detalle "
				+"	      ,t.finicio_asig_tarea "
				+"	      ,t.ffin_asig_tarea "
				+"	      ,t.finicio_tarea "
				+"	      ,t.ffin_tarea "
				+"	      ,t.estado_tarea "
				+"        ,e.valor_estadot "
				+"	      ,t.asunto_tarea "
				+"	      ,t.desc_tarea "
				+"	      ,t.tipo_tarea "
				+"	      ,(cb1.des_detalle)as strtipo_tarea "
				+"	  FROM man_tarea t "
				+"	  INNER JOIN man_proyecto_manager_medicion m ON t.id_operacion = m.id_operacion "
				+"	  INNER JOIN man_proyecto_manager_principal p ON m.id_proyectom = p.id_proyectom "
				+"	  INNER JOIN man_flujo f ON f.cod_flujo = t.id_actividad "
				+"	  LEFT JOIN man_cargo c ON c.id_cargo = t.id_grupo   "
				+"	  LEFT JOIN man_login_user l ON t.id_usuario = l.id_user "
				+"	  LEFT JOIN man_login_user l1 ON t.id_asigna = l1.id_user "
				+"	  INNER JOIN man_tarea_tipo ti on t.tipo_asignacion = ti.cod_ttarea  "
				+"	  INNER JOIN man_tarea_estado e ON e.id_estadot = t.estado_tarea  "
				+"	  INNER JOIN man_combo_box_detalle cb ON t.grupo_asignacion = cb.valor_detalle AND cb.id_combo = 42  "
				+"	  INNER JOIN man_combo_box_detalle cb1 ON t.tipo_tarea = cb1.valor_detalle AND cb1.id_combo = 43  ";
		        
		        query = query +"	WHERE  "; 
		         
				if(ttarea != 0){
					if(ttarea == 6 ){
						query = query  +"	 t.estado_tarea IN(1,2,4,5)  ";
					}else{
						query = query  +"	 t.estado_tarea =  " + ttarea + " ";
						
					}
		         }
				if(ttarea != 0){
					query = query  +" AND ";
				}
				if(user != 0){
					query = query  +" t.id_usuario =  " + user + " ";
				}
				if(user != 0 && grupo != 0){
					query = query  +" AND ";
				}
				
				if(grupo != 0){
					query = query  +"  t.id_grupo =  " + grupo + " ";
				}	
				
				query = query  +"ORDER BY t.finicio_asig_tarea DESC ";
		
		try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  logger.debug(query);
			  ResultSet rs = ps.executeQuery();	
			  while (rs.next()) {  
				  ObjTarea lis = new ObjTarea();
				  lis.setId_tarea(rs.getInt("id_tarea"));
				  lis.setId_operacion(rs.getInt("id_operacion"));
				  lis.setCodigo_operacion(rs.getInt("id_proyectom")+"-"+rs.getInt("cod_operacion"));
				  lis.setNombre_proyecto(rs.getString("nombre_operacion"));
				  lis.setId_actividad(rs.getInt("id_actividad"));
				  lis.setNombre_actividad(rs.getString("nombre_flujo"));
				  lis.setUser_asigna(rs.getInt("id_asigna"));
				  lis.setNombre_asigna(rs.getString("nlogin"));
				  lis.setId_grupo(rs.getInt("id_grupo"));
				  lis.setNombre_grupo(rs.getString("nombre_cargo"));
				  lis.setId_user(rs.getInt("id_usuario"));
				  lis.setNombre_usuario(rs.getString("login_user"));
				  lis.setTipo_asignacion(rs.getInt("tipo_asignacion"));
				  lis.setStr_tipo_asignacion(rs.getString("des_detalle"));
				  lis.setStr_tipo_asignacion(rs.getString("valor_ttarea"));
				  
				  	  if(rs.getDate("finicio_asig_tarea") != null){
					  	lis.setFinicio_asig_tarea(format4.format(rs.getDate("finicio_asig_tarea")));
					  }
					  if(rs.getDate("ffin_asig_tarea") != null){
						lis.setFfin_asig_tarea(format4.format(rs.getDate("ffin_asig_tarea")));
					  }
					  if(rs.getDate("finicio_tarea") != null){
					  	lis.setFecha_inicio(format4.format(rs.getDate("finicio_tarea")));
					  }
					  if(rs.getDate("ffin_tarea") != null){
					  	lis.setFecha_fin(format4.format(rs.getDate("ffin_tarea")));
					  }
				  lis.setEstado_tarea(rs.getInt("estado_tarea"));
				  lis.setStr_estado_tarea(rs.getString("valor_estadot"));
				  lis.setAsunto_tarea(rs.getString("asunto_tarea"));
				  lis.setDescr_tarea(rs.getString("desc_tarea"));
				  lis.setTipo_tarea(rs.getInt("tipo_tarea"));
				  lis.setStr_tipo_tarea(rs.getString("strtipo_tarea"));
				  
				  lists.add(lis);
				  
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
	public int setTarea(ObjTarea tarea){
		
		Connection conn = null;
		
		String query = "INSERT INTO man_tarea "
					   +"        (id_operacion "
					   +"        ,id_actividad "
					   +"        ,id_asigna "
					   +"        ,id_grupo "
					   +"        ,id_usuario "
					   +"        ,tipo_asignacion "
					   +"        ,grupo_asignacion "
					   +"        ,finicio_tarea "
					   +"        ,ffin_tarea "
					   +"        ,estado_tarea "
					   +"        ,asunto_tarea "
					   +"        ,desc_tarea "
					   +"        ,tipo_tarea) "
					   +"  VALUES "
					   +"        ("+tarea.getId_operacion()+" "
					   +"        ,"+tarea.getId_actividad()+" "
					   +"        ,"+tarea.getUser_asigna()+" "
					   +"        ,"+tarea.getId_grupo()+" "
					   +"        ,"+tarea.getId_user()+" "
					   +"        ,"+tarea.getTipo_asignacion()+" "
					   +"        ,"+tarea.getGrupo_asignacion()+" "
					   +"        ,'"+tarea.getFecha_inicio()+"' "
					   +"        ,'"+tarea.getFecha_fin()+"' "
					   +"        ,"+tarea.getEstado_tarea()+" "
					   +"        ,'"+tarea.getAsunto_tarea()+"' "
					   +"        ,'"+tarea.getDescr_tarea()+"' "
					   +"        ,"+tarea.getTipo_tarea()+")  "; 
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
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
	public int updateTarea(ObjTarea tarea){
		Connection conn = null;
		String query = "UPDATE man_tarea "
					   +"	   SET id_operacion = "+tarea.getId_operacion()+" "
					   +"      ,id_actividad = "+tarea.getId_actividad()+" "
					+"	      ,id_asigna = "+tarea.getUser_asigna()+" "
					+"	      ,id_grupo = "+tarea.getId_grupo()+" "
					+"	      ,id_usuario = "+tarea.getId_user()+" "
					+"	      ,tipo_asignacion = "+tarea.getTipo_asignacion()+" "
					+"	      ,grupo_asignacion = "+tarea.getGrupo_asignacion()+" "
					+"	      ,finicio_asig_tarea = '"+tarea.getFinicio_asig_tarea()+"' "
					+"	      ,ffin_asig_tarea = '"+tarea.getFfin_asig_tarea()+"' ";
					if(tarea.getFecha_inicio() != null){
						query = query +"	      ,finicio_tarea = '"+tarea.getFecha_inicio()+"' ";
					}
					if(tarea.getFecha_fin() != null){
						query = query +"	      ,ffin_tarea = '"+tarea.getFecha_fin()+"' ";
					}
					
					query = query +"	      ,estado_tarea = "+tarea.getEstado_tarea()+" "
					+"	      ,asunto_tarea = '"+tarea.getAsunto_tarea()+"' "
					+"	      ,desc_tarea = '"+tarea.getDescr_tarea()+"' "
					+"	      ,tipo_tarea = "+tarea.getTipo_tarea()+" "
					+"	 WHERE id_tarea = "+tarea.getId_tarea();
	
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
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
	public int deleteTarea(int id){
		Connection conn = null;
		String query = " DELETE FROM  man_tarea "
					+"	WHERE id_tarea = "+ id;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.debug(query);
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
