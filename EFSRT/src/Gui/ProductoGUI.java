package Gui;

import entidades.Producto;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Dao.ProductoDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductoGUI extends JFrame {
    private ProductoDAO productoDAO;
    private JButton btnRegistrar;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private JLabel lblCodigo;
    private JTextField txtNombre;
    private JLabel lblNombre;
    private JTextField txtPrecio;
    private JLabel lblPrecio;
    private JTextField txtCodigo;
    private JLabel lblNewLabel;

    public ProductoGUI() {
    	setResizable(false);
        productoDAO = new ProductoDAO(); // Reemplaza "ProductoDAO" con el nombre de tu clase DAO para la entidad Producto

        setTitle("Gestión de Productos");
 
        setSize(559, 474);
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
        btnRegistrar.setForeground(new Color(224, 255, 255));
        btnRegistrar.setBackground(new Color(0, 0, 0));
        btnRegistrar.setFont(new Font("Leelawadee", Font.BOLD, 13));
        btnRegistrar.setBounds(35, 190, 117, 23);
        btnEditar = new JButton("Editar");
        btnEditar.setForeground(new Color(0, 255, 0));
        btnEditar.setBackground(new Color(0, 0, 0));
        btnEditar.setFont(new Font("Leelawadee", Font.BOLD, 13));
        btnEditar.setBounds(166, 190, 114, 23);
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setForeground(new Color(255, 0, 0));
        btnEliminar.setBackground(new Color(0, 0, 0));
        btnEliminar.setFont(new Font("Leelawadee", Font.BOLD, 13));
        btnEliminar.setBounds(287, 190, 96, 23);
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setForeground(new Color(255, 255, 240));
        btnLimpiar.setBackground(new Color(0, 0, 0));
        btnLimpiar.setFont(new Font("Leelawadee", Font.BOLD, 13));
        btnLimpiar.setBounds(389, 190, 106, 23);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarProducto();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarProducto();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
        panel.setLayout(null);

        panel.add(btnRegistrar);
        panel.add(btnEditar);
        panel.add(btnEliminar);
        panel.add(btnLimpiar);
        
        lblCodigo = new JLabel("Código:");
        lblCodigo.setForeground(new Color(255, 255, 255));
        lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblCodigo.setBounds(35, 29, 89, 20);
        panel.add(lblCodigo);
        
        txtNombre = new JTextField();
        txtNombre.setBounds(166, 114, 114, 20);
        panel.add(txtNombre);
        
        lblNombre = new JLabel("Nombre:");
        lblNombre.setForeground(new Color(255, 255, 255));
        lblNombre.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNombre.setBounds(35, 111, 96, 20);
        panel.add(lblNombre);
        
        txtPrecio = new JTextField();
        txtPrecio.setBounds(166, 76, 114, 20);
        panel.add(txtPrecio);
        
        lblPrecio = new JLabel("Precio:");
        lblPrecio.setForeground(new Color(255, 255, 255));
        lblPrecio.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblPrecio.setBounds(35, 73, 89, 20);
        panel.add(lblPrecio);
        
        txtCodigo = new JTextField();
        txtCodigo.setBounds(166, 32, 114, 20);
        panel.add(txtCodigo);
        
        lblNewLabel = new JLabel("");
        lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewLabel.setIcon(new ImageIcon("IMG\\fond-pan.jpg"));
        lblNewLabel.setBounds(0, 0, 543, 268);
        panel.add(lblNewLabel);

        return panel;
    }

    private JScrollPane crearPanelTabla() {
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Precio");

        tablaProductos = new JTable(modeloTabla);
        tablaProductos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tablaProductos);
        scrollPane.setPreferredSize(new Dimension(700, 200));

        return scrollPane;
    }

    private void cargarDatosTabla() {
        List<Producto> productos = productoDAO.obtenerProductos();

        modeloTabla.setRowCount(0); // Limpiar filas existentes

        for (Producto producto : productos) {
            Object[] fila = {producto.getCodPro(), producto.getNomPro(), producto.getPrecioPro()};
            modeloTabla.addRow(fila);
        }
    }

    private void registrarProducto() {
    	try {
            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();
            String precio = txtPrecio.getText();

            Producto producto = new Producto();
            producto.setCodPro(Integer.parseInt(codigo));
            producto.setNomPro(nombre);
            producto.setPrecioPro(Double.parseDouble(precio));

            productoDAO.insertarProducto(producto);
            JOptionPane.showMessageDialog(null, "Producto registrado correctamente.");
            limpiarCampos();
            cargarDatosTabla();
            
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "No se puede Registrar Verique los campos", "Error", JOptionPane.ERROR_MESSAGE);
		}

    }

    private void editarProducto() {
        int filaSeleccionada = tablaProductos.getSelectedRow();

        if (filaSeleccionada != -1) {
            int codigoProducto = (int) tablaProductos.getValueAt(filaSeleccionada, 0);
            String nombre = (String) tablaProductos.getValueAt(filaSeleccionada, 1);
            double precioProducto = (double) tablaProductos.getValueAt(filaSeleccionada, 2);

            String codigo = txtCodigo.getText();
            String nombreEditado = txtNombre.getText();
            String precioEditado = txtPrecio.getText();

            if (!codigo.isEmpty()) {
                codigoProducto = Integer.parseInt(codigo);
            }

            if (!nombreEditado.isEmpty()) {
                nombre = nombreEditado;
            }

            if (!precioEditado.isEmpty()) {
                precioProducto = Double.parseDouble(precioEditado);
            }

            Producto producto = new Producto();
            producto.setCodPro(codigoProducto);
            producto.setNomPro(nombre);
            producto.setPrecioPro(precioProducto);

            productoDAO.actualizarProducto(producto);
            JOptionPane.showMessageDialog(null, "Producto editado correctamente.");
            limpiarCampos();
            cargarDatosTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto de la tabla para editar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void eliminarProducto() {
        int filaSeleccionada = tablaProductos.getSelectedRow();

        if (filaSeleccionada != -1) {
            int codigo = (int) tablaProductos.getValueAt(filaSeleccionada, 0);

            productoDAO.eliminarProducto(codigo);
            JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");
            limpiarCampos();
            cargarDatosTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un producto de la tabla para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtPrecio.setText("");
        tablaProductos.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ProductoGUI();
            }
        });
    }
}

