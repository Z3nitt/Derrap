package Vista;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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

import Controlador.Conector_BBDD;
import package_main.Background;

public class VtnCrearNuevoRegistro extends JFrame implements ActionListener{
	Conector_BBDD conexion = new Conector_BBDD();
	Background fondoPantalla = new Background();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo, lblApellidos, lblDNI, lblPassword, lblNombre, lblTelefono, lblKilometros, lblAnio, lblDniCliente;
	private JTextField txtNombre, txtApellidos, txtDNI, txtPassword, txtTelefono;
	private JButton btnCrear;
	String grupo;
	private JTextField txtKilometros;
	private JTextField txtAnio;
	private JTextField txtDniCliente;
	
	public VtnCrearNuevoRegistro(String grupo) {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1083, 626);
		setTitle(" Nuevo registro | Derrap");
		setIconImage(new ImageIcon(getClass().getResource("/package_assets/icon.png")).getImage());
		setResizable(false);
		setSize(746, 619);
        setLocationRelativeTo(null);
        
        this.grupo = grupo;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		fondoPantalla.setLayout(null);
		setContentPane(fondoPantalla);
		
		lblTitulo = new JLabel("CREAR NUEVO REGISTRO");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTitulo.setBounds(39, 47, 332, 29);
		fondoPantalla.add(lblTitulo);

		txtNombre = new JTextField();
		txtNombre.setBounds(137, 197, 207, 29);
		fondoPantalla.add(txtNombre);
		txtNombre.setColumns(10);

		txtApellidos = new JTextField();
		txtApellidos.setBounds(137, 260, 207, 29);
		fondoPantalla.add(txtApellidos);
		txtApellidos.setColumns(10);

		txtDNI = new JTextField();
		txtDNI.setBounds(137, 137, 207, 29);
		fondoPantalla.add(txtDNI);
		txtDNI.setColumns(10);

		txtPassword = new JTextField();
		txtPassword.setBounds(137, 326, 207, 29);
		fondoPantalla.add(txtPassword);
		txtPassword.setColumns(10);

		lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblApellidos.setBounds(24, 266, 66, 15);
		fondoPantalla.add(lblApellidos);

		lblDNI = new JLabel("DNI:");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDNI.setBounds(24, 143, 99, 14);
		fondoPantalla.add(lblDNI);

		lblPassword = new JLabel("Contraseña:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setBounds(24, 332, 99, 14);
		fondoPantalla.add(lblPassword);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNombre.setBounds(24, 203, 86, 14);
		fondoPantalla.add(lblNombre);


		btnCrear = new JButton("CREAR");
		btnCrear.addActionListener(this);
		btnCrear.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCrear.setBounds(488, 481, 164, 70);
		fondoPantalla.add(btnCrear);
		
		lblTelefono = new JLabel("Telefono: ");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTelefono.setBounds(383, 144, 99, 13);
		fondoPantalla.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(492, 137, 207, 29);
		fondoPantalla.add(txtTelefono);
		
		txtKilometros = new JTextField();
		txtKilometros.setBounds(492, 197, 207, 29);
		fondoPantalla.add(txtKilometros);
		txtKilometros.setColumns(10);
		
		txtAnio = new JTextField();
		txtAnio.setColumns(10);
		txtAnio.setBounds(488, 260, 207, 29);
		fondoPantalla.add(txtAnio);
		
		txtDniCliente = new JTextField();
		txtDniCliente.setColumns(10);
		txtDniCliente.setBounds(488, 326, 207, 29);
		fondoPantalla.add(txtDniCliente);
		
		lblKilometros = new JLabel("Kilometros: ");
		lblKilometros.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblKilometros.setBounds(383, 204, 101, 13);
		fondoPantalla.add(lblKilometros);
		
		lblAnio = new JLabel("Año: ");
		lblAnio.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAnio.setBounds(383, 268, 99, 13);
		fondoPantalla.add(lblAnio);
		
		lblDniCliente = new JLabel("DNI cliente:");
		lblDniCliente.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDniCliente.setBounds(383, 333, 99, 13);
		fondoPantalla.add(lblDniCliente);

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
		case "clientes":
			lblTitulo.setText("CREAR NUEVO CLIENTE");
			lblTelefono.setVisible(true);
			txtTelefono.setVisible(true);
			break;
		case "mecanicos":
			lblTitulo.setText("CREAR NUEVO MECANICO");
			lblPassword.setVisible(true);
			txtPassword.setVisible(true);
			break;
		case "vehiculos":
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
		}
	}

	public void crearNuevoRegistro(String grupo){
		
		String dniRegistro = txtDNI.getText();
		String patronDNI = "[0-9]{8}[A-Z a-z]";
		String password = txtPassword.getText();
		String nombreRegistro = txtNombre.getText();
		String apellidosRegistro = txtApellidos.getText();
		Pattern patternDni = Pattern.compile(patronDNI);
		Matcher matcherDni = patternDni.matcher(dniRegistro);
		
		String telefonoRegistro = txtTelefono.getText();
		String kilometros = txtKilometros.getText();
		String anio = txtAnio.getText();
		String dniCliente = txtDniCliente.getText();
		
		conexion.conectar();
		switch (grupo) {
			case "mecanicos":
				if(matcherDni.matches()) {
					String sql = "INSERT INTO usuario (DNI, contrasenia, nombre, apellidos, rol) VALUES ('"+dniRegistro+"', '"+password+"', '"+nombreRegistro+"', '"+apellidosRegistro+"', 'Mecanico')";
					try {
						int filasAfectadas = conexion.ejecutarInsertDeleteUpdate(sql);
					
						if(filasAfectadas > 0) {
							 JOptionPane.showMessageDialog(this, "Registro de mecanico exitoso", "Información", JOptionPane.INFORMATION_MESSAGE);
							 txtApellidos.setText("");
							 txtNombre.setText("");
							 txtPassword.setText("");
							 txtDNI.setText("");
						}
						else {
							 JOptionPane.showMessageDialog(this, "Error al crear el mecanico", "Error", JOptionPane.ERROR_MESSAGE);
						}
					
					 } catch (SQLException e) {
						 JOptionPane.showMessageDialog(this, "Error al crear el mecanico", "Error", JOptionPane.ERROR_MESSAGE);
		                 e.printStackTrace();
					 }

				 }
				 else {
			 			JOptionPane.showMessageDialog(this, "El DNI no es correcto, tiene que ser el siguiente formato: 00000000X","Error de autenticación", JOptionPane.WARNING_MESSAGE);
		 		 }
			
			break;
			case "clientes":
				String sql = "INSERT INTO cliente (DNI, nombre, apellidos, telefono) VALUES ('"+dniRegistro+"', '"+nombreRegistro+"', '"+apellidosRegistro+"', '"+telefonoRegistro+"')";

				try {
					int filasAfectadas = conexion.ejecutarInsertDeleteUpdate(sql);
				
					if(filasAfectadas > 0) {
						 JOptionPane.showMessageDialog(this, "Registro de cliente exitoso", "Información", JOptionPane.INFORMATION_MESSAGE);
						 txtDNI.setText("");
						 txtNombre.setText("");
						 txtApellidos.setText("");
						 txtTelefono.setText("");
					}
					else {
						 JOptionPane.showMessageDialog(this, "Error al crear el cliente", "Error", JOptionPane.ERROR_MESSAGE);
					}
				
				 } catch (SQLException e) {
					 JOptionPane.showMessageDialog(this, "Error al crear el cliente", "Error", JOptionPane.ERROR_MESSAGE);
	                 e.printStackTrace();
				 }
				
				break;
			case "vehiculos":
				//Utilizo los otros campos de mecanico y cliente para vehiculo (dni,nombre,etc)
				String sqlVehiculos = "INSERT INTO vehiculo (matricula, marca, modelo, color, combustible, kilometros, anio, Cliente_DNI) VALUES"
						+ " ('"+dniRegistro+"', '"+nombreRegistro+"', '"+apellidosRegistro+"', '"+password+"', '"+telefonoRegistro+"', '"+kilometros+"', '"+anio+"', '"+dniCliente+"')";

				try {
					int filasAfectadas = conexion.ejecutarInsertDeleteUpdate(sqlVehiculos);
				
					if(filasAfectadas > 0) {
						 JOptionPane.showMessageDialog(this, "Registro de vehiculo exitoso", "Información", JOptionPane.INFORMATION_MESSAGE);
						 txtDNI.setText("");
						 txtNombre.setText("");
						 txtApellidos.setText("");
						 txtPassword.setText("");
						 txtTelefono.setText("");
						 txtKilometros.setText("");
						 txtAnio.setText("");
						 txtDniCliente.setText("");
					}
					else {
						 JOptionPane.showMessageDialog(this, "Error al crear el vehiculo", "Error", JOptionPane.ERROR_MESSAGE);
					}
				
				 } catch (SQLException e) {
					 JOptionPane.showMessageDialog(this, "Error al crear el vehiculo", "Error", JOptionPane.ERROR_MESSAGE);
	                 e.printStackTrace();
				 }
				break;
		}
		
	 }
	 

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCrear) {
			crearNuevoRegistro(grupo);
		}
	}
}