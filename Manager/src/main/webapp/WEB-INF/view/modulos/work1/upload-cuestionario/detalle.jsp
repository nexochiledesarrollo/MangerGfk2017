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
 String urlRestServiceDelivery = (String) request.getAttribute("urlRestServiceDelivery");
 ObjConfigTools tol = (ObjConfigTools) request.getAttribute("tol");
 
 //lang option
 String langOption = (String) request.getAttribute("lang");
 URL url = application.getResource("/WEB-INF/lang/"+langOption+"/activity5.properties");
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
    <link href="<c:url value="/resources/manager/plugins/gritter/css/jquery.gritter.css" />" rel="stylesheet">
	<link href="<c:url value="/resources/manager/plugins/DataTables/media/css/dataTables.color.css" />" rel="stylesheet">
	
	<!-- ================== END PAGE LEVEL STYLE ================== -->
	<!-- ================== BEGIN PAGE LEVEL CSS STYLE UPLOAD ================== -->
    <link href="<c:url value="/resources/manager/plugins/jquery-file-upload/blueimp-gallery/blueimp-gallery.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/manager/plugins/jquery-file-upload/css/jquery.fileupload.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/manager/plugins/jquery-file-upload/css/jquery.fileupload-ui.css" />" rel="stylesheet">
	<!-- ================== END PAGE LEVEL CSS STYLE UPLOAD ================== -->
	
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
                    <div class="panel panel-inverse">
                        <div class="panel-heading">
                            <div class="panel-heading-btn">
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-default" data-click="panel-expand"><i class="fa fa-expand"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-success" data-click="panel-reload"><i class="fa fa-repeat"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-warning" data-click="panel-collapse"><i class="fa fa-minus"></i></a>
                                <a href="javascript:;" class="btn btn-xs btn-icon btn-circle btn-danger" data-click="panel-remove"><i class="fa fa-times"></i></a>
                            </div>
                            <h4 class="panel-title">Detalle Cuestionario</h4>
                        </div>
                        <div class="panel-body">
                        	<form class="form-horizontal form-bordered">
                           	  <div class="row">
                           	  	<div class="form-group col-md-6">
                           	  		<h1 id="h1_1" style="margin-left: 20px;">ID: Cargando ...</h1>
                          			
                           		</div>
                           	  	<div class="form-group col-md-6">
                           	  		<p class="pull-right">
                           	  		<a href="#info_op" class="btn btn-info m-r-5" style="margin-top: 5px; text-align: center;" id="btn_showhide">
                           	  			<i class="fa fa-check-square-o fa-2x pull-left"></i>
                           	  			Mostrar/Ocultar
                           	  		</a>
                           	  		
                           	  		<a href="#" class="btn btn-success m-r-5" style="margin-top: 5px; text-align: center;" onclick="JavaScript: updateEstudio();" id="btn_activae">
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
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Industria</label>
										<div class="col-md-8">
	                                         <select class="form-control" id="txt_detalle_01" name="txt_detalle_01" style="width: 100%;"></select>
	                                    </div>
									</div>
									<div class="form-group col-md-6">
										<label class="control-label col-md-4" style="background: #f0f3f4; font-weight: bold;">Tipo Estudio </label>
										<div class="col-md-8">
	                                         <select class="form-control" id="txt_detalle_02" name="txt_detalle_02" style="width: 100%;"></select>
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
							</form>
                            <div class="modal-body">
								<blockquote class="f-s-14">
			                        <p>
									Widget de carga de  selecci&oacute;n de varios archivos , arrastrar y soltar, compatible con navegadores con soporte de HTML5. 
									</p>
			                    </blockquote>
			                    <form id="fileupload" action="<c:url value='/FileUploadUser/upload' />" method="POST" enctype="multipart/form-data">
			                    	<input type="hidden" id="txt_idope_1" name="txt_idope_1" value="<%=  id_operacion %>" />
									<input type="hidden" id="txt_idope_2" name="txt_idope_2" value="" />
			                        <!-- Redirect browsers with JavaScript disabled to the origin page -->
		        					<noscript><input type="hidden" name="redirect" value="<c:url value='/home' />"></noscript>
			                        <div class="row fileupload-buttonbar">
			                            <div class="col-md-7">
			                                <span class="btn btn-success fileinput-button">
			                                    <i class="fa fa-plus"></i>
			                                    <span>Seleccionar Archivo...</span>
			                                    <input type="file" name="files[]" multiple="">
			                                </span>
			                                <button type="submit" class="btn btn-primary start">
			                                    <i class="fa fa-upload"></i>
			                                    <span>Subir</span>
			                                </button>
			                                <button type="reset" class="btn btn-warning cancel">
			                                    <i class="fa fa-ban"></i>
			                                    <span>Cancelar</span>
			                                </button>
			                                <button type="button" class="btn btn-danger delete">
			                                    <i class="glyphicon glyphicon-trash"></i>
			                                    <span>Borrar</span>
			                                </button>
			                                <!-- The global file processing state -->
			                                <span class="fileupload-process"></span>
			                            </div>
			                            <!-- The global progress state -->
			                            <div class="col-md-5 fileupload-progress fade">
			                                <!-- The global progress bar -->
			                                <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
			                                    <div class="progress-bar progress-bar-success" style="width:0%;"></div>
			                                </div>
			                                <!-- The extended global progress state -->
			                                <div class="progress-extended">&nbsp;</div>
			                            </div>
			                        </div>
			                        <!-- The table listing the files available for upload/download -->
			                        <table role="presentation" class="table table-striped"><tbody class="files"></tbody></table>
			                    </form>
			                    <div class="note note-info">
			                        <h4>Configuraciones de Subida</h4>
			                        <ul>
			                            <li>El m&aacute;ximo permitido en peso es de  <strong>5 MB</strong> .</li>
			                            <li>Solo esta permitido subir archivo excel (<strong>xls o xlsx</strong>).</li>
			                            <li>Los archivos subidos seran <strong>guardados como respaldo</strong>.</li>
			                        </ul>
			                    </div>
							</div>
                        </div>
                    </div>
                    <!-- end panel -->
                </div>
                <!-- end col-12 -->
            </div>
            <!-- end row -->
                       
		</div>
		<!-- end #content -->
		<!-- The blueimp Gallery widget -->
        <div id="blueimp-gallery" class="blueimp-gallery blueimp-gallery-controls" data-filter=":even">
            <div class="slides"></div>
            <h3 class="title"></h3>
            <a class="prev"><</a>
            <a class="next">></a>
            <a class="close">x</a>
            <a class="play-pause"></a>
            <ol class="indicator"></ol>
        </div>
        
		<!-- begin scroll to top btn -->
		<a href="javascript:;" class="btn btn-icon btn-circle btn-success btn-scroll-to-top fade" data-click="scroll-top"><i class="fa fa-angle-up"></i></a>
		<!-- end scroll to top btn -->
	</div>
	<!-- end page container -->
	<!-- The template to display files available for upload -->
    <script id="template-upload" type="text/x-tmpl">
        {% for (var i=0, file; file=o.files[i]; i++) { %}
            <tr class="template-upload fade">
                <td class="col-md-1">
                    <span class="preview"></span>
                </td>
                <td>
                    <p class="name">{%=file.name%}</p>
                    <strong class="error text-danger"></strong>
                </td>
                <td>
                    <p class="size">Procesando...</p>
                    <div class="progress progress-striped active"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
                </td>
                <td>
                    {% if (!i && !o.options.autoUpload) { %}
                        <button class="btn btn-primary btn-sm start" disabled>
                            <i class="fa fa-upload"></i>
                            <span>Iniciar</span>
                        </button>
                    {% } %}
                    {% if (!i) { %}
                        <button class="btn btn-white btn-sm cancel">
                            <i class="fa fa-ban"></i>
                            <span>Cancelar</span>
                        </button>
                    {% } %}
                </td>
            </tr>
        {% } %}
    </script>
    <!-- The template to display files available for download -->
    <script id="template-download" type="text/x-tmpl">
        {% for (var i=0, file; file=o.files[i]; i++) { %}
            <tr class="template-download fade">
                <td>
                    <span class="preview">
                        {% if (file.tipoarch_file == 1) { %}
                            <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>
                        {% }else{ %}
							<a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}"><img src="{%=file.thumbnailUrl%}"></a>
						{% } %}
                    </span>
                </td>
                <td>
                    <p class="name">
                        {% if (file.url) { %}
                            <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.tipoarch_file==1?'data-gallery':''%}>{%=file.name%}</a>
                        {% } else { %}
                            <span>{%=file.name%}</span>
                        {% } %}
                    </p>
                    {% if (file.error) { %}
                        <div><span class="label label-danger">Error</span> {%=file.error%}</div>
                    {% } %}
                </td>
                <td>
                    <span class="size">{%=o.formatFileSize(file.size)%}</span>
                </td>
				<td>
					<span class="size">{%=file.dateCreated%}</span>
				</td>
				<td>
					<span class="size">{%=file.str_user%}</span>
				</td>
				<td>
					<span class="size">Version: {%=file.vm_file%}</span>
				</td>
                <td>
					
					{% if (file.carga_file == 0 ) { %}
						{% if (file.deleteUrl) { %}
                        	<button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                           	 <i class="glyphicon glyphicon-trash"></i>
                            	<span>Borrar</span>
                        	</button>
                        	<input type="checkbox" name="delete" value="1" class="toggle">
                    	{% } else { %}
                        	<button class="btn btn-warning cancel">
                            	<i class="glyphicon glyphicon-ban-circle"></i>
                            	<span>Cancel</span>
                        	</button>
                    	{% } %}
					{% }else if(file.carga_file == 1){ %}
							<a class="btn btn-success" href="#">
								<i class="fa fa-check"></i>
	                        	<span>Cargado!</span>
							</a>
					{% } %}
                </td>
            </tr>
        {% } %}
    </script>
    
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
	<!-- ================== END PAGE LEVEL JS UPLOAD ================== -->
	<script src="<c:url value="/resources/app/tools/tools.js" />"></script>
	<script src="<c:url value="/resources/app/tools/tools-combo-box.js" />"></script>
	<script src="<c:url value="/resources/app/upload-cuestionario/detalle.js" />"></script>
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
