package Vista;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Controlador.Conector_BBDD;
import package_main.Background;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;

public class JFrameMain_Admin extends JFrame implements ActionListener, ListSelectionListener {
    Conector_BBDD conexion = new Conector_BBDD();
    Background fondoPantalla = new Background();
    Connection cn = null;
    Statement stm = null;
    ResultSet rsetresultado = null;
    VtnCrearNuevoRegistro vtnCrearNuevoRegistro;
    VtnActualizarRegistro vtnActualizarRegistro;
    
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnActualizarRegistro, btnCrearRegistro, btnLogout, btnImprimir, btnClientes, btnProveedores, btnMecanicos, btnVehiculos, btnCrearProveedores, btnBorrarRegistro;
    private JPanel jpClientes, jpMaterial, jpServicios, jpEconomia;
    private JTextField txtBuscadorClientes, txtBuscadorMecanicos, txtBuscadorProveedor;
    private JTable tblTablaClientes, tblTablaVehiculos, tblTablaMecanicos, tblTablaProveedores;
    private JTextField txtBuscadorVehiculos;
    private JScrollPane scrollPaneClientes, scrollPaneVehiculos, scrollPaneMecanicos, scrollPaneProveedores;
    private JPanel searchPanelClientes, searchPanelVehiculos, searchPanelMecanicos, searchPanelProveedores;
    private DefaultTableModel modelTablaClientes, modelTablaMecanicos, modelTablaVehiculos;
    
    //Valores de las columnas de cada tabla
    String[] columnasCliente = {"DNI","Nombre", "Apellidos", "Telefono"};
    String[] columnasMecanico = {"DNI", "Nombre", "Apellidos", "Contraseña", "Estado"};
    String[] columnasVehiculos = {"Matricula", "Marca", "Modelo", "Color", "Combustible", "Kilometros", "Año", "DNI cliente"};

    String grupo = "clientes";
    
    // Llamada principal para ejecutar la aplicación
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

    // Constructor de la clase que crea el JFrame
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

        setSize(1122, 735);
        setLocationRelativeTo(null);
        
        jpClientes = new JPanel();
        jpClientes.setLayout(null);
        
        jpMaterial = new JPanel();
        jpMaterial.setLayout(null);
        
        jpServicios = new JPanel();
        jpServicios.setLayout(null);
        
        jpEconomia = new JPanel();
        jpEconomia.setLayout(null);
        
        JTabbedPane jtpmenuPrincipal = new JTabbedPane();
        jtpmenuPrincipal.setBounds(10, 26, 1088, 662);
        jtpmenuPrincipal.setFont(new Font("Tahoma", Font.BOLD, 15));
        
        jtpmenuPrincipal.add("Clientes", jpClientes);
        jtpmenuPrincipal.add("Material", jpMaterial);
        jtpmenuPrincipal.add("Servicios", jpServicios);
        jtpmenuPrincipal.add("Economía", jpEconomia);
        
        getContentPane().add(jtpmenuPrincipal);
        

        
        //BOTON MECANICOS
        btnMecanicos = new JButton("MECÁNICOS");
        btnMecanicos.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnMecanicos.setBounds(21, 323, 150, 30);
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
        btnProveedores.setBounds(21, 428, 150, 30);
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
        
        txtBuscadorClientes.addKeyListener(new KeyAdapter() {
        	@Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	buscarRegistros("clientes");
                }
            }
		});

        // Crear un panel para el JTextField con el ícono
        searchPanelClientes = new JPanel(new BorderLayout());
        searchPanelClientes.setBounds(469, 26, 497, 24);
        searchPanelClientes.add(txtBuscadorClientes, BorderLayout.CENTER);

        // Agregar el searchPanel en lugar de txtBuscadorChoches directamente
        jpClientes.add(searchPanelClientes);

        //TABLA CLIENTES
        tblTablaClientes = new JTable();
        tblTablaClientes.setDefaultEditor(Object.class, null);
        tblTablaClientes.getSelectionModel().addListSelectionListener(this);
        modelTablaClientes = new DefaultTableModel(columnasCliente,0);
        tblTablaClientes.setModel(modelTablaClientes);
        
        scrollPaneClientes = new JScrollPane(tblTablaClientes); 
        scrollPaneClientes.setBounds(469, 61, 497, 153); 
        jpClientes.add(scrollPaneClientes);
        
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
        btnImprimir.setBounds(10, 506, 150, 45); // Ajusta la posición y tamaño
        jpClientes.add(btnImprimir);

        
        txtBuscadorVehiculos = new JTextField();
        txtBuscadorVehiculos.setColumns(10); // Ajusta el ancho
        txtBuscadorVehiculos.setText("Buscar vehiculos..."); // Placeholder
        txtBuscadorVehiculos.setForeground(Color.GRAY);
        
        String hint2 = "Buscar vehiculos...";
        txtBuscadorVehiculos.setText(hint2);
        txtBuscadorVehiculos.setForeground(Color.GRAY);
        txtBuscadorVehiculos.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtBuscadorVehiculos.getText().equals(hint2)) {
                	txtBuscadorVehiculos.setText("");
                	txtBuscadorVehiculos.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtBuscadorVehiculos.getText().isEmpty()) {
                	txtBuscadorVehiculos.setText(hint2);
                	txtBuscadorVehiculos.setForeground(Color.GRAY);
                }
            }
        });
        
        txtBuscadorVehiculos.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	buscarRegistros("vehiculos");
                }
            }
        });


        // Crear un panel para el JTextField con el ícono
        searchPanelVehiculos = new JPanel(new BorderLayout());
        searchPanelVehiculos.setBounds(469, 26, 497, 24);
        searchPanelVehiculos.add(txtBuscadorVehiculos, BorderLayout.CENTER);

        // Agregar el searchPanel en lugar de txtBuscadorChoches directamente
        jpClientes.add(searchPanelVehiculos);

        //TABLA VEHICULOS
        tblTablaVehiculos = new JTable();
        tblTablaVehiculos.setDefaultEditor(Object.class, null);
        tblTablaVehiculos.getSelectionModel().addListSelectionListener(this);
        modelTablaVehiculos = new DefaultTableModel(columnasVehiculos,0);
        tblTablaVehiculos.setModel(modelTablaVehiculos);
        
        scrollPaneVehiculos = new JScrollPane(tblTablaVehiculos); 
        scrollPaneVehiculos.setBounds(469, 61, 497, 153);
        jpClientes.add(scrollPaneVehiculos);
        
        
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
        
        txtBuscadorMecanicos.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	buscarRegistros("mecanicos");
                }
            }
        });
        
        searchPanelMecanicos = new JPanel(new BorderLayout());
        searchPanelMecanicos.setBounds(469, 26, 497, 24);
        searchPanelMecanicos.add(txtBuscadorMecanicos, BorderLayout.CENTER);

        // Agregar el searchPanel en lugar de txtBuscadorChoches directamente
        jpClientes.add(searchPanelMecanicos);

        
        //TABLA MECANICOS
        tblTablaMecanicos = new JTable();
        tblTablaMecanicos.setDefaultEditor(Object.class, null);
        tblTablaMecanicos.getSelectionModel().addListSelectionListener(this);
        
	    modelTablaMecanicos = new DefaultTableModel(columnasMecanico, 0); 
	    tblTablaMecanicos.setModel(modelTablaMecanicos);
	    
        scrollPaneMecanicos = new JScrollPane(tblTablaMecanicos);
        scrollPaneMecanicos.setBounds(469, 61, 497, 153);
        jpClientes.add(scrollPaneMecanicos);
        
        
        btnCrearRegistro = new JButton("Añadir");
        btnCrearRegistro.addActionListener(this);
        btnCrearRegistro.setBackground(new Color(170, 255, 0));
        btnCrearRegistro.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnCrearRegistro.setBounds(469, 226, 497, 35);
        jpClientes.add(btnCrearRegistro);
        
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

        // Crear un panel para el JTextField con el ícono
        searchPanelProveedores = new JPanel(new BorderLayout());
        searchPanelProveedores.setBounds(469, 26, 497, 24);
        searchPanelProveedores.add(txtBuscadorProveedor, BorderLayout.CENTER);

        // Agregar el searchPanel en lugar de txtBuscadorChoches directamente
        jpClientes.add(searchPanelProveedores);

        
        tblTablaProveedores = new JTable();
        tblTablaProveedores.setDefaultEditor(Object.class, null);
        tblTablaProveedores.getSelectionModel().addListSelectionListener(this);
        scrollPaneProveedores = new JScrollPane(tblTablaProveedores); 
        scrollPaneProveedores.setBounds(469, 61, 497, 153); 
        jpClientes.add(scrollPaneProveedores);

        
        btnCrearProveedores = new JButton("Añadir/Editar");
        btnCrearProveedores.addActionListener(this);
        btnCrearProveedores.setBackground(new Color(170, 255, 0));
        btnCrearProveedores.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnCrearProveedores.setBounds(469, 226, 497, 35);
        jpClientes.add(btnCrearProveedores);
        
        btnBorrarRegistro = new JButton("Borrar");
        btnBorrarRegistro.setBounds(976, 231, 89, 23);
        btnBorrarRegistro.setEnabled(false);
        btnBorrarRegistro.addActionListener(this);
        jpClientes.add(btnBorrarRegistro);
        
        btnActualizarRegistro = new JButton("Editar");
        btnActualizarRegistro.setBounds(976, 199, 89, 23);
        jpClientes.add(btnActualizarRegistro);
        btnActualizarRegistro.addActionListener(this);
        btnActualizarRegistro.setEnabled(false);
        
        btnVehiculos = new JButton("VEHÍCULOS");
        btnVehiculos.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnVehiculos.setBounds(21, 210, 150, 30);
        btnVehiculos.addActionListener(this);
        jpClientes.add(btnVehiculos);
        
        
        btnLogout = new JButton("Cerrar Sesión");
        btnLogout.addActionListener(this);
        btnLogout.setBounds(989, 2, 119, 23);
        fondoPantalla.add(btnLogout);
        
        //Al iniciar
        actualizarVisibilidad(grupo);
        actualizarTablas(grupo);
	}
	
	private void actualizarVisibilidad(String grupo) {
	    
	    txtBuscadorClientes.setVisible(false);
	    tblTablaClientes.setVisible(false);
	    txtBuscadorVehiculos.setVisible(false);
	    tblTablaVehiculos.setVisible(false);
	    scrollPaneClientes.setVisible(false);
	    scrollPaneVehiculos.setVisible(false);
	    searchPanelClientes.setVisible(false);
	    searchPanelVehiculos.setVisible(false);

	    txtBuscadorMecanicos.setVisible(false);
	    tblTablaMecanicos.setVisible(false);
	    btnCrearRegistro.setVisible(false);
	    scrollPaneMecanicos.setVisible(false);
	    searchPanelMecanicos.setVisible(false);

	    txtBuscadorProveedor.setVisible(false);
	    tblTablaProveedores.setVisible(false);
	    btnCrearProveedores.setVisible(false);
	    scrollPaneProveedores.setVisible(false);
	    searchPanelProveedores.setVisible(false);	
	    btnBorrarRegistro.setVisible(false);
	    btnActualizarRegistro.setVisible(false);
	    
	    //Limpiar las tablas
	    tblTablaClientes.clearSelection();
	    tblTablaMecanicos.clearSelection();
	    tblTablaVehiculos.clearSelection();
	    
	    switch(grupo) {
	        case "clientes":
	            txtBuscadorClientes.setVisible(true);
	            tblTablaClientes.setVisible(true);
	            txtBuscadorVehiculos.setVisible(true);
	            tblTablaVehiculos.setVisible(true);
	            scrollPaneClientes.setVisible(true);
	            scrollPaneVehiculos.setVisible(true);
	            searchPanelClientes.setVisible(true);
	            searchPanelVehiculos.setVisible(true);
	            btnCrearRegistro.setVisible(true);
	            btnBorrarRegistro.setVisible(true);
	            btnActualizarRegistro.setVisible(true);
	            
	            break;
	        case "mecanicos":
	            txtBuscadorMecanicos.setVisible(true);
	            tblTablaMecanicos.setVisible(true);
	            btnCrearRegistro.setVisible(true);
	            scrollPaneMecanicos.setVisible(true);
	            searchPanelMecanicos.setVisible(true);
	            btnBorrarRegistro.setVisible(true);
	            btnActualizarRegistro.setVisible(true);
	            
	            break;
	        case "vehiculos":
	        	txtBuscadorVehiculos.setVisible(true);
	        	tblTablaVehiculos.setVisible(true);
	        	btnCrearRegistro.setVisible(true);
	        	scrollPaneVehiculos.setVisible(true);
	        	searchPanelVehiculos.setVisible(true);
	        	btnBorrarRegistro.setVisible(true);
	        	btnActualizarRegistro.setVisible(true);
	        	break;
	        case "proveedores":
	            txtBuscadorProveedor.setVisible(true);
	            tblTablaProveedores.setVisible(true);
	            btnCrearProveedores.setVisible(true);
	            scrollPaneProveedores.setVisible(true);
	            searchPanelProveedores.setVisible(true);
	            
	            break;
	    }
	}
	
	public void actionPerformed(ActionEvent e) {
     	if(e.getSource() == btnLogout) {
    		logout();
    	} else if(e.getSource() == btnImprimir) {
    		System.out.println("Imprimiendo");
    	} else if(e.getSource() == btnClientes) {
    		grupo = "clientes";
    		actualizarVisibilidad("clientes");
    		actualizarTablas("clientes");
    	} else if (e.getSource() == btnMecanicos) {
    		grupo = "mecanicos";
    		actualizarVisibilidad("mecanicos");
    		actualizarTablas("mecanicos");
    	} else if (e.getSource() == btnVehiculos) {
    		grupo = "vehiculos";
    		actualizarVisibilidad("vehiculos");
    		actualizarTablas("vehiculos");
    	} else if (e.getSource() == btnProveedores) {
    		grupo = "proveedores";
    		actualizarVisibilidad("proveedores");
    		actualizarTablas("proveedores");
    	} else if (e.getSource() == btnCrearRegistro) {
    		//Pasa el grupo actual a la ventana de crear nuevo registro
    		vtnCrearNuevoRegistro = new VtnCrearNuevoRegistro(grupo);
    		vtnCrearNuevoRegistro.setVisible(true);
    	} else if(e.getSource() == btnBorrarRegistro) {
    		borrarRegistro(grupo);
    	} else if(e.getSource() == btnActualizarRegistro) {
    		actualizarRegistro(grupo);
    	}
	 }
	

	public void logout() {
		 JOptionPane.showMessageDialog(this, "Cerrando Sesión...", "Información", JOptionPane.INFORMATION_MESSAGE);
		 JFrameLogin JFLogin = new JFrameLogin();
		 JFLogin.setVisible(true);
         dispose();
	 }
	 
	private void buscarRegistros(String grupo) {
		
		try {
			conexion.conectar();
			switch (grupo) {
				case "mecanicos":
					String nombreMecanico = txtBuscadorMecanicos.getText().trim();
					// Consulta SQL para buscar mecánicos por nombre
				    String selectMecanicos = "SELECT * FROM usuario WHERE rol = 'Mecanico' AND nombre LIKE '%" + nombreMecanico + "%'";
				    ResultSet rsetMecanicos = conexion.ejecutarSelect(selectMecanicos);
				    modelTablaMecanicos.setRowCount(0);
				    
				    // Procesar los resultados
			        while (rsetMecanicos.next()) {
			            // Obtener cada campo del ResultSet y añadir la fila a la tabla
			            Object[] fila = new Object[5];
			            fila[0] = rsetMecanicos.getString("DNI");
			            fila[1] = rsetMecanicos.getString("nombre");
			            fila[2] = rsetMecanicos.getString("apellidos");
			            fila[3] = rsetMecanicos.getString("contrasenia");
			            fila[4] = rsetMecanicos.getString("estado");
			            modelTablaMecanicos.addRow(fila); // Agregar fila al modelo de la tabla
			        }
			        
					break;
				case "clientes":
					String nombreCliente = txtBuscadorClientes.getText().trim();
					String selectClientes = "SELECT * FROM cliente WHERE nombre LIKE '%" + nombreCliente + "%'";
					ResultSet rsetClientes = conexion.ejecutarSelect(selectClientes);
					modelTablaClientes.setRowCount(0);
					
					while (rsetClientes.next()) {
			            Object[] fila = new Object[4];
			            fila[0] = rsetClientes.getString("DNI");
			            fila[1] = rsetClientes.getString("nombre");
			            fila[2] = rsetClientes.getString("apellidos");
			            fila[3] = rsetClientes.getString("telefono");
			            modelTablaClientes.addRow(fila);
			        }
					
					break;
				case "vehiculos":
					String matriculaVehiculo = txtBuscadorVehiculos.getText().trim();
					String selectVehiculos = "SELECT * FROM vehiculo WHERE matricula LIKE '%" + matriculaVehiculo + "%'";
					ResultSet rsetVehiculos = conexion.ejecutarSelect(selectVehiculos);
					modelTablaVehiculos.setRowCount(0);
					
					while (rsetVehiculos.next()) {
			            Object[] fila = new Object[8];
			            fila[0] = rsetVehiculos.getString("matricula");
			            fila[1] = rsetVehiculos.getString("marca");
			            fila[2] = rsetVehiculos.getString("modelo");
			            fila[3] = rsetVehiculos.getString("color");
			            fila[4] = rsetVehiculos.getString("combustible");
			            fila[5] = rsetVehiculos.getString("kilometros");
			            fila[6] = rsetVehiculos.getString("anio");
			            fila[7] = rsetVehiculos.getString("Cliente_DNI");
			            modelTablaVehiculos.addRow(fila);
			        }
					
					break;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al buscar mecánicos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} 
	}
	
	 
	private void actualizarTablas(String grupo) {
		
		conexion.conectar();
		try {
			switch (grupo) {
				case "clientes":
					modelTablaClientes.setRowCount(0);
					ResultSet rsetCliente = conexion.ejecutarSelect("SELECT * FROM cliente");
					while (rsetCliente.next()) {
						// Obtener cada campo del ResultSet y añadir la fila a la tabla
		                Object[] fila = new Object[4];
		                fila[0] = rsetCliente.getString("DNI");
		                fila[1] = rsetCliente.getString("nombre");
		                fila[2] = rsetCliente.getString("apellidos");
		                fila[3] = rsetCliente.getString("telefono");
		                
		                modelTablaClientes.addRow(fila); // Agregar fila al modelo de la tabla
					}
					break;
				case "mecanicos":
					modelTablaMecanicos.setRowCount(0);
					ResultSet rsetMecanico = conexion.ejecutarSelect("SELECT * FROM usuario where rol = 'Mecanico' ");
					while (rsetMecanico.next()) {
			                
			                Object[] fila = new Object[5];
			                fila[0] = rsetMecanico.getString("DNI");       
			                fila[1] = rsetMecanico.getString("nombre");    
			                fila[2] = rsetMecanico.getString("apellidos");          
			                fila[3] = rsetMecanico.getString("contrasenia");           
			                fila[4] = rsetMecanico.getString("estado");       
			                
			                modelTablaMecanicos.addRow(fila); 
			        }
					break;
				case "vehiculos":
					modelTablaVehiculos.setRowCount(0);
					ResultSet rsetVehiculos = conexion.ejecutarSelect("SELECT * FROM vehiculo");
					while (rsetVehiculos.next()) {
		                Object[] fila = new Object[8];
		                fila[0] = rsetVehiculos.getString("matricula");       
		                fila[1] = rsetVehiculos.getString("marca");    
		                fila[2] = rsetVehiculos.getString("modelo");          
		                fila[3] = rsetVehiculos.getString("color");           
		                fila[4] = rsetVehiculos.getString("combustible");     
		                fila[5] = rsetVehiculos.getString("kilometros");           
		                fila[6] = rsetVehiculos.getString("anio");  
		                fila[7] = rsetVehiculos.getString("Cliente_DNI");             
		                
		                modelTablaVehiculos.addRow(fila); 
					}
					break;
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	 
	private void borrarRegistro(String grupo) {
		DefaultTableModel modelTablaSeleccionada=new DefaultTableModel();
		int registroSeleccionado=0;
		String sqlBorrar="";
		
		int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar esta fila?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
		switch(grupo) {
			case "clientes":
				registroSeleccionado = tblTablaClientes.getSelectedRow();
				Object dniCliente = tblTablaClientes.getValueAt(registroSeleccionado, 0);
				modelTablaSeleccionada=modelTablaClientes;
				sqlBorrar="DELETE FROM cliente WHERE DNI = '" + dniCliente +"'";
				break;
			case "mecanicos":
				registroSeleccionado = tblTablaMecanicos.getSelectedRow();
				Object dniMecanico = tblTablaMecanicos.getValueAt(registroSeleccionado, 0);
				modelTablaSeleccionada=modelTablaMecanicos;
				sqlBorrar="DELETE FROM usuario WHERE DNI = '" + dniMecanico +"'";
				break;
			case "vehiculos":
				registroSeleccionado = tblTablaVehiculos.getSelectedRow();
				Object matriculaVehiculo = tblTablaVehiculos.getValueAt(registroSeleccionado, 0);
				modelTablaSeleccionada=modelTablaVehiculos;
				sqlBorrar="DELETE FROM vehiculo WHERE matricula = '" + matriculaVehiculo+ "'";
				break;
		}
		
		if(confirmacion == JOptionPane.YES_OPTION) {
			modelTablaSeleccionada.removeRow(registroSeleccionado);
			
			try {
				conexion.conectar();
				int filasAfectadas = conexion.ejecutarInsertDeleteUpdate(sqlBorrar);
				
				if(filasAfectadas == 0) {
					JOptionPane.showMessageDialog(this, "Error al borrar el registro", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(this, "Registro borrado ", "Borrado exitoso", JOptionPane.INFORMATION_MESSAGE);
					btnActualizarRegistro.setEnabled(false);
				}
				
			} catch (Exception e) {
				System.out.println(e);
			}
			
		}
		
	} 
	
	 
	private void actualizarRegistro(String grupo) {

		switch (grupo) {
			case "clientes":
				int clienteSeleccionado = tblTablaClientes.getSelectedRow();
				//Obtengo todos los valores del registro seleccionado
				String dniCliente = (String) tblTablaClientes.getValueAt(clienteSeleccionado, 0);
			    String nombreCliente = (String) tblTablaClientes.getValueAt(clienteSeleccionado, 1);
			    String apellidosCliente = (String) tblTablaClientes.getValueAt(clienteSeleccionado, 2);
			    String telefono = (String) tblTablaClientes.getValueAt(clienteSeleccionado, 3);
			    String[] valoresActualesCliente = {dniCliente, nombreCliente, apellidosCliente, telefono};
			    
			    vtnActualizarRegistro = new VtnActualizarRegistro(columnasCliente, valoresActualesCliente, grupo);
				break;
			case "mecanicos":
				int mecanicoSeleccionado = tblTablaMecanicos.getSelectedRow();
				//Obtengo todos los valores del registro seleccionado
				String dniUsuario = (String) tblTablaMecanicos.getValueAt(mecanicoSeleccionado, 0);
			    String nombre = (String) tblTablaMecanicos.getValueAt(mecanicoSeleccionado, 1);
			    String apellidos = (String) tblTablaMecanicos.getValueAt(mecanicoSeleccionado, 2);
			    String contrasena = (String) tblTablaMecanicos.getValueAt(mecanicoSeleccionado, 3);
			    String estado = (String) tblTablaMecanicos.getValueAt(mecanicoSeleccionado, 4);  
			    String[] valoresActualesMecanico = {dniUsuario, nombre, apellidos, contrasena, estado};
			    
			    vtnActualizarRegistro = new VtnActualizarRegistro(columnasMecanico, valoresActualesMecanico, grupo);
				break;
			case "vehiculos":
				int vehiculoSeleccionado = tblTablaVehiculos.getSelectedRow();
				//Obtengo todos los valores del registro seleccionado
				String matricula = (String) tblTablaVehiculos.getValueAt(vehiculoSeleccionado, 0);
			    String marca = (String) tblTablaVehiculos.getValueAt(vehiculoSeleccionado, 1);
			    String modelo = (String) tblTablaVehiculos.getValueAt(vehiculoSeleccionado, 2);
			    String color = (String) tblTablaVehiculos.getValueAt(vehiculoSeleccionado, 3);
			    String combustible = (String) tblTablaVehiculos.getValueAt(vehiculoSeleccionado, 4);  
			    String kilometros = (String) tblTablaVehiculos.getValueAt(vehiculoSeleccionado, 5);
			    String anio = (String) tblTablaVehiculos.getValueAt(vehiculoSeleccionado, 6);
			    String cliente_dni = (String) tblTablaVehiculos.getValueAt(vehiculoSeleccionado, 7);
			    
			    String[] valoresActualesVehiculos = {matricula, marca, modelo, color, combustible,kilometros, anio, cliente_dni};
			    
			    vtnActualizarRegistro = new VtnActualizarRegistro(columnasVehiculos, valoresActualesVehiculos, grupo);
				break;
		}
	
		vtnActualizarRegistro.setVisible(true);
			
	}

	//Detecta el cambio en las tablas
	@Override
	public void valueChanged(ListSelectionEvent e) {
		//Verifica si hay una fila seleccionada y habilita o deshabilita el boton de borrar mecanico
		if(tblTablaClientes.getSelectedRow() != -1 || tblTablaMecanicos.getSelectedRow() != -1 || tblTablaVehiculos.getSelectedRow() != -1) {
			btnBorrarRegistro.setEnabled(true);
			btnActualizarRegistro.setEnabled(true);
		}else {
            btnBorrarRegistro.setEnabled(false);
            btnActualizarRegistro.setEnabled(false);
		}

	}
}