/*
 * Aryan Gandevia, Gary Qin, Brian Kang
 * Mrs. Krasteva
 * June 15, 2020
 * The "ImageImporting" class - This class is used to import images in an easy way, without causing errors and in a clean and effective manner, in three steps, by first declaring the path, making
 * it be set, and then giving the value to a variable.
 * 
 * Instance variables:
 * 
 * Name				Type								Description
 * 	
 * picture			public static BufferedImage			This variable holds the image in the class, that would be given to the actual image in the other method through the return method
 * pathway			public static String				This variable holds the image pathway for loading
 */

// its package
package com.blackbeard.game.graphics;

// imports
import java.awt.image.*;

import javax.imageio.ImageIO;

public class ImageImporting {

	// variables
    public static BufferedImage picture;
    public static String pathway;
    
    /* Sets all the variables to have a default null value */
    public ImageImporting () {
        picture = null;
        pathway = null;
    }
    
    /* Returns the loaded image */
    public BufferedImage getPicture () {
        return picture;
    }
    
    /* Sets the path way of the image
     * 
     * String path - this variable holds the sent-in image location
     */
    public void setPathway (String path) {
        pathway = path;
    }
    
    /* Sets the image from the pathway to the BufferedImage*/
    public void setImage () {
        try {
            picture = ImageIO.read(getClass().getResourceAsStream(pathway));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}