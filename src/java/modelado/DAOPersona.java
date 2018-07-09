/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelado;

import java.util.*;
import java.sql.*;
import uml.Persona;

/**
 *
 * @author ProgrammerPC
 */
public class DAOPersona implements Operaciones{
    
    DataBase db = new DataBase();

    @Override
    public int insertar(Object obj) {                
        Persona p = (Persona) obj;
        Connection conn;
        PreparedStatement pst;
        String sql = "INSERT INTO persona (nombres, apellidos, edad) values (?,?,?)";
        int respuesta = 0;
        try {
            Class.forName(db.getDriver());
            conn = DriverManager.getConnection(
            db.getUrl(),
            db.getUsuario(),
            db.getContraseña()
            );
            
            pst = conn.prepareStatement(sql);
            pst.setString(1, p.getNombres());
            pst.setString(2, p.getApellidos());
            pst.setInt(3, p.getEdad());
            
            int filas = pst.executeUpdate();
            
            conn.close();
            respuesta = filas;
        } catch(ClassNotFoundException | SQLException e){
            respuesta = 0;
        }
        return respuesta;
    }

    @Override
    public int eliminar(Object obj) {
        Persona p = (Persona) obj;
        Connection conn;
        PreparedStatement pst;
        String sql = "DELETE FROM persona WHERE id = ?";
        int respuesta = 0;
        try {
            Class.forName(db.getDriver());
            conn = DriverManager.getConnection(
            db.getUrl(),
            db.getUsuario(),
            db.getContraseña()
            );
            
            pst = conn.prepareStatement(sql);
            pst.setInt(1, p.getId());
            
            int filas = pst.executeUpdate();
            
            conn.close();
            respuesta = filas;
        } catch(ClassNotFoundException | SQLException e){
            respuesta = 0;
        }
        return respuesta;
    }

    @Override
    public int modificar(Object obj) {
        Persona p = (Persona) obj;
        Connection conn;
        PreparedStatement pst;
        String sql = "UPDATE persona SET nombres = ?, apellidos = ?, edad = ? WHERE id = ?";
        int respuesta = 0;
        try {
            Class.forName(db.getDriver());
            conn = DriverManager.getConnection(
            db.getUrl(),
            db.getUsuario(),
            db.getContraseña()
            );
            
            pst = conn.prepareStatement(sql);
            pst.setString(1, p.getNombres());
            pst.setString(2, p.getApellidos());
            pst.setInt(3, p.getEdad());
            pst.setInt(4, p.getId());

            int filas = pst.executeUpdate();
            
            conn.close();
            respuesta = filas;
        } catch(ClassNotFoundException | SQLException e){
            respuesta = 0;
        }
        return respuesta;
    }

    @Override
    public List<Persona> consultar() {
        List<Persona> datos = new ArrayList<>();
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        String sql = "SELECT * FROM persona";
        
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
                datos.add(new Persona(rs.getInt("id"),rs.getString("nombres"),rs.getString("apellidos"),rs.getInt("edad")));
            }
                    
            conn.close();
        } catch(ClassNotFoundException | SQLException e){
            
        }
        
        return datos;
    }

    @Override
    public List<?> filtrar(String campo, String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
