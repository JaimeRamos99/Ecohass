package ecohass;

import com.toedter.calendar.JDateChooser;
import static ecohass.Administrador.PASSWORD;
import static ecohass.Administrador.URL;
import static ecohass.Administrador.USER;
import static ecohass.NuevoArbol.hp;
import static ecohass.NuevoArbol.ps;
import static ecohass.NuevoArbol.ps2;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

//
/**
 *
 * @author Jaime
 */
public class Perfil extends javax.swing.JFrame {

    /**
     * Creates new form Perfil
     *
     * @param fruta
     * @param id
     */
    FileInputStream entrada;
    static ResultSet rr;
    int ruta, ids, prr;
    static DefaultListModel l1, l2, l3, l4;
    static String ac1 = "", ac2 = "", ac3 = "", ac4 = "";
    static PreparedStatement pp;
    static ByteArrayOutputStream ouput = new ByteArrayOutputStream();
    static String lazona;
    Image anuel;
    public static Connection conexion() {
        Connection c = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println(e);
        }
        return c;
    }
    private Image  ZoomImage(int w, int h, Image img){
        BufferedImage buf=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
        Graphics2D grf =buf.createGraphics();
        grf.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        grf.drawImage(img,0,0,w,h,null);
        grf.dispose();
        return buf;
    }
    public static String lafecha(String f) {
        String mes = "";

        switch (f.substring(4, 7)) {
            case "Jan":
                mes = "Enero";
                break;
            case "Feb":
                mes = "Febrero";
                break;
            case "Mar":
                mes = "Marzo";
                break;
            case "Apr":
                mes = "Abril";
                break;
            case "May":
                mes = "Mayo";
                break;
            case "Jun":
                mes = "Junio";
                break;
            case "Jul":
                mes = "Julio";
                break;
            case "Aug":
                mes = "Agosto";
                break;
            case "Sep":
                mes = "Septiembre";
                break;
            case "Oct":
                mes = "Octubre";
                break;
            case "Nov":
                mes = "Noviembre";
                break;
            case "Dec":
                mes = "Diciembre";
                break;

        }
        return f.substring(8, 10) + " de " + mes + " del " + f.substring(24, 28);
    }

    public static String fechactual() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MM/YYYY");
        return formatofecha.format(fecha);
    }

    public Perfil(String fruta, int id, int pr) throws SQLException {
        initComponents();
        prr = pr;
        if (pr == 0) {
            editar.setEnabled(false);
            jButton2.setEnabled(false);
        }
        l1 = new DefaultListModel();
        l2 = new DefaultListModel();
        l3 = new DefaultListModel();
        l4 = new DefaultListModel();
        this.setExtendedState(MAXIMIZED_BOTH);
        ruta = pr;
        ids = id;
        area.setBackground(Color.BLACK);
        Connection c = conexion();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLayout(null);
        j.setBounds(0, 0, (int) screenSize.getWidth(), (int) screenSize.getHeight());
        ImageIcon icn = new ImageIcon(getClass().getResource("/imagenes/caminante.gif"));
        Icon im = new ImageIcon(icn.getImage().getScaledInstance(j.getWidth(), j.getHeight(), Image.SCALE_DEFAULT));
        j.setIcon(im);
        ps = c.prepareStatement("SELECT * FROM aguacates WHERE id=\"" + ids + "\"");
        rr = ps.executeQuery();
        while (rr.next()) {
            try {
                lazona = rr.getString("zona");
                dos.setText(dos.getText() + " " + lazona);
                tres.setText(tres.getText() + " " + rr.getString("niveldecrecimiento"));
                cuatro.setText(cuatro.getText() + " " + rr.getString("totalproducido"));
                cinco.setText(cinco.getText() + " " + rr.getString("altura"));
                seis.setText(seis.getText() + " " + rr.getString("edad"));
                siete.setText(siete.getText() + " " + rr.getString("fechasiembra"));
                ocho.setText(ocho.getText() + " " + rr.getString("fechafoto"));
                nueve.setText(nueve.getText() + " " + rr.getString("ancho"));
                diez.setText(diez.getText()+ " "+rr.getString("salud"));
                Blob blob = rr.getBlob("fotoactual");
                if (blob != null) {
                    byte[] data = blob.getBytes(1, (int) blob.length());
                    BufferedImage img = ImageIO.read(new ByteArrayInputStream(data));
                     anuel=(Image)img;
                    try {
                        ImageIcon icono = new ImageIcon(img);
                        //JOptionPane.showMessageDialog(null, "Imagenes", "Imagen", JOptionPane.INFORMATION_MESSAGE, icono);
                        Icon ima = new ImageIcon(icono.getImage().getScaledInstance(foto.getWidth(), foto.getHeight(), Image.SCALE_DEFAULT));
                        foto.setIcon(ima);
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            } catch (IOException | SQLException ex) {
                //No hay imagen
            }

        }
        ps = c.prepareStatement("SELECT tarea,tipo FROM tareas WHERE receptor=\"" + id + "\"or receptor LIKE '%" + lazona + "%' or receptor LIKE '%Todos los arboles%'");
        rr = ps.executeQuery();
        while (rr.next()) {
            if (rr.getString("tipo").equals("SALUD")) {
                l1.addElement(rr.getString("tarea"));
            } else {
                if (rr.getString("tipo").equals("NUTRICIÓN")) {
                    l2.addElement(rr.getString("tarea"));
                } else {
                    if (rr.getString("tipo").equals("CUIDADO Y MANTENIMIENTO")) {
                        l3.addElement(rr.getString("tarea"));
                    } else {
                        l4.addElement(rr.getString("tarea"));
                    }
                }
            }
        }
        lista1.setModel(l1);
        lista2.setModel(l2);
        lista3.setModel(l3);
        lista4.setModel(l4);
        if (fruta.equals("a")) {
            titulo.setText("Arbol de aguacate #" + id);
        } else {
            if (fruta.equals("t")) {
                titulo.setText("Arbol de tomate de arbol #" + id);
            }
            this.add(titulo);
        }
        ps = c.prepareCall("SELECT observacion,fecha FROM observaciones WHERE idaguacate=\"" + id + "\"");
        rr = ps.executeQuery();
        String aa = "";
        String newline = System.lineSeparator();
        while (rr.next()) {
            aa = aa + rr.getString("fecha") + ": " + rr.getString("observacion") + "." + newline;
        }
        area.setText(aa);
    }

    private Perfil() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public byte[] AbrirArchivo(File archivo) {
        byte[] imagen = new byte[1024 * 100];
        try {
            entrada = new FileInputStream(archivo);
            entrada.read(imagen);
        } catch (Exception e) {

        }
        return imagen;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        lista1 = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lista2 = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        lista3 = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        lista4 = new javax.swing.JList<>();
        jScrollPane5 = new javax.swing.JScrollPane();
        area = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        dos = new javax.swing.JLabel();
        tres = new javax.swing.JLabel();
        cuatro = new javax.swing.JLabel();
        cinco = new javax.swing.JLabel();
        seis = new javax.swing.JLabel();
        siete = new javax.swing.JLabel();
        ocho = new javax.swing.JLabel();
        nueve = new javax.swing.JLabel();
        editar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        zoomin = new javax.swing.JButton();
        zoomout = new javax.swing.JButton();
        foto2 = new javax.swing.JScrollPane();
        foto = new javax.swing.JLabel();
        diez = new javax.swing.JLabel();
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

        titulo.setFont(new java.awt.Font("Microsoft Tai Le", 1, 36)); // NOI18N
        titulo.setText("PERFIL");
        getContentPane().add(titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 500, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/back.png"))); // NOI18N
        jButton1.setText("Atrás");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 630, 103, 51));

        lista1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lista1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 280, 270));

        jLabel2.setText("Tareas relacionadas con la salud");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, -1, -1));

        jLabel3.setText("Tareas relacionadas con la nutrición");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(308, 310, -1, -1));

        jLabel4.setText("Tareas relacionadas con cuidado y mantenimiento");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 310, -1, -1));

        jLabel5.setText("tareas relacionadas con la cosecha");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(977, 310, -1, -1));

        lista2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "FECHA: 2 DE MARZO---se le suministró droxipanina la orden fue dad por jaime y cada 5 días se le echa, no importa lo que pase uuuuuuuuuuuuuuuuuu probando.", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(lista2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(296, 330, 360, 270));

        lista3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(lista3);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(667, 330, 300, 270));

        lista4.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane4.setViewportView(lista4);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(977, 330, 300, 270));

        area.setColumns(20);
        area.setLineWrap(true);
        area.setRows(5);
        area.setEnabled(false);
        jScrollPane5.setViewportView(area);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 120, 305, 171));

        jLabel6.setText("Observaciones adicionales");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 100, -1, -1));

        dos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        dos.setText("Zona:");
        getContentPane().add(dos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 180, -1));

        tres.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tres.setText("Nivel de crecimiento:");
        getContentPane().add(tres, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 240, -1));

        cuatro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cuatro.setText("Total producido:");
        getContentPane().add(cuatro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 270, -1));

        cinco.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cinco.setText("Altura:");
        getContentPane().add(cinco, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 130, -1));

        seis.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        seis.setText("Edad:");
        getContentPane().add(seis, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 230, -1));

        siete.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        siete.setText("Fecha en que se sembró:");
        getContentPane().add(siete, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 320, -1));

        ocho.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ocho.setText("Fecha de la foto:");
        getContentPane().add(ocho, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 310, -1));

        nueve.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nueve.setText("Ancho:");
        getContentPane().add(nueve, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 140, -1));

        editar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        editar.setText("Editar información del arbol");
        editar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editarMouseClicked(evt);
            }
        });
        getContentPane().add(editar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, -1, -1));

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton2.setText("Actualizar Foto");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 50, 197, -1));

        zoomin.setBackground(new java.awt.Color(204, 204, 204));
        zoomin.setForeground(new java.awt.Color(204, 204, 204));
        zoomin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/zoom.png"))); // NOI18N
        zoomin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoominActionPerformed(evt);
            }
        });
        getContentPane().add(zoomin, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 10, 60, 40));

        zoomout.setBackground(new java.awt.Color(204, 204, 204));
        zoomout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/nzoom.png"))); // NOI18N
        zoomout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoomoutActionPerformed(evt);
            }
        });
        getContentPane().add(zoomout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 60, 60, 40));

        foto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        foto2.setViewportView(foto);

        getContentPane().add(foto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 10, 210, 280));

        diez.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        diez.setText("Estado de salud:");
        getContentPane().add(diez, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 80, 220, -1));

        j.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/caminante.gif"))); // NOI18N
        getContentPane().add(j, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 2, 1420, 1190));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        Inicio n = new Inicio(ruta);
        n.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void editarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editarMouseClicked
        if (prr != 0) {
            EdicionDeArbol ed = new EdicionDeArbol(ids, ruta);
            ed.setVisible(true);
            this.dispose();
        }

    }//GEN-LAST:event_editarMouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        if (prr != 0) {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Cualquier tipo de imagen", "jpg", "jpeg", "png");
            chooser.setFileFilter(filter);
            chooser.showOpenDialog(this);
            byte[] imagen;
            try {
                hp = chooser.getSelectedFile();
                if (hp != null) {
                    entrada = new FileInputStream(String.valueOf(hp));
                    imagen = AbrirArchivo(hp);
                    ImageIcon i = new ImageIcon(imagen);
                    Icon im = new ImageIcon(i.getImage().getScaledInstance(j.getWidth(), j.getHeight(), Image.SCALE_DEFAULT));
                    foto.setIcon(im);
                    Connection c = conexion();
                    JDateChooser jd = new JDateChooser();
                    jd.setDate(null);
                    String message = "Ingrese la fecha de la foto:\n";
                    Object[] params = {message, jd};
                    int g = JOptionPane.showConfirmDialog(null, params, "Fecha", JOptionPane.PLAIN_MESSAGE);
                    ps2 = c.prepareStatement("INSERT INTO fotos(foto,id,fechadelafoto,fechadesubida)VALUES (?,?,?,?)");
                    ps2.setBinaryStream(1, entrada);
                    ps2.setString(2, "" + ids);
                    ps2.setString(3, jd.getDate().toString());
                    ps2.setString(4, fechactual());
                    ps2.executeUpdate();
                    ps2.close();
                    pp = c.prepareStatement("UPDATE aguacates SET fotoactual = ? , fechafoto = ? WHERE id=" + ids);
                    entrada = new FileInputStream(String.valueOf(hp));
                    pp.setBinaryStream(1, entrada);
                    String gh = lafecha(jd.getDate().toString());
                    pp.setString(2, gh);
                    ocho.setText("Fecha de la foto: " + gh);
                    int t = pp.executeUpdate();
                    if (t == 1) {
                        JOptionPane.showMessageDialog(null, "Se ha cambiado la foto con éxito.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se seleccionó niguna foto");
                }
            } catch (SQLException | FileNotFoundException ex) {
                Logger.getLogger(Perfil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton2MouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        /*try {
            Process p = Runtime.getRuntime().exec("C:\\xampp\\xampp_stop");
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_formWindowClosing

    private void zoominActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoominActionPerformed
        int w=foto.getWidth();
        int h=foto.getHeight();
        ImageIcon ii=new ImageIcon(ZoomImage(w+100, h+100, anuel));
        foto.setIcon(ii);
    }//GEN-LAST:event_zoominActionPerformed

    private void zoomoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoomoutActionPerformed
        int w=foto.getWidth();
        int h=foto.getHeight();
        ImageIcon ii=new ImageIcon(ZoomImage(w-100, h-100, anuel));
        foto.setIcon(ii);
    }//GEN-LAST:event_zoomoutActionPerformed

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
            java.util.logging.Logger.getLogger(Perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Perfil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Perfil().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea area;
    private javax.swing.JLabel cinco;
    private javax.swing.JLabel cuatro;
    private javax.swing.JLabel diez;
    private javax.swing.JLabel dos;
    private javax.swing.JButton editar;
    private javax.swing.JLabel foto;
    private javax.swing.JScrollPane foto2;
    private javax.swing.JLabel j;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JList<String> lista1;
    private javax.swing.JList<String> lista2;
    private javax.swing.JList<String> lista3;
    private javax.swing.JList<String> lista4;
    private javax.swing.JLabel nueve;
    private javax.swing.JLabel ocho;
    private javax.swing.JLabel seis;
    private javax.swing.JLabel siete;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel tres;
    private javax.swing.JButton zoomin;
    private javax.swing.JButton zoomout;
    // End of variables declaration//GEN-END:variables

}
