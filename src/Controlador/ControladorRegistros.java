package Controlador;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Vista.VtnCrearNuevoRegistro;

//Clase que contiene todos los metodos necesarios para utilizar las tablas
public class ControladorRegistros {
	
	public static void actualizarTablas(String grupo, DefaultTableModel modelTabla) {
		Conector_BBDD conexion = new Conector_BBDD();
		conexion.conectar();
		try {
			//Guardo el nombre del grupo en una nueva variable
			String tabla = grupo;
			
			//En el caso que el grupo sea mecanicos, cambio el nombre de la tabla a usuarios
			if(grupo.equals("mecanico")) {
				tabla = "usuario";
			}
			
			//Guardo la consulta con la tabla correspondiente
			String consultaSql = "SELECT * FROM "+tabla;
			
			//Limpio la tabla
			modelTabla.setRowCount(0);
			
			ResultSet rset = conexion.ejecutarSelect(consultaSql);
			
			//Obtengo los metadatos de la consulta
			ResultSetMetaData metaData = rset.getMetaData();
			
			//Guardo el numero de columnas
			int numColumnas = metaData.getColumnCount();
			
			//Recorro los resultados obtenidos
			while (rset.next()) {
				//Por cada registro creo un array de objetos
	            Object[] fila = new Object[numColumnas];
	            
	            //Obtengo todos los valores de las columnas
	            for (int i = 1; i <= numColumnas; i++) { 
	                fila[i - 1] = rset.getString(i);
	            }
	            //Agrego la fila a la tabla
	            modelTabla.addRow(fila); 
            }
			
		}catch(Exception e) {
			System.out.println("Error al actualizar la tabla: " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public static void crearRegistro(String grupo, DefaultTableModel modelTabla) {
		//Pasa el grupo actual y el modelo de la tabla a la ventana de crear nuevo registro
		VtnCrearNuevoRegistro vtnCrearNuevoRegistro = new VtnCrearNuevoRegistro(grupo, modelTabla);
		vtnCrearNuevoRegistro.setVisible(true);
	}
	
	//Metodo para buscar en la tabla, recibe por parametros el grupo, el modelo de la tabla y el JTextField buscador de cada tabla
	public static void buscarRegistros(String grupo, DefaultTableModel modelTabla, JTextField buscador) {
		Conector_BBDD conexion = new Conector_BBDD();
		conexion.conectar();
		try {
			
			//Guardo el nombre del grupo en una nueva variable
			String tabla = grupo;
			
			//En el caso que el grupo sea mecanicos, cambio el nombre de la tabla a usuarios
			if(grupo.equals("mecanico")) {
				tabla = "usuario";
			}
			
			//Guardo el texto del usuario que esta buscando
			String textoBuscador = buscador.getText().trim();
			
			//Guardo la consulta con la tabla correspondiente y luego agrego las condiciones correspondientes
			String consultaSql = "SELECT * FROM "+tabla+" ";
			
			//Segun el grupo que este seleccionado, cambia la condicion de la consulta
			switch (grupo) {
				case "cliente": 
					consultaSql += "WHERE nombre LIKE '%" + textoBuscador + "%'";
					break;
 				case "mecanico":
 					consultaSql += "WHERE rol = 'Mecanico' AND nombre LIKE '%" + textoBuscador + "%'";
					break;
				case "vehiculo":
					consultaSql +=  "WHERE matricula LIKE '%" + textoBuscador + "%'";
					break;
				case "orden":
					consultaSql += "WHERE id_orden LIKE '%" + textoBuscador + "%'";
					break;
				case "repuesto":
					consultaSql += "WHERE id_repuesto LIKE '%" + textoBuscador + "%'";
					break;
				case "facturas":
					consultaSql += "WHERE id_factura LIKE '%" + textoBuscador + "%'";
					
					break;
			}
			
			//Limpio la tabla
			modelTabla.setRowCount(0);
			
			ResultSet rset = conexion.ejecutarSelect(consultaSql);
			
			//Obtengo los metadatos de la consulta
			ResultSetMetaData metaData = rset.getMetaData();
			
			//Guardo el numero de columnas
			int numColumnas = metaData.getColumnCount();
			
			//Recorro los resultados obtenidos
			while (rset.next()) {
				//Por cada registro creo un array de objetos
	            Object[] fila = new Object[numColumnas];
	            
	            //Obtengo todos los valores de las columnas
	            for (int i = 1; i <= numColumnas; i++) { 
	                fila[i - 1] = rset.getString(i);
	            }
	            //Agrego la fila a la tabla
	            modelTabla.addRow(fila); 
            }
			
		} catch (Exception e) {
			System.out.println(e);
		} 
	}
	
	
}
