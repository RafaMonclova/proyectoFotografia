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
public interface CamaraDAO {
    
    //Métodos para acceder a la BDD
    public void update(Camara c);
    public void insert(Camara c,String imagen);
    public void delete(int id);
    public Camara read(int id);
    
}
