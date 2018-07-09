
<%-- 
    Document   : ordenes
    Created on : Jun 3, 2018, 6:23:47 PM
    Author     : ProgrammerPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/session.jsp" %>

<% String active="Ordenes";%>

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
                <h3 class="panel-title">Administracion de Ordenes</h3>
                <div class="actions pull-right">
                  <i class="fa fa-chevron-down"></i>
                  <i class="fa fa-times"></i>
                </div>
              </div>
              <div class="panel-body">
                  <div class="row">
                    <div class="col-md-3">
                        <span class="btn btn-success" onclick="JavaScript:window.location = 'nuevaOrden.jsp';"><i class="fa fa-plus"></i>Agregar Orden</span>
                    </div>
                    <div class="col-md-6">

                    </div>
                    <div class="col-md-3">
                        <select class="form-control" name="estado" id="estado" onchange="cargar();">
                            <option value="3">En espera</option>
                            <option value="4">En proceso</option>
                            <option value="5">Despachada</option>

                        </select>
                    </div>
                  </div>
                  
                
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
  <script src="JavaScript/ordenes.js"></script>

</body>
</html>
