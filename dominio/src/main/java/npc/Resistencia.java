package npc;

import java.awt.Point;

import output.Sprite;

public class Resistencia extends Npc{
	public Resistencia() {
		super();
		nivel=1;
		this.nombre="Resistencia";
		this.salud=this.nivel*100;
	}

	@Override
	public void revivir() {
	
	}
	
	public Sprite getSprite(){
		return super.getSprite();
	}
	
	public String getSpritePath(){
		return "/servidor/src/main/resources/Sprites/GIF/Resistencia/ResistenciaIzquierda.gif";
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
