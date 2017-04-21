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
var handleDataTableAsignacionHoras = function() {
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
	$('#data-table2 tfoot th').each( function () {
        var title = $('#data-table2 thead th').eq( $(this).index() ).text();
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
var handleBootstrapWizardsValidation = function() {
    var button1 = $('#button_01').val();
    var button2 = $('#button_02').val();
	
	"use strict";
    $("#wizard").bwizard({
    	backBtnText: button2,
    	nextBtnText: button1,
        validating: function(e, t) {
            //wizard 1
        	var aux_error1 = 0,aux_error2 = 0, aux_error3 = 0, aux_error4 = 0, aux_error5 = 0, aux_error6 = 0, aux_error7 = 0, aux_error8 = 0, aux_error9 = 0, aux_error10 = 0,
        	aux_error11 = 0, aux_error12 = 0, aux_error13 = 0, aux_error14 = 0, aux_error15 = 0, aux_error16 = 0, aux_error17 = 0, aux_error18 = 0, aux_error19 = 0, aux_error20 = 0,
        	aux_error21 = 0, aux_error22 = 0, aux_error23 = 0, aux_error24 = 0, aux_error25 = 0, aux_error26 = 0, aux_1 = 0, aux_2 = 0, aux_3 = 0, aux_4 = 0, aux_5 = 0, aux_6 = 0,
        	aux_7 = 0, aux_8 = 0, aux_9 = 0, aux_10 = 0, aux_11 = 0, aux_12 = 0, aux_13 = 0, aux_14 = 0, aux_15 = 0, aux_16 = 0, aux_17 = 0, aux_18 = 0, aux_19 = 0, aux_20 = 0,
        	aux_21 = 0, aux_22 = 0, aux_23 = 0, aux_24 = 0, aux_25 = 0, aux_26 = 0;
        	//wizard 2
        	var aux_error44=0, aux_error45=0, aux_error57=0, aux_error58=0;
        	var aux_44=0, aux_45=0, aux_57=0, aux_58=0;
        	//wizard 3
        	var aux_error114=0, aux_error115=0, aux_errorentre1=0;
        	var aux_114=0,aux_115=0,aux_entre1=0;
        	//wizard 4
        	var aux_error169=0,aux_error170=0,aux_error171=0,aux_error172=0,aux_error173=0,aux_error174=0,aux_error175=0,aux_error176=0,aux_error177=0,aux_error178=0,aux_error179=0,aux_error180=0,aux_error181=0,aux_error182=0,aux_error183=0,aux_error184=0,aux_error185=0,aux_error186=0,aux_error187=0,aux_error188=0,aux_error189=0;	
        	var aux_169=0,aux_170=0,aux_171=0,aux_172=0,aux_173=0,aux_174=0,aux_175=0,aux_176=0,aux_177=0,aux_178=0,aux_179=0,aux_180=0,aux_181=0,aux_182=0,aux_183=0,aux_184=0,aux_185=0,aux_186=0,aux_187=0,aux_188=0,aux_189=0;
        	//wizard 5
        	var aux_error190=0, aux_error193=0, aux_error194=0, aux_error195=0;
        	var aux_190=0, aux_193=0, aux_194=0, aux_195=0;
        	
        	if (t.index == 0) {
              
        	  //alert($('#txt_04').val());
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
                    //alert('#txt_04:'+$('#txt_04').val());
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
//*************************************************************************************************************************************************************************
                	/*------------------------------WIZARD VALID 1 ---------------------------------------------------------------------------------------*/
		        	if(aux_error1 == 1 || aux_error3 == 1 || aux_error4 == 1 || aux_error5 == 1 || aux_error6 == 1  || aux_error7 == 1  || aux_error9 == 1
		        	  || aux_error11 == 1 || aux_error12 == 1 || aux_error13 == 1 || aux_error15 == 1 || aux_error16 == 1 || aux_error17 == 1 || aux_error18 == 1
		        	  || aux_error19 == 1 || aux_error20 == 1 || aux_error21 == 1 || aux_error22 == 1 || aux_error23 == 1 || aux_error24 == 1 || aux_error25 == 1
		        	  || aux_error26 == 1){
		        		
		        		var texto_error = "";
		        		
		        		if(aux_1 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_01').val() + ' :: ';}
		        		if(aux_2 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_02').val() + ' :: ';}
		        		if(aux_3 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_03').val() + ' :: ';}
		        		if(aux_4 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_04').val() + ' :: ';}
		        		if(aux_5 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_05').val() + ' :: ';}
		        		if(aux_6 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_06').val() + ' :: ';}
		        		if(aux_7 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_07').val() + ' :: ';}
		        		if(aux_9 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_09').val() + ' :: ';}
		        		if(aux_11 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_11').val() + ' :: ';}
		        		if(aux_12 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_12').val() + ' :: ';}
		        		if(aux_13 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_13').val() + ' :: ';}
		        		if(aux_15 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_15').val() + ' :: ';}
		        		if(aux_16 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_16').val() + ' :: ';}
		        		if(aux_17 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_17').val() + ' :: ';}
		        		if(aux_18 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_18').val() + ' :: ';}
		        		if(aux_19 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_19').val() + ' :: ';}
		        		if(aux_20 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_20').val() + ' :: ';}
		        		if(aux_21 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_21').val() + ' :: ';}
		        		if(aux_22 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_22').val() + ' :: ';}
		        		if(aux_23 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_23').val() + ' :: ';}
		        		
		        		$('#etext-newlogin').html(texto_error);
		        		$("#error-new").show();
		        		
		        		$(window).scrollTop($('#panel-new').offset().top);
		        		
		        		return false
		        	}else{
		        		$('#etext-newlogin').empty();
		        		$("#error-new").hide();
		        		var pdr_aux = $('#txt_04').val().toString();
		        		var param = {
		        		    id : $('#txt_08').val(),
		        		    canal_venta : 1,
		        		    id_cliente : $('#txt_05').val(),
		        		    sector_medicion : $('#txt_02').val(),
		        		    industria_medicion : $('#txt_03').val(),
		        		    id_tipo_entrevista : $('#txt_12').val(),
		        		    area_medicion : 0,
		        		    nombre_operacion : $('#txt_01').val(),
		        		    id_geografia : $('#txt_09').val(),
		        		    cant_paises : $('#txt_10').val(),
		        		    id_clie_facturar : $('#txt_06').val(),
		        		    producto : pdr_aux,
		        		    tipo_estudio : $('#txt_11').val(),
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
		        		    res_us1_op : $('#txt_24').val(),
		        		    res_us2_op : $('#txt_25').val(),
		        		    res_us3_op : $('#txt_26').val(),
		        		    obj_obs_op : $('#txt_27').val(),
		        		    desc_op : $('#txt_28').val(),
		        		    set_up_1 : $('#txt_29').bootstrapSwitch('state').toString(),
		        		    set_up_2 : $('#txt_30').bootstrapSwitch('state').toString(),
		        		    set_up_3 : $('#txt_31').bootstrapSwitch('state').toString(),
		        		    set_up_4 : $('#txt_32').bootstrapSwitch('state').toString(),
		        		    set_up_5 : $('#txt_33').bootstrapSwitch('state').toString(),
		        		    set_up_6 : $('#txt_34').bootstrapSwitch('state').toString(),
		        		    set_up_7 : $('#txt_35').bootstrapSwitch('state').toString(),
		        		    set_up_8 : $('#txt_36').bootstrapSwitch('state').toString(),
		        		    set_up_9 : $('#txt_37').bootstrapSwitch('state').toString(),
		        		    set_up_10 : $('#txt_38').bootstrapSwitch('state').toString(),
		        		    set_up_11 : $('#txt_39').bootstrapSwitch('state').toString()
		        		}
		        		//alert($('#aux_08').val());
		        		if($('#aux_08').val() == "1"){
		        			$.ajax({
		        				url: "/Manager/RestCotizacion/setCotizacion1",
		        				type: "GET",
		        				dataType: "json",
		        				data: param,
		        				async: false,
		        				beforeSend: function(){
			        				//cargando los datos
			    					$( "#modalg-val-text" ).empty();
			    					$('#modalg-val-text').html('<center>Ejecutando Creaci&oacute;n de Cotizaci&oacute;n <br/>  Favor espere... </center>');
			    					$("#modalg-val").modal("show");
		        				},
		        				success: function(data){
		        					$("#modalg-val-text" ).empty();
		        					$("#modalg-val").modal("hide");
//		        					$.each(data,function(index,val){
//		        						alert('Index: '+index+'\n'+'value: '+val);
//		        					});
		        					
		        					if(data.id_cotizacion != 0){
		        						
		        					    $("#modalg-success-text" ).empty();
		        						$('#modalg-success-text').html('<center>'+ data.text +'</center><br>');
		        						$("#modalg-success").modal("show");
		        						
		        						$('#aux_08').val(2);
		        						$('#aux_09').val(data.id_operacion);
		        						
		        						$('#new_cot1').text(data.codigo_cotizacion);
		        						
		        						readOnlyFirstTape();
		        						
		        					}else{
		        						return false;
		        						
		        						var data = {
		        							status: "Error de Permiso",
		        							text: '<center>Se ha generado un error: <strong>-- '+ data.text +' --</strong> <br/>  Favor contactar mesa de ayuda! </center>'
			        					}
			        					errorAjaxRequest(data);
			        					
			        					
		        					}
		        				},
		        				error: function (xhr, ajaxOptions, thrownError) {
		        					$("#modalg-val").modal("hide");
		        					var data = {
		        							status: xhr.status,
		        							text: '<center>Se ha generado un error: <strong>-- addCotizacionFase1 Modulo Cotizaci&oacute;n --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
		        					}
		        					errorAjaxRequest(data);
		        					
		        					return false;
		        					
		        				}
		        				
		        			});
		        		}//end $('#aux_08').val() == 1
		        		
		        		
		        	}
        	/*------------------------------END WIZARD VALID 1 ---------------------------------------------------------------------------------------*/
                

//*************************************************************************************************************************************************************************
              }//End Val 1
            } else if (t.index == 1) {
               //Wizard 2 Recoleccion
              //alert('val: '+ $('#valwiz_02').val());
          	  if($('#valwiz_02').val()== "0"){  
          		 //alert('bootstrapSwitch: '+ $('#txt_29').bootstrapSwitch('state').toString());
          		 if($('#txt_29').bootstrapSwitch('state').toString() == "true"){
	          		 if($('#txt_44').val()== "0"){
	                  	  aux_error44 = 1;
	                      aux_44 = 1;
	                      $('#label_44').addClass("parsley-error");
	                  }else{
	                	  aux_error44 = 0;
	                      aux_44 = 0;
	                      //alert('#txt_04:'+$('#txt_04').val());
	                      $('#label_44').removeClass("parsley-error");
	                 }
	          		 if($('#txt_45').val()== "0"){
	                   	aux_error45 = 1;
	                    aux_45 = 1;
	                    $('#label_45').addClass("parsley-error");
	                 }else{
	                	  aux_error45 = 0;
	                      aux_45 = 0;
	                      //alert('#txt_04:'+$('#txt_04').val());
	                      $('#label_45').removeClass("parsley-error");
	                 }
	          		 if($('#txt_57').val()== "" || $('#txt_57').val()== "0"){
	                   	aux_error57 = 1;
	                    aux_57 = 1;
	                    $('#txt_57').addClass("parsley-error");
	                 }else{
	                	  aux_error57 = 0;
	                      aux_57 = 0;
	                      //alert('#txt_04:'+$('#txt_04').val());
	                      $('#txt_57').removeClass("parsley-error");
	                 }
	          		 if($('#txt_58').val()== ""  || $('#txt_58').val()== "0"){
	                   	aux_error58 = 1;
	                    aux_58 = 1;
	                    $('#txt_58').addClass("parsley-error");
	                 }else{
	                	  aux_error58 = 0;
	                      aux_58 = 0;
	                      //alert('#txt_04:'+$('#txt_04').val());
	                      $('#txt_58').removeClass("parsley-error");
	                 }
	          		 if(aux_error44 == 1 || aux_error45 == 1 || aux_error57 == 1 || aux_error58 == 1){
	          			
	          			var texto_error = "";
	          		 
	          			if(aux_44 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_44').val() + ' :: ';}
	          			if(aux_45 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_45').val() + ' :: ';}
	          			if(aux_57 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_57').val() + ' :: ';}
	          			if(aux_58 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_58').val() + ' :: ';}
	          			
	          			$('#etext-newlogin').html(texto_error);
		        		$("#error-new").show();
		        		
		        		$(window).scrollTop($('#panel-new').offset().top);
		        		
		        		return false

	          			
	          		 }else{
	          			//Inicia Update de Estudio
	          			 $('#etext-newlogin').empty();
		        		 $("#error-new").hide();
		        		 
		        		 var param = {
		        		     id: $('#aux_09').val(),
		        		     modo: $('#txt_44').val(),
		        		     sub_modo: $('#txt_45').val(),
		        		     dur_intro: $('#txt_57').val(),
		        		     dur_cuest: $('#txt_58').val(),
		        		     dur_total: $('#txt_59').val()
		        		 }
//		        		 $.each(param,function(index,val){
//		        			alert('Index: '+index+'\n'+'value: '+val);
//		        		});
		        		 $.ajax({
		        				url: "/Manager/RestCotizacion/setCotizacion2",
		        				type: "GET",
		        				dataType: "json",
		        				data: param,
		        				async: false,
		        				beforeSend: function(){
			        				//cargando los datos
			    					$( "#modalg-val-text" ).empty();
			    					$('#modalg-val-text').html('<center>Ejecutando Update de Estudio - Recolecci&oacute;n <br/>  Favor espere... </center>');
			    					$("#modalg-val").modal("show");
		        				},
		        				success: function(data){
		        					$("#modalg-val-text" ).empty();
		        					$("#modalg-val").modal("hide");
		        					$.each(data,function(index,val){
		        						alert('Index: '+index+'\n'+'value: '+val);
		        					});
									
		        					if(data.id_operacion != 0){
		        						
		        						$.notify({
											icon: 'fa fa-check',
										    title: 'Se Actualizan datos de Recolecci&oacute;n',
										    message: ''
											
											//timer: 10000
										},
										{
											type: 'success',
										    animation:true
										});
		        						
		        					}else{
		        						return false;
		        						
		        						var data = {
		        							status: "Error de Permiso",
		        							text: '<center>Se ha generado un error: <strong>-- '+ data.text +' --</strong> <br/>  Favor contactar mesa de ayuda! </center>'
			        					}
			        					errorAjaxRequest(data);
			        					
			        					
		        					}
		        				},
		        				error: function (xhr, ajaxOptions, thrownError) {
		        					$("#modalg-val").modal("hide");
		        					var data = {
		        							status: xhr.status,
		        							text: '<center>Se ha generado un error: <strong>-- addCotizacionFase2 Modulo Cotizaci&oacute;n --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
		        					}
		        					errorAjaxRequest(data);
		        					
		        					return false;
		        					
		        				}
		        				
		        			});

	          		 }//end if(aux_error44 == 1 || aux_error45 == 1 || aux_error57 == 1 || aux_error58 == 1){
	             }else{
	            	 readOnlySecondtTape();
	             }// end $('#txt_29').bootstrapSwitch('state').toString() == true
	              
          	  }//end if($('#valwiz_02').val()=="0")
                
            } else if (t.index == 2) {
            	if($('#valwiz_03').val()== "0"){ 
            		if($('#txt_113').bootstrapSwitch('state').toString() == "true"){
            			if($('#txt_114').val() == 0){
            				aux_error114 = 1;
    	                    aux_114 = 1;
    	                    $('#label_114').addClass("parsley-error");
            			}else{
            			  aux_error114 = 0;
  	                      aux_114 = 0;
  	                      //alert('#txt_04:'+$('#txt_04').val());
  	                      $('#label_114').removeClass("parsley-error");
            			}
            			if($('#txt_115').val() == 0){
            				aux_error115 = 1;
    	                    aux_115 = 1;
    	                    $('#label_115').addClass("parsley-error");
            			}else{
            			  aux_error115 = 0;
  	                      aux_115 = 0;
  	                      //alert('#txt_04:'+$('#txt_04').val());
  	                      $('#label_115').removeClass("parsley-error");
            			}
            		}
            		if($('#entre_01').bootstrapSwitch('state').toString() == "true"){
            			if($('#txt_143').bootstrapSwitch('state').toString() == "true"
            			   || $('#txt_144').bootstrapSwitch('state').toString() == "true"
            			   || $('#txt_145').bootstrapSwitch('state').toString() == "true"
            			   || $('#txt_146').bootstrapSwitch('state').toString() == "true"
            			   || $('#txt_147').bootstrapSwitch('state').toString() == "true"
            			   || $('#txt_148').bootstrapSwitch('state').toString() == "true"	   
            				){
            					aux_errorentre1 = 0;
    	                      aux_entre1 = 0;
    	                      $('#label_entre1').removeClass("parsley-error");
            			}else{
            				aux_errorentre1 = 1;
  	                      aux_entre1 = 1;
  	                      $('#label_entre1').addClass("parsley-error");
            			}
            			
            		}
            		if(aux_error114 == 1 || aux_error115 == 1 || aux_errorentre1 == 1){
	          			
	          			var texto_error = "";
	          		 
	          			if(aux_114 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_114').val() + ' :: ';}
	          			if(aux_115 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_115').val() + ' :: ';}
	          			if(aux_entre1 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_entre1').val() + ' :: ';}
	          			
	          			
	          			$('#etext-newlogin').html(texto_error);
		        		$("#error-new").show();
		        		
		        		$(window).scrollTop($('#panel-new').offset().top);
		        		
		        		return false

	          			
	          		 }else{
	          			 //ejecuta actualizacion
	          			 //alert('Se ejecuta actualización set up 2');
	          			 $('#etext-newlogin').empty();
		        		 $("#error-new").hide();
		        		 
		        		 var param ={
		        			 id: $('#aux_09').val(),
		        			 dig : $('#txt_110').bootstrapSwitch('state').toString(),
		        			 ver_capture : $('#txt_111').bootstrapSwitch('state').toString(),
		        			 tab : $('#txt_113').bootstrapSwitch('state').toString(),
		        			 datos_entrega : $('#txt_114').val(),
		        		     formato_entrega : $('#txt_115').val(),
		        		     tabla_pre : $('#txt_116').bootstrapSwitch('state').toString(),
		        		     analisis : $('#ana_01').bootstrapSwitch('state').toString(),
		        		     diseno_muestra : $('#txt_131').bootstrapSwitch('state').toString(),
		        		     entrega : $('#entre_01').bootstrapSwitch('state').toString(),
		        		     entre_1 : $('#txt_143').bootstrapSwitch('state').toString(),
			        		 entre_2 : $('#txt_144').bootstrapSwitch('state').toString(),
			        		 entre_3 : $('#txt_145').bootstrapSwitch('state').toString(),
			        		 entre_4 : $('#txt_146').bootstrapSwitch('state').toString(),
			        		 entre_5 : $('#txt_147').bootstrapSwitch('state').toString(),
			        		 entre_6 : $('#txt_148').bootstrapSwitch('state').toString(),
			        		 estado_setup : $('#aux_10').val()
		        		 }
//		        		 $.each(param,function(index,val){
//		        			alert('Index: '+index+'\n'+'value: '+val);
//		        		});
		        		 $.ajax({
		        				url: "/Manager/RestCotizacion/setCotizacion3",
		        				type: "GET",
		        				dataType: "json",
		        				data: param,
		        				async: false,
		        				beforeSend: function(){
			        				//cargando los datos
			    					$( "#modalg-val-text" ).empty();
			    					$('#modalg-val-text').html('<center>Ejecutando Update de Estudio  - SET UP 2 <br/>  Favor espere... </center>');
			    					$("#modalg-val").modal("show");
		        				},
		        				success: function(data){
		        					$("#modalg-val-text" ).empty();
		        					$("#modalg-val").modal("hide");
//		        					$.each(data,function(index,val){
//		        						alert('Index: '+index+'\n'+'value: '+val);
//		        					});
		        					$('#aux_10').val(2);
		        					if(data.id_operacion != 0){
		        						$.notify({
											icon: 'fa fa-check',
										    title: 'Se Actualizan datos de SET UP 2',
										    message: ''
											
											//timer: 10000
										},
										{
											type: 'success',
										    animation:true
										});
		        						
		        					}else{
		        						return false;
		        						
		        						var data = {
		        							status: "Error de Permiso",
		        							text: '<center>Se ha generado un error: <strong>-- '+ data.text +' --</strong> <br/>  Favor contactar mesa de ayuda! </center>'
			        					}
			        					errorAjaxRequest(data);
			        					
			        					
		        					}
		        				},
		        				error: function (xhr, ajaxOptions, thrownError) {
		        					$("#modalg-val").modal("hide");
		        					var data = {
		        							status: xhr.status,
		        							text: '<center>Se ha generado un error: <strong>-- addCotizacionFase2 Modulo Cotizaci&oacute;n --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
		        					}
		        					errorAjaxRequest(data);
		        					
		        					return false;
		        					
		        				}
		        				
		        			});
	          		 }
            		
            	}//end if($('#valwiz_03').val()== "0"){
            } else if (t.index == 3) {
            	if($('#valwiz_04').val()==0){
            		if($('#txt_169').val() == 0){
        				aux_error169 = 1;
	                    aux_169 = 1;
	                    $('#label_169').addClass("parsley-error");
        			}else{
        			  aux_error169 = 0;
	                      aux_169 = 0;
	                      //alert('#txt_04:'+$('#txt_04').val());
	                      $('#label_169').removeClass("parsley-error");
        			}
            		if($('#txt_170').val() == ""){
        				aux_error170 = 1;
	                    aux_170 = 1;
	                    $('#txt_170').addClass("parsley-error");
        			}else{
        			  aux_error170 = 0;
	                      aux_170 = 0;
	                      //alert('#txt_04:'+$('#txt_04').val());
	                      $('#txt_170').removeClass("parsley-error");
        			}
            		if($('#txt_171').val() == 0){
        				aux_error171 = 1;
	                    aux_171 = 1;
	                    $('#label_171').addClass("parsley-error");
        			}else{
        			  aux_error171 = 0;
	                      aux_171 = 0;
	                      //alert('#txt_04:'+$('#txt_04').val());
	                      $('#label_171').removeClass("parsley-error");
        			}
            		if($('#txt_172').val() == ""){
        				aux_error172 = 1;
	                    aux_172 = 1;
	                    $('#txt_172').addClass("parsley-error");
        			}else{
        			  aux_error172 = 0;
	                      aux_172 = 0;
	                      //alert('#txt_04:'+$('#txt_04').val());
	                      $('#txt_172').removeClass("parsley-error");
        			}
            		if($('#txt_173').val() == 0){
        				aux_error173 = 1;
	                    aux_173 = 1;
	                    $('#label_173').addClass("parsley-error");
        			}else{
        			  aux_error173 = 0;
	                      aux_173 = 0;
	                      //alert('#txt_04:'+$('#txt_04').val());
	                      $('#label_173').removeClass("parsley-error");
        			}
            		if($('#txt_174').val() == 0){
        				aux_error174 = 1;
	                    aux_174 = 1;
	                    $('#label_174').addClass("parsley-error");
        			}else{
        			  aux_error174 = 0;
	                      aux_174 = 0;
	                      //alert('#txt_04:'+$('#txt_04').val());
	                      $('#label_174').removeClass("parsley-error");
        			}
            		if($('#txt_175').val() == ""){
        				aux_error175 = 1;
	                    aux_175 = 1;
	                    $('#txt_175').addClass("parsley-error");
        			}else{
        			  aux_error175 = 0;
	                      aux_175 = 0;
	                      //alert('#txt_04:'+$('#txt_04').val());
	                      $('#txt_175').removeClass("parsley-error");
        			}
            		if($('#txt_176').val() == 0){
        				aux_error176 = 1;
	                    aux_176 = 1;
	                    $('#label_176').addClass("parsley-error");
        			}else{
        			  aux_error176 = 0;
	                      aux_176 = 0;
	                      //alert('#txt_04:'+$('#txt_04').val());
	                      $('#label_176').removeClass("parsley-error");
        			}
            		if($('#txt_177').val() == ""){
        				aux_error177 = 1;
	                    aux_177 = 1;
	                    $('#txt_177').addClass("parsley-error");
        			}else{
        			  aux_error177 = 0;
	                      aux_177 = 0;
	                      //alert('#txt_04:'+$('#txt_04').val());
	                      $('#txt_177').removeClass("parsley-error");
        			}
        			if($('#txt_178').val() == ""){
        				aux_error178 = 1;
	                    aux_178 = 1;
	                    $('#txt_178').addClass("parsley-error");
        			}else{
        			  aux_error178 = 0;
	                      aux_178 = 0;
	                      //alert('#txt_04:'+$('#txt_04').val());
	                      $('#txt_178').removeClass("parsley-error");
        			}
        			if($('#txt_179').val() == 0){
        				aux_error179 = 1;
	                    aux_179 = 1;
	                    $('#label_179').addClass("parsley-error");
        			}else{
        			  aux_error179 = 0;
	                      aux_179 = 0;
	                      //alert('#txt_04:'+$('#txt_04').val());
	                      $('#label_179').removeClass("parsley-error");
        			}
        			if($('#txt_180').val() == ""){
        				aux_error180 = 1;
	                    aux_180 = 1;
	                    $('#txt_180').addClass("parsley-error");
        			}else{
        			  aux_error180 = 0;
	                  aux_180 = 0;
	                  //alert('#txt_04:'+$('#txt_04').val());
	                  $('#txt_180').removeClass("parsley-error");
        			}
        			if($('#txt_181').val() == 0){
        				aux_error181 = 1;
	                    aux_181 = 1;
	                    $('#label_181').addClass("parsley-error");
        			}else{
        			  aux_error181 = 0;
	                      aux_181 = 0;
	                      //alert('#txt_04:'+$('#txt_04').val());
	                      $('#label_181').removeClass("parsley-error");
        			}
        			if($('#txt_182').val() == 0){
        				aux_error182 = 1;
	                    aux_182 = 1;
	                    $('#label_182').addClass("parsley-error");
        			}else{
        			  aux_error182 = 0;
	                      aux_182 = 0;
	                      //alert('#txt_04:'+$('#txt_04').val());
	                      $('#label_182').removeClass("parsley-error");
        			}
        			if($('#txt_183').val() == ""){
        				aux_error183 = 1;
	                    aux_183 = 1;
	                    $('#txt_183').addClass("parsley-error");
        			}else{
        			  aux_error183 = 0;
	                  aux_183 = 0;
	                  //alert('#txt_04:'+$('#txt_04').val());
	                  $('#txt_183').removeClass("parsley-error");
        			}
        			if($('#txt_184').val() == ""){
        				aux_error184 = 1;
	                    aux_184 = 1;
	                    $('#txt_184').addClass("parsley-error");
        			}else{
        			  aux_error184 = 0;
	                  aux_184 = 0;
	                  //alert('#txt_04:'+$('#txt_04').val());
	                  $('#txt_184').removeClass("parsley-error");
        			}
        			if($('#txt_185').val() == ""){
        				aux_error185 = 1;
	                    aux_185 = 1;
	                    $('#txt_185').addClass("parsley-error");
        			}else{
        			  aux_error185 = 0;
	                  aux_185 = 0;
	                  //alert('#txt_04:'+$('#txt_04').val());
	                  $('#txt_185').removeClass("parsley-error");
        			}
        			if($('#txt_186').val() == 0){
        				aux_error186 = 1;
	                    aux_186 = 1;
	                    $('#label_186').addClass("parsley-error");
        			}else{
        			  aux_error186 = 0;
	                      aux_186 = 0;
	                      //alert('#txt_04:'+$('#txt_04').val());
	                      $('#label_186').removeClass("parsley-error");
        			}
        			if($('#txt_187').val() == 0){
        				aux_error187 = 1;
	                    aux_187 = 1;
	                    $('#label_187').addClass("parsley-error");
        			}else{
        			  aux_error187 = 0;
	                      aux_187 = 0;
	                      //alert('#txt_04:'+$('#txt_04').val());
	                      $('#label_187').removeClass("parsley-error");
        			}
        			if($('#txt_188').val() == ""){
        				aux_error188 = 1;
	                    aux_188 = 1;
	                    $('#txt_188').addClass("parsley-error");
        			}else{
        			  aux_error188 = 0;
	                  aux_188 = 0;
	                  //alert('#txt_04:'+$('#txt_04').val());
	                  $('#txt_188').removeClass("parsley-error");
        			}
        			if($('#txt_189').val() == ""){
        				aux_error189 = 1;
	                    aux_189 = 1;
	                    $('#txt_189').addClass("parsley-error");
        			}else{
        			  aux_error189 = 0;
	                  aux_189 = 0;
	                  //alert('#txt_04:'+$('#txt_04').val());
	                  $('#txt_189').removeClass("parsley-error");
        			}
        			if(aux_error169 == 1 ||aux_error170 == 1 ||aux_error171 == 1 ||aux_error172 == 1 ||aux_error173 == 1 ||aux_error174 == 1 ||aux_error175 == 1 ||aux_error176 == 1 ||aux_error177 == 1 ||aux_error178 == 1 ||aux_error179== 1 ||
        			   aux_error180 == 1 || aux_error181 == 1 ||aux_error182 == 1 ||aux_error183 == 1 ||aux_error184 == 1 ||aux_error185 == 1 ||aux_error186 == 1 ||aux_error187 == 1 ||aux_error188 == 1 ||aux_error189 == 1 ){
	          			
	          			var texto_error = "";
	          		 
	          			if(aux_169 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_169').val() + ' :: ';}
	          			if(aux_170 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_170').val() + ' :: ';}
	          			if(aux_171 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_171').val() + ' :: ';}
	          			if(aux_172 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_172').val() + ' :: ';}
	          			if(aux_173 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_173').val() + ' :: ';}
	          			if(aux_174 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_174').val() + ' :: ';}
	          			if(aux_175 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_175').val() + ' :: ';}
	          			if(aux_176 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_176').val() + ' :: ';}
	          			if(aux_177 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_177').val() + ' :: ';}
	          			if(aux_178 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_178').val() + ' :: ';}
	          			if(aux_179 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_179').val() + ' :: ';}
	          			if(aux_180 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_180').val() + ' :: ';}
	          			if(aux_181 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_181').val() + ' :: ';}
	          			if(aux_182 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_182').val() + ' :: ';}
	          			if(aux_183 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_183').val() + ' :: ';}
	          			if(aux_184 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_184').val() + ' :: ';}
	          			if(aux_185 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_185').val() + ' :: ';}
	          			if(aux_186 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_186').val() + ' :: ';}
	          			if(aux_187 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_187').val() + ' :: ';}
	          			if(aux_188 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_188').val() + ' :: ';}
	          			if(aux_189 == 1){texto_error = texto_error + '<i class="fa fa fa-exclamation-triangle"></i> '+ $('#errorv_189').val() + ' :: ';}
	          			
	          			
	          			$('#etext-newlogin').html(texto_error);
		        		$("#error-new").show();
		        		
		        		$(window).scrollTop($('#panel-new').offset().top);
		        		
		        		return false

	          			
	          		 }else{
	          			//ejecuta actualizacion
	          			 //alert('Se ejecuta actualización set up 2');
	          			 $('#etext-newlogin').empty();
		        		 $("#error-new").hide();
		        		 
		        		 var param ={
		        			 id: $('#aux_09').val(),
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
			        		 estado_setup : $('#aux_11').val()
		        		 }
//		        		 $.each(param,function(index,val){
//		        			alert('Index: '+index+'\n'+'value: '+val);
//		        		});
		        		 $.ajax({
		        				url: "/Manager/RestCotizacion/setCotizacion4",
		        				type: "GET",
		        				dataType: "json",
		        				data: param,
		        				async: false,
		        				beforeSend: function(){
			        				//cargando los datos
			    					$( "#modalg-val-text" ).empty();
			    					$('#modalg-val-text').html('<center>Ejecutando Update de Estudio  - SET UP 2 <br/>  Favor espere... </center>');
			    					$("#modalg-val").modal("show");
		        				},
		        				success: function(data){
		        					$("#modalg-val-text" ).empty();
		        					$("#modalg-val").modal("hide");
//		        					$.each(data,function(index,val){
//		        						alert('Index: '+index+'\n'+'value: '+val);
//		        					});
		        					$('#aux_10').val(2);
		        					if(data.id_operacion != 0){
		        						$.notify({
											icon: 'fa fa-check',
										    title: 'Se Actualizan datos de BUSSNESS CASE',
										    message: ''
											
											//timer: 10000
										},
										{
											type: 'success',
										    animation:true
										});
		        						
		        					}else{
		        						return false;
		        						
		        						var data = {
		        							status: "Error de Permiso",
		        							text: '<center>Se ha generado un error: <strong>-- '+ data.text +' --</strong> <br/>  Favor contactar mesa de ayuda! </center>'
			        					}
			        					errorAjaxRequest(data);
			        					
			        					
		        					}
		        				},
		        				error: function (xhr, ajaxOptions, thrownError) {
		        					$("#modalg-val").modal("hide");
		        					var data = {
		        							status: xhr.status,
		        							text: '<center>Se ha generado un error: <strong>-- addCotizacionFase2 Modulo Cotizaci&oacute;n --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
		        					}
		        					errorAjaxRequest(data);
		        					
		        					return false;
		        					
		        				}
		        				
		        			});
	          		 }
        				

            	} 
            }else if (t.index == 4) {
            	//wizard 5
            	//var aux_error190=0, aux_error193=0, aux_error194=0, aux_error195=0;
            	//var aux_190=0, aux_193=0, aux_194=0, aux_195=0;
            	if($('#valwiz_04').val()==0){
	            	var pventa = $('#txt_195').val();
	            	var num_entre = $('#txt_190').val();
	            	var por_inc = $('#txt_193').val();
	            	var rebate = $('#txt_194').val();
	            	if(pventa== ""){ pventa = 0;}
	            	if(num_entre== ""){ num_entre = 0;}
	            	if(por_inc== ""){ por_inc = 0;}
	            	if(rebate== ""){ rebate = 0;}
	            	
	            	var param ={
	            		id: $('#aux_09').val(),
	            		pventa: pventa,
	            		num_entre:num_entre,
	            		tipo_entre : $('#txt_12').val(),
	            		dur_entre : $('#txt_59').val(),
	            		por_inc : por_inc,
	            		rebate : rebate,
	            		modo : $('#txt_44').val(),
	            		submodo : $('#txt_45').val()
	            	}
	//		        		 $.each(param,function(index,val){
	//		        			alert('Index: '+index+'\n'+'value: '+val);
	//		        		});
			        		 $.ajax({
			        				url: "/Manager/RestCotizacion/setCotizacion5",
			        				type: "GET",
			        				dataType: "json",
			        				data: param,
			        				async: false,
			        				beforeSend: function(){
				        				//cargando los datos
				    					$( "#modalg-val-text" ).empty();
				    					$('#modalg-val-text').html('<center>Ejecutando Cotizador - FIN SET UP  <br/>  Favor espere... </center>');
				    					$("#modalg-val").modal("show");
			        				},
			        				success: function(data){
			        					$("#modalg-val-text" ).empty();
			        					$("#modalg-val").modal("hide");
	//		        					$.each(data,function(index,val){
	//		        						alert('Index: '+index+'\n'+'value: '+val);
	//		        					});
			        					$('#aux_10').val(2);
			        					if(data.id_operacion != 0){
			        						$.notify({
												icon: 'fa fa-check',
											    title: 'Se Finaliza Cotizaci&oacute;n con &Eacute;xito!',
											    message: ''
												
												//timer: 10000
											},
											{
												type: 'success',
											    animation:true
											});
			        						
			        					}else{
			        						return false;
			        						
			        						var data = {
			        							status: "Error de Permiso",
			        							text: '<center>Se ha generado un error: <strong>-- '+ data.text +' --</strong> <br/>  Favor contactar mesa de ayuda! </center>'
				        					}
				        					errorAjaxRequest(data);
				        					
				        					
			        					}
			        				},
			        				error: function (xhr, ajaxOptions, thrownError) {
			        					$("#modalg-val").modal("hide");
			        					var data = {
			        							status: xhr.status,
			        							text: '<center>Se ha generado un error: <strong>-- addCotizacionFaseFinal Modulo Cotizaci&oacute;n --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			        					}
			        					errorAjaxRequest(data);
			        					
			        					return false;
			        					
			        				}
			        				
			        			});  
			        		}// end debug wizard 5
            	
            }else if (t.index == 5) {
            	//alert(5);
            	//Volver de la Finalizacion de creacion de Cotizacion
            }
        	
        	
        	
        }
    })
};
var readOnlyFirstTape = function(){
	$('#txt_08').prop("disabled", true); //id 
	//canal_venta : 1,
	$('#txt_05').prop("disabled", true); //id_cliente
	$('#txt_02').prop("disabled", true); //sector_medicion
	$('#txt_03').prop("disabled", true); //industria_medicion
	$('#txt_12').prop("disabled", true);//id_tipo_entrevista 
	$('#txt_07').prop("disabled", true);//id_tipo_CUENTA 
	//area_medicion : 0,
	$('#txt_01').prop('readonly', true); //nombre_operacion : 
	$('#txt_09').prop("disabled", true);//id_geografia : 
	$('#txt_10').prop("disabled", true); // cant_paises : 
	$('#txt_06').prop("disabled", true);//id_clie_facturar :
	$('#txt_04').prop("disabled", true);//producto : pdr_aux,
	$('#txt_11').prop("disabled", true);//tipo_estudio : 
	$('#txt_13').prop("disabled", true);//booking_legal_entity : 
	$('#txt_15').prop('readonly', true); //centro_costo_op : 
	$('#txt_16').prop('readonly', true); //por_ejec_estudio : 
	$('#txt_17').prop("disabled", true);//digital_op : 
	$('#txt_18').prop("disabled", true);//moneda_op : 
	$('#txt_19').prop('readonly', true);//date_prob_in_est_op : 
	$('#txt_20').prop('readonly', true);//date_prob_entre_est_op : 
	$('#txt_21').prop('readonly', true);//ddate_pres_of_equipo_op : 
	$('#txt_22').prop('readonly', true);//ddate_pres_gps_op : 
	$('#txt_23').prop('readonly', true);//ddate_pres_clie_op : 
	$('#txt_24').prop("disabled", true);//res_us1_op : 
	$('#txt_25').prop("disabled", true);//res_us2_op : 
	$('#txt_26').prop("disabled", true);//res_us3_op : 
	$('#txt_27').prop('readonly', true);//obj_obs_op : 
	$('#txt_28').prop('readonly', true);//desc_op : 
	$('#txt_29').bootstrapSwitch('disabled',true); //set_up_1 : 
	$('#txt_30').bootstrapSwitch('disabled',true); //set_up_2 : 
	$('#txt_31').bootstrapSwitch('disabled',true); //set_up_3 : 
	$('#txt_32').bootstrapSwitch('disabled',true); //set_up_4 : 
	$('#txt_33').bootstrapSwitch('disabled',true); //set_up_5 : 
	$('#txt_34').bootstrapSwitch('disabled',true); //set_up_6 : 
	$('#txt_35').bootstrapSwitch('disabled',true); //set_up_7 : 
	$('#txt_36').bootstrapSwitch('disabled',true); //set_up_8 : 
	$('#txt_37').bootstrapSwitch('disabled',true); //set_up_9 : 
	$('#txt_38').bootstrapSwitch('disabled',true); //set_up_10 : 
	$('#txt_39').bootstrapSwitch('disabled',true); //set_up_11 : 
	
	////////*********************************************///////
	if($('#txt_29').bootstrapSwitch('state').toString() == "false"){
		readOnlySecondtTape();
	}else{
		activeSecondtTape();
	}
	$('#txt_99').bootstrapSwitch('disabled',true);
	$('#txt_100').bootstrapSwitch('disabled',true);
	$('#txt_110').bootstrapSwitch('disabled',true);
	$('#txt_112').bootstrapSwitch('disabled',true);
	$('#txt_113').bootstrapSwitch('disabled',true);
	$('#ana_01').bootstrapSwitch('disabled',true);
	$('#entre_01').bootstrapSwitch('disabled',true);
	
	
}
var readOnlySecondtTape = function(){
	$('#txt_44').prop("disabled", true);//modo
	$('#txt_45').prop("disabled", true);//sub modo
	$('#txt_57').prop('readonly', true);
	$('#txt_58').prop('readonly', true);
}
var activeSecondtTape = function(){
	$('#txt_44').prop("disabled", false);//modo
	$('#txt_45').prop("disabled", false);//sub modo
	$('#txt_57').prop('readonly', false);
	$('#txt_58').prop('readonly', false);
}
var calcularCotiza = function(){
	alert('En Desarrollo, pendiente definicion JP');
}
var verCOtizacion = function(){
	
	var nombre = encodeURIComponent($('#txt_01').val());
	window.open('/Manager/proyectoService/detalleProy?id='+$('#aux_09').val()+'&nombre='+nombre+'&tipo=1', '_self');
}
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
    });
};

var initSwitch = function(){
	$('.BSswitch').bootstrapSwitch();
	//$("#txt_99").bootstrapSwitch('disabled',true);
	
	$('#txt_30').on('switchChange.bootstrapSwitch', function(event, state) {
	  //alert(' true | false: ' +state );
	  $("#txt_99").bootstrapSwitch('state', state);
	});
	
	$('#txt_99').on('switchChange.bootstrapSwitch', function(event, state) {
	  //alert(' true | false: ' +state );
	  $("#txt_30").bootstrapSwitch('state', state);
	});
	
	$('#txt_31').on('switchChange.bootstrapSwitch', function(event, state) {
	  //alert(' true | false: ' +state );
	  $("#txt_100").bootstrapSwitch('state', state);
	});
	
	$('#txt_100').on('switchChange.bootstrapSwitch', function(event, state) {
	  //alert(' true | false: ' +state );
	  $("#txt_31").bootstrapSwitch('state', state);
	});
	$('#txt_32').on('switchChange.bootstrapSwitch', function(event, state) {
	  //alert(' true | false: ' +state );
	  $("#txt_110").bootstrapSwitch('state', state);
	});
	
	$('#txt_110').on('switchChange.bootstrapSwitch', function(event, state) {
	  //alert(' true | false: ' +state );
	  $("#txt_32").bootstrapSwitch('state', state);
	});
	$('#txt_33').on('switchChange.bootstrapSwitch', function(event, state) {
	  //alert(' true | false: ' +state );
	  $("#txt_112").bootstrapSwitch('state', state);
	});
	
	$('#txt_112').on('switchChange.bootstrapSwitch', function(event, state) {
	  //alert(' true | false: ' +state );
	  $("#txt_33").bootstrapSwitch('state', state);
	});
	$('#txt_34').on('switchChange.bootstrapSwitch', function(event, state) {
	  //alert(' true | false: ' +state );
	  $("#txt_113").bootstrapSwitch('state', state);
	});
	
	$('#txt_35').on('switchChange.bootstrapSwitch', function(event, state) {
	  //alert(' true | false: ' +state );
	  $("#ana_01").bootstrapSwitch('state', state);
	});
	$('#txt_36').on('switchChange.bootstrapSwitch', function(event, state) {
	  //alert(' true | false: ' +state );
	  $("#entre_01").bootstrapSwitch('state', state);
	});
	
	$('#txt_29').on('switchChange.bootstrapSwitch', function(event, state) {
	  //alert(' true | false: ' +state );
		if(state == true){
			activeSecondtTape();
		}else{
			readOnlySecondtTape();
		}
	  
	});
	
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
	handleSelect2GetGenericCombo("txt_12",0,$('#combo_12').val(),5,0,0,5);
	handleSelect2GetGenericCombo("txt_13",0,$('#combo_13').val(),6,0,0,2);
	handleSelect2GetGenericCombo("txt_17",0,$('#combo_17').val(),8,0,0,0);
	handleSelect2GetGenericCombo("txt_18",0,$('#combo_18').val(),9,0,0,0);
	handleSelect2GetUsuario("txt_24",0,0,$('#combo_24').val()) ;
	handleSelect2GetUsuario("txt_25",0,0,$('#combo_25').val()) ;
	handleSelect2GetUsuario("txt_26",0,0,$('#combo_26').val()) ;
	
	readOnlySecondtTape();// Al inicioar desactiva recoleccion
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
//	handleSelect2GetGenericCombo("txt_39",0,$('#combo_39').val(),10,0,0,0);
//	handleSelect2GetGenericCombo("txt_40",0,$('#combo_40').val(),11,0,0,0);
//	handleSelect2GetGenericCombo("txt_41",0,$('#combo_41').val(),12,0,0,0);
	handleSelect2GetGenericCombo("txt_44",0,$('#combo_44').val(),13,0,0,3);
	handleSelect2GetGenericCombo("txt_45",0,$('#combo_45').val(),14,0,0,4);
//	handleSelect2GetGenericCombo("txt_50",0,$('#combo_50').val(),15,0,0,0);
//	handleSelect2GetGenericCombo("txt_54",0,$('#combo_54').val(),16,0,0,0);
//	handleSelect2GetGenericCombo("txt_60",0,$('#combo_60').val(),17,0,0,0);
//	handleSelect2GetGenericCombo("txt_63",0,$('#combo_63').val(),18,0,0,0);
//	handleSelect2GetGenericCombo("txt_66",0,$('#combo_66').val(),19,0,0,0);
//	handleSelect2GetGenericCombo("txt_68",0,$('#combo_68').val(),20,0,0,0);
//	handleSelect2GetGenericCombo("txt_69",0,$('#combo_69').val(),21,0,0,0);
//	handleSelect2GetGenericCombo("txt_70",0,$('#combo_70').val(),22,0,0,0);
//	handleSelect2GetGenericCombo("txt_71",0,$('#combo_71').val(),23,0,0,0);
//	handleSelect2GetGenericCombo("txt_72",0,$('#combo_72').val(),24,0,0,0);
//	handleSelect2GetUsuario("txt_80",0,0,$('#combo_80').val()) ;
//	handleSelect2GetGenericCombo("txt_87",0,$('#combo_87').val(),26,0,0,0);
//	handleSelect2GetGenericCombo("txt_97",0,$('#combo_97').val(),27,0,0,0);
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
//	handleSelect2GetGenericCombo("txt_106",0,$('#combo_106').val(),28,0,0,0);
	handleSelect2GetGenericCombo("txt_114",0,$('#combo_114').val(),29,0,0,0);
	handleSelect2GetGenericCombo("txt_115",0,$('#combo_115').val(),30,0,0,0);
//	handleSelect2GetGenericCombo("txt_123",0,$('#combo_123').val(),31,0,0,0);
//	handleSelect2GetGenericCombo("txt_124",0,$('#combo_124').val(),32,0,0,0);
//	handleSelect2GetGenericCombo("txt_129",0,$('#combo_129').val(),33,0,0,0);
//	handleSelect2GetGenericCombo("txt_130",0,$('#combo_130').val(),34,0,0,0);
//	handleSelect2GetGenericCombo("txt_136",0,$('#combo_136').val(),35,0,0,0);
//	handleSelect2GetGenericCombo("txt_141",0,$('#combo_141').val(),36,0,0,0);
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
var Estudio = function() {
	"use strict";
	return {
		init : function() {
			handleBootstrapWizardsValidation();
			initSwitch();
			//initSelect2Multi();
			//initDatePicker();
			initSelect2_1();
			initSelect2_2();
			initSelect2_3();
			initSelect2_5();
			initSelect2_6();
			intPreSetConfigForm();
			 
		}
	}
}();