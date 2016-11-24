package servidor;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import baseDeDatos.SQLiteJDBC;
import comunicacion.User;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class ServerLog implements Runnable {// The Runnable interface should be implemented by any class whose instances are intended to be executed by a thread.
    Socket socket;
    Scanner input;
    public  ArrayList<Socket> listaDeConexionesMundoFisico = new ArrayList<>();
    public  ArrayList<Socket> listaDeConexionesMundoEnlace = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();
    public SQLiteJDBC mySQLCon;
    
    public ServerLog(Socket socket, ArrayList<Socket> listaDeConexionesMundo1,ArrayList<Socket> listaDeConexionesMundo2) {
        this.socket = socket;
        this.listaDeConexionesMundoFisico=listaDeConexionesMundo1;
        this.listaDeConexionesMundoEnlace=listaDeConexionesMundo2;
    }

    @SuppressWarnings("resource")
	@Override
    public void run() {//SOBRECARGAR DE RUN QUE SE REALIZARA CUANDO INICIE EL THREAD CREADO EN "SERVIDOR"
		try {
			Scanner sc;
			sc = new Scanner(socket.getInputStream());
			String input = sc.nextLine();
			User user = mapper.readValue(input, User.class);
			String accion = user.getAccion() ;
			mySQLCon = new SQLiteJDBC();
			
			//Si el cliente toca ingresar
			if(accion.compareTo("login")==0)
			{
				login(user);
			}
			
			if(accion.compareTo("registrar")==0)
			{
				String userEnviado = user.getNombre() ;
				String passEnviada= user.getPass() ;
				
				//////////CONEXION CON LA BASE DE DATOS////////////
				mySQLCon = new SQLiteJDBC();
				mySQLCon.getConnection();
				//////////////////////////////////////////////////
				
				//Registro un nuevo usuario en la Base de Datos
				if(mySQLCon.registrarse(userEnviado, passEnviada)==1)
				{
					mySQLCon.close();
					login(user);
				}
				else
				{
					System.out.println("Error al registrar");
					User userAEnviar = new User(null,null,"errorRegistro",null,null,0);
				    String jsonInString = mapper.writeValueAsString(userAEnviar);
				    PrintWriter out = new PrintWriter(socket.getOutputStream()); //OBTENGO EL CANAL DE SALIDA DEL SOCKET HACIA EL SERVIDOR
				    out.println(jsonInString); 
				    out.flush();
					socket.close();
					
				}
				
			}
			//ServerThread chat;
			
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	@SuppressWarnings("resource")
	private void login(User user)
			throws SQLException, IOException, JsonGenerationException, JsonMappingException, JsonParseException {
		String userEnviado = user.getNombre() ;
		String passEnviada = user.getPass() ;
		System.out.println("Llega el usuario con la accion: "+userEnviado+user.getAccion());

		
		//////////CONEXION CON LA BASE DE DATOS////////////
		mySQLCon = new SQLiteJDBC();
		mySQLCon.getConnection();
		///////////////////////////////////////////////////
		
		
		if(mySQLCon.verificarUserYPassword(userEnviado, passEnviada) == 1)
		{
			mySQLCon.close(); //CIERRO LA CONEXIÓN
			
		    User userAEnviar = new User(passEnviada,userEnviado,"abrirSeleccionMundo",this.listaDeConexionesMundoFisico,this.listaDeConexionesMundoEnlace,0);
		    String jsonInString = mapper.writeValueAsString(userAEnviar);
		    PrintWriter out = new PrintWriter(socket.getOutputStream()); //OBTENGO EL CANAL DE SALIDA DEL SOCKET HACIA EL SERVIDOR
		    out.println(jsonInString); 
		    out.flush();

			//mando array con mundos     
			///CREO THERAD SEGUN EL MUNDO

			Scanner sc2;
		    do{ 
            	sc2 = new Scanner(socket.getInputStream());
            	
            }while (sc2.hasNext()==false);
            
		    
		/*
		    Scanner sc2;
			sc2 = new Scanner(socket.getInputStream());
			 if (sc2.hasNextLine()) 
				 System.out.println("sc2ok"); 
			 else 
				 System.out.println("sc2mal");
	
		*/ 
		
			String input2 = sc2.nextLine();
			User user2 = mapper.readValue(input2, User.class);
			String accion2 = user2.getAccion() ;
		    System.out.println(accion2);
		    
		    
		    if(accion2.compareTo("oprimioCerrar")==0){
		    	System.out.println("Se ha desconectado: " + user2.getNombre());
		    }
		    
		    if(accion2.compareTo("entrarAMundo")==0){
				
				ServerThread partida;
				int mundoSeleccionado = user2.getMundoSelec();
				 switch (mundoSeleccionado) {
		         case 1:
		        	 System.out.println("Entre al mundo Fisico");
		        	 listaDeConexionesMundoFisico.add(socket);
		        	 partida = new ServerThread(socket, listaDeConexionesMundoFisico, user2.getNombre());
		             Thread nuevoProcesoParalelo1 = new Thread(partida);
		             nuevoProcesoParalelo1.start();
		             break;
		         case 2:
		        	 System.out.println("Entre al mundo Enlace de Datos");
		        	 listaDeConexionesMundoEnlace.add(socket);
		             partida = new ServerThread(socket, listaDeConexionesMundoEnlace, user2.getNombre());
		             Thread nuevoProcesoParalelo2 = new Thread(partida);
		             nuevoProcesoParalelo2.start();
		             break;
		         default:
		             break;
		             }
				
			}
		    else
		    	System.out.println("Se ha desconectado: " + user2.getNombre());
		    
		}
		else {
			User userAEnviar = new User(null, null, "error", null,null, 0);
			
			String jsonInString = mapper.writeValueAsString(userAEnviar);
			PrintWriter out = new PrintWriter(socket.getOutputStream()); 
			out.println(jsonInString); 
			out.flush();
		}
		
	}
    

}
