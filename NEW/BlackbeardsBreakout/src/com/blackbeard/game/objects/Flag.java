/*
 * Aryan Gandevia, Gary Qin, Brian Kang
 * June 15, 2020
 * Ms. Krasteva
 * The "Flag" class - This class holds the dimensions, shape, coordinates, and image of
 * 					  blocks in the game, which are used as win conditions that the user
 * 				      must reach in order to beat the level.
 */

/*
 * Variable Library:
 * Name                     Type                            Description
 * 
 * serialVersionUID 		static long						Prevents InvalidClassException errors by changing the default
 * 															serialVersionUID to 1L
 * 															https://stackoverflow.com/questions/285793/what-is-a-serialversionuid-and-why-should-i-use-it
 * images					ImageImporting					This is the class instance that is used for the image importing
 * picture					BufferedImage					This variable holds the image loaded from the image importing class
 */ 

package com.blackbeard.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.blackbeard.game.gamestate.GameState;
import com.blackbeard.game.graphics.ImageImporting;

public class Flag extends Rectangle{
	public static final long serialVersionUID = 1L;
	
	 ImageImporting images = new ImageImporting();
	 BufferedImage picture;
		
	public Flag(int x, int y) {
		setBounds(x, y, 50, 50); // sets coordinates and dimensions of a flag
	}
	
	public void tick() {
		
	}
	
	public void draw(Graphics g) {
		images.setPathway("/GameItems/flag.png"); // importing an image
	    images.setImage();
	    picture = images.getPicture();
	    g.drawImage(picture, x - (int)GameState.xOffset, y - (int)GameState.yOffset, null);  // x & y coordinate of flags moves with the background
	} 
}
