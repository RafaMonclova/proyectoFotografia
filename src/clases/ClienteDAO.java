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
public interface ClienteDAO {
    
    public void update(Cliente c);
    public void insert(Cliente c);
    public void delete(String dni);
    public Cliente read(String dni);
    
}
