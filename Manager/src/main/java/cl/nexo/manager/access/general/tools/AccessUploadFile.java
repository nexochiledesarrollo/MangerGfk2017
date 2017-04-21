package cl.nexo.manager.access.general.tools;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface AccessUploadFile {

	public int uploadFile(File localFile, MultipartFile archivo) throws IOException;

	public String getFileExtension(File file);

	public int existDirectorio(String url, int crea);

	
	
	

}
