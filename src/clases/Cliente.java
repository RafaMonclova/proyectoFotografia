/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.InputStream;

/**
 *
 * @author RAFAEL MONCLOVA SUANO
 */
public class Cliente {
    
    private String dni;
    private String nombre;
    private String apellidos;
    private String direccion;
    private int telefono;
    private boolean habitual;
    private InputStream imagen;

    public Cliente(String dni, String nombre, String apellidos, String direccion, int telefono,boolean habitual) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.habitual = habitual;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public boolean isHabitual() {
        return habitual;
    }

    public void setHabitual(boolean habitual) {
        this.habitual = habitual;
    }

    @Override
    public String toString() {
        return "DNI: " + dni +"\n"+
                "NOMBRE: " + nombre +"\n"+ 
                "APELLIDOS" + apellidos +"\n"+ 
                "DIRECCIÓN" + direccion + "\n"+ 
                "TELÉFONO" + telefono +"\n"+ 
                "HABITUAL" + habitual;
    }

    public InputStream getImagen() {
        return imagen;
    }

    public void setImagen(InputStream imagen) {
        this.imagen = imagen;
    }
    
    
    
    
}
