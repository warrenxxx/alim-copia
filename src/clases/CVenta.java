/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;


import bd.DVenta;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author WARREN
 */
public class CVenta extends C_Principal{
    
    private final String fecha_hora="fecha_hora";
    private final String persona_atendida="persona_atendida";
    private final String venta_detalle="venta_detalle";
    private final String tipo="tipo";
    
    public CVenta(){       
        this.datos=new HashMap();
        n=this.datos.size();
    }
    public CVenta(HashMap map){       
        this.datos=map;
        n=this.datos.size();
    }
    
    public String getid(){
        return String.valueOf( this.datos.get("id"));
    }
    public void Autoid(){
        int k=new DVenta().listar().size();
        this.datos.put("id", k);
    }
    public void setfecha_hora(Date x){
        this.datos.put(fecha_hora, x);
    }
    public Date getfecha_hora(){
        return (Date) this.datos.get(fecha_hora);        
    }
    public void setPersona_atendida(String x){
        this.datos.put(persona_atendida, x);
    }
    public String getPersona_atendida(){
        return  (String) this.datos.get(persona_atendida);        
    }
    public void setTipoCompra(){
        this.datos.put(tipo, "compra");
    }
    public void setTipoVenta(){
        this.datos.put(tipo, "venta");
    }
    public boolean is_compra(){
        Object x= this.datos.get(tipo);
        if(x==null)return false;
        if(x.toString().compareTo("compra")==0)return true;
        else return false;
    }
    public boolean is_venta(){
        Object x= this.datos.get(tipo);
        if(x==null)return true;
        if(x.toString().compareTo("compra")!=0)return true;
        else return false;
    }
       
    public void addVenta_detalle(CVenta_detalle x){

        ArrayList<HashMap> l=(ArrayList) this.datos.get(venta_detalle);
        if(l==null)l=new ArrayList<HashMap>();
        HashMap aux=x.get_datos();
        aux.put("id", l.size());
        l.add(aux);
        this.datos.put(venta_detalle, l);
    }
    public void clearVenta_detalle(){
        this.datos.put(venta_detalle, new ArrayList<HashMap>());
    }

    public ArrayList <CVenta_detalle> getVenta_detalle(){
        ArrayList<CVenta_detalle> x=new ArrayList();        
        ArrayList<HashMap> y=new ArrayList<>();
        
        if(this.datos.get(venta_detalle)!=null)
        y=( ArrayList )this.datos.get(venta_detalle);
        for(HashMap h:y){
            CVenta_detalle d=new CVenta_detalle();    
            d.set_datos(h);
            x.add((CVenta_detalle) d);
        }
        return   x;  
    }
    public ArrayList<HashMap> getVenta_detalle_hash(){
        return  (ArrayList<HashMap>) this.datos.get(venta_detalle);
    }
    public CVenta_detalle getVenta_detalle(int a){
        return   (CVenta_detalle) ( (ArrayList)this.datos.get(venta_detalle)).get(a);        
    }
    public void setVenta_detalle(CVenta_detalle x,int a){
        ArrayList l=  (ArrayList) this.datos.get(venta_detalle);
        l.set(a, x.get_datos());
        this.datos.put(venta_detalle, l);
    }
    public void setVenta_detalle(ArrayList< CVenta_detalle >x){
        this.datos.put(venta_detalle, x);
    }
    public CVenta sumar_ventas(CVenta a,CVenta b){
        ArrayList<CVenta_detalle> a1=a.getVenta_detalle(),
                                  a2=b.getVenta_detalle();
        ArrayList<CVenta_detalle>c=new CVenta_detalle().sumar(a1, a2);
        this.clearVenta_detalle();
        for(CVenta_detalle x:c){
            addVenta_detalle(x);
        }
       
        return this;
    }
    int buscar_proucto(String id,ArrayList<CVenta_detalle>v ){
        int k=0;
            for(CVenta_detalle x:v){
                if(x.getId_articulo().compareTo(id)==0){
                    return k;
                }
                k++;
            }
            return -1;
    }
    public String insertar(){
        return new DVenta().insertar(this);
    }
    public String modificar(){
        return new DVenta().modificar(this);
    }
    public String eliminar(){
        return new DVenta().eliminar(this);
    }
    
    public ArrayList<CVenta> listar(){
        return new DVenta().listar();        
    }
    public ArrayList<CVenta> listar(HashMap buscar){
        return new DVenta().listar(buscar);        
    }
    public ArrayList<CVenta> listar(String clave,String valor){
        return new DVenta().listar(clave, valor);        
    }
    
    public CVenta buscarid(String id){
        CVenta x= (CVenta) new DVenta().buscar_id(id);   
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
        return "CVenta{" +aux+'}';
    }
}
