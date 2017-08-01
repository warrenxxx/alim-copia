/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import clases.CCategoria;
import java.util.HashMap;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author WARREN
 */
public class modeloStockCategoria {
    private final SimpleStringProperty categoria;    
    private final SimpleStringProperty stock;    
    CCategoria cat;

    public modeloStockCategoria(HashMap d) {
        cat=new CCategoria().buscarid(d.get("_id").toString());        
        this.categoria =new SimpleStringProperty(cat.getNombre());
        this.stock = new SimpleStringProperty(d.get("total").toString());
    }
    
}
