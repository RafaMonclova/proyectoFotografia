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
public class AccesorioBDD implements AccesorioDAO{
    
    String usuario = "usuario";
    String clave = "root";
    String url = "jdbc:mysql://localhost:3306/fotografia";
   

    @Override
    public void update(Accesorio a) {
        String update = "UPDATE ACCESORIO SET ID="+a.getId()+","+"MARCA='"+a.getMarca()+"',"+"MODELO='"+a.getModelo()+"',"+"PRECIO="+a.getPrecio()+",TIPO='"+a.getTipo()+"'WHERE ID="+a.getId();
           
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
    public void insert(Accesorio a) {
        
        
        
        try{	
		 	 
			 Class.forName("com.mysql.cj.jdbc.Driver");
						
			 Connection connection=DriverManager.getConnection(url, usuario, clave);
                         PreparedStatement ps = null;
                         InputStream is = null;
			ps = connection.prepareCall("INSERT INTO ACCESORIO VALUES("+a.getId()+",'"+a.getMarca()+"','"+a.getModelo()+"',"+a.getPrecio()+",'"+a.getTipo()+"',"+"?"+")");
                        is = new FileInputStream(new File(a.getModelo()+".png"));
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
			ps = connection.prepareCall("INSERT INTO ACCESORIO VALUES("+a.getId()+",'"+a.getMarca()+"','"+a.getModelo()+"',"+a.getPrecio()+",'"+a.getTipo()+"',"+"?"+")");
                        
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
                Logger.getLogger(AccesorioBDD.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (SQLException ex1) {
                Logger.getLogger(AccesorioBDD.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (FileNotFoundException ex1) {
                Logger.getLogger(AccesorioBDD.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        
        
    }

    @Override
    public void delete(int id) {
        
        String delete="DELETE FROM ACCESORIO WHERE ID="+id;
        
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
    public Accesorio read(int id) {
        
        Accesorio a = null;
        
        String query="SELECT * FROM ACCESORIO WHERE ID="+id;
				
	 try{	
	 	 
                 
         
            Class.forName("com.mysql.cj.jdbc.Driver");


            Connection connection=DriverManager.getConnection(url, usuario, clave);

	     
		 Statement statement=connection.createStatement();
		 ResultSet result=statement.executeQuery(query);
		 
		 
		 while(result.next()){ 
                         
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
    
    public ArrayList<Accesorio> readAll() {
        
        ArrayList<Accesorio> accesorios = new ArrayList();
        
        String query="SELECT * FROM ACCESORIO";
				
	 try{	
	 	 
                 
         
            Class.forName("com.mysql.cj.jdbc.Driver");


            Connection connection=DriverManager.getConnection(url, usuario, clave);

	     
		 Statement statement=connection.createStatement();
		 ResultSet result=statement.executeQuery(query);
		 
		 
		 while(result.next()){ 
                         
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
