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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import static modelo.Fx2.SESSION;

/**
 *
 * @author ALIM
 */
public class rep_venta_detallado {
    //ArrayList res;

    ObservableList<modeloAsignacion> data=FXCollections.observableArrayList();;
    ObservableList<modeloAsignacion> filtro=FXCollections.observableArrayList();;
    TableView tv=new TableView();
      
    TextField buscar;
    HBox hb=new HBox();
    int k;
    ArrayList <CVenta> lista_ventas;
    public void display() throws IOException {
        lista_ventas=SESSION.getVenta();
        k=lista_ventas.size()-1;
        Button b1=new Button("Siguiente");
        Button b2=new Button("Anterior");
        b1.setDisable(true);

        FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/ventas/boleta.fxml"));
        VBox c =(VBox)loader.load();
        BoletaController pc=(BoletaController)loader.getController();
        if(k>=0)
        pc.ini(lista_ventas.get(k),"VENTA");
        
        b2.setOnAction(e->{
            if(k!=0){
                k--;
                VBox cc=null;
                FXMLLoader loaderr=new FXMLLoader(getClass().getResource("/vista/ventas/boleta.fxml"));
                try {
                    cc =(VBox)loaderr.load();
                } catch (IOException ex) {
                    Logger.getLogger(rep_venta_detallado.class.getName()).log(Level.SEVERE, null, ex);
                }
                BoletaController pcc=(BoletaController)loaderr.getController();
                pcc.ini(lista_ventas.get(k),"VENTA");
                hb.getChildren().clear();
                hb.getChildren().add(cc);
            }
            if(k<=0)b2.setDisable(true);else b2.setDisable(false);
            if(k>=lista_ventas.size()-1)b1.setDisable(true);else b1.setDisable(false);
        });
        b1.setOnAction(e->{
            if(k>=0){
                k++;
                VBox cc=null;
                FXMLLoader loaderr=new FXMLLoader(getClass().getResource("/vista/ventas/boleta.fxml"));
                try {
                    cc =(VBox)loaderr.load();
                } catch (IOException ex) {
                    Logger.getLogger(rep_venta_detallado.class.getName()).log(Level.SEVERE, null, ex);
                }
                BoletaController pcc=(BoletaController)loaderr.getController();
                pcc.ini(lista_ventas.get(k),"VENTA");
                hb.getChildren().clear();
                hb.getChildren().add(cc);
            }
            if(k<=0)b2.setDisable(true);else b2.setDisable(false);
            if(k>=lista_ventas.size()-1)b1.setDisable(true);else b1.setDisable(false);
        });
        hb.getChildren().add(c);
        VBox bv=new VBox(new HBox(b1,b2),hb);
        borderPane.setCenter(bv);
    }



}
