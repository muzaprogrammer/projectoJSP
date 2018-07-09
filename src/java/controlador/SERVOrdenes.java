/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelado.DAOOrdenes;
import uml.Ordenes;
import java.util.*;
import javax.servlet.http.HttpSession;
import uml.DetalleOrden;

/**
 *
 * @author ProgrammerPC
 */
public class SERVOrdenes extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            String accion = request.getParameter("accion");
            
            if("cargar".equals(accion)){
                DAOOrdenes dao = new DAOOrdenes();
                List<Ordenes> datos = new ArrayList();
                datos = dao.consultar(Integer.valueOf(request.getParameter("estado")));
                out.print("<table class=\"dynamicTable tableTools table table-striped\" id=\"tabla_ordenes\">");
                out.print("<thead><tr>");
                out.print("<th>#</th>");
                out.print("<th>FECHA</th>");
                out.print("<th>USUARIO</th>");            
                out.print("<th>CLIENTE</th>");
                out.print("<th>TIPO ORDEN</th>");
                out.print("<th>ESTADO</td>");
                out.print("<th>OBSERVACIONES</th>");
                out.print("<th>ACCIONES</th>");
                out.print("</tr></thead><tbody>");           
                int i = 0;
                for(Ordenes o : datos){
                    i++;

                    out.print("<tr>");
                    out.print("<td>"+i+"</td>");
                    out.print("<td>"+o.getFecha()+"</td>");
                    out.print("<td>"+o.getNombreUsuario()+"</td>");            
                    out.print("<td>"+o.getCliente()+"</td>");
                    out.print("<td>"+o.getNombreTipoOrden()+"</td>");
                    out.print("<td>"+o.getNombreEstado()+"</td>");
                    out.print("<td>"+o.getObservaciones()+"</td>");
                    out.print("<td>"
                            + "<span class=\"btn btn-info\" onclick=\"ver("+o.getIdOrden()+");\" title=\"Ver detalle de orden\"><i class=\"fa fa-eye\"></i></span>");
                    if(o.getIdEstado()==3){
                        out.print("<span class=\"btn btn-warning\" onclick=\"proceso("+o.getIdOrden()+");\" title=\"En proceso\"><i class=\"fa fa-check\"></i></span>");
                    }else if(o.getIdEstado()==4){
                        out.print("<span class=\"btn btn-success\" onclick=\"finalizar("+o.getIdOrden()+");\" title=\"En proceso\"><i class=\"fa fa-check\"></i></span>");
                    }
                        out.print("<span class=\"btn btn-danger\" onclick=\"eliminar("+o.getIdOrden()+");\" title=\"Eliminar orden\"><i class=\"fa fa-trash-o\"></i></span>"
                            + "</td>");
                    out.print("</tr>");    

                }
                out.print("</tbody></table>");
                out.print("<script>$(\"#tabla_ordenes\").DataTable({\"aaSorting\": [],\"displayLength\": 25});</script>");
            }else if("addDetalle".equals(accion)){
                HttpSession sesion = request.getSession();
                int respuesta = 0;
                DAOOrdenes dao = new DAOOrdenes();
                DetalleOrden o = new DetalleOrden();
                o.setCliente(String.valueOf(request.getParameter("cliente")));
                o.setIdTipoOrden(Integer.valueOf(request.getParameter("idTipoOrden")));
                o.setObservaciones(String.valueOf(request.getParameter("observaciones")));
                o.setIdArticulo(Integer.valueOf(request.getParameter("idArticulo")));
                o.setCantidad(Integer.valueOf(request.getParameter("cantidad")));
                o.setEstadoSave(Integer.valueOf(request.getParameter("estadoSave"))); 
                o.setIdUsuario((Integer) sesion.getAttribute("idUsuario"));    



                respuesta = dao.guardar(o);

                out.print(respuesta);
            }else if("cargarDetalle".equals(accion)){
                DAOOrdenes dao = new DAOOrdenes();
                List<DetalleOrden> datos = new ArrayList();
                DetalleOrden o = new DetalleOrden();
                o.setEstadoSave(Integer.valueOf(request.getParameter("estadoSave")));
                datos = dao.consultarDetalle(o);
                out.print("<table class=\"dynamicTable tableTools table table-striped\" id=\"tabla_ordenes_detalle\">");
                out.print("<thead><tr>");
                out.print("<th>#</th>");
                out.print("<th>CODIGO</th>");
                out.print("<th>DESCRIPCION</th>");            
                out.print("<th>PRECIO UNITARIO</th>");
                out.print("<th>CANTIDAD</th>");
                out.print("<th>TOTAL</td>");
                out.print("</tr></thead><tbody>");           
                int i = 0;
                double total = 0;
                for(DetalleOrden or : datos){
                    i++;

                    out.print("<tr>");
                    out.print("<td>"+i+"</td>");
                    out.print("<td>"+or.getCodigo()+"</td>");
                    out.print("<td>"+or.getNombreArticulo()+"</td>");            
                    out.print("<td>$"+String.format("%.2f", or.getPrecioVenta())+"</td>");
                    out.print("<td>"+or.getCantidad()+"</td>");
                    out.print("<td>$"+String.format("%.2f", or.getCantidad()*or.getPrecioVenta())+"</td>");
                    
                    out.print("</tr>");    
                    total+=or.getCantidad()*or.getPrecioVenta();
                }
                out.print("</tbody><tfoot>");
                out.print("<tr>");  
                out.print("<td colspan='5'></td>");  
                out.print("<td>$"+String.format("%.2f", total)+"</td>");  
                out.print("</tr>");  
                out.print("</tfoot></table>");
                out.print("<script>$(\"#tabla_ordenes_detalle\").DataTable({\"aaSorting\": [],\"displayLength\": 25});</script>");
            }else if("verDetalle".equals(accion)){
                out.print("  <div class=\"modal-dialog modal-lg\">\n"
                        + "		<div class=\"modal-content\">\n"
                        + "\n"
                        + "			<!-- Modal heading -->\n"
                        + "			<div class=\"modal-header\">\n"
                        + "				<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\n"
                        + "				<h3 class=\"modal-title\">Detalle de orden</h3>\n"
                        + "			</div>\n"
                        + "			<!-- // Modal heading END -->\n"
                        + "			<!-- Modal body -->\n"
                        + "			<div class=\"modal-body\">\n"
                        + "				<div class=\"innerAll\">\n"
                        + "					<div class=\"innerLR\">\n"
                        + "						<form class=\"form-horizontal\" role=\"form\" id=\"form_nuevo_articulo\" name=\"form_nuevo_articulo\">\n");
                      
                        DAOOrdenes dao = new DAOOrdenes();
                List<DetalleOrden> datos = new ArrayList();
                DetalleOrden o = new DetalleOrden();
                o.setEstadoSave(Integer.valueOf(request.getParameter("estadoSave")));
                datos = dao.consultarDetalle(o);
                out.print("<table class=\"dynamicTable tableTools table table-striped\" id=\"tabla_ordenes_detalle\">");
                out.print("<thead><tr>");
                out.print("<th>#</th>");
                out.print("<th>CODIGO</th>");
                out.print("<th>DESCRIPCION</th>");            
                out.print("<th>PRECIO UNITARIO</th>");
                out.print("<th>CANTIDAD</th>");
                out.print("<th>TOTAL</td>");
                out.print("</tr></thead><tbody>");           
                int i = 0;
                double total = 0;
                for(DetalleOrden or : datos){
                    i++;

                    out.print("<tr>");
                    out.print("<td>"+i+"</td>");
                    out.print("<td>"+or.getCodigo()+"</td>");
                    out.print("<td>"+or.getNombreArticulo()+"</td>");            
                    out.print("<td>$"+String.format("%.2f", or.getPrecioVenta())+"</td>");
                    out.print("<td>"+or.getCantidad()+"</td>");
                    out.print("<td>$"+String.format("%.2f", or.getCantidad()*or.getPrecioVenta())+"</td>");
                    
                    out.print("</tr>");    
                    total+=or.getCantidad()*or.getPrecioVenta();
                }
                out.print("</tbody><tfoot>");
                out.print("<tr>");  
                out.print("<td colspan='5'></td>");  
                out.print("<td>$"+String.format("%.2f", total)+"</td>");  
                out.print("</tr>");  
                out.print("</tfoot></table>");
                out.print("<script>$(\"#tabla_ordenes_detalle\").DataTable({\"aaSorting\": [],\"displayLength\": 25});</script>");
                        
                        out.print("              <div class=\"form-group text\">\n"
                        + "                <div class=\"text-center\">\n"
                        + "                  <button type=\"button\" class=\"btn btn-info\" onclick=\"cerrar_modal();\" id=\"salir\">\n"
                        + "                    <i class=\"fa fa-mail-reply\"></i> Cerrar\n"
                        + "                  </button>\n"
                        + "                </div>\n"
                        + "              </div>\n"
                        + "              <div class=\"form-actions\">\n"
                        + "              </div>\n"
                        + "            </form>\n"
                        + "          </div>\n"
                        + "				</div>\n"
                        + "			</div>\n"
                        + "			<!-- // Modal body END -->\n"
                        + "		</div>\n"
                        + "	</div>");
            }else if("procesar".equals(accion)){
                int respuesta = 0;
                DAOOrdenes dao = new DAOOrdenes();

                respuesta = dao.procesar(Integer.valueOf(request.getParameter("id")),Integer.valueOf(request.getParameter("estado")));

                out.print(respuesta);
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
