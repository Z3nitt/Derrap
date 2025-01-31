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
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Controlador.Conector_BBDD;
import Controlador.ControladorRegistros;
import package_main.Background;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    private JButton btnActualizarRegistro, btnCrearRegistro, btnLogout, btnImprimir, btnClientes, btnOrdenes, btnMecanicos, btnVehiculos, btnBorrarRegistro;
    private JPanel jpClientes, jpMaterial, jpServicios, jpEconomia;
    private JTextField txtBuscadorClientes, txtBuscadorMecanicos, txtBuscadorOrden;
    private JTable tblTablaClientes, tblTablaVehiculos, tblTablaMecanicos, tblTablaOrdenes;
    private JTextField txtBuscadorVehiculos;
    private JScrollPane scrollPaneClientes, scrollPaneVehiculos, scrollPaneMecanicos, scrollPaneOrdenes;
    private JPanel searchPanelClientes, searchPanelVehiculos, searchPanelMecanicos, searchPanelOrdenes;
    private DefaultTableModel modelTablaClientes, modelTablaMecanicos, modelTablaVehiculos, modelTablaOrdenes, modelTabla;
    
    //Valores de las columnas de cada tabla
    String[] columnasCliente = {"DNI","Nombre", "Apellidos", "Telefono"};
    String[] columnasMecanico = {"DNI", "Nombre", "Apellidos", "Contraseña", "Estado"};
    String[] columnasVehiculos = {"Matricula", "Marca", "Modelo", "Color", "Combustible", "Kilometros", "Año", "DNI cliente"};

    String grupo = "clientes";
    private JPanel panel;
    
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
        fondoPantalla.setBackground(new Color(102, 153, 204));
        fondoPantalla.setLayout(null);
       // setContentPane(fondoPantalla);

        setSize(1122, 706);
        setLocationRelativeTo(null);
        
        jpClientes = new JPanel();
        jpClientes.setBackground(new Color(255, 255, 255));
        jpClientes.setLayout(null);
        
        jpMaterial = new JPanel();
        jpMaterial.setLayout(null);
        
        jpServicios = new JPanel();
        jpServicios.setLayout(null);
        
        jpEconomia = new JPanel();
        jpEconomia.setLayout(null);
        
        JTabbedPane jtpmenuPrincipal = new JTabbedPane();
        jtpmenuPrincipal.setBackground(new Color(102, 153, 204));
        jtpmenuPrincipal.setBounds(10, 26, 1088, 662);
        jtpmenuPrincipal.setFont(new Font("Tahoma", Font.BOLD, 15));
        
        jtpmenuPrincipal.add("Clientes", jpClientes);
        jtpmenuPrincipal.add("Material", jpMaterial);
        jtpmenuPrincipal.add("Servicios", jpServicios);
        jtpmenuPrincipal.add("Economía", jpEconomia);
        
        getContentPane().add(jtpmenuPrincipal);

        // Configurar el hint con el FocusListener
        String hint = "Buscar clientes...";

        // Crear un panel para el JTextField con el ícono
        searchPanelClientes = new JPanel(new BorderLayout());
        searchPanelClientes.setBounds(336, 47, 616, 24);

        // Agregar el searchPanel en lugar de txtBuscadorChoches directamente
        jpClientes.add(searchPanelClientes);
        
        
    	// Crear el JTextField con ícono y hint
        txtBuscadorClientes = new JTextField();
        searchPanelClientes.add(txtBuscadorClientes, BorderLayout.CENTER);
        txtBuscadorClientes.setColumns(10); // Ajusta el ancho
        txtBuscadorClientes.setText("Buscar clientes..."); // Placeholder
        txtBuscadorClientes.setForeground(Color.GRAY);
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

        //TABLA CLIENTES
        tblTablaClientes = new JTable();
        tblTablaClientes.setDefaultEditor(Object.class, null);
        tblTablaClientes.getSelectionModel().addListSelectionListener(this);
        modelTablaClientes = new DefaultTableModel(columnasCliente,0);
        tblTablaClientes.setModel(modelTablaClientes);
        
        scrollPaneClientes = new JScrollPane(tblTablaClientes); 
        scrollPaneClientes.setBounds(336, 82, 616, 381); 
        jpClientes.add(scrollPaneClientes);

        // Coloca un ícono en el botón redondo
        Icon icon3 = new ImageIcon(getClass().getResource("/package_assets/printicon.png")); // Ruta del ícono
        
        String hint2 = "Buscar vehiculos...";


        // Crear un panel para el JTextField con el ícono
        searchPanelVehiculos = new JPanel(new BorderLayout());
        searchPanelVehiculos.setBounds(336, 47, 616, 24);

        // Agregar el searchPanel en lugar de txtBuscadorChoches directamente
        jpClientes.add(searchPanelVehiculos);
        
                
                txtBuscadorVehiculos = new JTextField();
                searchPanelVehiculos.add(txtBuscadorVehiculos, BorderLayout.CENTER);
                txtBuscadorVehiculos.setColumns(10); // Ajusta el ancho
                txtBuscadorVehiculos.setText("Buscar vehiculos..."); // Placeholder
                txtBuscadorVehiculos.setForeground(Color.GRAY);
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

        //TABLA VEHICULOS
        tblTablaVehiculos = new JTable();
        tblTablaVehiculos.setDefaultEditor(Object.class, null);
        tblTablaVehiculos.getSelectionModel().addListSelectionListener(this);
        modelTablaVehiculos = new DefaultTableModel(columnasVehiculos,0);
        tblTablaVehiculos.setModel(modelTablaVehiculos);
        
        scrollPaneVehiculos = new JScrollPane(tblTablaVehiculos); 
        scrollPaneVehiculos.setBounds(336, 82, 616, 381);
        jpClientes.add(scrollPaneVehiculos);

        // Configurar el hint con el FocusListener
        String hint3 = "Buscar mecanicos...";
        
        searchPanelMecanicos = new JPanel(new BorderLayout());
        searchPanelMecanicos.setBounds(336, 47, 616, 24);

        // Agregar el searchPanel en lugar de txtBuscadorChoches directamente
        jpClientes.add(searchPanelMecanicos);
        
        
        txtBuscadorMecanicos = new JTextField();
        searchPanelMecanicos.add(txtBuscadorMecanicos, BorderLayout.CENTER);
        txtBuscadorMecanicos.setColumns(10); // Ajusta el ancho
        txtBuscadorMecanicos.setText("Buscar mecanicos..."); // Placeholder
        txtBuscadorMecanicos.setForeground(Color.GRAY);
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

        
        //TABLA MECANICOS
        tblTablaMecanicos = new JTable();
        tblTablaMecanicos.setDefaultEditor(Object.class, null);
        tblTablaMecanicos.getSelectionModel().addListSelectionListener(this);
        
	    modelTablaMecanicos = new DefaultTableModel(columnasMecanico, 0); 
	    tblTablaMecanicos.setModel(modelTablaMecanicos);
	    
        scrollPaneMecanicos = new JScrollPane(tblTablaMecanicos);
        scrollPaneMecanicos.setBounds(336, 82, 616, 381);
        jpClientes.add(scrollPaneMecanicos);
        
        
        btnCrearRegistro = new JButton("Añadir");
        btnCrearRegistro.setBorder(new LineBorder(new Color(0, 0, 0)));
        btnCrearRegistro.setForeground(new Color(255, 255, 255));
        btnCrearRegistro.addActionListener(this);
        btnCrearRegistro.setBackground(new Color(102, 153, 204));
        btnCrearRegistro.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnCrearRegistro.setBounds(336, 472, 196, 35);
        btnCrearRegistro.setFocusable(false);
        btnCrearRegistro.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	btnCrearRegistro.setBackground(Color.BLACK);}
		    public void mouseExited(MouseEvent e) {
		    	btnCrearRegistro.setBackground(new Color(102, 153, 204));;}});
        jpClientes.add(btnCrearRegistro);

        // Configurar el hint con el FocusListener
        String hint4 = "Buscar proveedor...";

        // Crear un panel para el JTextField con el ícono
        searchPanelOrdenes = new JPanel(new BorderLayout());
        searchPanelOrdenes.setBounds(336, 47, 616, 24);

        // Agregar el searchPanel en lugar de txtBuscadorChoches directamente
        jpClientes.add(searchPanelOrdenes);
        
        txtBuscadorOrden = new JTextField();
        searchPanelOrdenes.add(txtBuscadorOrden, BorderLayout.CENTER);
        txtBuscadorOrden.setColumns(10); // Ajusta el ancho
        txtBuscadorOrden.setText("Buscar orden..."); // Placeholder
        txtBuscadorOrden.setForeground(Color.GRAY);
        txtBuscadorOrden.setText(hint4);
        txtBuscadorOrden.setForeground(Color.GRAY);
        txtBuscadorOrden.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtBuscadorOrden.getText().equals(hint4)) {
                	txtBuscadorOrden.setText("");
                	txtBuscadorOrden.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtBuscadorOrden.getText().isEmpty()) {
                	txtBuscadorOrden.setText(hint4);
                	txtBuscadorOrden.setForeground(Color.GRAY);
                }
            }
        });

        
        tblTablaOrdenes = new JTable();
        tblTablaOrdenes.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"Cliente", "ID", "Veh\u00EDculo", "Matr\u00EDcula"
        	}
        ));
        tblTablaOrdenes.setDefaultEditor(Object.class, null);
        tblTablaOrdenes.getSelectionModel().addListSelectionListener(this);
        scrollPaneOrdenes = new JScrollPane(tblTablaOrdenes); 
        scrollPaneOrdenes.setBounds(337, 82, 616, 381); 
        jpClientes.add(scrollPaneOrdenes);
        
        btnBorrarRegistro = new JButton("Borrar");
        btnBorrarRegistro.setBorder(new LineBorder(new Color(0, 0, 0)));
        btnBorrarRegistro.setForeground(new Color(255, 255, 255));
        btnBorrarRegistro.setBackground(new Color(102, 153, 204));
        btnBorrarRegistro.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnBorrarRegistro.setBounds(756, 472, 196, 35);
        btnBorrarRegistro.setEnabled(false);
        btnBorrarRegistro.addActionListener(this);
        btnBorrarRegistro.setFocusable(false);
        btnBorrarRegistro.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	btnBorrarRegistro.setBackground(Color.BLACK);}
		    public void mouseExited(MouseEvent e) {
		    	btnBorrarRegistro.setBackground(new Color(102, 153, 204));;}});
        jpClientes.add(btnBorrarRegistro);
        
        btnActualizarRegistro = new JButton("Editar");
        btnActualizarRegistro.setBorder(new LineBorder(new Color(0, 0, 0)));
        btnActualizarRegistro.setForeground(new Color(255, 255, 255));
        btnActualizarRegistro.setBackground(new Color(102, 153, 204));
        btnActualizarRegistro.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnActualizarRegistro.setBounds(542, 472, 204, 35);
        btnActualizarRegistro.setFocusable(false);
        btnActualizarRegistro.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	btnActualizarRegistro.setBackground(Color.BLACK);}
		    public void mouseExited(MouseEvent e) {
		    	btnActualizarRegistro.setBackground(new Color(102, 153, 204));;}});
        jpClientes.add(btnActualizarRegistro);
        btnActualizarRegistro.addActionListener(this);
        btnActualizarRegistro.setEnabled(false);
        
        panel = new JPanel();
        panel.setBackground(new Color(102, 153, 204));
        panel.setBounds(0, 0, 176, 634);
        jpClientes.add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel_4 = new JLabel("");
        lblNewLabel_4.setIcon(new ImageIcon(JFrameMain_Admin.class.getResource("/package_assets/derrapchicodefinitovo.png")));
        lblNewLabel_4.setBounds(10, 469, 150, 143);
        panel.add(lblNewLabel_4);
        
        btnOrdenes = new JButton("ÓRDENES  ");
        btnOrdenes.setForeground(new Color(255, 255, 255));
        btnOrdenes.setBackground(new Color(102, 153, 204));
        btnOrdenes.setBounds(0, 346, 176, 30);
        btnOrdenes.setBorderPainted(false);
        btnOrdenes.setFocusable(false);
        btnOrdenes.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	btnOrdenes.setBackground(Color.BLACK);}
		    public void mouseExited(MouseEvent e) {
		    	btnOrdenes.setBackground(new Color(102, 153, 204));;}});
        panel.add(btnOrdenes);
        btnOrdenes.addActionListener(this);
        btnOrdenes.setFont(new Font("Tahoma", Font.BOLD, 13));
        

        
        //BOTON MECANICOS
        btnMecanicos = new JButton("MECÁNICOS ");
        btnMecanicos.setForeground(new Color(255, 255, 255));
        btnMecanicos.setBackground(new Color(102, 153, 204));
        btnMecanicos.setBounds(0, 268, 176, 30);
        btnMecanicos.setBorderPainted(false);
        btnMecanicos.setFocusable(false);
        btnMecanicos.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	btnMecanicos.setBackground(Color.BLACK);}
		    public void mouseExited(MouseEvent e) {
		    	btnMecanicos.setBackground(new Color(102, 153, 204));;}});
        panel.add(btnMecanicos);
        btnMecanicos.setFont(new Font("Tahoma", Font.BOLD, 13));
        
        JLabel lblTitulo = new JLabel("GESTIÓN");
        lblTitulo.setForeground(new Color(255, 255, 255));
        lblTitulo.setBounds(0, 25, 176, 24);
        panel.add(lblTitulo);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        
        btnVehiculos = new JButton("VEHÍCULOS ");
        btnVehiculos.setForeground(new Color(255, 255, 255));
        btnVehiculos.setBackground(new Color(102, 153, 204));
        btnVehiculos.setBounds(0, 188, 176, 30);
        btnVehiculos.setBorderPainted(false);
        btnVehiculos.setFocusable(false);
        btnVehiculos.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	btnVehiculos.setBackground(Color.BLACK);}
		    public void mouseExited(MouseEvent e) {
		    	btnVehiculos.setBackground(new Color(102, 153, 204));;}});
        panel.add(btnVehiculos);
        btnVehiculos.setFont(new Font("Tahoma", Font.BOLD, 13));
        
        btnClientes = new JButton("CLIENTES ");
        btnClientes.setForeground(new Color(255, 255, 255));
        btnClientes.setBackground(new Color(102, 153, 204));
        btnClientes.setBounds(0, 109, 176, 30);
        btnClientes.setBorderPainted(false);
        btnClientes.setFocusable(false);
        btnClientes.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	btnClientes.setBackground(Color.BLACK);}
		    public void mouseExited(MouseEvent e) {
		    	btnClientes.setBackground(new Color(102, 153, 204));;}});
        panel.add(btnClientes);
        btnClientes.addActionListener(this);
        btnClientes.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnVehiculos.addActionListener(this);
        btnMecanicos.addActionListener(this);
        
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
        btnImprimir.setBounds(182, 579, 150, 45);
        jpClientes.add(btnImprimir);
        btnImprimir.setContentAreaFilled(false);
        btnImprimir.setFocusPainted(false);
        btnImprimir.setBorderPainted(false);
        btnImprimir.setOpaque(false);
        btnImprimir.addActionListener(this);
        btnImprimir.setIcon(icon3);
        btnImprimir.setBackground(new Color(255, 87, 34));
        
        
        btnLogout = new JButton("Cerrar Sesión");
        btnLogout.setBorder(new LineBorder(new Color(0, 0, 0)));
        btnLogout.setForeground(new Color(255, 255, 255));
        btnLogout.setBackground(new Color(102, 153, 204));
        btnLogout.addActionListener(this);
        btnLogout.setBounds(976, 600, 119, 23);
        btnLogout.setFocusable(false);
        btnLogout.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	btnLogout.setBackground(Color.BLACK);}
		    public void mouseExited(MouseEvent e) {
		    	btnLogout.setBackground(new Color(102, 153, 204));;}});
        jpClientes.add(btnLogout);
        
        
        //Al iniciar
        actualizarVisibilidad(grupo);
        ControladorRegistros.actualizarTablas("clientes", modelTablaClientes);
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

	    txtBuscadorOrden.setVisible(false); 
	    tblTablaOrdenes.setVisible(false);
	    scrollPaneOrdenes.setVisible(false);
	    searchPanelOrdenes.setVisible(false);	
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
	        case "ordenes":
	            txtBuscadorOrden.setVisible(true);
	            tblTablaOrdenes.setVisible(true);
	            btnOrdenes.setVisible(true);
	            scrollPaneOrdenes.setVisible(true);
	            searchPanelOrdenes.setVisible(true);
	            
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
    		modelTabla = modelTablaClientes;
    		actualizarVisibilidad(grupo);
    		ControladorRegistros.actualizarTablas(grupo, modelTabla);
    	} else if (e.getSource() == btnMecanicos) {
    		grupo = "mecanicos";
    		modelTabla = modelTablaMecanicos;
    		actualizarVisibilidad(grupo);
    		ControladorRegistros.actualizarTablas(grupo, modelTabla);
    	} else if (e.getSource() == btnVehiculos) {
    		grupo = "vehiculos";
    		modelTabla = modelTablaVehiculos;
    		actualizarVisibilidad(grupo);
    		ControladorRegistros.actualizarTablas(grupo, modelTabla);
    	} else if (e.getSource() == btnOrdenes) {
    		grupo = "ordenes";
    		modelTabla = modelTablaOrdenes;
    		actualizarVisibilidad(grupo);
    		ControladorRegistros.actualizarTablas(grupo, modelTabla);
    	} else if (e.getSource() == btnCrearRegistro) {
    		//Al crear un registro, llama al metodo crearRegistro y le pasa el grupo y el modelo actual
    		ControladorRegistros.crearRegistro(grupo, modelTabla);
    	} else if(e.getSource() == btnBorrarRegistro) {
    		borrarRegistro(grupo);
    	} else if(e.getSource() == btnActualizarRegistro) {
    		actualizarRegistro(grupo);
    	}
	 }
	

	public void logout() {
		//Pide confirmacion para cerrar sesion
		 int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar cerrar sesión?", "Confirmar cerrar sesión", JOptionPane.YES_NO_OPTION);
		 
		 if(confirmacion == JOptionPane.YES_OPTION) {
			 JOptionPane.showMessageDialog(this, "Cerrando Sesión...", "Información", JOptionPane.INFORMATION_MESSAGE);
			 JFrameLogin JFLogin = new JFrameLogin();
			 JFLogin.setVisible(true);
		     dispose();
		 }
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
			    
			    vtnActualizarRegistro = new VtnActualizarRegistro(columnasCliente, valoresActualesCliente, grupo, modelTabla);
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
			    
			    vtnActualizarRegistro = new VtnActualizarRegistro(columnasMecanico, valoresActualesMecanico, grupo, modelTabla);
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
			    
			    vtnActualizarRegistro = new VtnActualizarRegistro(columnasVehiculos, valoresActualesVehiculos, grupo, modelTabla);
				break;
		}
	
		vtnActualizarRegistro.setVisible(true);
			
	}

	//Detecta el cambio en las tablas (cuando selecciona un campo)
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