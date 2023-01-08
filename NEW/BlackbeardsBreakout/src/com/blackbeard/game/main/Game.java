/*
 * Aryan Gandevia, Brian Kang, Gary Qin
 * June 15, 2020
 * Ms. Krasteva
 * The "Game" class - initializes the JFrame, setting up all the properties of the JFrame such as
 * 					  if it is resizable, where the JFrame appears on the user's monitor, and the
 * 					  name of the JFrame.
 * 
 * Credits to Patrick Feltes' Java 2D Platformer Java Tutorial Series
 * Used to create foundation of a JFrame game. Baseline functions from
 * https://www.youtube.com/watch?v=ByucQN42OOY&list=PLJSII25WrAz5Pf1QMx0aUL0pEuZzdFp7W&index=2&t=0s
 */

/*
 * Variable Library:
 * Name                     Type                            Description
 * 
 * frame					JFrame							Creates a new JFrame object, names JFrame "Blackbeard's Breakout"
*/
package com.blackbeard.game.main;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Game {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Blackbeard's Breakout"); 		// Creates new JFrame, names it "Blackbeard's Breakout"
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		// Closes JFrame when IDE is closed
		frame.setResizable(false); 									// Doesn't allow JFrame to change size
		frame.add(new GamePanel(), BorderLayout.CENTER);			// Adds button panel in center of JPanel in border layout
		frame.pack();												// Resizes frame to panel size, gets preferred size of every object inside a frame
		frame.setLocationRelativeTo(null);							// Makes JFrame in middle of screen
		frame.setVisible(true);										// Makes JFrame visible
	}
}
