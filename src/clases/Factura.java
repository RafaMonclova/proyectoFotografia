/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.util.ArrayList;

/**
 *
 * @author RAFAEL MONCLOVA SUANO
 */
public class Factura {
    
    private int codigo;
    private String dni;
    private ArrayList<Compra> compras;
    private double importeTotal;

    public Factura(int codigo, String dni, double importeTotal) {
        this.codigo = codigo;
        this.dni = dni;
        this.importeTotal = importeTotal;
    }
    
    public Factura(String dni, double importeTotal) {
        this.codigo = generar();
        this.dni = dni;
        this.importeTotal = importeTotal;
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
    
    

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public ArrayList<Compra> getCompras() {
        return compras;
    }

    public void setCompras(ArrayList<Compra> compras) {
        this.compras = compras;
    }
    
    public String listaAString(){
        
        String salida = "";
        
        for(Compra c : compras){
            
            salida += c + "/";
            
        }
        
        return salida;
        
    }
    
    public String modelosLista(){
        
        String salida = "";
        
        for(Compra c : compras){
            
            salida += c.getModelo() + "/";
            
        }
        
        return salida;
        
    }
    

    public double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }
    
    
    
    
}
