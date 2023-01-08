/*
 * Aryan Gandevia, Gary Qin, Brian Kang
 * June 15, 2020
 * Mrs. Krasteva
 * The "SplashScreenState class" - this class is the splash screen of the game
 */

/*
 * Variable Library:
 * Name						Type                            Description
 * 
 * picture					BufferedImage					This variable holds the image loaded from the image importing class
 * image					BufferedImage					This variable holds the image loaded from the image importing class
 * images					ImageImporting					This is the class instance that is used for the image importing
 * a						int								This variable is used to delay before exiting
 */

package com.blackbeard.game.gamestate;
import java.awt.*;
import java.awt.image.BufferedImage;

import com.blackbeard.game.graphics.Colors;
import com.blackbeard.game.graphics.ImageAnimation;
import com.blackbeard.game.graphics.ImageImporting;

public class SplashScreenState extends GameState {
    //instance variable
	BufferedImage picture;
    static BufferedImage image;
    ImageImporting images = new ImageImporting();
    int a = 0;
    
    public SplashScreenState(GameStateManager gsm) { //class contructor
        super(gsm);    
    }
    
    public void draw (Graphics g) { //draw method
    	int[] x = {25 , 400, 775 , 400};
    	int[] y = {475, 465, 475, 485};
    	
    	g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);
    	
        images.setPathway("/Pictures/CompanyLogo.png");
        images.setImage();
        picture = images.getPicture();        
        
        image = ImageAnimation.fadeImage(picture, 0, 0, 500, 500, Colors.ALPHA_RGB, 0.007F);
        g.drawImage(image, 150, 50, null);
 
        if (ImageAnimation.changer == 1) {  //if ImageAnimation.changer is 1
        	if (a < 126) { //if a is less than 126
            	g.setColor(new Color(128, 128, 128, a*2));
            } else {
            	g.setColor(new Color (128, 128, 128));
            } 
        	for (int i = 0; i <= 800; i+= 80) { //for loop to draw numerous rectangle
        		g.fillRect(30 + i, 0, 20, 600);
        	}
        	
            g.drawImage(image, 150, 50, null);
            if (a < 126) { //if a is less than 126
            	g.setColor(new Color(a*2, a*2, a*2, a*2));
            } else {
            	g.setColor(Color.white);
            } 
            g.setFont(new Font("Ink Free", Font.BOLD, 60));
        	g.drawString("Blackbeard's Breakout", 75, 300);
        	g.setFont(new Font("Segoe Print", Font.PLAIN, 30));
        	g.drawString("By: Aryan Gandevia, Gary Qin & Brian Kang", 50, 450);
        	
            g.fillPolygon(x, y, 4);    		
        	
        	for (int i = 0; i <= 30; i++) { //for loop to draw shapes
        		g.drawRect(55 - i/2, 225 - i/2, 690 + i, 125 + i);
        		g.drawLine(145 + i, 0, 145 + i, 210);
        		g.drawLine(625 + i, 0, 625 + i, 210);
        		g.drawLine(0, 272 + i, 40, 272 + i);
        		g.drawLine(800, 272 + i, 760, 272 + i);
        	}
        	a++;
        }
    }
    

    
    public void init() { //init method
        
    }

    
    public void tick() { //tick method
        if (a == 180) { //if a is 180
            gsm.states.push(new MenuState(gsm));
        }
        
    }

    
    public void keyPressed(int k) { //keyPressed method
        
        
    }

    
    public void keyReleased(int k) { //keyReleased method
        
        
    }
}