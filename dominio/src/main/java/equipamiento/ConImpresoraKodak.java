package equipamiento;

import personaje.*;

import java.awt.Point;

import output.Sprite;

public class ConImpresoraKodak extends PersonajeEquipado{
	public ConImpresoraKodak(Personaje personajeDecorado) {
		super(personajeDecorado);
	}
	
	@Override
	public int obtenerPuntosDeDefensa() {
		return super.obtenerPuntosDeDefensa() - 10;
	}

	@Override
	public String getSpritepath() {
		
		return this.personajeDecorado.getSpritePath();
	}

	@Override
	public Sprite getSprite() {
		return this.personajeDecorado.getSprite();
	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPosition(Point p) {
		// TODO Auto-generated method stub
		
	}



}
