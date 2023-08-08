package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Principal extends JFrame {
    private JMenuBar menuBar;
    private JMenu mnInicio;
    private JMenu mnMantenedores;
    private JMenu mnVentas;
    private JMenu mnReportes;
    private JMenu mnInfo;
    private JMenuItem mntmSalir;
    private JMenuItem mntmDistritos;
    private JMenuItem mntmEmpleados;
    private JMenuItem mntmProductos;
    private JMenuItem mntmClientes;
    private JMenuItem mntmNewMenuItem_4;
    private JMenuItem mntmRDistritos;
    private JMenuItem mntmREmpleados;
    private JMenuItem mntmRProductos;
    private JMenuItem mntmRventas;
    private JMenuItem mntmNosotros;
    private JLabel lblNewLabel;

    public Principal() {
        super("GUI Principal");
        getContentPane().setBackground(Color.BLACK);
        setTitle("Panaderia");

        // Configuración de la GUI
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(598, 554);
        setLocationRelativeTo(null);
        setResizable(false);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        mnInicio = new JMenu("Inicio");
        menuBar.add(mnInicio);

        mntmSalir = new JMenuItem("Salir");
        mnInicio.add(mntmSalir);

        mnMantenedores = new JMenu("Mantenedores");
        menuBar.add(mnMantenedores);

        mntmDistritos = new JMenuItem("Distritos");
        mnMantenedores.add(mntmDistritos);

        mntmEmpleados = new JMenuItem("Empleados");
        mnMantenedores.add(mntmEmpleados);

        mntmProductos = new JMenuItem("Productos");
        mnMantenedores.add(mntmProductos);

        mntmClientes = new JMenuItem("Clientes");
        mnMantenedores.add(mntmClientes);

        mnVentas = new JMenu("Ventas");
        menuBar.add(mnVentas);

        mntmNewMenuItem_4 = new JMenuItem("Generar Venta");
        mnVentas.add(mntmNewMenuItem_4);

        mnReportes = new JMenu("Reportes");
        menuBar.add(mnReportes);

        mntmRDistritos = new JMenuItem("Distritos");
        mnReportes.add(mntmRDistritos);

        mntmREmpleados = new JMenuItem("Empleados");
        mnReportes.add(mntmREmpleados);

        mntmRProductos = new JMenuItem("Productos");
        mnReportes.add(mntmRProductos);

        mntmRventas = new JMenuItem("Ventas");
        mnReportes.add(mntmRventas);

        mnInfo = new JMenu("Info");
        menuBar.add(mnInfo);

        mntmNosotros = new JMenuItem("Nostoros");
        mnInfo.add(mntmNosotros);
        getContentPane().setLayout(null);
        
        lblNewLabel = new JLabel("New label");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setIcon(new ImageIcon("IMG\\Panaderia_variada (1).jpg"));
        lblNewLabel.setBounds(0, -24, 582, 517);
        getContentPane().add(lblNewLabel);

       
        

        mntmNosotros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Info info = new Info();
               info.setVisible(true);
            }
        });
   
        
        // Conectar DistritoGUI al menú mntmDistritos
        mntmDistritos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DistritoGUI distritoGUI = new DistritoGUI();
                distritoGUI.setVisible(true);
            }
        });

        // Conectar EmpleadoGUI al menú mntmDistritos
        mntmEmpleados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               EmpleadoGUI empleadoGUI = new EmpleadoGUI();
                empleadoGUI.setVisible(true);
            }
        });
        
        // Conectar ProductoGUI al menú mntmDistritos
        mntmProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ProductoGUI productoGUI = new ProductoGUI();
                productoGUI.setVisible(true);
            }
        });
        
        // Conectar ClientesUI al menú mntmDistritos
        mntmClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ClientesGUI clientesGUI = new ClientesGUI();
                clientesGUI.setVisible(true);
            }
        });
        
        // Conectar Ventas al menú mntmDistritos
        mntmNewMenuItem_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               SistemaVentasGUI sistemaVentasGUI = new SistemaVentasGUI();
                sistemaVentasGUI.setVisible(true);
            }
        });
        
        
        
        mntmRDistritos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ReporteDistrito reporteDistrito = new ReporteDistrito();
                reporteDistrito.setVisible(true);
            }
        });
        
        mntmREmpleados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ReporteEmpleado reporteEmpleado = new ReporteEmpleado();
                reporteEmpleado.setVisible(true);
            }
        });
        
        mntmRProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ReporteProducto reporteProducto = new ReporteProducto();
                reporteProducto.setVisible(true);
            }
        });
        
        mntmRventas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ReporteVenta reporteVenta = new ReporteVenta();
                reporteVenta.setVisible(true);
            }
        });
        
        mntmSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        // Agregar componentes aquí

        setVisible(true);
    }

    public static void main(String[] args) {
        Principal principal = new Principal();
        principal.setVisible(true);
    }
}
