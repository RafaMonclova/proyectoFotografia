/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.io.InputStream;

/**
 *
 * @author usuario
 */
abstract public class Producto {
    
    private int id;
    private String marca;
    private String modelo;
    private double precio;
    private InputStream imagen;

    public Producto(int id, String marca, String modelo, double precio) {
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
    
    public InputStream getImagen() {
        return imagen;
    }

    public void setImagen(InputStream imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "ID: " + id +"\n"+
                "MARCA: " + marca +"\n"+
                "MODELO:" + modelo +"\n"+ 
                "PRECIO=" + precio;
    }
    
    
    
}
