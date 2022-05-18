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
public interface ClienteDAO {
    
    //Métodos para acceder a la BDD
    public void update(Cliente c);
    public void insert(Cliente c,String imagen);
    public void delete(String dni);
    public Cliente read(String dni);
    public ArrayList<Cliente> readAll();
    
}
