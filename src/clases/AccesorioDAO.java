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
public interface AccesorioDAO {
    
    public void update(Accesorio a);
    public void insert(Accesorio a);
    public void delete(int id);
    public Accesorio read(int id);
    
}
