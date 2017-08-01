/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import bd.conecion;
import clases.CArticulo;
import clases.CCategoria;
import clases.CPersona;
import clases.CSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;
import org.bson.types.ObjectId;

/**
 *
 * @author WARREN
 */
public class Fx2 extends Application {
    public static   Stage ventana;    
    public static   CPersona USER;
    public static   CSession SESSION;
    
    
    public static final String P_ESTANDAR="58cc32706585592920bafcbb";
    public static final String RUC="1010102021";
    public static final conecion CONNE=new conecion();
    public static ArrayList<CArticulo> DATOS;
    public Parent login() throws IOException {
        Parent root1 = FXMLLoader.load(getClass().getResource("/vista/Login.fxml"));       
        TitledPane t1 = new TitledPane("Node 1", root1);        
        return root1;
    }
    @Override
    public void start(Stage stage) throws Exception {        
/*        ArrayList<CSession> x=new CSession().listar();
        for(CSession y:x){
            String h=(String) y.getPersona();
            y.setPersona(new ObjectId(h));
            y.modificar();
            System.out.println(y);
        }
  */      
        ventana=stage;
        ventana.setTitle("Tienda de Licores");
        ventana.setScene(new Scene(login()));
        ventana.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
