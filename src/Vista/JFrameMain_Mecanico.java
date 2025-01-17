package Vista;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		contentPane.setBackground(new Color(204, 204, 204));
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
				
				JLabel BtnOrdenes = new JLabel("Órdenes de reparación");
				BtnOrdenes.setHorizontalAlignment(SwingConstants.CENTER);
				BtnOrdenes.setFont(new Font("Tahoma", Font.BOLD, 13));
				BtnOrdenes.setBounds(30, 75, 154, 28);
				panel.add(BtnOrdenes);
				
				JLabel lblTitulo = new JLabel("GESTIÓN");
				lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
				lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 15));
				lblTitulo.setBounds(43, 24, 126, 24);
				panel.add(lblTitulo);
				
				JLabel lbln = new JLabel("Historial de vehículos");
				lbln.setHorizontalAlignment(SwingConstants.CENTER);
				lbln.setFont(new Font("Tahoma", Font.BOLD, 13));
				lbln.setBounds(30, 137, 154, 28);
				panel.add(lbln);
				
				JLabel lblSolicitarPiezas = new JLabel("Solicitar piezas");
				lblSolicitarPiezas.setHorizontalAlignment(SwingConstants.CENTER);
				lblSolicitarPiezas.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblSolicitarPiezas.setBounds(30, 202, 154, 28);
				panel.add(lblSolicitarPiezas);
				
				JLabel lblStockDePiezas = new JLabel("Stock de piezas");
				lblStockDePiezas.setHorizontalAlignment(SwingConstants.CENTER);
				lblStockDePiezas.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblStockDePiezas.setBounds(30, 270, 154, 28);
				panel.add(lblStockDePiezas);
				
				JLabel lblBuscarVehculos = new JLabel("Buscar vehículos");
				lblBuscarVehculos.setHorizontalAlignment(SwingConstants.CENTER);
				lblBuscarVehculos.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblBuscarVehculos.setBounds(30, 335, 154, 28);
				panel.add(lblBuscarVehculos);
				
				JLabel lblListaDeVehculos = new JLabel("Lista de vehículos");
				lblListaDeVehculos.setHorizontalAlignment(SwingConstants.CENTER);
				lblListaDeVehculos.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblListaDeVehculos.setBounds(30, 398, 154, 28);
				panel.add(lblListaDeVehculos);
				
				JPanel Orden_1 = new JPanel();
				Orden_1.setBounds(288, 87, 185, 302);
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
				
				JPanel Orden_2 = new JPanel();
				Orden_2.setBounds(540, 87, 185, 302);
				panel.add(Orden_2);
				Orden_2.setLayout(null);
				
				JPanel Orden_3 = new JPanel();
				Orden_3.setBounds(796, 87, 185, 302);
				panel.add(Orden_3);
				Orden_3.setLayout(null);
				
				JLabel lblrdenesAsignadas = new JLabel("Órdenes asignadas");
				lblrdenesAsignadas.setHorizontalAlignment(SwingConstants.CENTER);
				lblrdenesAsignadas.setFont(new Font("Tahoma", Font.BOLD, 15));
				lblrdenesAsignadas.setBounds(555, 24, 157, 24);
				panel.add(lblrdenesAsignadas);



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
