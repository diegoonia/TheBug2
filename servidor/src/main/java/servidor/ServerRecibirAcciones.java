package servidor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import org.codehaus.jackson.map.ObjectMapper;

import comunicacion.AccionesAEnviar;
import comunicacion.MapaAEnviar;
import comunicacion.User;
import logica.MapaAlianza;

public class ServerRecibirAcciones implements Runnable {// The Runnable interface should be implemented by any class whose instances are intended to be executed by a thread.
    Socket socket;
    Scanner input;
    Player jugadorRecibido;
    ArrayList<Socket> listaDeConexiones;
    ObjectMapper mapper = new ObjectMapper();

    //CONSTRUCTOR DEL CHAT
    public ServerRecibirAcciones(Socket socket) {
        this.socket = socket;
    }
    
    public ServerRecibirAcciones(Socket socket, ArrayList<Socket> listaDeConexiones) {
        this.socket = null;
    }
    
    @Override
    public void run() {//SOBRECARGAR DE RUN QUE SE REALIZARA CUANDO INICIE EL THREAD CREADO EN "SERVIDOR"
    	Scanner sc;
    	while(true){
			try {
				sc = new Scanner(socket.getInputStream());
				String input = sc.nextLine();
				jugadorRecibido = mapper.readValue(input, Player.class);
				
				
				for (int x = 0; x < this.listaDeConexiones.size(); x++) { // RECORRE TODA LA LISTA DE CONEXIONES DE LA SALA PARA ENVIAR EL MENSAJE RECIBIDO A TODOS.
                    Socket tempSocket = this.listaDeConexiones.get(x);
        			String jsonInString = mapper.writeValueAsString(jugadorAEnviar);
        			PrintWriter out = new PrintWriter(socket.getOutputStream()); 
        			out.println(jsonInString); 
        			out.flush();

                }
				
				
				//tengo q actualizar el arraylist mensajes del ServerThread con la actulizacion de los valores leidos.
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}
