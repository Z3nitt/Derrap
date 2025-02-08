package Controlador;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Vista.VtnActualizarRegistro;
import Vista.VtnCrearNuevoRegistro;

//Clase que contiene todos los metodos necesarios para utilizar las tablas
public class ControladorRegistros {
	
	public static void actualizarTablas(String grupo, DefaultTableModel modelTabla) {
		Conector_BBDD conexion = new Conector_BBDD();
		conexion.conectar();
		try {
			
			//En el caso que el grupo sea mecanicos, cambio el nombre de la tabla a usuarios y agrego la condiicon que tienen que ser mecanicos (no admins)
			if(grupo.equals("mecanico")) {
				grupo = "usuario WHERE rol = 'Mecanico'";
			}//Si el grupo es "ordenesActivas" mostrara solo las ordenes con el estado "Activa"
			else if(grupo.equals("ordenesActivas")) {
				grupo = "orden WHERE estado = 'Activa'";
			}
			
			//Guardo la consulta con la tabla correspondiente
			String consultaSql = "SELECT * FROM "+ grupo;
			
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
			
			//En el caso que el grupo sea mecanicos, cambio el nombre de la tabla a usuarios
			if(grupo.equals("mecanico")) {
				grupo = "usuario";
			}
			
			//Guardo el texto del usuario que esta buscando
			String textoBuscador = buscador.getText().trim();
			
			//Guardo la consulta con la tabla correspondiente y luego agrego las condiciones correspondientes
			String consultaSql = "SELECT * FROM "+grupo+" ";
			
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
	
	public static void borrarRegistro(String grupo, DefaultTableModel modelTabla, JTable tabla) {
		//FALTA COMPLETAR QUE SI UN CLIENTE TIENE UN VEHICULO ASIGNADO Y QUIERA ELIMINARSE HACER UN ON DELETE CASCADE O AVISAR QUE NO PUEDE HACERLO
		Conector_BBDD conexion = new Conector_BBDD();
		
		int registroSeleccionado=0;
		//Voy guardando en un String el sql del Delete
		String sqlBorrar="DELETE FROM " + grupo + " ";
		
		switch(grupo) {
			case "cliente":
				sqlBorrar+="WHERE DNI = ";
				break;
			case "mecanico":
				sqlBorrar+="WHERE DNI = ";
				break;
			case "vehiculo":
				sqlBorrar+="WHERE matricula = ";
				break;
			case "orden":
				sqlBorrar+="WHERE id_orden = ";
				break;
			case "repuesto":
				sqlBorrar+="WHERE id_repuesto = ";
				break;
		}
		
		//Guardo el registro seleccionado de la tabla
		registroSeleccionado = tabla.getSelectedRow();
		
		//Guardo la pk del registro
		Object primaryKeyTabla = tabla.getValueAt(registroSeleccionado, 0);
		
		//Agrego la pk al sql para borrar el registro adecuado
		sqlBorrar+="'" + primaryKeyTabla +"'";
		
		//Pido confirmacion para borrar
		int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas eliminar esta fila?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
		if(confirmacion == JOptionPane.YES_OPTION) {
			
			//Borro el registro de la tabla
			modelTabla.removeRow(registroSeleccionado);
			
			conexion.conectar();
			try {
				//ejecuto el delete, si se afectaron filas significa que se borro correctamente el registro
				int filasAfectadas = conexion.ejecutarInsertDeleteUpdate(sqlBorrar);
				
				if(filasAfectadas == 0) {
					JOptionPane.showMessageDialog(null, "Error al borrar el registro", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Registro borrado ", "Borrado exitoso", JOptionPane.INFORMATION_MESSAGE);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	} 
		
	public static void actualizarRegistro(String grupo, DefaultTableModel modelTabla, JTable tabla) {
		
		//Creo un ArrayList 
		ArrayList<String> columnas = new ArrayList<String>();
		
		//Segun el grupo que este seleccionado, guardo las columnas de la tabla correspondiente
		switch(grupo) {
		case "cliente":
			columnas.addAll(Arrays.asList("DNI","Nombre", "Apellidos", "Telefono"));
			break;
		case "mecanico":
			columnas.addAll(Arrays.asList("DNI", "Nombre", "Apellidos", "Password", "Estado"));
			break;
		case "vehiculo":
			columnas.addAll(Arrays.asList("matricula", "Marca", "Modelo", "Color", "Combustible", "Kilometros", "Año", "DNI_cliente"));
			break;
		case "orden":
			columnas.addAll(Arrays.asList("id_orden", "Estado", "matricula_vehiculo", "cliente_dni"));
			break;
		case "repuesto":
			columnas.addAll(Arrays.asList("ID_Repuesto", "Nombre", "Cantidad", "Precio_Compra", "Precio_Venta", "Mano_de_Obra", "ID_Proveedor"));
			break;
		case "factura":
			columnas.addAll(Arrays.asList("ID_Factura", "Precio", "Fecha", "ID_Orden"));
			break;
		}
		
		//Convierto el array list en un array de String
		String[] columnasTabla = columnas.toArray(new String[0]);
		
		//Guardo el registro seleccionado de la tabla
		int registroSeleccionado = tabla.getSelectedRow();
		
		//Creo un nuevo array de String con el tamaño de la cantidad de columnas que tiene la tabla
		String[] valoresActualesRegistro = new String [columnasTabla.length];
		
		//Recorro las columnas de la tabla para ir guardando los valores que tiene el registro en el array
		for (int i = 0; i < columnasTabla.length; i++) {
			valoresActualesRegistro[i] = (String) tabla.getValueAt(registroSeleccionado, i);
		}
		
		//Llamo al constructor de la ventana de actualizar registros y le paso las columnas, los valores, el grupo y el modelo actual
		VtnActualizarRegistro vtnActualizarRegistro = new VtnActualizarRegistro(columnasTabla, valoresActualesRegistro, grupo, modelTabla);
		vtnActualizarRegistro.setVisible(true);
			
	}
}
