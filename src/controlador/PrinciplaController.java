/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import clases.CArticulo;
import clases.CPersona;
import controlador.admi.SideController;
import controlador.admi.SideEmpleadoController;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import static modelo.Fx2.DATOS;
import static modelo.Fx2.USER;

/**
 * FXML Controller class
 *
 * @author WARREN
 */
public class PrinciplaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML Menu file;
            
    @FXML BorderPane borderPane;
    
    public void init(CPersona per) throws IOException{
        String warren=per.getTipo();                       
        file.setText(per.getNombre());
    
        borderPane.setLeft(new Label("no existe tipo de usuario"));        

           FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/admi/side.fxml"));
           VBox root =(VBox)loader.load();
           SideController pc=(SideController)loader.getController();        
           pc.set_border_pane(borderPane,per);
           borderPane.setLeft(root);        
                   cargar();
    }
    public void cargar(){
//            DATOS=new CArticulo().listar_con_categoria();
    }
        @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
