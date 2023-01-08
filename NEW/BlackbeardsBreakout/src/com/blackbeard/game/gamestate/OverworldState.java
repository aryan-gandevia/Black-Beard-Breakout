/*
 * Aryan Gandevia, Gary Qin, Brian Kang
 * June 15, 2020
 * Mrs. Krasteva
 * The "Overworld class" - this class is the overworld screen that updates according to the completion level
 */

/*
 * Variable Library:
 * Name						Type                            Description
 * 
 * image					BufferedImage					This variable holds the image loaded from the image importing class
 * images					ImageImporting					This is the class instance that is used for the image importing
 * picture					BufferedImage					This variable holds the image loaded from the image importing class
 * ivh						ImageVariableHolder				This class holds the image variable
 * firstDone				boolean							This variable stores whether the first level has been completed or not
 * secondDone				boolean							This variable stores whether the second level has been completed or not
 * thirdDone				boolean							This variable stores whether the third level has been completed or not
 * deathCounter				int								This variable stores the number of deaths the player has 
 */

package com.blackbeard.game.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.blackbeard.game.entities.Player;
import com.blackbeard.game.graphics.ImageImporting;
import com.blackbeard.game.graphics.ImageVariableHolder;

public class OverworldState extends GameState{
	//instance variables
	static BufferedImage image;
    ImageImporting images = new ImageImporting();
    BufferedImage picture;   
    ImageVariableHolder ivh = new ImageVariableHolder();
	public static boolean firstDone = false;
	public static boolean secondDone = false;
	public static boolean thirdDone = false;
	public static int deathCounter = 0;
	
	public OverworldState(GameStateManager gsm) { //class constructor
		super(gsm);
	}

	
	public void init() { //init method
	}

	
	public void tick() { //tick method
	}

	
	public void draw(Graphics g) { //draw method
		if (firstDone == false) { //if the first level is not completed
			images.setPathway("/GameItems/noLevels.png");
			images.setImage();
			picture = images.getPicture();
			g.drawImage(picture, 0, 0, null);
			images.setPathway("/GameItems/Player.png");
			images.setImage();
			picture = images.getPicture();
			g.drawImage(picture, 319, 200, null);	
			g.setFont(new Font("Courier New", Font.PLAIN, 40));
			g.setColor(Color.BLACK);
			g.drawString("Press ENTER", 20, 50);
			g.drawString("to start", 20, 100);
			g.drawString("the first", 20, 150);
			g.drawString("level", 20, 200);
		} else if (firstDone == true && secondDone == false) { //if the first level is completed
			images.setPathway("/GameItems/oneLevel.png");
			images.setImage();
			picture = images.getPicture();
			g.drawImage(picture, 0, 0, null);
			images.setPathway("/GameItems/Player.png");
			images.setImage();
			picture = images.getPicture();
			g.drawImage(picture, 97,320, null);
			g.setFont(new Font("Courier New", Font.PLAIN, 40));
			g.setColor(Color.BLACK);
			g.drawString("Press ENTER", 20, 50);
			g.drawString("to proceed", 20, 100);
			g.drawString("to the", 20, 150);
			g.drawString("next level", 20, 200);
			
		} else if (secondDone == true && thirdDone == false) { //if the second level is completed
			images.setPathway("/GameItems/twoLevels.png");
			images.setImage();
			picture = images.getPicture();
			g.drawImage(picture, 0, 0, null);
			images.setPathway("/GameItems/Player.png");
			images.setImage();
			picture = images.getPicture();
			g.drawImage(picture, 447,440, null);
			g.setFont(new Font("Courier New", Font.PLAIN, 40));
			g.setColor(Color.BLACK);
			g.drawString("Press ENTER", 20, 50);
			g.drawString("to proceed", 20, 100);
			g.drawString("to the", 20, 150);
			g.drawString("next level", 20, 200);
		} else if (thirdDone == true) {  //if the third level is completed
			images.setPathway("/GameItems/threeLevels.png");
			images.setImage();
			picture = images.getPicture();
			g.drawImage(picture, 0, 0, null);
			images.setPathway("/GameItems/Player.png");
			images.setImage();
			picture = images.getPicture();
			g.drawImage(picture, 687, 260, null);
			g.setFont(new Font("Courier New", Font.PLAIN, 40));
			g.setColor(Color.BLACK);
			g.drawString("Press ENTER", 20, 50);
			g.drawString("to finish", 20, 100);
			g.drawString("the game", 20, 150);
		}
		g.drawImage(ivh.blackbeard, 670, 10, null);
		g.drawString(("x" + deathCounter), 710, 45);
	}

	
	public void keyPressed(int k) { //keyPressed method
		if (firstDone == false && secondDone == false && thirdDone == false) { //if statements to start level one
			if (k == KeyEvent.VK_ENTER) {
				gsm.states.push(new LevelOneState(gsm));
			}
		} else if (firstDone == true && secondDone == false && thirdDone == false) { //if statements to start level two
			if (k == KeyEvent.VK_ENTER) {
				gsm.states.push(new LevelTwoState(gsm));
			}
		} else if (firstDone == true && secondDone == true && thirdDone == false) { //if statements to start level screen
			if (k == KeyEvent.VK_ENTER) {
				gsm.states.push(new LevelThreeState(gsm));
			}
		} else if (firstDone == true && secondDone == true && thirdDone == true) { //if statements to show the game won screen
			if (k == KeyEvent.VK_ENTER) {
				gsm.states.push(new GameWonState(gsm));
			}
		} 
		if(k == KeyEvent.VK_ESCAPE) { //if the escape key is pressed, go back to the menu
			gsm.states.push(new MenuState(gsm));
		}
		
		
	}

	
	public void keyReleased(int k) { //keyReleased method
		
		
	}

}
