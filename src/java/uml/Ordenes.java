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
public class Ordenes {
    
    int idOrden;
    String fecha;
    int idUsuario;
    String cliente;
    String nombreUsuario;
    int idTipoOrden;
    String nombreTipoOrden;
    String observaciones;
    int idEstado;
    String nombreEstado;

    public Ordenes() {
    }

    public Ordenes(int idOrden, String fecha, int idUsuario, String cliente, String nombreUsuario, int idTipoOrden, String nombreTipoOrden, String observaciones, int idEstado, String nombreEstado) {
        this.idOrden = idOrden;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
        this.cliente = cliente;
        this.nombreUsuario = nombreUsuario;
        this.idTipoOrden = idTipoOrden;
        this.nombreTipoOrden = nombreTipoOrden;
        this.observaciones = observaciones;
        this.idEstado = idEstado;
        this.nombreEstado = nombreEstado;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getIdTipoOrden() {
        return idTipoOrden;
    }

    public void setIdTipoOrden(int idTipoOrden) {
        this.idTipoOrden = idTipoOrden;
    }

    public String getNombreTipoOrden() {
        return nombreTipoOrden;
    }

    public void setNombreTipoOrden(String nombreTipoOrden) {
        this.nombreTipoOrden = nombreTipoOrden;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
    
    

    
}
