package com.blackbeard.game.graphics;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImageAnimation {
    public static float opacity = 0f;
    public static int changer = 0;
    
    public static BufferedImage fadeImage( BufferedImage image, int x, int y, int width, int height, int type, float rate) {
        BufferedImage returnImage = null;
        
        while (opacity <= 1 && changer < 1) { 
            opacity += rate;
            returnImage = new BufferedImage(width, height, type);
            Graphics2D g2d = returnImage.createGraphics();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
            g2d.drawImage(image, x, y, width, height, null);
            g2d.dispose();
            if (opacity > 0.99) {
            	changer = 1;
            }
            return returnImage;
            
        }
        return returnImage;
    }
}