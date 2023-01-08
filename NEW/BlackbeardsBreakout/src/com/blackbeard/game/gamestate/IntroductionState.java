/*
 * Aryan Gandevia, Gary Qin, Brian Kang
 * June 15, 2020
 * Mrs. Krasteva
 * The "IntroductionState class" - this class is the design of the introduction screen
 */

/*
 * Variable Library:
 * Name						Type                            Description
 * 
 * ivh						ImageVariableHolder				This class holds the image variable
 * image					BufferedImage					This variable holds the image loaded from the image importing class
 * a						int								This variable is used to delay before exiting
 * counter					int								This variable is used for randomizing						
 */


package com.blackbeard.game.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.blackbeard.game.graphics.Colors;
import com.blackbeard.game.graphics.ImageAnimation;
import com.blackbeard.game.graphics.ImageVariableHolder;

public class IntroductionState extends GameState {
	//instance variables
	ImageVariableHolder ivh = new ImageVariableHolder();
    static BufferedImage image;
    int a = 0;
    int counter = 0;
    
	public IntroductionState(GameStateManager gsm) { //class contructor
		super(gsm);
	}
	public void init() { //init method
	}

	public void tick() { //tick method
	}

	public void draw(Graphics g) { //draw method
		g.setFont(new Font ("Courier New", Font.PLAIN, 30));
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 600);
	      
		if (ImageAnimation.changer != 1) { //if ImageAnimation.changer is not 1
        image = ImageAnimation.fadeImage(ivh.blackbeard, 0, 0, 500, 500, Colors.ALPHA_RGB, 0.007F);
        g.drawImage(image, 150, 50, null);
       
		} else {
			g.drawImage(ivh.blackbeard, 150, 50, 500, 500, null);
			if (a < 256) { //if a is less than 256
		       	a++;
		       	g.setColor(new Color (255, 255, 255, a));    	
			} else if (counter < 420){ //if a is greater than or equal to 256 and counter is less than 420
				g.setColor(Color.WHITE);
			}
		    g.drawString("This is Blackbeard, this is you.", 110, 500);
		    if (counter == 419) { //if counter is 419
		    	a = 0;
		    }
		}
		if (counter > 420) { //if counter is less than 420
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 600);
			
			for (int i = 0; i < 3; i++) { //for loop to draw 6 images
				g.drawImage(ivh.sickPirate, -1260 + (counter * 3) + (i * 100), 200, null);
				g.drawImage(ivh.sickVillager, -1260 + (counter * 3) + (i * 100), 275, null);
			}
			if (a < 256) { //if a is less than 256
		       	a++;
		       	g.setColor(new Color (255, 255, 255, a));    	
			} else {
				g.setColor(Color.WHITE);
			}
			g.drawString("These are the infected.", 185, 500);
			g.drawString("These are the COVID spreaders.", 125, 550);
			if (counter == 794)  { //if counter is 794
				a = 0;
			}
		}
		if (counter > 795) { //if counter is greater than 795
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 600);
			
			for (int i = 0; i < 3; i++) { //for loop to draw 15 images
				g.drawImage(ivh.woodlog, 200, -2605 + (counter * 3) + (i * 100), 100, 100, null);
				g.drawImage(ivh.apple, 300, -2585 + (counter * 3) + (i * 100), 50, 50, null);
				g.drawImage(ivh.bread, 375, -2585 + (counter * 3) + (i * 100), 50, 50, null);
				g.drawImage(ivh.bucket, 450, -2585 + (counter * 3) + (i * 100), 50, 50, null);
				g.drawImage(ivh.waterBottle, 535, -2585 + (counter * 3) + (i * 100), 50, 50, null);
			}
				
				if (a < 256) { //if a is less than 256
			       	a++;
			       	g.setColor(new Color (255, 255, 255, a));    	
				} else {
					g.setColor(Color.WHITE);
				}
				g.drawString("You need the items to fix your ship and", 35, 450);
				g.drawString("for your voyage at sea. This village", 65, 500);
				g.drawString("is infected and you must leave ASAP.", 70, 550);
				if (counter == 1079) { //if counter is 1079
					a = 0;
				}
		}
		if (counter > 1080) {  //if counter is greater than 1080
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 600);	
			g.setFont(new Font ("Courier New", Font.PLAIN, 100));
			if (a < 256) { //if a is less than 256
		       	a++;
		       	g.setColor(new Color (255, 255, 255, a));    	
			} else {
				g.setColor(Color.WHITE);
			}
			g.drawString("GOODLUCK", 167, 325);
			
		}
		if (counter == 1180) { //if counter is 1180
			gsm.states.push(new OverworldState(gsm));
		}		
        counter++;
	}

	public void keyPressed(int k) { //keyPressed method
	}

	public void keyReleased(int k) { //keyRelease method
	}
}
