/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import static bd.gg.MSG_ACEPTADO;
import clases.CSession;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import static modelo.Fx2.CONNE;
import org.bson.types.ObjectId;

/**
 *
 * @author WARREN
 */
public class DSession implements Operaciones{
    private String table="sesscion";
        conecion con=CONNE;

    String verificar(CSession x){
      //  if(new CCategoria().buscarid( x.getCategoria())==null)return "No existe Categoria";
        return MSG_ACEPTADO;
    }

    
    @Override
    public String insertar(Object o) {
        CSession x=(CSession) o;  

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
        CSession x=(CSession) o;
        con.set_conecion(table);
        BasicDBObject datos = new BasicDBObject("_id",x.getId());        
        con.get_colletion().remove(datos);
        con.end();
        return datos.getString("_id");        
    }

    @Override
    public String modificar(Object o) {
        CSession x=(CSession) o;
                String ver=verificar(x);
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
       CSession x=new CSession();
       con.set_conecion(table);            
       List<DBObject> f=   con.get_colletion().find().toArray();
        ArrayList <CSession>res=new ArrayList<>();
        for(DBObject k:f){
            CSession y=new CSession();
            y.set_datos((HashMap) k.toMap());
            res.add(y);
        }
        return res;    
    }

    @Override
    public Object buscar_id(String id_find) {
       ArrayList datos=new ArrayList();
       CSession x=new CSession();
       con.set_conecion(table);        
        System.out.println(id_find+"warrennnnn");
       BasicDBObject id= new BasicDBObject("_id",new ObjectId(id_find));       
       DBCursor cursor=con.get_colletion().find(id);
       
        try{
            while(cursor.hasNext()){      
                x=new CSession();
                x.set_datos((HashMap) cursor.next().toMap());
                datos.add(x);                                           
            }
        } finally{
            cursor.close();
        }           
                con.end();
        if(datos.size()==0)return null;
        return datos.get(0);
    }

    public ArrayList listar(String clave, String valor) {
       ArrayList datos=new ArrayList();
       CSession x=new CSession();
       con.set_conecion(table);        
       BasicDBObject id= new BasicDBObject(clave,valor);       
       List<DBObject> f=   con.get_colletion().find(id).toArray();
        ArrayList <CSession>res=new ArrayList<>();
        for(DBObject k:f){
            CSession y=new CSession();
            y.set_datos((HashMap) k.toMap());
            res.add(y);
        }
        return res;    
    }

   
    @Override
    public ArrayList listar(HashMap map) {
       ArrayList datos=new ArrayList();
       CSession x=new CSession();
       con.set_conecion(table);        
       BasicDBObject id= new BasicDBObject(map);       
       List<DBObject> f=   con.get_colletion().find(id).toArray();
        ArrayList <CSession>res=new ArrayList<>();
        for(DBObject k:f){
            CSession y=new CSession();
            y.set_datos((HashMap) k.toMap());
            res.add(y);
        }
        return res;    
    }
    public ArrayList listar_con_persona() {
        ArrayList datos = new ArrayList();
        con.set_conecion(table);
        BasicDBObject group = new BasicDBObject(
                "$lookup", new BasicDBObject().
                        append("from","persona").
                        append("localField","persona").
                        append("foreignField", "_id").
                        append("as", "persona")                        
        );
        AggregationOutput out=con.get_colletion().aggregate(group);
        for(DBObject dbObject : out.results()){
            CSession x=new CSession();
            x.set_datos((HashMap) dbObject);
            System.out.println("**"+dbObject);
            datos.add(x);
        }                
        con.end();
        return datos;
    }
    public ArrayList listar_con_persona_y_suma_de_ventas() {
        ArrayList datos = new ArrayList();
        con.set_conecion(table);
        BasicDBObject a = new BasicDBObject(
                "$group", new BasicDBObject().
                        append("_id","$_id").
                        append("fecha",new BasicDBObject("$first","$fecha")).
                        append("c",new BasicDBObject("$push","$venta")).
                        append("persona", new BasicDBObject("$first","$persona"))
        );
        
        BasicDBObject b = new BasicDBObject("$unwind","c");
        BasicDBObject c = new BasicDBObject(
                "$group", new BasicDBObject().
                        append("_id","$_id").
                        append("fecha",new BasicDBObject("$first","$fecha")).
                        append("persona",new BasicDBObject("$first","$persona")).
                        append("suma", new BasicDBObject("$sum","1"))  
        );
        BasicDBObject d = new BasicDBObject(
                "$lookup", new BasicDBObject().
                        append("from","persona").
                        append("localField","persona").
                        append("foreignField", "_id").
                        append("as", "c")                        
        );
        BasicDBObject e = new BasicDBObject( "$sort",new BasicDBObject("$fecha","1")       );
        AggregationOutput out=con.get_colletion().aggregate(a,b,b,c,d,b,e);
        for(DBObject dbObject : out.results()){
            CSession x=new CSession();
            x.set_datos((HashMap) dbObject);
            datos.add(x);
        }                
        con.end();
        return datos;
    }

    
}
