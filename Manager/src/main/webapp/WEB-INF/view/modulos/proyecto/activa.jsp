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
 int tipo = Integer.parseInt((String) request.getAttribute("tipo"));
 String urlRestServiceDelivery = (String) request.getAttribute("urlRestServiceDelivery");
 ObjConfigTools tol = (ObjConfigTools) request.getAttribute("tol");
 
 //lang option
 String langOption = (String) request.getAttribute("lang");
 URL url = application.getResource("/WEB-INF/lang/"+langOption+"/estudio.properties");
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
	<title>Gestor de Proyectos - GfK Adimark</title>
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
	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in"><span class="spinner"></span></div>
	<!-- end #page-loader -->
	
	<!-- begin #page-container -->
	
		<!-- begin #header -->
		<%@ include file="../../header_top_menu.jsp" %>
		<!-- end #header -->
		
		<!-- begin #sidebar -->
		<%@ include file="../../top_sidebar.jsp" %>
		<!-- end #sidebar -->
		
		
		<!-- end #sidebar -->
		<!-- Configuracion Interna  -->
		<input type="hidden" id="txt_idope_1" name="txt_idope_1" value="<%=  id_operacion %>" />
		<input type="hidden" id="txt_tipo_2" name="txt_tipo_2" value="<%=  tipo %>" />
		<input type="hidden" id="conf_01"  name="conf_01" value="<%= perfil.getEs_admin() %>" />
		<input type="hidden" id="conf_02"  name="conf_02" value="<%= urlRestServiceDelivery %>" />
		<input type="hidden" id="conf_03"  name="conf_03" value="<%= permiso_access %>" />
		<input type="hidden" id="conf_04"  name="conf_04" value="<%= login.getId_user() %>" />
		<input type="hidden" id="conf_05"  name="conf_05" value="<%= login.getId_perfil() %>" />
		<!-- BEGIN Config General -->
			<%@ include file="aditional-info.jsp" %>
			<!-- END Config General -->	 
		<!-- begin #content -->
		<div id="content" class="content">
			<!-- begin breadcrumb -->
			<ol class="breadcrumb pull-right">
				<li><a href="/Manager/home" target="_self">Home</a></li>
				<li><a href="#">Estudio</a></li>
				<li class="active" id="li_estudio">Activa Estudio</li>
			</ol>
			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<h1 class="page-header" >Mantenedor de Estudios <small id="h1_estudio">Activación de Estudio...</small></h1>
			<!-- end page-header -->
			
			<!-- begin row -->
			<div class="row">
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
                            <h4 class="panel-title">Detalle General</h4>
                        </div>
                        
                        <div class="panel-body panel-form" id="panel_01">
                           
                           
                           <form class="form-horizontal form-bordered">
                           	  <div class="row">
                           	  	<div class="form-group col-md-6">
                           	  		<h1 id="h1_1" style="margin-left: 20px;">ID: Cargando ...</h1>
                          			
                           		</div>
                           	  	<div class="form-group col-md-6 ">
                           	  		
                           	  		<a href="#" class="btn btn-success m-r-5" style="margin-top: 5px; text-align: center;" onclick="JavaScript: activeEstudio();" id="btn_activae">
                           	  			<i class="fa fa-check-square-o fa-2x pull-left"></i>
                           	  			Activar <br/> Estudio
                           	  		</a>
                           	  		
                           	  		<a href="#" class="btn btn-warning m-r-5" style="margin-top: 5px; text-align: center;" onclick="JavaScript: updateEstudio();" id="btn_updatee">
                           	  			<i class="fa fa-edit fa-2x pull-left"></i>
                           	  			Actualizar <br/> Estudio
                           	  		</a>
                           	  		<a href="#" class="btn btn-danger m-r-5" style="margin-top: 5px; text-align: center;" onclick="JavaScript: deleteEstudio();" id="btn_deletee">
                           	  			<i class="fa fa-eraser fa-2x pull-left"></i>
                           	  			Eliminar <br/> Estudio
                           	  		</a>
                           	  		<a href="#" class="btn btn-info m-r-5" style="margin-top: 5px; text-align: center;" onclick="JavaScript: printEstudio();">
                           	  			<i class="fa fa-print fa-2x pull-left"></i>
                           	  			Imprimir <br/> Estudio
                           	  		</a>
                           	  		<%if(tipo == 1){ %>
                           	  		<a href="/Manager/cotizacionService/Mantenedor" target="_self" class="btn btn-white m-r-5" style="margin-top: 5px; text-align: center;" onclick="JavaScript:;">
                           	  			<i class="fa fa-rotate-left fa-2x pull-left"></i>
                           	  			Volver Listar <br/> Cotizaciones
                           	  		</a>
                           	  		<%}else{
                           	  		%>
                           	  		<a href="/Manager/proyectoService/manProy" target="_self" class="btn btn-white m-r-5" style="margin-top: 5px; text-align: center;" onclick="JavaScript:;">
                           	  			<i class="fa fa-rotate-left fa-2x pull-left"></i>
                           	  			Volver Listar <br/> Estudios
                           	  		</a>
                           	  		<%
                           	  		} %>
                           	  	</div>
                           	  </div>
							   <div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Código CRM </label>
										<div class="col-md-8">
	                                        <input type="text" class="form-control" id="txt_detalle_01" name="txt_detalle_01" value="" />
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Código SAP </label>
										<div class="col-md-8">
	                                        <input type="text" class="form-control" id="txt_detalle_02" name="txt_detalle_02" value=""/>
										</div>
									</div>
								</div>
								
							   <div class="row">
									<div class="form-group col-md-12">
										<label class="control-label col-md-2" style="background: #f0f3f4; font-weight: bold;">Nombre del Estudio </label>
										<div class="col-md-10">
	                                        <input type="text" class="form-control" id="txt_detalle_04" name="txt_detalle_04" value=""/>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Industria</label>
										<div class="col-md-8">
	                                        <select class="form-control" id="txt_detalle_05" name="txt_detalle_05"></select>
	                                        
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Tipo Estudio </label>
										<div class="col-md-8">
	                                        <select class="form-control" id="txt_detalle_06" name="txt_detalle_06"></select>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Tipo Entrevista </label>
										<div class="col-md-8">
	                                        <select class="form-control" id="txt_detalle_07" name="txt_detalle_07"></select>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Tamaño de Muestra </label>
										<div class="col-md-8">
	                                        <input type="number" class="form-control" id="txt_detalle_08" name="txt_detalle_08" value=""/>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Cliente Solicitante</label>
										<div class="col-md-8">
	                                        <input type="text" class="form-control" id="txt_detalle_09" name="txt_detalle_09" value="" readonly="readonly"/>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Cliente a Facturar </label>
										<div class="col-md-8">
	                                        <input type="text" class="form-control" id="txt_detalle_10" name="txt_detalle_10" value="" readonly="readonly"/>
										</div>
									</div>
								 </div>
								 <!-- 
								 <div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Precio Venta $ </label>
										<div class="col-md-8">
	                                         <input type="text" class="form-control" id="txt_detalle_11" name="txt_detalle_11" value=""/>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Costo Aprobado $  </label>
										<div class="col-md-8">
	                                         <input type="text" class="form-control" id="txt_detalle_12" name="txt_detalle_12" value=""/>
										</div>
									</div>
								 </div>
								 <div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Weekly semana </label>
										<div class="col-md-8">
	                                        <input type="number" class="form-control" id="txt_detalle_13" name="txt_detalle_13" value=""/>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Weekly año </label>
										<div class="col-md-8">
	                                        <input type="number" class="form-control" id="txt_detalle_14" name="txt_detalle_14" value=""/>
										</div>
									</div>
								 </div>
								  -->
								 <div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Gerente de Estudio </label>
										<div class="col-md-8">
	                                        <select class="default-select2 form-control" id="txt_detalle_15" name="txt_detalle_15"></select>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Director de estudios </label>
										<div class="col-md-8">
	                                        
	                                        <select class="default-select2 form-control" id="txt_detalle_16" name="txt_detalle_16"></select>
										</div>
									</div>
								 </div>
								 <div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Jefe de Estudios </label>
										<div class="col-md-8">
	                                        
	                                        <select class="default-select2 form-control" id="txt_detalle_17" name="txt_detalle_17"></select>
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
										
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #faebcc; font-weight: bold;">Flujo </label>
										<div class="col-md-8">
	                                        <input type="text" class="form-control" style="font-size: 20px; color: orange; " id="txt_detalle_20" name="txt_detalle_20" value="" readonly="readonly"/>
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
            <!-- Begin row -->
            <div class="row">
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
                            <h4 class="panel-title">SET UP y Detalle de Estudio </h4>
                        </div>
                        
                        <div class="panel-body panel-form" id="panel_02">
                           <div class="panel-body panel-form" style="margin-top: 10px;" id="panel_04">
	                            <div class="col-md-12">
		                           <ul class="nav nav-tabs">
										<li class="active" id="tab1_01"><a href="#work-pss-1" data-toggle="tab" aria-expanded="true">SET-UP</a></li>
										<li class="" id="tab1_02"><a href="#work-pss-2" data-toggle="tab" aria-expanded="false">Info General</a></li>
										<li class="" id="tab1_03"><a href="#work-pss-3" data-toggle="tab" aria-expanded="false">Recoleccion</a></li>
										<li class="" id="tab1_04"><a href="#work-pss-4" data-toggle="tab" aria-expanded="false">Scripting</a></li>
										<li class="" id="tab1_05"><a href="#work-pss-5" data-toggle="tab" aria-expanded="false">Codificación</a></li>
										<li class="" id="tab1_06"><a href="#work-pss-6" data-toggle="tab" aria-expanded="false">Digitación</a></li>
										<li class="" id="tab1_07"><a href="#work-pss-7" data-toggle="tab" aria-expanded="false">Depuración</a></li>
										<li class="" id="tab1_08"><a href="#work-pss-8" data-toggle="tab" aria-expanded="false">Tabulación</a></li>
										<li class="" id="tab1_09"><a href="#work-pss-9" data-toggle="tab" aria-expanded="false">Analisis</a></li>
										<li class="" id="tab1_10"><a href="#work-pss-10" data-toggle="tab" aria-expanded="false">Entrega</a></li>
										<li class="" id="tab1_11"><a href="#work-pss-11" data-toggle="tab" aria-expanded="false">Bussness Case</a></li>
										<li class="" id="tab1_12"><a href="#work-pss-12" data-toggle="tab" aria-expanded="false">Cuestionario</a></li>
										<li class="" id="tab1_13"><a href="#work-pss-13" data-toggle="tab" aria-expanded="false">Cotización</a></li>
									</ul>
										<div class="tab-content">
											<div class="tab-pane fade active in" id="work-pss-1">
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->												
												<div class="row">
					                           	  	<div class="form-group col-md-6">
					                           	  		<h3 id="h2_1" style="margin-left: 20px; color: #f59c1a;">Set Up</h3>
					                           	  		<table class="table table-bordered">
							                                <thead>
							                                    <tr>
							                                        <th>Detalle</th>
							                                        <th>Valor</th>
							                                    </tr>
							                                </thead>
							                                <tbody>
							                                    <tr>
							                                        <td><input  type="checkbox" id="txt_detalle_21" name="txt_detalle_21" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch"></td>
							                                        <td><label class="control-label col-lg-4"  style="font-weight: bold;">Recolección</label></td>
							                                    </tr>
							                                    <tr>
							                                        <td><input  type="checkbox" id="txt_detalle_22" name="txt_detalle_22" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch"></td>
							                                        <td><label class="control-label col-lg-4" style="font-weight: bold;">Scripting</label></td>
							                                    </tr>
							                                    <tr>
							                                        <td><input  type="checkbox" id="txt_detalle_23" name="txt_detalle_23" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch"></td>
							                                        <td><label class="control-label col-lg-4" style="font-weight: bold;">Codificación</label></td>
							                                    </tr>
							                                    <tr>
							                                       <td><input  type="checkbox" id="txt_detalle_24" name="txt_detalle_24" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch"></td>
							                                        <td><label class="control-label col-lg-4" style="font-weight: bold;">Digitacion</label></td>
							                                    </tr>
							                                   <tr>
							                                       <td><input  type="checkbox" id="txt_detalle_25" name="txt_detalle_25" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch"></td>
							                                        <td><label class="control-label col-lg-4" style="font-weight: bold;">Depuración</label></td>
							                                    </tr>
							                                     <tr>
							                                       <td><input  type="checkbox" id="txt_detalle_26" name="txt_detalle_26" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch"></td>
							                                        <td><label class="control-label col-lg-4" style="font-weight: bold;">Tabulación</label></td>
							                                    </tr>
							                                    <tr>
							                                       <td><input  type="checkbox" id="txt_detalle_27" name="txt_detalle_27" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch"></td>
							                                        <td><label class="control-label col-lg-4" style="font-weight: bold;">Analisis</label></td>
							                                    </tr>
							                                    <tr>
							                                       <td><input  type="checkbox" id="txt_detalle_28" name="txt_detalle_28" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch"></td>
							                                        <td><label class="control-label col-lg-4" style="font-weight: bold;">Entrega</label></td>
							                                    </tr>
							                                    <tr>
							                                       <td><input  type="checkbox" id="txt_detalle_29" name="txt_detalle_29" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch"></td>
							                                        <td><label class="control-label col-lg-4" style="font-weight: bold;">Cuestionario</label></td>
							                                    </tr>
							                                    <tr>
							                                       <td><input  type="checkbox" id="txt_detalle_30" name="txt_detalle_30" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch"></td>
							                                        <td><label class="control-label col-lg-4" style="font-weight: bold;">Otros</label></td>
							                                    </tr>
							                                     <tr>
							                                       <td><input  type="checkbox" id="txt_detalle_31" name="txt_detalle_31" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch"></td>
							                                        <td><label class="control-label col-lg-4" style="font-weight: bold;">Viaticos</label></td>
							                                    </tr>
							                                </tbody>
							                            </table>
					                          			
					                           		</div>
					                           	  	<div class="form-group col-md-6 ">
					                           	  		<h3 id="h3_2" style="margin-left: 20px; color: #f59c1a;">Adicionales</h3>
					                           	  		<table class="table table-bordered">
							                                <thead>
							                                    <tr>
							                                        <th>Detalle</th>
							                                        <th>Valor</th>
							                                    </tr>
							                                </thead>
							                                <tbody>
							                                    <tr>
							                                        <td><!-- <input id="req_switch_06" type="checkbox" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">--></td>
							                                        <td>
							                                        	<label class="control-label col-lg-4" style="font-weight: bold;">% de Incidencias</label>
																		<div class="col-md-8">
									                                       <input type="number" id="txt_detalle_32" class="form-control"  value="" >
																		</div>
							                                        
							                                        </td>
							                                    </tr>
							                                    <tr>
							                                        <td><!--  <input id="req_switch_07" type="checkbox" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">--></td>
							                                        <td>
							                                        	<label class="control-label col-lg-4" style="font-weight: bold;">% Rebate</label>
																		<div class="col-md-8">
									                                       <input type="number" id="txt_detalle_33" class="form-control"  value="" >
																		</div>
							                                        </td>
							                                    </tr>
							                                    <tr>
							                                        <td><input id="txt_detalle_34" type="checkbox" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch"></td>
							                                        <td><label class="control-label col-lg-4" style="font-weight: bold;">Libro de Codigo</label></td>
							                                    </tr>
							                                    <tr>
							                                       <td><input id="txt_detalle_35" type="checkbox" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch"></td>
							                                        <td><label class="control-label col-lg-4" style="font-weight: bold;">Verbatim</label></td>
							                                    </tr>
							                                    <tr>
							                                       <td><input id="txt_detalle_36" type="checkbox" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch"></td>
							                                        <td><label class="control-label col-lg-4" style="font-weight: bold;">Pre-Proceso</label></td>
							                                    </tr>
							                                    <tr>
							                                        <td><!-- <input id="req_switch_11" type="checkbox" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch"> --></td>
							                                        <td>
							                                        	<label class="control-label col-lg-4" style="font-weight: bold;">Abiertas a Codificar</label>
																		<div class="col-md-8">
									                                       <input type="number" id="txt_detalle_37" class="form-control"  value="" >
																		</div>
							                                        
							                                        </td>
							                                    </tr>
							                                    <tr>
							                                        <td><!--<input id="req_switch_12" type="checkbox" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">--></td>
							                                        <td>
							                                        	<label class="control-label col-lg-4" style="font-weight: bold;">Otros a Codificar</label>
																		<div class="col-md-8">
									                                       <input type="number" id="txt_detalle_38" class="form-control"  value="" >
																		</div>
							                                        </td>
							                                    </tr>
							                                    <tr>
							                                        <td><!--<input id="req_switch_13" type="checkbox" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">--></td>
							                                        <td>
							                                        	<label class="control-label col-lg-4" style="font-weight: bold;">TMO</label>
							                                        	<div class="col-md-8">
									                                       <input type="number" id="txt_detalle_39" class="form-control"  value="" >
																		</div>
							                                        </td>
							                                    </tr>
							                                </tbody>
							                            </table>
					                           	  	</div>
					                           	  </div>
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->											
											</div>
											<div class="tab-pane fade" id="work-pss-2">
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->												
												<fieldset>
		                                            <legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew1") %></legend>
		                                            <!-- end row -->
		                                            <!-- end row -->
		                                            <div class="row">
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
															<div class="form-group block1">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_sector") %>"><%= lang1.getProperty("sector") %> <span style="color: red;">(*)</span></label>
																<select class="default-select2 form-control" id="txt_02" style="width: 100%;"   placeholder="<%= lang1.getProperty("sector") %>" >
																</select>
															</div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
															<div class="form-group" id="label_04">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_producto") %>"><%= lang1.getProperty("producto") %> <span style="color: red;">(*)</span></label>
																<select class="multiple-select2 form-control" id="txt_04" style="width: 100%;" multiple="" tabindex="-1" style="display: none;"   placeholder="<%= lang1.getProperty("producto") %>" >
																</select>
															</div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                            </div>
		                                            <!-- end row -->
		                                            <!--begin row -->
		                                            <div class="row">
		                                                
		                                                
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4" >
															<div class="form-group" id="label_07">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_tipocuentacliente") %>"><%= lang1.getProperty("tipocuentacliente") %> <span style="color: red;">(*)</span></label>
																<select class="default-select2 form-control" id="txt_07" style="width: 100%;" >
																</select>
															</div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                            </div>
		                                            <!-- end row -->
		                                             <!--begin row -->
		                                            <div class="row">
		                                                
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
															<div class="form-group" id="label_09">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_geografia") %>"><%= lang1.getProperty("geografia") %> <span style="color: red;">(*)</span></label>
																<select class="default-select2 form-control" id="txt_09" style="width: 100%;"   >
																</select>
															</div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
															<div class="form-group" id="group_01">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cantidad_paises") %>"><%= lang1.getProperty("cantidad_paises") %></label>
																<select class="default-select2 form-control" id="txt_10" data-parsley-group="wizard-step-1"  placeholder="<%= lang1.getProperty("cantidad_paises") %>" >
																	<option value="1">1</option>
																	<option value="2">2</option>
																	<option value="3">3</option>
																	<option value="4">4</option>
																	<option value="5">5</option>
																	<option value="6">6</option>
																	<option value="7">7</option>
																	<option value="8">8</option>
																	<option value="9">9</option>
																	<option value="10">10</option>
																</select>
															</div>
															
		                                                </div>
		                                                <!-- end col-4 -->
		                                            </div>
		                                            
		                                            <!-- begin row -->
		                                            <div class="row">
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
															<div class="form-group block1" id="label_11">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_estudio1") %>"><%= lang1.getProperty("estudio1") %> <span style="color: red;">(*)</span></label>
																<select class="default-select2 form-control" style="width: 100%;" id="txt_11"   placeholder="<%= lang1.getProperty("estudio1") %>" >
																</select>
															</div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                
		                                                
		                                                
		                                            </div>
		                                            <!-- end row -->
		                                            <!-- begin row -->
		                                            <div class="row">
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
															<div class="form-group block1" id="label_13">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_bookinglegalentity") %>"><%= lang1.getProperty("bookinglegalentity") %> <span style="color: red;">(*)</span></label>
																<select class="default-select2 form-control" id="txt_13"  style="width: 100%;" >
																</select>
															</div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
															<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_legalEntityName") %>"><%= lang1.getProperty("legalEntityName") %></label>
																<input type="text"  class="default-select2 form-control" id="txt_14"   placeholder="<%= lang1.getProperty("legalEntityName") %>" readonly="readonly" />
															</div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
															<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_centroCosto") %>"><%= lang1.getProperty("centroCosto") %> <span style="color: red;">(*)</span></label>
																<input type="number"  class="default-select2 form-control" id="txt_15"   placeholder="<%= lang1.getProperty("centroCosto") %>" />
															</div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                
		                                            </div>
		                                            <!-- end row -->
		                                            <!-- begin row -->
		                                            <div class="row">
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
															<div class="form-group block1">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_probabilidadproyecto") %>"><%= lang1.getProperty("probabilidadproyecto") %> <span style="color: red;">(*)</span></label>
																<input type="number"  class="default-select2 form-control" id="txt_16"   placeholder="<%= lang1.getProperty("probabilidadproyecto") %>" />
															</div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
															<div class="form-group" id="label_17">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_digital") %>"><%= lang1.getProperty("digital") %> <span style="color: red;">(*)</span></label>
																<select class="default-select2 form-control" id="txt_17" style="width: 100%;"   placeholder="<%= lang1.getProperty("digital") %>" >
																</select>
															</div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
															<div class="form-group" id="label_18">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_moneda") %>"><%= lang1.getProperty("moneda") %> <span style="color: red;">(*)</span></label>
																<select class="default-select2 form-control" id="txt_18" style="width: 100%;"   placeholder="<%= lang1.getProperty("moneda") %>" >
																</select>
															</div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                
		                                            </div>
		                                            <!-- end row -->
		                                            <legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew2") %></legend>
		                                            <!-- begin row -->
		                                            <div class="row">
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
															<div class="form-group block1">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_fechaProbInicioEstudio") %>"><%= lang1.getProperty("fechaProbInicioEstudio") %> <span style="color: red;">(*)</span></label>
																<input type="text" class="form-control calendar" id="txt_19"  placeholder="<%= lang1.getProperty("fechaProbInicioEstudio") %>" readonly="readonly"  />
															</div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
															<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_fechaProbEntregaEstudio") %>"><%= lang1.getProperty("fechaProbEntregaEstudio") %> <span style="color: red;">(*)</span></label>
																<input type="text" class="form-control calendar" id="txt_20"  placeholder="<%= lang1.getProperty("fechaProbEntregaEstudio") %>" readonly="readonly"  />
															</div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
															
		                                                </div>
		                                                <!-- end col-4 -->
		                                                
		                                            </div>
		                                            <!-- end row -->
		                                            <legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew3") %></legend>
		                                            <!-- begin row -->
		                                            <div class="row">
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
															<div class="form-group block1">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_deadPresentacionEquipo") %>"><%= lang1.getProperty("deadPresentacionEquipo") %> <span style="color: red;">(*)</span></label>
																<input type="text" class="form-control calendar" id="txt_21"  placeholder="<%= lang1.getProperty("deadPresentacionEquipo") %>"  />
															</div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
															<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_deadPresentacionGps") %>"><%= lang1.getProperty("deadPresentacionGps") %> <span style="color: red;">(*)</span></label>
																<input type="text" class="form-control calendar" id="txt_22"  placeholder="<%= lang1.getProperty("deadPresentacionGps") %>"  />
															</div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
															<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_deadPresentacionCliente") %>"><%= lang1.getProperty("deadPresentacionCliente") %> <span style="color: red;">(*)</span></label>
																<input type="text" class="form-control calendar" id="txt_23"  placeholder="<%= lang1.getProperty("deadPresentacionCliente") %>"  />
															</div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                            </div>
		                                            
							                        <legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew6") %></legend>
		                                            <div class="row">
							                           	  	<div class="col-md-6 ">
							                           	  		<div class="form-group">
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_ob_objetivo") %>"><%= lang1.getProperty("ob_objetivo") %></label>
																	<textarea class="form-control"  id="txt_27" placeholder="<%= lang1.getProperty("ob_objetivo") %>" rows="5"></textarea>
																	
																</div>
							                           	  	</div>
							                           	  	<div class="col-md-6 ">
							                           	  		<div class="form-group">
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_ob_descripcion") %>"><%= lang1.getProperty("ob_descripcion") %></label>
																	<textarea class="form-control"  id="txt_28" placeholder="<%= lang1.getProperty("ob_descripcion") %>" rows="5"></textarea>
																</div>
							                           	  	</div>
							                           	  	
							                           	  	
							                        </div>
		                                            
												</fieldset>
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->												
												
											</div>
											<div class="tab-pane fade" id="work-pss-3">
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
												<fieldset>
													<legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew7") %></legend>
		                                            <!-- begin row -->
		                                            <div class="row">
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-3">
															<div class="form-group">
																<div class="form-group">
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_lenguaje") %>"><%= lang1.getProperty("lenguaje") %></label>
																	<select class="default-select2 form-control" id="txt_39" data-parsley-group="wizard-step-2" style="width: 100%;" >
																	</select>
																</div>
															</div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-3 -->
		                                                <div class="col-md-3">
															<div class="form-group" id="gtxt_40">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_frecuencia") %>"><%= lang1.getProperty("frecuencia") %></label>
																<select class="default-select2 form-control" id="txt_40" data-parsley-group="wizard-step-2"  style="width: 100%;" >
																</select>
															</div>
		                                                </div>
		                                                <!-- end col-3 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-3">
															<div class="form-group">
																<div class="form-group" id="gtxt_41">
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_frecuencia_ola") %>"><%= lang1.getProperty("frecuencia_ola") %></label>
																	<select class="default-select2 form-control" id="txt_41" data-parsley-group="wizard-step-2"  style="width: 100%;" >
																	</select>
																</div>
																<div class="form-group" id="gtxt_42">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_otro_frecuencia") %>"><% //lang1.getProperty("otro_frecuencia") %></label>
																	<input type="text"  class="default-select2 form-control" id="txt_42"  data-parsley-group="wizard-step-2" placeholder="<%= lang1.getProperty("otro_frecuencia") %>" />
																</div>
															</div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-3">
															<div class="form-group" id="gtxt_43">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_frecuencia_cantidad") %>"><%= lang1.getProperty("frecuencia_cantidad") %></label>
																<input type="number"  class="default-select2 form-control" id="txt_43"  data-parsley-group="wizard-step-2" placeholder="<%= lang1.getProperty("frecuencia_cantidad") %>" />
															</div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                            	
		                                                <!-- end col-3 -->
		                                           </div>
		                                           <div class="row">
		                                                <!-- begin col-3 -->
		                                                <div class="col-md-3">
															<div class="form-group">
																<div class="form-group">
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_modo") %>"><%= lang1.getProperty("modo") %></label>
																	<select class="default-select2 form-control" id="txt_44" data-parsley-group="wizard-step-2" style="width: 100%;" >
																	</select>
																</div>
															</div>
		                                                </div>
		                                                <!-- end col-3 -->
		                                                <!-- begin col-3 -->
		                                                <div class="col-md-3">
															<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_subModo") %>"><%= lang1.getProperty("subModo") %></label>
																<select class="default-select2 form-control" id="txt_45" data-parsley-group="wizard-step-2"  style="width: 100%;" >
															    </select>
															</div>
		                                                </div>
		                                                <!-- end col-3 -->
		                                                <div class="col-md-3">
															
		                                                </div>
		                                                <!-- end col-3 -->
		                                                <div class="col-md-3">
															
		                                                </div>
		                                                <!-- end col-3 -->
		                                            </div>
		                                            <div class="row">
		                                            	<div class="col-md-4 ">
						                           	  		<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_duracion_introduccion") %>"><%= lang1.getProperty("duracion_introduccion") %></label><br/>
																<input type="number"  class="default-select2 form-control" id="txt_57"  data-parsley-group="wizard-step-2" placeholder="<%= lang1.getProperty("duracion_introduccion") %>" />
															</div>
						                           	  	</div>
						                           	  	<div class="col-md-4 ">
						                           	  		<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_duracion_cuestionario") %>"><%= lang1.getProperty("duracion_cuestionario") %></label>
																<input type="number"  class="default-select2 form-control" id="txt_58"  data-parsley-group="wizard-step-2" placeholder="<%= lang1.getProperty("duracion_cuestionario") %>" />
															</div>
						                           	  	</div>
						                           	  	<div class="col-md-4 ">
						                           	  		<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_duracion_total") %>"><%= lang1.getProperty("duracion_total") %></label><br/>
																<input type="number"  class="default-select2 form-control" id="txt_59"  data-parsley-group="wizard-step-2" placeholder="<%= lang1.getProperty("duracion_total") %>" readonly="readonly" />
															</div>
						                           	  	</div>
						                           	  	
		                                            </div>
		                                            <!-- end row -->
		                                            <legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew8") %></legend>
		                                            <div class="row">
		                                                <!-- begin col-3 -->
		                                                <div class="col-md-3">
															<div class="form-group">
																<div class="form-group">
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_prueba_requiere") %>"><%= lang1.getProperty("prueba_requiere") %></label><br/>
																	<input id="txt_48" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
																</div>
															</div>
		                                                </div>
		                                                <!-- end col-3 -->
		                                                <!-- begin col-3 -->
		                                                <div class="col-md-3">
															<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_prueba_requiere_compra") %>"><%= lang1.getProperty("prueba_requiere_compra") %></label><br/>
																<input id="txt_49" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
															</div>
		                                                </div>
		                                                <!-- end col-3 -->
		                                                <div class="col-md-3">
															<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_tipo_test") %>"><%= lang1.getProperty("tipo_test") %></label>
																<select class="default-select2 form-control" id="txt_50" data-parsley-group="wizard-step-2"  style="width: 100%;" >
															    </select>
															</div>
		                                                </div>
		                                                <!-- end col-3 -->
		                                                <div class="col-md-3">
															<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_requiere_retorno") %>"><%= lang1.getProperty("requiere_retorno") %></label><br/>
																<input id="txt_51" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
															</div>
		                                                </div>
		                                                <!-- end col-3 -->
		                                            </div>
		                                            <!-- end row -->
		                                            <div class="row">
		                                            	<div class="col-md-6 ">
						                           	  		<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_descripcion_test") %>"><%= lang1.getProperty("descripcion_test") %></label>
																<textarea class="form-control"  id="txt_52" data-parsley-group="wizard-step-2" placeholder="<%= lang1.getProperty("descripcion_test") %>" rows="5"></textarea>
															</div>
						                           	  	</div>
						                           	  	<div class="col-md-6 ">
						                           	  		
						                           	  	</div>
		                                            </div>
		                                            <legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew24") %></legend>
		                                            <div class="row">
		                                            	<div class="col-md-3 ">
						                           	  		<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_reclutamiento") %>"><%= lang1.getProperty("reclutamiento") %></label><br/>
																<input id="txt_53" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
															</div>
						                           	  	</div>
						                           	  	<div class="col-md-3 ">
						                           	  		<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_modo_reclutamiento") %>"><%= lang1.getProperty("modo_reclutamiento") %></label>
																<select class="default-select2 form-control" id="txt_54" data-parsley-group="wizard-step-2"  style="width: 100%;">
															    </select>
															</div>
						                           	  	</div>
						                           	  	<div class="col-md-3 ">
						                           	  		<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_sub_cuota") %>"><%= lang1.getProperty("sub_cuota") %></label><br/>
																<input id="txt_55" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
															</div>
						                           	  	</div>
						                           	  	<div class="col-md-3 ">
						                           	  		<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_des_sub_cuota") %>"><%= lang1.getProperty("des_sub_cuota") %></label>
																<textarea class="form-control"  id="txt_56"data-parsley-group="wizard-step-2" placeholder="<%= lang1.getProperty("des_sub_cuota") %>" rows="5"></textarea>
															</div>
						                           	  	</div>
		                                            </div>
		                                            
		                                            
		                                            <legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew8_1") %></legend>
		                                            <div class="row">
		                                            	<div class="col-md-3 ">
						                           	  		<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_tipo_entrevistado") %>"><%= lang1.getProperty("tipo_entrevistado") %></label><br/>
																<select class="default-select2 form-control" id="txt_60" data-parsley-group="wizard-step-2" style="width: 100%;" >
															    </select>
															</div>
						                           	  	</div>
						                           	  	<div class="col-md-6 ">
						                           	  		<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_descripcion_tipo_entrevistado") %>"><%= lang1.getProperty("descripcion_tipo_entrevistado") %></label>
																<textarea class="form-control"  id="txt_61" data-parsley-group="wizard-step-2" placeholder="<%= lang1.getProperty("descripcion_tipo_entrevistado") %>" rows="5"></textarea>
															</div>
						                           	  	</div>
						                           	  	
		                                            </div>
		                                            <div class="row">
		                                            	<div class="col-md-3 ">
						                           	  		<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_identificar_cliente_respondiente") %>"><%= lang1.getProperty("identificar_cliente_respondiente") %></label><br/>
																<input id="txt_62" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
															</div>
						                           	  	</div>
						                           	  	<div class="col-md-3 ">
						                           	  		<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_fsi") %>"><%= lang1.getProperty("fsi") %></label><br/>
																<select class="default-select2 form-control" id="txt_63" data-parsley-group="wizard-step-2" style="width: 100%;"   >
															    </select>
															</div>
						                           	  	</div>
						                           	  	
		                                            </div>
		                                            <div class="row">
		                                            	<div class="col-md-3 ">
						                           	  		<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_requiere_piloto") %>"><%= lang1.getProperty("requiere_piloto") %></label><br/>
																<input id="txt_64" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
															</div>
						                           	  	</div>
						                           	  	<div class="col-md-3 ">
						                           	  		<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_descripcion_fsi") %>"><%= lang1.getProperty("descripcion_fsi") %></label><br/>
																<textarea class="form-control"  id="txt_65"data-parsley-group="wizard-step-2" placeholder="<%= lang1.getProperty("descripcion_fsi") %>" rows="5"></textarea>
															</div>
						                           	  	</div>
						                           	  	
		                                            </div>
		                                             <div class="row">
		                                            	<div class="col-md-3 ">
						                           	  		<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_ejemplo_diseno") %>"><%= lang1.getProperty("ejemplo_diseno") %></label><br/>
																<select class="default-select2 form-control" id="txt_66" data-parsley-group="wizard-step-2"  style="width: 100%;" >
															    </select>
															</div>
						                           	  	</div>
						                           	  	<div class="col-md-3 ">
						                           	  		<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_ejemplo_diseno_especifique") %>"><%= lang1.getProperty("ejemplo_diseno_especifique") %></label><br/>
																<textarea class="form-control"  id="txt_67"data-parsley-group="wizard-step-2" placeholder="<%= lang1.getProperty("ejemplo_diseno_especifique") %>" rows="5"></textarea>
															</div>
						                           	  		<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_ejemplo_diseno_randomizacion") %>"><%= lang1.getProperty("ejemplo_diseno_randomizacion") %></label><br/>
																<select class="default-select2 form-control" id="txt_68" data-parsley-group="wizard-step-2"  style="width: 100%;" >
															    </select>
															</div>
															<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_ejemplo_diseno_randomroute") %>"><%= lang1.getProperty("ejemplo_diseno_randomroute") %></label><br/>
																<select class="default-select2 form-control" id="txt_69" data-parsley-group="wizard-step-2"  style="width: 100%;" >
															    </select>
															</div>
															<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_ejemplo_diseno_adressroute") %>"><%= lang1.getProperty("ejemplo_diseno_adressroute") %></label><br/>
																<select class="default-select2 form-control" id="txt_70" data-parsley-group="wizard-step-2"  style="width: 100%;" >
															    </select>
															</div>
															<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_ejemplo_diseno_admroute") %>"><%= lang1.getProperty("ejemplo_diseno_admroute") %></label><br/>
																<select class="default-select2 form-control" id="txt_71" data-parsley-group="wizard-step-2"  style="width: 100%;" >
															    </select>
															</div>
						                           	  	</div>
						                           	  	
		                                            </div>
		                                            <div class="row">
		                                            	<div class="col-md-3 ">
						                           	  		<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_metodo_seleccion_respon") %>"><%= lang1.getProperty("metodo_seleccion_respon") %></label><br/>
																<select class="default-select2 form-control" id="txt_72" data-parsley-group="wizard-step-2"  style="width: 100%;" >
															    </select>
															</div>
						                           	  	</div>
						                           	  	<div class="col-md-3 ">
						                           	  		<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_metodo_seleccion_respon_npreg") %>"><%= lang1.getProperty("metodo_seleccion_respon_npreg") %></label><br/>
																<input type="number"  class="default-select2 form-control" id="txt_73"  data-parsley-group="wizard-step-2" placeholder="<%= lang1.getProperty("metodo_seleccion_respon_npreg") %>"  />
															</div>
						                           	  	</div>
						                           	</div>
						                           	<div class="row">
		                                            	<div class="col-md-6 ">
						                           	  		<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_metodo_objetivo_respuesta") %>"><%= lang1.getProperty("metodo_objetivo_respuesta") %></label><br/>
																<textarea class="form-control"  id="txt_74"data-parsley-group="wizard-step-2" placeholder="<%= lang1.getProperty("metodo_objetivo_respuesta") %>" rows="5"></textarea>
															</div>
						                           	  	</div>
						                           	  	<div class="col-md-6 ">
						                           	  		<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_metodo_informacion_probalistico") %>"><%= lang1.getProperty("metodo_informacion_probalistico") %></label><br/>
																<textarea class="form-control"  id="txt_75"data-parsley-group="wizard-step-2" placeholder="<%= lang1.getProperty("metodo_informacion_probalistico") %>" rows="5"></textarea>
															</div>
						                           	  	</div>
						                           	</div>
						                           	<legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew25") %></legend>
						                           	<div class="row">
		                                            	<div class="col-md-3 ">
						                           	  		<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_metodo_necesita_materiales") %>"><%= lang1.getProperty("metodo_necesita_materiales") %></label><br/>
																<input id="txt_76" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
															</div>
						                           	  	</div>
						                           	  	<div class="col-md-6 ">
						                           	  		<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_metodo_necesita_materiales_especifique") %>"><%= lang1.getProperty("metodo_necesita_materiales_especifique") %></label><br/>
																<textarea class="form-control"  id="txt_77"data-parsley-group="wizard-step-2" placeholder="<%= lang1.getProperty("metodo_necesita_materiales_especifique") %>" rows="5"></textarea>
															</div>
						                           	  	</div>
						                           	</div>
						                           	<legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew26") %></legend>
						                           	<div class="row">
		                                            	<div class="col-md-3 ">
						                           	  		<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_metodo_requiere_sesion_info") %>"><%= lang1.getProperty("metodo_requiere_sesion_info") %></label><br/>
																<input id="txt_78" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
															</div>
						                           	  	</div>
						                           	  	<div class="col-md-3 ">
						                           	  		<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_metodo_cliente_insentivo") %>"><%= lang1.getProperty("metodo_cliente_insentivo") %></label><br/>
																<input id="txt_79" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
															</div>
						                           	  	</div>
						                           	  	<div class="col-md-4 ">
						                           	  		<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_metodo_cliente_insentivo_responzable") %>"><%= lang1.getProperty("metodo_cliente_insentivo_responzable") %></label><br/>
																<select class="default-select2 form-control" id="txt_80" data-parsley-group="wizard-step-2"  style="width: 100%;" >
															    </select>
															</div>
															<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_metodo_cliente_insentivo_desc") %>"><%= lang1.getProperty("metodo_cliente_insentivo_desc") %></label><br/>
																<textarea class="form-control"  id="txt_81"data-parsley-group="wizard-step-2" placeholder="<%= lang1.getProperty("metodo_cliente_insentivo_desc") %>" rows="5"></textarea>
															</div>
						                           	  	</div>
						                           	</div>
						                           	<div id="cati_recol">
						                           	<legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew8_2") %></legend>
						                           	<h4><%= lang1.getProperty("itemgroupNew8_2_1") %></h4>
						                           	<div class="row">
							                           	  	<div class="col-md-2 ">
							                           	  		<div class="form-group">
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_setup_cati_rddline") %>"><%= lang1.getProperty("setup_cati_rddline") %></label><br/>
																	<input id="txt_82" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
																</div>
							                           	  	</div>
							                           	  	<div class="col-md-2 ">
							                           	  		<div class="form-group">
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_setup_cati_rddcell") %>"><%= lang1.getProperty("setup_cati_rddcell") %></label><br/>
																	<input id="txt_83" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
																</div>
							                           	  	</div>
							                           	  	<div class="col-md-2 ">
							                           	  		<div class="form-group">
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_setup_cati_target") %>"><%= lang1.getProperty("setup_cati_target") %></label><br/>
																	<input id="txt_84" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
																</div>
							                           	  	</div>
							                           	  	<div class="col-md-2 ">
							                           	  		<div class="form-group">
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_setup_cati_clientfone") %>"><%= lang1.getProperty("setup_cati_clientfone") %></label><br/>
																	<input id="txt_85" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
																</div>
							                           	  	</div>
							                           	  	<div class="col-md-2 ">
							                           	  		<div class="form-group">
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_setup_cati_other") %>"><%= lang1.getProperty("setup_cati_other") %></label><br/>
																	<input id="txt_86" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
																</div>
							                           	  	</div>
							                         </div>
							                         <div class="row">
							                           	 <div class="col-md-4 ">
							                           	  	<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cati_definicion_ej") %>"><%= lang1.getProperty("cati_definicion_ej") %></label><br/>
																<select class="default-select2 form-control" id="txt_87" data-parsley-group="wizard-step-2"  style="width: 100%;">
															    </select>
															</div>
															
							                           	  </div>
							                           	  <div class="col-md-4 ">
							                           	  		<div class="form-group">
																    <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cati_definicion_definicion") %>"><%= lang1.getProperty("cati_definicion_definicion") %></label><br/>
																    <textarea class="form-control"  id="txt_88"data-parsley-group="wizard-step-2" placeholder="<%= lang1.getProperty("cati_definicion_definicion") %>" rows="5"></textarea>
															    </div>
															    <div class="form-group">
																    <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cati_definicion_otro") %>"><%= lang1.getProperty("cati_definicion_otro") %></label><br/>
																    <textarea class="form-control"  id="txt_89"data-parsley-group="wizard-step-2" placeholder="<%= lang1.getProperty("cati_definicion_otro") %>" rows="5"></textarea>
															    </div>
							                           	  </div>
							                           	  	
							                         </div>
							                         <div class="row">
							                           	 <div class="col-md-6 ">
							                           	  	<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cati_definicion_ejemplo_obj") %>"><%= lang1.getProperty("cati_definicion_ejemplo_obj") %></label><br/>
																<textarea class="form-control"  id="txt_90"data-parsley-group="wizard-step-2" placeholder="<%= lang1.getProperty("cati_definicion_ejemplo_obj") %>" rows="5"></textarea>
															</div>
															
							                           	  </div>
							                           	  <div class="col-md-6 ">
							                           	  		
							                           	  </div>
							                         </div>
							                         <div class="row">
							                           	  	<div class="col-md-3 ">
							                           	  		<div class="form-group">
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cati_por_cell_espectativa") %>"><%= lang1.getProperty("cati_por_cell_espectativa") %></label><br/>
																	<input type="number"  class="default-select2 form-control" id="txt_91"  data-parsley-group="wizard-step-2" placeholder="<%= lang1.getProperty("cati_por_cell_espectativa") %>"  />
																</div>
							                           	  	</div>
							                           	  	<div class="col-md-2 ">
							                           	  		<div class="form-group">
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cati_n_cell_dedicada") %>"><%= lang1.getProperty("cati_n_cell_dedicada") %></label><br/>
																	<input id="txt_92" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
																</div>
							                           	  	</div>
							                           	  	<div class="col-md-2 ">
							                           	  		<div class="form-group">
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cati_req_del_duplicados") %>"><%= lang1.getProperty("cati_req_del_duplicados") %></label><br/>
																	<input id="txt_93" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
																</div>
							                           	  	</div>
							                           	  	
							                         </div>
							                         <div class="row">
							                           	  	<div class="col-md-3 ">
							                           	  		<div class="form-group">
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cati_total_num_file") %>"><%= lang1.getProperty("cati_total_num_file") %></label><br/>
																	<input type="number"  class="default-select2 form-control" id="txt_94"  data-parsley-group="wizard-step-2" placeholder="<%= lang1.getProperty("cati_total_num_file") %>"  />
																</div>
							                           	  	</div>
							                           	  	<div class="col-md-3 ">
							                           	  		<div class="form-group">
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cati_total_num_record_exp") %>"><%= lang1.getProperty("cati_total_num_record_exp") %></label><br/>
																	<input type="number"  class="default-select2 form-control" id="txt_95"  data-parsley-group="wizard-step-2" placeholder="<%= lang1.getProperty("cati_total_num_record_exp") %>"  />
																</div>
							                           	  	</div>
							                           	  	<div class="col-md-3 ">
							                           	  		<div class="form-group">
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cati_por_useable_record") %>"><%= lang1.getProperty("cati_por_useable_record") %></label><br/>
																	<input type="number"  class="default-select2 form-control" id="txt_96"  data-parsley-group="wizard-step-2" placeholder="<%= lang1.getProperty("cati_por_useable_record") %>"  />
																</div>
							                           	  	</div>
							                           	  	<div class="col-md-3 ">
								                           	  	<div class="form-group">
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cati_frec_file_delivery") %>"><%= lang1.getProperty("cati_frec_file_delivery") %></label><br/>
																	<select class="default-select2 form-control" id="txt_97" data-parsley-group="wizard-step-2"  style="width: 100%;" >
																    </select>
																</div>
																<div class="form-group">
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cati_frec_file_delivery_otro") %>"><%= lang1.getProperty("cati_frec_file_delivery_otro") %></label><br/>
																	<input type="text"  class="default-select2 form-control" id="txt_98"  data-parsley-group="wizard-step-2" placeholder="<%= lang1.getProperty("cati_frec_file_delivery_otro") %>"  />
																</div>
															</div>
							                           	  	
							                         </div>
							                        </div>
												</fieldset>
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->												
											</div>
											<div class="tab-pane fade" id="work-pss-4">
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
												<fieldset>
													<legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew9") %></legend>
			                                            <!-- begin row -->
			                                            <div class="row">
			                                                <!-- begin col-3 -->
			                                                <div class="col-md-3">
			                                                    <div class="form-group">
			                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_scripting") %>"><%= lang1.getProperty("scripting") %></label><br/>
																	<input id="txt_99" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
			                                                    </div>
			                                                </div>
			                                                <!-- end col-3 -->
			                                                <!-- begin col-3 -->
			                                                <div class="col-md-3">
			                                                    
			                                                    
			                                                </div>
			                                                <!-- end col-3 -->
			                                                <!-- begin col-3 -->
			                                                <div class="col-md-3">
			                                                    
			                                                    
			                                                </div>
			                                                <!-- end col-3 -->
			                                                <!-- begin col-3 -->
			                                                <div class="col-md-3">
			                                                   
			                                                    
			                                                </div>
			                                                <!-- end col-3 -->
			                                            </div>
			                                            <!-- end row -->
												</fieldset>
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->												
											</div>
											<div class="tab-pane fade" id="work-pss-5">
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
												<fieldset>
													<legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew10") %></legend>
			                                            <!-- begin row -->
			                                            <div class="row">
			                                                <!-- begin col-3 -->
			                                                <div class="col-md-3">
			                                                    <div class="form-group">
			                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_codificacion") %>"><%= lang1.getProperty("codificacion") %></label><br/>
																	<input id="txt_100" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
			                                                    </div>
			                                                </div>
			                                                <!-- end col-3 -->
			                                                <!-- begin col-3 -->
			                                                <div class="col-md-3">
			                                                    <div class="form-group">
			                                                        
			                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_precodigo") %>"><%= lang1.getProperty("precodigo") %></label><br/>
																	<input id="txt_101" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
			                                                    </div>
			                                                    
			                                                </div>
			                                                <!-- end col-3 -->
			                                                <!-- begin col-3 -->
			                                                <div class="col-md-3">
			                                                    <div class="form-group">
			                                                        
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_preguntas_abiertas_codificar") %>"><%= lang1.getProperty("preguntas_abiertas_codificar") %></label>
																	<input type="number"  class="default-select2 form-control" id="txt_102"  data-parsley-group="wizard-step-3" placeholder="<%= lang1.getProperty("preguntas_abiertas_codificar") %>" />
																</div>
			                                                    
			                                                </div>
			                                                <!-- end col-3 -->
			                                                <!-- begin col-3 -->
			                                                <div class="col-md-3">
			                                                    <div class="form-group">
			                                                        
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_preguntas_abiertas_codificar_otro") %>"><%= lang1.getProperty("preguntas_abiertas_codificar_otro") %></label>
																	<input type="number"  class="default-select2 form-control" id="txt_103"  data-parsley-group="wizard-step-3" placeholder="<%= lang1.getProperty("preguntas_abiertas_codificar_otro") %>" />
																</div>
			                                                    
			                                                </div>
			                                                <!-- end col-3 -->
			                                            </div>
			                                            <!-- end row -->
			                                            <!-- begin row -->
			                                            <div class="row">
			                                                <!-- begin col-3 -->
			                                                <div class="col-md-3">
			                                                    <div class="form-group">
			                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_codigo_abierto_entregado_a_cliente") %>"><%= lang1.getProperty("codigo_abierto_entregado_a_cliente") %></label><br/>
																	<input id="txt_104" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
			                                                    </div>
			                                                </div>
			                                                <!-- end col-3 -->
			                                                <!-- begin col-3 -->
			                                                <div class="col-md-3">
			                                                    <div class="form-group">
			                                                        
			                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_traducir_codigos") %>"><%= lang1.getProperty("traducir_codigos") %></label><br/>
																	<input id="txt_105" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
			                                                    </div>
			                                                    
			                                                </div>
			                                                <!-- end col-3 -->
			                                                <!-- begin col-3 -->
			                                                <div class="col-md-3">
			                                                    <div class="form-group">
			                                                        
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_especifique_lengua_de_traduccion") %>"><%= lang1.getProperty("especifique_lengua_de_traduccion") %></label>
																	<select class="default-select2 form-control" id="txt_106" data-parsley-group="wizard-step-3"  style="width: 100%" >
																    </select>
																</div>
			                                                    
			                                                </div>
			                                                <!-- end col-3 -->
			                                                <!-- begin col-3 -->
			                                                <div class="col-md-3">
			                                                    <div class="form-group">
			                                                        
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_editar_codigos") %>"><%= lang1.getProperty("editar_codigos") %></label><br/>
																	<input id="txt_107" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
																</div>
			                                                    
			                                                </div>
			                                                <!-- end col-3 -->
			                                            </div>
			                                            <!-- end row -->
			                                             <!-- end row -->
			                                            <div class="row">
			                                            	<div class="col-md-6 ">
							                           	  		<div class="form-group">
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_indique_nivel_de_edicion") %>"><%= lang1.getProperty("indique_nivel_de_edicion") %></label>
																	<textarea class="form-control"  id="txt_108" data-parsley-group="wizard-step-3" placeholder="<%= lang1.getProperty("indique_nivel_de_edicion") %>" rows="5"></textarea>
																</div>
							                           	  	</div>
							                           	  	<div class="col-md-6 ">
							                           	  		<div class="form-group">
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_otra_informacion_de_codificacion") %>"><%= lang1.getProperty("otra_informacion_de_codificacion") %></label>
																	<textarea class="form-control"  id="txt_109" data-parsley-group="wizard-step-3" placeholder="<%= lang1.getProperty("otra_informacion_de_codificacion") %>" rows="5"></textarea>
																</div>
							                           	  	</div>
			                                            </div>
												</fieldset>
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->	
											</div>
											<div class="tab-pane fade" id="work-pss-6">
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
												<fieldset>
													<legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew11") %></legend>
		                                            <!-- begin row -->
		                                            <div class="row">
		                                                <!-- begin col-3 -->
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_digitacion") %>"><%= lang1.getProperty("digitacion") %></label><br/>
																<input id="txt_110" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
		                                                    </div>
		                                                </div>
		                                                <!-- end col-3 -->
		                                                <!-- begin col-3 -->
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_verbatim_capture") %>"><%= lang1.getProperty("verbatim_capture") %></label><br/>
																<input id="txt_111" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
		                                                    </div>
		                                                    
		                                                </div>
		                                                <!-- end col-3 -->
		                                                <!-- begin col-3 -->
		                                                <div class="col-md-3">
		                                                    
		                                                    
		                                                </div>
		                                                <!-- end col-3 -->
		                                                <!-- begin col-3 -->
		                                                <div class="col-md-3">
		                                                    
		                                                    
		                                                </div>
		                                                <!-- end col-3 -->
		                                            </div>
		                                            <!-- end row -->
												</fieldset>
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->												
											</div>
											<div class="tab-pane fade" id="work-pss-7">
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
												<fieldset>
													<legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew12") %></legend>
		                                            <!-- begin row -->
		                                            <div class="row">
		                                                <!-- begin col-3 -->
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_depuracion") %>"><%= lang1.getProperty("depuracion") %></label><br/>
																<input id="txt_112" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
		                                                    </div>
		                                                </div>
		                                                <!-- end col-3 -->
		                                                <!-- begin col-3 -->
		                                                <div class="col-md-3">
		                                                    
		                                                    
		                                                </div>
		                                                <!-- end col-3 -->
		                                                <!-- begin col-3 -->
		                                                <div class="col-md-3">
		                                                    
		                                                    
		                                                </div>
		                                                <!-- end col-3 -->
		                                                <!-- begin col-3 -->
		                                                <div class="col-md-3">
		                                                    
		                                                    
		                                                </div>
		                                                <!-- end col-3 -->
		                                            </div>
		                                            <!-- end row -->
												</fieldset>
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->												
											</div>
											<div class="tab-pane fade" id="work-pss-8">
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
												<fieldset>
													<legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew13") %></legend>
		                                            <!-- begin row -->
		                                            <div class="row">
		                                                <!-- begin col-3 -->
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_tabulacion") %>"><%= lang1.getProperty("tabulacion") %></label><br/>
																<input id="txt_113" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
		                                                    </div>
		                                                </div>
		                                                <!-- end col-3 -->
		                                                <!-- begin col-3 -->
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_especifique_los_datos_a_entregar") %>"><%= lang1.getProperty("especifique_los_datos_a_entregar") %></label>
																<select class="default-select2 form-control" id="txt_114" data-parsley-group="wizard-step-3"  style="width: 100%" >
															    </select>
															</div>
		                                                    
		                                                </div>
		                                                <!-- end col-3 -->
		                                                <!-- begin col-3 -->
		                                                <div class="col-md-3">
		                                                     <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_formato_del_archivo_de_datos") %>"><%= lang1.getProperty("formato_del_archivo_de_datos") %></label>
																<select class="default-select2 form-control" id="txt_115" data-parsley-group="wizard-step-3"  style="width: 100%" >
															    </select>
															</div>
		                                                    
		                                                </div>
		                                                <!-- end col-3 -->
		                                                <!-- begin col-3 -->
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_tablas_preliminares_necesarios") %>"><%= lang1.getProperty("tablas_preliminares_necesarios") %></label><br/>
																<input id="txt_116" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
		                                                    </div>
		                                                    
		                                                </div>
		                                                <!-- end col-3 -->
		                                            </div>
		                                            <!-- end row -->
		                                            <!-- begin row -->
		                                            <div class="row">
		                                                <!-- begin col-3 -->
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cuadros_de_tendencias") %>"><%= lang1.getProperty("cuadros_de_tendencias") %></label><br/>
																<input id="txt_117" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
		                                                    </div>
		                                                </div>
		                                                <!-- end col-3 -->
		                                                <!-- begin col-3 -->
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_tendido_tabla_periodo") %>"><%= lang1.getProperty("tendido_tabla_periodo") %></label>
																<input type="number"  class="default-select2 form-control" id="txt_118"  data-parsley-group="wizard-step-3" placeholder="<%= lang1.getProperty("tendido_tabla_periodo") %>" />
															</div>
		                                                    
		                                                </div>
		                                                <!-- end col-3 -->
		                                                <!-- begin col-3 -->
		                                                <div class="col-md-3">
		                                                     <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_numero_estimado_de_banners") %>"><%= lang1.getProperty("numero_estimado_de_banners") %></label>
																<input type="number"  class="default-select2 form-control" id="txt_119"  data-parsley-group="wizard-step-3" placeholder="<%= lang1.getProperty("numero_estimado_de_banners") %>" />
															</div>
		                                                    
		                                                </div>
		                                                <!-- end col-3 -->
		                                                <!-- begin col-3 -->
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_anexar_o_los_datos_de_tendencia_de_otro_proveedor_o_fuente") %>"><%= lang1.getProperty("anexar_o_los_datos_de_tendencia_de_otro_proveedor_o_fuente") %></label><br/>
																<input id="txt_120" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
		                                                    </div>
		                                                    
		                                                </div>
		                                                <!-- end col-3 -->
		                                            </div>
		                                            <!-- end row -->
		                                            
		                                            <!-- end row -->
		                                            <!-- begin row -->
		                                            <div class="row">
		                                                <!-- begin col-6 -->
		                                                <div class="col-md-6">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_especificar_requisitos_de_integración_de_datos") %>"><%= lang1.getProperty("especificar_requisitos_de_integración_de_datos") %></label><br/>
																<textarea class="form-control"  id="txt_125"data-parsley-group="wizard-step-3" placeholder="<%= lang1.getProperty("especificar_requisitos_de_integración_de_datos") %>" rows="5"></textarea>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-6 -->
		                                                <!-- begin col-6 -->
		                                                <div class="col-md-6">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_otra_informacion_de_procesamiento_de_datos_especificaciones") %>"><%= lang1.getProperty("otra_informacion_de_procesamiento_de_datos_especificaciones") %></label><br/>
																<textarea class="form-control"  id="txt_126"data-parsley-group="wizard-step-3" placeholder="<%= lang1.getProperty("otra_informacion_de_procesamiento_de_datos_especificaciones") %>" rows="5"></textarea>
		                                                    </div>
		                                                    
		                                                </div>
		                                                <!-- end col-6 -->
		                                                
		                                            </div>
		                                            <!-- end row -->
		                                            <!-- begin row -->
		                                            <div class="row">
		                                               
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_tab_pruebas_stat") %>"><%= lang1.getProperty("tab_pruebas_stat") %></label><br/>
																<input id="txt_127" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_tab_ponderacion") %>"><%= lang1.getProperty("tab_ponderacion") %></label><br/>
																<input id="txt_128" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_tab_frec_entrega_file_datos") %>"><%= lang1.getProperty("tab_frec_entrega_file_datos") %></label><br/>
																<select class="default-select2 form-control" id="txt_129" data-parsley-group="wizard-step-3" style="width: 100%;"   >
															    </select>
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_tab_frec_tabla_datos_entrega") %>"><%= lang1.getProperty("tab_frec_tabla_datos_entrega") %></label><br/>
																<select class="default-select2 form-control" id="txt_130" data-parsley-group="wizard-step-3"  style="width: 100%;" >
															    </select>
		                                                    </div>
		                                                </div>
		                                                
		                                                
		                                            </div>
		                                            <!-- end row -->
												</fieldset>
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->												
											</div>
											<div class="tab-pane fade" id="work-pss-9">
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
												<fieldset>
													<legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew14") %></legend>
		                                            <!-- begin row -->
		                                            <div class="row">
		                                               
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_an_dis_muestra") %>"><%= lang1.getProperty("an_dis_muestra") %></label><br/>
																<input id="txt_131" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_an_toma_muestra_varios_paneles") %>"><%= lang1.getProperty("an_toma_muestra_varios_paneles") %></label><br/>
																<input id="txt_132" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_an_despropor_linea_fija") %>"><%= lang1.getProperty("an_despropor_linea_fija") %></label><br/>
																<input type="text"  class="form-control" id="txt_133"  data-parsley-group="wizard-step-3" placeholder="<%= lang1.getProperty("an_despropor_linea_fija") %>" />
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_an_telefono_fuente") %>"><%= lang1.getProperty("an_telefono_fuente") %></label><br/>
																<input type="text"  class=" form-control" id="txt_134" data-parsley-group="wizard-step-3"   placeholder="<%= lang1.getProperty("an_telefono_fuente") %>" />
		                                                    </div>
		                                                </div>
		                                             </div>
		                                            <!-- end row -->
		                                            <!-- begin row -->
		                                            <div class="row">
		                                               
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_an_pon_necesaria_mark") %>"><%= lang1.getProperty("an_pon_necesaria_mark") %></label><br/>
																<input id="txt_135" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_an_complejidad_pond") %>"><%= lang1.getProperty("an_complejidad_pond") %></label><br/>
																<select class="default-select2 form-control" id="txt_136" data-parsley-group="wizard-step-3"  style="width: 100%;" >
															    </select>
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_an_pond_cali_combinar_linea") %>"><%= lang1.getProperty("an_pond_cali_combinar_linea") %></label><br/>
																<input type="number"  class="form-control" id="txt_137"  data-parsley-group="wizard-step-3" placeholder="<%= lang1.getProperty("an_pond_cali_combinar_linea") %>" />
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_an_pond_cali_est_long") %>"><%= lang1.getProperty("an_pond_cali_est_long") %></label><br/>
																<input id="txt_138" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
		                                                    </div>
		                                                </div>
		                                                
		                                                
		                                            </div>
		                                            <!-- end row -->
		                                            <!-- begin row -->
		                                            <div class="row">
		                                               
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_an_uni_dat_nece") %>"><%= lang1.getProperty("an_uni_dat_nece") %></label><br/>
																<input id="txt_139" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_an_puntos_ref_nec") %>"><%= lang1.getProperty("an_puntos_ref_nec") %></label><br/>
																<input id="txt_140" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_an_wave_frec") %>"><%= lang1.getProperty("an_wave_frec") %></label><br/>
																<select class="default-select2 form-control" id="txt_141" data-parsley-group="wizard-step-3"  style="width: 100%;" >
															    </select>
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_an_met_encuesta_nec") %>"><%= lang1.getProperty("an_met_encuesta_nec") %></label><br/>
																<input id="txt_142" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
		                                                    </div>
		                                                </div>
		                                                
		                                                
		                                            </div>
		                                            <!-- end row -->
												</fieldset>
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->											
											</div>
											<div class="tab-pane fade" id="work-pss-10">
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
												<fieldset>
													<legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew15") %></legend>
			                                            <!-- begin row -->
			                                            <div class="row">
			                                               
			                                                <div class="col-md-2">
			                                                    <div class="form-group">
			                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_en_informe_cliente") %>"><%= lang1.getProperty("en_informe_cliente") %></label><br/>
																	<input id="txt_143" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
			                                                    </div>
			                                                </div>
			                                                <div class="col-md-2">
			                                                    <div class="form-group">
			                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_en_presentacion") %>"><%= lang1.getProperty("en_presentacion") %></label><br/>
																	<input id="txt_144" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
			                                                    </div>
			                                                </div>
			                                                <div class="col-md-2">
			                                                    <div class="form-group">
			                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_en_delivery") %>"><%= lang1.getProperty("en_delivery") %></label><br/>
																	<input id="txt_145" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
			                                                    </div>
			                                                </div>
			                                                <div class="col-md-2">
			                                                    <div class="form-group">
			                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_en_analisis") %>"><%= lang1.getProperty("en_analisis") %></label><br/>
																	<input id="txt_146" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
			                                                    </div>
			                                                </div>
			                                                <div class="col-md-2">
			                                                    <div class="form-group">
			                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_en_grabaciones") %>"><%= lang1.getProperty("en_grabaciones") %></label><br/>
																	<input id="txt_147" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
			                                                    </div>
			                                                </div>
			                                                 <div class="col-md-2">
			                                                    <div class="form-group">
			                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_en_pdf") %>"><%= lang1.getProperty("en_pdf") %></label><br/>
																	<input id="txt_148" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
			                                                    </div>
			                                                </div>
			                                                
			                                                
			                                            </div>
			                                            <!-- end row -->
												</fieldset>
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->											
											</div>
											<div class="tab-pane fade" id="work-pss-11">
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
												<fieldset>
													<legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew17") %></legend>
		                                            <!-- begin row -->
		                                            <div class="row">
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_bc_01") %>"><%= lang1.getProperty("bc_01") %></label><br/>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-8">
		                                                    <div class="form-group">
		                                                        <div class="controls">
		                                                            <select class="default-select2 form-control" id="txt_169" data-parsley-group="wizard-step-4"  style=" width: 100%;" >
															    	</select>
		                                                        </div>
		                                                        <div class="form-group" style="margin-top: 10px;">
		                                                        	<textarea class="form-control"  id="txt_170"data-parsley-group="wizard-step-5" placeholder="<%= lang1.getProperty("bc_01") %>" rows="5"></textarea>
		                                                    	</div>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-8 -->
		                                                
		                                            </div>
		                                            <!-- end row -->
		                                            <!-- begin row -->
		                                            <div class="row">
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_bc_02") %>"><%= lang1.getProperty("bc_02") %></label><br/>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-8">
		                                                    <div class="form-group">
		                                                        <div class="controls">
		                                                            <select class="default-select2 form-control" id="txt_171" data-parsley-group="wizard-step-5"  style="width: 100%;" >
															    	</select>
		                                                        </div>
		                                                        <div class="form-group" style="margin-top: 10px;">
		                                                        	<textarea class="form-control"  id="txt_172"data-parsley-group="wizard-step-5" placeholder="<%= lang1.getProperty("bc_02") %>" rows="5"></textarea>
		                                                    	</div>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-8 -->
		                                            </div>
		                                                <!-- begin row -->
		                                            <div class="row">
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_bc_03") %>"><%= lang1.getProperty("bc_03") %></label><br/>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-8">
		                                                    <div class="form-group">
		                                                        <div class="controls">
		                                                            <select class="default-select2 form-control" id="txt_173" data-parsley-group="wizard-step-5"  style="width: 100%;" >
															    	</select>
		                                                        </div>
		                                                        
		                                                    </div>
		                                                </div>
		                                                <!-- end col-8 -->
		                                            </div>
		                                            <!-- end row -->
		                                            <!-- begin row -->
		                                            <div class="row">
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_bc_04") %>"><%= lang1.getProperty("bc_04") %></label><br/>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-8">
		                                                    <div class="form-group">
		                                                        <div class="controls">
		                                                            <select class="default-select2 form-control" id="txt_174" data-parsley-group="wizard-step-5"  style="width: 100%;" >
															    	</select>
		                                                        </div>
		                                                        <div class="form-group" style="margin-top: 10px;">
		                                                        	<textarea class="form-control"  id="txt_175"data-parsley-group="wizard-step-5" placeholder="<%= lang1.getProperty("bc_04") %>" rows="5"></textarea>
		                                                    	</div>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-8 -->
		                                            </div>
		                                            <!-- end row -->
		                                            <!-- begin row -->
		                                            <div class="row">
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_bc_05") %>"><%= lang1.getProperty("bc_05") %></label><br/>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-8">
		                                                    <div class="form-group">
		                                                        <div class="controls">
		                                                            <select class="default-select2 form-control" id="txt_176" data-parsley-group="wizard-step-5"  style="width: 100%;" >
															    	</select>
		                                                        </div>
		                                                        <div class="form-group" style="margin-top: 10px;">
		                                                        	<textarea class="form-control"  id="txt_177"data-parsley-group="wizard-step-5" placeholder="<%= lang1.getProperty("bc_05") %>" rows="5"></textarea>
		                                                    	</div>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-8 -->
		                                            </div>
		                                            <!-- end row -->
		                                            <!-- begin row -->
		                                            <div class="row">
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_bc_06") %>"><%= lang1.getProperty("bc_06") %></label><br/>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-8">
		                                                    <div class="form-group">
		                                                        
		                                                        <div class="form-group" style="margin-top: 10px;">
		                                                        	<textarea class="form-control"  id="txt_178"data-parsley-group="wizard-step-5" placeholder="<%= lang1.getProperty("bc_06") %>" rows="5"></textarea>
		                                                    	</div>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-8 -->
		                                            </div>
		                                            <!-- end row -->
		                                            <!-- begin row -->
		                                            <div class="row">
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_bc_07") %>"><%= lang1.getProperty("bc_07") %></label><br/>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-8">
		                                                    <div class="form-group">
		                                                        <div class="controls">
		                                                            <select class="default-select2 form-control" id="txt_179" data-parsley-group="wizard-step-5"  style="width: 100%;" >
															    	</select>
		                                                        </div>
		                                                        <div class="form-group" style="margin-top: 10px;">
		                                                        	<textarea class="form-control"  id="txt_180"data-parsley-group="wizard-step-5" placeholder="<%= lang1.getProperty("bc_07") %>" rows="5"></textarea>
		                                                    	</div>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-8 -->
		                                            </div>
		                                            <!-- end row -->
		                                            <!-- begin row -->
		                                            <div class="row">
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_bc_08") %>"><%= lang1.getProperty("bc_08") %></label><br/>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-8">
		                                                    <div class="form-group">
		                                                        <div class="controls">
		                                                            <select class="default-select2 form-control" id="txt_181" data-parsley-group="wizard-step-5"  style="width: 100%;" >
															    	</select>
		                                                        </div>
		                                                   </div>
		                                                </div>
		                                                <!-- end col-8 -->
		                                            </div>
		                                            <!-- end row -->
		                                            <!-- begin row -->
		                                            <div class="row">
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_bc_09") %>"><%= lang1.getProperty("bc_09") %></label><br/>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-8">
		                                                    <div class="form-group">
		                                                        <div class="controls">
		                                                            <select class="default-select2 form-control" id="txt_182" data-parsley-group="wizard-step-5" style="width: 100%;" >
															    	</select>
		                                                        </div>
		                                                        <div class="form-group" style="margin-top: 10px;">
		                                                        	<textarea class="form-control"  id="txt_183"data-parsley-group="wizard-step-5" placeholder="<%= lang1.getProperty("bc_09") %>" rows="5"></textarea>
		                                                    	</div>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-8 -->
		                                            </div>
		                                            <!-- end row -->
		                                            <!-- begin row -->
		                                            <div class="row">
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_bc_10") %>"><%= lang1.getProperty("bc_10") %></label><br/>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-8">
		                                                    <div class="form-group">
		                                                        <div class="form-group" style="margin-top: 10px;">
		                                                        	<textarea class="form-control"  id="txt_184"data-parsley-group="wizard-step-5" placeholder="<%= lang1.getProperty("bc_10") %>" rows="5"></textarea>
		                                                    	</div>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-8 -->
		                                            </div>
		                                            <!-- end row -->
		                                            <!-- begin row -->
		                                            <div class="row">
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_bc_11") %>"><%= lang1.getProperty("bc_11") %></label><br/>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-8">
		                                                    <div class="form-group">
		                                                        <div class="form-group" style="margin-top: 10px;">
		                                                        	<textarea class="form-control"  id="txt_185"data-parsley-group="wizard-step-5" placeholder="<%= lang1.getProperty("bc_11") %>" rows="5"></textarea>
		                                                    	</div>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-8 -->
		                                            </div>
		                                            <!-- end row -->
		                                            <!-- begin row -->
		                                            <div class="row">
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_bc_12") %>"><%= lang1.getProperty("bc_12") %></label><br/>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-8">
		                                                    <div class="form-group">
		                                                        <div class="form-group" style="margin-top: 10px;">
		                                                        	<select class="default-select2 form-control" id="txt_186" data-parsley-group="wizard-step-5"  style="width: 100%;" >
															    	</select>
		                                                    	</div>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-8 -->
		                                            </div>
		                                            <!-- end row -->
		                                            <!-- begin row -->
		                                            <div class="row">
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_bc_13") %>"><%= lang1.getProperty("bc_13") %></label><br/>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-8">
		                                                    <div class="form-group">
		                                                        <div class="form-group" style="margin-top: 10px;">
		                                                        	<select class="default-select2 form-control" id="txt_187" data-parsley-group="wizard-step-5"  style="width: 100%;" >
															    	</select>
		                                                    	</div>
		                                                    	<div class="form-group" style="margin-top: 10px;">
		                                                    		<textarea class="form-control"  id="txt_188"data-parsley-group="wizard-step-5" placeholder="<%= lang1.getProperty("bc_13") %>" rows="5"></textarea>
		                                                    	</div>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-8 -->
		                                            </div>
		                                            <!-- end row -->
		                                            <!-- begin row -->
		                                            <div class="row">
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_bc_14") %>"><%= lang1.getProperty("bc_14") %></label><br/>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-8">
		                                                    <div class="form-group">
		                                                        <div class="form-group" style="margin-top: 10px;">
		                                                    		<textarea class="form-control"  id="txt_189" data-parsley-group="wizard-step-5" placeholder="<%= lang1.getProperty("bc_14") %>" rows="5"></textarea>
		                                                    	</div>
		                                                    </div>
		                                                </div>
		                                                <!-- end col-8 -->
		                                            </div>
		                                            <!-- end row -->
												</fieldset>
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->												
											</div>
											<div class="tab-pane fade" id="work-pss-12">
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
												<fieldset>
													<legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew16") %></legend>
		                                            <!-- begin row -->
		                                            <div class="row">
		                                               
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cu_num_cuest_unico") %>"><%= lang1.getProperty("cu_num_cuest_unico") %></label><br/>
																<input type="number"  class=" form-control" id="txt_149" data-parsley-group="wizard-step-4"   placeholder="<%= lang1.getProperty("cu_num_cuest_unico") %>" />
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cu_por_cuest_sueper") %>"><%= lang1.getProperty("cu_por_cuest_sueper") %></label><br/>
																<input type="number"  class=" form-control" id="txt_150" data-parsley-group="wizard-step-4"   placeholder="<%= lang1.getProperty("cu_por_cuest_sueper") %>" />
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cu_por_cuest_change") %>"><%= lang1.getProperty("cu_por_cuest_change") %></label><br/>
																<input type="number"  class=" form-control" id="txt_151" data-parsley-group="wizard-step-4"   placeholder="<%= lang1.getProperty("cu_por_cuest_change") %>" />
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cu_des_change_cuest") %>"><%= lang1.getProperty("cu_des_change_cuest") %></label><br/>
																<input id="txt_152" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
		                                                    </div>
		                                                </div>
		                                            </div>
		                                            <!-- end row -->
		                                            <!-- begin row -->
		                                            <div class="row">
		                                               
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cu_num_cuest_open") %>"><%= lang1.getProperty("cu_num_cuest_open") %></label><br/>
																<input type="number"  class=" form-control" id="txt_154" data-parsley-group="wizard-step-4"   placeholder="<%= lang1.getProperty("cu_num_cuest_open") %>" />
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cu_por_resp_open_end") %>"><%= lang1.getProperty("cu_por_resp_open_end") %></label><br/>
																<input type="number"  class=" form-control" id="txt_155" data-parsley-group="wizard-step-4"   placeholder="<%= lang1.getProperty("cu_por_resp_open_end") %>" />
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cu_num_otro_esp") %>"><%= lang1.getProperty("cu_num_otro_esp") %></label><br/>
																<textarea class="form-control"  id="txt_156"data-parsley-group="wizard-step-4" placeholder="<%= lang1.getProperty("cu_num_otro_esp") %>" rows="5"></textarea>
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cu_por_resp_otro") %>"><%= lang1.getProperty("cu_por_resp_otro") %></label><br/>
																<textarea class="form-control"  id="txt_157"data-parsley-group="wizard-step-4" placeholder="<%= lang1.getProperty("cu_por_resp_otro") %>" rows="5"></textarea>
		                                                    </div>
		                                                </div>
		                                            </div>
		                                            <!-- end row -->
		                                            <!-- begin row -->
		                                            <div class="row">
		                                               
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cu_cuestio_traduc_req") %>"><%= lang1.getProperty("cu_cuestio_traduc_req") %></label><br/>
																<input id="txt_158" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cu_otra_info_cuest") %>"><%= lang1.getProperty("cu_otra_info_cuest") %></label><br/>
																<textarea class="form-control"  id="txt_159"data-parsley-group="wizard-step-4" placeholder="<%= lang1.getProperty("cu_otra_info_cuest") %>" rows="5"></textarea>
																
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cu_num_total_preg") %>"><%= lang1.getProperty("cu_num_total_preg") %></label><br/>
																<input type="number"  class=" form-control" id="txt_160" data-parsley-group="wizard-step-4"   placeholder="<%= lang1.getProperty("cu_num_total_preg") %>" />
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cu_esp_req_exportacion_datos") %>"><%= lang1.getProperty("cu_esp_req_exportacion_datos") %></label><br/>
																<textarea class="form-control"  id="txt_161"data-parsley-group="wizard-step-4" placeholder="<%= lang1.getProperty("cu_esp_req_exportacion_datos") %>" rows="5"></textarea>
		                                                    </div>
		                                                </div>
		                                            </div>
		                                            <!-- end row -->
		                                            <!-- begin row -->
		                                            <div class="row">
		                                               
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cu_otro_cati_scripting_esp") %>"><%= lang1.getProperty("cu_otro_cati_scripting_esp") %></label><br/>
																<textarea class="form-control"  id="txt_162"data-parsley-group="wizard-step-4" placeholder="<%= lang1.getProperty("cu_otro_cati_scripting_esp") %>" rows="5"></textarea>
																
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-3">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cu_num_total_preg_prog") %>"><%= lang1.getProperty("cu_num_total_preg_prog") %></label><br/>
																<input type="number"  class=" form-control" id="txt_163" data-parsley-group="wizard-step-3"   placeholder="<%= lang1.getProperty("cu_num_total_preg_prog") %>" />
																
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-2">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cu_video") %>"><%= lang1.getProperty("cu_video") %></label><br/>
																<input id="txt_164" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-2">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cu_archivo") %>"><%= lang1.getProperty("cu_archivo") %></label><br/>
																<input id="txt_165" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-2">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cu_archivo_audio") %>"><%= lang1.getProperty("cu_archivo_audio") %></label><br/>
																<input id="txt_166" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
		                                                    </div>
		                                                </div>
		                                            </div>
		                                            <!-- end row -->
		                                            <!-- begin row -->
		                                            <div class="row">
		                                               
		                                                <div class="col-md-4">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cu_req_export_datos") %>"><%= lang1.getProperty("cu_req_export_datos") %></label><br/>
																<textarea class="form-control"  id="txt_167"data-parsley-group="wizard-step-4" placeholder="<%= lang1.getProperty("cu_req_export_datos") %>" rows="5"></textarea>
																
		                                                    </div>
		                                                </div>
		                                                <div class="col-md-4">
		                                                    <div class="form-group">
		                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cu_otro_cawi_capi") %>"><%= lang1.getProperty("cu_otro_cawi_capi") %></label><br/>
																<textarea class="form-control"  id="txt_168"data-parsley-group="wizard-step-4" placeholder="<%= lang1.getProperty("cu_otro_cawi_capi") %>" rows="5"></textarea>
																
		                                                    </div>
		                                                </div>
		                                                
		                                                
		                                                
		                                            </div>
		                                            <!-- end row -->
												</fieldset>
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->												
											</div>
											<div class="tab-pane fade" id="work-pss-13">
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->
												<fieldset>
													<legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew18") %></legend>
			                                            <div class="row">
			                                            	<!-- begin col-4 -->
			                                                <div class="col-md-4">
			                                                    <div class="form-group">
			                                                        <label  data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cot_precio_venta") %>"><%= lang1.getProperty("cot_precio_venta") %></label>
			                                                        <div class="controls">
			                                                            <input type="number" id="txt_195" style="font-size: 20px; color: blue; "  class="form-control" data-parsley-group="wizard-step-5" placeholder="<%= lang1.getProperty("cot_precio_venta") %>" value="0" >
			                                                        </div>
			                                                    </div>
			                                                </div>
			                                                <!-- end col-6 -->
			                                            </div>
			                                            
			                                            <!-- begin row -->
			                                            <div class="row">
			                                                <!-- begin col-4 -->
			                                                <div class="col-md-4">
			                                                    <div class="form-group">
			                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cot_num_entrevista") %>"><%= lang1.getProperty("cot_num_entrevista") %></label>
			                                                        <div class="controls">
			                                                            <input type="number" id="txt_190"  class="form-control" data-parsley-group="wizard-step-5" placeholder="<%= lang1.getProperty("cot_num_entrevista") %>" value="0">
			                                                        </div>
			                                                    </div>
			                                                </div>
			                                                <!-- end col-4 -->
			                                                <!-- begin col-4 -->
			                                                <div class="col-md-4">
			                                                    <div class="form-group">
			                                                        <label  data-toggle="tooltip"  data-placement="top" title="<%= lang1.getProperty("tooltip_tipoentrevista") %>"><%= lang1.getProperty("tipoentrevista") %></label>
			                                                        <div class="controls">
			                                                            <input type="text" id="txt_191" style="font-size: 20px; color: orange; "  class="form-control" data-parsley-group="wizard-step-5" placeholder="<%= lang1.getProperty("tipoentrevista") %>" readonly="readonly">
			                                                        </div>
			                                                    </div>
			                                                </div>
			                                                <!-- end col-4 -->
			                                                <!-- begin col-4 -->
			                                                <div class="col-md-4">
			                                                    <div class="form-group">
			                                                        <label  data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cot_dur_entrevista") %>"><%= lang1.getProperty("cot_dur_entrevista") %></label>
			                                                        <div class="controls">
			                                                            <input type="number" id="txt_192" style="font-size: 20px; color: orange;"  class="form-control" readonly="readonly" data-parsley-group="wizard-step-5" placeholder="<%= lang1.getProperty("cot_dur_entrevista") %>" value="0" >
			                                                        </div>
			                                                    </div>
			                                                </div>
			                                                <!-- end col-6 -->
			                                            </div>
			                                            <!-- end row -->
			                                            <!-- begin row -->
			                                            <div class="row">
			                                                <!-- begin col-4 -->
			                                                <div class="col-md-4">
			                                                    <div class="form-group">
			                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cot_por_incidencia") %>"><%= lang1.getProperty("cot_por_incidencia") %></label>
			                                                        <div class="controls">
			                                                            <input type="number" id="txt_193"  class="form-control" data-parsley-group="wizard-step-5" placeholder="<%= lang1.getProperty("cot_por_incidencia") %>" value="0">
			                                                        </div>
			                                                    </div>
			                                                </div>
			                                                <!-- end col-4 -->
			                                                <!-- begin col-4 -->
			                                                <div class="col-md-4">
			                                                    <div class="form-group">
			                                                        <label  data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cot_por_rebate") %>"><%= lang1.getProperty("cot_por_rebate") %></label>
			                                                        <div class="controls">
			                                                            <input type="number" id="txt_194"  class="form-control" data-parsley-group="wizard-step-5" placeholder="<%= lang1.getProperty("cot_por_rebate") %>" value="0" >
			                                                        </div>
			                                                    </div>
			                                                </div>
			                                                <!-- end col-4 -->
			                                                
			                                            </div>
			                                            <!-- end row -->
			                                            
												</fieldset>
												<fieldset>
		                                        	<legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew19") %></legend>
													<table id="data-table" class="table table-bordered" style="">
						                                <thead>
						                                    <tr>
						                                        <th width="200px" bgcolor="#f9f9f9"></th>
						                                        <th width="100px" bgcolor="#dff0d8" style="font-weight: bold;"><%= lang1.getProperty("cuad_porcentaje") %></th>
						                                        <th width="200px" bgcolor="#dff0d8" style="font-weight: bold;"><%= lang1.getProperty("cuad_target") %></th>
						                                        <th width="100px" bgcolor="#d9edf7" style="font-weight: bold;"><%= lang1.getProperty("cuad_porcentaje2") %></th>
						                                        <th width="200px" bgcolor="#d9edf7" style="font-weight: bold;"><%= lang1.getProperty("cuad_escenario") %></th>
						                                        <th width="100px" bgcolor="#d9edf7" style="font-weight: bold;"><%= lang1.getProperty("cuad_usd") %></th>
						                                        <th width="100px" bgcolor="#d9edf7" style="font-weight: bold;"><%= lang1.getProperty("cuad_eur") %></th>
						                                    </tr>
						                                </thead>
						                                <tbody>
						                                	<tr>
						                                		<td bgcolor="#f9f9f9" style="font-weight: bold;" align="right"><%= lang1.getProperty("cuad_precio_venta") %></td>
						                                		<td id="tbl_cto_1_1"></td>
						                                		<td id="tbl_cto_1_2" style="font-weight: bold;">0</td>
						                                		<td id="tbl_cto_1_3"></td>
						                                		<td id="tbl_cto_1_4" style="font-weight: bold;">0</td>
						                                		<td id="tbl_cto_1_5"></td>
						                                		<td id="tbl_cto_1_6"></td>
						                                	</tr>
						                                	<tr>
						                                		<td bgcolor="#f9f9f9" style="font-weight: bold;" align="right"><%= lang1.getProperty("cuad_precio_unico") %></td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_2_1"></td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_2_2" style="font-weight: bold;">0</td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_2_3"></td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_2_4" style="font-weight: bold;">0</td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_2_5"></td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_2_6"></td>
						                                	</tr>
						                                	<tr>
						                                		<td bgcolor="#f9f9f9" style="font-weight: bold;" align="right"><%= lang1.getProperty("cuad_rebate") %></td>
						                                		<td  id="tbl_cto_3_1"></td>
						                                		<td id="tbl_cto_3_2" style="font-weight: bold;">0</td>
						                                		<td id="tbl_cto_3_3"></td>
						                                		<td id="tbl_cto_3_4" style="font-weight: bold;">0</td>
						                                		<td id="tbl_cto_3_5"></td>
						                                		<td id="tbl_cto_3_6"></td>
						                                	</tr>
						                                	<tr>
						                                		<td bgcolor="#f9f9f9" style="font-weight: bold;" align="right"><%= lang1.getProperty("cuad_costo_directo") %></td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_4_1"></td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_4_2" style="font-weight: bold;">0</td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_4_3"></td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_4_4" style="font-weight: bold;">0</td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_4_5"></td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_4_6"></td>
						                                	</tr>
						                                	<tr>
						                                		<td bgcolor="#f9f9f9" style="font-weight: bold;" align="right"><%= lang1.getProperty("cuad_labor") %></td>
						                                		<td id="tbl_cto_5_1"></td>
						                                		<td id="tbl_cto_5_2" style="font-weight: bold;">0</td>
						                                		<td id="tbl_cto_5_3"></td>
						                                		<td id="tbl_cto_5_4" style="font-weight: bold;">0</td>
						                                		<td id="tbl_cto_5_5"></td>
						                                		<td id="tbl_cto_5_6"></td>
						                                	</tr>
						                                	<tr>
						                                		<td bgcolor="#f9f9f9" bgcolor="#f9f9f9" style="font-weight: bold;" align="right"><%= lang1.getProperty("cuad_overhead") %></td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_6_1"></td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_6_2" style="font-weight: bold;">0</td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_6_3"></td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_6_4" style="font-weight: bold;">0</td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_6_5"></td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_6_6"></td>
						                                	</tr>
						                                	<tr>
						                                		<td bgcolor="#f9f9f9" style="font-weight: bold;" align="right"><%= lang1.getProperty("cuad_c2_cost") %></td>
						                                		<td id="tbl_cto_7_1"></td>
						                                		<td id="tbl_cto_7_2" style="font-weight: bold;">0</td>
						                                		<td id="tbl_cto_7_3"></td>
						                                		<td id="tbl_cto_7_4" style="font-weight: bold;">0</td>
						                                		<td id="tbl_cto_7_5"></td>
						                                		<td id="tbl_cto_7_6"></td>
						                                	</tr>
						                                	<tr>
						                                		<td bgcolor="#fcf8e3" style="font-weight: bold;" align="right"><%= lang1.getProperty("cuad_cm_ii") %></td>
						                                		<td bgcolor="#fcf8e3" id="tbl_cto_8_1"></td>
						                                		<td bgcolor="#fcf8e3" id="tbl_cto_8_2" style="font-weight: bold;">0</td>
						                                		<td bgcolor="#fcf8e3" id="tbl_cto_8_3"></td>
						                                		<td bgcolor="#fcf8e3" id="tbl_cto_8_4" style="font-weight: bold;">0</td>
						                                		<td bgcolor="#fcf8e3" id="tbl_cto_8_5"></td>
						                                		<td bgcolor="#fcf8e3" id="tbl_cto_8_6"></td>
						                                	</tr>
						                                	<tr>
						                                		<td bgcolor="#f9f9f9" style="font-weight: bold;" align="right"><%= lang1.getProperty("cuad_ce_malta_charges") %></td>
						                                		<td id="tbl_cto_9_1"></td>
						                                		<td id="tbl_cto_9_2" style="font-weight: bold;">0</td>
						                                		<td id="tbl_cto_9_3"></td>
						                                		<td id="tbl_cto_9_4" style="font-weight: bold;">0</td>
						                                		<td id="tbl_cto_9_5"></td>
						                                		<td id="tbl_cto_9_6"></td>
						                                	</tr>
						                                	<tr>
						                                		<td bgcolor="#f9f9f9" bgcolor="#f9f9f9" style="font-weight: bold;" align="right"><%= lang1.getProperty("cuad_company_charges") %></td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_10_1"></td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_10_2" style="font-weight: bold;">0</td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_10_3"></td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_10_4" style="font-weight: bold;">0</td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_10_5"></td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_10_6"></td>
						                                	</tr>
						                                	<tr>
						                                		<td bgcolor="#f9f9f9" style="font-weight: bold;" align="right"><%= lang1.getProperty("cuad_group_charges") %></td>
						                                		<td id="tbl_cto_11_1"></td>
						                                		<td id="tbl_cto_11_2" style="font-weight: bold;">0</td>
						                                		<td id="tbl_cto_11_3"></td>
						                                		<td id="tbl_cto_11_4" style="font-weight: bold;">0</td>
						                                		<td id="tbl_cto_11_5"></td>
						                                		<td id="tbl_cto_11_6"></td>
						                                	</tr>
						                                	<tr>
						                                		<td bgcolor="#f9f9f9" style="font-weight: bold;" align="right"><%= lang1.getProperty("cuad_aop") %></td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_12_1"></td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_12_2" style="font-weight: bold;">0</td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_12_3"></td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_12_4" style="font-weight: bold;">0</td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_12_5"></td>
						                                		<td bgcolor="#f9f9f9" id="tbl_cto_12_6"></td>
						                                	</tr>
						                                	<tr>
						                                		<td bgcolor="#f9f9f9" style="font-weight: bold;" align="right"><%= lang1.getProperty("cuad_profit_margin") %></td>
						                                		<td id="tbl_cto_13_1"></td>
						                                		<td id="tbl_cto_13_2" style="font-weight: bold;">0</td>
						                                		<td id="tbl_cto_13_3"></td>
						                                		<td id="tbl_cto_13_4" style="font-weight: bold;">0</td>
						                                		<td id="tbl_cto_13_5"></td>
						                                		<td id="tbl_cto_13_6"></td>
						                                	</tr>
						                                </tbody>
		                            				</table>
												
												</fieldset>
												<fieldset>
		                                        	<legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew20") %></legend>
		                                        	
		                                               
		                                            <table id="data-table5" class="table table-striped table-bordered">
						                                <thead>
						                                    <tr>
						                                    	<th width="10%" bgcolor="#fcf8e3" style="font-weight: bold;"></th>
						                                    	<th width="20%" bgcolor="#fcf8e3" style="font-weight: bold;"><%= lang1.getProperty("hora_area") %> </th>
						                                        <th width="20%" bgcolor="#fcf8e3" style="font-weight: bold;"><%= lang1.getProperty("hora_cargo") %></th>
						                                        <th width="10%" bgcolor="#fcf8e3" style="font-weight: bold;"><%= lang1.getProperty("hora_hora") %></th>
						                                        <th width="20%" bgcolor="#fcf8e3" style="font-weight: bold;"><%= lang1.getProperty("hora_total") %></th>
						                                        <th width="20%" bgcolor="#fcf8e3" style="font-weight: bold;"></th>
														    </tr>
						                                </thead>
						                                <tfoot>
						                                    <tr>
						                                    	<th width="10%" bgcolor="#fcf8e3" style="font-weight: bold;"></th>
						                                    	<th width="20%" bgcolor="#fcf8e3" style="font-weight: bold;"><%= lang1.getProperty("hora_area") %> </th>
						                                        <th width="20%" bgcolor="#fcf8e3" style="font-weight: bold;"><%= lang1.getProperty("hora_cargo") %></th>
						                                        <th width="10%" bgcolor="#fcf8e3" style="font-weight: bold;"><%= lang1.getProperty("hora_hora") %></th>
						                                        <th width="20%" bgcolor="#fcf8e3" style="font-weight: bold;"><%= lang1.getProperty("hora_total") %></th>
						                                        <th width="20%" bgcolor="#fcf8e3" style="font-weight: bold;"></th>
														    </tr>
						                                </tfoot>
						                                <tbody>
						                                    
						                                </tbody>
						                            </table>
		                                        </fieldset>
		                                        <fieldset>
                                        			<legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew21") %></legend>
                                        			<table id="data-table9" class="table table-striped table-bordered">
						                                <thead>
						                                    <tr>
						                                    	<th width="10%" bgcolor="#d9edf7" style="font-weight: bold;"></th>
						                                    	<th width="15%" bgcolor="#d9edf7" style="font-weight: bold;"><%= lang1.getProperty("co_categoria") %> </th>
						                                        <th width="15%" bgcolor="#d9edf7" style="font-weight: bold;"><%= lang1.getProperty("co_subcat") %></th>
						                                        <th width="10%" bgcolor="#d9edf7" style="font-weight: bold;"><%= lang1.getProperty("co_item") %></th>
						                                        <th width="15%" bgcolor="#d9edf7" style="font-weight: bold;"><%= lang1.getProperty("co_cantidad") %></th>
						                                        <th width="15%" bgcolor="#d9edf7" style="font-weight: bold;"><%= lang1.getProperty("co_total") %></th>
						                                        <th width="10%" bgcolor="#d9edf7" style="font-weight: bold;"></th>
														    </tr>
						                                </thead>
						                                <tfoot>
						                                    <th width="10%" bgcolor="#d9edf7" style="font-weight: bold;"></th>
						                                    	<th width="15%" bgcolor="#d9edf7" style="font-weight: bold;"><%= lang1.getProperty("co_categoria") %> </th>
						                                        <th width="15%" bgcolor="#d9edf7" style="font-weight: bold;"><%= lang1.getProperty("co_subcat") %></th>
						                                        <th width="10%" bgcolor="#d9edf7" style="font-weight: bold;"><%= lang1.getProperty("co_item") %></th>
						                                        <th width="15%" bgcolor="#d9edf7" style="font-weight: bold;"><%= lang1.getProperty("co_cantidad") %></th>
						                                        <th width="15%" bgcolor="#d9edf7" style="font-weight: bold;"><%= lang1.getProperty("co_total") %></th>
						                                        <th width="10%" bgcolor="#d9edf7" style="font-weight: bold;"></th>
						                                </tfoot>
						                                <tbody>
						                                    
						                                </tbody>
						                            </table>
                                        		</fieldset>
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// -->												
											</div>
										
									</div>
	                           
	                        </div>
                           
                           
                           
                           
                           
                           	  
                           
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            </div>
            <!-- end row -->
			<!-- Begin row -->
            <div class="row">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-success">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">Costos Reales del Proyecto</h4>
                        </div>
                        
                        <div id="panel_08" class="panel-body panel-form" style="margin-top: 10px; margin-left: 10px;">
                           <h3 class="m-t-10" style=" color: #f59c1a;">Imputación de Costos</h3>
                           <br/>
                           <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th width="25%">Catergoria</th>
                                        <th width="25%">Detalle</th>
                                        <th width="10%">Presupuestado</th>
                                        <th width="10%">Cantidad</th>
                                        <th width="10%">Costo Unitario</th>
                                        <th width="10%">Total</th>
                                        <th width="10%"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                        	<select class="default-select2 form-control" id="txt_cfijo_00" name="txt_cfijo_00"></select>
                                        </td>
                                        <td>
                                        	<select class="default-select2 form-control" id="txt_cfijo_01" name="txt_cfijo_01"></select>
                                        </td>
                                        <td>
                                        	<input type="number" class="form-control" name="txt_add_cfijo_01" id="txt_add_cfijo_01" />
                                        </td>
                                        <td>
                                        	<input type="number" class="form-control" name="txt_add_cfijo_02" id="txt_add_cfijo_01" />
                                        </td>
                                        <td>
                                        	<input type="number" class="form-control" name="txt_add_cfijo_03" id="txt_add_cfijo_02" />
                                        </td>
                                        <td>
                                        	<input type="number" class="form-control" name="txt_add_cfijo_04" id="txt_add_cfijo_03" />
                                        </td>
                                        <td align="center">
                                        	<input type="button" class="btn btn-success m-r-5 m-b-5" name="btn_add_costo_01" id="btn_add_costo_01" value="Agregar Costo Fijo" />
                                        </td>
                                    </tr>
                                    
                                    
                                </tbody>
                            </table>
                           <h2>Detalle</h2>
                           <br/>
                           <table id="data-table2" class="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                    	<th width="10"></th>
                                    	<th width="300">Detalle</th>
                                        <th width="300">Categoria</th>
                                    	<th width="200">Presupuestado</th>
                                        <th width="100">Cantidad</th>
                                        <th width="200">Costo Unitario</th>
                                        <th width="200">Total</th>
                                        
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                    	<th width="10"></th>
                                    	<th width="250"> Detalle</th>
                                    	<th width="250">Categoria</th>
                                    	<th width="200">Presupuestado</th>
                                        <th width="100">Cantidad</th>
                                        <th width="200">Costo Unitario</th>
                                        <th width="200">Total</th>
                                    </tr>
                                </tfoot>
                                <tbody>
                                    
                                </tbody>
                            </table>
                            <br/><br/>
                           
                           
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
	</div>
	<!-- end page container -->
	<!-- Incluir modal generico -->
    <%@ include file="../../general-modal.jsp" %>
		
	<!-- ================== BEGIN BASE JS ================== -->
	<!-- ================== BEGIN BASE JS ================== -->
	<script src="<c:url value="/resources/manager/plugins/jquery/jquery-1.12.3.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/jquery/jquery-migrate-1.1.0.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/jquery-ui/ui/minified/jquery-ui.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/bootstrap/js/bootstrap.min.js" />"></script>
	
	<!--[if lt IE 9]>
		<script src="<c:url value="/resources/manager/crossbrowserjs/html5shiv.js" />"></script>
		<script src="<c:url value="/resources/manager/crossbrowserjs/respond.min.js" />"></script>
		<script src="<c:url value="/resources/manager/crossbrowserjs/excanvas.min.js" />"></script>
	<![endif]-->
	<script src="<c:url value="/resources/manager/plugins/slimscroll/jquery.slimscroll.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/jquery-cookie/jquery.cookie.js" />"></script>
	<!-- ================== END BASE JS ================== -->
	
	<!-- ================== BEGIN PAGE LEVEL JS ================== -->
	<script src="<c:url value="/resources/manager/plugins/notify/bootstrap-notify.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/flot/jquery.flot.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/flot/jquery.flot.time.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/flot/jquery.flot.resize.min.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/flot\jquery.flot.pie.min.js" />"></script>
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
	<script src="<c:url value="/resources/app/proyecto/activa.js" />"></script>
	<script src="<c:url value="/resources/manager/js/apps.js" />"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->
	
	<script>
		$(document).ready(function() {
			App.init();
			Proyecto.init();
		});
	</script>

</body>
</html>
