/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import static controlador.admi.SideController.borderPane;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

/**
 *
 * @author WARREN
 */
public class rutas {
    
    public void tocompras() throws IOException{
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/compras/compras.fxml"));
            VBox root =(VBox)loader.load();
            root.autosize();
            borderPane.setCenter(root);
    }
    public void toventas() throws IOException{
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/ventas/ventas.fxml"));
            VBox root =(VBox)loader.load();
            root.autosize();
            borderPane.setCenter(root);
    }
    public void tousuarios() throws IOException{
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/persona/m_persona.fxml"));
            VBox root =(VBox)loader.load();
            root.autosize();
            borderPane.setCenter(root);
    }
}
