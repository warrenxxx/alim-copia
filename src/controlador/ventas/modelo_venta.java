package controlador.ventas;
import clases.CArticulo;
import clases.CVenta_detalle;
import javafx.beans.property.SimpleStringProperty;
import modelo.numerico;

/**
 *
 * @author WARREN
 */

public class modelo_venta {
    private final SimpleStringProperty numero;
    private final SimpleStringProperty cantidad;    
    private final SimpleStringProperty codigo;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty precio;
    private final SimpleStringProperty descuento;
    private final SimpleStringProperty total;
    public CVenta_detalle x;
    public CArticulo y;
    public modelo_venta(String k,     CVenta_detalle x) {
        this.x=x;
        this.y=x.getArticuloAll();
        System.out.println("///"+x);
        System.out.println("///"+y);
        this.numero =new SimpleStringProperty(k);
        this.cantidad =new SimpleStringProperty( x.getCantidad()+"");
        this.codigo =new SimpleStringProperty( y.getCodigo());
        this.nombre =new SimpleStringProperty( y.getNombre());
        this.precio =new SimpleStringProperty( x.getPrecio()+"");
        this.total = new SimpleStringProperty(precio.get());
        this.descuento=new SimpleStringProperty(x.getDescuento()+"");
        calcular();
    }

    public String getNumero() {
        return numero.get();
    }
    public String getCantidad() {
        return cantidad.get();
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

    public String getTotal() {
        return total.get();
    }
    public String getDescuento() {
        return descuento.get();
    }
    public void calcular(){
        double k1=Double.parseDouble(this.cantidad.get());
        double k2=Double.parseDouble(this.precio.get());
        double k3=Double.parseDouble(this.descuento.get());
        double k4=k1*k2-k3;
        total.setValue(k4+"");        
    }
    public void setCantidad(String cantidad){
        this.cantidad.setValue(cantidad);
        calcular();
    }

    public void setDescuento(String desc){
        this.descuento.setValue(desc);
calcular();
    }
    public void setNumero(String numero){
        this.numero.setValue(numero);
    }

    public String getSubtotal() {
        double k1=Double.parseDouble(this.cantidad.get());
        double k2=Double.parseDouble(this.precio.get());
        double k3=Double.parseDouble(this.descuento.get());
        double k4=k1*k2-k3;
        return (k1*k2)+"";       
    }
    
}
