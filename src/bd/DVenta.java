/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import static bd.gg.MSG_ACEPTADO;
import clases.CVenta;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static modelo.Fx2.CONNE;
import org.bson.types.ObjectId;

/**
 *
 * @author WARREN
 */
public class DVenta implements Operaciones{
    private String table="venta";
        conecion con=CONNE;

    String verificar(CVenta x){
//        if(x.getVenta()==null)return "No existe Venta";
        return MSG_ACEPTADO;
    }

    
    @Override
    public String insertar(Object o) {
        CVenta x=(CVenta) o;  

        String ver=verificar(x);
        if(ver.compareTo(MSG_ACEPTADO)!=0)return ver;

        con.set_conecion(table);
        BasicDBObject datos = new BasicDBObject(x.get_datos());        
        con.get_colletion().insert(datos);
        con.end();
        return datos.getString("_id");
    }

    @Override
    public String eliminar(Object o) {
        CVenta x=(CVenta) o;
        con.set_conecion(table);
        BasicDBObject datos = new BasicDBObject("_id",x.getId());        
        con.get_colletion().remove(datos);
        con.end();
        return datos.getString("_id");        
    }

    @Override
    public String modificar(Object o) {
        CVenta x=(CVenta) o;
                String ver= verificar(x);
        if(ver.compareTo(MSG_ACEPTADO)!=0)return ver;

        con.set_conecion(table);
        BasicDBObject datos = new BasicDBObject(x.get_datos());        
        con.get_colletion().update(new BasicDBObject("_id",x.getId()),datos);
        con.end();
        return x.getId().toString() ;    
    }

    @Override
    public ArrayList listar() {
       ArrayList datos=new ArrayList();
       CVenta x=new CVenta();
       con.set_conecion(table);        
       List<DBObject> f=   con.get_colletion().find().toArray();
        ArrayList <CVenta>res=new ArrayList<>();
        for(DBObject k:f){
            CVenta y=new CVenta();
            y.set_datos((HashMap) k.toMap());
            res.add(y);
        }
        return res;    
    }

    @Override
    public Object buscar_id(String id_find) {
       ArrayList datos=new ArrayList();
       CVenta x=new CVenta();
       con.set_conecion(table);        
       BasicDBObject id= new BasicDBObject("_id",new ObjectId(id_find));       
       List<DBObject> f=   con.get_colletion().find(id).toArray();
        ArrayList <CVenta>res=new ArrayList<>();
        for(DBObject k:f){
            CVenta y=new CVenta();
            y.set_datos((HashMap) k.toMap());
            res.add(y);
        }
        return res;    
    }

    public ArrayList listar(String clave, String valor) {
       ArrayList datos=new ArrayList();
       CVenta x=new CVenta();
       con.set_conecion(table);        
       BasicDBObject id= new BasicDBObject(clave,valor);       
       List<DBObject> f=   con.get_colletion().find(id).toArray();
        ArrayList <CVenta>res=new ArrayList<>();
        for(DBObject k:f){
            CVenta y=new CVenta();
            y.set_datos((HashMap) k.toMap());
            res.add(y);
        }
        return res;    
    }

    @Override
    public ArrayList listar(HashMap map) {
       ArrayList datos=new ArrayList();
       CVenta x=new CVenta();
       con.set_conecion(table);        
       BasicDBObject id= new BasicDBObject(map);       
       List<DBObject> f=   con.get_colletion().find(id).toArray();
        ArrayList <CVenta>res=new ArrayList<>();
        for(DBObject k:f){
            CVenta y=new CVenta();
            y.set_datos((HashMap) k.toMap());
            res.add(y);
        }
        return res;    
    }
    
}
