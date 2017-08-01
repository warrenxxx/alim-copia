package controlador.admi;

import clases.CPersona;
import empaquetado.vista_empaquetado;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import static modelo.Fx2.SESSION;
import static modelo.Fx2.USER;
import modelo.rutas;
import reportes.rep_sesscion;
import reportes.rep_venta_detallado;
import reportes.rep_venta_total;
import reportes.stokc_por_Organizacion;
import reportes.stokc_por_categoria;

public class SideController implements Initializable {

        public static BorderPane borderPane;
    @FXML
        Button btnCategoria,btnEntrada,btnSecciones,btnProductos,
               btnStockCategoria,btnDesempacar,btnVentas_detallado,
               btnStockOrganizacion,btnVentas,btnVentas_fechas,btnVentas_total,btnUsuarios;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void m_productos(ActionEvent ev) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/articulo/m_articulo_ini.fxml"));
        VBox root =(VBox)loader.load();
        root.autosize();
        borderPane.setCenter(root);
    }

    public void m_categoria(ActionEvent ev) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/categoria/m_categoria_ini.fxml"));
        VBox root =(VBox)loader.load();
        root.autosize();
        borderPane.setCenter(root);
    }
    public void ventas(ActionEvent ev) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/ventas/ventas.fxml"));
        VBox root =(VBox)loader.load();
        root.autosize();
        borderPane.setCenter(root);
    }
    public void usuarios(ActionEvent ev) throws IOException{
        new rutas().tousuarios();
    }
    
    public void compras(ActionEvent ev) throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/vista/compras/compras.fxml"));
        VBox root =(VBox)loader.load();
        root.autosize();
        borderPane.setCenter(root);
    }
    public void desempacar(ActionEvent ev){
        new vista_empaquetado().display() ;
//        borderPane.setCenter(root);
    }
    public void ventas_detallado(ActionEvent ev) throws IOException{
        new rep_venta_detallado().display() ;

    }
    public void ventas_total(ActionEvent ev) throws IOException{
        new rep_venta_total().display() ;

    }
    public void stock_por_categoria(ActionEvent ev) throws IOException{
        new stokc_por_categoria().display() ;

    }
    public void     stock_por_organizacion(ActionEvent ev) throws IOException{
        new stokc_por_Organizacion().display() ;

    }

    public void secciones(ActionEvent ev) throws IOException{
        new rep_sesscion().display() ;

    }

    public void set_border_pane(BorderPane borderPane,CPersona per) {
        if(USER.getTipo().compareTo("1")==0){
            btnCategoria.setVisible(false);
            btnEntrada.setVisible(false);
            btnProductos.setVisible(false);
            btnUsuarios.setVisible(false);
        }
        this.borderPane=borderPane;
    }
}