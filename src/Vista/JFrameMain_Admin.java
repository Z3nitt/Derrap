package Vista;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Controlador.Conector_BBDD;
import Vista.VtnCrearActualizarMecanico;
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
    private JButton btnCrearMecanicos, btnLogout, btnImprimir, btnClientes, btnCrearCliente, btnCrearCoche, btnProveedores, btnMecanicos, btnCrearProveedores, btnBorrarMecanicos;
    private JPanel jpClientes, jpMaterial, jpServicios, jpEconomia;
    private JTextField txtBuscadorClientes, txtBuscadorMecanicos, txtBuscadorProveedor;
    private JTable tblTablaClientes, tblTablaCoches, tblTablaMecanicos, tblTablaProveedores;
    private JTextField txtBuscadorCoches;
    private JScrollPane scrollPaneClientes, scrollPaneCoches, scrollPaneMecanicos, scrollPaneProveedores;
    private JPanel searchPanel, searchPanel2, searchPanel3, searchPanel4;
    private DefaultTableModel modelTabla;

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
        
        //Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        //int height = pantalla.height;
        //int width = pantalla.width;

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
        searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBounds(469, 26, 497, 24);
        searchPanel.add(txtBuscadorClientes, BorderLayout.CENTER);

        // Agregar el searchPanel en lugar de txtBuscadorChoches directamente
        jpClientes.add(searchPanel);

        
        tblTablaClientes = new JTable();
        scrollPaneClientes = new JScrollPane(tblTablaClientes); 
        scrollPaneClientes.setBounds(469, 61, 497, 153); 
        jpClientes.add(scrollPaneClientes);
        
        btnCrearCliente = new JButton("Añadir/Editar");
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
        btnImprimir.setBounds(10, 506, 150, 45); // Ajusta la posición y tamaño
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
        searchPanel2 = new JPanel(new BorderLayout());
        searchPanel2.setBounds(469, 288, 497, 24);
        searchPanel2.add(txtBuscadorCoches, BorderLayout.CENTER);

        // Agregar el searchPanel en lugar de txtBuscadorChoches directamente
        jpClientes.add(searchPanel2);
        

        tblTablaCoches = new JTable();
        scrollPaneCoches = new JScrollPane(tblTablaCoches); 
        scrollPaneCoches.setBounds(469, 331, 497, 127); 
        jpClientes.add(scrollPaneCoches);

        
        btnCrearCoche = new JButton("Añadir/Editar");
        btnCrearCoche.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
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
        
        txtBuscadorMecanicos.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    //System.out.println("Enter presionado");
                    buscarMecanicos();
                }
            }
        });

        
        
        
        searchPanel3 = new JPanel(new BorderLayout());
        searchPanel3.setBounds(469, 26, 497, 24);
        searchPanel3.add(txtBuscadorMecanicos, BorderLayout.CENTER);

        // Agregar el searchPanel en lugar de txtBuscadorChoches directamente
        jpClientes.add(searchPanel3);

        
        tblTablaMecanicos = new JTable();
        scrollPaneMecanicos = new JScrollPane(tblTablaMecanicos);
        scrollPaneMecanicos.setBounds(469, 61, 497, 153);
        jpClientes.add(scrollPaneMecanicos);
        // Configurar la tabla para que no sea editable
        tblTablaMecanicos.setDefaultEditor(Object.class, null);
        
        // Definir los nombres de las columnas
	    String[] columnNames = {"Nombre", "Apellidos", "DNI", "Contraseña", "Estado"};
	    
	    // Crear un modelo de tabla vacío
	    modelTabla = new DefaultTableModel(columnNames, 0); 
	    
	    // Asignar el modelo de la tabla
	    tblTablaMecanicos.setModel(modelTabla);
	    
	    //Verifica si hay una fila seleccionada y habilita o deshabilita el boton de borrar mecanico
	    tblTablaMecanicos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
                if (tblTablaMecanicos.getSelectedRow() != -1) {
                    
                    btnBorrarMecanicos.setEnabled(true);
                } else {
                    
                	btnBorrarMecanicos.setEnabled(false);
                }
				
			}
		});
        
        
        
        btnCrearMecanicos = new JButton("Añadir/Editar");
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
        searchPanel4 = new JPanel(new BorderLayout());
        searchPanel4.setBounds(469, 26, 497, 24);
        searchPanel4.add(txtBuscadorProveedor, BorderLayout.CENTER);

        // Agregar el searchPanel en lugar de txtBuscadorChoches directamente
        jpClientes.add(searchPanel4);

        
        tblTablaProveedores = new JTable();
        scrollPaneProveedores = new JScrollPane(tblTablaProveedores); 
        scrollPaneProveedores.setBounds(469, 61, 497, 153); 
        jpClientes.add(scrollPaneProveedores);

        
        btnCrearProveedores = new JButton("Añadir/Editar");
        btnCrearProveedores.addActionListener(this);
        btnCrearProveedores.setBackground(new Color(170, 255, 0));
        btnCrearProveedores.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnCrearProveedores.setBounds(469, 226, 497, 35);
        jpClientes.add(btnCrearProveedores);
        
        btnBorrarMecanicos = new JButton("Borrar");
        btnBorrarMecanicos.setBounds(976, 231, 89, 23);
        btnBorrarMecanicos.setEnabled(false);
        jpClientes.add(btnBorrarMecanicos);
        
        
        btnLogout = new JButton("Cerrar Sesión");
        btnLogout.addActionListener(this);
        btnLogout.setBounds(989, 2, 119, 23);
        fondoPantalla.add(btnLogout);
        
        //Al iniciar
        actualizarVisibilidad("clientes");
        actualizarTablaMecanicos();
	}
	
	private void actualizarVisibilidad(String grupo) {
	    
	    txtBuscadorClientes.setVisible(false);
	    tblTablaClientes.setVisible(false);
	    btnCrearCliente.setVisible(false);
	    txtBuscadorCoches.setVisible(false);
	    tblTablaCoches.setVisible(false);
	    btnCrearCoche.setVisible(false);
	    scrollPaneClientes.setVisible(false);
	    scrollPaneCoches.setVisible(false);
	    searchPanel.setVisible(false);
	    searchPanel2.setVisible(false);

	    txtBuscadorMecanicos.setVisible(false);
	    tblTablaMecanicos.setVisible(false);
	    btnCrearMecanicos.setVisible(false);
	    scrollPaneMecanicos.setVisible(false);
	    searchPanel3.setVisible(false);

	    txtBuscadorProveedor.setVisible(false);
	    tblTablaProveedores.setVisible(false);
	    btnCrearProveedores.setVisible(false);
	    scrollPaneProveedores.setVisible(false);
	    searchPanel4.setVisible(false);	
	    btnBorrarMecanicos.setVisible(false);

	    
	    switch(grupo) {
	        case "clientes":
	            txtBuscadorClientes.setVisible(true);
	            tblTablaClientes.setVisible(true);
	            btnCrearCliente.setVisible(true);
	            txtBuscadorCoches.setVisible(true);
	            tblTablaCoches.setVisible(true);
	            btnCrearCoche.setVisible(true);
	            scrollPaneClientes.setVisible(true);
	            scrollPaneCoches.setVisible(true);
	            searchPanel.setVisible(true);
	            searchPanel2.setVisible(true);
	            

	            break;
	        case "mecanicos":
	            txtBuscadorMecanicos.setVisible(true);
	            tblTablaMecanicos.setVisible(true);
	            btnCrearMecanicos.setVisible(true);
	            scrollPaneMecanicos.setVisible(true);
	            searchPanel3.setVisible(true);
	            btnBorrarMecanicos.setVisible(true);
	            
	            

	            break;
	        case "proveedores":
	            txtBuscadorProveedor.setVisible(true);
	            tblTablaProveedores.setVisible(true);
	            btnCrearProveedores.setVisible(true);
	            scrollPaneProveedores.setVisible(true);
	            searchPanel4.setVisible(true);
	            

	            break;
	    }
	}



	
	 public void actionPerformed(ActionEvent e) {
	         if(e.getSource() == btnLogout) {
        		logout();
        		
        		
        	} else if(e.getSource() == btnImprimir) {
        		System.out.println("Imprimiendo");
        		
        		
        	} else if(e.getSource() == btnClientes) {
        		actualizarVisibilidad("clientes");
        		
        		

        	} else if (e.getSource() == btnMecanicos) {
        		actualizarVisibilidad("mecanicos");


        	} else if (e.getSource() == btnProveedores) {
        		actualizarVisibilidad("proveedores");


        	} else if (e.getSource() == btnCrearMecanicos) {
        		vtnmecanico.setVisible(true);
                
        	}
	 }

	 public void logout() {
		 JOptionPane.showMessageDialog(this, "Cerrando Sesión...", "Información", JOptionPane.INFORMATION_MESSAGE);
		 JFrameLogin JFLogin = new JFrameLogin();
		 JFLogin.setVisible(true);
         dispose();
	 }
	 
	 private void buscarMecanicos() {
		    String nombreMecanico = txtBuscadorMecanicos.getText().trim();
		 
		    // Consulta SQL para buscar mecánicos
		    String selectBuscador = "SELECT nombre, apellidos, DNI, contrasenia, rol, estado FROM usuario WHERE rol = 'Mecanico' AND nombre LIKE '%" + nombreMecanico + "%'";

		    try {
		        
		        ResultSet rset = conexion.ejecutarSelect(selectBuscador);
		        
		        //Vacia la tabla
		        modelTabla.setRowCount(0);
		        
		        // Procesar los resultados
		        while (rset.next()) {
		            // Obtener cada campo del ResultSet y añadir la fila a la tabla
		            Object[] fila = new Object[5];
		            fila[0] = rset.getString("nombre");
		            fila[1] = rset.getString("apellidos");
		            fila[2] = rset.getString("DNI");
		            fila[3] = rset.getString("contrasenia");
		            fila[4] = rset.getString("estado");
		            modelTabla.addRow(fila); // Agregar fila al modelo de la tabla
		        }
		    } catch (SQLException e) {
		        JOptionPane.showMessageDialog(this, "Error al buscar mecánicos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		    }
		}
	 
	 private void actualizarTablaMecanicos() {
		 try {
			 conexion.conectar();
			 
			 ResultSet rset = conexion.ejecutarSelect("SELECT nombre, apellidos, DNI, contrasenia, rol, estado FROM usuario where rol = 'Mecanico' ");
			 while (rset.next()) {
	                // Obtener cada campo del ResultSet y añadir la fila a la tabla
	                Object[] fila = new Object[5];
	                fila[0] = rset.getString("nombre");       
	                fila[1] = rset.getString("apellidos");    
	                fila[2] = rset.getString("DNI");          
	                fila[3] = rset.getString("contrasenia");           
	                fila[4] = rset.getString("estado");       
	                
	                modelTabla.addRow(fila); // Agregar fila al modelo de la tabla
            }
			 
		} catch (Exception e) {
			System.out.println(e);
		}
	 }
}