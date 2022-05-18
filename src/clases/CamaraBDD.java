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
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author RAFAEL MONCLOVA SUANO
 */
public class CamaraBDD implements CamaraDAO{
    
    /**
     * Actualiza el objeto en la base de datos
     * @param c Recibe el objeto a actualizar
     */
    @Override
    public void update(Camara c) {
        //Recibe los atributos de "c" y guarda la cadena
        String update = "UPDATE CAMARA SET ID="+c.getId()+","+"MARCA='"+c.getMarca()+"',"+"MODELO='"+c.getModelo()+"',"+"PRECIO="+c.getPrecio()+"WHERE ID="+c.getId();
           
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
     * @param c Recibe el objeto a insertar
     * @param imagen Recibe una cadena con la ruta de la imagen que tendrá
     */
    @Override
    public void insert(Camara c,String imagen) {
        
        
        
        try{	
		 	 //Conexión a la BDD. La clase Login contiene los atributos con el servidor, usuario y clave para conectarse
			 Class.forName("com.mysql.cj.jdbc.Driver");
						
			 Connection connection=DriverManager.getConnection(Login.servidor, Login.usuario, Login.clave);
                         //Se prepara el Statement con la cadena, y un InputStream creado con  la ruta de la imagen recibida
                         PreparedStatement ps = null;
                         InputStream is = null;
			ps = connection.prepareCall("INSERT INTO CAMARA VALUES("+c.getId()+",'"+c.getMarca()+"','"+c.getModelo()+"',"+c.getPrecio()+","+"?"+")");
                        //is = getClass().getResourceAsStream("/resources/"+c.getModelo()+".png");
                         System.out.println(imagen);
                         is = new FileInputStream(imagen);
                        

                        ps.setBinaryStream(1, is);
   
			 try{
                             ps.executeUpdate();
			     //statement.executeUpdate(insert);
			     System.out.println("Inserción realizada");
			 }catch(SQLException sqle){
				 System.out.println("SQL Exception 1");
                                 sqle.printStackTrace();
                                 
			 }
			 
			 ps.close();
			 connection.close();
			 	 
						
		 }catch(ClassNotFoundException cnfe){  
				System.out.printf("Not found the jdbc driver %s\n");
		 }catch (SQLException sqle){
				System.out.println("SQL Exception 2");
                                sqle.printStackTrace();
		 } catch (FileNotFoundException ex) {   
                    
            try {
                //Si la imagen no se encuentra o no se selecciona, se usa una por defecto "/resources/default.png". 
                //Se obtiene mediante getClass().getResourceAsStream(imagen) ya que es un recurso guardado con el proyecto
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection=DriverManager.getConnection(Login.servidor, Login.usuario, Login.clave);
                PreparedStatement ps = null;
                InputStream is = null;
                ps = connection.prepareCall("INSERT INTO CAMARA VALUES("+c.getId()+",'"+c.getMarca()+"','"+c.getModelo()+"',"+c.getPrecio()+","+"?"+")");
                is = getClass().getResourceAsStream(imagen);
                ps.setBinaryStream(1, is);
                ps.executeUpdate();

                System.out.println("Inserción realizada");
                
                
            } catch (ClassNotFoundException ex1) {
                Logger.getLogger(CamaraBDD.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SQLException ex1) {
                Logger.getLogger(CamaraBDD.class.getName()).log(Level.SEVERE, null, ex1);
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
        String delete="DELETE FROM CAMARA WHERE ID="+id;
        
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
     * Obtiene la cámara con el modelo recibido 
     * @param modelo Recibe el modelo a buscar
     * @return Devuelve un objeto Camara con el modelo dado
     */
    @Override
    public Camara read(String modelo) {
        
        Camara c = null;
        
        //Se guarda la consulta por id recibido
        String query="SELECT * FROM CAMARA WHERE MODELO='"+modelo+"'";
				
	 try{	
	 	 
                 
             //Conexión a la BDD. La clase Login contiene los atributos con el servidor, usuario y clave para conectarse
            Class.forName("com.mysql.cj.jdbc.Driver");


            Connection connection=DriverManager.getConnection(Login.servidor, Login.usuario, Login.clave);

	     
		 Statement statement=connection.createStatement();
		 ResultSet result=statement.executeQuery(query);
		 
		 
		 while(result.next()){ 
                         //Crea el objeto con los campos guardados en la tabla
                         c = new Camara(result.getInt(1),result.getString(2),result.getString(3),result.getDouble(4));
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
     * Lista con todos las Camaras de la BDD
     * @return Devuelve una lista con todos las Camaras registradas
     */
    @Override
    public ArrayList<Camara> readAll() {
        
        ArrayList<Camara> camaras = new ArrayList();
        
        //Consulta que devuelve todos los registros de ACCESORIO
        String query="SELECT * FROM CAMARA";
				
	 try{	
	 	 
                 
         //Conexión a la BDD. La clase Login contiene los atributos con el servidor, usuario y clave para conectarse
            Class.forName("com.mysql.cj.jdbc.Driver");


            Connection connection=DriverManager.getConnection(Login.servidor, Login.usuario, Login.clave);

	     
		 Statement statement=connection.createStatement();
		 ResultSet result=statement.executeQuery(query);
		 
		 
		 while(result.next()){ 
                         //Crea el objeto y lo añade a la lista. Se realiza por cada registro encontrado
                         Camara c = new Camara(result.getInt(1),result.getString(2),result.getString(3),result.getDouble(4));
                         camaras.add(c);
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
        return camaras;
        
    }
    
    
        
        
    
    
}
