package comunicacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import jugador.MapaEnlaceDatos;
import jugador.MapaFisico;


public class ClientAcciones implements Runnable {
    private Socket socket;
    private Scanner sc;
    private PrintWriter out;
    private String mapaSeleccionado;
    ObjectMapper mapper = new ObjectMapper();

    public ClientAcciones(Socket socket, String mapaSeleccionado) {
        this.socket = socket;
        this.mapaSeleccionado=mapaSeleccionado;
    }

    @Override
    public void run() {
			try {
				//while(true)
					enviarDatos();
			} catch (JsonGenerationException e) {
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
    public void enviarDatos() throws JsonGenerationException, JsonMappingException, IOException {
    	AccionesAEnviar acciones = new AccionesAEnviar();
        String jsonInString = mapper.writeValueAsString(acciones);
        PrintWriter out = new PrintWriter(socket.getOutputStream()); //OBTENGO EL CANAL DE SALIDA DEL SOCKET HACIA EL SERVIDOR
        out.println(jsonInString); 
        out.flush();
    }

    public void desconectar() throws Exception {
        this.out.println(" se ha retirado de la sala");
        this.out.flush();
        this.socket.close();
        System.exit(0);
    }
}
