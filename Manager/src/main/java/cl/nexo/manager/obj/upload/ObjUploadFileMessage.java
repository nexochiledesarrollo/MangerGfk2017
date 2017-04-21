package cl.nexo.manager.obj.upload;

import java.io.Serializable;

public class ObjUploadFileMessage implements Serializable {
	
	private static final long serialVersionUID = -4093981756240899937L;
	private String owner;
	private String description;
	private String filename;
	private String fileType;
	
	public ObjUploadFileMessage(){
		super();
	}
	
	public ObjUploadFileMessage(String owner, String description, String filename, String fileType) {
		super();
		this.owner = owner;
		this.description = description;
		this.filename = filename;
		this.fileType = fileType;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
