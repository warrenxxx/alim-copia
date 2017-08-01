/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.articulo;

import clases.CArticulo;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author WARREN
 */
public class modeloAsignacion {
    private final SimpleStringProperty numero;
    private final SimpleStringProperty codigo;
    private final SimpleStringProperty nombre;    
    private final SimpleStringProperty cantidad;
    public CArticulo x;
    public modeloAsignacion(String k,CArticulo y){
        this.numero =new SimpleStringProperty(k);
        this.cantidad =new SimpleStringProperty( "0");
        this.codigo =new SimpleStringProperty( y.getCodigo());
        this.nombre =new SimpleStringProperty( y.getNombre()+y.getDescripString());
        x=y;    
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
    public void setCantidad(String cantidad){
        this.cantidad.setValue(cantidad);
    }


    public void setNumero(String numero){
        this.numero.setValue(numero);
    }

}
