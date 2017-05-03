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
	
   $("#data-table8").dataTable().fnDestroy(); 

   $('#data-table8 tfoot th').each( function () {
        var title = $('#data-table8 thead th').eq( $(this).index() ).text();
        $(this).html( '<input type="text" placeholder="Buscar '+title+'" />' );
    } );

    var desde="01-01-1900";
    var hasta="01-01-1900";
    var div="0";
    var suvD="0"; 
    
    if($('#req_fechas_desde').val()!=''){
    	 desde = $('#req_fechas_desde').val();
    }
    
     if($('#req_fechas_hasta').val()!=''){
    	 hasta = $('#req_fechas_hasta').val();
    }
    
     if(($('#req_fechas_hasta').val()!='') && ($('#req_fechas_desde').val()!='')){
    	 $("#asignaCrearModal").show();
    }

   
   
    div =   $('#txt_08').val();
    suvD =  $('#txt_088').val();
    
    $("#detalle_usuarios").show();
	$("#detalle_usuarios_estudio").show();
	

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
        ajax: {
            url: '/Manager/RestAsignaUsuario/getUsuariosTotalHoras/' + desde + '/' + hasta + '/' + div + '/' + suvD,
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
		        { "data": "usuario.user_completo" },
		        { "data": "usuario.str_perfil" },
		        { "data": "horas_ocupadas" },
		        { "data": "asigna" }
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
				    
				});
   
			table.on( 'order.dt search.dt', function () {
				table.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
		            cell.innerHTML = i+1;
		        } );
		    } ).draw();

};

var handleDataTableAsignacionUsuario1 = function() {
	$('#data-table9 tfoot th').each( function () {
        var title = $('#data-table thead th').eq( $(this).index() ).text();
        $(this).html( '<input type="text" placeholder="Buscar '+title+'" />' );
    } );
	
	var table = $("#data-table9").DataTable({
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
        ajax : "/Manager/RestAsignaUsuario/getAsignadosEstudio/" + $('#txt_idope_1').val(), 
	    columns : [
	               		{ "data": null },
				        { "data": "usuario.user_completo" },
				        { "data": "usuario.str_perfil" },
				        { "data": "horas_ocupadas" }
				        
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
	   
	});
   
	table.on( 'order.dt search.dt', function () {
		table.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();
   
	
	table.on('dblclick', 'tr', function () {
        var data = table.row( this ).data();
       
        $("#txt_usuario_modal_detalle").val(data.usuario["user_completo"]);
        $("#txt_cargo_modal_detalle").val(data.usuario["str_perfil"]);
        $("#hiden_usuario2").val(data.usuario["id_user"]);
        handleDataTableAsignacionUsuarioModal(data.usuario["id_user"]);
        $("#modal-detalle").modal("show");
    } );
	
   
};

var handleDataTableAsignacionUsuarioModal = function(user) {
	$("#data-table10").dataTable().fnDestroy(); 

	$('#data-table10 tfoot th').each( function () {
        var title = $('#data-table thead th').eq( $(this).index() ).text();
        $(this).html( '<input type="text" placeholder="Buscar '+title+'" />' );
    } );
	
	var table = $("#data-table10").DataTable({
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
        ajax : "/Manager/RestAsignaUsuario/getAsignadosEstudioDias/" + $('#txt_idope_1').val() + "/" + user, 
	    columns : [
	               		{ "data": null },
				        { "data": "horas_ocupadas" },
				        { "data": "fecha" }
				        
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
	   
	});
   
	table.on( 'order.dt search.dt', function () {
		table.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();
   
	
	table.on('dblclick', 'tr', function () {
        var data = table.row( this ).data();
       
        


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
    $("#req_fechas_desde").datepicker({
        todayHighlight: true,
        format: 'dd-mm-yyyy',
        language:'es'
    });
    $("#req_fechas_hasta").datepicker({
        todayHighlight: true,
        format: 'dd-mm-yyyy',
        language:'es'
    });
}

function chargeComboRangoFechas(combo1){
	
	var param ={
		desde : $("#req_fechas_desde").val(),
		hasta : $("#req_fechas_hasta").val()
	}
	
	$.ajax({
		url: "/Manager/RestAsignaUsuario/cargaComboRangoFechas",
		type: "GET",
		dataType: "json",
		data: param,
		beforeSend: function(){
			//cargando los datos
		},
		success: function(data){
			var combo = $('#'+combo1);
			combo.empty();
			// iteramos a través del arreglo de ciudades
			combo.append("<option value='0'>Seleccione</option>");
            $.each(data, function(index, val) {
            	combo.append("<option value='"+val.id+"'>" + val.text + "</option>");
            });
        },
		error: function(xhr, ajaxOptions, thrownError){
			//alert('Se ha generado un error - function chargeDivisionCombo , Tools - Combos,  favor contactar al adminsitrador!');
			$("#modalg-charge").modal("hide");
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error - function chargeComboRangoFechas ,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
	});
	
}


function showModalAsigna(id,horas) {
	



		var param ={
			dia : $("#txt_fechas").val(),
			user : $("#hiden_usuario").val(),
		}
		
		$.ajax({
			url: "/Manager/RestLoginUser/getDetailUserById/" + id,
			type: "GET",
			dataType: "json",
			data: param,
			beforeSend: function(){
				//cargando los datos
			},
			success: function(data){
                 				
			         $("#modal-create").modal("show");
			         $('#hiden_usuario').val(id);
			         $('#txt_usuario').val(data.user_completo);
			         chargeComboRangoFechas("txt_fechas")

			},
			error: function(xhr, ajaxOptions, thrownError){
				//alert('Se ha generado un error - function chargeDivisionCombo , Tools - Combos,  favor contactar al adminsitrador!');
				$("#modalg-charge").modal("hide");
				var data = {
						status: xhr.status,
						text: '<center>Se ha generado un error - function chargeHorasOcupadas ,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
				}
				errorAjaxRequest(data);
			}
		});







    ;
	 
	 

};



function chargeHorasOcupadas(){
	
		var param ={
			dia : $("#txt_fechas").val(),
			user : $("#hiden_usuario").val(),
		}
		
		$.ajax({
			url: "/Manager/RestAsignaUsuario/buscaHorasDipsUserDia",
			type: "GET",
			dataType: "json",
			data: param,
			beforeSend: function(){
				//cargando los datos
			},
			success: function(data){
                 				
			        $('#txt_h_ocupadas').val(data);
			        chargeHorasOcupadasEstudio();


			},
			error: function(xhr, ajaxOptions, thrownError){
				//alert('Se ha generado un error - function chargeDivisionCombo , Tools - Combos,  favor contactar al adminsitrador!');
				$("#modalg-charge").modal("hide");
				var data = {
						status: xhr.status,
						text: '<center>Se ha generado un error - function chargeHorasOcupadas ,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
				}
				errorAjaxRequest(data);
			}
		});

}


function chargeHorasOcupadasEstudio(){
	
		var param ={
			dia : $("#txt_fechas").val(),
			user : $("#hiden_usuario").val(),
		    estudio : $("#txt_idope_1").val(),
		}
		
		$.ajax({
			url: "/Manager/RestAsignaUsuario/buscaHorasDipsUserDiaEstudio",
			type: "GET",
			dataType: "json",
			data: param,
			beforeSend: function(){
				//cargando los datos
			},
			success: function(data){
                 				
			        $('#txt_h_ocupadas_estudio').val(data);
			
			        
			        if(data==0){

			        	$('#hiden_accion').val(2);
			        }else{
			        
			        	$('#hiden_accion').val(1);
			        }
			        	



			},
			error: function(xhr, ajaxOptions, thrownError){
				//alert('Se ha generado un error - function chargeDivisionCombo , Tools - Combos,  favor contactar al adminsitrador!');
				$("#modalg-charge").modal("hide");
				var data = {
						status: xhr.status,
						text: '<center>Se ha generado un error - function chargeHorasOcupadasEstudio ,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
				}
				errorAjaxRequest(data);
			}
		});

}


function setHorasEstudio(){
	
		var param ={
			horas : $("#txt_h_asignar").val(),
			dia : $("#txt_fechas").val(),
			estudio : $("#txt_idope_1").val(),
			user : $("#hiden_usuario").val(),
		    accion : $("#hiden_accion").val()
		}
		
		$.ajax({
			url: "/Manager/RestAsignaUsuario/AsignaHorasUserEstudio",
			type: "GET",
			dataType: "json",
			data: param,
			beforeSend: function(){
				//cargando los datos
			},
			success: function(){
				$('#data-table8').DataTable().ajax.reload();

			},
			error: function(xhr, ajaxOptions, thrownError){}
		});

}

function limpiaModal() {
	
	$('#txt_fechas').val("");
	$('#txt_h_ocupadas').val("");
	$('#txt_h_asignar').val("");
	$('#txt_h_ocupadas_estudio').val("");

};




function createAsignacion() {

	setHorasEstudio()
	limpiaModal()
	$("#modal-create").modal("hide");
	handleDataTableAsignacionUsuario()
	$('#data-table9').DataTable().ajax.reload();

};




function  showModalAsignaModal(){
	$("#modal-detalle").modal("hide");
	showModalAsigna($("#hiden_usuario2").val(),0);
}



function aceptarAsignacion (){

	     var param = {
	    	 id_oper : $('#txt_idope_1').val(),
	       
	     }
	

		$.ajax({
			url: "/Manager/RestAsignaUsuario/aceptarAsignacion",
			type: "GET",
			dataType: "json",
			data: param,
			
			success: function(data){
				
				$("#modalg-charge").modal("hide");
				$("#modalg-success-text-asignacion" ).empty();
				$('#modalg-success-text-asignacion').html('<center>'+ data.text +'</center><br>');
				$("#modalg-success_asignacion").modal("show");

			
			},
			error: function (xhr, ajaxOptions, thrownError) {
		       // alert(xhr.status);
		       // alert(thrownError);
		       //alert('Se ha generado un error -- setUserLogin Modulo Usuario -- ,  favor contactar al adminsitrador!');
				$("#modalg-charge").modal("hide");
				var data = {
						status: xhr.status,
						text: '<center>Se ha generado un error: <strong>--  aceptar Asignacion de Personal  --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
				}
				errorAjaxRequest(data);
			}
		});
		
		

}


//---------------------------------------
var Proyecto = function() {
	"use strict";
	return {
		init : function() {
			iniHideData();
			iniDateSpiker();
		    chargeDivisionCombo('txt_08');
			getDetailEstudio();
            handleDataTableAsignacionUsuario();
			handleDataTableAsignacionUsuario1();
			$("#detalle_usuarios").hide();
			//$("#detalle_usuarios_estudio").hide();
			$("#asignaCrearModal").hide();
			



			
		}
	}
}();