package Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entidades.Detalle_Venta;

public class SistemaVentasDAO {
    private Connection conexion;

    public SistemaVentasDAO() {
        conectar();
    }

    private void conectar() {
        String url = "jdbc:mysql://localhost/bdpanaderia?useSSL=false&useTimezone=true&serverTimezone=UTC";
        String usuario = "root";
        String contrasena = "MySQL123$";

        try {
            conexion = DriverManager.getConnection(url, usuario, contrasena);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet obtenerClientePorCodigo(int codigoCliente) throws SQLException {
        String query = "SELECT * FROM cliente WHERE codCli = ?";
        PreparedStatement statement = conexion.prepareStatement(query);
        statement.setInt(1, codigoCliente);
        return statement.executeQuery();
    }

    public ResultSet obtenerProductoPorCodigo(int codigoProducto) throws SQLException {
        String query = "SELECT * FROM producto WHERE codPro = ?";
        PreparedStatement statement = conexion.prepareStatement(query);
        statement.setInt(1, codigoProducto);
        return statement.executeQuery();
    }

    public ResultSet obtenerEmpleadoPorNombre(String nombreEmpleado) throws SQLException {
        String query = "SELECT * FROM empleado WHERE nomEmp = ?";
        PreparedStatement statement = conexion.prepareStatement(query);
        statement.setString(1, nombreEmpleado);
        return statement.executeQuery();
    }



    
    
    public List<String> obtenerNombresEmpleados() throws SQLException {
        List<String> nombresEmpleados = new ArrayList<>();
        String query = "SELECT nomEmp FROM empleado";
        PreparedStatement statement = conexion.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String nombreEmpleado = resultSet.getString("nomEmp");
            nombresEmpleados.add(nombreEmpleado);
        }

        return nombresEmpleados;
    }

    public void insertarVenta(Detalle_Venta detalleVenta, JTable tablaVentas) {
        String query = "INSERT INTO venta ( codEmp,vendedor, codigoProducto, nombreProducto, cantidad, precioUnitario, total, fecha,importeTotal,vueltoDinero) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?)";
        try {
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, detalleVenta.getCodEmp());
            statement.setString(2, detalleVenta.getVendedor());
            statement.setInt(3, detalleVenta.getCodigoProducto());
            statement.setString(4, detalleVenta.getNombreProducto());
            statement.setInt(5, detalleVenta.getCantidad());
            statement.setDouble(6, detalleVenta.getPrecioUnitario());
            statement.setDouble(7, detalleVenta.getTotal());
            statement.setDate(8, Date.valueOf(detalleVenta.getFecha()));
           
       //     statement.setInt(9, detalleVenta.getCodPro());
            statement.setInt(9, detalleVenta.getImporteTotal());
            statement.setDouble(10, detalleVenta.getVueltoDinero());
            statement.executeUpdate();

            // Actualizar la tabla de ventas
            actualizarTablaVenta(tablaVentas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarTablaVenta(JTable tablaVentas) {
        DefaultTableModel modeloTabla = (DefaultTableModel) tablaVentas.getModel();
        modeloTabla.setRowCount(0); // Limpiar la tabla

        String query = "SELECT * FROM Venta";
        try {
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String vendedor = resultSet.getString("vendedor");
                int codigoProducto = resultSet.getInt("codigoProducto");
                String nombreProducto = resultSet.getString("nombreProducto");
                int cantidad = resultSet.getInt("cantidad");
                double precioUnitario = resultSet.getDouble("precioUnitario");
                double total = resultSet.getDouble("total");
                Date fecha = resultSet.getDate("fecha");

                modeloTabla.addRow(new Object[] { vendedor, codigoProducto, nombreProducto, cantidad, precioUnitario, total, fecha });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    public int obtenerCodigoEmpleadoPorNombre(String nombreEmpleado) throws SQLException {
        int codigoEmpleado = -1;
        String query = "SELECT codEmp FROM empleado WHERE nomEmp = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, nombreEmpleado);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    codigoEmpleado = result.getInt("codEmp");
                }
            }
        }
        return codigoEmpleado;
    }
    
    
    
    
}
