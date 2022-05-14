/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author usuario
 */
public class CamaraMain {
    
    public static void main(String[] args) {
        
        
        CamaraBDD camaras = new CamaraBDD();
        
        Camara c = new Camara(1,"CANNON","XZ56",200);
        camaras.insert(c);
        
        
    }
    
    
}
