package Controlador;

import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

import Vista.VtnCrearNuevoRegistro;

//Clase que contiene todos los metodos necesarios para utilizar las tablas
public class ControladorRegistros {
	
	public static void actualizarTablas(String grupo, DefaultTableModel modelTabla) {
		Conector_BBDD conexion = new Conector_BBDD();
		conexion.conectar();
		try {
			switch (grupo) {
				case "cliente":
					modelTabla.setRowCount(0);
					ResultSet rsetCliente = conexion.ejecutarSelect("SELECT * FROM cliente");
					while (rsetCliente.next()) {
						// Obtener cada campo del ResultSet y añadir la fila a la tabla
		                Object[] fila = new Object[4];
		                fila[0] = rsetCliente.getString("DNI");
		                fila[1] = rsetCliente.getString("nombre");
		                fila[2] = rsetCliente.getString("apellidos");
		                fila[3] = rsetCliente.getString("telefono");
		                
		                modelTabla.addRow(fila); // Agregar fila al modelo de la tabla
					}
					break;
				case "mecanico":
					modelTabla.setRowCount(0);
					ResultSet rsetMecanico = conexion.ejecutarSelect("SELECT * FROM usuario where rol = 'Mecanico' ");
					while (rsetMecanico.next()) {
			                
			                Object[] fila = new Object[5];
			                fila[0] = rsetMecanico.getString("DNI");       
			                fila[1] = rsetMecanico.getString("nombre");    
			                fila[2] = rsetMecanico.getString("apellidos");          
			                fila[3] = rsetMecanico.getString("password");           
			                fila[4] = rsetMecanico.getString("estado");       
			                
			                modelTabla.addRow(fila); 
			        }
					break;
				case "vehiculo":
					modelTabla.setRowCount(0);
					ResultSet rsetVehiculos = conexion.ejecutarSelect("SELECT * FROM vehiculo");
					while (rsetVehiculos.next()) {
		                Object[] fila = new Object[8];
		                fila[0] = rsetVehiculos.getString("matricula");       
		                fila[1] = rsetVehiculos.getString("marca");    
		                fila[2] = rsetVehiculos.getString("modelo");          
		                fila[3] = rsetVehiculos.getString("color");           
		                fila[4] = rsetVehiculos.getString("combustible");     
		                fila[5] = rsetVehiculos.getString("kilometros");           
		                fila[6] = rsetVehiculos.getString("year");  
		                fila[7] = rsetVehiculos.getString("dni_cliente");             
		                
		                modelTabla.addRow(fila); 
					}
					break;
				case "orden":
					modelTabla.setRowCount(0);
					ResultSet rsetOrden = conexion.ejecutarSelect("SELECT * FROM orden");
					while (rsetOrden.next()) {
		                Object[] fila = new Object[8];
		                fila[0] = rsetOrden.getString("id_orden");       
		                fila[1] = rsetOrden.getString("dni_cliente");    
		                fila[2] = rsetOrden.getString("matricula_vehiculo");                    
		                modelTabla.addRow(fila); 
					}
					break;
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	public static void crearRegistro(String grupo, DefaultTableModel modelTabla) {
		//Pasa el grupo actual y el modelo de la tabla a la ventana de crear nuevo registro
		VtnCrearNuevoRegistro vtnCrearNuevoRegistro = new VtnCrearNuevoRegistro(grupo, modelTabla);
		vtnCrearNuevoRegistro.setVisible(true);
	}
	
}
