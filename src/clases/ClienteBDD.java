/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author RAFAEL MONCLOVA SUANO
 */
public class ClienteBDD implements ClienteDAO{
    

    /**
     * Actualiza el objeto en la base de datos
     * @param c Recibe el objeto a actualizar
     */
    @Override
    public void update(Cliente c) {
        //Recibe los atributos de "c" y guarda la cadena
        String update = "UPDATE CLIENTE SET DNI='"+c.getDni()+"',"+"NOMBRE='"+c.getNombre()+"',"+"APELLIDOS='"+c.getApellidos()+"',"+"DIRECCION='"+c.getDireccion()+"',TELEFONO="+c.getTelefono()+", HABITUAL="+c.isHabitual()+" WHERE DNI='"+c.getDni()+"'";
           
		 try{	
		 	 //Conexión a la BDD. La clase Login contiene los atributos con el servidor, usuario y clave para conectarse
			 Class.forName("com.mysql.cj.jdbc.Driver");
						
			 Connection connection=DriverManager.getConnection(Login.servidor, Login.usuario, Login.clave);
			
			 Statement statement=connection.createStatement();
			 
			 
			 try{
			     statement.executeUpdate(update);
			     System.out.println("Actualización realizada");
			 }catch(SQLException sqle){
				 System.out.println("SQL Exception");
                                 sqle.printStackTrace();
			 }
			 
			 statement.close();
			 connection.close();
			 	 
						
		 }catch(ClassNotFoundException cnfe){  
				System.out.printf("Not found the jdbc driver %s\n");
		 }catch (SQLException sqle){
				System.out.println("SQL Exception");
                                sqle.printStackTrace();
		 }
    }

    /**
     * Inserta el objeto en la BDD
     * @param c Recibe el objeto a insertar
     * @param imagen Recibe una cadena con la ruta de la imagen que tendrá
     */
    @Override
    public void insert(Cliente c,String imagen) {
        
        
        
        try{	
		 	 //Conexión a la BDD. La clase Login contiene los atributos con el servidor, usuario y clave para conectarse
			 Class.forName("com.mysql.cj.jdbc.Driver");
						
			 Connection connection=DriverManager.getConnection(Login.servidor, Login.usuario, Login.clave);
                         
                         //Se prepara el Statement con la cadena, y un InputStream creado con  la ruta de la imagen recibida
                         PreparedStatement ps = null;
                         InputStream is = null;
			ps = connection.prepareCall("INSERT INTO CLIENTE VALUES('"+c.getDni()+"','"+c.getNombre()+"','"+c.getApellidos()+"','"+c.getDireccion()+"',"+c.getTelefono()+","+c.isHabitual()+","+"?"+")");
                        is = new FileInputStream(imagen);
                        ps.setBinaryStream(1, is);
			 
			 
			 try{
                             ps.executeUpdate();
			     
			     System.out.println("Inserción realizada");
			 }catch(SQLException sqle){
				 System.out.println("SQL Exception 1");
			 }
			 
			 ps.close();
			 connection.close();
			 	 
						
		 }catch(ClassNotFoundException cnfe){  
				System.out.printf("Not found the jdbc driver %s\n");
		 }catch (SQLException sqle){
				System.out.println("SQL Exception 2");
                                sqle.printStackTrace();
                                
        } catch (FileNotFoundException ex) {
            //Si la imagen no se encuentra o no se selecciona, por defecto se usa una por defecto "/resources/default.png". 
            //Se obtiene mediante getClass().getResourceAsStream(imagen) ya que es un recurso guardado con el proyecto
            
            try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection connection=DriverManager.getConnection(Login.servidor, Login.usuario, Login.clave);
                        PreparedStatement ps = null;
                        InputStream is = null;
                        ps = connection.prepareCall("INSERT INTO CLIENTE VALUES('"+c.getDni()+"','"+c.getNombre()+"','"+c.getApellidos()+"','"+c.getDireccion()+"',"+c.getTelefono()+","+c.isHabitual()+","+"?"+")");
                        is = getClass().getResourceAsStream(imagen);
                        ps.setBinaryStream(1, is);
                        ps.executeUpdate();
                        
                        System.out.println("Inserción realizada");
                     
                     
        }   catch (ClassNotFoundException ex1) {   
                Logger.getLogger(ClienteBDD.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SQLException ex1) {
                Logger.getLogger(ClienteBDD.class.getName()).log(Level.SEVERE, null, ex1);
            }   
            
            
        }
        
    }

    /**
     * Borra un registro de la BDD por DNI del cliente
     * @param dni Recibe el dni del registro a borrar
     */
    @Override
    public void delete(String dni) {
        
        //Sentencia para eliminar por id
        String delete="DELETE FROM CLIENTE WHERE DNI='"+dni+"'";
        
        try{	
		 	 //Conexión a la BDD. La clase Login contiene los atributos con el servidor, usuario y clave para conectarse
			 Class.forName("com.mysql.cj.jdbc.Driver");
						
			 Connection connection=DriverManager.getConnection(Login.servidor, Login.usuario, Login.clave);
			
			 Statement statement=connection.createStatement();
			 
			 
			 try{
			     statement.executeUpdate(delete);
			     System.out.println("Borrado realizado");
			 }catch(SQLException sqle){
				 System.out.println("SQL Exception 1");
			 }
			 
			 statement.close();
			 connection.close();
			 	 
						
		 }catch(ClassNotFoundException cnfe){  
				System.out.printf("Not found the jdbc driver %s\n");
		 }catch (SQLException sqle){
				System.out.println("SQL Exception 2");
                                sqle.printStackTrace();
		 }
        
    }

    /**
     * Obtiene el cliente con el dni recibido 
     * @param dni Recibe el dni a buscar
     * @return Devuelve un objeto Cliente con el dni dado
     */
    @Override
    public Cliente read(String dni) {
        
        Cliente c = null;
        
        //Se guarda la consulta por dni recibido
        String query="SELECT * FROM CLIENTE WHERE DNI='"+dni+"'";
				
	 try{	
	 	 
                 
            //Conexión a la BDD. La clase Login contiene los atributos con el servidor, usuario y clave para conectarse
            Class.forName("com.mysql.cj.jdbc.Driver");


            Connection connection=DriverManager.getConnection(Login.servidor, Login.usuario, Login.clave);

	     
		 Statement statement=connection.createStatement();
		 ResultSet result=statement.executeQuery(query);
		 
		 
		 while(result.next()){ 
                         //Crea el objeto con los campos guardados en la tabla
                         c = new Cliente(result.getString(1),result.getString(2),result.getString(3),result.getString(4),result.getInt(5),result.getBoolean(6));
		 }
		 
		 result.close(); 
		 statement.close();
		 connection.close();
					
	 }catch(ClassNotFoundException cnfe){  
			System.out.printf("Not found the jdbc driver %s\n");
	 }catch (SQLException sqle){
			System.out.println("SQL Exception");
                        System.out.println(sqle.getMessage());
                      sqle.printStackTrace();
                        
	 }					
        return c;
        
    }
    
    /**
     * Lista con todos los Clientes de la BDD
     * @return Devuelve una lista con todos los Clientes registrados
     */
    public ArrayList<Cliente> readAll() {
        
        ArrayList<Cliente> clientes = new ArrayList();
        
        //Consulta que devuelve todos los registros de ACCESORIO
        String query="SELECT * FROM CLIENTE";
				
	 try{	
	 	 
                 
            //Conexión a la BDD. La clase Login contiene los atributos con el servidor, usuario y clave para conectarse
            Class.forName("com.mysql.cj.jdbc.Driver");


            Connection connection=DriverManager.getConnection(Login.servidor, Login.usuario, Login.clave);

	     
		 Statement statement=connection.createStatement();
		 ResultSet result=statement.executeQuery(query);
		 
		 
		 while(result.next()){ 
                         //Crea el objeto y lo añade a la lista. Se realiza por cada registro encontrado
                         Cliente c = new Cliente(result.getString(1),result.getString(2),result.getString(3),result.getString(4),result.getInt(5),result.getBoolean(6));
                         clientes.add(c);
		 }
		 
		 result.close(); 
		 statement.close();
		 connection.close();
					
	 }catch(ClassNotFoundException cnfe){  
			System.out.printf("Not found the jdbc driver %s\n");
	 }catch (SQLException sqle){
			System.out.println("SQL Exception");
                        System.out.println(sqle.getMessage());
                      sqle.printStackTrace();
                        
	 }					
        return clientes;
        
    }
    
    
        
        
    
    
}
