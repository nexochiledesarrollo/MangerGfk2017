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
var handleDataTableAsignacionHoras = function() {
	$('#data-table5 tfoot th').each( function () {
        var title = $('#data-table5 thead th').eq( $(this).index() ).text();
        $(this).html( '<input type="text" placeholder="Buscar '+title+'" />' );
    } );
	
	var table = $("#data-table5").DataTable({
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
        autoFill: true,
        colReorder: true,
        keys: true,
        rowReorder: false,
        select: false,
        serverSide : false,
        processing : true,
       
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
var handleDataTableAsignacionItems = function() {
	$('#data-table9 tfoot th').each( function () {
        var title = $('#data-table9 thead th').eq( $(this).index() ).text();
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
        responsive: true,
        autoFill: true,
        colReorder: true,
        keys: true,
        rowReorder: false,
        select: false,
        serverSide : false,
        processing : true,
       
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
var handleDataTableCostosFijosProyecto = function() {
	$('#data-table2 tfoot th').each( function () {
        var title = $('#data-table thead th').eq( $(this).index() ).text();
        $(this).html( '<input type="text" placeholder="Buscar '+title+'" />' );
    } );
	
	var table = $("#data-table2").DataTable({
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

var handleDataTableCostosExtProyecto = function() {
	$('#data-table3 tfoot th').each( function () {
        var title = $('#data-table thead th').eq( $(this).index() ).text();
        $(this).html( '<input type="text" placeholder="Buscar '+title+'" />' );
    } );
	
	var table = $("#data-table3").DataTable({
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

var handleDataTableServIntProyecto = function() {
	$('#data-table4 tfoot th').each( function () {
        var title = $('#data-table thead th').eq( $(this).index() ).text();
        $(this).html( '<input type="text" placeholder="Buscar '+title+'" />' );
    } );
	
	var table = $("#data-table4").DataTable({
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

var handleDataTablePuestosCotizadosProyecto = function() {
	$('#data-table5 tfoot th').each( function () {
        var title = $('#data-table thead th').eq( $(this).index() ).text();
        $(this).html( '<input type="text" placeholder="Buscar '+title+'" />' );
    } );
	
	var table = $("#data-table5").DataTable({
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

var handleDataTableTrazaProyecto = function() {
	$('#data-table6 tfoot th').each( function () {
        var title = $('#data-table6 thead th').eq( $(this).index() ).text();
        $(this).html( '<input type="text" placeholder="Buscar '+title+'" />' );
    } );
	
	var table = $("#data-table6").DataTable({
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
        ajax : false, 
	    columns : [
	               	{ "data": null},
	               	{ "data": "fecha_traza" },
	                { "data": "str_usuario" },
	                { "data": "nombre_modulo" },
	                { "data": "nombre_traza" },
	                { "data": "detalle_traza" }
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
//        var data = table.row( this ).data();
//        alert( 'You clicked on '+data["id_operacion"]+'\'s row' );
    } );
	
   
};
var handleDataTableEncuestaProyecto = function() {
	$('#data-table7 tfoot th').each( function () {
        var title = $('#data-table thead th').eq( $(this).index() ).text();
        $(this).html( '<input type="text" placeholder="Buscar '+title+'" />' );
    } );
	
	var table = $("#data-table7").DataTable({
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
var getDetailEstudio = function(){
	var param = {
			id : $('#txt_idope_1').val(),
			tipo : $('#txt_tipo_2').val()
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
			var detalle = data.detalle;
			//**********************************
		
			var es_admin = $('#conf_01').val();
			var url_service = $('#conf_02').val();
			
			if(detalle.res_us1_op == $('#conf_04').val() || detalle.res_us2_op == $('#conf_04').val() || detalle.res_us3_op == $('#conf_04').val() || $('#conf_05').val() == 1 || $('#conf_05').val() == 3 ){
				//No hace nada ya que el boton por default esta activo
			}else{
				$('#btn_activae').hide();
			}
			if(data.activa_operacion == 1){
				$('#btn_activae').hide();
			}else{
				$('#btn_updatee').hide();
			}
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
			
			$( "#h1_1" ).empty();
			//alert("param.tipo: "+param.tipo);
			if(param.tipo == 1){
				$('#h1_1').addClass('text-success');
				$('#h1_1').html('ID Cotizaci&oacute;n: '+ data.id_cotizacion + "-" + data.codigo_cotizacion);
				$('#li_estudio').html("Detalle de Cotizacion");
				$('#h1_estudio').html("Gesti&oacute;n Detalle de Cotizaci&oacute;n...");
			
			}else if(param.tipo == 2){
				$('#h1_1').addClass('text-warning');
				$('#h1_1').html('ID Estudio: '+ data.id_proyectom + "-" + data.cod_operacion);
				$('#li_estudio').html("Detalle de Estudio");
				$('#h1_estudio').html("Gesti&oacute;n Detalle de Estudio...");
			
			}
				
			$('#txt_detalle_01').val(data.cod_sam);
			$('#txt_detalle_02').val(data.cod_manager);
			
			//$('#txt_detalle_03').val(data.nombre_proyectop);
			$('#txt_detalle_04').val(data.nombre_operacion);
			
			//chargeAreaCombo('txt_detalle_05',data.area_medicion);
			
			handleSelect2GetIndustria("txt_detalle_05",data.industria_medicion,"Seleccione Industria" );
			
			handleSelect2GetGenericCombo("txt_detalle_06",detalle.tipo_estudio,"Tipo de Estudio",4,0,0,0);
			
			
			
			
			
			//$('#txt_detalle_05').val(data.area_medicion);
			
			//$('#txt_detalle_06').val(data.str_tipo_estudio);
	//		chargeTipoEstudioCombo('txt_detalle_06',data.id_tipo_estudio);
			
			//$('#txt_detalle_07').val(data.str_tipo_entrevista);
			//chargeTipoEntrevistaCombo('txt_detalle_07',data.area_medicion,data.id_tipo_entrevista);
			handleSelect2GetGenericCombo("txt_detalle_07",data.id_tipo_entrevista,"Tipo de Entrevista",5,0,0,0);
			
			//$("#txt_detalle_08").select2();
			$('#txt_detalle_08').val(detalle.num_entrevistas_op);
			
			$('#txt_detalle_09').val(data.str_cliente);
			$('#txt_detalle_10').val(detalle.str_clie_facturar);
			
			var currency_param = {
					region : 'es-CL'
			}
			
			$('#txt_detalle_11').attr('readonly', true);
			$('#txt_detalle_11').val(detalle.precio_venta);
			$('#txt_detalle_11').formatCurrency(currency_param);
			
			$('#txt_detalle_12').attr('readonly', true);
			$('#txt_detalle_12').val();
			$('#txt_detalle_12').formatCurrency(currency_param);
			
			
			//$('#txt_detalle_13').val(data.weekly_semana);
			//$('#txt_detalle_14').val(data.weekly_ano);
			
			//$('#txt_detalle_15').val(data.str_user_coordinador_manager);
			handleSelect2GetUsuario('txt_detalle_15',0,detalle.res_us1_op,'SeleccGerente de Estudio' );
			
			//$('#txt_detalle_16').val(data.str_user_director_estudio_manager);
			handleSelect2GetUsuario('txt_detalle_16',0,detalle.res_us2_op,'Seleccione Director de Estudio' );
			
			//$('#txt_detalle_17').val(data.str_user_jefe_estudio_manager);
			handleSelect2GetUsuario('txt_detalle_17',0,detalle.res_us3_op,'Seleccione Jefe de Estudio' );
			
			$('#txt_detalle_18').val(data.str_estado_medicion);
			
			$('#txt_detalle_20').val(data.str_cola_operacion);
			
			//Set Costos cobos proyecto
			
//			handleSelect2GetTipoCosto('txt_cfijo_01',1,3,'Seleccione Costo Directo');
//			handleSelect2GetTipoCosto('txt_cfijo_02',1,4,'Seleccione Costo Externo');
//			handleSelect2GetTipoCosto('txt_cfijo_03',1,2,'Seleccione Costo de Servicio Interno');
			
			
			//Configuracion Show or Hide segun Set UP -----------------------------------------------------------
			// Configuracion y adicionales
			
			//$('.BSswitch').bootstrapSwitch();
			$('#txt_detalle_21').on('switchChange.bootstrapSwitch', function(event, state) {
				//alert(state);
				if(state == true){$('#tab1_03').show();}else{$('#tab1_03').hide();}
			});
			$('#txt_detalle_22').on('switchChange.bootstrapSwitch', function(event, state) {
				//alert(state);
				if(state == true){$('#tab1_04').show();}else{$('#tab1_04').hide();}
			});
			$('#txt_detalle_23').on('switchChange.bootstrapSwitch', function(event, state) {
				//alert(state);
				if(state == true){$('#tab1_05').show();}else{$('#tab1_05').hide();}
			});
			$('#txt_detalle_24').on('switchChange.bootstrapSwitch', function(event, state) {
				//alert(state);
				if(state == true){$('#tab1_06').show();}else{$('#tab1_06').hide();}
			});
			$('#txt_detalle_25').on('switchChange.bootstrapSwitch', function(event, state) {
				//alert(state);
				if(state == true){$('#tab1_07').show();}else{$('#tab1_07').hide();}
			});
			$('#txt_detalle_26').on('switchChange.bootstrapSwitch', function(event, state) {
				//alert(state);
				if(state == true){$('#tab1_08').show();}else{$('#tab1_08').hide();}
			});
			$('#txt_detalle_29').on('switchChange.bootstrapSwitch', function(event, state) {
				//alert(state);
				if(state == true){$('#tab1_09').show();}else{$('#tab1_09').hide();}
			});
			$('#txt_detalle_27').on('switchChange.bootstrapSwitch', function(event, state) {
				//alert(state);
				if(state == true){$('#tab1_10').show();}else{$('#tab1_10').hide();}
			});
			$('#txt_detalle_31').on('switchChange.bootstrapSwitch', function(event, state) {
				//alert(state);
				if(state == true){$('#tab1_11').show();}else{$('#tab1_11').hide();}
			});
			
			
			//COnfiguracion de Recoleccion
				$('#gtxt_41').hide(); // Frecuencia Ola
				
				$('#gtxt_43').hide(); // Cantidad de Olas
				
				$('#txt_42').hide(); // Otro -- Frecuencia Ola
				
			//Muestro u Oculto datos de recoleccion CATI
			if(data.id_tipo_entrevista == 2 || data.id_tipo_entrevista == 16 ){
				$('#cati_recol').show();
			}else{
				$('#cati_recol').hide();
			}
			 //alert(detalle.set_up_1);
			 if(detalle.set_up_1 == 'false'){
				 $('#tab1_03').hide();
			 }else{
				 $("#txt_detalle_21").bootstrapSwitch('state', detalle.set_up_1);
			 }
			 if(detalle.set_up_2 == 'false'){
				 $('#tab1_04').hide();
			 }else{
				 $("#txt_detalle_22").bootstrapSwitch('state', detalle.set_up_2); 
			 }
			 if(detalle.set_up_3 == 'false'){
				 $('#tab1_05').hide();
			 }else{
				 $("#txt_detalle_23").bootstrapSwitch('state', detalle.set_up_3); 
			 }
			 if(detalle.set_up_4 == 'false'){
				 $('#tab1_06').hide();
			 }else{
				 $("#txt_detalle_24").bootstrapSwitch('state', detalle.set_up_4);
			 }
			 if(detalle.set_up_5 == 'false'){
				 $('#tab1_07').hide();
			 }else{
				 $("#txt_detalle_25").bootstrapSwitch('state', detalle.set_up_5);
			 }
			 if(detalle.set_up_6 == 'false'){
				 $('#tab1_08').hide();
			 }else{
				 $("#txt_detalle_26").bootstrapSwitch('state', detalle.set_up_6);
			 }
			 if(detalle.set_up_7 == 'false'){
				 $('#tab1_09').hide();
			 }else{
				 $("#txt_detalle_27").bootstrapSwitch('state', detalle.set_up_7);
			 }
			 if(detalle.set_up_8 == 'false'){
				 $('#tab1_10').hide();
			 }else{
				 $("#txt_detalle_28").bootstrapSwitch('state', detalle.set_up_8);
			 }
			 if(detalle.set_up_9 == 'false'){
				 $('#tab1_11').hide();
			 }else{
				 $("#txt_detalle_29").bootstrapSwitch('state', detalle.set_up_9);
			 }
			 if(detalle.set_up_10 == 'true'){
				 $("#txt_detalle_30").bootstrapSwitch('state', detalle.set_up_10);
			 }
			 if(detalle.set_up_11 == 'true'){
				 $("#txt_detalle_31").bootstrapSwitch('state', detalle.set_up_11);
			 }
			//END Configuracion Show or Hide segun Set UP -----------------------------------------------------------
			
			$('#txt_detalle_32').val(detalle.cot_por_inc); 
			$('#txt_detalle_33').val(detalle.por_rebate); 
			
			$("#txt_detalle_34").bootstrapSwitch('state', detalle.crea_libro_codigo);
			$("#txt_detalle_35").bootstrapSwitch('state', detalle.verbatim_capture);
			$("#txt_detalle_36").bootstrapSwitch('state', detalle.pre_proceso);
			
			
			//Pestaña Datos Generales
			$('#txt_01').val(data.nombre_operacion);
			$('#txt_02').val(data.sector_medicion).trigger("change");
			$('#txt_03').val(data.industria_medicion).trigger("change");
			
			//array producto
			var prods = data.productos;
			var aux = [];
			for (i=0;i<prods.length;i++){ 
				//$("#txt_04").select2('val',prods[i]["id_producto"]);
				//alert(prods[i]["id_producto"]);
				aux.push(prods[i]["id_producto"]);
			}
			$("#txt_04").val(aux).trigger("change");
			
//			$("#").val().trigger("change");
//			$("#txt_05").val(data.id_cliente).trigger("change");
//			$("#txt_06").val(detalle.id_clie_facturar).trigger("change");
			//$("#txt_07").val(detalle.id_clie_facturar).trigger("change");
//			$("#txt_08").val(data.id_cotizacion).trigger("change");
			$("#txt_09").val(detalle.id_geografia).trigger("change");
			//alert(detalle.cant_paises);
			var aux_cant_paises = 1;
			if(detalle.cant_paises > 0){
				aux_cant_paises = detalle.cant_paises;
			}
			$("#txt_10").val(aux_cant_paises);
			$("#txt_11").val(detalle.tipo_estudio).trigger("change");
//			$("#txt_12").val(data.id_tipo_entrevista).trigger("change");
			$("#txt_13").val(detalle.booking_legal_entity).trigger("change");
			//$("#txt_14").val(detalle.booking_legal_entity).trigger("change");
			$("#txt_15").val(detalle.centro_costo_op);
			$("#txt_16").val(detalle.por_ejec_estudio);
			$("#txt_17").val(detalle.digital_op).trigger("change");
			$("#txt_18").val(detalle.moneda_op).trigger("change");
			
			$("#txt_19").val(detalle.date_prob_in_est_op);
			$("#txt_20").val(detalle.date_prob_entre_est_op);
			
			$("#txt_21").val(detalle.ddate_pres_of_equipo_op);
			$("#txt_22").val(detalle.ddate_pres_gps_op);
			$("#txt_23").val(detalle.ddate_pres_clie_op);
			
//			$("#txt_24").val(detalle.res_us1_op).trigger("change");
//			$("#txt_25").val(detalle.res_us2_op).trigger("change");
//			$("#txt_26").val(detalle.res_us3_op).trigger("change");
			
			$("#txt_27").val(detalle.obj_obs_op);
			$("#txt_28").val(detalle.desc_op);
			//recoleccion
			var recol = data.recoleccion;
			
			


			//scripting
			$("#txt_99").bootstrapSwitch('state', detalle.set_up_2);
			
			//codificacion
			var codi = data.codificacion;
			$("#txt_100").bootstrapSwitch('state', detalle.set_up_3);
			$("#txt_101").bootstrapSwitch('state', codi.pre_codigo_codi);
			$("#txt_102").val(codi.preg_ab_codi);
			$("#txt_103").val(codi.preg_otro_codi);
			$("#txt_104").bootstrapSwitch('state', codi.cod_ab_ent_clie_codi);
			$("#txt_105").bootstrapSwitch('state', codi.traducir_codi);
			$("#txt_106").val(codi.lang_codi).trigger("change");
			$("#txt_107").bootstrapSwitch('state', codi.editar_codi);
			$("#txt_108").val(codi.nivel_edi_codi);
			$("#txt_109").val(codi.otra_info_codi);
			$("#txt_detalle_36").bootstrapSwitch('state', detalle.pre_codigo_codi);
			
			$("#txt_detalle_37").val(codi.preg_ab_codi);
			$("#txt_detalle_38").val(codi.preg_otro_codi);
			$("#txt_detalle_39").val(codi.tmo_codi);
			$("#txt_detalle_34").bootstrapSwitch('state', detalle.crea_libro_codigo);
			
			//digitacion
			var digi = data.digitacion;
			$("#txt_detalle_35").bootstrapSwitch('state', digi.verbatim_capture);
			$("#txt_110").bootstrapSwitch('state', detalle.set_up_4);
			$("#txt_111").bootstrapSwitch('state', digi.verbatim_capture);
			
			//depuracion
			$("#txt_112").bootstrapSwitch('state', detalle.set_up_5);
			
			// Tabulacion
			var tab = data.tabulacion;
			if(detalle.set_up_6 == "true"){$("#txt_113").bootstrapSwitch('state', detalle.set_up_6);}
			$("#txt_114").val(tab.esp_datos_entr_tab).trigger("change");
			$("#txt_115").val(tab.format_file_tab).trigger("change");
			if(tab.tabla_prem_tab == "true"){$("#txt_116").bootstrapSwitch('state', tab.tabla_prem_tab);}
			if(tab.cuadro_ten_tab == "true"){$("#txt_117").bootstrapSwitch('state', tab.cuadro_ten_tab);}
			//$("#txt_118").val(tab.tend_tabla_perio_tab).trigger("change");
			$("#txt_118").val(tab.tend_tabla_perio_tab);
			$("#txt_119").val(tab.num_est_banner_tab);
			if(tab.anexar_datotos_tab == "true"){$("#txt_120").bootstrapSwitch('state', tab.anexar_datotos_tab);}
			if(tab.prueba_stat_tab == "true"){$("#txt_121").bootstrapSwitch('state', tab.prueba_stat_tab);}
			if(tab.pondera_tab == "true"){$("#txt_122").bootstrapSwitch('state', tab.pondera_tab);}
			$("#txt_123").val(tab.frec_entrega_datos_tab).trigger("change");
			$("#txt_124").val(tab.frec_tabla_datos_tab).trigger("change");
			$("#txt_125").val(tab.esp_req_tab);
			$("#txt_126").val(tab.otra_info_tab);
			$("#txt_127").bootstrapSwitch('state', tab.test_stat_tab);
			$("#txt_128").bootstrapSwitch('state', tab.ponderacion_tab);
			$("#txt_129").val(tab.ferc_entrega_file_datos_tab).trigger("change");
			$("#txt_130").val(tab.frec_tabla_entrega_tab).trigger("change");
			//Analisis
			var ana = data.analisis;
			if(ana.dis_muestra_ana == "true"){$("#txt_131").bootstrapSwitch('state', ana.dis_muestra_ana);}
			if(ana.tom_mue_mez_pan_ana == "true"){$("#txt_132").bootstrapSwitch('state', ana.tom_mue_mez_pan_ana);}
			$("#txt_133").val(ana.desp_lin_fij_cel_rdd_nec_ana);
			$("#txt_134").val(ana.tel_fuente_eje_ana);
			if(ana.pond_nec_mark_muest_est_ana == "true"){$("#txt_135").bootstrapSwitch('state', ana.pond_nec_mark_muest_est_ana);}
			$("#txt_136").val(ana.compl_pond_ana).trigger("change");
			$("#txt_137").val(ana.pond_nec_ana);
			if(ana.pondera_cal_nec_est_ana == "true"){$("#txt_138").bootstrapSwitch('state', ana.pondera_cal_nec_est_ana);}
			if(ana.uni_nec_ana == "true"){$("#txt_139").bootstrapSwitch('state', ana.uni_nec_ana);}
			if(ana.punto_ref_nec_ana == "true"){$("#txt_140").bootstrapSwitch('state', ana.punto_ref_nec_ana);}
			$("#txt_141").val(ana.wave_frec_ana).trigger("change");
			if(ana.met_enc_ana == "true"){$("#txt_142").bootstrapSwitch('state', ana.met_enc_ana);}
			
			//Entrega
			//alert(detalle.entre_1 + "--"+detalle.entre_2 + "--"+detalle.entre_3 + "--"+detalle.entre_4 + "--"+detalle.entre_5 + "--"+detalle.entre_6 + "--");
			if(detalle.entre_1 == "true"){ $("#txt_143").bootstrapSwitch('state', detalle.entre_1); }
			if(detalle.entre_2 == "true"){ $("#txt_144").bootstrapSwitch('state', detalle.entre_2); }
			if(detalle.entre_3 == "true"){ $("#txt_145").bootstrapSwitch('state', detalle.entre_3); }
			if(detalle.entre_4 == "true"){ $("#txt_146").bootstrapSwitch('state', detalle.entre_4); }
			if(detalle.entre_5 == "true"){ $("#txt_147").bootstrapSwitch('state', detalle.entre_5); }
			if(detalle.entre_6 == "true"){ $("#txt_148").bootstrapSwitch('state', detalle.entre_6); }
			//BC
			var bc = data.bbcc;
			$("#txt_169").val(bc.b_case_1_ceps_sam).trigger("change");
			$("#txt_170").val(bc.b_case_1_text_ceps_sam);
			$("#txt_171").val(bc.b_case_2_ceps_sam).trigger("change");
			$("#txt_172").val(bc.b_case_2_text_ceps_sam);
			$("#txt_173").val(bc.b_case_3_ceps_sam).trigger("change");
			$("#txt_174").val(bc.b_case_4_ceps_sam).trigger("change");
			$("#txt_175").val(bc.b_case_4_text_ceps_sam);
			$("#txt_176").val(bc.b_case_5_ceps_sam).trigger("change");
			$("#txt_177").val(bc.b_case_5_text_ceps_sam);
			$("#txt_178").val(bc.b_case_6_text_ceps_sam);
			$("#txt_179").val(bc.b_case_7_ceps_sam).trigger("change");
			$("#txt_180").val(bc.b_case_7_text_ceps_sam);
			$("#txt_181").val(bc.b_case_8_ceps_sam).trigger("change");
			$("#txt_182").val(bc.b_case_9_ceps_sam).trigger("change");
			$("#txt_183").val(bc.b_case_9_text_ceps_sam);
			$("#txt_184").val(bc.b_case_10_text_ceps_sam);
			$("#txt_185").val(bc.b_case_11_text_ceps_sam);
			$("#txt_186").val(bc.b_case_12_ceps_sam).trigger("change");
			$("#txt_187").val(bc.b_case_13_ceps_sam).trigger("change");
			$("#txt_188").val(bc.b_case_13_text_ceps_sam);
			$("#txt_189").val(bc.b_case_14_text_ceps_sam);
			
			//Cuestionario
			var cuest = data.cuestionario;
			$("#txt_149").val(cuest.num_cuest_unic_cuest);
			$("#txt_150").val(cuest.por_cuest_sup_cuest);
			$("#txt_151").val(cuest.por_cuest_camb_cuest);
			if(cuest.des_camb_cuest == "true"){ $("#txt_152").bootstrapSwitch('state', cuest.des_camb_cuest); }
			$("#txt_154").val(cuest.num_resp_ab_cuest);
			$("#txt_155").val(cuest.por_resp_open_end_cuest);
			$("#txt_156").val(cuest.esp_num_otra_cuest);
			$("#txt_157").val(cuest.esp_por_otro_men_cuest);
			if(cuest.cust_trad_req_cuest == "true"){ $("#txt_158").bootstrapSwitch('state', cuest.cust_trad_req_cuest); }
			$("#txt_159").val(cuest.otra_info_esp_cuest);
			$("#txt_160").val(cuest.num_total_preg_prog_cuest);
			$("#txt_161").val(cuest.esp_req_exp_cuest);
			$("#txt_162").val(cuest.otro_cati_cuest);
			$("#txt_163").val(cuest.num_tot_preg_prog_cati_cuest);
			if(cuest.video_cuest == "true"){ $("#txt_164").bootstrapSwitch('state', cuest.video_cuest); }
			if(cuest.file_cuest == "true"){ $("#txt_165").bootstrapSwitch('state', cuest.file_cuest); }
			if(cuest.audio_cuest == "true"){ $("#txt_166").bootstrapSwitch('state', cuest.audio_cuest); }
			$("#txt_167").val(cuest.esp_req_exp2_cuest);
			$("#txt_168").val(cuest.otro_cawi_capi_cuest);
			
			//Cotizacion 
			//$("#txt_195").val(cuest.otro_cawi_capi_cuest);
			$('#txt_195').attr('readonly', true);
			$('#txt_195').val(detalle.precio_venta);
			//$('#txt_195').formatCurrency(currency_param);
			$('#txt_190').attr('readonly', true);
			$('#txt_190').val(detalle.num_entrevistas_op);
			$('#txt_192').val(detalle.total_entr_reco);
			
			$('#txt_193').attr('readonly', true);
			$('#txt_193').val(detalle.cot_por_inc);
			
			$('#txt_194').attr('readonly', true);
			$('#txt_194').val(detalle.por_rebate);
			
			//$('#txt_196').attr('readonly', true);
			//$('#txt_196').val($('#txt_196 option:selected').text());

			//Fechas WorkFlow
			if(detalle.fingreso_puesta_marcha_operacion != null){$('#work_fechas_01').val(detalle.fingreso_puesta_marcha_operacion);}else{$('#work_fechas_01').val("Pendiente");}
			if(detalle.fsalida_puesta_marcha_operacion != null){$('#work_fechas_02').val(detalle.fsalida_puesta_marcha_operacion);}else{$('#work_fechas_02').val("Pendiente");}
			
			if(detalle.fingreso_implementacion_operacion != null){$('#work_fechas_03').val(detalle.fingreso_implementacion_operacion);}else{$('#work_fechas_03').val("Pendiente");}
			if(detalle.fsalida_implementacion_operacion != null){$('#work_fechas_04').val(detalle.fsalida_implementacion_operacion);}else{$('#work_fechas_04').val("Pendiente");}
			if(detalle.fingreso_recoleccion_operacion != null){$('#work_fechas_05').val(detalle.fingreso_recoleccion_operacion);}else{$('#work_fechas_05').val("Pendiente");}
			if(detalle.fsalida_recoleccion_operacion != null){$('#work_fechas_06').val(detalle.fsalida_recoleccion_operacion);}else{$('#work_fechas_06').val("Pendiente");}
			if(detalle.fingreso_codificacion_operacion != null){$('#work_fechas_07').val(detalle.fingreso_codificacion_operacion);}else{$('#work_fechas_07').val("Pendiente");}
			if(detalle.fsalida_codificacion_operacion != null){$('#work_fechas_08').val(detalle.fsalida_codificacion_operacion);}else{$('#work_fechas_08').val("Pendiente");}
			if(detalle.fingreso_digitacion_operacion != null){$('#work_fechas_09').val(detalle.fingreso_digitacion_operacion);}else{$('#work_fechas_09').val("Pendiente");}
			if(detalle.fsalida_digitacion_operacion != null){$('#work_fechas_10').val(detalle.fsalida_digitacion_operacion);}else{$('#work_fechas_10').val("Pendiente");}
			if(detalle.fingreso_depuracion_operacion != null){$('#work_fechas_11').val(detalle.fingreso_depuracion_operacion);}else{$('#work_fechas_11').val("Pendiente");}
			if(detalle.fsalida_depuracion_operacion != null){$('#work_fechas_12').val(detalle.fsalida_depuracion_operacion);}else{$('#work_fechas_12').val("Pendiente");}
			if(detalle.fingreso_tabulacion_operacion != null){$('#work_fechas_13').val(detalle.fingreso_tabulacion_operacion);}else{$('#work_fechas_13').val("Pendiente");}
			if(detalle.fsalida_tabulacion_operacion != null){$('#work_fechas_14').val(detalle.fsalida_tabulacion_operacion);}else{$('#work_fechas_14').val("Pendiente");}
			
			if(detalle.fingreso_entrega_operacion != null){$('#work_fechas_15').val(detalle.fingreso_entrega_operacion);}else{$('#work_fechas_15').val("Pendiente");}
			if(detalle.fsalida_entrega_operacion != null){$('#work_fechas_16').val(detalle.fsalida_entrega_operacion);}else{$('#work_fechas_16').val("Pendiente");}
			//Costos Proyecto
			
			handleDataTableCostosFijosProyecto();
			
			//handleDataTableCostosExtProyecto();
			
			//handleDataTableServIntProyecto();
			
			//handleDataTablePuestosCotizadosProyecto();
			if(data.id_tipo_entrevista == 1 || data.id_tipo_entrevista == 6 ){
				handleDataTableEncuestaProyecto();
			}else{
				$('#row_encuestas').hide();
			}
				
			
			handleDataTableAsignacionUsuario();
			
			//Resumen Proyecto
			
			$("#rs_costo_proyecto" ).empty();
			$('#rs_costo_proyecto').html(detalle.precio_venta);
			$('#rs_costo_proyecto').formatCurrency(currency_param);
			//Repositorio
			
			handleDataTableProyecto();
			
			//Traza
			handleDataTableTrazaProyecto();
			
			
			//Date Workflow
			
			//Puesta en marcha
			//$('#work_fechas_01').val(data.fingreso_puesta_marcha_operacion);
			
			
			
			//Colapse panel
			
			//$('#panel_8').collapse('hide');
			
			
			// fechas comprometidas
			
			if(es_admin == 1){
				
				$('#btn_mod_cal_01').show();
				
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
				
				$("#req_fechas_03").datepicker({
			        todayHighlight: true,
			        format: 'dd-mm-yyyy',
			        language:'es'
			    });
				
				$("#req_fechas_04").datepicker({
			        todayHighlight: true,
			        format: 'dd-mm-yyyy',
			        language:'es'
			    });
				
				$("#req_fechas_05").datepicker({
			        todayHighlight: true,
			        format: 'dd-mm-yyyy',
			        language:'es'
			    });
			
			}else{
				$('#btn_mod_cal_01').hide();
			}
			
			
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
////toastr Options
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

var activeEstudio = function(){
	
	
	var pdr_aux = $('#txt_04').val().toString();
	var aux_frecuencia_cant_olas = 0;
	if($('#txt_43').val() != ''){
		aux_frecuencia_cant_olas = $('#txt_43').val();
	}
	
	var aux_dur_total_entrevista = 0;
	if($('#txt_59').val() != ''){
		aux_dur_total_entrevista = $('#txt_59').val();
	}
	var aux_numero_preg = 0;
	if($('#txt_73').val()!= 0){
		aux_numero_preg = $('#txt_73').val();
	}
	
	var aux_porespc_cati = 0;
	if($('#txt_91').val()!= 0){
		aux_porespc_cati = $('#txt_91').val();
	}
	
	var aux_totalnumfile_cati = 0;
	if($('#txt_94').val()!= 0){
		aux_totalnumfile_cati = $('#txt_94').val();
	}
	var aux_totalnumrec_cati = 0;
	if($('#txt_95').val()!= 0){
		aux_totalnumrec_cati = $('#txt_95').val();
	}
	var aux_poruseable_cati = 0;
	if($('#txt_96').val()!= 0){
		aux_poruseable_cati = $('#txt_96').val();
	}
	var aux_tend_tabla_tab = 0;
	if($('#txt_118').val()!= 0){
		aux_tend_tabla_tab = $('#txt_118').val();
	}
	var aux_num_banner_tab = 0;
	if($('#txt_119').val()!= 0){
		aux_num_banner_tab = $('#txt_119').val();
	}
	
	
	var aux_cuest_01 = 0;
	if($('#txt_149').val()!= 0){
		aux_cuest_01 = $('#txt_149').val();
	}
	var aux_cuest_02 = 0;
	if($('#txt_150').val()!= 0){
		aux_cuest_02 = $('#txt_150').val();
	}
	var aux_cuest_03 = 0;
	if($('#txt_151').val()!= 0){
		aux_cuest_03 = $('#txt_151').val();
	}
	var aux_cuest_05 = 0;
	if($('#txt_154').val()!= 0){
		aux_cuest_05 = $('#txt_154').val();
	}
	var aux_cuest_06 = 0;
	if($('#txt_155').val()!= 0){
		aux_cuest_06 = $('#txt_155').val();
	}
	var aux_cuest_11 = 0;
	if($('#txt_160').val()!= 0){
		aux_cuest_11 = $('#txt_160').val();
	}
	var aux_cuest_14 = 0;
	if($('#txt_163').val()!= 0){
		aux_cuest_14 = $('#txt_163').val();
	}
	//alert($('#txt_77').val());
	var param = {
	     id : $('#txt_idope_1').val(),
		 crm : $('#txt_detalle_01').val(),
	     sap : $('#txt_detalle_02').val(),
	     nombre : $('#txt_detalle_04').val(),
	     industria : $('#txt_detalle_05').val(),
	     tipo_estudio : $('#txt_detalle_06').val(),
	     tipo_entrevista : $('#txt_detalle_07').val(),
	     muestra : $('#txt_detalle_08').val(),
		 res01: $('#txt_detalle_15').val(),
	     res02: $('#txt_detalle_16').val(),
	     res03: $('#txt_detalle_17').val(),
		 set_up_1 : $('#txt_detalle_21').bootstrapSwitch('state').toString(),
	     set_up_2 : $('#txt_detalle_22').bootstrapSwitch('state').toString(),
	     set_up_3 : $('#txt_detalle_23').bootstrapSwitch('state').toString(),
	     set_up_4 : $('#txt_detalle_24').bootstrapSwitch('state').toString(),
	     set_up_5 : $('#txt_detalle_25').bootstrapSwitch('state').toString(),
	     set_up_6 : $('#txt_detalle_26').bootstrapSwitch('state').toString(),
	     set_up_7 : $('#txt_detalle_27').bootstrapSwitch('state').toString(),
	     set_up_8 : $('#txt_detalle_28').bootstrapSwitch('state').toString(),
	     set_up_9 : $('#txt_detalle_29').bootstrapSwitch('state').toString(),
	     set_up_10 : $('#txt_detalle_30').bootstrapSwitch('state').toString(),
	     set_up_11 : $('#txt_detalle_31').bootstrapSwitch('state').toString(),
	     por_incidencia: $('#txt_detalle_32').val(),
	     por_irebate: $('#txt_detalle_33').val(),
	     libro_codigo : $('#txt_detalle_34').bootstrapSwitch('state').toString(),
	     verbatim : $('#txt_detalle_35').bootstrapSwitch('state').toString(),
	     pre_proceso : $('#txt_detalle_36').bootstrapSwitch('state').toString(),
	     abierta_codi: $('#txt_detalle_37').val(),
	     otra_codi: $('#txt_detalle_38').val(),
	     tmo: $('#txt_detalle_39').val(),
		 sector_medicion : $('#txt_02').val(),
	     id_geografia : $('#txt_09').val(),
	     cant_paises : $('#txt_10').val(),
		 producto : pdr_aux,
	     booking_legal_entity : $('#txt_13').val(),
	     centro_costo_op : $('#txt_15').val(),
	     por_ejec_estudio : $('#txt_16').val(),
	     digital_op : $('#txt_17').val(),
	     moneda_op : $('#txt_18').val(),
	     date_prob_in_est_op : $('#txt_19').val(),
	     date_prob_entre_est_op : $('#txt_20').val(),
	     ddate_pres_of_equipo_op : $('#txt_21').val(),
	     ddate_pres_gps_op : $('#txt_22').val(),
	     ddate_pres_clie_op : $('#txt_23').val(),
	     obj_obs_op : $('#txt_27').val(),
	     desc_op : $('#txt_28').val(),
	     
	
	     lenguaje : $('#txt_39').val(),
	     frecuencia : $('#txt_40').val(),
	     frecuencia_ola : $('#txt_41').val(),
	     frecuencia_otro : $('#txt_42').val(),
	     frecuencia_cant_olas : aux_frecuencia_cant_olas,
	     modo : $('#txt_44').val(),
	     sub_modo : $('#txt_45').val(),
		 req_prueba_producto : $('#txt_48').bootstrapSwitch('state').toString(),
		 req_compra_producto : $('#txt_49').bootstrapSwitch('state').toString(),
		 tipo_test : $('#txt_50').val(),
		 req_retorno_producto : $('#txt_51').bootstrapSwitch('state').toString(),
		 descrip_producto : $('#txt_52').val(),
		 req_reclutamiento : $('#txt_53').bootstrapSwitch('state').toString(),
		 modo_reclutamiento : $('#txt_54').val(),
		 sub_cuota : $('#txt_55').bootstrapSwitch('state').toString(),
		 desc_reclutamiento : $('#txt_56').val(),
		 dur_intro_entrevista : $('#txt_57').val(),
		 dur_cuest_entrevista : $('#txt_58').val(),
		 dur_total_entrevista : aux_dur_total_entrevista,
		 
		 tipo_entrevistado : $('#txt_60').val(),
		 des_entrevistado : $('#txt_61').val(),
		 ident_cliente_entrevistado : $('#txt_62').bootstrapSwitch('state').toString(),
		 como_entrevistado : $('#txt_63').val(),
		 req_piloto : $('#txt_64').bootstrapSwitch('state').toString(),
		 desc_piloto : $('#txt_65').val(),
		 ej_diseno : $('#txt_66').val(),
		 esp_diseno : $('#txt_67').val(),
		 metodo_random : $('#txt_68').val(),
		 random_route : $('#txt_69').val(),
		 adress_route : $('#txt_70').val(),
		 adm_route : $('#txt_71').val(),
		 metodo_seleccion : $('#txt_72').val(),
		 numero_preg : aux_numero_preg,
		 obj_respuesta : $('#txt_74').val(),
		 inf_probalis : $('#txt_75').val(),
	    
		 req_materiales : $('#txt_76').bootstrapSwitch('state').toString(),
		 esp_materiales : $('#txt_77').val(),
				 
		 req_sesioninfo : $('#txt_78').bootstrapSwitch('state').toString(),
		 req_insentivo_sesioninfo : $('#txt_79').bootstrapSwitch('state').toString(),
		 respon_sesioninfo : $('#txt_80').val(),
		 desc_sesioninfo : $('#txt_81').val(),
		 
		rrd_cati: $('#txt_82').bootstrapSwitch('state').toString(),
		rrdcel_cati: $('#txt_83').bootstrapSwitch('state').toString(),
		target_cati: $('#txt_84').bootstrapSwitch('state').toString(),
		clientfone_cati: $('#txt_85').bootstrapSwitch('state').toString(),
		otros_cati: $('#txt_86').bootstrapSwitch('state').toString(),
		defej_cati : $('#txt_87').val(),
		descdef_cati : $('#txt_88').val(),
		descdefotro_cati : $('#txt_89').val(),
		defobj_cati : $('#txt_90').val(),
		porespc_cati : aux_porespc_cati,
		numcel_cati: $('#txt_92').bootstrapSwitch('state').toString(),
		deldupli_cati: $('#txt_93').bootstrapSwitch('state').toString(),
		totalnumfile_cati : aux_totalnumfile_cati,
		totalnumrec_cati: aux_totalnumrec_cati,
		poruseable_cati: aux_poruseable_cati,
		freqfile_cati: $('#txt_97').val(),
		freqfilecual_cati: $('#txt_98').val(),
		
		precod_cod : $('#txt_101').bootstrapSwitch('state').toString(),
		pregabierta_cod: $('#txt_102').val(),
		pregotro_cod: $('#txt_103').val(),
		codabierto_cod : $('#txt_104').bootstrapSwitch('state').toString(),
		tradcod_cod : $('#txt_105').bootstrapSwitch('state').toString(),
		esptrad_cod: $('#txt_106').val(),
		editcod_cod : $('#txt_107').bootstrapSwitch('state').toString(),
		nivedicion_cod: $('#txt_108').val(),
		otrainfo_cod: $('#txt_109').val(),
		
		verbatim_cod : $('#txt_111').bootstrapSwitch('state').toString(),
		
		 esp_datos_tab: $('#txt_114').val(),
		 formato_entreg_tab : $('#txt_115').val(),
		 tablapre_tab : $('#txt_116').bootstrapSwitch('state').toString(),
		 cuadroten_tab : $('#txt_117').bootstrapSwitch('state').toString(),
		 tend_tabla_tab : aux_tend_tabla_tab,
		 num_banner_tab : aux_num_banner_tab,
		 anexar_datos_tab : $('#txt_120').bootstrapSwitch('state').toString(),
		 
		 esp_req_tab : $('#txt_125').val(),
		 otra_info_tab : $('#txt_126').val(),
		 
		prueba_stat_tab : $('#txt_127').bootstrapSwitch('state').toString(),
		ponder_tab : $('#txt_128').bootstrapSwitch('state').toString(),
		freq_archivo_tab : $('#txt_129').val(),
		freq_tabla_tab : $('#txt_130').val(),
		
		msience_ana: $('#txt_131').bootstrapSwitch('state').toString(),
		tomamuestra_ana: $('#txt_132').bootstrapSwitch('state').toString(),	
		desp_linea_ana : $('#txt_133').val(),
		fono_fuente_ana : $('#txt_134').val(),
		pond_necms_ana: $('#txt_135').bootstrapSwitch('state').toString(),
		pond_complej_ana : $('#txt_136').val(),
		pond_calib_ana : $('#txt_137').val(),
		pond_calibel_ana: $('#txt_138').bootstrapSwitch('state').toString(),
		univ_datos_ana: $('#txt_139').bootstrapSwitch('state').toString(),
		puntos_ref_ana: $('#txt_140').bootstrapSwitch('state').toString(),
		wave_frec_ana : $('#txt_141').val(),
		met_enc_ana: $('#txt_142').bootstrapSwitch('state').toString(),
		
		entreg_1_ana: $('#txt_143').bootstrapSwitch('state').toString(),
		entreg_2_ana: $('#txt_144').bootstrapSwitch('state').toString(),
		entreg_3_ana: $('#txt_145').bootstrapSwitch('state').toString(),
		entreg_4_ana: $('#txt_146').bootstrapSwitch('state').toString(),
		entreg_5_ana: $('#txt_147').bootstrapSwitch('state').toString(),
		entreg_6_ana: $('#txt_148').bootstrapSwitch('state').toString(),
		
		bc_01: $('#txt_169').val(),
		bc_02: $('#txt_170').val(),
		bc_03: $('#txt_171').val(),
		bc_04: $('#txt_172').val(),
		bc_05: $('#txt_173').val(),
		bc_06: $('#txt_174').val(),
		bc_07: $('#txt_175').val(),
		bc_08: $('#txt_176').val(),
		bc_09: $('#txt_177').val(),
		bc_10: $('#txt_178').val(),
		bc_11: $('#txt_179').val(),
		bc_12: $('#txt_180').val(),
		bc_13: $('#txt_181').val(),
		bc_14: $('#txt_182').val(),
		bc_15: $('#txt_183').val(),
		bc_16: $('#txt_184').val(),
		bc_17: $('#txt_185').val(),
		bc_18: $('#txt_186').val(),
		bc_19: $('#txt_187').val(),
		bc_20: $('#txt_188').val(),
		bc_21: $('#txt_189').val(),
		
		cuest_01 : aux_cuest_01,
		cuest_02 : aux_cuest_02,
		cuest_03 : aux_cuest_03,
		cuest_04: $('#txt_152').bootstrapSwitch('state').toString(),
		cuest_05 : aux_cuest_05,
		cuest_06 : aux_cuest_06,
		cuest_07 : $('#txt_156').val(),
		cuest_08 : $('#txt_157').val(),
		cuest_09: $('#txt_158').bootstrapSwitch('state').toString(),
		cuest_10 : $('#txt_159').val(),
		cuest_11 : aux_cuest_11,
		cuest_12 : $('#txt_161').val(),
		cuest_13 : $('#txt_162').val(),
		cuest_14 : aux_cuest_14,
		cuest_15 : $('#txt_164').bootstrapSwitch('state').toString(),
		cuest_16 : $('#txt_165').bootstrapSwitch('state').toString(),
		cuest_17 : $('#txt_166').bootstrapSwitch('state').toString(),
		cuest_18 : $('#txt_167').val(),
		cuest_19 : $('#txt_168').val()
	}
	//alert(param.crm + "::"+param.sap );
	$.ajax({
		url: "/Manager/RestWorkflow15/updateActiveWorkflow15",
		type: "GET",
		dataType: "json",
		data: param,
		async: false,
		beforeSend: function(){
			//cargando los datos
			$( "#modalg-val-text" ).empty();
			$('#modalg-val-text').html('<center>Se esta activando estudio: <strong>-- '+param.nombre+' --</strong> <br/>  Favor espere... </center>');
			$("#modalg-val").modal("show");
		},
		success: function(data){
			//alert(data.result);
			$( "#modalg-val-text" ).empty();
			$("#modalg-val").modal("hide");
			
			$("#btn_activae").hide();
			toastr.success("Estudio Activado!"); 
		},
		error: function (xhr, ajaxOptions, thrownError) {
	       
			$("#modalg-charge").modal("hide");
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error: <strong>-- activeEstudio Modulo Estudio --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
		
	});
	
}
var updateEstudio = function(){
	toastr.success("Estudio Actualizado!");
}
var deleteEstudio = function(){
	toastr.error("Estudio Eliminado!");
}
var printEstudio = function(){
	//toastr.info("Estudio Print!");
	window.print();
}
var getListTrazaByIdOp = function(){
	
	var urlAccess = "/Manager/RestTraza/getListTrazaByIdOp?id="+$('#txt_idope_1').val();
	//alert(urlAccess);
	$('#data-table6').DataTable().ajax.url(urlAccess).load();
	$('#row_traza').offset().top;
	//$(window).scrollTop($('#row_traza').offset().top);
}
var initSelect2_1 = function(){
	//Wizzard page 1
	//$(".default-select2").select2();
	handleSelect2GetSector('txt_02',1,$('#combo_01').val());
	handleSelect2GetIndustria("txt_03",0,$('#combo_02').val() );
	handleSelect2GetProducto("txt_04",0,$('#combo_03').val() );
	handleSelect2GetCliente("txt_05",0,0,$('#combo_04').val(),1);
	handleSelect2GetCliente("txt_06",0,0,$('#combo_05').val(),0);
	handleSelect2GetGenericCombo("txt_07",0,$('#combo_06').val(),1,0,0,0);
	handleSelect2GetCotizacion("txt_08",0,$('#combo_07').val(),0,0 );
	handleSelect2GetGenericCombo("txt_09",0,$('#combo_08').val(),3,0,0,1);
	handleSelect2GetGenericCombo("txt_11",0,$('#combo_11').val(),4,0,0,0);
	handleSelect2GetGenericCombo("txt_12",0,$('#combo_12').val(),5,0,0,5);
	handleSelect2GetGenericCombo("txt_13",0,$('#combo_13').val(),6,0,0,2);
	handleSelect2GetGenericCombo("txt_17",0,$('#combo_17').val(),8,0,0,0);
	handleSelect2GetGenericCombo("txt_18",0,$('#combo_18').val(),9,0,0,0);
	handleSelect2GetUsuario("txt_24",0,0,$('#combo_24').val()) ;
	handleSelect2GetUsuario("txt_25",0,0,$('#combo_25').val()) ;
	handleSelect2GetUsuario("txt_26",0,0,$('#combo_26').val()) ;
	
	//readOnlySecondtTape();// Al inicioar desactiva recoleccion
}

var sum2Var = function(aux1 , aux2){
	var result = 0;
	var aux_1 = $('#'+aux1).val();
	var aux_2 = $('#'+aux2).val();
	
	if(aux_1 == ""){aux_1 = 0;}
	if(aux_2 == ""){aux_2 = 0;}
	
	result = parseInt(aux_1) + parseInt(aux_2);
	
	return result;
}
var initSelect2_2 = function(){
	handleSelect2GetGenericCombo("txt_39",0,$('#combo_39').val(),10,0,0,0);
	handleSelect2GetGenericCombo("txt_40",0,$('#combo_40').val(),11,0,0,40);
	handleSelect2GetGenericCombo("txt_41",0,$('#combo_41').val(),12,0,0,41);
	handleSelect2GetGenericCombo("txt_44",0,$('#combo_44').val(),13,0,0,3);
	handleSelect2GetGenericCombo("txt_45",0,$('#combo_45').val(),14,0,0,4);
	handleSelect2GetGenericCombo("txt_50",0,$('#combo_50').val(),15,0,0,0);
	handleSelect2GetGenericCombo("txt_54",0,$('#combo_54').val(),16,0,0,0);
	handleSelect2GetGenericCombo("txt_60",0,$('#combo_60').val(),17,0,0,0);
	handleSelect2GetGenericCombo("txt_63",0,$('#combo_63').val(),18,0,0,0);
	handleSelect2GetGenericCombo("txt_66",0,$('#combo_66').val(),19,0,0,0);
	handleSelect2GetGenericCombo("txt_68",0,$('#combo_68').val(),20,0,0,0);
	handleSelect2GetGenericCombo("txt_69",0,$('#combo_69').val(),21,0,0,0);
	handleSelect2GetGenericCombo("txt_70",0,$('#combo_70').val(),22,0,0,0);
	handleSelect2GetGenericCombo("txt_71",0,$('#combo_71').val(),23,0,0,0);
	handleSelect2GetGenericCombo("txt_72",0,$('#combo_72').val(),24,0,0,0);
	handleSelect2GetUsuario("txt_80",0,0,$('#combo_80').val()) ;
	handleSelect2GetGenericCombo("txt_87",0,$('#combo_87').val(),26,0,0,0);
	handleSelect2GetGenericCombo("txt_97",0,$('#combo_97').val(),27,0,0,0);
   $('#txt_57').val(0);
   $('#txt_58').val(0);
   $('#txt_57').on('input',function(e){
	   $('#txt_59').val(sum2Var('txt_57','txt_58')); 
	   $('#txt_192').val(sum2Var('txt_57','txt_58'));
   });
   $('#txt_58').on('input',function(e){
	   $('#txt_59').val(sum2Var('txt_57','txt_58')); 
	   $('#txt_192').val(sum2Var('txt_57','txt_58'));
   });
}
var initSelect2_3 = function(){
	handleSelect2GetGenericCombo("txt_106",0,$('#combo_106').val(),28,0,0,0);
	handleSelect2GetGenericCombo("txt_114",0,$('#combo_114').val(),29,0,0,0);
	handleSelect2GetGenericCombo("txt_115",0,$('#combo_115').val(),30,0,0,0);
	handleSelect2GetGenericCombo("txt_123",0,$('#combo_123').val(),31,0,0,0);
	handleSelect2GetGenericCombo("txt_124",0,$('#combo_124').val(),32,0,0,0);
	handleSelect2GetGenericCombo("txt_129",0,$('#combo_129').val(),33,0,0,0);
	handleSelect2GetGenericCombo("txt_130",0,$('#combo_130').val(),34,0,0,0);
	handleSelect2GetGenericCombo("txt_136",0,$('#combo_136').val(),35,0,0,0);
	handleSelect2GetGenericCombo("txt_141",0,$('#combo_141').val(),36,0,0,0);
}
var initSelect2_5 = function(){
	handleSelect2GetGenericCombo("txt_169",0,$('#combo_169').val(),37,0,0,0);
	handleSelect2GetGenericCombo("txt_171",0,$('#combo_169').val(),37,0,0,0);
	handleSelect2GetGenericCombo("txt_173",0,$('#combo_169').val(),37,0,0,0);
	handleSelect2GetGenericCombo("txt_174",0,$('#combo_169').val(),37,0,0,0);
	handleSelect2GetGenericCombo("txt_176",0,$('#combo_176').val(),38,0,0,0);
	handleSelect2GetGenericCombo("txt_179",0,$('#combo_169').val(),37,0,0,0);
	handleSelect2GetGenericCombo("txt_181",0,$('#combo_169').val(),37,0,0,0);
	handleSelect2GetGenericCombo("txt_182",0,$('#combo_182').val(),39,0,0,0);
	handleSelect2GetGenericCombo("txt_186",0,$('#combo_169').val(),37,0,0,0);
	handleSelect2GetGenericCombo("txt_187",0,$('#combo_187').val(),40,0,0,0);
	
}
var initSelect2_6 = function(){
	
	handleDataTableAsignacionHoras();
	handleDataTableAsignacionItems();
}
var intPreSetConfigForm = function(){
	//Cantidad de Paises 257
	$('#group_01').hide();
	$('#txt_02').val($('#aux_07').val()).trigger("change");
	$('#txt_09').val("1").trigger("change");;
	$('#txt_13').val($('#aux_02').val()).trigger("change");
	$('#txt_14').val(getValueComboDetalle(6,$('#txt_13').val(), 1, $('#lang_01').val()).text);
	$('#txt_18').val($('#aux_05').val()).trigger("change");
	$('#error-new').hide();
}
var initDatePicker = function(){
	$(".calendar").datepicker({
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
			initDatePicker();
			initSelect2_1();
			initSelect2_2();
			initSelect2_3();
			initSelect2_5();
			initSelect2_6();
			$('.BSswitch').bootstrapSwitch();
			
			getDetailEstudio();
			
			
		}
	}
}();