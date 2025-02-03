package Vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import Controlador.Conector_BBDD;
import javax.swing.JSeparator;

public class JFrameLogin extends JFrame implements ActionListener, KeyListener {

	Conector_BBDD conexion = new Conector_BBDD();
	Connection cn = null;
	Statement stm = null;
	ResultSet rsetresultado = null;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JLabel lblUsuario, lblPass;
	private JButton btnLogin;
	private JPasswordField jpassword;
	private JSeparator separator_1;
	private JLabel lblPassIcon;
	private JLabel lblFondo2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					JFrameLogin frame = new JFrameLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JFrameLogin() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 626);
		// CAMBIAR PARA EL DISEÑO
		contentPane = new FondoPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Login | Derrap");
		setIconImage(new ImageIcon(getClass().getResource("/package_assets/icon.png")).getImage());
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		setSize(910, 622);
		setLocationRelativeTo(null);
		contentPane.setFocusable(true);
		contentPane.requestFocusInWindow();
		contentPane.addKeyListener(this);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255, 100));
		// panel.setOpaque(false);
		panel.setBounds(232, 109, 425, 360);
		contentPane.add(panel);
		panel.setLayout(null);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(53, 117, 318, 2);
		panel.add(separator);

		separator_1 = new JSeparator();
		separator_1.setForeground(new Color(0, 0, 0));
		separator_1.setBounds(53, 220, 318, 2);
		panel.add(separator_1);

		JLabel lblIconoUser = new JLabel();
		lblIconoUser.setBackground(new Color(255, 255, 255));
		lblIconoUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconoUser.setBounds(53, 83, 45, 36);
		ImageIcon iconoUsuario = new ImageIcon(getClass().getResource("/package_assets/user.png"));
		lblIconoUser.setIcon(iconoUsuario);
		panel.add(lblIconoUser);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(108, 87, 263, 30);
		panel.add(txtUsuario);
		txtUsuario.setBackground(new Color(255, 255, 255));
		txtUsuario.setBorder(null);
		txtUsuario.setHorizontalAlignment(SwingConstants.LEFT);
		addPlaceholder(txtUsuario, "Usuario");

		lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setBounds(154, 47, 155, 30);
		panel.add(lblUsuario);
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);

		btnLogin = new JButton("Acceder");
		btnLogin.setBounds(83, 270, 241, 30);
		panel.add(btnLogin);
		btnLogin.addActionListener(this);
		btnLogin.setBackground(new Color(0, 0, 0));
		btnLogin.setForeground(new Color(207, 249, 252));

		jpassword = new JPasswordField();
		jpassword.setBorder(null);
		jpassword.setBounds(108, 190, 263, 30);
		panel.add(jpassword);
		jpassword.setHorizontalAlignment(SwingConstants.LEFT);
		jpassword.addKeyListener(this);
		addPlaceholder(jpassword, "Contraseña");

		lblPass = new JLabel("Contraseña: ");
		lblPass.setBounds(154, 150, 155, 30);
		panel.add(lblPass);
		lblPass.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPass.setHorizontalAlignment(SwingConstants.CENTER);

		lblPassIcon = new JLabel();
		lblPassIcon.setBackground(new Color(255, 255, 255));
		lblPassIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassIcon.setBounds(53, 186, 45, 36);
		ImageIcon iconoPass = new ImageIcon(getClass().getResource("/package_assets/pass.png"));
		lblPassIcon.setIcon(iconoPass);
		panel.add(lblPassIcon);

		JLabel lblFondo1 = new JLabel();
		lblFondo1.setOpaque(true);
		lblFondo1.setBackground(new Color(255, 255, 255, 255));
		lblFondo1.setForeground(new Color(255, 255, 255));
		lblFondo1.setBounds(53, 83, 318, 36);
		panel.add(lblFondo1);

		lblFondo2 = new JLabel();
		lblFondo2.setOpaque(true);
		lblFondo2.setBackground(new Color(255, 255, 255));
		lblFondo2.setForeground(new Color(255, 255, 255));
		lblFondo2.setBounds(53, 190, 318, 32);
		panel.add(lblFondo2);
		txtUsuario.addKeyListener(this);
	}

	// Método para agregar un placeholder a un JTextField
	private void addPlaceholder(JTextField textField, String placeholder) {
		textField.setText(placeholder);
		textField.setForeground(Color.GRAY);

		// Listener para ocultar/mostrar el placeholder
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textField.getText().equals(placeholder)) {
					textField.setText("");
					textField.setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textField.getText().isEmpty()) {
					textField.setText(placeholder);
					textField.setForeground(Color.GRAY);
				}
			}
		});
	}

	// Clase personalizada para el fondo con imagen

	class FondoPanel extends JPanel {
		private Image imagenFondo;

		public FondoPanel() {
			imagenFondo = new ImageIcon(getClass().getResource("/package_assets/bckLogin.png")).getImage();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (imagenFondo != null) {
				g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogin) {
			String usuario = txtUsuario.getText();
			String password = new String(jpassword.getPassword());
			String admin = "Administrador";
			String mecanico = "Mecanico";
			try {
				conexion.conectar();
				ResultSet rset = conexion
						.ejecutarSelect("SELECT password,rol FROM usuario WHERE DNI = '" + usuario + "' ;");

				if (!rset.next()) {
					JOptionPane.showMessageDialog(this, "No se encontraron resultados para el usuario ingresado.",
							"Error de autenticación", JOptionPane.WARNING_MESSAGE);
					return;
				}

				String contraseniaBD = rset.getString("password");
				String rol = rset.getString("rol");

				if (contraseniaBD.equals(password) && rol.equals(admin)) {
					JOptionPane.showMessageDialog(rootPane, "Identificado como administrador");
					JFrameMain_Admin JFrameMain_Admin = new JFrameMain_Admin();
					JFrameMain_Admin.setVisible(true);
					dispose();
				} else if (contraseniaBD.equals(password) && rol.equals(mecanico)) {
					JOptionPane.showMessageDialog(rootPane, "Identificado como mecánico");
					JFrameMain_Mecanico JFrameMain_Mecanico = new JFrameMain_Mecanico();
					JFrameMain_Mecanico.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Contraseña incorrecta.", "Error de autenticación",
							JOptionPane.WARNING_MESSAGE);
				}

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos.", "Error",
						JOptionPane.ERROR_MESSAGE);
				System.out.println(ex);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Cuando el usuario toca el Enter, presiona el boton "Acceder"
		int keyCode = e.getKeyCode();
		if (keyCode == 10) {
			btnLogin.doClick();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
