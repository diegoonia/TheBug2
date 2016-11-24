package world.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import world.tiles.RockTile;
import world.tiles.Suelo2Tile;
import world.tiles.SueloTile;

public class Tile {
	
	//STATIC STUFF HERE
	
	public static Tile[] tiles = new Tile[256];
	public static Tile suelo2Tile = new Suelo2Tile(0);
	public static Tile sueloTile = new SueloTile(1);
	public static Tile rockTile = new RockTile(2);
	
	//CLASS
	
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
	
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id){
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g, int x, int y){
		g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid(){
		return false;
	}
	
	public int getId(){
		return id;
	}
	
}
