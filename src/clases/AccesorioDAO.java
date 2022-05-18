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
public interface AccesorioDAO {
    
    //Métodos para acceder a la BDD
    public void update(Accesorio a);
    public void insert(Accesorio a,String imagen);
    public void delete(int id);
    public Accesorio read(String modelo);
    public ArrayList<Accesorio> readAll();
    
}
