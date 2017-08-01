/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.articulo;

import clases.CArticulo;
import clases.CAsignacion;
import controlador.ventas.modelo_venta;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import recursos_sistema.label_text;
import recursos_sistema.label_int;

/**
 *
 * @author ALIM
 */
public class modal_asignar {
    ArrayList res;

    ObservableList<modeloAsignacion> data=FXCollections.observableArrayList();;
    ObservableList<modeloAsignacion> filtro=FXCollections.observableArrayList();;

    TextField buscar;

    public ArrayList display() {
        res=new ArrayList();
        Stage wind = new Stage();
        wind.initModality(Modality.APPLICATION_MODAL);
        wind.setTitle("Elegir Productos Asignados");
        
        TableView tv=new TableView();
        
        
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
        
        TableColumn<modeloAsignacion, String> tc_can =new TableColumn<>("Cantidad");
        tc_can.setCellValueFactory(new PropertyValueFactory<modeloAsignacion, String>("cantidad"));
        tc_can.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_can.setOnEditCommit(fila -> {
            modeloAsignacion p = fila.getRowValue();
            String h=p.getNumero();
            for(modeloAsignacion x: data){
                if(x.getNumero().compareTo(h)==0){
                    x.setCantidad(fila.getNewValue());
                }
            }
        });
             
        tv.getColumns().addAll(num,nombre,codigo,tc_can);
        tv.setItems(filtro);
        tv.setEditable(true);
        
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

        Button aceptar=new Button("Aceptar");
        Button cancelar=new Button("Cancelar");
        
        aceptar.setOnAction(e->{
            for(modeloAsignacion x:data){
                if(x.getCantidad().compareTo("0")!=0){
                    res.add(new CAsignacion(x.x.getId().toString(),Integer.parseInt( x.getCantidad())));
                }
            }
            wind.close();
        });
        cancelar.setOnAction(e->{
            wind.close();
        });
        HBox hb=new HBox(aceptar,cancelar);
        VBox vb=new VBox(buscar,tv,hb);
        Scene SC = new Scene(vb);
        wind.setScene(SC);
        wind.showAndWait();
        return res;
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
}
