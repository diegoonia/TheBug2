package comunicacion;

import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.*;
import org.codehaus.jackson.map.ObjectMapper;



public class CommHandler implements Runnable {
	
	private LinkedBlockingQueue<Mensaje> mensajes;
	ObjectMapper mapper = new ObjectMapper();
	private boolean running = false;
	ObjectInputStream in =null;
	ObjectOutputStream out = null;
	Socket socket = null;
	
	public CommHandler(Socket sck)
	{
		this.socket=sck;
		running = true;
	}
	
	public void run() {
		while (running)
			try {
				in = new ObjectInputStream(socket.getInputStream());
				out = new ObjectOutputStream(socket.getOutputStream());
				
				Mensaje m = mensajes.take();
				m.procesar();
				
				
				
				
				if (m == null)
					continue;
				out.writeObject(m);
				m = (Mensaje)in.readObject();
				mensajes.add(m);


				}
		
			catch (InterruptedException e)
			{ e.printStackTrace();}
		
			catch (RuntimeException rte) {
				rte.printStackTrace();
				//shutDown();
				}
		
			catch (Throwable t) {
				t.printStackTrace();
			}
			
				
	}


}
