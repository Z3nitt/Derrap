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
	private JLabel lblTitulo, lblApellidos, lblDNI, lblPassword, lblNombre, lblTelefono;
	private JTextField txtNombre, txtApellidos, txtDNI, txtPassword, txtTelefono;
	private JButton btnCrear;
	String grupo;
	
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
		txtNombre.setBounds(164, 197, 207, 29);
		fondoPantalla.add(txtNombre);
		txtNombre.setColumns(10);

		txtApellidos = new JTextField();
		txtApellidos.setBounds(164, 260, 207, 29);
		fondoPantalla.add(txtApellidos);
		txtApellidos.setColumns(10);

		txtDNI = new JTextField();
		txtDNI.setBounds(164, 137, 207, 29);
		fondoPantalla.add(txtDNI);
		txtDNI.setColumns(10);

		txtPassword = new JTextField();
		txtPassword.setBounds(164, 326, 207, 29);
		fondoPantalla.add(txtPassword);
		txtPassword.setColumns(10);

		lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblApellidos.setBounds(51, 266, 66, 15);
		fondoPantalla.add(lblApellidos);

		lblDNI = new JLabel("DNI:");
		lblDNI.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDNI.setBounds(51, 143, 99, 14);
		fondoPantalla.add(lblDNI);

		lblPassword = new JLabel("Contraseña:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPassword.setBounds(51, 332, 99, 14);
		fondoPantalla.add(lblPassword);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNombre.setBounds(51, 203, 86, 14);
		fondoPantalla.add(lblNombre);


		btnCrear = new JButton("CREAR");
		btnCrear.addActionListener(this);
		btnCrear.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCrear.setBounds(488, 481, 164, 70);
		fondoPantalla.add(btnCrear);
		
		lblTelefono = new JLabel("Telefono: ");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTelefono.setBounds(416, 144, 66, 13);
		fondoPantalla.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(492, 137, 207, 29);
		fondoPantalla.add(txtTelefono);

		actualizarVisibilidad(grupo);
	}
	
	private void actualizarVisibilidad(String grupo) {
		lblDNI.setVisible(false);
		lblNombre.setVisible(false);
		lblApellidos.setVisible(false);
		lblPassword.setVisible(false);
		lblTelefono.setVisible(false);
		txtDNI.setVisible(false);
		txtNombre.setVisible(false);
		txtApellidos.setVisible(false);
		txtPassword.setVisible(false);
		txtTelefono.setVisible(false);
		
		switch (grupo) {
		case "clientes":
			lblTitulo.setText("CREAR NUEVO CLIENTE");
			lblDNI.setVisible(true);
			lblNombre.setVisible(true);
			lblApellidos.setVisible(true);
			lblPassword.setVisible(false);
			lblTelefono.setVisible(true);
			txtDNI.setVisible(true);
			txtNombre.setVisible(true);
			txtApellidos.setVisible(true);
			txtPassword.setVisible(false);
			txtTelefono.setVisible(true);
			break;
		case "mecanicos":
			lblTitulo.setText("CREAR NUEVO MECANICO");
			lblDNI.setVisible(true);
			lblNombre.setVisible(true);
			lblApellidos.setVisible(true);
			lblPassword.setVisible(true);
			lblTelefono.setVisible(false);
			txtDNI.setVisible(true);
			txtNombre.setVisible(true);
			txtApellidos.setVisible(true);
			txtPassword.setVisible(true);
			txtTelefono.setVisible(false);
			break;
		case "vehiculos":
			lblTitulo.setText("CREAR NUEVO VEHICULO");
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