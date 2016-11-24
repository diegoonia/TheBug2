package dev.codenmore.tilegame;

import world.Game;

public class Launcher {

	public static void main(String[] args){
		Game game = new Game("The Bug", 640, 480);
		game.start();
	}
	
}
