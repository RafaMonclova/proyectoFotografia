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
 * @author usuario
 */
public class CargarImagenCliente extends JInternalFrame{
    
    public CargarImagenCliente(String dni) 
  {
    super("Imagen");
    setSize(240, 240);
    
    setTitle("Imagen del producto");
    try { 
      //creation and execution of the request
      Class.forName("com.mysql.cj.jdbc.Driver");
						
      Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/fotografia", "usuario", "root");
      PreparedStatement statement = connection.prepareStatement("SELECT IMAGEN FROM CLIENTE WHERE DNI ='"+dni+"'");
      
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
            Logger.getLogger(CargarImagenCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    setVisible(true);
  }
    
}