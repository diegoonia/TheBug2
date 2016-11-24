package logica;

import java.util.Random;

import output.Sprite;
import logica.Obstaculo;

public class MapaObstaculos extends MapaLogico {
	private static final long serialVersionUID = 1L;
	
	Obstaculo[][] obstaculos;

	public MapaObstaculos(int w, int h, double razon){
		super();
		
	}
	
	@Override
	public boolean ocupada(int x, int y) {
		return mapa[x][y]>0;
	}
	
	public Sprite getSprite(int x, int y){

			return obstaculos[x][y].getSprite(); //FIXME
		
	}
}
