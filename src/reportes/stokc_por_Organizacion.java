/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import clases.CArticulo;
import clases.CCategoria;
import clases.COrganizacion;
import controlador.articulo.*;
import clases.CVenta;
import static controlador.admi.SideController.borderPane;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author ALIM
 */
public class stokc_por_Organizacion {

    ObservableList<modeloAsignacion> data=FXCollections.observableArrayList();;
    ObservableList<modeloAsignacion> filtro=FXCollections.observableArrayList();;
    TableView tv=new TableView();
      
    TextField buscar;
    HBox hb=new HBox();
    ArrayList <CVenta> lista_ventas;
    public void display() throws IOException {        
        ArrayList <COrganizacion> lista=new CArticulo().listar_por_organizacion();
        Accordion acc = new Accordion();

        for(COrganizacion cat:lista){
            if(cat==null){continue;}
            String cant=cat.getSuma()+"";
            VBox vb=new VBox();
            ArrayList<CArticulo> list_art=cat.productos();
             
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
