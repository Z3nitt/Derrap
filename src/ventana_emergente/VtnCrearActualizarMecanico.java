package ventana_emergente;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import package_main.Background;
import package_main.Conector_BBDD;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;

public class VtnCrearActualizarMecanico extends JFrame {
	Conector_BBDD conexion = new Conector_BBDD();
	Background fondoPantalla = new Background();
	Connection cn = null;
    Statement stm = null;
    ResultSet rsetresultado = null;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VtnCrearActualizarMecanico frame = new VtnCrearActualizarMecanico();
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
	public VtnCrearActualizarMecanico() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1083, 626);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Gestionar Mecánicos | Derrap");
        setIconImage(new ImageIcon(getClass().getResource("/package_assets/icon.png")).getImage());
        setResizable(false);
        fondoPantalla.setLayout(null);
		setContentPane(fondoPantalla);
		
		JLabel lblNewLabel = new JLabel("DATOS MECÁNICO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(286, 82, 179, 14);
		fondoPantalla.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(51, 197, 207, 29);
		fondoPantalla.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(466, 197, 207, 29);
		fondoPantalla.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(51, 389, 207, 29);
		fondoPantalla.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(466, 389, 207, 29);
		fondoPantalla.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Apellidos:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(536, 143, 66, 15);
		fondoPantalla.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("DNI:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(124, 353, 99, 14);
		fondoPantalla.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Contraseña:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(536, 353, 99, 14);
		fondoPantalla.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Nombre:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(124, 143, 86, 14);
		fondoPantalla.add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("GUARDAR");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(76, 481, 164, 70);
		fondoPantalla.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("AÑADIR");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton_1.setBounds(488, 481, 164, 70);
		fondoPantalla.add(btnNewButton_1);
		
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;

        setSize(746, 619);
        setLocationRelativeTo(null);
	}
}
