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
import java.util.ArrayList;
import java.util.List;
import uml.Articulos;
import uml.Categorias;


/**
 *
 * @author ProgrammerPC
 */
public class DAOArticulos {
    
    DataBase db = new DataBase();

    public int guardar(Object obj) {                
        Articulos a = (Articulos) obj;
        Connection conn;
        PreparedStatement pst;
        String sql = "INSERT INTO articulos (idCategoria, codigo, descripcion, precioVenta, precioCompra) values (?,?,?,?,?)";
        int respuesta = 0;
        try {
            Class.forName(db.getDriver());
            conn = DriverManager.getConnection(
            db.getUrl(),
            db.getUsuario(),
            db.getContraseña()
            );
            
            pst = conn.prepareStatement(sql);
            pst.setInt(1, a.getIdCategoria());
            pst.setString(2, a.getCodigo());
            pst.setString(3, a.getDescripcion());
            pst.setDouble(4, a.getPrecioVenta());
            pst.setDouble(5, a.getPrecioCompra());
            
            int filas = pst.executeUpdate();
            
            conn.close();
            respuesta = filas;
        } catch(ClassNotFoundException | SQLException e){
            respuesta = 0;
        }
        return respuesta;
    }
    
    public int eliminar(Object obj) {
        Articulos a = (Articulos) obj;
        Connection conn;
        PreparedStatement pst;
        String sql = "UPDATE articulos SET idEstado = 2 WHERE idArticulo = ?";
        int respuesta = 0;
        try {
            Class.forName(db.getDriver());
            conn = DriverManager.getConnection(
            db.getUrl(),
            db.getUsuario(),
            db.getContraseña()
            );
            
            pst = conn.prepareStatement(sql);
            pst.setInt(1, a.getIdArticulo());
            
            int filas = pst.executeUpdate();
            
            conn.close();
            respuesta = filas;
        } catch(ClassNotFoundException | SQLException e){
            respuesta = 0;
        }
        return respuesta;
    }

    public int actualizar(Object obj) {
        Articulos a = (Articulos) obj;
        Connection conn;
        PreparedStatement pst;
        String sql = "UPDATE articulos SET idCategoria = ?, codigo= ?, descripcion= ?, precioVenta= ?, precioCompra= ? WHERE idArticulo = ?";
        int respuesta = 0;
        try {
            Class.forName(db.getDriver());
            conn = DriverManager.getConnection(
            db.getUrl(),
            db.getUsuario(),
            db.getContraseña()
            );
            
            pst = conn.prepareStatement(sql);
            pst.setInt(1, a.getIdCategoria());
            pst.setString(2, a.getCodigo());
            pst.setString(3, a.getDescripcion());
            pst.setDouble(4, a.getPrecioVenta());
            pst.setDouble(5, a.getPrecioCompra());
            pst.setDouble(6, a.getIdArticulo());


            int filas = pst.executeUpdate();
            
            conn.close();
            respuesta = filas;
        } catch(ClassNotFoundException | SQLException e){
            respuesta = 0;
        }
        return respuesta;
    }

    public List<Articulos> consultar() {
        List<Articulos> datos = new ArrayList<>();
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        String sql = "SELECT * FROM articulos INNER JOIN categorias ON categorias.idCategoria = articulos.idCategoria INNER JOIN estados ON estados.idEstado = articulos.idEstado WHERE articulos.idEstado = 1";
        
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
                datos.add(new Articulos(
                        rs.getInt("idArticulo"),
                        rs.getInt("idCategoria"),
                        rs.getString("nombreCategoria"),
                        rs.getString("codigo"),
                        rs.getString("descripcion"),
                        rs.getDouble("precioVenta"),
                        rs.getDouble("precioCompra"),
                        rs.getInt("idEstado"),
                        rs.getString("nombreEstado")
                ));
            }
                    
            conn.close();
        } catch(ClassNotFoundException | SQLException e){
            
        }
        
        return datos;
    }
    
    public List<Categorias> listarCategorias() {
        List<Categorias> datos = new ArrayList<>();
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        String sql = "SELECT * FROM categorias WHERE idEstado = 1";
        
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
                datos.add(new Categorias(
                        rs.getInt("idCategoria"),
                        rs.getString("nombreCategoria")
                ));
            }
                    
            conn.close();
        } catch(ClassNotFoundException | SQLException e){
            
        }
        
        return datos;
    }
  
    
}
