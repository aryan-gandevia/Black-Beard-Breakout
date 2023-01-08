/*
 * Aryan Gandevia, Gary Qin, Brian Kang
 * June 15, 2020
 * Mrs. Krasteva
 * The "MenuState class" - this class is the menu screen. This screen connects the main segments of the game together by directing the user to a different gamestate once they select an option
 */

/*
 * Variable Library:
 * Name						Type                            Description
 * 
 * picture					BufferedImage					This variable holds the image loaded from the image importing class
 * images					ImageImporting					This is the class instance that is used for the image importing
 * currectSelection			int								This variable stores the current selection of the menu options
 */

package com.blackbeard.game.gamestate;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.blackbeard.game.graphics.ImageImporting;

import java.awt.Color;
import java.awt.Font;

public class MenuState extends GameState {
	//instance variables
	BufferedImage picture;
    ImageImporting images = new ImageImporting();
	private int currentSelection = 1;
	
	public MenuState(GameStateManager gsm) { //class constructors
		super(gsm);
	}

	public void init() { //init method
	}

	public void tick() { //tick method
	}

	public void draw(Graphics g) { //draw method
		images.setPathway("/Pictures/menuScreen.jpg"); // importing image of menu screen background
	    images.setImage();
	    picture = images.getPicture();
	    g.drawImage(picture, 0, 0, null);
		
		images.setPathway("/Pictures/BBBreakout.png");  // importing image of title
        images.setImage();
        picture = images.getPicture();
        g.drawImage(picture, 0, 20, null);
		
		g.setColor(Color.WHITE); // draws boxes for menu options
		g.fillRoundRect(40, 450, 200, 80, 10, 10);
		g.fillRoundRect(300, 450, 200, 80, 10, 10);
		g.fillRoundRect(560, 450, 200, 80, 10, 10);
		
		g.setColor(Color.BLACK); // prints out what each menu option does
		g.setFont(new Font("Georgia", Font.PLAIN, 40));
		g.drawString("Start", 95, 500);
		g.setFont(new Font("Georgia", Font.PLAIN, 40));
		g.drawString("Tutorial", 327, 500);
		g.setFont(new Font("Georgia", Font.PLAIN, 40));
		g.drawString("Quit", 615, 500);
		
		images.setPathway("/Pictures/downArrow.png"); // importing image of arrow indicating which option the user is currently selecting
	    images.setImage();
	    picture = images.getPicture();
	    if(currentSelection == 1) { //if currentSelection is 1
	    	g.drawImage(picture, 87, 275, 100, 150, null); // start button created
	    } else if(currentSelection == 2) { //if currentSelection is 2
	    	g.drawImage(picture, 347, 275, 100, 150, null); // tutorial button created  	
	    } else if(currentSelection == 3) { //if currentSelection is 3
	    	g.drawImage(picture, 607, 275, 100, 150, null);	// quit button created    	
	    }
	}

	public void keyPressed(int k) { //keyPressed method
		if(k == KeyEvent.VK_LEFT) { // if left arrow button pressed
			if(currentSelection == 1) { // if at most left option in menu screen, 
				currentSelection = 3; // jump to most right option
			} else {
				currentSelection--; // go to option on current option's left
			}
		}
		else if(k == KeyEvent.VK_RIGHT) { // if right arrow button pressed
			if(currentSelection == 3) { // if at most right option in menu screen, 
				currentSelection = 1; // jump to most left option
			} else {
				currentSelection++; // go to option on current option's right
			}
		}
		
		if(k == KeyEvent.VK_ENTER) { //if the enter key is pressed
			if(currentSelection == 1) { // if start button is selected  
				gsm.states.push(new IntroductionState(gsm)); // go to game overworld
			}
			else if(currentSelection == 2) { // if tutorial button is selected
				gsm.states.push(new InstructionsState(gsm)); // go to instructions screen
			}
			else if(currentSelection == 3) { // if exit button is selected
				gsm.states.push(new ExitState(gsm)); // go to exit screen
			}
		}
	}

	public void keyReleased(int k) {  //keyReleased method
	}

}
