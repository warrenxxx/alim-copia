/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.admi;

import empaquetado.vista_empaquetado;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import modelo.rutas;
import reportes.rep_venta_detallado;
import reportes.rep_venta_total;

/**
 * FXML Controller class
 *
 * @author WARREN
 */
public class SideEmpleadoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public static BorderPane borderPane;

    public void ventas(ActionEvent ev) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/ventas/ventas.fxml"));
        VBox root =(VBox)loader.load();
        root.autosize();
        borderPane.setCenter(root);
    }
    public void compras(ActionEvent ev) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/compras/compras.fxml"));
        VBox root =(VBox)loader.load();
        root.autosize();
        borderPane.setCenter(root);
    }
    public void desempacar(ActionEvent ev){
        new vista_empaquetado().display() ;
//        borderPane.setCenter(root);
    }
    public void ventas_detallado(ActionEvent ev) throws IOException{
        new rep_venta_detallado().display() ;

    }
    public void ventas_total(ActionEvent ev) throws IOException{
        new rep_venta_total().display() ;

    }

    public void set_border_pane(BorderPane borderPane) {
        this.borderPane=borderPane;
    }
}
