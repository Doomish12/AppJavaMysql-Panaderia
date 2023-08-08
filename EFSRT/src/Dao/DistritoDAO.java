package Dao;

import entidades.Distrito;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DistritoDAO {
    private static final String URL = "jdbc:mysql://localhost/bdpanaderia?useSSL=false&useTimezone=true&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "MySQL123$";

    private static final String INSERTAR_DISTRITO = "INSERT INTO distrito (codDis, nomDis) VALUES (?, ?)";
    private static final String ACTUALIZAR_DISTRITO = "UPDATE distrito SET nomDis = ? WHERE codDis = ?";
    private static final String ELIMINAR_DISTRITO = "DELETE FROM distrito WHERE codDis = ?";
    private static final String OBTENER_DISTRITOS = "SELECT codDis, nomDis FROM distrito";

    public void insertarDistrito(Distrito distrito) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement stmt = conn.prepareStatement(INSERTAR_DISTRITO)) {
            stmt.setInt(1, distrito.getCodDis());
            stmt.setString(2, distrito.getNomDis());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarDistrito(Distrito distrito) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement stmt = conn.prepareStatement(ACTUALIZAR_DISTRITO)) {
            stmt.setString(1, distrito.getNomDis());
            stmt.setInt(2, distrito.getCodDis());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarDistrito(int codigo) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             PreparedStatement stmt = conn.prepareStatement(ELIMINAR_DISTRITO)) {
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Distrito> obtenerDistritos() {
        List<Distrito> distritos = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(OBTENER_DISTRITOS)) {
            while (rs.next()) {
                int codigo = rs.getInt("codDis");
                String nombre = rs.getString("nomDis");

                Distrito distrito = new Distrito();
                distrito.setCodDis(codigo);
                distrito.setNomDis(nombre);

                distritos.add(distrito);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return distritos;
    }
}

