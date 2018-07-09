
<%-- 
    Document   : ordenes
    Created on : Jun 3, 2018, 6:23:47 PM
    Author     : ProgrammerPC
--%>

<%@page import="modelado.DAOOrdenes"%>
<%@page import="java.util.*"%>
<%@page import="uml.Ordenes"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/session.jsp" %>

<% String active="Articulos";%>

<!DOCTYPE html>
<html class="no-js">
<head>
  <%@include file="includes/meta.jsp" %>

</head>
<body class="" onload="cargar();">
  <section id="container">
    <%@include file="includes/nav_bar_header.jsp" %>
    <%@include file="includes/menu.jsp" %>

    <!--main content start-->
    <section class="main-content-wrapper">
      <section id="main-content">
        <div class="row">
            <div class="col-md-12">
            <div class="panel panel-default">
              <div class="panel-heading">
                <h3 class="panel-title">Administracion de Articulos</h3>
                <div class="actions pull-right">
                  <i class="fa fa-chevron-down"></i>
                  <i class="fa fa-times"></i>
                </div>
              </div>
              <div class="panel-body">
                <span class="btn btn-success" onclick="agregar();"><i class="fa fa-plus"></i>Agregar Articulo</span>
                <br/>
                <hr class="separator" />

                <div class="table-responsive" id="div_tabla">
                    
                </div>
                <div class="modal fade" id="div_modal">
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>
    </section>
    <!--main content end-->
  </section>
  <%@include file="includes/footer.jsp" %>
  <script src="JavaScript/articulos.js"></script>

</body>
</html>
