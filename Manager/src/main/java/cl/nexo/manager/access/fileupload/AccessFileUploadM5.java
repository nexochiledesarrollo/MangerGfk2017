package cl.nexo.manager.access.fileupload;

import java.util.ArrayList;

import cl.nexo.manager.obj.upload.ObjUploadFile;

public interface AccessFileUploadM5 {

	public ObjUploadFile getMaxFileUpload(String newfile_file);

	public ObjUploadFile getFileUpload(Long id);

	public int createFile(ObjUploadFile fil);

	public int deleteFile(Long id);

	public ArrayList<ObjUploadFile> getlistFileUpload(int id);

	public int getLastVersionPUpload(int op, int tipo);

	public int updateStateVfile(int op, int tipo, int state);

}
