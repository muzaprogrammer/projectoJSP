
<%@page import="uml.Articulos"%>
<%@page import="modelado.DAOArticulos"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="uml.TiposOrdenes"%>
<%@page import="modelado.DAOOrdenes"%>
<%-- 
    Document   : ordenes
    Created on : Jun 3, 2018, 6:23:47 PM
    Author     : ProgrammerPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="includes/session.jsp" %>

<% String active = "Nueva Orden";%>

<!DOCTYPE html>
<html class="no-js">
    <head>
        <%@include file="includes/meta.jsp" %>

    </head>
    <body class="" onload="cargarDetalle();">
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
                                    <h3 class="panel-title">Nueva Orden</h3>
                                    <div class="actions pull-right">
                                        <i class="fa fa-chevron-down"></i>
                                        <i class="fa fa-times"></i>
                                    </div>
                                </div>
                                <div class="panel-body">

                                    <form class="form-horizontal form-border" id="form-alumno" method="get" autocomplete="off">
                                        <input class="form-control" id="estadoSave" name="estadoSave" type="hidden" value="0"/>
                                        <!-- Widget -->
                                        <div class="widget widget-inverse">
                                            <!-- // Widget heading END -->
                                            <div class="widget-body">
                                                <h3 class="box-title">DATOS GENERALES</h3>
                                                <hr class="m-t-0 m-b-40">
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label class="col-md-3 control-label" for="firstname">Cliente</label>
                                                            <div class="col-md-9">
                                                                <input class="form-control" id="cliente" name="cliente" type="text" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label class="col-md-3 control-label" for="firstname">Tipo de orden</label>
                                                            <div class="col-md-9">
                                                                <select class="form-control" id="idTipoOrden" name="idTipoOrden">
                                                                    <option value="">Seleccione...</option>
                                                                    <%
                                                                        DAOOrdenes dao = new DAOOrdenes();
                                                                        List<TiposOrdenes> datos = new ArrayList();
                                                                        datos = dao.listarTiposOrdenes();
                                                                        for(TiposOrdenes o : datos){
                                                                            out.print("<option value=\""+o.getIdTipoOrden()+"\">"+o.getNombreTipoOrden()+"</option>");

                                                                        }
                                                                        
                                                                    %>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-12">
                                                        <div class="form-group">
                                                            <label class="col-md-2 control-label" for="firstname">Observaciones:</label>
                                                            <div class="col-md-10">
                                                                <input class="form-control" id="observaciones" name="observaciones" type="text"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <h3 class="box-title">AGREGAR ARTICULOS A LA ORDEN</h3>
                                                <hr class="m-t-0 m-b-40">
                                                <div class="row">
                                                    <div class="col-md-4">
                                                        <div class="form-group">
                                                            <label class="col-md-3 control-label" for="firstname">Cantidad:</label>
                                                            <div class="col-md-9">
                                                                <input class="form-control" id="cantidad" name="cantidad" type="text" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label class="col-md-3 control-label" for="firstname">Articulo:</label>
                                                            <div class="col-md-9">
                                                                <select class="form-control" id="idArticulo" name="idArticulo">
                                                                    <option value="">Seleccione...</option>
                                                                    <%
                                                                        DAOArticulos dao2 = new DAOArticulos();
                                                                        List<Articulos> datos2 = new ArrayList();
                                                                        datos2 = dao2.consultar();
                                                                        for(Articulos a : datos2){
                                                                            out.print("<option value=\""+a.getIdArticulo()+"\">"+a.getCodigo()+" - "+a.getDescripcion()+"</option>");

                                                                        }
                                                                    %>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-2">
                                                        <div class="form-group">
                                                            <label class="col-md-3 control-label" for="firstname"></label>
                                                            <div class="col-md-9">
                                                                <span class="btn btn-success" id="editar" onclick="agregar_producto();"><i class="fa fa-check-circle"></i> AGREGAR</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    
                                                </div>
                                                                
                                            </div>
                                        </div>
                                    </form>


                                    <br/>
                                    <h3 class="box-title">DETALLE DE LA ORDEN</h3>
                                    <hr class="m-t-0 m-b-40">

                                    <div class="table-responsive" id="div_tabla">

                                    </div>
                                    <div class="modal fade" id="div_modal">
                                    </div>
                                    <hr class="separator" />
                                    <div class="form-group" align="center">
                                      <span class="btn btn-success" id="cancelar" onclick="JavaScript:window.location='ordenes.jsp';"><i class="fa fa-mail-reply"></i> FINALIZAR</span>
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
