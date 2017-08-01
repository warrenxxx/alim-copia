/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;


import bd.DSession;
import com.mongodb.BasicDBList;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import org.bson.types.ObjectId;

/**
 *
 * @author WARREN
 */
public class CSession extends C_Principal{
    
    /*
        datos. put("tipo_documento", "");
        datos. put("nro_documento", "");
        datos. put("turnos", "");
        datos. put("apellidos", "");
        datos. put("fecha_nacimiento", "");
        datos. put("sexo", "");
        datos. put("email", "");
        datos. put("id_usuario", "");
        datos. put("id_entidad", "");
    */
    private final String fecha="fecha";
    private final String turno="turno";
    private final String persona="persona";
    private final String venta="venta";

    
    public CSession(ObjectId id,Date fecha,String turno, String persona) {
        datos=new HashMap();
        setFecha(fecha);
        setTurno(turno);
        setPersona(persona);
        n=this.datos.size();
    } 
    public CSession(){       
        this.datos=new HashMap();
        n=this.datos.size();
    }       
    public void setFecha(Date x){
        this.datos.put(fecha, x);
    }
    public Date getFecha(){
        return (Date) this.datos.get(fecha);        
    }
    public void setTurno(String x){
        this.datos.put(turno, x);
    }
    public String getTurno(){
        return (String) this.datos.get(turno);
    }    
    public void setPersona(Object x){
        this.datos.put(persona, x);
    }
    public Object getPersona(){
        Object x=this.datos.get(persona);
        if(x.getClass()==HashMap.class){
            CPersona y=new CPersona();
            y.set_datos((HashMap) x);
            return y;
        }
        if(x.getClass()==BasicDBList.class){
            CPersona y=new CPersona();
            ArrayList h=(ArrayList) x;
            if(h.size()==0)return null;           
            y.set_datos((HashMap) h.get(0));
            return y;
        }
        return  x==null?"":((ObjectId)x);        
    }

    
    public void addVenta(CVenta x){
        ArrayList<HashMap> l=(ArrayList) this.datos.get(venta);
        if(l==null)l=new ArrayList<HashMap>();
        HashMap aux=x.get_datos();
        aux.put("id", l.size());
        int k=l.size();
        l.add(aux);
        this.datos.put(venta, l);
    }

    public ArrayList <CVenta> getVenta(){
        ArrayList<CVenta> x=new ArrayList();
        ArrayList<HashMap> y=new ArrayList<>();
        if(this.datos.get(venta)!=null)
            y=( ArrayList )this.datos.get(venta);
        for(HashMap h:y){
            CVenta d=new CVenta();    
            d.set_datos(h);
            if(d.is_venta())
                x.add((CVenta) d);
        }
        return   x;  
    }
    public ArrayList <CVenta> getCompra(){
        ArrayList<CVenta> x=new ArrayList();
        ArrayList<HashMap> y=new ArrayList<>();
        if(this.datos.get(venta)!=null)
            y=( ArrayList )this.datos.get(venta);
        for(HashMap h:y){
            CVenta d=new CVenta();    
            d.set_datos(h);
            if(d.is_compra())
                x.add((CVenta) d);
        }
        return   x;  
    }

    public ArrayList <CVenta> getVenta_entre_fechas(){
        ArrayList<CVenta> x=new ArrayList();
        ArrayList<HashMap> y=new ArrayList<>();
        if(this.datos.get(venta)!=null)
            y=( ArrayList )this.datos.get(venta);
        for(HashMap h:y){
            CVenta d=new CVenta();    
            d.set_datos(h);
            x.add((CVenta) d);
        }
        return   x;  
    }
    public CVenta getVenta(int a){
        return   (CVenta) ( (ArrayList)this.datos.get(venta)).get(a);        
    }
    public void setVenta(CVenta x,int a){
        ArrayList l=  (ArrayList) this.datos.get(venta);
        l.set(a, x.get_datos());
        this.datos.put(venta, l);
    }
    
    public String insertar(){
        return new DSession().insertar(this);
    }
    public String modificar(){
        return new DSession().modificar(this);
    }
    public String eliminar(){
        return new DSession().eliminar(this);
    }
    
    public ArrayList<CSession> listar(){
        return new DSession().listar();        
    }

    public ArrayList<CSession> listar_con_persona(){
        return new DSession().listar_con_persona();        
    }
    public ArrayList<CSession> listar_con_persona_y_sumaVentas(){
            return new DSession().listar_con_persona();        
    }
    public ArrayList<CSession> listar(HashMap buscar){
        return new DSession().listar(buscar);        
    }
    public ArrayList<CSession> listar(String clave,String valor){
        return new DSession().listar(clave, valor);        
    }
    
    public CSession buscarid(String id){
        CSession x= (CSession) new DSession().buscar_id(id);   
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
        return "CSession{" +aux+'}';
    }
}
