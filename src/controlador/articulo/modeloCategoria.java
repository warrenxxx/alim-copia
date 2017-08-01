package controlador.articulo;
import controlador.categoria.*;
import clases.CCategoria;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author WARREN
 */

public class modeloCategoria {
    private final SimpleStringProperty id;
    private final SimpleStringProperty nombre;    

    public CCategoria categoria;
    public modeloCategoria(CCategoria x) {
        if(x==null){
            this.id=new SimpleStringProperty(" no tiene");
            this.nombre=new SimpleStringProperty("no tiene");
        }else{
            if(x.getId()==null) this.id=new SimpleStringProperty("no tiene");
            else this.id=new SimpleStringProperty(x.getId().toString());

            if(x.getNombre()==null) this.nombre=new SimpleStringProperty("no tiene");
            else this.nombre=new SimpleStringProperty(x.getNombre());

        }
        this.categoria=x;
    }

    public String getNombre(){
        return nombre.get();
    }

    @Override
    public String toString() {
        return nombre.get();
    }       
}
