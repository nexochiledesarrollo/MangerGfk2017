<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!-- begin #sidebar -->
<div id="sidebar" class="sidebar">
				<!-- begin sidebar scrollbar -->
				<div data-scrollbar="true" data-height="100%">
					<!-- begin sidebar user -->
					<ul class="nav">
						<li class="nav-profile">
							<div class="image">
								<a href="javascript:;"><img src="/Manager/resources/manager/img/user-13.jpg" alt=""></a>
							</div>
							<div class="info">
								<%= login.getNombre_user() %> <%=login.getApp_user() %>
								<small><%= perfil.getNombre_perfil() %></small>
							</div>
						</li>
					</ul>
					<!-- end sidebar user -->
					<!-- begin sidebar nav -->
					<ul class="nav">
						<li class="nav-header">Navegación</li>
						<%
						if(menu != null){
						ArrayList<ObjUnidadMenuManager> unidades = menu.getUnidades();
							if(unidades != null){
								for(ObjUnidadMenuManager unidad:unidades){
									if(unidad_access == unidad.getId_unidad()){
										out.print("<li class='has-sub active'>");
									}else{
										out.print("<li class='has-sub'>");
									}
									out.print("<a href='javascript:;'>");
									if(unidad.getBox_unidad() == 1){
										out.print("<span class='badge pull-right' id='box_tarea'></span>");
									}else{
										out.print("<b class='caret pull-right'></b>");
									}
									out.print("<i class='"+unidad.getIcono_unidad()+"'></i>");
									out.print("<span>"+unidad.getNombre_unidad()+"</span>");
									out.print("</a>");
									out.print("<ul class='sub-menu'>");
										if(unidad.getModulos() != null){
											ArrayList<ObjModuloMenuManager> modulos = unidad.getModulos();
											for(ObjModuloMenuManager modulo: modulos){
												if(modulo_access == modulo.getId_modulo()){
													out.print("<li class='active'><a href='"+modulo.getAccion_modulo()+"'>"+modulo.getNombre_modulo()+"</a></li>");
												}else{
													out.print("<li><a href='"+modulo.getAccion_modulo()+"'>"+modulo.getNombre_modulo()+"</a></li>");
												}
											}
										}
									out.print("</ul>");
									out.print("</li>");
								}	
							}// end unidad null
						}//end if menu null
						%>
				        <!-- begin sidebar minify button -->
						<li><a onclick="" href="javascript:;" class="sidebar-minify-btn" data-click="sidebar-minify"><i class="fa fa-angle-double-left"></i></a></li>
				        <!-- end sidebar minify button -->
					</ul>
					<!-- end sidebar nav -->
				</div>
				<!-- end sidebar scrollbar -->
			</div>