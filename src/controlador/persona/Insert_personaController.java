/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.persona;
import clases.CPersona;
import static controlador.admi.SideController.borderPane;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author WARREN
 */
public class Insert_personaController implements Initializable {

    /**
     * Initializes the controller class.
     */ 
    @FXML TextField nrodocumento,nombre,apellidos,sexo,email,usuario,tipo;
    @FXML ComboBox tipodocumento;
    @FXML PasswordField password;
    @FXML DatePicker fecha;
    @FXML SplitMenuButton editardireccion;
        ArrayList direcciones=null;

    public void nueva_direccion(ActionEvent e) {
        direcciones=new modal_direccion().display(null);
    }
   
    public void insertar(ActionEvent e) throws IOException{
//        new CPersona(null, nombre.getText(), descripcion.getText(), proveedor.getText()).insertar();
        CPersona x=new CPersona();
        x.setApellidos(apellidos.getText());
        x.setEmail(email.getText());
        x.setFecha_nacimiento(new Date(fecha.getValue().toEpochDay()));
        x.setNombre(nombre.getText());
        x.setPaswword(password.getText());
        x.setSexo(sexo.getText());
        x.setTipo(tipo.getText());
        x.setTipoDocumento(tipodocumento.getSelectionModel().getSelectedItem().toString());
        x.setUsuario(usuario.getText());
        x.setnroDocumento(nrodocumento.getText());
        x.insertar();
        regresar();
    }
    public void cancelar(ActionEvent e) throws IOException{
                regresar();
    }

    public void regresar() throws IOException{
           FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/persona/m_persona.fxml"));
           VBox root =(VBox)loader.load();
           root.autosize();
           borderPane.setCenter(root);
    }   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tipodocumento.getItems().addAll("DNI","RUC","PASSAPORTE");
        tipodocumento.getSelectionModel().selectFirst();
        // TODO
    }    
    
}
