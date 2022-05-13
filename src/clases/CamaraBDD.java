/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author alumno
 */
public class CamaraBDD {
    
    //-----MySQL
		static String MySQL_jdbcDriver="com.mysql.jdbc.Driver";
		//static String prefix="jdbc:"+"mysql:";
	
		//static String MySQL_url="jdbc:mysql://localhost/sqlite/BD/BDEmpleados";	
	
	
	 //----sqlite3
		static String sqlite_jdbd_driver="org.sqlite.JDBC";
		static String prefix="jdbc:mysql:";
		static String hostName="http://www.sqlfiddle.com/#!9/ce9812/1";
		static String urlFolder="";
		static String dbName="";
		
		//static String url="jdbc:sqlite:/home/pub/miguelb/ad/tema2/dbms/sqlite3/ejemplo.db"; //Linux
	    //static String url="jdbc:sqlite:D:/sqlite/BD/ejemplo.db";                            //Windows
		                
	   // static String url="jdbc:sqlite:C:\\Users\\migue\\Bibliotecas\\Documentos\\eclipse\\workspace-AD\\tema2.conectores\\ejemploJDBCGestionEmpleados\\src\\database\\ejemplo.db";

		static String url=prefix+hostName+urlFolder+dbName;

		//----jdbcOdbc
		//static String Access_jdbcDriver="sun.jdbc.odbc.JdbcOdbcDriver";
		//static String prefix="jdbc:"+"odbc:";
		
		//static String SQLite_url="jdbc:odbc:D:/sqlite/BD/BDGestionEmpleados";
	
		
	//---------------------------Actual DB parameters-------------------------------
	static String driver=MySQL_jdbcDriver;
	//static String url=prefix+hostName+urlFolder+dbName;
	static String user=""; //"user";
	static String password=""; //"password";
        
    public Camara creaCamara(){
        
    Scanner sc = new Scanner(System.in);
            
    System.out.println("Introduce id de la cámara");
    int id = sc.nextInt();
    sc.nextLine();
    System.out.println("Introduce marca");
    String marca = sc.nextLine();
    System.out.println("Introduce modelo");
    String modelo = sc.nextLine();
    System.out.println("Introduce precio");
    double precio = sc.nextDouble();
    System.out.println("Tiene accesorios? s/n");
    char accesorioSiNo = sc.next().charAt(0);
    Camara c = new Camara(id,marca,modelo,precio);
        
        if(accesorioSiNo == 's'){
            
            System.out.println("Cuántos accesorios desea añadir?");
            int numAccesorios = sc.nextInt();
            
            for (int i = 0; i < numAccesorios; i++) {
                
                System.out.println("--DATOS DEL ACCESORIO--");
                System.out.println("Introduce id");
                int idAcc = sc.nextInt();
                sc.nextLine();
                System.out.println("Introduce marca");
                String marcaAcc = sc.nextLine();
                System.out.println("Introduce nombre");
                String nombre = sc.nextLine();
                System.out.println("Introduce precio");
                double precioAcc = sc.nextDouble();
            
                Accesorio a = new Accesorio(idAcc,marcaAcc,nombre,precioAcc);
                c.añadirAccesorio(a);
                
            }
             
            
        }
            
        return c;
        
    }
    
    
        
        
    
    
}
