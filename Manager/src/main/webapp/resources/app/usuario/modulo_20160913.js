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
	       // alert(xhr.status);
	       // alert(thrownError);
	       //alert('Se ha generado un error -- setUserLogin Modulo Usuario -- ,  favor contactar al adminsitrador!');
			$("#modalg-charge").modal("hide");
			//$( "#modalg-error-text" ).empty();
			//$('#modalg-error-text').html('<center>Se ha generado un error: <strong>-- valExistLoginUser Modulo Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText);
			//$("#modalg-error").modal("show");
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error: <strong>-- valExistLoginUser Modulo Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
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
				$( "#modalg-error-text" ).empty();
				$('#modalg-error-text').html('<center>Se ha generado un error: <strong>-- valExistRunUser Modulo Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText);
				$("#modalg-error").modal("show");
		}
		
	});
	
	return result;
}
//Function login
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
				$("#modalg-charge").modal("hide");
				$( "#modalg-error-text" ).empty();
				$('#modalg-error-text').html('<center>Se ha generado un error: <strong>-- detailLogin Modulo Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText);
				$("#modalg-error").modal("show");
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
		error: function(error){
			alert('Se ha generado un error - function chargeEditCombosPerfil , MANTENEDOR USUARIOS-,  favor contactar al adminsitrador!');
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
		error: function(error){
			alert('Se ha generado un error - function chargeEditCombosCargo , MANTENEDOR USUARIOS,  favor contactar al adminsitrador!');
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
			error: function(error){
				alert('Se ha generado un error - function chargeEditCombos Sub Div , EDIT USER - Combos,  favor contactar al adminsitrador!');
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
        ajax : '/Manager/RestLoginUser/getListUSerByCliente/0',
	    columns : [
	               	{ "data": null },
	                { "data": "run" },
	                { "data": "loginUser" },
	                { "data": "nombre" },
	                { "data": "apellido" },
	                { "data": "email" },
	                { "data": "perfil" },
	                { "data": "cargo" },
	                { "data": "division" },
	                { "data": "otp" },
	                { "data": "estado" }
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
	     },
	     "createdRow": function ( row, data, index ) {
	         if(data["otp"] == "ACTIVADO"){
	        	  $('td', row).eq(9).addClass('highgreen');
	         }else{
	        	  $('td', row).eq(9).addClass('highred');
	         }
	         if(data["estado"] == "ACTIVO"){
	        	  $('td', row).eq(10).addClass('highgreen');
	         }else{
	        	  $('td', row).eq(10).addClass('highred');
	         }
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
	$('#txt_07').val('0');
	$('#txt_088').val('0');
	$('#txt_10').val('0');
	$('#txt_08').val('0');
	$('#txt_11').val('1');
	$('#txt_13').val('0');
	$('#txt_14').val('0');
	$('#txt_17').val('');
	$('#txt_18').val('');
	$('#txt_19').val('');
	$('#txt_20').val('');
	$('#txt_21').val('');
	$('#txt_22').val('');
	$('#txt_23').val('');
	$('#txt_24').val('NO');
	
}

//---------------------------------------


//Show Modal
var handleDetalleUsuario = function(id) {
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
	var data = detailLogin(id);
	$('#grptxt_22').hide();
	
	$('#txt_01').val(data.run);
	$('#txt_02').val(data.login_user);
	$('#txt_03').val(data.nombre_user);
	$('#txt_04').val(data.app_user);
	$('#txt_05').val(data.mail_user);
	$('#txt_06').val(data.fnacimiento_user);
	$('#txt_08').val(data.id_division);
	
	chargeEditCombos(data.id_sdiv,data.id_perfil,data.id_cargo);
	
	$('#txt_11').val(data.estado_user);
	$('#txt_13').val(data.id_tipo_contrato);
	$('#txt_14').val(data.id_tipo_pago);
	$('#txt_17').val(data.id_pais);
	$('#txt_18').val(data.id_region);
	$('#txt_19').val(data.id_ciudad);
	$('#txt_20').val(data.anexo_user);
	$('#txt_21').val(data.direccion_user);
	$('#txt_23').val(data.id_sam_user);
	$('#txt_24').val(data.existe_delivery);
	$('#txt_111').val(data.if_user);
	
	
	
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
	
	
	$('#grptxt_22').show();
	
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
var showModalDeleteUser = function(id, login){
	
	$('#aux_del1').val('');
	$('#aux_del2').val('');
	
	$('#aux_del1').val($('#txt_00').val());
	$('#aux_del2').val($('#txt_02').val());
	$('#aux_del3').val($('#txt_24').val());
	
	$("#modal-delete-alert").modal("show");
	
}
//---------------------------------------
//Function module
var createLoginUser = function(){
	$("#error-newlogin").hide();
	var param = {
			run : $('#txt_01').val(),
			login: $('#txt_02').val(),
			nombre : $('#txt_03').val(),
			app : $('#txt_04').val(),
			mail : $('#txt_05').val(),
			nacimiento : $('#txt_06').val(),
			perfil : $('#txt_07').val(),
			division : $('#txt_08').val(),
			area : 0,//$('#txt_09').val(),
			cargo : $('#txt_10').val(),
			estado : $('#txt_11').val(),
			tcontrato : $('#txt_13').val(),
			tpago : $('#txt_14').val(),
			tjornada : 0,//$('#txt_15').val(),
			pais : $('#txt_17').val(),
			region  : $('#txt_18').val(),
			ciudad  : $('#txt_19').val(),
			anexo : $('#txt_20').val(),
			direccion : $('#txt_21').val(),
			numero : $('#txt_22').val(),
			idsam : $('#txt_23').val(),
			delivery : $('#txt_24').val(),
			subdiv : $('#txt_088').val(),
			ifuser : $('#txt_111').val()
	}
	
	//debugArray(param);
	
	if(param.run == ''){
		$('#etext-newlogin').html('Ingresa RUN de usuario!');
		$("#error-newlogin").show();
		$("#txt_01").focus();
	}else if(valExistRunUser(param.run) == 1){
		$('#etext-newlogin').html('RUN Ingresado ya existe en el sistema!');
		$("#error-newlogin").show();
		$("#txt_01").focus();
	}else if(!$.validateRut(param.run)){
		$('#etext-newlogin').html('RUN Ingresado no es valido!');
		$("#error-newlogin").show();
		$("#txt_01").focus();
	}else if(param.login == ''){
		$('#etext-newlogin').html('Ingresa Nombre de Usuario!');
		$("#error-newlogin").show();
		$("#txt_02").focus();
	}else if(valExistLoginUser(param.login) == 1){
		$('#etext-newlogin').html('Nombre de usuario ya existe en el sistema!');
		$("#error-newlogin").show();
		$("#txt_02").focus();
	}else if(param.nombre == ''){
		$('#etext-newlogin').html('Ingresa Nombre del Usuario!');
		$("#error-newlogin").show();
		$("#txt_03").focus();
	}else if(param.app == ''){
		$('#etext-newlogin').html('Ingresa Apellidos del Usuario!');
		$("#error-newlogin").show();
		$("#txt_04").focus();
	}else if(!regex.test(param.mail.trim())){
		$('#etext-newlogin').html('Ingresa E-mail del Usuario v&aacute;lido!');
		$("#error-newlogin").show();
		$("#txt_05").focus();
	}else if(param.perfil == 0){
		$('#etext-newlogin').html('Debes seleccionar perfil de Usuario!');
		$("#error-newlogin").show();
		$("#txt_07").focus();
	}else if(param.division == 0){
		$('#etext-newlogin').html('Debes seleccionar divisi&oacute;n a la que pertenece el Usuario!');
		$("#error-newlogin").show();
		$("#txt_08").focus();
	}else if(param.subdiv == 0){
		$('#etext-newlogin').html('Debes seleccionar sub-divisi&oacute;n a la que pertenece el Usuario!');
		$("#error-newlogin").show();
		$("#txt_088").focus();
	}else if(param.cargo == 0){
		$('#etext-newlogin').html('Debes seleccionar cargo al que pertenece el Usuario!');
		$("#error-newlogin").show();
		$("#txt_10").focus();
	}else{
		if(param.idsam == ''){param.idsam = 0;}
		$('#etext-newlogin').empty();
		$("#error-newlogin").hide();
		$("#txt_01").focus();
		
		
		$.ajax({
			url: "/Manager/RestLoginUser/setUserLogin",
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
				$( "#modalg-error-text" ).empty();
				$('#modalg-error-text').html('<center>Se ha generado un error: <strong>-- setUserLogin Modulo Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText);
				$("#modalg-error").modal("show");
			}
		});
	}
	
}
var updateLoginUser = function(){
	$("#error-newlogin").hide();
	var param = {
			id : $('#txt_00').val(),
			run : $('#txt_01').val(),
			login: $('#txt_02').val(),
			nombre : $('#txt_03').val(),
			app : $('#txt_04').val(),
			mail : $('#txt_05').val(),
			nacimiento : $('#txt_06').val(),
			perfil : $('#txt_07').val(),
			division : $('#txt_08').val(),
			area : 0,//$('#txt_09').val(),
			cargo : $('#txt_10').val(),
			estado : $('#txt_11').val(),
			tcontrato : $('#txt_13').val(),
			tpago : $('#txt_14').val(),
			tjornada : 0,//$('#txt_15').val(),
			pais : $('#txt_17').val(),
			region  : $('#txt_18').val(),
			ciudad  : $('#txt_19').val(),
			anexo : $('#txt_20').val(),
			direccion : $('#txt_21').val(),
			idsam : $('#txt_23').val(),
			delivery : $('#txt_24').val(),
			subdiv : $('#txt_088').val(),
			ifuser : $('#txt_111').val()
	}
	
	//debugArray(param);
	
	if(param.run == ''){
		$('#etext-newlogin').html('ERROR RUN de usuario! </br> Favor revisar con Administrador');
		$("#error-newlogin").show();
		$("#txt_01").focus();
	}else if(!$.validateRut(param.run)){
		$('#etext-newlogin').html('RUN Ingresado no es valido!');
		$("#error-newlogin").show();
		$("#txt_01").focus();
	}else if(param.login == ''){
		$('#etext-newlogin').html('Ingresa Nombre de Usuario!');
		$("#error-newlogin").show();
		$("#txt_02").focus();
	}else if(param.nombre == ''){
		$('#etext-newlogin').html('Ingresa Nombre del Usuario!');
		$("#error-newlogin").show();
		$("#txt_03").focus();
	}else if(param.app == ''){
		$('#etext-newlogin').html('Ingresa Apellidos del Usuario!');
		$("#error-newlogin").show();
		$("#txt_04").focus();
	}else if(!regex.test(param.mail.trim())){
		$('#etext-newlogin').html('Ingresa E-mail del Usuario v&aacute;lido!');
		$("#error-newlogin").show();
		$("#txt_05").focus();
	}else if(param.perfil == 0){
		$('#etext-newlogin').html('Debes seleccionar perfil de Usuario!');
		$("#error-newlogin").show();
		$("#txt_07").focus();
	}else if(param.division == 0){
		$('#etext-newlogin').html('Debes seleccionar divisi&oacute;n a la que pertenece el Usuario!');
		$("#error-newlogin").show();
		$("#txt_08").focus();
	}else if(param.subdiv == 0){
		$('#etext-newlogin').html('Debes seleccionar sub-divisi&oacute;n a la que pertenece el Usuario!');
		$("#error-newlogin").show();
		$("#txt_088").focus();
	}else if(param.cargo == 0){
		$('#etext-newlogin').html('Debes seleccionar cargo al que pertenece el Usuario!');
		$("#error-newlogin").show();
		$("#txt_10").focus();
	}else{
		if(param.idsam == ''){param.idsam = 0;}
		$('#etext-newlogin').empty();
		$("#error-newlogin").hide();
		$("#txt_01").focus();
		
		$.ajax({
			url: "/Manager/RestLoginUser/updateUserLogin",
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
				$( "#modalg-error-text" ).empty();
				$('#modalg-error-text').html('<center>Se ha generado un error: <strong>-- setUserLogin Modulo Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText);
				$("#modalg-error").modal("show");
			}
		});
	}
	
}
var deleteLoginUser = function(){
	$("#error-newlogin").hide();
	$("#modal-create").modal("hide");
	$("#modal-delete-alert").modal("hide");
	
	$("#modalg-charge").modal("show");
	
	var param = {
			id : $('#aux_del1').val(),
			login: $('#aux_del2').val(),
			delivery : $('#aux_del3').val()
	}
	
	    //debugArray(param);
		$.ajax({
			url: "/Manager/RestLoginUser/deleteUserLogin",
			type: "GET",
			dataType: "json",
			data: param,
			beforeSend: function(){
				//cargando los datos
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
				$( "#modalg-error-text" ).empty();
				$('#modalg-error-text').html('<center>Se ha generado un error: <strong>-- setUserLogin Modulo Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText);
				$("#modalg-error").modal("show");
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
			//customStatusCode();
			handleDataTableUsuarios();
			dataSpickers();
			$("#error-newlogin").hide();
			chargeDivisionCombo('txt_08');
			//chargePerfilCombo(1,'txt_07');
			//chargeAreaCombo('txt_09');
			//chargeCargoCombo('txt_10');
			chargeTipoContratoCombo('txt_13');
			chargeTipoPagoCombo('txt_14');
			handleJqueryFileUpload();
		}
	}
}();