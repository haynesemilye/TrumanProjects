package edu.truman.cs260.HaynesLucarz;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.JComponent;
import javax.swing.Timer;

/** Container for bouncing shapes
 * @author Emily Haynes
 * @author Katie Lucarz
 */
public class AnimationComponent extends JComponent{

	// Instance variables
	//Would not let us compile without adding this
	private static final long serialVersionUID = 1L; 
	private ArrayList <BouncyShape> shapes;
	private Point mousePoint;
	private Point mouseRelease;
	private boolean released; //to start times


	/**
	 * Method to construct component for bouncing shapes
	 */
	public AnimationComponent()
	{
		// ArrayList for shapes
		shapes = new ArrayList<BouncyShape>();

		/**
		 * MouseListener to keep track of mouse actions
		 */
		addMouseListener(new MouseAdapter()
		{
			/**
			 * To select a shape
			 * @param evet: when the mouse is pressed
			 */
			public void mousePressed(MouseEvent event)
			{
				mousePoint = event.getPoint();
				for (BouncyShape s: shapes)
				{
					if(s.contains(mousePoint))
					{
						s.setSelected(true);
					}
				}
				for (BouncyShape s: shapes)
				{
					if(s.isSelected())
					{
						s.makeLine(mousePoint);
					}
				}
			}


			/**
			 * To start shape movement
			 * @param event for when the mouse is released
			 */
			public void mouseReleased(MouseEvent event)
			{
				mouseRelease = event.getPoint();
				for(BouncyShape s : shapes)
				{
					if(s.isSelected())
					{
						s.setTranslate(mouseRelease);
						s.removeLine();
						s.setSelected(false);

					}
				}
				released = true;
			}
		});

		/**
		 * MotionListener to keep track of mouse movements
		 */
		addMouseMotionListener(new MouseMotionAdapter()
		{
			/**
			 * for when the mouse is dragged
			 * to determine the vector of motion
			 * @param event the movement of the mouse
			 */
			public void mouseDragged(MouseEvent event)
			{
				mousePoint = event.getPoint();
				for (BouncyShape s : shapes)
				{
					if (s.isSelected())
					{
						s.makeLine(mousePoint);

					}
				}	
				repaint();
			}

		});

	}
	//end constructor

	/**
	 * Method to add a shape to the ArrayList of shapes
	 * in the component
	 * @param s: object that implements 
	 * AbstractBouncyShape class
	 */
	public void add(AbstractBouncyShape s)
	{
		shapes.add(s);
		repaint();
	}

	/**
	 * Method to paint the shapes onto the component
	 * @param g: graphics object for painting
	 */
	public void paintComponent(Graphics g)
	{
		final Graphics2D g2 = (Graphics2D)g;
		for(BouncyShape b : shapes)
		{
			b.draw(g2);
		}

		//Timer for component
		Timer reDrawTime = new Timer(40, new ActionListener()
		{	
			/**
			 * Method to redraw component
			 * @param event: how often to repaint
			 */
			public void actionPerformed(ActionEvent event)
			{
				for(BouncyShape s : shapes)
				{
					s.translate(released);
					repaint();
				}
			}
		});
		reDrawTime.start();
	}	
}
