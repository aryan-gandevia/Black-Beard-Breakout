/*
 * Aryan Gandevia, Brian Kang, Gary Qin
 * June 15, 2020
 * Ms. Krasteva
 * The "GameState" class - the abstract class that holds all the methods and variables in each
 * 						   game state. All game states extend this abstract class. Methods include:
 * 						   init() - method contains code that stores what object are in the state
 * 						   tick() - method contains code that stores what is checked every tick
 * 						   draw() - method contains code that stores what is displayed in the frame
 * 						   keyPressed() - method contains code that listens for keys pressed
 * 						   keyReleased() - method contains code that listens for keys released
 * 
 * Credits to Patrick Feltes' Java 2D Platformer Java Tutorial Series
 * Used to create foundation of a JFrame game. Baseline functions from
 * https://www.youtube.com/watch?v=ByucQN42OOY&list=PLJSII25WrAz5Pf1QMx0aUL0pEuZzdFp7W&index=2&t=0s
 */

/*
 * Variable Library:
 * Name                     Type                            Description
 * 
 * gsm						GameStateManager				Creates object of GameStateManager class, initializes all of the methods that run in a game state
 * 															and organizes all of the game state classes
 * xOffset					static double					Holds y "coordinate" of the background. 
 * yOffset					static double					Holds x "coordinate" of the background. Since the character stays in one part of the screen, the
 * 															background moves instead of the player to simulate movement while keeping the player static.
*/

package com.blackbeard.game.gamestate;

import java.awt.Graphics;


public abstract class GameState {
	
	// "protected variables can be accessed only by the subclasses in other packages or any class within the package of the protected members' class."
	// source: https://www.tutorialspoint.com/java/java_access_modifiers.htm#:~:text=Protected%20Access%20Modifier%20%2D%20Protected,applied%20to%20class%20and%20interfaces.
	protected GameStateManager gsm; 
	
	/*
	 * determines how much the background must move to simulate the character sprite
	 * moving across the map, as the character will stay in the center of the screen.
	 * eg. if the character is moving left, the program will move the background to the
	 * right to simulate the character moving to the left.
	 */
	public static double xOffset, yOffset;
	
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
		
		this.xOffset = 0; // x coordinate of background
		this.yOffset = 0; // y coordinate of background
		
		init();
	}
	
	public abstract void init();
	public abstract void tick();
	public abstract void draw(Graphics g);
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
}
