/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author RAFAEL MONCLOVA SUANO
 */
public class Camara extends Producto{
    
    
    private ArrayList<Accesorio> accesorios = new ArrayList();

    
    public Camara(int id, String marca, String modelo, double precio) {
        super(id, marca, modelo, precio);
    }

    public ArrayList<Accesorio> getAccesorios() {
        return accesorios;
    }

    public void setAccesorios(ArrayList<Accesorio> accesorios) {
        this.accesorios = accesorios;
    } 
    
    public void añadirAccesorio(Accesorio a){
        
        accesorios.add(a);
        
    }
    
    public void eliminarAccesorio(int id){
        
        Iterator it = accesorios.iterator();
        
        while(it.hasNext()){
            Accesorio actual  = (Accesorio) it.next();
            if(actual.getId() == id){
                    it.remove();
            }
        }
        
    }

    @Override
    public String toString() {
        return super.toString() +"\n" +
                "ACCESORIOS: "+accesorios;
    }
    
    
    
    
    
}
