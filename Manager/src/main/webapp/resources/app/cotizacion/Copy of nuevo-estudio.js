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
        var title = $('#data-table thead th').eq( $(this).index() ).text();
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
			id : $('#txt_detalle_1').val()
	}
	
	$.ajax({
		url: "/Manager/RestProyecto/getDetailProyectoById",
		type: "GET",
		dataType: "json",
		data: param,
		async: false,
		beforeSend: function(){
			//cargando los datos
			$("#modalg-charge").modal("show");
		},
		success: function(data){
			
			var es_admin = $('#conf_01').val();
			var url_service = $('#conf_02').val();
			
			//alert('admin:' + es_admin + ' -- url_service:'+url_service);
			
			
			$( "#h1_1" ).empty();
			$('#h1_1').html(data.codigo_proyectom + "-" + data.cod_operacion + "-" + data.cod_area);
			
			$('#txt_detalle_01').val(data.cod_sam);
			$('#txt_detalle_02').val(data.cod_manager);
			
			$('#txt_detalle_03').val(data.nombre_proyectop);
			$('#txt_detalle_04').val(data.nombre_operacion);
			
			chargeAreaCombo('txt_detalle_05',data.area_medicion);
			
			
			//$('#txt_detalle_05').val(data.area_medicion);
			
			//$('#txt_detalle_06').val(data.str_tipo_estudio);
			chargeTipoEstudioCombo('txt_detalle_06',data.id_tipo_estudio);
			
			//$('#txt_detalle_07').val(data.str_tipo_entrevista);
			chargeTipoEntrevistaCombo('txt_detalle_07',data.area_medicion,data.id_tipo_entrevista);
			
			//$("#txt_detalle_08").select2();
			$('#txt_detalle_08').val(data.muestra_manager);
			
			$('#txt_detalle_09').val(data.nombre_cliente);
			$('#txt_detalle_10').val(data.nombre_cliente_factura);
			
			var currency_param = {
					region : 'es-CL'
			}
			
			$('#txt_detalle_11').attr('readonly', true);
			$('#txt_detalle_11').val(data.precio_venta_manager);
			$('#txt_detalle_11').formatCurrency(currency_param);
			
			$('#txt_detalle_12').attr('readonly', true);
			$('#txt_detalle_12').val(data.precio_costo_manager);
			$('#txt_detalle_12').formatCurrency(currency_param);
			
			
			//$('#txt_detalle_13').val(data.weekly_semana);
			//$('#txt_detalle_14').val(data.weekly_ano);
			
			//$('#txt_detalle_15').val(data.str_user_coordinador_manager);
			handleSelect2GetUsuario('txt_detalle_15',0,data.id_user_coordinador_manager,'Seleccione Coordinador' );
			
			//$('#txt_detalle_16').val(data.str_user_director_estudio_manager);
			handleSelect2GetUsuario('txt_detalle_16',0,data.id_user_director_estudio_manager,'Seleccione Director de Estudio' );
			
			//$('#txt_detalle_17').val(data.str_user_jefe_estudio_manager);
			handleSelect2GetUsuario('txt_detalle_17',0,data.id_user_jefe_estudio_manager,'Seleccione Jefe de Estudio' );
			
			$('#txt_detalle_18').val(data.str_estado_medicion);
			
			//Set Costos cobos proyecto
			
			handleSelect2GetTipoCosto('txt_cfijo_01',1,3,'Seleccione Costo Directo');
			handleSelect2GetTipoCosto('txt_cfijo_02',1,4,'Seleccione Costo Externo');
			handleSelect2GetTipoCosto('txt_cfijo_03',1,2,'Seleccione Costo de Servicio Interno');
			
			
			
			// Configuracion y adicionales
			
			$('.BSswitch').bootstrapSwitch();
			
			onOffSwitch('req_switch_01',data.revision_pd_manager);
			onOffSwitch('req_switch_02',data.supervision_pd_manager);
			onOffSwitch('req_switch_03',data.codificacion_pd_manager);
			onOffSwitch('req_switch_04',data.digitacion_pd_manager);
			onOffSwitch('req_switch_05',data.procesamiento_pd_manager);
			
			
			$('#req_switch_06_aux_01').val(data.porcentaje_supervicion_ra_manager); 
			$('#req_switch_07_aux_01').val(data.porcentaje_re_digitacion_ra_manager); 
			
			onOffSwitch('req_switch_08',data.procesamiento_pd_manager);
			onOffSwitch('req_switch_09',data.procesamiento_pd_manager);
			onOffSwitch('req_switch_10',data.procesamiento_pd_manager);
			
			
			//Otros Datos
			$('#req_switch_11_aux_01').val(data.abiertas_codificar_od_manager);
			$('#req_switch_12_aux_01').val(data.otros_codificar_od_manager);
			$('#req_switch_13_aux_01').val(data.tmo_od_manager);
			
			//Plazos Comprometidos
			
			$('#req_fechas_01').val(data.fingreso_campo_proyecto);
			$('#req_fechas_02').val(data.fentrada_base_proyecto);
			$('#req_fechas_03').val(data.fentrada_presentacion);
			
			$('#req_fechas_04').val(data.fsalida_campo_proyecto);
			$('#req_fechas_05').val(data.fsalida_base_proyecto);
			
			
			//Costos Proyecto
			
			handleDataTableCostosFijosProyecto();
			
			//handleDataTableCostosExtProyecto();
			
			//handleDataTableServIntProyecto();
			
			//handleDataTablePuestosCotizadosProyecto();
			
			handleDataTableEncuestaProyecto();
			
			handleDataTableAsignacionUsuario();
			
			//Resumen Proyecto
			
			$( "#rs_costo_proyecto" ).empty();
			$('#rs_costo_proyecto').html(data.precio_costo_manager);
			$('#rs_costo_proyecto').formatCurrency(currency_param);
			//Repositorio
			
			handleDataTableProyecto();
			
			//Traza
			handleDataTableTrazaProyecto();
			
			
			//Date Workflow
			
				//Puesta en marcha
				$('#work_fechas_01').val(data.fingreso_puesta_marcha_operacion);
			
			
			
			//Colapse panel
			
			//$('#panel_8').collapse('hide');
			
			
			// fechas comprometidas
			
			if(es_admin == 1){
				
				$('#btn_mod_cal_01').show();
				
				$("#req_fechas_01").datepicker({
			        todayHighlight: true,
			        format: 'dd-mm-yyyy',
			        lang:'es'
			    });
				
				$("#req_fechas_02").datepicker({
			        todayHighlight: true,
			        format: 'dd-mm-yyyy',
			        lang:'es'
			    });
				
				$("#req_fechas_03").datepicker({
			        todayHighlight: true,
			        format: 'dd-mm-yyyy',
			        lang:'es'
			    });
				
				$("#req_fechas_04").datepicker({
			        todayHighlight: true,
			        format: 'dd-mm-yyyy',
			        lang:'es'
			    });
				
				$("#req_fechas_05").datepicker({
			        todayHighlight: true,
			        format: 'dd-mm-yyyy',
			        lang:'es'
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
var handleBootstrapWizardsValidation = function() {
    var button1 = $('#button_01').val();
    var button2 = $('#button_02').val();
	
	"use strict";
    $("#wizard").bwizard({
    	backBtnText: button2,
    	nextBtnText: button1,
        validating: function(e, t) {
            
        	var aux_error1 = 0,aux_error2 = 0, aux_error3 = 0, aux_error4 = 0, aux_error5 = 0, aux_error6 = 0, aux_error7 = 0, aux_error8 = 0, aux_error9 = 0, aux_error10 = 0,
        	aux_error11 = 0, aux_error12 = 0, aux_error13 = 0, aux_error14 = 0, aux_error15 = 0, aux_error16 = 0, aux_error17 = 0, aux_error18 = 0, aux_error19 = 0, aux_error20 = 0,
        	aux_error21 = 0, aux_error22 = 0, aux_error23 = 0, aux_error24 = 0, aux_error25 = 0, aux_error26 = 0, aux_1 = 0, aux_2 = 0, aux_3 = 0, aux_4 = 0, aux_5 = 0, aux_6 = 0,
        	aux_7 = 0, aux_8 = 0, aux_9 = 0, aux_10 = 0, aux_11 = 0, aux_12 = 0, aux_13 = 0, aux_14 = 0, aux_15 = 0, aux_16 = 0, aux_17 = 0, aux_18 = 0, aux_19 = 0, aux_20 = 0,
        	aux_21 = 0, aux_22 = 0, aux_23 = 0, aux_24 = 0, aux_25 = 0, aux_26 = 0;
        	
        	if (t.index == 0) {
              if($('#valwiz_01').val()=="0"){  
        		if($('#txt_01').val()== ""){
                	aux_error1 = 1;
                    aux_1 = 1;
                    $('#txt_01').addClass("parsley-error");
                }else{
                	aux_error1 = 0;
                    aux_1 = 0;
                	$('#txt_01').removeClass("parsley-error");
                }
                if($('#txt_03').val()== "0"){
                	aux_error3 = 1;
                    aux_3 = 1;
                    $('#label_03').addClass("parsley-error");
                    
                }else{
                	aux_error3 = 0;
                    aux_3 = 0;
                	$('#label_03').removeClass("parsley-error");

                }
                if($('#txt_04').val()== null){
                	aux_error4 = 1;
                    aux_4 = 1;
                    //alert('#txt_04:'+$('#txt_04').val());
                    $('#label_04').addClass("parsley-error");
                }else{
                	aux_error4 = 0;
                    aux_4 = 0;
                    alert('#txt_04:'+$('#txt_04').val());
                    $('#label_04').removeClass("parsley-error");
                }
                if($('#txt_05').val()== "0"){
                	aux_error5 = 1;
                    aux_5 = 1;
                    //alert('#txt_04:'+$('#txt_04').val());
                    $('#label_05').addClass("parsley-error");
                }else{
                	aux_error5 = 0;
                    aux_5 = 0;
                    //alert('#txt_04:'+$('#txt_04').val());
                    $('#label_05').removeClass("parsley-error");
                }
                if($('#txt_06').val()== "0"){
                	aux_error6 = 1;
                    aux_6 = 1;
                    //alert('#txt_04:'+$('#txt_04').val());
                    $('#label_06').addClass("parsley-error");
                }else{
                	aux_error6 = 0;
                    aux_6 = 0;
                    //alert('#txt_04:'+$('#txt_04').val());
                    $('#label_06').removeClass("parsley-error");
                }
                if($('#txt_07').val()== "0"){
                	aux_error7 = 1;
                    aux_7 = 1;
                    //alert('#txt_04:'+$('#txt_04').val());
                    $('#label_07').addClass("parsley-error");
                }else{
                	aux_error7 = 0;
                    aux_7 = 0;
                    //alert('#txt_04:'+$('#txt_04').val());
                    $('#label_07').removeClass("parsley-error");
                }
                if($('#txt_09').val()== "0"){
                	aux_error9 = 1;
                    aux_9 = 1;
                    //alert('#txt_09:'+$('#txt_09').val());
                    $('#label_09').addClass("parsley-error");
                }else{
                	aux_error9 = 0;
                    aux_9 = 0;
                    //alert('#txt_04:'+$('#txt_04').val());
                    $('#label_09').removeClass("parsley-error");
                }
                if($('#txt_11').val()== "0"){
                	aux_error11 = 1;
                    aux_11 = 1;
                    //alert('#txt_09:'+$('#txt_09').val());
                    $('#label_11').addClass("parsley-error");
                }else{
                	aux_error11 = 0;
                    aux_11 = 0;
                    //alert('#txt_04:'+$('#txt_04').val());
                    $('#label_11').removeClass("parsley-error");
                }
                if($('#txt_12').val()== "0"){
                	aux_error12 = 1;
                    aux_12 = 1;
                    //alert('#txt_09:'+$('#txt_09').val());
                    $('#label_12').addClass("parsley-error");
                }else{
                	aux_error12 = 0;
                    aux_12 = 0;
                    //alert('#txt_04:'+$('#txt_04').val());
                    $('#label_12').removeClass("parsley-error");
                }
                if($('#txt_13').val()== "0"){
                	aux_error13 = 1;
                    aux_13 = 1;
                    //alert('#txt_09:'+$('#txt_09').val());
                    $('#label_13').addClass("parsley-error");
                }else{
                	aux_error13 = 0;
                    aux_13 = 0;
                    //alert('#txt_04:'+$('#txt_04').val());
                    $('#label_13').removeClass("parsley-error");
                }
                if($('#txt_15').val()== ""){
                	aux_error15 = 1;
                    aux_15 = 1;
                    //alert('#txt_09:'+$('#txt_09').val());
                    $('#txt_15').addClass("parsley-error");
                }else{
                	aux_error15 = 0;
                    aux_15 = 0;
                    //alert('#txt_04:'+$('#txt_04').val());
                    $('#txt_15').removeClass("parsley-error");
                }
                if($('#txt_16').val()== ""){
                	aux_error16 = 1;
                    aux_16 = 1;
                    //alert('#txt_09:'+$('#txt_09').val());
                    $('#txt_16').addClass("parsley-error");
                }else{
                	aux_error16 = 0;
                    aux_16 = 0;
                    //alert('#txt_04:'+$('#txt_04').val());
                    $('#txt_16').removeClass("parsley-error");
                }
                if($('#txt_17').val()== "0"){
                	aux_error17 = 1;
                    aux_17 = 1;
                    //alert('#txt_09:'+$('#txt_09').val());
                    $('#label_17').addClass("parsley-error");
                }else{
                	aux_error17 = 0;
                    aux_17 = 0;
                    //alert('#txt_04:'+$('#txt_04').val());
                    $('#label_17').removeClass("parsley-error");
                }
                if($('#txt_18').val()== "0"){
                	aux_error18 = 1;
                    aux_18 = 1;
                    //alert('#txt_09:'+$('#txt_09').val());
                    $('#label_18').addClass("parsley-error");
                }else{
                	aux_error18 = 0;
                    aux_18 = 0;
                    //alert('#txt_04:'+$('#txt_04').val());
                    $('#label_18').removeClass("parsley-error");
                }
                if(!regexDate.test($('#txt_19').val().trim())){
                	aux_error19 = 1;
                    aux_19 = 1;
                    //alert('#txt_09:'+$('#txt_09').val());
                    $('#txt_19').addClass("parsley-error");
                }else{
                	aux_error19 = 0;
                    aux_19 = 0;
                    //alert('#txt_04:'+$('#txt_04').val());
                    $('#txt_19').removeClass("parsley-error");
                }
                if(!regexDate.test($('#txt_20').val().trim())){
                	aux_error20 = 1;
                    aux_20 = 1;
                    //alert('#txt_09:'+$('#txt_09').val());
                    $('#txt_20').addClass("parsley-error");
                }else{
                	aux_error20 = 0;
                    aux_20 = 0;
                    //alert('#txt_04:'+$('#txt_04').val());
                    $('#txt_20').removeClass("parsley-error");
                }
                if(!regexDate.test($('#txt_21').val().trim())){
                	aux_error21 = 1;
                    aux_21 = 1;
                    //alert('#txt_09:'+$('#txt_09').val());
                    $('#txt_21').addClass("parsley-error");
                }else{
                	aux_error21 = 0;
                    aux_21 = 0;
                    //alert('#txt_04:'+$('#txt_04').val());
                    $('#txt_21').removeClass("parsley-error");
                }
                if(!regexDate.test($('#txt_22').val().trim())){
                	aux_error22 = 1;
                    aux_22 = 1;
                    //alert('#txt_09:'+$('#txt_09').val());
                    $('#txt_22').addClass("parsley-error");
                }else{
                	aux_error22 = 0;
                    aux_22 = 0;
                    //alert('#txt_04:'+$('#txt_04').val());
                    $('#txt_22').removeClass("parsley-error");
                }
                if(!regexDate.test($('#txt_23').val().trim())){
                	aux_error23 = 1;
                    aux_23 = 1;
                    //alert('#txt_09:'+$('#txt_09').val());
                    $('#txt_23').addClass("parsley-error");
                }else{
                	aux_error23 = 0;
                    aux_23 = 0;
                    //alert('#txt_04:'+$('#txt_04').val());
                    $('#txt_23').removeClass("parsley-error");
                }
                if($('#txt_24').val()== "0"){
                	aux_error24 = 1;
                    aux_24 = 1;
                    //alert('#txt_09:'+$('#txt_09').val());
                    $('#label_24').addClass("parsley-error");
                }else{
                	aux_error24 = 0;
                    aux_24 = 0;
                    //alert('#txt_04:'+$('#txt_04').val());
                    $('#label_24').removeClass("parsley-error");
                }
                if($('#txt_25').val()== "0"){
                	aux_error25 = 1;
                    aux_25 = 1;
                    //alert('#txt_09:'+$('#txt_09').val());
                    $('#label_25').addClass("parsley-error");
                }else{
                	aux_error25 = 0;
                    aux_25 = 0;
                    //alert('#txt_04:'+$('#txt_04').val());
                    $('#label_25').removeClass("parsley-error");
                }
                if($('#txt_26').val()== "0"){
                	aux_error26 = 1;
                    aux_26 = 1;
                    //alert('#txt_09:'+$('#txt_09').val());
                    $('#label_26').addClass("parsley-error");
                }else{
                	aux_error26 = 0;
                    aux_26 = 0;
                    //alert('#txt_04:'+$('#txt_04').val());
                    $('#label_26').removeClass("parsley-error");
                }
              }//End Val 
            } else if (t.index == 1) {
                
            } else if (t.index == 2) {
                
            } else if (t.index == 3) {
                
            }else if (t.index == 4) {
                
            }else if (t.index == 5) {
                
            }
        	
        	/*------------------------------WIZARD VALID 1 ---------------------------------------------------------------------------------------*/
        	if(aux_error1 == 1 || aux_error3 == 1 || aux_error4 == 1 || aux_error5 == 1 || aux_error6 == 1  || aux_error7 == 1  || aux_error9 == 1
        	  || aux_error11 == 1 || aux_error12 == 1 || aux_error13 == 1 || aux_error15 == 1 || aux_error16 == 1 || aux_error17 == 1 || aux_error18 == 1
        	  || aux_error19 == 1 || aux_error20 == 1 || aux_error21 == 1 || aux_error22 == 1 || aux_error23 == 1 || aux_error24 == 1 || aux_error25 == 1
        	  || aux_error26 == 1){
        		
        		var texto_error = "";
        		
        		if(aux_1 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+$('#errorv_01').val() + ' :: ';}
        		if(aux_2 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+$('#errorv_02').val() + ' :: ';}
        		if(aux_3 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+$('#errorv_03').val() + ' :: ';}
        		if(aux_4 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+$('#errorv_04').val() + ' :: ';}
        		if(aux_5 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+$('#errorv_05').val() + ' :: ';}
        		if(aux_6 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+$('#errorv_06').val() + ' :: ';}
        		if(aux_7 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+$('#errorv_07').val() + ' :: ';}
        		if(aux_9 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+$('#errorv_09').val() + ' :: ';}
        		if(aux_11 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+$('#errorv_11').val() + ' :: ';}
        		if(aux_12 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+$('#errorv_12').val() + ' :: ';}
        		if(aux_13 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+$('#errorv_13').val() + ' :: ';}
        		if(aux_15 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+$('#errorv_15').val() + ' :: ';}
        		if(aux_16 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+$('#errorv_16').val() + ' :: ';}
        		if(aux_17 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+$('#errorv_17').val() + ' :: ';}
        		if(aux_18 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+$('#errorv_18').val() + ' :: ';}
        		if(aux_19 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+$('#errorv_19').val() + ' :: ';}
        		if(aux_20 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+$('#errorv_20').val() + ' :: ';}
        		if(aux_21 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+$('#errorv_21').val() + ' :: ';}
        		if(aux_22 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+$('#errorv_22').val() + ' :: ';}
        		if(aux_23 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+$('#errorv_23').val() + ' :: ';}
        		
        		$('#etext-newlogin').html(texto_error);
        		$("#error-new").show();
        		
        		$(window).scrollTop($('#panel-new').offset().top);
        		
        		return false
        	}
        	/*------------------------------END WIZARD VALID 1 ---------------------------------------------------------------------------------------*/
        }
    })
};
var handleBootstrapWizardsValidation2 = function() {
    var button1 = $('#button_01').val();
    var button2 = $('#button_02').val();
	
	"use strict";
    $("#wizard").bwizard({
    	backBtnText: button2,
    	nextBtnText: button1,
        validating: function(e, t) {
            if (t.index == 0) {
                if (false === $('form[name="form-wizard"]').parsley().validate("wizard-step-1")) {
                    return false
                }
            } else if (t.index == 1) {
                if (false === $('form[name="form-wizard"]').parsley().validate("wizard-step-2")) {
                    return false
                }
            } else if (t.index == 2) {
                if (false === $('form[name="form-wizard"]').parsley().validate("wizard-step-3")) {
                    return false
                }
            }
        }
    })
};

var initSwitch = function(){
	$('.BSswitch').bootstrapSwitch();
}
var initSelect2Multi = function(){
	$(".multiple-select2").select2({
        placeholder: "Select a state"
    });
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
	handleSelect2GetGenericCombo("txt_12",0,$('#combo_12').val(),5,0,0,0);
	handleSelect2GetGenericCombo("txt_13",0,$('#combo_13').val(),6,0,0,2);
	handleSelect2GetGenericCombo("txt_17",0,$('#combo_17').val(),8,0,0,0);
	handleSelect2GetGenericCombo("txt_18",0,$('#combo_18').val(),9,0,0,0);
	handleSelect2GetUsuario("txt_24",0,0,$('#combo_24').val()) ;
	handleSelect2GetUsuario("txt_25",0,0,$('#combo_25').val()) ;
	handleSelect2GetUsuario("txt_26",0,0,$('#combo_26').val()) ;
	
	
	
}
var initSelect2_2 = function(){
	handleSelect2GetGenericCombo("txt_39",0,$('#combo_39').val(),10,0,0,0);
	handleSelect2GetGenericCombo("txt_40",0,$('#combo_40').val(),11,0,0,0);
	handleSelect2GetGenericCombo("txt_41",0,$('#combo_41').val(),12,0,0,0);
	handleSelect2GetGenericCombo("txt_44",0,$('#combo_44').val(),13,0,0,0);
	handleSelect2GetGenericCombo("txt_45",0,$('#combo_45').val(),14,0,0,0);
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
var intPreSetConfigForm = function(){
	//Cantidad de Paises 257
	$('#group_01').hide();
	$('#txt_02').select2('val',"1");
	$('#txt_09').select2('val',"1");
	$('#txt_13').select2('val',$('#aux_02').val());
	$('#txt_14').val(getValueComboDetalle(6,$('#txt_13').val(), 1, $('#lang_01').val()).text);
	$('#txt_18').select2('val',"1");
	$('#error-new').hide();
}
var initDatePicker = function(){
	$(".calendar").datepicker({
        todayHighlight: true,
        format: 'dd-mm-yyyy',
        lang:'es'
    });
	
	
}

//---------------------------------------
var Estudio = function() {
	"use strict";
	return {
		init : function() {
			handleBootstrapWizardsValidation();
			initSwitch();
			//initSelect2Multi();
			initDatePicker();
			initSelect2_1();
			initSelect2_2();
			initSelect2_3();
			initSelect2_5();
			intPreSetConfigForm();
			 
		}
	}
}();