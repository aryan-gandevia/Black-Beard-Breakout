/*
 * Aryan Gandevia, Gary Qin, Brian Kang
 * June 15, 2020
 * Mrs. Krasteva
 * The "ExitState class" - this class is the design of the exit screen
 */

/*
 * Variable Library:
 * Name						Type                            Description
 * 
 * picture					BufferedImage					This variable holds the image loaded from the image importing class
 * image					BufferedImage					This variable holds the image loaded from the image importing class
 * images					ImageImporting					This is the class instance that is used for the image importing
 * a						int								This variable is used to delay before exiting
 */

package com.blackbeard.game.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.blackbeard.game.graphics.Colors;
import com.blackbeard.game.graphics.ImageAnimation;
import com.blackbeard.game.graphics.ImageImporting;

public class ExitState extends GameState{
	//instance variables
	 BufferedImage picture;
	 static BufferedImage image;
	 ImageImporting images = new ImageImporting();
	 int a = 0;
	 
	public ExitState(GameStateManager gsm) { //class constructor
		super(gsm);
	}

	public void init() { //init method
	}

	public void tick() { //tick method
	}

	public void draw(Graphics g) { //draw method
		 images.setPathway("/Pictures/exitScreen.png"); // importing image of exit screen background
	     images.setImage();
	     picture = images.getPicture();

	    
	    if (ImageAnimation.changer != 1) { //ImageAnimation.changer is not 1
	    	image = ImageAnimation.fadeImage(picture, 0, 0, 800, 600, Colors.ALPHA_RGB, 0.007F);
		    g.drawImage(image, 0, 0, null);
	    } else {
		    g.drawImage(picture, 0, 0, null);	  
		    images.setPathway("/Pictures/CompanyLogo.png");
		    images.setImage();
		    picture = images.getPicture();
		    g.drawImage(picture, 300, 200, 200, 200, null);
		    g.setFont(new Font ("Courier New", Font.BOLD, 30));
		    g.setColor(Color.BLACK);
		    g.drawString("Thanks for playing, and stay COVID free!", 40, 450);
		    a++;
		    if (a == 140) { //if a is 140
		    	System.exit(0);
		    }
	    }
	}

	public void keyPressed(int k) { //keyPressed method
	}

	public void keyReleased(int k) { //keyReleased method
	}

}
