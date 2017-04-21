package cl.nexo.manager.imp.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Random;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cl.nexo.manager.access.dao.AccessDao;
import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.obj.conf.ObjConfGeneral;
import cl.nexo.manager.obj.login.ObjLoginUser;

public class GeneralTools implements AccessGeneralTools {

	private static final Logger logger = Logger.getLogger(GeneralTools.class);	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public ObjConfGeneral getConfigGeneralById(int idAtributo){
		Connection conn = null;
		
		ObjConfGeneral conf = new ObjConfGeneral();
		
		String query = "SELECT v.id_valor "
				   +"	      ,v.id_atributo " 
				   +"		  ,a.valor_atributo "
				   +"	      ,v.cadena_valor "
				   +"	      ,v.id_cliente "
				   +"		  ,c.nombre_cliente "
				   +"	FROM man_valor_cadena v "
				   +"	INNER JOIN man_atributo a ON v.id_atributo = a.cod_atributo "
				   +"	INNER JOIN del_clientes c ON v.id_cliente = c.id_cliente "
				   +"	WHERE v.id_atributo = " + idAtributo;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {

				conf.setId_valor(rs.getInt("id_valor"));
				conf.setId_atributo(rs.getInt("id_atributo"));
				conf.setValor_atributo(rs.getString("valor_atributo"));
				conf.setCadena_valor(rs.getString("cadena_valor"));
				conf.setId_cliente(rs.getInt("id_cliente"));
			}
			return conf;
		}catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	@Override
	public String getValorConfigById(int idAtributo){
		Connection conn = null;
		String result ="";
		
		String query = "SELECT v.id_valor "
				   +"	      ,v.id_atributo " 
				   +"		  ,a.valor_atributo "
				   +"	      ,v.cadena_valor "
				   +"	      ,c.id_cliente "
				   +"		  ,c.nombre_cliente "
				   +"	FROM man_valor_cadena v "
				   +"	INNER JOIN man_atributo a ON v.id_atributo = a.cod_atributo "
				   +"	INNER JOIN man_cliente c ON a.id_cliente = c.id_cliente "
				   +"	WHERE v.id_atributo = " + idAtributo;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				result = rs.getString("cadena_valor");
				
			}
			return result;
		}catch (SQLException e) {
			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
		
		
		
		
	}
	
	@Override
	public int splitNumberExcel(String number){
		int result = 0;
		if(!number.equals("BLANK") && !number.equals("FORMULA") ){
			//logger.info("Valor String: "+ number);
			double amount = Double.parseDouble(number);
			DecimalFormat formatter = new DecimalFormat("#,###");
			String format = formatter.format(amount);
			result = Integer.parseInt(format);
			//logger.info("Valor Split: "+result);
			 // 004
		}
		return result;
	}
	
  @Override
  public String getNewCodigo(int longitud){
	  String cadenaAleatoria = "";
	  long milis = new java.util.GregorianCalendar().getTimeInMillis();
	  Random r = new Random(milis);
	  int i = 0;
	  while ( i < longitud){
		  char c = (char)r.nextInt(255);
		  if ( (c >= '0' && c <='9') || (c >='A' && c <='Z') ){
			  cadenaAleatoria += c;
			  i ++;
		  }
	  }
	  return cadenaAleatoria;
	  
  }
  
  @Override
  public String getNewUuId(){
	  
	  String uuid = java.util.UUID.randomUUID().toString();
	  
	  return uuid;
  }
  
  @Override
  public int existCharOfString(String cadena, String caracter){
	  /* 0 = No existe
	   * 1 = Existe
	   * */
	  int resp = 0;
	  if(cadena.indexOf(caracter) == -1){
		    //no existe 
		  resp = 0;
	  }else{ 
		    //existe
		  resp = 1;
	  }
	  return resp;
	}
}
