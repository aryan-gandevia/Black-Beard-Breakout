package com.blackbeard.game.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.blackbeard.game.entities.Player;
import com.blackbeard.game.graphics.Colors;
import com.blackbeard.game.graphics.ImageAnimation;
import com.blackbeard.game.graphics.ImageImporting;
import com.blackbeard.game.graphics.ImageVariableHolder;
import com.blackbeard.game.objects.Block;
import com.blackbeard.game.objects.Flag;

public class TutorialState extends GameState{
	
	private Player player;
	private Block[] b;
	private Flag[] f;
	
	String itemStatus = "0";
    static BufferedImage image;
    ImageImporting images = new ImageImporting();
    BufferedImage picture;
    int checker = 0;
    int mask = 0;
    int soap = 0;
    int delay = 0;
    
    ImageVariableHolder ivh = new ImageVariableHolder();
	
	public TutorialState(GameStateManager gsm) {
		super(gsm);
		
	}

	
	public void init() {
		player = new Player(30, 30);
		b = new Block[14];
		f = new Flag[1];
	
		// left wall
		b[0] = new Block(100 , 50, 50, 500);
		//ground level
		b[1] = new Block (150, 500, 500, 50);
	
		for (int i = 0; i < 5; i++) {
			b[2 + i] = new Block (650 + (i * 50), 500 + (i * 50), 50, 50);
		}	
			b[7] = new Block (950, 800, 500, 50);
		
		for (int i = 0; i < 5; i ++) {
			b[8 + i] = new Block (1400 + (i * 50), 800 - (i * 100), 50, 50);
		}	
		
		b[13] = new Block (1700, 500, 2750, 50);
		
		// win condition
		f[0] = new Flag(4400, 450);	
	}

	
	public void tick() {
		
		for(int i = 0; i < b.length; i++) {
			b[i].tick();
		}
		
		for(int i = 0; i < f.length; i++) {
			f[i].tick();
		}
		
		player.tick(b);
		player.tick(f);
		
		if(((int)GameState.xOffset >= 4185 && (int)GameState.xOffset <= 4235) && ((int)GameState.yOffset >= 130 && (int)GameState.yOffset <= 140) && checker == 1) {
			gsm.states.push(new MenuState(gsm));
		}
		
		if ((int)GameState.yOffset >  1000) {
			gsm.states.push(new InstructionsState(gsm));
		}
	}

	
	public void draw(Graphics g) {
		
		g.drawImage(ivh.levelOneBg, -300, -300, 4000, 4000, null);
		
		// drawing the player
		player.draw(g);
		
		// drawing the blocks and the flags
		for(int i = 0; i < b.length; i++) {
			b[i].draw(g);
		}
		for(int i = 0; i < f.length; i++) {
			f[i].draw(g);
		} 
		
		// drawing the graphics for the blocks (this is because if you put graphics on the blocks it can only be one image for all of them)
		for (int i = 0; i < 9; i++) {
			g.drawImage(ivh.rock, 100 - (int)GameState.xOffset, 50 + (i * 50) - (int)GameState.yOffset, null);
		}
		for (int i = 0; i < 4; i++) {
			g.drawImage(ivh.rock, 1450 + (i * 50) - (int)GameState.xOffset, 700 - (i * 100) - (int)GameState.yOffset, null);			
		}	
	
		for (int i = 0; i < 12; i++) {
			g.drawImage(ivh.grass, 100 + (i * 50) - (int)GameState.xOffset, 500 - (int)GameState.yOffset, null);
		}		
		for (int i = 0; i < 4; i++) {
			g.drawImage(ivh.grass, 700 + (i * 50) - (int)GameState.xOffset, 550 + (i * 50) - (int)GameState.yOffset, null);
			
		}		
		for (int i = 0; i < 10; i++) {
			g.drawImage(ivh.grass, 950 + (i * 50) - (int)GameState.xOffset, 800 - (int)GameState.yOffset, null);
			
		}		
		for (int i = 0; i < 55; i++) {
			g.drawImage(ivh.grass, 1700 + (i * 50) - (int)GameState.xOffset, 500 - (int)GameState.yOffset, null);
		}
		
		// the left and right arrow keys image
	    g.drawImage(ivh.leftRight, 550 - (int)GameState.xOffset, 150 - (int)GameState.yOffset, 200, 100, null);
	    
	    // the instructions text
	    g.setFont(new Font ("Courier New", Font.BOLD, 20));
	    g.setColor(Color.BLACK);
	    g.drawString("Hey, welcome to the tutorial. You must be Blackbeard.", 160 - (int)GameState.xOffset, 300 - (int)GameState.yOffset);
	    g.drawString("To start off this tutorial, use the left and right", 160 - (int)GameState.xOffset, 335 - (int)GameState.yOffset);
	    g.drawString("buttons to move laterally across the map. If you", 160 - (int)GameState.xOffset, 370 - (int)GameState.yOffset);
	    g.drawString("hit a wall, your momentum goes away, so you must ", 160 - (int)GameState.xOffset, 405 - (int)GameState.yOffset);
	    g.drawString("click it again. Go down the stairs to reach the", 160 - (int)GameState.xOffset, 440 - (int)GameState.yOffset);
	    g.drawString("next set of instructions.", 160 - (int)GameState.xOffset, 475 - (int)GameState.yOffset);
	    
	    // the up and down arrow keys image
	    g.drawImage(ivh.upDown, 1350 - (int)GameState.xOffset, 450 - (int)GameState.yOffset, 100, 200, null);
	    
	    // the text for the instructions
	    g.drawString("Press the up arrow to jump. It's", 950 - (int)GameState.xOffset, 525 - (int)GameState.yOffset);
	    g.drawString("been pretty self-explanatory so", 950 - (int)GameState.xOffset, 560 - (int)GameState.yOffset);
	    g.drawString("far I know, but it'll get useful", 950 - (int)GameState.xOffset, 595 - (int)GameState.yOffset);
	    g.drawString("soon, just wait. When jumping, if", 950 - (int)GameState.xOffset, 630 - (int)GameState.yOffset);
	    g.drawString("you hit a ceiling, your next jump", 950 - (int)GameState.xOffset, 665 - (int)GameState.yOffset);
	    g.drawString("will be lower, so be wary.", 950 - (int)GameState.xOffset, 700 - (int)GameState.yOffset);
	    
	    
	    // the red arrow
	    g.drawImage(ivh.downArrow, 2025 - (int)GameState.xOffset, 305 - (int)GameState.yOffset, 100, 150, null);
	    
	    g.drawString("A teleporter, you know what it does.", 1400 - (int)GameState.xOffset, 285 - (int)GameState.yOffset);
	
	    g.drawImage(ivh.portal, 1585 - (int)GameState.xOffset, 290 - (int)GameState.yOffset, 100, 110, null);
	    if (((int)GameState.xOffset >= 1425 && (int)GameState.xOffset <= 1467) && ((int)GameState.yOffset >= - 50 && (int)GameState.yOffset <= 57)) {
	    	GameState.xOffset = 1570;
	    	GameState.yOffset = 100;
	    }
	    
	    
	    // the text for the instructions
	    g.drawString("To pick up the item, simply move ", 1900 - (int)GameState.xOffset, 220 - (int)GameState.yOffset);
	    g.drawString("your character over it. You will", 1900 - (int)GameState.xOffset, 255 - (int)GameState.yOffset);
	    g.drawString("see the status update on the", 1900 - (int)GameState.xOffset, 290 - (int)GameState.yOffset);
	    g.drawString("top-left box     on the screen.", 1900 - (int)GameState.xOffset, 325 - (int)GameState.yOffset);
	    
	    // conditional to only draw if it isn't picked up yet and update its status
		if (((int)GameState.xOffset >= 1850 && (int)GameState.xOffset <= 1925) && ((int)GameState.yOffset >= 120 && (int)GameState.yOffset <= 140) && checker == 0) {
			checker = 1;
		} else if (checker == 0) {
		    g.drawImage(ivh.woodlog, 2000 - (int)GameState.xOffset, 425 - (int)GameState.yOffset, 150, 125, null);
		}
	 
		 // conditional to only draw if it isn't picked up yet and update its status
		 if (((int)GameState.xOffset >= 2620 && (int)GameState.xOffset <= 2650) && ((int)GameState.yOffset >= 120 && (int)GameState.yOffset <= 140)) {
				mask = 1;
		} else if (mask == 0) {
			 g.drawImage(ivh.mask, 2800 - (int)GameState.xOffset, 475 - (int)GameState.yOffset, null);	
		}
		 // the set of instructions
		 g.drawString("Pick up the mask to walk across the virus cloud", 2550 - (int)GameState.xOffset, 240 - (int)GameState.yOffset);
		 g.drawString("without getting infected and dying. The first time", 2550 - (int)GameState.xOffset, 275 - (int)GameState.yOffset);
		 g.drawString("you pass a cloud, the mask will MOST LIKELY work", 2550 - (int)GameState.xOffset, 310 - (int)GameState.yOffset);
		 g.drawString("(unlike a golden mask that always works).", 2550 - (int)GameState.xOffset, 345 - (int)GameState.yOffset);
		 g.drawString("However, after that, there is no guarentee it works", 2550 - (int)GameState.xOffset, 380 - (int)GameState.yOffset);
		 g.drawString("again, as reusing masks don't guarentee safety.", 2550 - (int)GameState.xOffset, 415 - (int)GameState.yOffset);
		 g.drawString("For now, we'll throw it out after one use.", 2550 - (int)GameState.xOffset, 450 - (int)GameState.yOffset);
		
		 // the virus clouds
	    for (int i = 0; i < 3; i++) {
	    	for (int n = 0; n < 3; n++) {
	    		g.drawImage(ivh.cloud, 3200 + (i * 50) - (int)GameState.xOffset, 350 + (n * 50) - (int)GameState.yOffset, null);
	    	}
	    }
	    
	    // if you walk into the virus clouds with no mask
	    if (((int)GameState.xOffset >= 3026 && (int)GameState.xOffset < 3146) && ((int)GameState.yOffset >= 50 && (int)GameState.yOffset <= 140) && mask != 1) {
	    	gsm.states.push(new MenuState(gsm));
	    }
	    
	    // the conditional to make you lose the mask after crossing the clouds
	    if ((int)GameState.xOffset > 3200) {
	    	mask = 2;
	    }
	    
		// conditional to check if it was picked up by the player
		if (((int)GameState.xOffset >= 3420 && (int)GameState.xOffset <= 3450) && ((int)GameState.yOffset >= 120 && (int)GameState.yOffset <= 140)) {
			soap = 1;
		} else if (soap == 0) {
			g.drawImage(ivh.soap, 3600 - (int)GameState.xOffset, 475 - (int)GameState.yOffset, null);
		}		
		
		// the set of instructions
		g.drawString("Soap is essential when passing by virus", 3630 - (int)GameState.xOffset, 250  - (int)GameState.yOffset);
		g.drawString("infected people, as you use it to clean", 3630 - (int)GameState.xOffset, 285  - (int)GameState.yOffset);
		g.drawString("yourself after the interaction. Passing", 3630 - (int)GameState.xOffset, 320  - (int)GameState.yOffset);
		g.drawString("an infected entity without soap will result", 3600 - (int)GameState.xOffset, 355  - (int)GameState.yOffset);
		g.drawString("in you getting infected and die.", 3600 - (int)GameState.xOffset, 390  - (int)GameState.yOffset);
		g.drawString("Just like the mask, it is gone after one", 3600 - (int)GameState.xOffset, 425  - (int)GameState.yOffset);
		g.drawString("usage of it.", 3600 - (int)GameState.xOffset, 460  - (int)GameState.yOffset);
		
		// to remove the soap from the inventory upon crossing the virus entity
		if (((int)GameState.xOffset < 3679 && (int)GameState.xOffset > 3669) && ((int)GameState.yOffset >= 120 && (int)GameState.yOffset <= 140) && soap == 1) {
			soap = 2;
		}
		
		if (((int)GameState.xOffset < 3657 && (int)GameState.xOffset > 3625) && ((int)GameState.yOffset >= 120 && (int)GameState.yOffset <= 140) && soap != 1) {
			gsm.states.push(new MenuState(gsm));			
		}
		
		// the sick villager
		g.drawImage(ivh.sickVillager, 3800 - (int)GameState.xOffset, 465 - (int)GameState.yOffset, null);
		
		// the last set of text
		g.drawString ("You're now ready. Goodluck Blackbeard, stay safe!", 4200 - (int)GameState.xOffset, 335 - (int)GameState.yOffset);
		
		g.setColor(Color.BLACK);
		for (int i = 0; i < 3; i++) {
			for (int n = 0; n < 20; n++) {
				g.drawRect(325 + (i * 50) - (n/2), 10 - (n/2), 50, 50);
			}
		}
		g.setColor(Color.WHITE);
		g.setFont(new Font ("Courier New", Font.BOLD, 20));
		if (checker == 1) {
			g.drawImage(ivh.woodlog, 320, 15, 50, 40, null);
			g.drawString(("" + checker), 350, 45);
			
		}
	
		
		if (mask == 1) {
			 g.drawImage(ivh.mask, 380, 15, null);	
		}
		
		if (soap == 1) {
			 g.drawImage(ivh.soap, 430, 15, null);	
		}
	}
	
	public void keyPressed(int k) {
		player.keyPressed(k);		
	}
	
	public void keyReleased(int k) {
		player.keyReleased(k);		
	}
}
