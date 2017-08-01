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
public class CDireccion extends C_Principal{
    
    private final String direccion="direccion";
    private final String descripcion="descripcion";//cajas o bolsas paquetes
    private final String ubigeo="ubigeo";
    private final String codigo_postal="codigo_postal";
    private final String latitud="latitud";
    private final String altitud="altitud";

    public CDireccion(String direccion, String descripcion, String ubigeo,String codigo_postal,double latitud,double altitud) {
        datos=new HashMap();
        setDireccion(direccion);
        setDescripcion(descripcion);
        setUbigeo(ubigeo);
        setLatitud(latitud);
        setAltitud(altitud);
        n=this.datos.size();
    } 
    public CDireccion(){       
        this.datos=new HashMap();
        n=this.datos.size();
    }       
    public void setDireccion(String x){
        this.datos.put(direccion, x);
    }
    public String getDireccion(){
        return (String) this.datos.get(direccion);
    }    
    public void setDescripcion(String x){
        this.datos.put(descripcion, x);
    }
    public String getDescripcion(){
        return (String) this.datos.get(descripcion);        
    }
    public void setUbigeo(String x){
        this.datos.put(ubigeo, x);
    }
    public String getUbigeo(){
        return (String) this.datos.get(ubigeo);        
    }
    public void setCodigo_postal(String x){
        this.datos.put(codigo_postal, x);
    }
    public String getCodigo_postal(){
        return (String) this.datos.get(codigo_postal);        
    }
    public void setLatitud(double x){
        this.datos.put(latitud, x);
    }
    public double getLatitud(){
        return (double) this.datos.get(latitud);        
    }
    public void setAltitud(double x){
        this.datos.put(altitud, x);
    }
    public double getAltitud(){
        return (double) this.datos.get(altitud);        
    }
        
    @Override
    public String toString() {
        String aux="";
        Iterator<String> it=this.datos.keySet().iterator();
        while(it.hasNext()){
            String s=it.next();
            aux+=s+" = "+datos.get(s)+",";
        }
        return "CDireccion{" +aux+'}';
    }    
}
