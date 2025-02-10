package Vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import Controlador.Conector_BBDD;
import Controlador.ControladorRegistros;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JFrameMain_Mecanico extends JFrame implements ActionListener, ListSelectionListener {
	Conector_BBDD conexion = new Conector_BBDD();
	Connection cn = null;
	Statement stm = null;
	ResultSet rsetresultado = null;
	// Background fondoPantalla = new Background();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JPanel orden1;
	private JPanel orden2;
	private JPanel orden3;
	private JButton btnLogout,btnAsignar;
	private JPanel panel, panelOrdenes;
	private CardLayout cardLayout = new CardLayout(0, 0);
	private JTable tblTablaOrdenes, tblTablaRepuestos;
	private static DefaultTableModel modelTablaOrdenes, modeloTablaRepuestos;
	private JLabel lblSinOrdenAsignada1, lblSinOrdenAsignada2, lblSinOrdenAsignada3;
	
	String[] columnasRepuesto = {"ID_Repuesto", "Nombre", "Cantidad", "Precio_Compra", "Precio_Venta", "Mano_de_Obra", "ID_Proveedor"};
	String[] columnasOrdenes = {"id_orden", "Estado", "Matricula", "Cliente", "Piezas"};
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					JFrameMain_Mecanico frame = new JFrameMain_Mecanico();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrameMain_Mecanico() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 626);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Mecánico | Derrap");
		setIconImage(new ImageIcon(getClass().getResource("/package_assets/icon.png")).getImage());
		setResizable(false);
		// fondoPantalla.setLayout(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel barra = new JPanel();
		barra.setBounds(10, 11, 213, 674);
		contentPane.add(barra);
		barra.setBackground(new Color(102, 153, 204));
		barra.setLayout(null);

		JLabel Btnstock = new JLabel("Stock de piezas");
		Btnstock.setForeground(new Color(255, 255, 255));
		Btnstock.setBounds(23, 169, 154, 28);
		Btnstock.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				Btnstock.setForeground(Color.BLACK);
			}

			public void mouseExited(MouseEvent e) {
				Btnstock.setForeground(Color.WHITE);
			}

			public void mouseClicked(MouseEvent e) {
				//Actualizo la tabla de repuestp
				ControladorRegistros.actualizarTablas("repuesto", modeloTablaRepuestos);
				cardLayout.show(panel, "PanelStock");
			}
		});
		barra.add(Btnstock);
		Btnstock.setHorizontalAlignment(SwingConstants.CENTER);
		Btnstock.setFont(new Font("Tahoma", Font.BOLD, 13));

		JLabel Btnordenes = new JLabel("Órdenes de reparación");
		Btnordenes.setForeground(new Color(255, 255, 255));
		Btnordenes.setBounds(23, 100, 154, 28);
		Btnordenes.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				Btnordenes.setForeground(Color.BLACK);
			}

			public void mouseExited(MouseEvent e) {
				Btnordenes.setForeground(Color.WHITE);
			}

			public void mouseClicked(MouseEvent e) {
				cardLayout.show(panel, "panelOrdenes");
			}

		});
		barra.add(Btnordenes);
		Btnordenes.setHorizontalAlignment(SwingConstants.CENTER);
		Btnordenes.setFont(new Font("Tahoma", Font.BOLD, 13));

		JLabel lblTitulo = new JLabel("GESTIÓN");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setBounds(37, 29, 126, 24);
		barra.add(lblTitulo);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(
				new ImageIcon(JFrameMain_Mecanico.class.getResource("/package_assets/derrapchicodefinitovo.png")));
		lblNewLabel_4.setBounds(23, 465, 155, 163);
		barra.add(lblNewLabel_4);

		btnLogout = new JButton("Cerrar Sesión");
		btnLogout.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnLogout.setBounds(37, 651, 126, 23);
		barra.add(btnLogout);
		btnLogout.setForeground(new Color(0, 0, 0));
		btnLogout.setBackground(new Color(255, 255, 255));
		btnLogout.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnLogout.setBackground(Color.BLACK);
				btnLogout.setForeground(Color.WHITE);
			}

			public void mouseExited(MouseEvent e) {
				btnLogout.setBackground(Color.WHITE);
				btnLogout.setForeground(Color.BLACK);
			}
		});
		btnLogout.addActionListener(this);
		btnLogout.setFocusable(false);

		JLabel btnListaVehiculos = new JLabel("Lista de vehículos");
		btnListaVehiculos.setHorizontalAlignment(SwingConstants.CENTER);
		btnListaVehiculos.setForeground(Color.WHITE);
		btnListaVehiculos.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnListaVehiculos.setBounds(23, 234, 154, 28);
		btnListaVehiculos.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnListaVehiculos.setForeground(Color.BLACK);
			}

			public void mouseExited(MouseEvent e) {
				btnListaVehiculos.setForeground(Color.WHITE);
			}

			public void mouseClicked(MouseEvent e) {
				cardLayout.show(panel, "PanelVehiculos");
			}
		});
		barra.add(btnListaVehiculos);

		panel = new JPanel();
		panel.setBounds(233, 11, 840, 674);
		contentPane.add(panel);
		panel.setLayout(cardLayout);

		panelOrdenes = new JPanel();
		panelOrdenes.setBackground(new Color(255, 255, 255));
		panel.add(panelOrdenes, "panelOrdenes");
		panelOrdenes.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 412, 691, 206);
		panelOrdenes.add(scrollPane);

		tblTablaOrdenes = new JTable();
		modelTablaOrdenes = new DefaultTableModel(columnasOrdenes,0);
		tblTablaOrdenes.setModel(modelTablaOrdenes);
		tblTablaOrdenes.getSelectionModel().addListSelectionListener(this);
		scrollPane.setViewportView(tblTablaOrdenes);

		orden1 = new JPanel();
		orden1.setBorder(new LineBorder(new Color(0, 0, 0)));
		orden1.setBackground(new Color(102, 153, 204));
		orden1.setBounds(70, 59, 184, 295);
		panelOrdenes.add(orden1);
		orden1.setLayout(null);
		
		lblSinOrdenAsignada1 = new JLabel("SIN ORDEN ASIGNADA");
		lblSinOrdenAsignada1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSinOrdenAsignada1.setForeground(new Color(255, 255, 255));
		lblSinOrdenAsignada1.setBounds(7, 105, 174, 72);
		orden1.add(lblSinOrdenAsignada1);
		
		orden3 = new JPanel();
		orden3.setBorder(new LineBorder(new Color(0, 0, 0)));
		orden3.setLayout(null);
		orden3.setBackground(new Color(102, 153, 204));
		orden3.setBounds(577, 59, 184, 295);
		panelOrdenes.add(orden3);
		
		lblSinOrdenAsignada3 = new JLabel("SIN ORDEN ASIGNADA");
		lblSinOrdenAsignada3.setForeground(Color.WHITE);
		lblSinOrdenAsignada3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSinOrdenAsignada3.setBounds(7, 105, 174, 72);
		orden3.add(lblSinOrdenAsignada3);

		orden2 = new JPanel();
		orden2.setBorder(new LineBorder(new Color(0, 0, 0)));
		orden2.setLayout(null);
		orden2.setBackground(new Color(102, 153, 204));
		orden2.setBounds(322, 59, 184, 295);
		panelOrdenes.add(orden2);
		
		lblSinOrdenAsignada2 = new JLabel("SIN ORDEN ASIGNADA");
		lblSinOrdenAsignada2.setForeground(Color.WHITE);
		lblSinOrdenAsignada2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSinOrdenAsignada2.setBounds(7, 105, 174, 72);
		orden2.add(lblSinOrdenAsignada2);

		btnAsignar = new JButton("Asignar");
		btnAsignar.setEnabled(false);
		btnAsignar.setBackground(new Color(102, 153, 204));
		btnAsignar.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnAsignar.setForeground(new Color(255, 255, 255));
		btnAsignar.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAsignar.setBounds(322, 629, 184, 34);
		btnAsignar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnAsignar.setBackground(Color.BLACK);
			}

			public void mouseExited(MouseEvent e) {
				btnAsignar.setBackground(new Color(102, 153, 204));
				;
			}
		});

		btnAsignar.addActionListener(this);
		panelOrdenes.add(btnAsignar);

		JPanel PanelStock = new JPanel();
		PanelStock.setBackground(new Color(255, 255, 255));
		panel.add(PanelStock, "PanelStock");
		PanelStock.setLayout(null);

		JLabel lblRepuestosDisponibles = new JLabel("Repuestos disponibles:");
		lblRepuestosDisponibles.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblRepuestosDisponibles.setBounds(55, 69, 286, 43);
		PanelStock.add(lblRepuestosDisponibles);

		JScrollPane scrollPaneTablaRepuestos = new JScrollPane();
		scrollPaneTablaRepuestos.setBounds(55, 158, 691, 394);
		PanelStock.add(scrollPaneTablaRepuestos);

		tblTablaRepuestos = new JTable();
		modeloTablaRepuestos = new DefaultTableModel(columnasRepuesto, 0); 
		tblTablaRepuestos.setModel(modeloTablaRepuestos);
		scrollPaneTablaRepuestos.setViewportView(tblTablaRepuestos);

		JPanel PanelVehiculos = new JPanel();
		panel.add(PanelVehiculos, "PanelVehiculos");
		PanelVehiculos.setLayout(null);

		setSize(1122, 735);
		setLocationRelativeTo(null);
		orden1.setEnabled(false);
		orden2.setEnabled(false);
		orden3.setEnabled(false);
		
		//Al iniciar, obtengo los datos de la tabla de repuestos y la de ordenes
		ControladorRegistros.actualizarTablas("repuesto", modeloTablaRepuestos);
		ControladorRegistros.actualizarTablas("ordenesActivas", modelTablaOrdenes);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogout) {
			logout();
		}else if(e.getSource() == btnAsignar) {
			asignarOrden();
		}
	}

	private void asignarOrden() {
		
		//Obtengo el primer panel vacio
		JPanel panelDisponible = obtenerPrimerPanelVacio();
		
		//Si hay al menos uno
		if(panelDisponible != null) {
			//Habilito el panel
			panelDisponible.setEnabled(true);
			
			
			int filaSeleccionada = tblTablaOrdenes.getSelectedRow();
			
			//Obtengo los datos de la orden
			String idOrden = (String) tblTablaOrdenes.getValueAt(filaSeleccionada, 0);
			String estadoOrden = (String) tblTablaOrdenes.getValueAt(filaSeleccionada, 1);
			String matriculaOrden = (String) tblTablaOrdenes.getValueAt(filaSeleccionada, 2);
			String dniClienteOrden = (String) tblTablaOrdenes.getValueAt(filaSeleccionada, 3);
			
			//Borro de la tabla la orden seleccionada 
			
			modelTablaOrdenes.removeRow(filaSeleccionada);
			
			//y le cambio el estado a "En proceso"
			try {
				Conector_BBDD conexion = new Conector_BBDD();
				conexion.conectar();
				conexion.ejecutarInsertDeleteUpdate("UPDATE orden SET estado = 'En proceso' WHERE id_orden = '" + idOrden + "'");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//Relleno el panel
			//Guardo los textos que tendran los label
			String[] textos = {"Cliente: " + dniClienteOrden, "ID: " + idOrden, "Matrícula: " + matriculaOrden};
			
			//La posicion en el eje Y que tendran los label
	        int[] posicionesY = {59, 24, 97}; 

	        //Hago 3 labels y un boton y los agrego
	        for (int i = 0; i < textos.length; i++) {
	            JLabel label = new JLabel(textos[i]);
	            label.setForeground(Color.WHITE);
	            label.setFont(new Font("Tahoma", Font.BOLD, 12));
	            label.setBounds(10, posicionesY[i], 136, 14);
	            panelDisponible.add(label);
	            
	            
	            //Creo el boton
	            JButton boton = new JButton("Abrir orden");
	            boton.setBounds(35, 220, 111, 53);
	            boton.setFont(new Font("Tahoma", Font.BOLD, 12));
	            boton.setBorder(new LineBorder(new Color(0, 0, 0)));
	            boton.setBackground(new Color(255, 255, 255));
	            boton.setFocusable(false);
	            //Agrego el action listener desde aca
	            boton.addActionListener(new ActionListener() {
					
	            	//Cuando hace click abre la ventana de la orden
					@Override
					public void actionPerformed(ActionEvent e) {
						//Creo la nueva ventana y le paso la orden con los datos
						//Tambien le paso el panel para despues poder volver a cerrarlo
						VtnAbrirOrden VtnAbrirOrden = new VtnAbrirOrden(idOrden, panelDisponible);
						VtnAbrirOrden.setVisible(true);
					}
				});
	            
	            //Agrego el hover
	            boton.addMouseListener(new MouseAdapter() {
	    			public void mouseEntered(MouseEvent e) {
	    				boton.setBackground(Color.BLACK);
	    				boton.setForeground(Color.WHITE);
	    			}

	    			public void mouseExited(MouseEvent e) {
	    				boton.setBackground(Color.WHITE);
	    				boton.setForeground(Color.BLACK);
	    			}
	    		});
	            panelDisponible.add(boton);
	        }
	        
	        
	        
	        
	        //Esto sirve para refrescar el panel y que cargue los labels
	        panelDisponible.revalidate(); 
	        panelDisponible.repaint();
			
		}else {
			JOptionPane.showMessageDialog(this, "Ya hay 3 ordenes asignadas", "Limite de ordenes", JOptionPane.WARNING_MESSAGE);
		}
		
		
	}
	


	private JPanel obtenerPrimerPanelVacio() {
		//Obtengo el primer panel que este vacio (deshabilitado), sino devuelve null
		//Tambien oculta el label de "orden sin asignar"
        if (!orden1.isEnabled()) {
        	orden1.removeAll();
        	orden1.revalidate();
        	orden1.repaint();
        	return orden1;
        }
        else if (!orden2.isEnabled()) {
        	orden2.removeAll();
        	orden2.revalidate();
        	orden2.repaint();
        	return orden2;
        }
        else if (!orden3.isEnabled()) {
        	orden3.removeAll();
        	orden3.revalidate();
        	orden3.repaint();
        	return orden3;
        }else {
        	return null;
        }
        
    }

	public void logout() {

		// Pide confirmacion para cerrar sesion
		int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar cerrar sesión?",
				"Confirmar cerrar sesión", JOptionPane.YES_NO_OPTION);

		if (confirmacion == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(this, "Cerrando Sesión...", "Información", JOptionPane.INFORMATION_MESSAGE);
			JFrameLogin JFLogin = new JFrameLogin();
			JFLogin.setVisible(true);
			dispose();
		}
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		//Verifica si hay una fila seleccionada y habilita o deshabilita el boton de asignar orden
		if(tblTablaOrdenes.getSelectedRow() != -1) {
			btnAsignar.setEnabled(true);
		}else {
			btnAsignar.setEnabled(false);
		}

	}
	 
	public static void sacarTarjetaOrden(JPanel panel) {
		//Recibo el panel de la orden y lo reinicio
		panel.setEnabled(false);
		panel.removeAll();
		
		//Le agrego el label de "sin orden asignada"
		JLabel lblSinOrdenAsignada = new JLabel("SIN ORDEN ASIGNADA");
		lblSinOrdenAsignada.setForeground(Color.WHITE);
		lblSinOrdenAsignada.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSinOrdenAsignada.setBounds(7, 105, 174, 72);
		panel.add(lblSinOrdenAsignada);
				
		
		panel.revalidate();
		panel.repaint();
		
		
		//Actualizo la tabla de ordenes
		ControladorRegistros.actualizarTablas("ordenesActivas", modelTablaOrdenes);
	}
}
