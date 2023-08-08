package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Detalle_Venta;
import entidades.Distrito;

public class ReporteVentaDAO {
    private Connection conexion;

    public ReporteVentaDAO() {
    	  String url = "jdbc:mysql://localhost/bdpanaderia?useSSL=false&useTimezone=true&serverTimezone=UTC";
          String usuario = "root";
          String contrasena = "MySQL123$";
          
          try {
              conexion = DriverManager.getConnection(url, usuario, contrasena);
          } catch (SQLException e) {
              e.printStackTrace();
          }
    }

    public List<Detalle_Venta> obtenerVentas() {
        List<Detalle_Venta> detalle_ventas = new ArrayList<>();

        try {
            // Preparar la consulta SQL
            String consulta = "SELECT codEmp, vendedor ,codigoProducto, nombreProducto,cantidad,precioUnitario, total,fecha,importeTotal,vueltoDinero  FROM Venta";
            PreparedStatement statement = conexion.prepareStatement(consulta);

            // Ejecutar la consulta
            ResultSet resultSet = statement.executeQuery();

            // Recorrer los resultados y crear objetos Distrito
            while (resultSet.next()) {
                int codEmp = resultSet.getInt("codEmp");
                String vendedor = resultSet.getString("vendedor");
                int codigoProducto = resultSet.getInt("codigoProducto");
                String nombreProducto = resultSet.getString("nombreProducto");
                int cantidad = resultSet.getInt("cantidad");
                double precioUnitario = resultSet.getDouble("precioUnitario");
                double total = resultSet.getDouble("total");
                String fecha = resultSet.getString("fecha");
                int importeTotal = resultSet.getInt("importeTotal");
                double vueltoDinero = resultSet.getDouble("vueltoDinero");
                
                
                Detalle_Venta detalle_venta = new  Detalle_Venta();
                detalle_venta.setCodEmp(codEmp);
                detalle_venta.setVendedor(vendedor);
                detalle_venta.setCodigoProducto(codigoProducto);
                detalle_venta.setNombreProducto(nombreProducto);
                detalle_venta.setCantidad(cantidad);
                detalle_venta.setPrecioUnitario(precioUnitario);
                detalle_venta.setTotal(total);
                detalle_venta.setFecha(fecha);
                detalle_venta.setImporteTotal(importeTotal);
                detalle_venta.setVueltoDinero(vueltoDinero);
                
                
                detalle_ventas.add(detalle_venta);
            }

            // Cerrar recursos
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detalle_ventas;
    }
}
