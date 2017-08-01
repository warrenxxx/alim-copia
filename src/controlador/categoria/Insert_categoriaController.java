/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.categoria;
import clases.CCategoria;
import static controlador.admi.SideController.borderPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author WARREN
 */
public class Insert_categoriaController implements Initializable {

    /**
     * Initializes the controller class.
     */ 
    @FXML TextField nombre;
    @FXML TextField descripcion;
    @FXML TextField proveedor;
   
    public void insertar(ActionEvent e) throws IOException{
        new CCategoria(null, nombre.getText(), descripcion.getText(), proveedor.getText()).insertar();
        regresar();
    }
    public void cancelar(ActionEvent e) throws IOException{
                regresar();
    }

    public void regresar() throws IOException{
             FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/categoria/m_categoria_ini.fxml"));
           VBox root =(VBox)loader.load();
           root.autosize();
           borderPane.setCenter(root);
    }   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
