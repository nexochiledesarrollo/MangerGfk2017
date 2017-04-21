<%@page import="cl.nexo.manager.obj.dashboard.ObjWidjetAccessAdmin"%>
<%@page import="cl.nexo.manager.obj.login.ObjPerfilLogin"%>
<%@page import="cl.nexo.manager.obj.login.ObjLoginUser"%>
<%@page import="cl.nexo.manager.obj.menu.ObjModuloMenuManager"%>
<%@page import="cl.nexo.manager.obj.menu.ObjUnidadMenuManager"%>
<%@page import="cl.nexo.manager.obj.asignacion.usuario.*"%>
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

 int unidad_access = Integer.parseInt((String) request.getAttribute("unidad_access"));
 int modulo_access = Integer.parseInt((String) request.getAttribute("modulo_access"));
 int permiso_access = Integer.parseInt((String) request.getAttribute("permisoModulo"));
 ArrayList<ObjAsignUser> list = (ArrayList<ObjAsignUser>) request.getAttribute("list");
 int id_operacion = Integer.parseInt((String) request.getAttribute("id_operacion"));
//lang option
 String langOption = (String) request.getAttribute("lang");
 URL url = application.getResource("/WEB-INF/lang/"+langOption+"/activity12.properties");
 InputStream in = url.openStream();
 Properties lang1 = new Properties();
 lang1.load(in);
%>

<table id="data-table9" class="table table-striped table-bordered">
    <thead>
        <tr>
        	<th width="10"></th>
        	<th width="300">Personal</th>
            <th width="200">Cargo</th>
            
            <th>nn/nn/nnnn</th>
            <th>nn/nn/nnnn</th>
            
            <th width="200">Total Horas</th>
        </tr>
    </thead>
    <tfoot>
           
    </tfoot>
    <tbody>
           
    </tbody>
</table>
  
  
                           