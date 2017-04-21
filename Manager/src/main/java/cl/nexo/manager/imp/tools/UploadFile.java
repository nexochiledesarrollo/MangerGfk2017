package cl.nexo.manager.imp.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import cl.nexo.manager.access.general.tools.AccessUploadFile;

public class UploadFile implements AccessUploadFile {
	private static final Logger logger = Logger.getLogger(UploadFile.class);
	@Override
	public int uploadFile(File localFile, MultipartFile archivo) throws IOException{
		int result = 0;
		FileOutputStream os = null;
		try {
    		os = new FileOutputStream(localFile);
    		os.write(archivo.getBytes());
    		return result;
    	} finally {
    		if (os != null) {
    			try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    	}
		
		
		
	}
	@Override
	public String getFileExtension(File file) {
	    String name = file.getName();
	    try {
	        return name.substring(name.lastIndexOf(".") + 1);
	    } catch (Exception e) {
	        return "";
	    }
	}
	
	@Override
	public int existDirectorio(String url, int crea){
		//1 crea directorio -- 2 existe  --3 no existe pero no se crea
		int result = 0;
		File folder = new File(url);
		if (folder.isDirectory()) { // escribimos algo si es un directorio 
			logger.info("Existe direcctorio: "+url);
			result = 2;
		}else{
			if(crea == 1){
				folder.mkdirs();
				logger.info("Se crea direcctorio: "+url);
				result = 1;
			}else{
				logger.info("No se crea direcctorio: "+url);
				result = 3;
			}
		}
		return result;
	}
	
}
