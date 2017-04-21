package cl.nexo.manager.imp.tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cl.nexo.manager.access.general.tools.AccessDateParceTools;

public class DateParceTools implements AccessDateParceTools {
	
	@Override
	public String getParseFecha(int date){
		String result = null;
		String aux_date = Integer.toString(date);
		String ano = aux_date.substring(0,4);
		String mes = aux_date.substring(4,6);
		String dia = aux_date.substring(6,8);
		result = dia+"-"+mes+"-"+ano;
		
		
		return result;
		
	}
	
	@Override
	public int getParseFechaToInt(String date){
		//22/02/2016
		int result = 0;
		String dia = date.substring(0,2);
		String mes = date.substring(3,5);
		String ano = date.substring(6,10);
		
		
		String aux_result = ano+mes+dia;
		
		result = Integer.parseInt(aux_result);
		
		return result;
		
	}
	
	@Override
	public int getDiferencia2fechas(String fechaIn, String fechaOut){
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		    Date fechainicial = new Date();
		    Date fechafinal = new Date();;
			
		    try {
				fechainicial = formatter.parse(fechaIn);
				fechafinal = formatter.parse(fechaOut);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			
			

			DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
			String fechainiciostring = df.format(fechainicial);
			try {
			fechainicial = df.parse(fechainiciostring);
			}
			catch (ParseException ex) {
			}
			
			String fechafinalstring = df.format(fechafinal);
			try {
			fechafinal = df.parse(fechafinalstring);
			}
			catch (ParseException ex) {
			}
			
			long fechainicialms = fechainicial.getTime();
			long fechafinalms = fechafinal.getTime();
			long diferencia = fechafinalms - fechainicialms;
			double dias = Math.floor(diferencia / 86400000L);// 3600*24*1000 
			
			
			
			return ( (int) dias);
	}
	
	
}
