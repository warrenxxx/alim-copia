package controlador.categoria;
import clases.CCategoria;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author WARREN
 */

public class modeloCategoria {
    private final SimpleStringProperty id;
    private final SimpleStringProperty nombre;    
    private final SimpleStringProperty descripcion;
    private final SimpleStringProperty proveedor;
    public CCategoria categoria;
    public modeloCategoria(CCategoria x) {
        this.id=new SimpleStringProperty(x.getId().toString());
        this.nombre=new SimpleStringProperty(x.getNombre());
        this.descripcion=new SimpleStringProperty(x.getDescripcion());
        this.proveedor=new SimpleStringProperty(x.getProveedor());        
        this.categoria=x;
    }

    public String getNombre(){
        return nombre.get();
    }
    public String getDescripcion(){
        return descripcion.get();
    }
    public String getProveedor(){
        return proveedor.get();
    }
    @Override
    public String toString() {
        return id.get()+" "+nombre.get()+" "+descripcion.get()+" "+proveedor.get();
    }     
}
