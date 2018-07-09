/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelado;

import java.util.List;

/**
 *
 * @author ProgrammerPC
 */
public interface Operaciones {
    
    public int insertar(Object obj);
    
    public int eliminar(Object obj);
    
    public int modificar(Object obj);
    
    public List<?> consultar();
    
    public List<?> filtrar(String campo, String criterio);    
    
}
