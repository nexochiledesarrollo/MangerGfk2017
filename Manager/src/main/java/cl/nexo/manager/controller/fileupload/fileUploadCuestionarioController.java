package cl.nexo.manager.controller.fileupload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

























import cl.nexo.manager.access.fileupload.AccessFileUpload;
import cl.nexo.manager.access.fileupload.AccessFileUploadM5;
import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.access.general.tools.AccessUploadFile;
import cl.nexo.manager.access.login.LoginAccess;
import cl.nexo.manager.access.traza.AccessTraza;
import cl.nexo.manager.obj.login.ObjLoginUser;
import cl.nexo.manager.obj.traza.ObjTrazaManager;
import cl.nexo.manager.obj.upload.ObjUploadFile;


@Controller
@RequestMapping("/FileUploadCuestionario")
public class fileUploadCuestionarioController {
	
	private static final Logger logger = Logger.getLogger(fileUploadCuestionarioController.class);
	
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	
	@RequestMapping
    public String index(){
        logger.info("FileUploadCuestionario home");
        return "image/index";
    }
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
    public @ResponseBody Map list(@RequestParam("id") int id,@RequestParam("cod") String cod) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessFileUploadM5 filess = (AccessFileUploadM5) context.getBean("AccessFileUploadM5");
		
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		String urlBaseFisica = tools.getValorConfigById(13);
		String urlBaseMapping = tools.getValorConfigById(28);
		
		logger.info("Upload GET CUESTIONARIO called LIST ---------------------- ");
        logger.debug("ID OPERACION: "+id);
        
		ArrayList<ObjUploadFile> list = filess.getlistFileUpload(id);
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
    public @ResponseBody Map list(MultipartHttpServletRequest request, 
    							  HttpServletResponse response
    							  ) throws ParseException {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessFileUploadM5 filess = (AccessFileUploadM5) context.getBean("AccessFileUploadM5");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		AccessUploadFile uTools = (AccessUploadFile) context.getBean("AccessUploadFile");
		AccessTraza traza = (AccessTraza) context.getBean("AccessTraza");
		SecurityContext securityContext = SecurityContextHolder.getContext();
	    Authentication authentication = securityContext.getAuthentication();
	    LoginAccess logins = (LoginAccess) context.getBean("LoginAccess");
		
	    ObjLoginUser sesion = logins.getUserByLogin(authentication.getName());
	    
	    ObjLoginUser user = logins.getUserByLogin(authentication.getName());
	    
	    String urlBaseFisica = tools.getValorConfigById(13);
		String urlBaseMapping = tools.getValorConfigById(28);
		
		String fechaNow = format3.format(new Date());
		
		logger.info("Upload POST CUESTIONARIO called SET ---------------------- ");
		    String idOp = request.getParameter("txt_idope_2");
		    int idOp2 = Integer.parseInt(request.getParameter("txt_idope_1"));
			logger.debug("ID OP: "+ idOp);
			logger.debug("ID OP2: "+ idOp2);
		
		
		Iterator<String> itr = request.getFileNames();
        MultipartFile mpf;
        List<ObjUploadFile> list = new LinkedList<>();
		
        while (itr.hasNext()) {
        	mpf = request.getFile(itr.next());
        	logger.info("Upload file {}"+ mpf.getOriginalFilename());
        	
        	String newFilenameBase = UUID.randomUUID().toString();
            String originalFileExtension = mpf.getOriginalFilename().substring(mpf.getOriginalFilename().lastIndexOf("."));
            String newFilename = newFilenameBase + originalFileExtension;
            String storageDirectory = urlBaseFisica+"/estudios/"+idOp+"/";
            String contentType = mpf.getContentType();
            
            
            
            /*******************************************************************/
            /***  CONFIGURACION DE UPLOAD **************************************/
            String thmberFile = "excell-6.png";
        	String thumUrl = "/temp/icons/office/excel/";
        	String fileUrl = "/estudios/"+idOp+"/";
            String deleteUrl = "/Manager/FileUploadCuestionario/delete/";
            String deleteType = "DELETE";
            int tipoArchivo = 2; 
            int idModulo = 5;
            int cargaFile = 0;
            int tipoDocumental = 2;
            int idProyecto = 0;
            int idOpercion = idOp2;
        	/******************************************************************/
            
            File newFile = new File(storageDirectory  + newFilename);
            
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
	            	fil.setId_operacion(new Long(idOp2));
	            	fil.setVs_file(0);
	            	fil.setEstado_file(1);
	            	fil.setEstadov_file(1);
	            	
	            	int lastVersion = filess.getLastVersionPUpload(idOp2, 2);
	            	int aux_version = lastVersion + 1;
	            	fil.setVm_file(aux_version);
	            	
	            	filess.updateStateVfile(idOp2, 2, 2);
	            	
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
	    
	    traza.setTraza(new ObjTrazaManager(0, fechaNow, user.getId_user(), 0, idOp2, 4, 0, 5, "ESTUDIO UPLOAD CUESTIONARIO", "USUARIO UPLOAD CUESTIONARIO ID: "+ idOp2,11));
	    
        return files;
	}
	
	 @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	 public @ResponseBody List delete(@PathVariable Long id){
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 AccessFileUpload filess = (AccessFileUpload) context.getBean("AccessFileUpload");
		 AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		 
		 logger.info("BEGIN Delete DELETE CUESTIOANRIO Called SET ---------------------- ");
		 
		 String urlBaseFisica = tools.getValorConfigById(13);
		 String urlBaseMapping = tools.getValorConfigById(28);
		 
		 ObjUploadFile obFile = filess.getFileUpload(id);
		 
		 String storageDirectory = urlBaseFisica+obFile.getUrl_file();
		 
		 ObjUploadFile file = filess.getFileUpload(id);
		 File fil = new File(storageDirectory + file.getNewFilename());
		 filess.deleteFile(file.getId());
		 fil.delete();
		 
		 logger.info(" END Delete DELETE CUESTIOANRIO Called SET ---------------------- ");
		 
		 List<Map<String, Object>> results = new ArrayList<>();
         Map<String, Object> success = new HashMap<>();
         success.put("success", true);
         results.add(success);
         return results;
		 
	}
	
}
