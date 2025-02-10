package Vista;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Controlador.Conector_BBDD;
import Controlador.ControladorRegistros;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;

public class VtnAbrirOrden extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, panelActual;
	private static String idOrden;
	private JButton btnCancelar, btnTerminar, btnAgregarPiezas;
	private static JLabel lblValorDniCliente, lblValorMarca, lblValorMatricula, lblValorModelo, lblValorPiezas, lblValorColor, lblValorCombustible, lblValorKilometros, lblValorEstadoOrden, lblValorNombreCliente;
	private static Conector_BBDD conexion = new Conector_BBDD();
	
	public VtnAbrirOrden(String idOrden, JPanel panelActual) {	
		//Obtengo el id de la orden para hacer las consultas
		this.idOrden = idOrden;
		//Obtengo el panel en el que esta la orden para poder cambiarlo luego
		this.panelActual = panelActual;
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1083, 626);
		setTitle(" Abrir Orden | Derrap");
		setIconImage(new ImageIcon(getClass().getResource("/package_assets/icon.png")).getImage());
		setResizable(false);
		setSize(630, 619);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblTitulo = new JLabel("DETALLES DE LA ORDEN");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitulo.setBounds(25, 60, 224, 23);
		contentPane.add(lblTitulo);
		
		JLabel lblIdOrden = new JLabel("ID  orden:");
		lblIdOrden.setForeground(new Color(255, 255, 255));
		lblIdOrden.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIdOrden.setBounds(10, 139, 96, 14);
		contentPane.add(lblIdOrden);
		
		JLabel lblValorOrden = new JLabel(idOrden);
		lblValorOrden.setForeground(Color.WHITE);
		lblValorOrden.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblValorOrden.setBounds(153, 139, 96, 14);
		contentPane.add(lblValorOrden);
		
		JLabel lblDniCliente = new JLabel("DNI cliente: ");
		lblDniCliente.setForeground(Color.WHITE);
		lblDniCliente.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDniCliente.setBounds(320, 139, 107, 14);
		contentPane.add(lblDniCliente);
		
		lblValorDniCliente = new JLabel("ID de la orden");
		lblValorDniCliente.setForeground(Color.WHITE);
		lblValorDniCliente.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblValorDniCliente.setBounds(471, 139, 96, 26);
		contentPane.add(lblValorDniCliente);
		
		JLabel lblMarca = new JLabel("Marca:");
		lblMarca.setForeground(Color.WHITE);
		lblMarca.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMarca.setBounds(24, 255, 96, 14);
		contentPane.add(lblMarca);
		
		lblValorMarca = new JLabel("ID de la orden:");
		lblValorMarca.setForeground(Color.WHITE);
		lblValorMarca.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblValorMarca.setBounds(142, 255, 107, 14);
		contentPane.add(lblValorMarca);
		
		JLabel lblMatricula = new JLabel("Matrícula:");
		lblMatricula.setForeground(Color.WHITE);
		lblMatricula.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMatricula.setBounds(24, 231, 96, 14);
		contentPane.add(lblMatricula);
		
		lblValorMatricula = new JLabel("ID de la orden:");
		lblValorMatricula.setForeground(Color.WHITE);
		lblValorMatricula.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblValorMatricula.setBounds(142, 231, 107, 14);
		contentPane.add(lblValorMatricula);
		
		JLabel lblModelo = new JLabel("Modelo:");
		lblModelo.setForeground(Color.WHITE);
		lblModelo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblModelo.setBounds(24, 279, 96, 14);
		contentPane.add(lblModelo);
		
		lblValorModelo = new JLabel("ID de la orden:");
		lblValorModelo.setForeground(Color.WHITE);
		lblValorModelo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblValorModelo.setBounds(142, 279, 107, 14);
		contentPane.add(lblValorModelo);
		
		JLabel lblPiezas = new JLabel("Piezas:");
		lblPiezas.setForeground(Color.WHITE);
		lblPiezas.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPiezas.setBounds(320, 327, 60, 14);
		contentPane.add(lblPiezas);
		
		lblValorPiezas = new JLabel("...");
		lblValorPiezas.setForeground(Color.WHITE);
		lblValorPiezas.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblValorPiezas.setBounds(427, 292, 140, 85);
		contentPane.add(lblValorPiezas);
		
		JLabel lblTituloVehiculo = new JLabel("VEHÍCULO");
		lblTituloVehiculo.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblTituloVehiculo.setForeground(new Color(255, 255, 255));
		lblTituloVehiculo.setBounds(24, 187, 114, 23);
		contentPane.add(lblTituloVehiculo);
		
		JLabel lblColor = new JLabel("Color:");
		lblColor.setForeground(Color.WHITE);
		lblColor.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblColor.setBounds(24, 303, 96, 14);
		contentPane.add(lblColor);
		
		JLabel lblCombustible = new JLabel("Combustible:");
		lblCombustible.setForeground(Color.WHITE);
		lblCombustible.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCombustible.setBounds(24, 327, 96, 14);
		contentPane.add(lblCombustible);
		
		JLabel lblKilometros = new JLabel("Kilometros:");
		lblKilometros.setForeground(Color.WHITE);
		lblKilometros.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblKilometros.setBounds(24, 351, 96, 14);
		contentPane.add(lblKilometros);
		
		lblValorColor = new JLabel("ID de la orden:");
		lblValorColor.setForeground(Color.WHITE);
		lblValorColor.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblValorColor.setBounds(142, 303, 107, 14);
		contentPane.add(lblValorColor);
		
		lblValorCombustible = new JLabel("ID de la orden:");
		lblValorCombustible.setForeground(Color.WHITE);
		lblValorCombustible.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblValorCombustible.setBounds(142, 327, 107, 14);
		contentPane.add(lblValorCombustible);
		
		lblValorKilometros = new JLabel("ID de la orden:");
		lblValorKilometros.setForeground(Color.WHITE);
		lblValorKilometros.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblValorKilometros.setBounds(142, 351, 107, 14);
		contentPane.add(lblValorKilometros);
		
		JLabel lblEstadoOrden = new JLabel("Estado de la orden: ");
		lblEstadoOrden.setForeground(Color.WHITE);
		lblEstadoOrden.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEstadoOrden.setBounds(320, 65, 158, 14);
		contentPane.add(lblEstadoOrden);
		
		lblValorEstadoOrden = new JLabel("Activa");
		lblValorEstadoOrden.setForeground(Color.WHITE);
		lblValorEstadoOrden.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblValorEstadoOrden.setBounds(499, 66, 107, 14);
		contentPane.add(lblValorEstadoOrden);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnCancelar.setFocusable(false);
		btnCancelar.setBounds(142, 526, 133, 38);
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.setForeground(new Color(102, 153, 204));
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCancelar.addActionListener(this);
		btnCancelar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnCancelar.setBackground(Color.BLACK);
				btnCancelar.setForeground(Color.WHITE);
			}

			public void mouseExited(MouseEvent e) {
				btnCancelar.setBackground(Color.WHITE);
				btnCancelar.setForeground(new Color(102, 153, 204));
				;
			}
		});
		btnCancelar.setFocusable(false);
		contentPane.add(btnCancelar);
		
		btnTerminar = new JButton("Terminar");
		btnTerminar.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnTerminar.setFocusable(false);
		btnTerminar.setBounds(361, 526, 138, 38);
		btnTerminar.setBackground(Color.WHITE);
		btnTerminar.setForeground(new Color(102, 153, 204));
		btnTerminar.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnTerminar.addActionListener(this);
		btnTerminar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnTerminar.setBackground(Color.BLACK);
				btnTerminar.setForeground(Color.WHITE);
			}

			public void mouseExited(MouseEvent e) {
				btnTerminar.setBackground(Color.WHITE);
				btnTerminar.setForeground(new Color(102, 153, 204));
				;
			}
		});
		btnTerminar.setFocusable(false);
		contentPane.add(btnTerminar);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(167, 200, 233));
		panel.setBounds(10, 174, 246, 202);
		contentPane.add(panel);
		
		JLabel lblNombreCliente = new JLabel("Nombre cliente: ");
		lblNombreCliente.setForeground(Color.WHITE);
		lblNombreCliente.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNombreCliente.setBounds(320, 174, 120, 14);
		contentPane.add(lblNombreCliente);
		
		lblValorNombreCliente = new JLabel("ID de la orden");
		lblValorNombreCliente.setForeground(Color.WHITE);
		lblValorNombreCliente.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblValorNombreCliente.setBounds(471, 176, 96, 23);
		contentPane.add(lblValorNombreCliente);
		
		btnAgregarPiezas = new JButton("Agregar Piezas");
		btnAgregarPiezas.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnAgregarPiezas.setFocusable(false);
		btnAgregarPiezas.setForeground(new Color(102, 153, 204));
		btnAgregarPiezas.setBackground(new Color(255, 255, 255));
		btnAgregarPiezas.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAgregarPiezas.setBounds(309, 395, 140, 21);
		btnAgregarPiezas.addActionListener(this);
		btnAgregarPiezas.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnAgregarPiezas.setBackground(Color.BLACK);
				btnAgregarPiezas.setForeground(Color.WHITE);
			}

			public void mouseExited(MouseEvent e) {
				btnAgregarPiezas.setBackground(Color.WHITE);
				btnAgregarPiezas.setForeground((new Color(102, 153, 204)));
				;
			}
		});
		contentPane.add(btnAgregarPiezas);
		
		
		actualizarValores();
		
		
		
	}


	private void cancelarOrden() {
		conexion.conectar();
		//Ejecuto un update sobre la tabla ordenes para cambiar el estado de En proceso -> Activa
		try {
			int filasAfectadas = conexion.ejecutarInsertDeleteUpdate("UPDATE orden SET estado = 'Activa' WHERE id_orden = '"+ idOrden + "'");
			if(filasAfectadas > 0) {
				
				//Saco la orden de la tarjeta
				JFrameMain_Mecanico.sacarTarjetaOrden(panelActual);
				
				//Muestro un mensaje y cierra la ventana
				JOptionPane.showMessageDialog(this, "Orden cancelada");
				dispose();
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void terminarOrden() {
		int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas terminar la orden?", "Confirmar terminar orden", JOptionPane.YES_NO_OPTION);

		if(confirmacion == JOptionPane.YES_OPTION) {
			conexion.conectar();
			//Ejecuto un update sobre la tabla ordenes para cambiar el estado de En proceso -> Terminada
			try {
				int filasAfectadas = conexion.ejecutarInsertDeleteUpdate("UPDATE orden SET estado = 'Terminada' WHERE id_orden = '"+ idOrden + "'");
				if(filasAfectadas > 0) {
					
					//Saco la orden de la tarjeta
					JFrameMain_Mecanico.sacarTarjetaOrden(panelActual);
					
					//Muestro un mensaje y cierra la ventana
					JOptionPane.showMessageDialog(this, "Orden terminada");
					dispose();
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void actualizarValores() {
		conexion.conectar();
		//La consulta obtendra todos los datos de la orden y del vehiculo
		String consultaSql = "SELECT o.*, \r\n"
				+ "       v.*, \r\n"
				+ "       c.*, \r\n"
				+ "       GROUP_CONCAT(CONCAT(r.nombre, ' (', ru.cantidad, ')') SEPARATOR ', ') AS piezas_utilizadas\r\n"
				+ "FROM orden o\r\n"
				+ "JOIN vehiculo v ON o.matricula_vehiculo = v.matricula\r\n"
				+ "JOIN cliente c ON v.dni_cliente = c.dni\r\n"
				+ "LEFT JOIN repuestos_utilizados ru ON o.id_orden = ru.id_orden\r\n"
				+ "LEFT JOIN repuesto r ON ru.id_repuesto = r.id_repuesto\r\n"
				+ "WHERE o.id_orden = '" + idOrden + "' "
				+ "GROUP BY o.id_orden, v.matricula, c.dni";
		
		try {
			//Ejecuto la consulta
			ResultSet rset = conexion.ejecutarSelect(consultaSql);
			
			//Recorro todos los resultados y los guardo en los labels correspondientes
			while(rset.next()) {
				lblValorMatricula.setText(rset.getString("matricula"));
				lblValorMarca.setText(rset.getString("marca"));
				lblValorModelo.setText(rset.getString("modelo"));
				lblValorColor.setText(rset.getString("color"));
				lblValorCombustible.setText(rset.getString("combustible"));
				lblValorKilometros.setText(rset.getString("kilometros"));
				lblValorDniCliente.setText(rset.getString("dni_cliente"));
				lblValorNombreCliente.setText(rset.getString("nombre"));
				lblValorPiezas.setText(rset.getString("piezas_utilizadas"));
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar) {
			cancelarOrden();
		}else if(e.getSource() == btnTerminar) {
			terminarOrden();
		}else if(e.getSource() == btnAgregarPiezas) {
			VtnAgregarPiezas vtnAgregarPiezas = new VtnAgregarPiezas(idOrden);
			vtnAgregarPiezas.setVisible(true);
		}
		
	}
}
