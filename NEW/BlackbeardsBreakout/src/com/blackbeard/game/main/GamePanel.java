/*
 * Aryan Gandevia, Gary Qin, Brian Kang
 * June 15, 2020
 * Ms. Krasteva
 * The "GamePanel" class - declares all properties of the game and JFrame. Initializes
 * 						   all methods in every game state by doing things such as
 * 						   limiting frames per second and initializing game state manager
 * 
 * Credits to Patrick Feltes' Java 2D Platformer Java Tutorial Series
 * Used to create foundation of a JFrame game. Baseline functions from
 * https://www.youtube.com/watch?v=ByucQN42OOY&list=PLJSII25WrAz5Pf1QMx0aUL0pEuZzdFp7W&index=2&t=0s
 */

/*
 * Variable Library:
 * Name                     Type                            Description
 * 
 * serialVersionUID 		static long						Prevents InvalidClassException errors by changing the default
 * 															serialVersionUID to 1L
 * 															https://stackoverflow.com/questions/285793/what-is-a-serialversionuid-and-why-should-i-use-it
 * WIDTH					static int						Stores width of JFrame
 * HEIGHT					static int						Stores height of JFrame
 * thread					Thread							Allows for GamePanel class to run parallel to the rest of the game and the other classes
 * isRunning				boolean							Stores whether or not the game is running
 * FPS						int								Stores how many frames pass each second in the program
 * targetTime				long							Stores the amount of time allowed for each frame to last, synced up with FPS
 * gsm						GameStateManager				Creates object of GameStateManager class, initializes all of the methods that run in a game state
 * 															and organizes all of the game state classes
 */ 

package com.blackbeard.game.main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

import com.blackbeard.game.gamestate.GameStateManager;

public class GamePanel extends JPanel implements Runnable, KeyListener {
	public static final long serialVersionUID = 1L; // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/Serializable.html
	
	public static final int WIDTH = 800; // width of frame
	public static final int HEIGHT = 600; // height of frame
	
	private Thread thread;
	private boolean isRunning = false; // checks if game is running
	
	private int FPS = 60; // frames per second
	private long targetTime = 1000 / FPS; // amount of time allowed for a frame
	
	private GameStateManager gsm; // creates object of game state manager
	
	public GamePanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT)); // sets preferred size of panel
		
		addKeyListener(this); // receives and reads keyboard events
		setFocusable(true); // allows object to be a focus owner for keyboard input
		
		start();
	}
	
	private void start() { // when this method is called, the run method is also called
		
		isRunning = true;
		thread = new Thread(this); // "this" refers to the implemented Runnable
		thread.start();
	}
	
	public void run() {
		long start, elapsed, wait;
		
		gsm = new GameStateManager(); // initializes game state manager
		
		/*   this while method ensures that the fixed FPS for every computer will be 60 fps
		 * 	 this is done by checking the amount of time passed in a tick. If it is
		 *   faster than it should be, the thread is slept until it is as fast as
		 *   60 fps goes.
		 */
		while(isRunning) {
			start = System.nanoTime(); // stores nanoseconds from some arbitrary but fixed time
			
			tick();
			repaint();
			
			elapsed = System.nanoTime() - start; // stores nanoseconds from beginning of loop to elapsed
			wait = targetTime - elapsed / 1000000;
		
			if(wait <= 0) {
				wait = 5;
			}
			
			try {
				Thread.sleep(wait);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void tick() { // updates game logic
		gsm.tick();
	}
	
	public void paintComponent(Graphics g) { // where graphics are drawn
		super.paintComponent(g);
		
		g.clearRect(0, 0, WIDTH, HEIGHT); // clears screen	
		gsm.draw(g);
	}
	
	public void keyPressed(KeyEvent e) { // checks if a key is pressed down
		gsm.keyPressed(e.getKeyCode());
	}

	public void keyReleased(KeyEvent e) { // checks if a key is released
		gsm.keyReleased(e.getKeyCode());
	}
	
}
