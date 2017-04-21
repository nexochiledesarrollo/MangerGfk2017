/*

Version: 1.0
Author: sbarraza
Website: Manager
Date: 20-09-2016 
 
 */

 //-------------------------------------------------------------------------------

 ///// ---------------------------------- FUNCIONES LUIS --------------------------------------
var createInstructivo = function(){
	$("#error-newlogin").hide();
	
	            var c6 = '';
			    $("input[name=txt_man_muest_06]").each(function (index) { 
			       if($(this).is(':checked')){
			          c6 = $(this).val();
			       }
			    });
			    
			    
                var c7 = 0;
                
			    if (c6 ==2){ 
	                c6=true;
				    $("input[name=txt_man_muest_07]").each(function (index) { 
				       if($(this).is(':checked')){
				          c7 = $(this).val();
				       }
				    });
				 }else{
					c6=false;
				 }

    var param = {
                 
            id_oper : $('#txt_idope_1').val(),
            
	        // Informacion del Trabajo 
			rep_ola : $('#txt_inf_trab_01').bootstrapSwitch('state').toString(),
			carta : $('#txt_inf_trab_02').bootstrapSwitch('state').toString(),
	        loi : $('#txt_inf_trab_03').val(),
			incidencia : $('#txt_inf_trab_04').val(),
			tasa : $('#txt_inf_trab_05').val(),
			//Incentivo
	        
			incent_dinero:$('#txt_incent_01').bootstrapSwitch('state').toString(),
			incent_voucher:$('#txt_incent_02').bootstrapSwitch('state').toString(),
			incent_regalo:$('#txt_incent_03').bootstrapSwitch('state').toString(),
			incent_especif:$('#txt_incent_04').val(),
		        
			//VistaGeneral
            vista_1:$('#txt_vista_01').val(),
			vista_2:$('#txt_vista_02').val(),
			//Metodo Recoleccion
			
			
			que_papi:$('#txt_detalle_metodo_que_01').bootstrapSwitch('state').toString(),
			que_capi:$('#txt_detalle_metodo_que_02').bootstrapSwitch('state').toString(),
			que_movil:$('#txt_detalle_metodo_que_03').bootstrapSwitch('state').toString(),
			que_mixto:$('#txt_detalle_metodo_que_04').bootstrapSwitch('state').toString(),
			que_otro:$('#txt_detalle_metodo_que_05').val(),
			

			donde_casa:$('#txt_detalle_metodo_donde_01').bootstrapSwitch('state').toString(),
			donde_central:$('#txt_detalle_metodo_donde_02').bootstrapSwitch('state').toString(),
			donde_calle:$('#txt_detalle_metodo_donde_03').bootstrapSwitch('state').toString(),
			donde_tienda:$('#txt_detalle_metodo_donde_04').bootstrapSwitch('state').toString(),
			donde_otro:$('#txt_detalle_metodo_donde_05').val(),
			

			//Grupo Objetivo
            grupo_1:$('#txt_grupo_01').val(),
			grupo_2:$('#txt_grupo_02').val(),
			
			//Questionnaire
            estructura:$('#txt_quest_01').val(),
			glosario:$('#txt_quest_02').val(),
			
			//Questionnaire PAPI
            quest_papi1:$('#txt_quest_papi_01').val(),
			quest_papi2:$('#txt_quest_papi_02').val(),

			//Questionnaire CAPI
			quest_capi1:$('#txt_quest_capi_01').val(),
			quest_capi2:$('#txt_quest_capi_02').val(),
			quest_capi3:$('#txt_quest_capi_03').val(),

			//cuota
			cuota1:$('#txt_man_muest_01').val(),
			cuota2:$('#txt_man_muest_02').bootstrapSwitch('state').toString(),
			cuota3:$('#txt_man_muest_03').val(),
			cuota4:$('#txt_man_muest_04').val(),
			cuota5:$('#txt_man_muest_05').val(),
			cuota6:c6,
			cuota7:c7,
			
			
            //Plazos
			t_campo_desde:$('#txt_start').val(),
            t_campo_hasta:$('#txt_end').val(),
			p_temporal:$('#txt_p_temporal').val(),
			p_final:$('#txt_p_final').val(),
			desc_obj:$('#txt_plazo_05').val(),
			dia_mes:$('#txt_p_interm').val(),
			planificacion:$('#txt_plazo_07').val(),
          
		    //Actualizaciones de estado PAPI

			txt_plazo_papi_01:$('#txt_plazo_PAPI_01').val(),
			txt_plazo_papi_02:$('#txt_plazo_PAPI_02').val(),
			txt_plazo_papi_03:$('#txt_plazo_PAPI_03').val(),
			txt_plazo_papi_04:$('#txt_plazo_PAPI_04').val(),
			

            //Actualizaciones de estado CAPI

			txt_plazo_capi_01:$('#txt_plazo_CAPI_01').val(),
			txt_plazo_capi_02:$('#txt_plazo_CAPI_02').val(),
			txt_plazo_capi_03:$('#txt_plazo_CAPI_03').val(),
			

			//Remuneracion

			txt_remun_01:$('#txt_remun_01').val(),
			txt_remun_02:$('#txt_remun_02').val(),
			
			//Pack

			txt_pack_01:$('#txt_pack_01').val(),
		
			//Contacto
			
			txt_contacto_01:$('#txt_contacto_01').val(),
			txt_contacto_02:$('#txt_contacto_02').val(),
			txt_contacto_03:$('#txt_contacto_03').val(),
			txt_contacto_04:$('#txt_contacto_04').val(),
			txt_contacto_05:$('#txt_contacto_05').val()

	}
	

		$.ajax({
			url: "/Manager/RestInstructivo/crearInstructivo",
			type: "GET",
			dataType: "json",
			data: param,
			beforeSend: function(){
				//cargando los datos
				$("#modal-create").modal("hide");
				$("#modalg-charge").modal("show");
			},
			success: function(data){
				$("#modalg-charge").modal("hide");
				$("#modalg-success-text" ).empty();
				$('#modalg-success-text').html('<center>'+ data.text +'</center><br>');
				$("#modalg-success").modal("show");
				
				setTimeout(irPrincipal(),5000);
				
				//eraseForm();
				
				//$('#data-table').DataTable().ajax.reload();
			
			},
			error: function (xhr, ajaxOptions, thrownError) {
		       // alert(xhr.status);
		       // alert(thrownError);
		       //alert('Se ha generado un error -- setUserLogin Modulo Usuario -- ,  favor contactar al adminsitrador!');
				$("#modalg-charge").modal("hide");
				var data = {
						status: xhr.status,
						text: '<center>Se ha generado un error: <strong>-- setUserLogin Modulo Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
				}
				errorAjaxRequest(data);
			}
		});
	
	
}



var createInstructivoCati = function(){
	$("#error-newlogin").hide();
	
	            var c14 = '';
			    $("input[name=txt_man_muest_14_cati]").each(function (index) { 
			       if($(this).is(':checked')){
			          c14 = $(this).val();
			       }
			    });
			    
			    
                var c15 = 0;
                
			    if (c14 ==2){ 
	                c14=true;
				    $("input[name=txt_man_muest_15_cati]").each(function (index) { 
				       if($(this).is(':checked')){
				          c15 = $(this).val();
				       }
				    });
				 }else{
					c14=false;
				 }
			   

    var param = {
                 
            id_oper : $('#txt_idope_1').val(),
            
	        // Informacion del Trabajo 
            nombre_guion : $('#txt_inf_trab_01_cati').val(),
            nombre_servidor : $('#txt_inf_trab_02_cati').val(),
	        tasa : $('#txt_inf_trab_03_cati').val(),
            paises : $('#txt_inf_trab_04_cati').val(),
			loi : $('#txt_inf_trab_05_cati').val(),
			incidencia : $('#txt_inf_trab_06_cati').val(),
            rep_ola : $('#txt_inf_trab_07_cati').bootstrapSwitch('state').toString(),
			carta : $('#txt_inf_trab_08_cati').bootstrapSwitch('state').toString(),
	        
			//Incentivo

			incent_dinero : $('#txt_incent_01').bootstrapSwitch('state').toString(),
			incent_voucher : $('#txt_incent_02').bootstrapSwitch('state').toString(),
			incent_regalo : $('#txt_incent_03').bootstrapSwitch('state').toString(),
			incent_especif : $('#txt_incent_04').val(),
			realiza_cita : $('#txt_incent_05').bootstrapSwitch('state').toString(),
			coment_incent : $('#txt_incent_06').val(),
			

			//VistaGeneral
            vista_1:$('#txt_vista_01_cati').val(),
			vista_2:$('#txt_vista_02_cati').val(),
			
			//Metodo Recoleccion
			
			B2B : $('#txt_detalle_metodo_que_01_cati').bootstrapSwitch('state').toString(),
			B2C : $('#txt_detalle_metodo_que_03_cati').bootstrapSwitch('state').toString(),
			Porc_B2B : $('#txt_detalle_metodo_que_02_cati').val(),
			Porc_B2C : $('#txt_detalle_metodo_que_04_cati').val(),
			
			Porc_B2B : $('#txt_detalle_metodo_que_02_cati').val(),
			Porc_B2C : $('#txt_detalle_metodo_que_04_cati').val(),
			
			met_mixto : $('#txt_detalle_metodo_que_05_cati').val(),
			coment : $('#txt_detalle_metodo_que_06_cati').val(),
						
			//Grupo Objetivo
            grupo_1:$('#txt_grupo_01').val(),
			grupo_2:$('#txt_grupo_02').val(),
			
			//Questionnaire
            estructura:$('#txt_quest_01').val(),
			glosario:$('#txt_quest_02').val(),
			

			//cuota

			muestra_ent:$('#txt_man_muest_01_cati').bootstrapSwitch('state').toString(),
			especif:$('#txt_man_muest_02_cati').val(),
			rdd:$('#txt_man_muest_03_cati').bootstrapSwitch('state').toString(),
			muestra_cliente:$('#txt_man_muest_04_cati').bootstrapSwitch('state').toString(),
			base_datos:$('#txt_man_muest_05_cati').bootstrapSwitch('state').toString(),
			otro:$('#txt_man_muest_06_cati').bootstrapSwitch('state').toString(),
			especif_otro:$('#txt_man_muest_07_cati').val(),
			
			nombre_contact:$('#txt_man_muest_08_cati').bootstrapSwitch('state').toString(),
			porc_nombre:$('#txt_man_muest_09_cati').val(),

			permit_recom:$('#txt_man_muest_10_cati').bootstrapSwitch('state').toString(),
			especif_recom:$('#txt_man_muest_11_cati').val(),
			
			n_recom:$('#txt_man_muest_12_cati').val(),
			coment_adic:$('#txt_man_muest_13_cati').val(),
			
			cuota14:c14,
			cuota15:c15,
			
			//Instruccion Para el Supervisor

			inst_sup1:$('#txt_inst_sup_01_cati').val(),
			inst_sup2:$('#txt_inst_sup_02_cati').val(),


            //Plazos
			t_campo_desde:$('#txt_start').val(),
            t_campo_hasta:$('#txt_end').val(),
			p_temporal:$('#txt_p_temporal').val(),
			p_final:$('#txt_p_final').val(),
			desc_obj:$('#txt_plazo_05').val(),
			dia_mes:$('#txt_p_interm').val(),
			planificacion:$('#txt_plazo_07').val(),

			//Remuneracion

			txt_remun_01:$('#txt_remun_01_cati').val(),
			
		   

	}
	

		$.ajax({
			url: "/Manager/RestInstructivo/crearInstructivoCati",
			type: "GET",
			dataType: "json",
			data: param,
			beforeSend: function(){
				//cargando los datos
				$("#modal-create").modal("hide");
				$("#modalg-charge").modal("show");
			},
			success: function(data){
				$("#modalg-charge").modal("hide");
				$("#modalg-success-text" ).empty();
				$('#modalg-success-text').html('<center>'+ data.text +'</center><br>');
				$("#modalg-success").modal("show");
				
				
				setTimeout(irPrincipal(),5000);

				//eraseForm();
				
				//$('#data-table').DataTable().ajax.reload();
			
			},
			error: function (xhr, ajaxOptions, thrownError) {
//		        alert(xhr.status);
//		        alert(thrownError);
		       //alert('Se ha generado un error -- setUserLogin Modulo Usuario -- ,  favor contactar al adminsitrador!');
				$("#modalg-charge").modal("hide");
				var data = {
						status: xhr.status,
						text: '<center>Se ha generado un error: <strong>-- setUserLogin Modulo Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
				}
				errorAjaxRequest(data);
			}
		});
	
	
}

function irPrincipal(){
	window.location.href = "/Manager/workflowEtapa1/Instructivo";
}





var updateInstructivoCati = function(){
	$("#error-newlogin").hide();
	
	            var c14 = '';
			    $("input[name=txt_man_muest_14_cati]").each(function (index) { 
			       if($(this).is(':checked')){
			          c14 = $(this).val();
			       }
			    });
			    
			    
                var c15 = 0;
                
			    if (c14 ==2){ 
	                c14=true;
				    $("input[name=txt_man_muest_15_cati]").each(function (index) { 
				       if($(this).is(':checked')){
				          c15 = $(this).val();
				       }
				    });
				 }else{
					c14=false;
				 }
			   

    var param = {
                 
            id_oper : $('#txt_idope_1').val(),
            
	        // Informacion del Trabajo 
            nombre_guion : $('#txt_inf_trab_01_cati').val(),
            nombre_servidor : $('#txt_inf_trab_02_cati').val(),
	        tasa : $('#txt_inf_trab_03_cati').val(),
            paises : $('#txt_inf_trab_04_cati').val(),
			loi : $('#txt_inf_trab_05_cati').val(),
			incidencia : $('#txt_inf_trab_06_cati').val(),
            rep_ola : $('#txt_inf_trab_07_cati').bootstrapSwitch('state').toString(),
			carta : $('#txt_inf_trab_08_cati').bootstrapSwitch('state').toString(),
	        
			//Incentivo

			incent_dinero : $('#txt_incent_01').bootstrapSwitch('state').toString(),
			incent_voucher : $('#txt_incent_02').bootstrapSwitch('state').toString(),
			incent_regalo : $('#txt_incent_03').bootstrapSwitch('state').toString(),
			incent_especif : $('#txt_incent_04').val(),
			realiza_cita : $('#txt_incent_05').bootstrapSwitch('state').toString(),
			coment_incent : $('#txt_incent_06').val(),
			

			//VistaGeneral
            vista_1:$('#txt_vista_01_cati').val(),
			vista_2:$('#txt_vista_02_cati').val(),
			
			//Metodo Recoleccion
			
			B2B : $('#txt_detalle_metodo_que_01_cati').bootstrapSwitch('state').toString(),
			B2C : $('#txt_detalle_metodo_que_03_cati').bootstrapSwitch('state').toString(),
			Porc_B2B : $('#txt_detalle_metodo_que_02_cati').val(),
			Porc_B2C : $('#txt_detalle_metodo_que_04_cati').val(),
			
			Porc_B2B : $('#txt_detalle_metodo_que_02_cati').val(),
			Porc_B2C : $('#txt_detalle_metodo_que_04_cati').val(),
			
			met_mixto : $('#txt_detalle_metodo_que_05_cati').val(),
			coment : $('#txt_detalle_metodo_que_06_cati').val(),
						
			//Grupo Objetivo
            grupo_1:$('#txt_grupo_01').val(),
			grupo_2:$('#txt_grupo_02').val(),
			
			//Questionnaire
            estructura:$('#txt_quest_01').val(),
			glosario:$('#txt_quest_02').val(),
			

			//cuota

			muestra_ent:$('#txt_man_muest_01_cati').bootstrapSwitch('state').toString(),
			especif:$('#txt_man_muest_02_cati').val(),
			rdd:$('#txt_man_muest_03_cati').bootstrapSwitch('state').toString(),
			muestra_cliente:$('#txt_man_muest_04_cati').bootstrapSwitch('state').toString(),
			base_datos:$('#txt_man_muest_05_cati').bootstrapSwitch('state').toString(),
			otro:$('#txt_man_muest_06_cati').bootstrapSwitch('state').toString(),
			especif_otro:$('#txt_man_muest_07_cati').val(),
			
			nombre_contact:$('#txt_man_muest_08_cati').bootstrapSwitch('state').toString(),
			porc_nombre:$('#txt_man_muest_09_cati').val(),

			permit_recom:$('#txt_man_muest_10_cati').bootstrapSwitch('state').toString(),
			especif_recom:$('#txt_man_muest_11_cati').val(),
			
			n_recom:$('#txt_man_muest_12_cati').val(),
			coment_adic:$('#txt_man_muest_13_cati').val(),
			
			cuota14:c14,
			cuota15:c15,
			
			//Instruccion Para el Supervisor

			inst_sup1:$('#txt_inst_sup_01_cati').val(),
			inst_sup2:$('#txt_inst_sup_02_cati').val(),


            //Plazos
			t_campo_desde:$('#txt_start').val(),
            t_campo_hasta:$('#txt_end').val(),
			p_temporal:$('#txt_p_temporal').val(),
			p_final:$('#txt_p_final').val(),
			desc_obj:$('#txt_plazo_05').val(),
			dia_mes:$('#txt_p_interm').val(),
			planificacion:$('#txt_plazo_07').val(),

			//Remuneracion

			txt_remun_01:$('#txt_remun_01_cati').val(),
			
		   

	}
	

		$.ajax({
			url: "/Manager/RestInstructivo/updateInstructivoCati",
			type: "GET",
			dataType: "json",
			data: param,
			beforeSend: function(){
				//cargando los datos
				$("#modal-create").modal("hide");
				$("#modalg-charge").modal("show");
			},
			success: function(data){
				$("#modalg-charge").modal("hide");
				$("#modalg-success-text" ).empty();
				$('#modalg-success-text').html('<center>'+ data.text +'</center><br>');
				$("#modalg-success").modal("show");
				
				setTimeout(irPrincipal(),5000);
				
				
				
				//eraseForm();
				
				//$('#data-table').DataTable().ajax.reload();
			
			},
			error: function (xhr, ajaxOptions, thrownError) {
//		        alert(xhr.status);
//		        alert(thrownError);
		       //alert('Se ha generado un error -- setUserLogin Modulo Usuario -- ,  favor contactar al adminsitrador!');
				$("#modalg-charge").modal("hide");
				var data = {
						status: xhr.status,
						text: '<center>Se ha generado un error: <strong>-- setUserLogin Modulo Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
				}
				errorAjaxRequest(data);
			}
		});
	
	
}



var updateInstructivo = function(){
	$("#error-newlogin").hide();
	
	            var c6 = '';
			    $("input[name=txt_man_muest_06]").each(function (index) { 
			       if($(this).is(':checked')){
			          c6 = $(this).val();
			       }
			    });
			    
			    
                var c7 = 0;
                
			    if (c6 ==2){ 
	                c6=true;
				    $("input[name=txt_man_muest_07]").each(function (index) { 
				       if($(this).is(':checked')){
				          c7 = $(this).val();
				       }
				    });
				 }else{
					c6=false;
				 }

    var param = {
                 
            id_oper : $('#txt_idope_1').val(),
            
	        // Informacion del Trabajo 
			rep_ola : $('#txt_inf_trab_01').bootstrapSwitch('state').toString(),
			carta : $('#txt_inf_trab_02').bootstrapSwitch('state').toString(),
	        loi : $('#txt_inf_trab_03').val(),
			incidencia : $('#txt_inf_trab_04').val(),
			tasa : $('#txt_inf_trab_05').val(),
			//Incentivo
	        
			incent_dinero:$('#txt_incent_01').bootstrapSwitch('state').toString(),
			incent_voucher:$('#txt_incent_02').bootstrapSwitch('state').toString(),
			incent_regalo:$('#txt_incent_03').bootstrapSwitch('state').toString(),
			incent_especif:$('#txt_incent_04').val(),
		        
			//VistaGeneral
            vista_1:$('#txt_vista_01').val(),
			vista_2:$('#txt_vista_02').val(),
			//Metodo Recoleccion
			
			
			que_papi:$('#txt_detalle_metodo_que_01').bootstrapSwitch('state').toString(),
			que_capi:$('#txt_detalle_metodo_que_02').bootstrapSwitch('state').toString(),
			que_movil:$('#txt_detalle_metodo_que_03').bootstrapSwitch('state').toString(),
			que_mixto:$('#txt_detalle_metodo_que_04').bootstrapSwitch('state').toString(),
			que_otro:$('#txt_detalle_metodo_que_05').val(),
			

			donde_casa:$('#txt_detalle_metodo_donde_01').bootstrapSwitch('state').toString(),
			donde_central:$('#txt_detalle_metodo_donde_02').bootstrapSwitch('state').toString(),
			donde_calle:$('#txt_detalle_metodo_donde_03').bootstrapSwitch('state').toString(),
			donde_tienda:$('#txt_detalle_metodo_donde_04').bootstrapSwitch('state').toString(),
			donde_otro:$('#txt_detalle_metodo_donde_05').val(),
			

			//Grupo Objetivo
            grupo_1:$('#txt_grupo_01').val(),
			grupo_2:$('#txt_grupo_02').val(),
			
			//Questionnaire
            estructura:$('#txt_quest_01').val(),
			glosario:$('#txt_quest_02').val(),
			
			//Questionnaire PAPI
            quest_papi1:$('#txt_quest_papi_01').val(),
			quest_papi2:$('#txt_quest_papi_02').val(),

			//Questionnaire CAPI
			quest_capi1:$('#txt_quest_capi_01').val(),
			quest_capi2:$('#txt_quest_capi_02').val(),
			quest_capi3:$('#txt_quest_capi_03').val(),

			//cuota
			cuota1:$('#txt_man_muest_01').val(),
			cuota2:$('#txt_man_muest_02').bootstrapSwitch('state').toString(),
			cuota3:$('#txt_man_muest_03').val(),
			cuota4:$('#txt_man_muest_04').val(),
			cuota5:$('#txt_man_muest_05').val(),
			cuota6:c6,
			cuota7:c7,
			
			
            //Plazos
			t_campo_desde:$('#txt_start').val(),
            t_campo_hasta:$('#txt_end').val(),
			p_temporal:$('#txt_p_temporal').val(),
			p_final:$('#txt_p_final').val(),
			desc_obj:$('#txt_plazo_05').val(),
			dia_mes:$('#txt_p_interm').val(),
			planificacion:$('#txt_plazo_07').val(),
          
		    //Actualizaciones de estado PAPI

			txt_plazo_papi_01:$('#txt_plazo_PAPI_01').val(),
			txt_plazo_papi_02:$('#txt_plazo_PAPI_02').val(),
			txt_plazo_papi_03:$('#txt_plazo_PAPI_03').val(),
			txt_plazo_papi_04:$('#txt_plazo_PAPI_04').val(),
			

            //Actualizaciones de estado CAPI

			txt_plazo_capi_01:$('#txt_plazo_CAPI_01').val(),
			txt_plazo_capi_02:$('#txt_plazo_CAPI_02').val(),
			txt_plazo_capi_03:$('#txt_plazo_CAPI_03').val(),
			

			//Remuneracion

			txt_remun_01:$('#txt_remun_01').val(),
			txt_remun_02:$('#txt_remun_02').val(),
			
			//Pack

			txt_pack_01:$('#txt_pack_01').val(),
		
			//Contacto
			
			txt_contacto_01:$('#txt_contacto_01').val(),
			txt_contacto_02:$('#txt_contacto_02').val(),
			txt_contacto_03:$('#txt_contacto_03').val(),
			txt_contacto_04:$('#txt_contacto_04').val(),
			txt_contacto_05:$('#txt_contacto_05').val()
			


	}
	

		$.ajax({
			url: "/Manager/RestInstructivo/updateInstructivo",
			type: "GET",
			dataType: "json",
			data: param,
			beforeSend: function(){
				//cargando los datos
				$("#modal-create").modal("hide");
				$("#modalg-charge").modal("show");
			},
			success: function(data){
				$("#modalg-charge").modal("hide");
				$("#modalg-success-text" ).empty();
				$('#modalg-success-text').html('<center>'+ data.text +'</center><br>');
				$("#modalg-success").modal("show");
				
				setTimeout(irPrincipal(),5000);
				
				//eraseForm();
				
				//$('#data-table').DataTable().ajax.reload();
			
			},
			error: function (xhr, ajaxOptions, thrownError) {
		       // alert(xhr.status);
		       // alert(thrownError);
		       //alert('Se ha generado un error -- setUserLogin Modulo Usuario -- ,  favor contactar al adminsitrador!');
				$("#modalg-charge").modal("hide");
				var data = {
						status: xhr.status,
						text: '<center>Se ha generado un error: <strong>-- setUserLogin Modulo Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
				}
				errorAjaxRequest(data);
			}
		});
	
	
}








var detailInstructivoCati = function(id){
	var result = null;
	$.ajax({
		url: "/Manager/RestInstructivo/getDetailInstructivoCatiById/"+id,
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
				var data = {
						status: xhr.status,
						text: '<center>Se ha generado un error: <strong>-- detailLogin Modulo Usuario --</strong> <br/>  Favor contactar mesa de ayuda! </center><br> STATUS: '+xhr.status + '<br/> ERROR: '+thrownError +'<br/>Detail: '+xhr.responseText
				}
				errorAjaxRequest(data);
		}
		
	});
	
	return result;
	
}




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
			
			//$( "#h1_1" ).empty();
			//$('#h1_1').addClass('text-warning');
			//$('#h1_1').html(data.id_operacion + "-" + data.codigo_cotizacion + " :: "+ data.nombre_operacion);
			$('#txt_ok_02').val(data.id_operacion + "-" + data.codigo_cotizacion);
			$('#txt_ok_01').val(data.nombre_operacion);
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

var dataSpickers1 = function(){
	$("#txt_p_temporal").datepicker({
        todayHighlight: true,
        format: 'dd-mm-yyyy',
	    autoclose:true,
        lang:'es'
    });
} 

var dataSpickers2 = function(){
	$("#txt_p_final").datepicker({
        todayHighlight: true,
        format: 'dd-mm-yyyy',
	    autoclose:true,
        lang:'es'
    });
} 

var dataSpickers3 = function(){
	$("#txt_p_interm").datepicker({
        todayHighlight: true,
        format: 'dd-mm',
	    autoclose:true,
	    lang:'es'
    });
} 


var showTipoEncuesta= function() {
    
	 $('#inf_trabajo').hide();
	 $('#inf_trabajo_cati').hide();
	 $('#vista_general').hide();
	 $('#vista_general_cati').hide();
	 $('#met_rec_data_todos').hide();
	 $('#met_rec_data_cati').hide();
	 $('#cuest_papi').hide();
	 $('#cuest_capi').hide();
	 
	 $('#man_muestra_cati').hide();
	 $('#man_muestra').hide();
	 $('#plazos_papi').hide();
	 $('#plazos_capi').hide();
	 $('#etiqueta_plazos').hide();
	 $('#inst_supervisor').hide();
	 $('#remuneracion').hide();
	 $('#pack').hide();
	 $('#contacto').hide();
	 $('#remuneracion_cati').hide();

	


	 
if($('#tipo_entrevista').val() == '2'){
		
		$('#inf_trabajo_cati').show();
		$('#vista_general_cati').show();
		$('#met_rec_data_cati').show();
		$('#man_muestra_cati').show();
		$('#inst_supervisor').show();
		$('#remuneracion_cati').show();
		$('#form_btn_ok_cati').show();
		$('#realiza_cita_cati').show();

		
	}else{

		$('#inf_trabajo').show();
		$('#vista_general').show();
		$('#met_rec_data_todos').show();
		$('#cuest_papi').show();
		$('#cuest_capi').show();
		$('#man_muestra').show();
		$('#plazos_papi').show();
		$('#plazos_capi').show();
		$('#etiqueta_plazos').show();
		$('#remuneracion').show();
		$('#pack').show();
		$('#contacto').show();
		$('#form_btn_ok').show();

	};
}




function showRevelar() {
	
     var r1='';
     $("input[name=txt_man_muest_06]").each(function (index) { 
		if($(this).is(':checked')){
			 r1 = $(this).val();
		}

	})
     
      if (r1==2){
    	 
    	  $('#form_si').show();
      }else{
    	  
    	  $('#form_si').hide();
      }
      
      
      
;}

function showRevelarCati() {
	
     var r1='';
     $("input[name=txt_man_muest_14_cati]").each(function (index) { 
		if($(this).is(':checked')){
			 r1 = $(this).val();
		}

	})
     
      if (r1==2){
    	 
    	  $('#form_si_cati').show();
      }else{
    	 
    	  $('#form_si_cati').hide();
      }
      
      
      
;}




function showReconedadosCati() {

	if ($('#txt_man_muest_10_cati').bootstrapSwitch('state')){
		$('#recomend_cati').show();
      }else{
    	  $('#txt_man_muest_11_cati').val('');
	      $('#txt_man_muest_12_cati').val('');
	      $('#txt_man_muest_13_cati').val('');
	      $('#recomend_cati').hide();
      }

;}





var getDetailInstructivoCati = function(){
	var param = {
	    id : $('#txt_idope_1').val(),
	}
	
	$.ajax({
		url: "/Manager/RestInstructivo/getDetailInstructivoCatiById/"+ $('#txt_idope_1').val(),
		type: "GET",
		dataType: "json",
		data: param,
		async: false,
		beforeSend: function(){
			//cargando los datos
			//$("#modalg-charge").modal("show");
		},
		success: function(data){
			//***********************************
			//var detalle = data.detalle;
			//**********************************
		
			
          $('#txt_inf_trab_01_cati').val(data.nombre_guion);
          $('#txt_inf_trab_02_cati').val(data.nombre_servidor);
          $('#txt_inf_trab_03_cati').val(data.tasa),
          $('#txt_inf_trab_04_cati').val(data.paises),
	      $('#txt_inf_trab_05_cati').val(data.loi),
		  $('#txt_inf_trab_06_cati').val(data.incidencia),
		  $('#txt_inf_trab_07_cati').bootstrapSwitch('state',data.rep_ola),
		  $('#txt_inf_trab_08_cati').bootstrapSwitch('state',data.carta),
          $('#txt_incent_01').bootstrapSwitch('state',data.incent_dinero),
		  $('#txt_incent_02').bootstrapSwitch('state',data.incent_voucher),
		  $('#txt_incent_03').bootstrapSwitch('state',data.incent_regalo),
		  $('#txt_incent_04').val(data.incent_especif),
		  $('#txt_incent_05').bootstrapSwitch('state',data.realiza_cita),
		  $('#txt_incent_06').val(data.coment_incent),
          //VistaGeneral
          $('#txt_vista_01_cati').val(data.vista_1),
	      $('#txt_vista_02_cati').val(data.vista_2),
			
			//Metodo Recoleccion

		
			
			 $('#txt_detalle_metodo_que_01_cati').bootstrapSwitch('state',data.b2B),
			 $('#txt_detalle_metodo_que_03_cati').bootstrapSwitch('state',data.b2C),
			 $('#txt_detalle_metodo_que_02_cati').val(data.porc_B2B),
			 $('#txt_detalle_metodo_que_04_cati').val(data.porc_B2C),
			 $('#txt_detalle_metodo_que_05_cati').val(data.met_mixto),
			 $('#txt_detalle_metodo_que_06_cati').val(data.coment),
						
			//Grupo Objetivo
            $('#txt_grupo_01').val(data.grupo_1),
			$('#txt_grupo_02').val(data.grupo_2),
			
			//Questionnaire
            $('#txt_quest_01').val(data.estructura),
			$('#txt_quest_02').val(data.glosario),
			

			//cuota

			$('#txt_man_muest_01_cati').bootstrapSwitch('state',data.muestra_ent),
			$('#txt_man_muest_02_cati').val(data.especif),
			$('#txt_man_muest_03_cati').bootstrapSwitch('state',data.rdd),
			$('#txt_man_muest_04_cati').bootstrapSwitch('state',data.muestra_cliente),
			$('#txt_man_muest_05_cati').bootstrapSwitch('state',data.base_datos),
			$('#txt_man_muest_06_cati').bootstrapSwitch('state',data.otro),
			$('#txt_man_muest_07_cati').val(data.especif_otro),
			$('#txt_man_muest_08_cati').bootstrapSwitch('state',data.nombre_contact),
			$('#txt_man_muest_09_cati').val(data.porc_nombre),
            $('#txt_man_muest_10_cati').bootstrapSwitch('state',data.permit_recom),
			$('#txt_man_muest_11_cati').val(data.especif_recom),
			$('#txt_man_muest_12_cati').val(data.n_recom),
			$('#txt_man_muest_13_cati').val(data.coment_adic),
			
			//data.cuota14:c14,
			//data.cuota15:c15,
			
			//Instruccion Para el Supervisor

			$('#txt_inst_sup_01_cati').val(data.inst_sup1),
			$('#txt_inst_sup_02_cati').val(data.inst_sup2),


            //Plazos
			$('#txt_start').val(data.t_campo_desde),
            $('#txt_end').val(data.t_campo_hasta),
            $('#txt_p_temporal').val(data.p_temporal),
            $('#txt_p_final').val(data.p_final),
            $('#txt_plazo_05').val(data.desc_obj),
            $('#txt_p_interm').val(data.dia_mes),
            $('#txt_plazo_07').val(data.planificacion),

			//Remuneracion

			$('#txt_remun_01_cati').val(data.remun_01),




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



var getDetailInstructivoTodos = function(){
  
	var param = {
	     id : $('#txt_idope_1').val()
	}
	
	$.ajax({
		url: "/Manager/RestInstructivo/getDetailInstructivoById/"+ $('#txt_idope_1').val(),
		type: "GET",
		dataType: "json",
		data: param,
		async: false,
		beforeSend: function(){
			//cargando los datos
			//$("#modalg-charge").modal("show");
		},
		success: function(data){
			//***********************************
			//var detalle = data.detalle;
			//**********************************

            // Informacion del Trabajo 
			$('#txt_inf_trab_01').bootstrapSwitch('state',data.rep_ola),
			$('#txt_inf_trab_02').bootstrapSwitch('state',data.carta),
			$('#txt_inf_trab_03').val(data.loi),
			$('#txt_inf_trab_04').val(data.incidencia),
			$('#txt_inf_trab_05').val(data.tasa),
			//Incentivo
	        
			$('#txt_incent_01').bootstrapSwitch('state',data.incent_dinero),
			$('#txt_incent_02').bootstrapSwitch('state',data.incent_voucher),
			$('#txt_incent_03').bootstrapSwitch('state',data.incent_regalo),
			$('#txt_incent_04').val(data.incent_especif),
		        
			//VistaGeneral
            $('#txt_vista_01').val(data.vista_1),
			$('#txt_vista_02').val(data.vista_2),
			//Metodo Recoleccion
			
			
			$('#txt_detalle_metodo_que_01').bootstrapSwitch('state',data.que_papi),
			$('#txt_detalle_metodo_que_02').bootstrapSwitch('state',data.que_capi),
			$('#txt_detalle_metodo_que_03').bootstrapSwitch('state',data.que_movil),
			$('#txt_detalle_metodo_que_04').bootstrapSwitch('state',data.que_mixto),
			$('#txt_detalle_metodo_que_05').val(data.que_otro),
			

			$('#txt_detalle_metodo_donde_01').bootstrapSwitch('state',data.donde_casa),
			$('#txt_detalle_metodo_donde_02').bootstrapSwitch('state',data.donde_central),
			$('#txt_detalle_metodo_donde_03').bootstrapSwitch('state',data.donde_calle),
			$('#txt_detalle_metodo_donde_04').bootstrapSwitch('state',data.donde_tienda),
			$('#txt_detalle_metodo_donde_05').val(data.donde_otro),
			

			//Grupo Objetivo
            $('#txt_grupo_01').val(data.grupo_1),
			$('#txt_grupo_02').val(data.grupo_2),
			
			//Questionnaire
            $('#txt_quest_01').val(data.estructura),
			$('#txt_quest_02').val(data.glosario),
			
			//Questionnaire PAPI
            $('#txt_quest_papi_01').val(data.quest_papi1),
			$('#txt_quest_papi_02').val(data.quest_papi2),

			//Questionnaire CAPI
			$('#txt_quest_capi_01').val(data.quest_capi1),
			$('#txt_quest_capi_02').val(data.quest_capi2),
			$('#txt_quest_capi_03').val(data.quest_capi3),

			//cuota
			$('#txt_man_muest_01').val(data.cuota1),
			$('#txt_man_muest_02').bootstrapSwitch('state',data.cuota2),
			$('#txt_man_muest_03').val(data.cuota3),
			$('#txt_man_muest_04').val(data.cuota4),
			$('#txt_man_muest_05').val(data.cuota5),
			//data.cuota6:c6,
			//data.cuota7:c7,
			
			
            //Plazos
			$('#txt_start').val(data.t_campo_desde),
            $('#txt_end').val(data.t_campo_hasta),
            $('#txt_p_temporal').val(data.p_temporal),
            $('#txt_p_final').val(data.p_final),
            $('#txt_plazo_05').val(data.desc_obj),
            $('#txt_p_interm').val(data.dia_mes),
            $('#txt_plazo_07').val(data.planificacion),
          
		    //Actualizaciones de estado PAPI

			$('#txt_plazo_PAPI_01').val(data.plazo_papi_01),
			$('#txt_plazo_PAPI_02').val(data.plazo_papi_02),
			$('#txt_plazo_PAPI_03').val(data.plazo_papi_03),
			$('#txt_plazo_PAPI_04').val(data.plazo_papi_04),
			

            //Actualizaciones de estado CAPI

			$('#txt_plazo_CAPI_01').val(data.plazo_capi_01),
			$('#txt_plazo_CAPI_02').val(data.plazo_capi_02),
			$('#txt_plazo_CAPI_03').val(data.plazo_capi_03),
			

			//Remuneracion

			$('#txt_remun_01').val(data.remun_01),
			$('#txt_remun_02').val(data.remun_02),
			
			//Pack

			$('#txt_pack_01').val(data.pack_01),
		
			//Contacto
			
			$('#txt_contacto_01').val(data.contacto_01),
			$('#txt_contacto_02').val(data.contacto_02),
			$('#txt_contacto_03').val(data.contacto_03),
			$('#txt_contacto_04').val(data.contacto_04),
			$('#txt_contacto_05').val(data.contacto_05)



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


var getDetailInstructivo = function(){
	
   if ($('#tipo_entrevista').val()==2){
	   getDetailInstructivoCati();
    }else{
    	getDetailInstructivoTodos();
    }

}



//---------------------------------------
var Proyecto = function() {
	"use strict";
	return {
		init : function() {
		
			$('.BSswitch').bootstrapSwitch();
			getDetailEstudio();
			dataSpickers1();
			dataSpickers2();
			dataSpickers3();
			$('#form_si').hide();
			$('#form_si_cati').hide();
			$('#form_btn_ok').hide();
			$('#form_btn_ok_cati').hide();
			$('#recomend_cati').hide();
			$('#realiza_cita_cati').hide();
			showTipoEncuesta();
			getDetailInstructivo();
	
		}
	}
}();