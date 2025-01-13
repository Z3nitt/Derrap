package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conector_BBDD {
	//BASE DE DATOS LOCAL
	private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/derrapev4?useSSL=false";
	private static final String USUARIO = "root";
	private static final String CLAVE = "root";
	Connection connection = null;
	Statement stm = null;
	ResultSet rsetresultado = null;
	
	private void registrarDriver() throws SQLException {
        try{
             Class.forName(CONTROLADOR);
        }catch(ClassNotFoundException e){
            throw new SQLException("Error");
        }
    }
       
    public void conectar() {
    	try {
    		if((connection == null) || connection.isClosed()){
                registrarDriver();
                connection = (Connection) DriverManager.getConnection(URL, USUARIO, CLAVE);
           }
		} catch (SQLException e) {
			System.out.println(e);
		}
        
    }
       
    public void desconectar() throws SQLException{
         if((connection != null) && !connection.isClosed()){
             connection.close();
        }
    }

    public ResultSet ejecutarSelect(String consulta) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(consulta);
    }

    public int ejecutarInsertDeleteUpdate(String consulta) throws SQLException{
        Statement stmt = connection.createStatement();
        return stmt.executeUpdate(consulta);
    }
}
