/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import controlador.articulo.*;
import clases.CVenta;
import static controlador.admi.SideController.borderPane;
import controlador.ventas.BoletaController;
import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import static modelo.Fx2.SESSION;

/**
 *
 * @author ALIM
 */
public class rep_venta_total {
    //ArrayList res;

    ObservableList<modeloAsignacion> data=FXCollections.observableArrayList();;
    ObservableList<modeloAsignacion> filtro=FXCollections.observableArrayList();;
    TableView tv=new TableView();
      
    TextField buscar;
    HBox hb=new HBox();
    ArrayList <CVenta> lista_ventas;
    public void display() throws IOException {
        lista_ventas=SESSION.getVenta();
        CVenta x=new CVenta();
        for(CVenta venta: lista_ventas){
            x=x.sumar_ventas(venta,x);
        }
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/ventas/boleta.fxml"));
        VBox c =(VBox)loader.load();
        BoletaController pc=(BoletaController)loader.getController();
            
        pc.ini(x,"VENTA");
        
        hb.getChildren().add(c);

        borderPane.setCenter(hb);
    }



}
