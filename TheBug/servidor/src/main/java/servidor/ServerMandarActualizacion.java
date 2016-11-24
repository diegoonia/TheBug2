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

public class ServerMandarActualizacion implements Runnable {// The Runnable interface should be implemented by any class whose instances are intended to be executed by a thread.
    Scanner input;
    ArrayList<Socket> listaDeConexiones = new ArrayList<>();
    ArrayList<AccionesAEnviar> mensajes= new ArrayList<>();
    String mensaje=null;



    public ServerMandarActualizacion(ArrayList<Socket> listaDeSala,ArrayList<AccionesAEnviar> mensajes) {
        this.listaDeConexiones = listaDeSala;
        this.mensajes=mensajes;
    }
    public ServerMandarActualizacion() {
        this.listaDeConexiones = null;
        this.mensajes=null;
    }
    @Override
    public void run() {//SOBRECARGAR DE RUN QUE SE REALIZARA CUANDO INICIE EL THREAD CREADO EN "SERVIDOR"

    	//MapaObstaculos mO= new MapaObstaculos(32, 32, 0.5);
   
    	try {
           // this.input = new Scanner(this.socket.getInputStream()); // OBTENGO EL CANAL DE ENTRADA DEL SOCKET        
            
            while (true) { 
            	//tengo q cambiar el for, por cada mensaje, tengo q mandarle al socket la informacion.
            	// para lo cual debo sacarle (GET/SET) las posiciones y pasarlas a la estructura de "MAPAAENVIAR"
            	//lo creo y se lo mando en un json
            	
            	                                     
                    //this.mensaje = this.input.nextLine(); // GUARDO EN MENSAJE EL TEXTO RECIBIDO
                    //System.out.println(this.mensaje);
            		
                    for (int x = 0; x < this.listaDeConexiones.size(); x++) { // RECORRE TODA LA LISTA DE CONEXIONES DE LA SALA PARA ENVIAR EL MENSAJE RECIBIDO A TODOS.
                        Socket tempSocket = this.listaDeConexiones.get(x);
                        PrintWriter tempOut = new PrintWriter(tempSocket.getOutputStream()); // OBTENGO EL CANAL DE SALIDA PARA ENVIARLE EL MENSAJE A EL SOCKET
                        tempOut.println("jose "+x); // ENVIA EL MENSAJE
                        tempOut.flush(); // LIMPIO EL BUFFER DE SALIDA
                    }
                }
            }
         catch (Exception e) {
            e.printStackTrace();
        }

    }
}
