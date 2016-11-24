package dev.codenmore.tilegame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import dev.codenmore.tilegame.Handler;
import dev.codenmore.tilegame.display.Display;
import dev.codenmore.tilegame.entities.creatures.Player;
import dev.codenmore.tilegame.gfx.Assets;
import dev.codenmore.tilegame.gfx.GameCamera;
import dev.codenmore.tilegame.input.KeyManager;
import dev.codenmore.tilegame.states.GameState;
import dev.codenmore.tilegame.states.State;
import world.VentanaPelea;

public class Game implements Runnable {

	private Display display; //esta es la ventana
	private int width, height; // con su alto y ancho
	public String title;
	
	private ArrayList<Player> ListaJugadores;
	
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
	
	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
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
	
	int pos = 5; //despues sacarlo, es para la prueba nada mas
	
	private void render(){ //este metodo dibuja en la pantalla todo
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
		
		//esto iria adentro de un foreach, y anda joya
		Player player2 = new Player(handler, pos, 50); //pasandole un X e Y te lo dibuja aca, sin colision igual
		pos++;
		player2.render(g);
		Player player3 = new Player(handler, pos+20, 100);
		player3.render(g);
		
		if(estaPeleando == false)
		{
			if( Math.abs(this.gameState.getWorld().getEntityManager().getPlayer().getX() - player2.getX() ) < 30
						&& Math.abs(this.gameState.getWorld().getEntityManager().getPlayer().getY()) - player2.getY() < 30)
			{
				ventanaPelea = new VentanaPelea(this.gameState.getWorld().getEntityManager().getPlayer(), player2);
				ventanaPelea.setVisible(true);
				estaPeleando = true;
			}
			
			if ( Math.abs(this.gameState.getWorld().getEntityManager().getPlayer().getX() - 
					this.gameState.getWorld().getEntityManager().getEntities().get(3).getX()) < 30
				&&
				Math.abs(this.gameState.getWorld().getEntityManager().getPlayer().getY() - 
						this.gameState.getWorld().getEntityManager().getEntities().get(3).getY()) < 30)
			{
				ventanaPelea = new VentanaPelea(this.gameState.getWorld().getEntityManager().getPlayer(), player2);
				ventanaPelea.setVisible(true);
				estaPeleando = true;
			}
		}
		
		
		//termino de dibujar aca
		bs.show(); //muestro lo que tiene el bufferstrategy
		g.dispose(); //hago dispose de los graficos
	}
	
	public void run(){
		init();
		
		int fps = 60;
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
				render();
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
	
}











