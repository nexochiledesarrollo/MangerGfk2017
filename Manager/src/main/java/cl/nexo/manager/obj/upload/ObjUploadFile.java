package cl.nexo.manager.obj.upload;

import java.io.Serializable;
import java.util.Date;

import javax.mail.search.SizeTerm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"thumbnailFilename","newFilename","contentType","lastUpdated"})
public class ObjUploadFile implements Serializable {
	 
	    private Long id;
	    private String name;
	    private String thumbnailFilename;
	    private String newFilename;
	    private String contentType;
	    private Long size;
	    private Long thumbnailSize;
	    private Date dateCreated;
	    private Date lastUpdated;
	    private String url;
	    private String thumbnailUrl;
	    private String deleteUrl;
	    private String deleteType;
	    private int tipoarch_file;
	    private String exten_file;
	    private int uploader_file;
	    private int id_modulo;
	    private int carga_file;
	    private int tipo_documental;
	    private int str_tipo_documental;
	    private Long id_proyecto;
	    private Long id_operacion;
	    private String url_file;
	    private String str_user;
	    private int vm_file;
	    private int vs_file;
	    private int estadov_file;
	    private int estado_file;
	    
	    
	    
	    public ObjUploadFile(){}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getThumbnailFilename() {
			return thumbnailFilename;
		}


		public void setThumbnailFilename(String thumbnailFilename) {
			this.thumbnailFilename = thumbnailFilename;
		}


		public String getNewFilename() {
			return newFilename;
		}


		public void setNewFilename(String newFilename) {
			this.newFilename = newFilename;
		}


		public String getContentType() {
			return contentType;
		}


		public void setContentType(String contentType) {
			this.contentType = contentType;
		}


		public Long getSize() {
			return size;
		}


		public void setSize(Long size) {
			this.size = size;
		}


		public Long getThumbnailSize() {
			return thumbnailSize;
		}


		public void setThumbnailSize(Long thumbnailSize) {
			this.thumbnailSize = thumbnailSize;
		}


		public Date getDateCreated() {
			return dateCreated;
		}


		public void setDateCreated(Date dateCreated) {
			this.dateCreated = dateCreated;
		}


		public Date getLastUpdated() {
			return lastUpdated;
		}


		public void setLastUpdated(Date lastUpdated) {
			this.lastUpdated = lastUpdated;
		}


		public String getUrl() {
			return url;
		}


		public void setUrl(String url) {
			this.url = url;
		}


		public String getThumbnailUrl() {
			return thumbnailUrl;
		}


		public void setThumbnailUrl(String thumbnailUrl) {
			this.thumbnailUrl = thumbnailUrl;
		}


		public String getDeleteUrl() {
			return deleteUrl;
		}


		public void setDeleteUrl(String deleteUrl) {
			this.deleteUrl = deleteUrl;
		}


		public String getDeleteType() {
			return deleteType;
		}


		public void setDeleteType(String deleteType) {
			this.deleteType = deleteType;
		};
	    
		public int getTipoarch_file() {
			return tipoarch_file;
		}


		public void setTipoarch_file(int tipoarch_file) {
			this.tipoarch_file = tipoarch_file;
		}


		public String getExten_file() {
			return exten_file;
		}


		public void setExten_file(String exten_file) {
			this.exten_file = exten_file;
		}


		public int getUploader_file() {
			return uploader_file;
		}


		public void setUploader_file(int uploader_file) {
			this.uploader_file = uploader_file;
		}


		public int getId_modulo() {
			return id_modulo;
		}


		public void setId_modulo(int id_modulo) {
			this.id_modulo = id_modulo;
		}


		public int getCarga_file() {
			return carga_file;
		}


		public void setCarga_file(int carga_file) {
			this.carga_file = carga_file;
		}


		public int getTipo_documental() {
			return tipo_documental;
		}


		public void setTipo_documental(int tipo_documental) {
			this.tipo_documental = tipo_documental;
		}


		public Long getId_proyecto() {
			return id_proyecto;
		}


		public void setId_proyecto(Long id_proyecto) {
			this.id_proyecto = id_proyecto;
		}


		public Long getId_operacion() {
			return id_operacion;
		}


		public void setId_operacion(Long id_operacion) {
			this.id_operacion = id_operacion;
		}


		public String getUrl_file() {
			return url_file;
		}


		public void setUrl_file(String url_file) {
			this.url_file = url_file;
		}
		
		

		public String getStr_user() {
			return str_user;
		}


		public void setStr_user(String str_user) {
			this.str_user = str_user;
		}
		
		

		public int getStr_tipo_documental() {
			return str_tipo_documental;
		}


		public void setStr_tipo_documental(int str_tipo_documental) {
			this.str_tipo_documental = str_tipo_documental;
		}

		
		
		public int getVm_file() {
			return vm_file;
		}


		public void setVm_file(int vm_file) {
			this.vm_file = vm_file;
		}


		public int getVs_file() {
			return vs_file;
		}


		public void setVs_file(int vs_file) {
			this.vs_file = vs_file;
		}


		public int getEstadov_file() {
			return estadov_file;
		}


		public void setEstadov_file(int estadov_file) {
			this.estadov_file = estadov_file;
		}


		public int getEstado_file() {
			return estado_file;
		}


		public void setEstado_file(int estado_file) {
			this.estado_file = estado_file;
		}


		@Override
	    public String toString() {
	        return "files{" + "name=" + name + ", thumbnailFilename=" + thumbnailFilename + ", newFilename=" + newFilename + ", contentType=" + contentType + ", url=" + url + ", thumbnailUrl=" + thumbnailUrl + ", deleteUrl=" + deleteUrl + ", deleteType=" + deleteType+ ", thumbnailUrl=" + thumbnailUrl + ", deleteUrl=" + deleteUrl + ", tipoarch_file=" + tipoarch_file + ", exten_file=" + exten_file + ", uploader_file=" + uploader_file + ", id_modulo=" + id_modulo + ", carga_file=" + carga_file + ", tipo_documental=" + tipo_documental + ", id_proyecto=" + id_proyecto + ", id_operacion=" + id_operacion+ ", str_tipo_documental=" + str_tipo_documental + ", url_file=" + url_file+ ", id_file=" + id+ ", vm_file=" + vm_file+ ", vs_file=" + vs_file+ ", estadov_file=" + estadov_file+ ", estado_file=" + estado_file + '}';
	    }
	
}

