/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author RAFAEL MONCLOVA SUANO
 */
public class Camara extends Producto{
    
    
    
    private InputStream imagen;
    
    public Camara(int id, String marca, String modelo, double precio) {
        super(id, marca, modelo, precio);
    }

    
    public InputStream getImagen() {
        return imagen;
    }

    public void setImagen(InputStream imagen) {
        this.imagen = imagen;
    }
    

    @Override
    public String toString() {
        return super.toString();
    }
    
    
    
    
    
}
