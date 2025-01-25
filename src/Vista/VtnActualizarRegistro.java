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
import java.sql.SQLSyntaxErrorException;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class VtnActualizarRegistro extends JFrame implements ActionListener {
	Background fondoPantalla = new Background();

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNuevoValor;
	private JButton btnAceptar;
	private JComboBox comboBox;
	private DefaultTableModel modelTabla;
	private String [] valoresActuales;
	private String grupo;
	
	Conector_BBDD conexion = new Conector_BBDD();
	
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
		this.grupo = grupo;
		this.modelTabla = modelTabla;
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		fondoPantalla.setLayout(null);
		setContentPane(fondoPantalla);
		
		JLabel lblIndicadorDNI = new JLabel("DNI Del Usuario: ");
		lblIndicadorDNI.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIndicadorDNI.setBounds(39, 137, 127, 45);
		getContentPane().add(lblIndicadorDNI);
		
		JLabel lblEleccionCampo = new JLabel("Dato a actualizar:");
		lblEleccionCampo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEleccionCampo.setBounds(431, 141, 144, 36);
		getContentPane().add(lblEleccionCampo);
		
		JLabel lblValorActual = new JLabel("a");
		lblValorActual.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblValorActual.setBounds(219, 282, 170, 16);
		getContentPane().add(lblValorActual);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 11));
		comboBox.setBounds(610, 148, 175, 25);
		
		//Añade cada campo de la tabla seleccionada al combo box
		for (String campo : columnasTabla) {
			if(!campo.equals("DNI") && !campo.equals("Matricula")) {
				comboBox.addItem(campo);  
			}
        }
		lblValorActual.setText(valoresActuales[1]);
		
		//segun el item del combo box que se seleccione, se mostrara el valor actual del mismo
		comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
            	
                String itemSeleccionado = (String) comboBox.getSelectedItem();
                
                // Encontrar el índice de la columna seleccionada
                int indiceValor = -1;
                for (int i = 0; i < columnasTabla.length; i++) {
                    if (columnasTabla[i].equals(itemSeleccionado)) {
                    	indiceValor = i;
                        break;
                    }
                }
                // Mostrar el valor actual correspondiente
                if (indiceValor != -1) {
                	lblValorActual.setText(valoresActuales[indiceValor]); 
                }
            }
        });
		
		getContentPane().add(comboBox);
		
		txtNuevoValor = new JTextField();
		txtNuevoValor.setBounds(610, 283, 175, 25);
		getContentPane().add(txtNuevoValor);
		txtNuevoValor.setColumns(10);
		
		JLabel lblNuevoValor = new JLabel("Nuevo valor:");
		lblNuevoValor.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNuevoValor.setBounds(431, 271, 144, 36);
		getContentPane().add(lblNuevoValor);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAceptar.setBounds(679, 421, 156, 45);
		btnAceptar.addActionListener(this);
		getContentPane().add(btnAceptar);
		
		JLabel lblDNIUsuario = new JLabel("dniusuario");
		lblDNIUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblDNIUsuario.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDNIUsuario.setBounds(165, 141, 181, 36);
		lblDNIUsuario.setText(valoresActuales[0]);
		getContentPane().add(lblDNIUsuario);
		
		JLabel lblIndicadorActual = new JLabel("Valor actual: ");
		lblIndicadorActual.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblIndicadorActual.setBounds(39, 280, 127, 16);
		getContentPane().add(lblIndicadorActual);
		
		JLabel lblTitulo = new JLabel("ACTUALIZAR REGISTRO");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTitulo.setBounds(39, 27, 280, 36);
		getContentPane().add(lblTitulo);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			if(e.getSource() == btnAceptar) {
	     		String valorIntroducido = txtNuevoValor.getText();
	     		String itemSeleccionado = (String) comboBox.getSelectedItem();
	     		String sqlActualizar="";
	     		conexion.conectar();
	     		switch (grupo) {
					case "clientes":
						sqlActualizar="UPDATE cliente SET " + itemSeleccionado.toLowerCase() + " = '" + valorIntroducido + "' WHERE DNI = '" + valoresActuales[0] + "'";
						break;
					case "mecanicos":
						//Corrige diferencias entre la bd y la tabla
			     		if(itemSeleccionado.equalsIgnoreCase("Contraseña")) {
			     			itemSeleccionado = "contrasenia";
			     		}
			     		sqlActualizar="UPDATE usuario SET " + itemSeleccionado.toLowerCase() + " = '" + valorIntroducido + "' WHERE DNI = '" + valoresActuales[0] + "'";		     		
			     		break;
					case "vehiculos":
			     		sqlActualizar="UPDATE vehiculo SET " + itemSeleccionado.toLowerCase() + " = '" + valorIntroducido + "' WHERE matricula = '" + valoresActuales[0] + "'";		     		
						break;
				
	     		}
	     		int filasAfectadas = conexion.ejecutarInsertDeleteUpdate(sqlActualizar);
	     		if(filasAfectadas == 0) {
	    			JOptionPane.showMessageDialog(this, "Error al actualizar el registro", "Error", JOptionPane.ERROR_MESSAGE);
	    		}else {
	    			ControladorRegistros.actualizarTablas(grupo, modelTabla);
	    			JOptionPane.showMessageDialog(this, "Actualizacion exitosa ", "Actualizacion exitoso", JOptionPane.INFORMATION_MESSAGE);
	    			dispose();
	    		}
			}
		 }catch(SQLSyntaxErrorException e2) {
			 JOptionPane.showMessageDialog(this, e2, "Error", JOptionPane.ERROR_MESSAGE);
		 }catch(Exception e3) {
			 System.out.println(e3);
		}
 	}
}