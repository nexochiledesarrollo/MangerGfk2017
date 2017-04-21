package cl.nexo.manager.imp.fileupload;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;







import cl.nexo.manager.access.fileupload.AccessFileUpload;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.upload.ObjUploadFile;


public class FileUpload implements AccessFileUpload {
		
	private static final Logger logger = Logger.getLogger(FileUpload.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	@Override
    public ArrayList<ObjUploadFile> getlistFileUpload() {
		ArrayList<ObjUploadFile> list = new ArrayList<ObjUploadFile>();
		
		Connection conn = null;
		
		
		String query="SELECT f.id_file "
					  +"    ,f.name_file "
					  +"    ,f.thumbnail_file "
					  +"    ,f.newfile_file "
					  +"    ,f.tipo_file "
					  +"    ,f.fcreacion_file "
					  +"    ,f.fmod_file "
					  +"    ,f.thumbnailurl_file "
					  +"    ,f.deleteurl_file "
					  +"    ,f.deletetype_file "
					  +"    ,f.size_file "
					  +"    ,f.tipoarch_file "
					  + "   ,f.exten_file "
					  + "   ,f.uploader_file "
					  + "   ,f.id_modulo "
					  + "   ,f.carga_file " 
					  + "   ,f.tipo_documental "
					  + "   ,f.id_proyecto "
					  + "   ,f.id_operacion"
					  + "   ,f.url_file "
					  +"    ,u.login_user "
					  +" FROM man_file_upload f "
					  +" INNER JOIN man_login_user u ON u.id_user = f.uploader_file"
					  +" order by f.id_file DESC ";
		 try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  ResultSet rs = ps.executeQuery();
			  while (rs.next()) {
				  ObjUploadFile row = new ObjUploadFile();
				  row.setId(rs.getLong("id_file"));
				  row.setName(rs.getString("name_file"));
				  row.setThumbnailFilename(rs.getString("thumbnail_file"));
				  row.setNewFilename(rs.getString("newfile_file"));
				  row.setContentType(rs.getString("tipo_file"));
				  row.setDateCreated(rs.getDate("fcreacion_file"));
				  row.setLastUpdated(rs.getDate("fmod_file"));
				  row.setThumbnailUrl(rs.getString("thumbnailurl_file"));
				  row.setDeleteUrl(rs.getString("deleteurl_file"));
				  row.setDeleteType(rs.getString("deletetype_file"));
				  row.setSize(rs.getLong("size_file"));
				  row.setTipoarch_file(rs.getInt("tipoarch_file"));
				  row.setExten_file(rs.getString("exten_file"));
				  row.setUploader_file(rs.getInt("uploader_file"));
				  row.setId_modulo(rs.getInt("id_modulo"));
				  row.setCarga_file(rs.getInt("carga_file"));
				  row.setTipo_documental(rs.getInt("tipo_documental"));
				  row.setId_proyecto(rs.getLong("id_proyecto"));
				  row.setId_operacion(rs.getLong("id_operacion"));
				  row.setUrl_file(rs.getString("url_file"));
				  row.setStr_user(rs.getString("login_user"));
				  
				  list.add(row);
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
    public ObjUploadFile getMaxFileUpload(String newfile_file) {
		ObjUploadFile row = new ObjUploadFile();
		
		Connection conn = null;
		
		
		String query="SELECT f.id_file "
					  +"    ,f.name_file "
					  +"    ,f.thumbnail_file "
					  +"    ,f.newfile_file "
					  +"    ,f.tipo_file "
					  +"    ,f.fcreacion_file "
					  +"    ,f.fmod_file "
					  +"    ,f.thumbnailurl_file "
					  +"    ,f.deleteurl_file "
					  +"    ,f.deletetype_file "
					  +"    ,f.size_file "
					  +"    ,f.tipoarch_file "
					  + "   ,f.exten_file "
					  + "   ,f.uploader_file "
					  + "   ,f.id_modulo "
					  + "   ,f.carga_file " 
					  + "   ,f.tipo_documental "
					  + "   ,f.id_proyecto "
					  + "   ,f.id_operacion"
					  + "   ,f.url_file "
					  +"    ,u.login_user "
					  +" FROM man_file_upload f "
					  +" INNER JOIN man_login_user u ON u.id_user = f.uploader_file "
					  +" WHERE f.newfile_file = '"+newfile_file+"' "
					  +" order by f.id_file DESC ";
		 try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  ResultSet rs = ps.executeQuery();
			  while (rs.next()) {
				  
				  row.setId(rs.getLong("id_file"));
				  row.setName(rs.getString("name_file"));
				  row.setThumbnailFilename(rs.getString("thumbnail_file"));
				  row.setNewFilename(rs.getString("newfile_file"));
				  row.setContentType(rs.getString("tipo_file"));
				  row.setDateCreated(rs.getDate("fcreacion_file"));
				  row.setLastUpdated(rs.getDate("fmod_file"));
				  row.setThumbnailUrl(rs.getString("thumbnailurl_file"));
				  row.setDeleteUrl(rs.getString("deleteurl_file"));
				  row.setDeleteType(rs.getString("deletetype_file"));
				  row.setSize(rs.getLong("size_file"));
				  row.setTipoarch_file(rs.getInt("tipoarch_file"));
				  row.setExten_file(rs.getString("exten_file"));
				  row.setUploader_file(rs.getInt("uploader_file"));
				  row.setId_modulo(rs.getInt("id_modulo"));
				  row.setCarga_file(rs.getInt("carga_file"));
				  row.setTipo_documental(rs.getInt("tipo_documental"));
				  row.setId_proyecto(rs.getLong("id_proyecto"));
				  row.setId_operacion(rs.getLong("id_operacion"));
				  row.setUrl_file(rs.getString("url_file"));
				  
				  
			  }
			  
			  return row;
			  
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
    public ObjUploadFile getFileUpload(Long id) {
		ObjUploadFile row = new ObjUploadFile();
		
		Connection conn = null;
		
		
		String query="SELECT f.id_file "
					  +"    ,f.name_file "
					  +"    ,f.thumbnail_file "
					  +"    ,f.newfile_file "
					  +"    ,f.tipo_file "
					  +"    ,f.fcreacion_file "
					  +"    ,f.fmod_file "
					  +"    ,f.thumbnailurl_file "
					  +"    ,f.deleteurl_file "
					  +"    ,f.deletetype_file "
					  +"    ,f.size_file "
					  +"    ,f.tipoarch_file "
					  + "   ,f.exten_file "
					  + "   ,f.uploader_file "
					  + "   ,f.id_modulo "
					  + "   ,f.carga_file " 
					  + "   ,f.tipo_documental "
					  + "   ,f.id_proyecto "
					  + "   ,f.id_operacion"
					  + "   ,f.url_file "
					  +"    ,u.login_user "
					  +" FROM man_file_upload f "
					  +" INNER JOIN man_login_user u ON u.id_user = f.uploader_file "
					  +" WHERE f.id_file = "+id+" "
					  +" order by f.id_file DESC ";
		 try {
			  conn = dataSource.getConnection();
			  PreparedStatement ps = conn.prepareStatement(query);
			  ResultSet rs = ps.executeQuery();
			  while (rs.next()) {
				  
				  row.setId(rs.getLong("id_file"));
				  row.setName(rs.getString("name_file"));
				  row.setThumbnailFilename(rs.getString("thumbnail_file"));
				  row.setNewFilename(rs.getString("newfile_file"));
				  row.setContentType(rs.getString("tipo_file"));
				  row.setDateCreated(rs.getDate("fcreacion_file"));
				  row.setLastUpdated(rs.getDate("fmod_file"));
				  row.setThumbnailUrl(rs.getString("thumbnailurl_file"));
				  row.setDeleteUrl(rs.getString("deleteurl_file"));
				  row.setDeleteType(rs.getString("deletetype_file"));
				  row.setSize(rs.getLong("size_file"));
				  row.setTipoarch_file(rs.getInt("tipoarch_file"));
				  row.setExten_file(rs.getString("exten_file"));
				  row.setUploader_file(rs.getInt("uploader_file"));
				  row.setId_modulo(rs.getInt("id_modulo"));
				  row.setCarga_file(rs.getInt("carga_file"));
				  row.setTipo_documental(rs.getInt("tipo_documental"));
				  row.setId_proyecto(rs.getLong("id_proyecto"));
				  row.setId_operacion(rs.getLong("id_operacion"));
				  row.setUrl_file(rs.getString("url_file"));
				  
				  
			  }
			  
			  return row;
			  
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
	public int createFile(ObjUploadFile fil){
		Connection conn = null;
		
		String fechaNow = format3.format(new Date());
		
		String query = "INSERT INTO man_file_upload "
					   +"        (name_file "
					   +"        ,thumbnail_file "         
					   +"        ,newfile_file  "
					   +"        ,tipo_file  "
					   +"        ,fcreacion_file  "
					   +"        ,fmod_file  "
					   +"        ,thumbnailurl_file  "
					   +"        ,deleteurl_file  "
					   +"        ,deletetype_file"
					   +"        ,size_file  "
					   +"        ,tipoarch_file  "
					   +"        ,exten_file  "
					   +"        ,uploader_file  "
					   +"        ,id_modulo  "
					   +"        ,carga_file  "
					   +"        ,tipo_documental  "
					   +"        ,id_proyecto  "
					   +"        ,id_operacion  "
					   +"        ,url_file  "
					   +"        )  "
					   +"  VALUES "
					   +"        ('"+fil.getName()+"' "
					   +"        ,'"+fil.getThumbnailFilename()+"' "
					   +"        ,'"+fil.getNewFilename()+"' "
					   +"        ,'"+fil.getContentType()+"' "
					   +"        ,'"+fechaNow+"' "
					   +"        ,'"+fechaNow+"' "
					   +"        ,'"+fil.getThumbnailUrl()+"' "
					   +"        ,'"+fil.getDeleteUrl()+"' "
					   +"        ,'"+fil.getDeleteType()+"' "         
					   +"        ,"+fil.getSize()+" "
					   +"        ,"+fil.getTipoarch_file()+" "
					   +"        ,'"+fil.getExten_file()+"' "
					   +"        ,"+fil.getUploader_file()+" "
					   +"        ,"+fil.getId_modulo()+" "
					   +"        ,"+fil.getCarga_file()+" "
					   +"        ,"+fil.getTipo_documental()+" "
					   +"        ,"+fil.getId_proyecto()+" "
					   +"        ,"+fil.getId_operacion()+" "
					   +"        ,'"+fil.getUrl_file()+"' "
					   +"       )";
		
		
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.info(query);
			ps.executeUpdate();
			return 1;
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
	public int deleteFile(Long id){
		Connection conn = null;
		String query = "DELETE FROM man_file_upload "
					   +" WHERE id_file = " + id;
		
		
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			logger.info(query);
			ps.executeUpdate();
			return 1;
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
