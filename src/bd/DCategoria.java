/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import static bd.gg.MSG_ACEPTADO;
import clases.CCategoria;
import com.mongodb.AggregationOutput;
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
public class DCategoria implements Operaciones{
    private String table="categoria";
        conecion con=CONNE;

    String verificar(CCategoria x){
//        if(x.getCategoria()==null)return "No existe Categoria";
        return MSG_ACEPTADO;
    }

    
    @Override
    public String insertar(Object o) {
        CCategoria x=(CCategoria) o;  

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
        CCategoria x=(CCategoria) o;
        con.set_conecion(table);
        BasicDBObject datos = new BasicDBObject("_id",x.getId());        
        con.get_colletion().remove(datos);
        con.end();
        return datos.getString("_id");        
    }

    @Override
    public String modificar(Object o) {
        CCategoria x=(CCategoria) o;
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
       CCategoria x=new CCategoria();
       con.set_conecion(table);               
       List<DBObject> f=   con.get_colletion().find().toArray();
        ArrayList <CCategoria>res=new ArrayList<>();
        for(DBObject k:f){
            CCategoria y=new CCategoria();
            y.set_datos((HashMap) k.toMap());
            res.add(y);
        }
        return res;    }

    @Override
    public Object buscar_id(String id_find) {
       ArrayList<CCategoria>x=listar("_id",new ObjectId( id_find));
       if(x.size()==0)return null;
       else return x.get(0);

    }

    public ArrayList listar(String clave, Object valor) {
       ArrayList datos=new ArrayList();
       CCategoria x=new CCategoria();
       con.set_conecion(table);        
       BasicDBObject id= new BasicDBObject(clave,valor);       
       List<DBObject> f=   con.get_colletion().find(id).toArray();
        ArrayList <CCategoria>res=new ArrayList<>();
        for(DBObject k:f){
            CCategoria y=new CCategoria();
            y.set_datos((HashMap) k.toMap());
            res.add(y);
        }
        return res;    

    }

    @Override
    public ArrayList listar(HashMap map) {
       ArrayList datos=new ArrayList();
       CCategoria x=new CCategoria();
       con.set_conecion(table);        
       BasicDBObject id= new BasicDBObject(map);       
       List<DBObject> f=   con.get_colletion().find(id).toArray();
        ArrayList <CCategoria>res=new ArrayList<>();
        for(DBObject k:f){
            CCategoria y=new CCategoria();
            y.set_datos((HashMap) k.toMap());
            res.add(y);
        }
        return res;    
    }
     public ArrayList listar_articulos_categorias_con_sumas(){
        ArrayList datos = new ArrayList();
        con.set_conecion(table);
        BasicDBObject group = new BasicDBObject().append(
                        "$lookup", new BasicDBObject().
                            append("from","ARTICULO").
                            append("localField","_id").
                            append("foreignField", "categoria").
                            append("as", "suma")
        );
        BasicDBObject undw = new BasicDBObject("$unwind","$suma");
        BasicDBObject group2 = new BasicDBObject().append(
                        "$group",new BasicDBObject().
                                   append("_id", "$_id").
                                   append("nombre", new BasicDBObject("$first","$nombre")).
                                   append("suma",new BasicDBObject("$sum","$suma.stock")).
                                   append("productos", new BasicDBObject("$push","$suma"))        );
        
        AggregationOutput out=con.get_colletion().aggregate(group,undw,undw,group2);
        for(DBObject dbObject : out.results()){
            CCategoria x=new CCategoria();
            x.set_datos((HashMap) dbObject);
            datos.add(x);
        }                
        con.end();
        return datos;
    }       }
