/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import clases.CPersona;
import clases.CSession;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import static modelo.Fx2.SESSION;
import static modelo.Fx2.USER;
import static modelo.Fx2.ventana;

/**
 * FXML Controller class
 *
 * @author WARREN
 */
public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML TextField usuario;
    @FXML TextField password;
    @FXML
    private void entrar(ActionEvent ev) throws IOException{
       HashMap d=new HashMap();
       d.put("usuario", usuario.getText());
       d.put("password", password.getText());
       ArrayList usu=new CPersona().listar(d);
       if(usu.size()==0)return;
       
       CPersona per=(CPersona) usu.get(0);
       USER= per;

       SESSION=new CSession();
       SESSION.setFecha(new Date());
       SESSION.setPersona(per.getId());
       SESSION.setId(    SESSION.insertar());

       FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/Principla.fxml"));
       BorderPane root =(BorderPane)loader.load();       
       PrinciplaController pc=(PrinciplaController)loader.getController();        
       pc.init(per);
       Scene sc=new Scene(root);

       ventana.setScene(sc);
       ventana.setMaximized(true);       
//       ventana.setFullScreen(true);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(usuario.getText());
    }    
    
}
