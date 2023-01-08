/*
 * Aryan Gandevia. Gary Qin, Brian Kang
 * Mrs. Krasteva
 * June 19, 2020
 * The "ImageVariableHolder" class - This class holds the images in the game specifically and some other portions of the program, and is very useful in reducing the lag in the game.
 * This was implemented when we realized our game had too much lag loading images in the levels itself
 * 
 * EACH VARIABLE IS JUST A BUFFEREDIMAGE HOLDING A DIFFERENT PICTURE, THE NAMES ARE VERY SELF-EXPLANAORY, EXCEPT THE TOP 2 METHODS
 * 
 * Instance Variables:
 * 
 * Name						Type						Description
 * 
 * images					ImageImporting				This class used for instantiating the object of the ImageImporting class
 * <variable name>			public BufferedImage		This is used to hold the images loaded and used in the game
 */

// its package
package com.blackbeard.game.graphics;

// importing BufferedImage
import java.awt.image.BufferedImage;

public class ImageVariableHolder {
	

    ImageImporting images = new ImageImporting();	// object for image importing
    // the variables for different images
    public BufferedImage levelOneBg;
    public BufferedImage downArrow;
    public BufferedImage grass;
    public BufferedImage rock;
    public BufferedImage woodlog;
    public BufferedImage mask;
    public BufferedImage cloud;
    public BufferedImage portal;
    public BufferedImage sickVillager;
    public BufferedImage levelTwoBg;
    public BufferedImage wood;
    public BufferedImage apple;
    public BufferedImage bread;
    public BufferedImage goldMask;
    public BufferedImage portalR;
    public BufferedImage levelThreeBg;
    public BufferedImage waterBottle;
    public BufferedImage soap;
    public BufferedImage bucket;
    public BufferedImage leftRight;
    public BufferedImage upDown;
    public BufferedImage blackbeard;
    public BufferedImage sickPirate;
    
    /* The constructor initializes the value of every value using the setPathway and getImage of the ImageImporting class. */
	public ImageVariableHolder () {	
		// blue background
		images.setPathway("/Pictures/blueBackground.jpg");
	    images.setImage();
	    levelOneBg = images.getPicture();
	    
	    // red arrow
	    images.setPathway("/Pictures/downArrow.png");
		images.setImage();
		downArrow = images.getPicture();
		
		//Grass blocks
		images.setPathway("/GameItems/grass.png");
	    images.setImage();
	    grass = images.getPicture();
	    
	    // rock blocks
	    images.setPathway("/GameItems/rock.png");
	    images.setImage();
	    rock = images.getPicture();
	    
	    // woodlog object
	    images.setPathway("/Pictures/woodlog.png");
		images.setImage();
		woodlog = images.getPicture();
		
		// mask object
		images.setPathway("/GameItems/mask.png");
		images.setImage();
		mask = images.getPicture();
		 
		// cloud objects
		images.setPathway("/GameItems/viruscloud.png");
		images.setImage();
		cloud = images.getPicture();
		
		// portal object
		images.setPathway("/GameItems/teleporter.png");
		images.setImage();
		portal = images.getPicture();
		
		// sick villager
		images.setPathway("/GameItems/sickvillager.png");
		images.setImage();
		sickVillager = images.getPicture();	
		
		// level two background
		images.setPathway("/Pictures/levelTwoBackground.jpg");
	    images.setImage();
	    levelTwoBg = images.getPicture();
	    
	    // wood blocks
		images.setPathway("/GameItems/wood.png");
	    images.setImage();
	    wood = images.getPicture();
	    
	    // apple object
	    images.setPathway("/GameItems/apple.png");
		images.setImage();
		apple = images.getPicture();
		
		// bread object
		images.setPathway("/GameItems/bread.png");
		images.setImage();
		bread = images.getPicture();
		
		// gold mask
		images.setPathway("/GameItems/goldMask.png");
		images.setImage();
		goldMask = images.getPicture();
		
		// right side portal
		images.setPathway("/GameItems/portalRight.png");
		images.setImage();
		portalR = images.getPicture();	
		
		// level three background
		images.setPathway("/Pictures/orangePixelBackground.png");
	    images.setImage();
	    levelThreeBg = images.getPicture();
	    
	    // water bottle object
	    images.setPathway("/GameItems/waterbottle.png");
	    images.setImage();
	    waterBottle = images.getPicture();
	    
	    
	    //soap object
	    images.setPathway("/GameItems/soap.png");
	  	images.setImage();
	  	soap = images.getPicture();
	  	
	  	// water bucket
	  	images.setPathway("/GameItems/bucket.png");
		images.setImage();
		bucket = images.getPicture();
		
		// left and right arrow key
		images.setPathway("/Pictures/leftRight.png");
	    images.setImage();
	    leftRight = images.getPicture();
	    
	    // up and down arrow key
	    images.setPathway("/Pictures/upDown.png");
	    images.setImage();
	    upDown = images.getPicture();
	    
	    // blackbeard object
	    images.setPathway("/GameItems/Player.png");
	    images.setImage();
	    blackbeard = images.getPicture();
	    
	    // sick pirate object
	    images.setPathway("/GameItems/sickpirate.png");
	    images.setImage();
	    sickPirate = images.getPicture();
	}
}
