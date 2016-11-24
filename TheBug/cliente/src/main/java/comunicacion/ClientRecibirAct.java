package comunicacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import jugador.MapaEnlaceDatos;
import jugador.MapaFisico;


public class ClientRecibirAct implements Runnable {
    private Socket socket;
    private Scanner sc;
    private PrintWriter out;
    private String mapaSeleccionado;
    ObjectMapper mapper = new ObjectMapper();

    public ClientRecibirAct(Socket socket, String mapaSeleccionado) {
        this.socket = socket;
        this.mapaSeleccionado=mapaSeleccionado;
    }

    @Override
    public void run() {
    		try {
    			//while(true)
    				recibirDatos();
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 	
    }
    @SuppressWarnings("resource")
	private void recibirDatos() throws JsonParseException, JsonMappingException, IOException {
    	Scanner input;
    	MapaAEnviar mapaEnviado = new MapaAEnviar();
        input = new Scanner(socket.getInputStream());
		String in = input.nextLine();
		mapaEnviado = mapper.readValue(in, MapaAEnviar.class);
    }
}
