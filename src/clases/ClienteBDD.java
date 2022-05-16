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
 * @author alumno
 */
public class ClienteBDD implements ClienteDAO{
    
    String usuario = "usuario";
    String clave = "root";
    String url = "jdbc:mysql://localhost:3306/fotografia";
   

    @Override
    public void update(Cliente c) {
        String update = "UPDATE ACCESORIO SET DNI='"+c.getDni()+"',"+"NOMBRE='"+c.getNombre()+"',"+"APELLIDOS='"+c.getApellidos()+"',"+"DIRECCION='"+c.getDireccion()+"',TELEFONO="+c.getTelefono()+", HABITUAL="+c.isHabitual()+"WHERE DNI="+c.getDni();
           
		 try{	
		 	 
			 Class.forName("com.mysql.cj.jdbc.Driver");
						
			 Connection connection=DriverManager.getConnection(url, usuario, clave);
			
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

    @Override
    public void insert(Cliente c) {
        
        
        
        try{	
		 	 
			 Class.forName("com.mysql.cj.jdbc.Driver");
						
			 Connection connection=DriverManager.getConnection(url, usuario, clave);
                         PreparedStatement ps = null;
                         InputStream is = null;
			ps = connection.prepareCall("INSERT INTO CLIENTE VALUES('"+c.getDni()+"','"+c.getNombre()+"','"+c.getApellidos()+"','"+c.getDireccion()+"',"+c.getTelefono()+","+c.isHabitual()+","+"?"+")");
                        is = new FileInputStream(new File(c.getDni()+".png"));
                        ps.setBinaryStream(1, is);
			 //Statement statement=connection.createStatement();
			 
			 
			 try{
                             ps.executeUpdate();
			     //statement.executeUpdate(insert);
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
                      try{	
		 	 
			 Class.forName("com.mysql.cj.jdbc.Driver");
						
			 Connection connection=DriverManager.getConnection(url, usuario, clave);
                         PreparedStatement ps = null;
                         InputStream is = null;
			ps = connection.prepareCall("INSERT INTO CLIENTE VALUES('"+c.getDni()+"','"+c.getNombre()+"','"+c.getApellidos()+"','"+c.getDireccion()+"',"+c.getTelefono()+","+c.isHabitual()+","+"?"+")");
                        
                        is = new FileInputStream(new File("default.png"));
                        ps.setBinaryStream(1, is);
			 //Statement statement=connection.createStatement();
			 
			 
			 try{
                             ps.executeUpdate();
			     //statement.executeUpdate(insert);
			     System.out.println("Inserción realizada");
			 }catch(SQLException sqle){
				 System.out.println("SQL Exception 3");
			 }
			 
			 ps.close();
			 connection.close();
			 	 
						
		 } catch (ClassNotFoundException ex1) {
                Logger.getLogger(ClienteBDD.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SQLException ex1) {
                Logger.getLogger(ClienteBDD.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (FileNotFoundException ex1) {
                Logger.getLogger(ClienteBDD.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
        
    }

    @Override
    public void delete(String dni) {
        
        String delete="DELETE FROM CLIENTE WHERE DNI='"+dni+"'";
        
        try{	
		 	 
			 Class.forName("com.mysql.cj.jdbc.Driver");
						
			 Connection connection=DriverManager.getConnection(url, usuario, clave);
			
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

    @Override
    public Cliente read(String dni) {
        
        Cliente c = null;
        
        String query="SELECT * FROM CLIENTE WHERE DNI='"+dni+"'";
				
	 try{	
	 	 
                 
         
            Class.forName("com.mysql.cj.jdbc.Driver");


            Connection connection=DriverManager.getConnection(url, usuario, clave);

	     
		 Statement statement=connection.createStatement();
		 ResultSet result=statement.executeQuery(query);
		 
		 
		 while(result.next()){ 
                         
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
    
    public ArrayList<Cliente> readAll() {
        
        ArrayList<Cliente> clientes = new ArrayList();
        
        String query="SELECT * FROM CLIENTE";
				
	 try{	
	 	 
                 
         
            Class.forName("com.mysql.cj.jdbc.Driver");


            Connection connection=DriverManager.getConnection(url, usuario, clave);

	     
		 Statement statement=connection.createStatement();
		 ResultSet result=statement.executeQuery(query);
		 
		 
		 while(result.next()){ 
                         
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
