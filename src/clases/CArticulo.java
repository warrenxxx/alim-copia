/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;


import bd.DArticulo;
import com.mongodb.BasicDBList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.bson.types.ObjectId;

/**
 *
 * @author WARREN
 */
public class CArticulo extends C_Principal{
    private final String codigo_barras="codigo_barras";
    private final String nombre="nombre";
    private final String costo="costo";
    private final String precio_dia="precio_dia";
    private final String precio_noche="precio_noche";
    private final String descripcion="descripcion";//cajas o bolsas paquetes
    private final String unidades_caja="unidades_caja";//cajas o bolsas paquetes
    private final String stock="stock";
    private final String categoria="categoria";
    private final String organizacion="organizacion";
    private final String asignacion="asignacion";


    public CArticulo(){       
        this.datos=new HashMap();
        n=this.datos.size();
    }       
    public void setCodigo(String x){
        this.datos.put(codigo_barras, x);
    }
    public String getCodigo(){
        return this.datos.get(codigo_barras)==null?"":(String) this.datos.get(codigo_barras);        
    }

    public void setOrganizacion(String x){
        this.datos.put(organizacion, x);
    }
    public String getOrganizacion(){
        Object aux= this.datos.get(organizacion);        
        if(aux!=null)return (String)aux;
        else return "";
    }
    public ArrayList getAllOrganizacion(){
        ArrayList x=new DArticulo().getAllOrganitation();        
        return x;
    }
    public void setNombre(String x){
        this.datos.put(nombre, x);
    }
    public String getNombre(){
        
        return this.datos.get(nombre)==null?"":(String) this.datos.get(nombre);
    }    
    public void setCosto(Double x){
        this.datos.put(costo, x);
    }
    public Double getCosto(){
        
        return (Double) (this.datos.get(costo)==null?0:this.datos.get(costo));        
    }
    public void setPrecio_dia(Double x){
        this.datos.put(precio_dia, x);
    }
    public Double getPrecio_dia(){
        return (Double) this.datos.get(precio_dia);        
    }
    public void setPrecio_noche(Double x){
        this.datos.put(precio_noche, x);
    }
    public Double getPrecio_noche(){
        return (Double) this.datos.get(precio_noche);        
    }
    public void setStock(int x){
        this.datos.put(stock, x);   
    }
    public int getStock(){

        String aux=  this.datos.get(stock).toString();       
        double k=Double.parseDouble(aux);
        return (int)k;
    }      
    
    public void setDescripcion(String x){
        this.datos.put(descripcion, x);
    }
    public String getDescripString(){
        return (String) this.datos.get(descripcion);        
    }
    public void setUnidades_caja(int x){
        this.datos.put(unidades_caja, x);
    }
    public int getUnidades_caja(){
        return  (int) this.datos.get(unidades_caja);        
    }
    public void setCategoria(Object x){
//        System.out.println(x);
  
        this.datos.put(categoria, x);
    }
    public Object getCategoria(){
        Object x=this.datos.get(categoria);
        if(x.getClass()==HashMap.class){
            CCategoria y=new CCategoria();
            y.set_datos((HashMap) x);
            return y;
        }
        if(x.getClass()==BasicDBList.class){
            CCategoria y=new CCategoria();
            ArrayList h=(ArrayList) x;
            if(h.size()==0)return null;           
            y.set_datos((HashMap) h.get(0));
            return y;
        }
        return  this.datos.get(categoria)==null?"":((ObjectId) this.datos.get(categoria)).toString();       
    }

    public CCategoria getCategoriaAll(){
        return (CCategoria) new CCategoria().buscarid(this.getCategoria().toString());        
    }
    public void setAsignaciones(ArrayList<CAsignacion> x){        
        if(x==null)return;
        ArrayList <HashMap> d=new ArrayList();
        for(CAsignacion y:x){
            HashMap aux=y.get_datos();
            d.add(aux);
        }
        this.datos.put(asignacion, d);
    }

    public ArrayList getAsignaciones(){
        ArrayList<CAsignacion> x=new ArrayList();
        ArrayList<HashMap> y=( ArrayList )this.datos.get(asignacion);
        if(y==null)return x;
        for(HashMap h:y){
            CAsignacion d=new CAsignacion();
            d.set_datos(h);
            x.add( d);
        }
        return   x;  
    }
    
    public ArrayList get_stock_por_categoria(){
        ArrayList<HashMap> x=new DArticulo().listar_por_categoria();
        return x;
    }
    public ArrayList get_stock_por_categoria(String categoria){    
        return new CArticulo().listar("categoria",new ObjectId(categoria));
    }
    
    public String insertar(){
        return new DArticulo().insertar(this);
    }
    public String modificar(){
        Object x=this.datos.get("categoria");
        if(x.getClass()==HashMap.class){
            CCategoria y=new CCategoria();
            y.set_datos((HashMap) x);
            this.datos.put(categoria, y.getId());
        }
        if(x.getClass()==BasicDBList.class){
            CCategoria y=new CCategoria();
            ArrayList h=(ArrayList) x;            
            if(h.size()!=0){           
                y.set_datos((HashMap) h.get(0));
                this.datos.put(categoria, y.getId());
            }
        }
        return new DArticulo().modificar(this);
    }
    public String eliminar(){
        return new DArticulo().eliminar(this);
    }
    
    public ArrayList<CArticulo> listar(){
        return new DArticulo().listar();        
    }
    public ArrayList<CArticulo> listar_con_categoria(){
        return new DArticulo().listar_articulos_categorias();        
    }
    public ArrayList<COrganizacion> listar_por_organizacion(){
        return new DArticulo().listar_por_organizacion();        
    }
    public ArrayList<CArticulo> listar(HashMap buscar){
        return new DArticulo().listar(buscar);        
    }
    public ArrayList<CArticulo> listar(String clave,Object valor){
        return new DArticulo().listar(clave, valor);        
    }

    
    public CArticulo buscarid(String id){
        CArticulo x= (CArticulo) new DArticulo().buscar_id(id);   
        return x;
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
    
 /*   
    public String toString() {
        return getCodigo()+" "+getNombre();
    }*/
}
