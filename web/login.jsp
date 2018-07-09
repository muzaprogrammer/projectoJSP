<%--
Document   : index
Created on : May 29, 2018, 6:28:57 PM
Author     : ProgrammerPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String active ="Login";
    HttpSession sesion = request.getSession();
    if(sesion.getAttribute("usuario") != null){
        response.sendRedirect("index.jsp");
    }
%>
<!DOCTYPE html>
<html>
  <head>
    <%@include file="includes/meta.jsp" %>
  </head>
  <body class=" loginWrapper">
    <section id="login-container">
      
      <div class="row">
        <div class="col-md-3" id="login-wrapper">
          <div class="panel panel-primary animated flipInY">
            <div class="panel-heading">
              <h4 class="innerAll margin-none border-bottom text-center">
                LOGIN
              </h4>
            </div>
            <div class="panel-body">
              <p class="text-center">
                <i class="fa fa-lock"></i>
                Ingreso al sistema.
              </p>
              <form method="POST">
                
                <div class="form-group">
                  <label for="exampleInputEmail1">Usuario</label>
                  <input type="text" class="form-control" name="usuario" id="usuario" placeholder="Usuario">
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">Contraseña</label>
                    <input type="password" class="form-control" name="contrasena" placeholder="Contraseña" id="contrasena">
                    </div>
                    <span onclick="validar()" class="btn btn-primary btn-block">Ingresar</span>
                    
                  </form>
                </div>
              </div>
            </div>
          </div>
          
        </section>
        
        
        <script src="assets/js/jquery-1.10.2.min.js"></script>
        <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/plugins/waypoints/waypoints.min.js"></script>
        <script src="assets/plugins/nanoScroller/jquery.nanoscroller.min.js"></script>
        <script src="assets/js/application.js"></script>
        <script src="assets/plugins/sweetalert/sweetalert.min.js"></script>
        <script src="assets/plugins/sweetalert/jquery.sweet-alert.custom.js"></script>
        <script src="JavaScript/login.js"></script>
        
      </body>
    </html>

