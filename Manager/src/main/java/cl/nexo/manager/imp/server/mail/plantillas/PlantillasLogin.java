package cl.nexo.manager.imp.server.mail.plantillas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cl.nexo.manager.access.general.tools.AccessGeneralTools;
import cl.nexo.manager.access.server.mail.plantillas.AccessPlantillasLogin;

public class PlantillasLogin implements AccessPlantillasLogin {
	
	private static final Logger logger = Logger.getLogger(PlantillasLogin.class);
	DateFormat format1 = new SimpleDateFormat("yyyyMMdd");
	DateFormat format2 = new SimpleDateFormat("HH:mm:ss");
	DateFormat format3 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	@Override
	public String sendNewLogin(String nombre, String usuario, String clave, String id ){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		
		String urlHome = tools.getValorConfigById(7);
    	String deliveryCliente = tools.getValorConfigById(8);
    	String correoFrom = tools.getValorConfigById(5);
    	String correoTi = tools.getValorConfigById(14);
		
		/* Mensaje en modo texto
		String body = "<strong>Bienvenido</strong> "+usuario 
			      + "<br/><br/> Se ha habilitado su cuenta, los datos para activar tu cuenta es: <br/> "
			      +" * usuario: "+ usuario + " <br/>"
			      +" * clave: "+ clave + " <br/><br/>"
				  +"Haz click <input type='button' onClick='"+urlHome+"/AdminDelivery/otpService/activeUser/"+id+"' value='Aqu�' /> para activar tu cuenta <br/>"
				  +"Si el boton no abre url en navegador favor ingresar a la siguiente dirección en su navegador: <strong>"+urlHome+"/AdminDelivery/otpService/activeUser/"+id+"</strong>"
				  +"<br/><br/>"
				  +"Atte <br/> "+deliveryCliente+" Delivery ";
		*/
		
		String body2 ="<table width='100%' border='0' align='center' cellpadding='0' cellspacing='0' class='cont-bg' bgcolor='#f1f1f1' style='background:#f1f1f1; padding:27px 0px 0px 0px;'>"
					 +"<tr>"
					 +"<td align='center' valign='top'>"
					 +"<table width='650' border='0' align='center' cellpadding='0' cellspacing='0'>"
					 +"<tr>"
					 +"<td align='left' valign='top'>"
					 +"<table width='650' border='0' align='center' cellpadding='0' cellspacing='0'>"
					 +"<tr>"
					 +"<td width='287' align='left' valign='top' bgcolor='#FFFFFF' style='background:#FFF;'><img mc:edit='logo' src='"+urlHome+"/Manager/public_ftp_manager/mail/new_user/logo.png' style='display:block;' width='auto' height='94' alt='' /></td>"
					 +"<td width='363' height='94' align='left' valign='middle' bgcolor='#FFFFFF' style='background:#FFF;'>"
					 + "<table width='340' border='0' cellspacing='0' cellpadding='0'>"
					 +"<tr>"
					 +"<td align='right' valign='top' mc:edit='Date' style='font:Bold 18px Arial, Helvetica, sans-serif; color:#e7563a; text-transform:uppercase; padding-bottom:8px;'>Activaci&oacute;n de Cuenta</td>"
					 +"</tr>"
					 +"<tr>"	
					 +"<td align='left' valign='top'><table width='340' border='0' cellspacing='0' cellpadding='0'>"
					 +" </table></td>"
					 +"</tr>"
					 +" </table></td>"
					 +"</tr>"
					 +"<tr>"	
					 +"<td colspan='2' align='left' valign='top'>"
					 +"<table width='650' border='0' cellspacing='0' cellpadding='0'>"
					 +"<tr>"
					 +"<td align='left' valign='top'><img mc:edit='Banner-image' src='"+urlHome+"/Manager/public_ftp_manager/mail/new_user/banner-image.png' width='650' height='356' alt='' style='display:block;' /></td>"
					 +"</tr>"
					 +"<tr>"
					 +"<td align='left' valign='top' bgcolor='#e7563a' style='padding:25px 0px 18px 20px; background:#e7563a;'><table width='611' border='0' cellspacing='0' cellpadding='0'>"
					 +"<tr>"
					 +"<td align='left' valign='top' mc:edit='Banner-title' style='font:Normal 24px Arial, Helvetica, sans-serif; color:#FFF; padding-bottom:8px;'>Bienvenido(a) "+nombre+" a Plataforma de Gesti&oacute;n de Estudios GfK.</td>"
					 +"</tr>"
					 +"<tr>"
					 +"<td align='left' valign='top' mc:edit='Banner-text' style='font:Normal 12px Arial, Helvetica, sans-serif; color:#FFF; line-height:18px; padding:0px 0px 12px 4px;'>Para poder acceder a la plataforma debes activar tu cuenta nombre de usuario <strong>"+usuario+"</strong> en el siguiente link: </td>"
					 +"</tr>"
					 +"<tr>"
					 +"<td align='left' valign='top' mc:edit='Banner-read-more'><a href='"+urlHome+"/Manager/otpService/activeUser/"+id+"'><img src='"+urlHome+"/Manager/public_ftp_manager/mail/new_user/activar.png' width='128' height='31' alt='' /></a></td>"
					 +"</tr>"
					 +"<tr>"
					 +"<td align='left' valign='top' mc:edit='Banner-text' style='font:Normal 12px Arial, Helvetica, sans-serif; color:#FFF; line-height:18px; padding:0px 0px 12px 4px;'><br/>Si el bot&oacute;n no abre url en navegador, favor ingresar a la siguiente direcci&oacute;n en su navegador: <strong>"+urlHome+"/Manager/otpService/activeUser/"+id+"</strong></td>"
					 +"</tr>"
					 +"</table></td>"
					 +"</tr>"
					 +"</table>"
					 +"</td>"
					 +"</tr>"
					 +"</table>"
					 +"</td>"
					 +"</tr>"									
					 +"<tr>"
					 +"<td align='left' valign='top'><table width='650' border='0' cellspacing='0' cellpadding='0'>"
					 +"<tr>"
					 +"<td align='center' valign='top' bgcolor='#FFFFFF' style='background:#FFF; padding:28px 0px 27px 0px;'><table width='544' border='0' align='center' cellpadding='0' cellspacing='0'>"
					 +"<tr>"
					 +"<td align='center' valign='top' mc:edit='un-sp-text' style='font:Normal 12px Arial, Helvetica, sans-serif; color:#737373; line-height:18px;'>Tu has recibido este mail ya que se ha activado una cuenta en nuestra plataforma de Gesti&oacute;n de Estudios GfK, si crees que es un error favor contactar a "+correoTi+".</td>"
					 +"</tr>"									
					 +"<tr>"
					 +"<td align='center' valign='top' mc:edit='c-right-text' style='font:Bold 12px Arial, Helvetica, sans-serif; color:#737373; line-height:18px;'>Copyright &copy; 2017 Adimark.cl</td>"
					 +"</tr>"
					 +"</table></td>"				
					 +"</tr>"									
					 +"<tr>"
					 +"<td align='center' valign='top' style='padding:20px 0px 35px 0px;'><table width='277' border='0' cellspacing='0' cellpadding='0' style='font:Bold 12px Arial, Helvetica, sans-serif; color:#737373; line-height:18px;'>"
					 +"</table></td>"
					 +"</tr>"
					 +"</table>"
					 +"</td>"
					 +"</tr>"
					 +"</table>";
		
		return body2;
	}
	@Override
	public String updatePassLogin(String nombre, String usuario, String clave, String id){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		String urlHome = tools.getValorConfigById(7);
    	String deliveryCliente = tools.getValorConfigById(8);
    	String correoFrom = tools.getValorConfigById(5);
    	String correoTi = tools.getValorConfigById(14);
		/*
		String body = "<strong>Estimado</strong> "+usuario 
			      + "<br/><br/> Se ha activado su cuenta, los datos para activar tu cuenta es: <br/> "
			      +" * usuario: "+ usuario + " <br/>"
			      +" * clave: ********** <br/><br/>"
				  +"Haz click <input type='button' onClick='"+urlHome+"/Delivery/' value='Aqu�' /> para acceder a Delivery <br/>"
				  +"Si el boton no abre url en navegador favor ingresar a la siguiente direcci�n en su navegador: <strong>"+urlHome+"/Delivery/</strong>"
				  +"<br/><br/>"
				  +"Atte <br/> "+deliveryCliente+" Delivery ";
		*/
		String body2 ="<table width='100%' border='0' align='center' cellpadding='0' cellspacing='0' class='cont-bg' bgcolor='#f1f1f1' style='background:#f1f1f1; padding:27px 0px 0px 0px;'>"
				 +"<tr>"
				 +"<td align='center' valign='top'>"
				 +"<table width='650' border='0' align='center' cellpadding='0' cellspacing='0'>"
				 +"<tr>"
				 +"<td align='left' valign='top'>"
				 +"<table width='650' border='0' align='center' cellpadding='0' cellspacing='0'>"
				 +"<tr>"
				 +"<td width='287' align='left' valign='top' bgcolor='#FFFFFF' style='background:#FFF;'><img mc:edit='logo' src='"+urlHome+"/Manager/public_ftp_manager/mail/new_user/logo.png' style='display:block;' width='auto' height='94' alt='' /></td>"
				 +"<td width='363' height='94' align='left' valign='middle' bgcolor='#FFFFFF' style='background:#FFF;'>"
				 + "<table width='340' border='0' cellspacing='0' cellpadding='0'>"
				 +"<tr>"
				 +"<td align='right' valign='top' mc:edit='Date' style='font:Bold 18px Arial, Helvetica, sans-serif; color:#e7563a; text-transform:uppercase; padding-bottom:8px;'>Habilitaci&oacute;n de Cuenta</td>"
				 +"</tr>"
				 +"<tr>"	
				 +"<td align='left' valign='top'><table width='340' border='0' cellspacing='0' cellpadding='0'>"
				 +" </table></td>"
				 +"</tr>"
				 +" </table></td>"
				 +"</tr>"
				 +"<tr>"	
				 +"<td colspan='2' align='left' valign='top'>"
				 +"<table width='650' border='0' cellspacing='0' cellpadding='0'>"
				 +"<tr>"
				 +"<td align='left' valign='top'><img mc:edit='Banner-image' src='"+urlHome+"/Manager/public_ftp_manager/mail/new_user/banner-image.png' width='650' height='356' alt='' style='display:block;' /></td>"
				 +"</tr>"
				 +"<tr>"
				 +"<td align='left' valign='top' bgcolor='#e7563a' style='padding:25px 0px 18px 20px; background:#e7563a;'><table width='611' border='0' cellspacing='0' cellpadding='0'>"
				 +"<tr>"
				 +"<td align='left' valign='top' mc:edit='Banner-title' style='font:Normal 24px Arial, Helvetica, sans-serif; color:#FFF; padding-bottom:8px;'>Estimado(a) "+nombre+" se ha re activado tu cuenta en la Plataforma de Gesti&oacute;n de Estudios GfK.</td>"
				 +"</tr>"
				 +"<tr>"
				 +"<td align='left' valign='top' mc:edit='Banner-text' style='font:Normal 12px Arial, Helvetica, sans-serif; color:#FFF; line-height:18px; padding:0px 0px 12px 4px;'>Para poder acceder a la plataforma debes actualizar tu password en el siguiente link: </td>"
				 +"</tr>"
				 +"<tr>"
				 +"<td align='left' valign='top' mc:edit='Banner-read-more'><a href='"+urlHome+"/Manager/otpService/activeUser/"+id+"'><img src='"+urlHome+"/Manager/public_ftp_manager/mail/new_user/activar.png' width='128' height='31' alt='' /></a></td>"
				 +"</tr>"
				 +"<tr>"
				 +"<td align='left' valign='top' mc:edit='Banner-text' style='font:Normal 12px Arial, Helvetica, sans-serif; color:#FFF; line-height:18px; padding:0px 0px 12px 4px;'><br/>Si el bot&oacute;n no abre url en navegador, favor ingresar a la siguiente direcci&oacute;n en su navegador: <strong>"+urlHome+"/Manager/otpService/activeUser/"+id+"</strong></td>"
				 +"</tr>"
				 +"</table></td>"
				 +"</tr>"
				 +"</table>"
				 +"</td>"
				 +"</tr>"
				 +"</table>"
				 +"</td>"
				 +"</tr>"									
				 +"<tr>"
				 +"<td align='left' valign='top'><table width='650' border='0' cellspacing='0' cellpadding='0'>"
				 +"<tr>"
				 +"<td align='center' valign='top' bgcolor='#FFFFFF' style='background:#FFF; padding:28px 0px 27px 0px;'><table width='544' border='0' align='center' cellpadding='0' cellspacing='0'>"
				 +"<tr>"
				 +"<td align='center' valign='top' mc:edit='un-sp-text' style='font:Normal 12px Arial, Helvetica, sans-serif; color:#737373; line-height:18px;'>Tu has recibido este mail ya que se ha activado una cuenta en nuestra plataforma de Gesti&oacte;n de Estudios GfK, si crees que es un error favor contactar a "+correoTi+".</td>"
				 +"</tr>"									
				 +"<tr>"
				 +"<td align='center' valign='top' mc:edit='c-right-text' style='font:Bold 12px Arial, Helvetica, sans-serif; color:#737373; line-height:18px;'>Copyright &copy; 2017 Adimark.cl</td>"
				 +"</tr>"
				 +"</table></td>"				
				 +"</tr>"									
				 +"<tr>"
				 +"<td align='center' valign='top' style='padding:20px 0px 35px 0px;'><table width='277' border='0' cellspacing='0' cellpadding='0' style='font:Bold 12px Arial, Helvetica, sans-serif; color:#737373; line-height:18px;'>"
				 +"</table></td>"
				 +"</tr>"
				 +"</table>"
				 +"</td>"
				 +"</tr>"
				 +"</table>";
		
		return body2;
	}
	@Override
	public String updatePassByAdmin(String nombre, String usuario, String clave, int id ){
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Module.xml");
		AccessGeneralTools tools = (AccessGeneralTools) context.getBean("AccessGeneralTools");
		String urlHome = tools.getValorConfigById(7);
    	String deliveryCliente = tools.getValorConfigById(8);
    	String correoTi = tools.getValorConfigById(14);
    	
		
		/*
		String body = "<strong>Estimado</strong> "+nombre 
			      + "<br/><br/> Se ha actualizado su clave de acceso: <br/> "
			      +" * usuario: "+ usuario + " <br/>"
			      +" * clave: "+ clave + " <br/><br/>"
				  +"Haz click <input type='button' onClick='http://"+urlHome+"/AdminDelivery/otp/usuario/"+id+"' value='Aqu�' /> para activar tu cuenta <br/>"
				  +"Si el boton no abre url en navegador favor ingresar a la siguiente direcci�n en su navegador: <strong>http://"+urlHome+"/AdminDelivery/otp/usuario/"+id+"</strong>"
				  +"<br/><br/>"
				  +"Atte <br/> "+deliveryCliente+" Delivery ";
		*/
		String body2 ="<table width='100%' border='0' align='center' cellpadding='0' cellspacing='0' class='cont-bg' bgcolor='#f1f1f1' style='background:#f1f1f1; padding:27px 0px 0px 0px;'>"
				 +"<tr>"
				 +"<td align='center' valign='top'>"
				 +"<table width='650' border='0' align='center' cellpadding='0' cellspacing='0'>"
				 +"<tr>"
				 +"<td align='left' valign='top'>"
				 +"<table width='650' border='0' align='center' cellpadding='0' cellspacing='0'>"
				 +"<tr>"
				 +"<td width='287' align='left' valign='top' bgcolor='#FFFFFF' style='background:#FFF;'><img mc:edit='logo' src='"+urlHome+"/Manager/public_ftp_manager/mail/new_user/logo.png' style='display:block;' width='auto' height='94' alt='' /></td>"
				 +"<td width='363' height='94' align='left' valign='middle' bgcolor='#FFFFFF' style='background:#FFF;'>"
				 + "<table width='340' border='0' cellspacing='0' cellpadding='0'>"
				 +"<tr>"
				 +"<td align='right' valign='top' mc:edit='Date' style='font:Bold 18px Arial, Helvetica, sans-serif; color:#e7563a; text-transform:uppercase; padding-bottom:8px;'>Cambio de Clave Usuario</td>"
				 +"</tr>"
				 +"<tr>"	
				 +"<td align='left' valign='top'><table width='340' border='0' cellspacing='0' cellpadding='0'>"
				 +" </table></td>"
				 +"</tr>"
				 +" </table></td>"
				 +"</tr>"
				 +"<tr>"	
				 +"<td colspan='2' align='left' valign='top'>"
				 +"<table width='650' border='0' cellspacing='0' cellpadding='0'>"
				 +"<tr>"
				 +"<td align='left' valign='top'><img mc:edit='Banner-image' src='"+urlHome+"/Manager/public_ftp_manager/mail/new_user/banner-image.png' width='650' height='356' alt='' style='display:block;' /></td>"
				 +"</tr>"
				 +"<tr>"
				 +"<td align='left' valign='top' bgcolor='#e7563a' style='padding:25px 0px 18px 20px; background:#e7563a;'><table width='611' border='0' cellspacing='0' cellpadding='0'>"
				 +"<tr>"
				 +"<td align='left' valign='top' mc:edit='Banner-title' style='font:Normal 24px Arial, Helvetica, sans-serif; color:#FFF; padding-bottom:8px;'>Estimado(a) "+nombre+" se ha solicitado restaurar clave de su cuenta en la Plataforma de Gesti&oacte;n de Estudios GfK.</td>"
				 +"</tr>"
				 +"<tr>"
				 +"<td align='left' valign='top' mc:edit='Banner-text' style='font:Normal 12px Arial, Helvetica, sans-serif; color:#FFF; line-height:18px; padding:0px 0px 12px 4px;'>Para poder restaurar clave favor acceder a la plataforma con tu cuenta nombre de usuario <strong>"+usuario+"</strong> en el siguiente link: </td>"
				 +"</tr>"
				 +"<tr>"
				 +"<td align='left' valign='top' mc:edit='Banner-read-more'><a href='"+urlHome+"/Manager/otp/usuario/"+id+"'><img src='"+urlHome+"/Manager/public_ftp_manager/mail/new_user/activar.png' width='128' height='31' alt='' /></a></td>"
				 +"</tr>"
				 +"<tr>"
				 +"<td align='left' valign='top' mc:edit='Banner-text' style='font:Normal 12px Arial, Helvetica, sans-serif; color:#FFF; line-height:18px; padding:0px 0px 12px 4px;'><br/>Si el bot&oacute;n no abre url en navegador, favor ingresar a la siguiente direcci&oacute;n en su navegador: <strong>"+urlHome+"/Manager/otp/usuario/"+id+"</strong></td>"
				 +"</tr>"
				 +"</table></td>"
				 +"</tr>"
				 +"</table>"
				 +"</td>"
				 +"</tr>"
				 +"</table>"
				 +"</td>"
				 +"</tr>"									
				 +"<tr>"
				 +"<td align='left' valign='top'><table width='650' border='0' cellspacing='0' cellpadding='0'>"
				 +"<tr>"
				 +"<td align='center' valign='top' bgcolor='#FFFFFF' style='background:#FFF; padding:28px 0px 27px 0px;'><table width='544' border='0' align='center' cellpadding='0' cellspacing='0'>"
				 +"<tr>"
				 +"<td align='center' valign='top' mc:edit='un-sp-text' style='font:Normal 12px Arial, Helvetica, sans-serif; color:#737373; line-height:18px;'>Tu has recibido este mail ya que se ha generado una solicitud de restaurar clave de su cuenta en nuestra plataforma de Gesti&oacute;n de Estudios GfK, si crees que es un error favor contactar a "+correoTi+".</td>"
				 +"</tr>"									
				 +"<tr>"
				 +"<td align='center' valign='top' mc:edit='c-right-text' style='font:Bold 12px Arial, Helvetica, sans-serif; color:#737373; line-height:18px;'>Copyright &copy; 2017 Adimark.cl</td>"
				 +"</tr>"
				 +"</table></td>"				
				 +"</tr>"									
				 +"<tr>"
				 +"<td align='center' valign='top' style='padding:20px 0px 35px 0px;'><table width='277' border='0' cellspacing='0' cellpadding='0' style='font:Bold 12px Arial, Helvetica, sans-serif; color:#737373; line-height:18px;'>"
				 +"</table></td>"
				 +"</tr>"
				 +"</table>"
				 +"</td>"
				 +"</tr>"
				 +"</table>";
		
		return body2;
		
		//return body;
	}

}
