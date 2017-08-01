/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos_sistema;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 *
 * @author WARREN
 */

public class label_text extends HBox{
    TextField y;    
    public label_text(String name){
        Label x=new Label(name);
        y=new TextField(name);
        this.getChildren().addAll(x,y);
    }    
    public label_text(String name,double k){
        Label x=new Label(name);
        y=new TextField("");
        y.setPromptText(name);
        y.setPrefWidth(k);
        x.setPrefWidth(k);
        this.getChildren().addAll(x,y);
    }    
    public String get(){
        return y.getText();
    }
    public void set(String x){
        y.setText(x);
    }
}
