/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uml;

/**
 *
 * @author ProgrammerPC
 */
public class DetalleOrden {
    
    String cliente;
    int idTipoOrden;
    String observaciones;
    int idArticulo;
    String nombreArticulo;   
    String codigo;
    int cantidad;
    double precioVenta;
    int estadoSave;
    int idUsuario;


    public DetalleOrden() {
    }

    public DetalleOrden(String cliente, int idTipoOrden, String observaciones, int idArticulo, String nombreArticulo, String codigo, int cantidad, double precioVenta, int estadoSave, int idUsuario) {
        this.cliente = cliente;
        this.idTipoOrden = idTipoOrden;
        this.observaciones = observaciones;
        this.idArticulo = idArticulo;
        this.nombreArticulo = nombreArticulo;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.precioVenta = precioVenta;
        this.estadoSave = estadoSave;
        this.idUsuario = idUsuario;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getIdTipoOrden() {
        return idTipoOrden;
    }

    public void setIdTipoOrden(int idTipoOrden) {
        this.idTipoOrden = idTipoOrden;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getEstadoSave() {
        return estadoSave;
    }

    public void setEstadoSave(int estadoSave) {
        this.estadoSave = estadoSave;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    

}
