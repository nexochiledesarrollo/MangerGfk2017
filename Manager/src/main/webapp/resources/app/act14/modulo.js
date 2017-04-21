/*
Version: 1.0
Author: sbarraza
Website: Manager
Date: 20-09-2016 
 */
//-------------------------------------------------------------------------------

//Data Table
var handleDataTableProyecto = function() {
	

	$('#data-table1 tfoot th').each( function () {
        var title = $('#data-table thead th').eq( $(this).index() ).text();
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
        responsive: false,
        autoFill: false,
        colReorder: false,
        keys: false,
        rowReorder: false,
        select: false,
        serverSide : false,
        processing : true,
        "scrollY": "100%",
        "scrollCollapse": true,
        "scrollX": true,
        ajax: {
            url: "/Manager/RestWorkflow15/getListWorkflow16?id=0&lang="+$('#aux_01').val(),
            error : function(xhr, status, error) {
        		var data = {
						status: xhr.status,
						text: '<center>Se ha generado un error: <strong>-- List Actividad 12 Workflow --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+error +'<br/>Detail: '+xhr.responseText
				}
				errorAjaxRequest(data);
        	}
        },
	    columns : [
	               	{ "data": null},
	               	{ "data": "id_cotizacion" },
	                { "data": "codigo_proyectom" },
	                { "data": "nombre_operacion" },
	                { "data": "str_estado_medicion" },
	                { "data": "num_entrevistas_op" },
	                { "data": "str_industria_medicion" },
	               	{ "data": "fcreacion_proyectom" },
	                { "data": "str_res_us1_op" },
	                { "data": "str_res_us2_op" },
	                { "data": "str_res_us3_op" },
	                { "data": "str_cliente" },
	                { "data": "str_id_tipo_entrevista" }
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
        //alert( 'You clicked on '+data["id_operacion"]+'\'s row' );
        var nombre = encodeURIComponent(data["nombre_operacion"]);
        
        window.open("/Manager/workflowEtapa1/DetalleKickOff?id="+data["id_operacion"]+"&nombre="+nombre+"&tipo=2", "_self");
        
    } );
	
    setInterval( function () {
    	//alert('prueba');
    	table.ajax.reload( null, false ); // user paging is not reset on reload
	}, $('#aux_02').val() );//3000000 default 5 minutos
   
};




//---------------------------------------
var Proyecto = function() {
	"use strict";
	return {
		init : function() {
		    //alert("hola");
			handleDataTableProyecto();
		}
	}
}();