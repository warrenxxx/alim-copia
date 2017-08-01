/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import clases.CArticulo;
import clases.CCategoria;
import controlador.articulo.*;
import clases.CVenta;
import static controlador.admi.SideController.borderPane;
import controlador.ventas.BoletaController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import static modelo.Fx2.SESSION;

/**
 *
 * @author ALIM
 */
public class stokc_por_categoria {

    ObservableList<modeloAsignacion> data=FXCollections.observableArrayList();;
    ObservableList<modeloAsignacion> filtro=FXCollections.observableArrayList();;
    TableView tv=new TableView();
      
    TextField buscar;
    HBox hb=new HBox();
    ArrayList <CVenta> lista_ventas;
    public void display() throws IOException {        
        ArrayList <CCategoria> lista=new CCategoria().listar_con_categoria_con_suma();
        Accordion acc = new Accordion();

        for(CCategoria cat:lista){
            System.out.println("//"+cat);
            if(cat==null){continue;}
            String cant=cat.getsuma()+"";
            VBox vb=new VBox();
            ArrayList<CArticulo> list_art=cat.getProductos();
             
            for(CArticulo a:list_art){
                vb.getChildren().add(new Label(a.getNombre()+" "+a.getStock()));
            }
            TitledPane tp = new TitledPane(cat.getNombre()+" "+cant,vb);            
            acc.getPanes().add(tp);
            
        }        
        hb.getChildren().add(acc);
        borderPane.setCenter(new ScrollPane (hb));
    }



}
