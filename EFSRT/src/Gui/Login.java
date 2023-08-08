package Gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Login extends JFrame {

	private JPanel contentPane;
	private JLabel lblFondo;
	private JLabel lblIcono;
	private JLabel lbUsuario;
	private JTextField txtUser;
	private JLabel lblContraseña;
	private JPasswordField txtContraseña;
	private JButton btnLogin;
	
	// Configuración de la base de datos
	private static final String JDBC_URL = "jdbc:mysql://localhost/bdpanaderia?useSSL=false&useTimezone=true&serverTimezone=UTC";
	private static final String JDBC_USERNAME = "root";
	private static final String JDBC_PASSWORD = "MySQL123$";
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 591);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnLogin = new JButton("LOGIN");
		btnLogin.setForeground(Color.CYAN);
		btnLogin.setBackground(Color.BLACK);
		btnLogin.setFont(new Font("Leelawadee", Font.BOLD, 15));
		btnLogin.setBounds(181, 481, 108, 23);
		contentPane.add(btnLogin);
		
		txtContraseña = new JPasswordField();
		txtContraseña.setBackground(Color.WHITE);
		txtContraseña.setFont(new Font("Leelawadee", Font.BOLD, 15));
		txtContraseña.setColumns(10);
		txtContraseña.setBounds(108, 396, 260, 32);
		contentPane.add(txtContraseña);
		
		lblContraseña = new JLabel("Contraseña:");
		lblContraseña.setForeground(Color.WHITE);
		lblContraseña.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblContraseña.setBounds(108, 356, 129, 25);
		contentPane.add(lblContraseña);
		
		txtUser = new JTextField();
		txtUser.setFont(new Font("Leelawadee", Font.BOLD, 15));
		txtUser.setBounds(108, 272, 260, 32);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		lbUsuario = new JLabel("Usuario:");
		lbUsuario.setForeground(Color.WHITE);
		lbUsuario.setFont(new Font("Tahoma", Font.BOLD, 20));
		lbUsuario.setBounds(108, 225, 93, 25);
		contentPane.add(lbUsuario);
		
		lblIcono = new JLabel("");
		lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcono.setIcon(new ImageIcon("IMG\\logoLogin-removebg-preview__3_-removebg-preview.png"));
		lblIcono.setBounds(120, 11, 213, 203);
		contentPane.add(lblIcono);
		
		lblFondo = new JLabel("");
		lblFondo.setHorizontalAlignment(SwingConstants.CENTER);
		lblFondo.setLabelFor(lblFondo);
		lblFondo.setIcon(new ImageIcon("IMG\\Fondo2 (2).jpg"));
		lblFondo.setBounds(0, 0, 473, 552);
		contentPane.add(lblFondo);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 379, 432, 162);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		// Agregar el ActionListener al botón de login
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = txtUser.getText();
				String password = new String(txtContraseña.getPassword());
				
				if (login(username, password)) {
				    // Si el login es exitoso, redirigir a la clase Principal
				    Principal principal = new Principal();
				    principal.setVisible(true);
				    dispose(); // Cierra la ventana de inicio de sesión
				} else {
				    JOptionPane.showMessageDialog(Login.this, "Usuario o contraseña incorrectos");
				}

			}
		});
	}
	
	private boolean login(String username, String password) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			// Establecer conexión con la base de datos
			connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
			
			// Crear consulta SQL
			String query = "SELECT * FROM admin WHERE usuario = ? AND contraseña = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			
			// Ejecutar consulta
			resultSet = statement.executeQuery();
			
			// Verificar si el usuario y contraseña coinciden
			return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			// Cerrar recursos
			try {
				if (resultSet != null) resultSet.close();
				if (statement != null) statement.close();
				if (connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
