package com.pau_Proj_UF6.Vista;

import com.pau_Proj_UF6.Model.MySQLConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.pau_Proj_UF6.Model.MySQLConnection.getConnection;

public class Finestra extends JFrame implements ActionListener {
    private JButton[] bton = new JButton[9];
    Connection cx = MySQLConnection.getConnection();

    public Finestra() throws SQLException {
        this.setSize(500, 500); // Finestra amb la mida
        setTitle("TPV - PauPulido"); // Titol de la pag
        setLocationRelativeTo(null);// Amb aixo apareix la ventana al centre
        setLayout(new BorderLayout());
        this.getContentPane().setBackground(Color.GRAY); // Color de la pag
        iniciarComponents();

        setDefaultCloseOperation(EXIT_ON_CLOSE); // Aixo fa que la creu red tanqui
        setVisible(true);
    }

    private void iniciarComponents() {
        // Creacions --> -----------------------------------
        // Panels -->
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();

        // Etiquetes -->
        JLabel Titol = new JLabel("TPV");

        // Botons -->
        for (int i = 0; i < 9; i++) {
            bton[i] = new JButton();
            panel2.add(bton[i]);
            bton[i].addActionListener(this);
        }

        //-------------------------------------------------

        // Color del panel --------------------------------
        panel1.setBackground(Color.GREEN);
        panel2.setBackground(Color.blue);

        //------------------------------------------------

        // Afegir panel a l'inicial
        this.getContentPane().add(panel1);
        this.getContentPane().add(panel2);
        this.getContentPane().add(panel3);
        this.getContentPane().add(panel4);
        this.getContentPane().add(panel5);
        this.getContentPane().add(panel6);

        // Mides panels
        panel1.setBounds(0, 0, 500, 40);
        panel2.setBounds(50, 100, 300, 300);

        Titol.setFont(new Font("Tahoma", Font.BOLD, 18));
        panel1.add(Titol, BorderLayout.CENTER); // La etiqueta 1 la he aplicat al panel

        GridLayout gridLayout = new GridLayout(3, 3);
        panel2.setLayout(gridLayout);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == bton[i]) {
                try {
                    inserir(cx, 23, 2, "coca2");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
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
}
