package world.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import npc.Capacitor;
import world.Handler;
import world.gfx.Animation;
import world.gfx.Assets;

public class GameNpc extends Creature {
	private Capacitor npc;
	private Animation animDown, animUp, animLeft, animRight;
	private Assets asset;
	
	public Capacitor getNpc() {
		return npc;
	}

	public void setNpc(Capacitor npc) {
		this.npc = npc;
	}

	public GameNpc(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		npc = new Capacitor();
		
		bounds.x = 22;
		bounds.y = 44;
		bounds.width = 19;
		bounds.height = 19;
	}

	public void render(Graphics g) {
		asset = new Assets();
		BufferedImage imagenCapacitor = asset.npcCap;
		g.drawImage( imagenCapacitor, (int) (x - handler.getGameCamera().getxOffset()),
				   (int) (y - handler.getGameCamera().getyOffset()), width, height, null );
	}
	
	private BufferedImage getCurrentAnimationFrame(){
		if(xMove < 0){
			return animLeft.getCurrentFrame();
		}else if(xMove > 0){
			return animRight.getCurrentFrame();
		}else if(yMove < 0){
			return animUp.getCurrentFrame();
		}else{
			return animDown.getCurrentFrame();
		}
	}


	public void tick() {
		
	}

}
