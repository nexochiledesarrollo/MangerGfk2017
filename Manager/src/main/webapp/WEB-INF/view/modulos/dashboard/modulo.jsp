<%@page import="cl.nexo.manager.obj.dashboard.ObjWidjetAccessAdmin"%>
<%@page import="cl.nexo.manager.obj.login.ObjPerfilLogin"%>
<%@page import="cl.nexo.manager.obj.login.ObjLoginUser"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<%
 ObjLoginUser login = (ObjLoginUser) request.getAttribute("login");
 ObjPerfilLogin perfil = (ObjPerfilLogin) request.getAttribute("perfil");
 ObjWidjetAccessAdmin widAdmin = (ObjWidjetAccessAdmin) request.getAttribute("WidjetAccessAdmin");

%>
    
 <!-- begin breadcrumb -->
<ol class="breadcrumb pull-right">
	<li><a href="javascript:;">Home</a></li>
	<li class="active">Dashboard</li>
</ol>
<!-- end breadcrumb -->
<!-- begin page-header -->
<h1 class="page-header">Dashboard <small>header small text goes here...</small></h1>
<!-- end page-header -->
<%if(perfil.getEs_admin() == 1){ %>
<!-- begin row -->
<div class="row">
	<!-- begin col-3 -->
	<div class="col-md-3 col-sm-6">
		<div class="widget widget-stats bg-green">
			<div class="stats-icon"><i class="fa fa-desktop"></i></div>
			<div class="stats-info">
				<h4>PROYECTOS</h4>
				<p><%= widAdmin.getProyectos() %></p>	
			</div>
			<div class="stats-link">
				<a href="javascript:;">Ver Detalle <i class="fa fa-arrow-circle-o-right"></i></a>
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
				<a href="javascript:;">Ver Detalle <i class="fa fa-arrow-circle-o-right"></i></a>
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
	<!-- begin col-3 -->
	<div class="col-md-3 col-sm-6">
		<div class="widget widget-stats bg-blue">
			<div class="stats-icon"><i class="fa fa-chain-broken"></i></div>
			<div class="stats-info">
				<h4>CONFIGURACIONES</h4>
				<p>20.44%</p>	
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
		
	</div>
	<!-- end col-8 -->
	<!-- begin col-4 -->
	<div class="col-md-4">
		
	</div>
	<!-- end col-4 -->
</div>
<!-- end row -->