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
public class CContacto extends C_Principal{
    
    private final String tipo="tipo";
    private final String numero="numero";//cajas o bolsas paquetes
    private final String empresa="empresa";

    public CContacto(String tipo, String numero, String empresa) {
        datos=new HashMap();
        setTipo(tipo);
        setNumero(numero);
        setEmpresa(empresa);
        n=this.datos.size();
    } 
    public CContacto(){       
        this.datos=new HashMap();
        n=this.datos.size();
    }       
    public void setTipo(String x){
        this.datos.put(tipo, x);
    }
    public String getTipo(){
        return (String) this.datos.get(tipo);
    }    
    public void setNumero(String x){
        this.datos.put(numero, x);
    }
    public String getNumero(){
        return (String) this.datos.get(numero);        
    }
    public void setEmpresa(String x){
        this.datos.put(empresa, x);
    }
    public String getEmpresa(){
        return (String) this.datos.get(empresa);        
    }
        
    @Override
    public String toString() {
        String aux="";
        Iterator<String> it=this.datos.keySet().iterator();
        while(it.hasNext()){
            String s=it.next();
            aux+=s+" = "+datos.get(s)+",";
        }
        return "CContacto{" +aux+'}';
    }    
}
