package logica;

import java.awt.Point;

import output.IhasSprite;
import output.Sprite;

public class Obstaculo implements IhasSprite {
	
	int id=0,x,y;
	
	Obstaculo(int x, int y, int id){
		this.x=x;
		this.y=y;
		this.id=id;
	}

	@Override
	public Sprite getSprite() {
		return new Sprite(getSpritePath());
	}

	@Override
	public String getSpritePath() {
		return id>0? "servidor\bin\tiletest.png" : "servidor\bin\null";
	}
	
	public void setid(int i) {id=i;}
	public boolean getOcupado() {return id>0?true:false;}

	@Override
	public Point getPosition() {
		return null;
	}

	@Override
	public void setPosition(Point p) {
		
	}

}
