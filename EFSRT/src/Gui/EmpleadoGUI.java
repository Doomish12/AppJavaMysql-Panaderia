package Gui;

import entidades.Empleado;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Dao.EmpleadoDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EmpleadoGUI extends JFrame {
    private EmpleadoDAO empleadoDAO;
    private JButton btnRegistrar;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JTable tablaEmpleados;
    private DefaultTableModel modeloTabla;
    private JLabel lblNewLabel;
    private JLabel lblCodigo;
    private JTextField txtApellido;
    private JLabel lblNombre;
    private JTextField txtNombre;
    private JLabel lblApellido;
    private JTextField txtCodigo;

    public EmpleadoGUI() {
    	setResizable(false);
        empleadoDAO = new EmpleadoDAO(); // Reemplaza "EmpleadoDAO" con el nombre de tu clase DAO para la entidad Empleado

        setTitle("Gestión de Empleados");
        setSize(482, 453);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        JPanel panelBotones = crearPanelBotones();
        JScrollPane panelTabla = crearPanelTabla();
        getContentPane().add(panelBotones, BorderLayout.CENTER);
        getContentPane().add(panelTabla, BorderLayout.SOUTH);

        cargarDatosTabla();

        setVisible(true);
    }

 

    private JPanel crearPanelBotones() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setFont(new Font("Leelawadee", Font.BOLD, 13));
        btnRegistrar.setBackground(SystemColor.desktop);
        btnRegistrar.setForeground(new Color(224, 255, 255));
        btnRegistrar.setBounds(20, 154, 100, 23);
        btnEditar = new JButton("Editar");
        btnEditar.setFont(new Font("Leelawadee", Font.BOLD, 13));
        btnEditar.setBackground(SystemColor.desktop);
        btnEditar.setForeground(new Color(0, 255, 0));
        btnEditar.setBounds(130, 154, 96, 23);
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("Leelawadee", Font.BOLD, 13));
        btnEliminar.setBackground(SystemColor.desktop);
        btnEliminar.setForeground(new Color(255, 0, 0));
        btnEliminar.setBounds(236, 154, 88, 23);
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setFont(new Font("Leelawadee", Font.BOLD, 13));
        btnLimpiar.setBackground(SystemColor.desktop);
        btnLimpiar.setForeground(new Color(255, 250, 250));
        btnLimpiar.setBounds(334, 154, 88, 23);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarEmpleado();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarEmpleado();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarEmpleado();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
        panel.setLayout(null);
        
        lblApellido = new JLabel("Apellido:");
        lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
        lblApellido.setBackground(new Color(255, 255, 255));
        lblApellido.setForeground(new Color(255, 255, 255));
        lblApellido.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblApellido.setBounds(20, 99, 88, 20);
        panel.add(lblApellido);
        
        txtApellido = new JTextField();
        txtApellido.setBounds(160, 99, 132, 20);
        panel.add(txtApellido);
        
        txtNombre = new JTextField();
        txtNombre.setBounds(160, 55, 132, 20);
        panel.add(txtNombre);
        
        txtCodigo = new JTextField();
        txtCodigo.setBounds(160, 24, 132, 20);
        panel.add(txtCodigo);
        
        lblNombre = new JLabel("Nombre:");
        lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
        lblNombre.setBackground(new Color(255, 255, 255));
        lblNombre.setForeground(new Color(255, 255, 255));
        lblNombre.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNombre.setBounds(20, 59, 88, 20);
        panel.add(lblNombre);
        
        lblCodigo = new JLabel("Código:");
        lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
        lblCodigo.setBackground(new Color(255, 255, 255));
        lblCodigo.setForeground(new Color(255, 255, 255));
        lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblCodigo.setBounds(20, 24, 88, 20);
        panel.add(lblCodigo);

        panel.add(btnRegistrar);
        panel.add(btnEditar);
        panel.add(btnEliminar);
        panel.add(btnLimpiar);
        
        lblNewLabel = new JLabel("");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setIcon(new ImageIcon("IMG\\fond-pan.jpg"));
        lblNewLabel.setBounds(0, 0, 466, 214);
        panel.add(lblNewLabel);

        return panel;
    }

    private JScrollPane crearPanelTabla() {
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");

        tablaEmpleados = new JTable(modeloTabla);
        tablaEmpleados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tablaEmpleados);
        scrollPane.setPreferredSize(new Dimension(700, 200));

        return scrollPane;
    }

    private void cargarDatosTabla() {
        List<Empleado> empleados = empleadoDAO.obtenerEmpleados();

        modeloTabla.setRowCount(0); // Limpiar filas existentes

        for (Empleado empleado : empleados) {
            Object[] fila = {empleado.getCodEmp(), empleado.getNomEmp(), empleado.getApEmp()};
            modeloTabla.addRow(fila);
        }
    }

    private void registrarEmpleado() {
    	
    	try {
    		  String codigo = txtCodigo.getText();
    	        String nombre = txtNombre.getText();
    	        String apellido = txtApellido.getText();

    	        Empleado empleado = new Empleado();
    	        empleado.setCodEmp(Integer.parseInt(codigo));
    	        empleado.setNomEmp(nombre);
    	        empleado.setApEmp(apellido);

    	        empleadoDAO.insertarEmpleado(empleado);
    	        JOptionPane.showMessageDialog(null, "Empleado registrado correctamente.");
    	        limpiarCampos();
    	        cargarDatosTabla();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "No se puede Registrar Verique los campos", "Error", JOptionPane.ERROR_MESSAGE);
		}
      
    }

    private void editarEmpleado() {
        int filaSeleccionada = tablaEmpleados.getSelectedRow();

        if (filaSeleccionada != -1) {
            int codigoEmpleado = (int) tablaEmpleados.getValueAt(filaSeleccionada, 0);
            String nombre = (String) tablaEmpleados.getValueAt(filaSeleccionada, 1);
            String apellido = (String) tablaEmpleados.getValueAt(filaSeleccionada, 2);

            String codigo = txtCodigo.getText();
            String nombreEditado = txtNombre.getText();
            String apellidoEditado = txtApellido.getText();

            if (!codigo.isEmpty()) {
                codigoEmpleado = Integer.parseInt(codigo);
            }

            if (!nombreEditado.isEmpty()) {
                nombre = nombreEditado;
            }

            if (!apellidoEditado.isEmpty()) {
                apellido = apellidoEditado;
            }

            Empleado empleado = new Empleado();
            empleado.setCodEmp(codigoEmpleado);
            empleado.setNomEmp(nombre);
            empleado.setApEmp(apellido);

            empleadoDAO.actualizarEmpleado(empleado);
            JOptionPane.showMessageDialog(null, "Empleado editado correctamente.");
            limpiarCampos();
            cargarDatosTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un empleado de la tabla para editar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void eliminarEmpleado() {
        int filaSeleccionada = tablaEmpleados.getSelectedRow();

        if (filaSeleccionada != -1) {
            int codigo = (int) tablaEmpleados.getValueAt(filaSeleccionada, 0);

            empleadoDAO.eliminarEmpleado(codigo);
            JOptionPane.showMessageDialog(null, "Empleado eliminado correctamente.");
            limpiarCampos();
            cargarDatosTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un empleado de la tabla para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        tablaEmpleados.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EmpleadoGUI();
            }
        });
    }
}
