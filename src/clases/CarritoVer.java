/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package clases;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author RAFAEL MONCLOVA SUANO
 */
public class CarritoVer extends javax.swing.JFrame {

    private int id = 0;
    JButton button = new JButton();
    DefaultTableModel tbl;
    
    //Producto siguiente;
            
    
    public CarritoVer() throws ClassNotFoundException {
        //Establece el fondo de la ventana
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResourceAsStream("/resources/fondo.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(800, 508, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        setContentPane(new JLabel(imageIcon));
        
        initComponents();
        
        //Esta linea hace que puedan ordenarse las lineas de la tabla según la columna donde se haga click
        jTable1.setAutoCreateRowSorter(true);
        jLabel1.setOpaque(true);
        
        //Crea un evento que al pulsar sobre una fila, se abra una ventana con la información de ese producto
        MouseAdapter evento = new MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
            int row = jTable1.getSelectedRow();
            
          
            int codigo = (int)jTable1.getModel().getValueAt(row, 0);
            CompraBDD compras = new CompraBDD();
            Compra c = compras.read(codigo);
            
            
            
            
            DatosCompra datos = new DatosCompra(""+c.getCodigo(),c.getModelo(),""+c.getCantidad(),""+c.getPrecio());
            datos.setVisible(true);
        }
        };
    jTable1.addMouseListener(evento);
    
        CompraBDD compras = new CompraBDD();
        ArrayList<Compra> lista = compras.readAll();
   
        
        for (int i = 0; i < lista.size(); i++) {
            //Por cada objeto de la lista, se crea la fila de la tabla con los valores de los atributos
            
            
                CamaraBDD camaras = new CamaraBDD();
                AccesorioBDD accesorios = new AccesorioBDD();
                
                if(camaras.read(lista.get(i).getModelo()) != null){
                    
                    Camara cam = camaras.read(lista.get(i).getModelo());
                    Compra c = new Compra(lista.get(i).getCodigo(),cam.getModelo(),lista.get(i).getCantidad(),cam.getPrecio()*(double)(lista.get(i).getCantidad()));
            
                    compras.update(c);
            
                    Object[] row = { lista.get(i).getCodigo(), lista.get(i).getModelo(), c.getCantidad(), c.getPrecio() };
            
                    tbl = (DefaultTableModel)jTable1.getModel();
            
                    tbl.addRow(row);
                    
                    //siguiente = camaras.read(lista.get(i+1).getModelo());
                    
                }
                else{
                    
                    Accesorio acc = accesorios.read(lista.get(i).getModelo());
                    Compra c = new Compra(lista.get(i).getCodigo(),acc.getModelo(),lista.get(i).getCantidad(),acc.getPrecio()*(double)(lista.get(i).getCantidad()));
            
                    compras.update(c);
            
                    Object[] row = { lista.get(i).getCodigo(), lista.get(i).getModelo(), c.getCantidad(), c.getPrecio() };
            
                    tbl = (DefaultTableModel)jTable1.getModel();
            
                    tbl.addRow(row);
                    
                    //siguiente = accesorios.read(lista.get(i+1).getModelo());
                    
                }
                        
            
                
                
        }

        jLabel2.setText("SE HAN ENCONTRADO "+lista.size()+" REGISTROS");
        
    }
    
    
    /**
     * Método que actualiza los datos mostrados en la tabla
     */
    public void actualizarTabla(){
        
        //Borra todos los datos de la tabla y los vuelve a cargar con información actualizada de la BDD
        tbl.setRowCount(0);
        CompraBDD compras = new CompraBDD();
        ArrayList<Compra> lista = compras.readAll();
        
       
        for (int i = 0; i < lista.size(); i++) {
            //Por cada objeto de la lista, se crea la fila de la tabla con los valores de los atributos
            
            
                CamaraBDD camaras = new CamaraBDD();
                AccesorioBDD accesorios = new AccesorioBDD();
                
                if(camaras.read(lista.get(i).getModelo()) != null){
                    
                    Camara cam = camaras.read(lista.get(i).getModelo());
                    Compra c = new Compra(lista.get(i).getCodigo(),cam.getModelo(),lista.get(i).getCantidad(),cam.getPrecio()*(double)(lista.get(i).getCantidad()));
            
                    compras.update(c);
            
                    Object[] row = { lista.get(i).getCodigo(), lista.get(i).getModelo(), c.getCantidad(), c.getPrecio() };
            
                    tbl = (DefaultTableModel)jTable1.getModel();
            
                    tbl.addRow(row);
                    
                }
                else{
                    
                    Accesorio acc = accesorios.read(lista.get(i).getModelo());
                    Compra c = new Compra(lista.get(i).getCodigo(),acc.getModelo(),lista.get(i).getCantidad(),acc.getPrecio()*(double)(lista.get(i).getCantidad()));
            
                    compras.update(c);
            
                    Object[] row = { lista.get(i).getCodigo(), lista.get(i).getModelo(), c.getCantidad(), c.getPrecio() };
            
                    tbl = (DefaultTableModel)jTable1.getModel();
            
                    tbl.addRow(row);
                    
                }
   
            
        }

        jLabel2.setText("SE HAN ENCONTRADO "+lista.size()+" REGISTROS");
       
        
    }
    
        

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setTitle("LISTADO CÁMARAS");
        setResizable(false);

        jLabel1.setBackground(new java.awt.Color(102, 102, 255));
        jLabel1.setFont(new java.awt.Font("Fira Sans Heavy", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CARRITO");
        jLabel1.setMaximumSize(new java.awt.Dimension(100, 100));
        jLabel1.setMinimumSize(new java.awt.Dimension(100, 100));
        jLabel1.setName(""); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(100, 100));

        jLabel2.setFont(new java.awt.Font("Fira Sans", 3, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CARRITO");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "MODELO", "CANTIDAD", "PRECIO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Borrar del carrito");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Actualizar carrito");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(354, 354, 354)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 681, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        
        int row = jTable1.getSelectedRow();
        String id = jTable1.getModel().getValueAt(row, 0).toString();
                
        //Borra de la BDD y de la tabla de la ventana       
        CamaraBDD camaras = new CamaraBDD();
        camaras.delete(Integer.parseInt(id));
        tbl.removeRow(row);
                
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Botón para actualizar la tabla de la ventana
     * @param evt 
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        actualizarTabla();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CarritoVer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CarritoVer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CarritoVer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CarritoVer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new CarritoVer().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(CarritoVer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
