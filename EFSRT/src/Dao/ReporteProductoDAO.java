package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Distrito;
import entidades.Producto;

public class ReporteProductoDAO {
	private Connection conexion;

    public ReporteProductoDAO() {
    	  String url = "jdbc:mysql://localhost/bdpanaderia?useSSL=false&useTimezone=true&serverTimezone=UTC";
          String usuario = "root";
          String contrasena = "MySQL123$";
          
          try {
              conexion = DriverManager.getConnection(url, usuario, contrasena);
          } catch (SQLException e) {
              e.printStackTrace();
          }
    }

    public List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();

        try {
            // Preparar la consulta SQL
            String consulta = "SELECT codPro, nomPro,precioPro FROM producto";
            PreparedStatement statement = conexion.prepareStatement(consulta);

            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();

            // Recorrer los resultados y crear objetos Distrito
            while (resultSet.next()) {
                int codPro = resultSet.getInt("codPro");
                String nomPro = resultSet.getString("nomPro");
                double precioPro = resultSet.getDouble("precioPro");
                		
                Producto producto = new Producto();
                producto.setCodPro(codPro);
                producto.setNomPro(nomPro);
                producto.setPrecioPro(precioPro);

                productos.add(producto);
            }

            // Cerrar recursos
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }
}
