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
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author WARREN
 */
public class M_articulo_iniController implements Initializable {

    @FXML  TableView table;
    @FXML  Button nuevo;
    @FXML  TextField buscar;
    @FXML  TextField filterField;
    @FXML TableColumn tid,tcodigo,tnombre,tdescripcion,tcosto,tpreciodia,tprecionoche,tstock,tunidades;
    ObservableList<modeloArticulo> data=FXCollections.observableArrayList();
    ObservableList<modeloArticulo> filter=FXCollections.observableArrayList();;
    private final ObservableList<modeloCategoria> categoriaData
            = FXCollections.observableArrayList();
    ArrayList <CCategoria>cat;
    ArrayList <CArticulo>lista_articulos;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ini();
        
    }

    public void insertar(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/articulo/insert_articulo.fxml"));
        VBox root = null;
        try {
            root = (VBox) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(M_articulo_iniController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderPane.setCenter(root);

    }
    public void llenar_datos(){
       cat=new CCategoria().listar();
       for(CCategoria x:cat){
           categoriaData.add(new modeloCategoria(x));
       }
       lista_articulos=new CArticulo().listar_con_categoria();
    }
    public void ini() {           
        llenar_datos();
        tnombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        tid.setCellValueFactory(new PropertyValueFactory<>("id"));
        tdescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tcosto.setCellValueFactory(new PropertyValueFactory<>("costo"));
        tpreciodia.setCellValueFactory(new PropertyValueFactory<>("preciodia"));
        tprecionoche.setCellValueFactory(new PropertyValueFactory<>("precionoche"));
        tstock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tunidades.setCellValueFactory(new PropertyValueFactory<>("unidades"));
       
        
        
        TableColumn<modeloArticulo, modeloArticulo> editColumn = column("Asignar", ReadOnlyObjectWrapper<modeloArticulo>::new, 75);

        editColumn.setCellFactory(col -> {
            Button editButton = new Button("Asignar");
                
            TableCell<modeloArticulo, modeloArticulo> cell = new TableCell<modeloArticulo, modeloArticulo>() {
                @Override
                public void updateItem(modeloArticulo person, boolean empty) {
                    super.updateItem(person, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(editButton);
                        editButton.setText(person.articulo.getOrganizacion());
                    }
                }
            };
//            editButton.setText(cell.getItem().articulo.getOrganizacion());
            editButton.setOnAction(e -> {
                String h=modal_org.display(); 
                modeloArticulo tmp= cell.getItem();
                tmp.articulo.setOrganizacion(h);
                tmp.articulo.modificar();
                editButton.setText(h);
            });

            return cell;
        });

        
        Callback<TableColumn<modeloArticulo, modeloCategoria>, TableCell<modeloArticulo, modeloCategoria>> comboBoxCellFactory
                = (TableColumn<modeloArticulo, modeloCategoria> param) -> new ComboBoxEditingCell(cat);
                
        TableColumn<modeloArticulo, modeloCategoria> lastNameCol = new TableColumn("Categoria");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(cellData -> cellData.getValue().typObjProperty());
        lastNameCol.setCellFactory(comboBoxCellFactory);
        lastNameCol.setOnEditCommit(
                (TableColumn.CellEditEvent<modeloArticulo, modeloCategoria> t) -> {
                    CArticulo x=new CArticulo();
                    x.set_datos(t.getRowValue().articulo.get_datos());
                    x.setCategoria(t.getNewValue().categoria.getId());
                    x.modificar();
                    ((modeloArticulo) t.getTableView().getItems()
                    .get(t.getTablePosition().getRow()))
                    .setTypObj(t.getNewValue());
                }); 
        table.getColumns().add(lastNameCol);
        table.getColumns().add(editColumn);

        
        TableColumn<modeloArticulo, String> ttstock = tstock;
        ttstock.setCellFactory(TextFieldTableCell.forTableColumn());
        ttstock.setOnEditCommit(data -> {
            modeloArticulo p = data.getRowValue();            
            Pattern pat = Pattern.compile("([+-][0-9]|[0-9])[0-9]*");
            Matcher mat = pat.matcher(data.getNewValue());
            if (!mat.matches()){ p.setStock(data.getOldValue()); table.refresh();}
            else{ p.setStock(data.getNewValue());CArticulo art=p.articulo; art.modificar();}

        });
        TableColumn<modeloArticulo, String> ttunidades = tunidades;
        ttunidades.setCellFactory(TextFieldTableCell.forTableColumn());
        ttunidades.setOnEditCommit(data -> {
            modeloArticulo p = data.getRowValue();           
            Pattern pat = Pattern.compile("([+-][0-9]|[0-9])[0-9]*");
            Matcher mat = pat.matcher(data.getNewValue());
            if (!mat.matches()){ p.setUnidades(data.getOldValue()); table.refresh();}
            else{ p.setUnidades(data.getNewValue()); CArticulo art=p.articulo; art.modificar();}
        });
        TableColumn<modeloArticulo, String> ttnombre = tnombre;
        ttnombre.setCellFactory(TextFieldTableCell.forTableColumn());
        ttnombre.setOnEditCommit(data -> {
            modeloArticulo p = data.getRowValue();
            p.setNombre(data.getNewValue());
            CArticulo art=p.articulo;            
            art.modificar();
        });
       
        TableColumn<modeloArticulo, String> ttcodigo = tcodigo;
        ttcodigo.setCellFactory(TextFieldTableCell.forTableColumn());
        ttcodigo.setOnEditCommit(data -> {
            modeloArticulo p = data.getRowValue();
            p.setCodigo(data.getNewValue());
            CArticulo art=p.articulo;
            art.modificar();
        });
        TableColumn<modeloArticulo, String> ttdesc = tdescripcion;
        ttdesc.setCellFactory(TextFieldTableCell.forTableColumn());
        ttdesc.setOnEditCommit(data -> {
            modeloArticulo p = data.getRowValue();
            p.setDescripcion(data.getNewValue());
            CArticulo art=p.articulo;           
            art.modificar();
        });
        TableColumn<modeloArticulo, String> ttcosto = tcosto;
        ttcosto.setCellFactory(TextFieldTableCell.forTableColumn());
        ttcosto.setOnEditCommit(data -> {
            modeloArticulo p = data.getRowValue();            
            Pattern pat = Pattern.compile("([+-][0-9]|[0-9])[0-9]*(.[0-9]*)?");
            Matcher mat = pat.matcher(data.getNewValue());
            if (!mat.matches()){ p.setCosto(data.getOldValue()); table.refresh();}
            else{ p.setCosto(data.getNewValue());CArticulo art=p.articulo; art.modificar();}

        });
        TableColumn<modeloArticulo, String> ttpd = tpreciodia;
        ttpd.setCellFactory(TextFieldTableCell.forTableColumn());
        ttpd.setOnEditCommit(data -> {
            modeloArticulo p = data.getRowValue();

            Pattern pat = Pattern.compile("([+-][0-9]|[0-9])[0-9]*(.[0-9]*)?");
            Matcher mat = pat.matcher(data.getNewValue());
            if (!mat.matches()){ p.setPreciodia(data.getOldValue()); table.refresh();}
            else{ p.setPreciodia(data.getNewValue());            CArticulo art=p.articulo; art.modificar();}

        });
        TableColumn<modeloArticulo, String> ttpn = tprecionoche;
        ttpn.setCellFactory(TextFieldTableCell.forTableColumn());
        ttpn.setOnEditCommit(data -> {
            modeloArticulo p = data.getRowValue();            
            Pattern pat = Pattern.compile("([+-][0-9]|[0-9])[0-9]*(.[0-9]*)?");
            Matcher mat = pat.matcher(data.getNewValue());
            if (!mat.matches()){ p.setPrecionoche(data.getOldValue()); table.refresh();}
            else{ p.setPrecionoche(data.getNewValue());CArticulo art=p.articulo; art.modificar();}
        });
       
        table.setEditable(true);
        table.setPrefWidth(800);

        for (CArticulo cat : lista_articulos){
            data.add(new modeloArticulo(cat));
        }
        table.setItems(filter);        
        filter.addAll(data);
        filterField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filter.clear();
                for (modeloArticulo p : data) {
                    if (buscar(p)) {
                        filter.add(p);
                    }
                }
                reapplyTableSortOrder();                
            }

        });
    }
    private boolean buscar(modeloArticulo x) {
        String filterString = filterField.getText();
        if (filterString == null || filterString.isEmpty()) {
            return true;
        }
        String lowerCaseFilterString = filterString.toLowerCase();
        

        if (x.getNombre().toLowerCase().indexOf(lowerCaseFilterString) != -1) return true; else
        if (x.getCodigo().toLowerCase().indexOf(lowerCaseFilterString) != -1) return true;else
        if (x.getCategoria().getNombre().toLowerCase().indexOf(lowerCaseFilterString) != -1) return true;
      
        return false; // Does not match
    }
    
    private void reapplyTableSortOrder() {
        ArrayList<TableColumn<modeloArticulo, ?>> sortOrder = new ArrayList<>(table.getSortOrder());
        table.getSortOrder().clear();
        table.getSortOrder().addAll(sortOrder);
    }
    private <S, T> TableColumn<S, T> column(String title, Function<S, ObservableValue<T>> property, double width) {
        TableColumn<S, T> col = new TableColumn<>(title);
        col.setCellValueFactory(cellData -> property.apply(cellData.getValue()));
        col.setPrefWidth(width);
        return col;
    }

}
