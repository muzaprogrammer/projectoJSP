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
public class TiposOrdenes {
    int idTipoOrden;
    String nombreTipoOrden;

    public TiposOrdenes() {
    }
    
    public TiposOrdenes(int idTipoOrden, String nombreTipoOrden) {
        this.idTipoOrden = idTipoOrden;
        this.nombreTipoOrden = nombreTipoOrden;
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
    
    
    
    
}
