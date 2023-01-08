/*
 * Aryan Gandevia, Gary Qin, Brian Kang
 * June 15, 2020
 * Mrs. Krasteva
 * The "Player class" - this class is designed to hold the player throughout the levels of the game. It contains all the collisios, placements and movements of the player. It loads in the player
 * 						image and constantly draws it at its new position
 */

/*
 * Variable Library:
 * Name					Type							Description
 * 
 * right				boolean							This determines if the player is trying to move right
 * left					public boolean					This determines if the player is trying to move left
 * jumping				boolean							This determines if the player is moving up to jump
 * falling				boolean							This determines if the palyer is falling
 * topCollision			boolean							This is used to detect the collision into a platform
 * images				ImageImporting					This is the class instance that is used for the image importing
 * picture				BufferedImage					This variable holds the image loaded from the image importing class
 * x					private double					This variable is the x-coordinate location of the player
 * y					private double					This variable is the y-coordinate location of the player
 * width				private int						This variable holds the width of the player
 * height				private int						This variable holders the height of the player
 * moveSpeed			private double					This variable is the lateral movement speed
 * jumpSpeed			private double					This variable is for the vertical movement
 * currentJumpSpeed		private double					This variable is the jump inertia throughout it
 * maxFallSpeed			private double					This is the max speed for jumping
 * currentFallSpeed		private double					This is the variable for the current falling inertia
 * win					public static boolean			
 */

package com.blackbeard.game.entities;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.blackbeard.game.gamestate.GameState;
import com.blackbeard.game.graphics.ImageImporting;
import com.blackbeard.game.main.GamePanel;
import com.blackbeard.game.objects.Block;
import com.blackbeard.game.objects.Flag;
import com.blackbeard.game.physics.Collision;

public class Player {
	
	// movements
	boolean right = false;
	public boolean left = false;
	boolean jumping = false;
	boolean falling = false;
	boolean topCollision = false;
	
	// importing the character model
    ImageImporting images = new ImageImporting();
    BufferedImage picture;
	
	// location and dimensions
	private double x, y;
	private int width, height;
	
	// move speed
	private double moveSpeed = 2.5;
	
	// jump speeds
	private double jumpSpeed = 5;
	private double currentJumpSpeed = jumpSpeed;
	
	// fall speeds
	private double maxFallSpeed = 5;
	private double currentFallSpeed = 0.2;
	
	// win condition
	public static boolean win = false;
	
	public Player(int w, int h) { // width & height initialization
		x = GamePanel.WIDTH / 4 - 25; // x coordinate for where player appears in frame
		y = GamePanel.HEIGHT / 1.75; // y coordinate for where player appears in frame
		this.width = w;
		this.height = h;
	}

	
	public void tick(Block[] b) {
		
		int iX = (int) x;
		int iY = (int) y;
		
		/*
		 * iX and iY represent the x and y coordinate of the center of the character.
		 * width and height represent the width and height of the character.
		 * xOffset and yOffset represent how much the character has moved in the background.
		 * b[i] is a block in the array b, which stores all the blocks in the level.
		 */
		
		for(int i = 0; i < b.length; i++) {
			// collision of player hitting a block on its RIGHT side (checking if any point on the side of the character from the top right corner to the bottom right corner is inside a block)
			if(Collision.playerBlock(new Point(iX  + width + (int) GameState.xOffset, iY + (int) GameState.yOffset), b[i]) 
					|| Collision.playerBlock(new Point(iX + width + (int) GameState.xOffset, iY + height + (int) GameState.yOffset), b[i])) {
					right = false;
			}
			// collision of player hitting a block on its LEFT side (checking if any point on the side of the character from the top left corner to the bottom left corner is inside a block)
			if(Collision.playerBlock(new Point(iX + (int)GameState.xOffset - 1, iY + (int)GameState.yOffset + 2), b[i]) 
				|| Collision.playerBlock(new Point(iX + (int)GameState.xOffset - 1, iY + height + (int)GameState.yOffset - 1), b[i])) {
				left = false;
			}
			
			// collision of player hitting a block on its top side (checking if any point on the side of the character from the top left corner to the top right corner is inside a block)
			if(Collision.playerBlock(new Point(iX + (int)GameState.xOffset + 1, iY + (int)GameState.yOffset), b[i]) 
				|| Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset - 2, iY + (int)GameState.yOffset), b[i])) {
				jumping = false;
				falling = true;
			}
			// collision of player hitting a block on its bottom side (checking if any point on the side of the character from the bottom left corner to the bottom right corner is inside a block)
			if(Collision.playerBlock(new Point(iX + (int)GameState.xOffset + 2, iY + height + (int)GameState.yOffset + 1), b[i]) 
				|| Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset - 2, iY + height + (int)GameState.yOffset + 1), b[i])) {
				y = b[i].getY() - height - GameState.yOffset;
				falling = false;
				topCollision = true;
				break;
			}
			// if the player is not on top of a block
			else {
				if(!topCollision && !jumping) {
					falling = true;
				}
			}
		
		}
		
		topCollision = false; // stores if player hits top side on a block
		
		// if player is registered as going right
		if(right == true) {
			GameState.xOffset += moveSpeed; // move background to the left
		}
		
		// if player is registered as going left
		if(left == true) {
			GameState.xOffset -= moveSpeed; // move background to the right
		}
		
		// if player is registered as jumping
		if(jumping == true) { // incremental jump speed
			GameState.yOffset -= currentJumpSpeed;
			
			currentJumpSpeed -= .1;
			if(currentJumpSpeed <= 0) { // when peak of jump is reached, start falling
				currentJumpSpeed = jumpSpeed;
				jumping = false;
				falling = true;
			}
		}
		
		if(falling == true) { // incremental falling speed
			GameState.yOffset += currentFallSpeed;
			
			if(currentFallSpeed < maxFallSpeed) {
				currentFallSpeed += .1;
			}
		}
		if(falling == false) { // when falling has stopped
			currentFallSpeed = .1;
		}
		
	}
	
	public void tick(Flag[] f) {
		int iX = (int) x;
		int iY = (int) y;
		
		/*
		 * iX and iY represent the x and y coordinate of the center of the character.
		 * width and height represent the width and height of the character.
		 * xOffset and yOffset represent how much the character has moved in the background.
		 * b[i] is a block in the array b, which stores all the blocks in the level.
		 */
		for(int i = 0; i < f.length; i++) {
			// collision of player hitting a block on its left side (checking if any point on the side of the character from the top right corner to the bottom right corner is inside a block)
			if(Collision.playerFlag(new Point(iX + width + (int) GameState.xOffset, iY + (int) GameState.yOffset + 2), f[i]) 
				|| Collision.playerFlag(new Point(iX + width + (int) GameState.xOffset, iY + height + (int) GameState.yOffset - 1), f[i])) {
				System.out.print("win");
				win = true;
			}
			// collision of player hitting a block on its right side (checking if any point on the side of the character from the top left corner to the bottom left corner is inside a block)
			if(Collision.playerFlag(new Point(iX + (int)GameState.xOffset - 1, iY + (int)GameState.yOffset + 2), f[i]) 
				|| Collision.playerFlag(new Point(iX + (int)GameState.xOffset - 1, iY + height + (int)GameState.yOffset - 1), f[i])) {
				win = true;
			}
			
			// collision of player hitting a block on its bottom side (checking if any point on the side of the character from the top left corner to the top right corner is inside a block)
			if(Collision.playerFlag(new Point(iX + (int)GameState.xOffset + 1, iY + (int)GameState.yOffset), f[i]) 
				|| Collision.playerFlag(new Point(iX + width + (int)GameState.xOffset - 1, iY + (int)GameState.yOffset), f[i])) {
				win = true;
			}
			
			// collision of player hitting a block on its top side (checking if any point on the side of the character from the bottom left corner to the bottom right corner is inside a block)
			if(Collision.playerFlag(new Point(iX + (int)GameState.xOffset + 2, iY + height + (int)GameState.yOffset + 1), f[i]) 
				|| Collision.playerFlag(new Point(iX + width + (int)GameState.xOffset - 1, iY + height + (int)GameState.yOffset + 1), f[i])) {
				win = true;
			}
		}
	}
	
	public void draw(Graphics g) {
		images.setPathway("/GameItems/Player.png"); // importing picture of character
	    images.setImage();
	    picture = images.getPicture();	    
		g.drawImage(picture, (int) x,  (int) y,  width,  height, null);
		
	}

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_RIGHT) { // if right arrow key is pressed 
			right = true;
		}
		if(k == KeyEvent.VK_LEFT) { // if left arrow key is pressed 
			left = true;
		}
		if(k == KeyEvent.VK_UP && !jumping && !falling) { // if up arrow key is pressed & player not already in air
			jumping = true;
		}
	}

	public void keyReleased(int k) {
		if(k == KeyEvent.VK_RIGHT) { // when right arrow key is released
			right = false;
		}
		if(k == KeyEvent.VK_LEFT) { // when left arrow key is released
			left = false;
		}
	}
}
