/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author RAFAEL MONCLOVA SUANO
 */
public class Accesorio extends Producto{

    
    private String tipo;
    
    public Accesorio(int id, String marca, String modelo, double precio) {
        super(id, marca, modelo, precio);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return super.toString() +"\n" +
                "TIPO: "+tipo;
    }
    
    
    
    
    
}
