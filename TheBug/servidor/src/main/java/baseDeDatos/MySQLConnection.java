package baseDeDatos;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;


public class MySQLConnection {

	private static Connection conn;
	private static java.sql.Statement sentencia;

	public MySQLConnection() {
	}
	
	public Connection getConnection() {
		try {
			if(conn == null) {	
				String driver="com.mysql.jdbc.Driver"; //el driver varia segun la BD que usemos
				
				/*
				 * PARA LOGIN DE http://www.freesqldatabase.com/
				 * email: matias.devanna@gmail.com
				 * contra: matiasdevanna
				 * 
				 * DESPUES TENES QUE IR A "hpMyAdmin for database administration Follow this link for phpMyAdmin" QUE 
				 * ESTA POR EL MEDIO DE LA PANTALLA
				 * 
				 * EN LA VENTANA NUEVA QUE TE ABRE POONES ESTO
				 * 
				 * SERVIDOR:   sql6.freesqldatabase.com
				 * Database user: sql6143481
				   Database password: JtJ4f8uerx
				 * 
				 * 
				 * */

			    String serverName = "sql6.freesqldatabase.com";
			    String mydatabase = "sql6143481";
			    String url = "jdbc:mysql://" + serverName + "/" + mydatabase; 
				String pwd="JtJ4f8uerx";
				String usr="sql6143481";
				Class.forName(driver);
				conn = DriverManager.getConnection(url,usr,pwd);
				System.out.println("Se realizó la conexión con la BD con éxito");
			}
			else{
				System.out.println("La conexión se encuentra realizada.");
			}
		} catch (ClassNotFoundException cnfe) {
			System.out.println("No se encuentra el Driver.");
		} catch (SQLException sqle) {
			System.out.println("Error al intentar la conexión.");
		}	
		return conn;
	}
	
	public void close() {
		try {
			if(conn != null) {
				conn.close();
				conn = null;
				System.out.println("Desconexión de la BD exitosa.");
			}
		} catch (SQLException sqle) {
			System.out.println("Error al intentar la conexión.");
		}
	}
	
	public int registrarse (String usuario, String password) throws SQLException{
		sentencia = null;
		
		try {
			System.out.println("Me Registro?");
			sentencia = conn.createStatement(); 
			String query = "INSERT INTO `usuarios`(`usuario`, `contraseña`)  VALUES(\""+usuario+"\",\""+password+"\")";
			sentencia.execute(query);
			
			System.out.println("Se inserto correctamente");
			return 1;
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "No logro ejecutar Insertar Correctamente la consulta","Error", JOptionPane.ERROR_MESSAGE);
			return 0;
		}
		
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "No logro ejecutar Insertar Correctamente la consulta","Error", JOptionPane.ERROR_MESSAGE);
			return 0;
		}	
	}

	public int verificarExistenciaUsuario(String usuario) throws SQLException {
		sentencia = null;
		boolean respuesta = false;
		
		try {
			
			sentencia = conn.createStatement(); 
			String query = "SELECT * FROM usuarios WHERE usuario = \""+usuario+ "\"";
			ResultSet rs = sentencia.executeQuery(query);
			
			while(rs.next()){
				respuesta = true;
			}
			if(respuesta == true){
				System.out.println("Usuario existente");
				return 1;
			}
			else{
				System.out.println("Usuario Inexistente");
				return 0;
			}	
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "No se logro establecer conexión con la BD","Error", JOptionPane.ERROR_MESSAGE);
			return 0;
		}
		
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "No se logro establecer conexión con la BD","Error", JOptionPane.ERROR_MESSAGE);
			return 0;
		}	
	}
	
	public int verificarUserYPassword(String usuario, String password) throws SQLException {
		sentencia = null;
		boolean respuesta = false;
		
		try {
			
			sentencia = conn.createStatement(); 
			String query = "SELECT * FROM usuarios WHERE usuario = \""+usuario+"\" and contraseña = \""+password+"\"";
			ResultSet rs = sentencia.executeQuery(query);
			
			while(rs.next()){
				respuesta = true;
			}
			if(respuesta == true){
				System.out.println("Acceso Permitido");
				return 1;
			}
			else{
				System.out.println("Acceso Denegado");
				return 0;
			}
		
			
		}
		catch(SQLException ex){
			JOptionPane.showMessageDialog(null, "No se logro establecer conexión con la BD","Error", JOptionPane.ERROR_MESSAGE);
			return 0;
		}
		
		catch(Exception e){
			JOptionPane.showMessageDialog(null, "No se logro establecer conexión con la BD","Error", JOptionPane.ERROR_MESSAGE);
			return 0;
		}
		
	}
	
	public static void main(String[] args) throws SQLException {
		
		MySQLConnection mySQLCon = new MySQLConnection();
		mySQLCon.getConnection();
		
		//conexionBD.consultar("select * from usuarios");
		//mySQLCon.registrarse("jorge67","contragfg");
		
		
		mySQLCon.verificarUserYPassword("ivan","12345");
		mySQLCon.verificarExistenciaUsuario("jorge");
		mySQLCon.close();
	}

	


	
}
