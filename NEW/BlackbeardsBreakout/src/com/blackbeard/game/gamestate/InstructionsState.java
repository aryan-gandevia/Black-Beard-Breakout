/*
 * Aryan Gandevia, Gary Qin, Brian Kang
 * June 15, 2020
 * Mrs. Krasteva
 * The "InstructionsState class" - this class is the design of the instructions screen
 */

/*
 * Variable Library:
 * Name						Type                            Description
 * 
 * images					ImageImporting					This is the class instance that is used for the image importing
 * picture					BufferedImage					This variable holds the image loaded from the image importing class							
 */

package com.blackbeard.game.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.blackbeard.game.graphics.ImageAnimation;
import com.blackbeard.game.graphics.ImageImporting;

public class InstructionsState extends GameState{
	//instance variables
    BufferedImage picture;
    ImageImporting images = new ImageImporting();
    
	public InstructionsState(GameStateManager gsm) { //class constructor
		super(gsm);
	}

	public void init() { //init method	
	} 

	public void tick() { //tick method
	}

	public void draw(Graphics g) { //draw method
		 images.setPathway("/Pictures/Instructions.png");
	     images.setImage();
	     picture = images.getPicture();  
	     g.drawImage(picture, 0, 0, 800, 600, null);
	     
	     g.setFont(new Font("Georgia", Font.BOLD, 70));
	     g.setColor(Color.BLACK);
	     g.drawString("INSTRUCTIONS", 100, 70);
	     g.setColor(new Color(240, 210, 150));
	     g.fillRect(0, 100, 800, 600);
	     
	     images.setPathway("/Pictures/PirateGunUp.png");
	     images.setImage();
	     picture = images.getPicture();  
	     g.drawImage(picture, -70, 85, 325, 475, null);
	     
	     images.setPathway("/Pictures/PirateGunDown.png");
	     images.setImage();
	     picture = images.getPicture();  
	     g.drawImage(picture, 540, 85, 325, 475, null);
	     
	     g.setColor(Color.BLACK); //Changed after the fact of presentation
	     g.setFont(new Font ("Georgia", Font.PLAIN, 25));
	     g.drawString("Welcome to Blackbeard's Breakout, a 2-D ", 125, 130);
	     g.drawString("platformer game designed to teach you", 125, 160);
	     g.drawString("how to stay safe from COVID-19. This", 125, 190);
	     g.drawString("game contains three levels, and you must", 125, 220);
	     g.drawString("complete them without dying to get Blackbeard", 125, 250);
	     g.drawString("back safely on his ship. You must collect the", 125, 280);
	     g.drawString("needed items and avoid and stay safe from all", 125, 310);
	     g.drawString("the possible ways to get the virus. Start the", 125, 340);
	     g.drawString("tutorial to learn the game before you dive into", 155, 370);
	     g.drawString("his breakout. ESCAPE to go back.", 155, 400);
	     
	     images.setPathway("/Pictures/downArrow.png");
	     images.setImage();
	     picture = images.getPicture();  
	     for (int i = 0 ; i < 3; i++) { //for loop to draw 3 pictures
	    	 g.drawImage(picture,225 + (i * 175), 425, 75, 110, null);
	     }
	     
	     g.setColor(Color.BLACK);
	     g.setFont (new Font ("Consolas", Font.BOLD, 30));
	     g.drawString("Press ENTER to start the tutorial", 175, 570);
	}

	public void keyPressed(int k) { //keyPressed method
		if (k == KeyEvent.VK_ENTER) { //if the enter key is pressed
			gsm.states.push(new TutorialState(gsm));
		} 
		if (k == KeyEvent.VK_ESCAPE) { //if the escape key is pressed
			gsm.states.push(new MenuState(gsm));			
		}
	}

	public void keyReleased(int k) { //keyReleased method
	}
}
