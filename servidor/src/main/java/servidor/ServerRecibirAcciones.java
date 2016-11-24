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
    String mensaje = "";
    ArrayList<String> mensajes= new ArrayList<String>();
    ObjectMapper mapper = new ObjectMapper();

    //CONSTRUCTOR DEL CHAT
    public ServerRecibirAcciones(Socket socket) {
        this.socket = socket;
    }
    
    public ServerRecibirAcciones() {
        this.socket = null;
    }
    
    @Override
    public void run() {//SOBRECARGAR DE RUN QUE SE REALIZARA CUANDO INICIE EL THREAD CREADO EN "SERVIDOR"
    	Scanner sc;
    	while(true){
			try {
				sc = new Scanner(socket.getInputStream());
				String input = sc.nextLine();
				AccionesAEnviar acciones = mapper.readValue(input, AccionesAEnviar.class);
				//tengo q actualizar el arraylist mensajes del ServerThread con la actulizacion de los valores leidos.
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}
