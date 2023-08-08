package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Distrito;
import entidades.Empleado;

public class ReporteEmpleadoDAO {
	  private Connection conexion;

	    public ReporteEmpleadoDAO() {
	    	  String url = "jdbc:mysql://localhost/bdpanaderia?useSSL=false&useTimezone=true&serverTimezone=UTC";
	          String usuario = "root";
	          String contrasena = "MySQL123$";
	          
	          try {
	              conexion = DriverManager.getConnection(url, usuario, contrasena);
	          } catch (SQLException e) {
	              e.printStackTrace();
	          }
	    }

	    public List<Empleado> obtenerEmpleados() {
	        List<Empleado> empleados = new ArrayList<>();

	        try {
	            // Preparar la consulta SQL
	            String consulta = "SELECT codEmp, nomEmp,apEmp FROM empleado";
	            PreparedStatement statement = conexion.prepareStatement(consulta);

	            // Ejecutar la consulta
	            ResultSet resultSet = statement.executeQuery();

	            // Recorrer los resultados y crear objetos Distrito
	            while (resultSet.next()) {
	                int codEmp = resultSet.getInt("codEmp");
	                String nomEmp = resultSet.getString("nomEmp");
	                String apEmp = resultSet.getString("apEmp");
	                
	                Empleado empleado = new Empleado();
	                empleado.setCodEmp(codEmp);
	                empleado.setNomEmp(nomEmp);
	                empleado.setApEmp(apEmp);

	                empleados.add(empleado);
	            }

	            // Cerrar recursos
	            resultSet.close();
	            statement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return empleados;
	    }
}
