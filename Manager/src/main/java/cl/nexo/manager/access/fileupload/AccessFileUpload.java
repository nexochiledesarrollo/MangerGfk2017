package cl.nexo.manager.access.fileupload;

import java.util.ArrayList;
import java.util.List;

import cl.nexo.manager.obj.upload.ObjUploadFile;

public interface AccessFileUpload {

	public ArrayList<ObjUploadFile> getlistFileUpload();

	public int createFile(ObjUploadFile fil);

	public ObjUploadFile getMaxFileUpload(String newfile_file);

	public ObjUploadFile getFileUpload(Long id);

	public int deleteFile(Long id);

	

}
