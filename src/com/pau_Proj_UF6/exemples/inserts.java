package com.pau_Proj_UF6.exemples;

import com.pau_Proj_UF6.Model.MySQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class inserts {

    public static void main(String[] args) {
        try {
            // Obtener la conexión a la base de datos
            Connection conn = MySQLConnection.getConnection();

            // Definir la sentencia SQL de inserción
            String sql = "INSERT INTO  (columna1, columna2, columna3) VALUES (?,?,?)";

            // Crear un objeto PreparedStatement con la sentencia SQL
            PreparedStatement statement = conn.prepareStatement(sql);

            // Asignar los valores a los parámetros de la sentencia SQL
            statement.setString(1, "valor_columna1");
            statement.setString(2, "valor_columna2");
            statement.setString(3, "valor_columna3");

            // Ejecutar la sentencia SQL de inserción
            int filasInsertadas = statement.executeUpdate();

            // Verificar si se insertaron filas correctamente
            if (filasInsertadas > 0) {
                System.out.println("Inserción exitosa");
            } else {
                System.out.println("No se pudo insertar el registro");
            }

            // Cerrar la conexión y liberar los recursos
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}

