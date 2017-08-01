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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class M_categoria_iniController implements Initializable {

    @FXML
    TableView tablee;
    @FXML
    Button nuevo;
    @FXML
    TextField buscar, filterField;
    @FXML
    TableColumn id, nombre, descripcion, proveedor;

    ObservableList<CCategoria> data = FXCollections.observableArrayList();
    ObservableList<CCategoria> filter = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ini();
    }

    public void insertar(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/categoria/insert_categoria.fxml"));
        VBox root = null;
        try {
            root = (VBox) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(M_categoria_iniController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderPane.setCenter(root);
    }
    public void ini() {
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        proveedor.setCellValueFactory(new PropertyValueFactory<>("proveedor"));

        tablee.setRowFactory(tv -> {
            TableRow<CCategoria> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if ((!row.isEmpty())) {
                    CCategoria rowData = row.getItem();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/categoria/edit_delete_categoria.fxml"));
                    VBox root = null;
                    try {
                        root = (VBox) loader.load();
                    } catch (IOException ex) {
                        System.err.println(ex.getMessage());
                    }
                    Edit_delete_categoriaController pc = (Edit_delete_categoriaController) loader.getController();
//                    pc.setCategoria(rowData.getNombre());
                    Scene sc = new Scene(root);
                    borderPane.setCenter(root);
                }
            });
            return row;
        });

        ArrayList<CCategoria> lista = new CCategoria().listar();
        for (CCategoria cat : lista) {
            data.add(cat);
            System.out.println(cat);
        }
        tablee.setItems(filter);
        filter.addAll(data);    
        filterField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filter.clear();
                for (CCategoria p : data) 
                    if (buscar(p)) 
                        filter.add(p);                                    
                reapplyTableSortOrder();
            }
        });
    }

    private boolean buscar(CCategoria x) {
        String filterString = filterField.getText();
        if (filterString == null || filterString.isEmpty())             return true;
        String lowerCaseFilterString = filterString.toLowerCase();
        if (x.getNombre().toLowerCase().indexOf(lowerCaseFilterString) != -1)return true;         else 
        if (x.getDescripcion().toLowerCase().indexOf(lowerCaseFilterString) != -1) return true;else
        if (x.getProveedor().toLowerCase().indexOf(lowerCaseFilterString) != -1) return true;
        
        return false; // Does not match
    }

    private void reapplyTableSortOrder() {
        ArrayList<TableColumn<CCategoria, ?>> sortOrder = new ArrayList<>(tablee.getSortOrder());
        tablee.getSortOrder().clear();
        tablee.getSortOrder().addAll(sortOrder);
    }
}
