<%
    HttpSession sesion = request.getSession();
    
    if(sesion.getAttribute("usuario") == null){
        response.sendRedirect("login.jsp");
    }
    
%>

