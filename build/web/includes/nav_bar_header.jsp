<%-- 
    Document   : nav_bar_header
    Created on : May 30, 2018, 3:08:09 AM
    Author     : ProgrammerPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<header id="header">
  <!--logo start-->
  <div class="brand">
    <a href="index" class="logo"><span><% out.print(String.valueOf(sesion.getAttribute("nombreApp")));%></span></a>
  </div>
  <!--logo end-->
  <div class="toggle-navigation toggle-left">
    <div class="pull-left">
      <button type="button" class="btn btn-default" id="toggle-left" data-toggle="tooltip" data-placement="right" title="Toggle Navigation">
        <i class="fa fa-bars"></i>
      </button>
    </div>
    <div class="navbar-brand">
      <% out.print(String.valueOf(sesion.getAttribute("nombreEmpresa")));%> - <% out.print(active);%>
    </div>
  </div>
  <div class="user-nav">
    <ul>
      <li class="profile-photo">
        <img src="assets/images/usuarios/<% out.print(String.valueOf(sesion.getAttribute("foto")));%>" alt="" height="35px" width="35px" class="img-circle">
      </li>
      <li class="dropdown settings">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
         <% out.print(String.valueOf(sesion.getAttribute("nombre")));%> <i class="fa fa-angle-down"></i>
        </a>
        <ul class="dropdown-menu animated fadeInDown">

          <li>
            <a href="SERVCerrarSesion"><i class="fa fa-power-off"></i> CERRAR SESSION</a>
          </li>
        </ul>
      </li>

      </ul>
    </div>
  </header>
