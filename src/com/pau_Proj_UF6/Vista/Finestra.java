package com.pau_Proj_UF6.Vista;

import com.pau_Proj_UF6.Model.MySQLConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Finestra extends JFrame implements ActionListener {
    private JButton[] bton = new JButton[9];
    private String[] nombres = {"Coca-cola", "Braves", "Calamars", "Entrepà", "Hamburguesa", "Frankfurd", "Gelat", "Cripetes", "Fruita"};
    Connection cx = MySQLConnection.getConnection();
    private JButton bTot;
    private JButton bLogin;
    private JButton bDelete;

    public Finestra() throws SQLException {
        this.setSize(500, 470);
        setTitle("TPV - PauPulido");
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        this.getContentPane().setBackground(Color.GRAY);
        iniciarComponents();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void iniciarComponents() {
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();

        bTot = new JButton("Factura");
        bLogin = new JButton("Iniciar sessió");
        bDelete = new JButton("Delete");

        JLabel Titol = new JLabel("TPV");

        for (int i = 0; i < 9; i++) {
            bton[i] = new JButton(nombres[i]);
            panel2.add(bton[i]);
            bton[i].addActionListener(this);
        }

        panel1.setBackground(Color.GREEN);
        panel2.setBackground(Color.BLUE);

        this.getContentPane().add(panel1, BorderLayout.NORTH);
        this.getContentPane().add(panel2);
        this.getContentPane().add(panel3, BorderLayout.SOUTH);
        this.getContentPane().add(panel4, BorderLayout.SOUTH);

        panel1.setBounds(20, 20, 460, 40);
        panel2.setBounds(50, 100, 300, 300);
        panel3.setBounds(400, 400, 45, 25 );

        Titol.setFont(new Font("Tahoma", Font.BOLD, 18));
        panel1.add(Titol, BorderLayout.CENTER);

        GridLayout gridLayout = new GridLayout(3, 3);
        panel2.setLayout(gridLayout);
        panel3.add(bTot);
        panel4.add(bLogin);
        panel4.add(bDelete);

        bTot.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Finestra2 finestra2 = new Finestra2();
                finestra2.setVisible(true);
                try {
                    finestra2.mostrarFactura();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        bLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Finestra3 finestra3 = new Finestra3();
                finestra3.setVisible(true);
            }
        });

        bDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    deleteProductes(cx, 1);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void deleteProductes(Connection cx, int idProducto) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            String sql = "DELETE FROM productes WHERE id_productes = ?";
            preparedStatement = cx.prepareStatement(sql);
            preparedStatement.setInt(1, idProducto);
            preparedStatement.executeUpdate();
            System.out.println("Producte eliminat correctament.");
        } catch (SQLException excepcion) {
            throw new SQLException(excepcion);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bton[0]) {
            try {
                inserir(cx, 1, 2, "Coca-Cola");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == bton[1]) {
            try {
                inserir(cx, 1, 5, "Braves");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == bton[2]) {
            try {
                inserir(cx, 1, 6, "Calamars");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == bton[3]) {
            try {
                inserir(cx, 1, 3, "Entrepa");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == bton[4]) {
            try {
                inserir(cx, 1, 5, "Hamburguesa");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == bton[5]) {
            try {
                inserir(cx, 1, 4, "Frankfurd");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == bton[6]) {
            try {
                inserir(cx, 1, 2, "Gelat");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == bton[7]) {
            try {
                inserir(cx, 1, 1, "Crispetes");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        if (e.getSource() == bton[8]) {
            try {
                inserir(cx, 1, 5, "Fruita");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public static void inserir(Connection cx, int id_entrat, int preu_entrat, String nom_entrat) throws Exception {
        PreparedStatement preparedStatement = null;
        System.out.println("hola");
        try {
            String sql = "INSERT INTO productes (id_productes,preu, nom)" +
                    "VALUES(?,?,?)";
            preparedStatement = cx.prepareStatement(sql);
            preparedStatement.setInt(1, id_entrat);
            preparedStatement.setInt(2, preu_entrat);
            preparedStatement.setString(3, nom_entrat);
            preparedStatement.executeUpdate();
        } catch (SQLException excepcion) {
            throw new IOException(excepcion);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    Finestra finestra = new Finestra();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

class Finestra2 extends JFrame {
    public Finestra2() {
        this.setSize(500, 500);
        setTitle("Factura");
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        this.getContentPane().setBackground(Color.WHITE);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void mostrarFactura() throws SQLException {
        Connection cx = MySQLConnection.getConnection();
        PreparedStatement preparedStatement = cx.prepareStatement("SELECT * FROM productes");
        ResultSet resultSet = preparedStatement.executeQuery();

        JTextArea textAreaFac = new JTextArea();
        textAreaFac.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Aquesta font l'he trobat a internet

        // Títol de la factura
        String tituloFactura = " ********* FACTURA ********* ";
        String separador = "====================";

        textAreaFac.append("\n");
        textAreaFac.append(String.format(" %-10s %-10s%n","Preu", "Nom"));
        textAreaFac.append(separador + "\n");

        while (resultSet.next()) {

            int preu = resultSet.getInt("preu");
            String nom = resultSet.getString("nom");

            // Els percentatges amb els 10 es per ubicarho be
            textAreaFac.append(String.format("%-10s %-10s%n", preu, nom));
        }

        JScrollPane scrollPane = new JScrollPane(textAreaFac);
        add(scrollPane, BorderLayout.CENTER);
        textAreaFac.setEditable(false);
    }
}

class Finestra3 extends JFrame {
    private JLabel lblImatge;
    private JTextField txtUsuari;
    private JPasswordField txtContra;

    public Finestra3() {
        this.setSize(500, 500);
        setTitle("Finestra3");
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        this.getContentPane().setBackground(Color.WHITE);

        lblImatge = new JLabel();
        lblImatge.setHorizontalAlignment(SwingConstants.CENTER);

        JButton btnSeleccionar = new JButton("Seleccionar Imatge");
        btnSeleccionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Seleccionar Imatge");
                int seleccion = fileChooser.showOpenDialog(null);
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    String path = file.getAbsolutePath();
                    lblImatge.setIcon(new ImageIcon(path));
                }
            }
        });

        JPanel panelImagen = new JPanel();
        panelImagen.add(lblImatge);

        JPanel panelUsuario = new JPanel();
        JLabel lblUsuario = new JLabel("Usuari:");
        txtUsuari = new JTextField(15);
        panelUsuario.add(lblUsuario);
        panelUsuario.add(txtUsuari);

        JPanel panelContraseña = new JPanel();
        JLabel lblContraseña = new JLabel("Contra:");
        txtContra = new JPasswordField(15);
        panelContraseña.add(lblContraseña);
        panelContraseña.add(txtContra);

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnSeleccionar);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(panelImagen, BorderLayout.CENTER);
        panelPrincipal.add(panelUsuario, BorderLayout.NORTH);
        panelPrincipal.add(panelContraseña, BorderLayout.SOUTH);

        add(panelPrincipal, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
