/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelado.DAOLogin;
import uml.Login;

/**
 *
 * @author ProgrammerPC
 */
public class SERVLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DAOLogin dao = new DAOLogin();
            Login l = new Login();
            
            String respuesta = "0";
            
            try{
                l.setUsuario(request.getParameter("usuario"));
                l.setContrasena(request.getParameter("contrasena"));
                    
                respuesta = dao.validar(l);
                
                HttpSession sesion = request.getSession();
                sesion.setAttribute("idUsuario", l.getIdUsuario());
                sesion.setAttribute("usuario", l.getUsuario());
                sesion.setAttribute("contrasena", l.getContrasena());
                sesion.setAttribute("nombre", l.getNombre());
                sesion.setAttribute("idRol", l.getIdRol());
                sesion.setAttribute("foto", l.getFoto());
                sesion.setAttribute("nombreRol", l.getNombreRol());   
                sesion.setAttribute("nombreEmpresa", l.getNombreEmpresa());   
                sesion.setAttribute("nombreApp", l.getNombreApp());   

                
                out.print(respuesta);
            } catch (NumberFormatException e){
                
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
