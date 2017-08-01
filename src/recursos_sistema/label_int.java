/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursos_sistema;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 *
 * @author WARREN
 */
public class label_int extends HBox{
        TextField y;    
    public label_int(String name){
        Label x=new Label(name);
        y=new TextField(name);
        this.getChildren().addAll(x,y);
        validaciones();
    }
     public label_int(String name,double k){
        Label x=new Label(name);
        y=new TextField("0");
        
        y.setPrefWidth(k);
        x.setPrefWidth(k);
        this.getChildren().addAll(x,y);
        validaciones();
    }
    public int get(){
        return Integer.parseInt( y.getText());
    }
    public void set(int x){
        y.setText(x+"");
    }
    public void set(String x){
        y.setText(x);
    }
    public void validaciones(){
        y.textProperty().addListener((obserbable,oldv,newv)->{
            Pattern pat = Pattern.compile("([+-][0-9]|[0-9])[0-9]*");
            Matcher mat = pat.matcher(newv);
            if (!mat.matches())   y.setText(oldv);
        });
    }
}
