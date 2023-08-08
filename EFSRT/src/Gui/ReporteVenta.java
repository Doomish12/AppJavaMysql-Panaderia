package Gui;

import com.itextpdf.io.exceptions.IOException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import Dao.ReporteVentaDAO;
import entidades.Detalle_Venta;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;

public class ReporteVenta extends JFrame {
    private JButton btnGenerarReporte;
    private JButton btnGenerarPDF;

    public ReporteVenta() {
        super("Reporte de Venta");

        // Configuración de la GUI
        initComponents();
        setLayout();
        setupListeners();

        pack();
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
    }

    private void initComponents() {

        // Botón "Generar PDF"
        btnGenerarPDF = new JButton("Generar PDF");
    }

    private void setLayout() {
        getContentPane().setLayout(new BorderLayout());

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());
        // Botón "Generar Reporte"
        btnGenerarReporte = new JButton("Generar Reporte");
        
                panelBotones.add(btnGenerarReporte);
        panelBotones.add(btnGenerarPDF);

        panelPrincipal.add(panelBotones, BorderLayout.NORTH);

        getContentPane().add(panelPrincipal);
    }

    private void setupListeners() {
        // Listener del botón "Generar Reporte"
        btnGenerarReporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReporte();
            }
        });

        // Listener del botón "Generar PDF"
        btnGenerarPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarPDF();
            }
        });
    }

    private void generarReporte() {
        ReporteVentaDAO reporteVentaDAO = new ReporteVentaDAO();
        List<Detalle_Venta> ventas = reporteVentaDAO.obtenerVentas();

        // Mostrar las ventas en el JTextArea
        StringBuilder reporte = new StringBuilder();
        reporte.append("REPORTES DE VENTAS\n\n");
        for (Detalle_Venta detalle_Venta : ventas) {
            reporte.append("==============================================================================================\n");
            reporte.append("Código Empleado: " + detalle_Venta.getCodEmp() + "\n");
            reporte.append("Vendedor: " + detalle_Venta.getVendedor() + "\n");
            reporte.append("Codigo Producto: " + detalle_Venta.getCodigoProducto() + "\n");
            reporte.append("Nombre Producto: " + detalle_Venta.getNombreProducto() + "\n");
            reporte.append("Cantidad: " + detalle_Venta.getCantidad() + "\n");
            reporte.append("Precio Unitario: " + detalle_Venta.getPrecioUnitario() + "\n");
            reporte.append("Total: " + detalle_Venta.getTotal() + "\n");
            reporte.append("Fecha: " + detalle_Venta.getFecha() + "\n");
            reporte.append("Importe Total: " + detalle_Venta.getImporteTotal() + "\n");
            reporte.append("Vuelto Dinero: " + detalle_Venta.getVueltoDinero() + "\n\n");
        }

        JOptionPane.showMessageDialog(this, reporte.toString(), "Generar Reporte",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void generarPDF() {
        ReporteVentaDAO reporteVentaDAO = new ReporteVentaDAO();
        List<Detalle_Venta> ventas = reporteVentaDAO.obtenerVentas();

        // Crear el documento PDF
        try {
            PdfWriter writer = new PdfWriter("reporte_ventas.pdf");
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Agregar el título
            Paragraph title = new Paragraph("Reportes de Ventas");
            title.setFontSize(20);
            title.setBold();
            document.add(title);

            // Crear la tabla
            Table table = new Table(10);
            table.addHeaderCell("Código Empleado");
            table.addHeaderCell("Vendedor");
            table.addHeaderCell("Codigo Producto");
            table.addHeaderCell("Nombre Producto");
            table.addHeaderCell("Cantidad");
            table.addHeaderCell("Precio Unitario");
            table.addHeaderCell("Total");
            table.addHeaderCell("Fecha");
            table.addHeaderCell("Importe Total");
            table.addHeaderCell("Vuelto Dinero");

            // Agregar las ventas a la tabla
            for (Detalle_Venta venta : ventas) {
                table.addCell(Integer.toString(venta.getCodEmp()));
                table.addCell(venta.getVendedor());
                table.addCell(Integer.toString(venta.getCodigoProducto()));
                table.addCell(venta.getNombreProducto());
                table.addCell(Integer.toString(venta.getCantidad()));
                table.addCell(Double.toString(venta.getPrecioUnitario()));
                table.addCell(Double.toString(venta.getTotal()));
                table.addCell(venta.getFecha());
                table.addCell(Double.toString(venta.getImporteTotal()));
                table.addCell(Double.toString(venta.getVueltoDinero()));
            }

            // Agregar la tabla al documento
            document.add(table);

            document.close();

            JOptionPane.showMessageDialog(this, "El PDF se generó correctamente.", "Generar PDF",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "No se encontró el archivo.", "Generar PDF",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error al generar el PDF.", "Generar PDF",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ReporteVenta();
            }
        });
    }
}
