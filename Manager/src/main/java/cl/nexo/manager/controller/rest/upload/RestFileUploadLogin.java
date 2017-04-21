package cl.nexo.manager.controller.rest.upload;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;









import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;






import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.access.general.tools.AccessUploadFile;
import cl.nexo.manager.access.menu.AccessMenu;
import cl.nexo.manager.obj.upload.FileMeta;;

@RestController
@RequestMapping("/RestFileUploadLogin")
public class RestFileUploadLogin {
	 /***************************************************
     * URL: /RestFileUploadLogin/upload  
     * upload(): receives files
     * @param request : MultipartHttpServletRequest auto passed
     * @param response : HttpServletResponse auto passed
     * @return LinkedList<FileMeta> as json format
     ****************************************************/
	
	private static final Logger logger = Logger.getLogger(RestFileUploadLogin.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	DateFormat format4 = new SimpleDateFormat("dd-MM-yyyy");
	LinkedList<FileMeta> files =  new LinkedList<FileMeta>();
	
	@RequestMapping(value = "/upload", headers = "content-type=multipart/*", method = RequestMethod.POST)
	public LinkedList<FileMeta> upload(MultipartHttpServletRequest request, HttpServletResponse response)
	{
		
		 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		 AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		 AccessUploadFile uploadTools = (AccessUploadFile) context.getBean("AccessUploadFile");
		 
		 
		 String urlBasePrivada = tools.getValorConfigById(13);
		 String urlTmpExcelUser = "user/";
		 
		//1. build an iterator
         Iterator<String> itr =  request.getFileNames();
         MultipartFile mpf = null;
		 
       //2. get each file
         while(itr.hasNext()){
 
             //2.1 get next MultipartFile
             mpf = request.getFile(itr.next()); 
             logger.info(mpf.getOriginalFilename() +" uploaded! "+files.size());
 
             //2.2 if files > 10 remove the first from the list
             if(files.size() >= 10){
            	 files.pop();
             }
             
             //2.3 create new fileMeta
             FileMeta fileMeta = new FileMeta();
             fileMeta.setFileName(mpf.getOriginalFilename());
             fileMeta.setFileSize(mpf.getSize()/1024+" Kb");
             fileMeta.setFileType(mpf.getContentType());
 
             try {
                fileMeta.setBytes(mpf.getBytes());
 
                 // copy file to local disk (make sure the path "e.g. D:/temp/files" exists else create path)            
                uploadTools.existDirectorio(urlBasePrivada+urlTmpExcelUser, 1); 
                FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(urlBasePrivada+urlTmpExcelUser+mpf.getOriginalFilename()));
 
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
             //2.4 add to files
             files.add(fileMeta);
         }
        // result will be like this
        // [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
        return files;
		 
	}
	
	/***************************************************
     * URL: /RestFileUploadLogin/get/{value}
     * get(): get file as an attachment
     * @param response : passed by the server
     * @param value : value from the URL
     * @return void
     ****************************************************/
     @RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
     public void get(HttpServletResponse response,@PathVariable String value){
         FileMeta getFile = files.get(Integer.parseInt(value));
         try {      
                response.setContentType(getFile.getFileType());
                response.setHeader("Content-disposition", "attachment; filename=\""+getFile.getFileName()+"\"");
                FileCopyUtils.copy(getFile.getBytes(), response.getOutputStream());
         }catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
         }
     }
	
	
}
