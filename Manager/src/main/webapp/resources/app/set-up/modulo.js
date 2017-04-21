/*
Version: 1.0
Author: sbarraza
Website: Manager
Date: 29-11-2016 
 */
//-------------------------------------------------------------------------------
//################################################################################
//Data Table
//tab1
//Data Table
var handleDataTable1 = function() {
	$('#data-table1 tfoot th').each( function () {
        var title = $('#data-table1 thead th').eq( $(this).index() ).text();
        $(this).html( '<input type="text" placeholder="Buscar '+title+'" />' );
    } );
	
	var table = $("#data-table1").DataTable({
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
        ajax : false, 
	    columns : [
	               	{ "data": null},
	               	{ "data": "des_detalle" },
	                { "data": "valor_detalle" },
	                { "data": "str_combo_depende" },
	                { "data": "depende_detalle" },
	                { "data": "orden_combo" },
	                {"render": function(data, type, row){
							if($('#per_01').val()== "1" || $('#per_01').val()== "2"){
								return  '<a class="btn btn-danger btn-icon btn-circle btn-lg" href="JavaScript: deleteOption1('+row["id_detalle"]+',&#39;'+row["des_detalle"]+'&#39;,&#39;'+$('#txt_01 option:selected').text()+'&#39;,'+row["combo_depende"]+');"><i class="fa fa-trash-o"></i></a>'
	                		}else{
	                			return null
	                		}
	                	}
					}
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

	     }
	});
   
	table.on( 'order.dt search.dt', function () {
		table.column(0, {search:'applied', order:'applied'}).nodes().each( function (cell, i) {
            cell.innerHTML = i+1;
        } );
    } ).draw();
   
	
	table.on('dblclick', 'tr', function () {
        var data = table.row( this ).data();
        //alert( 'You clicked on '+data["id_detalle"]+'\'s row' );
        $('#txts_00').val(data["id_detalle"]);
        $('#txts_01').val(data["des_detalle"])
        $('#txts_02').val(data["valor_detalle"]);
        $('#txts_03').val(data["combo_depende"]).trigger("change");
        $('#txts_04').val(data["depende_detalle"]);
        $('#txts_05').val(data["orden_combo"]);
        $('#modal-edit').modal('show');
   } );
	
   
};
//################################################################################
var deleteOption1 = function(id,detalle,namePadre,idPadre){
	
	alert(id+" -- "+detalle + " -- " +namePadre +" -- "+idPadre);

}

var createOption = function(){
	var data = $('#txt_01 option:selected').text();
	alert(data);
}

//################################################################################
//Combo Box Select 2 
var handleSelect2GetComboPadre = function(combo, place) {
	var result ="";
	//alert('Ejecuta: '+ combo + " -- place: "+ place);
	var param = {
					pace: place
				}
	
	$.ajax({
		url: "/Manager/RestSetUp/getComboPadre",
		type: "GET",
		dataType: "json",
		data: param,
		async: false,
		beforeSend: function(){
			//cargando los datos
		},
		success: function(data){
			 result = data;
        },
		error: function(xhr, ajaxOptions, thrownError){
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error - function handleSelect2GetComboPadre , Set-Up - Combos,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
	});
	
	
	
	$("#"+combo).select2({
        placeholder: {
        	id: -1,
        	text : place
        }, 
        data : result
    }).on('change', function (e) {
	   
		   var urlAccess = "/Manager/RestSetUp/getDetCombo?lang="+$('#txt_02').val()+"&id="+$('#txt_01').val();
			//alert(urlAccess);
		   $('#data-table1').DataTable().ajax.url(urlAccess).load();
	   
	});
	
	
}
//---------------------------------------
var valPermis = function(){
	var permiso = $('#per_01').val();
	if(permiso == 2 || permiso == 3){
		$('#btn_bcreate').hide();
	}
	if(permiso == 3){
		$('#btn_bcreate').hide();
		$('#button-bupdate').hide();
		$('#button-bdelete').hide();
	}
	
}
var initTab1 = function(){
	handleSelect2GetComboPadre("txt_01", $('#pace_01').val());
	handleSelect2GetGenericCombo("txt_02",$("#aux_01").val(),$("#pace_01").val(),41,0,0,0);
	handleDataTable1();
	$('#error-option').hide();
	handleSelect2GetComboPadre("txts_03", $('#pace_01').val());
}
//---------------------------------------
var SetUp = function() {
	"use strict";
	return {
		init : function() {
			valPermis();
			initTab1();
		}
	}
}();