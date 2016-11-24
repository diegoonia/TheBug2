package servidor;

//import org.codehaus.jackson.map.ObjectMapper;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    //MUNDOS DISPONIBLES
    public static ArrayList<Socket> listaDeConexionesMundoFisico = new ArrayList<>();
    public static ArrayList<Socket> listaDeConexionesMundoEnlace = new ArrayList<>();

    
    public static void main(String[] args) throws Exception {

    	try {
            final int PORT = 4445;
            @SuppressWarnings("resource") //no cierro server
			ServerSocket server = new ServerSocket(PORT);
            //ObjectMapper mapper = new ObjectMapper();

            while (true) {
                System.out.println("Esperando un cliente");
                Socket socket = server.accept();

                //CREAR THREAD LOGUEO
                /// De aca para abajo hay q psarlo al thread de logueo
                ServerLog log = new ServerLog(socket,listaDeConexionesMundoFisico,listaDeConexionesMundoEnlace);           
                Thread thread = new Thread(log);
                thread.start();
                /// HASTA ACA
                
            }
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    	
    	
    }
}
