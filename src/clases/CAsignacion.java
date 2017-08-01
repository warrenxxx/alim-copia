/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;


import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author WARREN
 */
public class CAsignacion extends C_Principal{
    
    private final String codigo="codigo";
    private final String cantidad="cantidad";//cajas o bolsas paquetes
  
    public CAsignacion(String codigo, int cantidad){
        datos=new HashMap();
        setCodigo(codigo);
        setCantidad(cantidad);
       
        n=this.datos.size();
    } 
    public CAsignacion(){       
        this.datos=new HashMap();
        n=this.datos.size();
    }       
    public void setCodigo(String x){
        this.datos.put(codigo, x);
    }
    public String getCodigo(){
        return (String) this.datos.get(codigo);
    }    
    public void setCantidad(int x){
        this.datos.put(cantidad, x);
    }
    public int getCantidad(){
        return (int) this.datos.get(cantidad);        
    }
   
    @Override
    public String toString() {
        String aux="";
        Iterator<String> it=this.datos.keySet().iterator();
        while(it.hasNext()){
            String s=it.next();
            aux+=s+" = "+datos.get(s)+",";
        }
        return "CCodigo{" +aux+'}';
    }    
}
