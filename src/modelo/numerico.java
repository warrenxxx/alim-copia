/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import clases.CArticulo;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author WARREN
 */
public class numerico {

    public boolean ISDOUBLE(String a) {
        char k[] = a.toCharArray();
        int cont = 0, i = 0;
        if (k[0] == '+' || k[0] == '-') {
            i++;
        }
        for (; i < k.length; i++) {
            char c = k[i];
            if (c > '9' || c < '0') {
                if (c == '.' && cont == 0 && i > 0) {
                    cont++;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean ISINT(String a) {
        char k[] = a.toCharArray();
        int cont = 0, i = 0;
        if (k[0] == '+' || k[0] == '-') {
            i++;
        }
        for (; i < k.length; i++) {
            char c = k[i];
            if (c > '9' || c < '0') {
                return false;
            }
        }
        return true;
    }
    public double precio_segun_hora(CArticulo x){
        int k=Integer.parseInt(HORAACTUAL());
        if(k>23||k<7)return x.getPrecio_noche();
        return x.getPrecio_dia();
    }
    public static String HORAACTUAL() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("HH");
        return formateador.format(ahora);
    }
}
