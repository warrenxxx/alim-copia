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
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author WARREN
 */
public class M_personaController implements Initializable {

    @FXML
    TableView tablee;
    @FXML
    Button nuevo;
    @FXML
    TextField buscar, filterField;
    @FXML
    TableColumn id, nombre,tipodocumento,nrodocumento,apellidos,fecha,sexo,email,usuario,tipo;

    ObservableList<modeloPersona> data = FXCollections.observableArrayList();
    ObservableList<modeloPersona> filter = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ini();
    }

    public void insertar(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/persona/insert_persona.fxml"));
        VBox root = null;
        try {
            root = (VBox) loader.load();
        } catch (IOException ex) {
                    System.out.println(ex.getMessage());
        }
        borderPane.setCenter(root);
    }
    public void ini() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tipodocumento.setCellValueFactory(new PropertyValueFactory<>("tipo_documento"));
        nrodocumento.setCellValueFactory(new PropertyValueFactory<>("nro_documento"));
        apellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        fecha.setCellValueFactory(new PropertyValueFactory<>("fecha_nacimeinto"));
        sexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        tipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        
        tablee.setRowFactory(tv -> {
            TableRow<modeloPersona> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if ((!row.isEmpty())) {
                    modeloPersona rowData = row.getItem();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/persona/edit_delete_persona.fxml"));
                    VBox root = null;
                    try {
                        root = (VBox) loader.load();
                    } catch (IOException ex) {
                        System.err.println(ex.getMessage());
                    }
                    Edit_delete_personaController pc = (Edit_delete_personaController) loader.getController();
                    pc.setPersona(rowData.x);
                    Scene sc = new Scene(root);
                    borderPane.setCenter(root);
                }
            });
            return row;
        });

        ArrayList<CPersona> lista = new CPersona().listar();
        for (CPersona cat : lista) {
            data.add(new modeloPersona(cat));
            System.out.println(cat);
        }
        tablee.setItems(filter);
        filter.addAll(data);    
        filterField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filter.clear();
                for (modeloPersona p : data) 
                    if (buscar(p)) 
                        filter.add(p);                                    
                reapplyTableSortOrder();
            }
        });
    }

    private boolean buscar(modeloPersona x) {
        String filterString = filterField.getText();
        if (filterString == null || filterString.isEmpty())             return true;
        String lowerCaseFilterString = filterString.toLowerCase();
        if (x.getNombre().toLowerCase().indexOf(lowerCaseFilterString) != -1)return true;             
        return false; // Does not match
    }

    private void reapplyTableSortOrder() {
        ArrayList<TableColumn<CPersona, ?>> sortOrder = new ArrayList<>(tablee.getSortOrder());
        tablee.getSortOrder().clear();
        tablee.getSortOrder().addAll(sortOrder);
    }
}
