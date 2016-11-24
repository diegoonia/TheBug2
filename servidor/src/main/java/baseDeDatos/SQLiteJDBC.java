package baseDeDatos;

import java.sql.*;

import javax.swing.JOptionPane;

public class SQLiteJDBC
{
	private static Connection conn;
	private static java.sql.Statement sentencia;
	
	public SQLiteJDBC(){
		
	}
	
	public Connection getConnection() {
		try {
			if(conn == null) {	
				
				Class.forName("org.sqlite.JDBC");
			    conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			    conn.setAutoCommit(false);
				System.out.println("Se realizó la conexión con la BD con éxito");
				
			}
			else{
				System.out.println("La conexión ya se encuentra realizada.");
			}
		} catch (ClassNotFoundException cnfe) {
			System.out.println("No se encuentra el Driver.");
		} catch (SQLException sqle) {
			System.out.println("Error al intentar la conexión.");
		}	
		return conn;
	}
	

	

	public int registrarse(String usuario, String password) throws SQLException {
		sentencia = null;
		try {
			sentencia = conn.createStatement();
			String query = "INSERT INTO `usuarios`(`usuario`, `contraseña`)  VALUES(\"" + usuario + "\",\"" + password+ "\")";
			sentencia.execute(query);
			sentencia.close();
			conn.commit();
			System.out.println("Se inserto con exito");
			return 1;
			
		} catch (SQLException ex) {
			//JOptionPane.showMessageDialog(null, "No logro ejecutar Insertar Correctamente la consulta", "Error",JOptionPane.ERROR_MESSAGE);
			return 0;
		}

		catch (Exception e) {
			//JOptionPane.showMessageDialog(null, "No logro ejecutar Insertar Correctamente la consulta", "Error", JOptionPane.ERROR_MESSAGE);
			return 0;
		}
	}
  

 
    public int verificarUserYPassword(String usuario, String password) throws SQLException 
    {
      sentencia = null;
      boolean respuesta = false;
		
		try {
			
			sentencia = conn.createStatement(); 
			String query = "SELECT * FROM usuarios WHERE usuario = \""+usuario+"\" and contraseña = \""+password+"\"";
			ResultSet rs = sentencia.executeQuery(query);
			
			while(rs.next()){
				respuesta = true;
				rs.close();
		        sentencia.close();
			}
			if(respuesta == true){
				System.out.println("Acceso Permitido! (^.^)");
				return 1;
			}
			else{
				System.out.println("Acceso Denegado! (O_O)");
				return 0;
			}
		
			
		}
		catch(SQLException ex){
			//JOptionPane.showMessageDialog(null, "No se logro establecer conexión con la BD","Error", JOptionPane.ERROR_MESSAGE);
			return 0;
		}
		
		catch(Exception e){
			//JOptionPane.showMessageDialog(null, "No se logro establecer conexión con la BD","Error", JOptionPane.ERROR_MESSAGE);
			return 0;
		}
		
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
    
	
	public static void crearTabla(){
		
		try {
	        sentencia = conn.createStatement();
	        String sql = "CREATE TABLE usuarios (usuario CHAR(15) PRIMARY KEY,contraseña CHAR(15))";
	        sentencia.executeUpdate(sql);
	        sentencia.close();

	      } catch ( Exception e ) {
	        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	        System.exit(0);
	      }
	      System.out.println("Tabla creada con EXITO!");
		
		
		
	}
	
    public static void main( String args[] ) throws SQLException
    {
  	  
  	  SQLiteJDBC mySQLCon = new SQLiteJDBC();
  	  mySQLCon.getConnection();
  		
  		//conexionBD.consultar("select * from usuarios");
  	  //mySQLCon.registrarse("ivan","123");
  		
  		
  		mySQLCon.verificarUserYPassword("ivan","123");
  		//mySQLCon.verificarExistenciaUsuario("ivan");
  		mySQLCon.close();
      
    }
  
}
  
  
  





