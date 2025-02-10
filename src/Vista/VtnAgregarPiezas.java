package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Controlador.Conector_BBDD;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Toolkit;

public class VtnAgregarPiezas extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox comboBoxPiezas;
	private JLabel lblValorStockDisponible, lblCantidad;
	private JButton btnMas, btnMenos, btnAgregar;
	private String idOrden;
	private Conector_BBDD conexion = new Conector_BBDD();


	public VtnAgregarPiezas(String idOrden) {
		setTitle("Agregar piezas | Derrap");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VtnAgregarPiezas.class.getResource("/package_assets/icon.png")));
		this.idOrden = idOrden;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 479, 398);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPiezasDisponibles = new JLabel("Agregar Pieza: ");
		lblPiezasDisponibles.setForeground(new Color(255, 255, 255));
		lblPiezasDisponibles.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPiezasDisponibles.setBounds(66, 35, 143, 34);
		contentPane.add(lblPiezasDisponibles);
		
		comboBoxPiezas = new JComboBox();
		comboBoxPiezas.setBounds(240, 44, 162, 21);
		comboBoxPiezas.addActionListener(this);
		contentPane.add(comboBoxPiezas);
		
		btnMenos = new JButton("-");
		btnMenos.setBackground(new Color(255, 255, 255));
		btnMenos.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnMenos.setFocusable(false);
		btnMenos.setFont(new Font("Tahoma", Font.BOLD, 28));
		btnMenos.setBounds(143, 204, 60, 43);
		btnMenos.addActionListener(this);
		contentPane.add(btnMenos);
		
		btnMas = new JButton("+");
		btnMas.setBackground(new Color(255, 255, 255));
		btnMas.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnMas.setFocusable(false);
		btnMas.setFont(new Font("Tahoma", Font.BOLD, 28));
		btnMas.setBounds(268, 204, 60, 43);
		btnMas.addActionListener(this);
		contentPane.add(btnMas);
		
		lblCantidad = new JLabel("1");
		lblCantidad.setForeground(new Color(255, 255, 255));
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		lblCantidad.setBounds(213, 208, 45, 42);
		contentPane.add(lblCantidad);
		
		JLabel lblStockDisponible = new JLabel("Stock Disponible:");
		lblStockDisponible.setForeground(new Color(255, 255, 255));
		lblStockDisponible.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStockDisponible.setBounds(66, 107, 143, 34);
		contentPane.add(lblStockDisponible);
		
		lblValorStockDisponible = new JLabel("1");
		lblValorStockDisponible.setForeground(new Color(255, 255, 255));
		lblValorStockDisponible.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorStockDisponible.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblValorStockDisponible.setBounds(296, 102, 45, 42);
		contentPane.add(lblValorStockDisponible);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnAgregar.setForeground(new Color(102, 153, 204));
		btnAgregar.setBackground(new Color(255, 255, 255));
		btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnAgregar.setFocusable(false);
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(181, 312, 105, 21);
		btnAgregar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnAgregar.setBackground(Color.BLACK);
				btnAgregar.setForeground(Color.WHITE);
			}

			public void mouseExited(MouseEvent e) {
				btnAgregar.setBackground(Color.WHITE);
				btnAgregar.setForeground((new Color(102, 153, 204)));
				;
			}
		});
		contentPane.add(btnAgregar);
		
		
		//Cargo las piezas en el combo box
		conexion.conectar();
		String consultaSql = "SELECT id_repuesto, nombre FROM repuesto";
		try {
			//Ejecuto la consulta
			ResultSet rset = conexion.ejecutarSelect(consultaSql);
			
			//Recorro todos los resultados y los guardo en el combo box
			while(rset.next()) {
				comboBoxPiezas.addItem(rset.getString("nombre"));
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void actualizarStockLabel() {
		//Guardo la pieza actual
		String pieza = comboBoxPiezas.getSelectedItem().toString();
		
		//Obtengo el stock disponible de esa pieza
		conexion.conectar();
		String consultaSql = "SELECT cantidad FROM repuesto where nombre = '"+ pieza + "'";
		
		try {
			//Ejecuto la consulta
			ResultSet rset = conexion.ejecutarSelect(consultaSql);
			
			while(rset.next()) {
				lblValorStockDisponible.setText(rset.getString("cantidad"));
			}
			
			//Reinicio el label de cantidad
			lblCantidad.setText("1");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void agregarPieza() {
		//Obtengo el nombre de la pieza, el stock y la cantidad 
		String pieza = comboBoxPiezas.getSelectedItem().toString();
		int stock = Integer.parseInt(lblValorStockDisponible.getText());
		int cantidad = Integer.parseInt(lblCantidad.getText());
		String idRepuesto="";
		
		//Calculo la cantidad de las piezas que quedaran
		int cantidadRestante = stock - cantidad;
		
		//Pido confirmacion para agregar la pieza
		int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas agregar " + cantidad + " " + pieza + "? "
				, "Confirmar agregar pieza", JOptionPane.YES_NO_OPTION);

		if(confirmacion == JOptionPane.YES_OPTION) {
			conexion.conectar();
			//Ejecuto un update sobre la tabla repuesto para cambiar el stock restante
			try {
				
				//Primero obtengo el id del repuesto
				String consultaSql = "SELECT id_repuesto FROM repuesto where nombre = '"+ pieza + "'";
				ResultSet rset = conexion.ejecutarSelect(consultaSql);
				
				//Recorro todos los resultados y los guardo en el combo box
				while(rset.next()) {
					idRepuesto = rset.getString("id_repuesto");
				}
				
				int filasAfectadas = conexion.ejecutarInsertDeleteUpdate("UPDATE repuesto SET cantidad = "+cantidadRestante+" WHERE nombre = '"+pieza+"'");
				
				//Tambien se agrega la pieza y la cantidad a la tabla intermedia repuestos_utilizados
				int filasAfectadas2 = conexion.ejecutarInsertDeleteUpdate("INSERT INTO repuestos_utilizados (id_orden, id_repuesto, cantidad) VALUES ('"
						+ idOrden + "', '" + idRepuesto + "', '" + cantidad + "')");
				
				if(filasAfectadas > 0 && filasAfectadas2 > 0) {
					JOptionPane.showMessageDialog(this, "Piezas agregadas");
					
					//Actualizo los valores de la ventana anterior
					VtnAbrirOrden.actualizarValores();
					dispose();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == comboBoxPiezas) {
			actualizarStockLabel();
		}else if(e.getSource() == btnMenos) {
			//Obtengo la cantidad actual
			int cantidadActual = Integer.parseInt(lblCantidad.getText());
			//Si es mayor que 1, resta uno
			if(cantidadActual > 1) {
				cantidadActual--;
			}
			//Cambia el numero del label
			lblCantidad.setText(String.valueOf(cantidadActual));
		}else if(e.getSource() == btnMas) {
			//Obtengo la cantidad actual
			int cantidadActual = Integer.parseInt(lblCantidad.getText());
			int stockDisponible = Integer.parseInt(lblValorStockDisponible.getText());
			
			//Si es menor que el stock disponible, suma uno
			if(cantidadActual < stockDisponible) {
				cantidadActual++;
			}
			//Cambia el numero del label
			lblCantidad.setText(String.valueOf(cantidadActual));
		}else if(e.getSource() == btnAgregar) {
			agregarPieza();
		}
		
	}

}
