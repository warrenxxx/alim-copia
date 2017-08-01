/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import clases.CSession;
import clases.CVenta;
import static controlador.admi.SideController.borderPane;
import controlador.ventas.BoletaController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import static modelo.Fx2.SESSION;

/**
 *
 * @author ALIM
 */
public class rep_sesscion {
    //ArrayList res;

    ObservableList<modeloSeccion> dataSeccion = FXCollections.observableArrayList();
    
    ObservableList<modeloSeccion> filtro = FXCollections.observableArrayList();
    
    TableView tv = new TableView();

    TextField buscar;
    HBox hb = new HBox();
    HBox hb2 = new HBox();
    VBox a = new VBox();
    VBox b = new VBox();
    VBox c = new VBox();

    ArrayList<CVenta> lista_ventas;
    ArrayList<CSession> lista_session;

    public void display() throws IOException {
        lista_session = new CSession().listar_con_persona();
        lista_ventas = SESSION.getVenta();

        for (CSession x : lista_session) {
            dataSeccion.add(new modeloSeccion(x));
        }
        filtro.addAll(dataSeccion);

        TableColumn codigo = new TableColumn("FECHA");
        codigo.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        TableColumn nombre = new TableColumn("NOMBRE");
        nombre.setCellValueFactory(new PropertyValueFactory<>("persona"));

        TableColumn<modeloSeccion, modeloSeccion> editColumn = column("Operacion", ReadOnlyObjectWrapper<modeloSeccion>::new, 60);
        editColumn.setCellFactory(col -> {
            Button editButton = new Button("Ventas");
            editButton.setStyle("-fx-base: coral;");
            editButton.setContentDisplay(ContentDisplay.LEFT);
                
            TableCell<modeloSeccion, modeloSeccion> cell = new TableCell<modeloSeccion, modeloSeccion>() {
                @Override
                public void updateItem(modeloSeccion person, boolean empty) {
                    super.updateItem(person, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(editButton);
                    }
                }
            };

            editButton.setOnAction(e -> {
                    modeloSeccion rowData=cell.getItem();
                    lista_ventas = rowData.sesscion.getVenta();
                    CVenta x = new CVenta();
                     for (CVenta venta : lista_ventas) 
                        x = x.sumar_ventas(venta, x);                    
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/ventas/boleta.fxml"));
                    try {
                        c = (VBox) loader.load();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    BoletaController pc = (BoletaController) loader.getController();                   
                    pc.ini(x,"VENTA");                               
                    hb2.getChildren().clear();
                    hb2.getChildren().add(c);
            });
            return cell;
        });
        TableColumn<modeloSeccion, modeloSeccion> btnCompras = column("Operacion", ReadOnlyObjectWrapper<modeloSeccion>::new, 60);
        btnCompras.setCellFactory(col -> {
            Button editButton = new Button("Compras");
            editButton.setStyle("-fx-base: coral;");
            editButton.setContentDisplay(ContentDisplay.LEFT);
                
            TableCell<modeloSeccion, modeloSeccion> cell = new TableCell<modeloSeccion, modeloSeccion>() {
                @Override
                public void updateItem(modeloSeccion person, boolean empty) {
                    super.updateItem(person, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(editButton);
                    }
                }
            };

            editButton.setOnAction(e -> {
                    modeloSeccion rowData=cell.getItem();
                    lista_ventas = rowData.sesscion.getCompra();
                    CVenta x = new CVenta();
                     for (CVenta venta : lista_ventas) 
                        x = x.sumar_ventas(venta, x);                    
                    
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/ventas/boleta.fxml"));
                    try {
                        c = (VBox) loader.load();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                    BoletaController pc = (BoletaController) loader.getController();                   
                    pc.ini(x,"COMPRA");                               
                    hb2.getChildren().clear();
                    hb2.getChildren().add(c);
            });
            return cell;
        });

        tv.getColumns().addAll(codigo, nombre,editColumn,btnCompras);
        tv.setItems(filtro);
        tv.setPrefWidth(440);
        buscar = new TextField();
        buscar.setPromptText("Buscar");

        buscar.textProperty().addListener((obserbable, m, n) -> {
            n = n.toLowerCase();
            filtro.clear();
            for (modeloSeccion x : dataSeccion) {
                if (buscar_modelo(x, n)) {
                    filtro.add(x);
                }
            }
        });

        a.getChildren().addAll(buscar, tv);
        hb.getChildren().addAll(a, hb2);

        borderPane.setCenter(hb);
    }

    private boolean buscar_modelo(modeloSeccion sesccion, String filterString) {
        if (filterString == null || filterString.isEmpty()) {
            return true;
        }

        String lowerCaseFilterString = filterString.toLowerCase();

        if (sesccion.getPersona().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (sesccion.getFecha().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        }

        return false; // Does not match
    }
    
    private <S, T> TableColumn<S, T> column(String title, Function<S, ObservableValue<T>> property, double width) {
        TableColumn<S, T> col = new TableColumn<>(title);
        col.setCellValueFactory(cellData -> property.apply(cellData.getValue()));
        col.setPrefWidth(width);
        return col;
    }
}
