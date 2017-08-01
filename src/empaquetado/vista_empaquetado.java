/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empaquetado;

import controlador.articulo.*;
import clases.CArticulo;
import clases.CAsignacion;
import static controlador.admi.SideController.borderPane;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author ALIM
 */
public class vista_empaquetado {
    //ArrayList res;

    ObservableList<modeloAsignacion> data=FXCollections.observableArrayList();;
    ObservableList<modeloAsignacion> filtro=FXCollections.observableArrayList();;
    TableView tv=new TableView();
      
    TextField buscar;
    VBox vb=new VBox();

    public void display() {
//        res=new ArrayList();
 //       Stage wind = new Stage();
  //      wind.initModality(Modality.APPLICATION_MODAL);
   //     wind.setTitle("Elegir Productos Asignados");
        table_articulo();
             
        buscar =new TextField();
        buscar.setPromptText("Buscar");
        
        buscar.textProperty().addListener((obserbable,m,n)->{
            n=n.toLowerCase();
            filtro.clear();
            for(modeloAsignacion x:data){
                if(buscar_modelo(x, n)){
                    filtro.add(x);
                }
            }
        });        

        tv.setPrefWidth(400);
        HBox hb1=new HBox(new VBox(buscar,tv),vb);
 
//        Scene SC = new Scene(vb1);
        borderPane.setCenter(hb1);
//        wind.setScene(SC);
//        wind.showAndWait();
    }


    private boolean buscar_modelo(modeloAsignacion asignacion,String filterString) {
        if (filterString == null || filterString.isEmpty()) 
            return true;
        
        String lowerCaseFilterString = filterString.toLowerCase();

        if (asignacion.getNombre().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (asignacion.getCodigo().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        }

        return false; // Does not match
    }
    void table_articulo(){
            
        ArrayList<CArticulo> aux = new CArticulo().listar();
             int k=0;
        for(CArticulo x:aux){            
            data.add(new modeloAsignacion((k++)+"", x));            
        }        
        filtro.addAll(data);    
        
        TableColumn num = new TableColumn("#");
        num.setCellValueFactory(new PropertyValueFactory<>("numero"));

        TableColumn codigo = new TableColumn("CODIGO");
        codigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));

        TableColumn nombre = new TableColumn("NOMBRE");
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        

        tv.setRowFactory(tv -> {
            TableRow<modeloAsignacion> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if ((!row.isEmpty())) {
                    CArticulo art = row.getItem().x;
                    ArrayList <CAsignacion>d=art.getAsignaciones();
                    vb.getChildren().clear();
                    for(CAsignacion asig: d){
                        item_paquetado ipaq=new item_paquetado(art,asig);
                        vb.getChildren().add(ipaq);
                    }
                }
            });
            return row;
        });
        tv.getColumns().addAll(num,nombre,codigo);
        tv.setItems(filtro);
        tv.setEditable(true);
    }
}
