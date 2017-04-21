<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="es" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html lang="es">
<!--<![endif]-->
<head>
	 <meta charset="utf-8">
	<title>Acceso Gestor de Proyectos - GfK Adimark</title>
	<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
	<meta content="" name="description">
	<meta content="" name="author">
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
	<!-- ================== BEGIN BASE JS ================== -->
	<script src="<c:url value="/resources/manager/plugins/pace/pace.min.js" />"></script>
	<!-- ================== END BASE JS ================== -->
	<!-- ================== SPRING ALERT CSS STYLE ================== -->
	<style>
		.error {
			padding: 15px;
			margin-bottom: 20px;
			border: 1px solid transparent;
			border-radius: 4px;
			color: #a94442;
			background-color: #f2dede;
			border-color: #ebccd1;
		}
		
		.msg {
			padding: 15px;
			margin-bottom: 20px;
			border: 1px solid transparent;
			border-radius: 4px;
			color: #31708f;
			background-color: #d9edf7;
			border-color: #bce8f1;
		}
		
		#login-box {
			width: 300px;
			padding: 20px;
			margin: 100px auto;
			background: #fff;
			-webkit-border-radius: 2px;
			-moz-border-radius: 2px;
			border: 1px solid #000;
		}
	</style>
	<!-- ================== END SPRING ALERT CSS STYLE ================== -->
</head>
<body class="pace-top" onload="document.loginForm.username.focus();">
	<!-- begin #page-loader -->
	<div id="page-loader" class="fade in"><span class="spinner"></span></div>
	<!-- end #page-loader -->
	
	<div class="login-cover">
	    <div class="login-cover-image"><img src="<c:url value="/resources/manager/img/login-bg/bg-2.jpg" />" data-id="login-cover-image" alt=""></div>
	    <div class="login-cover-bg"></div>
	</div>
	<!-- begin #page-container -->
	<div id="page-container" class="fade">
	    <!-- begin login -->
        <div class="login login-v2" data-pageload-addclass="animated fadeIn">
            <!-- begin brand -->
            <div class="login-header">
                <div class="brand">
                    <img src="<c:url value="/resources/manager/img/login-bg/logo.png" />" />
                    <small>Plataforma de Gesti&oacute;n de Estudio</small>
                </div>
                <div class="icon">
                    <i class="fa fa-sign-in"></i>
                </div>
            </div>
            <!-- end brand -->
            <div class="login-content">
           		<div class="alert alert-danger fade in m-b-15" id="error-modulo">
					<strong>Error!</strong>
					<span class="close" data-dismiss="alert">x</span>
					<div id="etext-modulo"></div>
				</div>
            		
                <form name="loginForm"  class="margin-bottom-0">
                    <div class="form-group m-b-20">
                        <input type="text" name="text_01" id="text_01" class="form-control input-lg" placeholder="Ingresa correo de usuario">
                    </div>
                    <div class="login-buttons">
                        <button type="button" class="btn btn-success btn-block btn-lg" onclick="JavaScript: solictarActive();">Solicitar</button>
                    </div>
                    <div class="m-t-20">
                        Volver a login <a href="<c:url value='/login' />">aqu&iacute;</a>.
                    </div>
                </form>
            </div>
        </div>
        <!-- end login -->
        <!-- Incluir modal generico -->
       <%@ include file="general-modal.jsp" %>
        <ul class="login-bg-list">
            <li class="active"><a href="#" data-click="change-bg"><img src="<c:url value="/resources/manager/img/login-bg/bg-2.jpg" />" alt=""></a></li>
            <li><a href="#" data-click="change-bg"><img src="<c:url value="/resources/manager/img/login-bg/bg-6.jpg" />" alt=""></a></li>
            <li><a href="#" data-click="change-bg"><img src="<c:url value="/resources/manager/img/login-bg/bg-3.jpg" />" alt=""></a></li>
            <li><a href="#" data-click="change-bg"><img src="<c:url value="/resources/manager/img/login-bg/bg-4.jpg" />" alt=""></a></li>
            <li><a href="#" data-click="change-bg"><img src="<c:url value="/resources/manager/img/login-bg/bg-5.jpg" />" alt=""></a></li>
            <li><a href="#" data-click="change-bg"><img src="<c:url value="/resources/manager/img/login-bg/bg-1.jpg" />" alt=""></a></li>
            <li><a href="#" data-click="change-bg"><img src="<c:url value="/resources/manager/img/login-bg/bg-7.jpg" />" alt=""></a></li>
            <li><a href="#" data-click="change-bg"><img src="<c:url value="/resources/manager/img/login-bg/bg-8.jpg" />" alt=""></a></li>
        </ul>
        
        
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
	<script src="<c:url value="/resources/manager/js/login-v2.js" />"></script>
	<script src="<c:url value="/resources/manager/js/apps.js" />"></script>
	<!-- ================== END PAGE LEVEL JS ================== -->
	<!-- ================== BEGIN PAGE LEVEL JS custom ================== -->
	<script src="<c:url value="/resources/app/olvido_clave/modulo.js" />"></script>
	<!-- ================== END PAGE LEVEL JS custom ================== -->
	<script>
		$(document).ready(function() {
			modulo.init();
			App.init();
			//
		});
	</script>

</body>
</html>