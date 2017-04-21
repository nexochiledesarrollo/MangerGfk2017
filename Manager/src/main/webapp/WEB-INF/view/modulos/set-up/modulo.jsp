<%@page import="cl.nexo.manager.obj.dashboard.ObjWidjetAccessAdmin"%>
<%@page import="cl.nexo.manager.obj.login.ObjPerfilLogin"%>
<%@page import="cl.nexo.manager.obj.login.ObjLoginUser"%>
<%@page import="cl.nexo.manager.obj.menu.ObjModuloMenuManager"%>
<%@page import="cl.nexo.manager.obj.menu.ObjUnidadMenuManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cl.nexo.manager.obj.menu.ObjMenuManager"%>
<%@page import="java.net.*"%>
<%@page import="java.io.*"%>
<%@page import="java.util.Properties"%>
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
 String title_plataform = (String) request.getAttribute("title_plataform");
 
 //lang option
 String langOption = (String) request.getAttribute("lang");
 URL url = application.getResource("/WEB-INF/lang/"+langOption+"/set-up.properties");
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
	<title><%= title_plataform %></title>
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
	<input type="hidden" class="form-control" id="lang_01" value="<%= langOption %>" />
	<input type="hidden" class="form-control" id="per_01" value="<%= permiso_access %>" />
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
				<li><a href="#"><%= lang1.getProperty("navUnidad") %></a></li>
				<li class="active"><%= lang1.getProperty("navModulo") %></li>
			</ol>
			<!-- end breadcrumb -->
			<!-- begin page-header -->
			<h1 class="page-header"><%= lang1.getProperty("navUnidad") %> <small><%= lang1.getProperty("nuevoSmallHeader") %></small></h1>
			<!-- end page-header -->
			
			<!-- begin panel -->
                    <div class="panel panel-default panel-with-tabs" data-sortable-id="ui-unlimited-tabs-2">
                        <div class="panel-heading p-0">
                            <div class="panel-heading-btn m-r-10 m-t-10">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-inverse" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                            </div>
                            <!-- begin nav-tabs -->
                            <div class="tab-overflow">
                                <ul class="nav nav-tabs">
                                    <li class="prev-button"><a href="javascript:;" data-click="prev-tab" class="text-inverse"><i class="fa fa-arrow-left"></i></a></li>
                                    <li class="active"><a href="#nav-tab2-1" data-toggle="tab"><%= lang1.getProperty("tab_1") %></a></li>
                                    <li class=""><a href="#nav-tab2-2" data-toggle="tab">Nav Tab 2</a></li>
                                    
                                    <li class="next-button"><a href="javascript:;" data-click="next-tab" class="text-inverse"><i class="fa fa-arrow-right"></i></a></li>
                                </ul>
                            </div>
                        </div>
                        <input type="hidden" class="form-control" id="pace_01" value="<%= lang1.getProperty("tab1_paceSelectPadre") %>" />
                        <input type="hidden" class="form-control" id="aux_01" value="<%= langOption %>" />
                        <div class="tab-content">
                            <div class="tab-pane fade active in" id="nav-tab2-1">
                                <h3 class="m-t-10"><%= lang1.getProperty("tab1_tittle") %></h3>
                                <br/>
                                <div class="row">
                                	<div class="col-md-6 ui-sortable">
                                		<div class="form-group">
		                                    <label class="col-md-3 control-label"><%= lang1.getProperty("tab1_select1") %></label>
		                                    <div class="col-md-6">
		                                        <select  class="default-select2 form-control" id="txt_01" name="txt_01">
		                                        </select>
		                                    </div>
		                                    <div class="col-md-3">
		                                        <a href="javascript: createOption();" class="btn btn-success btn-lg m-r-5" id="btn_bcreate"><%= lang1.getProperty("tab1_btnadd") %></a>
		                                    </div>
		                                </div>
                                	</div>
                                </div>
                                <br/>
                                <div class="row">
                                	<div class="col-md-6 ui-sortable">
                                		<div class="form-group">
		                                    <label class="col-md-3 control-label"><%= lang1.getProperty("tab1_lang") %></label>
		                                    <div class="col-md-6">
		                                        <select  class="default-select2 form-control" id="txt_02">
		                                        </select>
		                                    </div>
		                                    <div class="col-md-3">
		                                    	
		                                    </div>
		                                </div>
                                	</div>
                                </div>
                                <br/><br/>
                                <div class="row">
                                	<div class="col-md-12 ui-sortable">
                                		<table id="data-table1" class="table table-striped table-bordered">
			                                <thead>
			                                    <tr>
			                                    	<th width="5%"></th>
			                                    	<th width="20%">Detalle</th>
			                                    	<th width="15%">Valor</th>
			                                        <th width="20%">Combo Depende</th>
											    	<th width="20%">Valor Depende</th>
			                                    	<th width="10%">Orden</th>
			                                    	<th width="5%"></th>
			                                    	
			      									
			                                    </tr>
			                                </thead>
			                                <tfoot>
			                                    <tr>
			                                    	<th width="5%"></th>
			                                    	<th width="20%">Detalle</th>
			                                    	<th width="15%">Valor</th>
			                                        <th width="20%">Combo Depende</th>
											    	<th width="20%">Valor Depende</th>
			                                    	<th width="10%">Orden</th>
			                                    	<th width="5%"></th>
			      									
			                                    </tr>
			                                </tfoot>
			                                <tbody>
			                                    
			                                </tbody>
			                            </table>
                                	</div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="nav-tab2-2">
                                <h3 class="m-t-10">Nav Tab 2</h3>
                                <p>
                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
                                    Integer ac dui eu felis hendrerit lobortis. Phasellus elementum, nibh eget adipiscing porttitor, 
                                    est diam sagittis orci, a ornare nisi quam elementum tortor. 
                                    Proin interdum ante porta est convallis dapibus dictum in nibh. 
                                    Aenean quis massa congue metus mollis fermentum eget et tellus. 
                                    Aenean tincidunt, mauris ut dignissim lacinia, nisi urna consectetur sapien, 
                                    nec eleifend orci eros id lectus.
                                </p>
                                <p>
                                    Aenean eget odio eu justo mollis consectetur non quis enim. 
                                    Vivamus interdum quam tortor, et sollicitudin quam pulvinar sit amet. 
                                    Donec facilisis auctor lorem, quis mollis metus dapibus nec. Donec interdum tellus vel mauris vehicula, 
                                    at ultrices ex gravida. Maecenas at elit tincidunt, vulputate augue vitae, vulputate neque.
                                    Aenean vel quam ligula. Etiam faucibus aliquam odio eget condimentum. 
                                    Cras lobortis, orci nec eleifend ultrices, orci elit pellentesque ex, eu sodales felis urna nec erat. 
                                    Fusce lacus est, congue quis nisi quis, sodales volutpat lorem.
                                </p>
                            </div>
                            
                        </div>
                    </div>
			        <!-- end panel -->
			
			
			
			
			
			
			
		</div>
		<!-- end #content -->
		<!-- Modal custom -->
        <div class="modal modal-message fade" id="modal-edit">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header" id="header-updateop">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><strong>x</strong></button>
						<h1 class="modal-title">
						<span class="fa-stack fa-2x text-warning">
							<i class="fa fa-circle-o fa-stack-2x"></i>
							<i class="fa fa-cogs fa-stack-1x"></i>
						</span>
						<%= lang1.getProperty("tab1_mtitle") %></h1>
					</div>
					
					<div class="modal-body">
					<div class="alert alert-danger fade in m-b-15" id="error-option">
						<strong>Error!</strong>
						<span class="close" data-dismiss="alert">X</span>
						<div id="etext-newlogin"></div>
					</div>
						<input type="hidden" class="form-control"  name="txts_00" id="txts_00">
						<h4 class="m-t-20"><strong><%= lang1.getProperty("tab1_mstitle") %></strong></h4>
						<div class="row">
						    <!-- begin col-12 -->
						    <div class="col-md-6">
								<div class="form-group" style="">
		                            <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_tab1_mdetail") %>"><%= lang1.getProperty("tab1_mdetail") %> <span style="color: red;">(*)</span></label>
									<input type="text" class="form-control" placeholder="<%= lang1.getProperty("tab1_mdetail") %>" name="txts_01" id="txts_01" />
		                        </div>
		                    </div>
		                    <div class="col-md-6">
								<div class="form-group">
		                            <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_tab1_mvalue") %>"><%= lang1.getProperty("tab1_mvalue") %> <span style="color: red;">(*)</span></label>
		                            <input type="text" class="form-control" placeholder="<%= lang1.getProperty("tab1_mvalue") %>" name="txts_02" id="txts_02" >
		                        </div>
		                    </div>
		                </div>
		                <p></p>
		                <div class="row">
						    <!-- begin col-12 -->
						    <div class="col-md-6">
								<div class="form-group">
		                            <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_tab1_mdepende") %>"><%= lang1.getProperty("tab1_mdepende") %></label>
		                            <select class="default-select2 form-control" style="width: 100%;" id="txts_03">
									</select>
		                        </div>
		                    </div>
		                    <div class="col-md-6">
								<div class="form-group">
		                            <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_tab1_mdependevalue") %>"><%= lang1.getProperty("tab1_mdependevalue") %></label>
		                            <input type="text" class="form-control" placeholder="<%= lang1.getProperty("tab1_mdependevalue") %>" name="txts_04" id="txts_04">
		                        </div>
		                    </div>
		                </div>
		                <p></p>
		                <div class="row">
						    <!-- begin col-12 -->
						    <div class="col-md-6">
								<div class="form-group">
		                            <label data-toggle="tooltip" data-placement="top" title="<%= lang1.getProperty("tooltip_tab1_morden") %>"><%= lang1.getProperty("tab1_morden") %> <span style="color: red;">(*)</span></label>
		                            <input type="number" class="form-control" placeholder="<%= lang1.getProperty("tab1_morden") %>" name="txts_05" id="txts_05" />
		                        </div>
		                    </div>
		                    <div class="col-md-6">
								
		                    </div>
		                </div>
		                <p></p>
		            </div>
					<div class="modal-footer">
						<a href="javascript:;" class="btn btn-lg btn-white" data-dismiss="modal"><%= lang1.getProperty("btn_close") %></a>
						<a href="JavaScript: updateOption();" class="btn btn-lg btn-warning" id="button-bupdate"><%= lang1.getProperty("btn_update") %></a>
						<a href="JavaScript: deleteOption();" class="btn btn-lg btn-danger" id="button-bdelete"><%= lang1.getProperty("btn_delete") %></a>
					</div>
				</div>
			</div>
		</div>
		
		<!-- begin scroll to top btn -->
		<a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
		<!-- end scroll to top btn -->
	</div>
	<!-- end page container -->
	<!-- Incluir modal generico -->
    <%@ include file="../../general-modal.jsp" %>
		
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
	<script src="<c:url value="/resources/app/set-up/modulo.js" />"></script>
	<script src="<c:url value="/resources/manager/js/apps.js" />"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->
	
	<script>
		$(document).ready(function() {
			App.init();
			SetUp.init();
		});
	</script>

</body>
</html>
