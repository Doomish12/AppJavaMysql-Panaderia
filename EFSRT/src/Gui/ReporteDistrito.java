package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.io.exceptions.IOException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import Dao.DistritoDAO;
import entidades.Distrito;

public class ReporteDistrito extends JFrame {
    private JButton btnGenerarReporte;
    private JButton btnGenerarPDF;

    public ReporteDistrito() {
        super("Reporte de Distrito");

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
        DistritoDAO distritoDAO = new DistritoDAO();
        List<Distrito> distritos = distritoDAO.obtenerDistritos();

        // Mostrar los distritos en una ventana de diálogo
        StringBuilder reporte = new StringBuilder();
        for (Distrito distrito : distritos) {
            reporte.append("=============================================================================================="
                    + "\n" + "\n" + "Código: " + distrito.getCodDis() + "\n" + "Nombre: " + distrito.getNomDis() + "\n"
                    + "\n");
        }
        JOptionPane.showMessageDialog(this, reporte.toString(), "Reporte de Distritos", JOptionPane.INFORMATION_MESSAGE);
    }

    private void generarPDF() {
        DistritoDAO distritoDAO = new DistritoDAO();
        List<Distrito> distritos = distritoDAO.obtenerDistritos();

        // Crear el documento PDF
        try {
            PdfWriter writer = new PdfWriter("reporte_distritos.pdf");
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Agregar el título al documento PDF
            Paragraph title = new Paragraph("Reporte de Distritos");
            document.add(title);

         // Crear la tabla
            Table table = new Table(2); // 2 columnas para Código y Nombre

            // Agregar encabezados de columna
            table.addCell(new Cell().add(new Paragraph("Código")));
            table.addCell(new Cell().add(new Paragraph("Nombre")));

         // Agregar los distritos a la tabla
            for (Distrito distrito : distritos) {
                table.addCell(new Cell().add(new Paragraph(String.valueOf(distrito.getCodDis()))));
                table.addCell(new Cell().add(new Paragraph(distrito.getNomDis())));
            }



            // Agregar la tabla al documento PDF
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
                new ReporteDistrito();
            }
        });
    }
}
