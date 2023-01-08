/*
 * Aryan Gandevia, Gary Qin, Brian Kang
 * June 15, 2020
 * Ms. Krasteva
 * The "Collision" class - This class holds two methods which will be called when
 * 						   checking for collision between the player and a platform 
 *                         or flag. Checks if a point on the player is is contained
 *                         in a Block object or Flag object.
 */

package com.blackbeard.game.physics;

import java.awt.Point;

import com.blackbeard.game.objects.Block;
import com.blackbeard.game.objects.Flag;

public class Collision {
	public static boolean playerBlock(Point p, Block b) {
		return b.contains(p); // if variable p (a point on the player entity) is inside a block, return true
	}
	public static boolean playerFlag(Point p, Flag f) {
		return f.contains(p); // if variable p (a point on the player entity) is inside a flag, return true
	}
}
