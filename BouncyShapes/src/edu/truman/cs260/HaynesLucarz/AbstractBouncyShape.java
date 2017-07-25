package edu.truman.cs260.HaynesLucarz;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

/**
 * Abstract implementation of BouncyShape interface
 * @author Emily Haynes
 * @author Katie Lucarz
 * @date 9 May 2013
 */
public abstract class AbstractBouncyShape implements edu.truman.cs260.HaynesLucarz.BouncyShape 
{
	//Instance variables
	private boolean selected = false; 
	private double cornerX;
	private double cornerY;
	private double size;
	private double changeX;
	private double changeY;
	private double minBoundX = 0.0;
	private double minBoundY = 0.0;
	private double maxBoundX = 500.0; //Size of component
	private double maxBoundY = 500.0; //Size of component

	private Point2D.Double center;
	private Line2D.Double mouseFollow;

	/**
	 * Abstract constructor
	 * @param anX: Top left x-coordinate of shape
	 * @param aY: Top left y-coordinate of shape
	 * @param aSize: Size of bounding box of shape
	 */
	public AbstractBouncyShape(double anX, double aY, double aSize)
	{
		cornerX = anX;
		cornerY = aY;
		size = aSize;
		center = new Point2D.Double(size/2 + cornerX, size/2 + cornerY);
	}

	/**
	 * Setting selection state for shape
	 * @param b: if shape is selected or not
	 */
	public void setSelected(boolean b)
	{
		selected = b;
	}

	/**
	 * Checks for selection state
	 * @return selected: state of selected
	 */
	public boolean isSelected()
	{
		return selected;
	}

	/**
	 * Constructing line to be drawn
	 * @param mouse: the point of the mouse to which to connect
	 */
	public void makeLine(Point mouse)
	{
		mouseFollow = new Line2D.Double(center, mouse);
	}

	/**
	 * Removes line after mouse is released
	 */
	public void removeLine()
	{
		Point2D.Double offScreen = new Point2D.Double(0.0, 0.0);
		mouseFollow = new Line2D.Double(offScreen, offScreen);
	}

	/**
	 * Draws line from center of shape to mouse point
	 * @param g: graphics object to draw with
	 */
	public void draw(Graphics2D g)
	{
		if(mouseFollow != null)
		{
			g.draw(mouseFollow);
		}
	}

	/**
	 * Determines how far to move shape
	 * @param mousePoint: used to get distance to move shape
	 */
	public void setTranslate(Point mousePoint)
	{
		//10000.0 is 100% a magic number. Depending on processing
		//speed of computer, it slows down the shapes
		changeX = (-1)*((mousePoint.getX())/10000.0) 
		+ (center.getX())/10000.0;
		changeY = (-1)*((mousePoint.getY())/10000.0) 
		+ (center.getY())/10000.0;
	}

	/**
	 * Moves the shape
	 * @param b: determines if shape is ready to move
	 */
	public void translate(boolean b)
	{
		if (b){

			if ((cornerX+size) > maxBoundX || cornerX < minBoundX)
			{
				changeX *= (-1);
			}
			if ((cornerY+size) > maxBoundY || cornerY < minBoundY)
			{
				changeY *= (-1);
			}
			cornerX += changeX;
			cornerY += changeY;
		}
	}

	/**
	 * Checks to see if mouse point is inside of shape
	 * @param p: the point to be checked
	 */
	public boolean contains(Point p)
	{
		if(p.getX() > this.getX()
				&& p.getX() < this.getX()+this.getSize()
				&& p.getY() > this.getY()
				&& p.getY() < this.getY()+this.getSize())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Returns x location
	 * @return cornerX: the x location of the shape
	 */
	public double getX()
	{
		return cornerX;
	}

	/**
	 * Returns y location
	 * @return cornerY: the y location of the shape
	 */
	public double getY()
	{
		return cornerY;
	}

	/**
	 * Returns size
	 * @return size: the size of the shape
	 */
	public double getSize()
	{
		return size;
	}
}