/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author WARREN
 */
public class CVenta_detalle extends C_Principal{
    private final String cantidad="cantidad";
    private final String precio="precio";
    private final String descuento="descuento";
    private final String id_articulo="id_articulo";//cajas o bolsas paquetes
 
    public CVenta_detalle(){       
        this.datos=new HashMap();
        n=this.datos.size();
    }       
    public void setCantidad(int x){
        this.datos.put(cantidad, x);
    }
    public int getCantidad(){
        return (int) this.datos.get(cantidad);        
    }
    public void setPrecio(Double x){
        this.datos.put(precio, x);
    }
    public Double getPrecio(){
        return (Double) this.datos.get(precio);
    }
    public void setDescuento(Double x){
        this.datos.put(descuento, x);
    }
    public Double getDescuento(){
        return (Double) this.datos.get(descuento);        
    }
    
    public void setId_articulo(String x){
        this.datos.put(id_articulo, x);
    }
    public String getId_articulo(){
        return (String) this.datos.get(id_articulo);        
    }
    public CArticulo getArticuloAll(){
        return new CArticulo().buscarid(getId_articulo());
    }
    public ArrayList<CVenta_detalle> sumar(CVenta_detalle a,CVenta_detalle b){
       ArrayList<CVenta_detalle> re=new ArrayList<>();
       if(a.getId_articulo().compareTo(b.getId_articulo())==0){
           a.setCantidad(a.getCantidad()+b.getCantidad());
           re.add(a);
       }
       return re;
    }
    public ArrayList<CVenta_detalle> sumar(ArrayList<CVenta_detalle> list,CVenta_detalle a){
       ArrayList<CVenta_detalle> re=new ArrayList<>();
       for(CVenta_detalle x:list){
           if(x.id_articulo.compareTo(a.getId_articulo())==0){
               x.setCantidad(x.getCantidad()+a.getCantidad());
               return re;
           }
       }
       list.add(a);
       return list;
    }
    public ArrayList<CVenta_detalle> sumar(ArrayList<CVenta_detalle> a,ArrayList<CVenta_detalle> b){
       ArrayList<CVenta_detalle> re=new ArrayList<>();
       for(CVenta_detalle x:a){
           b=sumar(b, x);
       }
       return b;
    }
    @Override
    public String toString() {
        String aux="";
        Iterator<String> it=this.datos.keySet().iterator();
        while(it.hasNext()){
            String s=it.next();
            aux+=s+" = "+datos.get(s)+",";
        }
        return "CArticulo{" +aux+'}';
    }
}
