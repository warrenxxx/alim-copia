/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.HashMap;
import org.bson.types.ObjectId;


/**
 *
 * @author WARREN
 */
public class C_Principal {
    protected HashMap datos;
    protected int n;
    
 
    public HashMap get_datos(){
        return (HashMap) datos.clone();
    }
    public void set_datos(HashMap set){
        this.datos=set;
    }
    public void setId(String valor){
        if(valor==null){            
            datos.put("_id",null);        
            return;
        }
        if(valor.length()!=24){            
            datos.put("_id",null);        
            return;
        }
        datos.put("_id",new ObjectId(valor));        
    }
    public void setId(ObjectId valor){
        datos.put("_id",valor);
    }
    public ObjectId getId(){
        return (ObjectId) datos.get("_id");
    }

}
