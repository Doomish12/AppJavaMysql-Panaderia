package Gui;

import com.itextpdf.io.exceptions.IOException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import Dao.ReporteEmpleadoDAO;
import entidades.Distrito;
import entidades.Empleado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

public class ReporteEmpleado extends JFrame {
	private JButton btnGenerarReporte;
    private JButton btnGenerarPDF;

    public ReporteEmpleado() {
        super("Reporte de Empleado");

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
        ReporteEmpleadoDAO reporteEmpleadoDAO = new ReporteEmpleadoDAO();
        List<Empleado> empleados = reporteEmpleadoDAO.obtenerEmpleados();

        // Mostrar los empleados en el JTextArea
        StringBuilder reporte = new StringBuilder();
        for (Empleado empleado : empleados) {
            reporte.append("=============================================================================================="
                    + "\n" + "\n" + "Código: " + empleado.getCodEmp() + "\n" + "Nombre: " + empleado.getNomEmp() + 
                    "\n" + "Apellido: " + empleado.getApEmp()+"\n"
                    + "\n");
        }

        JOptionPane.showMessageDialog(this, reporte.toString(), "Generar Reporte",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void generarPDF() {
        ReporteEmpleadoDAO reporteEmpleadoDAO = new ReporteEmpleadoDAO();
        List<Empleado> empleados = reporteEmpleadoDAO.obtenerEmpleados();

        // Crear el documento PDF
        try {
            PdfWriter writer = new PdfWriter("reporte_empleados.pdf");
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Agregar el título
            Paragraph title = new Paragraph("Reporte de Empleados");
            title.setFontSize(20);
            title.setBold();
            document.add(title);

            // Crear la tabla
            Table table = new Table(3);
            table.addHeaderCell("Código");
            table.addHeaderCell("Nombre");
            table.addHeaderCell("Apellido");

            // Agregar los empleados a la tabla
            for (Empleado empleado : empleados) {
                table.addCell(Integer.toString(empleado.getCodEmp()));
                table.addCell(empleado.getNomEmp());
                table.addCell(empleado.getApEmp());
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
                new ReporteEmpleado();
            }
        });
    }
}

