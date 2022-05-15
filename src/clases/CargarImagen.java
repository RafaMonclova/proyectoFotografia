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
import javax.swing.JLabel;

/**
 *
 * @author usuario
 */
public class CargarImagen extends JFrame{
    
    public CargarImagen(String modelo) 
  {
    super("Imagen");
    setSize(300, 300);
    
    
    try { 
      //creation and execution of the request
      Class.forName("com.mysql.cj.jdbc.Driver");
						
      Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/fotografia", "usuario", "root");
      PreparedStatement statement = connection.prepareStatement("SELECT IMAGEN FROM CAMARA WHERE MODELO ='"+modelo+"'");
      ResultSet res = statement.executeQuery();
      //get image as byte
      byte[] image = null;
      while (res.next()) {
        image = res.getBytes("IMAGEN");
      }
      //create the image 
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
