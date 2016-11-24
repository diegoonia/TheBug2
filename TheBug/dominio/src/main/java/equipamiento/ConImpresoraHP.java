package equipamiento;

import java.awt.Point;

import personaje.*;


public class ConImpresoraHP extends PersonajeEquipado{
	public ConImpresoraHP(Personaje personajeDecorado) {
		super(personajeDecorado);
	}
	
	@Override
	public int obtenerPuntosDeDefensa() {
		return super.obtenerPuntosDeDefensa() *2;
	}

	@Override
	public String getSpritepath() {
		
		return this.personajeDecorado.getSpritePath();
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
