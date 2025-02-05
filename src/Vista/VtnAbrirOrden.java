package Vista;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class VtnAbrirOrden extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	public VtnAbrirOrden() {
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
		
		JLabel lbl_orden = new JLabel("DETALLES DE LA ORDEN");
		lbl_orden.setForeground(new Color(255, 255, 255));
		lbl_orden.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_orden.setBounds(194, 57, 218, 14);
		contentPane.add(lbl_orden);
		
		JLabel lbl_idorden = new JLabel("ID de la orden:");
		lbl_idorden.setForeground(new Color(255, 255, 255));
		lbl_idorden.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_idorden.setBounds(24, 139, 96, 14);
		contentPane.add(lbl_idorden);
		
		JLabel lbl_idorden_1 = new JLabel("ID de la orden");
		lbl_idorden_1.setForeground(Color.WHITE);
		lbl_idorden_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_idorden_1.setBounds(142, 139, 96, 14);
		contentPane.add(lbl_idorden_1);
		
		JLabel lbl_idorden_2 = new JLabel("Cliente:");
		lbl_idorden_2.setForeground(Color.WHITE);
		lbl_idorden_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_idorden_2.setBounds(384, 139, 49, 14);
		contentPane.add(lbl_idorden_2);
		
		JLabel lbl_idorden_3 = new JLabel("ID de la orden");
		lbl_idorden_3.setForeground(Color.WHITE);
		lbl_idorden_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_idorden_3.setBounds(456, 139, 96, 14);
		contentPane.add(lbl_idorden_3);
		
		JLabel lbl_idorden_4 = new JLabel("Marca:");
		lbl_idorden_4.setForeground(Color.WHITE);
		lbl_idorden_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_idorden_4.setBounds(24, 220, 96, 14);
		contentPane.add(lbl_idorden_4);
		
		JLabel lbl_idorden_5 = new JLabel("ID de la orden:");
		lbl_idorden_5.setForeground(Color.WHITE);
		lbl_idorden_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_idorden_5.setBounds(90, 221, 96, 14);
		contentPane.add(lbl_idorden_5);
		
		JLabel lbl_idorden_6 = new JLabel("Matrícula:");
		lbl_idorden_6.setForeground(Color.WHITE);
		lbl_idorden_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_idorden_6.setBounds(384, 221, 96, 14);
		contentPane.add(lbl_idorden_6);
		
		JLabel lbl_idorden_7 = new JLabel("ID de la orden:");
		lbl_idorden_7.setForeground(Color.WHITE);
		lbl_idorden_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_idorden_7.setBounds(456, 221, 96, 14);
		contentPane.add(lbl_idorden_7);
		
		JLabel lbl_idorden_8 = new JLabel("Modelo:");
		lbl_idorden_8.setForeground(Color.WHITE);
		lbl_idorden_8.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_idorden_8.setBounds(24, 297, 96, 14);
		contentPane.add(lbl_idorden_8);
		
		JLabel lbl_idorden_9 = new JLabel("ID de la orden:");
		lbl_idorden_9.setForeground(Color.WHITE);
		lbl_idorden_9.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_idorden_9.setBounds(90, 298, 96, 14);
		contentPane.add(lbl_idorden_9);
		
		JLabel lbl_idorden_10 = new JLabel("Piezas:");
		lbl_idorden_10.setForeground(Color.WHITE);
		lbl_idorden_10.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_idorden_10.setBounds(24, 410, 49, 14);
		contentPane.add(lbl_idorden_10);
		
		JLabel lbl_idorden_11 = new JLabel("ID de la orden:");
		lbl_idorden_11.setForeground(Color.WHITE);
		lbl_idorden_11.setFont(new Font("Tahoma", Font.BOLD, 13));
		lbl_idorden_11.setBounds(105, 375, 462, 85);
		contentPane.add(lbl_idorden_11);
	}
}
