package controlador.persona;
import controlador.categoria.*;
import clases.CCategoria;
import clases.CPersona;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author WARREN
 */

public class modeloPersona {
    private final SimpleStringProperty id;
    private final SimpleStringProperty tipo_documento;
    private final SimpleStringProperty nro_documento;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty apellidos;
    private final SimpleStringProperty fecha_nacimiento;
    private final SimpleStringProperty sexo;
    private final SimpleStringProperty email;
    private final SimpleStringProperty direccion;
    
    private final SimpleStringProperty usuario;
    private final SimpleStringProperty password;
    private final SimpleStringProperty tipo;
    
    CPersona x;
    public modeloPersona(CPersona x) {
        this.x=x;
        this.id=new SimpleStringProperty(x.getId().toString());
        this.tipo_documento=new SimpleStringProperty(x.getTipoDocumento());
        this.nro_documento=new SimpleStringProperty(x.getnroDocumento());       
        this.nombre=new SimpleStringProperty(x.getNombre());
        this.apellidos=new SimpleStringProperty(x.getApellidos());
        this.fecha_nacimiento=new SimpleStringProperty(x.getFecha_nacimiento().toString());
        this.sexo=new SimpleStringProperty(x.getSexo());
        this.email=new SimpleStringProperty(x.getEmail());
        String dir="";
        if(x.getDireccion().size()!=0)dir=x.getDireccion().get(0).toString();
        this.direccion=new SimpleStringProperty(dir);
        this.usuario=new SimpleStringProperty(x.getUsuario());
        this.password=new SimpleStringProperty(x.getPasword());
        this.tipo=new SimpleStringProperty(x.getTipo());
    }
    public String getId(){
        return id.get();
    }
    public String getTipo_documento(){
        return tipo_documento.get();
    }
    public String Nro_documento(){
        return nro_documento.get();
    }    
    public String getNombre(){
        return nombre.get();
    }
    public String getApellidos(){
        return apellidos.get();
    }
    public String getFecha_nacimiento(){
        return fecha_nacimiento.get();
    }
    public String getSexo(){
        return sexo.get();
    }
    public String getEmail(){
        return email.get();
    }
            
    public String getDireccion(){
        return direccion.get();
    }
    public String getUsuario(){
        return usuario.get();
    }
    public String getPassword(){
        return password.get();
    }
    public String getTipo(){
        return tipo.get();
    }

}
