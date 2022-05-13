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
public class Camara {
    
    private int id;
    private String marca;
    private String modelo;
    private double precio;
    private ArrayList<Accesorio> accesorios = new ArrayList();
   

    public Camara(int id,String marca, String modelo, double precio) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
        
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
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
    
    public void eliminarAccesorio(Accesorio a){
        
        Iterator it = accesorios.iterator();
        
        while(it.hasNext()){
            Accesorio actual  = (Accesorio) it.next();
            if(actual.getId() == a.getId()){
                    it.remove();
            }
        }
        
    }
    
    
    
}
