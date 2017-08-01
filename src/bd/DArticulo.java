/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import static modelo.Fx2.CONNE;
import clases.CArticulo;
import clases.COrganizacion;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author WARREN
 */
public class DArticulo implements Operaciones {
    private String table = "ARTICULO";
    conecion con=CONNE;    

    @Override
    public String insertar(Object o) {
        CArticulo x = (CArticulo) o;
        con.set_conecion(table);
        con.set_conecion(table);
        BasicDBObject datos = new BasicDBObject(x.get_datos());
        con.get_colletion().insert(datos);
        con.end();
        return datos.getString("_id");
    }

    @Override
    public String eliminar(Object o) {
        CArticulo x = (CArticulo) o;
        con.set_conecion(table);
        BasicDBObject datos = new BasicDBObject("_id", x.getId());
        con.get_colletion().remove(datos);
        con.end();
        return datos.getString("_id");
    }

    @Override
    public String modificar(Object o) {
        CArticulo x = (CArticulo) o;
      
        con.set_conecion(table);
        BasicDBObject datos = new BasicDBObject(x.get_datos());
        con.get_colletion().update(new BasicDBObject("_id", x.getId()), datos);
        con.end();
        return x.getId().toString();
    }

    @Override
    public ArrayList listar() {
        CArticulo x = new CArticulo();
        con.set_conecion(table);
        List<DBObject> f=   con.get_colletion().find().toArray();
        ArrayList <CArticulo>res=new ArrayList<>();
        for(DBObject k:f){
            CArticulo y=new CArticulo();
            y.set_datos((HashMap) k.toMap());
            res.add(y);
        }
        return res;
    }

    @Override
    public Object buscar_id(String id_find) {
       ArrayList<CArticulo>x=listar("_id",new ObjectId( id_find));
       if(x.size()==0)return null;
       else return x.get(0);
    }

    public ArrayList listar(String clave, Object valor) {
        con.set_conecion(table);
        BasicDBObject id = new BasicDBObject(clave, valor);
        List<DBObject> f=   con.get_colletion().find(id).toArray();
        ArrayList <CArticulo>res=new ArrayList<>();
        for(DBObject k:f){
            CArticulo y=new CArticulo();
            y.set_datos((HashMap) k.toMap());
            res.add(y);
        }
        return res;
    }

    @Override
    public ArrayList listar(HashMap map) {
        con.set_conecion(table);
        BasicDBObject id = new BasicDBObject(map);
        List<DBObject> f=   con.get_colletion().find(id).toArray();
        ArrayList <CArticulo>res=new ArrayList<>();
        for(DBObject k:f){
            CArticulo y=new CArticulo();
            y.set_datos((HashMap) k.toMap());
            res.add(y);
        }
        return res;
    }
    public ArrayList getAllOrganitation() {
        ArrayList datos = new ArrayList();
        CArticulo x = new CArticulo();
        con.set_conecion(table);
        BasicDBObject id = new BasicDBObject();
        return (ArrayList) con.get_colletion().distinct("organizacion");
    }

    public ArrayList listar_por_categoria() {
        ArrayList datos = new ArrayList();
        CArticulo x = new CArticulo();
        con.set_conecion(table);

        BasicDBObject group = new BasicDBObject(
                "$group", new BasicDBObject().append(
                        "total", new BasicDBObject("$sum", "$stock")
                )
        );
        AggregationOutput out=con.get_colletion().aggregate(group);
        for(DBObject dbObject : out.results()){
            HashMap xx=new HashMap();
            xx.put("_id", dbObject.get("_id"));
            xx.put("total", dbObject.get("total"));

            datos.add(xx);
        }
                
        con.end();
        return datos;
    }
    public ArrayList listar_articulos_categorias(){
        ArrayList datos = new ArrayList();
        con.set_conecion(table);
        BasicDBObject group = new BasicDBObject(
                "$lookup", new BasicDBObject().
                        append("from","categoria").
                        append("localField","categoria").
                        append("foreignField", "_id").
                        append("as", "categoria")                        
        );
        AggregationOutput out=con.get_colletion().aggregate(group);
        for(DBObject dbObject : out.results()){
            CArticulo x=new CArticulo();
            x.set_datos((HashMap) dbObject);
            datos.add(x);
        }                
        con.end();
        return datos;
    }    
    public ArrayList listar_por_secciones(){
        ArrayList datos = new ArrayList();
        con.set_conecion(table);
        BasicDBObject group = new BasicDBObject(
                "$lookup", new BasicDBObject().
                        append("from","categoria").
                        append("localField","categoria").
                        append("foreignField", "_id").
                        append("as", "categoria")                        
        );
        AggregationOutput out=con.get_colletion().aggregate(group);
        for(DBObject dbObject : out.results()){
            CArticulo x=new CArticulo();
            x.set_datos((HashMap) dbObject);
            datos.add(x);
        }                
        con.end();
        return datos;
    }    
    public ArrayList listar_por_organizacion(){
        ArrayList datos = new ArrayList();
        con.set_conecion(table);
        BasicDBObject c1 = new BasicDBObject(
                "$group", new BasicDBObject("_id","$organizacion")
        );
        BasicDBObject c2 = new BasicDBObject(
                "$lookup", new BasicDBObject().
                        append("from","ARTICULO").
                        append("localField","_id").
                        append("foreignField", "organizacion").
                        append("as", "c")                        
        );

        BasicDBObject c3= new BasicDBObject("$unwind","$c");

        BasicDBObject group = new BasicDBObject(
                "$group", new BasicDBObject().
                        append("_id","$_id").
                        append("suma",new BasicDBObject("$sum","$c.stock")).
                        append("productos",new BasicDBObject("$push","$c"))
        );
        AggregationOutput out=con.get_colletion().aggregate(c1,c2,c3,group);
        for(DBObject dbObject : out.results()){
            COrganizacion x=new COrganizacion((HashMap) dbObject);
            datos.add(x);
        }                
        con.end();
        return datos;
    }    
}
