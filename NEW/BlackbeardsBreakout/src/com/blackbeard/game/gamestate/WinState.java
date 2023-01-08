/*
 * Aryan Gandevia, Gary Qin, Brian Kang
 * June 15, 2020
 * Mrs. Krasteva
 * The "WinState class" - this class is the design of the win screen
 */

/*
 * Variable Library:
 * Name						Type                            Description
 * 
 * COVID_FACTS				String[]						This variable stores many different facts about COVID-19							
 * counter					int								This variable is used for randomizing
 * index					int								This variable is used for the index of the String array
 * x						int								This variable is used for the x coordinate of the COVID fact
 * image					BufferedImage					This variable holds the image loaded from the image importing class
 * images					ImageImporting					This is the class instance that is used for the image importing
 * picture					BufferedImage					This variable holds the image loaded from the image importing class							
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

public class WinState extends GameState {
	//instance variables
	static final String[] COVID_FACTS = {"Social distancing makes it easier to contract the virus!", "Avoid areas of high crowding!", "Stay at home as much as possible!", "Limit physical contact!", "Don't share ingestible items with anyone!", "Always wash your hands after going out!", "Be sure to wear protective gear at all points when going outside!"};
	int counter = 0;
	int index;
	int x;
	static BufferedImage image;
	ImageImporting images = new ImageImporting();
	BufferedImage picture;
	
	public WinState(GameStateManager gsm) { //class constructor
		super(gsm);
	}

	
	public void init() { //init method
	}

	
	public void tick() { //tick method
	}

	
	public void draw(Graphics g) { //draw method
		images.setPathway("/Pictures/winScreenScreen.jpg");
	    images.setImage();
	    picture = images.getPicture();
	    g.drawImage(picture, 0, 0, null);
		if (counter < 1) { //if counter is less than 1
			counter++;
			index = (int)(Math.random() * 6);
		}
		g.drawImage(picture, 0, 0, null);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Georgia", Font.PLAIN, 70));
		g.drawString("You Win!", 250, 200);
		g.setFont(new Font("Georgia", Font.PLAIN, 40));
		g.drawString("Press enter to continue...", 175, 340);
		g.setFont(new Font("Georgia", Font.PLAIN, 20));
		if (index == 0) { //if index is 0
			x = 115;	
		} else if (index == 1) { //if index is 1
			x = 215;
		} else if (index == 2) { //if index is 2
			x = 195;
		} else if (index == 3) { //if index is 3
			x = 250;
		} else if (index == 4 || index == 5) { //if index is 4 or 5
			x = 165;
		} else if (index == 6) { //if index is 6
			x = 70;
		}
		g.setColor(Color.WHITE);
		g.drawString(("Fun Fact: " + COVID_FACTS[index]), x, 465);
	}

	
	public void keyPressed(int k) { //keyPressed method
		if(k == KeyEvent.VK_ENTER) { //if the enter key is pressed
			gsm.states.push(new OverworldState(gsm));
		}
	}

	
	public void keyReleased(int k) { //keyReleased method
		
		
	}
}
