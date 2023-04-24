package mind_the_gap;

import java.awt.Color;
import javax.swing.*;


/*
 *  A simple extension of JButton which primarily sets the background colour to white and switches color, label each button
 * accessible to players and flags them unaccessible to players when functions called upon them.
 *
 *  @author aRahnama
 */
public class GridSquare extends JButton
{
	private int xcoord, ycoord;     // location of this square
	private String name;
	private boolean accessibleToP1;
	private boolean accessibleToP2;
	

	// constructor takes the x and y coordinates of this square
	public GridSquare( int xcoord, int ycoord)
	{
		super();
		this.setSize(50,50);//setting the size of each button sqaure
		this.xcoord = xcoord;
		this.ycoord = ycoord;
		this.accessibleToP1=true;
		this.accessibleToP2=true;
		setColor();// sets the color blue or red	
	}
	
	// sets the color of each swuare button to white
	public void setColor( )
	{
		
		this.setBackground( Color.white);
	}
	
	// if the decider is false it sets the button's color to red, and if it's true it sets the color to blue
	public void switchColor(boolean decider)
	{
		Color colour = decider != true ? Color.red : Color.blue;
		this.setBackground( colour);
	}

	//this will avoid switching color once a button is selected.
	public void saveColor()
	{
		this.setBackground( this.getBackground());
	}
    
    // simple setters(mutators) and getters(accessor)
    public void setXcoord(int value)    { xcoord = value; }
    public void setYcoord(int value)    { ycoord = value; }
    public int getXcoord()              { return xcoord; }
    public int getYcoord()              { return ycoord; }
	public String getName(){return this.name;}

	//label the gridSquare buttons with a name based on the color of the button
	public void setName()
	{
		if(this.getBackground()==Color.red){
			this.name="Player 1";
		}
		if(this.getBackground()==Color.blue){
			this.name="Player 2";
		}
	}
	//makes the GridSquare unaccessible basd on the variable given(player1 or player 2)
	public void setOffAccess(String player){
		if(player=="Player 1"){
			this.accessibleToP1=false;
		}
		if(player=="Player 2"){
			this.accessibleToP2=false;
		}
	}
	//set the grid square accessible again when needed to both players
	public void setAccessible(){
		this.accessibleToP1=true;
		this.accessibleToP2=true;
	}
	//returns true if accessible to player1
	public boolean isAccessibleToP1(){
		return(this.accessibleToP1==true ? true: false);

	}
	//returns true if accessible to player2
	public boolean isAccessibleToP2(){
		return(this.accessibleToP2==true ? true: false);
	}	  
}
