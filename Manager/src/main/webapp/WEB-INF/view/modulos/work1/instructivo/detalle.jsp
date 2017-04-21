<%@page import="cl.nexo.manager.obj.dashboard.ObjWidjetAccessAdmin"%>
<%@page import="cl.nexo.manager.obj.login.ObjPerfilLogin"%>
<%@page import="cl.nexo.manager.obj.login.ObjLoginUser"%>
<%@page import="cl.nexo.manager.obj.menu.ObjModuloMenuManager"%>
<%@page import="cl.nexo.manager.obj.menu.ObjUnidadMenuManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.net.*"%>
<%@page import="java.io.*"%>
<%@page import="cl.nexo.manager.obj.tools.ObjConfigTools"%>
<%@page import="java.util.Properties"%>
<%@page import="cl.nexo.manager.obj.menu.ObjMenuManager"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%
 ObjLoginUser login = (ObjLoginUser) request.getAttribute("login");
 ObjPerfilLogin perfil = (ObjPerfilLogin) request.getAttribute("perfil");
 ObjMenuManager menu = (ObjMenuManager) request.getAttribute("menu");
 int unidad_access = Integer.parseInt((String) request.getAttribute("unidad_access"));
 int modulo_access = Integer.parseInt((String) request.getAttribute("modulo_access"));
 int permiso_access = Integer.parseInt((String) request.getAttribute("permisoModulo"));
 ObjWidjetAccessAdmin widAdmin = (ObjWidjetAccessAdmin) request.getAttribute("WidjetAccessAdmin");
 int id_operacion = Integer.parseInt((String) request.getAttribute("id_operacion"));
 int id_tipo = Integer.parseInt((String) request.getParameter("id_tipo"));
 String urlRestServiceDelivery = (String) request.getAttribute("urlRestServiceDelivery");
 ObjConfigTools tol = (ObjConfigTools) request.getAttribute("tol");
 
 //lang option
 String langOption = (String) request.getAttribute("lang");
 URL url = application.getResource("/WEB-INF/lang/"+langOption+"/activity7.properties");
 InputStream in = url.openStream();
 Properties lang1 = new Properties();
 lang1.load(in);
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="es" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="es">
<!--<![endif]-->
<head>
	<meta charset="utf-8">
	<title></title>
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
	<meta content="" name="description">
	<meta content="Nexo-Chile" name="author">
	<link rel="icon" type="image/ico" href="<c:url value="/resources/manager/img/favicon.ico" />" />
	<!-- ================== BEGIN BASE CSS STYLE ================== -->
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/jquery-ui/themes/base/minified/jquery-ui.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/font-awesome/css/font-awesome.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/css/animate.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/css/style.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/css/style-responsive.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/css/theme/orange.css"/>" rel="stylesheet" id="theme">
	<!-- ================== END BASE CSS STYLE ================== -->
	
	<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
	<link href="<c:url value="/resources/manager/plugins/jquery-jvectormap/jquery-jvectormap-1.2.2.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/bootstrap-datepicker/css/datepicker.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/bootstrap-datepicker/css/datepicker3.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/bootstrap-switch-master/dist/css/bootstrap3/bootstrap-switch.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/DataTables/media/css/dataTables.bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/DataTables/extensions/Buttons/css/buttons.bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/DataTables/extensions/Responsive/css/responsive.bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/DataTables/extensions/AutoFill/css/autoFill.bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/DataTables/extensions/ColReorder/css/colReorder.bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/DataTables/extensions/KeyTable/css/keyTable.bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/DataTables/extensions/RowReorder/css/rowReorder.bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/DataTables/extensions/Select/css/select.bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/select2/dist/css/select2.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css" />" rel="stylesheet">


	
	<link href="<c:url value="/resources/manager/plugins/DataTables/extensions/ColVis/css/dataTables.colVis.min.css" />" rel="stylesheet">
	<!-- alertas rigth -->
    <!-- alertas rigth -->
    <link href="<c:url value="/resources/manager/plugins/gritter/css/jquery.gritter.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/DataTables/media/css/dataTables.color.css" />" rel="stylesheet">
	<!-- ================== END PAGE LEVEL STYLE ================== -->
	<!-- ================== BEGIN PAGE toastr  ================== -->
    <link href="<c:url value="/resources/manager/plugins/toastr/build/toastr.css" />" rel="stylesheet">
	<!-- ================== BEGIN PAGE LEVEL CSS STYLE UPLOAD ================== -->
    <link href="<c:url value="/resources/manager/plugins/jquery-file-upload/blueimp-gallery/blueimp-gallery.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/manager/plugins/jquery-file-upload/css/jquery.fileupload.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/manager/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" />" rel="stylesheet">
	<!-- ================== BEGIN BASE JS ================== -->
	<script src="<c:url value="/resources/manager/plugins/pace/pace.min.js" />"></script>
	<!-- ================== END BASE JS ================== -->
	
</head>
<body>
	<!-- data hide -->
	<input type="hide" class="form-control" name="txth_01" id="txth_01" value="<%= permiso_access %>">
	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in"><span class="spinner"></span></div>
	<!-- end #page-loader -->
	
	<!-- begin #page-container -->
		<!-- begin #header -->
		<%@ include file="../../../header_top_menu.jsp" %>
		<!-- end #header -->
		
		<!-- begin #sidebar -->
		<%@ include file="../../../top_sidebar.jsp" %>
		<!-- end #sidebar -->
		
		<!-- Configuracion Interna  -->
		<input type="hidden" id="txt_idope_1" name="txt_idope_1" value="<%=  id_operacion %>" />
		<input type="hidden" id="conf_01"  name="conf_01" value="<%= perfil.getEs_admin() %>" />
		<input type="hidden" id="conf_02"  name="conf_02" value="<%= urlRestServiceDelivery %>" />
		<input type="hidden" id="conf_03"  name="conf_03" value="<%= permiso_access %>" />
		<input type="hidden" id="conf_04"  name="conf_04" value="<%= login.getId_user() %>" />
		<input type="hidden" id="conf_05"  name="conf_05" value="<%= login.getId_perfil() %>" />
		<!-- BEGIN Config General -->
		<input type="hidden" id="combo_1" name="combo_1" value="" />
		<input type="hidden" id="combo_2" name="combo_2" value="" />
		<input type="hidden" id="combo_3" name="combo_3" value="" />
		<input type="hidden" class="form-control" id="lang_01" value="<%= langOption %>" />
		<input type="hidden" class="form-control" id="pace_01" value="<%= lang1.getProperty("tipoentrevista") %>" />
	    <input type="hidden" class="form-control" id="tipo_entrevista" value="<%=id_tipo%>" />
		<!-- END Config General -->
		
		<!-- begin #content -->
		<div id="content" class="content">
			<!-- begin breadcrumb -->
			<ol class="breadcrumb pull-right">
				<li><a href="/Manager/home" target="_self">Home</a></li>
				<li><a href="#"><%= lang1.getProperty("navEtapa") %></a></li>
				<li class="active" id="li_estudio"><%= lang1.getProperty("navActividad") %></li>
			</ol>
			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<h1 class="page-header" ><%= lang1.getProperty("header") %> <small id="h1_estudio"><%= lang1.getProperty("nuevoSmallHeader") %></small></h1>
			<!-- end page-header -->
			
			<!-- begin row -->
			<div class="row">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title"> Detalles clave</h4>
                            
                          
                            
                        </div>
                        <div class="panel-body">
                        	<form class="form-horizontal form-bordered">
                           	  <div class="row">
                           	  	<div class="form-group col-md-6">
                           	  		
                          			
                           		</div>
                           	  	
                           	  	<div class="form-group col-md-6" id="form_btn_ok">
                           	  		<p class="pull-right">
                           	  		
                           	  		
                           	  		 <a href="#" class=" btn btn-warning m-r-5" style="margin-top: 5px; text-align: center;" onclick="JavaScript: updateInstructivo();" id="btn_modificar">
                           	  			<i class="fa fa-check-square-o fa-2x pull-left"></i>
                           	  			Modificar
                           	  		</a>
                           	  		
                           	  
                           	  		<a href="#" class="btn btn-success m-r-5" style="margin-top: 5px; text-align: center;" onclick="JavaScript: createInstructivo();" id="btn_activae">
                           	  			<i class="fa fa-check-square-o fa-2x pull-left"></i>
                           	  			OK
                           	  		</a>
                          
                           	  		
                           	
                           	  		<a href="#" class="btn btn-danger m-r-5" style="margin-top: 5px; text-align: center;" onclick="JavaScript: deleteEstudio();" id="btn_deletee">
                           	  			<i class="fa fa-times fa-2x pull-left"></i>
                           	  			Cancelar
                           	  		</a>
                           	  		</p>
                           	  		
                           	  	</div>
                           	  	
                           	  	
                           	  	<div class="form-group col-md-6" id="form_btn_ok_cati">
                           	  		<p class="pull-right">
                           	  		
                           	        
                           	        <a href="#" class=" btn btn-warning m-r-5" style="margin-top: 5px; text-align: center;" onclick="JavaScript: updateInstructivoCati();" id="btn_modificar">
                           	  			<i class="fa fa-check-square-o fa-2x pull-left"></i>
                           	  			Modificar
                           	  		</a>
                           	  
                           	  
                           	  		<a href="#" class="btn btn-success m-r-5" style="margin-top: 5px; text-align: center;" onclick="JavaScript: createInstructivoCati();" id="btn_activae">
                           	  			<i class="fa fa-check-square-o fa-2x pull-left"></i>
                           	  			OK
                           	  		</a>
                          
                           	  		
                           	
                           	  		<a href="#" class="btn btn-danger m-r-5" style="margin-top: 5px; text-align: center;" onclick="JavaScript: deleteEstudio();" id="btn_deletee">
                           	  			<i class="fa fa-times fa-2x pull-left"></i>
                           	  			Cancelar
                           	  		</a>
                           	  		</p>
                           	  		
                           	  	</div>
                           	  	
                           	  	
                           	  	
                           	  </div>
                           	  <div id="info_op">
                           	  <div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Nombre del Estudio</label>
										<div class="col-md-8">
	                                         <input type="text" class="form-control" id="txt_ok_01" name="txt_ok_01" value="" readonly="readonly"/>
	                                    </div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">N&uacute;mero del Estudio </label>
										<div class="col-md-8">
	                                         <input type="text" class="form-control" id="txt_ok_02" name="txt_ok_02" value="" readonly="readonly"/>
										</div>
									</div>
								</div>
							  <div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Industria</label>
										<div class="col-md-8">
	                                         <select class="form-control" id="txt_detalle_01" name="txt_detalle_01" style="width: 100%;"></select>
	                                    </div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Producto</label>
										<div class="col-md-8">
	                                         <select class="form-control" id="txt_ok_03" name="txt_detalle_02" style="width: 100%;"></select>
										</div>
									</div>
								</div>
								<div class="row">
									
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Tipo Estudio </label>
										<div class="col-md-8">
	                                         <select class="form-control" id="txt_detalle_02" name="txt_detalle_02" style="width: 100%;"></select>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Fecha</label>
										<div class="col-md-8">
	                                         <select class="form-control" id="txt_ok_04" name="txt_ok_04" style="width: 100%;"></select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Tipo Entrevista </label>
										<div class="col-md-8">
	                                        <select class="form-control" id="txt_detalle_03" name="txt_detalle_03" style="width: 100%;"></select>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Tamaño de Muestra </label>
										<div class="col-md-8">
	                                         <input type="text" class="form-control" id="txt_detalle_04" name="txt_detalle_04" value="" readonly="readonly"/>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Director de estudios </label>
										<div class="col-md-8">
	                                         <select class="default-select2 form-control" id="txt_detalle_05" style="width: 100%;" name="txt_detalle_05"></select>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Cliente Solicitante</label>
										<div class="col-md-8">
	                                        <input type="text" class="form-control" style=" " id="txt_detalle_08" name="txt_detalle_08" value="" readonly="readonly"/>
										</div>
									</div>
									
								 </div>
								 <div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Gerente de Estudio </label>
										<div class="col-md-8">
											<select class="default-select2 form-control" id="txt_detalle_06" name="txt_detalle_06" style="width: 100%;"></select>
	                                         
										</div>
									</div>
									
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #faebcc; font-weight: bold;">Estado de Estudio </label>
										<div class="col-md-8">
	                                        <input type="text" class="form-control" style="font-size: 20px; color: orange; " id="txt_detalle_18" name="txt_detalle_18" value="" readonly="readonly"/>
										</div>
									</div>
								 </div>
								 <div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Jefe de Estudios </label>
										<div class="col-md-8">
	                                        
	                                         <select class="default-select2 form-control" id="txt_detalle_07" name="txt_detalle_07" style="width: 100%;"></select>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #faebcc; font-weight: bold;">Flujo </label>
										<div class="col-md-8">
	                                        <input type="text" class="form-control" style="font-size: 20px; color: orange; " id="txt_detalle_20" name="txt_detalle_20" value="" readonly="readonly"/>
										</div>
									</div>
								 </div>
							</div> 
							<br/>
							
							<div class="row"  >
                           	  	<div class="form-group col-md-6">
                           	  		<h1 id="" style="margin-left: 20px;">Informaci&oacute;n de Trabajo</h1>
                          			
                           		</div>
                           	 </div>
                           	 
                           	 
                <!-- Inf Trabajo Todos -->          	 
                <div class="row"  id="inf_trabajo"  >
                           	 <div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Reptici&oacute;n de ola/Estudio </label>
										<div class="col-md-8">
	                                        <input  type="checkbox" id="txt_inf_trab_01" name="txt_inf_trab_01" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Carta de pre anuncio</label>
										<div class="col-md-8">
	                                        <input  type="checkbox" id="txt_inf_trab_02" name="txt_inf_trab_02" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
										</div>
									</div>
							 </div>
							 
							 
							 <div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">LOI (...mins) </label>
										<div class="col-md-8">
	                                        <input type="number" class="form-control" style="" id="txt_inf_trab_03" name="txt_inf_trab_03" value="0" />  
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Incidencia esperada (%)</label>
										<div class="col-md-8">
	                                        <input type="number" class="form-control" style="" id="txt_inf_trab_04" name="txt_inf_trab_04" value="0" />  
										</div>
									</div>
							 </div>
							 <div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Tasa de productividad esperada (...por hora) </label>
										<div class="col-md-8">
	                                        <input type="number" class="form-control" style="" id="txt_inf_trab_05" name="txt_inf_trab_05" value="0" />  
										</div>
									</div>
				            </div>			
			    </div>
			    
			    <!-- End Inf Trabajo Todos -->
			     
			    <!-- Inf Trabajo CATI -->
			    <div class="row"  id="inf_trabajo_cati"  >
                           	 <div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-2" style="background: #f0f3f4; font-weight: bold;">Nombre del gui&oacute;n: *</label>
										<div class="col-md-10">
	                                         <input type="text" class="form-control" style="" id="txt_inf_trab_01_cati" name="txt_inf_trab_01_cati" value="" />
	                                    </div>
									</div>
									
							 </div>
							 
							 <div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-2" style="background: #f0f3f4; font-weight: bold;">Nombre del servidor: *</label>
										<div class="col-md-10">
	                                         <input type="text" class="form-control" style="" id="txt_inf_trab_02_cati" name="txt_inf_trab_02_cati" value="" />
	                                    </div>
									</div>
									
							 </div>

							 
							 
							 <div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Tasa de productividad esperada (...por hora) </label>
										<div class="col-md-8">
	                                        <input type="number" class="form-control" style="" id="txt_inf_trab_03_cati" name="txt_inf_trab_03_cati" value="0" />  
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Pa&iacute;ses:*</label>
										<div class="col-md-8">
	                                         <input type="text" class="form-control" style="" id="txt_inf_trab_04_cati" name="txt_inf_trab_04_cati" value="" />
	                                    </div>
									</div>
				            </div>			
							 
							 
							 
							 <div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">LOI (...mins) </label>
										<div class="col-md-8">
	                                        <input type="number" class="form-control" style="" id="txt_inf_trab_05_cati" name="txt_inf_trab_05_cati" value="0" />  
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Incidencia esperada (%)</label>
										<div class="col-md-8">
	                                        <input type="number" class="form-control" style="" id="txt_inf_trab_06_cati" name="txt_inf_trab_06_cati" value="0" />  
										</div>
									</div>
							 </div>
							 
							 <br>
							 <div class="row"  >
                           	  	<div class="form-group col-md-6">
                           	  		<h1 id="" style="margin-left: 20px;"></h1>
                          			
                           		</div>
                           	 </div>
							 
							 
							 
							  <div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Reptici&oacute;n de ola/Estudio </label>
										<div class="col-md-8">
	                                        <input  type="checkbox" id="txt_inf_trab_07_cati" name="txt_inf_trab_07_cati" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Carta de pre anuncio</label>
										<div class="col-md-8">
	                                        <input  type="checkbox" id="txt_inf_trab_08_cati" name="txt_inf_trab_08_cati" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
										</div>
									</div>
							 </div>
							 
							 
			    </div>
			    
			    <!-- End Inf Trabajo CATI -->
			    
							 <div class="row">
                           	  	<div class="form-group col-md-6">
                           	  		<h1 id="" style="margin-left: 20px;">Incentivo </h1>
                          			
                           		</div>
                           	  	
                           	 </div>
                           	 <div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Dinero  </label>
										<div class="col-md-8">
	                                        <input  type="checkbox" id="txt_incent_01" name="txt_incent_01" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Voucher </label>
										<div class="col-md-8">
	                                        <input  type="checkbox" id="txt_incent_02" name="txt_incent_02" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
										</div>
									</div>
							 </div>
							 <div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Regalo </label>
										<div class="col-md-8">
	                                        <input  type="checkbox" id="txt_incent_03" name="txt_incent_03" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Otro especificar</label>
										<div class="col-md-8">
	                                        <input type="text" class="form-control" style="" id="txt_incent_04" name="txt_incent_04" value="" /> 
										</div>
									</div>
									
							 </div>		
			                  <div class="row" id="realiza_cita_cati">
		                           	 
		                           	 <div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Podemos realizar citas </label>
										<div class="col-md-8">
	                                        <input  type="checkbox" id="txt_incent_05" name="txt_incent_05" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
										</div>
									</div>
									
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Comentarios adicionales</label>
										<div class="col-md-8">
	                                        <input type="text" class="form-control" style="" id="txt_incent_06" name="txt_incent_06" value="" /> 
										</div>
									</div>
									
								</div>	
									
									
									
									
							 
						</form>
                            
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            </div>
            <!-- end row -->
            
            
            <!-- begin row Vista General_Todos -->
			<div class="row" id="vista_general">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title"> Vista general</h4>
                        </div>
                        <div class="panel-body">
                        	<form class="form-horizontal form-bordered">
                           	  <div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Prop&oacute;sito de investigaci&oacute;n</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_vista_01" placeholder="Explicar el objetivo general de la investigaci&oacute;n: satisfacci&oacute;n del cliente, conocimiento de la marca.*"  rows="5"></textarea>
	                                    </div>
									</div>
									
							  </div>
							  <div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Trasfondo</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_vista_02" placeholder="Describir el contexto/trasfondo para este estudio: cliente, sector, historia."  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							  
						</form>
                            
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            </div>
            <!-- end row Vista General_Todos -->
            
            <!-- begin row Vista General_Todos_Cati -->
			<div class="row" id="vista_general_cati">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title"> Resumen</h4>
                        </div>
                        <div class="panel-body">
                        	<form class="form-horizontal form-bordered">
                           	  <div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Prop&oacute;sito de investigaci&oacute;n</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_vista_01_cati" placeholder="Explicar el objetivo general de la investigaci&oacute;n: satisfacci&oacute;n del cliente, conocimiento de la marca.*"  rows="5"></textarea>
	                                    </div>
									</div>
									
							  </div>
							  <div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Antecedente</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_vista_02_cati" placeholder="Describir el contexto/trasfondo para este estudio: cliente, sector, historia."  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							  
						</form>
                            
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            </div>
            <!-- end row Vista General_Todos_Cati -->
            
            
            
            
            
            
            
            <!-- begin row  Metodo de recoleccion de Data Todos -->
			<div class="row" id='met_rec_data_todos'>
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title"> M&eacute;todo de recolecci&oacute;n de datos</h4>
                        </div>
                        <div class="panel-body">
                        	<form class="form-horizontal form-bordered">
                           	  <div class="row">
                           	  	<div class="form-group col-md-6">
                           	  		<h4 id="" style="margin-left: 20px; ">	&iquest;QU&Eacute;? </h4>
                          			
                           		</div>
                           	  	
                           	  </div>
                           	  
							
							  <div class="row">
									<div class="form-group col-md-3">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">PAPI</label>
										<div class="col-md-8">
	                                         <input  type="checkbox" id="txt_detalle_metodo_que_01" name="txt_detalle_metodo_que_01" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
	                                    </div>
									</div>
									<div class="form-group col-md-3">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">CAPI</label>
										<div class="col-md-8">
	                                         <input  type="checkbox" id="txt_detalle_metodo_que_02" name="txt_detalle_metodo_que_02" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
	                                    </div>
									</div>
									<div class="form-group col-md-3">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Movil</label>
										<div class="col-md-8">
	                                         <input  type="checkbox" id="txt_detalle_metodo_que_03" name="txt_detalle_metodo_que_03" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
	                                    </div>
									</div>
									<div class="form-group col-md-3">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Modo Mixto</label>
										<div class="col-md-8">
	                                         <input  type="checkbox" id="txt_detalle_metodo_que_04" name="txt_detalle_metodo_que_04" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
	                                    </div>
									</div>
							
							 </div>
							 
							 <br>
							 
							 	  <div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Otro Especificar</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_detalle_metodo_que_05" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>

		                      <hr/>
							  
							  <div class="row">
                           	  	<div class="form-group col-md-6">
                           	  		<h4 id="" style="margin-left: 20px; ">	&iquest;DONDE? </h4>
                          			
                           		</div>
                           	  	
                           	  </div>
                           	  
                           	  
							  <div class="row">
									<div class="form-group col-md-3">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">En casa</label>
										<div class="col-md-8">
	                                         <input  type="checkbox" id="txt_detalle_metodo_donde_01" name="txt_detalle_metodo_donde_01" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
	                                    </div>
									</div>
									<div class="form-group col-md-3">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Ubicaci&oacute;n Central</label>
										<div class="col-md-8">
	                                         <input  type="checkbox" id="txt_detalle_metodo_donde_02" name="txt_detalle_metodo_donde_02" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
	                                    </div>
									</div>
									<div class="form-group col-md-3">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">En la calle</label>
										<div class="col-md-8">
	                                         <input  type="checkbox" id="txt_detalle_metodo_donde_03" name="txt_detalle_metodo_donde_03" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
	                                    </div>
									</div>
									<div class="form-group col-md-3">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">En tienda/salida de entrevista</label>
										<div class="col-md-8">
	                                         <input  type="checkbox" id="txt_detalle_metodo_donde_04" name="txt_detalle_metodo_donde_04" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
	                                    </div>
									</div>
									
							 </div>
							 
							 
							 <div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Otro Especificar</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_detalle_metodo_donde_05" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							  
						</form>
                            
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            </div>
            <!-- end   Metodo de recoleccion de Data Todos -->
            
            
                 
            <!-- begin row  Metodo de recoleccion de Data cati -->
			<div class="row" id='met_rec_data_cati'>
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title"> M&eacute;todo de recolecci&oacute;n de datos</h4>
                        </div>
                        <div class="panel-body">
                        	<form class="form-horizontal form-bordered">
                           	  <div class="row">
                           	  	<div class="form-group col-md-6">
                           	  		<h4 id="" style="margin-left: 20px; ">	&iquest;QU&Eacute;? </h4>
                          			
                           		</div>
                           	  	
                           	  </div>
                           	  
                      
							
							  <div class="row">
									
								
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">B2B</label>
										<div class="col-md-2">
	                                         <input  type="checkbox" id="txt_detalle_metodo_que_01_cati" name="txt_detalle_metodo_que_01_cati" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
	                                    </div>
									</div>
									
									
								  <div class="form-group col-md-4">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">%</label>
										<div class="col-md-2">
	                                        <input type="text" class="form-control" style="" id="txt_detalle_metodo_que_02_cati" name="txt_detalle_metodo_que_02_cati" value="" /> 
										</div>
									</div>
									
									
									
									
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">B2C</label>
										<div class="col-md-2">
	                                         <input  type="checkbox" id="txt_detalle_metodo_que_03_cati" name="txt_detalle_metodo_que_03_cati" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
	                                    </div>
									</div>
									
									
									
									<div class="form-group col-md-4">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">%</label>
										<div class="col-md-2">
	                                        <input type="text" class="form-control" style="" id="txt_detalle_metodo_que_04_cati" name="txt_detalle_metodo_que_04_cati" value="" /> 
										</div>
									</div>
									
									

							 </div>
							 
							 <br>
							 
							 	  <div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Si se utiliza un modo mixto, por favor especificar los m&eacute;todos</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_detalle_metodo_que_05_cati" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
								 </div>
								 
								  <div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Comentarios adicionales</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_detalle_metodo_que_06_cati" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
								 </div>
						</form>
                            
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            </div>
            <!-- end   Metodo de recoleccion de Data cati -->

            
             <!-- begin row -->
			<div class="row">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">Grupo objetivo</h4>
                        </div>
                        <div class="panel-body">
                        	<form class="form-horizontal form-bordered">
                           	  
							 <div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Definici&oacute;n de grupo objetivo  *</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_grupo_01" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;"> Si el grupo objetivo contiene menores y/o adultos vulnerables, explicar procedimiento de   solicitar permiso acorde a legislaci&oacute;n/reglas nacionales.  *</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_grupo_02" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							  
						</form>
                            
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            </div>
            <!-- end row -->  
            <!-- begin row -->
			<div class="row">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">Questionnaire</h4>
                        </div>
                        <div class="panel-body">
                        	<form class="form-horizontal form-bordered">
                           	  
							 <div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Estructura </label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_quest_01" placeholder="Explicar las distintas partes del estudio (y por qu&eacute; est&aacute;n en un orden espec&iacute;fico).*"  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Glosario</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_quest_02" placeholder="Explicar palabras t&eacute;cnicas, y frases, ya sea las que se encuentren en el cuestionario o las que el entrevistado pueda usar.*"  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							  
						</form>
                            
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            </div>
            <!-- end row --> 
            
            
            
            
            
            <!-- begin row -->
			<div class="row"  id="cuest_papi">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title"> PAPI </h4>
                        </div>
                        <div class="panel-body">
                        	<form class="form-horizontal form-bordered">
                           	  
							 <div class="row">
									<div class="form-group col-md-12">
										<h4 class="panel-title">Recordar</h4>
										<ul>
											<li>Leer todas las instrucciones atentamente, asegurarse de estar familiarizado con la ruta del     cuestionario y todos sus aspectos.</li>
											<li>Si tiene dudas, contacte a su supervisor/coordinador.</li>
											<li>En caso de distintos cuestionarios, asegúrese de usar el correcto.  Entrevistas hechas en el cuestionario incorrecto no son útiles y puede que no sean pagadas. </li>  
											
										</ul>
									</div>
									
							</div>
							
						  <div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">A&ntilde;adir identificador para los distintos tipos/versiones.*</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_quest_papi_01" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							
							
							<br>
							
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">A&ntilde;adir instrucciones espec&iacute;ficas al proyecto.*</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_quest_papi_02" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							  
						</form>
                            
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            </div>
            <!-- end row -->  
            
            
            
            <!-- begin row -->
			<div class="row" id="cuest_capi">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title"> CAPI </h4>
                        </div>
                        <div class="panel-body">
                        	<form class="form-horizontal form-bordered">
                           	  
							 
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">El gui&oacute;n CAPI estar&aacute; disponible para discar y tomar desde:</label>
										<div class="col-md-8">
	                                         <input type="text" class="form-control" style="" id="txt_quest_capi_01" name="txt_detalle_25" value="" />
	                                    </div>
									</div>
									
							</div>
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Aseg&uacute;rese de tomar el gui&oacute;n correcto para este proyecto. A&ntilde;adir identificador de proyecto  (pantalla de apertura /nombre de proyecto a seleccionar). *</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_quest_capi_02" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							<div class="row">
									<div class="form-group col-md-12">
										<h4 class="panel-title">Recordar</h4>
										<ul>
											<li>Leer todas las instrucciones atentamente y asegurarse de estar familiarizado con   todos los aspectos del cuestionario.</li>
											<li>Se requiere que haga una entrevista de prueba. Aseg&uacute;rese de indicar que la entrevista   es de prueba y no una.</li>
											<li> Si tiene dudas, contacte a su supervisor/coordinador.</li>
										</ul>
									</div>
									
							</div>
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">A&ntilde;adir instrucciones espec&iacute;ficas al proyecto*</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_quest_capi_03" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							  
						</form>
                            
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            </div>
            <!-- end row -->
            
            
            
            
            
            
            <!-- begin row   Muestra Todos -->
			<div class="row" id="man_muestra">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title"> Manejo de la muestra/cuota </h4>
                        </div>
                        <div class="panel-body">
                        	<form class="form-horizontal form-bordered">
                           	  
							 
							
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Especificar la fuente/plan de la muestra</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_man_muest_01" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							
							<div class="row">
									<div class="form-group col-md-12">
										<h4 class="panel-title"><strong>Reglas para el uso de la muestra</strong>  </h4>
									</div>
									
							</div>
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Reglas espec&iacute;ficas para el procedimiento de contacto</label>
										<div class="col-md-8">
	                                         <input  type="checkbox" id="txt_man_muest_02" name="txt_man_muest_02" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
	                                    </div>
									</div>
									
							</div>
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Proyecto/procedimiento de contacto si corresponde.  Especificar las reglas</label>
										<div class="col-md-8">
											<textarea class="form-control"  id="txt_man_muest_03" placeholder="Explicar en detalle el procedimiento de contacto (prioridad, # de contactos, c&oacute;digos de contacto, uso de las tarjetas de contacto – cartas de presentaci&oacute;n).*"  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							<br/>
							<br/>
							<div class="row">
									<div class="form-group col-md-12">
										<h4 class="panel-title">Recordar</h4>
										<ul>
											<li>Entregar a su entrevistados su identificaci&oacute;n y explicar el prop&oacute;sito de su visita. Ofrecer toda la    informaci&oacute;n disponible que pueda tranquilizar al encuestado para participar en la investigaci&oacute;n  de mercado.</li>
											<li>A no ser que se haya determinado de otra manera, solo entrevistar a encuestados que  vivan en la direcci&oacute;n, s&oacute;lo entrevistar 1 persona por casa y no entrevistar a nadie que Ud.  Conozca. </li>
											<li>Ud. es responsable de mantener  y asegurar todas las muestras, listas de direcciones y   otros registros que contengan datos personales mientras se encuentren en su posesi&oacute;n. </li>
											<li>Agregar otra informaci&oacute;n local relevante.*</li>
										</ul>
									</div>
									
							</div>
							
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Agregar otra informaci&oacute;n local relevante.*</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_man_muest_04" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							
							<br/>
							
							<div class="row">
								<div class="form-group col-md-12">
									<h2 class="panel-title">Si corresponde: cuota </h2>
									
								</div>
									
							</div>
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Agregar la cuota personal del entrevistado a alcanzar o remitirse a una hoja de cuota separada. *</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_man_muest_05" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							
							<br/>
							<br/>
							<div class="row">
								<div class="form-group col-md-12">
									<h2 class="panel-title">Revelar el cliente al encuestado </h2>
									
								</div>
									
							</div>
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">No est&aacute; permitido (Esto solo aplica si se utiliza una muestra que no sea del cliente y el informe es an&oacute;nimo)</label>
										<div class="col-md-8">
	                                         <input  type="radio" value="1" checked="checked" name="txt_man_muest_06" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch"  onChange="JavaScript: showRevelar();">
	                                    </div>
									</div>
									
							</div>
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Si es necesario/lo solicita el encuestado</label>
										<div class="col-md-8">
	                                         <input  type="radio" value="2" name="txt_man_muest_06" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch"  >
	                                    </div>
									</div>
									
							</div>
							
							<br/>
							<br/>
							
							
					<div class="row" id="form_si">
							<div class="row">
								<div class="form-group col-md-12">
									<h2 class="panel-title"><strong>SI</strong></h2>
									
								</div>
									
							</div>
							
						
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">al comienzo de la encuesta</label>
										<div class="col-md-8">
	                                         <input  type="radio" value="1" checked="checked" name="txt_man_muest_07" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
	                                    </div>
									</div>
									
							</div>
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">durante la encuesta </label>
										<div class="col-md-8">
	                                         <input  type="radio" value="2" name="txt_man_muest_07" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
	                                    </div>
									</div>
									
							</div>
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">al final de la encuesta </label>
										<div class="col-md-8">
	                                         <input  type="radio" value="3" name="txt_man_muest_07" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
	                                    </div>
									</div>
									
							</div>
							
					</div>	
							  
						</form>
                            
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            </div>
            <!-- end row --> 
            
            
            
             <!-- begin row -->
			<div class="row" id="man_muestra_cati">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title"> Manejo de la muestra/cuota </h4>
                        </div>
                        <div class="panel-body">
                        	<form class="form-horizontal form-bordered">

							
							<div class="row">
									<div class="form-group col-md-12">
										<h4 class="panel-title"><strong>Especificar la fuente de la muestra</strong>  </h4>
									</div>
									
							</div>
							
							
								 <div class="row">
										<div class="form-group col-md-6">
											<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Muestra entregada por GfK</label>
											<div class="col-md-8">
		                                         <input  type="checkbox" id="txt_man_muest_01_cati" name="txt_man_muest_01_cati" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
		                                    </div>
										</div>
										
										
										<div class="form-group col-md-6">
											<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Especificar</label>
											<div class="col-md-8">
		                                        <input type="text" class="form-control" style="" id="txt_man_muest_02_cati" name="txt_man_muest_02_cati" value="" /> 
											</div>
										</div>

								
								 </div>
								 
					
								 
								 
								 
								 <div class="row">
								 
								 
								       <div class="form-group col-md-6">
											<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">RDD</label>
											<div class="col-md-8">
		                                         <input  type="checkbox" id="txt_man_muest_03_cati" name="txt_man_muest_03_cati" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
		                                    </div>
										</div>
										
										<div class="form-group col-md-6">
											<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Muestra del cliente</label>
											<div class="col-md-8">
		                                         <input  type="checkbox" id="txt_man_muest_04_cati" name="txt_man_muest_04_cati" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
		                                    </div>
										</div>
									
								
								 </div>
								 
								 
								 <div class="row">

										<div class="form-group col-md-6">
											<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Bases de datos</label>
											<div class="col-md-8">
		                                         <input  type="checkbox" id="txt_man_muest_05_cati" name="txt_man_muest_05_cati" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
		                                    </div>
										</div>
								
								 </div>
								 
								 
								 
								 
								 
								 
								  <div class="row">
										<div class="form-group col-md-6">
											<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Otro</label>
											<div class="col-md-8">
		                                         <input  type="checkbox" id="txt_man_muest_06_cati" name="txt_man_muest_06_cati" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
		                                    </div>	                                    
										</div>
										
										<div class="form-group col-md-6">
											<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Especificar</label>
											<div class="col-md-8">
		                                        <input type="text" class="form-control" style="" id="txt_man_muest_07_cati" name="txt_man_muest_07_cati" value="" /> 
											</div>
										</div>

								 </div>

								 
								 <br>
								 <br>
								 
								 <div class="row">
										<div class="form-group col-md-6">
											<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">La muestra incluye el nombre de contacto</label>
											<div class="col-md-8">
		                                         <input  type="checkbox" id="txt_man_muest_08_cati" name="txt_man_muest_08_cati" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
		                                    </div>	                                    
										</div>
										
										<div class="form-group col-md-6">
											<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Ambos %</label>
											<div class="col-md-8">
		                                        <input type="text" class="form-control" style="" id="txt_man_muest_09_cati" name="txt_man_muest_09_cati" value="" /> 
											</div>
										</div>

								 </div>
								 
								 
								 
								 <br>
								 <br>
							
							
							
							     <div class="row" >
										<div class="form-group col-md-6">
											<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Se permiten recomendados </label>
											<div class="col-md-8">
		                                         <input  type="checkbox" id="txt_man_muest_10_cati" name="txt_man_muest_10_cati" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch" onChange="JavaScript: showReconedadosCati();">
		                                    </div>	                                    
										</div>
										
								</div>
								
								<br>
								
								 <div class="row" id="recomend_cati">		
										
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Si la respuesta es si, especificar las reglas para los recomendados.</label>
										<div class="col-md-8">
											<textarea class="form-control"  id="txt_man_muest_11_cati" placeholder=""  rows="5"></textarea>
		                                   </div>
									</div>
	 
								 
					
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Indicar el n&uacute;mero de recomendados permitido</label>
										<div class="col-md-8">
	                                        <input type="number" class="form-control" style="" id="txt_man_muest_12_cati" name="txt_man_muest_12_cati" value="" />  
										</div>
									</div>
									
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Comentarios adicionales</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_man_muest_13_cati" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>

							 </div>	 
							 
							 
 							<br>							
                            <br/>
							
							<div class="row">
								<div class="form-group col-md-12">
									<h2 class="panel-title">Identificaci&oacute;n del cliente </h2>
								</div>
							</div>
							
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">No permitido (Esto solo se aplica si se utiliza una muestra de un no cliente y la evaluaci&oacute;n es an&oacute;nima)</label>
										<div class="col-md-8">
	                                         <input  type="radio" value="1" checked="checked" name="txt_man_muest_14_cati" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch" onChange="JavaScript: showRevelarCati();">
	                                    </div>
									</div>
									
							</div>
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Si es necesario/lo solicita el encuestado</label>
										<div class="col-md-8">
	                                         <input  type="radio" value="2" name="txt_man_muest_14_cati" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
	                                    </div>
									</div>
									
							</div>
							
							<br/>
							<br/>
							
						<div class="row" id="form_si_cati">	
							<div class="row" >
								<div class="form-group col-md-12">
									<h2 class="panel-title"><strong>SI</strong></h2>
									
								</div>
									
							</div>
							
						
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">al comienzo de la encuesta</label>
										<div class="col-md-8">
	                                         <input  type="radio" value="1" checked="checked" name="txt_man_muest_15_cati" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
	                                    </div>
									</div>
									
							</div>
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">durante la encuesta </label>
										<div class="col-md-8">
	                                         <input  type="radio" value="2" name="txt_man_muest_15_cati" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
	                                    </div>
									</div>
									
							</div>
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">al final de la encuesta </label>
										<div class="col-md-8">
	                                         <input  type="radio" value="3" name="txt_man_muest_15_cati" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">
	                                    </div>
									</div>
									
							</div>
							
							<br>
							
					</div>
							  
						</form>
                            
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            </div>
            <!-- end row --> 
            
            
            
            
             <!-- begin row -->
			<div class="row" id="inst_supervisor">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">Instrucci&oacute;n para el Supervisor</h4>
                        </div>
                        <div class="panel-body">
                        	<form class="form-horizontal form-bordered">
                           	  
							 <div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Especificar todas las instrucciones para la carga de la muestra, incluyendo prioridad, variables de la muestra por cuotas, etc. </label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_inst_sup_01_cati" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Especificar las instrucciones para el manejo de cuotas, por ej. cuotas duras vs suaves, etc.*</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_inst_sup_02_cati" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							  
						</form>
                            
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            </div>
            <!-- end row -->


            
            <!-- begin row -->
			<div class="row">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">Plazos </h4>
                        </div>
                        <div class="panel-body">
                        	<form class="form-horizontal form-bordered">
                           	  
							 
							
							<div class="row">
							   <div class="form-group col-md-12">
										
	                                    
	                                <div class="form-group col-md-12">
                                    <label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Fechas de trabajo de campo</label>
                                    <div class="col-md-8">
                                        <div class="input-group input-daterange">
                                            <input type="text" class="form-control" id="txt_start"  name="txt_start" placeholder="Desde">
                                            <span class="input-group-addon">to</span>
                                            <input type="text" class="form-control" id="txt_end" name="txt_end" placeholder="Hasta">
                                        </div>
                                    </div>
                                </div>
	                            </div>
									
							</div>
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Plazo temporal:</label>
										<div class="col-md-6">
											<div class="form-group">
					                            <input type="text" class="form-control clsDatePicker"  placeholder="Ingrese Fecha" name="txt_p_temporal" id="txt_p_temporal">
					                        </div>
		                    			</div>
									</div>
									
							</div>
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Plazo final:</label>
										<div class="col-md-6">
											<div class="form-group">
					                            <input type="text" class="form-control clsDatePicker"  placeholder="Ingrese Fecha" name="txt_p_final" id="txt_p_final">
					                        </div>
		                    			</div>
									</div>
									
							</div>
							
							<br/>
							<div class="row">
								<div class="form-group col-md-12">
									<h2 class="panel-title"><strong>Plazos intermedios (si corresponde):</strong></h2>
									
								</div>
									
							</div>
							
						
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Agregar la descripci&oacute;n del objetivo: *</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_plazo_05" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">DD/MM</label>
										<div class="col-md-6">
											<div class="form-group">
					                            <input type="text" class="form-control clsDatePicker" placeholder="Ingrese Fecha" name="txt_p_interm" id="txt_p_interm">
					                        </div>
		                    			</div>
									</div>
									
							</div>
						
							
						<br/>
						<br/>
							<div class="row">
								<div class="form-group col-md-12">
									<h2 class="panel-title"><strong>Planificaci&oacute;n detallada /organigrama de trabajo (si corresponde)</strong></h2>
									
								</div>
									
							</div>
							
						
							<div class="row">
								  <div class="form-group col-md-12">
									<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Agregar la planificaci&oacute;n detallada/organigrama de trabajo o hacer referencia a otro documento separado</label>
									   <div class="col-md-8">
		                                      <textarea class="form-control"  id="txt_plazo_07" placeholder="Especificar cu&aacute;ndo realizar la entrevista (d&iacute;as permitidos, hora, etc...)."  rows="5"></textarea>
		                                </div>
								 </div>
									
							</div>
							
	
	
	<br>
	<br>	
		<div class="row" id="etiqueta_plazos">
			<div class="form-group col-md-12">
				<h2 class="panel-title"><strong>Actualizaciones de estado  feedback   env&iacute;o/llamadas de vuelta de las entrevistas completas:</strong></h2>
			</div>
		</div>
	<br>
	<br>						
	
	
							
	<div class="row" id="plazos_papi">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title"> PAPI </h4>
                        </div>
                        <div class="panel-body">
                        	<form class="form-horizontal form-bordered">
                           	  
							 <div class="row">
									<div class="form-group col-md-12">
										
										<ul>
											<li>Enviar de vuelta sus cuestionarios completos.</li>
										</ul>
									</div>
									
							</div>
							
						  <div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Agregar las instrucciones espec&iacute;ficas del proyecto. *</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_plazo_PAPI_01" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							
							<br>
							
							<div class="row">
									<div class="form-group col-md-12">
										
										<ul>
											<li>Ud. deber&aacute; entregar una actualizaci&oacute;n de estado a su supervisor/coordinador:</li>
										</ul>
									</div>
									
							</div>
							
							
							<br>
							
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Agregar los plazos. *</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_plazo_PAPI_02" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							
							<br>
							
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Agregar una descripci&oacute;n exacta de la informaci&oacute;n deseada. *</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_plazo_PAPI_03" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							
							
							<br>
							
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Agregar las instrucciones para transmitir la informaci&oacute;n (correo, tel&eacute;fono) + nombre(s) de la(s)
persona (s) responsable(s). *</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_plazo_PAPI_04" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							
							
							
							
							  
						</form>
                            
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            </div>
            
            
            
            
         <div class="row" id="plazos_capi">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title"> CAPI </h4>
                        </div>
                        <div class="panel-body">
                        	<form class="form-horizontal form-bordered">
                           	  
							 <div class="row">
									<div class="form-group col-md-12">
										
										<ul>
											<li>Por favor ll&aacute;menos su primer d&iacute;a de trabajo/primeras entrevistas. Esto es importante ya que
												nos entregar&aacute; una indicaci&oacute;n de c&oacute;mo est&aacute; funcionando la entrevista en el campo y nos
												permitir&aacute; darle feedback a nuestro cliente.</li>
											<li>Por favor aseg&uacute;rese que ingrese a su m&aacute;quina CAPI cada d&iacute;a en que ha trabajado en el
												proyecto  Esto es muy importante ya que nos permitir&aacute; monitorear el progreso sin necesitad de una
												comunicaci&oacute;n espec&aacute;fica posterior</li>
											<li>Cuando la informaci&oacute;n de la muestra, los c&oacute;digos de contacto, las citas, etc. no est&eacute;n
												incluidas en la plataforma CAPI, Ud. deber&aacute; entregar una actualizaci&oacute;n de estado a su
												supervisor/coordinador de acuerdo a las siguientes instrucciones: *</li>  
											
										</ul>
									</div>
									
							</div>
							
						  <div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Agregar el plazo. *</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_plazo_CAPI_01" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							
							
							<br>
							
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Agregar le descripci&oacute;n exacta de la informaci&oacute;n deseada *</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_plazo_CAPI_02" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							
							
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Agregar instrucciones de como pasar la informaci&oacute;n (correo, tel&eacute;fono) + detalles de contacto
										(si es diferente a la informaci&oacute;n ya incluida) *</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_plazo_CAPI_03" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>

						</form>
                            
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            </div>

  
						</form>
						

                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            </div>
            <!-- end row -->   
            
          
          
          <!-- begin row -->
			<div class="row" id="remuneracion">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">Remuneraci&oacute;n</h4>
                        </div>
                        <div class="panel-body">
                        	<form class="form-horizontal form-bordered">
                           	  
							 <div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Agregar informaci&oacute;n sobre la remuneraci&oacute;n y/o referirse al contrato del proyecto. *</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_remun_01" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;"> Agregar las normas locales para pagar facturas/siniestros/tasa por milla. *</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_remun_02" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							  
						</form>
                            
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            </div>
            <!-- end row -->   
          
          
         <!-- begin row -->
			<div class="row" id="pack">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">Detalles del pack</h4>
                        </div>
                        <div class="panel-body">
                        	<form class="form-horizontal form-bordered">
                        	
                           	
                          <div class="row">
									<div class="form-group col-md-12">
										
										<ul>
											<li>Si faltara alg&oacute;n contenido por favor contactar inmediatamente a la oficina y se lo despacharemos.</li>
											
										</ul>
									</div>
					
							</div>
							
							
							<div class="row">
								<div class="form-group col-md-12">
									<h2 class="panel-title"><strong>Contenidos de su pack</strong></h2>
									
								</div>
									
							</div>
                        	
                        	

                          <!-- 	  
							***************
							Por favor ingrese el contenido de su pack. *
							TABLA PARA AGREGAR ELEMENTOS
							**************
							-->
							
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;"> Mencionar el n&uacute;mero exacto si es necesario o referirse a otros documentos (contrato).*</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_pack_01" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							  
						</form>
                            
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            </div>
            <!-- end row -->  
            
            
            
             <!-- begin row -->
			<div class="row" id="contacto">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">Detalles de Contacto</h4>
                        </div>
                        <div class="panel-body">
                        	<form class="form-horizontal form-bordered">
                           	  
							 <div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Agregar informaci&oacute;n de contacto para el supervisor/coordinador.*</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_contacto_01" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Si corresponde, agregar n&uacute;meros de tel&eacute;fono gratuitos.*</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_contacto_02" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Agregar otra informaci&oacute;n de contacto relevante:*</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_contacto_03" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							
							
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Compa&ntilde;&iacute;a local *</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_contacto_04" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							
							<div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Sociedad/federaci&oacute;n de estudios de mercado (por ej. Esomar, .)*</label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_contacto_05" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							
							  
						</form>
                            
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            </div>
            <!-- end row --> 
            
            
            
             <!-- begin row -->
			<div class="row" id="remuneracion_cati">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">Remuneraci&oacute;n</h4>
                        </div>
                        <div class="panel-body">
                        	<form class="form-horizontal form-bordered">
                           	  
							 <div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Agregar informaci&oacute;n sobre la remuneraci&oacute;n y/o hacer referencia al contrato del proyecto. * </label>
										<div class="col-md-8">
	                                         <textarea class="form-control"  id="txt_remun_01_cati" placeholder=""  rows="5"></textarea>
	                                    </div>
									</div>
									
							</div>
							
							  
						</form>
                            
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            </div>
            <!-- end row -->
 

                   
		</div>
		<!-- end #content -->
		
        
		<!-- begin scroll to top btn -->
		<a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
		<!-- end scroll to top btn -->

	<!-- end page container -->
	
   
    
		<!-- #modal-delete-alert -->
		<div class="modal fade" id="modal-delete-alert">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
						<h4 class="modal-title">Alerta!</h4>
					</div>
					<div class="modal-body">
						<div class="alert alert-danger m-b-0">
							<h4><i class="fa fa-info-circle"></i> Solicitud de Eliminaci&oacute;n</h4>
							<p>Si deseas eliminar usuario seleccionado haz <strong>click</strong> en bot&oacute;n <strong>ELIMINAR</strong>,  si no haz <strong>click</strong> en bot&oacute;n <strong>CANCELAR</strong> </p>
							<input type="hidden" id="aux_del1" name="aux_del1" class="form-control" value="" />
							<input type="hidden" id="aux_del2" name="aux_del2" class="form-control" value="" />
							<input type="hidden" id="aux_del3" name="aux_del3" class="form-control" value="" />
						</div>
					</div>
					<div class="modal-footer">
						<a href="javascript:;" class="btn btn-lg btn-white" data-dismiss="modal">Cancelar</a>
						<a href="JavaScript: deleteLoginUser();" class="btn btn-lg btn-danger">Eliminar</a>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="modal-delete-alert2">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
						<h4 class="modal-title">Alerta!</h4>
					</div>
					<div class="modal-body">
						<div class="alert alert-danger m-b-0">
							<h4><i class="fa fa-info-circle"></i> Solicitud de Eliminaci&oacute;n</h4>
							<p>Si deseas eliminar perfil seleccionado haz <strong>click</strong> en bot&oacute;n <strong>ELIMINAR</strong>,  si no haz <strong>click</strong> en bot&oacute;n <strong>CANCELAR</strong> </p>
							<input type="hidden" id="aux2_del1" name="aux2_del1" class="form-control" value="" />
							<input type="hidden" id="aux2_del2" name="aux2_del2" class="form-control" value="" />
						</div>
					</div>
					<div class="modal-footer">
						<a href="javascript:;" class="btn btn-lg btn-white" data-dismiss="modal">Cancelar</a>
						<a href="JavaScript: deletePerfilUser();" class="btn btn-lg btn-danger">Eliminar</a>
					</div>
				</div>
			</div>
		</div>
	   <!-- End Modal -->
	   <!-- End MODAL USUER  ----------------------------------------------------------------------------------------------------- -->
	   
	   <!-- Incluir modal generico -->
       <%@ include file="../../../general-modal.jsp" %>
		
		
	<!-- ================== BEGIN BASE JS ================== -->
	

	
	
	<script src="<c:url value="/resources/manager/plugins/jquery/jquery-1.9.1.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/jquery/jquery-migrate-1.1.0.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/jquery-ui/ui/minified/jquery-ui.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/bootstrap/js/bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/slimscroll/jquery.slimscroll.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/jquery-cookie/jquery.cookie.js" />"></script>
	<!-- ================== END BASE JS ================== -->
	
	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="<c:url value="/resources/manager/plugins/notify/bootstrap-notify.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/flot/jquery.flot.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/flot/jquery.flot.time.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/flot/jquery.flot.resize.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/flot/jquery.flot.pie.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/sparkline/jquery.sparkline.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/jquery-jvectormap/jquery-jvectormap-1.2.2.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/bootstrap-datepicker/js/bootstrap-datepicker.es.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/DataTables/media/js/jquery.dataTables.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/DataTables/media/js/dataTables.bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/DataTables/extensions/Buttons/js/dataTables.buttons.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/DataTables/extensions/Buttons/js/buttons.bootstrap.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/DataTables/extensions/Buttons/js/buttons.print.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/DataTables/extensions/Buttons/js/buttons.flash.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/DataTables/extensions/Buttons/js/buttons.html5.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/DataTables/extensions/Responsive/js/dataTables.responsive.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/DataTables/extensions/AutoFill/js/dataTables.autoFill.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/DataTables/extensions/ColReorder/js/dataTables.colReorder.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/DataTables/extensions/KeyTable/js/dataTables.keyTable.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/DataTables/extensions/RowReorder/js/dataTables.rowReorder.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/DataTables/extensions/Select/js/dataTables.select.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/DataTables/extensions/ColVis/js/dataTables.colVis.min.js" />"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->
	<!-- ================== BEGIN VAL RUT ================== -->
	<script src="<c:url value="/resources/manager/plugins/jquery-rut/jquery.rut.js" />"></script>
	<!-- ================== END VAL RUT ================== -->
	<!-- ================== BEGIN PAGE LEVEL JS UPLOAD ================== -->
	<script src="<c:url value="/resources/manager/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js" />"></script>
	<!-- The Templates plugin is included to render the upload/download listings -->
    <script src="<c:url value="/resources/manager/plugins/jquery-file-upload/js/vendor/tmpl.min.js" />"></script>
    <!-- The Load Image plugin is included for the preview images and image resizing functionality -->
    <script src="<c:url value="/resources/manager/plugins/jquery-file-upload/js/vendor/load-image.min.js" />"></script>
    <!-- The Canvas to Blob plugin is included for image resizing functionality -->
    <script src="<c:url value="/resources/manager/plugins/jquery-file-upload/js/vendor/canvas-to-blob.min.js" />"></script>
    <!-- Select2 script -->
    <script src="<c:url value="/resources/manager/plugins/select2/dist/js/select2.min.js" />"></script>
    <!-- bootstrap-switch-master script -->
    <script src="<c:url value="/resources/manager/plugins/bootstrap-switch-master/dist/js/bootstrap-switch.js" />"></script>
    <!-- blueimp Gallery script -->
    <script src="<c:url value="/resources/manager/plugins/jquery-file-upload/blueimp-gallery/jquery.blueimp-gallery.min.js" />"></script>
    <!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
    <script src="<c:url value="/resources/manager/plugins/jquery-file-upload/js/jquery.iframe-transport.js" />"></script>
    <!-- The basic File Upload plugin -->
    <script src="<c:url value="/resources/manager/plugins/jquery-file-upload/js/jquery.fileupload.js" />"></script>
    <!-- The File Upload processing plugin -->
    <script src="<c:url value="/resources/manager/plugins/jquery-file-upload/js/jquery.fileupload-process.js" />"></script>
    <!-- The File Upload image preview & resize plugin -->
    <script src="<c:url value="/resources/manager/plugins/jquery-file-upload/js/jquery.fileupload-image.js" />"></script>
    <!-- The File Upload audio preview plugin -->
    <script src="<c:url value="/resources/manager/plugins/jquery-file-upload/js/jquery.fileupload-audio.js" />"></script>
    <!-- The File Upload video preview plugin -->
    <script src="<c:url value="/resources/manager/plugins/jquery-file-upload/js/jquery.fileupload-video.js" />"></script>
    <!-- The File Upload validation plugin -->
    <script src="<c:url value="/resources/manager/plugins/jquery-file-upload/js/jquery.fileupload-validate.js" />"></script>
    <!-- The File Upload user interface plugin -->
    <script src="<c:url value="/resources/manager/plugins/jquery-file-upload/js/jquery.fileupload-ui.js" />"></script>
    <!-- Jquery Currency plugin -->
    <script src="<c:url value="/resources/manager/plugins/jquery-currency/jquery.formatCurrency-1.4.0.js" />"></script>
    <script src="<c:url value="/resources/manager/plugins/jquery-currency/i18n/jquery.formatCurrency.es-CL.js" />"></script>
    <!--[if (gte IE 8)&(lt IE 10)]>
        <script src="<c:url value="/resources/manager/plugins/jquery-file-upload/js/cors/jquery.xdr-transport.js" />"></script>
    <![endif]-->
	<!-- ================== END PAGE LEVEL JS ================== -->
	<!-- ================== BEGIN PAGE toastr  ================== -->
	<script src="<c:url value="/resources/manager/plugins/toastr/toastr.js" />"></script>
	<!-- ================== END PAGE toastr  ================== -->
	<script src="<c:url value="/resources/app/tools/tools.js" />"></script>
	<script src="<c:url value="/resources/app/tools/tools-combo-box.js" />"></script>
	<script src="<c:url value="/resources/app/instructivo/detalle.js" />"></script>
	<script src="<c:url value="/resources/manager/js/apps.js" />"></script>
	
	<script src="<c:url value="/resources/manager/plugins/bootstrap-daterangepicker/daterangepicker.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/bootstrap-daterangepicker/moment.js" />"></script>
	
	<script src="<c:url value="/resources/manager/js/apps_login.js" />"></script>
	<script src="<c:url value="/resources/manager/js/form-plugins.demo.min.js" />"></script>
	
	
	<script src="<c:url value="/resources/manager/plugins/pace/pace.min.js" />"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->
	
	<script>
		$(document).ready(function() {
			App.init();
			Proyecto.init();
			FormPlugins.init();
			
		});
	</script>

</body>
</html>
