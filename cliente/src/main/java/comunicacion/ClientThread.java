package comunicacion;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import org.codehaus.jackson.map.ObjectMapper;

import jugador.MapaEnlaceDatos;
import jugador.MapaFisico;
import world.Game;


public class ClientThread implements Runnable {
    private Socket socket;
    private Scanner sc;
    private PrintWriter out;
    private String mapaSeleccionado;



    public ClientThread(Socket socket, String mapaSeleccionado) {
        this.socket = socket;
        this.mapaSeleccionado=mapaSeleccionado;
    }

    @Override
    public void run() {
    	
    	Game game = new Game("The bug", 640, 480, socket);
		game.start();
		
		
		

//    	switch (mapaSeleccionado) {
//		case "Fisica":
//			MapaFisico mapaF = new MapaFisico();
//			mapaF.setVisible(true);
//			break;
//		case "Enlace de Datos":
//			MapaEnlaceDatos mapaED = new MapaEnlaceDatos();
//			mapaED.setVisible(true);
//		default:
//			//Poner mensaje de error
//			break;
//		}
    	/*
    	ClientRecibirAct clientRecibirAct = new ClientRecibirAct(socket,mapaSeleccionado);
        Thread threadRecibirAct = new Thread(clientRecibirAct);
        threadRecibirAct.start();
        
    	ClientAcciones clientAcciones = new ClientAcciones(socket,mapaSeleccionado);
        Thread threadRecibirAcciones = new Thread(clientAcciones);
        threadRecibirAcciones.start();
    	*/
    	
    	
    	
    	
    }


    private void recibirDatos() {
        if (this.sc.hasNext()) {
            String mensajeEntrante = this.sc.nextLine();
            System.out.println(mensajeEntrante);
        }
    }

    public void enviarDatos(String mensajeSaliente) {
        this.out.println(mensajeSaliente);
        this.out.flush();
    }

    public void desconectar() throws Exception {
        this.out.println(" se ha retirado de la sala");
        this.out.flush();
        this.socket.close();
        System.exit(0);
    }
}
