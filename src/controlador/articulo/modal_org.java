/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.articulo;


import clases.CArticulo;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author ALIM
 */
public class modal_org {
    public static String display(){
        Stage wind=new Stage();
        wind.initModality(Modality.APPLICATION_MODAL);
        wind.setTitle("BUSCAR ORGANIZACION");
        
        TextField lab1=new TextField("Estandar");
        lab1.setPromptText("Digite organizacion");
        
        ListView<String> list = new ListView<String>();
        ArrayList r=new CArticulo().getAllOrganizacion();
        ObservableList<String> items =FXCollections.observableArrayList ("Estandar");
        items.addAll(r);
        Button ac=new Button("Aceptar");
        ac.setOnAction(e->{
            wind.close();
        });
        
        
         list.setOnMouseClicked(new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            lab1.setText(list.getSelectionModel().getSelectedItem());
        }
    });
        Button can=new Button("Cancelar");
        can.setOnAction(e->{
            lab1.setText("Estandar");
                    wind.close();
        });
        
        list.setItems(items);

        VBox vb=new VBox(10);
        vb.getChildren().addAll(lab1,list,ac,can);
        
        Scene SC=new Scene(vb);
        wind.setScene(SC);
        wind.showAndWait();
        return lab1.getText();
    }
}
