/*
Version: 1.0
Author: Sebastian Barraza
Website: Manager 2.0
Date: 04-09-2016
*/
//-------------------------------------------------------------------------------
//Validate atrivute
var valExistLoginUser = function(login){
	var result = null;	
	$.ajax({
		url: "/Manager/RestLoginUser/getExistLogin/"+login,
		type: "GET",
		dataType: "json",
		data: false,
		async: false,
		beforeSend: function(){
			//cargando los datos
			$( "#modalg-val-text" ).empty();
			$('#modalg-val-text').html('<center>Se esta validando login: <strong>-- '+login+' --</strong> <br/>  Favor espere... </center>');
			$("#modalg-val").modal("show");
		},
		success: function(data){
			//alert(data.result);
			$( "#modalg-val-text" ).empty();
			$("#modalg-val").modal("hide");
			
			result = data.result; 
		},
		error: function (xhr, ajaxOptions, thrownError) {
	       
			$("#modalg-charge").modal("hide");
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error: <strong>-- valExistLoginUser Modulo Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
		
	});
	
	return result;
}
var valExistRunUser = function(run){
	
	
	var result = null;	
	$.ajax({
		url: "/Manager/RestLoginUser/getExistRun/"+run,
		type: "GET",
		dataType: "json",
		data: false,
		async: false,
		beforeSend: function(){
			//cargando los datos
			$( "#modalg-val-text" ).empty();
			$('#modalg-val-text').html('<center>Se esta validando RUN: <strong>-- '+run+' --</strong> <br/>  Favor espere... </center>');
			$("#modalg-val").modal("show");
		},
		success: function(data){
			//alert(data.result);
			$( "#modalg-val-text" ).empty();
			$("#modalg-val").modal("hide");
			
			result = data.result; 
		},
		error: function (xhr, ajaxOptions, thrownError) {
		       // alert(xhr.status);
		       // alert(thrownError);
		       //alert('Se ha generado un error -- setUserLogin Modulo Usuario -- ,  favor contactar al adminsitrador!');
				$("#modalg-charge").modal("hide");
				var data = {
						status: xhr.status,
						text: '<center>Se ha generado un error: <strong>-- valExistRunUser Modulo Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
				}
				errorAjaxRequest(data);
		}
		
	});
	
	return result;
}
//Function login
var detailApoderado = function(id){
	var result = null;
	$.ajax({
		url: "/Manager/RestApoderado/getDetailApoderadoById/"+id,
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
				$("#modalg-charge").modal("hide");
				var data = {
						status: xhr.status,
						text: '<center>Se ha generado un error: <strong>-- detailLogin Modulo Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
				}
				errorAjaxRequest(data);
		}
		
	});
	
	return result;
	
}

//Charge custom combo
var chargeEditCombosPerfil = function(id_perfil){
	var cliente = 1;
	
	var param ={
			div : $('#txt_088').val()
	}
	//alert('cliente:'+cliente+' div:'+param.div);
	
	$.ajax({
		url: "/Manager/RestPerfilUser/getComboPerfil/"+cliente,
		type: "GET",
		dataType: "json",
		data: param,
		async: false,
		beforeSend: function(){
			//cargando los datos
		},
		success: function(data){
			var combo = $('#txt_07');
			combo.empty();
			// iteramos a través del arreglo de ciudades
			combo.append("<option value='0'>Seleccione</option>");
            $.each(data, function(index, val) {
            	combo.append("<option value='"+val.value+"'>" + val.name + "</option>");
            });
            $('#txt_07').val(id_perfil);
		},
		error: function(xhr, ajaxOptions, thrownError){
			//alert('Se ha generado un error - function chargeEditCombosPerfil , MANTENEDOR USUARIOS-,  favor contactar al adminsitrador!');
			var data = {
					status: xhr.status,
					text: 'Se ha generado un error - function chargeEditCombosPerfil , MANTENEDOR USUARIOS-,  favor contactar al adminsitrador! <br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
	});


}
var chargeEditCombosCargo = function(id_cargo){
	var cliente = 1;
	
	var param ={
			div : $('#txt_088').val()
	}
	//alert('cliente:'+cliente+' div:'+param.div);
	
	$.ajax({
		url: "/Manager/RestServiceCargo/getCombo",
		type: "GET",
		dataType: "json",
		data: param,
		async: false,
		beforeSend: function(){
			//cargando los datos
		},
		success: function(data){
			var combo = $('#txt_10');
			combo.empty();
			// iteramos a través del arreglo de ciudades
			combo.append("<option value='0'>Seleccione</option>");
            $.each(data, function(index, val) {
            	combo.append("<option value='"+val.value+"'>" + val.name + "</option>");
            });
            $('#txt_10').val(id_cargo);
        },
		error: function(xhr, ajaxOptions, thrownError){
			//alert('Se ha generado un error - function chargeEditCombosCargo , MANTENEDOR USUARIOS,  favor contactar al adminsitrador!');
			var data = {
					status: xhr.status,
					text: 'Se ha generado un error - function chargeEditCombosCargo , MANTENEDOR USUARIOS,  favor contactar al adminsitrador! <br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
	});


}


var chargeEditCombos = function(id_sdiv,id_perfil,id_cargo){
	
	var param ={
			div : $('#txt_08').val()
		}
		
		$.ajax({
			url: "/Manager/RestServiceDivision/getSubDivCombo",
			type: "GET",
			dataType: "json",
			data: param,
			async: false,
			beforeSend: function(){
				//cargando los datos
			},
			success: function(data){
				var combo = $('#txt_088');
				combo.empty();
				// iteramos a través del arreglo de ciudades
				combo.append("<option value='0'>Seleccione</option>");
	            $.each(data, function(index, val) {
	            	combo.append("<option value='"+val.value+"'>" + val.name + "</option>");
	            });
	            
	            $('#txt_088').val(id_sdiv);
	            chargeEditCombosPerfil(id_perfil);
	            chargeEditCombosCargo(id_cargo);
	            
	        },
			error: function(xhr, ajaxOptions, thrownError){
				//alert('Se ha generado un error - function chargeEditCombos Sub Div , EDIT USER - Combos,  favor contactar al adminsitrador!');
				var data = {
						status: xhr.status,
						text: 'Se ha generado un error - function chargeEditCombos Sub Div , EDIT USER - Combos,  favor contactar al adminsitrador! <br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
				}
				errorAjaxRequest(data);
			}
		});
	
	
	
}


//Data Table
var handleDataTableUsuarios = function() {
	
	$('#data-table tfoot th').each( function () {
        var title = $('#data-table thead th').eq( $(this).index() ).text();
        $(this).html( '<input type="text" placeholder="Buscar '+title+'" />' );
    } );
	
	var table = $("#data-table").DataTable({
		dom: 'C<"clear">lBfrtip',
		"language": {
        	"url": "http://cdn.datatables.net/plug-ins/1.10.12/i18n/Spanish.json"
        },
		colVis: {
            "buttonText": "Ocultar Columnas"
        },
        "iDisplayLength": 25,
		"lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "Todo"]],
		buttons: [{
            extend: "copy",
            buttonText: "Copiar",
            className: "btn-sm"
        }, {
            extend: "csv",
            className: "btn-sm"
        }, {
            extend: "excel",
            className: "btn-sm"
        }, {
            extend: "pdf",
            className: "btn-sm"
        }, {
            extend: "print",
            className: "btn-sm"
        }],
        responsive: false,
        autoFill: true,
        colReorder: true,
        keys: true,
        rowReorder: false,
        select: false,
        serverSide : false,
        processing : true,
        "scrollY": "100%",
        "scrollCollapse": true,
        "scrollX": true,
        ajax: {
            url: '/Manager/RestApoderado/getListApoderado',
            error : function(xhr, status, error) {
        		var data = {
						status: xhr.status,
						text: '<center>Se ha generado un error: <strong>-- List Actividad 15 Workflow --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+error +'<br/>Detail: '+xhr.responseText
				}
				errorAjaxRequest(data);
        	}
        },
	    columns : [
	               	{ "data": null },
	                { "data": "cedula" },
	                { "data": "nombres" },
	                { "data": "apellido" },
	                { "data": "cargo" },
	                { "data": "email" }
	              
	            ],
	     "initComplete": function( ) {
	    	// Apply the filter
    	    table.columns().eq( 0 ).each( function ( colIdx ) {
    	        $( 'input', table.column( colIdx ).footer() ).on( 'keyup change', function () {
    	            table
    	                .column( colIdx )
    	                .search( this.value )
    	                .draw();
    	        } );
    	    } );
	     }
	});
   
	table.on( 'order.dt search.dt', function () {
		table.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();
   
};
//---------------------------------------
//Combo plugins
var dataSpickers = function(){
	$("#txt_06").datepicker({
        todayHighlight: true,
        format: 'dd-mm-yyyy',
        lang:'es'
    });
} 
//---------------------------------------
//Tools User forms

var eraseForm = function(){
	
	$('#txt_01').val('');
	$('#txt_02').val('');
	$('#txt_03').val('');
	$('#txt_04').val('');
	$('#txt_05').val('');
	$('#txt_06').val('');
	$('#txt_07').val(0);
	$('#txt_088').val(0);
	$('#txt_10').val(0);
	$('#txt_08').val(0);
	$('#txt_11').val(1);
	$('#txt_13').val(0);
	$('#txt_14').val(0);
	$('#txt_17').val('');
	$('#txt_18').val('');
	$('#txt_19').val('');
	$('#txt_199').val(0);
	$('#txt_20').val('');
	$('#txt_21').val('');
	$('#txt_22').val('');
	$('#txt_23').val('');
	$('#txt_24').val('NO');
	
}

//---------------------------------------
var editChargeComboUbicacion = function(pais,region,ciudad,comuna){
	/*
	$('#txt_17').val(data.id_pais);
	$('#txt_18').val(data.id_region);
	$('#txt_19').val(data.id_ciudad);
	*/
	$('#txt_17').val(pais);
	
	chargeComboRegion('txt_18',pais,1,region);
	
	chargeComboCiudad('txt_19',region,1,ciudad);
	
	chargeComboComuna('txt_199',ciudad,1,comuna);
	
}

//Show Modal
var handleDetalleApoderado = function(id) {
	//$("#modalg-charge").modal("show");
	
	eraseForm();
	
	$('#txt_01').prop('disabled', true);
	$('#txt_02').prop('disabled', true);
	
	//Style modal box
	$('#header-createuser').hide();
	$('#button-createuser').hide();
	
	$('#header-updateuser').show();
	
	//data
	$('#txt_00').val(id);
	//alert('Ingresa: '+ $('#txt_00').val());
	
	
    var data = detailApoderado(id);
	
	
	$('#txt_01').val(data.cedula);
	$('#txt_03').val(data.nombres);
	$('#txt_04').val(data.apellido);
	$('#txt_05').val(data.email);
	$('#txt_06').val(data.cargo);
	
	
	//chargeEditCombos(data.id_sdiv,data.id_perfil,data.id_cargo);
	

	//$('#txt_198').select2('val',data.lang_user);
	//$('#txt_198').val(data.lang_user).trigger("change");
	//editChargeComboUbicacion(data.id_pais,data.id_region,data.id_ciudad,data.id_comuna);
	
	

	
	if($('#txth_01').val() == '1'){
		$('#button-updateuser').show();
		$('#button-deleteuser').show();
	}else if($('#txth_01').val() == '2'){
		$('#button-updateuser').show();
		$('#button-deleteuser').hide();
	}else if($('#txth_01').val() == '3'){
		$('#button-updateuser').hide();
		$('#button-deleteuser').hide();
	}
	
	
	//$("#modalg-charge").modal("hide");
	$("#modal-create").modal("show");
};

var showModalCreateUser = function(id) {
	
	eraseForm();
	$('#txt_01').prop('disabled', false);
	$('#txt_02').prop('disabled', false);
	
	$('#header-updateuser').hide();
	$('#button-updateuser').hide();
	$('#button-deleteuser').hide();
	$('#button-createuser').show();
	$('#header-createuser').show();
	
	$("#modal-create").modal("show");
	
};



var showModalUploadUser = function(id) {
	$("#modal-upload").modal("show");
	
};
var showModalDeleteApoderado = function(id, login){
	
	$('#aux_del1').val('');
    $('#aux_del1').val($('#txt_01').val());
    $("#modal-delete-alert").modal("show");
	
}
//---------------------------------------
//Function module
var createApoderado = function(){
	$("#error-newlogin").hide();
	var param = {
			cedula : $('#txt_01').val(),
			nombre : $('#txt_03').val(),
			apellido : $('#txt_04').val(),
			email : $('#txt_05').val(),
			cargo : $('#txt_06').val(),
			
	}
	
	//debugArray(param);
	
	if(param.cedula == ''){
		$('#etext-newlogin').html('Ingresa Cedula de usuario!');
		$("#error-newlogin").show();
		$("#txt_01").focus();
	}else if(param.nombre == ''){
		$('#etext-newlogin').html('Ingresa Nombre del Usuario!');
		$("#error-newlogin").show();
		$("#txt_03").focus();
	}else if(param.apellido == ''){
		$('#etext-newlogin').html('Ingresa Apellido del Usuario!');
		$("#error-newlogin").show();
		$("#txt_04").focus();
	}else if(param.cargo == ''){
		$('#etext-newlogin').html('Debes Especificar el cargo!');
		$("#error-newlogin").show();
		$("#txt_06").focus();
	
	}else{
		
		
		$.ajax({
			url: "/Manager/RestApoderado/setApoderado",
			type: "GET",
			dataType: "json",
			data: param,
			beforeSend: function(){
				//cargando los datos
				$("#modal-create").modal("hide");
				$("#modalg-charge").modal("show");
			},
			success: function(data){
				$("#modalg-charge").modal("hide");
				$( "#modalg-success-text" ).empty();
				$('#modalg-success-text').html('<center>'+ data.text +'</center><br>');
				$("#modalg-success").modal("show");
				
				eraseForm();
				
				$('#data-table').DataTable().ajax.reload();
			
			},
			error: function (xhr, ajaxOptions, thrownError) {
		       // alert(xhr.status);
		       // alert(thrownError);
		       //alert('Se ha generado un error -- setUserLogin Modulo Usuario -- ,  favor contactar al adminsitrador!');
				$("#modalg-charge").modal("hide");
				var data = {
						status: xhr.status,
						text: '<center>Se ha generado un error: <strong>-- setUserLogin Modulo Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
				}
				errorAjaxRequest(data);
			}
		});
	}
	
}
var updateApoderado = function(){

$("#error-newlogin").hide();
var param = {
		
		cedula : $('#txt_01').val(),
	    nombre : $('#txt_03').val(),
		apellido : $('#txt_04').val(),
		email : $('#txt_05').val(),
		cargo : $('#txt_06').val(),
		
}


if(param.nombre == ''){
	$('#etext-newlogin').html('Ingresa Nombre del Usuario!');
	$("#error-newlogin").show();
	$("#txt_03").focus();
}else if(param.apellido == ''){
	$('#etext-newlogin').html('Ingresa Apellidos del Usuario!');
	$("#error-newlogin").show();
	$("#txt_04").focus();
}else if(param.email == ''){
	$('#etext-newlogin').html('Ingresa E-mail del Usuario v&aacute;lido!');
	$("#error-newlogin").show();
	$("#txt_05").focus();
}else if(param.cargo == ''){
	$('#etext-newlogin').html('Debes especificar el cargo!');
	$("#error-newlogin").show();
	$("#txt_06").focus();
}else{
	
	$.ajax({
		url: "/Manager/RestApoderado/updateApoderado",
		type: "GET",
		dataType: "json",
		data: param,
		beforeSend: function(){
			//cargando los datos
			$("#modal-create").modal("hide");
			$("#modalg-charge").modal("show");
		},
		success: function(data){
			$("#modalg-charge").modal("hide");
			$( "#modalg-success-text" ).empty();
			$('#modalg-success-text').html('<center>'+ data.text +'</center><br>');
			$("#modalg-success").modal("show");
			
			eraseForm();
			
			$('#data-table').DataTable().ajax.reload();
		
		},
		error: function (xhr, ajaxOptions, thrownError) {
			$("#modalg-charge").modal("hide");
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error: <strong>-- setUserLogin Modulo Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
	});
}


}




var deleteApoderado = function(){
	
	$("#error-newlogin").hide();
	$("#modal-create").modal("hide");
	$("#modal-delete-alert").modal("hide");
	
	$("#modalg-charge").modal("show");
	
	var param = {
			cedula : $('#aux_del1').val(),
			
	}
	
	   
		$.ajax({
			url: "/Manager/RestApoderado/deleteApoderado",
			type: "GET",
			dataType: "json",
			data: param,
			beforeSend: function(){
				//cargando los datos
			},
			success: function(data){
				$("#modalg-charge").modal("hide");
				$("#modalg-success-text" ).empty();
				$('#modalg-success-text').html('<center>'+ data.text +'</center><br>');
				$("#modalg-success").modal("show");
				
				eraseForm();
				
				$('#data-table').DataTable().ajax.reload();
			
			},
			error: function (xhr, ajaxOptions, thrownError) {
		       // alert(xhr.status);
		       // alert(thrownError);
		       //alert('Se ha generado un error -- setUserLogin Modulo Usuario -- ,  favor contactar al adminsitrador!');
				$("#modalg-charge").modal("hide");
				var data = {
						status: xhr.status,
						text: '<center>Se ha generado un error: <strong>-- setUserLogin Modulo Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
				}
				errorAjaxRequest(data);
			}
		});
	
	
}
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Upload file
var handleJqueryFileUpload = function() {
	//$('<div class="alert alert-danger"/>').text("Servicio de Upload de archivos temporalmente fuera de servicio - " + new Date).appendTo("#fileupload");
	// Initialize the jQuery File Upload widget:
    $('#fileupload').fileupload({
        // Uncomment the following to send cross-domain cookies:
        //xhrFields: {withCredentials: true},
        url: '/Manager/FileUploadUser/upload'
    });
    
    $('#fileupload').fileupload('option', {
        // Enable image resizing, except for Android and Opera,
        // which actually support image resizing, but fail to
        // send Blob objects via XHR requests:
        disableImageResize: /Android(?!.*Chrome)|Opera/
            .test(window.navigator.userAgent),
        maxFileSize: 5000000,
        acceptFileTypes: /(\.|\/)(xls|xlsx)$/i
    });
    
   // Load existing files:
    $('#fileupload').addClass('fileupload-processing');
    $.ajax({
        // Uncomment the following to send cross-domain cookies:
        //xhrFields: {withCredentials: true},
        url: $('#fileupload').fileupload('option', 'url'),
        dataType: 'json',
        context: $('#fileupload')[0]
    }).always(function () {
        $(this).removeClass('fileupload-processing');
    }).done(function (result) {
        $(this).fileupload('option', 'done')
            .call(this, $.Event('done'), {result: result});
    });
    
    
}
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Charge excel
var chargeFileUser = function(id){
	alert('Archivo id: '+ id);

}
var customStatusCode = function(){
	$.ajaxSetup({
		statusCode: 
		{
			901: sessionTimeOutModal
		}
	});
	
}
/************************************************************************************/
/************************INIT APP****************************************************/
var usuarios = function() {
	"use strict";
	return {
		init : function() {
			
		    handleDataTableUsuarios();
		    chargeDivisionCombo('txt_08');
			$("#error-newlogin").hide();
			
		}
	}
}();