/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;

/**
 *
 * @author RAFAEL MONCLOVA SUANO
 */
public interface FacturaDAO {
    
    //Métodos para acceder a la BDD
    public void update(Factura f);
    public void insert(Factura f);
    public void delete(int codigo);
    public Factura read(int codigo);
    public ArrayList<Factura> readAll();
    
}
