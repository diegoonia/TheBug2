package servidor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import comunicacion.AccionesAEnviar;
import comunicacion.MapaAEnviar;
import logica.MapaAlianza;
import logica.MapaLogico;
import logica.MapaObstaculos;

public class ServerThread implements Runnable {// The Runnable interface should be implemented by any class whose instances are intended to be executed by a thread.
    Socket socket;
    Scanner input;
    String mensaje = "";
    ArrayList<AccionesAEnviar> mensajes= new ArrayList<>();
    ArrayList<Socket> listaDeConexiones = new ArrayList<>();
    String nickName;
    MapaObstaculos mo;
    
    public ServerThread(Socket socket, ArrayList<Socket> listaDeSala, String alias) {
        this.socket = socket;
        this.listaDeConexiones = listaDeSala;
        this.nickName = alias;
        mo= new MapaObstaculos(32,32, 0.4);
        
        
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
   
    	try {
            this.input = new Scanner(this.socket.getInputStream()); // OBTENGO EL CANAL DE ENTRADA DEL SOCKET

                if (this.estaConectado()) { // VERIFICO QUE EL SOCKET ESTE CONECTADO, SI NO LO ESTA CIERRO ESE SOCKET.
                    if (!this.input.hasNext()) { // SI NO HA TIENE MENSAJE ACTUAL BUCLEO A LA ESPERA DE UNO
                        return;
                    }
                } //Nano, i'm so sorry.
                
	            ServerMandarActualizacion mapaAEnviar = new ServerMandarActualizacion(listaDeConexiones,mensajes);           
                Thread threadMandarAct = new Thread(mapaAEnviar);
                threadMandarAct.start();
                
                ServerRecibirAcciones accionesARecibir = new ServerRecibirAcciones(socket);           
                Thread threadRecibir = new Thread(accionesARecibir);
                threadRecibir.start();
            }
         catch (Exception e) {
            e.printStackTrace(); 
        }

    }
}
