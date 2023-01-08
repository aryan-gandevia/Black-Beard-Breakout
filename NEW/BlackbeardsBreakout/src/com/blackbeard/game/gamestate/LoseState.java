/*
 * Aryan Gandevia, Gary Qin, Brian Kang
 * June 15, 2020
 * Mrs. Krasteva
 * The "LoseState class" - this class is the design of the losing screen
 */

/*
 * Variable Library:
 * Name						Type                            Description
 * 
 * COVID_FACTS				String[]						This variable stores many different facts about COVID-19
 * images					ImageImporting					This is the class instance that is used for the image importing
 * ivh						ImageVariableHolder				This class holds the image variable
 * picture					BufferedImage					This variable holds the image loaded from the image importing class
 * x						int								This variable is used for the x coordinate of the COVID fact
 * counter					int								This variable is used for randomizing
 * index					int								This variable is used for the index of the String array
 * ticker					int								This variable is used to delay
 * image					BufferedImage					This variable holds the image loaded from the image importing class
 */

package com.blackbeard.game.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.blackbeard.game.graphics.Colors;
import com.blackbeard.game.graphics.ImageAnimation;
import com.blackbeard.game.graphics.ImageImporting;
import com.blackbeard.game.graphics.ImageVariableHolder;

public class LoseState extends GameState {
	//instance variables
	static final String[] COVID_FACTS = {"don't into a virus cloud without a mask!", "Avoid the infected entities if you don't have soap!", "try to stay on the map and not fall in the voids!", "collect all the items before going to the finish!"};
	ImageImporting images = new ImageImporting();
	ImageVariableHolder ivh = new ImageVariableHolder();
	BufferedImage picture;
	int x;
	int counter = 0;
	int index;
	int ticker = 0;
	static BufferedImage image;
     
	public LoseState(GameStateManager gsm) { //class contructor
		super(gsm);
	}

	public void init() { //init method
	}

	public void tick() { //tick method
	}

	public void draw(Graphics g) { //draw method
		if (ticker < 140) { //if ticker is less than 140
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 600);
			image = ImageAnimation.fadeImage(ivh.blackbeard, 0, 0, 30, 30, Colors.ALPHA_RGB, 0.007F);
	        g.drawImage(image, 360, 285, null);
		    g.setFont (new Font("Courier New", Font.PLAIN, 20));
		    g.setColor(Color.WHITE);
		    g.drawString(("x" + OverworldState.deathCounter), 400, 310);
		}
		else { //if ticker is greater than or equal to 140
			ImageAnimation.opacity = 0f;
		if (counter < 1) { //if counter is less than 1
			counter++;
			index = (int)(Math.random() * 4);
		}
		images.setPathway("/Pictures/loseScreen.jpg");
	    images.setImage();
	    picture = images.getPicture();
	    g.drawImage(picture, 0, 0, null);
	    images.setPathway("/Pictures/skull.png");
	    images.setImage();
	    picture = images.getPicture();
	    g.drawImage(picture, 200, 100, 400, 400, null);
	    if (index == 0) { //if index is 0
			x = 100;	
		} else if (index == 1) { //if index is 1
			x = 33;
		} else if (index == 2) { //if index is 2
			x = 40;
		} else if (index == 3) { //if index is 3
			x = 50;
		} 
	    g.setFont (new Font("Courier New", Font.PLAIN, 70));
		g.setColor(Color.WHITE);
	    g.drawString("You lost...", 200, 100);
		g.setFont(new Font("Courier New", Font.PLAIN, 20));
		g.drawString(("Remember: " + COVID_FACTS[index]), x, 525);
		}
		ticker++;
	}

	public void keyPressed(int k) { //keyPressed method
		if (k == KeyEvent.VK_ENTER) { //if the enter key is pressed
			gsm.states.push(new OverworldState(gsm));
		}
	}

	public void keyReleased(int k) { //keyReleased method
	}

}
