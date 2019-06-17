package ecohass;

import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

/**
 *
 * @author Jaime
 */
public class Administrador extends javax.swing.JFrame {

    /**
     * Creates new form Administrador
     */
    //SET GLOBAL time_zone = '-3:00' java.sql.SQLException: The server time zone value 'Hora est. PacÃ­fico, SudamÃ©r' is unrecognized or represents more than one time zone. You must configure either the server or JDBC driver (via the serverTimezone configuration property) to use a more specifc time zone value if you want to utilize time zone support.
    static String[] datos = new String[1000000];
    static String[] datos2 = new String[1000000];
    public static final String URL = "jdbc:mysql://www.gises.com:3306/rossette_tareasecohass?useTimezone=true&serverTimezone=UTC";
    public static final String USER = "rossette";
    public static final String PASSWORD = "jireh1963";
    static PreparedStatement ps, ps2;
    static ResultSet res, rr, r2;
    static JList l = new JList();
    static DefaultListModel dm = new DefaultListModel();
    static DefaultComboBoxModel modelo;
    static String arboles[];

    public static Connection conexion() {
        Connection c = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(URL, USER, PASSWORD);//issue with driver manager about timezone +3
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return c;
    }

    public static boolean verificadorcedulaentero(String cedula) {
        try {
            int r = Integer.parseInt(cedula);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Object james(String h) {
        Object o;
        if (h.equals("no cumplida")) {
            o = 0;
        } else {
            o = 1;
        }
        return o;
    }

    public static String nuevacadena(String tarea) {
        String newline = System.lineSeparator();
        String nuevo = "";

        for (int i = 0; i < tarea.length(); i++) {
            if (i % 30 == 0 && i > 0) {
                nuevo = nuevo + newline;
            } else {
                nuevo = nuevo + tarea.substring(i, i + 1);
            }
        }
        return nuevo;
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
        return f.substring(8, 10) + " de " + mes + " del " + f.substring(24, 28) + " a las " + f.substring(11, 19);
    }

    public void actualizar(int r, int dif) throws SQLException {
        Connection c = conexion();
        if (r > 0) {
            dm.clear();
            if (dif == 0) {
                JOptionPane.showMessageDialog(null, "Tarea creada exitosamente.");
                ce.setText("");
                ntxt.setText("");
                txt.setText("");
                a.setText("");
                combo2.setSelectedIndex(0);
                dtx.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(null, "Tarea actualizada exitosamente.");
            }
            ps2 = c.prepareStatement("SELECT titulo,estado FROM tareas");
            rr = ps2.executeQuery();
            int contador = 0;
            while (rr.next()) {
                datos[contador] = rr.getString("titulo");
                clase k = new clase(rr.getString("titulo"), rr.getString("estado"));
                dm.addElement(k);
                contador++;
            }
            l2.setCellRenderer(new CustomRenderer());
            l2.setModel(dm);
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo llevar a cabo la solicitud.", "ADVERTENCIA", JOptionPane.ERROR_MESSAGE);
        }
        c.close();
    }

    public static String fechactual() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MM/YYYY");
        return formatofecha.format(fecha);
    }

    public Administrador() throws SQLException {
        initComponents();
        jMenuItem1.setText("Cambiar estado");
        jMenuItem2.setText("Detalles");
        int ancho=this.getWidth();
        int altura=this.getHeight();
        this.setExtendedState(MAXIMIZED_BOTH);
        Connection c = conexion();
        fecha.setDate(new Date());
        titu.setBounds((int) ancho / 2, 20,titu.getWidth(),titu.getHeight());
        j.setBounds(0, 0, ancho, (int)(altura*0.18));
        fondo.setBounds(0, (int)(altura*0.18), ancho, (int)(altura*0.7742));
        nt.setBounds(10, (int)(altura*0.20), (int)(ancho*0.1227), (int)(altura*0.1015));
        ntxt.setBounds((int)(ancho*0.13), (int)(altura*0.23477), (int)(ancho*0.1443), (int)(altura*0.0381));
        cedu.setBounds(10, (int)(altura*0.29), (int)(ancho*0.1443), (int)(altura*0.03807));
        ce.setBounds((int)(ancho*0.1298), (int)(altura*0.2855), (int)(ancho*0.1443), 30);
        fo.setBounds(10, (int)(altura*0.3426), (int)(ancho*0.1443), (int)(altura*0.0381));
        fecha.setBounds((int)(ancho*0.13), (int)(altura*0.3426), (int)(ancho*0.1443), (int)(altura*0.0381));
        to.setBounds(10, (int)(altura*0.3997), (int)(ancho*0.1443), (int)(altura*0.0381));
        txt.setBounds((int)(ancho*0.1298), (int)(altura*0.3997),(int)(ancho*0.1443),(int)(altura*0.0381));
        da.setBounds(10, (int)(altura*0.4441), (int)(ancho*0.07215), (int)(altura*0.0381));
        dtx.setBounds((int)(ancho*0.1298),(int)(altura*0.4441), (int)(ancho*0.1443), (int)(altura*0.0381));
        a.setBounds(10, (int)(altura*0.5012), (int)(ancho*0.3246), (int)(altura*0.2538));
        oa.setBounds((int)(ancho*0.5050), (int)(altura*0.2157), (int)(ancho*0.1443),(int)(altura*0.1015));
        combo2.setBounds((int)(ancho*0.2813), (int)(altura*0.4441), (int)(ancho*0.1443), (int)(altura*0.0381));
        back.setLocation(10, altura - 100);
        Border lineBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
        a.setBorder(lineBorder);
        l2.setModel(dm);
        this.add(combo2);
        this.add(back);
        this.add(oa);
        this.add(a);
        this.add(txt);
        this.add(to);
        this.add(fecha);
        this.add(nt);
        this.add(cedu);
        this.add(ntxt);
        this.add(ce);
        this.add(fo);
        this.add(da);
        this.add(dtx);
        j.setOpaque(true);
        this.add(j);
        this.add(fondo);
        try {
            dm.clear();
            ps2 = c.prepareStatement("SELECT id,titulo,estado FROM tareas");
            rr = ps2.executeQuery();
            int contador = 0;
            while (rr.next()) {
                datos[contador] = rr.getString("titulo");
                datos2[contador] = rr.getString("id");
                clase k = new clase(rr.getString("titulo"), rr.getString("estado"));
                dm.addElement(k);
                contador++;
            }
            l2.setCellRenderer(new CustomRenderer());
            l2.setModel(dm);
            ps = c.prepareStatement("SELECT id FROM aguacates");
            res = ps.executeQuery();
            contador = 6;
            String hpta = "- - - - - - - - - - - - - - - - - - - - - - - -;Todos los arboles;zona 1;zona 2;zona 3;zona 4;zona 5;zona 1 y zona 2;zona 1 y zona 3;zona 1 y zona 4;zona 1 y zona 5;zona 2 y zona 3;zona 2 y zona 4;zona 2 y zona 5;zona 3 y zona 4;zona 3 y zona 5;zona 4 y zona 5;zona 1, zona 2 y zona 3;zona 1, zona 2 y zona 3;zona 1, zona 2 y zona 4;zona 1, zona 2 y zona 5;zona 1, zona 3 y zona 4;zona 1, zona3 y zona 5;zona 1, zona 4 y zona 5;zona 2, zona 3 y zona 4;zona 2, zona 3 y zona 5;zona 3, zona 4 y zona 5;zona 1, zona 2, zona 3 y zona 4;zona 1, zona 2, zona 3, zona 5;zona 2, zona 3, zona 4 y zona 5;zona 1, zona 2, zona 4 y zona 5;zona 1, zona 3, zona 4 y zona 5";
            while (res.next()) {
                hpta = hpta + ";" + res.getString("id");
                contador++;
            }
            arboles = hpta.split(";");
            modelo = new DefaultComboBoxModel(arboles);
            dtx.setModel(modelo);
            int vectores[] = new int[1000];
            ps2 = c.prepareStatement("SELECT * FROM aguacates");
            rr = ps2.executeQuery();
            contador = 1;
            while (rr.next()) {
                vectores[rr.getInt(1)] = 1;
            }
            /*for (int i = 1; i < 800; i++) {
                if(vectores[i]==0){
                }
            }*/
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pop = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        titu = new javax.swing.JLabel();
        j = new javax.swing.JLabel();
        boton = new javax.swing.JButton();
        to = new javax.swing.JLabel();
        nt = new javax.swing.JLabel();
        fo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        a = new javax.swing.JTextArea();
        ntxt = new javax.swing.JTextField();
        txt = new javax.swing.JTextField();
        fecha = new com.toedter.calendar.JDateChooser();
        ce = new javax.swing.JTextField();
        fondo = new javax.swing.JLabel();
        cedu = new javax.swing.JLabel();
        oa = new javax.swing.JLabel();
        back = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        l2 = new javax.swing.JList<>();
        da = new javax.swing.JLabel();
        dtx = new javax.swing.JComboBox<>();
        combo2 = new javax.swing.JComboBox<>();

        jMenuItem1.setText("jMenuItem1");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        pop.add(jMenuItem1);

        jMenuItem2.setText("jMenuItem2");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        pop.add(jMenuItem2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ECOHASS-FINCA LA ESPERANZA");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        titu.setBackground(new java.awt.Color(0, 0, 0));
        titu.setFont(new java.awt.Font("Microsoft Tai Le", 1, 48)); // NOI18N
        titu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/manager.png"))); // NOI18N
        titu.setText("Tareas");

        j.setBackground(new java.awt.Color(0, 102, 0));

        boton.setBackground(new java.awt.Color(0, 102, 0));
        boton.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        boton.setForeground(new java.awt.Color(255, 255, 255));
        boton.setText("Asignar nueva tarea");
        boton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        boton.setFocusPainted(false);
        boton.setFocusable(false);
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonMouseClicked(evt);
            }
        });
        boton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActionPerformed(evt);
            }
        });
        boton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                botonKeyPressed(evt);
            }
        });

        to.setBackground(new java.awt.Color(255, 255, 255));
        to.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        to.setText("Título de la orden");
        to.setOpaque(true);

        nt.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        nt.setText("Nombre del técnico");

        fo.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        fo.setText("Fecha de la orden");

        a.setColumns(20);
        a.setLineWrap(true);
        a.setRows(5);
        a.setToolTipText("Escriba la nueva orden aquí.");
        jScrollPane1.setViewportView(a);

        txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtActionPerformed(evt);
            }
        });

        ce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ceActionPerformed(evt);
            }
        });
        ce.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ceKeyTyped(evt);
            }
        });

        fondo.setBackground(new java.awt.Color(255, 255, 255));
        fondo.setOpaque(true);

        cedu.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        cedu.setText("Cédula");

        oa.setFont(new java.awt.Font("Microsoft Tai Le", 1, 24)); // NOI18N
        oa.setText("Tareas anteriores");

        back.setBackground(new java.awt.Color(0, 102, 0));
        back.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        back.setForeground(new java.awt.Color(255, 255, 255));
        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/back.png"))); // NOI18N
        back.setText("Atrás");
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });

        l2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "item", "43", "232", "24231", "21324", "24", "dsfsdf", "asfda" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        l2.setToolTipText("tareas creadas anteriormente");
        l2.setAutoscrolls(false);
        l2.setComponentPopupMenu(pop);
        l2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                l2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(l2);

        da.setBackground(new java.awt.Color(255, 255, 255));
        da.setFont(new java.awt.Font("Microsoft Tai Le", 1, 18)); // NOI18N
        da.setText("Dirigida A:");
        da.setOpaque(true);

        dtx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        dtx.setToolTipText("Solo aparecerán los arboles registrados hasta el momento.");

        combo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- - - - - - - - - - - - - - - - - - - - - - - -", "SALUD", "NUTRICIÓN", "CUIDADO Y MANTENIMIENTO", "COSECHA" }));
        combo2.setToolTipText("Estas opciones hacen referencia al tipo de tarea que se está por crear.");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(j)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(273, 273, 273)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(cedu))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(nt)
                                            .addComponent(fo)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(da)
                                                .addComponent(to)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(ce)
                                            .addComponent(ntxt)
                                            .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(121, 121, 121)
                                        .addComponent(back)
                                        .addGap(234, 234, 234)
                                        .addComponent(oa))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(dtx, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGap(74, 74, 74)
                                            .addComponent(fondo)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(441, 441, 441)
                                        .addComponent(combo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(437, 437, 437)
                                .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(149, 149, 149)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(495, 495, 495)
                        .addComponent(titu))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(boton, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(359, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(144, 144, 144)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cedu)
                                        .addGap(90, 90, 90)
                                        .addComponent(fo)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(to))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(42, 42, 42)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(ce, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(combo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(22, 22, 22)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(nt)
                                            .addComponent(ntxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(da)
                                    .addComponent(dtx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(67, 67, 67)
                                .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(titu)
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(j, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fondo))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 286, Short.MAX_VALUE)
                .addComponent(boton, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(back)
                        .addGap(246, 246, 246))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(oa)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonActionPerformed

    private void txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtActionPerformed

    private void ceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ceActionPerformed

    }//GEN-LAST:event_ceActionPerformed

    private void botonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonMouseClicked
        if (ce.getText().equals("") || fecha.getDate().equals("") || ntxt.getText().equals("") || txt.getText().equals("") || a.getText().equals("") || dtx.getSelectedItem().equals("- - - - - - - - - - - - - - - - - - - - - - - -") || combo2.getSelectedItem().equals("- - - - - - - - - - - - - - - - - - - - - - - -")) {//verificación si se llenaron todos los campos.
            JOptionPane.showMessageDialog(null, "faltó rellenar alguno de los campos obligatorios", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
        } else {
            if (verificadorcedulaentero(ce.getText())) {
                if (JOptionPane.showConfirmDialog(null, "¿Estás seguro que quieres agregar esta nueva nota?", "CONFIRMACIÓN", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
                    try {
                        Properties props = new Properties();
                        props.setProperty("mail.smtp.host", "smtp.gmail.com");
                        props.setProperty("mail.smtp.starttls.enable", "true");
                        props.setProperty("mail.smtp.port", "587");
                        props.setProperty("mail.smtp.auth", "true");
                        Session session = Session.getDefaultInstance(props);
                        String remitente = "serverecohass@gmail.com";
                        String pass = "jireh2019";
                        String receptor = "jrossetes@uninorte.edu.co";
                        String asunto = "Nueva tarea Ecohass: " + txt.getText();
                        String newline = System.lineSeparator();
                        String mensaje = txt.getText() + "." + newline
                                + "Asignada por:" + ntxt.getText() + "." + newline
                                + "Cédula: " + ce.getText() + "." + newline
                                + "Dirigida a: " + dtx.getSelectedItem().toString() + newline
                                + "Tipo de orden: " + combo2.getSelectedItem().toString() + newline
                                + newline
                                + a.getText();
                        MimeMessage message = new MimeMessage(session);
                        message.setFrom(new InternetAddress(remitente));
                        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receptor));
                        message.setSubject(asunto);
                        message.setText(mensaje);
                        Transport t = session.getTransport("smtp");
                        t.connect(remitente, pass);
                        t.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
                        t.close();
                        Connection c = null;
                        c = conexion();
                        ps = c.prepareStatement("INSERT INTO tareas(nombre,cedula,titulo,fecha,tarea,estado,receptor,tipo) VALUES (?,?,?,?,?,?,?,?)");
                        ps.setString(1, ntxt.getText());
                        ps.setString(2, ce.getText());
                        ps.setString(3, txt.getText());
                        ps.setString(4, fecha.getDate().toString());
                        ps.setString(5, a.getText());
                        ps.setString(6, "no cumplida");
                        ps.setString(7, dtx.getSelectedItem().toString());
                        ps.setString(8, combo2.getSelectedItem().toString());
                        int r = ps.executeUpdate();
                        actualizar(r, 0);
                    } catch (AddressException ex) {
                        Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (MessagingException ex) {
                        Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLIntegrityConstraintViolationException e) {
                        JOptionPane.showMessageDialog(null, "Debes cambiar el título, ya está registrado.", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                    } catch (SQLException ex) {
                        Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "Digite un número de cedula válido.", "ERROR", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_botonMouseClicked

    private void botonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_botonKeyPressed

    }//GEN-LAST:event_botonKeyPressed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        menu m = new menu();
        m.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backMouseClicked

    private void l2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_l2MouseClicked

    }//GEN-LAST:event_l2MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (l2.getSelectedIndex() >= 0) {
            PreparedStatement ppp, psp;
            Connection c = conexion();
            String title = datos[l2.getSelectedIndex()];
            try {
                ps = c.prepareStatement("SELECT estado,titulo FROM tareas");
                r2 = ps.executeQuery();
                while (r2.next()) {
                    String estado = r2.getString("estado");
                    if (r2.getString("titulo").equals(title)) {
                        if (estado.equals("no cumplida")) {
                            if (JOptionPane.showConfirmDialog(null, "¿Estás seguro que quieres marcar la tarea como cumplida?", "CONFIRMACIÓN", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
                                String nombre = JOptionPane.showInputDialog(null, "Digite su nombre");
                                String cedula = JOptionPane.showInputDialog(null, "Digite su cedula");
                                psp = c.prepareStatement("INSERT INTO registrodecambiodetareas(idtarea,nombre,cedula,fecha,nuevoestado) VALUES (?,?,?,?,?)");
                                psp.setString(1, datos2[l2.getSelectedIndex()]);
                                psp.setString(2, nombre);
                                psp.setString(3, cedula);
                                psp.setString(4, fechactual());
                                psp.setString(5, "cumplida");
                                psp.executeUpdate();
                                ppp = c.prepareStatement("UPDATE tareas SET estado= 'cumplida' WHERE titulo='" + title + "'");
                                actualizar(ppp.executeUpdate(), 1);
                            }
                        } else {
                            if (JOptionPane.showConfirmDialog(null, "¿Estás seguro que quieres marcar la tarea como no cumplida?", "CONFIRMACIÓN", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
                                String nombre = JOptionPane.showInputDialog(null, "Digite su nombre");
                                String cedula = JOptionPane.showInputDialog(null, "Digite su cedula");
                                psp = c.prepareStatement("INSERT INTO registrodecambiodetareas(idtarea,nombre,cedula,fecha,nuevoestado) VALUES (?,?,?,?,?)");
                                psp.setString(1, datos2[l2.getSelectedIndex()]);
                                psp.setString(2, nombre);
                                psp.setString(3, cedula);
                                psp.setString(4, fechactual());
                                psp.setString(5, "no cumplida");
                                psp.executeUpdate();
                                ppp = c.prepareStatement("UPDATE tareas SET estado= 'no cumplida' WHERE titulo='" + title + "'");
                                actualizar(ppp.executeUpdate(), 1);

                            }
                        }

                    }
                }
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        PreparedStatement ppp;
        Connection c = conexion();
        String title = datos[l2.getSelectedIndex()];
        String newline = System.lineSeparator();
        try {
            ps = c.prepareStatement("SELECT * FROM tareas WHERE titulo='" + title + "'");
            rr = ps.executeQuery();
            while (rr.next()) {
                JOptionPane.showMessageDialog(null, "Titulo: " + rr.getString("titulo") + newline + "Creador: " + rr.getString("nombre") + newline + "Receptor: " + rr.getString("receptor") + newline + "Tarea: " + nuevacadena(rr.getString("tarea")) + newline + "fecha de creación:" + lafecha(rr.getString("fecha")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        /*try {
            Process p = Runtime.getRuntime().exec("C:\\xampp\\xampp_stop");
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }//GEN-LAST:event_formWindowClosing

    private void ceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ceKeyTyped
        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Solo se pueden ingresar números.");
        }
    }//GEN-LAST:event_ceKeyTyped

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
            java.util.logging.Logger.getLogger(Administrador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Administrador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Administrador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Administrador.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new Administrador().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea a;
    private javax.swing.JButton back;
    private javax.swing.JButton boton;
    private javax.swing.JTextField ce;
    private javax.swing.JLabel cedu;
    private javax.swing.JComboBox<String> combo2;
    private javax.swing.JLabel da;
    private javax.swing.JComboBox<String> dtx;
    private com.toedter.calendar.JDateChooser fecha;
    private javax.swing.JLabel fo;
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel j;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> l2;
    private javax.swing.JLabel nt;
    private javax.swing.JTextField ntxt;
    private javax.swing.JLabel oa;
    private javax.swing.JPopupMenu pop;
    private javax.swing.JLabel titu;
    private javax.swing.JLabel to;
    private javax.swing.JTextField txt;
    // End of variables declaration//GEN-END:variables
}
