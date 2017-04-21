<%@page import="cl.nexo.manager.obj.dashboard.ObjWidjetAccessAdmin"%>
<%@page import="cl.nexo.manager.obj.login.ObjPerfilLogin"%>
<%@page import="cl.nexo.manager.obj.login.ObjLoginUser"%>
<%@page import="cl.nexo.manager.obj.menu.ObjModuloMenuManager"%>
<%@page import="cl.nexo.manager.obj.menu.ObjUnidadMenuManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.net.*"%>
<%@page import="java.io.*"%>
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
	<div id="page-container" class="fade page-sidebar-fixed page-header-fixed">
		<!-- begin #header -->
		<%@ include file="../../header.jsp" %>
		<!-- end #header -->
		
		<!-- begin #sidebar -->
		<%@ include file="../../sidebar.jsp" %>
		<!-- end #sidebar -->
		<div class="sidebar-bg"></div>
		<!-- end #sidebar -->
		<!-- Configuracion Interna  -->
		<input type="hidden" id="txt_detalle_1" name="txt_detalle_1" value="<%=  id_operacion %>" />
		<input type="hidden" id="txt_detalle_2" name="txt_detalle_2" value="<%=  tipo %>" />
		<input type="hidden" id="conf_01"  name="conf_01" value="<%= perfil.getEs_admin() %>" />
		<input type="hidden" id="conf_02"  name="conf_01" value="<%= urlRestServiceDelivery %>" />
		<!-- BEGIN Config General -->
			<%@ include file="aditional-info.jsp" %>
			<!-- END Config General -->	 
		<!-- begin #content -->
		<div id="content" class="content">
			<!-- begin breadcrumb -->
			<ol class="breadcrumb pull-right">
				<li><a href="/Manager/home" target="_self">Home</a></li>
				<li><a href="#">Proyectos</a></li>
				<li class="active" id="li_estudio">Detalle de Proyectos</li>
			</ol>
			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<h1 class="page-header" >Mantenedor de Estudios <small id="h1_estudio">Gestión Detalle de Estudio...</small></h1>
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
                           	  		<!-- 
                           	  		<a href="#" class="btn btn-success m-r-5" style="margin-top: 5px; text-align: center;" onclick="JavaScript:;">
                           	  			<i class="fa fa-check-square-o fa-2x pull-left"></i>
                           	  			Activar <br/> Proyecto
                           	  		</a>
                           	  		 -->
                           	  		<a href="#" class="btn btn-warning m-r-5" style="margin-top: 5px; text-align: center;" onclick="JavaScript:;">
                           	  			<i class="fa fa-edit fa-2x pull-left"></i>
                           	  			Actualizar <br/> Proyecto
                           	  		</a>
                           	  		<a href="#" class="btn btn-danger m-r-5" style="margin-top: 5px; text-align: center;" onclick="JavaScript:;">
                           	  			<i class="fa fa-eraser fa-2x pull-left"></i>
                           	  			Eliminar <br/> Proyecto
                           	  		</a>
                           	  		<a href="#" class="btn btn-info m-r-5" style="margin-top: 5px; text-align: center;" onclick="JavaScript:;">
                           	  			<i class="fa fa-print fa-2x pull-left"></i>
                           	  			Imprimir <br/> Proyecto
                           	  		</a>
                           	  		<a href="/Manager/proyectoService/manProy" target="_self" class="btn btn-white m-r-5" style="margin-top: 5px; text-align: center;" onclick="JavaScript:;">
                           	  			<i class="fa fa-rotate-left fa-2x pull-left"></i>
                           	  			Volver Listar <br/> Proyectos
                           	  		</a>
                           	  	</div>
                           	  </div>
							   <div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Código SAM </label>
										<div class="col-md-8">
	                                        <input type="text" class="form-control" id="txt_detalle_01" name="txt_detalle_01" value="" />
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Código Manager 1.0 </label>
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
	                                        <input type="text" class="form-control" id="txt_detalle_09" name="txt_detalle_09" value=""/>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Cliente a Facturar </label>
										<div class="col-md-8">
	                                        <input type="text" class="form-control" id="txt_detalle_10" name="txt_detalle_10" value=""/>
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
										<li class="active"><a href="#work-pss-1" data-toggle="tab" aria-expanded="true">SET-UP</a></li>
										<li class=""><a href="#work-pss-2" data-toggle="tab" aria-expanded="false">Info General</a></li>
										<li class=""><a href="#work-pss-3" data-toggle="tab" aria-expanded="false">Recoleccion</a></li>
										<li class=""><a href="#work-pss-4" data-toggle="tab" aria-expanded="false">Scripting</a></li>
										<li class=""><a href="#work-pss-5" data-toggle="tab" aria-expanded="false">Codificación</a></li>
										<li class=""><a href="#work-pss-6" data-toggle="tab" aria-expanded="false">Digitación</a></li>
										<li class=""><a href="#work-pss-7" data-toggle="tab" aria-expanded="false">Depuración</a></li>
										<li class=""><a href="#work-pss-8" data-toggle="tab" aria-expanded="false">Tabulación</a></li>
										<li class=""><a href="#work-pss-9" data-toggle="tab" aria-expanded="false">Analisis</a></li>
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
		                                            <!--begin row -->
		                                            <div class="row">
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-12">
															<div class="form-group block1">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_nombreestudio") %>"><%= lang1.getProperty("nombreestudio") %> <span style="color: red;">(*)</span></label>
																<input type="text" class="form-control" id="txt_01"  placeholder="<%= lang1.getProperty("nombreestudio") %>" >
															</div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                
		                                            </div>
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
		                                                <div class="col-md-4">
															<div class="form-group" id="label_03">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_industria") %>"><%= lang1.getProperty("industria") %> <span style="color: red;">(*)</span></label>
																<select class="default-select2 form-control" id="txt_03" style="width: 100%;"   placeholder="<%= lang1.getProperty("industria") %>" >
																</select>
															</div>
		                                                </div>
		                                                <!-- end col-4 -->
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
		                                                <div class="col-md-4">
															<div class="form-group block1" id="label_05">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_clienteestudio") %>"><%= lang1.getProperty("clienteestudio") %> <span style="color: red;">(*)</span></label>
																<select class="default-select2 form-control" style="width: 100%;" id="txt_05"   placeholder="<%= lang1.getProperty("clienteestudio") %>" >
																</select>
															</div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
															<div class="form-group" id="label_06">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_clienteestudiofacturar") %>"><%= lang1.getProperty("clienteestudiofacturar") %> <span style="color: red;">(*)</span></label>
																<select class="default-select2 form-control" id="txt_06" style="width: 100%;"  placeholder="<%= lang1.getProperty("clienteestudiofacturar") %>" >
																</select>
															</div>
		                                                </div>
		                                                <!-- end col-4 -->
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
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
															<div class="form-group block1">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_tipocotizacion") %>"><%= lang1.getProperty("tipocotizacion") %> <span style="color: red;">(*)</span></label>
																<select class="default-select2 form-control" id="txt_08" style="width: 100%;"  placeholder="<%= lang1.getProperty("tipocotizacion") %>" >
																</select>
															</div>
		                                                </div>
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
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
															<div class="form-group" id="label_12">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_tipoentrevista") %>"><%= lang1.getProperty("tipoentrevista") %> <span style="color: red;">(*)</span></label>
																<select class="default-select2 form-control" id="txt_12"  style="width: 100%;"  placeholder="<%= lang1.getProperty("tipoentrevista") %>" >
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
																<input type="text" class="form-control calendar" id="txt_19"  placeholder="<%= lang1.getProperty("fechaProbInicioEstudio") %>"  />
															</div>
		                                                </div>
		                                                <!-- end col-4 -->
		                                                <!-- begin col-4 -->
		                                                <div class="col-md-4">
															<div class="form-group">
																<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_fechaProbEntregaEstudio") %>"><%= lang1.getProperty("fechaProbEntregaEstudio") %> <span style="color: red;">(*)</span></label>
																<input type="text" class="form-control calendar" id="txt_20"  placeholder="<%= lang1.getProperty("fechaProbEntregaEstudio") %>"  />
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
		                                            <legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew5") %></legend>
		                                            <div class="row">
							                           	  	<div class="col-md-4 ">
							                           	  		<div class="form-group" id="label_24">
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_responzable1") %>"><%= lang1.getProperty("responzable1") %> <span style="color: red;">(*)</span></label>
																	<select class="default-select2 form-control" id="txt_24" style="width: 100%;"   placeholder="<%= lang1.getProperty("responzable1") %>" >
																	</select>
																</div>
							                           	  	</div>
							                           	  	<div class="col-md-4 ">
							                           	  		<div class="form-group" id="label_25">
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_responzable2") %>"><%= lang1.getProperty("responzable2") %> <span style="color: red;">(*)</span></label>
																	<select class="default-select2 form-control" id="txt_25" style="width: 100%;"   placeholder="<%= lang1.getProperty("responzable2") %>" >
																	</select>
																</div>
							                           	  	</div>
							                           	  	<div class="col-md-4 ">
							                           	  		<div class="form-group" id="label_26">
																	<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_responzable3") %>"><%= lang1.getProperty("responzable3") %> <span style="color: red;">(*)</span></label>
																	<select class="default-select2 form-control" id="txt_26" style="width: 100%;"   placeholder="<%= lang1.getProperty("responzable3") %>" >
																	</select>
																</div>
							                           	  	</div>
							                           	  	
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
											<div class="tab-pane fade" id="default-tab-3">
												<p>
													<span class="fa-stack fa-4x pull-left m-r-10">
														<i class="fa fa-square-o fa-stack-2x"></i>
														<i class="fa fa-twitter fa-stack-1x"></i>
													</span>
													Praesent tincidunt nulla ut elit vestibulum viverra. Sed placerat magna eget eros accumsan elementum. 
													Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam quis lobortis neque. 
													Maecenas justo odio, bibendum fringilla quam nec, commodo rutrum quam. 
													Donec cursus erat in lacus congue sodales. Nunc bibendum id augue sit amet placerat. 
													Quisque et quam id felis tempus volutpat at at diam. Vivamus ac diam turpis.Sed at lacinia augue. 
													Nulla facilisi. Fusce at erat suscipit, dapibus elit quis, luctus nulla. 
													Quisque adipiscing dui nec orci fermentum blandit.
													Sed at lacinia augue. Nulla facilisi. Fusce at erat suscipit, dapibus elit quis, luctus nulla. 
													Quisque adipiscing dui nec orci fermentum blandit.
												</p>
											</div>
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
                    <div class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">Hoja de Especificacion de Proyecto</h4>
                            
						</div>
                       
                       	<div class="panel-body panel-form" style="margin-top: 10px;" id="panel_04">
                            <div class="col-md-12">
	                           <ul class="nav nav-tabs">
									<li class="active"><a href="#work-pss-1" data-toggle="tab" aria-expanded="true">General</a></li>
									<li class=""><a href="#work-pss-2" data-toggle="tab" aria-expanded="false">Cuestionario</a></li>
									<li class=""><a href="#work-pss-3" data-toggle="tab" aria-expanded="false">Recoleccion</a></li>
									<li class=""><a href="#work-pss-4" data-toggle="tab" aria-expanded="false">Codificacion</a></li>
									<li class=""><a href="#work-pss-5" data-toggle="tab" aria-expanded="false">Procesamiento</a></li>
									<li class=""><a href="#work-pss-6" data-toggle="tab" aria-expanded="false">Marketing Sience</a></li>
								</ul>
									<div class="tab-content">
										<div class="tab-pane fade active in" id="default-tab-1">
											<h3 class="m-t-10"><i class="fa fa-cog"></i> Lorem ipsum dolor sit amet</h3>
											<p>
												Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
												Integer ac dui eu felis hendrerit lobortis. Phasellus elementum, nibh eget adipiscing porttitor, 
												est diam sagittis orci, a ornare nisi quam elementum tortor. Proin interdum ante porta est convallis 
												dapibus dictum in nibh. Aenean quis massa congue metus mollis fermentum eget et tellus. 
												Aenean tincidunt, mauris ut dignissim lacinia, nisi urna consectetur sapien, nec eleifend orci eros id lectus.
											</p>
											<p class="text-right m-b-0">
												<a href="javascript:;" class="btn btn-white m-r-5">Default</a>
												<a href="javascript:;" class="btn btn-primary">Primary</a>
											</p>
										</div>
										<div class="tab-pane fade" id="default-tab-2">
											<blockquote>
												<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
												<small>Someone famous in <cite title="Source Title">Source Title</cite></small>
											</blockquote>
											<h4>Lorem ipsum dolor sit amet</h4>
											<p>
												Nullam ac sapien justo. Nam augue mauris, malesuada non magna sed, feugiat blandit ligula. 
												In tristique tincidunt purus id iaculis. Pellentesque volutpat tortor a mauris convallis, 
												sit amet scelerisque lectus adipiscing.
											</p>
										</div>
										<div class="tab-pane fade" id="default-tab-3">
											<p>
												<span class="fa-stack fa-4x pull-left m-r-10">
													<i class="fa fa-square-o fa-stack-2x"></i>
													<i class="fa fa-twitter fa-stack-1x"></i>
												</span>
												Praesent tincidunt nulla ut elit vestibulum viverra. Sed placerat magna eget eros accumsan elementum. 
												Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam quis lobortis neque. 
												Maecenas justo odio, bibendum fringilla quam nec, commodo rutrum quam. 
												Donec cursus erat in lacus congue sodales. Nunc bibendum id augue sit amet placerat. 
												Quisque et quam id felis tempus volutpat at at diam. Vivamus ac diam turpis.Sed at lacinia augue. 
												Nulla facilisi. Fusce at erat suscipit, dapibus elit quis, luctus nulla. 
												Quisque adipiscing dui nec orci fermentum blandit.
												Sed at lacinia augue. Nulla facilisi. Fusce at erat suscipit, dapibus elit quis, luctus nulla. 
												Quisque adipiscing dui nec orci fermentum blandit.
											</p>
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
                    <div class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">Encuesta Ceps</h4>
                        </div>
                        
                        <div class="panel-body panel-form" id="panel_03">
                           
                           <div class="row">
                           	  	<div class="form-group col-md-6">
                           	  		<h3 id="h2_1" style="margin-left: 20px; color: #f59c1a;">Compromiso de Inicio de Etapas</h3>
                           	  		
                           	  	</div>
                           	    <div class="form-group col-md-6">
                           	  		<h3 id="h2_1" style="margin-left: 20px; color: #f59c1a;">Compromiso de Fin de Etapas</h3>
                           	  		
		                            <p class="text-right m-b-0" style="margin-right: 20px;">
	                           	  		<a href="javascript:;" class="btn btn-primary" id="btn_mod_cal_01"><i class="fa fa-calendar"></i> Modificar Admin</a>
	                           	  	</p>
		                            
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
                    <div class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">Plazos Comprometidos</h4>
                        </div>
                        
                        <div class="panel-body panel-form" id="panel_03">
                           
                           <div class="row">
                           	  	<div class="form-group col-md-6">
                           	  		<h3 id="h2_1" style="margin-left: 20px; color: #f59c1a;">Compromiso de Inicio de Etapas</h3>
                           	  		<table class="table table-bordered">
		                                <thead>
		                                    <tr>
		                                        <th>Detalle</th>
		                                        <th>Valor</th>
		                                    </tr>
		                                </thead>
		                                <tbody>
		                                    <tr>
		                                        <td><label class="control-label col-lg-12" style="font-weight: bold;">Fecha Inicio Campo </label></td>
		                                        <td>
		                                        	<div class="col-md-12">
				                                       <input type="text" id="req_fechas_01" class="form-control"  value="" readonly="readonly" />
													</div>
		                                        
		                                        </td>
		                                    </tr>
		                                    <tr>
		                                        <td><label class="control-label col-lg-12" style="font-weight: bold;">Fecha Inicio BBDD </label></td>
		                                        <td>
		                                        	<div class="col-md-12">
				                                       <input type="text" id="req_fechas_02" class="form-control"  value="" readonly="readonly" />
													</div>
		                                        
		                                        </td>
		                                    </tr>
		                                    <tr>
		                                        <td><label class="control-label col-lg-12" style="font-weight: bold;">Fecha Entrega </label></td>
		                                        <td>
		                                        	<div class="col-md-12">
				                                       <input type="text" id="req_fechas_03" class="form-control"  value="" readonly="readonly" />
													</div>
		                                        
		                                        </td>
		                                    </tr>
		                                   
		                                    
		                                </tbody>
		                            </table>
                           	  	</div>
                           	    <div class="form-group col-md-6">
                           	  		<h3 id="h2_1" style="margin-left: 20px; color: #f59c1a;">Compromiso de Fin de Etapas</h3>
                           	  		<table class="table table-bordered">
		                                <thead>
		                                    <tr>
		                                        <th>Detalle</th>
		                                        <th>Valor</th>
		                                    </tr>
		                                </thead>
		                                <tbody>
		                                    <tr>
		                                        <td><label class="control-label col-lg-12" style="font-weight: bold;">Fecha Fin Campo </label></td>
		                                        <td>
		                                        	<div class="col-md-12">
				                                       <input type="text" id="req_fechas_04" class="form-control"  value="" readonly="readonly" />
													</div>
		                                        
		                                        </td>
		                                    </tr>
		                                    <tr>
		                                        <td><label class="control-label col-lg-12" style="font-weight: bold;">Fecha Fin BBDD </label></td>
		                                        <td>
		                                        	<div class="col-md-12">
				                                       <input type="text" id="req_fechas_05" class="form-control"  value="" readonly="readonly" / >
													</div>
		                                        
		                                        </td>
		                                    </tr>
		                                    
		                                    
		                                </tbody>
		                            </table>
		                            <p class="text-right m-b-0" style="margin-right: 20px;">
	                           	  		<a href="javascript:;" class="btn btn-primary" id="btn_mod_cal_01"><i class="fa fa-calendar"></i> Modificar Admin</a>
	                           	  	</p>
		                            
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
                    <div class="panel panel-warning">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">Dashboard Workflow</h4>
                        </div>
                        
                        <div class="panel-body panel-form" id="panel_05">
                           <div class="col-md-12">
	                        	<div class="panel-body panel-form" style="margin-top: 10px;">
	                           
		                           <ul class="nav nav-tabs">
										<li class="active"><a href="#work-etapa-1" data-toggle="tab" aria-expanded="true">Puesta en Marcha</a></li>
										<li class=""><a href="#dwork-etapa-2" data-toggle="tab" aria-expanded="false">Implementacion</a></li>
										<li class=""><a href="#dwork-etapa-3" data-toggle="tab" aria-expanded="false">Recoleccion</a></li>
										<li class=""><a href="#dwork-etapa-4" data-toggle="tab" aria-expanded="false">Codificacion</a></li>
										<li class=""><a href="#dwork-etapa-5" data-toggle="tab" aria-expanded="false">Digitacion</a></li>
										<li class=""><a href="#dwork-etapa-6" data-toggle="tab" aria-expanded="false">Depuracion</a></li>
										<li class=""><a href="#dwork-etapa-7" data-toggle="tab" aria-expanded="false">Tabulacion</a></li>
										<li class=""><a href="#dwork-etapa-8" data-toggle="tab" aria-expanded="false">Entrega</a></li>
									</ul>
										<div class="tab-content">
											<div class="tab-pane fade active in" id="work-etapa-1">
												<h3 class="m-t-10" style="color: #f59c1a;"><i class="fa fa-cog"></i> Fechas de Ejecución Etapa</h3>
												<div class="row">
					                           	  	<div class="form-group col-md-6">
					                           	  		
					                           	  		<table class="table table-bordered">
							                                <thead>
							                                    <tr>
							                                        <th>Detalle</th>
							                                        <th>Estado</th>
							                                    </tr>
							                                </thead>
							                                <tbody>
							                                    <tr>
							                                        <td><label class="control-label col-lg-12" style="font-weight: bold;">Fecha Inicio Puesta en Marcha </label></td>
							                                        <td>
							                                        	<div class="col-md-12">
									                                       <input type="text" id="work_fechas_01" class="form-control"  value="" readonly="readonly" />
																		</div>
							                                        
							                                        </td>
							                                    </tr>
							                                    <tr>
							                                        <td><label class="control-label col-lg-12" style="font-weight: bold;">Fecha Fin Puesta en Marcha </label></td>
							                                        <td>
							                                        	<div class="col-md-12">
									                                       <input type="text" id="work_fechas_02" class="form-control"  value="" readonly="readonly" />
																		</div>
							                                        
							                                        </td>
							                                    </tr>
							                                    
							                                   
							                                    
							                                </tbody>
							                            </table>
					                           	  	</div>
					                           	    <div class="form-group col-md-6">
					                           	  		<!-- 
					                           	  		<h3 id="h2_1" style="margin-left: 20px; color: #f59c1a;">Compromiso de Fin de Etapas</h3>
					                           	  		<table class="table table-bordered">
							                                <thead>
							                                    <tr>
							                                        <th>Detalle</th>
							                                        <th>Valor</th>
							                                    </tr>
							                                </thead>
							                                <tbody>
							                                    <tr>
							                                        <td><label class="control-label col-lg-12" style="font-weight: bold;">Fecha Fin Campo </label></td>
							                                        <td>
							                                        	<div class="col-md-12">
									                                       <input type="text" id="req_fechas_04" class="form-control"  value="" readonly="readonly" />
																		</div>
							                                        
							                                        </td>
							                                    </tr>
							                                    <tr>
							                                        <td><label class="control-label col-lg-12" style="font-weight: bold;">Fecha Fin BBDD </label></td>
							                                        <td>
							                                        	<div class="col-md-12">
									                                       <input type="text" id="req_fechas_05" class="form-control"  value="" readonly="readonly" / >
																		</div>
							                                        
							                                        </td>
							                                    </tr>
							                                    
							                                    
							                                </tbody>
							                            </table>
							                             -->
					                           	  	</div>
					                           	  
					                           	  
					                           	  
					                           	  </div>
					                           	  <h3 class="m-t-10" style="color: #f59c1a;"><i class="fa fa-cog"></i> Ejecución de Actividades</h3>
													<div class="row">
						                           	  	<div class="form-group col-md-6">
						                           	  		
						                           	  		<table class="table table-bordered">
								                                <thead>
								                                    <tr>
								                                        <th>Detalle</th>
								                                        <th>Estado</th>
								                                        <th>Fecha</th>
								                                        <th>Usuario</th>
								                                    </tr>
								                                </thead>
								                                <tbody>
								                                    <!-- 
								                                    <tr>
								                                        <td><label class="control-label col-lg-12" style="font-weight: bold;">Fecha Inicio Puesta en Marcha </label></td>
								                                        <td>
								                                        	<div class="col-md-12">
										                                       <input type="text" id="work_fechas_01" class="form-control"  value="" readonly="readonly" />
																			</div>
								                                        
								                                        </td>
								                                    </tr>
								                                    <tr>
								                                        <td><label class="control-label col-lg-12" style="font-weight: bold;">Fecha Fin Puesta en Marcha </label></td>
								                                        <td>
								                                        	<div class="col-md-12">
										                                       <input type="text" id="work_fechas_02" class="form-control"  value="" readonly="readonly" />
																			</div>
								                                        
								                                        </td>
								                                    </tr>
								                                     -->
								                                   
								                                    
								                                </tbody>
								                            </table>
						                           	  	</div>
						                           	    <div class="form-group col-md-6">
						                           	  		<!-- 
						                           	  		<h3 id="h2_1" style="margin-left: 20px; color: #f59c1a;">Compromiso de Fin de Etapas</h3>
						                           	  		<table class="table table-bordered">
								                                <thead>
								                                    <tr>
								                                        <th>Detalle</th>
								                                        <th>Valor</th>
								                                    </tr>
								                                </thead>
								                                <tbody>
								                                    <tr>
								                                        <td><label class="control-label col-lg-12" style="font-weight: bold;">Fecha Fin Campo </label></td>
								                                        <td>
								                                        	<div class="col-md-12">
										                                       <input type="text" id="req_fechas_04" class="form-control"  value="" readonly="readonly" />
																			</div>
								                                        
								                                        </td>
								                                    </tr>
								                                    <tr>
								                                        <td><label class="control-label col-lg-12" style="font-weight: bold;">Fecha Fin BBDD </label></td>
								                                        <td>
								                                        	<div class="col-md-12">
										                                       <input type="text" id="req_fechas_05" class="form-control"  value="" readonly="readonly" / >
																			</div>
								                                        
								                                        </td>
								                                    </tr>
								                                    
								                                    
								                                </tbody>
								                            </table>
								                             -->
						                           	  	</div>
					                           	  
					                           	  
					                           	  
					                           	  </div>
												<!-- 
												
												<p>
													Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
													Integer ac dui eu felis hendrerit lobortis. Phasellus elementum, nibh eget adipiscing porttitor, 
													est diam sagittis orci, a ornare nisi quam elementum tortor. Proin interdum ante porta est convallis 
													dapibus dictum in nibh. Aenean quis massa congue metus mollis fermentum eget et tellus. 
													Aenean tincidunt, mauris ut dignissim lacinia, nisi urna consectetur sapien, nec eleifend orci eros id lectus.
												</p>
												<p class="text-right m-b-0">
													<a href="javascript:;" class="btn btn-white m-r-5">Default</a>
													<a href="javascript:;" class="btn btn-primary">Primary</a>
												</p>
											
											
											 -->
											
											</div>
											<div class="tab-pane fade" id="default-tab-2">
												<blockquote>
													<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
													<small>Someone famous in <cite title="Source Title">Source Title</cite></small>
												</blockquote>
												<h4>Lorem ipsum dolor sit amet</h4>
												<p>
													Nullam ac sapien justo. Nam augue mauris, malesuada non magna sed, feugiat blandit ligula. 
													In tristique tincidunt purus id iaculis. Pellentesque volutpat tortor a mauris convallis, 
													sit amet scelerisque lectus adipiscing.
												</p>
											</div>
											<div class="tab-pane fade" id="default-tab-3">
												<p>
													<span class="fa-stack fa-4x pull-left m-r-10">
														<i class="fa fa-square-o fa-stack-2x"></i>
														<i class="fa fa-twitter fa-stack-1x"></i>
													</span>
													Praesent tincidunt nulla ut elit vestibulum viverra. Sed placerat magna eget eros accumsan elementum. 
													Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam quis lobortis neque. 
													Maecenas justo odio, bibendum fringilla quam nec, commodo rutrum quam. 
													Donec cursus erat in lacus congue sodales. Nunc bibendum id augue sit amet placerat. 
													Quisque et quam id felis tempus volutpat at at diam. Vivamus ac diam turpis.Sed at lacinia augue. 
													Nulla facilisi. Fusce at erat suscipit, dapibus elit quis, luctus nulla. 
													Quisque adipiscing dui nec orci fermentum blandit.
													Sed at lacinia augue. Nulla facilisi. Fusce at erat suscipit, dapibus elit quis, luctus nulla. 
													Quisque adipiscing dui nec orci fermentum blandit.
												</p>
											</div>
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
                    <div class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">Usuarios Asignados</h4>
                        </div>
                        
                        <div class="panel-body panel-form" style="margin-top: 10px; margin-left: 10px;" id="panel_06">
                           
                           <table id="data-table8" class="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                    	<th width="10"></th>
                                    	<th width="50"> Personal</th>
                                        <th width="200">Cargo</th>
                                        <th width="200"></th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                    	<th width="10"></th>
                                    	<th width="50"> Personal</th>
                                        <th width="200">Cargo</th>
                                        <th width="200"></th>
                                    </tr>
                                </tfoot>
                                <tbody>
                                    
                                </tbody>
                            </table>
                           
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
                            <h4 class="panel-title">Repositorio</h4>
                        </div>
                        
                        <div class="panel-body panel-form" style="margin-top: 10px; margin-left: 10px;" id="panel_06">
                           
                           <table id="data-table1" class="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                    	<th width="10"></th>
                                    	<th width="50"> Ico</th>
                                        <th width="200">Nombre</th>
                                        <th width="200">Tipo Documental</th>
                                        <th width="100">Peso</th>
                                        <th width="100">Usuario</th>
                                        <th width="100">Fecha</th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                    	<th width="10"></th>
                                    	<th width="50"> Ico</th>
                                        <th width="200">Nombre</th>
                                        <th width="200">Tipo Documental</th>
                                        <th width="100">Peso</th>
                                        <th width="100">Usuario</th>
                                        <th width="100">Fecha</th>
                                    </tr>
                                </tfoot>
                                <tbody>
                                    
                                </tbody>
                            </table>
                           
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
                            <h4 class="panel-title">Encuestas</h4>
                        </div>
                        
                        <div class="panel-body panel-form" style="margin-top: 10px; margin-left: 10px;" id="panel_06">
                           
                           <table id="data-table7" class="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                    	<th width="10"></th>
                                    	<th width="50"> Ico</th>
                                        <th width="200">Nombre</th>
                                        <th width="200">Tipo Documental</th>
                                        <th width="100">Peso</th>
                                        <th width="100">Usuario</th>
                                        <th width="100">Fecha</th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                    	<th width="10"></th>
                                    	<th width="50"> Ico</th>
                                        <th width="200">Nombre</th>
                                        <th width="200">Tipo Documental</th>
                                        <th width="100">Peso</th>
                                        <th width="100">Usuario</th>
                                        <th width="100">Fecha</th>
                                    </tr>
                                </tfoot>
                                <tbody>
                                    
                                </tbody>
                            </table>
                           
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
                            <h4 class="panel-title">Valores del Estudio</h4>
                        </div>
                        
                        <div class="panel-body panel-form" style="margin-top: 10px; margin-left: 10px;" id="panel_07">
                            <table class="table table-bordered" style="width: 60%;" >
                                <thead>
                                    <tr>
                                    	<th width="50%">Item</th>
                                        <th width="50%">Monto $</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                    	<td id="res_td_1_1" style="background-color: #dff0d8; font-weight: bold;" >Venta de Proyecto</td>
                                    	<td id="res_td_1_2" style="background-color: #dff0d8; font-weight: bold;" ><input type="text" class="form-control" id="txt_detalle_11" name="txt_detalle_11" value=""/></td>
                                    	
                                    </tr>
                                    <tr>
                                    	<td id="res_td_1_1" style="background-color: #F8E0E6; font-weight: bold;" >Costo Aprobado</td>
                                    	<td id="res_td_1_2" style="background-color: #F8E0E6; font-weight: bold;" ><input type="text" class="form-control" id="txt_detalle_12" name="txt_detalle_12" value=""/></td>
                                    </tr>
                                </tbody>
                            </table>
                           
                           
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            
            
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
                            <h4 class="panel-title">Comparativo Resumen Presupuesto v/s Costos Reales del Proyecto</h4>
                        </div>
                        
                        <div class="panel-body panel-form" style="margin-top: 10px; margin-left: 10px;" id="panel_07">
                            <table class="table table-bordered">
                                <thead>
                                    <tr>
                                    	<th width="30%">Item</th>
                                        <th width="20%">Monto Presupuestado $</th>
                                        <th width="20%">Monto Gasto Real $</th>
                                        <th width="15%">% Cumplimiento</th>
                                        <th width="15%">Desviación</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                    	<td id="res_td_1_1" style="background-color: #dff0d8; font-weight: bold;" >Presupuesto de Costos</td>
                                    	<td id="res_td_1_2" style="background-color: #dff0d8; font-weight: bold;" ><div id="rs_costo_proyecto"></div></td>
                                    	<td id="res_td_1_3" style="background-color: #dff0d8; font-weight: bold;"><div id="rs_por_cumplimiento"></div></td>
                                    	<td id="res_td_1_4" style="background-color: #dff0d8; font-weight: bold;"><div id="rs_por_cumplimiento"></div></td>
                                    	<td id="res_td_1_5" style="background-color: #dff0d8; font-weight: bold;"><div id="rs_por_cumplimiento"></div></td>
                                    </tr>
                                    <tr>
                                    	<td id="res_td_2_1" style="" >Costos Directos</td>
                                    	<td id="res_td_2_2" style="" ><div id="rs_costo_proyecto"></div></td>
                                    	<td id="res_td_2_3" style=""></td>
                                    	<td id="res_td_2_4" style=""><div id="rs_por_cumplimiento"></div></td>
                                    	<td id="res_td_2_5" style=""></td>
                                    </tr>
                                    <tr>
                                    	<td id="res_td_3_1" style="" >Costos Externos</td>
                                    	<td id="res_td_3_2" style="" ><div id="rs_costo_indirecto"></div></td>
                                    	<td id="res_td_3_3" style=""></td>
                                    	<td id="res_td_3_4" style=""><div id="rs_por_cumplimiento"></div></td>
                                    	<td id="res_td_3_5" style=""></td>
                                    </tr>
                                    <tr>
                                    	<td id="res_td_4_1" style="" >Costos de Servicios Internos</td>
                                    	<td id="res_td_4_2" style="" ><div id="rs_costo_sector"></div></td>
                                    	<td id="res_td_4_3" style=""></td>
                                    	<td id="res_td_4_4" style=""><div id="rs_por_cumplimiento"></div></td>
                                    	<td id="res_td_4_5" style=""></td>
                                    </tr>
                                    <tr>
                                    	<td id="res_td_5_1" style="" >Puestos Cotizados</td>
                                    	<td id="res_td_5_2" style="" ><div id="rs_costo_grupo"></div></td>
                                    	<td id="res_td_5_3" style=""></td>
                                    	<td id="res_td_5_4" style=""><div id="rs_por_cumplimiento"></div></td>
                                    	<td id="res_td_5_5" style=""></td>
                                    </tr>
                                </tbody>
                            </table>
                           
                           
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
             <!-- Begin row -->
            <div class="row">
			    <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-danger">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">Historial de Alertas</h4>
                        </div>
                         <a href="/Manager/proyectoService/manProy" target="_self" class="btn btn-primary m-r-5" style="margin-top: 5px; margin-left:10px; text-align: center;" onclick="JavaScript:;">
                         	  	<i class="fa fa-search fa-2x pull-left"></i>
                         	  	Consultar Alertas <br/> Estudio
                         	</a>
                           <br/>
                        <div class="panel-body panel-form" style="margin-top: 10px; margin-left: 10px;" id="panel_09">
                          
                           <table id="data-table7" class="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                    	<th width="10"></th>
                                    	<th width="100"> Fecha</th>
                                        <th width="100">Modulo</th>
                                        <th width="100">Actividad</th>
                                        <th width="200">Detalle</th>
                                        
								    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                    	<th width="10"></th>
                                    	<th width="100"> Fecha</th>
                                        <th width="100">Modulo</th>
                                        <th width="100">Actividad</th>
                                        <th width="200">Detalle</th>
      									
                                    </tr>
                                </tfoot>
                                <tbody>
                                    
                                </tbody>
                            </table>
                           
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
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">Historial de Requerimientos</h4>
                        </div>
                         <a href="/Manager/proyectoService/manProy" target="_self" class="btn btn-primary m-r-5" style="margin-top: 5px; margin-left:10px; text-align: center;" onclick="JavaScript:;">
                         	  	<i class="fa fa-search fa-2x pull-left"></i>
                         	  	Consultar Requerimientos <br/> Estudio
                         	</a>
                           <br/>
                        <div class="panel-body panel-form" style="margin-top: 10px; margin-left: 10px;" id="panel_09">
                          
                           <table id="data-table7" class="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                    	<th width="10"></th>
                                    	<th width="100"> Usuario</th>
                                    	<th width="100"> Fecha</th>
                                        <th width="100">Tipo de Requerimiento</th>
                                        <th width="100">Estado</th>
                                        <th width="350">Detalle</th>
                                        
								    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                    	<th width="10"></th>
                                    	<th width="100">Usuario</th>
                                    	<th width="100">Fecha</th>
                                        <th width="100">Tipo de Requerimiento</th>
                                        <th width="100">Estado</th>
                                        <th width="350">Detalle</th>
      								</tr>
                                </tfoot>
                                <tbody>
                                    
                                </tbody>
                            </table>
                           
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
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">Traza Estudio</h4>
                        </div>
                         <a href="/Manager/proyectoService/manProy" target="_self" class="btn btn-primary m-r-5" style="margin-top: 5px; margin-left:10px; text-align: center;" onclick="JavaScript:;">
                         	  	<i class="fa fa-search fa-2x pull-left"></i>
                         	  	Consultar Traza <br/> Proyecto
                         	</a>
                           <br/>
                        <div class="panel-body panel-form" style="margin-top: 10px; margin-left: 10px;" id="panel_09">
                          
                           <table id="data-table6" class="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                    	<th width="10"></th>
                                    	<th width="100"> Fecha</th>
                                        <th width="100">Usuario</th>
                                        <th width="100">Modulo</th>
                                        <th width="100">Actividad</th>
                                        <th width="200">Detalle</th>
                                        
								    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                    	<th width="10"></th>
                                    	<th width="100"> Fecha</th>
                                        <th width="100">Usuario</th>
                                        <th width="100">Modulo</th>
                                        <th width="100">Actividad</th>
                                        <th width="200">Detalle</th>
      									
                                    </tr>
                                </tfoot>
                                <tbody>
                                    
                                </tbody>
                            </table>
                           
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
	<script src="<c:url value="/resources/manager/plugins/gritter/js/jquery.gritter.js" />"></script>
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
	<script src="<c:url value="/resources/app/tools/tools.js" />"></script>
	<script src="<c:url value="/resources/app/tools/tools-combo-box.js" />"></script>
	<script src="<c:url value="/resources/app/proyecto/detalle.js" />"></script>
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
