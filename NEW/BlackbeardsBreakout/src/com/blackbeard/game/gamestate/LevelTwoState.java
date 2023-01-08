/*
 * Aryan Gandevia, Gary Qin, Brian Kang
 * Mrs. Krasteva
 * June 15, 2020
 * The "LevelTwoState" - This class is for the second level of the game.
 * 
 * Instance Variables:
 * Name					Type					Description
 * 
 * player				private Player			This variable holds the player, and draws it every tick in the draw method to constantly update the player on the map
 * b					Block[]					This variable holds all the blocks and then draws it in draw every tick to constantly keep the map in motion
 * f					Flag[]					This variable holds the flag object that is used to win the level
 * apple				int						This variable holds the amount of apples the player has collected
 * bread				int						This variable holds the bread the player has picked up
 * mask					int						This variable checks if the player has picked up the golden mask 
 * food					boolean[]				This variable checks if a certain object has been picked up	so it can be not drawn and put into the player's inventory
 * ivh					ImageVariableHolder		This variabler holds the object for the ImageVariableHolder class and is used to add the images into the game without loading them with lag as they are pre-loaded
 */

// package
package com.blackbeard.game.gamestate;

// imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.blackbeard.game.entities.Player;
import com.blackbeard.game.graphics.ImageImporting;
import com.blackbeard.game.graphics.ImageVariableHolder;
import com.blackbeard.game.objects.Block;
import com.blackbeard.game.objects.Flag;

public class LevelTwoState extends GameState{
	
	// the variables
	private Player player;
	private Block[] b;
	private Flag[] f;
    int apple = 0;
    int bread = 0;
    int mask = 0;
    boolean[] food = {false, false, false};
    ImageVariableHolder ivh = new ImageVariableHolder();
    
    /* Constructor that helps to run this through the states of the game */
	public LevelTwoState(GameStateManager gsm) {
		super(gsm);
		
	}

	/* The class that loads the whold map location and gives the player its size*/
	public void init() {
		player = new Player(30, 30); // giving the player his size
		b = new Block[18];	// declaring the amount of blocks in the level
		f = new Flag[1];	// the flag
	
		// the map (some blocks are in the middle of the comments but can be at the middle of the map, as we added blocks as we went on)
		// left wall
		b[0] = new Block(100 , 50, 50, 500);
		//ground level
		b[1] = new Block (150, 500, 500, 50);		
		// bottom
		b[2] = new Block(700, 600, 50, 50);
		b[4] = new Block(750, 650, 50, 50);
		b[9] = new Block(850, 800, 350, 50);
		b[10] = new Block(1395, 800, 50, 50);
		b[11] = new Block(1390, 685, 50, 50);
		b[12] = new Block(1395, 570, 50, 50);
		
		// top
		b[3] = new Block(700, 400, 650, 50);
		b[5] = new Block(1600, 500, 100, 50);
		// box
		b[6] = new Block(1750, 400, 50, 100);
		b[7] = new Block(1800, 500, 250, 50);
		b[8] = new Block(1750, 250, 50, 100);
		b[13] = new Block(2050, 400, 450, 50);
		// The bottom
		b[14] = new Block (660, 900, 50, 50);
		b[15] = new Block (850, 1000, 350, 50);
		b[16] = new Block (1340, 1100, 50, 50);
		b[17] = new Block (850, 1200, 350, 50);
		
		// the flag
		f[0] = new Flag(2400, 350);	
	}

	 /* Method that ran every tick in order to check conditionals of winning or losing and constantly refreshing the blocks and flag */	
	public void tick() {
		// runs through every block in the method
		for(int i = 0; i < b.length; i++) {
			b[i].tick();
		}
		

		// runs through every flag in the map
		for(int i = 0; i < f.length; i++) {
			f[i].tick();
		}

		// runs through every block and flag tick for object collision purposes
		player.tick(b);
		player.tick(f);
		
		// if the game is won
		if(((int)GameState.xOffset >= 2212 && (int)GameState.xOffset <= 2267) && ((int)GameState.yOffset >= 15 && (int)GameState.yOffset <= 60) && (food[0] == true && food[1] == true && food[2] == true)) {
			OverworldState.secondDone = true; // goes to next level
			// refreshes the items to replay
			apple = 0;
			bread = 0;
			mask = 0;
			food[0] = false;
			food[1] = false;
			food[2] = false;
			gsm.states.push(new WinState(gsm));	// win screen
		}
		
		// if the game is lost
		if ((int)GameState.yOffset >  1000) {
			// refreshes the items
			apple = 0;
			bread = 0;
			mask = 0;
			food[0] = false;
			food[1] = false;
			food[2] = false;
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));	// win screen
		}
	}

	/* Method that draws the level */
	public void draw(Graphics g) {
	
		// the background
	    g.drawImage(ivh.levelTwoBg, 0 - (int)GameState.xOffset, -200 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.levelTwoBg, 0 - (int)GameState.xOffset, 1350 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.levelTwoBg, 0 - (int)GameState.xOffset, -1200 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.levelTwoBg, 1970 - (int)GameState.xOffset, -200 - (int)GameState.yOffset, null);
	    
		// drawing the player
		player.draw(g);
		
		// drawing the blocks and the flags
		for(int i = 0; i < b.length; i++) {
			b[i].draw(g);
		}
		for(int i = 0; i < f.length; i++) {
			f[i].draw(g);
		} 

	    for (int i = 0; i < 11; i++) {
			g.drawImage(ivh.wood, 100 + (i * 50) - (int)GameState.xOffset, 500 - (int)GameState.yOffset, null);
		}
	    
	    for (int i = 0; i < 13; i++) {
			g.drawImage(ivh.wood, 700 + (i * 50) - (int)GameState.xOffset, 400 - (int)GameState.yOffset, null);	    	
	    }
	    for (int i = 0; i < 7; i++) {
	    	g.drawImage(ivh.wood, 850 + (i * 50) - (int)GameState.xOffset, 800 - (int)GameState.yOffset, null);
	    }
	    for (int i = 0; i < 7; i++) {
	    	g.drawImage(ivh.wood, 850 + (i * 50) - (int)GameState.xOffset, 1000 - (int)GameState.yOffset, null);
	    }
	    for (int i = 0; i < 7; i++) {
	    	g.drawImage(ivh.wood, 850 + (i * 50) - (int)GameState.xOffset, 1200 - (int)GameState.yOffset, null);
	    }
	    for (int i = 0; i < 2; i++) {
	    	g.drawImage(ivh.wood, 1600 + (i * 50) - (int)GameState.xOffset, 500 - (int)GameState.yOffset, null);
	    }
	    for (int i = 0; i < 5; i++) {
	    	g.drawImage(ivh.wood, 1800 + (i * 50) - (int)GameState.xOffset, 500 - (int)GameState.yOffset, null);
	    }
	    for (int i = 0; i < 9; i++) {
	    	g.drawImage(ivh.wood, 2050 + (i * 50) - (int)GameState.xOffset, 400 - (int)GameState.yOffset, null);
	    }
	    
	    // the instructions for the level
	    g.setColor(Color.WHITE);
		g.setFont(new Font("Courier New", Font.PLAIN, 25));
		g.drawString("Collect the 3 items of food", 180 - (int)GameState.xOffset, 250 - (int)GameState.yOffset);
		g.drawString("scattered around the map to", 180 - (int)GameState.xOffset, 285 - (int)GameState.yOffset);
		g.drawString("advance to the next level.", 180 - (int)GameState.xOffset, 320 - (int)GameState.yOffset);

		// the rocks in the game for the platforms
	    for (int i = 0; i < 2; i++) {
			g.drawImage(ivh.rock, 700 + (i * 50) - (int)GameState.xOffset, 600 + (i * 50) - (int)GameState.yOffset, null);	    	
	    }
		g.drawImage(ivh.rock, 1395 - (int)GameState.xOffset, 800 - (int)GameState.yOffset, null);
		g.drawImage(ivh.rock, 1390 - (int)GameState.xOffset, 685 - (int)GameState.yOffset, null);		
		g.drawImage(ivh.rock, 1395 - (int)GameState.xOffset, 570 - (int)GameState.yOffset, null);
		for (int i = 0; i < 5; i++) {
			if (i == 2) {
			} else {
		    	g.drawImage(ivh.rock, 1750 - (int)GameState.xOffset, 250 + (i * 50) - (int)GameState.yOffset, null);				
			}
		}
	    g.drawImage(ivh.rock, 660  - (int)GameState.xOffset, 900  - (int)GameState.yOffset, null);  
	    g.drawImage(ivh.rock, 1340  - (int)GameState.xOffset, 1100  - (int)GameState.yOffset, null);    
		// drawing the graphics for the blocks (this is because if you put graphics on the blocks it can only be one image for all of them)
		for (int i = 0; i < 9; i++) {
			g.drawImage(ivh.rock, 100 - (int)GameState.xOffset, 50 + (i * 50) - (int)GameState.yOffset, null);
		}
	
		// the object collision for the items able to be picked up and detection if they go in inventory or ground
		 if (((int)GameState.xOffset >= 817 && (int)GameState.xOffset <= 845) && ((int)GameState.yOffset >= 630 && (int)GameState.yOffset <= 667) && food[0] == false) {
			 food[0] = true; 
			 apple++;
		 } else if (food[0] == false) {
			 g.drawImage(ivh.apple, 1000 - (int)GameState.xOffset, 970 - (int)GameState.yOffset, null);			 
		 }
		 if (((int)GameState.xOffset >= 1217 && (int)GameState.xOffset <= 1252) && ((int)GameState.yOffset >= 315 && (int)GameState.yOffset <= 352) && food[1] == false) {
			 food[1] = true; 
			 apple++;
		 } else if (food[1] == false) {
			 g.drawImage(ivh.apple, 1400 - (int)GameState.xOffset, 655 - (int)GameState.yOffset, null);
		 }

		 if (((int)GameState.xOffset >= 1637 && (int)GameState.xOffset <= 1677) && ((int)GameState.yOffset >= 120 && (int)GameState.yOffset <= 150) && food[2] == false) {
			 food[2] = true; 
			 bread++;
		 } else if (food[2] == false) {
			 g.drawImage(ivh.bread, 1820 - (int)GameState.xOffset, 470 - (int)GameState.yOffset, 30, 30, null);	 
		 }
		 
		 if (((int)GameState.xOffset >= 1892 && (int)GameState.xOffset <= 1925) && ((int)GameState.yOffset >= 25 && (int)GameState.yOffset <= 50) && mask == 0) {
			 mask = 1;
		 } else if (mask == 0) {
			 g.drawImage(ivh.goldMask, 2082 - (int)GameState.xOffset, 375 - (int)GameState.yOffset, null);
		 }
		 
		 // the virus clouds
	    g.drawImage(ivh.cloud, 300 - (int)GameState.xOffset, 455 - (int)GameState.yOffset, null);		
	    
	    for (int i = 0; i < 2; i++) {
	    	g.drawImage(ivh.cloud,650 - (int)GameState.xOffset, 950 + (i * 50) - (int)GameState.yOffset, null);
	    }
	    for (int i = 0; i < 3; i++) {
	    	g.drawImage(ivh.cloud, 700 + (i * 50) - (int)GameState.xOffset, 1000 - (int)GameState.yOffset, null);	 
	    }
	    for (int i = 0; i < 2; i++) {
	    	g.drawImage(ivh.cloud, 1350 - (int)GameState.xOffset, 1150 + (i * 50) - (int)GameState.yOffset, null);
	    }
	    for (int i = 0; i < 3; i++) {
	    	g.drawImage(ivh.cloud, 1200 + (i * 50) - (int)GameState.xOffset, 1200 - (int)GameState.yOffset, null);	 
	    }
	    for (int i = 0; i < 4; i++) {
	    	g.drawImage(ivh.cloud, 1200 + (i * 50) - (int)GameState.xOffset, 800 - (int)GameState.yOffset, null);
	    }
	    // staircase
	    g.drawImage(ivh.cloud, 1440 - (int)GameState.xOffset, 685 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 1345 - (int)GameState.xOffset, 570 - (int)GameState.yOffset, null);
	    // top floor
	    g.drawImage(ivh.cloud, 850 - (int)GameState.xOffset, 355 - (int)GameState.yOffset, null);
	    
	    g.drawImage(ivh.cloud, 1000 - (int)GameState.xOffset, 355 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 1000 - (int)GameState.xOffset, 310 - (int)GameState.yOffset, null);
	    
	    g.drawImage(ivh.cloud, 1200 - (int)GameState.xOffset, 355 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 1200 - (int)GameState.xOffset, 200 - (int)GameState.yOffset, null);
	    
	    // box
	    g.drawImage(ivh.cloud, 1800 - (int)GameState.xOffset, 250 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 1850 - (int)GameState.xOffset, 250 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 1800 - (int)GameState.xOffset, 300 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 1850 - (int)GameState.xOffset, 300 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 1850 - (int)GameState.xOffset, 350 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 1850 - (int)GameState.xOffset, 400 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 1950 - (int)GameState.xOffset, 300 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 1950 - (int)GameState.xOffset, 450 - (int)GameState.yOffset, null);
	    
	    // end
	    g.drawImage(ivh.cloud, 2150 - (int)GameState.xOffset, 250 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 2150 - (int)GameState.xOffset, 300 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 2150 - (int)GameState.xOffset, 350 - (int)GameState.yOffset, null); 
	    
	    
	    // object collision for walking in the clouds and if you die
	    if (((int)GameState.xOffset >= 482 && (int)GameState.xOffset <= 680) && ((int)GameState.yOffset >= 646 && (int)GameState.yOffset <= 680) && mask == 0) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }
	    if (((int)GameState.xOffset >= 962 && (int)GameState.xOffset <= 1162) && ((int)GameState.yOffset >= 856 && (int)GameState.yOffset <= 880) && mask == 0) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }
	    if (((int)GameState.xOffset >= 1025 && (int)GameState.xOffset <= 1225) && ((int)GameState.yOffset >= 463 && (int)GameState.yOffset <= 513) && mask == 0) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }
	    // start and staircase
	    if (((int)GameState.xOffset > 95 && (int)GameState.xOffset < 175) && ((int)GameState.yOffset >= 85 && (int)GameState.yOffset <= 130) && mask == 0) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }
	    if (((int)GameState.xOffset > 1145 && (int)GameState.xOffset < 1215) && ((int)GameState.yOffset >= 205 && (int)GameState.yOffset <= 285) && mask == 0) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }
	    if (((int)GameState.xOffset > 1235 && (int)GameState.xOffset < 1305) && ((int)GameState.yOffset >= 335 && (int)GameState.yOffset <= 400) && mask == 0) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }  
	    // top
	    if (((int)GameState.xOffset > 645 && (int)GameState.xOffset < 725) && ((int)GameState.yOffset >= -15 && (int)GameState.yOffset <= 30) && mask == 0) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }
	    if (((int)GameState.xOffset > 795 && (int)GameState.xOffset < 875) && ((int)GameState.yOffset >= -60 && (int)GameState.yOffset <= 30) && mask == 0) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }
	    if (((int)GameState.xOffset > 1020 && (int)GameState.xOffset < 1075) && ((int)GameState.yOffset >= -5 && (int)GameState.yOffset <= 30) && mask == 0) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));	    	
	    }	
	    if (((int)GameState.xOffset > 995 && (int)GameState.xOffset < 1075) && ((int)GameState.yOffset >= -135 && (int)GameState.yOffset <= -95) && mask == 0) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }	
	    // box
	    if (((int)GameState.xOffset > 1595 && (int)GameState.xOffset < 1725) && ((int)GameState.yOffset >= -85 && (int)GameState.yOffset <= 10) && mask == 0) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }	
	    if (((int)GameState.xOffset > 1675 && (int)GameState.xOffset < 1725) && ((int)GameState.yOffset >= 10 && (int)GameState.yOffset <= 105) && mask == 0) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }
	    if (((int)GameState.xOffset > 1645 && (int)GameState.xOffset < 1825) && ((int)GameState.yOffset >= 150 && (int)GameState.yOffset <= 185) && mask == 0) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }
	    if (((int)GameState.xOffset > 1745 && (int)GameState.xOffset < 1825) && ((int)GameState.yOffset >= -40 && (int)GameState.yOffset <= 15) && mask == 0) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }	    
	    // wall
	    if (((int)GameState.xOffset > 1945 && (int)GameState.xOffset < 2025) && ((int)GameState.yOffset >= -120 && (int)GameState.yOffset <= 50) && mask == 0) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }

	    // drawing the sick villager and if you run into him you die
		g.drawImage(ivh.sickVillager, 950 - (int)GameState.xOffset, 760 - (int)GameState.yOffset, null);
		if (((int)GameState.xOffset > 745 && (int)GameState.xOffset < 815) && ((int)GameState.yOffset >= 393 && (int)GameState.yOffset <= 420)) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }
		
		// drawing the top item bar
		g.setColor(Color.WHITE);
		for (int i = 0; i < 3; i++) {
			for (int n = 0; n < 20; n++) {
				g.drawRect(325 + (i * 50) - (n/2), 10 - (n/2), 50, 50);
			}
		}
		
		// if the items are picked up and stored in your inventory
		if (apple > 0) {
			g.drawImage(ivh.apple, 330, 15, null);
			g.setColor(Color.WHITE);
			g.setFont(new Font ("Courier New", Font.BOLD, 20));
			g.drawString(("" + apple), 350, 45);
		}
		if (bread > 0) {
			g.drawImage(ivh.bread, 380, 15, 30, 30, null);
			g.setColor(Color.WHITE);
			g.setFont(new Font ("Courier New", Font.BOLD, 20));
			g.drawString(("" + bread), 400, 45);
		}
		if (mask == 1) {
			g.drawImage(ivh.goldMask, 430, 16, 30, 30, null);
		}
	}
	
	/* Method that checks movement and sends it to the Player class's method to do the correct movement. */
	public void keyPressed(int k) {
		player.keyPressed(k);		
	}
	
	/* Method that checks movement and sends it to the Player class's method to do the correct movement. */
	public void keyReleased(int k) {
		player.keyReleased(k);		
	}
}
