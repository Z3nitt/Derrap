package Vista;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.Conector_BBDD;
import Controlador.ControladorRegistros;
import package_main.Background;
import java.awt.Color;

public class VtnCrearNuevoRegistro extends JFrame implements ActionListener, KeyListener {
	Conector_BBDD conexion = new Conector_BBDD();
	Background fondoPantalla = new Background();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo, lblApellidos, lblDNI, lblPassword, lblNombre, lblTelefono, lblKilometros, lblAnio,
			lblDniCliente;
	private JTextField txtNombre, txtApellidos, txtDNI, txtPassword, txtTelefono;
	private JButton btnCrear;
	private String grupo;
	private JTextField txtKilometros;
	private JTextField txtAnio;
	private JTextField txtDniCliente;
	private DefaultTableModel modelTabla;
	private JPanel panel;

	public VtnCrearNuevoRegistro(String grupo, DefaultTableModel modelTabla) {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1083, 626);
		setTitle(" Nuevo registro | Derrap");
		setIconImage(new ImageIcon(getClass().getResource("/package_assets/icon.png")).getImage());
		setResizable(false);
		setSize(746, 619);
		setLocationRelativeTo(null);

		this.grupo = grupo;
		this.modelTabla = modelTabla;

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		fondoPantalla.setBackground(new Color(102, 153, 204));
		fondoPantalla.setLayout(null);
		setContentPane(fondoPantalla);

		txtNombre = new JTextField();
		txtNombre.setBounds(137, 197, 207, 29);
		fondoPantalla.add(txtNombre);
		txtNombre.setColumns(10);
		txtNombre.addKeyListener(this);

		txtApellidos = new JTextField();
		txtApellidos.setBounds(137, 260, 207, 29);
		fondoPantalla.add(txtApellidos);
		txtApellidos.setColumns(10);
		txtApellidos.addKeyListener(this);

		txtDNI = new JTextField();
		txtDNI.setBounds(137, 137, 207, 29);
		fondoPantalla.add(txtDNI);
		txtDNI.setColumns(10);
		txtDNI.addKeyListener(this);

		txtPassword = new JTextField();
		txtPassword.setBounds(137, 326, 207, 29);
		fondoPantalla.add(txtPassword);
		txtPassword.setColumns(10);
		txtPassword.addKeyListener(this);

		lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setForeground(new Color(255, 255, 255));
		lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblApellidos.setBounds(24, 266, 66, 15);
		fondoPantalla.add(lblApellidos);

		lblDNI = new JLabel("DNI:");
		lblDNI.setForeground(new Color(255, 255, 255));
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDNI.setBounds(24, 143, 99, 14);
		fondoPantalla.add(lblDNI);

		lblPassword = new JLabel("Contraseña:");
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setBounds(24, 332, 99, 14);
		fondoPantalla.add(lblPassword);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setForeground(new Color(255, 255, 255));
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNombre.setBounds(24, 203, 86, 14);
		fondoPantalla.add(lblNombre);

		lblTelefono = new JLabel("Telefono: ");
		lblTelefono.setForeground(new Color(255, 255, 255));
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTelefono.setBounds(383, 144, 99, 13);
		fondoPantalla.add(lblTelefono);

		txtAnio = new JTextField();
		txtAnio.setColumns(10);
		txtAnio.setBounds(488, 260, 207, 29);
		fondoPantalla.add(txtAnio);

		txtDniCliente = new JTextField();
		txtDniCliente.setColumns(10);
		txtDniCliente.setBounds(488, 326, 207, 29);
		txtDniCliente.addKeyListener(this);
		fondoPantalla.add(txtDniCliente);

		lblKilometros = new JLabel("Kilometros: ");
		lblKilometros.setForeground(new Color(255, 255, 255));
		lblKilometros.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblKilometros.setBounds(383, 204, 101, 13);

		fondoPantalla.add(lblKilometros);

		lblAnio = new JLabel("Año: ");
		lblAnio.setForeground(new Color(255, 255, 255));
		lblAnio.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAnio.setBounds(383, 268, 99, 13);
		fondoPantalla.add(lblAnio);

		lblDniCliente = new JLabel("DNI cliente:");
		lblDniCliente.setForeground(new Color(255, 255, 255));
		lblDniCliente.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDniCliente.setBounds(383, 333, 99, 13);
		fondoPantalla.add(lblDniCliente);

		panel = new JPanel();
		panel.setBackground(new Color(102, 153, 204));
		panel.setBounds(0, 0, 730, 580);
		fondoPantalla.add(panel);
		panel.setLayout(null);

		lblTitulo = new JLabel("CREAR NUEVO REGISTRO");
		lblTitulo.setBounds(212, 49, 284, 27);
		panel.add(lblTitulo);
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));

		btnCrear = new JButton("CREAR");
		btnCrear.setBounds(223, 458, 284, 42);
		btnCrear.setFocusable(false);
		btnCrear.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnCrear.setBackground(Color.BLACK);
				btnCrear.setForeground(Color.WHITE);
			}

			public void mouseExited(MouseEvent e) {
				btnCrear.setBackground(Color.WHITE);
				btnCrear.setForeground(new Color(102, 153, 204));
				;
			}
		});
		panel.add(btnCrear);
		btnCrear.setForeground(new Color(102, 153, 204));
		btnCrear.setBackground(new Color(255, 255, 255));
		btnCrear.addActionListener(this);
		btnCrear.setFont(new Font("Tahoma", Font.BOLD, 14));

		txtKilometros = new JTextField();
		txtKilometros.setBounds(490, 197, 207, 29);
		panel.add(txtKilometros);
		txtKilometros.addKeyListener(this);
		txtKilometros.setColumns(10);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(490, 138, 207, 29);
		panel.add(txtTelefono);
		txtTelefono.addKeyListener(this);
		txtTelefono.setColumns(10);

		actualizarVisibilidad(grupo);
	}

	private void actualizarVisibilidad(String grupo) {
		lblPassword.setVisible(false);
		txtPassword.setVisible(false);
		lblTelefono.setVisible(false);
		txtTelefono.setVisible(false);
		lblKilometros.setVisible(false);
		txtKilometros.setVisible(false);
		lblAnio.setVisible(false);
		txtAnio.setVisible(false);
		lblDniCliente.setVisible(false);
		txtDniCliente.setVisible(false);

		switch (grupo) {
		case "cliente":
			lblTitulo.setText("CREAR NUEVO CLIENTE");
			lblTelefono.setVisible(true);
			txtTelefono.setVisible(true);
			break;
		case "mecanico":
			lblTitulo.setText("CREAR NUEVO MECANICO");
			lblPassword.setVisible(true);
			txtPassword.setVisible(true);
			break;
		case "vehiculo":
			lblTitulo.setText("CREAR NUEVO VEHICULO");
			lblDNI.setText("Matricula:");
			lblNombre.setText("Marca:");
			lblApellidos.setText("Modelo:");
			lblPassword.setText("Color:");
			lblTelefono.setText("Combustible:");
			lblPassword.setVisible(true);
			txtPassword.setVisible(true);
			lblTelefono.setVisible(true);
			txtTelefono.setVisible(true);
			lblKilometros.setVisible(true);
			txtKilometros.setVisible(true);
			lblAnio.setVisible(true);
			txtAnio.setVisible(true);
			lblDniCliente.setVisible(true);
			txtDniCliente.setVisible(true);
			break;
		case "orden":
			lblTitulo.setText("CREAR NUEVA ORDEN");
			lblDNI.setVisible(true);
			lblDNI.setText("ID Orden:");
			lblNombre.setVisible(true);
			lblNombre.setText("Cliente:");
			lblApellidos.setVisible(true);
			lblApellidos.setText("Matrícula:");
			lblPassword.setVisible(false);
			lblPassword.setText("Modelo:");
			lblTelefono.setVisible(false);
			lblTelefono.setText("Matricula:");
			txtDNI.setVisible(true);
			txtNombre.setVisible(true);
			txtApellidos.setVisible(true);
			txtPassword.setVisible(false);
			txtTelefono.setVisible(false);
			break;
		}
	}

	public void crearNuevoRegistro(String grupo) {

		String dniRegistro = txtDNI.getText();
		String patronDNI = "[0-9]{8}[A-Z a-z]";
		String password = txtPassword.getText();
		String nombreRegistro = txtNombre.getText();
		String apellidosRegistro = txtApellidos.getText();
		Pattern patternDni = Pattern.compile(patronDNI);
		Matcher matcherDni = patternDni.matcher(dniRegistro);

		String telefonoRegistro = txtTelefono.getText();
		String kilometros = txtKilometros.getText();
		String year = txtAnio.getText();
		String dniCliente = txtDniCliente.getText();

		conexion.conectar();
		String sql = "";
		// Segun el grupo que sea, cambia el insert
		switch (grupo) {
		case "mecanico":
			if (matcherDni.matches()) {
				sql = "INSERT INTO usuario (DNI, password, nombre, apellidos, rol) VALUES " + "('" + dniRegistro
						+ "', '" + password + "', '" + nombreRegistro + "', '" + apellidosRegistro + "', 'Mecanico')";
			}
			break;
		case "cliente":
			sql = "INSERT INTO cliente (DNI, nombre, apellidos, telefono) VALUES ('" + dniRegistro + "', '"
					+ nombreRegistro + "', '" + apellidosRegistro + "', '" + telefonoRegistro + "')";

			break;
		case "vehiculo":

			if (dniCliente.isBlank()) {
				dniCliente = "NULL";
			} else {
				dniCliente = "'" + dniCliente + "'";
			}

			// Utilizo los otros campos de mecanico y cliente para vehiculo (dni,nombre,etc)
			sql = "INSERT INTO vehiculo (matricula, marca, modelo, color, combustible, kilometros, year, dni_cliente) VALUES"
					+ " ('" + dniRegistro + "', '" + nombreRegistro + "', '" + apellidosRegistro + "', '" + password
					+ "', '" + telefonoRegistro + "', '" + kilometros + "', '" + year + "', " + dniCliente + ")";
			break;
		case "orden":
			sql = "INSERT INTO orden (id_orden, dni_cliente, matricula_vehiculo) VALUES ('" + dniRegistro + "', '"
					+ nombreRegistro + "', '" + apellidosRegistro + "')";
		
			break;
		}
		// Controla la creacion del registro
		try {
			int filasAfectadas = conexion.ejecutarInsertDeleteUpdate(sql);

			// Si se creó correctamente el registro, muestra un messageDialog, actualiza la
			// tabla correspondiente y cierra la ventana
			if (filasAfectadas > 0) {
				ControladorRegistros.actualizarTablas(grupo, modelTabla);
				JOptionPane.showMessageDialog(this, "Registro exitoso", "Información", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Error al crear el registro", "Error", JOptionPane.ERROR_MESSAGE);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Error en la base de datos al crear el registro", "Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCrear) {
			crearNuevoRegistro(grupo);
		}
	}

	// Eventos de teclado
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Si le da al enter, presiona el boton de aceptar
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			btnCrear.doClick();
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {// Si le da al escape, pide confirmacion para volver a la
															// ventana anterior y la cierra
			int confirmacion = JOptionPane.showConfirmDialog(null, "¿Volver atrás?", "Confirmar volver",
					JOptionPane.YES_NO_OPTION);

			if (confirmacion == JOptionPane.YES_OPTION) {
				dispose();
			}
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}