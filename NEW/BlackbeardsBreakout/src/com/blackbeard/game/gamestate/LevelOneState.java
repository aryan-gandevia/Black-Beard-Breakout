/*
 * Aryan Gandevia, Gary Qin, Brian Kang
 * Mrs. Krasteva
 * June 15, 2020
 * The "LevelOneState" - This class is for the first level of the game.
 * 
 * Instance Variables:
 * Name					Type					Description
 * 
 * player				private Player			This variable holds the player, and draws it every tick in the draw method to constantly update the player on the map
 * b					Block[]					This variable holds all the blocks and then draws it in draw every tick to constantly keep the map in motion
 * f					Flag[]					This variable holds the flag object that is used to win the level
 * mask					int						This variable has values to check if you have the flag, it has not been picked up yet or it has been used
 * wood					int						This variable counts the amount of wood pieces collected in order to see if you are eligible to win the game
 * death				int						This variable holds the counter to see when the mask is used once 
 * ticker				int						This variable is used to count the ticks it will take while a player has a mask to die in a poison cloud, adding the "non-guaranteed safety" factor
 * woodChecker			boolean[]				This variable holds the boolean for each wooden log, and to use in an if statement in order to decide if its still on the map or in your inventory
 */

// its package
package com.blackbeard.game.gamestate;

// imports
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

// imports the other classes used
import com.blackbeard.game.entities.Player;
import com.blackbeard.game.graphics.ImageVariableHolder;
import com.blackbeard.game.objects.Block;
import com.blackbeard.game.objects.Flag;

public class LevelOneState extends GameState{
	
	private Player player;
	private Block[] b;
	private Flag[] f;

    int mask = 0;
    int wood = 0;
    int death = 0;
    int ticker = 0;
	boolean[] woodChecker = {false, false, false}; 
	
	ImageVariableHolder ivh = new ImageVariableHolder();
	public LevelOneState(GameStateManager gsm) {
		super(gsm);
		
	}

	/* The class that loads the whold map location and gives the player its size*/
	public void init() {
		player = new Player(30, 30);	// calling the player's dimensions in its class
		b = new Block[17];	// the amount of blocks on the map
		f = new Flag[1];	// the flag
	
		// the map (some blocks are in the middle of the comments but can be at the middle of the map, as we added blocks as we went on)
		b[0] = new Block(100 , 50, 50, 500);
		b[1] = new Block (150, 500, 500, 50);
		b[2] = new Block (700, 500, 50, 50);
		b[3] = new Block (800, 500, 50, 50);
		b[4] = new Block (950, 500, 50, 50);
		b[5] = new Block (1200, 500, 50, 50);
		b[6] = new Block (1500, 500, 50, 50);
		b[7] = new Block (1650, 400, 800, 50);	
		b[8] = new Block (775, 700, 200, 50);
		b[9] = new Block (900, 850, 150, 50);
		b[10] = new Block (675, 850, 100, 50);
		b[11] = new Block (550, 950, 50, 50);
 		b[12] = new Block (700, 1100, 150, 50);
		b[13] = new Block (1000, 1100, 100, 50);
		b[14] = new Block (1200, 1100, 50, 50);
		b[15] = new Block (2500, 1200, 50, 50);
		b[16] = new Block (2700, 1200, 150, 50);
		
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
		if(((int)GameState.xOffset >= 2123 && (int)GameState.xOffset <= 2250) && ((int)GameState.yOffset >= -20 && (int)GameState.yOffset <= 50) && wood == 3) {
			OverworldState.firstDone = true;	// going on to the next level
			// refreshing values for replay functionality
			mask = 0;
			wood = 0;
			woodChecker[0] = false;
			woodChecker[1] = false;
			woodChecker[2] = false;
			death = 0;
			ticker = 0;
			// going to win screen
			gsm.states.push(new WinState(gsm));
		}
		
		// if the game is lost
		if ((int)GameState.yOffset >  1000) {
			// refreshes the values to replay the level
			mask = 0;
			wood = 0;
			woodChecker[0] = false;
			woodChecker[1] = false;
			woodChecker[2] = false;
			death = 0;
			ticker = 0;
			// adds to amount of deaths
			OverworldState.deathCounter++;
			// going to the loss screen
			gsm.states.push(new LoseState(gsm));
		}
	}

	/* Method that draws all the graphics in the map */
	public void draw(Graphics g) {		
		
		g.drawImage(ivh.levelOneBg, -300, -300, 4000, 4000, null);	// drawing the background

		for (int i = 0 ; i < 10; i++) {
			g.drawImage(ivh.downArrow,  2500 - (int)GameState.xOffset,  100 + (i * 100) - (int)GameState.yOffset, 50, 75, null);	// part of the background for the last wood log
		}
		
		// drawing the player
		player.draw(g);
		
		// drawing the blocks and the flags
		for(int i = 0; i < b.length; i++) {
			b[i].draw(g);
		}
		for(int i = 0; i < f.length; i++) {
			f[i].draw(g);
		} 
		
		// drawing the boxes at the top of the screen to hold the items
		g.setColor(Color.WHITE);
		for (int i = 0; i < 3; i++) {
			for (int n = 0; n < 20; n++) {
				g.drawRect(325 + (i * 50) - (n/2), 10 - (n/2), 50, 50);
			}
		}
		
		// the instructions for the level completion conditions
		g.setColor(Color.BLACK);
		g.setFont(new Font("Courier New", Font.PLAIN, 25));
		g.drawString("Collect 3 pieces of wood without", 160 - (int)GameState.xOffset, 250 - (int)GameState.yOffset);
		g.drawString("contracting the virus to advance", 160 - (int)GameState.xOffset, 280 - (int)GameState.yOffset);
		g.drawString("to the next level!", 270 - (int)GameState.xOffset, 310 - (int)GameState.yOffset);
		
		// drawing the graphics of the blocks
	    for (int i = 0; i < 11; i++) {
			g.drawImage(ivh.grass, 100 + (i * 50) - (int)GameState.xOffset, 500 - (int)GameState.yOffset, null);
		}
	    for (int i = 0; i < 16; i++) {
			g.drawImage(ivh.grass, 1650 + (i * 50) - (int)GameState.xOffset, 400 - (int)GameState.yOffset, null);	    	
	    }

	 // drawing the graphics for the blocks (this is because if you put graphics on the blocks it can only be one image for all of them)
	 	for (int i = 0; i < 9; i++) {
	 		g.drawImage(ivh.rock, 100 - (int)GameState.xOffset, 50 + (i * 50) - (int)GameState.yOffset, null);
	 	}
	    g.drawImage(ivh.rock, 700 - (int)GameState.xOffset, 500 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.rock, 800 - (int)GameState.xOffset, 500 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.rock, 950 - (int)GameState.xOffset, 500 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.rock, 1200 - (int)GameState.xOffset, 500 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.rock, 1500 - (int)GameState.xOffset, 500 - (int)GameState.yOffset, null);
	        
	    for (int i = 0; i < 4; i++) {
	    	g.drawImage(ivh.rock, 775 + (i * 50) - (int)GameState.xOffset, 700 - (int)GameState.yOffset, null);
	    }
		for (int i = 0; i < 2; i++) {
			g.drawImage(ivh.rock, 675 + (i * 50) - (int)GameState.xOffset, 850 - (int)GameState.yOffset, null);
		}
		for (int i = 0; i < 3; i++) {
			g.drawImage(ivh.rock, 900 + (i * 50) - (int)GameState.xOffset, 850 - (int)GameState.yOffset, null);			
		}
		g.drawImage (ivh.rock, 550 - (int)GameState.xOffset, 950 - (int)GameState.yOffset, null);
		for (int i = 0; i < 3; i++) {
			g.drawImage(ivh.rock, 700 + (i * 50) - (int)GameState.xOffset, 1100 - (int)GameState.yOffset, null);	
		}
		for (int i = 0; i < 2; i++) {
			g.drawImage(ivh.rock, 1000 + (i * 50) - (int)GameState.xOffset, 1100 - (int)GameState.yOffset, null);	
		}
		g.drawImage(ivh.rock, 1200 - (int)GameState.xOffset, 1100 - (int)GameState.yOffset, null);
		g.drawImage(ivh.rock,  2500 - (int)GameState.xOffset, 1200 - (int)GameState.yOffset, null);
		for (int i = 0; i < 3; i++) {
			g.drawImage(ivh.rock,  2700 + (i * 50) - (int)GameState.xOffset, 1200 - (int)GameState.yOffset, null);			
		}
		
		// draws the wooden logs and checks if the player has run over any of them to update their condition and move them off the map and into the player's inventory
		 if (((int)GameState.xOffset >= 560 && (int)GameState.xOffset <= 640) && ((int)GameState.yOffset >= 725 && (int)GameState.yOffset <= 750) && woodChecker[0] == false) {
			 woodChecker[0] = true;
			 wood++;
		 } else if (woodChecker[0] == false) {
			 g.drawImage(ivh.woodlog, 710 - (int)GameState.xOffset, 1025 - (int)GameState.yOffset, 150, 125, null);			 
		 }
		 if (((int)GameState.xOffset >= 2010 && (int)GameState.xOffset <= 2110) && ((int)GameState.yOffset >= 20 && (int)GameState.yOffset <= 50) && woodChecker[1] == false) {
			 woodChecker[1] = true;
			 wood++;
		 } else if (woodChecker[1] == false) {
			 g.drawImage(ivh.woodlog, 2175 - (int)GameState.xOffset, 320 - (int)GameState.yOffset, 150, 125, null); 			 
		 }
		 if (((int)GameState.xOffset >= 2520 && (int)GameState.xOffset <= 2620) && ((int)GameState.yOffset >= 820 && (int)GameState.yOffset <= 850) && woodChecker[2] == false) {
			 woodChecker[2] = true;
			 wood++;
		 } else if (woodChecker[2] == false) {
			 g.drawImage(ivh.woodlog, 2680 - (int)GameState.xOffset, 1120  - (int)GameState.yOffset, 150, 125, null);
		 }

		 // checks if the player has collected the mask or if it is still lying on the ground
		 if (((int)GameState.xOffset > 310 && (int)GameState.xOffset < 342) && ((int)GameState.yOffset > 110 && (int)GameState.yOffset <128) && mask == 0) {
			 mask = 1;
		 } else if (mask == 0){
			 g.drawImage(ivh.mask, 500 - (int)GameState.xOffset, 475 - (int)GameState.yOffset, null);			 
		 }
		 
		// the virus clouds that are drawn throughout the maps as the obstacles that kill you
	    g.drawImage(ivh.cloud, 300 - (int)GameState.xOffset, 455 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 650 - (int)GameState.xOffset, 500 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 750 - (int)GameState.xOffset, 500 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 875 - (int)GameState.xOffset, 500 - (int)GameState.yOffset, null);
		g.drawImage(ivh.cloud, 975 - (int)GameState.xOffset, 700 - (int)GameState.yOffset, null);	 	
	    for (int i = 0; i < 2; i++) {
			g.drawImage(ivh.cloud, 787 + (i * 50) - (int)GameState.xOffset, 850 - (int)GameState.yOffset, null);	 	    	
	    }
		g.drawImage(ivh.cloud, 600 - (int)GameState.xOffset, 950 - (int)GameState.yOffset, null);	 
	    // pyramid
	    g.drawImage(ivh.cloud, 1300 - (int)GameState.xOffset, 455 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 1350 - (int)GameState.xOffset, 405 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 1350 - (int)GameState.xOffset, 455 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 1400 - (int)GameState.xOffset, 455 - (int)GameState.yOffset, null);
	    // tight jump
	    g.drawImage(ivh.cloud, 1550 - (int)GameState.xOffset, 305 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 1575 - (int)GameState.xOffset, 455 - (int)GameState.yOffset, null);
	    g.drawImage(ivh.cloud, 1600 - (int)GameState.xOffset, 405 - (int)GameState.yOffset, null);
	    
	    // conditionals that check if you have a mask or not and walk into the cloud; if you don't you die, if you do, you have a certain timer before you die in the cloud with the mask
	    if (((int)GameState.xOffset >= 470 && (int)GameState.xOffset <= 520) && ((int)GameState.yOffset >= 160 && (int)GameState.yOffset <= 210)) {
	    	if (mask == 1) {
	    		mask = 2;
	    		death++;
	    	} else if (mask != 1 && ticker > 7) {
	    		death++;
	    	} else if (death == 0) {
	    		death = 2;
	    	}
	    	ticker++;
	    } 
	    if (((int)GameState.xOffset >= 570 && (int)GameState.xOffset <= 620) && ((int)GameState.yOffset >= 160 && (int)GameState.yOffset <= 210)) {
	    	if (mask == 1) {
	    		mask = 2;
	    		death++;
	    	} else if (mask != 1 && ticker > 6) {
	    		death++;
	    	} else if (death == 0) {
	    		death = 2;
	    	}
	    	ticker++;
	    }
	    if (((int)GameState.xOffset >= 695 && (int)GameState.xOffset <= 745) && ((int)GameState.yOffset >= 160 && (int)GameState.yOffset <= 210)) {
	    	if (mask == 1) {
	    		mask = 2;
	    		death++;
	    	} else if (mask != 1 && ticker > 6) {
	    		death++;
	    	} else if (death == 0) {
	    		death = 2;
	    	}
	    	ticker++;
	    }
	    if (((int)GameState.xOffset >= 795 && (int)GameState.xOffset <= 845) && ((int)GameState.yOffset >= 350 && (int)GameState.yOffset <= 400)) {
	    	if (mask == 1) {
	    		mask = 2;
	    		death++;
	    	} else if (mask != 1 && ticker > 6) {
	    		death++;
	    	} else if (death == 0) {
	    		death = 2;
	    	}
	    	ticker++;
	    }
	    if (((int)GameState.xOffset >= 600 && (int)GameState.xOffset <= 700) && ((int)GameState.yOffset >= 510 && (int)GameState.yOffset <= 560)) {
	    	if (mask == 1) {
	    		mask = 2;
	    		death++;
	    	} else if (mask != 1 && ticker > 6) {
	    		death++;
	    	} else if (death == 0) {
	    		death = 2;
	    	}
	    	ticker++;
	    }
	    if (((int)GameState.xOffset >= 400 && (int)GameState.xOffset <= 450) && ((int)GameState.yOffset >= 610 && (int)GameState.yOffset <= 660)) {
	    	if (mask == 1) {
	    		mask = 2;
	    		death++;
	    	} else if (mask != 1 && ticker > 6) {
	    		death++;
	    	} else if (death == 0) {
	    		death = 2;
	    	}
	    	ticker++;
	    }
	    
	    
	    if (((int)GameState.xOffset > 95 && (int)GameState.xOffset < 175) && ((int)GameState.yOffset >= 85 && (int)GameState.yOffset <= 130)) {
	    	if (mask == 1) {
	    		mask = 2;
	    		death++;
	    	} else if (mask != 1 && ticker > 6) {
	    		death++;
	    	} else if (death == 0) {
	    		death = 2;
	    	}
	    	ticker++;
	    }
	    if (((int)GameState.xOffset > 1095 && (int)GameState.xOffset < 1275) && ((int)GameState.yOffset >= 85 && (int)GameState.yOffset <= 130) && mask != 1) {
	    	if (mask == 1) {
	    		mask = 2;
	    		death++;
	    	} else if (mask != 1 && ticker > 6) {
	    		death++;
	    	} else if (death == 0) {
	    		death = 2;
	    	}
	    	ticker++;
	    }
	    if (((int)GameState.xOffset > 1145 && (int)GameState.xOffset < 1225) && ((int)GameState.yOffset >= 40 && (int)GameState.yOffset <= 130) && mask != 1) {
	    	if (mask == 1) {
	    		mask = 2;
	    		death++;
	    	} else if (mask != 1 && ticker > 6) {
	    		death++;
	    	} else if (death == 0) {
	    		death = 2;
	    	}
	    	ticker++;
	    }
	    if (((int)GameState.xOffset > 1345 && (int)GameState.xOffset < 1425) && ((int)GameState.yOffset >= -65 && (int)GameState.yOffset <= -20) && mask != 1) {
	    	if (mask == 1) {
	    		mask = 2;
	    		death++;
	    	} else if (mask != 1 && ticker > 6) {
	    		death++;
	    	} else if (death == 0) {
	    		death = 2;
	    	}
	    	ticker++;
	    }
	    if (((int)GameState.xOffset > 1370 && (int)GameState.xOffset < 1450) && ((int)GameState.yOffset >= 85 && (int)GameState.yOffset <= 130) && mask != 1) {
	    	if (mask == 1) {
	    		mask = 2;
	    		death++;
	    	} else if (mask != 1 && ticker > 6) {
	    		death++;
	    	} else if (death == 0) {
	    		death = 2;
	    	}
	    	ticker++;
	    }
	    if (((int)GameState.xOffset > 1395 && (int)GameState.xOffset < 1475) && ((int)GameState.yOffset >= 40 && (int)GameState.yOffset <= 130) && mask != 1) {
	    	if (mask == 1) {
	    		mask = 2;
	    		death++;
	    	} else if (mask != 1 && ticker > 6) {
	    		death++;
	    	} else if (death == 0) {
	    		death = 2;
	    	}
	    	ticker++;
	    }
	    
	   
	    // drawing and checking if the player used the portal to teleport the player to another location
		g.drawImage(ivh.portal, 1180 - (int)GameState.xOffset, 1000 - (int)GameState.yOffset, 100, 100, null);
		if (((int)GameState.xOffset >= 1030 && (int)GameState.xOffset <= 1070) && ((int)GameState.yOffset >= 680 && (int)GameState.yOffset <= 750)) {
			GameState.xOffset = 780;
			GameState.yOffset = 127;
		}
		g.drawImage(ivh.portal, 2780 - (int)GameState.xOffset, 1100 - (int)GameState.yOffset, 100, 100, null);
		if (((int)GameState.xOffset >= 2620 && (int)GameState.xOffset <= 2680) && ((int)GameState.yOffset >= 780 && (int)GameState.yOffset <= 850)) {
			GameState.xOffset = 2000;
			GameState.yOffset = 35;
		}
		
		// the sick villager obstacles
		g.drawImage(ivh.sickVillager, 1800 - (int)GameState.xOffset, 360 - (int)GameState.yOffset, null);
		g.drawImage(ivh.sickVillager, 1900 - (int)GameState.xOffset, 360 - (int)GameState.yOffset, null);
		g.drawImage(ivh.sickVillager, 2000 - (int)GameState.xOffset, 360 - (int)GameState.yOffset, null);
		g.drawImage(ivh.sickVillager, 2100 - (int)GameState.xOffset, 360 - (int)GameState.yOffset, null);
		// checking to see if the player ran into them as this would cause death
		if (((int)GameState.xOffset > 1605 && (int)GameState.xOffset < 1665) && ((int)GameState.yOffset >= -13)) {
			death = 2;
	    }
		if (((int)GameState.xOffset > 1705 && (int)GameState.xOffset < 1765) && ((int)GameState.yOffset >= -13)) {
			death = 2;
	    }
		if (((int)GameState.xOffset > 1805 && (int)GameState.xOffset < 1865) && ((int)GameState.yOffset >= -13)) {
			death = 2;
	    }
		if (((int)GameState.xOffset > 1905 && (int)GameState.xOffset < 1965) && ((int)GameState.yOffset >= -13 )) {
			death = 2;
	    }
	
		// conditionals to see if an item was picked up and stored in the inventory of the player with the amount for wood log
		if (mask > 0) {
			 g.drawImage(ivh.mask, 380, 15, null);			
		}
		if (woodChecker[0] == true || woodChecker[1] == true || woodChecker[2] == true) {
			g.drawImage(ivh.woodlog, 322, 5, 45, 50, null);
		}
		if (wood != 0) {
			g.setColor(Color.WHITE);
			g.setFont(new Font ("Courier New", Font.BOLD, 20));
			g.drawString(("" + wood), 350, 45);
		}
		
		// if the player dies, send them to the loss screen
		if (death > 1) {
			OverworldState.deathCounter++;
			gsm.states.push(new LoseState(gsm));
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
