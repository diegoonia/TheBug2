package world;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import comunicacion.Jugador;
import world.display.Display;
import world.entities.creatures.GameNpc;
import world.entities.creatures.Player;
import world.gfx.Assets;
import world.gfx.GameCamera;
import world.input.KeyManager;
import world.states.GameState;
import world.states.State;

public class Game implements Runnable {

	private Display display; //esta es la ventana
	private int width, height; // con su alto y ancho
	public String title;
	
	private ArrayList<Player> listaJugadores;
	private ArrayList<GameNpc> listaNpcs;
	Socket socket;
	Jugador jugadorMio;
	Jugador jugadorRecibido;
	Player player;
	Scanner sc;
	
	private VentanaPelea ventanaPelea;
	private boolean estaPeleando;	
	

	private boolean running = false;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	//States
	private GameState gameState;
	//Input
	private KeyManager keyManager;
	
	//Camera
	private GameCamera gameCamera;
	
	//Handler
	private Handler handler;
	
	public Game(String title, int width, int height, Socket socket){
		this.width = width;
		this.height = height;
		this.title = title;
		this.socket = socket;
		//jugadorMio = new Jugador();
		jugadorRecibido = new Jugador();
		keyManager = new KeyManager();
		player = new Player(handler,0,0);
		estaPeleando = false;

	}
	
	private void init(){
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init(); //en Assets recorto todas las imagenes y las guardo
					   //en un BufferedImage
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		gameState = new GameState(handler);
		State.setState(gameState);
		
	}
	
	private void tick(){ //este metodo hace el update todo el tiempo
		keyManager.tick();
		
		if(State.getState() != null)
			State.getState().tick();
	}
	
	private void render() throws JsonGenerationException, JsonMappingException, IOException{ //este metodo dibuja en la pantalla todo
		
	    String jsonInString;
	    float x = this.gameState.getWorld().getEntityManager().getPlayer().getX();
	    float y = this.gameState.getWorld().getEntityManager().getPlayer().getY();
	    //Humano = this.gameState.getWorld().getEntityManager().getPlayer().getPersonaje();
	    jugadorMio = new Jugador(x,y);
	    
		ObjectMapper mapper = new ObjectMapper();
		jsonInString = mapper.writeValueAsString(jugadorMio);
		PrintWriter out = new PrintWriter(socket.getOutputStream()); //OBTENGO EL CANAL DE SALIDA DEL SOCKET HACIA EL SERVIDOR
		out.println(jsonInString);
		out.flush();
		
		
		bs = display.getCanvas().getBufferStrategy(); //BufferStrategy es lo que la computadora le avisa que tiene que dibujar
		if(bs == null){
			display.getCanvas().createBufferStrategy(3); //si recien arranco el juego, crea el bstrategy, vamos a usar 3 buffers
			return;
		}
		g = bs.getDrawGraphics();

		g.clearRect(0, 0, width, height); //rectangulo que limpia la pantalla

		//aca dibujo
		if(State.getState() != null)
			State.getState().render(g);
		
		//escribir un for que vaya iterando entre un Arraylist<Players> que me envian
		//desde el servidor o genero en cliente, con los Player que me envia el servidor
		//y aca en un foreach los voy mostrando en pantalla
		//antes del for hacer un dispose de los anteriores, asi me genera como una especie de "update" de la posicion
		/*
		listaJugadores = new ArrayList<Player>(); 
		
		Player player1 = new Player(handler, 400, 50);		
		Player player2 = new Player(handler, 50, 50);
		listaJugadores.add(player1);
		listaJugadores.add(player2);
		
		if(jugadorRecibido.getId() != jugadorMio.getId()){
			Player player2 = new Player(handler, jugadorRecibido.getX(),jugadorRecibido.getY());
			player2.render(g);
		}
		
		*/
		
		//Player player2 = new Player(handler, jugadorRecibido.getX(),jugadorRecibido.getY());
		//player2.render(g);
		sc = new Scanner(socket.getInputStream());
		String input = sc.nextLine();
		jugadorRecibido = mapper.readValue(input, Jugador.class);
		
		if(jugadorRecibido.getId() != jugadorMio.getId()){
		
			Player player = new Player(handler, jugadorRecibido.getX(),jugadorRecibido.getY());
		
		
			player.render(g);
			
				if(estaPeleando == false)
				{
					if( Math.abs(this.gameState.getWorld().getEntityManager().getPlayer().getX() - player.getX() ) < 30
								&& Math.abs(this.gameState.getWorld().getEntityManager().getPlayer().getY()) - player.getY() < 30)
					{
						ventanaPelea = new VentanaPelea(this.gameState.getWorld().getEntityManager().getPlayer(), player, this);
						ventanaPelea.setVisible(true);
						estaPeleando = true;
					}
				}

		}

		
		listaNpcs = new ArrayList<GameNpc>();
		
		GameNpc npc1 = new GameNpc(handler, 500, 200);
		listaNpcs.add(npc1);
		GameNpc npc2 = new GameNpc(handler, 600, 50);
		listaNpcs.add(npc2);

		
		for (GameNpc npc : listaNpcs) {
			npc.render(g);
			
			if(estaPeleando == false)
			{
				if( Math.abs(this.gameState.getWorld().getEntityManager().getPlayer().getX() - npc.getX() ) < 30
							&& Math.abs(this.gameState.getWorld().getEntityManager().getPlayer().getY()) - npc.getY() < 30)
				{
					ventanaPelea = new VentanaPelea(this.gameState.getWorld().getEntityManager().getPlayer(), npc, this);
					ventanaPelea.setVisible(true);
					estaPeleando = true;
				}
			}
		}

		//termino de dibujar aca
		bs.show(); //muestro lo que tiene el bufferstrategy
		g.dispose(); //hago dispose de los graficos
	}
		
	
	public void run(){
		init();
		
		int fps = 100;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				try {
					render();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000){
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		stop();
	}
	
	public KeyManager getKeyManager(){
		return keyManager;
	}
	
	public GameCamera getGameCamera(){
		return gameCamera;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public synchronized void start(){
		if(running) //si ya esta empezado, no hace nada, es por las dudas
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running) //lo mismo que en start, si ya esta running=false sale.
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			JOptionPane.showMessageDialog(null,"ERROR DE THREAD"); 
		}
	}
	
	public boolean isEstaPeleando() {
		return estaPeleando;
	}

	public void setEstaPeleando(boolean estaPeleando) {
		this.estaPeleando = estaPeleando;
	}

}











