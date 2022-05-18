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

    //Atributos de Accesorio
    private String tipo;
    
    //Constructor
    public Accesorio(int id, String marca, String modelo, double precio,String tipo) {
        super(id, marca, modelo, precio);
        this.tipo = tipo;
    }

    /**
     * 
     * @return Devuelve el tipo del accesorio
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * 
     * @param tipo Recibe el tipo y lo asigna
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return super.toString() +"\n" +
                "TIPO: "+tipo;
    }
    
    
    
    
    
}
