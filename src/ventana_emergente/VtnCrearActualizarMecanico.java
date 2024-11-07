package ventana_emergente;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import package_main.Background;
import package_main.Conector_BBDD;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VtnCrearActualizarMecanico extends JFrame implements ActionListener{
	Conector_BBDD conexion = new Conector_BBDD();
	Background fondoPantalla = new Background();
	Connection cn = null;
    Statement stm = null;
    ResultSet rsetresultado = null;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtnombreMecanico;
	private JTextField txtapellidosMecanico;
	private JTextField txtusuarioMecanico;
	private JTextField txtpasswordMecanico;
	private JButton btnInsert, btnUpdate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VtnCrearActualizarMecanico frame = new VtnCrearActualizarMecanico();
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
	public VtnCrearActualizarMecanico() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1083, 626);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Gestionar Mecánicos | Derrap");
        setIconImage(new ImageIcon(getClass().getResource("/package_assets/icon.png")).getImage());
        setResizable(false);
        fondoPantalla.setLayout(null);
		setContentPane(fondoPantalla);
		
		JLabel lblNewLabel = new JLabel("DATOS MECÁNICO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(286, 82, 179, 14);
		fondoPantalla.add(lblNewLabel);
		
		txtnombreMecanico = new JTextField();
		txtnombreMecanico.setBounds(51, 197, 207, 29);
		fondoPantalla.add(txtnombreMecanico);
		txtnombreMecanico.setColumns(10);
		
		txtapellidosMecanico = new JTextField();
		txtapellidosMecanico.setBounds(466, 197, 207, 29);
		fondoPantalla.add(txtapellidosMecanico);
		txtapellidosMecanico.setColumns(10);
		
		txtusuarioMecanico = new JTextField();
		txtusuarioMecanico.setBounds(51, 389, 207, 29);
		fondoPantalla.add(txtusuarioMecanico);
		txtusuarioMecanico.setColumns(10);
		
		txtpasswordMecanico = new JTextField();
		txtpasswordMecanico.setBounds(466, 389, 207, 29);
		fondoPantalla.add(txtpasswordMecanico);
		txtpasswordMecanico.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Apellidos:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(536, 143, 66, 15);
		fondoPantalla.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("DNI:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(124, 353, 99, 14);
		fondoPantalla.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Contraseña:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(536, 353, 99, 14);
		fondoPantalla.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Nombre:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(124, 143, 86, 14);
		fondoPantalla.add(lblNewLabel_4);
		
		btnUpdate = new JButton("ACTUALIZAR");
		btnUpdate.addActionListener(this);
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnUpdate.setBounds(76, 481, 164, 70);
		fondoPantalla.add(btnUpdate);
		
		btnInsert = new JButton("AÑADIR");
		btnInsert.addActionListener(this);
		btnInsert.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnInsert.setBounds(488, 481, 164, 70);
		fondoPantalla.add(btnInsert);
		
		//Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        //int height = pantalla.height;
        //int width = pantalla.width;

        setSize(746, 619);
        setLocationRelativeTo(null);
	}
	
	
	
	 public void registroMecanico(){
		String dniMecanico = txtusuarioMecanico.getText();
		String patronDNI = "[0-9]{8}[A-Z a-z]";
		String validarPass = txtpasswordMecanico.getText();
		String nombreMecanico = txtnombreMecanico.getText();
		String apellidosMecanico = txtapellidosMecanico.getText();
		
		Pattern patron_dni_mecanico = Pattern.compile(patronDNI);
		Matcher mat_dni_mecanico = patron_dni_mecanico.matcher(dniMecanico);
		 
		 if(mat_dni_mecanico.matches()) {
			 if(validarPass.isEmpty()) {
				 JOptionPane.showMessageDialog(this, "Introduzca una contraseña","Error de registro", JOptionPane.WARNING_MESSAGE);
			 }
			 else {
				 cn = conexion.conexion_correcta();
				 if(cn!=null) {
					 String sql = "INSERT INTO usuario (DNI, contrasenia, nombre, apellidos, rol) VALUES (?, ?, ?, ?, 'Mecanico')";
					 try(PreparedStatement stmt = cn.prepareStatement(sql)){
						 stmt.setString(1, dniMecanico);
						 stmt.setString(2, validarPass);
						 stmt.setString(3, nombreMecanico);
						 stmt.setString(4, apellidosMecanico);
						 
						 int filasAfectadas = stmt.executeUpdate();
						 
						 if(filasAfectadas > 0) {
							 JOptionPane.showMessageDialog(this, "Registro exitoso", "Información", JOptionPane.INFORMATION_MESSAGE);
							 txtapellidosMecanico.setText("");
							 txtnombreMecanico.setText("");
							 txtpasswordMecanico.setText("");
							 txtusuarioMecanico.setText("");
						 } 
						 
						 else {
							 JOptionPane.showMessageDialog(this, "Error al insertar el registro", "Error", JOptionPane.ERROR_MESSAGE);
						 }
					 } catch (SQLException e) {
						 JOptionPane.showMessageDialog(this, "Error al registrar el mecánico", "Error", JOptionPane.ERROR_MESSAGE);
		                 e.printStackTrace();
					 }
				 } 
				 
				 else {
					 JOptionPane.showMessageDialog(this, "No se pudo establecer la conexión", "Error de conexión", JOptionPane.ERROR_MESSAGE);
				 }
				 
			 }
		 }
		 else {
	 			JOptionPane.showMessageDialog(this, "El usuario introducido no es válido. Solo se permite usar un DNI como nombre de usuario","Error de autenticación", JOptionPane.WARNING_MESSAGE);
	 		 }
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnInsert) {
			registroMecanico();
		}
		
	}
}
