<%@page import="cl.nexo.manager.obj.dashboard.ObjWidjetAccessAdmin"%>
<%@page import="cl.nexo.manager.obj.login.ObjPerfilLogin"%>
<%@page import="cl.nexo.manager.obj.login.ObjLoginUser"%>
<%@page import="cl.nexo.manager.obj.menu.ObjModuloMenuManager"%>
<%@page import="cl.nexo.manager.obj.menu.ObjUnidadMenuManager"%>
<%@page import="cl.nexo.manager.obj.tools.ObjConfigTools"%>
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
 ObjConfigTools tol = (ObjConfigTools) request.getAttribute("tol");
 String moneda = (String) request.getAttribute("moneda");
 String legal_entity = (String) request.getAttribute("legal_entity");
 String geografia = (String) request.getAttribute("geografia");
 String sector = (String) request.getAttribute("sector");
 //lang option
 String langOption = (String) request.getAttribute("lang");
 URL url = application.getResource("/WEB-INF/lang/"+langOption+"/estudio.properties");
 InputStream in = url.openStream();
 Properties lang1 = new Properties();
 lang1.load(in);
 
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="<%= lang1.getProperty("lang") %>" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="<%= lang1.getProperty("lang") %>">
<!--<![endif]-->
<head>
	<meta charset="utf-8">
	<title><%= lang1.getProperty("title") %></title>
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
	<!-- ================== BEGIN PAGE LEVEL STYLE ================== -->
	<link href="<c:url value="/resources/manager/plugins/bootstrap-wizard/css/bwizard.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/parsley/src/parsley.css" />" rel="stylesheet">
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
		<!-- end #sidebar
		<!-- end #sidebar -->
		<!-- Configuracion Interna  -->
		<input type="hidden" id="txt_detalle_1" name="txt_detalle_1" value="" />
		<input type="hidden" id="conf_01"  name="conf_01" value="" />
		<input type="hidden" id="conf_02"  name="conf_01" value="" /> 
		<!-- begin #content -->
		<div id="content" class="content">
			<!-- begin breadcrumb -->
			<ol class="breadcrumb pull-right">
				<li><a href="/Manager/home" target="_self">Home</a></li>
				<li><a href="#"><%= lang1.getProperty("navProyecto") %></a></li>
				<li class="active"><%= lang1.getProperty("navEstudio") %></li>
			</ol>
			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<h1 class="page-header"><%= lang1.getProperty("nuevoCotizacion") %> <small><%= lang1.getProperty("nuevoSmallHeaderCot") %></small></h1>
			<h1 class="text-success" id="new_cot1"></h1>
			<!-- end page-header -->
			<!-- BEGIN Config General -->
			<%@ include file="aditional-info.jsp" %>
			<!-- END Config General -->	
			<!-- begin row -->
			<div class="row">
                <!-- begin col-12 -->
			    <div class="col-md-12">
			        <!-- begin panel -->
                    <div class="panel panel-inverse" id="panel-new">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title"><%= lang1.getProperty("ventana1wizard") %></h4>
                        </div>
                        <div class="panel-body">
                            <div class="alert alert-danger fade in m-b-15" id="error-new">
								<strong>Error!</strong>
								<!-- <span class="close" data-dismiss="alert">X</span>-->
								<div id="etext-newlogin"></div>
							</div>
                            <form action="/" method="POST" data-parsley-validate="true" name="form-wizard">
								<div id="wizard">
									<ol>
										<li>
										    <%= lang1.getProperty("pestana1") %>
										    <small><%= lang1.getProperty("smallpestana1") %></small>
										</li>
										<li>
										    <%= lang1.getProperty("pestana2") %>
										    <small><%= lang1.getProperty("smallpestana2") %></small>
										</li>
										<li>
										   <%= lang1.getProperty("pestana3") %>
										    <small><%= lang1.getProperty("smallpestana3") %></small>
										</li>
										<li>
										    <%= lang1.getProperty("pestana4") %>
										    <small><%= lang1.getProperty("smallpestana4") %></small>
										</li>
										<li>
										    <%= lang1.getProperty("pestana5") %>
										    <small><%= lang1.getProperty("smallpestana5") %></small>
										</li>
										<li>
										    <%= lang1.getProperty("pestana6") %>
										    <small><%= lang1.getProperty("smallpestana6") %></small>
										</li>
									</ol>
									<!-- begin wizard step-1 -->
									<div class="wizard-step-1">
                                        <fieldset>
                                            <legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew1") %></legend>
                                            
                                            <!--begin row -->
                                            <div class="row">
                                                <!-- begin col-4 -->
                                                <div class="col-md-4">
													<div class="form-group block1">
														<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_tipocotizacion") %>"><%= lang1.getProperty("tipocotizacion") %> <span style="color: red;">(*)</span></label>
														<select class="default-select2 form-control" id="txt_08" data-parsley-group="wizard-step-1"  placeholder="<%= lang1.getProperty("tipocotizacion") %>" >
														</select>
													</div>
                                                </div>
                                                <!-- end col-4 -->
                                                <!-- begin col-4 -->
                                                <div class="col-md-4">
													<div class="form-group" id="label_09">
														<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_geografia") %>"><%= lang1.getProperty("geografia") %> <span style="color: red;">(*)</span></label>
														<select class="default-select2 form-control" id="txt_09" data-parsley-group="wizard-step-1"  style="width: 100%;" >
														</select>
													</div>
                                                </div>
                                                <!-- end col-4 -->
                                                <!-- begin col-4 -->
                                                <div class="col-md-4">
													<div class="form-group" id="group_01">
														<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_cantidad_paises") %>"><%= lang1.getProperty("cantidad_paises") %></label>
														<select class="default-select2 form-control" id="txt_10" data-parsley-group="wizard-step-1"  placeholder="<%= lang1.getProperty("cantidad_paises") %>" >
															<option value="1" selected="selected">1</option>
															<option value="2">2</option>
															<option value="3">3</option>
															<option value="4">4</option>
															<option value="5">5</option>
															<option value="6">6</option>
															<option value="7">7</option>
															<option value="8">8</option>
															<option value="9">9</option>
															<option value="10">10</option>
															<option value="11">11</option>
															<option value="12">12</option>
															<option value="13">13</option>
															<option value="14">14</option>
															<option value="15">15</option>
															<option value="16">16</option>
															<option value="17">17</option>
															<option value="18">18</option>
															<option value="19">19</option>
															<option value="20">20</option>
														</select>
													</div>
													
                                                </div>
                                                <!-- end col-4 -->
                                            </div>
                                            
                                            
                                            <!--begin row -->
                                            <div class="row">
                                                <!-- begin col-4 -->
                                                <div class="col-md-4">
													<div class="form-group block1" id="label_05">
														<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_clienteestudio") %>"><%= lang1.getProperty("clienteestudio") %> <span style="color: red;">(*)</span></label>
														<select class="default-select2 form-control" id="txt_05" data-parsley-group="wizard-step-1"  placeholder="<%= lang1.getProperty("clienteestudio") %>" >
														</select>
													</div>
                                                </div>
                                                <!-- end col-4 -->
                                                <!-- begin col-4 -->
                                                <div class="col-md-4">
													<div class="form-group" id="label_06">
														<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_clienteestudiofacturar") %>"><%= lang1.getProperty("clienteestudiofacturar") %> <span style="color: red;">(*)</span></label>
														<select class="default-select2 form-control" id="txt_06" data-parsley-group="wizard-step-1"  placeholder="<%= lang1.getProperty("clienteestudiofacturar") %>" >
														</select>
													</div>
                                                </div>
                                                <!-- end col-4 -->
                                                <!-- begin col-4 -->
                                                <div class="col-md-4" >
													<div class="form-group" id="label_07">
														<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_tipocuentacliente") %>"><%= lang1.getProperty("tipocuentacliente") %> <span style="color: red;">(*)</span></label>
														<select class="default-select2 form-control" id="txt_07" data-parsley-group="wizard-step-1" >
														</select>
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
														<select class="default-select2 form-control" id="txt_02" data-parsley-group="wizard-step-1"  placeholder="<%= lang1.getProperty("sector") %>" >
														</select>
													</div>
                                                </div>
                                                <!-- end col-4 -->
                                                <!-- begin col-4 -->
                                                <div class="col-md-4">
													<div class="form-group" id="label_03">
														<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_industria") %>"><%= lang1.getProperty("industria") %> <span style="color: red;">(*)</span></label>
														<select class="default-select2 form-control" id="txt_03" data-parsley-group="wizard-step-1"  placeholder="<%= lang1.getProperty("industria") %>" >
														</select>
													</div>
                                                </div>
                                                <!-- end col-4 -->
                                                <!-- begin col-4 -->
                                                <div class="col-md-4">
													<div class="form-group" id="label_04">
														<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_producto") %>"><%= lang1.getProperty("producto") %> <span style="color: red;">(*)</span></label>
														<select class="multiple-select2 form-control" id="txt_04" multiple="" tabindex="-1" style="display: none;" data-parsley-group="wizard-step-1"  placeholder="<%= lang1.getProperty("producto") %>" >
														</select>
													</div>
                                                </div>
                                                <!-- end col-4 -->
                                            </div>
                                            <!-- end row -->
                                            
                                            <!--begin row -->
                                            <div class="row">
                                                <!-- begin col-4 -->
                                                <div class="col-md-12">
													<div class="form-group block1">
														<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_nombreestudio") %>"><%= lang1.getProperty("nombreestudio") %> <span style="color: red;">(*)</span></label>
														<input type="text" class="form-control" id="txt_01" data-parsley-group="wizard-step-1" placeholder="<%= lang1.getProperty("nombreestudio") %>" >
													</div>
                                                </div>
                                                <!-- end col-4 -->
                                                
                                            </div>
                                            <!-- end row -->
                                            
                                            
                                             
                                            
                                            <!-- begin row -->
                                            <div class="row">
                                                <!-- begin col-4 -->
                                                <div class="col-md-4">
													<div class="form-group block1" id="label_11">
														<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_estudio1") %>"><%= lang1.getProperty("estudio1") %> <span style="color: red;">(*)</span></label>
														<select class="default-select2 form-control" id="txt_11" data-parsley-group="wizard-step-1"  placeholder="<%= lang1.getProperty("estudio1") %>" >
														</select>
													</div>
                                                </div>
                                                <!-- end col-4 -->
                                                <!-- begin col-4 -->
                                                <div class="col-md-4">
													<div class="form-group" id="label_12">
														<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_tipoentrevista") %>"><%= lang1.getProperty("tipoentrevista") %> <span style="color: red;">(*)</span></label>
														<select class="default-select2 form-control" id="txt_12"  data-parsley-group="wizard-step-1"  placeholder="<%= lang1.getProperty("tipoentrevista") %>" >
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
														<select class="default-select2 form-control" id="txt_13" data-parsley-group="wizard-step-1" style="width: 100%;" >
														</select>
													</div>
                                                </div>
                                                <!-- end col-4 -->
                                                <!-- begin col-4 -->
                                                <div class="col-md-4">
													<div class="form-group">
														<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_legalEntityName") %>"><%= lang1.getProperty("legalEntityName") %></label>
														<input type="text"  class="default-select2 form-control" id="txt_14"  data-parsley-group="wizard-step-1" placeholder="<%= lang1.getProperty("legalEntityName") %>" readonly="readonly" />
													</div>
                                                </div>
                                                <!-- end col-4 -->
                                                <!-- begin col-4 -->
                                                <div class="col-md-4">
													<div class="form-group">
														<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_centroCosto") %>"><%= lang1.getProperty("centroCosto") %> <span style="color: red;">(*)</span></label>
														<input type="number"  class="default-select2 form-control" id="txt_15"  data-parsley-group="wizard-step-1" placeholder="<%= lang1.getProperty("centroCosto") %>" />
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
														<input type="number"  class="default-select2 form-control" id="txt_16"  data-parsley-group="wizard-step-1" placeholder="<%= lang1.getProperty("probabilidadproyecto") %>" />
													</div>
                                                </div>
                                                <!-- end col-4 -->
                                                <!-- begin col-4 -->
                                                <div class="col-md-4">
													<div class="form-group" id="label_17">
														<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_digital") %>"><%= lang1.getProperty("digital") %> <span style="color: red;">(*)</span></label>
														<select class="default-select2 form-control" id="txt_17" data-parsley-group="wizard-step-1"  placeholder="<%= lang1.getProperty("digital") %>" >
														</select>
													</div>
                                                </div>
                                                <!-- end col-4 -->
                                                <!-- begin col-4 -->
                                                <div class="col-md-4">
													<div class="form-group" id="label_18">
														<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_moneda") %>"><%= lang1.getProperty("moneda") %> <span style="color: red;">(*)</span></label>
														<select class="default-select2 form-control" id="txt_18" data-parsley-group="wizard-step-1"  placeholder="<%= lang1.getProperty("moneda") %>" >
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
														<input type="text" class="form-control calendar" id="txt_19" data-parsley-group="wizard-step-1" placeholder="<%= lang1.getProperty("fechaProbInicioEstudio") %>"  />
													</div>
                                                </div>
                                                <!-- end col-4 -->
                                                <!-- begin col-4 -->
                                                <div class="col-md-4">
													<div class="form-group">
														<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_fechaProbEntregaEstudio") %>"><%= lang1.getProperty("fechaProbEntregaEstudio") %> <span style="color: red;">(*)</span></label>
														<input type="text" class="form-control calendar" id="txt_20" data-parsley-group="wizard-step-1" placeholder="<%= lang1.getProperty("fechaProbEntregaEstudio") %>"  />
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
														<input type="text" class="form-control calendar" id="txt_21" data-parsley-group="wizard-step-1" placeholder="<%= lang1.getProperty("deadPresentacionEquipo") %>"  />
													</div>
                                                </div>
                                                <!-- end col-4 -->
                                                <!-- begin col-4 -->
                                                <div class="col-md-4">
													<div class="form-group">
														<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_deadPresentacionGps") %>"><%= lang1.getProperty("deadPresentacionGps") %> <span style="color: red;">(*)</span></label>
														<input type="text" class="form-control calendar" id="txt_22" data-parsley-group="wizard-step-1" placeholder="<%= lang1.getProperty("deadPresentacionGps") %>"  />
													</div>
                                                </div>
                                                <!-- end col-4 -->
                                                <!-- begin col-4 -->
                                                <div class="col-md-4">
													<div class="form-group">
														<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_deadPresentacionCliente") %>"><%= lang1.getProperty("deadPresentacionCliente") %> <span style="color: red;">(*)</span></label>
														<input type="text" class="form-control calendar" id="txt_23" data-parsley-group="wizard-step-1" placeholder="<%= lang1.getProperty("deadPresentacionCliente") %>"  />
													</div>
                                                </div>
                                                <!-- end col-4 -->
                                            </div>
                                            <legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew5") %></legend>
                                            <div class="row">
					                           	  	<div class="col-md-4 ">
					                           	  		<div class="form-group" id="label_24">
															<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_responzable1") %>"><%= lang1.getProperty("responzable1") %> <span style="color: red;">(*)</span></label>
															<select class="default-select2 form-control" id="txt_24" data-parsley-group="wizard-step-1"  placeholder="<%= lang1.getProperty("responzable1") %>" >
															</select>
														</div>
					                           	  	</div>
					                           	  	<div class="col-md-4 ">
					                           	  		<div class="form-group" id="label_25">
															<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_responzable2") %>"><%= lang1.getProperty("responzable2") %> <span style="color: red;">(*)</span></label>
															<select class="default-select2 form-control" id="txt_25" data-parsley-group="wizard-step-1"  placeholder="<%= lang1.getProperty("responzable2") %>" >
															</select>
														</div>
					                           	  	</div>
					                           	  	<div class="col-md-4 ">
					                           	  		<div class="form-group" id="label_26">
															<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_responzable3") %>"><%= lang1.getProperty("responzable3") %> <span style="color: red;">(*)</span></label>
															<select class="default-select2 form-control" id="txt_26" data-parsley-group="wizard-step-1"  placeholder="<%= lang1.getProperty("responzable3") %>" >
															</select>
														</div>
					                           	  	</div>
					                           	  	
					                        </div>
					                        <legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew6") %></legend>
                                            <div class="row">
					                           	  	<div class="col-md-6 ">
					                           	  		<div class="form-group">
															<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_ob_objetivo") %>"><%= lang1.getProperty("ob_objetivo") %></label>
															<textarea class="form-control"  id="txt_27"data-parsley-group="wizard-step-1" placeholder="<%= lang1.getProperty("ob_objetivo") %>" rows="5"></textarea>
															
														</div>
					                           	  	</div>
					                           	  	<div class="col-md-6 ">
					                           	  		<div class="form-group">
															<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_ob_descripcion") %>"><%= lang1.getProperty("ob_descripcion") %></label>
															<textarea class="form-control"  id="txt_28"data-parsley-group="wizard-step-1" placeholder="<%= lang1.getProperty("ob_descripcion") %>" rows="5"></textarea>
														</div>
					                           	  	</div>
					                           	  	
					                           	  	
					                        </div>
                                            <legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew4") %></legend>
                                            <div class="panel-body panel-form" id="panel_02">
                           
					                           <div class="row">
					                           	  	<div class="col-md-3 ">
					                           	  		<div class="form-group">
															<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_setup_recoleccion") %>"><%= lang1.getProperty("setup_recoleccion") %></label><br/>
															<input id="txt_29" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="true" class="BSswitch">
														</div>
					                           	  	</div>
					                           	  	<div class="col-md-3 ">
					                           	  		<div class="form-group">
															<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_setup_scripting") %>"><%= lang1.getProperty("setup_scripting") %></label><br/>
															<input id="txt_30" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
														</div>
					                           	  	</div>
					                           	  	<div class="col-md-3 ">
					                           	  		<div class="form-group">
															<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_setup_codificacion") %>"><%= lang1.getProperty("setup_codificacion") %></label><br/>
															<input id="txt_31" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" class="BSswitch">
														</div>
					                           	  	</div>
					                           	  	<div class="col-md-3 ">
					                           	  		<div class="form-group">
															<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_setup_digitacion") %>"><%= lang1.getProperty("setup_digitacion") %></label><br/>
															<input id="txt_32" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
														</div>
					                           	  	</div>
					                           	  </div>
					                           	  <div class="row">
					                           	  	<div class="col-md-3 ">
					                           	  		<div class="form-group">
															<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_setup_depuracion") %>"><%= lang1.getProperty("setup_depuracion") %></label><br/>
															<input id="txt_33" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
														</div>
					                           	  	</div>
					                           	  	<div class="col-md-3 ">
					                           	  		<div class="form-group">
															<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_setup_tabulacion") %>"><%= lang1.getProperty("setup_tabulacion") %></label><br/>
															<input id="txt_34" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
														</div>
					                           	  	</div>
					                           	  	<div class="col-md-3 ">
					                           	  		<div class="form-group">
															<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_setup_analisis") %>"><%= lang1.getProperty("setup_analisis") %></label><br/>
															<input id="txt_35" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
														</div>
					                           	  	</div>
					                           	  	<div class="col-md-3 ">
					                           	  		<div class="form-group">
															<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_setup_entrega") %>"><%= lang1.getProperty("setup_entrega") %></label><br/>
															<input id="txt_36" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
														</div>
					                           	  	</div>
					                           	  </div>
					                           	  <div class="row">
					                           	  	<div class="col-md-3 ">
					                           	  		<div class="form-group">
															<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_setup_cuestionario") %>"><%= lang1.getProperty("setup_cuestionario") %></label><br/>
															<input id="txt_37" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
														</div>
					                           	  	</div>
					                           	  	<div class="col-md-3 ">
					                           	  		<div class="form-group">
															<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_setup_otros") %>"><%= lang1.getProperty("setup_otros") %></label><br/>
															<input id="txt_38" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
														</div>
					                           	  	</div>
					                           	  	<div class="col-md-3 ">
					                           	  		<div class="form-group">
															<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_setup_viatico") %>"><%= lang1.getProperty("setup_viatico") %></label><br/>
															<input id="txt_39" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
														</div>
					                           	  	</div>
					                           	  	
					                           	  </div>
					                           
					                        </div>
										</fieldset>
									</div>
									<!-- end wizard step-1 -->
									<!-- begin wizard step-2 -->
										<!-- Wizard page 2 -->
                            			<input type="hidden" class="form-control" id="combo_44" value="<%= lang1.getProperty("modo") %>" />
                            			<input type="hidden" class="form-control" id="combo_45" value="<%= lang1.getProperty("subModo") %>" />
                            			<!-- end Wizard page 2 -->
									<div class="wizard-step-2">
										<fieldset>
										  <legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew7") %></legend>
                                           <div class="row">
                                                <!-- begin col-3 -->
                                                <div class="col-md-3">
													<div class="form-group">
														<div class="form-group" id="label_44">
															<label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_modo") %>"><%= lang1.getProperty("modo") %></label>
															<select class="default-select2 form-control" id="txt_44" data-parsley-group="wizard-step-2" style="width: 100%;" >
															</select>
														</div>
													</div>
                                                </div>
                                                <!-- end col-3 -->
                                                <!-- begin col-3 -->
                                                <div class="col-md-3">
													<div class="form-group" id="label_45">
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
														<input type="number"  class="default-select2 form-control" style="font-size: 20px; color: orange; " id="txt_59"  data-parsley-group="wizard-step-2" placeholder="<%= lang1.getProperty("duracion_total") %>" readonly="readonly" />
													</div>
				                           	  	</div>
				                           	  	
                                            </div>
                                            
				                           	
					                        
										</fieldset>
									</div>
									<!-- end wizard step-2 -->
									<!-- begin wizard step-3 -->
									<!-- Wizard config page 3 -->
                            			<input type="hidden" class="form-control" id="combo_114" value="<%= lang1.getProperty("especifique_los_datos_a_entregar") %>" />
                            			<input type="hidden" class="form-control" id="combo_115" value="<%= lang1.getProperty("formato_del_archivo_de_datos") %>" />
                            		<!-- END Wizard config page 3 -->	
									<div class="wizard-step-3">
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
                                            <legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew13") %></legend>
                                            <!-- begin row -->
                                            <div class="row">
                                                <!-- begin col-3 -->
                                                <div class="col-md-3">
                                                    <div class="form-group" id="label_113">
                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_tabulacion") %>"><%= lang1.getProperty("tabulacion") %></label><br/>
														<input id="txt_113" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
                                                    </div>
                                                </div>
                                                <!-- end col-3 -->
                                                <!-- begin col-3 -->
                                                <div class="col-md-3">
                                                    <div class="form-group" id="label_114">
                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_especifique_los_datos_a_entregar") %>"><%= lang1.getProperty("especifique_los_datos_a_entregar") %></label>
														<select class="default-select2 form-control" id="txt_114" data-parsley-group="wizard-step-3"  style="width: 100%" >
													    </select>
													</div>
                                                    
                                                </div>
                                                <!-- end col-3 -->
                                                <!-- begin col-3 -->
                                                <div class="col-md-3">
                                                     <div class="form-group" id="label_115">
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
                                            
                                            <legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew14") %></legend>
                                            <!-- begin row -->
                                            <div class="row">
                                                <!-- begin col-3 -->
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_setup_analisis") %>"><%= lang1.getProperty("setup_analisis") %></label><br/>
														<input id="ana_01" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
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
                                            
                                            <!-- begin row -->
                                            <div class="row">
                                               
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_an_dis_muestra") %>"><%= lang1.getProperty("an_dis_muestra") %></label><br/>
														<input id="txt_131" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
                                                    </div>
                                                </div>
                                                <div class="col-md-3">
                                                    
                                                </div>
                                                <div class="col-md-3">
                                                    
                                                </div>
                                                <div class="col-md-3">
                                                    
                                                </div>
                                             </div>
                                            <!-- end row -->
                                            <legend class="pull-left width-full"><%= lang1.getProperty("itemgroupNew15") %></legend>
                                            <!-- begin row -->
                                            <div class="row">
                                                <!-- begin col-3 -->
                                                <div class="col-md-3">
                                                    <div class="form-group" id="label_entre1">
                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_setup_entrega") %>"><%= lang1.getProperty("setup_entrega") %></label><br/>
														<input id="entre_01" type="checkbox" data-off-text="<%= lang1.getProperty("bSswitchOFF") %>" data-on-text="<%= lang1.getProperty("bSswitchON") %>" data-switch-value="false" class="BSswitch">
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
									</div>
									<!-- end wizard step-3 -->
									<!-- begin wizard step-4 -->
										<!-- Wizard config page 4 -->
                            			<input type="hidden" class="form-control" id="combo_169" value="<%= lang1.getProperty("select2IfSIorNOT") %>" />
                            			<input type="hidden" class="form-control" id="combo_176" value="<%= lang1.getProperty("bc_05") %>" />
                            			<input type="hidden" class="form-control" id="combo_182" value="<%= lang1.getProperty("bc_09") %>" />
                            			<input type="hidden" class="form-control" id="combo_187" value="<%= lang1.getProperty("bc_13") %>" />
                            			<!-- Wizard config page 4 -->
									<div class="wizard-step-5">
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
                                                    <div class="form-group" id="label_169">
                                                        <div class="controls" >
                                                            <select class="default-select2 form-control" id="txt_169" data-parsley-group="wizard-step-4"  style=" width: 100%;" >
													    	</select>
                                                        </div>
                                                        <div class="form-group" style="margin-top: 10px;">
                                                        	<textarea class="form-control"  id="txt_170" data-parsley-group="wizard-step-4" placeholder="<%= lang1.getProperty("bc_01") %>" rows="5"></textarea>
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
                                                    <div class="form-group" id="label_171">
                                                        <div class="controls" >
                                                            <select class="default-select2 form-control" id="txt_171" data-parsley-group="wizard-step-4"  style="width: 100%;" >
													    	</select>
                                                        </div>
                                                        <div class="form-group" style="margin-top: 10px;">
                                                        	<textarea class="form-control"  id="txt_172"data-parsley-group="wizard-step-4" placeholder="<%= lang1.getProperty("bc_02") %>" rows="5"></textarea>
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
                                                    <div class="form-group" id="label_173">
                                                        <div class="controls" >
                                                            <select class="default-select2 form-control" id="txt_173" data-parsley-group="wizard-step-4"  style="width: 100%;" >
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
                                                    <div class="form-group"  id="label_174">
                                                        <div class="controls">
                                                            <select class="default-select2 form-control" id="txt_174" data-parsley-group="wizard-step-4"  style="width: 100%;" >
													    	</select>
                                                        </div>
                                                    </div>
                                                    <div class="form-group" style="margin-top: 10px;">
                                                       	<textarea class="form-control"  id="txt_175"data-parsley-group="wizard-step-4" placeholder="<%= lang1.getProperty("bc_04") %>" rows="5"></textarea>
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
                                                    <div class="form-group" id="label_176">
                                                        <div class="controls" >
                                                            <select class="default-select2 form-control" id="txt_176" data-parsley-group="wizard-step-4"  style="width: 100%;" >
													    	</select>
                                                        </div>
                                                        
                                                    </div>
                                                    <div class="form-group">
                                                    	<div class="form-group" style="margin-top: 10px;">
                                                        	<textarea class="form-control"  id="txt_177"data-parsley-group="wizard-step-4" placeholder="<%= lang1.getProperty("bc_05") %>" rows="5"></textarea>
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
                                                        	<textarea class="form-control"  id="txt_178"data-parsley-group="wizard-step-4" placeholder="<%= lang1.getProperty("bc_06") %>" rows="5"></textarea>
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
                                                    <div class="form-group" id="label_179">
                                                        <div class="controls" >
                                                            <select class="default-select2 form-control" id="txt_179" data-parsley-group="wizard-step-4"  style="width: 100%;" >
													    	</select>
                                                        </div>
                                                        
                                                    </div>
                                                    <div class="form-group" >
	                                                    <div class="form-group" style="margin-top: 10px;">
	                                                       	<textarea class="form-control"  id="txt_180"data-parsley-group="wizard-step-4" placeholder="<%= lang1.getProperty("bc_07") %>" rows="5"></textarea>
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
                                                    <div class="form-group" id="label_181">
                                                        <div class="controls" >
                                                            <select class="default-select2 form-control" id="txt_181" data-parsley-group="wizard-step-4"  style="width: 100%;" >
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
                                                    <div class="form-group" id="label_182">
                                                        <div class="controls" >
                                                            <select class="default-select2 form-control" id="txt_182" data-parsley-group="wizard-step-4" style="width: 100%;" >
													    	</select>
                                                        </div>
                                                        
                                                    </div>
                                                    <div id="form-group" >
                                                    	<div class="controls" style="margin-top: 10px;">
                                                        	<textarea class="form-control"  id="txt_183"data-parsley-group="wizard-step-4" placeholder="<%= lang1.getProperty("bc_09") %>" rows="5"></textarea>
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
                                                        <div class="controls" style="margin-top: 10px;">
                                                        	<textarea class="form-control"  id="txt_184"data-parsley-group="wizard-step-4" placeholder="<%= lang1.getProperty("bc_10") %>" rows="5"></textarea>
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
                                                        <div class="controls" style="margin-top: 10px;">
                                                        	<textarea class="form-control"  id="txt_185" data-parsley-group="wizard-step-4" placeholder="<%= lang1.getProperty("bc_11") %>" rows="5"></textarea>
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
                                                    <div class="form-group" id="label_186">
                                                        <div class="controls" style="margin-top: 10px;" >
                                                        	<select class="default-select2 form-control" id="txt_186" data-parsley-group="wizard-step-4"  style="width: 100%;" >
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
                                                        <div class="controls" style="margin-top: 10px;" id="label_187">
                                                        	<select class="default-select2 form-control" id="txt_187" data-parsley-group="wizard-step-4"  style="width: 100%;" >
													    	</select>
                                                    	</div>
                                                    	
                                                    </div>
                                                    <div class="form-group">
                                                    	<div class="controls" style="margin-top: 10px;">
                                                    		<textarea class="form-control"  id="txt_188"data-parsley-group="wizard-step-4" placeholder="<%= lang1.getProperty("bc_13") %>" rows="5"></textarea>
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
                                                        <div class="controls" style="margin-top: 10px;">
                                                    		<textarea class="form-control"  id="txt_189" data-parsley-group="wizard-step-4" placeholder="<%= lang1.getProperty("bc_14") %>" rows="5"></textarea>
                                                    	</div>
                                                    </div>
                                                </div>
                                                <!-- end col-8 -->
                                            </div>
                                            <!-- end row -->
                                            
                                        </fieldset>
									</div>
									<!-- end wizard step-4 -->
									<!-- begin wizard step-5 -->
									<div class="wizard-step-5">
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
                                            <!-- begin row -->
                                            <div class="row">
                                                <!-- begin col-4 -->
                                                <div class="col-md-4">
                                                    <div class="form-group">
                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_modo") %>"><%= lang1.getProperty("modo") %></label>
                                                        <div class="controls">
                                                            <input type="text" id="txt_196" style="font-size: 20px; color: orange; "  class="form-control" data-parsley-group="wizard-step-5" placeholder="<%= lang1.getProperty("modo") %>" readonly="readonly">
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- end col-4 -->
                                                <!-- begin col-4 -->
                                                <div class="col-md-4">
                                                    <div class="form-group">
                                                        <label  data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_subModo") %>"><%= lang1.getProperty("subModo") %></label>
                                                        <div class="controls">
                                                            <input type="text" id="txt_197" style="font-size: 20px; color: orange; "  class="form-control" data-parsley-group="wizard-step-5" placeholder="<%= lang1.getProperty("subModo") %>" readonly="readonly">
                                                        
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- end col-4 -->
                                                <!-- begin col-4 -->
                                                <div class="col-md-4">
                                                	<label>&nbsp;</label>
                                                        <div class="controls">
                                                    		<a href="javascript: calcularCotiza();" class="btn btn-primary btn-lg m-r-5"><%= lang1.getProperty("btn_calcular") %></a>
                                                		</div>
                                                </div>
                                                <!-- end col-6 -->
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
                                        	<!-- begin row -->
                                            <div class="row" style="background-color: #fcf8e3;">
                                                <!-- begin col-4 -->
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_hora_area") %>"><%= lang1.getProperty("hora_area") %></label>
                                                        <div class="controls">
                                                            <select class="default-select2 form-control" id="txt_198" data-parsley-group="wizard-step-4"  style="width: 100%;" >
													    	</select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- end col-4 -->
                                                <!-- begin col-4 -->
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label  data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_hora_cargo") %>"><%= lang1.getProperty("hora_cargo") %></label>
                                                        <div class="controls">
                                                            <select class="default-select2 form-control" id="txt_199" data-parsley-group="wizard-step-4"  style="width: 100%;" >
													    	</select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- end col-4 -->
                                                <!-- begin col-4 -->
                                                <div class="col-md-2">
                                                    <div class="form-group">
                                                        <label  data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_hora_hora") %>"><%= lang1.getProperty("hora_hora") %></label>
                                                        <div class="controls">
                                                            <input type="number" id="txt_200"  class="form-control" data-parsley-group="wizard-step-5" placeholder="<%= lang1.getProperty("hora_hora") %>" >
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- end col-6 -->
                                                <!-- begin col-4 -->
                                                <div class="col-md-2">
                                                    <div class="form-group">
                                                        <label  data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_hora_total") %>"><%= lang1.getProperty("hora_total") %></label>
                                                        <div class="controls">
                                                            <input type="number" id="txt_201"  class="form-control" data-parsley-group="wizard-step-5" placeholder="<%= lang1.getProperty("hora_total") %>" readonly="readonly" />
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- end col-6 -->
                                                <!-- begin col-4 -->
                                                <div class="col-md-2">
                                                    <div class="form-group">
                                                        <label > </label><br/>
                                                        <div class="controls">
                                                        	
                                                            <input type="button" id="btn_202"  class="btn btn-success m-r-5 m-b-5" data-toggle="tooltip" data-placement="top"  title="<%= lang1.getProperty("tooltip_hora_btn_add") %>" value="<%= lang1.getProperty("hora_btn_add") %>" / >
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- end col-6 -->
                                            </div>
                                            <!-- end row -->
                                            <br/>
                                            <table id="data-table1" class="table table-striped table-bordered">
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
                                        	<!-- begin row -->
                                            <div class="row" style="background-color: #d9edf7;">
                                                <!-- begin col-4 -->
                                                <div class="col-md-4">
                                                    <div class="form-group">
                                                        <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_co_categoria") %>"><%= lang1.getProperty("co_categoria") %></label>
                                                        <div class="controls">
                                                            <select class="default-select2 form-control" id="txt_203" data-parsley-group="wizard-step-4"  style="width: 100%;" >
													    	</select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- end col-4 -->
                                                <!-- begin col-4 -->
                                                <div class="col-md-4">
                                                    <div class="form-group">
                                                        <label  data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_co_subcat") %>"><%= lang1.getProperty("co_subcat") %></label>
                                                        <div class="controls">
                                                            <select class="default-select2 form-control" id="txt_204" data-parsley-group="wizard-step-4"  style="width: 100%;" >
													    	</select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- end col-4 -->
                                                <!-- begin col-4 -->
                                                <div class="col-md-4">
                                                    <div class="form-group">
                                                        <label  data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_co_item") %>"><%= lang1.getProperty("co_item") %></label>
                                                        <div class="controls">
                                                            <select class="default-select2 form-control" id="txt_205" data-parsley-group="wizard-step-4"  style="width: 100%;" >
													    	</select>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- end col-4 -->
                                                
                                            </div>
                                            <!-- end row -->
                                            <!-- begin row -->
                                            <div class="row" style="background-color: #d9edf7;">
                                                <!-- begin col-4 -->
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label id="cacost_01">Prueba</label>
                                                        <div class="controls">
                                                            <input type="number" id="txt_206"  class="form-control" data-parsley-group="wizard-step-5" placeholder="<%= lang1.getProperty("co_cant_uni") %>"  />
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- end col-4 -->
                                                <!-- begin col-4 -->
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label  id="cacost_02">Prueba</label>
                                                        <div class="controls">
                                                            <input type="number" id="txt_200"  class="form-control" data-parsley-group="wizard-step-5" placeholder="<%= lang1.getProperty("co_cant_uni") %>" >
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- end col-4 -->
                                                <!-- begin col-4 -->
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label  id="cacost_03"><%= lang1.getProperty("co_total") %></label>
                                                        <div class="controls">
                                                            <input type="number" id="txt_200"  class="form-control" data-parsley-group="wizard-step-5" placeholder="<%= lang1.getProperty("co_total") %>" >
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- end col-6 -->
                                                <!-- begin col-4 -->
                                                <div class="col-md-3">
                                                    <div class="form-group">
                                                        <label > </label><br/>
                                                        <div class="controls">
                                                        	
                                                            <input type="button" id="btn_202"  class="btn btn-success m-r-5 m-b-5" data-toggle="tooltip" data-placement="top"  title="<%= lang1.getProperty("tooltip_co_btn_add") %>" value="<%= lang1.getProperty("co_btn_add") %>" / >
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- end col-6 -->
                                                
                                            </div>
                                            <!-- end row -->
                                            <br/>
                                            <table id="data-table2" class="table table-striped table-bordered">
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
									</div>
									<!-- end wizard step-6 -->
									<!-- begin wizard step-7 -->
									<div>
									    <div class="jumbotron m-b-0 text-center">
                                            <h1><%= lang1.getProperty("titulo_final") %></h1>
                                            <p><%= lang1.getProperty("desc_final") %></p>
                                            <p><a href="JavaScript: verCOtizacion();" class="btn btn-success btn-lg" role="button"><%= lang1.getProperty("btn_final") %></a></p>
                                        </div>
									</div>
									<!-- end wizard step-7 -->
								</div>
							</form>
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            </div>
            <!-- end row -->
           
            
		
        
		
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
    <script src="<c:url value="/resources/manager/plugins/select2/dist/js/select2.full.min.js" />"></script>
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
	<script src="<c:url value="/resources/manager/plugins/parsley/dist/parsley.js" />"></script>
	<script src="<c:url value="/resources/manager/plugins/bootstrap-wizard/js/bwizard.js" />"></script>
	
	<script src="<c:url value="/resources/app/tools/tools.js" />"></script>
	<script src="<c:url value="/resources/app/tools/tools-combo-box.js" />"></script>
	<script src="<c:url value="/resources/app/cotizacion/nuevo-estudio.js" />"></script>
	<script src="<c:url value="/resources/manager/js/apps.js" />"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->
	
	<script>
		$(document).ready(function() {
			App.init();
			Estudio.init();
		});
		
	</script>

</body>
</html>
