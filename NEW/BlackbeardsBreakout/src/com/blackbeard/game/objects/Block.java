/*
 * Aryan Gandevia, Gary Qin, Brian Kang
 * June 15, 2020
 * Ms. Krasteva
 * The "Block" class - This class holds the dimensions, shape, dimensions, and image of
 * 					   blocks in the game, which are used as platforms that the user
 * 					   can stand on and collide with. Changes color depending on level.
 */

/*
 * Variable Library:
 * Name                     Type                            Description
 * 
 * serialVersionUID 		static long						Prevents InvalidClassException errors by changing the default
 * 															serialVersionUID to 1L
 * 															https://stackoverflow.com/questions/285793/what-is-a-serialversionuid-and-why-should-i-use-it
 */ 


package com.blackbeard.game.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.blackbeard.game.gamestate.GameState;
import com.blackbeard.game.gamestate.OverworldState;

public class Block extends Rectangle {
	public static final long serialVersionUID = 1L;
	
	public Block(int x, int y, int blockSizeWidth, int blockSizeLength) {

		setBounds(x, y, blockSizeWidth, blockSizeLength); // sets coordinates and dimensions of a block
	}
	
	public void tick() {
		
	}
	
	public void draw(Graphics g) {
		if(OverworldState.firstDone == false) {
			g.setColor(new Color (135,206,250));
		}
		else if(OverworldState.secondDone == false) {
			g.setColor(Color.BLACK);
		}
		else if(OverworldState.thirdDone == false) {
			g.setColor(new Color (255,69,0));
		}
		g.fillRect(x - (int)GameState.xOffset, y - (int)GameState.yOffset, width, height); // x & y coordinate of blocks moves with the background as a group
	}
}
