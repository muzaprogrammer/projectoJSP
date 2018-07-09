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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelado.DAOArticulos;
import uml.Categorias;
import uml.Articulos;

/**
 *
 * @author ProgrammerPC
 */
public class SERVArticulos extends HttpServlet {

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

            String accion = request.getParameter("accion");

            if ("agregar".equals(accion)) {
                out.print("  <div class=\"modal-dialog\">\n"
                        + "		<div class=\"modal-content\">\n"
                        + "\n"
                        + "			<!-- Modal heading -->\n"
                        + "			<div class=\"modal-header\">\n"
                        + "				<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\n"
                        + "				<h3 class=\"modal-title\">Agregar Articulo</h3>\n"
                        + "			</div>\n"
                        + "			<!-- // Modal heading END -->\n"
                        + "			<!-- Modal body -->\n"
                        + "			<div class=\"modal-body\">\n"
                        + "				<div class=\"innerAll\">\n"
                        + "					<div class=\"innerLR\">\n"
                        + "						<form class=\"form-horizontal\" role=\"form\" id=\"form_nuevo_articulo\" name=\"form_nuevo_articulo\">\n"
                        + "              <br>\n"
                        + "              <div class=\"form-group\">\n"
                        + "                <label class=\"col-sm-2 control-label\">Categoria: </label>\n"
                        + "                <div class=\"col-sm-10\">\n"
                        + "<select id=\"idCategoria\" name=\"idCategoria\" class=\"form-control select2\" >\n"
                        + "                    <option value=\"none\">Seleccione una categoria...</option>\n");

                DAOArticulos dao = new DAOArticulos();
                List<Categorias> datos = new ArrayList();
                datos = dao.listarCategorias();
                for (Categorias c : datos) {
                    out.print("<option value=\"" + c.getIdCategoria() + "\">" + c.getNombreCategoria() + "</option>");

                }
                out.print("</select> "
                        + "                </div>\n"
                        + "              </div>\n"
                        + "               <div class=\"form-group\">\n"
                        + "                <label class=\"col-sm-2 control-label\">Codigo: </label>\n"
                        + "                <div class=\"col-sm-10\">\n"
                        + "                  <input type=\"text\" name=\"codigo\" id=\"codigo\" class=\"form-control\">\n"
                        + "                </div>\n"
                        + "              </div>\n"
                        + "              <div class=\"form-group\">\n"
                        + "                <label class=\"col-sm-2 control-label\">Descripcion: </label>\n"
                        + "                <div class=\"col-sm-10\">\n"
                        + "                  <input type=\"text\" name=\"descripcion\" id=\"descripcion\" class=\"form-control\">\n"
                        + "                </div>\n"
                        + "              </div>\n"
                        + "              <div class=\"form-group\">\n"
                        + "                <label class=\"col-sm-2 control-label\">Precio venta: </label>\n"
                        + "                <div class=\"col-sm-10\">\n"
                        + "                  <input type=\"text\" name=\"precioVenta\" id=\"precioVenta\" class=\"form-control\">\n"
                        + "                </div>\n"
                        + "              </div>\n"
                        + "              <div class=\"form-group\">\n"
                        + "                <label class=\"col-sm-2 control-label\">Precio compra: </label>\n"
                        + "                <div class=\"col-sm-10\">\n"
                        + "                  <input type=\"text\" name=\"precioCompra\" id=\"precioCompra\" class=\"form-control\">\n"
                        + "                </div>\n"
                        + "              </div>"
                        + "              <br><br>\n"
                        + "              <div class=\"form-group text\">\n"
                        + "                <div class=\"text-center\">\n"
                        + "                  <button type=\"button\" class=\"btn btn-success\" onclick=\"guardar();\" name=\"submit\" id=\"submit\">\n"
                        + "                    <i class=\"fa fa-save\"></i> Guardar\n"
                        + "                  </button>\n"
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
            } else if ("cargar".equals(accion)) {
                DAOArticulos dao = new DAOArticulos();
                List<Articulos> datos = new ArrayList();
                datos = dao.consultar();
                out.print("<table class=\"dynamicTable tableTools table table-striped\" id=\"tabla_articulos\">");
                out.print("<thead><tr>");
                out.print("<th>#</th>");
                out.print("<th>CATEGORIA</th>");
                out.print("<th>CODIGO</th>");
                out.print("<th>DESCRIPCION</th>");
                out.print("<th>PRECIO VENTA</th>");
                out.print("<th>PRECIO COMPRA</td>");
                out.print("<th>ACCIONES</th>");
                out.print("</tr></thead><tbody>");
                int i = 0;
                for (Articulos a : datos) {
                    i++;

                    out.print("<tr>");
                    out.print("<td>" + i + "</td>");
                    out.print("<td>" + a.getNombreCategoria() + "</td>");
                    out.print("<td>" + a.getCodigo() + "</td>");
                    out.print("<td>" + a.getDescripcion() + "</td>");
                    out.print("<td>$" + String.format("%.2f", a.getPrecioVenta()) + "</td>");
                    out.print("<td>$" + String.format("%.2f", a.getPrecioCompra()) + "</td>");
                    out.print("<td>"
                            + "<span class=\"btn btn-info\" onclick=\"editar(" + a.getIdArticulo() + ",'" + a.getIdCategoria() + "','" + a.getCodigo() + "','" + a.getDescripcion() + "','" + a.getPrecioVenta() + "','" + a.getPrecioCompra() + "');\" title=\"Editar\"><i class=\"fa fa-pencil\"></i></span>"
                            + "<span class=\"btn btn-danger\" onclick=\"eliminar(" + a.getIdArticulo() + ");\" title=\"Eliminar orden\"><i class=\"fa fa-trash-o\"></i></span>"
                            + "</td>");
                    out.print("</tr>");

                }
                out.print("</tbody></table>");
                out.print("<script>$(\"#tabla_articulos\").DataTable({\"aaSorting\": [],\"displayLength\": 25});</script>");
            } else if ("editar".equals(accion)) {
                out.print("  <div class=\"modal-dialog\">\n"
                        + "		<div class=\"modal-content\">\n"
                        + "\n"
                        + "			<!-- Modal heading -->\n"
                        + "			<div class=\"modal-header\">\n"
                        + "				<button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-hidden=\"true\">&times;</button>\n"
                        + "				<h3 class=\"modal-title\">Editar Articulo</h3>\n"
                        + "			</div>\n"
                        + "			<!-- // Modal heading END -->\n"
                        + "			<!-- Modal body -->\n"
                        + "			<div class=\"modal-body\">\n"
                        + "				<div class=\"innerAll\">\n"
                        + "					<div class=\"innerLR\">\n"
                        + "						<form class=\"form-horizontal\" role=\"form\" id=\"form_editar_articulo\" name=\"form_editar_articulo\">\n"
                        + "              <br><input type=\"hidden\" name=\"idArticulo\" id=\"idArticulo\" value=\"" + request.getParameter("idArticulo") + "\">\n"
                        + "              <div class=\"form-group\">\n"
                        + "                <label class=\"col-sm-2 control-label\">Categoria: </label>\n"
                        + "                <div class=\"col-sm-10\">\n"
                        + "<select id=\"idCategoria\" name=\"idCategoria\" class=\"form-control select2\" >\n"
                        + "                    <option value=\"none\">Seleccione una categoria...</option>\n");

                DAOArticulos dao = new DAOArticulos();
                List<Categorias> datos = new ArrayList();
                datos = dao.listarCategorias();
                for (Categorias c : datos) {
                    if (Integer.valueOf(c.getIdCategoria()) == Integer.valueOf(request.getParameter("idCategoria"))) {
                        out.print("<option value=\"" + c.getIdCategoria() + "\" selected>" + c.getNombreCategoria() + "</option>");
                    } else {
                        out.print("<option value=\"" + c.getIdCategoria() + "\">" + c.getNombreCategoria() + "</option>");
                    }

                }
                out.print("</select> "
                        + "                </div>\n"
                        + "              </div>\n"
                        + "               <div class=\"form-group\">\n"
                        + "                <label class=\"col-sm-2 control-label\">Codigo: </label>\n"
                        + "                <div class=\"col-sm-10\">\n"
                        + "                  <input type=\"text\" name=\"codigo\" id=\"codigo\" class=\"form-control\" value='" + request.getParameter("codigo") + "'>\n"
                        + "                </div>\n"
                        + "              </div>\n"
                        + "              <div class=\"form-group\">\n"
                        + "                <label class=\"col-sm-2 control-label\">Descripcion: </label>\n"
                        + "                <div class=\"col-sm-10\">\n"
                        + "                  <input type=\"text\" name=\"descripcion\" id=\"descripcion\" class=\"form-control\" value='" + request.getParameter("descripcion") + "'>\n"
                        + "                </div>\n"
                        + "              </div>\n"
                        + "              <div class=\"form-group\">\n"
                        + "                <label class=\"col-sm-2 control-label\">Precio venta: </label>\n"
                        + "                <div class=\"col-sm-10\">\n"
                        + "                  <input type=\"text\" name=\"precioVenta\" id=\"precioVenta\" class=\"form-control\" value='" + request.getParameter("precioVenta") + "'>\n"
                        + "                </div>\n"
                        + "              </div>\n"
                        + "              <div class=\"form-group\">\n"
                        + "                <label class=\"col-sm-2 control-label\">Precio compra: </label>\n"
                        + "                <div class=\"col-sm-10\">\n"
                        + "                  <input type=\"text\" name=\"precioCompra\" id=\"precioCompra\" class=\"form-control\" value='" + request.getParameter("precioCompra") + "'>\n"
                        + "                </div>\n"
                        + "              </div>"
                        + "              <br><br>\n"
                        + "              <div class=\"form-group text\">\n"
                        + "                <div class=\"text-center\">\n"
                        + "                  <button type=\"button\" class=\"btn btn-warning\" onclick=\"actualizar();\" name=\"submit\" id=\"submit\">\n"
                        + "                    <i class=\"fa fa-save\"></i> Actualizar\n"
                        + "                  </button>\n"
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
            } else if ("eliminar".equals(accion)) {
                int respuesta = 0;
                DAOArticulos dao = new DAOArticulos();
                Articulos a = new Articulos();
                a.setIdArticulo(Integer.valueOf(request.getParameter("id")));

                respuesta = dao.eliminar(a);

                out.print(respuesta);
            } else if ("guardar".equals(accion)) {
                int respuesta = 0;
                DAOArticulos dao = new DAOArticulos();
                Articulos a = new Articulos();
                a.setIdCategoria(Integer.valueOf(request.getParameter("idCategoria")));
                a.setCodigo(String.valueOf(request.getParameter("codigo")));
                a.setDescripcion(String.valueOf(request.getParameter("descripcion")));
                a.setPrecioVenta(Double.valueOf(request.getParameter("precioVenta")));
                a.setPrecioCompra(Double.valueOf(request.getParameter("precioCompra")));

                respuesta = dao.guardar(a);

                out.print(respuesta);
            } else if ("actualizar".equals(accion)) {
                int respuesta = 0;
                DAOArticulos dao = new DAOArticulos();
                Articulos a = new Articulos();
                a.setIdArticulo(Integer.valueOf(request.getParameter("idArticulo")));
                a.setIdCategoria(Integer.valueOf(request.getParameter("idCategoria")));
                a.setCodigo(String.valueOf(request.getParameter("codigo")));
                a.setDescripcion(String.valueOf(request.getParameter("descripcion")));
                a.setPrecioVenta(Double.valueOf(request.getParameter("precioVenta")));
                a.setPrecioCompra(Double.valueOf(request.getParameter("precioCompra")));

                respuesta = dao.actualizar(a);

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
