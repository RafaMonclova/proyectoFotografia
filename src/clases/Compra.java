/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author RAFAEL MONCLOVA SUANO
 */
public class Compra {
    
    
    private int codigo;
    private String modelo;
    private int cantidad;
    private double precio;

    public Compra(String modelo, int cantidad, double precio) {
        this.codigo = generar();
        this.modelo = modelo;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    
    public Compra(int codigo, String modelo, int cantidad, double precio) {
        this.codigo = codigo;
        this.modelo = modelo;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    
    public int generar(){
        return (int)(Math.random()*10000+1);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return codigo + ";" + modelo + ";" +cantidad + ";" + precio;
    }
    
    
    
    
    
}
