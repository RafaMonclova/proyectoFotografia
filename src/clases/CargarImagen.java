/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.awt.Image;
import java.awt.Toolkit;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

/**
 *
 * @author RAFAEL MONCLOVA SUANO
 */
public class CargarImagen extends JInternalFrame{
    
    public CargarImagen(String clase, String modelo) 
  {
      
    super("Imagen");
    setSize(240, 240);
    
    setTitle("Imagen del producto");
    try { 
      
      //Establece la conexión con la BDD  
      Class.forName("com.mysql.cj.jdbc.Driver");
						
      Connection connection=DriverManager.getConnection(Login.servidor, Login.usuario, Login.clave);
      
      //Selecciona el valor del campo IMAGEN de la tabla y ejecuta la consulta
      PreparedStatement statement = connection.prepareStatement("SELECT IMAGEN FROM "+clase+" WHERE MODELO ='"+modelo+"'");
      
      ResultSet res = statement.executeQuery();
      //Obtiene la imagen como un array de bytes
      byte[] image = null;
      while (res.next()) {
        image = res.getBytes("IMAGEN");
      }
      //Crea la imagen y la muestra en la ventana
      Image img = Toolkit.getDefaultToolkit().createImage(image);
      ImageIcon icone = new ImageIcon(img);
      JLabel l = new JLabel();
      l.setIcon(icone);
      add(l);
    } catch (SQLException e) {
      e.printStackTrace();
    }   catch (ClassNotFoundException ex) {
            Logger.getLogger(CargarImagen.class.getName()).log(Level.SEVERE, null, ex);
        }
    setVisible(true);
  }
    
}
