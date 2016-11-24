package output;

import java.awt.Point;

import output.Sprite;

public interface IhasSprite {
	
	public Sprite getSprite();
	public String getSpritePath();
	public Point getPosition();
	public void setPosition(Point p);

}
