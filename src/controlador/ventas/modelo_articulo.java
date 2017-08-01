package controlador.ventas;
import clases.CArticulo;
import clases.CCategoria;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import static modelo.numerico.HORAACTUAL;

/**
 *
 * @author WARREN
 */

public class modelo_articulo {
    private final SimpleStringProperty id;
    private final SimpleStringProperty categoria;
    private final SimpleStringProperty stock;    
    private final SimpleStringProperty codigo;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty precio;
    public CArticulo x;
    public CCategoria y;
    public modelo_articulo(CArticulo x) {
        this.x=x;
        this.y=(CCategoria) x.getCategoria();
        if(this.y==null)this.y=new CCategoria();
        
        this.id=new SimpleStringProperty(x.getId().toString());
        this.categoria =new SimpleStringProperty( y.getNombre());
        this.stock =new SimpleStringProperty(String.valueOf(x.getStock()));
        this.codigo =new SimpleStringProperty( x.getCodigo());
        this.nombre =new SimpleStringProperty( x.getNombre());  
        int hora=Integer.parseInt(HORAACTUAL());
        if(hora>23&&hora<7) this.precio =new SimpleStringProperty( x.getPrecio_dia().toString());
        else this.precio =new SimpleStringProperty( x.getPrecio_noche().toString());

    }   

    public String getId() {
        return id.get();
    }
    public String getCategoria() {
        return categoria.get();
    }
    public String getStock() {
        return stock.get();
    }
    
    public String getCodigo() {
        return codigo.get();
    }

    public String getNombre() {
        return nombre.get();
    }

    public String getPrecio() {
        return precio.get();
    }
    

    public void setStock(int a){
        int x=Integer.parseInt(stock.get())+a;
        stock.setValue(x+"");
    }    
}
