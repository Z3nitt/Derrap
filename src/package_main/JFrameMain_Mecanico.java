package package_main;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class JFrameMain_Mecanico extends JFrame implements ActionListener {
	Conector_BBDD conexion = new Conector_BBDD();
	Connection cn = null;
    Statement stm = null;
    ResultSet rsetresultado = null;
	Background fondoPantalla = new Background();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnBORRAR;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 626);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Mecánico | Derrap");
        setIconImage(new ImageIcon(getClass().getResource("/package_assets/icon.png")).getImage());
        setResizable(false);
        fondoPantalla.setLayout(null);
		setContentPane(fondoPantalla);
		
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int height = pantalla.height;
        int width = pantalla.width;

        setSize(width / 2, height / 2);
        setLocationRelativeTo(null);
        
        
        btnBORRAR = new JButton();
        
	}
	
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBORRAR) {
        	
        }
 }

}
