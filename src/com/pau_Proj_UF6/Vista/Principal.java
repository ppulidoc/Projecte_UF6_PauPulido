package com.pau_Proj_UF6.Vista;

import javax.swing.*;
import java.sql.SQLException;

public class Principal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Finestra();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }
        });

    }
}
