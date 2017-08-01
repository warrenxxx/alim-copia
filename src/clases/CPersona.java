/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;


import bd.DPersona;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author WARREN
 */
public class CPersona extends C_Principal{
/*       private final SimpleStringProperty usuario;
    private final SimpleStringProperty contraseña;
    private final SimpleStringProperty permisos;

    
    private final SimpleStringProperty tipo_documento;
    private final SimpleStringProperty nro_documento;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty apellidos;
    private final SimpleStringProperty fecha_nacimiento;
    private final SimpleStringProperty sexo;
    private final SimpleStringProperty email;
    private final SimpleStringProperty direccion;
*/
    
    private final String tipo_documento="tipo_documento";
    private final String nro_documento="nro_documento";
    private final String nombre="nombre";
    private final String apellidos="apellidos";
    private final String fecha_nacimiento="fecha_nacimiento";
    private final String sexo="sexo";
    private final String email="email";
    private final String direccion="direccion";
    
    private final String usuario="usuario";
    private final String password="password";//cajas o bolsas paquetes
    private final String tipo="tipo";

    public CPersona(){       
        this.datos=new HashMap();
        n=this.datos.size();
    }       
    public void setTipoDocumento(String x){
        this.datos.put(tipo_documento, x);
    }
    public String getTipoDocumento(){
        
        String aux= (String) this.datos.get(tipo_documento);
        return aux==null?"":aux;
    }    
    public void setnroDocumento(String x){
        this.datos.put(nro_documento, x);
    }
    public String getnroDocumento(){
        String aux= (String) this.datos.get(nro_documento);
        return aux==null?"":aux;
    }    
    public void setNombre(String x){
        this.datos.put(nombre, x);
    }
    public String getNombre(){
        String aux= (String) this.datos.get(nombre);
        return aux==null?"":aux;
    }    
    public void setApellidos(String x){
        this.datos.put(apellidos, x);
    }
    public String getApellidos(){
        String aux= (String) this.datos.get(apellidos);        
        return aux==null?"":aux;
    }
    public void setFecha_nacimiento(Date x){
        this.datos.put(fecha_nacimiento, x);
    }
    public Date getFecha_nacimiento(){
        Date aux= (Date) this.datos.get(fecha_nacimiento);        
        return aux==null?new Date():aux;
    }
    public void setSexo(String x){
        this.datos.put(sexo, x);
    }
    public String getSexo(){
        String aux= (String) this.datos.get(sexo);        
        return aux==null?"":aux;
    }
    public void setEmail(String x){
        this.datos.put(email, x);
    }    
    public String getEmail(){
        String aux= (String) this.datos.get(email);        
        return aux==null?"":aux;
    }
    public void setUsuario(String x){
        this.datos.put(usuario, x);
    }    
    public String getUsuario(){ 
        String aux= (String) this.datos.get(usuario);        
        return aux==null?"":aux;
    }
    public void setPaswword(String x){
        this.datos.put(password, x);
    }    
    public String getPasword(){
        String aux= (String) this.datos.get(password);        
        return aux==null?"":aux;
    }
    public void setTipo(String x){
        this.datos.put(tipo, x);
    }    
    public String getTipo(){
        String aux= (String) this.datos.get(tipo);        
        return aux==null?"":aux;
    }
    public void addDireccion(CDireccion x){

        ArrayList<HashMap> l=(ArrayList) this.datos.get(direccion);
        if(l==null)l=new ArrayList<HashMap>();
        HashMap aux=x.get_datos();
        aux.put("id", l.size());
        l.add(aux);
        this.datos.put(direccion, l);
    }

    public ArrayList <CDireccion> getDireccion(){
        ArrayList<CDireccion> x=new ArrayList();
        ArrayList<HashMap> y=( ArrayList )this.datos.get(direccion);
        if(y==null)return x;
        for(HashMap h:y){
            CDireccion d=new CDireccion();    
            d.set_datos(h);
            x.add((CDireccion) d);
        }
        return   x;  
    }
    public CDireccion getDireccion(int a){
        return   (CDireccion) ( (ArrayList)this.datos.get(direccion)).get(a);        
    }
    public void setDireccion(CDireccion x,int a){
        ArrayList l=  (ArrayList) this.datos.get(direccion);
        l.set(a, x.get_datos());
        this.datos.put(direccion, l);
    }
    
    
    public String insertar(){
        return new DPersona().insertar(this);
    }
    public String modificar(){
        return new DPersona().modificar(this);
    }
    public String eliminar(){
        return new DPersona().eliminar(this);
    }
    
    public ArrayList<CPersona> listar(){
        return new DPersona().listar();        
    }
    public ArrayList<CPersona> listar(HashMap buscar){
        return new DPersona().listar(buscar);        
    }
    public ArrayList<CPersona> listar(String clave,String valor){
        return new DPersona().listar(clave, valor);        
    }
    
    public CPersona buscarid(String id){
        CPersona x= (CPersona) new DPersona().buscar_id(id);   
        return x;
    }
    
    
    @Override
    public String toString() {
        String aux="";
        Iterator<String> it=this.datos.keySet().iterator();
        while(it.hasNext()){
            String s=it.next();
            aux+=s+" = "+datos.get(s)+",";
        }
        return "CPersona{" +aux+'}';
    }    
}
