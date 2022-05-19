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
public class CompraBDD implements CompraDAO{
    
    /**
     * Actualiza el objeto en la base de datos
     * @param c Recibe el objeto a actualizar
     */
    @Override
    public void update(Compra c) {
        //Recibe los atributos de "c" y guarda la cadena
        String update = "UPDATE COMPRA SET CODIGO="+c.getCodigo()+","+"MODELO='"+c.getModelo()+"',"+"CANTIDAD="+c.getCantidad()+","+"PRECIO="+c.getPrecio()+"WHERE CODIGO="+c.getCodigo();
           
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
     */
    @Override
    public void insert(Compra c) {
        
        
        
        try{	
		 	 //Conexión a la BDD. La clase Login contiene los atributos con el servidor, usuario y clave para conectarse
			 Class.forName("com.mysql.cj.jdbc.Driver");
						
			 Connection connection=DriverManager.getConnection(Login.servidor, Login.usuario, Login.clave);
                         //Se prepara el Statement con la cadena, y un InputStream creado con  la ruta de la imagen recibida
                         PreparedStatement ps = null;
                         InputStream is = null;
			ps = connection.prepareCall("INSERT INTO COMPRA VALUES("+c.getCodigo()+",'"+c.getModelo()+"',"+c.getCantidad()+","+c.getPrecio()+")");
                        
   
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
		 }
        
        
        
    }

    /**
     * Borra un registro de la BDD por codigo de compra
     * @param codigo Recibe el codigo del registro a borrar
     */
    @Override
    public void delete(int codigo) {
        
        //Sentencia para eliminar por compra
        String delete="DELETE FROM COMPRA WHERE CODIGO="+codigo;
        
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
     * Obtiene la compra con el codigo recibido 
     * @param codigo Recibe el codigo a buscar
     * @return Devuelve un objeto Compra con el codigo dado
     */
    @Override
    public Compra read(int codigo) {
        
        Compra c = null;
        
        //Se guarda la consulta por codigo recibido
        String query="SELECT * FROM COMPRA WHERE CODIGO="+codigo+"";
				
	 try{	
	 	 
                 
             //Conexión a la BDD. La clase Login contiene los atributos con el servidor, usuario y clave para conectarse
            Class.forName("com.mysql.cj.jdbc.Driver");


            Connection connection=DriverManager.getConnection(Login.servidor, Login.usuario, Login.clave);

	     
		 Statement statement=connection.createStatement();
		 ResultSet result=statement.executeQuery(query);
		 
		 
		 while(result.next()){ 
                         //Crea el objeto con los campos guardados en la tabla
                         c = new Compra(result.getInt(1),result.getString(2),result.getInt(3),result.getDouble(4));
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
     * Lista con todos las Compras de la BDD
     * @return Devuelve una lista con todos las Compras registradas
     */
    @Override
    public ArrayList<Compra> readAll() {
        
        ArrayList<Compra> compras = new ArrayList();
        
        //Consulta que devuelve todos los registros de COMPRA
        String query="SELECT * FROM COMPRA";
				
	 try{	
	 	 
                 
         //Conexión a la BDD. La clase Login contiene los atributos con el servidor, usuario y clave para conectarse
            Class.forName("com.mysql.cj.jdbc.Driver");


            Connection connection=DriverManager.getConnection(Login.servidor, Login.usuario, Login.clave);

	     
		 Statement statement=connection.createStatement();
		 ResultSet result=statement.executeQuery(query);
		 
		 
		 while(result.next()){ 
                         //Crea el objeto y lo añade a la lista. Se realiza por cada registro encontrado
                         Compra c = new Compra(result.getInt(1),result.getString(2),result.getInt(3),result.getDouble(4));
                         compras.add(c);
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
        return compras;
        
    }
    
    
        
        
    
    
}
