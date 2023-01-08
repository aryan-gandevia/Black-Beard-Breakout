/*
 * Aryan Gandevia, Brian Kang, Gary Qin
 * June 15, 2020
 * Ms. Krasteva
 * The "GameStateManager" class - Manages all game states and updates whichever state is on
 * 								  the top of a stack. Runs the methods for each game state.
 * 
 * Credits to Patrick Feltes' Java 2D Platformer Java Tutorial Series
 * Used to create foundation of a JFrame game. Baseline functions from
 * https://www.youtube.com/watch?v=ByucQN42OOY&list=PLJSII25WrAz5Pf1QMx0aUL0pEuZzdFp7W&index=2&t=0s
 */

/*
 * Variable Library:
 * Name                     Type                            Description
 * 
 * states					Stack<GameState>				A stack that holds every game state. Essentially, the program flow
 * 															of the game. When a state is pushed (eg. levelOneState is called 
 * 															and pushed from overworldState), that state takes priority and is pushed
 * 															to the top of the program flow to be run immediately.
*/

package com.blackbeard.game.gamestate;

import java.awt.Graphics;
import java.util.Stack;


/* Manages all game states and updates whichever state is on the top of a stack
 * eg. if in pause state, the class updates to draw the pause screen and checks for key presses exclusive to the pause state
 * eg. if in levelOne state, the class updates to draw the background, enemies, and graphics of level one and key presses in level one
 */
public class GameStateManager { 
	
	public Stack<GameState> states;
	// a stack holds all the game states that have to run in the program
	// when a game state is called, it is pushed to the top of the stack and runs it
	
	public GameStateManager() {
		states = new Stack<GameState>();
		states.push(new SplashScreenState(this));
	}
	
	// .peek() looks at the object at the top of the stack
	public void tick() {
		states.peek().tick();
	}
	
	public void draw(Graphics g) {
		states.peek().draw(g);
	}
	
	public void keyPressed(int k) {
		states.peek().keyPressed(k);
	}
	
	public void keyReleased(int k) {
		states.peek().keyReleased(k);
	}
}
