package logica;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import output.Sprite;

// Aunque se puede usar por si misma, la idea es heredar de mapa logico. Esta clase sirve para saber si  cada celda esta ocupada o no.
public abstract class MapaLogico extends Observable implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected int w,h;
	protected  Random rnd;
	protected double razon=0.5;
	protected int  mapa[][];
	public MapaLogico()
	{
		super();
		
		w=32;
		h=32;
		rnd = new Random();		
	}
	
	public MapaLogico(int w, int h, double razon) //rango va desde 0% hasta 100%
	{
		super();
		
		razon=Math.max(razon, 0);
		razon=Math.min(razon, 1);
		this.razon=razon;
		this.w=w;
		this.h=h;
		rnd = new Random();
		mapa = new int[w][h];
		
		for (int i=0; i<w; i++)
			for (int j=0; j<h;j++){
				if (rnd.nextDouble()>razon)
					mapa[i][j]=0;
				else
					mapa[i][j]=rnd.nextInt(2)+1;
			}
				
	}
	
	public abstract boolean ocupada(int x, int y);
	
	public int getW() {
		return w;
	}

	public int getH() {
		return h;
	}
	
//	public String toString() //Para test de generacion
//	{
//		String aux = new String("");
//		for (int i=0; i < h; i++) {
//			for (int j=0; j < w; j++) 
//				if (celdas[i][j]) aux += "*";
//				else aux += ".";
//		aux+= '\n';}
//		return aux;
//	}
	
	public void monitorear(Observer o)
	{
		this.addObserver(o);
	}
	
	public abstract Sprite getSprite(int x, int y);	

}
