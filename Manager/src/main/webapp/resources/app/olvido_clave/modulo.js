/*
Version: 1.0
Author: Sebastian Barraza
Website: Manager 2.0
Date: 04-09-2016
*/

var valExisteLogin = function(mail){
	
	var result = null;	
	var param = {
		mail : mail	
	}
	
	$.ajax({
		url: "/Manager/RestOtpUser/getExistLoginByMail",
		type: "GET",
		dataType: "json",
		data: param,
		async: false,
		beforeSend: function(){
			//cargando los datos
		},
		success: function(data){
			//alert(data.result);
			
			result = data.result; 
		},
		error: function (xhr, ajaxOptions, thrownError) {
	       // alert(xhr.status);
	       // alert(thrownError);
	       //alert('Se ha generado un error -- setUserLogin Modulo Usuario -- ,  favor contactar al adminsitrador!');
			$( "#etext-modulo" ).empty();
			$('#etext-modulo').html('<center>Se ha generado un error: <strong>-- valExisteLogin Modulo Olvido Clave User --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText);
			$("#error-modulo").show();
		}
		
	});
	
	return result;
	
	
}

var sendOtpLogin = function(mail){
	var result = 0;	
	var param = {
			mail : mail	
	}
	$.ajax({
		url: "/Manager/RestOtpUser/updateOtpLoginByMail",
		type: "GET",
		dataType: "json",
		data: param,
		async: false,
		beforeSend: function(){
			//cargando los datos
		},
		success: function(data){
			//alert(data.result);
			
			result = data.result; 
		},
		error: function (xhr, ajaxOptions, thrownError) {
	       // alert(xhr.status);
	       // alert(thrownError);
	       //alert('Se ha generado un error -- setUserLogin Modulo Usuario -- ,  favor contactar al adminsitrador!');
			$( "#etext-modulo" ).empty();
			$('#etext-modulo').html('<center>Se ha generado un error: <strong>-- newOtpLoginBy Modulo Olvido Clave User --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText);
			$("#error-modulo").show()
		}
		
	});
	
	return result;
	
	
}
var detailLogin = function(id){
	var result = null;
	$.ajax({
		url: "/Manager/RestLoginUser/getDetailUserById/"+id,
		type: "GET",
		dataType: "json",
		data: false,
		async: false,
		beforeSend: function(){
			//cargando los datos
		},
		success: function(data){
			//debugArray(data);
			result = data; 
		},
		error: function (xhr, ajaxOptions, thrownError) {
		       // alert(xhr.status);
		       // alert(thrownError);
		       //alert('Se ha generado un error -- setUserLogin Modulo Usuario -- ,  favor contactar al adminsitrador!');
				$( "#etext-modulo" ).empty();
				$('#etext-modulo').html('<center>Se ha generado un error: <strong>-- detailLogin Modulo Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText);
				$("#error-modulo").show()
		}
		
	});
	
	return result;
	
}


var ocultarErrorAlert = function(){
	
	$('#error-modulo').hide();
}

var solictarActive = function(){
	var mail = $('#text_01').val();
	//alert('mail: '+ mail);
	
	if(valExisteLogin(mail) == 0){
		$('#etext-modulo').html('E-mail Ingresado no existe en el sistema!');
		$("#error-modulo").show();
		$("#txt_01").focus();
	}else{
		
		var res  = sendOtpLogin(mail);
		
		if(res == 1){
			window.open("/Manager/login","_self");
			
		}else{
			$('#etext-modulo').html('Se ha generado un error en la solicitud, favor intente m&aacute;s tarde.');
			$("#error-modulo").show();
			$("#txt_01").focus();
			
		}
	}

}
var activarCuenta = function(){
	var param = {
		id: $('#text_00').val(),
		pass : $('#text_01').val(),
		pass2 : $('#text_02').val()
	 }
	
	if(param.id == ''){
		$('#etext-modulo').html('Se ha generado un error en la solicitud, favor intente m&aacute;s tarde.');
		$("#error-modulo").show();
		$("#txt_01").focus();
		
	}else if(param.pass == ''){
		$('#etext-modulo').html('Debes ingresar password!');
		$("#error-modulo").show();
		$("#txt_01").focus();
	
	}else if(param.pass2 == ''){
		$('#etext-modulo').html('Debes ingresar re ingreso de password!');
		$("#error-modulo").show();
		$("#txt_02").focus();
	}else if(param.pass != param.pass2){
		$('#etext-modulo').html('Existe diferencia en las claves ingresadas,  favor corregir!');
		$("#error-modulo").show();
		$("#txt_02").focus();
	}else{
		$.ajax({
			url: "/Manager/RestOtpUser/activeOtpLoginById",
			type: "GET",
			dataType: "json",
			data: param,
			beforeSend: function(){
				
			},
			success: function(data){
				 
				 if(data.result == 1){
					 alert('Usuario Activado!');
					 window.open("/Manager/home","_self");
				 } 
			},
			error: function(error){
				alert('Se ha generado un error -- ACTIVAR USUARIO -- ,  favor contactar al adminsitrador!');
			}
		});
	}
}

var modulo = function() {
	"use strict";
	return {
		init : function() {
			ocultarErrorAlert();
		}
	}
}();