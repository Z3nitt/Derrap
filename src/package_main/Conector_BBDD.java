package package_main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;

public class Conector_BBDD {
	//BASE DE DATOS LOCAL
	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/derrap2?useSSL=false";
	private static final String USUARIO = "root";
	private static final String CLAVE = "1234";
	Connection cn = null;
	Statement stm = null;
	ResultSet rsetresultado = null;
	
	public Connection conexion_correcta() {
		try {
			cn = DriverManager.getConnection(URL, USUARIO, CLAVE);
			//System.out.println("Conectado Correctamente");
			
		} catch (SQLException e) {
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}
		return cn;
	}
	
	public int ejecutarInsertDeleteUpdate (String consulta) throws SQLException{
        Statement stmt = cn.createStatement();
        return stmt.executeUpdate(consulta);
    }
	
	public ResultSet ejecutarSelect (String consulta) throws SQLException{
        Statement stmt = cn.createStatement();
        return stmt.executeQuery(consulta);
	
	
	
	
	
	}
}
