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
 
 ObjWidjetAccessAdmin widAdmin = (ObjWidjetAccessAdmin) request.getAttribute("WidjetAccessAdmin");
 int aux_refresco = Integer.parseInt((String) request.getAttribute("aux_refresco"));
 
 
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
	<title>Gestor de Estudios - GfK Adimark</title>
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
	<link href="<c:url value="/resources/manager/plugins/DataTables/media/css/dataTables.bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/DataTables/extensions/Buttons/css/buttons.bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/DataTables/extensions/Responsive/css/responsive.bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/DataTables/extensions/AutoFill/css/autoFill.bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/DataTables/extensions/ColReorder/css/colReorder.bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/DataTables/extensions/KeyTable/css/keyTable.bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/DataTables/extensions/RowReorder/css/rowReorder.bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/DataTables/extensions/Select/css/select.bootstrap.min.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/DataTables/extensions/ColVis/css/dataTables.colVis.min.css" />" rel="stylesheet">
	<!-- alertas rigth -->
    <link href="<c:url value="/resources/manager/plugins/gritter/css/jquery.gritter.css" />" rel="stylesheet">
	<!-- ================== END PAGE LEVEL STYLE ================== -->
	
	<!-- ================== BEGIN BASE JS ================== -->
	<script src="<c:url value="/resources/manager/plugins/pace/pace.min.js" />"></script>
	<!-- ================== END BASE JS ================== -->
	
</head>
<body>
	<input type="hidden" name="hd_access_unidad" id="hd_access_unidad" value="<%= unidad_access %>" />
	<input type="hidden" name="hd_access_modulo" id="hd_access_modulo" value="<%= unidad_access %>" />
	<input type="hidden" id="aux_01" name="aux_01" value="<%= langOption %>" />
	<input type="hidden" id="aux_02" name="aux_02" value="<%= aux_refresco %>" />
	<input type="hidden" id="aux_03" name="aux_03" value="<%= login.getId_user() %>" />
	
	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in"><span class="spinner"></span></div>
	<!-- end #page-loader -->
	
	<!-- begin #page-container 
	<div id="page-container" class="fade page-sidebar-fixed page-header-fixed">-->
	<div id="page-container" class="page-container fade page-without-sidebar page-header-fixed page-with-top-menu">
		<!-- begin #header -->
		<%@ include file="header_top_menu.jsp" %>
		<!-- end #header -->
		
		<!-- begin #sidebar -->
		<%@ include file="top_sidebar.jsp" %>
		<!-- END Navegacion 
		<div class="sidebar-bg"></div>-->
		<!-- end #sidebar -->
		
		<!-- begin #content -->
		<div id="content" class="content">
			 <!-- begin breadcrumb -->
			<ol class="breadcrumb pull-right">
				<li><a href="javascript:;">Home</a></li>
				<li class="active">Dashboard</li>
			</ol>
			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<h1 class="page-header">Dashboard <small>Panel de Control de Gestión de Proyecto</small></h1>
			<!-- end page-header -->
			<%if(perfil.getEs_admin() == 1){ %>
			<!-- begin row -->
			<div class="row">
				<!-- begin col-3 -->
				<div class="col-md-3 col-sm-6">
					<div class="widget widget-stats bg-green">
						<div class="stats-icon"><i class="fa fa-desktop"></i></div>
						<div class="stats-info">
							<h4>Estudios</h4>
							<p><%= widAdmin.getProyectos() %></p>	
						</div>
						<div class="stats-link">
							<a href="Manager/proyectoService/manProy" target="_self">Ver Detalle <i class="fa fa-arrow-circle-o-right"></i></a>
						</div>
					</div>
				</div>
				<!-- end col-3 -->
				<!-- begin col-3 -->
				<div class="col-md-3 col-sm-6">
					<div class="widget widget-stats bg-blue">
						<div class="stats-icon"><i class="fa fa-chain-broken"></i></div>
						<div class="stats-info">
							<h4>Cotizaciones</h4>
							<p><%= widAdmin.getCotiza() %></p>	
						</div>
						<div class="stats-link">
							<a href="/Manager/cotizacionService/Mantenedor" target="_self">Ver Detalle <i class="fa fa-arrow-circle-o-right"></i></a>
						</div>
					</div>
				</div>
				<!-- end col-3 -->
				<!-- begin col-3 -->
				<div class="col-md-3 col-sm-6">
					<div class="widget widget-stats bg-purple">
						<div class="stats-icon"><i class="fa fa-users"></i></div>
						<div class="stats-info">
							<h4>USUARIOS</h4>
							<p><%= widAdmin.getUsuarios() %></p>	
						</div>
						<div class="stats-link">
							<a href="/Manager/loginService/manUser" >Ver Detalle <i class="fa fa-arrow-circle-o-right"></i></a>
						</div>
					</div>
				</div>
				<!-- end col-3 -->
				<!-- begin col-3 -->
				<div class="col-md-3 col-sm-6">
					<div class="widget widget-stats bg-red">
						<div class="stats-icon"><i class="fa fa-clock-o"></i></div>
						<div class="stats-info">
							<h4>TAREAS</h4>
							<p><%= widAdmin.getTareas()%></p>	
						</div>
						<div class="stats-link">
							<a href="javascript:;">Ver Detalle <i class="fa fa-arrow-circle-o-right"></i></a>
						</div>
					</div>
				</div>
				<!-- end col-3 -->
				
			</div>
			<!-- end row -->
			<%} %>
			<!-- begin row -->
			<div class="row">
				<!-- begin col-8 -->
				<div class="col-md-8">
					<div class="panel panel-inverse" data-sortable-id="index-1">
						<div class="panel-heading">
							<div class="panel-heading-btn">
								<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
								<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
								<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
								<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
							</div>
							<h4 class="panel-title">Estudios Asignados</h4>
						</div>
						<div class="panel-body">
							<div id="" class="height-lg" data-scrollbar="true">
								<table id="data-table3" class="table table-striped table-bordered">
	                                <thead>
	                                    <tr>
	                                        <th width="20" nowrap=""></th>
	                                        <th width="100" nowrap="">Codigo</th>
	                                        <th width="50" nowrap="">Etapa</th>
	                                        <th width="200" nowrap="">Estudio</th>
	                                        <th width="100" nowrap="">Flujo</th>
	                                        <th width="100" nowrap="">Estado</th>
	                                    </tr>
	                                </thead>
	                                <tfoot>
	                                	<tr>
	                                        <th width="20" nowrap=""></th>
	                                        <th width="100" nowrap="">Codigo</th>
	                                        <th width="50" nowrap="">Etapa</th>
	                                        <th width="200" nowrap="">Estudio</th>
	                                        <th width="100" nowrap="">Flujo</th>
	                                        <th width="100" nowrap="">Estado</th>
	                                    </tr>
	                                </tfoot>
	                                <tbody>
	                                    
	                                    
	                                </tbody>
	                            </table>
							</div>
						</div>
					</div>
					
					
					<div class="panel panel-inverse" data-sortable-id="index-4">
                        <div class="panel-heading">
							<div class="panel-heading-btn">
								<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
								<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
								<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
								<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
							</div>
							<h4 class="panel-title">Nuevas Tareas</h4>
						</div>
						<div class="panel-body">
							<div id="" class="height-lg" data-scrollbar="true">
								<table id="data-table" class="table table-striped table-bordered">
	                                <thead>
	                                    <tr>
	                                        <th width="20px" nowrap=""></th>
	                                        <th width="100px" nowrap="">Tipo</th>
	                                        <th width="200px" nowrap="">Estudio</th>
	                                        <th width="200px" nowrap="">Actividad</th>
	                                        <th width="200px" nowrap="">Asunto</th>
	                                        <th width="50px" nowrap="">Inicio</th>
	                                        <th width="50px" nowrap="">Fin</th>
	                                        
	                                    </tr>
	                                </thead>
	                                <tfoot>
	                                	<tr>
	                                        <th width="20px" nowrap=""></th>
	                                        <th width="100px" nowrap="">Tipo</th>
	                                        <th width="200px" nowrap="">Estudio</th>
	                                        <th width="200px" nowrap="">Actividad</th>
	                                        <th width="200px" nowrap="">Asunto</th>
	                                        <th width="50px" nowrap="">Inicio</th>
	                                        <th width="50px" nowrap="">Fin</th>
	                                        
	                                    </tr>
	                                </tfoot>
	                                <tbody>
	                                    
	                                    
	                                    
	                                </tbody>
	                            </table>
							</div>
						</div>
                    </div>
                    <!--
					<div class="panel panel-inverse" data-sortable-id="index-5">
						<div class="panel-heading">
							<div class="panel-heading-btn">
								<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
								<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
								<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
								<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
							</div>
							<h4 class="panel-title">Nuevos Mensajes</h4>
						</div>
						<div class="panel-body">
							<div class="height-sm" data-scrollbar="true">
								<ul class="media-list media-list-with-divider">
									<li class="media media-sm">
										
										<div class="media-body">
											<a href="javascript:;"><h4 class="media-heading">No existen nuevos Mensajes.</h4></a>
											<p class="m-b-5">
												
											</p>
											<i class="text-muted"></i>
										</div>
									</li>
									 
									<li class="media media-sm">
										<a href="javascript:;" class="pull-left">
											<img src="/Manager/resources/manager/img/new-msj.jpg" alt="" class="media-object rounded-corner">
										</a>
										<div class="media-body">
											<a href="javascript:;"><h4 class="media-heading">Lorem ipsum dolor sit amet, consectetur adipiscing elit.</h4></a>
											<p class="m-b-5">
												DE: Sebastian Barraza
											</p>
											<i class="text-muted">Enviado: 04/16/2013 12.39pm</i>
										</div>
									</li>
									
								</ul>
							</div>
						</div>
						
					</div>
				-->
				</div>
				<!-- end col-8 -->
				<!-- begin col-4 -->
				<div class="col-md-4">
					
					   <div class="panel panel-inverse" data-sortable-id="index-8">
						<div class="panel-heading">
							<div class="panel-heading-btn">
								<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
								<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
								<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
								<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
							</div>
							<h4 class="panel-title">Mis Notas</h4>
						</div>
						<div class="panel-body p-0">
							<ul class="todolist" id="todolist">
								
							</ul>
						</div>
						<div class="panel-footer">
							<form>
								<div class="input-group">
									<input type="text" id="txt_nota" class="form-control bg-silver" placeholder="Ingresa Nota">
									<span class="input-group-btn">
										<button class="btn btn-primary" type="button" onclick="JavaScript: setTodoList();"><i class="fa fa-pencil"></i></button>
									</span>
								</div>
							</form>
                        </div>
					</div>
					
					
					<div class="panel panel-inverse" data-sortable-id="index-10">
						<div class="panel-heading">
							<div class="panel-heading-btn">
								<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
								<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
								<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
								<a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
							</div>
							<h4 class="panel-title">Calendario</h4>
						</div>
						<div class="panel-body">
							<div id="datepicker-inline" class="datepicker-full-width"><div></div></div>
						</div>
					</div>
				</div>
				<!-- end col-4 -->
			</div>
			<!-- end row -->
		<!-- end #content -->
		
        
		
		<!-- begin scroll to top btn -->
		<a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
		<!-- end scroll to top btn -->
	</div>
	<!-- end page container -->
	
	<!-- ================== BEGIN BASE JS ================== -->
	<script src="<c:url value="/resources/manager/plugins/jquery/jquery-1.9.1.min.js" />"></script>
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
	<!-- ================== BEGIN CUSTOM PAGE LEVEL JS ================== -->
	<script src="<c:url value="/resources/manager/js/dashboard.js" />"></script>
	<script src="<c:url value="/resources/manager/js/apps.js" />"></script>
	<!-- ================== END CUSTOM PAGE LEVEL JS ================== -->
	<script>
		$(document).ready(function() {
			App.init();
			Dashboard.init();
		});
	</script>

</body>
</html>
