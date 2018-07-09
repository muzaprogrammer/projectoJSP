<%-- 
    Document   : menu
    Created on : May 30, 2018, 3:18:03 AM
    Author     : ProgrammerPC
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<aside class="sidebar">
  <div id="leftside-navigation" class="nano">
    <ul class="nano-content">
        <li <% if(active == "Index"){ out.print("class='active'"); } %> ><a href="index.jsp"><i class=" icon-projector-screen-line"></i><span>Dashboard</span></a></li>
<%

    if((Integer) sesion.getAttribute("idRol") == 1){
       %>
        <li <% if(active == "Articulos"){ out.print("class='active'"); } %> ><a href="articulos.jsp"><i class="fa fa-barcode"></i><span>Articulos</span></a></li>
        <li <% if(active == "Ordenes" || active == "Nueva Orden"){ out.print("class='active'"); } %> ><a href="ordenes.jsp"><i class="fa fa-list-ol"></i><span>Ordenes</span></a></li>
       <%
    }else if((Integer) sesion.getAttribute("idRol") == 2){
       %>
        <li <% if(active == "Ordenes" || active == "Nueva Orden"){ out.print("class='active'"); } %> ><a href="ordenes.jsp"><i class="fa fa-list-ol"></i><span>Ordenes</span></a></li>
       <%
    }

%>
        <li <% if(active == ""){ out.print("class='active'"); } %> ><a href="SERVCerrarSesion"><i class="fa fa-sign-out"></i><span>Cerrar Sesion</span></a></li>
    </ul>
  </div>
</aside>


      



 