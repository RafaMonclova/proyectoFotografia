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
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author RAFAEL MONCLOVA SUANO
 */
public class FacturaBDD implements FacturaDAO{
    
    /**
     * Actualiza el objeto en la base de datos
     * @param f Recibe el objeto a actualizar
     */
    @Override
    public void update(Factura f) {
        //Recibe los atributos de "f" y guarda la cadena
        String update = "UPDATE FACTURA SET CODIGO="+f.getCodigo()+","+"DNI='"+f.getDni()+"',"+"COMPRAS='"+f.listaAString()+"',"+"PRECIO="+f.getImporteTotal()+"WHERE CODIGO="+f.getCodigo();
           
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
     * @param f Recibe el objeto a insertar
     */
    @Override
    public void insert(Factura f) {
        
        
        
        
        try{	
		 	 //Conexión a la BDD. La clase Login contiene los atributos con el servidor, usuario y clave para conectarse
			 Class.forName("com.mysql.cj.jdbc.Driver");
						
			 Connection connection=DriverManager.getConnection(Login.servidor, Login.usuario, Login.clave);
                         //Se prepara el Statement con la cadena, y un InputStream creado con  la ruta de la imagen recibida
                         PreparedStatement ps = null;
                         InputStream is = null;
			ps = connection.prepareCall("INSERT INTO FACTURA VALUES("+f.getCodigo()+",'"+f.getDni()+"','"+f.listaAString()+"',"+f.getImporteTotal()+")");
                        
   
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
     * Borra un registro de la BDD por codigo de factura
     * @param codigo Recibe el codigo del registro a borrar
     */
    @Override
    public void delete(int codigo) {
        
        //Sentencia para eliminar por factura
        String delete="DELETE FROM FACTURA WHERE CODIGO="+codigo;
        
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
     * Obtiene la factura con el codigo recibido 
     * @param codigo Recibe el codigo a buscar
     * @return Devuelve un objeto Factura con el codigo dado
     */
    @Override
    public Factura read(int codigo) {
        
        Factura f = null;
        
        ArrayList<Compra> listaCompras = new ArrayList();
        
        //Se guarda la consulta por codigo recibido
        String query="SELECT * FROM FACTURA WHERE CODIGO="+codigo;
				
	 try{	
	 	 
                 
             //Conexión a la BDD. La clase Login contiene los atributos con el servidor, usuario y clave para conectarse
            Class.forName("com.mysql.cj.jdbc.Driver");


            Connection connection=DriverManager.getConnection(Login.servidor, Login.usuario, Login.clave);

	     
		 Statement statement=connection.createStatement();
		 ResultSet result=statement.executeQuery(query);
		 
		 
		 while(result.next()){ 
                         
                        String[] compras = result.getString(3).split("\\/");
                        
                        for (int i = 0; i < compras.length; i++) {
                         
                            String [] parametroCompra = compras[i].split("\\;");
                            
                            Compra c = new Compra(Integer.parseInt(parametroCompra[0]),parametroCompra[1],Integer.parseInt(parametroCompra[2]),Double.parseDouble(parametroCompra[3]));
                            
                            listaCompras.add(c);
                            
                        }
                     
                         
                         f = new Factura(result.getInt(1),result.getString(2),result.getDouble(4));
                         f.setCompras(listaCompras);
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
        return f;
        
    }
    
    /**
     * Lista con todos las Facturas de la BDD
     * @return Devuelve una lista con todos las Facturas registradas
     */
    @Override
    public ArrayList<Factura> readAll() {
        
        ArrayList<Compra> listaCompras = new ArrayList();
        ArrayList<Factura> facturas = new ArrayList();
        //Consulta que devuelve todos los registros de FACTURA
        String query="SELECT * FROM FACTURA";
				
	 try{	
	 	 
                 
         //Conexión a la BDD. La clase Login contiene los atributos con el servidor, usuario y clave para conectarse
            Class.forName("com.mysql.cj.jdbc.Driver");


            Connection connection=DriverManager.getConnection(Login.servidor, Login.usuario, Login.clave);

	     
		 Statement statement=connection.createStatement();
		 ResultSet result=statement.executeQuery(query);
		 
		 
		 while(result.next()){ 

                        String[] compras = result.getString(3).split("\\/");
                        
                        for (int i = 0; i < compras.length; i++) {
                         
                            String [] parametroCompra = compras[i].split("\\;");
                            
                            Compra c = new Compra(Integer.parseInt(parametroCompra[0]),parametroCompra[1],Integer.parseInt(parametroCompra[2]),Double.parseDouble(parametroCompra[3]));
                            
                            listaCompras.add(c);
                            
                            
                        }
                     
                         
                        Factura f = new Factura(result.getInt(1),result.getString(2),result.getDouble(4));
                        f.setCompras(listaCompras);
                        facturas.add(f);
                        listaCompras = new ArrayList();
                        
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
        return facturas;
        
    }
    
    
        
        
    
    
}
