/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecohass;

import static ecohass.Administrador.PASSWORD;
import static ecohass.Administrador.URL;
import static ecohass.Administrador.USER;
import static ecohass.Administrador.fechactual;
import static ecohass.Administrador.ps;
import static ecohass.Administrador.ps2;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Jaime
 */
public class NuevoArbol extends javax.swing.JFrame {

    static FileInputStream entrada = null;
    static PreparedStatement ps, ps2;
    static ResultSet res, rr;
    static File hp = null;
    static boolean ci = false;

    public static byte[] AbrirArchivo(File archivo) {
        byte[] imagen = new byte[1024 * 100];
        try {
            entrada = new FileInputStream(archivo);
            entrada.read(imagen);
        } catch (Exception e) {

        }
        return imagen;
    }

    public static boolean existencia(String i) throws SQLException {
        Connection c = conexion();
        ps = c.prepareStatement("SELECT id FROM aguacates");
        rr = ps.executeQuery();
        int g = Integer.parseInt(i);
        while (rr.next()) {
            int ii = Integer.parseInt(rr.getString("id"));
            if (ii == g) {
                rr.close();
                return true;
            }
        }
        return false;
    }

    public static Connection conexion() {
        Connection c = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);//issue with driver manager about timezone +3
        } catch (Exception e) {
            System.out.println(e);
        }
        return c;
    }

    public static String fechactual() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MM/YYYY");
        return formatofecha.format(fecha);
    }

    public NuevoArbol() throws SQLException {
        initComponents();
        fs.setDate(new Date());
        fotico.setVisible(false);
        jButton2.setVisible(false);
        fechafoto.setVisible(false);
        jLabel9.setVisible(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        j.setBounds(0, 0,(int) screenSize.getWidth(), (int)screenSize.getHeight());
        ImageIcon icn = new ImageIcon(getClass().getResource("/imagenes/nuevo.gif"));
        Icon im = new ImageIcon(icn.getImage().getScaledInstance(j.getWidth(), j.getHeight(), Image.SCALE_DEFAULT));
        j.setIcon(im);
        /*wConnection c = conexion();
        ps2 = c.prepareStatement("SELECT fotoactual FROM aguacates");
        rr = ps2.executeQuery();
        while (rr.next()) {
            Blob blob = rr.getBlob(1);
            if (blob != null) {
                try {
                    byte[] data = blob.getBytes(1, (int) blob.length());
                    BufferedImage img = null;
                    try {
                        img = ImageIO.read(new ByteArrayInputStream(data));
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                    ImageIcon icono = new ImageIcon(img);
                    //JOptionPane.showMessageDialog(null, "Imagenes", "Imagen", JOptionPane.INFORMATION_MESSAGE, icono);

                } catch (Exception ex) {
                    //No hay imagen
                }
            } else {
                //No hay imagen
            }
        }*/

//primero me aseguro que no este vacío.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        nc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tp = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        edad = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        altura = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        fs = new com.toedter.calendar.JDateChooser();
        fotico = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        fechafoto = new com.toedter.calendar.JDateChooser();
        zona = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        ancho = new javax.swing.JTextField();
        j = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ECOHASS-FINCA LA ESPERANZA");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        jButton1.setText("Registrar");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 540, -1, -1));

        jLabel1.setFont(new java.awt.Font("Microsoft Tai Le", 1, 36)); // NOI18N
        jLabel1.setText("Registro de nuevo Arbol");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        jLabel2.setText("ID");
        jLabel2.setToolTipText("Número del árbol en el inventario");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 30, 20));

        id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                idKeyTyped(evt);
            }
        });
        getContentPane().add(id, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 110, 30));

        jLabel3.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        jLabel3.setText("ZONA");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, -1, -1));

        jLabel4.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        jLabel4.setText("NIVEL DE CRECIMIENTO");
        jLabel4.setToolTipText("");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 100, -1, -1));

        nc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ncActionPerformed(evt);
            }
        });
        getContentPane().add(nc, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 90, 130, 30));

        jLabel5.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        jLabel5.setText("TOTAL PRODUCIDO");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 160, -1, -1));
        getContentPane().add(tp, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 160, 130, 30));

        jLabel6.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        jLabel6.setText("EDAD");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, -1, -1));

        edad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                edadKeyTyped(evt);
            }
        });
        getContentPane().add(edad, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 210, 110, 30));

        jLabel7.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        jLabel7.setText("FECHA DE SIEMBRA");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 220, -1, -1));

        jLabel8.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        jLabel8.setText("ALTURA");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, -1, 20));
        getContentPane().add(altura, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 280, 110, 30));

        jButton2.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        jButton2.setText("CARGAR FOTO");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 350, -1, -1));
        getContentPane().add(fs, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 220, 130, 30));

        fotico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(fotico, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 270, 150, 200));

        jButton3.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/back.png"))); // NOI18N
        jButton3.setText("Atrás");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, -1, -1));

        jLabel9.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        jLabel9.setText("FECHA DE LA FOTO");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 490, -1, -1));
        getContentPane().add(fechafoto, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 490, 90, -1));

        zona.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "------------------", "zona 1", "zona 2", "zona 3", "zona 4", "zona 5" }));
        getContentPane().add(zona, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 150, 110, 30));

        jLabel10.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        jLabel10.setText("ANCHO");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 350, -1, -1));
        getContentPane().add(ancho, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 110, 30));

        j.setBackground(new java.awt.Color(255, 255, 255));
        j.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        j.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nuevo.gif"))); // NOI18N
        getContentPane().add(j, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, 590));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ncActionPerformed

    }//GEN-LAST:event_ncActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Cualquier tipo de imagen", "jpg", "jpeg", "png");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(this);
        byte[] imagen;
        hp = chooser.getSelectedFile();

        imagen = AbrirArchivo(hp);
        ImageIcon i = new ImageIcon(imagen);
        Icon im = new ImageIcon(i.getImage().getScaledInstance(fotico.getWidth(), fotico.getHeight(), Image.SCALE_DEFAULT));
        fotico.setIcon(im);
        ci = true;
    }//GEN-LAST:event_jButton2ActionPerformed

    private void idKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idKeyTyped
        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo se pueden ingresar números.");
        }
    }//GEN-LAST:event_idKeyTyped

    private void edadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edadKeyTyped

    }//GEN-LAST:event_edadKeyTyped

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        Connection c = conexion();
        try {
            if (!id.equals("") && !zona.getSelectedItem().equals("------------------")) {
                if (!existencia(id.getText())) {
                    // if (ci == true) {
                    ps = c.prepareStatement("INSERT INTO aguacates(id,zona,niveldecrecimiento,totalproducido,altura,ancho,edad,fechasiembra)VALUES(?,?,?,?,?,?,?,?)");
                    ps.setString(1, id.getText());
                    ps.setString(2, zona.getSelectedItem().toString());
                    ps.setString(3, nc.getText());
                    ps.setString(4, tp.getText());
                    ps.setString(5, altura.getText());
                    ps.setString(6, ancho.getText());
                    ps.setString(7, edad.getText());
                    ps.setString(8, fs.getDate().toString());
                    int i = ps.executeUpdate();
                    ps.close();
                    if (i > 0) {
                        JOptionPane.showMessageDialog(null, "Se ha registrado el nuevo arbol de aguacate con id " + id.getText());
                    }
                   /* if (hp != null){
                         entrada = new FileInputStream(String.valueOf(hp));
                    ps.setBinaryStream(9, entrada);
                    }else{
                        ps.setBinaryStream(9,0);
                    }
                       
                    if (fechafoto.getDate() != null) {
                        ps.setString(10, fechafoto.getDate().toString());
                    } else {
                        ps.setString(10, "null");
                    }

                    int i = ps.executeUpdate();
                    ps.close();
                    if (i > 0) {
                        JOptionPane.showMessageDialog(null, "Se ha registrado el nuevo arbol de aguacate con id " + id.getText());
                        ps2 = c.prepareStatement("INSERT INTO fotos(foto,id,fechadelafoto,fechadesubida)VALUES (?,?,?,?)");
                        entrada = new FileInputStream(String.valueOf(hp));
                        ps2.setBinaryStream(1, entrada);
                        ps2.setString(2, id.getText());
                        ps2.setString(3, fechafoto.getDate().toString());
                        ps2.setString(4, fechactual());
                        ps2.executeUpdate();
                        ps2.close();
                    }*/
                    // } else {
                    // JOptionPane.showMessageDialog(null, "Necesitas poner una foto para este árbol.", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                    // }
                } else {
                    JOptionPane.showMessageDialog(null, "Ya el árbol con ese ID ha sido registrado");
                }

            } else {
                if(id.equals("")){
                JOptionPane.showMessageDialog(null, "Debes proporcionar un id", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
            }else{
                    JOptionPane.showMessageDialog(null, "Debes proporcionar una zona", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(NuevoArbol.class.getName()).log(Level.SEVERE, null, ex);
        } /*catch (FileNotFoundException ex) {
            Logger.getLogger(NuevoArbol.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        menu m = new menu();
        this.dispose();
        m.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        /* try {
            Process p = Runtime.getRuntime().exec ("C:\\xampp\\xampp_stop");
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(NuevoArbol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevoArbol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevoArbol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevoArbol.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new NuevoArbol().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(NuevoArbol.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField altura;
    private javax.swing.JTextField ancho;
    private javax.swing.JTextField edad;
    private com.toedter.calendar.JDateChooser fechafoto;
    private javax.swing.JLabel fotico;
    private com.toedter.calendar.JDateChooser fs;
    private javax.swing.JTextField id;
    private javax.swing.JLabel j;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField nc;
    private javax.swing.JTextField tp;
    private javax.swing.JComboBox<String> zona;
    // End of variables declaration//GEN-END:variables
}