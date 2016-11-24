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
import comunicacion.Jugador;
import comunicacion.MapaAEnviar;
import logica.MapaAlianza;
import logica.MapaLogico;
import logica.MapaObstaculos;

public class ServerThread implements Runnable {// The Runnable interface should be implemented by any class whose instances are intended to be executed by a thread.
    Socket socket;
    Scanner sc;
    PrintWriter out;
    ObjectMapper mapper = new ObjectMapper();
    ArrayList<Socket> listaDeConexiones = new ArrayList<>();
    Jugador jugadorParaActualizar;
    String nickName;

    
    public ServerThread(Socket socket, ArrayList<Socket> listaDeSala, String alias) {
        this.socket = socket;
        this.listaDeConexiones = listaDeSala;
        this.nickName = alias;
        //mo= new MapaObstaculos(32,32, 0.4);
        
        
    }
    public boolean estaConectado() throws IOException {
        if (!this.socket.isConnected()) {// SI EL SOCKET ESTA DESCONECTADO LO ELIMINA DE MI LISTA DE CONEXIONES.
            for (int x = 0; x < this.listaDeConexiones.size(); x++) {
                if (this.listaDeConexiones.get(x) == this.socket) {
                    this.listaDeConexiones.remove(x);
                }
            }
            return false;
        }
        return true;
    }

    @Override
    public void run() {//SOBRECARGAR DE RUN QUE SE REALIZARA CUANDO INICIE EL THREAD CREADO EN "SERVIDOR"
   
    	try {/*
            this.sc = new Scanner(this.socket.getInputStream()); // OBTENGO EL CANAL DE ENTRADA DEL SOCKET

                if (this.estaConectado()) { // VERIFICO QUE EL SOCKET ESTE CONECTADO, SI NO LO ESTA CIERRO ESE SOCKET.
                    if (!this.sc.hasNext()) { // SI NO HA TIENE MENSAJE ACTUAL BUCLEO A LA ESPERA DE UNO
                        return;
                    }
                } //Nano, i'm so sorry.
                */
                while(true){
                		
        				sc = new Scanner(socket.getInputStream());
        				String input = sc.nextLine();
        				jugadorParaActualizar = mapper.readValue(input, Jugador.class);
        				//jugadorParaActualizar.mostrarUbicacion();
        				
        				
        				for (int x = 0; x < this.listaDeConexiones.size(); x++) { // RECORRE TODA LA LISTA DE CONEXIONES DE LA SALA PARA ENVIAR EL MENSAJE RECIBIDO A TODOS.
                            Socket tempSocket = this.listaDeConexiones.get(x);
                			String jsonInString = mapper.writeValueAsString(jugadorParaActualizar);
                			out = new PrintWriter(tempSocket.getOutputStream()); 
                			out.println(jsonInString); 
                			out.flush();
        				}
        		}

         }
         catch (Exception e) {
            e.printStackTrace(); 
        }

    }
}
