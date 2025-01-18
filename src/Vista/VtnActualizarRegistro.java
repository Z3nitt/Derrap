package Vista;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.Conector_BBDD;

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

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNuevoValor;
	private JButton btnAceptar;
	private JComboBox comboBox;
	String [] valoresActuales;
	String grupo;
	Conector_BBDD conexion = new Conector_BBDD();
	
	public VtnActualizarRegistro(String [] columnasTabla, String [] valoresActuales, String grupo) {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1083, 626);
		setResizable(false);
		setIconImage(new ImageIcon(getClass().getResource("/package_assets/icon.png")).getImage());
		setSize(875, 527);
        setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		this.valoresActuales = valoresActuales;
		this.grupo = grupo;
		
		JLabel lblIndicadorDNI = new JLabel("DNI usuario: ");
		lblIndicadorDNI.setFont(new Font("Dialog", Font.BOLD, 18));
		lblIndicadorDNI.setBounds(24, 37, 181, 54);
		getContentPane().add(lblIndicadorDNI);
		
		JLabel lblEleccionCampo = new JLabel("Campo a actualizar:");
		lblEleccionCampo.setFont(new Font("Dialog", Font.BOLD, 18));
		lblEleccionCampo.setBounds(430, 46, 181, 36);
		getContentPane().add(lblEleccionCampo);
		
		JLabel lblValorActual = new JLabel("a");
		lblValorActual.setBounds(206, 165, 201, 16);
		getContentPane().add(lblValorActual);
		
		comboBox = new JComboBox();
		comboBox.setBounds(659, 54, 175, 25);
		
		//Añade cada campo de la tabla seleccionada al combo box
		for (String campo : columnasTabla) {
			if(!campo.equals("DNI")) {
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
		txtNuevoValor.setBounds(206, 239, 337, 36);
		getContentPane().add(txtNuevoValor);
		txtNuevoValor.setColumns(10);
		
		JLabel lblNuevoValor = new JLabel("Nuevo valor:");
		lblNuevoValor.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNuevoValor.setBounds(24, 237, 181, 36);
		getContentPane().add(lblNuevoValor);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Dialog", Font.BOLD, 18));
		btnAceptar.setBounds(653, 394, 181, 63);
		btnAceptar.addActionListener(this);
		getContentPane().add(btnAceptar);
		
		JLabel lblDNIUsuario = new JLabel("dniusuario");
		lblDNIUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblDNIUsuario.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		lblDNIUsuario.setBounds(170, 46, 181, 36);
		lblDNIUsuario.setText(valoresActuales[0]);
		getContentPane().add(lblDNIUsuario);
		
		JLabel lblIndicadorActual = new JLabel("Valor actual: ");
		lblIndicadorActual.setFont(new Font("Dialog", Font.BOLD, 14));
		lblIndicadorActual.setBounds(24, 164, 170, 16);
		getContentPane().add(lblIndicadorActual);
		
		
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
						break;
				
	     		}
	     		int filasAfectadas = conexion.ejecutarInsertDeleteUpdate(sqlActualizar);
	     		if(filasAfectadas == 0) {
	    			JOptionPane.showMessageDialog(this, "Error al actualizar el registro", "Error", JOptionPane.ERROR_MESSAGE);
	    		}else {
	    			JOptionPane.showMessageDialog(this, "Actualizacion exitosa ", "Actualizacion exitoso", JOptionPane.INFORMATION_MESSAGE);
	    			txtNuevoValor.setText("");
	    		}
			}
		 }catch(SQLSyntaxErrorException e2) {
			 JOptionPane.showMessageDialog(this, e2, "Error", JOptionPane.ERROR_MESSAGE);
		 }catch(Exception e3) {
			 System.out.println(e3);
		}
 	}
		
}