/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.persona;

import clases.CPersona;
import controlador.PrinciplaController;
import static controlador.admi.SideController.borderPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import static modelo.Fx2.USER;
import static modelo.Fx2.ventana;
import org.bson.types.ObjectId;

/**
 * FXML Controller class
 *
 * @author WARREN
 */
public class Edit_delete_personaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    CPersona x;
    @FXML TextField id;
    @FXML TextField nombre;
    @FXML TextField descripcion;
    @FXML TextField proveedor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setPersona(CPersona x){
        this.x=x;
        id.setText(x.getId().toString());
        nombre.setText(x.getNombre());
//        descripcion.setText(x.getDescripcion());
 //       proveedor.setText(x.getProveedor());
        
    }
    public void eliminar(ActionEvent e) throws IOException{
        x.eliminar();
        regresar();
    }
    
    public void editar(ActionEvent e) throws IOException{
   //     new CPersona(new ObjectId(id.getText()), nombre.getText(), descripcion.getText(), proveedor.getText()).eliminar();
        regresar();
    }
    public void cancelar(ActionEvent e) throws IOException{
        regresar();
    }
    public void regresar() throws IOException{
             FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/persona/m_persona_ini.fxml"));
           VBox root =(VBox)loader.load();
           root.autosize();
           borderPane.setCenter(root);
    }
}
