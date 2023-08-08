package Gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Dao.ClienteDAO;
import entidades.Cliente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClientesGUI extends JFrame {
    private JButton btnRegistrar;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;

    private ClienteDAO clienteDAO;
    private JLabel lblCodigo;
    private JTextField txtNombre;
    private JLabel lblNombre;
    private JTextField txtCodigo;
    private JLabel lblApellido;
    private JTextField txtApellido;
    private JLabel lblTelefono;
    private JTextField txtTelefono;
    private JLabel lblDistrito;
    private JComboBox<String> cmbDistrito;
    private JLabel lblNewLabel;

    public ClientesGUI() {
    	setResizable(false);
        clienteDAO = new ClienteDAO();

        setTitle("Clientes");
        setLocationRelativeTo(null);
        setSize(502, 704);
        getContentPane().setLayout(new BorderLayout());

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Color.LIGHT_GRAY);
        panelBotones.setForeground(Color.WHITE);
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(38, 193, 103, 25);
        btnRegistrar.setForeground(new Color(224, 255, 255));
        btnRegistrar.setBackground(Color.BLACK);
        btnRegistrar.setFont(new Font("Leelawadee", Font.BOLD, 13));
        btnEditar = new JButton("Editar");
        btnEditar.setBounds(151, 193, 86, 25);
        btnEditar.setForeground(Color.CYAN);
        btnEditar.setBackground(Color.BLACK);
        btnEditar.setFont(new Font("Leelawadee", Font.BOLD, 13));
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(251, 193, 98, 25);
        btnEliminar.setForeground(new Color(255, 0, 0));
        btnEliminar.setFont(new Font("Leelawadee", Font.BOLD, 13));
        btnEliminar.setBackground(Color.BLACK);
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(359, 193, 97, 25);
        btnLimpiar.setForeground(new Color(255, 250, 205));
        btnLimpiar.setFont(new Font("Leelawadee", Font.BOLD, 13));
        btnLimpiar.setBackground(Color.BLACK);
        panelBotones.setLayout(null);

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("Distrito");

        tablaClientes = new JTable(modeloTabla);
        tablaClientes.setBackground(new Color(255, 250, 250));
        tablaClientes.setForeground(new Color(0, 0, 0));
        tablaClientes.setFont(new Font("Leelawadee", Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(tablaClientes);
        getContentPane().add(panelBotones, BorderLayout.CENTER);
        
        lblCodigo = new JLabel("Código:");
        lblCodigo.setForeground(new Color(255, 255, 255));
        lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblCodigo.setBounds(10, 11, 112, 25);
        panelBotones.add(lblCodigo);
        
        txtNombre = new JTextField();
        txtNombre.setBackground(new Color(255, 250, 250));
        txtNombre.setBounds(132, 45, 162, 25);
        panelBotones.add(txtNombre);
        
        lblNombre = new JLabel("Nombre:");
        lblNombre.setForeground(new Color(255, 255, 255));
        lblNombre.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNombre.setBounds(10, 44, 87, 25);
        panelBotones.add(lblNombre);
        
        txtCodigo = new JTextField();
        txtCodigo.setBackground(new Color(255, 250, 250));
        txtCodigo.setBounds(132, 11, 162, 25);
        panelBotones.add(txtCodigo);
        
        lblApellido = new JLabel("Apellido:");
        lblApellido.setForeground(new Color(255, 255, 255));
        lblApellido.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblApellido.setBounds(10, 81, 103, 25);
        panelBotones.add(lblApellido);
        
        txtApellido = new JTextField();
        txtApellido.setBackground(new Color(255, 250, 250));
        txtApellido.setBounds(132, 82, 162, 25);
        panelBotones.add(txtApellido);
        
        lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setForeground(new Color(255, 255, 255));
        lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTelefono.setBounds(10, 116, 97, 25);
        panelBotones.add(lblTelefono);
        
        txtTelefono = new JTextField();
        txtTelefono.setBackground(new Color(255, 250, 250));
        txtTelefono.setBounds(131, 117, 163, 25);
        panelBotones.add(txtTelefono);
        
        lblDistrito = new JLabel("Distrito:");
        lblDistrito.setForeground(new Color(255, 255, 255));
        lblDistrito.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblDistrito.setBounds(10, 153, 87, 25);
        panelBotones.add(lblDistrito);
        
        cmbDistrito = new JComboBox<String>();
        cmbDistrito.setFont(new Font("Leelawadee", Font.BOLD, 13));
        cmbDistrito.setBackground(new Color(255, 250, 205));
        cmbDistrito.setBounds(132, 153, 159, 25);
        panelBotones.add(cmbDistrito);
        
        lblNewLabel = new JLabel("");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setIcon(new ImageIcon("IMG\\fond-pan.jpg"));
        lblNewLabel.setBounds(0, 0, 486, 263);
        panelBotones.add(lblNewLabel);
        getContentPane().add(scrollPane, BorderLayout.SOUTH);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarCliente();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarCliente();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCliente();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        cargarDistritos();

        mostrarClientes();
    }

    private void cargarDistritos() {
        ArrayList<String> distritos = clienteDAO.obtenerDistritos();

        for (String distrito : distritos) {
            cmbDistrito.addItem(distrito);
        }
    }

    private void mostrarClientes() {
        ArrayList<Cliente> clientes = clienteDAO.obtenerClientes();
        modeloTabla.setRowCount(0);

        for (Cliente cliente : clientes) {
            Object[] fila = {
                    cliente.getCodCli(),
                    cliente.getNomCli(),
                    cliente.getApCli(),
                    cliente.getCelCli(),
                    cliente.getCodDis()
            };
            modeloTabla.addRow(fila);
        }
    }

    private void registrarCliente() {
    	try {
    	     Cliente cliente = new Cliente();
    	        cliente.setCodCli(Integer.parseInt(txtCodigo.getText()));
    	        cliente.setNomCli(txtNombre.getText());
    	        cliente.setApCli(txtApellido.getText());
    	        cliente.setCelCli(txtTelefono.getText());
    	        cliente.setCodDis(cmbDistrito.getSelectedIndex() + 1);

    	        clienteDAO.registrarCliente(cliente);
    	        JOptionPane.showMessageDialog(null, "Cliente Registrado correctamente.");
    	        mostrarClientes();
    	        limpiarCampos();
    	        
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "No se puede Registrar Verique los campos", "Error", JOptionPane.ERROR_MESSAGE);
		}
   
    }

    private void editarCliente() {
        int filaSeleccionada = tablaClientes.getSelectedRow();

        if (filaSeleccionada != -1) {
            int codigoCliente = (int) tablaClientes.getValueAt(filaSeleccionada, 0);
            Cliente cliente = clienteDAO.obtenerClientePorCodigo(codigoCliente);

            // Verificar cada campo de texto antes de actualizar el cliente
            if (!txtNombre.getText().isEmpty()) {
                cliente.setNomCli(txtNombre.getText());
            }

            if (!txtApellido.getText().isEmpty()) {
                cliente.setApCli(txtApellido.getText());
            }

            if (!txtTelefono.getText().isEmpty()) {
                cliente.setCelCli(txtTelefono.getText());
            }

            if (cmbDistrito.getSelectedIndex() != -1) {
                cliente.setCodDis(cmbDistrito.getSelectedIndex() + 1);
            }

            clienteDAO.actualizarCliente(cliente);
            JOptionPane.showMessageDialog(null, "Cliente Editado correctamente.");
            mostrarClientes();
            limpiarCampos();
        } else {
        	JOptionPane.showMessageDialog(this, "Seleccione un cliente de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void eliminarCliente() {
        int filaSeleccionada = tablaClientes.getSelectedRow();

        if (filaSeleccionada != -1) {
            int codigoCliente = (int) tablaClientes.getValueAt(filaSeleccionada, 0);

            clienteDAO.eliminarCliente(codigoCliente);
            JOptionPane.showMessageDialog(null, "Cliente Eliminado correctamente.");
            mostrarClientes();
            limpiarCampos();
        } else {
        	JOptionPane.showMessageDialog(this, "Seleccione un cliente de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        cmbDistrito.setSelectedIndex(0);
        tablaClientes.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ClientesGUI().setVisible(true);
            }
        });
    }
}

