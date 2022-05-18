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
public class AccesorioBDD implements AccesorioDAO{
    
    
    /**
     * Actualiza el objeto en la base de datos
     * @param a Recibe el objeto a actualizar
     */
    @Override
    public void update(Accesorio a) {
        
        //Recibe los atributos de "a" y guarda la cadena
        String update = "UPDATE ACCESORIO SET ID="+a.getId()+","+"MARCA='"+a.getMarca()+"',"+"MODELO='"+a.getModelo()+"',"+"PRECIO="+a.getPrecio()+",TIPO='"+a.getTipo()+"'WHERE ID="+a.getId();
           
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
     * @param a Recibe el objeto a insertar
     * @param imagen Recibe una cadena con la ruta de la imagen que tendrá
     */
    @Override
    public void insert(Accesorio a,String imagen) {
        
        
        
        try{	
		 	 //Conexión a la BDD. La clase Login contiene los atributos con el servidor, usuario y clave para conectarse
			 Class.forName("com.mysql.cj.jdbc.Driver");
						
			 Connection connection=DriverManager.getConnection(Login.servidor, Login.usuario, Login.clave);
                         
                         //Se prepara el Statement con la cadena, y un InputStream creado con  la ruta de la imagen recibida
                         PreparedStatement ps = null;
                         InputStream is = null;
			ps = connection.prepareCall("INSERT INTO ACCESORIO VALUES("+a.getId()+",'"+a.getMarca()+"','"+a.getModelo()+"',"+a.getPrecio()+",'"+a.getTipo()+"',"+"?"+")");
                        //is = getClass().getResourceAsStream("/resources/"+a.getModelo()+".png");
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
                        ps = connection.prepareCall("INSERT INTO ACCESORIO VALUES("+a.getId()+",'"+a.getMarca()+"','"+a.getModelo()+"',"+a.getPrecio()+",'"+a.getTipo()+"',"+"?"+")");
                        is = getClass().getResourceAsStream(imagen);
                        ps.setBinaryStream(1, is);
                        ps.executeUpdate();
                        
                        System.out.println("Inserción realizada");
                     
                     
        }   catch (ClassNotFoundException ex1) {
                Logger.getLogger(AccesorioBDD.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SQLException ex1) {
                Logger.getLogger(AccesorioBDD.class.getName()).log(Level.SEVERE, null, ex1);
            }
        

        
        
        }
    }   

    /**
     * Borra un registro de la BDD por id de producto
     * @param id Recibe el id del registro a borrar
     */
    @Override
    public void delete(int id) {
        
        //Sentencia para eliminar por id
        String delete="DELETE FROM ACCESORIO WHERE ID="+id;
        
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
     * Obtiene el accesorio con el modelo recibido 
     * @param modelo Recibe el modelo a buscar
     * @return Devuelve un objeto Accesorio con el modelo dado
     */
    @Override
    public Accesorio read(String modelo) {
        
        Accesorio a = null;
        
        //Se guarda la consulta por id recibido
        String query="SELECT * FROM ACCESORIO WHERE MODELO='"+modelo+"'";
				
	 try{	
	 	 
                 
            //Conexión a la BDD. La clase Login contiene los atributos con el servidor, usuario y clave para conectarse
            Class.forName("com.mysql.cj.jdbc.Driver");


            Connection connection=DriverManager.getConnection(Login.servidor, Login.usuario, Login.clave);

	     
		 Statement statement=connection.createStatement();
		 ResultSet result=statement.executeQuery(query);
		 
		 
		 while(result.next()){ 
                         //Crea el objeto con los campos guardados en la tabla
                         a = new Accesorio(result.getInt(1),result.getString(2),result.getString(3),result.getDouble(4),result.getString(5));
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
        
        return a;
        
    }
    
    /**
     * Lista con todos los Accesorios de la BDD
     * @return Devuelve una lista con todos los Accesorios registrados
     */
    @Override
    public ArrayList<Accesorio> readAll() {
        
        ArrayList<Accesorio> accesorios = new ArrayList();
        
        //Consulta que devuelve todos los registros de ACCESORIO
        String query="SELECT * FROM ACCESORIO";
				
	 try{	
	 	 
                 
            //Conexión a la BDD. La clase Login contiene los atributos con el servidor, usuario y clave para conectarse
            Class.forName("com.mysql.cj.jdbc.Driver");


            Connection connection=DriverManager.getConnection(Login.servidor, Login.usuario, Login.clave);

	     
		 Statement statement=connection.createStatement();
		 ResultSet result=statement.executeQuery(query);
		 
		 
		 while(result.next()){ 
                         //Crea el objeto y lo añade a la lista. Se realiza por cada registro encontrado
                         Accesorio a = new Accesorio(result.getInt(1),result.getString(2),result.getString(3),result.getDouble(4),result.getString(5));
                         accesorios.add(a);
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
        
        return accesorios;
        
    }
    
    
        
        
    
    
}
