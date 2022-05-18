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
    
    //Atributos de cliente
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

    /**
     * 
     * @return Devuelve el dni
     */
    public String getDni() {
        return dni;
    }

    /**
     * 
     * @param dni Asigna el dni
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * 
     * @return Devuelve el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * 
     * @param nombre Asigna el nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * 
     * @return Devuelve los apellidos
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * 
     * @param apellidos Asigna los apellidos
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * 
     * @return Devuelve la dirección
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * 
     * @param direccion Asigna la dirección
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    
    /**
     * 
     * @return Devuelve el teléfono
     */
    public int getTelefono() {
        return telefono;
    }

    /**
     * 
     * @param telefono Asigna el teléfono
     */
    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    /**
     * 
     * @return Devuelve true si es cliente habitual
     */
    public boolean isHabitual() {
        return habitual;
    }

    /**
     * 
     * @param habitual Asigna si es cliente habitual
     */
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

    /**
     * 
     * @return Devuelve un InputStream con la imagen que tendrá el cliente en la BDD
     */
    public InputStream getImagen() {
        return imagen;
    }

    /**
     * 
     * @param imagen Recibe un InputStream y lo asigna
     */
    public void setImagen(InputStream imagen) {
        this.imagen = imagen;
    }
    
    
    
    
}
