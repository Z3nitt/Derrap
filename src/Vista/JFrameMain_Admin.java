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
    private JPanel contentPane, searchPanelFacturas;
    private JButton btnActualizarRegistro, btnCrearRegistro, btnLogout, btnImprimir, btnClientes, btnOrdenes, btnMecanicos, btnVehiculos, btnBorrarRegistro, btnLogout2, btnCrearPieza, btnActualizarPieza, btnBorrarPieza, btnLogout3, btnCrearFactura;
    private JPanel jpClientes, jpMaterial, jpEconomia;
    private JTextField txtBuscadorClientes, txtBuscadorMecanicos, txtBuscadorOrden, txtBuscadorStock;
    private JTable tblTablaClientes, tblTablaVehiculos, tblTablaMecanicos, tblTablaOrdenes, tblTablaRepuesto, tblTablaFactura;
    private JTextField txtBuscadorVehiculos, txtBuscadorFacturas;
    private JScrollPane scrollPaneClientes, scrollPaneVehiculos, scrollPaneMecanicos, scrollPaneOrdenes, scrollPaneRepuesto, scrollPaneFactura;
    private JPanel searchPanelClientes, searchPanelVehiculos, searchPanelMecanicos, searchPanelOrdenes, searchPanelMaterial;
    private DefaultTableModel modelTablaClientes, modelTablaMecanicos, modelTablaVehiculos, modelTablaOrdenes, modelTabla, modelTablaRepuesto, modelTablaFactura;
    
    //Valores de las columnas de cada tabla
    String[] columnasCliente = {"DNI","Nombre", "Apellidos", "Telefono"};
    String[] columnasMecanico = {"DNI", "Nombre", "Apellidos", "Password", "Estado"};
    String[] columnasVehiculos = {"matricula", "Marca", "Modelo", "Color", "Combustible", "Kilometros", "Año", "DNI_cliente"};
    String[] columnasOrdenes = {"ID", "Cliente", "Matricula", "Piezas"};
    String[] columnasRepuesto = {"ID_Repuesto", "Nombre", "Cantidad", "Precio_Compra", "Precio_Venta", "Mano_de_Obra", "ID_Proveedor"};
    String[] columnasFactura = {"ID_Factura", "Precio", "Fecha", "ID_Orden"};

    String grupo = "cliente";
    String grupoStock = "repuesto";
    private JPanel panel, panel2, panel3;
    
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
        jpMaterial.setBackground(new Color(255, 255, 255));
        jpMaterial.setLayout(null);
        
        
        jpEconomia = new JPanel();
        jpEconomia.setBackground(new Color(255, 255, 255));
        jpEconomia.setLayout(null);
        
        JTabbedPane jtpmenuPrincipal = new JTabbedPane();
        jtpmenuPrincipal.setBackground(new Color(102, 153, 204));
        jtpmenuPrincipal.setBounds(10, 26, 1088, 662);
        jtpmenuPrincipal.setFont(new Font("Tahoma", Font.BOLD, 15));
        
        jtpmenuPrincipal.add("Clientes", jpClientes);
        jtpmenuPrincipal.add("Material", jpMaterial);
    
        jtpmenuPrincipal.add("Economía", jpEconomia);
        
        getContentPane().add(jtpmenuPrincipal);

        // Configurar el hint con el FocusListener
        String hint = "Buscar clientes...";
        String hintrep = "Buscar Repuestos...";

        // Crear un panel para el JTextField con el ícono
        searchPanelClientes = new JPanel(new BorderLayout());
        searchPanelClientes.setBounds(336, 47, 616, 24);
        
        searchPanelMaterial = new JPanel(new BorderLayout());
        searchPanelMaterial.setBounds(336, 47, 616, 24);

        // Agregar el searchPanel en lugar de txtBuscadorChoches directamente
        jpClientes.add(searchPanelClientes);
        
        jpMaterial.add(searchPanelMaterial);
        
        
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
                	ControladorRegistros.buscarRegistros("cliente", modelTablaClientes, txtBuscadorClientes);
                }
            }
		});
        
        //STOCK
        txtBuscadorStock = new JTextField();
        searchPanelMaterial.add(txtBuscadorStock, BorderLayout.CENTER);
        txtBuscadorStock.setColumns(10); // Ajusta el ancho
        txtBuscadorStock.setText("Buscar Piezas..."); // Placeholder
        txtBuscadorStock.setForeground(Color.GRAY);
        txtBuscadorStock.setText(hintrep);
        txtBuscadorStock.setForeground(Color.GRAY);
        txtBuscadorStock.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtBuscadorStock.getText().equals(hintrep)) {
                	txtBuscadorStock.setText("");
                	txtBuscadorStock.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtBuscadorStock.getText().isEmpty()) {
                	txtBuscadorStock.setText(hintrep);
                	txtBuscadorStock.setForeground(Color.GRAY);
                }
            }
        });
        
        txtBuscadorStock.addKeyListener(new KeyAdapter() {
        	@Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	ControladorRegistros.buscarRegistros("stock", modelTablaRepuesto, txtBuscadorStock);
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
        
        searchPanelFacturas = new JPanel(new BorderLayout());
        searchPanelFacturas.setBounds(336, 47, 616, 24);
        
        jpEconomia.add(searchPanelFacturas);
        
        String hintf = "Buscar Facturas...";
        
     // Crear el JTextField con ícono y hint
        txtBuscadorFacturas = new JTextField();
        searchPanelFacturas.add(txtBuscadorFacturas, BorderLayout.CENTER);
        txtBuscadorFacturas.setColumns(10); // Ajusta el ancho
        txtBuscadorFacturas.setText("Buscar Facturas..."); // Placeholder
        txtBuscadorFacturas.setForeground(Color.GRAY);
        txtBuscadorFacturas.setText(hintf);
        txtBuscadorFacturas.setForeground(Color.GRAY);
        txtBuscadorFacturas.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (txtBuscadorFacturas.getText().equals(hintf)) {
                	txtBuscadorFacturas.setText("");
                	txtBuscadorFacturas.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (txtBuscadorFacturas.getText().isEmpty()) {
                	txtBuscadorFacturas.setText(hintf);
                	txtBuscadorFacturas.setForeground(Color.GRAY);
                }
            }
        });
        
        txtBuscadorFacturas.addKeyListener(new KeyAdapter() {
        	@Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	ControladorRegistros.buscarRegistros("facturas", modelTablaFactura, txtBuscadorFacturas);
                }
            }
		});
        
      //TABLA STOCK
        tblTablaRepuesto = new JTable();
        tblTablaRepuesto.setDefaultEditor(Object.class, null);
        tblTablaRepuesto.getSelectionModel().addListSelectionListener(this);
        modelTablaRepuesto = new DefaultTableModel(columnasRepuesto,0);
        tblTablaRepuesto.setModel(modelTablaRepuesto);
        
        scrollPaneRepuesto = new JScrollPane(tblTablaRepuesto); 
        scrollPaneRepuesto.setBounds(336, 82, 616, 381); 
        jpMaterial.add(scrollPaneRepuesto);
        
        //Tabla Factura
        tblTablaFactura = new JTable();
        tblTablaFactura.setDefaultEditor(Object.class, null);
        tblTablaFactura.getSelectionModel().addListSelectionListener(this);
        modelTablaFactura = new DefaultTableModel(columnasFactura,0);
        tblTablaFactura.setModel(modelTablaFactura);
        
        scrollPaneFactura = new JScrollPane(tblTablaFactura); 
        scrollPaneFactura.setBounds(336, 82, 616, 381); 
        jpEconomia.add(scrollPaneFactura);

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
                        	ControladorRegistros.buscarRegistros("vehiculo", modelTablaVehiculos, txtBuscadorVehiculos);
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
                	ControladorRegistros.buscarRegistros("mecanico", modelTablaMecanicos, txtBuscadorMecanicos);
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
        
        btnCrearFactura = new JButton("Añadir");
        btnCrearFactura.setBorder(new LineBorder(new Color(0, 0, 0)));
        btnCrearFactura.setForeground(new Color(255, 255, 255));
        btnCrearFactura.addActionListener(this);
        btnCrearFactura.setBackground(new Color(102, 153, 204));
        btnCrearFactura.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnCrearFactura.setBounds(336, 472, 196, 35);
        btnCrearFactura.setFocusable(false);
        btnCrearFactura.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	btnCrearFactura.setBackground(Color.BLACK);}
		    public void mouseExited(MouseEvent e) {
		    	btnCrearFactura.setBackground(new Color(102, 153, 204));;}});
        jpEconomia.add(btnCrearFactura);
        
        //STOCK
        btnCrearPieza = new JButton("Añadir");
        btnCrearPieza.setBorder(new LineBorder(new Color(0, 0, 0)));
        btnCrearPieza.setForeground(new Color(255, 255, 255));
        btnCrearPieza.addActionListener(this);
        btnCrearPieza.setBackground(new Color(102, 153, 204));
        btnCrearPieza.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnCrearPieza.setBounds(336, 472, 196, 35);
        btnCrearPieza.setFocusable(false);
        btnCrearPieza.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	btnCrearPieza.setBackground(Color.BLACK);}
		    public void mouseExited(MouseEvent e) {
		    	btnCrearPieza.setBackground(new Color(102, 153, 204));;}});
        jpMaterial.add(btnCrearPieza);

        // Configurar el hint con el FocusListener
        String hint4 = "Buscar orden...";

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
        
        txtBuscadorOrden.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	ControladorRegistros.buscarRegistros("orden", modelTablaOrdenes, txtBuscadorOrden);
                }
            }
        });

        
        tblTablaOrdenes = new JTable();
        tblTablaOrdenes.setDefaultEditor(Object.class, null);
        tblTablaOrdenes.getSelectionModel().addListSelectionListener(this);
        modelTablaOrdenes = new DefaultTableModel(columnasOrdenes,0);
        tblTablaOrdenes.setModel(modelTablaOrdenes);
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
        
        btnBorrarPieza = new JButton("Borrar");
        btnBorrarPieza.setBorder(new LineBorder(new Color(0, 0, 0)));
        btnBorrarPieza.setForeground(new Color(255, 255, 255));
        btnBorrarPieza.setBackground(new Color(102, 153, 204));
        btnBorrarPieza.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnBorrarPieza.setBounds(756, 472, 196, 35);
        btnBorrarPieza.setEnabled(false);
        btnBorrarPieza.addActionListener(this);
        btnBorrarPieza.setFocusable(false);
        btnBorrarPieza.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	btnBorrarPieza.setBackground(Color.BLACK);}
		    public void mouseExited(MouseEvent e) {
		    	btnBorrarPieza.setBackground(new Color(102, 153, 204));;}});
        jpMaterial.add(btnBorrarPieza);
        
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
        
        btnActualizarPieza = new JButton("Editar");
        btnActualizarPieza.setBorder(new LineBorder(new Color(0, 0, 0)));
        btnActualizarPieza.setForeground(new Color(255, 255, 255));
        btnActualizarPieza.setBackground(new Color(102, 153, 204));
        btnActualizarPieza.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnActualizarPieza.setBounds(542, 472, 204, 35);
        btnActualizarPieza.setFocusable(false);
        btnActualizarPieza.addMouseListener(new MouseAdapter() {
		    public void mouseEntered(MouseEvent e) {
		    	btnActualizarPieza.setBackground(Color.BLACK);}
		    public void mouseExited(MouseEvent e) {
		    	btnActualizarPieza.setBackground(new Color(102, 153, 204));;}});
        jpMaterial.add(btnActualizarPieza);
        btnActualizarPieza.addActionListener(this);
        btnActualizarPieza.setEnabled(false);
        
        panel = new JPanel();
        panel.setBackground(new Color(102, 153, 204));
        panel.setBounds(0, 0, 176, 674);
        jpClientes.add(panel);
        panel.setLayout(null);
        
        panel2 = new JPanel();
        panel2.setBackground(new Color(102, 153, 204));
        panel2.setBounds(0, 0, 176, 674);
        jpMaterial.add(panel2);
        panel2.setLayout(null);
        
        panel3 = new JPanel();
        panel3.setBackground(new Color(102, 153, 204));
        panel3.setBounds(0, 0, 176, 674);
        jpEconomia.add(panel3);
        panel3.setLayout(null);
        
        JLabel lblNewLabel_4 = new JLabel("");
        lblNewLabel_4.setIcon(new ImageIcon(JFrameMain_Admin.class.getResource("/package_assets/derrapchicodefinitovo.png")));
        lblNewLabel_4.setBounds(10, 469, 150, 143);
        panel.add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("");
        lblNewLabel_5.setIcon(new ImageIcon(JFrameMain_Admin.class.getResource("/package_assets/derrapchicodefinitovo.png")));
        lblNewLabel_5.setBounds(10, 469, 150, 143);
        panel2.add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("");
        lblNewLabel_6.setIcon(new ImageIcon(JFrameMain_Admin.class.getResource("/package_assets/derrapchicodefinitovo.png")));
        lblNewLabel_6.setBounds(10, 469, 150, 143);
        panel3.add(lblNewLabel_6);
        
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
        
        JLabel lblTitulo2 = new JLabel("PIEZAS");
        lblTitulo2.setForeground(new Color(255, 255, 255));
        lblTitulo2.setBounds(0, 25, 176, 24);
        panel2.add(lblTitulo2);
        lblTitulo2.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTitulo2.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel lblTitulo3 = new JLabel("FACTURAS");
        lblTitulo3.setForeground(new Color(255, 255, 255));
        lblTitulo3.setBounds(0, 25, 176, 24);
        panel3.add(lblTitulo3);
        lblTitulo3.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTitulo3.setHorizontalAlignment(SwingConstants.CENTER);
        
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
        
        btnLogout2 = new JButton("Cerrar Sesión");
        btnLogout2.setBorder(new LineBorder(new Color(0, 0, 0)));
        btnLogout2.setForeground(new Color(255, 255, 255));
        btnLogout2.setBackground(new Color(102, 153, 204));
        btnLogout2.addActionListener(this);
        btnLogout2.setBounds(976, 600, 119, 23);
        btnLogout2.setFocusable(false);
        btnLogout2.addMouseListener(new MouseAdapter() {
	    public void mouseEntered(MouseEvent e) {
	    	btnLogout2.setBackground(Color.BLACK);}
	    public void mouseExited(MouseEvent e) {
	    	btnLogout2.setBackground(new Color(102, 153, 204));;}});
        jpMaterial.add(btnLogout2);
        
        btnLogout3 = new JButton("Cerrar Sesión");
        btnLogout3.setBorder(new LineBorder(new Color(0, 0, 0)));
        btnLogout3.setForeground(new Color(255, 255, 255));
        btnLogout3.setBackground(new Color(102, 153, 204));
        btnLogout3.addActionListener(this);
        btnLogout3.setBounds(976, 600, 119, 23);
        btnLogout3.setFocusable(false);
        btnLogout3.addMouseListener(new MouseAdapter() {
	    public void mouseEntered(MouseEvent e) {
	    	btnLogout3.setBackground(Color.BLACK);}
	    public void mouseExited(MouseEvent e) {
	    	btnLogout3.setBackground(new Color(102, 153, 204));;}});
        jpEconomia.add(btnLogout3);
        
        
        //Al iniciar
        modelTabla = modelTablaClientes;
        actualizarVisibilidad(grupo);
        ControladorRegistros.actualizarTablas("cliente", modelTablaClientes);
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
	    scrollPaneMecanicos.setVisible(false);
	    searchPanelMecanicos.setVisible(false);

	    txtBuscadorOrden.setVisible(false); 
	    tblTablaOrdenes.setVisible(false);
	    scrollPaneOrdenes.setVisible(false);
	    searchPanelOrdenes.setVisible(false);
	    
	    //Limpiar las tablas
	    tblTablaClientes.clearSelection();
	    tblTablaMecanicos.clearSelection();
	    tblTablaVehiculos.clearSelection();
	    
	    switch(grupo) {
	        case "cliente":
	            txtBuscadorClientes.setVisible(true);
	            tblTablaClientes.setVisible(true);
	            txtBuscadorVehiculos.setVisible(true);
	            tblTablaVehiculos.setVisible(true);
	            scrollPaneClientes.setVisible(true);
	            scrollPaneVehiculos.setVisible(true);
	            searchPanelClientes.setVisible(true);
	            searchPanelVehiculos.setVisible(true);
	         
	            break;
	        case "mecanico":
	            txtBuscadorMecanicos.setVisible(true);
	            tblTablaMecanicos.setVisible(true);
	            scrollPaneMecanicos.setVisible(true);
	            searchPanelMecanicos.setVisible(true);
	            
	            break;
	        case "vehiculo":
	        	txtBuscadorVehiculos.setVisible(true);
	        	tblTablaVehiculos.setVisible(true);
	        	scrollPaneVehiculos.setVisible(true);
	        	searchPanelVehiculos.setVisible(true);
	        	break;
	        case "orden":
	            txtBuscadorOrden.setVisible(true);
	            tblTablaOrdenes.setVisible(true);
	            btnCrearRegistro.setVisible(true);
	            scrollPaneOrdenes.setVisible(true);
	            searchPanelOrdenes.setVisible(true);
	            break;
	        case "repuesto":
	        	txtBuscadorStock.setVisible(true);
	        	tblTablaRepuesto.setVisible(true);
	        	btnCrearPieza.setVisible(true);
	        	scrollPaneRepuesto.setVisible(true);
	        	searchPanelMaterial.setVisible(true);
	    }
	}
	
	public void actionPerformed(ActionEvent e) {
     	if(e.getSource() == btnLogout) {
    		logout();
    	} else if(e.getSource() == btnImprimir) {
    		System.out.println("Imprimiendo");
    	} else if(e.getSource() == btnClientes) {
    		grupo = "cliente";
    		modelTabla = modelTablaClientes;
    		actualizarVisibilidad(grupo);
    		ControladorRegistros.actualizarTablas(grupo, modelTabla);
    	} else if (e.getSource() == btnMecanicos) {
    		grupo = "mecanico";
    		modelTabla = modelTablaMecanicos;
    		actualizarVisibilidad(grupo);
    		ControladorRegistros.actualizarTablas(grupo, modelTabla);
    	} else if (e.getSource() == btnVehiculos) {
    		grupo = "vehiculo";
    		modelTabla = modelTablaVehiculos;
    		actualizarVisibilidad(grupo);
    		ControladorRegistros.actualizarTablas(grupo, modelTabla);
    	} else if (e.getSource() == btnOrdenes) {
    		grupo = "orden";
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
    	} else if(e.getSource()== btnCrearPieza) {
    		grupo = "repuesto";
    		modelTabla = modelTablaRepuesto;
 
    		ControladorRegistros.crearRegistro(grupo, modelTabla);
    	} else if (e.getSource() == btnActualizarPieza) {
    		grupo = "repuesto";
    		modelTabla = modelTablaRepuesto;
    		//actualizarVisibilidad(grupo);
    		actualizarRegistro(grupo);
    	} else if (e.getSource() == btnBorrarPieza) {
    		grupo = "repuesto";
    		modelTabla = modelTablaRepuesto;
    		borrarRegistro(grupo);
    	} else if (e.getSource() == btnCrearFactura) {
    		grupo = "factura";
    		modelTabla = modelTablaFactura;
    		ControladorRegistros.crearRegistro(grupo, modelTabla);
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
	 
	

	private void borrarRegistro(String grupo) {
		DefaultTableModel modelTablaSeleccionada=new DefaultTableModel();
		int registroSeleccionado=0;
		String sqlBorrar="";
		
		int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar esta fila?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
		switch(grupo) {
			case "cliente":
				registroSeleccionado = tblTablaClientes.getSelectedRow();
				Object dniCliente = tblTablaClientes.getValueAt(registroSeleccionado, 0);
				modelTablaSeleccionada=modelTablaClientes;
				sqlBorrar="DELETE FROM cliente WHERE DNI = '" + dniCliente +"'";
				break;
			case "mecanico":
				registroSeleccionado = tblTablaMecanicos.getSelectedRow();
				Object dniMecanico = tblTablaMecanicos.getValueAt(registroSeleccionado, 0);
				modelTablaSeleccionada=modelTablaMecanicos;
				sqlBorrar="DELETE FROM usuario WHERE DNI = '" + dniMecanico +"'";
				break;
			case "vehiculo":
				registroSeleccionado = tblTablaVehiculos.getSelectedRow();
				Object matriculaVehiculo = tblTablaVehiculos.getValueAt(registroSeleccionado, 0);
				modelTablaSeleccionada=modelTablaVehiculos;
				sqlBorrar="DELETE FROM vehiculo WHERE matricula = '" + matriculaVehiculo+ "'";
				break;
			case "repuesto":
				registroSeleccionado = tblTablaRepuesto.getSelectedRow();
				Object id_orden = tblTablaRepuesto.getValueAt(registroSeleccionado, 0);
				modelTablaSeleccionada=modelTablaRepuesto;
				sqlBorrar="DELETE FROM repuesto WHERE id_repuesto = '" + id_orden+ "'";
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
			case "cliente":
				int clienteSeleccionado = tblTablaClientes.getSelectedRow();
				//Obtengo todos los valores del registro seleccionado
				String dniCliente = (String) tblTablaClientes.getValueAt(clienteSeleccionado, 0);
			    String nombreCliente = (String) tblTablaClientes.getValueAt(clienteSeleccionado, 1);
			    String apellidosCliente = (String) tblTablaClientes.getValueAt(clienteSeleccionado, 2);
			    String telefono = (String) tblTablaClientes.getValueAt(clienteSeleccionado, 3);
			    String[] valoresActualesCliente = {dniCliente, nombreCliente, apellidosCliente, telefono};
			    
			    vtnActualizarRegistro = new VtnActualizarRegistro(columnasCliente, valoresActualesCliente, grupo, modelTabla);
				break;
			case "mecanico":
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
			case "vehiculo":
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
			case "repuesto":
				int piezaSeleccionada = tblTablaRepuesto.getSelectedRow();
				//Obtengo todos los valores del registro seleccionado
				String id_repuesto = (String) tblTablaRepuesto.getValueAt(piezaSeleccionada, 0);
			    String nombrepieza = (String) tblTablaRepuesto.getValueAt(piezaSeleccionada, 1);
			    String cantidad = (String) tblTablaRepuesto.getValueAt(piezaSeleccionada, 2);
			    String precio_compra = (String) tblTablaRepuesto.getValueAt(piezaSeleccionada, 3);
			    String precio_venta = (String) tblTablaRepuesto.getValueAt(piezaSeleccionada, 4);  
			    String mano_de_obra = (String) tblTablaRepuesto.getValueAt(piezaSeleccionada, 5);
			    String id_proveedor = (String) tblTablaRepuesto.getValueAt(piezaSeleccionada, 6);
			    
			    String[] valoresActualesPiezas = {id_repuesto, nombrepieza, cantidad, precio_compra, precio_venta, mano_de_obra, id_proveedor};
			    
			    vtnActualizarRegistro = new VtnActualizarRegistro(columnasRepuesto, valoresActualesPiezas, grupo, modelTabla);
				break;
		}
	
		vtnActualizarRegistro.setVisible(true);
			
	}

	//Detecta el cambio en las tablas (cuando selecciona un campo)
	@Override
	public void valueChanged(ListSelectionEvent e) {
		//Verifica si hay una fila seleccionada y habilita o deshabilita el boton de borrar mecanico
		if(tblTablaClientes.getSelectedRow() != -1 || tblTablaMecanicos.getSelectedRow() != -1 || tblTablaVehiculos.getSelectedRow() != -1 || tblTablaRepuesto.getSelectedRow() != -1) {
			btnBorrarRegistro.setEnabled(true);
			btnActualizarRegistro.setEnabled(true);
			btnActualizarPieza.setEnabled(true);
			btnBorrarPieza.setEnabled(true);
		}else {
            btnBorrarRegistro.setEnabled(false);
            btnActualizarRegistro.setEnabled(false);
            btnActualizarPieza.setEnabled(false);
            btnBorrarPieza.setEnabled(false);
		}

	}
}