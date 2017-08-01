/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import com.mongodb.*;

/**
 *
 * @author WARREN
 */
public class conecion {
    private String database;
    private String tabla;
    private String url;
    MongoClient mongo=null;    
    DB db;
    public conecion(){
        this.url="localhost";
        this.database="bd3";

       MongoClientURI uri = new MongoClientURI(
       "mongodb://warren_x_x_x:amirvalentino123@cluster0-shard-00-00-pvnfj.mongodb.net:27017,cluster0-shard-00-01-pvnfj.mongodb.net:27017,cluster0-shard-00-02-pvnfj.mongodb.net:27017/bd3?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin");
       mongo=new MongoClient(uri);
   
   //     mongo=new MongoClient(url,27017);
       db=mongo.getDB(database);                     
        
    }
    public void set_conecion(String tabla) {
        this.tabla=tabla;
    }
/*    
    public conecion(String database, String tabla, String url) {
        this.database = database;
        this.tabla = tabla;
        this.url = url;
    }*/
    public DBCollection get_colletion(){
        return db.getCollection(tabla);            
    }
    public int end(){
        try{
     //       if(mongo!=null)mongo.close();
        }catch(Exception e){
            return -1;
        }
        return 1;
    }
    
}
