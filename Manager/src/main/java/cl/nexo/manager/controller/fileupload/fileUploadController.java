package cl.nexo.manager.controller.fileupload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.pattern.LogEvent;
import org.imgscalr.Scalr;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


















import cl.nexo.manager.access.fileupload.AccessFileUpload;
import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.access.general.tools.AccessUploadFile;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.upload.ObjUploadFile;


@Controller
@RequestMapping("/FileUploadUser")
public class fileUploadController {
	
	private static final Logger logger = Logger.getLogger(fileUploadController.class);
	
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	@RequestMapping
    public String index(){
        logger.info("FileUploadUser home");
        return "image/index";
    }
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
    public @ResponseBody Map list() {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessFileUpload filess = (AccessFileUpload) context.getBean("AccessFileUpload");
		
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		String urlBaseFisica = tools.getValorConfigById(13);
		String urlBaseMapping = tools.getValorConfigById(28);
		
		logger.info("Upload GET called LIST ---------------------- ");
        ArrayList<ObjUploadFile> list = filess.getlistFileUpload();
        for(ObjUploadFile fi : list) {
        	
        	fi.setUrl(urlBaseMapping+fi.getUrl_file()+fi.getNewFilename());
        	fi.setThumbnailUrl(urlBaseMapping+fi.getThumbnailUrl()+fi.getThumbnailFilename());
        	fi.setDeleteUrl(fi.getDeleteUrl()+fi.getId());
        	fi.setDeleteType(fi.getDeleteType());
        }
        Map<String, Object> files = new HashMap<>();
        files.put("files", list);
        
        return files;
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody Map list(MultipartHttpServletRequest request, HttpServletResponse response) throws ParseException {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessFileUpload filess = (AccessFileUpload) context.getBean("AccessFileUpload");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		AccessUploadFile uTools = (AccessUploadFile) context.getBean("AccessUploadFile");
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		
	    ObjLoginUser sesion = logins.getUserByLogin(authentication.getName());
	    
	    String urlBaseFisica = tools.getValorConfigById(13);
		String urlBaseMapping = tools.getValorConfigById(28);
		
		String fechaNow = format3.format(new Date());
		
		logger.info("Upload POST called SET ---------------------- ");
		Iterator<String> itr = request.getFileNames();
        MultipartFile mpf;
        List<ObjUploadFile> list = new LinkedList<>();
		
        while (itr.hasNext()) {
        	mpf = request.getFile(itr.next());
        	logger.info("Upload file {}"+ mpf.getOriginalFilename());
        	
        	String newFilenameBase = UUID.randomUUID().toString();
            String originalFileExtension = mpf.getOriginalFilename().substring(mpf.getOriginalFilename().lastIndexOf("."));
            String newFilename = newFilenameBase + originalFileExtension;
            String storageDirectory = urlBaseFisica+"/temp/user";
            String contentType = mpf.getContentType();
            
            /*******************************************************************/
            /***  CONFIGURACION DE UPLOAD **************************************/
            String thmberFile = "excell-6.png";
        	String thumUrl = "/temp/icons/office/excel/";
        	String fileUrl = "/temp/user/file/";
            String deleteUrl = "/Manager/FileUploadUser/delete/";
            String deleteType = "DELETE";
            int tipoArchivo = 2; 
            int idModulo = 1;
            int cargaFile = 0;
            int tipoDocumental = 0;
            int idProyecto = 0;
            int idOpercion = 0;
        	/******************************************************************/
            
            File newFile = new File(storageDirectory + "/file/" + newFilename);
            
            	try {
					uTools.uploadFile(newFile, mpf);
					
					
            		mpf.transferTo(newFile);
					
					/* 
					if file is image then
	            	BufferedImage thumbnail = Scalr.resize(ImageIO.read(newFile), 290);
	                String thumbnailFilename = newFilenameBase + "-thumbnail.png";
	                File thumbnailFile = new File(storageDirectory + "/" + thumbnailFilename);
	                ImageIO.write(thumbnail, "png", thumbnailFile);
	            	*/
					
					ObjUploadFile fil = new ObjUploadFile();
	            	fil.setName(mpf.getOriginalFilename());
	            	fil.setThumbnailFilename(thmberFile);
	            	fil.setNewFilename(newFilename);
	            	fil.setContentType(contentType);
	            	fil.setDateCreated(format3.parse(fechaNow));
	            	fil.setLastUpdated(format3.parse(fechaNow));
	            	fil.setThumbnailUrl(thumUrl);
	            	fil.setDeleteUrl(deleteUrl);
	            	fil.setDeleteType(deleteType);
	            	fil.setSize(mpf.getSize());
	            	fil.setTipoarch_file(tipoArchivo);
	            	fil.setExten_file(originalFileExtension);
	            	fil.setUploader_file(sesion.getId_user());
	            	fil.setId_modulo(idModulo);
	            	fil.setCarga_file(cargaFile);
	            	fil.setTipo_documental(tipoDocumental);
	            	fil.setId_proyecto(new Long(idProyecto));
	            	fil.setId_operacion(new Long(idOpercion));
	            	fil.setUrl_file(fileUrl);
	            	filess.createFile(fil);
	            	
	            	fil = filess.getMaxFileUpload(newFilename);
	            	
	            	fil.setUrl(urlBaseMapping+fil.getUrl_file()+fil.getNewFilename());
	            	fil.setThumbnailUrl(urlBaseMapping+fil.getThumbnailUrl()+fil.getThumbnailFilename());
	            	fil.setDeleteUrl(fil.getDeleteUrl()+fil.getId());
	            	fil.setDeleteType(fil.getDeleteType());
	            	
	            	
	            	list.add(fil);
	            	
            	
            	} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	
            	
         }
        logger.info("END POST called SET ---------------------- ");
		Map<String, Object> files = new HashMap<>();
	    files.put("files", list);
        return files;
	}
	
	 @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	 public @ResponseBody List delete(@PathVariable Long id){
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 AccessFileUpload filess = (AccessFileUpload) context.getBean("AccessFileUpload");
		 AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		 
		 logger.info("BEGIN Delete DELETE called SET ---------------------- ");
		 
		 String urlBaseFisica = tools.getValorConfigById(13);
		 String urlBaseMapping = tools.getValorConfigById(28);
		 
		 String storageDirectory = urlBaseFisica+"/temp/user";
		 
		 ObjUploadFile file = filess.getFileUpload(id);
		 File fil = new File(storageDirectory + "/user/file/" + file.getNewFilename());
		 filess.deleteFile(file.getId());
		 fil.delete();
		 
		 logger.info(" END Delete DELETE called SET ---------------------- ");
		 
		 List<Map<String, Object>> results = new ArrayList<>();
         Map<String, Object> success = new HashMap<>();
         success.put("success", true);
         results.add(success);
         return results;
		 
	}
	
}
