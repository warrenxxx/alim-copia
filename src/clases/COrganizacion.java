/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author WARREN
 */
public class COrganizacion extends C_Principal{

    public COrganizacion(HashMap m) {
        this.set_datos(m);
    }

    public String getNombre(){
        return (String) this.datos.get("_id");
    }
    public String getSuma(){
        return String.valueOf(this.datos.get("suma"));
    }
    public ArrayList productos(){
        if(this.datos.get("productos")==null)return new ArrayList();
        ArrayList<HashMap>aux=(ArrayList<HashMap>) this.datos.get("productos");
        ArrayList<CArticulo> res=new ArrayList<>();
        for(HashMap h:aux){
            CArticulo x=new CArticulo();
            x.set_datos(h);
            res.add(x);
        }
        return res;

    }
}
