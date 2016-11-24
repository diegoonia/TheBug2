package personaje;

import java.awt.Point;
import java.awt.Rectangle;

public class Humano extends Personaje{
	
	public Humano(String name) {			
		super();
		super.setSpritePath("src/main/resources/imagenes/Humano/Programador/HumanoProgramadorCaminando.gif");
	}

	@Override
	public boolean puedeAtacar() {
		return energia >= 10;
	}

	@Override
	public Point getPosition() {
		Rectangle r =this.getSprite().getBounds();
		
		return new Point((int)(r.getX()/r.getHeight()),(int)(r.getY()/r.getWidth())); //TODO: revisar si pue bien el orden
	}

	@Override
	public void setPosition(Point p) {
		// TODO Auto-generated method stub
		
	}




	
}
