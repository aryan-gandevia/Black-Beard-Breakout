/*
 * Aryan Gandevia, Gary Qin, Brian Kang
 * June 15, 2020
 * Mrs. Krasteva
 * The "GameWonState class" - this class is the design of the game won screen
 */

/*
 * Variable Library:
 * Name						Type                            Description
 * 
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

import com.blackbeard.game.graphics.ImageImporting;

public class GameWonState extends GameState {
	//instance variables
	static BufferedImage image;
    ImageImporting images = new ImageImporting();
    BufferedImage picture;
	
	public GameWonState(GameStateManager gsm) { //class contructor
		super(gsm);
	}

	public void init() { //init method
	}
	public void tick() { //tick method
	}

	public void draw(Graphics g) { //draw method
		images.setPathway("/Pictures/finishScreen.jpg");
		images.setImage();
		picture = images.getPicture();
		g.drawImage(picture, 0, 0, null);
		
		g.setFont(new Font("Sylfaen", Font.BOLD, 40));
		g.setColor(Color.BLACK);
		g.drawString("Well done, you've successfully escaped", 35, 70);
		g.drawString("the village without geting COVID-19.", 50, 115);
		g.drawString("The worst part is over. You may sail", 55, 160);
		g.drawString("at peace, free and away from the", 90, 205);
		g.drawString("dangers of that virus. Well done", 85, 250);
		g.drawString("Blackbeard, and safe sailing.", 120, 295);
		
		g.setColor(Color.WHITE);
		g.drawString("Stay safe out there, and remember to", 45, 380);
		g.drawString("follow protocol so you don't endanger", 40, 425);
		g.drawString("yourself and others :)", 215, 470);	
		
		g.setFont(new Font("Sylfaen", Font.BOLD, 35));
		g.setColor(Color.BLACK);
		g.drawString("Press ENTER to go back to the main menu...", 25, 550);
		OverworldState.firstDone = false;
		OverworldState.secondDone = false;
		OverworldState.thirdDone = false;
		
	}

	public void keyPressed(int k) { //keyPressed method
		if (k == KeyEvent.VK_ENTER) { //if the enter key is pressed
			OverworldState.deathCounter = 0;
			gsm.states.push(new MenuState(gsm));
		}
	}

	public void keyReleased(int k) { //keyReleased method
	}

}
