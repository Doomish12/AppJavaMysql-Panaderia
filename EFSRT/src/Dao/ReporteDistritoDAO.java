package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Distrito;

public class ReporteDistritoDAO {

    private Connection conexion;

    public ReporteDistritoDAO() {
    	  String url = "jdbc:mysql://localhost/bdpanaderia?useSSL=false&useTimezone=true&serverTimezone=UTC";
          String usuario = "root";
          String contrasena = "MySQL123$";
          
          try {
              conexion = DriverManager.getConnection(url, usuario, contrasena);
          } catch (SQLException e) {
              e.printStackTrace();
          }
    }

    public List<Distrito> obtenerDistritos() {
        List<Distrito> distritos = new ArrayList<>();

        try {
            // Preparar la consulta SQL
            String consulta = "SELECT codDis, nomDis FROM distrito";
            PreparedStatement statement = conexion.prepareStatement(consulta);

            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();

            // Recorrer los resultados y crear objetos Distrito
            while (resultSet.next()) {
                int codDis = resultSet.getInt("codDis");
                String nomDis = resultSet.getString("nomDis");

                Distrito distrito = new Distrito();
                distrito.setCodDis(codDis);
                distrito.setNomDis(nomDis);

                distritos.add(distrito);
            }

            // Cerrar recursos
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return distritos;
    }
}

