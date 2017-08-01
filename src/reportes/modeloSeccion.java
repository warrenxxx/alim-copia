package reportes;

import controlador.articulo.*;
import clases.CArticulo;
import clases.CCategoria;
import clases.CPersona;
import clases.CSession;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author WARREN
 */

public class modeloSeccion {
    private final SimpleStringProperty persona;    

    public CSession sesscion;
    public CPersona per;    
    public modeloSeccion(CSession x) {
        this.sesscion=x;
        this.per=(CPersona) x.getPersona();
        if(per==null)            this.persona=new SimpleStringProperty(" no ");
        else    this.persona=new SimpleStringProperty(per.getNombre()+per.getUsuario());
    }

    public String getFecha() {
        return sesscion.getFecha().toString();
    }

    public String getPersona() {
        return persona.get();
    }

}
