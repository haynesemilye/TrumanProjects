
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.truman.cs260.HaynesLucarz.*;

/** A program to bounce shapes around a box. 
 * @author Emily Haynes
 * @author Katie Lucarz
 * @date 9 May 2012
 */
public class HaynesLucarzProject3 {

	public static void main(String[] args) 
	{
		//Instance variables for the class
	//Where to initialize new shape
	final double TOP_CORNER = 20.0; 
	final double SHAPE_SIZE = 100.0; // Size of the shape
	JPanel buttons = new JPanel(); //Buttons panel
	JFrame frame = new JFrame(); //Container
	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	//The container for the bouncing shapes
	final AnimationComponent scene 
		= new AnimationComponent();
	
	//Button to create circle
	JButton circleButton = new JButton("Circle");
	circleButton.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			scene.add(new CircleShape(TOP_CORNER, 
										TOP_CORNER, 
										SHAPE_SIZE));
		}
	});
	
	//Button to add square
	JButton squareButton = new JButton("Square");
	squareButton.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			scene.add(new SquareShape(TOP_CORNER, 
										TOP_CORNER, 
										SHAPE_SIZE));
		}
	});
	
	//Add everything to screen
		buttons.add(circleButton);
		buttons.add(squareButton);
		frame.add(scene, BorderLayout.CENTER);
		frame.add(buttons, BorderLayout.SOUTH);
		frame.setSize(500, 500);
		frame.setVisible(true);
	}

}
