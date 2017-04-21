/*
Version: 1.0
Author: Sebastian Barraza
Website: Manager 2.0
Date: 04-09-2016
*/
//Usuario

//Perfil
var chargePerfilCombo = function(cliente, combo1,combo2){
	//cliente: id cliente -- combo 1: combo perfil -- combo 2:  sub-division
	
	var param ={
			div : $('#'+combo2).val()
	}
	//alert('cliente:'+cliente+' div:'+param.div);
	
	$.ajax({
		url: "/Manager/RestPerfilUser/getComboPerfil/"+cliente,
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
            	combo.append("<option value='"+val.value+"'>" + val.name + "</option>");
            });
        },
		error: function(xhr, ajaxOptions, thrownError){
			//alert('Se ha generado un error - function chargePerfilCombo , MANTENEDOR USUARIOS-,  favor contactar al adminsitrador!');
			$("#modalg-charge").modal("hide");
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error - function chargePerfilCombo , MANTENEDOR USUARIOS-,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
	});
	
	
}

//Division
var chargeDivisionCombo = function(combo1){
	
	$.ajax({
		url: "/Manager/RestServiceDivision/getCombo",
		type: "GET",
		dataType: "json",
		data: false,
		beforeSend: function(){
			//cargando los datos
		},
		success: function(data){
			var combo = $('#'+combo1);
			combo.empty();
			// iteramos a través del arreglo de ciudades
			combo.append("<option value='0'>Seleccione</option>");
            $.each(data, function(index, val) {
            	combo.append("<option value='"+val.value+"'>" + val.name + "</option>");
            });
        },
		error: function(xhr, ajaxOptions, thrownError){
			//alert('Se ha generado un error - function chargeDivisionCombo , Tools - Combos,  favor contactar al adminsitrador!');
			$("#modalg-charge").modal("hide");
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error - function chargeDivisionCombo , Tools - Combos,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
	});
	
}

//Sub Division
var chargeSubDivisionCombo = function(combo1, comboDiv){
	
	var param ={
		div : $('#'+comboDiv).val()
	}
	
	$.ajax({
		url: "/Manager/RestServiceDivision/getSubDivCombo",
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
            	combo.append("<option value='"+val.value+"'>" + val.name + "</option>");
            });
        },
		error: function(xhr, ajaxOptions, thrownError){
			//alert('Se ha generado un error - function chargeDivisionCombo , Tools - Combos,  favor contactar al adminsitrador!');
			$("#modalg-charge").modal("hide");
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error - function chargeDivisionCombo , Tools - Combos,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
	});
	
}


//Area
var chargeAreaCombo = function(combo1,id){
	
	$.ajax({
		url: "/Manager/RestServiceArea/getCombo",
		type: "GET",
		dataType: "json",
		data: false,
		beforeSend: function(){
			//cargando los datos
		},
		success: function(data){
			var combo = $('#'+combo1);
			combo.empty();
			// iteramos a través del arreglo de ciudades
			combo.append("<option value='0'>Seleccione</option>");
            $.each(data, function(index, val) {
            	combo.append("<option value='"+val.value+"'>" + val.name + "</option>");
            });
            
            $('#'+combo1).val(id);
        },
		error: function(xhr, ajaxOptions, thrownError){
			//alert('Se ha generado un error - function chargeAreaCombo , Tools - Combos,  favor contactar al adminsitrador!');
			
			$("#modalg-charge").modal("hide");
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error - function chargeAreaCombo , Tools - Combos,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
	});
	
	
}
//Tipo Estudio
var chargeTipoEstudioCombo = function(combo1,id){
	
	$.ajax({
		url: "/Manager/RestServiceTipoEstudio/getCombo",
		type: "GET",
		dataType: "json",
		data: false,
		beforeSend: function(){
			//cargando los datos
		},
		success: function(data){
			var combo = $('#'+combo1);
			combo.empty();
			// iteramos a través del arreglo de ciudades
			combo.append("<option value='0'>Seleccione</option>");
            $.each(data, function(index, val) {
            	combo.append("<option value='"+val.value+"'>" + val.name + "</option>");
            });
            
            $('#'+combo1).val(id);
        },
		error: function(xhr, ajaxOptions, thrownError){
			$("#modalg-charge").modal("hide");
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error - function chargeTipoEstudioCombo , Tools - Combos,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
	});
	
	
}
//Tipo Entrevista
var chargeTipoEntrevistaCombo = function(combo1,area,id){
	
	var param = {
			id: area
	}
	
	$.ajax({
		url: "/Manager/RestServiceTipoEntrevista/getCombo",
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
            	combo.append("<option value='"+val.value+"'>" + val.name + "</option>");
            });
            
            $('#'+combo1).val(id);
        },
		error: function(xhr, ajaxOptions, thrownError){
			$("#modalg-charge").modal("hide");
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error - function chargeTipoEstudioCombo , Tools - Combos,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
	});
	
	
}
//Cargo
var chargeCargoCombo = function(combo1,combo2){
	//combo1: combo cargo -- combo2: combo sub division
	
	var param ={
			div : $('#'+combo2).val()
	}
	
	$.ajax({
		url: "/Manager/RestServiceCargo/getCombo",
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
            	combo.append("<option value='"+val.value+"'>" + val.name + "</option>");
            });
        },
		error: function(xhr, ajaxOptions, thrownError){
			//alert('Se ha generado un error - function chargeCargoCombo , Tools - Combos,  favor contactar al adminsitrador!');
			$("#modalg-charge").modal("hide");
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error - function chargeCargoCombo , Tools - Combos,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
	});
	
	
}
//Tipo contrato
var chargeTipoContratoCombo = function(combo1){
	
	$.ajax({
		url: "/Manager/RestServiceTipoContrato/getCombo",
		type: "GET",
		dataType: "json",
		data: false,
		beforeSend: function(){
			//cargando los datos
		},
		success: function(data){
			var combo = $('#'+combo1);
			combo.empty();
			// iteramos a través del arreglo de ciudades
			combo.append("<option value='0'>Seleccione</option>");
            $.each(data, function(index, val) {
            	combo.append("<option value='"+val.value+"'>" + val.name + "</option>");
            });
        },
		error: function(xhr, ajaxOptions, thrownError){
			//alert('Se ha generado un error - function chargeTipoContratoCombo , Tools - Combos,  favor contactar al adminsitrador!');
			
			$("#modalg-charge").modal("hide");
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error - function chargeTipoContratoCombo , Tools - Combos,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
	});
	
	
}
//Tipo pago
var chargeTipoPagoCombo = function(combo1){
	
	$.ajax({
		url: "/Manager/RestServiceTipoPago/getCombo",
		type: "GET",
		dataType: "json",
		data: false,
		beforeSend: function(){
			//cargando los datos
		},
		success: function(data){
			var combo = $('#'+combo1);
			combo.empty();
			// iteramos a través del arreglo de ciudades
			combo.append("<option value='0'>Seleccione</option>");
            $.each(data, function(index, val) {
            	combo.append("<option value='"+val.value+"'>" + val.name + "</option>");
            });
        },
		error: function(xhr, ajaxOptions, thrownError){
			//alert('Se ha generado un error - function chargeTipoPagoCombo , Tools - Combos,  favor contactar al adminsitrador!');
			
			$("#modalg-charge").modal("hide");
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error - function chargeTipoPagoCombo , Tools - Combos,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
	});
	
	
}

//Ubicacion
//Pais
var chargeComboPais = function(cpais){
	
	$.ajax({
		url: "/Manager/RestServiceComboUbicacion/getComboPais",
		type: "GET",
		dataType: "json",
		data: false,
		async: false,
		beforeSend: function(){
			//cargando los datos
		},
		success: function(data){
			var combo = $('#'+cpais);
			combo.empty();
			// iteramos a través del arreglo de ciudades
			combo.append("<option value='0'>Seleccione</option>");
            $.each(data, function(index, val) {
            	combo.append("<option value='"+val.value+"'>" + val.name + "</option>");
            });
            
            
        },
		error: function(xhr, ajaxOptions, thrownError){
			//alert('Se ha generado un error - function chargeTipoPagoCombo , Tools - Combos,  favor contactar al adminsitrador!');
			
			$("#modalg-charge").modal("hide");
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error - function getComboPais , Tools - Combos,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
	});
	
	
}
//Region
var chargeComboRegion = function(cregion, idpais,edit,val){
	
	var param = {
			pais : idpais
	}
	//alert('alert: '+ param.pais);
	$.ajax({
		url: "/Manager/RestServiceComboUbicacion/getComboRegion",
		type: "GET",
		dataType: "json",
		data: param,
		async: false,
		beforeSend: function(){
			//cargando los datos
		},
		success: function(data){
			var combo = $('#'+cregion);
			combo.empty();
			// iteramos a través del arreglo de ciudades
			combo.append("<option value='0'>Seleccione</option>");
            $.each(data, function(index, val) {
            	combo.append("<option value='"+val.value+"'>" + val.name + "</option>");
            });
            
            if(edit == 1){
            	$('#'+cregion).val(val);
            }
            
        },
		error: function(xhr, ajaxOptions, thrownError){
			//alert('Se ha generado un error - function chargeTipoPagoCombo , Tools - Combos,  favor contactar al adminsitrador!');
			
			$("#modalg-charge").modal("hide");
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error - function getComboRegion , Tools - Combos,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
	});
}
//Ciudad
var chargeComboCiudad = function(cciudad, idregion,edit,val){
	
	var param = {
			region : idregion
	}
	//alert('alert: '+ param.region);
	$.ajax({
		url: "/Manager/RestServiceComboUbicacion/getComboCiudad",
		type: "GET",
		dataType: "json",
		data: param,
		async: false,
		beforeSend: function(){
			//cargando los datos
		},
		success: function(data){
			var combo = $('#'+cciudad);
			combo.empty();
			// iteramos a través del arreglo de ciudades
			combo.append("<option value='0'>Seleccione</option>");
            $.each(data, function(index, val) {
            	combo.append("<option value='"+val.value+"'>" + val.name + "</option>");
            });
            
            if(edit == 1){
            	$('#'+cciudad).val(val);
            }
            
        },
		error: function(xhr, ajaxOptions, thrownError){
			//alert('Se ha generado un error - function chargeTipoPagoCombo , Tools - Combos,  favor contactar al adminsitrador!');
			
			$("#modalg-charge").modal("hide");
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error - function chargeComboCiudad , Tools - Combos,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
	});
}
//Comuna
var chargeComboComuna = function(ccomuna, idciudad,edit,val){
	
	var param = {
			ciudad : idciudad
	}
	//alert('alert: '+ param.idciudad);
	$.ajax({
		url: "/Manager/RestServiceComboUbicacion/getComboComuna",
		type: "GET",
		dataType: "json",
		data: param,
		async: false,
		beforeSend: function(){
			//cargando los datos
		},
		success: function(data){
			var combo = $('#'+ccomuna);
			combo.empty();
			// iteramos a través del arreglo de ciudades
			combo.append("<option value='0'>Seleccione</option>");
            $.each(data, function(index, val) {
            	combo.append("<option value='"+val.value+"'>" + val.name + "</option>");
            });
            
            if(edit == 1){
            	$('#'+ccomuna).val(val);
            }
        },
		error: function(xhr, ajaxOptions, thrownError){
			//alert('Se ha generado un error - function chargeTipoPagoCombo , Tools - Combos,  favor contactar al adminsitrador!');
			
			$("#modalg-charge").modal("hide");
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error - function chargeComboComuna , Tools - Combos,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
	});
}
//Usuario sistema
var handleSelect2GetUsuario = function(combo,tipo,id,place ) {
	var result ="";
	
	var param ={
			tipo : tipo,
			id : id,
			place:place
	}
	
	$.ajax({
		url: "/Manager/RestLoginUser/getCombo",
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
			//alert('Se ha generado un error - function chargeTipoPagoCombo , Tools - Combos,  favor contactar al adminsitrador!');
			
			//$("#modalg-charge").modal("hide");
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error - function handleSelect2GetUsuario , Tools - Combos,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
	});
	
	
	
	$("#"+combo).select2({
        placeholder: {
        	id: -1,
        	text : place
        }, //"Seleccione Coordinador"
        data : result
    });
	
	
	if(id != 0){
		$('#'+combo).val(id).trigger("change");
	}
	
}
//Sector sistema
var handleSelect2GetSector = function(combo,id,place) {
	var result ="";
	
	$.ajax({
		url: "/Manager/RestSector/getCombo",
		type: "GET",
		dataType: "json",
		data: false,
		async: false,
		beforeSend: function(){
			//cargando los datos
		},
		success: function(data){
			 result = data;
        },
		error: function(xhr, ajaxOptions, thrownError){
			//alert('Se ha generado un error - function chargeTipoPagoCombo , Tools - Combos,  favor contactar al adminsitrador!');
			
			//$("#modalg-charge").modal("hide");
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error - function handleSelect2GetSector , Tools - Combos,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
	});
	
	
	
	$("#"+combo).select2({
        placeholder: {
        	id: -1,
        	text : place
        }, //"Seleccione "
        data : result
    });
	
	
	if(id != 0){
		$('#'+combo).select2('val',id);
	}
	
}
//Cliente sistema
var handleSelect2GetCliente = function(combo,industria,id,place,change) {
	var result ="";
	
	var param ={
			id : industria,
			place: place
	}
	
	$.ajax({
		url: "/Manager/RestCliente/getCombo",
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
			//alert('Se ha generado un error - function chargeTipoPagoCombo , Tools - Combos,  favor contactar al adminsitrador!');
			
			//$("#modalg-charge").modal("hide");
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error - function handleSelect2GetCliente , Tools - Combos,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
	});
	
	
	
	$("#"+combo).select2({
        placeholder: {
        	id: -1,
        	text : place
        }, //"Seleccione Coordinador"
        data : result
    }).on('change', function (e) {
	    if(change == 1){
	    	
	    	var aux_id = $('#txt_05').val();
	    	
	    	$('#txt_06').select2('val',aux_id);
	    	
	    	var dataClie = getClienteGfK(aux_id);
	    	var aux_tipo = dataClie.tipo_cuenta;
	    	//alert('tipo: ' + aux_tipo);
	    	
	    	$('#aux_01').val(aux_tipo);
	    	$('#aux_04').val();
	    	
	    	//alert($('#aux_04').val());
	    	
	    	$('#txt_07').select2('val',$('#aux_01').val());
	    	
	    	$('#txt_03').val(dataClie.id_industria);
	    	$('#txt_03').trigger('change');
	    	
	    }
	});
	
	
	if(id != 0){
		$('#'+combo).select2('val',id);
	}
	
}
//Tipo Costo
var handleSelect2GetTipoCosto = function(combo,tipo,id,place ) {
	var result ="";
	
	var param ={
			tipo : tipo,
			id : id
	}
	
	$.ajax({
		url: "/Manager/RestProyectoCosto/getCombo",
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
			//alert('Se ha generado un error - function chargeTipoPagoCombo , Tools - Combos,  favor contactar al adminsitrador!');
			
			//$("#modalg-charge").modal("hide");
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error - function handleSelect2GetTipoCosto , Tools - Combos,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
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
    });
	
	if(id != 0){
		$("#"+combo).select2('val',id);
	}
	
	
}
//Lang
var handleSelect2LangManager = function(combo,id,place ) {
	var result ="";
	//alert("Combo: "+combo + " --id: "+id + " --place:"+place);
	$.ajax({
		url: "/Manager/RestServiceLang/getCombo",
		type: "GET",
		dataType: "json",
		data: false,
		async: false,
		beforeSend: function(){
			//cargando los datos
		},
		success: function(data){
			 result = data;
        },
		error: function(xhr, ajaxOptions, thrownError){
			//alert('Se ha generado un error - function chargeTipoPagoCombo , Tools - Combos,  favor contactar al adminsitrador!');
			
			//$("#modalg-charge").modal("hide");
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error - function handleSelect2LangManager , Tools - Combos,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
	});
	//$("#"+combo).select2();
	$("#"+combo).select2({
        placeholder: {
        	id: -1,
        	text : place
        }, 
        data : result
    });
	
	
	if(id != 0){
		$("#"+combo).select2('val',id);
	}
	
	
}
//Cotizacion existentes
var handleSelect2GetCotizacion2 = function(combo,tipo,id,place ) {
	var result ="";
	
	var param ={
			tipo : tipo,
			id : id
	}
	alert(tipo + " -- "+ id);
	$.ajax({
		url: "/Manager/RestCotizacion/getCombo",
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
			//alert('Se ha generado un error - function chargeTipoPagoCombo , Tools - Combos,  favor contactar al adminsitrador!');
			
			//$("#modalg-charge").modal("hide");
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error - function handleSelect2GetCotizacion , Tools - Combos,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
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
    });
	
	if(id != 0){
		$("#"+combo).select2('val',id);
	}
}

//Industria
var handleSelect2GetIndustria = function(combo,id,place ) {
	var result ="";
	
	var param ={
			lang : $('#lang_01').val(),
			place : place
	}
	
	$.ajax({
		url: "/Manager/RestIndustria/getCombo",
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
			//alert('Se ha generado un error - function chargeTipoPagoCombo , Tools - Combos,  favor contactar al adminsitrador!');
			
			//$("#modalg-charge").modal("hide");
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error - function handleSelect2GetIndustria , Tools - Combos,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
	});
	
	
	
	$("#"+combo).select2({
		tags: "true",
		placeholder: {
        	id: -1,
        	text : place
        },
        data : result
    });
	
	if(id != 0){
		//$("#"+combo).select2('val',id);
		$("#"+combo).val(id).trigger("change");
	}
}
//Producto
var handleSelect2GetProducto = function(combo,id,place ) {
	var result ="";
	
	var param ={
			lang : $('#lang_01').val()
	}
	
	$.ajax({
		url: "/Manager/RestProducto/getCombo",
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
			//alert('Se ha generado un error - function chargeTipoPagoCombo , Tools - Combos,  favor contactar al adminsitrador!');
			
			//$("#modalg-charge").modal("hide");
			var data = {
					status: xhr.status,
					text: '<center>Se ha generado un error - function handleSelect2GetProducto , Tools - Combos,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
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
    });
	
	if(id != 0){
		$("#"+combo).select2('val',id);
	}
}
//Combo Box Select 2 Normal standar
var handleSelect2GetGenericCombo = function(combo,idSelected,place,idcombo,depende,comboDepende,change ) {
	var result ="";
	
	var param ={
			lang : $('#lang_01').val(),
			id: idcombo,
			depende: depende,
			place: place,
			comboDepende:comboDepende
	}
	
	$.ajax({
		url: "/Manager/RestToolComboBox/getCombo",
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
					text: '<center>Se ha generado un error - function handleSelect2GetGenericCombo , Tools - Combos,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
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
	    /*
	     * change 1 = geografia
	     * change 2 = Booking legal entity;
	     * change 3 = Modo
	     * change 4 = Sub Modo
	     * change 5 = Tipo Entrevista
	     * change 41 = Frecuencia Ola
	     */
    	
    	if(change == 1){
	    	
	    	var aux_value = $('#txt_09').val();
	    	
	    	if(aux_value == "3"){
	    		$('#group_01').show();
	    	}else{
	    		$('#group_01').hide();
	    	}
	    }else if(change == 2){
	    	//alert(2);
	    	
	    }else if(change == 3){
	    	//alert(2);
	    	$('#txt_196').val($('#txt_44 option:selected').text());
	    }else if(change == 4){
	    	//alert(2);
	    	$('#txt_197').val($('#txt_45 option:selected').text());
	    }else if(change == 5){
	    	//alert(5);
	    	var entre = $('#'+combo).val();
	    	//alert(entre);
	    	if(entre == 4 || entre == 5 || entre == 16  || entre == 3){
	    		$('#txt_32').bootstrapSwitch('disabled',true);
	    		//$("#txt_110").bootstrapSwitch('state', true);
	    	}else{
	    		$('#txt_32').bootstrapSwitch('disabled',false);
	    	}
	    	if(entre == 3){
	    		//SI ES CAWI
	    		//$('#txt_32').bootstrapSwitch('disabled',true);
	    		//$("#txt_110").bootstrapSwitch('state', true);
	    	}else{
	    		//$('#txt_32').bootstrapSwitch('disabled',false);
	    	}
	    	
	    	$('#txt_191').val($('#txt_12 option:selected').text());
	    }else if(change == 40){
	    	if($('#'+combo).val()== 2 || $('#'+combo).val()== 3 || $('#'+combo).val()== 4){
	    		$('#gtxt_41').show();
	    		$('#gtxt_43').show();
	    	}else{
	    		$('#gtxt_41').hide();
	    		$('#gtxt_43').hide();
	    	}
	    }else if(change == 41){
	    	if($('#'+combo).val()== 5){
	    		$('#txt_42').show();
	    	}else{
	    		$('#txt_42').hide();
	    	}
	    }
	});
	
	if(idSelected != 0){
		$('#'+combo).val(idSelected).trigger("change");
		//$("#"+combo).select2('val',idSelected);
	}
	
}
//########################################################################################################################################
//################################################################################
//Estudio
//Cotizacion
//Combo Box Select 2 Normal standar
var handleSelect2GetCotizacion = function(combo,idSelected,place,idCliente ) {
	var result ="";
	
	var param ={
			id: idCliente,
			place: place
	}
	//alert(idCliente + " -- "+ place);
	$.ajax({
		url: "/Manager/RestCotizacion/getCombo",
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
					text: '<center>Se ha generado un error - function handleSelect2GetCotizacion , Tools - Combos,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
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
    });
	
	if(idSelected != 0){
		$("#"+combo).select2('val',idSelected);
	}
}
