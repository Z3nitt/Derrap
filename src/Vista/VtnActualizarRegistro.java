package Vista;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.Conector_BBDD;
import Controlador.ControladorRegistros;
import package_main.Background;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;

public class VtnActualizarRegistro extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel fondoPantalla;
	private JButton btnAceptar;
	private DefaultTableModel modelTabla;
	private String [] valoresActuales, columnasTablas;
	private String grupo;
	
	Conector_BBDD conexion = new Conector_BBDD();
	ArrayList<JTextField> txtFields = new ArrayList<>();
	ArrayList<JLabel> labels = new ArrayList<>();
	private JTextField txtPrimaryKey, txtCampo1, txtCampo2, txtCampo3, txtCampo4, txtCampo5, txtCampo6;
	private JLabel lblPrimaryKey, lblTitulo, lblCampo1, lblCampo2, lblCampo3, lblCampo4, lblCampo5, lblCampo6;
	private JTextField txtForeignKey;
	private JLabel lblForeignKey;
	
	
	public VtnActualizarRegistro(String [] columnasTabla, String [] valoresActuales, String grupo, DefaultTableModel modelTabla) {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1083, 626);
		setResizable(false);
		setIconImage(new ImageIcon(getClass().getResource("/package_assets/icon.png")).getImage());
		setTitle(" Actualizar registro | Derrap");
		setSize(875, 527);
        setLocationRelativeTo(null);
		
		getContentPane().setLayout(null);

		this.valoresActuales = valoresActuales;
		this.columnasTablas = columnasTabla;
		this.grupo = grupo;
		this.modelTabla = modelTabla;
		
		fondoPantalla = new JPanel();
		fondoPantalla.setBorder(new EmptyBorder(5, 5, 5, 5));
		fondoPantalla.setLayout(null);
		fondoPantalla.setBackground(new Color(102, 153, 204));
		setContentPane(fondoPantalla);
		
		lblPrimaryKey = new JLabel("Valor Pk: ");
		lblPrimaryKey.setForeground(new Color(255, 255, 255));
		lblPrimaryKey.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPrimaryKey.setBounds(39, 113, 127, 45);
		labels.add(lblPrimaryKey);
		getContentPane().add(lblPrimaryKey);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setForeground(new Color(102, 153, 204));
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAceptar.setBounds(309, 407, 284, 42);
		btnAceptar.addActionListener(this);
		btnAceptar.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnAceptar.setBackground(Color.BLACK);
				btnAceptar.setForeground(Color.WHITE);
			}

			public void mouseExited(MouseEvent e) {
				btnAceptar.setBackground(Color.WHITE);
				btnAceptar.setForeground(new Color(102, 153, 204));
				;
			}
		});
		
		getContentPane().add(btnAceptar);
		
		lblTitulo = new JLabel("ACTUALIZAR REGISTRO");
		lblTitulo.setForeground(new Color(255, 255, 255));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTitulo.setBounds(39, 27, 280, 36);
		getContentPane().add(lblTitulo);
		
		lblCampo1 = new JLabel("");
		lblCampo1.setForeground(new Color(255, 255, 255));
		lblCampo1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCampo1.setBounds(39, 168, 127, 45);
		fondoPantalla.add(lblCampo1);
		labels.add(lblCampo1);
		
		lblCampo2 = new JLabel("");
		lblCampo2.setForeground(new Color(255, 255, 255));
		lblCampo2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCampo2.setBounds(39, 236, 127, 45);
		fondoPantalla.add(lblCampo2);
		labels.add(lblCampo2);
		
		lblCampo3 = new JLabel("");
		lblCampo3.setForeground(new Color(255, 255, 255));
		lblCampo3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCampo3.setBounds(39, 299, 127, 45);
		fondoPantalla.add(lblCampo3);
		labels.add(lblCampo3);
		
		lblCampo4 = new JLabel("");
		lblCampo4.setForeground(new Color(255, 255, 255));
		lblCampo4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCampo4.setBounds(412, 113, 127, 45);
		fondoPantalla.add(lblCampo4);
		labels.add(lblCampo4);
		
		lblCampo5 = new JLabel("");
		lblCampo5.setForeground(new Color(255, 255, 255));
		lblCampo5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCampo5.setBounds(412, 168, 127, 45);
		fondoPantalla.add(lblCampo5);
		labels.add(lblCampo5);
		
		lblCampo6 = new JLabel("");
		lblCampo6.setForeground(new Color(255, 255, 255));
		lblCampo6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCampo6.setBounds(412, 236, 127, 45);
		fondoPantalla.add(lblCampo6);
		labels.add(lblCampo6);
		
		txtPrimaryKey = new JTextField();
		txtPrimaryKey.setEditable(false);
		txtPrimaryKey.setEnabled(false);
		txtPrimaryKey.setColumns(10);
		txtPrimaryKey.setBounds(176, 125, 170, 23);
		fondoPantalla.add(txtPrimaryKey);
		txtFields.add(txtPrimaryKey);
		
		txtCampo1 = new JTextField();
		txtCampo1.setBounds(176, 180, 170, 23);
		fondoPantalla.add(txtCampo1);
		txtCampo1.setColumns(10);
		txtFields.add(txtCampo1);
		
		txtCampo2 = new JTextField();
		txtCampo2.setColumns(10);
		txtCampo2.setBounds(176, 248, 170, 23);
		fondoPantalla.add(txtCampo2);
		txtFields.add(txtCampo2);
		
		txtCampo3 = new JTextField();
		txtCampo3.setColumns(10);
		txtCampo3.setBounds(176, 313, 170, 23);
		fondoPantalla.add(txtCampo3);
		txtFields.add(txtCampo3);
		
		txtCampo4 = new JTextField();
		txtCampo4.setColumns(10);
		txtCampo4.setBounds(549, 125, 168, 23);
		fondoPantalla.add(txtCampo4);
		txtFields.add(txtCampo4);
		
		txtCampo5 = new JTextField();
		txtCampo5.setColumns(10);
		txtCampo5.setBounds(549, 180, 168, 23);
		fondoPantalla.add(txtCampo5);
		txtFields.add(txtCampo5);
		
		txtCampo6 = new JTextField();
		txtCampo6.setColumns(10);
		txtCampo6.setBounds(549, 248, 168, 23);
		fondoPantalla.add(txtCampo6);
		txtFields.add(txtCampo6);
		
		txtForeignKey = new JTextField();
		txtForeignKey.setEnabled(false);
		txtForeignKey.setEditable(false);
		txtForeignKey.setColumns(10);
		txtForeignKey.setBounds(549, 315, 170, 23);
		fondoPantalla.add(txtForeignKey);
		txtFields.add(txtForeignKey);
		
		lblForeignKey = new JLabel("");
		lblForeignKey.setForeground(new Color(255, 255, 255));
		lblForeignKey.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblForeignKey.setBounds(412, 299, 127, 45);
		fondoPantalla.add(lblForeignKey);
		labels.add(lblForeignKey);
		
		
		cargarValores();
	}

	private void cargarValores() {
		
		//Itero sobre el array de los valores empezando de 1 (salteo la pk)
		for (int i = 0; i < valoresActuales.length; i++) {
			//Por cada valor le doy el texto inicial a el txtField correspondiente
			txtFields.get(i).setText(valoresActuales[i]);
		}
		
		//Ahora cambio los label
		for (int i = 0; i < columnasTablas.length; i++) {
			labels.get(i).setText(columnasTablas[i]);
		}
		
		
		//Ahora oculto los labels y textField que no son necesarios
		for (int i = 0; i < labels.size(); i++) {
			if(labels.get(i).getText().isBlank()) {
				labels.get(i).setVisible(false);
			}
		}
		for (int i = 0; i < txtFields.size(); i++) {
			//Agrego los keylisteners y oculto los textField que no son necesarios
			txtFields.get(i).addKeyListener(this);
			if(txtFields.get(i).getText().isBlank()) {
				txtFields.get(i).setVisible(false);
			}
		}
		
		//En el caso que este en la ventana de vehiculos siempre muestra el campo de dni cliente para poder reasignarlo
		if(grupo.equals("vehiculo")) {
			txtForeignKey.setVisible(true);
			
			//Si no tiene un cliente ya asignado, permite asignarle uno
			if(txtForeignKey.getText().isEmpty()) {
				txtForeignKey.setEditable(true);
				txtForeignKey.setEnabled(true);
			}
		
		}
		//En el caso que este en la ventana de orden, deshabilita el campo de cliente y el de matricula
		if(grupo.equals("orden")) {
			txtCampo2.setEnabled(false);
			txtCampo2.setEditable(false);
		}
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			if(e.getSource() == btnAceptar) {
	     		conexion.conectar();   
	     		
	     		//Guardo cada campo con su respectivo valor cambiado en un string
	     		String campos = "";
	     		for (int i = 1; i < columnasTablas.length; i++) {
	     			
	     			if(columnasTablas[i].equals("Año")) {
	     				columnasTablas[i] = "year";
	     			}
	     			
     				campos+= columnasTablas[i].toLowerCase() + " = '" + txtFields.get(i).getText() + "'," ;

				}
	     		
	     		String tabla = grupo;
	     		
	     		if(grupo.equals("mecanico")) {
	     			tabla = "usuario";
	     		}
	     		
	     		
	     		//elimino la ultima coma restante
	     		String camposFinales = campos.substring(0, campos.length()-1);
	     		
	     		//Guardo en un string el sql , segun la tabla (grupo) y segun sus campos 
	     		String sqlActualizar = "UPDATE "+ tabla + " SET " + camposFinales + " WHERE " +  columnasTablas[0] + " = '" + valoresActuales[0] + "'" ;

	     		System.out.println(sqlActualizar);
	     		int filasAfectadas = conexion.ejecutarInsertDeleteUpdate(sqlActualizar);
	     		
	     		if(filasAfectadas == 0) {
	    			JOptionPane.showMessageDialog(this, "Error al actualizar el registro", "Error", JOptionPane.ERROR_MESSAGE);
	    			
	    		}else {
	    			ControladorRegistros.actualizarTablas(grupo, modelTabla);
	    			JOptionPane.showMessageDialog(this, "Actualizacion exitosa ", "Actualizacion exitoso", JOptionPane.INFORMATION_MESSAGE);
	    			dispose(); 
	    		}
	     		 		
			}
		 }catch(Exception e2) {
			 JOptionPane.showMessageDialog(this, e2, "Error", JOptionPane.ERROR_MESSAGE);
			 System.out.println(e2);
		}
		
 	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//Si le da al enter, presiona el boton de aceptar
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			btnAceptar.doClick();
        }else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {//Si le da al escape, pide confirmacion para volver a la ventana anterior y la cierra
        	int confirmacion = JOptionPane.showConfirmDialog(null, "¿Volver atrás?", "Confirmar volver", JOptionPane.YES_NO_OPTION);

    		if (confirmacion == JOptionPane.YES_OPTION) {
    			dispose();
    		}
        }
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}