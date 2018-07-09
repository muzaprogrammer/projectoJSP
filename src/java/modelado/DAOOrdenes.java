/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import uml.DetalleOrden;
import uml.Ordenes;
import uml.TiposOrdenes;

/**
 *
 * @author ProgrammerPC
 */
public class DAOOrdenes {
    
    DataBase db = new DataBase();
    
    public List<Ordenes> consultar(int estado) {
        List<Ordenes> datos = new ArrayList<>();
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        String sql = "SELECT * FROM ordenes INNER JOIN usuarios ON usuarios.idUsuario = ordenes.idUsuario INNER JOIN tiposOrdenes ON tiposOrdenes.idTipoOrden = ordenes.idTipoOrden INNER JOIN estados ON estados.idEstado = ordenes.idEstado WHERE ordenes.idEstado = ?";
        String error;
        try {
            Class.forName(db.getDriver());
            conn = DriverManager.getConnection(
            db.getUrl(),
            db.getUsuario(),
            db.getContraseña()
            );
            
            pst = conn.prepareStatement(sql);
            pst.setInt(1, estado);
            rs = pst.executeQuery();
            
            while(rs.next()){
                datos.add(new Ordenes(
                        rs.getInt("idOrden"),
                        rs.getString("fecha"),
                        rs.getInt("idUsuario"),
                        rs.getString("cliente"),
                        rs.getString("nombre"),
                        rs.getInt("idTipoOrden"),
                        rs.getString("nombreTipoOrden"),
                        rs.getString("observaciones"),
                        rs.getInt("idEstado"),
                        rs.getString("nombreEstado")
                ));
            }
                    
            conn.close();
        } catch(ClassNotFoundException | SQLException e){
            error =String.valueOf(e);
        }
        
        return datos;
    }
    
    public List<TiposOrdenes> listarTiposOrdenes() {
        List<TiposOrdenes> datos = new ArrayList<>();
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        String sql = "SELECT * FROM tiposOrdenes WHERE idEstado = 1";
        
        try {
            Class.forName(db.getDriver());
            conn = DriverManager.getConnection(
            db.getUrl(),
            db.getUsuario(),
            db.getContraseña()
            );
            
            pst = conn.prepareStatement(sql);
            
            rs = pst.executeQuery();
            
            while(rs.next()){
                datos.add(new TiposOrdenes(
                        rs.getInt("idTipoOrden"),
                        rs.getString("nombreTipoOrden")
                ));
            }
                    
            conn.close();
        } catch(ClassNotFoundException | SQLException e){
            
        }
        
        return datos;
    }
    
    public int guardar(Object obj) {                
        DetalleOrden a = (DetalleOrden) obj;
        Connection conn;
        PreparedStatement pst;        
        int respuesta = 0, filas=0, idOrden=0;
        String respuesta2="", sql="";
        ResultSet rs;
        try {
            Class.forName(db.getDriver());
            conn = DriverManager.getConnection(
            db.getUrl(),
            db.getUsuario(),
            db.getContraseña()
            );
            
            if(a.getEstadoSave()==0){                
                sql = "INSERT INTO ordenes (idUsuario, idTipoOrden, observaciones, cliente) values (?,?,?,?)";
                
                pst = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                pst.setInt(1, a.getIdUsuario());
                pst.setInt(2, a.getIdTipoOrden());
                pst.setString(3, a.getObservaciones());
                pst.setString(4, a.getCliente());
            
                filas = pst.executeUpdate();
                
                rs = pst.getGeneratedKeys();
                while(rs.next()){
                idOrden=rs.getInt(1);
                }
                
                sql = "SELECT * FROM articulos WHERE idArticulo = ?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, a.getIdArticulo());
                rs = pst.executeQuery();
                double precio = 0;
                if(rs.next()){                    
                    precio = rs.getDouble("precioVenta");
                }
                
                sql = "INSERT INTO detallesordenes (idOrden, idArticulo, cantidad, precio) values (?,?,?,?)";
                
                pst = conn.prepareStatement(sql);
                pst.setInt(1, idOrden);
                pst.setInt(2, a.getIdArticulo());
                pst.setInt(3, a.getCantidad());
                pst.setDouble(4, precio);
            
                filas = pst.executeUpdate();
                
            }else{
                
                sql = "UPDATE ordenes SET idTipoOrden = ?, observaciones = ?, cliente = ? WHERE idOrden = ?";
                
                pst = conn.prepareStatement(sql);
                pst.setInt(1, a.getIdTipoOrden());
                pst.setString(2, a.getObservaciones());
                pst.setString(3, a.getCliente());
                pst.setInt(4, a.getEstadoSave());

                pst.executeUpdate();
                
                sql = "SELECT * FROM articulos WHERE idArticulo = ?";
                pst = conn.prepareStatement(sql);
                pst.setInt(1, a.getIdArticulo());
                rs = pst.executeQuery();
                double precio = 0;
                if(rs.next()){                    
                    precio = rs.getDouble("precioVenta");
                }
                
                sql = "INSERT INTO detallesordenes (idOrden, idArticulo, cantidad, precio) values (?,?,?,?)";
                
                pst = conn.prepareStatement(sql);
                pst.setInt(1, a.getEstadoSave());
                pst.setInt(2, a.getIdArticulo());
                pst.setInt(3, a.getCantidad());
                pst.setDouble(4, precio);
            
                filas = pst.executeUpdate();
                
                idOrden = a.getEstadoSave();
            }
            conn.close();
            respuesta = idOrden;
        } catch(ClassNotFoundException | SQLException e){
            //respuesta2 = String.valueOf(e);
            respuesta = 0;
        }
        return respuesta;
    }
    
    public List<DetalleOrden> consultarDetalle(Object obj) {                
        DetalleOrden a = (DetalleOrden) obj;
        List<DetalleOrden> datos = new ArrayList<>();
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        String sql = "SELECT * FROM detallesOrdenes INNER JOIN ordenes ON ordenes.idOrden = detallesOrdenes.idOrden INNER JOIN articulos ON articulos.idArticulo = detallesOrdenes.idArticulo WHERE ordenes.idOrden = ?";
        String error;
        try {
            Class.forName(db.getDriver());
            conn = DriverManager.getConnection(
            db.getUrl(),
            db.getUsuario(),
            db.getContraseña()
            );
            
            pst = conn.prepareStatement(sql);
            pst.setInt(1, a.getEstadoSave());
            rs = pst.executeQuery();
            
            while(rs.next()){
                datos.add(new DetalleOrden(
                        rs.getString("cliente"),
                        rs.getInt("idTipoOrden"),
                        rs.getString("observaciones"),
                        rs.getInt("idArticulo"),
                        rs.getString("descripcion"),
                        rs.getString("codigo"),
                        rs.getInt("cantidad"),
                        rs.getDouble("precio"),
                        rs.getInt("idOrden"),
                        rs.getInt("idUsuario")
                ));
            }
                    
            conn.close();
        } catch(ClassNotFoundException | SQLException e){
            error =String.valueOf(e);
        }
        
        return datos;
    }
    
    public int procesar(int id, int estado) {   
        Connection conn;
        PreparedStatement pst;
        String sql = "UPDATE ordenes SET idEstado = ? WHERE idOrden = ?";
        int respuesta = 0;
        try {
            Class.forName(db.getDriver());
            conn = DriverManager.getConnection(
            db.getUrl(),
            db.getUsuario(),
            db.getContraseña()
            );
            
            pst = conn.prepareStatement(sql);
            pst.setInt(1, estado);
            pst.setInt(2, id);
            
            int filas = pst.executeUpdate();
            
            conn.close();
            respuesta = filas;
        } catch(ClassNotFoundException | SQLException e){
            respuesta = 0;
        }
        return respuesta;
    }
}
