package Vista;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import package_main.Background;
import Controlador.Conector_BBDD;

public class VtnCrearActualizarOrden extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Background fondoPantalla = new Background();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VtnCrearActualizarOrden frame = new VtnCrearActualizarOrden();
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
	public VtnCrearActualizarOrden() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 748, 610);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setTitle("Gestionar Órdenes | Derrap");
        setIconImage(new ImageIcon(getClass().getResource("/package_assets/icon.png")).getImage());
        setResizable(false);
        fondoPantalla.setLayout(null);
		setContentPane(fondoPantalla);

	}

}
