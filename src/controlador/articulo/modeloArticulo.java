package controlador.articulo;

import clases.CArticulo;
import clases.CCategoria;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author WARREN
 */

public class modeloArticulo {
    private final SimpleStringProperty id;
    private final SimpleStringProperty codigo;    
    private final SimpleStringProperty nombre;    
    private final SimpleStringProperty descripcion;
    private final SimpleStringProperty costo;
    private final SimpleStringProperty preciodia;
    private final SimpleStringProperty precionoche;
    private final SimpleStringProperty stock;
    private final SimpleStringProperty unidades;
    private final SimpleObjectProperty<modeloCategoria> categoria;

    public CArticulo articulo;
    public CCategoria cat;
    public modeloArticulo(CArticulo x) {
                this.articulo=x;
        this.cat=(CCategoria) x.getCategoria();
        System.out.println(cat+"warrne"+x);
        this.id=new SimpleStringProperty(x.getId().toString());
        this.codigo=new SimpleStringProperty(x.getCodigo());
        this.nombre=new SimpleStringProperty(x.getNombre());
        this.descripcion=new SimpleStringProperty(x.getDescripString());
        this.costo=new SimpleStringProperty(x.getCosto().toString());
        this.preciodia =new SimpleStringProperty(x.getPrecio_dia().toString());
        this.precionoche =new SimpleStringProperty(x.getPrecio_noche().toString());
        this.stock=new SimpleStringProperty(String.valueOf( x.getStock()));
        this.unidades=new SimpleStringProperty(String.valueOf( x.getUnidades_caja()));
        this.categoria=new SimpleObjectProperty(new modeloCategoria(cat));
//        System.out.println(this.categoria.get()+"ggggggggg");
    }

    public String getId() {
        return id.get();
    }
    public void setId(String x){
        this.id.set(x);
        articulo.setId(x);
    }

    public String getCodigo() {
        return codigo.get();
    }
    public void setCodigo(String x){
        this.codigo.set(x);
        articulo.setCodigo(x);
    }

    public String getNombre() {
        return nombre.get();
    }
    public void setNombre(String x){
        this.nombre.set(x);
        articulo.setNombre(x);
    }

    public String getDescripcion() {
        return descripcion.get();
    }
    public void setDescripcion(String x){
        this.descripcion.set(x);
                articulo.setDescripcion(x);

    }

    public String getCosto() {
        return costo.get();
    }
    public void setCosto(String x){
        this.costo.set(x);
                articulo.setCosto(Double.parseDouble(x));

    }

    public String getPreciodia() {
        return preciodia.get();
    }
    public void setPreciodia(String x){
        this.preciodia.set(x);
                articulo.setPrecio_dia(Double.parseDouble(x));

    }


    public String getPrecionoche() {
        return precionoche.get();
    }
    public void setPrecionoche(String x){
        this.precionoche.set(x);
        articulo.setPrecio_noche(Double.parseDouble(x));
        
    }

    public String getStock() {
        return stock.get();
    }
    public void setStock(String x){
        this.stock.set(x);
        articulo.setStock(Integer.parseInt(x));
    }

    public String getUnidades() {
        return unidades.get();
    }
    public void setUnidades(String x){
        this.unidades.set(x);
        articulo.setUnidades_caja(Integer.parseInt(x));
    }


    public modeloCategoria getCategoria() {
        return categoria.get();
    }
    public void setCategoria(modeloCategoria x){
        this.categoria.set(x);
        articulo.setCategoria(x.categoria.getId().toString());
    }
    public ObjectProperty<modeloCategoria> typObjProperty() {
            return this.categoria;
        }
      public void setTypObj(modeloCategoria typ) {
            this.categoria.set(typ);
        }
}
