/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelado;

import java.util.*;
import java.sql.*;
import uml.Login;

/**
 *
 * @author ProgrammerPC
 */
public class DAOLogin {
    
    DataBase db = new DataBase();
    
    public String validar(Object obj){
        Login l = (Login) obj;
        Connection conn;
        PreparedStatement pst;
        ResultSet rs;
        String sql = "SELECT COUNT(*) AS num, usr.* ,rol.nombreRol, dat.* FROM usuarios AS usr INNER JOIN roles AS rol ON rol.idRol = usr.idRol INNER JOIN datosEmpresa AS dat ON dat.idEmpresa = usr.idEmpresa WHERE usr.idEstado = 1 AND usuario = ? AND contrasena = ?";
        String respuesta = "0";
        try {
            Class.forName(db.getDriver());
            conn = DriverManager.getConnection(
            db.getUrl(),
            db.getUsuario(),
            db.getContraseña()
            );
            
            pst = conn.prepareStatement(sql);
            
            pst.setString(1, l.getUsuario());
            pst.setString(2, l.getContrasena());

            rs = pst.executeQuery();
            if(rs.next()){
                respuesta = rs.getString("num");
                
                l.setIdUsuario(rs.getInt("idUsuario"));
                l.setUsuario(rs.getString("usuario"));
                l.setContrasena(rs.getString("contrasena"));
                l.setNombre(rs.getString("nombre"));
                l.setIdRol(rs.getInt("idRol"));
                l.setFoto(rs.getString("foto"));
                l.setNombreRol(rs.getString("nombreRol"));
                l.setNombreEmpresa(rs.getString("nombreEmpresa"));
                l.setNombreApp(rs.getString("nombreApp"));

            }
            conn.close();
        } catch(ClassNotFoundException | SQLException e){
            respuesta = String.valueOf(e);
        }
        return respuesta;
    }
}
