/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.compras;

import controlador.ventas.*;
import bd.DPersona;
import bd.DVenta;
import clases.CPersona;
import clases.CVenta;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import static modelo.Fx2.USER;

/**
 * FXML Controller class
 *
 * @author WARREN
 */
public class BoletaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML Label ruc,direccion,cliente,total,boleta,mes,dia,año;
    @FXML TableColumn tcodigo,tpocicion,tcantidad,tnombre,ttotal,tprecio;
    @FXML TableView venta_detalle;
    String ini_boleta="0001";
    String ini_direccion="Direccion: ";
    String ini_nombre="Señor(es):  ";

    CVenta venta=new CVenta();
    CPersona clientex;
    
    public void ini(CPersona clientex,ObservableList<modelo_venta> data) {
        tpocicion.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tcodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tnombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        ttotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        tprecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        
        venta.Autoid();
        venta_detalle.setItems(data);
        venta_detalle.refresh();
        if(clientex.getId()!=null)
        venta.setPersona_atendida(clientex.getId().toString());
        venta.setfecha_hora(new Date());
        if(clientex.getId()!=null)        
        clientex=(CPersona) new DPersona().buscar_id(clientex.getId().toString());
        boleta.setText(ini_boleta+"-"+venta.getid().toString());
        long time=venta.getfecha_hora().getTime();
        Date fecha=new Date(time);
                if(clientex.getId()!=null)      {
        direccion.setText(ini_direccion+clientex.getDireccion());
        cliente.setText(ini_nombre+clientex.getNombre()+" "+clientex.getApellidos());
                }
        Calendar cal=Calendar.getInstance();
        cal.setTimeInMillis(time);
        
        dia.setText(cal.get(Calendar.DAY_OF_MONTH)+"");
        mes.setText((cal.get(Calendar.MONTH)+1)+"");
        año.setText(cal.get(Calendar.YEAR)+"");
        total(data);
    }
    void total(ObservableList<modelo_venta> data){
        double cant=0;
        for(modelo_venta p:data){
            cant+=Double.parseDouble(p.getTotal());
        }
        total.setText(cant+"");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
