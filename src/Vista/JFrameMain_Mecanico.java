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
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.LineBorder;

public class JFrameMain_Mecanico extends JFrame implements ActionListener {
	Conector_BBDD conexion = new Conector_BBDD();
	Connection cn = null;
    Statement stm = null;
    ResultSet rsetresultado = null;
	//Background fondoPantalla = new Background();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JButton btnLogout;
	private JPanel panel;
	private  CardLayout cardLayout = new CardLayout(0,0);
	private JTable table;


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
        //fondoPantalla.setLayout(null);
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
						    Btnstock.setForeground(Color.BLACK);}
						    public void mouseExited(MouseEvent e) {
						    Btnstock.setForeground(Color.WHITE);}
						    public void mouseClicked(MouseEvent e) {
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
						    Btnordenes.setForeground(Color.BLACK);}
						    public void mouseExited(MouseEvent e) {
						    Btnordenes.setForeground(Color.WHITE);}							
						    public void mouseClicked(MouseEvent e) {
						    	cardLayout.show(panel, "PanelOrdenes");
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
						lblNewLabel_4.setIcon(new ImageIcon(JFrameMain_Mecanico.class.getResource("/package_assets/derrapchicodefinitovo.png")));
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
						    	btnLogout.setForeground(Color.WHITE);}
						    public void mouseExited(MouseEvent e) {
						    	btnLogout.setBackground(Color.WHITE);
						    	btnLogout.setForeground(Color.BLACK);}});
						btnLogout.addActionListener(this);
						btnLogout.setFocusable(false);
						
						JLabel btnListaVehiculos = new JLabel("Lista de vehículos");
						btnListaVehiculos.setHorizontalAlignment(SwingConstants.CENTER);
						btnListaVehiculos.setForeground(Color.WHITE);
						btnListaVehiculos.setFont(new Font("Tahoma", Font.BOLD, 13));
						btnListaVehiculos.setBounds(23, 234, 154, 28);
						btnListaVehiculos.addMouseListener(new MouseAdapter() {
						    public void mouseEntered(MouseEvent e) {
						    	btnListaVehiculos.setForeground(Color.BLACK);}
						    public void mouseExited(MouseEvent e) {
						    	btnListaVehiculos.setForeground(Color.WHITE);}
						    public void mouseClicked(MouseEvent e) {
						    	cardLayout.show(panel, "PanelVehiculos");
							}
});
						barra.add(btnListaVehiculos);
						
						panel = new JPanel();
						panel.setBounds(233, 11, 840, 674);
						contentPane.add(panel);
						panel.setLayout(cardLayout);
						
						JPanel PanelOrdenes = new JPanel();
						PanelOrdenes.setBackground(new Color(255, 255, 255));
						panel.add(PanelOrdenes, "PanelOrdenes");
						PanelOrdenes.setLayout(null);
						
						JScrollPane scrollPane = new JScrollPane();
						scrollPane.setBounds(70, 412, 691, 206);
						PanelOrdenes.add(scrollPane);
						
						table = new JTable();
						table.setModel(new DefaultTableModel(
							new Object[][] {
							},
							new String[] {
								"Cliente", "ID", "Veh\u00EDculo", "Matr\u00EDcula"
							}
						));
						scrollPane.setViewportView(table);
						
						JPanel OrdenA = new JPanel();
						OrdenA.setBorder(new LineBorder(new Color(0, 0, 0)));
						OrdenA.setBackground(new Color(102, 153, 204));
						OrdenA.setBounds(70, 59, 184, 295);
						PanelOrdenes.add(OrdenA);
						OrdenA.setLayout(null);
						
						JLabel lblNewLabel = new JLabel("Cliente: ");
						lblNewLabel.setForeground(new Color(255, 255, 255));
						lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
						lblNewLabel.setBounds(10, 11, 56, 14);
						OrdenA.add(lblNewLabel);
						
						JLabel lblId = new JLabel("ID: ");
						lblId.setForeground(new Color(255, 255, 255));
						lblId.setFont(new Font("Tahoma", Font.BOLD, 12));
						lblId.setBounds(10, 51, 56, 14);
						OrdenA.add(lblId);
						
						JLabel lblMarca = new JLabel("Marca: ");
						lblMarca.setForeground(new Color(255, 255, 255));
						lblMarca.setFont(new Font("Tahoma", Font.BOLD, 12));
						lblMarca.setBounds(10, 91, 56, 14);
						OrdenA.add(lblMarca);
						
						JLabel lblModelo = new JLabel("Modelo: ");
						lblModelo.setForeground(new Color(255, 255, 255));
						lblModelo.setFont(new Font("Tahoma", Font.BOLD, 12));
						lblModelo.setBounds(10, 134, 56, 14);
						OrdenA.add(lblModelo);
						
						JLabel lblMatrcula = new JLabel("Matrícula: ");
						lblMatrcula.setForeground(new Color(255, 255, 255));
						lblMatrcula.setFont(new Font("Tahoma", Font.BOLD, 12));
						lblMatrcula.setBounds(10, 175, 64, 14);
						OrdenA.add(lblMatrcula);
						
						JButton btnAbrirOrden = new JButton("Abrir orden");
						btnAbrirOrden.setFont(new Font("Tahoma", Font.BOLD, 12));
						btnAbrirOrden.setBorder(new LineBorder(new Color(0, 0, 0)));
						btnAbrirOrden.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							}
						});
						btnAbrirOrden.setBackground(new Color(255, 255, 255));
						btnAbrirOrden.setBounds(35, 220, 111, 53);
						btnAbrirOrden.setFocusable(false);
						btnAbrirOrden.addMouseListener(new MouseAdapter() {
						    public void mouseEntered(MouseEvent e) {
						    	btnAbrirOrden.setBackground(Color.BLACK);
						    	btnAbrirOrden.setForeground(Color.WHITE);}
						    public void mouseExited(MouseEvent e) {
						    	btnAbrirOrden.setBackground(Color.WHITE);
						    	btnAbrirOrden.setForeground(Color.BLACK);}});
						
						OrdenA.add(btnAbrirOrden);
						
						JPanel OrdenA_1 = new JPanel();
						OrdenA_1.setBorder(new LineBorder(new Color(0, 0, 0)));
						OrdenA_1.setLayout(null);
						OrdenA_1.setBackground(new Color(102, 153, 204));
						OrdenA_1.setBounds(577, 59, 184, 295);
						PanelOrdenes.add(OrdenA_1);
						
						JLabel lblNewLabel_1 = new JLabel("Cliente: ");
						lblNewLabel_1.setForeground(Color.WHITE);
						lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
						lblNewLabel_1.setBounds(10, 11, 56, 14);
						OrdenA_1.add(lblNewLabel_1);
						
						JLabel lblId_1 = new JLabel("ID: ");
						lblId_1.setForeground(Color.WHITE);
						lblId_1.setFont(new Font("Tahoma", Font.BOLD, 12));
						lblId_1.setBounds(10, 51, 56, 14);
						OrdenA_1.add(lblId_1);
						
						JLabel lblMarca_1 = new JLabel("Marca: ");
						lblMarca_1.setForeground(Color.WHITE);
						lblMarca_1.setFont(new Font("Tahoma", Font.BOLD, 12));
						lblMarca_1.setBounds(10, 91, 56, 14);
						OrdenA_1.add(lblMarca_1);
						
						JLabel lblModelo_1 = new JLabel("Modelo: ");
						lblModelo_1.setForeground(Color.WHITE);
						lblModelo_1.setFont(new Font("Tahoma", Font.BOLD, 12));
						lblModelo_1.setBounds(10, 134, 56, 14);
						OrdenA_1.add(lblModelo_1);
						
						JLabel lblMatrcula_1 = new JLabel("Matrícula: ");
						lblMatrcula_1.setForeground(Color.WHITE);
						lblMatrcula_1.setFont(new Font("Tahoma", Font.BOLD, 12));
						lblMatrcula_1.setBounds(10, 175, 64, 14);
						OrdenA_1.add(lblMatrcula_1);
						
						JButton btnAbrirOrden_3 = new JButton("Abrir orden");
						btnAbrirOrden_3.setFont(new Font("Tahoma", Font.BOLD, 12));
						btnAbrirOrden_3.setBorder(new LineBorder(new Color(0, 0, 0)));
						btnAbrirOrden_3.setFocusable(false);
						btnAbrirOrden_3.setBackground(Color.WHITE);
						btnAbrirOrden_3.setBounds(35, 220, 111, 53);
						btnAbrirOrden_3.addMouseListener(new MouseAdapter() {
						    public void mouseEntered(MouseEvent e) {
						    	btnAbrirOrden_3.setBackground(Color.BLACK);
						    	btnAbrirOrden_3.setForeground(Color.WHITE);}
						    public void mouseExited(MouseEvent e) {
						    	btnAbrirOrden_3.setBackground(Color.WHITE);
						    	btnAbrirOrden_3.setForeground(Color.BLACK);}});
						OrdenA_1.add(btnAbrirOrden_3);
						
						JPanel OrdenA_2 = new JPanel();
						OrdenA_2.setBorder(new LineBorder(new Color(0, 0, 0)));
						OrdenA_2.setLayout(null);
						OrdenA_2.setBackground(new Color(102, 153, 204));
						OrdenA_2.setBounds(322, 59, 184, 295);
						PanelOrdenes.add(OrdenA_2);
						
						JLabel lblNewLabel_2 = new JLabel("Cliente: ");
						lblNewLabel_2.setForeground(Color.WHITE);
						lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
						lblNewLabel_2.setBounds(10, 11, 56, 14);
						OrdenA_2.add(lblNewLabel_2);
						
						JLabel lblId_2 = new JLabel("ID: ");
						lblId_2.setForeground(Color.WHITE);
						lblId_2.setFont(new Font("Tahoma", Font.BOLD, 12));
						lblId_2.setBounds(10, 51, 56, 14);
						OrdenA_2.add(lblId_2);
						
						JLabel lblMarca_2 = new JLabel("Marca: ");
						lblMarca_2.setForeground(Color.WHITE);
						lblMarca_2.setFont(new Font("Tahoma", Font.BOLD, 12));
						lblMarca_2.setBounds(10, 91, 56, 14);
						OrdenA_2.add(lblMarca_2);
						
						JLabel lblModelo_2 = new JLabel("Modelo: ");
						lblModelo_2.setForeground(Color.WHITE);
						lblModelo_2.setFont(new Font("Tahoma", Font.BOLD, 12));
						lblModelo_2.setBounds(10, 134, 56, 14);
						OrdenA_2.add(lblModelo_2);
						
						JLabel lblMatrcula_2 = new JLabel("Matrícula: ");
						lblMatrcula_2.setForeground(Color.WHITE);
						lblMatrcula_2.setFont(new Font("Tahoma", Font.BOLD, 12));
						lblMatrcula_2.setBounds(10, 175, 64, 14);
						OrdenA_2.add(lblMatrcula_2);
						
						JButton btnAbrirOrden_2 = new JButton("Abrir orden");
						btnAbrirOrden_2.setFont(new Font("Tahoma", Font.BOLD, 12));
						btnAbrirOrden_2.setBorder(new LineBorder(new Color(0, 0, 0)));
						btnAbrirOrden_2.setFocusable(false);
						btnAbrirOrden_2.setBackground(Color.WHITE);
						btnAbrirOrden_2.setBounds(35, 220, 111, 53);
						btnAbrirOrden_2.addMouseListener(new MouseAdapter() {
						    public void mouseEntered(MouseEvent e) {
						    	btnAbrirOrden_2.setBackground(Color.BLACK);
						    	btnAbrirOrden_2.setForeground(Color.WHITE);}
						    public void mouseExited(MouseEvent e) {
						    	btnAbrirOrden_2.setBackground(Color.WHITE);
						    	btnAbrirOrden_2.setForeground(Color.BLACK);}});
						OrdenA_2.add(btnAbrirOrden_2);
						
						JButton btnAsignar = new JButton("Asignar");
						btnAsignar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							}
						});
						btnAsignar.setBackground(new Color(102, 153, 204));
						btnAsignar.setBorder(new LineBorder(new Color(0, 0, 0)));
						btnAsignar.setForeground(new Color(255, 255, 255));
						btnAsignar.setFont(new Font("Tahoma", Font.BOLD, 15));
						btnAsignar.setBounds(322, 629, 184, 34);
						 btnAsignar.addMouseListener(new MouseAdapter() {
							    public void mouseEntered(MouseEvent e) {
							    	btnAsignar.setBackground(Color.BLACK);}
							    public void mouseExited(MouseEvent e) {
							    	btnAsignar.setBackground(new Color(102, 153, 204));;}});
						PanelOrdenes.add(btnAsignar);
						
						JPanel PanelStock = new JPanel();
						PanelStock.setBackground(new Color(255, 255, 255));
						panel.add(PanelStock, "PanelStock");
						PanelStock.setLayout(null);
						
						JPanel PanelVehiculos = new JPanel();
						panel.add(PanelVehiculos, "PanelVehiculos");
						PanelVehiculos.setLayout(null);



        setSize(1122, 735);
        setLocationRelativeTo(null);




	}

	@Override
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogout) {
        	logout();
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
}
