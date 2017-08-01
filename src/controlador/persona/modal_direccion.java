/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.persona;

import clases.CDireccion;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import recursos_sistema.label_double;
import recursos_sistema.label_int;
import recursos_sistema.label_text;

/**
 *
 * @author ALIM
 */
public class modal_direccion {
    Stage wind=new Stage();
    VBox vb=new VBox(10);  
    ObservableList<CDireccion> items =FXCollections.observableArrayList ();
    int k=-1;

    public ArrayList display(ArrayList direcciones){
        wind.initModality(Modality.APPLICATION_MODAL);
        wind.setTitle("Direcciones");

        label_text direccion=new label_text("Dirccion",150);
        label_text descripcion=new label_text("Descripcion",150);
        label_int ubigeo=new label_int("Ubigeo",150);
        label_int codigo_postal=new label_int("Coigo Postal",150);
        label_double latitud=new label_double("Latitud",150);
        label_double altitud=new label_double("Altitud",150);
      
        
    
        Button guardar=new Button("Guardar");
        Button cancelar=new Button("Cancelar");
        Button add=new Button("Agregar");        
        Button quit=new Button("Quitar");        
        
        ListView<CDireccion> list = new ListView<CDireccion>();
        HBox hb=new HBox();        
        hb.getChildren().addAll(add,quit);                
        HBox hb2=new HBox();        
        hb2.getChildren().addAll(guardar,cancelar);                
        vb.getChildren().addAll(direccion,descripcion,ubigeo,codigo_postal,latitud,altitud,hb,list,hb2);

        
        if(direcciones!=null) 
            items.setAll(direcciones);
        
        guardar.setOnAction(e->{
            wind.close();
        });
        
        cancelar.setOnAction(e->{
            items.setAll(direcciones);
            wind.close();
        });
        add.setOnAction(e->{
            CDireccion aux=new CDireccion();
            aux.setAltitud(altitud.get());
            aux.setCodigo_postal(codigo_postal.get()+"");
            aux.setDescripcion(descripcion.get());
            aux.setUbigeo(ubigeo.get()+"");
            aux.setLatitud(latitud.get());
            items.add(aux);
        });
        quit.setOnAction(e->{
            items.remove(k);
        });
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            CDireccion aux=list.getSelectionModel().getSelectedItem();
            k=list.getSelectionModel().getSelectedIndex();
            altitud.set(aux.getAltitud());
            codigo_postal.set(aux.getCodigo_postal());
            descripcion.set(aux.getDescripcion());
            ubigeo.set(aux.getUbigeo());
            latitud.set(aux.getLatitud());
        }
    });

        list.setItems(items);        
        Scene SC=new Scene(vb);
        wind.setScene(SC);
        wind.showAndWait();
        return (ArrayList) items;
    }
    /*
    public void map(){
     final MapView mapView = new MapView();

        // Setting of a ready handler to MapView object. onMapReady will be called when map initialization is done and
        // the map object is ready to use. Current implementation of onMapReady customizes the map object.
        mapView.setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {
                // Check if the map is loaded correctly
                if (status == MapStatus.MAP_STATUS_OK) {
                    // Getting the associated map object
                    final Map map = mapView.getMap();
                    // Creating a map options object
                    MapOptions options = new MapOptions();
                    // Creating a map type control options object
                    MapTypeControlOptions controlOptions = new MapTypeControlOptions();
                    // Changing position of the map type control
                    controlOptions.setPosition(ControlPosition.TOP_RIGHT);
                    // Setting map type control options
                    options.setMapTypeControlOptions(controlOptions);
                    // Setting map options
                    map.setOptions(options);
                    // Setting the map center
                    map.setCenter(new LatLng(35.91466, 10.312499));
                    // Setting initial zoom value
                    map.setZoom(2.0);
                }
            }
        });
    }*/
}
