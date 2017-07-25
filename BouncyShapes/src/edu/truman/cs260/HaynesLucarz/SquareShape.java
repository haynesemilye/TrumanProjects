package edu.truman.cs260.HaynesLucarz;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

/**
 * Class for square shape
 * @author Emily Haynes
 * @author Katie Lucarz
 * @date 9 May 2013
 */
public class SquareShape extends AbstractBouncyShape{

	/**
	 * Constructor for square shape
	 * @param aSquareX: Top left x coordinate of square
	 * @param aSquareY: Top left y coordinate of square
	 * @param aLength: Diameter of square
	 */
	public SquareShape(double aSquareX, 
			double aSquareY, 
			double aLength)
	{
		super(aSquareX, aSquareY, aLength);
	}

	/**
	 * Draw method overridden from abstract class
	 * @param g: graphics object to draw with
	 */
	public void draw(Graphics2D g)
	{
		g.setColor(Color.CYAN);
		g.fill(new Rectangle2D.Double(super.getX(), 
				super.getY(), super.getSize(), 
				super.getSize()));
		g.setColor(Color.BLACK);
		super.draw(g);
	}
}
