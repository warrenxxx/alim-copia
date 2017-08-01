/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empaquetado;

import clases.CArticulo;
import clases.CAsignacion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 *
 * @author WARREN
 */
public class item_paquetado extends HBox{

    Label x;
    Button y,z;
    CArticulo m;
    CArticulo n;
    int cant;
    public item_paquetado(CArticulo m,CAsignacion as) {
        CArticulo art=new CArticulo().buscarid(as.getCodigo());
        this.m=m;
        this.n=art;
        this.cant=as.getCantidad();
        x=new Label(art.getCodigo()+" "+art.getNombre()+" "+art.getStock());
        
        y=new Button("Desempaquetar");
        z=new Button("Empaquetar");
        y.setOnAction(e->{
            m.setStock(m.getStock()+cant);
            n.setStock(n.getStock()-1);
            m.modificar();
            n.modificar();
        });
        z.setOnAction(e->{
            m.setStock(m.getStock()-cant);
            n.setStock(n.getStock()+1);
            m.modificar();
            n.modificar();
        });
        this.getChildren().addAll(x,y,z);        
    }
    
}
