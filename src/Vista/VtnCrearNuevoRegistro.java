package Vista;

import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
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
import Controlador.Conector_BBDD;


public class VtnCrearNuevoRegistro extends JFrame implements ActionListener, KeyListener {
	Conector_BBDD conexion = new Conector_BBDD();
	Background fondoPantalla = new Background();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo, lblCampo2, lblPrimaryKey, lblCampo3, lblCampo1, lblCampo4, lblCampo5, lblCampo6,
			lblForeignKey;
	private JTextField txtPrimaryKey, txtCampo1, txtCampo2, txtCampo3, txtCampo4;
	private JButton btnCrear;
	private String grupo;
	private JTextField txtCampo5;
	private JTextField txtCampo6;
	private JTextField txtForeignKey;
	private DefaultTableModel modelTabla;
	private JPanel panel;

	public VtnCrearNuevoRegistro(String grupo, DefaultTableModel modelTabla) {
		Conector_BBDD conexion = new Conector_BBDD();
		conexion.conectar();
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

		txtCampo1 = new JTextField();
		txtCampo1.setBounds(137, 197, 207, 29);
		fondoPantalla.add(txtCampo1);
		txtCampo1.setColumns(10);
		txtCampo1.addKeyListener(this);

		txtCampo2 = new JTextField();
		txtCampo2.setBounds(137, 260, 207, 29);
		fondoPantalla.add(txtCampo2);
		txtCampo2.setColumns(10);
		txtCampo2.addKeyListener(this);

		txtPrimaryKey = new JTextField();
		txtPrimaryKey.setBounds(137, 137, 207, 29);
		fondoPantalla.add(txtPrimaryKey);
		txtPrimaryKey.setColumns(10);
		txtPrimaryKey.addKeyListener(this);

		txtCampo3 = new JTextField();
		txtCampo3.setBounds(137, 326, 207, 29);
		fondoPantalla.add(txtCampo3);
		txtCampo3.setColumns(10);
		txtCampo3.addKeyListener(this);

		lblCampo2 = new JLabel("");
		lblCampo2.setForeground(new Color(255, 255, 255));
		lblCampo2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCampo2.setBounds(24, 266, 103, 15);
		fondoPantalla.add(lblCampo2);

		lblPrimaryKey = new JLabel("Pk: ");
		lblPrimaryKey.setForeground(new Color(255, 255, 255));
		lblPrimaryKey.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrimaryKey.setBounds(24, 143, 99, 14);
		fondoPantalla.add(lblPrimaryKey);

		lblCampo3 = new JLabel("");
		lblCampo3.setForeground(new Color(255, 255, 255));
		lblCampo3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCampo3.setBounds(24, 332, 99, 14);
		fondoPantalla.add(lblCampo3);

		lblCampo1 = new JLabel("Nombre:");
		lblCampo1.setForeground(new Color(255, 255, 255));
		lblCampo1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCampo1.setBounds(24, 203, 86, 14);
		fondoPantalla.add(lblCampo1);

		lblCampo4 = new JLabel("");
		lblCampo4.setForeground(new Color(255, 255, 255));
		lblCampo4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCampo4.setBounds(383, 144, 99, 13);
		fondoPantalla.add(lblCampo4);

		txtCampo6 = new JTextField();
		txtCampo6.setColumns(10);
		txtCampo6.setBounds(488, 260, 207, 29);
		fondoPantalla.add(txtCampo6);

		txtForeignKey = new JTextField();
		txtForeignKey.setColumns(10);
		txtForeignKey.setBounds(488, 326, 207, 29);
		txtForeignKey.addKeyListener(this);
		fondoPantalla.add(txtForeignKey);

		lblCampo5 = new JLabel("");
		lblCampo5.setForeground(new Color(255, 255, 255));
		lblCampo5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCampo5.setBounds(383, 204, 101, 13);

		fondoPantalla.add(lblCampo5);

		lblCampo6 = new JLabel("");
		lblCampo6.setForeground(new Color(255, 255, 255));
		lblCampo6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCampo6.setBounds(383, 268, 99, 13);
		fondoPantalla.add(lblCampo6);

		lblForeignKey = new JLabel("");
		lblForeignKey.setForeground(new Color(255, 255, 255));
		lblForeignKey.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblForeignKey.setBounds(383, 333, 99, 13);
		fondoPantalla.add(lblForeignKey);

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

		txtCampo5 = new JTextField();
		txtCampo5.setBounds(490, 197, 207, 29);
		panel.add(txtCampo5);
		txtCampo5.addKeyListener(this);
		txtCampo5.setColumns(10);

		txtCampo4 = new JTextField();
		txtCampo4.setBounds(490, 138, 207, 29);
		panel.add(txtCampo4);
		txtCampo4.addKeyListener(this);
		txtCampo4.setColumns(10);

		actualizarVisibilidad(grupo);
	}

	private void actualizarVisibilidad(String grupo) {
		//Se ocultan todos los labels y textFields menos el de la primary key , el del nombre y el campo2 (porque aparecen en todas las opciones)
		lblCampo3.setVisible(false);
		lblCampo4.setVisible(false);
		lblCampo5.setVisible(false);
		lblCampo6.setVisible(false);
		lblForeignKey.setVisible(false);
		txtCampo3.setVisible(false);
		txtCampo4.setVisible(false);
		txtCampo5.setVisible(false);
		txtCampo6.setVisible(false);
		txtForeignKey.setVisible(false);
		 
		switch (grupo) {
		case "cliente":
			lblTitulo.setText("CREAR NUEVO CLIENTE");
			lblPrimaryKey.setText("DNI:");
			lblCampo2.setText("Apellidos:");
			lblCampo4.setVisible(true);
			lblCampo4.setText("Telefono:");
			txtCampo4.setVisible(true);
			lblCampo4.setVisible(true);
			break;
		case "mecanico":
			lblTitulo.setText("CREAR NUEVO MECANICO");
			lblPrimaryKey.setText("DNI:");
			lblCampo2.setText("Apellidos: ");
			lblCampo3.setVisible(true);
			lblCampo3.setText("Contraseña: ");
			txtCampo3.setVisible(true);
			break;
		case "vehiculo":
			lblTitulo.setText("CREAR NUEVO VEHICULO");
			lblPrimaryKey.setText("Matricula:");
			lblCampo1.setText("Marca:");
			lblCampo2.setText("Modelo:");
			lblCampo3.setText("Color:");
			lblCampo4.setText("Combustible:");
			lblCampo5.setText("Kilometros:");
			lblCampo6.setText("Año:");
			lblForeignKey.setText("Dni cliente:");
			lblCampo3.setVisible(true);
			txtCampo3.setVisible(true);
			lblCampo4.setVisible(true);
			txtCampo4.setVisible(true);
			lblCampo5.setVisible(true);
			txtCampo5.setVisible(true);
			lblCampo6.setVisible(true);
			txtCampo6.setVisible(true);
			lblForeignKey.setVisible(true);
			txtForeignKey.setVisible(true);
			break;
		case "orden":
			lblTitulo.setText("CREAR NUEVA ORDEN");
			lblPrimaryKey.setVisible(true);
			lblPrimaryKey.setText("ID Orden:");
			lblCampo1.setVisible(true);
			lblCampo1.setText("Matricula:");
			lblCampo2.setVisible(true);
			lblCampo2.setText("Cliente:");
			lblCampo3.setVisible(false);
			lblCampo3.setText("Modelo:");
			lblCampo4.setVisible(false);
			lblCampo4.setText("Matricula:");
			txtPrimaryKey.setVisible(true);
			txtCampo1.setVisible(true);
			txtCampo2.setVisible(true);
			txtCampo3.setVisible(false);
			txtCampo4.setVisible(false);
			break;
		case "repuesto":
			lblTitulo.setText("CREAR NUEVA PIEZA");
			lblPrimaryKey.setVisible(true);
			lblPrimaryKey.setText("ID Pieza:");
			lblCampo1.setVisible(true);
			lblCampo1.setText("Nombre:");
			lblCampo2.setVisible(true);
			lblCampo2.setText("Cantidad:");
			lblCampo3.setVisible(true);
			lblCampo3.setText("Precio Compra:");
			lblCampo4.setVisible(true);
			lblCampo4.setText("Precio Venta:");
			lblCampo5.setVisible(true);
			lblCampo5.setText("Mano De Obra:");
			lblCampo6.setVisible(true);
			lblCampo6.setText("ID Proveedor:");
			txtCampo1.setVisible(true);
			txtCampo2.setVisible(true);
			txtCampo3.setVisible(true);
			txtCampo4.setVisible(true);
			txtCampo5.setVisible(true);
			txtCampo6.setVisible(true);
			break;
		case "factura":
			lblTitulo.setText("CREAR NUEVA FACTURA");
			lblPrimaryKey.setVisible(true);
			lblPrimaryKey.setText("ID Factura:");
			lblCampo1.setVisible(true); 
			lblCampo1.setText("Precio:");
			lblCampo2.setVisible(true);
			lblCampo2.setText("Fecha:");
			lblCampo3.setVisible(true);
			lblCampo3.setText("ID Orden");
			lblCampo4.setVisible(false);
			lblCampo4.setText("Precio De Venta:");
			lblCampo5.setVisible(false);
			lblCampo5.setText("Mano De Obra");
			lblCampo6.setVisible(false);
			lblCampo6.setText("ID Proveedor");
			txtCampo1.setVisible(true);
			txtCampo2.setVisible(true);
			txtCampo3.setVisible(true);
			txtCampo4.setVisible(false);
			txtCampo5.setVisible(false);
			txtCampo6.setVisible(false);
			break;
		}
	}

	public void crearNuevoRegistro(String grupo) throws SQLException {

		String primaryKey = txtPrimaryKey.getText(); //DNI USUARIO, DNI CLIENTE, MATRICULA, ID ORDEN, ID REPUESTO Y ID FACTURA
		String patronDNI = "[0-9]{8}[A-Z a-z]";
		Pattern patternDni = Pattern.compile(patronDNI);
		Matcher matcherDni = patternDni.matcher(primaryKey);
		
		String valorCampo1 = txtCampo1.getText(); //NOMBRE, MARCA, MATRICULA_VEHICULO (ORDEN), PRECIO TOTAL
		String valorCampo2 = txtCampo2.getText(); //APELLIDO, MODELO, CLIENTE DNI (ORDEN), CANTIDAD, FECHA
		String valorCampo3 = txtCampo3.getText(); //CONTRASEÑA, COLOR, PRECIO COMPRA, ID ORDEN (FACTURA)
		String valorCampo4 = txtCampo4.getText(); //TELEFONO, COMBUSTIBLE, PRECIO VENTA
		String valorCampo5 = txtCampo5.getText(); //KILOMETROS, MANO DE OBRA
		String valorCampo6 = txtCampo6.getText(); //AÑO VEHICULO, ID PROVEEDOR
		String foreignKey = txtForeignKey.getText(); //DNI CLIENTE
		 
  
		conexion.conectar();
		String sql = "";
		// Segun el grupo que sea, cambia el insert
		switch (grupo) {
		case "mecanico":
			if (matcherDni.matches()) {
				sql = "INSERT INTO usuario (DNI, password, nombre, apellidos, rol) VALUES " + "('" + primaryKey
						+ "', '" + valorCampo3 + "', '" + valorCampo1 + "', '" + valorCampo2 + "', 'Mecanico')";
			}
			break;
		case "cliente":
			sql = "INSERT INTO cliente (DNI, nombre, apellidos, telefono) VALUES ('" + primaryKey + "', '"
					+ valorCampo1 + "', '" + valorCampo2 + "', '" + valorCampo4 + "')";

			break;
		case "vehiculo": 

			if (foreignKey.isBlank()) {
				foreignKey = "NULL";
			} else {
				foreignKey = "'" + foreignKey + "'";
			}

			// Utilizo los otros campos de mecanico y cliente para vehiculo (dni,nombre,etc)
			sql = "INSERT INTO vehiculo (matricula, marca, modelo, color, combustible, kilometros, year, dni_cliente) VALUES"
					+ " ('" + primaryKey + "', '" + valorCampo1 + "', '" + valorCampo2 + "', '" + valorCampo3
					+ "', '" + valorCampo4 + "', '" + valorCampo5 + "', '" + valorCampo6 + "', " + foreignKey + ")";
			break;
		case "orden":
			sql = "INSERT INTO orden (id_orden, matricula_vehiculo, cliente_DNI) VALUES ('" + primaryKey + "', '"
					+ valorCampo1 + "', '" + valorCampo2 + "')";
			break;
		case "repuesto":
		    sql = "INSERT INTO repuesto (id_repuesto, nombre, cantidad, precio_compra, precio_venta, mano_de_obra, id_proveedor) VALUES"
		        + " ('" + primaryKey + "', '" + valorCampo1 + "', " + valorCampo2 + ", " + valorCampo3 + ", "
		        + valorCampo4 + ", " + valorCampo5 + ", '" + valorCampo6 + "')";
		    break;
		    
		case "factura":
			double precioVentaRepuesto = obtenerPrecioRepuesto(primaryKey, "precio_venta"); // Método para obtener el precio de venta
		    double manoDeObraRepuesto = obtenerPrecioRepuesto(primaryKey, "mano_de_obra"); // Método para obtener la mano de obra
		    
		    double precioTotal = precioVentaRepuesto + manoDeObraRepuesto;
		    
		    sql = "INSERT INTO factura (id_factura, precio_total, fecha, id_orden) VALUES "
		            + "('" + primaryKey + "', '" + valorCampo1 + "', '" + valorCampo2 + "', '" + valorCampo3 + "')";
		        
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
			try {
				crearNuevoRegistro(grupo);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
	
	public double obtenerPrecioRepuesto(String idRepuesto, String columna) throws SQLException {
	    double valor = 0.0;

	    // Realiza la consulta para obtener el valor de la columna especificada (precio_venta o mano_de_obra)
	    String sql = "SELECT " + columna + " FROM repuesto WHERE id_repuesto = '" + idRepuesto + "'";

	    // Ejecuta la consulta
	    ResultSet rs = conexion.ejecutarSelect(sql);  // Asegúrate de ejecutar el SQL correcto

	    try {
	        if (rs.next()) {
	            valor = rs.getDouble(columna);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return valor;
	}

	


	
}