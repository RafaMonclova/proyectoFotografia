/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.io.InputStream;

/**
 *
 * @author RAFAEL MONCLOVA SUANO
 */
abstract public class Producto {
    
    //Atributos de producto
    private int id;
    private String marca;
    private String modelo;
    private double precio;
    private InputStream imagen;

    //Constructor
    public Producto(int id, String marca, String modelo, double precio) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
    }

    /**
     * 
     * @return Devuelve el id del producto
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id Recibe el id del producto y lo asigna
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 
     * @return Devuelve la marca del producto
     */
    public String getMarca() {
        return marca;
    }

    /**
     * 
     * @param marca Recibe la marca y la asigna
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * 
     * @return Devuelve el modelo del producto
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * 
     * @param modelo Recibe el modelo y lo asigna
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * 
     * @return Devuelve el precio del producto
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * 
     * @param precio Recibe el precio y lo asigna
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    /**
     * 
     * @return Devuelve un InputStream con la imagen que tendrá el producto en la BDD
     */
    public InputStream getImagen() {
        return imagen;
    }

    /**
     * 
     * @param imagen Recibe un InputStream y lo asigna
     */
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
