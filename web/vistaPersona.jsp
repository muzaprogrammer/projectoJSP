<%@page import="modelado.DAOPersona"%>
<%@page import="java.util.*"%>
<%@page import="uml.Persona"%>

<%-- 
    Document   : vistaPersona
    Created on : May 28, 2018, 10:31:37 PM
    Author     : ProgrammerPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="SERVPersona" method="POST">
            
            Nombres: <input type="text" name="nombres"><br>
            Apellidos <input type="text" name="apellidos"><br>
            Edad <input type="text" name="edad"><br>
            
            <input type="submit" name="insert" value="Insertar">
            <input type="submit" name="update" value="Modificar">
            <input type="submit" name="delete" value="Eliminar">

        </form>
        
        <table>
            <tr>
                <td>
                    Id
                </td>
                <td>
                    Nombres
                </td>
                <td>
                    Apellidos
                </td>
                <td>
                    Edad
                </td>
            </tr>
            
            <%
                DAOPersona dao = new DAOPersona();
                List<Persona> datos = new ArrayList();
                
            datos = dao.consultar();
            for(Persona p : datos){
                %>
                <tr>
                    <td>
                        <%= p.getId()%>
                    </td>
                    <td>
                        <%= p.getNombres()%>
                    </td>
                    <td>
                        <%= p.getApellidos()%>
                    </td>
                    <td>
                        <%= p.getEdad()%>
                    </td>
                </tr>
                <%               
            }
            %>
            <tr>
                
            </tr>
        </table>
        
    </body>
</html>
    