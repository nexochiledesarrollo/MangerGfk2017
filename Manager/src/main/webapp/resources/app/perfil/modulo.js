/*
Version: 1.0
Author: sbarraza
Website: Manager
Date: 03-09-2016 
 */
//-------------------------------------------------------------------------------
//Validate atrivute
var valExistPerfilUser = function(perfil){
	var result = null;	
	$.ajax({
		url: "/Manager/RestPerfilUser/getExistPerfil/"+perfil,
		type: "GET",
		dataType: "json",
		data: false,
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
			$("#modalg-charge").modal("hide");
			$( "#modalg-error-text" ).empty();
			$('#modalg-error-text').html('<center>Se ha generado un error: <strong>-- valExistPerfilUser Modulo Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText);
			$("#modalg-error").modal("show");
		}
		
	});
	
	return result;
}
//Function Perfil
var detailPerfil = function(id){
	var result = null;
	$.ajax({
		url: "/Manager/RestPerfilUser/getDetailUserById/"+id,
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
var getPermisoModulo = function(tipo,modulo,perfil){
	var param = {
			tipo: tipo,
			modulo: modulo,
			perfil: perfil
	}
	$.ajax({
		url: "/Manager/RestPerfilUser/setPerfilModuloById",
		type: "GET",
		dataType: "json",
		data: param,
		async: true,
		beforeSend: function(){
			//cargando los datos
			//$("#modalg-charge").modal("show");
		},
		success: function(data){
			//alert(data.result);
			var urlAccess = "/Manager/RestPerfilUser/getListAccessPerfilById/"+$('#txt2_00').val();
			$('#data-table3').DataTable().ajax.url(urlAccess).load();
			
			//$("#modalg-charge").modal("hide");
			 
		},
		error: function (xhr, ajaxOptions, thrownError) {
	       // alert(xhr.status);
	       // alert(thrownError);
	       //alert('Se ha generado un error -- setUserLogin Modulo Usuario -- ,  favor contactar al adminsitrador!');
			$("#modalg-charge").modal("hide");
			$( "#modalg-error-text" ).empty();
			$('#modalg-error-text').html('<center>Se ha generado un error: <strong>-- valExistPerfilUser Modulo Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText);
			$("#modalg-error").modal("show");
		}
		
	});
	
}

var getPermisoSdiv = function(tipo,sdiv,perfil){
	var param = {
			tipo: tipo,
			sdiv: sdiv,
			perfil: perfil
	}
	$.ajax({
		url: "/Manager/RestPerfilUser/setPerfilSubDivById",
		type: "GET",
		dataType: "json",
		data: param,
		async: true,
		beforeSend: function(){
			//cargando los datos
			//$("#modalg-charge").modal("show");
		},
		success: function(data){
			//alert(data.result);
			var urlAccess = "/Manager/RestPerfilUser/getListAccessDivPerfilById/"+$('#txt2_00').val();
			$('#data-table4').DataTable().ajax.url(urlAccess).load();
			
			//$("#modalg-charge").modal("hide");
			 
		},
		error: function (xhr, ajaxOptions, thrownError) {
	       // alert(xhr.status);
	       // alert(thrownError);
	       //alert('Se ha generado un error -- setUserLogin Modulo Usuario -- ,  favor contactar al adminsitrador!');
			$("#modalg-charge").modal("hide");
			$( "#modalg-error-text" ).empty();
			$('#modalg-error-text').html('<center>Se ha generado un error: <strong>-- valExistPerfilUser Modulo Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText);
			$("#modalg-error").modal("show");
		}
		
	});
	
}
var getIdPerfilUserByName = function(perfil){
	var result = null;	
	$.ajax({
		url: "/Manager/RestPerfilUser/getIdPerfilUserByName/"+perfil,
		type: "GET",
		dataType: "json",
		data: false,
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
			$("#modalg-charge").modal("hide");
			$( "#modalg-error-text" ).empty();
			$('#modalg-error-text').html('<center>Se ha generado un error: <strong>-- getIdPerfilUserByName Modulo Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText);
			$("#modalg-error").modal("show");
		}
		
	});
	
	return result;
}

//Data Table
var handleDataTablePerfil = function() {
	
	$('#data-table2 tfoot th').each( function () {
        var title = $('#data-table2 thead th').eq( $(this).index() ).text();
        $(this).html( '<input type="text" placeholder="Buscar '+title+'" />' );
    } );
	
	var table2 = $("#data-table2").DataTable({
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
        ajax : '/Manager/RestPerfilUser/getListPerfilByCliente/0',
	    columns : [
	               	{ "data": null },
	                { "data": "perfil" },
	                { "data": "esAdmin" },
	                { "data": "estado" },
	                
	     ],
	     "initComplete": function( ) {
	    	// Apply the filter
    	    table2.columns().eq( 0 ).each( function ( colIdx ) {
    	        $( 'input', table2.column( colIdx ).footer() ).on( 'keyup change', function () {
    	            table2
    	                .column( colIdx )
    	                .search( this.value )
    	                .draw();
    	        } );
    	    } );
	     },
	     "createdRow": function ( row, data, index ) {
	         if(data["estado"] == "ACTIVADO"){
	        	  $('td', row).eq(3).addClass('highgreen');
	         }else{
	        	  $('td', row).eq(3).addClass('highred');
	         }
	     }
	});
   
	table2.on( 'order.dt search.dt', function () {
		table2.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();
   
};
//Data Table
var handleDataTableAccessPerfil = function() {
	$('#data-table3 tfoot th').each( function () {
        var title = $('#data-table3 thead th').eq( $(this).index() ).text();
        $(this).html( '<input type="text" placeholder="Buscar '+title+'" />' );
    } );
	
	var table3 = $("#data-table3").DataTable({
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
        responsive: true,
        autoFill: false,
        colReorder: false,
        keys: false,
        rowReorder: false,
        select: false,
        serverSide : false,
        processing : true,
        ajax : "/Manager/RestPerfilUser/getListAccessPerfilById/"+$('#txt2_00').val(), 
        columns : [
	               	{ "data": null },
	                { "data": "unidad_nombre" },
	                { "data": "modulo_nombre" },
	                { "data": "permiso_nombre" },
	                { "data": "butons" },
	                
	     ],
	     "initComplete": function( ) {
	    	// Apply the filter
    	    table3.columns().eq( 0 ).each( function ( colIdx ) {
    	        $( 'input', table3.column( colIdx ).footer() ).on( 'keyup change', function () {
    	            table3
    	                .column( colIdx )
    	                .search( this.value )
    	                .draw();
    	        } );
    	    } );
	     },
	     "createdRow": function ( row, data, index ) {
	         if(data["permiso_nombre"] == "Total"){
	        	  $('td', row).eq(3).addClass('highgreen');
	         }else if(data["permiso_nombre"] == "Escritura"){
	        	  $('td', row).eq(3).addClass('highblue');
	         }else if(data["permiso_nombre"] == "Lectura"){
	        	  $('td', row).eq(3).addClass('highyelow');
	         }else{
	        	  $('td', row).eq(3).addClass('highred');
	         }
	     }
	});
   
	table3.on( 'order.dt search.dt', function () {
		table3.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();
   
};

//Data Table
var handleDataTableAccessDivPerfil = function() {
	$('#data-table4 tfoot th').each( function () {
        var title = $('#data-table4 thead th').eq( $(this).index() ).text();
        $(this).html( '<input type="text" placeholder="Buscar '+title+'" />' );
    } );
	
	var table4 = $("#data-table4").DataTable({
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
        responsive: true,
        autoFill: false,
        colReorder: false,
        keys: false,
        rowReorder: false,
        select: false,
        serverSide : false,
        processing : true,
        ajax: {
            url: "/Manager/RestPerfilUser/getListAccessDivPerfilById/"+$('#txt2_00').val(),
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
	                { "data": "nombre_div" },
	                { "data": "nombre_sdiv" },
	                { "data": "permiso_nombre" },
	                { "data": "butons" },
	                
	     ],
	     "initComplete": function( ) {
	    	// Apply the filter
    	    table4.columns().eq( 0 ).each( function ( colIdx ) {
    	        $( 'input', table4.column( colIdx ).footer() ).on( 'keyup change', function () {
    	            table3
    	                .column( colIdx )
    	                .search( this.value )
    	                .draw();
    	        } );
    	    } );
	     },
	     "createdRow": function ( row, data, index ) {
	         if(data["permiso_nombre"] == "Total"){
	        	  $('td', row).eq(3).addClass('highgreen');
	         }else if(data["permiso_nombre"] == "Escritura"){
	        	  $('td', row).eq(3).addClass('highblue');
	         }else if(data["permiso_nombre"] == "Lectura"){
	        	  $('td', row).eq(3).addClass('highyelow');
	         }else{
	        	  $('td', row).eq(3).addClass('highred');
	         }
	     }
	});
   
	table4.on( 'order.dt search.dt', function () {
		table4.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();
   
};

//---------------------------------------
//Combo plugins


//---------------------------------------
//Tools User forms

var eraseFormPerfil = function(){
	
	$('#txt2_00').val('0');
	$('#txt2_01').val('');
	$('#txt2_02').val('0');
	$('#txt2_03').val('1');
	$('#txt2_04').val('');
	
	
}

//---------------------------------------
//Show Modal
var handleDetallePerfil = function(id) {
	//$("#modalg-charge").modal("show");
	$('#error-newperfil').hide();
	
	
	
	$('#perfil-permisos').show();
	
	eraseFormPerfil();
	
	//$('#txt2_01').prop('disabled', true);
	//$('#txt2_02').prop('disabled', true);
	
	//Style modal box
	$('#header-createperfil').hide();
	$('#button-createperfil').hide();
	
	$('#header-updateperfil').show();
	
	//data
	$('#txt2_00').val(id);
	//alert('Ingresa: '+ $('#txt_00').val());
	var data = detailPerfil(id);
	
	
	$('#txt2_01').val(data.nombre_perfil);
	$('#txt2_02').val(data.es_admin);
	$('#txt2_03').val(data.estado_perfil);
	$('#txt2_04').val(data.codigo_perfil);
	
	
	if($('#txth_01').val() == '1'){
		$('#button-updateperfil').show();
		$('#button-deleteperfil').show();
	}else if($('#txth_01').val() == '2'){
		$('#button-updateperfil').show();
		$('#button-deleteperfil').hide();
	}else if($('#txth_01').val() == '3'){
		$('#button-updateperfil').hide();
		$('#button-deleteperfil').hide();
	}
	var urlAccess = "/Manager/RestPerfilUser/getListAccessPerfilById/"+$('#txt2_00').val();
	var urlAccess2 = "/Manager/RestPerfilUser/getListAccessDivPerfilById/"+$('#txt2_00').val();
	$('#data-table3').DataTable().ajax.url(urlAccess).load();
	$('#data-table4').DataTable().ajax.url(urlAccess2).load();
	//$("#modalg-charge").modal("hide");
	$("#modal-ficha-perfil").modal("show");
};

var showModalCreatePerfil = function(id) {
	$('#error-newperfil').hide();
	
	$('#perfil-permisos').hide();
	
	eraseFormPerfil();
	//$('#txt2_01').prop('disabled', false);
	//$('#txt2_02').prop('disabled', false);
	
	
	$('#header-updateperfil').hide();
	$('#button-updateperfil').hide();
	$('#button-deleteperfil').hide();
	
	$('#header-createperfil').show();
	$('#button-createperfil').show();
	
	$("#modal-ficha-perfil").modal("show");
	
};
var showModalUploadUser = function(id) {
	$("#modal-upload").modal("show");
	
};


var showModalDeletePerfil = function(id, login){
	$("#modal-ficha-perfil").modal("hide");
	$('#aux2_del1').val('');
	$('#aux2_del2').val('');
	
	$('#aux2_del1').val($('#txt2_00').val());
	$('#aux2_del2').val($('#txt2_01').val());
	
	
	$("#modal-delete-alert2").modal("show");
	
}
//---------------------------------------
//Function module
var createPerfilUser = function(){
	$("#error-newperfil").hide();
	var param = {
			nombre : $('#txt2_01').val(),
			admin: $('#txt2_02').val(),
			estado : $('#txt2_03').val(),
			codigo : $('#txt2_04').val()
	}
	
	//debugArray(param);
	
	if(param.nombre == ''){
		$('#etext-newperfil').html('Ingresa Nombre de perfil!');
		$("#error-newperfil").show();
		$("#txt_01").focus();
	}else if(valExistPerfilUser(param.nombre) == 1){
		$('#etext-newperfil').html('Nombre Perfil Ingresado ya existe en el sistema!');
		$("#error-newperfil").show();
		$("#txt2_01").focus();
	}else{
		if(param.codigo == ''){param.codigo = 0;}
		
		$.ajax({
			url: "/Manager/RestPerfilUser/setPerfilLogin",
			type: "GET",
			dataType: "json",
			data: param,
			async: false,
			beforeSend: function(){
				//cargando los datos
				$("#modalg-charge").modal("show");
			},
			success: function(data){
				
				var id_perfil = getIdPerfilUserByName(param.nombre);
				
				$('#txt2_00').val(id_perfil);
				
				var urlAccess = "/Manager/RestPerfilUser/getListAccessPerfilById/"+id_perfil;
				$('#data-table3').DataTable().ajax.url(urlAccess).load();
				
				$("#perfil-permisos").modal("show");
				
				$('#header-createperfil').hide();
				$('#button-createperfil').hide();
				
				$('#header-updateperfil').show();
				
				if($('#txth_01').val() == '1'){
					$('#button-updateperfil').show();
					$('#button-deleteperfil').show();
				}else if($('#txth_01').val() == '2'){
					$('#button-updateperfil').show();
					$('#button-deleteperfil').hide();
				}else if($('#txth_01').val() == '3'){
					$('#button-updateperfil').hide();
					$('#button-deleteperfil').hide();
				}
				
				$("#modalg-charge").modal("hide");
				
				$("#modalg-success-text" ).empty();
				$('#modalg-success-text').html('<center>'+ data.text +'</center><br>');
				$("#modalg-success").modal("show");
				$('#data-table2').DataTable().ajax.reload();
				
			
			},
			error: function (xhr, ajaxOptions, thrownError) {
		       // alert(xhr.status);
		       // alert(thrownError);
		       //alert('Se ha generado un error -- setUserLogin Modulo Usuario -- ,  favor contactar al adminsitrador!');
				$("#modalg-charge").modal("hide");
				$( "#modalg-error-text" ).empty();
				$('#modalg-error-text').html('<center>Se ha generado un error: <strong>-- setPerfilLogin Modulo Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText);
				$("#modalg-error").modal("show");
			}
		});
	}
	
}
var updatePerfilUser = function(){
	$("#error-newperfil").hide();
	var param = {
			id: $('#txt2_00').val(),
			nombre : $('#txt2_01').val(),
			admin: $('#txt2_02').val(),
			estado : $('#txt2_03').val(),
			codigo : $('#txt2_04').val()
	}
	
	//debugArray(param);
	
	if(param.nombre == ''){
		$('#etext-newperfil').html('Ingresa Nombre de perfil!');
		$("#error-newperfil").show();
		$("#txt_01").focus();
	}else{
		if(param.codigo == ''){param.codigo = 0;}
		
		$.ajax({
			url: "/Manager/RestPerfilUser/updatePerfilLogin",
			type: "GET",
			dataType: "json",
			data: param,
			beforeSend: function(){
				//cargando los datos
				$("#modalg-charge").modal("show");
			},
			success: function(data){
				$("#modalg-charge").modal("hide");
				$( "#modalg-success-text" ).empty();
				$('#modalg-success-text').html('<center>'+ data.text +'</center><br>');
				$("#modalg-success").modal("show");
				
				$('#data-table2').DataTable().ajax.reload();
			
			},
			error: function (xhr, ajaxOptions, thrownError) {
		       // alert(xhr.status);
		       // alert(thrownError);
		       //alert('Se ha generado un error -- setUserLogin Modulo Usuario -- ,  favor contactar al adminsitrador!');
				$("#modalg-charge").modal("hide");
				$( "#modalg-error-text" ).empty();
				$('#modalg-error-text').html('<center>Se ha generado un error: <strong>-- updatePerfilLogin Modulo Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText);
				$("#modalg-error").modal("show");
			}
		});
	}
	
}
var deletePerfilUser = function(){
	$("#error-newperfil").hide();
	$("#modal-ficha-perfil").modal("hide");
	$("#modal-delete-alert2").modal("hide");
	
	var param = {
			id : $('#aux2_del1').val(),
			nombre: $('#aux2_del2').val()
	}
	
	    //debugArray(param);
		$.ajax({
			url: "/Manager/RestPerfilUser/deletePerfilLogin",
			type: "GET",
			dataType: "json",
			data: param,
			beforeSend: function(){
				//cargando los datos
				$("#modalg-charge").modal("show");
			},
			success: function(data){
				$("#modalg-charge").modal("hide");
				$( "#modalg-success-text" ).empty();
				$('#modalg-success-text').html('<center>'+ data.text +'</center><br>');
				$("#modalg-success").modal("show");
				
				eraseForm();
				
				$('#data-table2').DataTable().ajax.reload();
			
			},
			error: function (xhr, ajaxOptions, thrownError) {
		       // alert(xhr.status);
		       // alert(thrownError);
		       //alert('Se ha generado un error -- setUserLogin Modulo Usuario -- ,  favor contactar al adminsitrador!');
				$("#modalg-charge").modal("hide");
				$( "#modalg-error-text" ).empty();
				$('#modalg-error-text').html('<center>Se ha generado un error: <strong>-- deletePerfilLogin Modulo Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText);
				$("#modalg-error").modal("show");
			}
		});
	
	
}
//---------------------------------------
var perfil = function() {
	"use strict";
	return {
		init : function() {
			handleDataTablePerfil();
			handleDataTableAccessPerfil();
			handleDataTableAccessDivPerfil();
		}
	}
}();