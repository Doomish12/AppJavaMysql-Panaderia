package Gui;

import entidades.Distrito;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Dao.DistritoDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DistritoGUI extends JFrame {
    private DistritoDAO distritoDAO;
    private JButton btnRegistrar;
    private JButton btnEditar;
    private JButton btnEliminar;
    private JButton btnLimpiar;
    private JTable tablaDistritos;
    private DefaultTableModel modeloTabla;
    private JLabel lblCodigo;
    private JTextField txtNombre;
    private JLabel lblNombre;
    private JTextField txtCodigo;
    private JLabel lblNewLabel;

    public DistritoGUI() {
    	setResizable(false);
        distritoDAO = new DistritoDAO(); // Reemplaza "DistritoDAO" con el nombre de tu clase DAO para la entidad Distrito

        setTitle("Gestión de Distritos");
        setSize(516, 421);
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
        panel.setBackground(SystemColor.activeCaption);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(22, 125, 96, 25);
        btnRegistrar.setForeground(new Color(224, 255, 255));
        btnRegistrar.setBackground(new Color(0, 0, 0));
        btnRegistrar.setFont(new Font("Leelawadee", Font.BOLD, 13));
        btnEditar = new JButton("Editar");
        btnEditar.setBounds(139, 125, 96, 25);
        btnEditar.setForeground(new Color(0, 255, 0));
        btnEditar.setBackground(new Color(0, 0, 0));
        btnEditar.setFont(new Font("Leelawadee", Font.BOLD, 13));
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(258, 125, 96, 25);
        btnEliminar.setForeground(new Color(255, 0, 0));
        btnEliminar.setBackground(new Color(0, 0, 0));
        btnEliminar.setFont(new Font("Leelawadee", Font.BOLD, 13));
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(364, 125, 96, 25);
        btnLimpiar.setBackground(new Color(0, 0, 0));
        btnLimpiar.setForeground(new Color(255, 250, 250));
        btnLimpiar.setFont(new Font("Leelawadee", Font.BOLD, 13));

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarDistrito();
            }
        });

        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarDistrito();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarDistrito();
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
        lblCodigo.setForeground(SystemColor.menuText);
        lblCodigo.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblCodigo.setBounds(22, 39, 83, 21);
        panel.add(lblCodigo);
        
        txtNombre = new JTextField();
        txtNombre.setFont(new Font("Leelawadee", Font.BOLD, 12));
        txtNombre.setBackground(new Color(255, 250, 240));
        txtNombre.setBounds(115, 81, 147, 21);
        panel.add(txtNombre);
        
        lblNombre = new JLabel("Nombre:");
        lblNombre.setForeground(SystemColor.menuText);
        lblNombre.setFont(new Font("Tahoma", Font.BOLD, 17));
        lblNombre.setBounds(22, 80, 83, 21);
        panel.add(lblNombre);
        
        txtCodigo = new JTextField();
        txtCodigo.setFont(new Font("Leelawadee", Font.BOLD, 12));
        txtCodigo.setBackground(new Color(255, 250, 240));
        txtCodigo.setBounds(115, 40, 147, 21);
        panel.add(txtCodigo);
        
        lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("IMG\\pan (1).jpg"));
        lblNewLabel.setBounds(-31, 0, 531, 416);
        panel.add(lblNewLabel);

        return panel;
    }

    private JScrollPane crearPanelTabla() {
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Nombre");

        tablaDistritos = new JTable(modeloTabla);
        tablaDistritos.setForeground(new Color(0, 0, 0));
        tablaDistritos.setBackground(new Color(255, 255, 255));
        tablaDistritos.setFont(new Font("Leelawadee", Font.BOLD, 13));
        tablaDistritos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tablaDistritos);
        scrollPane.setEnabled(false);
        scrollPane.setPreferredSize(new Dimension(500, 200));

        return scrollPane;
    }

    private void cargarDatosTabla() {
        List<Distrito> distritos = distritoDAO.obtenerDistritos();

        modeloTabla.setRowCount(0); // Limpiar filas existentes

        for (Distrito distrito : distritos) {
            Object[] fila = {distrito.getCodDis(), distrito.getNomDis()};
            modeloTabla.addRow(fila);
        }
    }

    private void registrarDistrito() {
    	try {
    	     String codigo = txtCodigo.getText();
    	        String nombre = txtNombre.getText();

    	        Distrito distrito = new Distrito();
    	        distrito.setCodDis(Integer.parseInt(codigo));
    	        distrito.setNomDis(nombre);

    	        distritoDAO.insertarDistrito(distrito);
    	        JOptionPane.showMessageDialog(null, "Distrito registrado correctamente.");
    	        limpiarCampos();
    	        cargarDatosTabla();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "No se puede Registrar Verique los campos", "Error", JOptionPane.ERROR_MESSAGE);
		}
   
       
    }

    private void editarDistrito() {
        int filaSeleccionada = tablaDistritos.getSelectedRow();

        if (filaSeleccionada != -1) {
            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();

            int codigoDistrito = (int) tablaDistritos.getValueAt(filaSeleccionada, 0);

            Distrito distrito = new Distrito();
            distrito.setCodDis(codigoDistrito);
            distrito.setNomDis(nombre);

            distritoDAO.actualizarDistrito(distrito);
            
            JOptionPane.showMessageDialog(null, "Distrito editado correctamente.");
            
            limpiarCampos();
            cargarDatosTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un distrito de la tabla para editar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarDistrito() {
        int filaSeleccionada = tablaDistritos.getSelectedRow();

        if (filaSeleccionada != -1) {
            int codigo = (int) tablaDistritos.getValueAt(filaSeleccionada, 0);

            distritoDAO.eliminarDistrito(codigo);
            JOptionPane.showMessageDialog(null, "Distrito eliminado correctamente.");
            limpiarCampos();
            cargarDatosTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un distrito de la tabla para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtCodigo.setText("");
        txtNombre.setText("");
        tablaDistritos.clearSelection();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DistritoGUI();
            }
        });
    }
}
