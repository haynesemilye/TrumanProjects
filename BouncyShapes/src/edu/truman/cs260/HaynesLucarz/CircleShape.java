package edu.truman.cs260.HaynesLucarz;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * Class for circle shape
 * @author Emily Haynes
 * @author Katie Lucarz
 * @date 9 May 2013
 */
public class CircleShape extends AbstractBouncyShape{

	/**
	 * Constructor for circle shape
	 * @param aCircleX: Top left x coordinate of circle
	 * @param aCircleY: Top left y coordinate of circle
	 * @param aDiameter: Diameter of circle
	 */
	public CircleShape(double aCircleX, 
			double aCircleY, 
			double aDiameter)
	{
		super(aCircleX, aCircleY, aDiameter);
	}

	/**
	 * Draw method overridden from abstract class
	 * @param g: graphics object to draw with
	 */
	public void draw(Graphics2D g)
	{
		g.setColor(Color.MAGENTA);
		g.fill(new Ellipse2D.Double(super.getX(), 
				super.getY(), super.getSize(), 
				super.getSize()));
		g.setColor(Color.BLACK);
		super.draw(g);
	}
}
