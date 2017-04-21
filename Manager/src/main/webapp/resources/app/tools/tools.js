/*
Version: 1.0
Author: Sebastian Barraza
Website: Manager 2.0
Date: 04-09-2016
*/
//general function
var debugArray = function(param){
// funcion para debug atributos de un arreglo Jquery	
	$.each(param,function(index,val){
		alert('Index: '+index+'\n'+'value: '+val);
	});
}
var parseRut = function(rut, textbox){
	//Format rut 15666333-2
	$('#'+textbox).val($.formatRut2(rut));
 
}
var parseRut2 = function(rut, textbox){
	//Format rut 15.666.333-2
	$('#'+textbox).val($.formatRut2(rut));
}


//Expreciones regulares 

//Expresion regular para validar el correo
var regex = /[\w-\.]{2,}@([\w-]{2,}\.)*([\w-]{2,}\.)[\w-]{2,4}/;
//Expresion regular para validar fecha
var regexDate = /(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-][0-9]{4}/;



//Alert Error General

var errorAjaxRequest = function(data){
	if(data.status == 901){
		//modalg-restlogout
		$("#modalg-restlogout").modal("show");
		
	}else{
		$( "#modalg-error-text" ).empty();
		$('#modalg-error-text').html(data.text);
		$("#modalg-error").modal("show");
	}
	
}

//Switch boostrap

var onOffSwitch = function(combo, value){
	
	if(value == 1){
		$('#'+combo).bootstrapSwitch('state', true);
	}else{
		$('#'+combo).bootstrapSwitch('state', false);
	}
	
}

//var redirect

var windowLocationLoginRestService = function(){
	//var v1 = window.location.href; 		// Returns full URL
	var v2 = window.location.pathname; // Returns path only
	//var v3 = "/Manager/login" // Login url
	//alert('1:'+ v1 + '-- v2: '+v2 );
	
	window.location.replace(v2);
}

//Get Data Object
var getClienteGfK = function(id){
	
var result ="";
	
	var param ={
			id : id
	}
	
	$.ajax({
		url: "/Manager/RestCliente/getClienteById",
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
					text: '<center>Se ha generado un error - function getClienteGfK , Tools,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
			}
			errorAjaxRequest(data);
		}
	});
	
	return result;
	
}
var getValueComboDetalle = function(combo,id, tipo, lang){
	
	var result ="";
		
		var param ={
				id : id,
				combo:combo,
				tipo:tipo,
				lang: lang
		}
		//alert('id:'+id + ' --combo: '+combo+' --tipo: '+tipo+ ' --lang: '+lang);
		$.ajax({
			url: "/Manager/RestToolComboBox/getValueComboDetalle",
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
						text: '<center>Se ha generado un error - function getValueComboDetalle , Tools,  favor contactar al adminsitrador! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
				}
				errorAjaxRequest(data);
			}
		});
		return result;
}
//utf encode
var utf8_encode = function  (argString) { // eslint-disable-line camelcase
	  if (argString === null || typeof argString === 'undefined') {
	    return ''
	  }

	  // .replace(/\r\n/g, "\n").replace(/\r/g, "\n");
	  var string = (argString + '')
	  var utftext = ''
	  var start
	  var end
	  var stringl = 0

	  start = end = 0
	  stringl = string.length
	  for (var n = 0; n < stringl; n++) {
	    var c1 = string.charCodeAt(n)
	    var enc = null

	    if (c1 < 128) {
	      end++
	    } else if (c1 > 127 && c1 < 2048) {
	      enc = String.fromCharCode(
	        (c1 >> 6) | 192, (c1 & 63) | 128
	      )
	    } else if ((c1 & 0xF800) !== 0xD800) {
	      enc = String.fromCharCode(
	        (c1 >> 12) | 224, ((c1 >> 6) & 63) | 128, (c1 & 63) | 128
	      )
	    } else {
	      // surrogate pairs
	      if ((c1 & 0xFC00) !== 0xD800) {
	        throw new RangeError('Unmatched trail surrogate at ' + n)
	      }
	      var c2 = string.charCodeAt(++n)
	      if ((c2 & 0xFC00) !== 0xDC00) {
	        throw new RangeError('Unmatched lead surrogate at ' + (n - 1))
	      }
	      c1 = ((c1 & 0x3FF) << 10) + (c2 & 0x3FF) + 0x10000
	      enc = String.fromCharCode(
	        (c1 >> 18) | 240, ((c1 >> 12) & 63) | 128, ((c1 >> 6) & 63) | 128, (c1 & 63) | 128
	      )
	    }
	    if (enc !== null) {
	      if (end > start) {
	        utftext += string.slice(start, end)
	      }
	      utftext += enc
	      start = end = n + 1
	    }
	  }

	  if (end > start) {
	    utftext += string.slice(start, stringl)
	  }

	  return utftext
}

var utf8_decode = function(strData) { // eslint-disable-line camelcase
	  var tmpArr = []
	  var i = 0
	  var c1 = 0
	  var seqlen = 0

	  strData += ''

	  while (i < strData.length) {
	    c1 = strData.charCodeAt(i) & 0xFF
	    seqlen = 0

	    // http://en.wikipedia.org/wiki/UTF-8#Codepage_layout
	    if (c1 <= 0xBF) {
	      c1 = (c1 & 0x7F)
	      seqlen = 1
	    } else if (c1 <= 0xDF) {
	      c1 = (c1 & 0x1F)
	      seqlen = 2
	    } else if (c1 <= 0xEF) {
	      c1 = (c1 & 0x0F)
	      seqlen = 3
	    } else {
	      c1 = (c1 & 0x07)
	      seqlen = 4
	    }

	    for (var ai = 1; ai < seqlen; ++ai) {
	      c1 = ((c1 << 0x06) | (strData.charCodeAt(ai + i) & 0x3F))
	    }

	    if (seqlen === 4) {
	      c1 -= 0x10000
	      tmpArr.push(String.fromCharCode(0xD800 | ((c1 >> 10) & 0x3FF)))
	      tmpArr.push(String.fromCharCode(0xDC00 | (c1 & 0x3FF)))
	    } else {
	      tmpArr.push(String.fromCharCode(c1))
	    }

	    i += seqlen
	  }

	  return tmpArr.join('')
}
