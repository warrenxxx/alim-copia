/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.ventas;

import clases.CArticulo;
import clases.CCategoria;
import clases.CPersona;
import clases.CVenta;
import clases.CVenta_detalle;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static modelo.Fx2.SESSION;
import modelo.numerico; 
import modelo.rutas;

/**
 * FXML Controller class
 *
 * @author WARREN
 */
public class VentasController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML ComboBox cliente_tipo_doc;    

    @FXML
    TextField cliente_nombre,cliente_dni,cliente_apellidos,cliente_direccion,pago;
    
    @FXML
    TableColumn tpocicion,tcantidad,tcodigo,tnombre,tprecio,ttotal,tdescuento,
                pid,pcodigo,pnombre,pcategoria,pprecio,pstock;

    @FXML    TextField barras;
    
    @FXML
    Label nombre, costo, stock, total1, total2,vuelto;
    
    @FXML ToggleGroup gr1;
    @FXML
        RadioButton restandar, rcdatos;

    @FXML
        TextField filterField;
        
    @FXML
    TableView table, venta_detalle;
    
    ObservableList<modelo_venta> data_venta=FXCollections.observableArrayList();;   
    ObservableList<modelo_articulo> data=FXCollections.observableArrayList();;
    ObservableList<modelo_articulo> filter=FXCollections.observableArrayList();;
    
    CPersona clientex = new CPersona();
    ArrayList<CArticulo>lista_articulo;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lista_articulo=new CArticulo().listar_con_categoria();
        ini_cliente();
        ini_tabla_articulo();
        ini2();
    }
    
    modelo_articulo m_art;
    public void buscarp(ActionEvent ev) {                        
                modelo_articulo tmp=buscar2(barras.getText());
                    if (tmp!=null) {
                    CArticulo f = tmp.x;
                    modelo_venta e = buscar_venta(f.getCodigo());
                    if (e != null) {
                        int k = Integer.parseInt(e.getCantidad()) + 1;
                        e.setCantidad(k + "");
                    } else {
                        int k = data_venta.size() + 1;
                        int aux=Integer.parseInt(numerico.HORAACTUAL());
                        CVenta_detalle nv=new CVenta_detalle();
                        nv.setCantidad(1);
                        nv.setDescuento(0.0);
                        nv.setId_articulo(f.getId().toString());
                        nv.setPrecio(new numerico().precio_segun_hora(f));
                        e=new modelo_venta(k+"", nv);
//                        e = new modelo_venta(k + "", f.getCodigo(), f.getNombre(),new numerico().precio_segun_hora(f)+"","0");
                        data_venta.add(e);
                    }
                    venta_detalle.refresh();
                    sumar();
                    Platform.runLater(() -> { 
                        barras.clear(); 
                    }); 

                }

    }
    public void buscar_cliente(ActionEvent ev) {
        String tipo = cliente_tipo_doc.getSelectionModel().getSelectedItem().toString();
                String numro = cliente_dni.getText();
                HashMap map = new HashMap();
                map.put("tipo_documento", tipo);
                map.put("nro_documento", numro);
                ArrayList aux = (new CPersona().listar(map));
                if (aux.size() > 0) {
                    clientex = (CPersona) aux.get(0);
                    cliente_nombre.setText(clientex.getNombre());
                    cliente_apellidos.setText(clientex.getApellidos());
                }else clientex=new CPersona();    
    }
    public void registrar(ActionEvent ev) { 
        CVenta v = new CVenta();
        if (!restandar.isSelected()) {
                String tipo = cliente_tipo_doc.getSelectionModel().getSelectedItem().toString();
                clientex.setApellidos(cliente_apellidos.getText());
                clientex.setNombre(cliente_nombre.getText());
                clientex.setTipoDocumento(tipo);
                clientex.setnroDocumento(cliente_dni.getText());
                clientex.setId( clientex.insertar());                
                v.setPersona_atendida(clientex.getId().toString());
        }
        v.setfecha_hora(new Date());
        v.setTipoVenta();
        for (modelo_venta m : data_venta) {
            CVenta_detalle m1 = new CVenta_detalle();
            CArticulo art=m.y;
            m1.setId_articulo(art.getId().toString());
            m1.setCantidad(Integer.parseInt(m.getCantidad()));
            m1.setPrecio(Double.parseDouble(m.getPrecio()));
            m1.setDescuento(Double.parseDouble(m.getDescuento()));
            art.setStock(art.getStock()-m1.getCantidad());
            art.modificar();
            v.addVenta_detalle(m1);
        }
        SESSION.addVenta(v);
        SESSION.modificar();
        data_venta.clear();
        venta_detalle.refresh();
        limpiar();        
    }

    public void limpiar() {
        try {
            costo.setText("");
            nombre.setText("");
            stock.setText("");
            total1.setText("");
            total2.setText("");
            cliente_nombre.setText("");
            cliente_dni.setText("");
            cliente_apellidos.setText("");
            cliente_direccion.setText("");
            table.refresh();
            new rutas().toventas();
        } catch (IOException ex) {
            Logger.getLogger(VentasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void estado_venta(ActionEvent ev) throws IOException{
        Stage x=new Stage();
//        x.initModality(Modality.APPLICATION_MODAL);
        x.setTitle("Estado de Venta");        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/ventas/boleta.fxml"));
        VBox c =(VBox)loader.load();
        BoletaController pc=(BoletaController)loader.getController();
        
        pc.ini(clientex,data_venta);
        Scene s=new Scene(c);
        x.setScene(s);
        x.showAndWait();
    }
    
    int hhh = 0;
    double total=0;
    public void ini_cliente(){
        pago.textProperty().addListener((obserbable,oldv,newv)->{
            Pattern pat = Pattern.compile("([+-][0-9]|[0-9])[0-9]*(.[0-9]*)?");
            if(newv.compareTo("")==0)newv="0";
            Matcher mat = pat.matcher(newv);
            if (!mat.matches())   pago.setText(oldv);           
            double k2=Double.parseDouble(pago.getText().compareTo("")==0?"0":pago.getText());

            vuelto.setText((k2-total)+"");
        });
        
        cliente_tipo_doc.getItems().addAll("DNI", "RUC", "PASAPORTE");
        cliente_tipo_doc.getSelectionModel().select(0);/*
        cliente_dni.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String tipo = cliente_tipo_doc.getSelectionModel().getSelectedItem().toString();
                String numro = cliente_dni.getText();
                HashMap map = new HashMap();
                map.put("tipo_documento", tipo);
                map.put("nro_documento", numro);
                ArrayList aux = (new CPersona().listar(map));
                if (aux.size() > 0) {
                    clientex = (CPersona) aux.get(0);
                    cliente_nombre.setText(clientex.getNombre());
                    cliente_apellidos.setText(clientex.getApellidos());
                   // cliente_direccion.setText(clientex.getDireccion());
                    
                }else clientex=new CPersona();
            }
        });*/
        gr1.selectedToggleProperty().addListener((obserableValue, old_toggle, new_toggle) -> {
            if (gr1.getSelectedToggle() != null) {
                RadioButton rr = (RadioButton) gr1.getSelectedToggle();
                if (rr.getId().compareTo("rdatos") == 0) {
                    cliente_apellidos.setVisible(true);
                    cliente_dni.setVisible(true);
                    cliente_nombre.setVisible(true);
                    cliente_tipo_doc.setVisible(true);
                    cliente_direccion.setVisible(true);
                } else {
                    cliente_apellidos.setVisible(false);
                    cliente_dni.setVisible(false);
                    cliente_nombre.setVisible(false);
                    cliente_tipo_doc.setVisible(false);
                    cliente_direccion.setVisible(false);
                }
            }
        });
    }
    public void ini2() {
        
        data_venta = FXCollections.observableArrayList();
        tpocicion.setCellValueFactory(new PropertyValueFactory<>("numero"));

        TableColumn tc=new TableColumn<>();

        TableColumn<modelo_venta, String> tc_can = tcantidad;
        tc_can.setCellValueFactory(new PropertyValueFactory<modelo_venta, String>("cantidad"));
        tc_can.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_can.setOnEditCommit(data -> {
            modelo_venta p = data.getRowValue();
            if (!data.getNewValue().matches("[0-9]+(\\.[0-9][0-9])?")) {
                p.setCantidad(data.getOldValue());
            } else {
                p.setCantidad(data.getNewValue());
            }
            sumar();
            venta_detalle.refresh();
        });
        TableColumn<modelo_venta, String> tc_desc = tdescuento;
        tc_desc.setCellValueFactory(new PropertyValueFactory<modelo_venta, String>("descuento"));
        tc_desc.setCellFactory(TextFieldTableCell.forTableColumn());
        tc_desc.setOnEditCommit(data -> {
            modelo_venta p = data.getRowValue();
            if (!data.getNewValue().matches("([+-][0-9]|[0-9])[0-9]*(.[0-9]*)?")) {
                p.setDescuento(data.getOldValue());
            } else {
                p.setDescuento(data.getNewValue());
            }
            sumar();
            venta_detalle.refresh();
        });

        tcodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tnombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        tprecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        ttotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        TableColumn<modelo_venta, modelo_venta> editColumn = column("Eliminar", ReadOnlyObjectWrapper<modelo_venta>::new, 60);
        venta_detalle.getColumns().add(editColumn);

        editColumn.setCellFactory(col -> {
            final Label response = new Label();
            final ImageView imageView = new ImageView(
                    new Image("/recursos/basurero2.png")
            );
            Button editButton = new Button("", imageView);
            editButton.setStyle("-fx-base: coral;");
            editButton.setContentDisplay(ContentDisplay.LEFT);
                
            TableCell<modelo_venta, modelo_venta> cell = new TableCell<modelo_venta, modelo_venta>() {
                @Override
                public void updateItem(modelo_venta person, boolean empty) {
                    super.updateItem(person, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(editButton);
                    }
                }
            };

            editButton.setOnAction(e -> {
                data_venta.remove(cell.getItem());
                sumar();
            });

            return cell;
        });

        venta_detalle.setItems(data_venta);
        venta_detalle.setEditable(true);
    }
    private <S, T> TableColumn<S, T> column(String title, Function<S, ObservableValue<T>> property, double width) {
        TableColumn<S, T> col = new TableColumn<>(title);
        col.setCellValueFactory(cellData -> property.apply(cellData.getValue()));
        col.setPrefWidth(width);
        return col;
    }

    public void ini_tabla_articulo() {
               
        pid.setCellValueFactory(new PropertyValueFactory<>("id"));
        pcodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        pnombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        pcategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        pprecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        pstock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        
        table.setRowFactory(tv -> {
            TableRow<modelo_articulo> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if ((!row.isEmpty())) {
                    CArticulo art = row.getItem().x;
                    m_art=new modelo_articulo(art);
                    barras.setText(art.getCodigo());
                    nombre.setText(art.getNombre());
                    costo.setText(art.getCosto().toString());
                    stock.setText(String.valueOf(art.getStock()));
                    buscarp(null);                    
                }
            });
            return row;
        });
 
        
        for (CArticulo cat : lista_articulo) 
            data.add(new modelo_articulo(cat));
        
        table.setItems(filter);
        filter.addAll(data);
        filterField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filter.clear();
                for (modelo_articulo p : data) 
                    if (buscar(p)) 
                        filter.add(p);                                    
                reapplyTableSortOrder();
            }

        });
        
    }

    public void sumar() {
        double cont = 0;
        int num = 0;
        for (modelo_venta m : data_venta) {
            num++;
            double k = Double.parseDouble(m.getTotal());
            cont += k;
            m.setNumero(num + "");
        }
        total=cont;
        total2.setText("Total es : " + cont + " soles");
        total1.setText("S/." + cont + "");
    }

    private modelo_venta buscar_venta(String barras) {
        for (modelo_venta p : data_venta) {
            if (p.getCodigo().compareTo(barras) == 0) {
                return p;
            }
        }
        return null;
    }

    private boolean buscar(modelo_articulo person) {
        String filterString = filterField.getText();
        if (filterString == null || filterString.isEmpty()) {
            return true;
        }
        String lowerCaseFilterString = filterString.toLowerCase();

        if (person.getNombre().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (person.getCategoria().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (person.getCodigo().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        }

        return false; // Does not match
    }
    private modelo_articulo buscar2(String person) {


        for(modelo_articulo x:data){
            if (x.getCodigo().toLowerCase().indexOf(person) != -1)return x;
        }
        return null; // Does not match
    }

    private void reapplyTableSortOrder() {
        ArrayList<TableColumn<CCategoria, ?>> sortOrder = new ArrayList<>(table.getSortOrder());
        table.getSortOrder().clear();
        table.getSortOrder().addAll(sortOrder);
    }

}
