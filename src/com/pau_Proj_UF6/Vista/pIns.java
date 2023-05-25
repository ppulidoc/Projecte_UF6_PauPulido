package com.pau_Proj_UF6.Vista;
import com.pau_Proj_UF6.Model.MySQLConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class pIns {
//    public static void main(String[] args) throws Exception {
//
//        try{
////            inserir(cx,23,2,"coca1");
//        }
//        catch (IOException e){
//           throw  e;
//        }
//    }

    public static void inserir(Connection conn, int id_entrat, int preu_entrat, String nom_entrat) throws Exception
    {   PreparedStatement preparedStatement = null;
        try {
            String sql = "INSERT INTO productes (id_productes,preu, nom)" +
                    "VALUES(?,?,?)";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id_entrat);
            preparedStatement.setInt(2, preu_entrat);
            preparedStatement.setString(3, nom_entrat);
            preparedStatement.executeUpdate();

        } catch (SQLException excepcion) {
            throw new IOException(excepcion);
        } finally
        {
            if (preparedStatement != null) preparedStatement.close();
        }
    }

}

