package Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entidades.Empleado;

public class EmpleadoDAO {
	  private static final String URL = "jdbc:mysql://localhost/bdpanaderia?useSSL=false&useTimezone=true&serverTimezone=UTC";
	    private static final String USUARIO = "root";
	    private static final String CONTRASENA = "MySQL123$";

    public void insertarEmpleado(Empleado empleado) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA)) {
            String query = "INSERT INTO empleado (codEmp, nomEmp, apEmp) VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, empleado.getCodEmp());
            statement.setString(2, empleado.getNomEmp());
            statement.setString(3, empleado.getApEmp());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarEmpleado(Empleado empleado) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA)) {
            String query = "UPDATE empleado SET nomEmp = ?, apEmp = ? WHERE codEmp = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, empleado.getNomEmp());
            statement.setString(2, empleado.getApEmp());
            statement.setInt(3, empleado.getCodEmp());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarEmpleado(int codigo) {
        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA)) {
            String query = "DELETE FROM empleado WHERE codEmp = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, codigo);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Empleado> obtenerEmpleados() {
        List<Empleado> empleados = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(URL, USUARIO, CONTRASENA)) {
            String query = "SELECT codEmp, nomEmp, apEmp FROM empleado";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int codigo = resultSet.getInt("codEmp");
                String nombre = resultSet.getString("nomEmp");
                String apellido = resultSet.getString("apEmp");

                Empleado empleado = new Empleado();
                empleado.setCodEmp(codigo);
                empleado.setNomEmp(nombre);
                empleado.setApEmp(apellido);

                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empleados;
    }
    
    
    
    
}
