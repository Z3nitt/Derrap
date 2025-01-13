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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Mecánico | Derrap");
        setIconImage(new ImageIcon(getClass().getResource("/package_assets/icon.png")).getImage());
        setResizable(false);
        //fondoPantalla.setLayout(null);
		setContentPane(contentPane);

		btnLogout = new JButton("Cerrar Sesión");
		btnLogout.addActionListener(this);
		btnLogout.setBounds(969, 10, 129, 38);
		contentPane.add(btnLogout);



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
