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
 * soap					int						This variable used to see if you pick up the soap
 * water				int						This variable used to see if you picked up the water bucket
 * waterbottle			int						This variable used to see if you picked up the water bottle
 * waterBucket			boolean[]				This variable used to see if the water items have been picked up or not and if they should be refreshed and moved off the map into your inventory
 * ivh					ImageVariableHolder		This variabler holds the object for the ImageVariableHolder class and is used to add the images into the game without loading them with lag as they are pre-loaded
 */

// its package
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

public class LevelThreeState extends GameState{
	
	private Player player;
	private Block[] b;
	private Flag[] f;
    int soap = 0;
    int water = 0;
    int waterbottle = 0;
    boolean[] waterBucket = {false, false, false, false, false};
    ImageVariableHolder ivh = new ImageVariableHolder();
    
    /* Constructor that helps to run this through the states of the game */
	public LevelThreeState(GameStateManager gsm) {
		super(gsm);	
	}

	/* The class that loads the whold map location and gives the player its size*/
	public void init() {
		player = new Player(30, 30);	// giving the player dimensions
		b = new Block[17];		// the maount of blocks 
		f = new Flag[1];		// the flag
		
		// left wall
		b[0] = new Block(100 , 50, 50, 500);
		//ground level
		b[1] = new Block (150, 500, 350, 50);
		b[2] = new Block(500, 1200, 1650, 50);
		b[3] = new Block(2200, 1150, 50, 50);
		for(int i = 1; i < 5; i++) {
			b[3 + i] = new Block(2150 + (i * 100), 1150 - (i * 100), 50, 50);
		}
		// left branch
		b[8] = new Block(400, 1300, 100, 50);
		b[9] = new Block(300, 1200, 100, 50);
		b[10] = new Block(200, 1100, 100, 50);
		b[11] = new Block(100, 1000, 100, 50);
		b[12] = new Block(200, 900, 100, 50);
		// right branch
		b[13] = new Block(2300, 1200, 200, 50);
		b[14] = new Block(2400, 1300, 200, 50);
		b[15] = new Block(2500, 1400, 200, 50);
		b[16] = new Block(2900, 1400, 500, 50);
		
		// the flag
		f[0] = new Flag(2550, 700);
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
		if(((int)GameState.xOffset >= 2350 && (int)GameState.xOffset <= 4000) && ((int)GameState.yOffset <= 400) && water == 2 && waterbottle == 3) {
			OverworldState.thirdDone = true;	// goes to finished state
			// refreshes so you can play again
			soap = 0;
			waterbottle = 0;
			water = 0;
			for (int i = 0; i < 5; i++) {
				waterBucket[i] = false;
			}
			gsm.states.push(new WinState(gsm));	//win screen
		}		
		// if game is lost
		if ((int)GameState.yOffset > 1500) {
			// refreshes so you can replay
			soap = 0;
			waterbottle = 0;
			water = 0;
			for (int i = 0; i < 5; i++) {
				waterBucket[i] = false;
			}
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));	//loss screen
		}
	}

	/* Method that draws the game */
	public void draw(Graphics g) {
				
	    g.drawImage(ivh.levelThreeBg, -150 - (int)GameState.xOffset, -230 - (int)GameState.yOffset, 4100, 3200, null);	// the background
	    
		// drawing the player
		player.draw(g);
		
		// drawing the blocks and the flags
		for(int i = 0; i < b.length; i++) {
			b[i].draw(g);
		}
		for(int i = 0; i < f.length; i++) {
			f[i].draw(g);
		} 
	
		// drawing the platform grass graphics
	    for (int i = 0; i < 8; i++) {
			g.drawImage(ivh.grass, 100 + (i * 50) - (int)GameState.xOffset, 500 - (int)GameState.yOffset, null);
		}
	    for (int i = 0; i < 33; i++) {
			g.drawImage(ivh.grass, 500 + (i * 50) - (int)GameState.xOffset, 1200 - (int)GameState.yOffset, null);	    	
	    }	
		g.drawImage(ivh.grass, 400 - (int)GameState.xOffset, 1300 - (int)GameState.yOffset, null);
		g.drawImage(ivh.grass, 450 - (int)GameState.xOffset, 1300 - (int)GameState.yOffset, null);
		g.drawImage(ivh.grass, 300 - (int)GameState.xOffset, 1200 - (int)GameState.yOffset, null);
		g.drawImage(ivh.grass, 350 - (int)GameState.xOffset, 1200 - (int)GameState.yOffset, null);	
		g.drawImage(ivh.grass, 200 - (int)GameState.xOffset, 1100 - (int)GameState.yOffset, null);
		g.drawImage(ivh.grass, 250 - (int)GameState.xOffset, 1100 - (int)GameState.yOffset, null);
		g.drawImage(ivh.grass, 100 - (int)GameState.xOffset, 1000 - (int)GameState.yOffset, null);
		g.drawImage(ivh.grass, 150 - (int)GameState.xOffset, 1000 - (int)GameState.yOffset, null);
		g.drawImage(ivh.grass, 200 - (int)GameState.xOffset, 900 - (int)GameState.yOffset, null);
		g.drawImage(ivh.grass, 250 - (int)GameState.xOffset, 900 - (int)GameState.yOffset, null);
		g.drawImage(ivh.grass, 2200 - (int)GameState.xOffset, 1150 - (int)GameState.yOffset, null);
		for(int i = 0; i < 4; i++) {
			g.drawImage(ivh.grass, 2250 + (i * 100) - (int)GameState.xOffset, 1050 - (i * 100) - (int)GameState.yOffset, null);
		}
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 4; j++) {
				g.drawImage(ivh.grass, 2300 + (i * 100) + (j * 50) - (int)GameState.xOffset, 1200 + (i * 100) - (int)GameState.yOffset, null);
			}
		}
		b[16] = new Block(2900, 1400, 500, 50);
		
		for(int i = 0; i < 10; i++) {
			g.drawImage(ivh.grass, 2900 + (i * 50) - (int)GameState.xOffset, 1400 - (int)GameState.yOffset, null);
		}
	    
	    
		// drawing the graphics for the blocks (this is because if you put graphics on the blocks it can only be one image for all of them)
		for (int i = 0; i < 9; i++) {
			g.drawImage(ivh.rock, 100 - (int)GameState.xOffset, 50 + (i * 50) - (int)GameState.yOffset, null);
		}
		
		// the object collision and drawing of the objects that are able to be picked up into your inventory and to check if they draw on the map or on your top inventory bar
		if (((int)GameState.xOffset >= 1080 && (int)GameState.xOffset <= 1110) && ((int)GameState.yOffset >= 815 && (int)GameState.yOffset <= 845) && waterBucket[2] == false) {
			waterBucket[2] = true;
		   	waterbottle++;
		} else if (waterBucket[2] == false) {
		   	g.drawImage(ivh.waterBottle, 1256 - (int)GameState.xOffset, 1170 - (int)GameState.yOffset, 30, 30, null);
		}	
		if (((int)GameState.xOffset >= 1120 && (int)GameState.xOffset <= 1150) && ((int)GameState.yOffset >= 815 && (int)GameState.yOffset <= 845) && waterBucket[3] == false) {
			waterBucket[3] = true;
			waterbottle++;
		} else if (waterBucket[3] == false) {
		   	g.drawImage(ivh.waterBottle, 1296 - (int)GameState.xOffset, 1170 - (int)GameState.yOffset, 30, 30, null);
		}
		if (((int)GameState.xOffset >= 1160 && (int)GameState.xOffset <= 1190) && ((int)GameState.yOffset >= 815 && (int)GameState.yOffset <= 845) && waterBucket[4] == false) {
			waterBucket[4] = true;
			waterbottle++;
		} else if (waterBucket[4] == false) {
		   	g.drawImage(ivh.waterBottle, 1336 - (int)GameState.xOffset, 1170 - (int)GameState.yOffset, 30, 30, null);
		}
	    
		// level instructions
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.PLAIN, 25));
		g.drawString("Collect the 2 water buckets", 180 - (int)GameState.xOffset, 250 - (int)GameState.yOffset);
		g.drawString("and 3 water bottles to ready", 180 - (int)GameState.xOffset, 285 - (int)GameState.yOffset);
		g.drawString("yourself for your departure", 180 - (int)GameState.xOffset, 320 - (int)GameState.yOffset);
		g.drawString("from the infected village.", 180 - (int)GameState.xOffset, 355 - (int)GameState.yOffset);
	    	
		 // the virus clouds
	    g.drawImage(ivh.cloud, 300 - (int)GameState.xOffset, 455 - (int)GameState.yOffset, null);
	    // funnel
	    for(int i = 550; i <= 1050; i += 50) {
	    	g.drawImage(ivh.cloud, 450 - (int)GameState.xOffset, i - (int)GameState.yOffset, null);
	    }
	    for(int i = 300; i <= 1050; i += 50) {
	    	g.drawImage(ivh.cloud, 650 - (int)GameState.xOffset, i - (int)GameState.yOffset, null);
	    }
	    g.drawImage(ivh.cloud, 500 - (int)GameState.xOffset, 700 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 600 - (int)GameState.xOffset, 700 - (int)GameState.yOffset, null);
	    
	    for(int i = 950; i <= 1150; i += 50) {
	    	g.drawImage(ivh.cloud, 550 - (int)GameState.xOffset, i - (int)GameState.yOffset, null);
	    }  
	    // left branch
	    g.drawImage(ivh.cloud, 400 - (int)GameState.xOffset, 1200 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 300 - (int)GameState.xOffset, 1100 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 250 - (int)GameState.xOffset, 1000 - (int)GameState.yOffset, null);	    
	    // zigzag
	    g.drawImage(ivh.cloud, 800 - (int)GameState.xOffset, 1150 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 850 - (int)GameState.xOffset, 1000 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 900 - (int)GameState.xOffset, 1150 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 950 - (int)GameState.xOffset, 1000 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 1000 - (int)GameState.xOffset, 1150 - (int)GameState.yOffset, null);	    
	    // final
	    g.drawImage(ivh.cloud, 1500 - (int)GameState.xOffset, 1150 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 1500 - (int)GameState.xOffset, 1100 - (int)GameState.yOffset, null);	    
	    g.drawImage(ivh.cloud, 1600 - (int)GameState.xOffset, 1050 - (int)GameState.yOffset, null);	    
	    g.drawImage(ivh.cloud, 1670 - (int)GameState.xOffset, 1150 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 1720 - (int)GameState.xOffset, 1150 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 1770 - (int)GameState.xOffset, 1150 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 1770 - (int)GameState.xOffset, 1000 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 1870 - (int)GameState.xOffset, 1150 - (int)GameState.yOffset, null);
	    
	    // object collision with the clouds that if you run into them you die
	    if (((int)GameState.xOffset > 95 && (int)GameState.xOffset < 175) && ((int)GameState.yOffset >= 85 && (int)GameState.yOffset <= 130)) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }
	    if (((int)GameState.xOffset > 245 && (int)GameState.xOffset < 321) && ((int)GameState.yOffset >= 150 && (int)GameState.yOffset <= 770)) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }
	    if (((int)GameState.xOffset > 450 && (int)GameState.xOffset < 520) && ((int)GameState.yOffset >= -50 && (int)GameState.yOffset <= 770)) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }	    
	    if (((int)GameState.xOffset > 295 && (int)GameState.xOffset <= 365) && ((int)GameState.yOffset >= 330 && (int)GameState.yOffset <= 430)) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }
	    if (((int)GameState.xOffset > 400 && (int)GameState.xOffset <= 470) && ((int)GameState.yOffset >= 330 && (int)GameState.yOffset <= 430)) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }
	    if (((int)GameState.xOffset > 350 && (int)GameState.xOffset <= 420) && ((int)GameState.yOffset >= 585 && (int)GameState.yOffset <= 850)) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }	    
	    // left branch
	    if (((int)GameState.xOffset > 195 && (int)GameState.xOffset <= 275) && ((int)GameState.yOffset > 840 && (int)GameState.yOffset <= 925)) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }
	    if (((int)GameState.xOffset > 95 && (int)GameState.xOffset <= 175) && ((int)GameState.yOffset > 765 && (int)GameState.yOffset <= 825)) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }
	    if (((int)GameState.xOffset > 45 && (int)GameState.xOffset <= 125) && ((int)GameState.yOffset > 650 && (int)GameState.yOffset <= 710)) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }   
	    // zig zag
	    if (((int)GameState.xOffset > 595 && (int)GameState.xOffset <= 675) && ((int)GameState.yOffset >= 790 && (int)GameState.yOffset <= 840)) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }
	    if (((int)GameState.xOffset > 695 && (int)GameState.xOffset <= 775) && ((int)GameState.yOffset >= 790 && (int)GameState.yOffset <= 840)) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }
	    if (((int)GameState.xOffset > 795 && (int)GameState.xOffset <= 875) && ((int)GameState.yOffset >= 790 && (int)GameState.yOffset <= 840)) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }    
	    if (((int)GameState.xOffset > 645 && (int)GameState.xOffset <= 725) && ((int)GameState.yOffset >= 400 && (int)GameState.yOffset <= 710)) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }
	    if (((int)GameState.xOffset > 745 && (int)GameState.xOffset <= 825) && ((int)GameState.yOffset >= 400 && (int)GameState.yOffset <= 710)) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }   
	    // final
	    if (((int)GameState.xOffset > 1295 && (int)GameState.xOffset <= 1375) && ((int)GameState.yOffset >= 740 && (int)GameState.yOffset <= 840)) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }
	     if (((int)GameState.xOffset > 1395 && (int)GameState.xOffset <= 1470) && ((int)GameState.yOffset >= 690 && (int)GameState.yOffset <= 770)) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }
	    if (((int)GameState.xOffset > 1465 && (int)GameState.xOffset < 1645) && ((int)GameState.yOffset > 790 && (int)GameState.yOffset <= 860)) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }	    
	    if (((int)GameState.xOffset > 1665 && (int)GameState.xOffset < 1745) && ((int)GameState.yOffset > 790 && (int)GameState.yOffset <= 860)) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }	    
	    if (((int)GameState.xOffset > 1565 && (int)GameState.xOffset < 1645) && ((int)GameState.yOffset > 605 && (int)GameState.yOffset <= 708)) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }
	    
	    // if you pick up the soap or not
		if (((int)GameState.xOffset >= 930 && (int)GameState.xOffset <= 965) && ((int)GameState.yOffset >= 815 && (int)GameState.yOffset <= 860) && soap == 0) {
			soap = 1;
		} else if (soap == 0) {
			g.drawImage(ivh.soap, 1120 - (int)GameState.xOffset, 1175  - (int)GameState.yOffset, null);
		}
		
		// the wall of sick villagers
		for(int i = 1250; i <= 1330; i += 40) {
			for(int j = 1080; j <= 1160; j += 40) {
				g.drawImage(ivh.sickVillager, i - (int)GameState.xOffset, j - (int)GameState.yOffset, null);
			}
		}

		// if you dont have soap and run into the villagers, you die
		if (((int)GameState.xOffset > 1045 && (int)GameState.xOffset < 1195) && ((int)GameState.yOffset > 700 && (int)GameState.yOffset < 835) && soap != 1) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
	    }
		// if you have the soap and run past them you lose the soap
		if (((int)GameState.xOffset >= 1195) && soap == 1) {
			soap = 2;
		}
	
		// object collision method with the water buckets
		if (((int)GameState.xOffset >= -90 && (int)GameState.xOffset <= -55) && ((int)GameState.yOffset >= 600 && (int)GameState.yOffset <= 650) && waterBucket[0] == false) {
			waterBucket[0] = true;
			water++;
		} else if (waterBucket[0] == false) {
			g.drawImage(ivh.bucket, 100 - (int)GameState.xOffset, 970 - (int)GameState.yOffset, 30, 30, null);
		}
		if (((int)GameState.xOffset >= 2907 && (int)GameState.xOffset <= 2952) && ((int)GameState.yOffset >= 1010 && (int)GameState.yOffset <= 1065) && waterBucket[1] == false) {
			waterBucket[1] = true;
			water++;
		} else if (waterBucket[1] == false) {
			g.drawImage(ivh.bucket, 3100 - (int)GameState.xOffset, 1370 - (int)GameState.yOffset, 30, 30, null);			
		}
	
		// if the player goes through the drawed portals and teleporting him
		g.drawImage(ivh.portal, 240 - (int)GameState.xOffset, 800 - (int)GameState.yOffset, 100, 100, null);
		g.drawImage(ivh.portal, 3300 - (int)GameState.xOffset, 1300 - (int)GameState.yOffset, 100, 100, null);
		if (((int)GameState.xOffset > 70 && (int)GameState.xOffset < 120) && ((int)GameState.yOffset > 420 && (int)GameState.yOffset < 545)) {
			GameState.xOffset = 0;
			GameState.yOffset = 120;
		}
		if (((int)GameState.xOffset > 3130 && (int)GameState.xOffset < 3200) && ((int)GameState.yOffset > 900 && (int)GameState.yOffset < 1100)) {
			GameState.xOffset = 1850;
			GameState.yOffset = 800;
		}
	
		// drawing the top inventory
		g.setColor(Color.WHITE);
		for (int i = 0; i < 3; i++) {
			for (int n = 0; n < 20; n++) {
				g.drawRect(325 + (i * 50) - (n/2), 10 - (n/2), 50, 50);
			}
		}
		
		// the top inventory if items are picked up to be drawn in
		g.setColor(Color.WHITE);
		g.setFont(new Font ("Courier New", Font.BOLD, 20));
		if (water > 0) {
			g.drawImage(ivh.bucket, 330, 15, 30, 30, null);
			g.drawString(("" + water), 350, 45);
		}	
		if (waterbottle > 0) {
			g.drawImage(ivh.waterBottle, 380, 15, 30, 30, null);
			g.drawString(("" + waterbottle), 400, 45);
		}
		if (soap == 1) {
			g.drawImage(ivh.soap, 430, 15, null);
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
