package Dao;

import java.sql.*;
import java.util.ArrayList;

import entidades.Cliente;

public class ClienteDAO {
    private Connection connection;
    private final String URL = "jdbc:mysql://localhost/bdpanaderia?useSSL=false&useTimezone=true&serverTimezone=UTC";
    private final String USERNAME = "root";
    private final String PASSWORD = "MySQL123$";

    public ClienteDAO() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> obtenerDistritos() {
        ArrayList<String> distritos = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT nomDis FROM distrito");

            while (resultSet.next()) {
                String distrito = resultSet.getString("nomDis");
                distritos.add(distrito);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return distritos;
    }

    public ArrayList<Cliente> obtenerClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM cliente");

            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setCodCli(resultSet.getInt("codCli"));
                cliente.setNomCli(resultSet.getString("nomCli"));
                cliente.setApCli(resultSet.getString("apCli"));
                cliente.setCelCli(resultSet.getString("celCli"));
                cliente.setCodDis(resultSet.getInt("codDis"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    public void registrarCliente(Cliente cliente) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cliente (codCli, nomCli, apCli, celCli, codDis) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setInt(1, cliente.getCodCli());
            preparedStatement.setString(2, cliente.getNomCli());
            preparedStatement.setString(3, cliente.getApCli());
            preparedStatement.setString(4, cliente.getCelCli());
            preparedStatement.setInt(5, cliente.getCodDis());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cliente obtenerClientePorCodigo(int codigoCliente) {
        Cliente cliente = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM cliente WHERE codCli = ?");
            preparedStatement.setInt(1, codigoCliente);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                cliente = new Cliente();
                cliente.setCodCli(resultSet.getInt("codCli"));
                cliente.setNomCli(resultSet.getString("nomCli"));
                cliente.setApCli(resultSet.getString("apCli"));
                cliente.setCelCli(resultSet.getString("celCli"));
                cliente.setCodDis(resultSet.getInt("codDis"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    public void actualizarCliente(Cliente cliente) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE cliente SET nomCli = ?, apCli = ?, celCli = ?, codDis = ? WHERE codCli = ?");
            preparedStatement.setString(1, cliente.getNomCli());
            preparedStatement.setString(2, cliente.getApCli());
            preparedStatement.setString(3, cliente.getCelCli());
            preparedStatement.setInt(4, cliente.getCodDis());
            preparedStatement.setInt(5, cliente.getCodCli());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarCliente(int codigoCliente) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM cliente WHERE codCli = ?");
            preparedStatement.setInt(1, codigoCliente);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
