package cl.nexo.manager.imp.leer.excel;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.format.annotation.DateTimeFormat;





import cl.nexo.manager.access.LeerCargaSam.AccessLeerCargaSam;
import cl.nexo.manager.access.cliente.AccessCliente;
import cl.nexo.manager.access.combo.box.AccessComboBox;
import cl.nexo.manager.access.proyecto.AccessCotizacion;
import cl.nexo.manager.access.proyecto.AccessEstudio;
import cl.nexo.manager.imp.proyecto.Estudio;
import cl.nexo.manager.obj.proyecto.ObjEstudio;
import cl.nexo.manager.obj.proyecto.ObjEstudioDetalle;
import cl.nexo.manager.obj.proyecto.ObjEstudioProducto;
import cl.nexo.manager.obj.proyecto.ObjResultCreaCotOp;

public class LeerCargaSam implements AccessLeerCargaSam {
	private static final Logger logger = Logger.getLogger(LeerCargaSam.class);
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy");
	
	
	boolean antiguo = false;
	
	
	@Override
	public void leerArchivoRespuestas(String archivo){
		ArrayList<String[]> Lista_Datos_Celda = new ArrayList<>();
		 
		if (archivo.contains(".xlsx")) {
			 
		   GENERAR_XLSX(archivo, Lista_Datos_Celda);
		 
		   logger.info("Ingresa excel .xls, "+ archivo);
		   
		   antiguo = false;
		 
		  } else if (archivo.contains(".xls")) {
		 
		   GENERAR_XLS(archivo, Lista_Datos_Celda);
		   
		   logger.info("Ingresa excel .xlsx, "+ archivo);
		   
		   antiguo = true;
		 
		  }
		 //logger.debug("Excel: "+ archivo);
		 
		 //this.setRespuestas(Lista_Datos_Celda, resp);
		
		this.setSamEstudios(Lista_Datos_Celda);
	}
	
	private void GENERAR_XLSX(String Nombre_Archivo, List Lista_Datos_Celda) {
		 
		  try {
		 
		   /**
		 
		    * Crea una nueva instancia de la clase FileInputStream
		 
		    */
		 
		   FileInputStream fileInputStream = new FileInputStream(
		 
		     Nombre_Archivo);
		 
		   /**
		 
		    * Crea una nueva instancia de la clase XSSFWorkBook
		 
		    */
		 
		   XSSFWorkbook Libro_trabajo = new XSSFWorkbook(fileInputStream);
		 
		   XSSFSheet Hoja_hssf = Libro_trabajo.getSheetAt(0);
		 
		   /**
		 
		    * Iterar las filas y las celdas de la hoja de cálculo para obtener
		 
		    * toda la data.
		 
		    */
		 
		   Iterator Iterador_de_Fila = Hoja_hssf.rowIterator();
		 
		   while (Iterador_de_Fila.hasNext()) {
		 
		    XSSFRow Fila_hssf = (XSSFRow) Iterador_de_Fila.next();
		 
		    Iterator iterador = Fila_hssf.cellIterator();
		 
		    List Lista_celda_temporal = new ArrayList();
		 
		    while (iterador.hasNext()) {
		 
		     XSSFCell Celda_hssf = (XSSFCell) iterador.next();
		 
		     Lista_celda_temporal.add(Celda_hssf);
		 
		    }
		 
		    Lista_Datos_Celda.add(Lista_celda_temporal);
		 
		   }
		 
		  } catch (Exception e) {
		 
		   e.printStackTrace();
		 
		  }
		 
		 }
		 
		 private void GENERAR_XLS(String Nombre_Archivo, List Lista_Datos_Celda) {
		 
		  try {
		 
		   /**
		 
		    * Crea una nueva instancia de la clase FileInputStream
		 
		    */
		 
		   FileInputStream fileInputStream = new FileInputStream(
		 
		     Nombre_Archivo);
		 
		   /**
		 	
		    * Crea una nueva instancia de la clase POIFSFileSystem
		 
		    */
		 
		   POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
		 
		   /**
		 
		    * Crea una nueva instancia de la clase HSSFWorkBook
		 
		    */
		 
		   HSSFWorkbook Libro_trabajo = new HSSFWorkbook(fsFileSystem);
		 
		   HSSFSheet Hoja_hssf = Libro_trabajo.getSheetAt(0);
		 
		   /**
		 
		    * Iterar las filas y las celdas de la hoja de cálculo para obtener
		 
		    * toda la data.
		 
		    */
		 
		   Iterator Iterador_de_Fila = Hoja_hssf.rowIterator();
		 
		   while (Iterador_de_Fila.hasNext()) {
		 
		    HSSFRow Fila_hssf = (HSSFRow) Iterador_de_Fila.next();
		 
		    Iterator iterador = Fila_hssf.cellIterator();
		 
		    List Lista_celda_temporal = new ArrayList();
		 
		    while (iterador.hasNext()) {
		 
		     HSSFCell Celda_hssf = (HSSFCell) iterador.next();
		 
		     Lista_celda_temporal.add(Celda_hssf);
		 
		    }
		 
		    Lista_Datos_Celda.add(Lista_celda_temporal);
		 
		   }
		 
		  } catch (Exception e) {
		 
		   e.printStackTrace();
		 
		  }
		 
		 }
		 
		 private void setSamEstudios(ArrayList<String[]> arrayDatosExcel){
			 ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
			 AccessCliente cliente = (AccessCliente) context.getBean("AccessCliente");
			 AccessEstudio estudio = (AccessEstudio) context.getBean("AccessEstudio");
			 AccessCotizacion coti = (AccessCotizacion) context.getBean("AccessCotizacion");
			 AccessComboBox combo = (AccessComboBox) context.getBean("AccessComboBox");
			 
			 int r = 0;
			    for (String[] next : arrayDatosExcel) {
			    	System.out.print("Array Row: " + r++ + " -> ");
			    	
			    	if(r >= 2){
			    		
			    		ObjEstudio est = new ObjEstudio();
			    		ObjEstudioDetalle det = new ObjEstudioDetalle();
			    		ObjEstudioProducto prod = new ObjEstudioProducto();
			    		
			    		est.setCod_sam(next[1]);
			    		est.setTipo_sam(next[2]);
			    		est.setNombre_operacion(next[3]);
			    		est.setId_cliente(cliente.getIdClienteByName(next[4]));
			    		
			    		est.setCola_operacion(1);
			    		
			    		
			    		if(next[7] != null){
			    		
			    			String aux_dateini =  format3.format(next[7]);
			    			est.setFcreacion_proyectom(aux_dateini);
			    		}
			    		
			    			est.setScreacion_proyectom(3); //Creacion automatica por workflow
			    		
			    		if(next[8] != null){
			    			String aux_dateE =  format3.format(next[8]);
			    			det.setDate_prob_entre_est_op(aux_dateE);
			    		}
			    		
			    		
			    		if(next[9] != null){
			    			String aux_dateC =  format3.format(next[9]);
			    			det.setDdate_pres_clie_op(aux_dateC);
			    		}
			    		
			    		if(next[10] != null){
			    			String aux_dateEn =  format3.format(next[10]);
			    			est.setFcom_entrega(aux_dateEn);
			    		}
			    		
			    		if(next[10] != null){
			    			String aux_dateEn =  format3.format(next[10]);
			    			est.setFcom_entrega(aux_dateEn);
			    		}
			    		
			    		if(next[11] != null){
			    			String aux_dateEn2 =  format3.format(next[11]);
			    			est.setFcom_ini_campo(aux_dateEn2);
			    		}
			    		
			    		
			    		if(next[21] != null){
			    			String aux_sector =  format3.format(next[21]);
			    			int aux = 0;
			    			if(aux_sector.equals("CE")){
			    				aux = 1;
			    			}else if(aux_sector.equals("CC")){
			    				aux = 2;
			    			}
			    			
			    			est.setSector_medicion(aux);
			    		}
			    		
			    		if(next[23] != null){
			    			//Producto
			    			String[] numerosComoArray = next[23].split(" ");
			    			prod.setId_producto(Integer.parseInt(numerosComoArray[0]));
			    			prod.setStr_producto(numerosComoArray[1]);
			    		}
			    		
			    		if(next[24] != null){
			    			//tipo estudio
			    			String[] numerosComoArray = next[24].split(" ");
			    			det.setTipo_estudio(numerosComoArray[0]);
			    		}
			    		
			    		if(next[25] != null){
			    			//Geo
			    			String[] numerosComoArray = next[25].split(" ");
			    			det.setId_geografia(numerosComoArray[0]);
			    		}
			    		
			    		if(next[26] != null){
			    			//Digital
			    			String[] numerosComoArray = next[26].split(" ");
			    			det.setDigital_op(numerosComoArray[0]);
			    		}
			    		
			    		if(next[27] != null){
			    			//Digital
			    			String[] numerosComoArray = next[27].split(" ");
			    			det.setDigital_op(numerosComoArray[0]);
			    		}
			    		
			    		if(next[30] != null){
			    			//Tipo entrevista
			    			est.setId_tipo_entrevista(combo.getValueComboSelected(next[30]));
			    		}
			    		
			    		det.setId_clie_facturar(cliente.getIdClienteByName(next[5]));
			    		det.setPrecio_venta(Float.parseFloat(next[6]));
			    		
			    		
			    		ArrayList<ObjEstudioProducto> lprod = new ArrayList<ObjEstudioProducto>();
 			    		lprod.add(prod);
			    		
 			    		
			    		
			    		est.setDetalle(det);
			    		est.setProductos(lprod);
			    		
			    		ObjResultCreaCotOp res = estudio.setOperacion(est);
			    		
			    		
			    		
			    	}
			    	r = r + 1;
			    	
			    }
		}


}
