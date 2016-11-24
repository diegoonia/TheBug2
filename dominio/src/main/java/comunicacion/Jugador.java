package comunicacion;
import java.io.Serializable;
import java.util.Random;

import personaje.*;

public class Jugador implements Serializable{

	private static final long serialVersionUID = 1L;
	private float x;
	private float y;
	private int id;
	private Humano jugador;
	
	public Jugador(float x2, float y2){
		this.x = x2;
		this.y = y2;
		id = (int) Math.floor(Math.random() * 10001);
		
		//this.jugador = jugador;
		
	}
	
	public Jugador(){
		
		
	}
	
	public void mostrarUbicacion(){
		
		System.out.println(this.x + " " + this.y);
		
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
