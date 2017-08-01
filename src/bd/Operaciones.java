/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author WARREN
 */
public interface Operaciones {
    public String insertar(Object o);
    public String eliminar(Object o);
    public String modificar(Object o);
    public ArrayList listar();
    public Object buscar_id(String id_find);
//    public ArrayList listar(String clave,String valor);
    public ArrayList listar(HashMap map);
}
