/*

Version: 1.0
Author: sbarraza
Website: Manager
Date: 20-09-2016 
 
 */

 //-------------------------------------------------------------------------------
var showModalUploadUser = function(id) {
	$("#modal-upload").modal("show");
	
};
//Data Table
var getDetailEstudio = function(){
	var param = {
			id : $('#txt_idope_1').val(),
			tipo : 2
	}
	
	$.ajax({
		url: "/Manager/RestCotizacion/getFullDetalleOperacion",
		type: "GET",
		dataType: "json",
		data: param,
		async: false,
		beforeSend: function(){
			//cargando los datos
			$("#modalg-charge").modal("show");
		},
		success: function(data){
			//***********************************
			//var detalle = data.detalle;
			//**********************************
		
			var es_admin = $('#conf_01').val();
			var url_service = $('#conf_02').val();
			
			
			//alert('Permiso: '+$('#conf_03').val());
			if($('#conf_03').val() == 3){$('#btn_activae').hide(); $('#btn_updatee').hide(); $('#btn_deletee').hide();}
			if($('#conf_03').val() == 2){$('#btn_deletee').hide();}
			//alert('admin:' + es_admin + ' -- url_service:'+url_service);
			
			if(es_admin == 1){
				$('#btn_mod_cal_02').hide();
			}else{
				$('#btn_mod_cal_01').hide();
				if(data.activa_operacion == 1){
					$('#btn_mod_cal_02').hide();
				}
			}
			
			
			//$('#txt_detalle_04').val(data.nombre_operacion);
			
			//$("#txt_detalle_08").select2();
			var detalle = data.detalle;
			
			$('#combo_1').val(data.industria_medicion);
			$('#combo_2').val(detalle.tipo_estudio);
			$('#combo_3').val(data.id_tipo_entrevista);
			//alert(data.muestra_manager);
			
			

			handleSelect2GetIndustria("txt_detalle_01",$('#combo_1').val(),"Industria" );
			handleSelect2GetGenericCombo("txt_detalle_02",$('#combo_2').val(),"Tipo Estudio",4,0,0,0);
			handleSelect2GetGenericCombo("txt_detalle_03",$('#combo_3').val(),"Tipo Entrevista",5,0,0,0);
			
			$('#txt_detalle_04').val(detalle.muestra_manager);
			
			handleSelect2GetUsuario('txt_detalle_05',0,detalle.res_us1_op,'SeleccGerente de Estudio' );
			
			//$('#txt_detalle_16').val(data.str_user_director_estudio_manager);
			handleSelect2GetUsuario('txt_detalle_06',0,detalle.res_us2_op,'Seleccione Director de Estudio' );
			
			//$('#txt_detalle_17').val(data.str_user_jefe_estudio_manager);
			handleSelect2GetUsuario('txt_detalle_07',0,detalle.res_us3_op,'Seleccione Jefe de Estudio' );
			
			$('#txt_detalle_08').val(data.str_cliente);
			$('#txt_detalle_18').val(data.str_estado_medicion);
			
			$('#txt_detalle_20').val(data.str_cola_operacion);
			
			$( "#h1_1" ).empty();
			$('#h1_1').addClass('text-warning');
			$('#h1_1').html(data.id_operacion + "-" + data.codigo_cotizacion + " :: "+ data.nombre_operacion);
			$('#li_estudio').html("Detalle de Estudio");
			$('#h1_estudio').html("Upload Cuestionario...");
			
			$('#id_op1').val(data.id_operacion + "-" + data.codigo_cotizacion);
			
			$("#modalg-charge").modal("hide");
        
		},
		error: function(xhr, ajaxOptions, thrownError){
			$("#modalg-charge").modal("hide");
			var data = {
					status: xhr.status,
					text: 'Se ha generado un error - function getDetailEstudio , MANTENEDOR PROYECTO,  favor contactar al adminsitrador! <br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
	});
	
}


//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Upload file

var iniHideData = function(){
	$("#info_op").hide();              
    
	$("#btn_showhide").click(function(){
          var nodo = $(this).attr("href");  
          //alert(nodo);
          if ($(nodo).is(":visible")){
               $(nodo).hide();
               return false;
          }else{
	        $("#info_op").hide("slow");                             
	        $(nodo).fadeToggle("fast");
	        return false;
          }
    });
}
var handleDataTableAsignacionUsuario = function() {
	$('#data-table8 tfoot th').each( function () {
        var title = $('#data-table thead th').eq( $(this).index() ).text();
        $(this).html( '<input type="text" placeholder="Buscar '+title+'" />' );
    } );
	
	var table = $("#data-table8").DataTable({
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
        //ajax : "/Manager/RestProyecto/getListProyectos", 
	    /*columns : [
	               	{ "data": null},
	               	{ "data": "codigo_proyectom" },
	                { "data": "cod_operacion" },
	                { "data": "cod_area" },
	                { "data": "cod_sam" },
	                { "data": "nombre_proyectop" },
	                { "data": "nombre_operacion" },
	               	{ "data": "str_estado_medicion" },
	                { "data": "priori_operacion" },
	                { "data": "fcrea_operacion" },
	                { "data": "factivacion_operacion" },
	                { "data": "nombre_area" },
	                { "data": "str_user_coordinador_manager" },
	                { "data": "str_user_director_estudio_manager" },
	                { "data": "str_user_jefe_estudio_manager" },
	                { "data": "nombre_cliente" },
	                { "data": "muestra_manager" },
	                { "data": "str_tipo_entrevista" }
	            ],*/
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
//	         if(data["otp"] == "ACTIVADO"){
//	        	  $('td', row).eq(9).addClass('highgreen');
//	         }else{
//	        	  $('td', row).eq(9).addClass('highred');
//	         }
//	         if(data["estado"] == "ACTIVO"){
//	        	  $('td', row).eq(10).addClass('highgreen');
//	         }else{
//	        	  $('td', row).eq(10).addClass('highred');
//	         }
	     }
	});
   
	table.on( 'order.dt search.dt', function () {
		table.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();
   
	
	table.on('dblclick', 'tr', function () {
        var data = table.row( this ).data();
        alert( 'You clicked on '+data["id_operacion"]+'\'s row' );
    } );
	
   
};
var handleDataTableAsignacionUsuario2 = function() {
	
   toastr.options = {
		"closeButton": true,
		"debug": false,
		"newestOnTop": false,
		"progressBar": true,
		"rtl": false,
		"positionClass": "toast-top-right",
		"preventDuplicates": false,
		"onclick": null,
		"showDuration": 300,
		"hideDuration": 1000,
		"timeOut": 5000,
		"extendedTimeOut": 1000,
		"showEasing": "swing",
		"hideEasing": "linear",
		"showMethod": "fadeIn",
		"hideMethod": "fadeOut"
	}
	
   	if($('#req_fechas_01').val() == ''){
   		toastr.error("Debes Seleccionar Fecha Desde! ");
   	}else if($('#req_fechas_02').val() == ''){
   		toastr.error("Debes Seleccionar Fecha Hasta! ");
   	}else{
	
		$('#charge_table9').load('/Manager/workflowEtapa1/ListDetalleAsignacionUsuario?div='+$('#txt_08').val()+'&sub='+$('#txt_088').val()+'&desde='+$('#req_fechas_01').val()+'&hasta='+$('#req_fechas_02').val()+'&id='+$('#txt_idope_1').val(),false ,function(responseText, textStatus, XMLHttpRequest){
			
			if (textStatus == "success") {
				
	            var table = $("#data-table9").DataTable({
					dom: 'C<"clear">lBfrtip',
					"language": {
			        	"url": "http://cdn.datatables.net/plug-ins/1.10.12/i18n/Spanish.json"
			        },
					"iDisplayLength": -1,
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
			        "scrollY": "100%",
			        "scrollCollapse": true,
			        "scrollX": true,
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
			//	         if(data["otp"] == "ACTIVADO"){
			//	        	  $('td', row).eq(9).addClass('highgreen');
			//	         }else{
			//	        	  $('td', row).eq(9).addClass('highred');
			//	         }
			//	         if(data["estado"] == "ACTIVO"){
			//	        	  $('td', row).eq(10).addClass('highgreen');
			//	         }else{
			//	        	  $('td', row).eq(10).addClass('highred');
			//	         }
				     }
				});
	            
	            
	            
			}
			if (textStatus == "error") {
				 //alert('Se genero un problema MANTENEDOR CLIENTE - CARGA LIST CLIENTE, favor volver a intentar!');
				 var data = {
						status: textStatus,
						text: '<center>Se ha generado un error: <strong>-- handleDataTableAsignacionUsuario2 Modulo Asignacion de Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+textStatus + '<br/> ERROR Detail: '+responseText
				}
				errorAjaxRequest(data);
			}	
	
		});	
	  }//fin revision


	
};

var iniDateSpiker = function(){
	$("#req_fechas_01").datepicker({
        todayHighlight: true,
        format: 'dd-mm-yyyy',
        language:'es'
    });
    $("#req_fechas_02").datepicker({
        todayHighlight: true,
        format: 'dd-mm-yyyy',
        language:'es'
    });

}

//---------------------------------------
var Proyecto = function() {
	"use strict";
	return {
		init : function() {
			iniHideData();
			iniDateSpiker();
			//initDatePicker();
			chargeDivisionCombo('txt_08');
			getDetailEstudio();
			//handleJqueryFileUpload();
			handleDataTableAsignacionUsuario();
			//handleDataTableAsignacionUsuario2();
			
		}
	}
}();