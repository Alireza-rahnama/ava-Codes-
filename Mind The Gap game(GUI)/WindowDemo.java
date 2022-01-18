import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.util.ArrayList;

/*
 *  The main window of the gui
 *  Notice that it extends JFrame - so we can add our own components.
 *  Notice that it implements ActionListener - so we can handle user input.
 * 
 *  @author aRahnama
 */

public class WindowDemo extends JFrame implements ActionListener {
	// gui components that are contained in this frame:
	private JPanel topPanel, bottomPanel; // top and bottom panels in the main window
	private JLabel topLabel; // a text label to appear in the top panel
	private JButton topButton; // a 'Start' button to appear in the top panel
	private GridSquare[][] gridSquares; // squares to appear in grid formation in the bottom panel
	private int x, y; // the size of the grid
	private ArrayList<String> player1Neighbours;
	private ArrayList<String> player2Neighbours;

	/*
	 * constructor method takes as input how many rows and columns of gridsquares to
	 * create
	 * it then creates the panels, their subcomponents and puts them all together in
	 * the main frame
	 * it makes sure that action listeners are added to selectable items
	 * it makes sure that the gui will be visible
	 */
	public WindowDemo(int x, int y) 
	{
		this.x = x;
		this.y = y;
		this.setSize(600, 600);
		// first create the panels
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(x, y));
		bottomPanel.setSize(500, 500);

		// then create the components for each panel and add them to it

		// for the top panel:
		topLabel = new JLabel("Don't select neighbour squares! Click to begin >>");
		topButton = new JButton("New Game");
		topButton.addActionListener(this); 

		topPanel.add(topLabel);
		topPanel.add(topButton);

		// for the bottom panel:
		// create the buttons and add them to the grid
		gridSquares = new GridSquare[x][y];
		for (int column = 0; column < x; column++) {
			for (int row = 0; row < y; row++) {
				gridSquares[column][row] = new GridSquare(column, row);
				gridSquares[column][row].setSize(20,20);
				gridSquares[column][row].setColor();
				gridSquares[column][row].setOpaque(true); // without this line and the next the OS' default
				gridSquares[column][row].setBorderPainted(true); // look & feel will dominate / interfere
				bottomPanel.add(gridSquares[column][row]);
			}
		}

		// now add the top and bottom panels to the main frame
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(topPanel, BorderLayout.NORTH);
		getContentPane().add(bottomPanel, BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	public void disableAction(){
		for ( int column = 0; column < x; column ++)
					{
						for ( int row = 0; row < y; row ++)
						{
							gridSquares [column][row].removeActionListener(this);	
						}
					}

	}

	/*
	 * handles actions performed in the gui
	 * this method must be present to correctly implement the ActionListener
	 * interface
	 */
	public void actionPerformed (ActionEvent aevt)
	{
		// get the object that was selected in the gui
		Object selected = aevt.getSource();
		
		/*
		 * I'm using instanceof here so that I can easily cover the selection of any of the gridsquares
		 * with just one piece of code.
		 * 
		 */
		
		// if a gridsquare is selected then switch its color at each player's turn, change the information label's text appropriately
		//based on the information of the Mind The Gap game.
		if ( selected instanceof GridSquare)
		{
			if(((GridSquare) selected).getBackground()==Color.white){
				topLabel.setText(topLabel.getText()== "Player 1's turn..." ? "Player 2's turn..." : "Player 1's turn...");
				boolean Decider= topLabel.getText()== "Player 1's turn...";
				((GridSquare) selected).switchColor(Decider);
				((GridSquare) selected).setName();
				//added below for debugging delete later!!!!
				System.out.println(((GridSquare) selected).getName());
				
				//this block will try to find the offset from the seected button and while every button at the time of instanciation
				/*
				**/
				for (int i=-1 ; i<2 ; i++){
					for (int j=-1 ; j<2 ; j++){
						if(!(i ==0 && j==0)){
						
							int rx = ((GridSquare) selected).getXcoord() + i;
							int ry = ((GridSquare) selected).getYcoord() + j;
							//finding the neighbour gridcell and adding them to the neighbours arrayList
			
							if (rx >= 0 && ry >= 0 && rx < x && ry < y && ((GridSquare) selected).getName().equals(("Player 1")))
							{
								gridSquares[rx][ry].setOffAccess("Player 1");
							}
			
							if (rx >= 0 && ry >= 0 && rx < x && ry < y && ((GridSquare) selected).getName().equals(("Player 2")))
							{
								gridSquares[rx][ry].setOffAccess("Player 2");
							}
						}
						
					}
				}
				//this block of code determines the winner and disable acction listener of the instances of gridSqaures
				if(!(((GridSquare) selected).isAccessibleToP1()) && ((GridSquare) selected).getName().equals("Player 1")){
					topLabel.setText("Player 2 Won!");
					for ( int column = 0; column < x; column ++)
					{
						for ( int row = 0; row < y; row ++)
						{
							gridSquares [column][row].removeActionListener(this);	
						}
					}
					
				}

				if(!(((GridSquare) selected).isAccessibleToP2()) && ((GridSquare) selected).getName().equals("Player 2")){
					topLabel.setText("Player 1 Won!");
					for ( int column = 0; column < x; column ++)
					{
						for ( int row = 0; row < y; row ++)
						{
							gridSquares [column][row].removeActionListener(this);	
						}
					}
					
				}

			}
		}

			
     
	
		
		// if top button is selected it enables the action listeners and randomly
		//assigns a player to start the game, it aalso reset the game.
		if ( selected.equals( topButton) )
		{
			
			player1Neighbours = new ArrayList<>();
			player2Neighbours = new ArrayList<>();
			Random rand= new Random();
			int a= rand.nextInt(2);
			topLabel.setText(a==0 ? "Player 1's turn..." : "Player 2's turn...");
			for ( int column = 0; column < x; column ++)
			{
				for ( int row = 0; row < y; row ++)
				{
					gridSquares [column][row].setColor();
					gridSquares [column][row].addActionListener(this);
					gridSquares [column][row].setAccessible();	
				}
			}
		}
	}

}

