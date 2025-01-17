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

public class JFrameMain_Mecanico extends JFrame implements ActionListener {
	Conector_BBDD conexion = new Conector_BBDD();
	Connection cn = null;
    Statement stm = null;
    ResultSet rsetresultado = null;
	//Background fondoPantalla = new Background();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JButton btnLogout;



	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
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
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 11, 1086, 674);
		contentPane.add(panel);
				panel.setLayout(null);
		
				btnLogout = new JButton("Cerrar Sesión");
				btnLogout.setForeground(new Color(255, 255, 255));
				btnLogout.setBackground(new Color(0, 0, 0));
				btnLogout.setBounds(956, 640, 120, 23);
				panel.add(btnLogout);
				btnLogout.addActionListener(this);
				btnLogout.setFocusable(false);
				
				JPanel Orden_1 = new JPanel();
				Orden_1.setBounds(306, 87, 167, 302);
				panel.add(Orden_1);
				Orden_1.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("ID Orden:");
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNewLabel.setBounds(10, 21, 76, 14);
				Orden_1.add(lblNewLabel);
				
				JLabel lblMarca = new JLabel("Marca:");
				lblMarca.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblMarca.setBounds(10, 57, 76, 14);
				Orden_1.add(lblMarca);
				
				JLabel lblModelo = new JLabel("Modelo:");
				lblModelo.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblModelo.setBounds(10, 95, 76, 14);
				Orden_1.add(lblModelo);
				
				JLabel lblMatrcula = new JLabel("Matrícula:");
				lblMatrcula.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblMatrcula.setBounds(10, 134, 76, 14);
				Orden_1.add(lblMatrcula);
				
				JLabel lblEstado = new JLabel("Estado:");
				lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblEstado.setBounds(10, 173, 76, 14);
				Orden_1.add(lblEstado);
				
				JLabel lblNewLabel_1 = new JLabel("0001");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblNewLabel_1.setBounds(107, 22, 31, 14);
				Orden_1.add(lblNewLabel_1);
				
				JLabel lblNewLabel_1_1 = new JLabel("Audi");
				lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblNewLabel_1_1.setBounds(107, 58, 31, 14);
				Orden_1.add(lblNewLabel_1_1);
				
				JLabel lblNewLabel_1_2 = new JLabel("A1");
				lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblNewLabel_1_2.setBounds(112, 97, 46, 14);
				Orden_1.add(lblNewLabel_1_2);
				
				JLabel lblNewLabel_1_3 = new JLabel("6969 DML");
				lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblNewLabel_1_3.setBounds(96, 135, 73, 14);
				Orden_1.add(lblNewLabel_1_3);
				
				JLabel lblNewLabel_1_4 = new JLabel("En proceso");
				lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblNewLabel_1_4.setBounds(92, 174, 77, 14);
				Orden_1.add(lblNewLabel_1_4);
				
				JButton btnabrirorden_1 = new JButton("Abrir orden");
			    btnabrirorden_1.setFocusable(false);
				btnabrirorden_1.setBackground(new Color(0, 0, 0));
				btnabrirorden_1.setForeground(new Color(255, 255, 255));
				btnabrirorden_1.setBounds(30, 227, 108, 50);
				Orden_1.add(btnabrirorden_1);
				
				JLabel lblrdenesAsignadas = new JLabel("Órdenes asignadas");
				lblrdenesAsignadas.setHorizontalAlignment(SwingConstants.CENTER);
				lblrdenesAsignadas.setFont(new Font("Tahoma", Font.BOLD, 15));
				lblrdenesAsignadas.setBounds(555, 24, 157, 24);
				panel.add(lblrdenesAsignadas);
				
				JPanel Orden_1_1 = new JPanel();
				Orden_1_1.setLayout(null);
				Orden_1_1.setBounds(545, 87, 167, 302);
				panel.add(Orden_1_1);
				
				JLabel lblNewLabel_2 = new JLabel("ID Orden:");
				lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNewLabel_2.setBounds(10, 21, 76, 14);
				Orden_1_1.add(lblNewLabel_2);
				
				JLabel lblMarca_1 = new JLabel("Marca:");
				lblMarca_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblMarca_1.setBounds(10, 57, 76, 14);
				Orden_1_1.add(lblMarca_1);
				
				JLabel lblModelo_1 = new JLabel("Modelo:");
				lblModelo_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblModelo_1.setBounds(10, 95, 76, 14);
				Orden_1_1.add(lblModelo_1);
				
				JLabel lblMatrcula_1 = new JLabel("Matrícula:");
				lblMatrcula_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblMatrcula_1.setBounds(10, 134, 76, 14);
				Orden_1_1.add(lblMatrcula_1);
				
				JLabel lblEstado_1 = new JLabel("Estado:");
				lblEstado_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblEstado_1.setBounds(10, 173, 76, 14);
				Orden_1_1.add(lblEstado_1);
				
				JLabel lblNewLabel_1_5 = new JLabel("0002");
				lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblNewLabel_1_5.setBounds(107, 22, 31, 14);
				Orden_1_1.add(lblNewLabel_1_5);
				
				JLabel lblNewLabel_1_1_1 = new JLabel("Seat");
				lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblNewLabel_1_1_1.setBounds(107, 58, 31, 14);
				Orden_1_1.add(lblNewLabel_1_1_1);
				
				JLabel lblNewLabel_1_2_1 = new JLabel("Leon");
				lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblNewLabel_1_2_1.setBounds(107, 96, 31, 14);
				Orden_1_1.add(lblNewLabel_1_2_1);
				
				JLabel lblNewLabel_1_3_1 = new JLabel("1010 AVG");
				lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblNewLabel_1_3_1.setBounds(96, 135, 73, 14);
				Orden_1_1.add(lblNewLabel_1_3_1);
				
				JLabel lblNewLabel_1_4_1 = new JLabel("En proceso");
				lblNewLabel_1_4_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblNewLabel_1_4_1.setBounds(92, 174, 77, 14);
				Orden_1_1.add(lblNewLabel_1_4_1);
				
				JButton btnabrirorden_2 = new JButton("Abrir orden");
			    btnabrirorden_2.setFocusable(false);
				btnabrirorden_2.setForeground(Color.WHITE);
				btnabrirorden_2.setBackground(Color.BLACK);
				btnabrirorden_2.setBounds(30, 227, 108, 50);
				Orden_1_1.add(btnabrirorden_2);
				
				JPanel Orden_1_2 = new JPanel();
				Orden_1_2.setLayout(null);
				Orden_1_2.setBounds(780, 87, 167, 302);
				panel.add(Orden_1_2);
				
				JLabel lblNewLabel_3 = new JLabel("ID Orden:");
				lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblNewLabel_3.setBounds(10, 21, 76, 14);
				Orden_1_2.add(lblNewLabel_3);
				
				JLabel lblMarca_2 = new JLabel("Marca:");
				lblMarca_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblMarca_2.setBounds(10, 57, 76, 14);
				Orden_1_2.add(lblMarca_2);
				
				JLabel lblModelo_2 = new JLabel("Modelo:");
				lblModelo_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblModelo_2.setBounds(10, 95, 76, 14);
				Orden_1_2.add(lblModelo_2);
				
				JLabel lblMatrcula_2 = new JLabel("Matrícula:");
				lblMatrcula_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblMatrcula_2.setBounds(10, 134, 76, 14);
				Orden_1_2.add(lblMatrcula_2);
				
				JLabel lblEstado_2 = new JLabel("Estado:");
				lblEstado_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
				lblEstado_2.setBounds(10, 173, 76, 14);
				Orden_1_2.add(lblEstado_2);
				
				JLabel lblNewLabel_1_6 = new JLabel("0003");
				lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblNewLabel_1_6.setBounds(107, 22, 31, 14);
				Orden_1_2.add(lblNewLabel_1_6);
				
				JLabel lblNewLabel_1_1_2 = new JLabel("Opel");
				lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblNewLabel_1_1_2.setBounds(107, 58, 31, 14);
				Orden_1_2.add(lblNewLabel_1_1_2);
				
				JLabel lblNewLabel_1_2_2 = new JLabel("Corsa");
				lblNewLabel_1_2_2.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblNewLabel_1_2_2.setBounds(96, 96, 42, 14);
				Orden_1_2.add(lblNewLabel_1_2_2);
				
				JLabel lblNewLabel_1_3_2 = new JLabel("7777 TWX");
				lblNewLabel_1_3_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblNewLabel_1_3_2.setBounds(96, 135, 73, 14);
				Orden_1_2.add(lblNewLabel_1_3_2);
				
				JLabel lblNewLabel_1_4_2 = new JLabel("En proceso");
				lblNewLabel_1_4_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblNewLabel_1_4_2.setBounds(92, 174, 77, 14);
				Orden_1_2.add(lblNewLabel_1_4_2);
				
				JButton btnabrirorden_3 = new JButton("Abrir orden");
			    btnabrirorden_3.setFocusable(false);
				btnabrirorden_3.setForeground(Color.WHITE);
				btnabrirorden_3.setBackground(Color.BLACK);
				btnabrirorden_3.setBounds(30, 227, 108, 50);
				Orden_1_2.add(btnabrirorden_3);
				
				JPanel barra = new JPanel();
				barra.setBackground(new Color(102, 153, 204));
				barra.setBounds(0, 0, 213, 674);
				panel.add(barra);
				barra.setLayout(null);
				
				JLabel Btnlista = new JLabel("Lista de vehículos");
				Btnlista.setForeground(new Color(255, 255, 255));
				Btnlista.setBounds(23, 361, 154, 28);
				Btnlista.addMouseListener(new MouseAdapter() {
			    public void mouseEntered(MouseEvent e) {
			    Btnlista.setForeground(Color.BLACK);}
			    public void mouseExited(MouseEvent e) {
			    Btnlista.setForeground(Color.WHITE);}});
				barra.add(Btnlista);
				Btnlista.setHorizontalAlignment(SwingConstants.CENTER);
				Btnlista.setFont(new Font("Tahoma", Font.BOLD, 13));
				
				JLabel Btnbuscar = new JLabel("Buscar vehículos");
				Btnbuscar.setForeground(new Color(255, 255, 255));
				Btnbuscar.setBounds(23, 310, 154, 28);
				Btnbuscar.addMouseListener(new MouseAdapter() {
				    public void mouseEntered(MouseEvent e) {
				    Btnbuscar.setForeground(Color.BLACK);}
				    public void mouseExited(MouseEvent e) {
				    Btnbuscar.setForeground(Color.WHITE);}});
				barra.add(Btnbuscar);
				Btnbuscar.setHorizontalAlignment(SwingConstants.CENTER);
				Btnbuscar.setFont(new Font("Tahoma", Font.BOLD, 13));
				
				JLabel Btnstock = new JLabel("Stock de piezas");
				Btnstock.setForeground(new Color(255, 255, 255));
				Btnstock.setBounds(23, 259, 154, 28);
				Btnstock.addMouseListener(new MouseAdapter() {
				    public void mouseEntered(MouseEvent e) {
				    Btnstock.setForeground(Color.BLACK);}
				    public void mouseExited(MouseEvent e) {
				    Btnstock.setForeground(Color.WHITE);}});
				barra.add(Btnstock);
				Btnstock.setHorizontalAlignment(SwingConstants.CENTER);
				Btnstock.setFont(new Font("Tahoma", Font.BOLD, 13));
				
				JLabel Btnsolicitar = new JLabel("Solicitar piezas");
				Btnsolicitar.setForeground(new Color(255, 255, 255));
				Btnsolicitar.setBounds(23, 206, 154, 28);
				Btnsolicitar.addMouseListener(new MouseAdapter() {
				    public void mouseEntered(MouseEvent e) {
				    Btnsolicitar.setForeground(Color.BLACK);}
				    public void mouseExited(MouseEvent e) {
				    Btnsolicitar.setForeground(Color.WHITE);}});
				barra.add(Btnsolicitar);
				Btnsolicitar.setHorizontalAlignment(SwingConstants.CENTER);
				Btnsolicitar.setFont(new Font("Tahoma", Font.BOLD, 13));
				
				JLabel Btnhistorial = new JLabel("Historial de vehículos");
				Btnhistorial.setForeground(new Color(255, 255, 255));
				Btnhistorial.setBounds(23, 154, 154, 28);
				Btnhistorial.addMouseListener(new MouseAdapter() {
				    public void mouseEntered(MouseEvent e) {
				    Btnhistorial.setForeground(Color.BLACK);}
				    public void mouseExited(MouseEvent e) {
				    Btnhistorial.setForeground(Color.WHITE);}});
				barra.add(Btnhistorial);
				Btnhistorial.setHorizontalAlignment(SwingConstants.CENTER);
				Btnhistorial.setFont(new Font("Tahoma", Font.BOLD, 13));
				
				JLabel Btnordenes = new JLabel("Órdenes de reparación");
				Btnordenes.setForeground(new Color(255, 255, 255));
				Btnordenes.setBounds(23, 100, 154, 28);
				Btnordenes.addMouseListener(new MouseAdapter() {
				    public void mouseEntered(MouseEvent e) {
				    Btnordenes.setForeground(Color.BLACK);}
				    public void mouseExited(MouseEvent e) {
				    Btnordenes.setForeground(Color.WHITE);}});
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
				lblNewLabel_4.setIcon(new ImageIcon(JFrameMain_Mecanico.class.getResource("/package_assets/derraplogochikito.png")));
				lblNewLabel_4.setBounds(22, 445, 155, 163);
				barra.add(lblNewLabel_4);



        setSize(1122, 735);
        setLocationRelativeTo(null);




	}

	@Override
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogout) {
        	logout();
        }
 }

	private void logout() {
		JOptionPane.showMessageDialog(this, "Cerrando Sesión...", "Información", JOptionPane.INFORMATION_MESSAGE);
		JFrameLogin JFLogin = new JFrameLogin();
		JFLogin.setVisible(true);
        dispose();
        

	}
}
