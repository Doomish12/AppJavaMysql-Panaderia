package Gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Dao.SistemaVentasDAO;
import entidades.Detalle_Venta;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

public class SistemaVentasGUI extends JFrame {
    private SistemaVentasDAO sistemaVentasDAO;
    private JTextField txtCodigoCliente, txtCliente, txtCodigoProducto, txtProducto, txtPrecio, txtCantidad, txtImporteTotal, txtFechaActual, txtVendedor;
    private JTable tablaVentas;
    private DefaultTableModel modeloTabla;

    public SistemaVentasGUI() {
        super("Panaderia");

        sistemaVentasDAO = new SistemaVentasDAO(); // Instancia de SistemaVentasDAO para acceder a la base de datos

        // Configuración de la GUI
        setLayout(new BorderLayout());

        // Título
        JLabel lblTitulo = new JLabel("Panaderia");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo, BorderLayout.NORTH);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de entrada
        JPanel panelEntrada = new JPanel(new GridLayout(5, 4, 5, 5));
        panelEntrada.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        // Filas 1-4: Componentes de entrada
        panelEntrada.add(new JLabel("Código Cliente:"));
        panelEntrada.add(txtCodigoCliente = new JTextField());
        panelEntrada.add(createButton("Buscar", e -> buscarCliente()));
        panelEntrada.add(new JLabel("Cliente:"));
        panelEntrada.add(txtCliente = new JTextField());
        panelEntrada.add(new JLabel("Código Producto:"));
        panelEntrada.add(txtCodigoProducto = new JTextField());
        panelEntrada.add(createButton("Buscar", e -> buscarProducto()));
        panelEntrada.add(new JLabel("Producto:"));
        panelEntrada.add(txtProducto = new JTextField());
        panelEntrada.add(new JLabel("Precio:"));
        panelEntrada.add(txtPrecio = new JTextField());
        panelEntrada.add(createButton("Agregar", e -> agregarVendedor()));
        panelEntrada.add(new JLabel("Vendedor:"));
        panelEntrada.add(txtVendedor = new JTextField());
        panelEntrada.add(new JLabel("Cantidad:"));
        panelEntrada.add(txtCantidad = new JTextField());
        panelEntrada.add(new JLabel("Importe Total:"));
        panelEntrada.add(txtImporteTotal = new JTextField());
        panelEntrada.add(new JLabel("Fecha Actual:"));
        panelEntrada.add(txtFechaActual = new JTextField());

        // Mostrar la fecha actual en el campo de texto txtFechaActual
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String fechaActual = dateFormat.format(new Date());
        txtFechaActual.setText(fechaActual);
        txtFechaActual.setEditable(false);

        panelPrincipal.add(panelEntrada, BorderLayout.NORTH);

        // Botón "Generar Venta"
        JButton btnGenerarVenta = createButton("Generar Venta", e -> generarVenta());
        panelPrincipal.add(btnGenerarVenta, BorderLayout.CENTER);

        // Tabla de ventas
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Vendedor");
        modeloTabla.addColumn("Código Producto");
        modeloTabla.addColumn("Producto");
        modeloTabla.addColumn("Cantidad");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Total");
        modeloTabla.addColumn("Vuelto Dinero");
        modeloTabla.addColumn("Fecha");

        tablaVentas = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaVentas);
        panelPrincipal.add(scrollPane, BorderLayout.SOUTH);

        add(panelPrincipal);

        pack();
        setSize(900,700);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }

    private void buscarCliente() {
        int codigoCliente = Integer.parseInt(txtCodigoCliente.getText());
        try {
            ResultSet resultSet = sistemaVentasDAO.obtenerClientePorCodigo(codigoCliente);
            if (resultSet.next()) {
                String nombreCliente = resultSet.getString("nomCli");
                txtCliente.setText(nombreCliente);
            } else {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void buscarProducto() {
        int codigoProducto = Integer.parseInt(txtCodigoProducto.getText());
        try {
            ResultSet resultSet = sistemaVentasDAO.obtenerProductoPorCodigo(codigoProducto);
            if (resultSet.next()) {
                String nombreProducto = resultSet.getString("nomPro");
                double precioProducto = resultSet.getDouble("precioPro");
                txtProducto.setText(nombreProducto);
                txtPrecio.setText(Double.toString(precioProducto));
            } else {
                JOptionPane.showMessageDialog(this, "Producto no encontrado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void agregarVendedor() {
        try {
            List<String> nombresEmpleados = sistemaVentasDAO.obtenerNombresEmpleados();
            int cantidadEmpleados = nombresEmpleados.size();

            if (cantidadEmpleados > 0) {
                int randomIndex = (int) (Math.random() * cantidadEmpleados);
                String nombreEmpleado = nombresEmpleados.get(randomIndex);
                txtVendedor.setText(nombreEmpleado);
            } else {
                JOptionPane.showMessageDialog(this, "No hay empleados registrados");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    
    private void generarVenta() {
        try {
        	  // Obtener los datos de los campos de texto
            int codigoProducto = Integer.parseInt(txtCodigoProducto.getText());
            int importeTotal = Integer.parseInt(txtImporteTotal.getText());
            String nombreProducto = txtProducto.getText();
            int cantidad = Integer.parseInt(txtCantidad.getText());
            double precioUnitario = Double.parseDouble(txtPrecio.getText());
            double total = precioUnitario * cantidad;
            double vueltoDinero = (importeTotal - total); // Corregido: calcular vueltoDinero usando importeTotal - total
            
            // Validar el importe total
            if (importeTotal <= total) {
                JOptionPane.showMessageDialog(this, "El importe total no coincide con el cálculo del precio y la cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Detener la generación de la venta si no coincide el importe total
            }else {
            	 JOptionPane.showMessageDialog(null, "Venta Realizada correctamente.");
            }
           
            // Formatear la fecha al formato "yyyy-MM-dd"
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fecha = dateFormat.format(new Date());
            
         
            String nombreEmpleado = txtVendedor.getText();
            int codEmp = sistemaVentasDAO.obtenerCodigoEmpleadoPorNombre(nombreEmpleado);
            
         
     //       int codPro = 1;
           
            // Crear objeto Detalle_Venta con los datos
            Detalle_Venta detalleVenta = new Detalle_Venta();
            detalleVenta.setCodEmp(codEmp);
            detalleVenta.setVendedor(txtVendedor.getText());
            detalleVenta.setCodigoProducto(codigoProducto);
            detalleVenta.setNombreProducto(nombreProducto);
            detalleVenta.setCantidad(cantidad);
            detalleVenta.setPrecioUnitario(precioUnitario);
            detalleVenta.setTotal(total);
            detalleVenta.setFecha(fecha);
      
   //         detalleVenta.setCodPro(codPro);
            detalleVenta.setImporteTotal(importeTotal);
            detalleVenta.setVueltoDinero(vueltoDinero);
            
            // Insertar el objeto Detalle_Venta en la base de datos
            sistemaVentasDAO.insertarVenta(detalleVenta, tablaVentas);

            //
          
            // Limpiar la tabla de ventas antes de agregar la venta
            limpiarTablaVentas();

            // Agregar la venta generada a la tabla en la GUI
            agregarVentaATabla(detalleVenta);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error en el formato de los datos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al generar la venta.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }





    private void agregarVentaATabla(Detalle_Venta detalleVenta) {
        // Obtener el modelo de la tabla
        DefaultTableModel tableModel = (DefaultTableModel) tablaVentas.getModel();

        // Crear un arreglo con los datos de la venta
        Object[] rowData = {
                detalleVenta.getVendedor(),
                detalleVenta.getCodigoProducto(),
                detalleVenta.getNombreProducto(),
                detalleVenta.getCantidad(),
                detalleVenta.getPrecioUnitario(),
                detalleVenta.getTotal(),
                detalleVenta.getVueltoDinero(),
                detalleVenta.getFecha()
        };

        // Agregar el arreglo como una nueva fila a la tabla
        tableModel.addRow(rowData);
    }



    
    
    
    private void limpiarTablaVentas() {
        DefaultTableModel tableModel = (DefaultTableModel) tablaVentas.getModel();
        tableModel.setRowCount(0);
    }


    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(SistemaVentasGUI::new);
    }
}