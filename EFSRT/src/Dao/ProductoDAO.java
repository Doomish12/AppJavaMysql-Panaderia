package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entidades.Producto;

public class ProductoDAO {
	  private static final String URL = "jdbc:mysql://localhost/bdpanaderia?useSSL=false&useTimezone=true&serverTimezone=UTC";
	    private static final String USUARIO = "root";
	    private static final String CONTRASENA = "MySQL123$";

    public void insertarProducto(Producto producto) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA)) {
            String query = "INSERT INTO producto (codPro, nomPro, precioPro) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, producto.getCodPro());
            statement.setString(2, producto.getNomPro());
            statement.setDouble(3, producto.getPrecioPro());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarProducto(Producto producto) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA)) {
            String query = "UPDATE producto SET nomPro = ?, precioPro = ? WHERE codPro = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, producto.getNomPro());
            statement.setDouble(2, producto.getPrecioPro());
            statement.setInt(3, producto.getCodPro());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarProducto(int codigo) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA)) {
            String query = "DELETE FROM producto WHERE codPro = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, codigo);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Producto> obtenerProductos() {
        List<Producto> productos = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA)) {
            String query = "SELECT codPro, nomPro, precioPro FROM producto";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int codigo = resultSet.getInt("codPro");
                String nombre = resultSet.getString("nomPro");
                double precio = resultSet.getDouble("precioPro");

                Producto producto = new Producto();
                producto.setCodPro(codigo);
                producto.setNomPro(nombre);
                producto.setPrecioPro(precio);

                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }
}
