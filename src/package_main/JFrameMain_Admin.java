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

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import ventana_emergente.VtnCrearActualizarMecanico;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


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
import javax.swing.JTable;

public class JFrameMain_Admin extends JFrame implements ActionListener {
	Conector_BBDD conexion = new Conector_BBDD();
	Background fondoPantalla = new Background();
	Connection cn = null;
    Statement stm = null;
    ResultSet rsetresultado = null;
    VtnCrearActualizarMecanico vtnmecanico = new VtnCrearActualizarMecanico();
	

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnCrearMecanicos, btnLogout, btnImprimir, btnClientes, btnCrearCliente,btnCrearCoche, btnProveedores,btnMecanicos, btnCrearProveedores;
	private JPanel jpClientes,jpvacio1,jpvacio2,jpvacio3;
	private JTextField txtusuarioAdmin,txtpasswordAdmin, txtusuarioMecanico, txtpasswordMecanico, txtnombreAdmin, txtapellidosAdmin, txtnombreMecanico, txtapellidosMecanico,txtBuscadorClientes,txtBuscadorMecanicos, txtBuscadorProveedor;
	private JTable tblTablaClientes,tblTablaCoches, tblTablaMecanicos, tblTablaProveedores;
	private JTextField txtBuscadorCoches;

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

        setSize(1062, 660);
        setLocationRelativeTo(null);
        
        jpClientes = new JPanel();
        jpClientes.setLayout(null);
        
        
        jpvacio1 = new JPanel();
        jpvacio2 = new JPanel();
        jpvacio3 = new JPanel();
        
        
        JTabbedPane jtpmenuPrincipal = new JTabbedPane();
        jtpmenuPrincipal.setBounds(10, 38, 1026, 572);
        
        jtpmenuPrincipal.add("Clientes",jpClientes);
        jtpmenuPrincipal.add("Vacio 1",jpvacio1);
        jtpmenuPrincipal.add("Vacio 2",jpvacio2);
        jtpmenuPrincipal.add("Vacio 3",jpvacio3);
        
        getContentPane().add(jtpmenuPrincipal);
        

        
        //CREAR MECANICOS
        btnMecanicos = new JButton("MECÁNICOS");
        btnMecanicos.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnMecanicos.setBounds(21, 226, 150, 30);
        btnMecanicos.addActionListener(this);
        jpClientes.add(btnMecanicos);
        
        JLabel lblTitulo = new JLabel("GESTIÓN");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(21, 26, 150, 24);
        jpClientes.add(lblTitulo);
        
        btnClientes = new JButton("CLIENTES");
        btnClientes.addActionListener(this);
        btnClientes.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnClientes.setBounds(21, 105, 150, 30);
        jpClientes.add(btnClientes);
        
        btnProveedores = new JButton("PROVEEDORES");
        btnProveedores.addActionListener(this);
        btnProveedores.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnProveedores.setBounds(21, 356, 150, 30);
        jpClientes.add(btnProveedores);
        

        
        
        
     // Crear el JTextField con ícono y hint
        txtBuscadorClientes = new JTextField();
        txtBuscadorClientes.setColumns(10); // Ajusta el ancho
        txtBuscadorClientes.setText("Buscar clientes..."); // Placeholder
        txtBuscadorClientes.setForeground(Color.GRAY);

        // Configurar el hint con el FocusListener
        String hint = "Buscar clientes...";
        txtBuscadorClientes.setText(hint);
        txtBuscadorClientes.setForeground(Color.GRAY);
        txtBuscadorClientes.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtBuscadorClientes.getText().equals(hint)) {
                    txtBuscadorClientes.setText("");
                    txtBuscadorClientes.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtBuscadorClientes.getText().isEmpty()) {
                    txtBuscadorClientes.setText(hint);
                    txtBuscadorClientes.setForeground(Color.GRAY);
                }
            }
        });

        // Configurar el ícono

        // Crear un panel para el JTextField con el ícono
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBounds(469, 26, 497, 24);
        searchPanel.add(txtBuscadorClientes, BorderLayout.CENTER);

        // Agregar el searchPanel en lugar de txtBuscadorChoches directamente
        jpClientes.add(searchPanel);

        
        tblTablaClientes = new JTable();
        tblTablaClientes.setBounds(469, 61, 497, 153);
        jpClientes.add(tblTablaClientes);
        
        btnCrearCliente = new JButton("+");
        btnCrearCliente.addActionListener(this);
        btnCrearCliente.setBackground(new Color(170, 255, 0));
        btnCrearCliente.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnCrearCliente.setBounds(469, 226, 497, 35);
        jpClientes.add(btnCrearCliente);
        
     // Cambia el botón btnImprimir para que sea redondo y tenga un ícono
        btnImprimir = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                if (isOpaque()) {
                    // Dibujar el fondo en forma de círculo
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(getBackground());
                    g2.fillOval(0, 0, getWidth(), getHeight());
                    g2.dispose();
                }
                super.paintComponent(g);
            }

            @Override
            public Dimension getPreferredSize() {
                // Define dimensiones iguales para mantener el botón redondo
                return new Dimension(50, 50); // Tamaño personalizado para el botón
            }
        };
        btnImprimir.setContentAreaFilled(false);
        btnImprimir.setFocusPainted(false);
        btnImprimir.setBorderPainted(false);
        btnImprimir.setOpaque(false);
        btnImprimir.addActionListener(this);

        // Coloca un ícono en el botón redondo
        Icon icon3 = new ImageIcon(getClass().getResource("/package_assets/printicon.png")); // Ruta del ícono
        btnImprimir.setIcon(icon3);
        btnImprimir.setBackground(new Color(255, 87, 34)); // Color de fondo del botón

        // Añade el botón a tu panel
        btnImprimir.setBounds(10, 463, 150, 45); // Ajusta la posición y tamaño
        jpClientes.add(btnImprimir);

        
        txtBuscadorCoches = new JTextField();
        txtBuscadorCoches.setColumns(10); // Ajusta el ancho
        txtBuscadorCoches.setText("Buscar coches..."); // Placeholder
        txtBuscadorCoches.setForeground(Color.GRAY);
        
        String hint2 = "Buscar coches...";
        txtBuscadorCoches.setText(hint2);
        txtBuscadorCoches.setForeground(Color.GRAY);
        txtBuscadorCoches.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtBuscadorCoches.getText().equals(hint2)) {
                	txtBuscadorCoches.setText("");
                	txtBuscadorCoches.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtBuscadorCoches.getText().isEmpty()) {
                	txtBuscadorCoches.setText(hint2);
                	txtBuscadorCoches.setForeground(Color.GRAY);
                }
            }
        });


        // Crear un panel para el JTextField con el ícono
        JPanel searchPanel2 = new JPanel(new BorderLayout());
        searchPanel2.setBounds(469, 288, 497, 24);
        searchPanel2.add(txtBuscadorCoches, BorderLayout.CENTER);

        // Agregar el searchPanel en lugar de txtBuscadorChoches directamente
        jpClientes.add(searchPanel2);
        

        tblTablaCoches = new JTable();
        tblTablaCoches.setBounds(469, 331, 497, 127);
        jpClientes.add(tblTablaCoches);
        
        btnCrearCoche = new JButton("+");
        btnCrearCoche.setBackground(new Color(170, 255, 0));
        btnCrearCoche.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnCrearCoche.setBounds(469, 473, 497, 35);
        jpClientes.add(btnCrearCoche);
        
        
        txtBuscadorMecanicos = new JTextField();
        txtBuscadorMecanicos.setColumns(10); // Ajusta el ancho
        txtBuscadorMecanicos.setText("Buscar mecanicos..."); // Placeholder
        txtBuscadorMecanicos.setForeground(Color.GRAY);

        // Configurar el hint con el FocusListener
        String hint3 = "Buscar mecanicos...";
        txtBuscadorMecanicos.setText(hint3);
        txtBuscadorMecanicos.setForeground(Color.GRAY);
        txtBuscadorMecanicos.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtBuscadorMecanicos.getText().equals(hint3)) {
                	txtBuscadorMecanicos.setText("");
                	txtBuscadorMecanicos.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtBuscadorMecanicos.getText().isEmpty()) {
                	txtBuscadorMecanicos.setText(hint3);
                	txtBuscadorMecanicos.setForeground(Color.GRAY);
                }
            }
        });

        // Configurar el ícono

        // Crear un panel para el JTextField con el ícono
        JPanel searchPanel3 = new JPanel(new BorderLayout());
        searchPanel3.setBounds(469, 26, 497, 24);
        searchPanel3.add(txtBuscadorMecanicos, BorderLayout.CENTER);

        // Agregar el searchPanel en lugar de txtBuscadorChoches directamente
        jpClientes.add(searchPanel3);

        
        tblTablaMecanicos = new JTable();
        tblTablaMecanicos.setBounds(469, 61, 497, 153);
        jpClientes.add(tblTablaMecanicos);
        
        btnCrearMecanicos = new JButton("+");
        btnCrearMecanicos.addActionListener(this);
        btnCrearMecanicos.setBackground(new Color(170, 255, 0));
        btnCrearMecanicos.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnCrearMecanicos.setBounds(469, 226, 497, 35);
        jpClientes.add(btnCrearMecanicos);
        
        txtBuscadorProveedor = new JTextField();
        txtBuscadorProveedor.setColumns(10); // Ajusta el ancho
        txtBuscadorProveedor.setText("Buscar proveedor..."); // Placeholder
        txtBuscadorProveedor.setForeground(Color.GRAY);

        // Configurar el hint con el FocusListener
        String hint4 = "Buscar proveedor...";
        txtBuscadorProveedor.setText(hint4);
        txtBuscadorProveedor.setForeground(Color.GRAY);
        txtBuscadorProveedor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtBuscadorProveedor.getText().equals(hint4)) {
                	txtBuscadorProveedor.setText("");
                	txtBuscadorProveedor.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtBuscadorProveedor.getText().isEmpty()) {
                	txtBuscadorProveedor.setText(hint4);
                	txtBuscadorProveedor.setForeground(Color.GRAY);
                }
            }
        });

        // Configurar el ícono

        // Crear un panel para el JTextField con el ícono
        JPanel searchPanel4 = new JPanel(new BorderLayout());
        searchPanel4.setBounds(469, 26, 497, 24);
        searchPanel4.add(txtBuscadorProveedor, BorderLayout.CENTER);

        // Agregar el searchPanel en lugar de txtBuscadorChoches directamente
        jpClientes.add(searchPanel4);

        
        tblTablaProveedores = new JTable();
        tblTablaProveedores.setBounds(469, 61, 497, 153);
        jpClientes.add(tblTablaProveedores);
        
        btnCrearProveedores = new JButton("+");
        btnCrearProveedores.addActionListener(this);
        btnCrearProveedores.setBackground(new Color(170, 255, 0));
        btnCrearProveedores.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnCrearProveedores.setBounds(469, 226, 497, 35);
        jpClientes.add(btnCrearProveedores);
        
        
        btnLogout = new JButton("Cerrar Sesión");
        btnLogout.addActionListener(this);
        btnLogout.setBounds(917, 11, 119, 23);
        fondoPantalla.add(btnLogout);
        
        txtBuscadorClientes.setVisible(false);
		tblTablaClientes.setVisible(false);
		btnCrearCliente.setVisible(false);
		txtBuscadorCoches.setVisible(false);
		tblTablaCoches.setVisible(false);
		btnCrearCoche.setVisible(false);
		
		txtBuscadorMecanicos.setVisible(false);
		tblTablaMecanicos.setVisible(false);
		btnCrearMecanicos.setVisible(false);
		
		txtBuscadorProveedor.setVisible(false);
		tblTablaProveedores.setVisible(false);
		btnCrearProveedores.setVisible(false);
        
        //ACTION PERFORMED_____________________________________________________________
        
	}
	
	 public void actionPerformed(ActionEvent e) {
	         if(e.getSource() == btnLogout) {
        		logout();
        	} else if(e.getSource() == btnImprimir) {
        		System.out.println("adasdasds");
        	} else if(e.getSource() == btnClientes) {
        		txtBuscadorClientes.setVisible(true);
        		tblTablaClientes.setVisible(true);
        		btnCrearCliente.setVisible(true);
        		txtBuscadorCoches.setVisible(true);
        		tblTablaCoches.setVisible(true);
        		btnCrearCoche.setVisible(true);
        		
        		txtBuscadorMecanicos.setVisible(false);
        		tblTablaMecanicos.setVisible(false);
        		btnCrearMecanicos.setVisible(false);
        	} else if (e.getSource() == btnMecanicos) {
        		txtBuscadorMecanicos.setVisible(true);
        		tblTablaMecanicos.setVisible(true);
        		btnCrearMecanicos.setVisible(true);
        		
        		txtBuscadorClientes.setVisible(false);
        		tblTablaClientes.setVisible(false);
        		btnCrearCliente.setVisible(false);
        		txtBuscadorCoches.setVisible(false);
        		tblTablaCoches.setVisible(false);
        		btnCrearCoche.setVisible(false);
        	} else if (e.getSource() == btnProveedores) {
        		txtBuscadorProveedor.setVisible(true);
        		tblTablaProveedores.setVisible(true);
        		btnCrearProveedores.setVisible(true);
        		
        		txtBuscadorMecanicos.setVisible(false);
        		tblTablaMecanicos.setVisible(false);
        		btnCrearMecanicos.setVisible(false);
        		
        		txtBuscadorClientes.setVisible(false);
        		tblTablaClientes.setVisible(false);
        		btnCrearCliente.setVisible(false);
        		txtBuscadorCoches.setVisible(false);
        		tblTablaCoches.setVisible(false);
        		btnCrearCoche.setVisible(false);
        	} else if (e.getSource() == btnCrearMecanicos) {
        		vtnmecanico.setVisible(true);
                
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
