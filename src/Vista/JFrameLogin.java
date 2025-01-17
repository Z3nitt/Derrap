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

 //hhsakdjhaskdjaskdjaslkdjasldjaslk   
    
    public JFrameLogin() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 1083, 626);
        contentPane = new FondoPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setTitle("Login | Derrap");
        setIconImage(new ImageIcon(getClass().getResource("/package_assets/icon.png")).getImage());
        setResizable(false);

        setContentPane(contentPane);
        contentPane.setLayout(null);

        //Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        //int height = pantalla.height;
        //int width = pantalla.width;

        setSize(910, 622);
        setLocationRelativeTo(null);
        contentPane.setFocusable(true);
	    contentPane.requestFocusInWindow();
	    contentPane.addKeyListener(this);

        lblUsuario = new JLabel("Usuario: ");
        lblUsuario.setBounds(383, 105, 155, 30);
        lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblUsuario);

        lblPass = new JLabel("Contraseña: ");
        lblPass.setBounds(383, 250, 155, 30);
        lblPass.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblPass.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblPass);

        jpassword = new JPasswordField();
        jpassword.setBounds(383, 275, 155, 30);
        jpassword.setHorizontalAlignment(SwingConstants.CENTER);
        jpassword.addKeyListener(this);
        addPlaceholder(jpassword, "Contraseña");
        contentPane.add(jpassword);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(383, 129, 155, 30);
        txtUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        addPlaceholder(txtUsuario, "Usuario");
        txtUsuario.addKeyListener(this);
        contentPane.add(txtUsuario);

        btnLogin = new JButton("Acceder");
        btnLogin.setBounds(404, 374, 114, 40);
        btnLogin.addActionListener(this);
        btnLogin.setBackground(new Color(0, 0, 0));
        btnLogin.setForeground(new Color(207, 249, 252));
        contentPane.add(btnLogin);
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
            String password = new String (jpassword.getPassword());
            String admin = "Administrador";
            String mecanico = "Mecanico";
            try {
            	conexion.conectar();
            	ResultSet rset=conexion.ejecutarSelect("SELECT contrasenia,rol FROM usuario WHERE DNI = '" + usuario + "' ;");

            	if(!rset.next()) {
            		JOptionPane.showMessageDialog(this, "No se encontraron resultados para el usuario ingresado.", "Error de autenticación", JOptionPane.WARNING_MESSAGE);
            		return;
            	}


            	String contraseniaBD = rset.getString("contrasenia");
            	String rol = rset.getString("rol");

                if(contraseniaBD.equals(password) && rol.equals(admin)){
                	JOptionPane.showMessageDialog(rootPane, "Identificado como administrador");
                    JFrameMain_Admin JFrameMain_Admin = new JFrameMain_Admin();
                    JFrameMain_Admin.setVisible(true);
                    dispose();
                }else if (contraseniaBD.equals(password) && rol.equals(mecanico)){
                	JOptionPane.showMessageDialog(rootPane, "Identificado como mecánico");
                	JFrameMain_Mecanico JFrameMain_Mecanico = new JFrameMain_Mecanico();
                	JFrameMain_Mecanico.setVisible(true);
                    dispose();
                }else {
                	JOptionPane.showMessageDialog(this, "Contraseña incorrecta.", "Error de autenticación", JOptionPane.WARNING_MESSAGE);
                }

            }
            catch (Exception ex) {
            	JOptionPane.showMessageDialog(this, "Error al conectar con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//Cuando el usuario toca el Enter, presiona el boton "Acceder"
		int keyCode = e.getKeyCode();
		if(keyCode == 10) {
			btnLogin.doClick();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
