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
public interface CompraDAO {
    
    //Métodos para acceder a la BDD
    public void update(Compra c);
    public void insert(Compra c);
    public void delete(int codigo);
    public Compra read(int codigo);
    public ArrayList<Compra> readAll();
    
}
