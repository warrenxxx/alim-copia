/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.articulo;

import clases.CArticulo;
import clases.CCategoria;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import modelo.numerico;

/**
 * FXML Controller class
 *
 * @author WARREN
 */
public class Insert_articuloController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TextField nombre,
            codigo,
            descripcion,
            unidades,
            stock,
            costo,
            preciodia,
            precionoche,
            variaciondia,
            variacionnoche;

    @FXML
    Label lpreciodia, lprecionoche, lvariaciondia, lvariacionnoche,organizacion,lasignar;
    
    @FXML
    ComboBox categoria;
    
    ArrayList new_asignar=new ArrayList();
    
    public void buscar_organizacion(ActionEvent e) throws IOException {
        organizacion.setText(modal_org.display());
    }
    public void asignar(ActionEvent e) throws IOException {
        new_asignar=new  modal_asignar().display()    ;
    }
    
    public void insertar(ActionEvent e) throws IOException {
        CArticulo y = new CArticulo();
        y.setCategoria(((CCategoria) categoria.getValue()).getId().toString());
        y.setCodigo(codigo.getText());
        y.setCosto(Double.parseDouble(costo.getText().compareTo("")==0?"0":costo.getText()));
        y.setDescripcion(descripcion.getText());
        y.setNombre(nombre.getText());
        y.setPrecio_dia(Double.parseDouble(lpreciodia.getText().compareTo("")==0?"0":lpreciodia.getText()));
        y.setPrecio_noche(Double.parseDouble(lprecionoche.getText().compareTo("")==0?"0":lprecionoche.getText()));
        y.setStock(Integer.parseInt(stock.getText().compareTo("")==0?"0":stock.getText()));
        y.setUnidades_caja(Integer.parseInt(unidades.getText().compareTo("")==0?"0":unidades.getText()));
        y.setOrganizacion(organizacion.getText());
        y.setAsignaciones(new_asignar);
        y.insertar();   
        regresar();
    }

    public void cancelar(ActionEvent e) throws IOException {
        regresar();
    }

    public void regresar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/articulo/m_articulo_ini.fxml"));
        VBox root = (VBox) loader.load();
        root.autosize();
        borderPane.setCenter(root);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        codigo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                ArrayList x=new CArticulo().listar("codigo_barras", newValue);
                if(x.size()>0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Error");
                        alert.setHeaderText("Duplicado");
                        alert.setContentText("Este producto ya existe en su inventario");
                        alert.showAndWait();
                        alert.showAndWait();
                        codigo.setText(oldValue);                        
                }                          
            }
        });


        ArrayList<CCategoria> cat = new CCategoria().listar();
        ObservableList<CCategoria> obs = FXCollections.observableArrayList(cat);
        categoria.setItems(obs);
        categoria.getSelectionModel().selectFirst();
        costo.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.compareTo("") == 0) {
                    newValue = "0";
                }
                if (new numerico().ISDOUBLE(newValue) == false) {
                    newValue = oldValue;
                }
                double cos = Double.parseDouble(newValue.compareTo("") == 0 ? "0" : newValue);
                double pred = Double.parseDouble(preciodia.getText().compareTo("") == 0 ? "0" : preciodia.getText());
                double vard = (pred - cos) * 100 / pred;

                double pren = Double.parseDouble(precionoche.getText().compareTo("") == 0 ? "0" : precionoche.getText());
                double varn = (pren - cos) * 100 / pren;

                lpreciodia.setText(pred + "");
                lvariaciondia.setText(vard + "");
                if (vard < 0) {
                    lvariaciondia.setStyle("-fx-text-fill: red");
                } else {
                    lvariaciondia.setStyle("-fx-text-fill: black");
                }

                lprecionoche.setText(pren + "");
                lvariacionnoche.setText(varn + "");
                if (vard < 0) {
                    lvariacionnoche.setStyle("-fx-text-fill: red");
                } else {
                    lvariacionnoche.setStyle("-fx-text-fill: black");
                }

            }
        });
        preciodia.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.compareTo("") == 0) {
                    newValue = "0";
                }
                if (new numerico().ISDOUBLE(newValue) == false) {
                    newValue = oldValue;
                }
                double pre = Double.parseDouble(newValue.compareTo("") == 0 ? "0" : newValue);
                double cos = Double.parseDouble(costo.getText().compareTo("") == 0 ? "0" : costo.getText());
                double gg = pre - cos;
                double var = (gg) * 100 / cos;

                lpreciodia.setText(pre + "");
                lvariaciondia.setText(var + "");
                if (var < 0) {
                    lvariaciondia.setStyle("-fx-text-fill: red");
                } else {
                    lvariaciondia.setStyle("-fx-text-fill: black");
                }

            }
        });
        variaciondia.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.compareTo("") == 0) {
                    newValue = "0";
                }
                if (new numerico().ISDOUBLE(newValue) == false) {
                    newValue = oldValue;
                }
                double var = Double.parseDouble(newValue.compareTo("") == 0 ? "0" : newValue);
                double cos = Double.parseDouble(costo.getText().compareTo("") == 0 ? "0" : costo.getText());

                double rest = cos * var / 100 + cos;
                double pre = rest;

                lpreciodia.setText(pre + "");
                lvariaciondia.setText(var + "");
                if (var < 0) {
                    lvariaciondia.setStyle("-fx-text-fill: red");
                } else {
                    lvariaciondia.setStyle("-fx-text-fill: black");
                }
            }

        });
        precionoche.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.compareTo("") == 0) {
                    newValue = "0";
                }
                if (new numerico().ISDOUBLE(newValue) == false) {
                    newValue = oldValue;
                }
                double pre = Double.parseDouble(newValue.compareTo("") == 0 ? "0" : newValue);
                double cos = Double.parseDouble(costo.getText().compareTo("") == 0 ? "0" : costo.getText());
                double gg = pre - cos;
                double var = (gg) * 100 / cos;

                lprecionoche.setText(pre + "");
                lvariacionnoche.setText(var + "");
                if (var < 0) lvariacionnoche.setStyle("-fx-text-fill: red");
                else         lvariacionnoche.setStyle("-fx-text-fill: black");
            }
        });
        variacionnoche.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.compareTo("") == 0) {
                    newValue = "0";
                }
                if (new numerico().ISDOUBLE(newValue) == false) {
                    newValue = oldValue;
                }
                double var = Double.parseDouble(newValue.compareTo("") == 0 ? "0" : newValue);
                double cos = Double.parseDouble(costo.getText().compareTo("") == 0 ? "0" : costo.getText());

                double rest = cos * var / 100 + cos;
                double pre = rest;

                lprecionoche.setText(pre + "");
                lvariacionnoche.setText(var + "");
                if (var < 0) {
                    lvariacionnoche.setStyle("-fx-text-fill: red");
                } else {
                    lvariacionnoche.setStyle("-fx-text-fill: black");
                }

            }
        });
        stock.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) 
                    stock.setText(newValue.replaceAll("[^\\d]", ""));
            }            
        });
        unidades.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    unidades.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
}
