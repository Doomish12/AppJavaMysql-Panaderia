package Gui;

import com.itextpdf.io.exceptions.IOException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import Dao.ReporteProductoDAO;
import entidades.Producto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

public class ReporteProducto extends JFrame {
    private JButton btnGenerarReporte;
    private JButton btnGenerarPDF;

    public ReporteProducto() {
        super("Reporte de Productos");

        // Configuración de la GUI
        getContentPane().setLayout(new BorderLayout());

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Panel de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());

        // Botón "Generar Reporte"
        btnGenerarReporte = new JButton("Generar Reporte");
        btnGenerarReporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarReporte();
            }
        });
        panelBotones.add(btnGenerarReporte);

        // Botón "Generar PDF"
        btnGenerarPDF = new JButton("Generar PDF");
        btnGenerarPDF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarPDF();
            }
        });
        panelBotones.add(btnGenerarPDF);

        panelPrincipal.add(panelBotones, BorderLayout.NORTH);

        getContentPane().add(panelPrincipal);

        pack();
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
    }

    private void generarReporte() {
        ReporteProductoDAO reporteProductoDAO = new ReporteProductoDAO();
        List<Producto> productos = reporteProductoDAO.obtenerProductos();

        // Mostrar los productos en el JTextArea
        StringBuilder reporte = new StringBuilder();
        for (Producto producto : productos) {
            reporte.append("=============================================================================================="
                    + "\n" + "\n" + "Código: " + producto.getCodPro() + "\n" + "Nombre: " + producto.getNomPro() +
                    "\n" + "Precio: " + producto.getPrecioPro() + "\n" + "\n");
        }

        JOptionPane.showMessageDialog(this, reporte.toString(), "Generar Reporte",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void generarPDF() {
        ReporteProductoDAO reporteProductoDAO = new ReporteProductoDAO();
        List<Producto> productos = reporteProductoDAO.obtenerProductos();

        // Crear el documento PDF
        try {
            PdfWriter writer = new PdfWriter("reporte_productos.pdf");
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Agregar el título
            Paragraph title = new Paragraph("Reporte de Productos");
            title.setFontSize(20);
            title.setBold();
            document.add(title);

            // Crear la tabla
            Table table = new Table(3);
            table.addHeaderCell("Código");
            table.addHeaderCell("Nombre");
            table.addHeaderCell("Precio");

            // Agregar los productos a la tabla
            for (Producto producto : productos) {
                table.addCell(Integer.toString(producto.getCodPro()));
                table.addCell(producto.getNomPro());
                table.addCell(Double.toString(producto.getPrecioPro()));
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
                new ReporteProducto();
            }
        });
    }
}
