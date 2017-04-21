<%@page import="cl.nexo.manager.obj.dashboard.ObjWidjetAccessAdmin"%>
<%@page import="cl.nexo.manager.obj.login.ObjPerfilLogin"%>
<%@page import="cl.nexo.manager.obj.login.ObjLoginUser"%>
<%@page import="cl.nexo.manager.obj.menu.ObjModuloMenuManager"%>
<%@page import="cl.nexo.manager.obj.menu.ObjUnidadMenuManager"%>
<%@page import="java.util.ArrayList"%>
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
 Float id_operacion = Float.parseFloat((String) request.getAttribute("id_operacion"));
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
		
		<!-- begin #content -->
		<div id="content" class="content">
			<!-- begin breadcrumb -->
			<ol class="breadcrumb pull-right">
				<li><a href="/Manager/home" target="_self">Home</a></li>
				<li><a href="#">Proyectos</a></li>
				<li class="active">Detalle de Proyectos</li>
			</ol>
			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<h1 class="page-header">Mantenedor de Proyectos <small>Gestión Detalle de Proyecto...</small></h1>
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
                          			<input type="hidden" id="txt_detalle_1" name="txt_detalle_1" value="<%=  String.format("%.0f",id_operacion) %>" />
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
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Nombre Proyecto</label>
										<div class="col-md-8">
	                                        <input type="text" class="form-control" id="txt_detalle_03" name="txt_detalle_03" value=""/>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Nombre del estudio </label>
										<div class="col-md-8">
	                                        <input type="text" class="form-control" id="txt_detalle_04" name="txt_detalle_04" value=""/>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Area</label>
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
								 <div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Precio Venta $ </label>
										<div class="col-md-8">
	                                         <input type="text" class="form-control" id="txt_detalle_11" name="txt_detalle_11" value=""/>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Precio Venta UF </label>
										<div class="col-md-8">
	                                         <input type="text" class="form-control" id="txt_detalle_12" name="txt_detalle_12" value=""/>
										</div>
									</div>
								 </div>
								 <div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Weekly semana </label>
										<div class="col-md-8">
	                                        <input type="text" class="form-control" id="txt_detalle_13" name="txt_detalle_13" value=""/>
										</div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Weekly año </label>
										<div class="col-md-8">
	                                        <input type="text" class="form-control" id="txt_detalle_14" name="txt_detalle_14" value=""/>
										</div>
									</div>
								 </div>
								 <div class="row">
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Coordinador </label>
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
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Estado de Estudio </label>
										<div class="col-md-8">
	                                        <input type="text" class="form-control" id="txt_detalle_18" name="txt_detalle_18" value="" readonly="readonly"/>
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
                            <h4 class="panel-title">Procesamiento y Requerimientos Adicionales</h4>
                        </div>
                        
                        <div class="panel-body panel-form" id="panel_02">
                           
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
		                                        <td><input id="req_switch_01" type="checkbox" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch"></td>
		                                        <td><label class="control-label col-lg-4" style="font-weight: bold;">Revision</label></td>
		                                    </tr>
		                                    <tr>
		                                        <td><input id="req_switch_02" type="checkbox" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch"></td>
		                                        <td><label class="control-label col-lg-4" style="font-weight: bold;">Supervision</label></td>
		                                    </tr>
		                                    <tr>
		                                        <td><input id="req_switch_03" type="checkbox" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch"></td>
		                                        <td><label class="control-label col-lg-4" style="font-weight: bold;">Codificacion</label></td>
		                                    </tr>
		                                    <tr>
		                                       <td><input id="req_switch_04" type="checkbox" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch"></td>
		                                        <td><label class="control-label col-lg-4" style="font-weight: bold;">Digitacion</label></td>
		                                    </tr>
		                                    <tr>
		                                       <td><input id="req_switch_05" type="checkbox" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch"></td>
		                                        <td><label class="control-label col-lg-4" style="font-weight: bold;">Procesamiento</label></td>
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
		                                        	<label class="control-label col-lg-4" style="font-weight: bold;">% Supervision</label>
													<div class="col-md-8">
				                                       <input type="number" id="req_switch_06_aux_01" class="form-control"  value="" >
													</div>
		                                        
		                                        </td>
		                                    </tr>
		                                    <tr>
		                                        <td><!--  <input id="req_switch_07" type="checkbox" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">--></td>
		                                        <td>
		                                        	<label class="control-label col-lg-4" style="font-weight: bold;">% Re-Digitacion</label>
													<div class="col-md-8">
				                                       <input type="number" id="req_switch_07_aux_01" class="form-control"  value="" >
													</div>
		                                        </td>
		                                    </tr>
		                                    <tr>
		                                        <td><input id="req_switch_08" type="checkbox" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch"></td>
		                                        <td><label class="control-label col-lg-4" style="font-weight: bold;">Libro de Codigo</label></td>
		                                    </tr>
		                                    <tr>
		                                       <td><input id="req_switch_09" type="checkbox" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch"></td>
		                                        <td><label class="control-label col-lg-4" style="font-weight: bold;">Verbatim</label></td>
		                                    </tr>
		                                    <tr>
		                                       <td><input id="req_switch_10" type="checkbox" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch"></td>
		                                        <td><label class="control-label col-lg-4" style="font-weight: bold;">Pre-Proceso</label></td>
		                                    </tr>
		                                </tbody>
		                            </table>
                           	  	</div>
                           	  </div>
                           	  <div class="row">
                           	  	<div class="form-group col-md-6">
                           	  		<h3 id="h2_1" style="margin-left: 20px; color: #f59c1a;">Otros Datos</h3>
                           	  		<table class="table table-bordered">
		                                <thead>
		                                    <tr>
		                                        <th>Detalle</th>
		                                        <th>Valor</th>
		                                    </tr>
		                                </thead>
		                                <tbody>
		                                    <tr>
		                                        <td><!-- <input id="req_switch_11" type="checkbox" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch"> --></td>
		                                        <td>
		                                        	<label class="control-label col-lg-4" style="font-weight: bold;">Abiertas a Codificar</label>
													<div class="col-md-8">
				                                       <input type="number" id="req_switch_11_aux_01" class="form-control"  value="" >
													</div>
		                                        
		                                        </td>
		                                    </tr>
		                                    <tr>
		                                        <td><!--<input id="req_switch_12" type="checkbox" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">--></td>
		                                        <td>
		                                        	<label class="control-label col-lg-4" style="font-weight: bold;">Otros a Codificar</label>
													<div class="col-md-8">
				                                       <input type="number" id="req_switch_12_aux_01" class="form-control"  value="" >
													</div>
		                                        </td>
		                                    </tr>
		                                    <tr>
		                                        <td><!--<input id="req_switch_13" type="checkbox" data-off-text="False" data-on-text="True" data-switch-value="false" class="BSswitch">--></td>
		                                        <td>
		                                        	<label class="control-label col-lg-4" style="font-weight: bold;">TMO</label>
		                                        	<div class="col-md-8">
				                                       <input type="number" id="req_switch_13_aux_01" class="form-control"  value="" >
													</div>
		                                        </td>
		                                    </tr>
		                                    
		                                </tbody>
		                            </table>
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
                            <h4 class="panel-title">Costos del Proyecto</h4>
                        </div>
                        
                        <div id="panel_08" class="panel-body panel-form" style="margin-top: 10px; margin-left: 10px;">
                           <h3 class="m-t-10" style=" font-weight: bold;">Fijos</h3>
                           <br/>
                           <h3 class="m-t-10" style=" color: #f59c1a;">Costos Directos del Proyecto</h3>
                           <br/>
                           <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th width="40%">Detalle</th>
                                        <th width="15%">Cantidad</th>
                                        <th width="15%">Costo Unitario</th>
                                        <th width="15%">Total</th>
                                        <th width="15%"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                        	<select class="default-select2 form-control" id="txt_cfijo_01" name="txt_cfijo_01"></select>
                                        </td>
                                        <td>
                                        	<input type="number" class="form-control" name="txt_add_cfijo_01" id="txt_add_cfijo_01" />
                                        </td>
                                        <td>
                                        	<input type="number" class="form-control" name="txt_add_cfijo_02" id="txt_add_cfijo_02" />
                                        </td>
                                        <td>
                                        	<input type="number" class="form-control" name="txt_add_cfijo_03" id="txt_add_cfijo_03" />
                                        </td>
                                        <td align="center">
                                        	<input type="button" class="btn btn-success m-r-5 m-b-5" name="btn_add_costo_01" id="btn_add_costo_01" value="Agregar Costo Fijo" />
                                        </td>
                                    </tr>
                                    
                                    
                                </tbody>
                            </table>
                           
                           <br/>
                           <table id="data-table2" class="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                    	<th width="10"></th>
                                    	<th width="300"> Detalle</th>
                                        <th width="100">Cantidad</th>
                                        <th width="200">Costo Unitario</th>
                                        <th width="200">Total</th>
                                        
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                    	<th width="10"></th>
                                    	<th width="300"> Detalle</th>
                                        <th width="100">Cantidad</th>
                                        <th width="200">Costo Unitario</th>
                                        <th width="200">Total</th>
                                    </tr>
                                </tfoot>
                                <tbody>
                                    
                                </tbody>
                            </table>
                            <br/><br/>
                           <h3 class="m-t-10" style=" color: #f59c1a;">Costos Externos del Proyecto</h3>
                           <br/>
                           <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th width="40%">Detalle</th>
                                        <th width="15%">Cantidad</th>
                                        <th width="15%">Costo Unitario</th>
                                        <th width="15%">Total</th>
                                        <th width="15%"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                        	<select class="default-select2 form-control" id="txt_cfijo_02" name="txt_cfijo_02"></select>
                                        </td>
                                        <td>
                                        	<input type="number" class="form-control" name="txt_add_cfijo_01" id="txt_add_cfijo_01" />
                                        </td>
                                        <td>
                                        	<input type="number" class="form-control" name="txt_add_cfijo_02" id="txt_add_cfijo_02" />
                                        </td>
                                        <td>
                                        	<input type="number" class="form-control" name="txt_add_cfijo_03" id="txt_add_cfijo_03" />
                                        </td>
                                        <td align="center">
                                        	<input type="button" class="btn btn-success m-r-5 m-b-5" name="btn_add_costo_02" id="btn_add_costo_02" value="Agregar Costo Externo" />
                                        </td>
                                    </tr>
                                    
                                    
                                </tbody>
                            </table>
                           
                           <br/>
                           <table id="data-table3" class="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                    	<th width="10"></th>
                                    	<th width="300"> Detalle</th>
                                        <th width="100">Cantidad</th>
                                        <th width="200">Costo Unitario</th>
                                        <th width="200">Total</th>
                                        
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                    	<th width="10"></th>
                                    	<th width="300"> Detalle</th>
                                        <th width="100">Cantidad</th>
                                        <th width="200">Costo Unitario</th>
                                        <th width="200">Total</th>
                                    </tr>
                                </tfoot>
                                <tbody>
                                    
                                </tbody>
                            </table>
                            <br/><br/>
                           <h3 class="m-t-10" style=" color: #f59c1a;">Costos de Servicios Internos</h3>
                           <br/>
                           <table class="table table-bordered">
                                <thead>
                                    <tr>
                                        <th width="40%">Detalle</th>
                                        <th width="15%">Cantidad</th>
                                        <th width="15%">Costo Unitario</th>
                                        <th width="15%">Total</th>
                                        <th width="15%"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                        	<select class="default-select2 form-control" id="txt_cfijo_03" name="txt_cfijo_03"></select>
                                        </td>
                                        <td>
                                        	<input type="number" class="form-control" name="txt_add_cfijo_01" id="txt_add_cfijo_01" />
                                        </td>
                                        <td>
                                        	<input type="number" class="form-control" name="txt_add_cfijo_02" id="txt_add_cfijo_02" />
                                        </td>
                                        <td>
                                        	<input type="number" class="form-control" name="txt_add_cfijo_03" id="txt_add_cfijo_03" />
                                        </td>
                                        <td align="center">
                                        	<input type="button" class="btn btn-success m-r-5 m-b-5" name="btn_add_costo_03" id="btn_add_costo_03" value="Agregar Servicio Interno" />
                                        </td>
                                    </tr>
                                    
                                    
                                </tbody>
                            </table>
                           
                           <br/>
                           <table id="data-table4" class="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                    	<th width="10"></th>
                                    	<th width="300"> Detalle</th>
                                        <th width="100">Cantidad</th>
                                        <th width="200">Costo Unitario</th>
                                        <th width="200">Total</th>
                                        
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                    	<th width="10"></th>
                                    	<th width="300"> Detalle</th>
                                        <th width="100">Cantidad</th>
                                        <th width="200">Costo Unitario</th>
                                        <th width="200">Total</th>
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
    <!--[if (gte IE 8)&(lt IE 10)]>
        <script src="<c:url value="/resources/manager/plugins/jquery-file-upload/js/cors/jquery.xdr-transport.js" />"></script>
    <![endif]-->
	<!-- ================== END PAGE LEVEL JS ================== -->
	<script src="<c:url value="/resources/app/tools/tools.js" />"></script>
	<script src="<c:url value="/resources/app/tools/tools-combo-box.js" />"></script>
	<script src="<c:url value="/resources/app/proyecto-habilitacion/detalle.js" />"></script>
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
