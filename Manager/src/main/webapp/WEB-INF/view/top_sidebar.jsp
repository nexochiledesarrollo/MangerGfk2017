<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!-- begin #top-menu -->
		<div id="top-menu" class="top-menu">
            <!-- begin top-menu nav -->
            <ul class="nav">
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
            </ul>
        </div>

 <%
// Tipo menu 1 top  2 left
if(tol.getTipo_menu() == 2){ 
%>
<!-- END Navegacion -->
		<div class="sidebar-bg"></div>
		<!-- end #sidebar -->  
<%} %>     