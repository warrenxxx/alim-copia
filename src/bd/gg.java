package bd;

import clases.CPersona;
import clases.CPersona;
import clases.CDireccion;
import clases.CPersona;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author WARREN
 */
public class gg {

    /**
     * @param args the command line arguments
     */
    public static final String MSG_ACEPTADO ="si";
    public static final String MSG_RECHASADO ="NO";

    public static void main(String[] args) {
//        CCategoria{_id = 58dc9af551378143d4c81702,descripcion = caja1,proveedor = proveedor1,nombre = ron1,}
//CCategoria{_id = 58dc9af651378143d4c81704,descripcion = caja2,proveedor = proveedor2,nombre = ron2,}
//CCategoria{_id = 58dc9af651378143d4c81706,descripcion = caja3,proveedor = proveedor3,nombre = ron3,}

         CPersona c[]=new CPersona[3];
         c[0]=new CPersona();
            c[0].setApellidos("ap1");
            c[0].setEmail("email1");
            c[0].setFecha_nacimiento(new Date());
            c[0].setNombre("nombre1");
            c[0].setPaswword("pass1");
            c[0].setSexo("sexo1");
            c[0].setTipo("admi");
            c[0].setTipoDocumento("tipodoc1");
            c[0].setUsuario("usu1");
            c[0].setnroDocumento("123456");
            c[0].addDireccion(new CDireccion("calle tal1", "por ay", "456987", "456798", 0, 1));
            c[0].addDireccion(new CDireccion("calle tal1", "por ay1", "4569871", "4567981", 1, 2));
        c[1]=new CPersona();
            c[1].setApellidos("ap2");
            c[1].setEmail("email1");
            c[1].setFecha_nacimiento(new Date());
            c[1].setNombre("nombre1");
            c[1].setPaswword("pass1");
            c[1].setSexo("sexo1");
            c[1].setTipo("admi");
            c[1].setTipoDocumento("tipodoc1");
            c[1].setUsuario("usu1");
            c[1].setnroDocumento("123456");
            c[1].addDireccion(new CDireccion("calle tal2", "por ay", "456987", "456798", 0, 1));
            c[1].addDireccion(new CDireccion("calle tal2", "por ay1", "4569871", "4567981", 1, 2));
        c[2]=new CPersona();
            c[2].setApellidos("ap3");
            c[2].setEmail("email1");
            c[2].setFecha_nacimiento(new Date());
            c[2].setNombre("nombre1");
            c[2].setPaswword("pass1");
            c[2].setSexo("sexo1");
            c[2].setTipo("admi");
            c[2].setTipoDocumento("tipodoc1");
            c[2].setUsuario("usu1");
            c[2].setnroDocumento("123456");
            c[2].addDireccion(new CDireccion("calle ta3", "por ay", "456987", "456798", 0, 1));
            c[2].addDireccion(new CDireccion("calle tal3", "por ay1", "4569871", "4567981", 1, 2));
            
        c[0].setId(c[0].insertar());
        c[1].setId(c[1].insertar());
        c[2].setId(c[2].insertar());
        
        ArrayList <CPersona> ac=new DPersona().listar();
        System.out.println("insertar");
        for(CPersona x:ac) System.out.println(x);
        System.out.println("");
        
        c[1].setApellidos("caja22");
        c[1].setNombre("ron22");
        c[1].setDireccion(new CDireccion("GG","GG PORY", "UBIGEO", "POSTAL", 0, 0), 0);
        c[1].setId(c[1].modificar());
        
        ac=new DPersona().listar();
        System.out.println("modificar");        
        for(CPersona x:ac) System.out.println(x);
        System.out.println("");

        System.out.println(c[1].eliminar()+"kkkk");
        c[1].setId(c[1].eliminar());
        ac=new DPersona().listar();
        for(CPersona x:ac) System.out.println(x);
        System.out.println("");
        
        CPersona otro=new CPersona().buscarid(c[0].getId().toString());
        System.out.println(otro+" "+c[1].getId().toString());
        
        ac=new DPersona().listar("nombre","a1");
        for(CPersona x:ac) System.out.println(x);
        
        HashMap p=new HashMap();
        p.put("nombre", "a1");
        p.put("descripcion", "caja");
        
        ac=new DPersona().listar(p);
        for(CPersona x:ac) System.out.println(x);
        
        ArrayList<CDireccion> dir=c[2].getDireccion();
        for(CDireccion x:dir) System.out.println(x);
        
    }
    
}
