package package_main;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class JFrameMain_Admin extends JFrame implements ActionListener {
	Conector_BBDD conexion = new Conector_BBDD();
	Background fondoPantalla = new Background();
	Connection cn = null;
    Statement stm = null;
    ResultSet rsetresultado = null;
	

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btncrearAdmin, btncrearMecanicos, btnenviarAdmin, btnenviarMecanico, btnLogout;
	private JPanel jpcrearUsuario,jpvacio1,jpvacio2,jpvacio3;
	private JTextField txtusuarioAdmin,txtpasswordAdmin, txtusuarioMecanico, txtpasswordMecanico, txtnombreAdmin, txtapellidosAdmin, txtnombreMecanico, txtapellidosMecanico;
	private JLabel lblusuarioAdmin, lblpasswordAdmin, lblusuarioMecanico, lblpasswordMecanico, lblinfoAdmin, lblinfoMecanico, lblnombreAdmin, lblapellidosAdmin, lblnombreMecanico, lblapellidosMecanico;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameMain_Admin frame = new JFrameMain_Admin();
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
	public JFrameMain_Admin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 626);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Administrador | Derrap");
        setIconImage(new ImageIcon(getClass().getResource("/package_assets/icon.png")).getImage());
        setResizable(false);
        fondoPantalla.setLayout(null);
		setContentPane(fondoPantalla);
		
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;

        setSize(width / 2, height / 2);
        setLocationRelativeTo(null);
        
        jpcrearUsuario = new JPanel();
        jpcrearUsuario.setLayout(null);
        
        
        jpvacio1 = new JPanel();
        jpvacio2 = new JPanel();
        jpvacio3 = new JPanel();
        
        
        JTabbedPane jtpmenuPrincipal = new JTabbedPane();
        jtpmenuPrincipal.setBounds(20, 30, 900, 460);
        
        jtpmenuPrincipal.add("Añadir Usuarios",jpcrearUsuario);
        jtpmenuPrincipal.add("Vacio 1",jpvacio1);
        jtpmenuPrincipal.add("Vacio 2",jpvacio2);
        jtpmenuPrincipal.add("Vacio 3",jpvacio3);
        
        getContentPane().add(jtpmenuPrincipal);
        
        //CREAR ADMINS
        btncrearAdmin = new JButton("Crear Administrador");
        btncrearAdmin.setBounds(50, 100, 150, 30);
        btncrearAdmin.addActionListener(this);
        jpcrearUsuario.add(btncrearAdmin);
        
        lblinfoAdmin = new JLabel();
        lblinfoAdmin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
        lblinfoAdmin.setHorizontalAlignment(SwingConstants.CENTER);
        lblinfoAdmin.setBounds(50, 200, 223, 30);
        lblinfoAdmin.setText("CREANDO NUEVO ADMINISTRADOR");
        jpcrearUsuario.add(lblinfoAdmin);
        lblinfoAdmin.setVisible(false);
        
        
        lblusuarioAdmin = new JLabel();
        lblusuarioAdmin.setBounds(50, 260, 150, 30);
        lblusuarioAdmin.setText("Introducir DNI: ");
        jpcrearUsuario.add(lblusuarioAdmin);
        lblusuarioAdmin.setVisible(false);
        
        txtusuarioAdmin = new JTextField();
        txtusuarioAdmin.setBounds(200, 260, 150, 30);
        jpcrearUsuario.add(txtusuarioAdmin);
        txtusuarioAdmin.setVisible(false);
        
        lblpasswordAdmin = new JLabel();
        lblpasswordAdmin.setBounds(50, 320, 150, 30);
        lblpasswordAdmin.setText("Introducir contraseña: ");
        jpcrearUsuario.add(lblpasswordAdmin);
        lblpasswordAdmin.setVisible(false);
        
        txtpasswordAdmin = new JTextField();
        txtpasswordAdmin.setBounds(200, 320, 150, 30);
        jpcrearUsuario.add(txtpasswordAdmin);
        txtpasswordAdmin.setVisible(false);
        
        lblnombreAdmin = new JLabel();
        lblnombreAdmin.setBounds(400, 260, 150, 30);
        lblnombreAdmin.setText("Introducir Nombre: ");
        jpcrearUsuario.add(lblnombreAdmin);
        lblnombreAdmin.setVisible(false);
        
        txtnombreAdmin = new JTextField();
        txtnombreAdmin.setBounds(550, 260, 150, 30);
        jpcrearUsuario.add(txtnombreAdmin);
        txtnombreAdmin.setVisible(false);
        
        lblapellidosAdmin = new JLabel();
        lblapellidosAdmin.setBounds(400, 320, 150, 30);
        lblapellidosAdmin.setText("Introducir Apellidos: ");
        jpcrearUsuario.add(lblapellidosAdmin);
        lblapellidosAdmin.setVisible(false);
        
        txtapellidosAdmin = new JTextField();
        txtapellidosAdmin.setBounds(550, 320, 150, 30);
        jpcrearUsuario.add(txtapellidosAdmin);
        txtapellidosAdmin.setVisible(false);
        
        btnenviarAdmin = new JButton("Registrar");
        btnenviarAdmin.setBounds(325, 390, 150, 30);
        btnenviarAdmin.addActionListener(this);
        jpcrearUsuario.add(btnenviarAdmin);
        btnenviarAdmin.setVisible(false);
        
        //CREAR MECANICOS
        btncrearMecanicos = new JButton("Crear Mecánico");
        btncrearMecanicos.setBounds(690, 100, 150, 30);
        btncrearMecanicos.addActionListener(this);
        jpcrearUsuario.add(btncrearMecanicos);
        
        lblinfoMecanico = new JLabel();
        lblinfoMecanico.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
        lblinfoMecanico.setHorizontalAlignment(SwingConstants.CENTER);
        lblinfoMecanico.setBounds(50, 200, 223, 30);
        lblinfoMecanico.setText("CREANDO NUEVO MECÁNICO");
        jpcrearUsuario.add(lblinfoMecanico);
        lblinfoMecanico.setVisible(false);
        
        lblusuarioMecanico = new JLabel();
        lblusuarioMecanico.setBounds(50, 260, 150, 30);
        lblusuarioMecanico.setText("Introducir DNI: ");
        jpcrearUsuario.add(lblusuarioMecanico);
        lblusuarioMecanico.setVisible(false);
        
        txtusuarioMecanico = new JTextField();
        txtusuarioMecanico.setBounds(200, 260, 150, 30);
        jpcrearUsuario.add(txtusuarioMecanico);
        txtusuarioMecanico.setVisible(false);
        
        lblpasswordMecanico = new JLabel();
        lblpasswordMecanico.setBounds(50, 320, 150, 30);
        lblpasswordMecanico.setText("Introducir contraseña: ");
        jpcrearUsuario.add(lblpasswordMecanico);
        lblpasswordMecanico.setVisible(false);
        
        txtpasswordMecanico = new JTextField();
        txtpasswordMecanico.setBounds(200, 320, 150, 30);
        jpcrearUsuario.add(txtpasswordMecanico);
        txtpasswordMecanico.setVisible(false);
        
        lblnombreMecanico = new JLabel();
        lblnombreMecanico.setBounds(400, 260, 150, 30);
        lblnombreMecanico.setText("Introducir Nombre: ");
        jpcrearUsuario.add(lblnombreMecanico);
        lblnombreMecanico.setVisible(false);
        
        txtnombreMecanico = new JTextField();
        txtnombreMecanico.setBounds(550, 260, 150, 30);
        jpcrearUsuario.add(txtnombreMecanico);
        txtnombreMecanico.setVisible(false);
        
        lblapellidosMecanico = new JLabel();
        lblapellidosMecanico.setBounds(400, 320, 150, 30);
        lblapellidosMecanico.setText("Introducir Apellidos: ");
        jpcrearUsuario.add(lblapellidosMecanico);
        lblapellidosMecanico.setVisible(false);
        
        txtapellidosMecanico = new JTextField();
        txtapellidosMecanico.setBounds(550, 320, 150, 30);
        jpcrearUsuario.add(txtapellidosMecanico);
        txtapellidosMecanico.setVisible(false);
        
        btnenviarMecanico = new JButton("Registrar");
        btnenviarMecanico.setBounds(325, 390, 150, 30);
        btnenviarMecanico.addActionListener(this);
        jpcrearUsuario.add(btnenviarMecanico);
        btnenviarMecanico.setVisible(false);
        
        btnLogout = new JButton("Cerrar Sesión");
        btnLogout.addActionListener(this);
        btnLogout.setBounds(815, 11, 119, 23);
        fondoPantalla.add(btnLogout);
        
        
        
        
        
	}
	
	 public void actionPerformed(ActionEvent e) {
	        if (e.getSource() == btncrearAdmin) {
	        	lblinfoAdmin.setVisible(true);
	        	lblusuarioAdmin.setVisible(true);
	        	txtusuarioAdmin.setVisible(true);
	        	lblpasswordAdmin.setVisible(true);
	        	txtpasswordAdmin.setVisible(true);
	        	btnenviarAdmin.setVisible(true);
	        	lblnombreAdmin.setVisible(true);
	        	txtnombreAdmin.setVisible(true);
	        	lblapellidosAdmin.setVisible(true);
	        	txtapellidosAdmin.setVisible(true);
	        	
	        	txtapellidosAdmin.setText("");
	        	txtnombreAdmin.setText("");
	        	txtpasswordAdmin.setText("");
	        	txtusuarioAdmin.setText("");
	        	txtapellidosMecanico.setText("");
	        	txtnombreMecanico.setText("");
	        	txtpasswordMecanico.setText("");
	        	txtusuarioMecanico.setText("");
	        	
	        	lblinfoMecanico.setVisible(false);
	        	lblusuarioMecanico.setVisible(false);
	        	txtusuarioMecanico.setVisible(false);
	        	lblpasswordMecanico.setVisible(false);
	        	txtpasswordMecanico.setVisible(false);
	        	btnenviarMecanico.setVisible(false);
	        	lblnombreMecanico.setVisible(false);
	        	txtnombreMecanico.setVisible(false);
	        	lblapellidosMecanico.setVisible(false);
	        	txtapellidosMecanico.setVisible(false);

	        }else if(e.getSource() == btncrearMecanicos) {
	        	lblinfoAdmin.setVisible(false);
	        	lblusuarioAdmin.setVisible(false);
	        	txtusuarioAdmin.setVisible(false);
	        	lblpasswordAdmin.setVisible(false);
	        	txtpasswordAdmin.setVisible(false);
	        	btnenviarAdmin.setVisible(false);
	        	lblnombreAdmin.setVisible(false);
	        	txtnombreAdmin.setVisible(false);
	        	lblapellidosAdmin.setVisible(false);
	        	txtapellidosAdmin.setVisible(false);
	        	
	        	txtapellidosAdmin.setText("");
	        	txtnombreAdmin.setText("");
	        	txtpasswordAdmin.setText("");
	        	txtusuarioAdmin.setText("");
	        	txtapellidosMecanico.setText("");
	        	txtnombreMecanico.setText("");
	        	txtpasswordMecanico.setText("");
	        	txtusuarioMecanico.setText("");
	        	
	        	lblinfoMecanico.setVisible(true);
	        	lblusuarioMecanico.setVisible(true);
	        	txtusuarioMecanico.setVisible(true);
	        	lblpasswordMecanico.setVisible(true);
	        	txtpasswordMecanico.setVisible(true);
	        	btnenviarMecanico.setVisible(true);
	        	lblnombreMecanico.setVisible(true);
	        	txtnombreMecanico.setVisible(true);
	        	lblapellidosMecanico.setVisible(true);
	        	txtapellidosMecanico.setVisible(true);
	        	
	        }else if(e.getSource() == btnenviarAdmin) {
	        	registroAdmin();
	        	
	        	txtapellidosAdmin.setText("");
	        	txtnombreAdmin.setText("");
	        	txtpasswordAdmin.setText("");
	        	txtusuarioAdmin.setText("");
	        	txtapellidosMecanico.setText("");
	        	txtnombreMecanico.setText("");
	        	txtpasswordMecanico.setText("");
	        	txtusuarioMecanico.setText("");
        		
        	}else if (e.getSource() == btnenviarMecanico) {
        		registroMecanico();
        		
        		txtapellidosAdmin.setText("");
	        	txtnombreAdmin.setText("");
	        	txtpasswordAdmin.setText("");
	        	txtusuarioAdmin.setText("");
	        	txtapellidosMecanico.setText("");
	        	txtnombreMecanico.setText("");
	        	txtpasswordMecanico.setText("");
	        	txtusuarioMecanico.setText("");
        	} else if(e.getSource() == btnLogout) {
        		logout();
        	}
	 }
	 
	 public void registroAdmin() {
		String dniAdmin = txtusuarioAdmin.getText();
		String patronDNI = "[0-9]{8}[A-Z a-z]";
		String validarPass = txtpasswordAdmin.getText();
		String nombreAdmin = txtnombreAdmin.getText();
		String apellidosAdmin = txtapellidosAdmin.getText();
		 
		 
		 Pattern patron_dni_admin = Pattern.compile(patronDNI);
 		 Matcher mat_dni_admin = patron_dni_admin.matcher(dniAdmin);
 		 
 		 if(mat_dni_admin.matches()) {
 			 if(validarPass.isEmpty()) {
 				JOptionPane.showMessageDialog(this, "Introduzca una contraseña","Error de registro", JOptionPane.WARNING_MESSAGE);
 			 }
 			else {
				 cn = conexion.conexion_correcta();
				 if(cn!=null) {
					 String sql = "INSERT INTO usuario (DNI, contrasenia, nombre, apellidos, rol) VALUES (?, ?, ?, ?, 'Administrador')";
					 try(PreparedStatement stmt = cn.prepareStatement(sql)){
						 stmt.setString(1, dniAdmin);
						 stmt.setString(2, validarPass);
						 stmt.setString(3, nombreAdmin);
						 stmt.setString(4, apellidosAdmin);
						 
						 int filasAfectadas = stmt.executeUpdate();
						 
						 if(filasAfectadas > 0) {
							 JOptionPane.showMessageDialog(this, "Registro exitoso", "Información", JOptionPane.INFORMATION_MESSAGE);
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
	 
	 public void logout() {
		 JOptionPane.showMessageDialog(this, "Cerrando Sesión...", "Información", JOptionPane.INFORMATION_MESSAGE);
		 JFrameLogin JFLogin = new JFrameLogin();
		 JFLogin.setVisible(true);
         dispose();
	 }
}
