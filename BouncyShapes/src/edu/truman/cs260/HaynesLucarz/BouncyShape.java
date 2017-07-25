package edu.truman.cs260.HaynesLucarz;

import java.awt.Graphics2D;
import java.awt.Point;

/** Things a bouncy shape should do.
 * @author Emily Haynes
 * @author Katie Lucarz
 * @date 9 May 2013
 */
public interface BouncyShape {
	
	/**
	 * Setting selection state for shape
	 * @param b: if shape is selected or not
	 */
	void setSelected(boolean b);
	
	/**
	 * Checks for selection state
	 * @return selected: state of selected
	 */
	boolean isSelected();
	
	/**
	 * Constructing line to be drawn
	 * @param mouse: the point of the mouse to which to connect
	 */
	void makeLine(Point mouse);
	
	/**
	 * Removes line after mouse is released
	 */
	void removeLine();
	
	/**
	 * Determines how far to move shape
	 * @param mousePoint: used to get distance to move shape
	 */
	void setTranslate(Point mousePoint);
	
	/**
	 * Moves the shape
	 * @param b: determines if shape is ready to move
	 */
	void translate(boolean b);
	
	/**
	 * Checks to see if mouse point is inside of shape
	 * @param p: the point to be checked
	 */
	boolean contains(Point p);
	
	/**
	 * Draws something in the component
	 * @param g: graphics object to draw with
	 */
	void draw(Graphics2D g2);
}
