import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.*;

/**
 * 
 */

/**
 * this class extends jFrame and should create the gui of KINGDOMINO-Take A Turn
 * sketched in the prototype sketch
 * 
 *
 */
public class TakeTurn extends JFrame implements ActionListener {
	private int a;
	private JButton newGam;
	private JButton saveGame;
	private JButton calculateScore;
	private RectangleTile tile;
	private ArrayList<RectangleTile> tiles;
	private ArrayList<RectangleTile> toBeDisplayedTiles;
	private static ArrayList<RectangleTile> selectedRectangleTiles;
	private static int gridSelectedTimes;
	

	private JPanel midPanel;
	private JPanel topPanel;
	private JButton newDominos;
	static JLabel playerlabel;
	private int count;

	Icon kingMeeple;
	
	private JPanel kingdom1;
	private JPanel kingdom2;
	private JPanel kingdom3;
	private JPanel kingdom4;
	int x = 9;
	int y = 9;
	
	private GridSquare[][] kingdom1grid;
	private GridSquare[][] kingdom2grid;
	private GridSquare[][] kingdom3grid;
	private GridSquare[][] kingdom4grid;
	
	private int n = 0;
	
	private int kingdom1wheatCrowns = 0;
	private int kingdom1forestCrowns = 0;
	private int kingdom1grassCrowns = 0;
	private int kingdom1lakesCrowns = 0;
	private int kingdom1minesCrowns = 0;
	private int kingdom1swampCrowns = 0;
	private int kingdom1wheatFields;
	private int kingdom1forest;
	private int kingdom1grassLands;
	private int kingdom1lakes;
	private int kingdom1mines;
	private int kingdom1swamp;
	private int kingdom1Score;
	
	private int kingdom2wheatCrowns = 0;
	private int kingdom2forestCrowns = 0;
	private int kingdom2grassCrowns = 0;
	private int kingdom2lakesCrowns = 0;
	private int kingdom2minesCrowns = 0;
	private int kingdom2swampCrowns = 0;
	private int kingdom2wheatFields;
	private int kingdom2forest;
	private int kingdom2grassLands;
	private int kingdom2lakes;
	private int kingdom2mines;
	private int kingdom2swamp;
	private int kingdom2Score;
	
	private int kingdom3wheatCrowns = 0;
	private int kingdom3forestCrowns = 0;
	private int kingdom3grassCrowns = 0;
	private int kingdom3lakesCrowns = 0;
	private int kingdom3minesCrowns = 0;
	private int kingdom3swampCrowns = 0;
	private int kingdom3wheatFields;
	private int kingdom3forest;
	private int kingdom3grassLands;
	private int kingdom3lakes;
	private int kingdom3mines;
	private int kingdom3swamp;
	private int kingdom3Score;
	
	private int kingdom4wheatCrowns = 0;
	private int kingdom4forestCrowns = 0;
	private int kingdom4grassCrowns = 0;
	private int kingdom4lakesCrowns = 0;
	private int kingdom4minesCrowns = 0;
	private int kingdom4swampCrowns = 0;
	private int kingdom4wheatFields;
	private int kingdom4forest;
	private int kingdom4grassLands;
	private int kingdom4lakes;
	private int kingdom4mines;
	private int kingdom4swamp;
	private int kingdom4Score;
	
	private RectangleTile temp1;
	private RectangleTile temp2;
	private RectangleTile temp3;
	private RectangleTile temp4;


	/**
	 * constructor
	 */
	public TakeTurn() {

		// initilaize a jframe and its size

		this.setTitle("KingDomino-Take Turn");
		this.setSize(1600, 760);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		gridSelectedTimes=0;




		// divide the gui to 3 panels
		topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		newGam = new JButton("NEW GAME");
		newGam.addActionListener(this);
		saveGame = new JButton("SAVE GAME");
		saveGame.addActionListener(this);
		newDominos = new JButton("Display New Dominos");
		newDominos.addActionListener(this);
		calculateScore = new JButton("Calculate Score");
		calculateScore.addActionListener(this);
		playerlabel = new JLabel("Player1's Turn");
		topPanel.add(newGam);
		topPanel.add(saveGame);
		topPanel.add(calculateScore);
		topPanel.add(newDominos);
		topPanel.add(playerlabel);
		this.add(topPanel, BorderLayout.NORTH);
		midPanel = new JPanel();
		midPanel.setLayout(new GridLayout(4, 2, 25, 25));
		midPanel.setPreferredSize(new Dimension(300,350));
		
	
		//Creating the full array of RectangleTiles (dominos)
		tiles = new ArrayList<>();
		for (int i = 1; i < 49; i++) {
			// i is starting from 1 to correctly create the tiles with accurate ids
			RectangleTile tile = new RectangleTile(i);
			// adding the tiles to the array
			tiles.add(tile);
		}
		
		
		//Creating the array of RectangleTiles (dominos) that need to be displayed at a time
		toBeDisplayedTiles = new ArrayList<>();
		a = 48;
		for (int j = 0; j < 8; j++) {
			Random rand = new Random();
			int randomInteger = rand.nextInt(a);
			toBeDisplayedTiles.add(tiles.get(randomInteger));
			tiles.remove(randomInteger);
			a = a - 1;
		}
	
		
		

		// this part will sort the displayed tiles based on their Id/numbers from top to
		// bottom
		// allowing us to meet the requirements of the game
		Collections.sort(toBeDisplayedTiles, Comparator.comparingLong(RectangleTile::getNumber));

		for (RectangleTile tilee : toBeDisplayedTiles) {
			midPanel.add(tilee);
			tilee.addActionListener(this);

		}

		selectedRectangleTiles= new ArrayList<>();

		this.add(midPanel, BorderLayout.SOUTH);

		// this will include 2 or 4 kingdoms where the players select to place their
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));


		//Creating the kingdoms for a two player game
		if (NewGameSetUp.getPlayers().equals("Two Players")) {
			kingdom1 = new JPanel();
			kingdom1.setSize(200, 200);
			kingdom1.setLayout(new GridLayout(9,9));
			kingdom1grid = new GridSquare[x][y];
			for (int column = 0; column < x; column++) {
				for (int row = 0; row < y; row++) {
					// location= ""+column+row;
					kingdom1grid[column][row] = new GridSquare(column,row);
					kingdom1grid[column][row].setBorder(BorderFactory.createLineBorder(Color.yellow)); // Simple Line Border
					kingdom1grid[column][row].setOpaque(true); // without this line and the next the OS' default
					kingdom1grid[column][row].setBorderPainted(true); // look & feel will dominate / interfere
					kingdom1grid[column][row].addActionListener(this);

					if(NewGameSetUp.getDifficulty().equals("Easy")&&column==4&&row==4){
						Icon castle =new ImageIcon("images/castle1.jpg");
						kingdom1grid[column][row].setIcon(castle);

					}
					if(NewGameSetUp.getDifficulty().equals("Hard")&&column==2&&row==2){
						Icon castle =new ImageIcon("images/castle1.jpg");
						kingdom1grid[column][row].setIcon(castle);
			
					}
					if((NewGameSetUp.getDifficulty().equals("Easy")&&column==4&&row==3)
					||(NewGameSetUp.getDifficulty().equals("Easy")&&column==4&&row==5)
					||(NewGameSetUp.getDifficulty().equals("Easy")&&column==5&&row==4)
					||(NewGameSetUp.getDifficulty().equals("Easy")&&column==3&&row==4)){
						kingdom1grid[column][row].setAccessible();
					}
					kingdom1.add(kingdom1grid[column][row]);
				}
			}

			kingdom2 = new JPanel();
			kingdom2.setSize(200, 200);
			kingdom2.setLayout(new GridLayout(9,9));
			kingdom2grid = new GridSquare[x][y];
			for (int column = 0; column < x; column++) {
				for (int row = 0; row < y; row++) {
					// location= ""+column+row;
					kingdom2grid[column][row] = new GridSquare(column,row);
					kingdom2grid[column][row].setBorder(BorderFactory.createLineBorder(Color.blue)); // Simple Line Border
					kingdom2grid[column][row].setOpaque(true); // without this line and the next the OS' default
					kingdom2grid[column][row].setBorderPainted(true); // look & feel will dominate / interfere
					kingdom2grid[column][row].addActionListener(this);

					if(NewGameSetUp.getDifficulty().equals("Easy")&&column==4&&row==4){
						Icon castle =new ImageIcon("images/castle1.jpg");
						kingdom2grid[column][row].setIcon(castle);

					}
					if(NewGameSetUp.getDifficulty().equals("Hard")&&column==2&&row==2){
						Icon castle =new ImageIcon("images/castle1.jpg");
						kingdom2grid[column][row].setIcon(castle);
			
					}
					if((NewGameSetUp.getDifficulty().equals("Easy")&&column==4&&row==3)
					||(NewGameSetUp.getDifficulty().equals("Easy")&&column==4&&row==5)
					||(NewGameSetUp.getDifficulty().equals("Easy")&&column==5&&row==4)
					||(NewGameSetUp.getDifficulty().equals("Easy")&&column==3&&row==4)){
						kingdom2grid[column][row].setAccessible();
					}
					kingdom2.add(kingdom2grid[column][row]);
				}
			}
			bottomPanel.add(kingdom1);
			bottomPanel.add(kingdom2);

		}
		
		//Creating the kingdoms for a four player game
		if (NewGameSetUp.getPlayers().equals("Four Players")) {
			kingdom1 = new JPanel();
			kingdom1.setSize(200, 200);
			kingdom1.setLayout(new GridLayout(9,9));
			kingdom1grid = new GridSquare[x][y];
			for (int column = 0; column < x; column++) {
				for (int row = 0; row < y; row++) {
					// location= ""+column+row;
					kingdom1grid[column][row] = new GridSquare(column,row);
					kingdom1grid[column][row].setBorder(BorderFactory.createLineBorder(Color.yellow)); // Simple Line Border
					kingdom1grid[column][row].setOpaque(true); // without this line and the next the OS' default
					kingdom1grid[column][row].setBorderPainted(true); // look & feel will dominate / interfere
					kingdom1grid[column][row].addActionListener(this);

					if(NewGameSetUp.getDifficulty().equals("Easy")&&column==4&&row==4){
						Icon castle =new ImageIcon("images/castle1.jpg");
						kingdom1grid[column][row].setIcon(castle);

					}
					if(NewGameSetUp.getDifficulty().equals("Hard")&&column==2&&row==2){
						Icon castle =new ImageIcon("images/castle1.jpg");
						kingdom1grid[column][row].setIcon(castle);
			
					}
					if((NewGameSetUp.getDifficulty().equals("Easy")&&column==4&&row==3)
					||(NewGameSetUp.getDifficulty().equals("Easy")&&column==4&&row==5)
					||(NewGameSetUp.getDifficulty().equals("Easy")&&column==5&&row==4)
					||(NewGameSetUp.getDifficulty().equals("Easy")&&column==3&&row==4)){
						kingdom1grid[column][row].setAccessible();
					}
					kingdom1.add(kingdom1grid[column][row]);
				}
			}

			kingdom2 = new JPanel();
			kingdom2.setSize(200, 200);
			kingdom2.setLayout(new GridLayout(9,9));
			kingdom2grid = new GridSquare[x][y];
			for (int column = 0; column < x; column++) {
				for (int row = 0; row < y; row++) {
					// location= ""+column+row;
					kingdom2grid[column][row] = new GridSquare(column,row);
					kingdom2grid[column][row].setBorder(BorderFactory.createLineBorder(Color.red)); // Simple Line Border
					kingdom2grid[column][row].setOpaque(true); // without this line and the next the OS' default
					kingdom2grid[column][row].setBorderPainted(true); // look & feel will dominate / interfere
					kingdom2grid[column][row].addActionListener(this);

					if(NewGameSetUp.getDifficulty().equals("Easy")&&column==4&&row==4){
						Icon castle =new ImageIcon("images/castle1.jpg");
						kingdom2grid[column][row].setIcon(castle);

					}
					if(NewGameSetUp.getDifficulty().equals("Hard")&&column==2&&row==2){
						Icon castle =new ImageIcon("images/castle1.jpg");
						kingdom2grid[column][row].setIcon(castle);
			
					}
					if((NewGameSetUp.getDifficulty().equals("Easy")&&column==4&&row==3)
					||(NewGameSetUp.getDifficulty().equals("Easy")&&column==4&&row==5)
					||(NewGameSetUp.getDifficulty().equals("Easy")&&column==5&&row==4)
					||(NewGameSetUp.getDifficulty().equals("Easy")&&column==3&&row==4)){
						kingdom2grid[column][row].setAccessible();
					}
					kingdom2.add(kingdom2grid[column][row]);
				}
			}
			
			kingdom3 = new JPanel();
			kingdom3.setSize(200, 200);
			kingdom3.setLayout(new GridLayout(9,9));
			kingdom3grid = new GridSquare[x][y];
			for (int column = 0; column < x; column++) {
				for (int row = 0; row < y; row++) {
					// location= ""+column+row;
					kingdom3grid[column][row] = new GridSquare(column,row);
					kingdom3grid[column][row].setBorder(BorderFactory.createLineBorder(Color.blue)); // Simple Line Border
					kingdom3grid[column][row].setOpaque(true); // without this line and the next the OS' default
					kingdom3grid[column][row].setBorderPainted(true); // look & feel will dominate / interfere
					kingdom3grid[column][row].addActionListener(this);

					if(NewGameSetUp.getDifficulty().equals("Easy")&&column==4&&row==4){
						Icon castle =new ImageIcon("images/castle1.jpg");
						kingdom3grid[column][row].setIcon(castle);

					}
					if(NewGameSetUp.getDifficulty().equals("Hard")&&column==2&&row==2){
						Icon castle =new ImageIcon("images/castle1.jpg");
						kingdom3grid[column][row].setIcon(castle);
			
					}
					if((NewGameSetUp.getDifficulty().equals("Easy")&&column==4&&row==3)
					||(NewGameSetUp.getDifficulty().equals("Easy")&&column==4&&row==5)
					||(NewGameSetUp.getDifficulty().equals("Easy")&&column==5&&row==4)
					||(NewGameSetUp.getDifficulty().equals("Easy")&&column==3&&row==4)){
						kingdom3grid[column][row].setAccessible();
					}
					kingdom3.add(kingdom3grid[column][row]);
				}
			}

			kingdom4 = new JPanel();
			kingdom4.setSize(200, 200);
			kingdom4.setLayout(new GridLayout(9,9));
			kingdom4grid = new GridSquare[x][y];
			for (int column = 0; column < x; column++) {
				for (int row = 0; row < y; row++) {
					// location= ""+column+row;
					kingdom4grid[column][row] = new GridSquare(column,row);
					kingdom4grid[column][row].setBorder(BorderFactory.createLineBorder(Color.green)); // Simple Line Border
					kingdom4grid[column][row].setOpaque(true); // without this line and the next the OS' default
					kingdom4grid[column][row].setBorderPainted(true); // look & feel will dominate / interfere
					kingdom4grid[column][row].addActionListener(this);

					if(NewGameSetUp.getDifficulty().equals("Easy")&&column==4&&row==4){
						Icon castle =new ImageIcon("images/castle1.jpg");
						kingdom4grid[column][row].setIcon(castle);

					}
					if(NewGameSetUp.getDifficulty().equals("Hard")&&column==2&&row==2){
						Icon castle =new ImageIcon("images/castle1.jpg");
						kingdom4grid[column][row].setIcon(castle);
			
					}
					if((NewGameSetUp.getDifficulty().equals("Easy")&&column==4&&row==3)
					||(NewGameSetUp.getDifficulty().equals("Easy")&&column==4&&row==5)
					||(NewGameSetUp.getDifficulty().equals("Easy")&&column==5&&row==4)
					||(NewGameSetUp.getDifficulty().equals("Easy")&&column==3&&row==4)){
						kingdom4grid[column][row].setAccessible();
					}
					kingdom4.add(kingdom4grid[column][row]);
				}
			}
			bottomPanel.add(kingdom1);
			bottomPanel.add(kingdom2);
			bottomPanel.add(kingdom3);
			bottomPanel.add(kingdom4);

		}

		//this.add(westPanel,BorderLayout.WEST);

		this.add(bottomPanel, BorderLayout.CENTER);
		setVisible(true);
	}
	
	
	//Tried to implement a method to save the game
	/*public static void saveGame() {
		try {
			FileOutputStream fos = new FileOutputStream("Adv.sav");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(game);
			oos.flush();
			oos.close();
		}
		catch(Exception e) {
		
		}
	}*/

	public static void increaseGridSelectedTimes(){
        gridSelectedTimes++;
    }
	public static int getGridselectedTimes(){
		return gridSelectedTimes;
	}


	public static ArrayList<RectangleTile> getSelectedRectangleTiles(){
		return selectedRectangleTiles;
	}

	public static void addSelectedRectangleTiles(RectangleTile rT){
		selectedRectangleTiles.add(rT);
	}
	
	
	//Designating the winner of a four player game
	public static String getMax(int kingdom1Score, int kingdom2Score, int kingdom3Score, int kingdom4Score) {
		int max = kingdom1Score;
		String winner = "Player 1";
		
		if (kingdom2Score > max) {
			max = kingdom2Score;
			winner = "Player 2";
		}
		if (kingdom3Score > max) {
			max = kingdom3Score;
			winner = "Player 3";
		}
		if (kingdom4Score > max) {
			max = kingdom4Score;
			winner = "Player 4";
		}
		
		return winner;
	}

	@Override
	// action listener
	public void actionPerformed(ActionEvent e) {
		// get the object that was selected in the gui
		Object selected = e.getSource();

		if (selected instanceof JButton) {
			if ((selected).equals(newGam)) {
				NewGameSetUp newGameSet = new NewGameSetUp();
			}
			
			if ((selected).equals(saveGame)) {
				//saveGame();
			}

			//This button generates a new set of dominos
			if ((selected).equals(newDominos)) {
				midPanel.removeAll();
				toBeDisplayedTiles.remove(n);
				toBeDisplayedTiles.remove(n+1);
				toBeDisplayedTiles.remove(n+2);
				toBeDisplayedTiles.remove(n+3);				
				
				
				// If it is not the last turn of the game, 4 random dominos are selected from the tiles array
				// and are added to the toBeDisplayedTiles array.
				// After the dominos are randomly selected from the tiles array, they are deleted from the tiles
				// array (to avoid tiles being selected twice)
				if (tiles.size() > 4) {
					Random rand = new Random();
					int randomInteger = rand.nextInt(a);
					toBeDisplayedTiles.add(n+1, tiles.get(randomInteger));
					tiles.remove(randomInteger);
					a = a - 1;

					rand = new Random();
					randomInteger = rand.nextInt(a);
					toBeDisplayedTiles.add(n+3, tiles.get(randomInteger));
					tiles.remove(randomInteger);
					a = a - 1;

					rand = new Random();
					randomInteger = rand.nextInt(a);
					toBeDisplayedTiles.add(n+5, tiles.get(randomInteger));
					tiles.remove(randomInteger);
					a = a - 1;

					rand = new Random();
					randomInteger = rand.nextInt(a);
					toBeDisplayedTiles.add(n+7, tiles.get(randomInteger));
					tiles.remove(randomInteger);
					a = a - 1;
				}
				
				// If it is the last turn of the game, 4 invisible dominos are generated and added to the 
				// toBeDisplayedTiles array (to act as a placeholder for the right column)
				else { //when tiles.size() == 4
					temp1 = new RectangleTile(1);
					temp2 = new RectangleTile(1);
					temp3 = new RectangleTile(1);
					temp4 = new RectangleTile(1);
					
					//temp1.setOpaque(false);
					temp1.setContentAreaFilled(false);
					temp1.setBorderPainted(false);
					temp1.setIcon(null);

					
					//temp2.setOpaque(false);
					temp2.setContentAreaFilled(false);
					temp2.setBorderPainted(false);
					temp2.setIcon(null);
					
					//temp3.setOpaque(false);
					temp3.setContentAreaFilled(false);
					temp3.setBorderPainted(false);
					temp3.setIcon(null);
					
					//temp4.setOpaque(false);
					temp4.setContentAreaFilled(true);
					temp4.setBorderPainted(false);
					temp4.setIcon(null);
					
					toBeDisplayedTiles.add(n+1, temp1);
					toBeDisplayedTiles.add(n+3, temp2);
					toBeDisplayedTiles.add(n+5, temp3);
					toBeDisplayedTiles.add(n+7, temp4);
				}
				
				for (RectangleTile tilee : toBeDisplayedTiles) {
					midPanel.add(tilee);
					tilee.addActionListener(this);
				}
				
				playerlabel.setText(toBeDisplayedTiles.get(n).getText() + " move your tile to the kingdom.");
				
				
				midPanel.repaint();
				
			}
		}
		
		if ((selected).equals(calculateScore)) {
				//Iterate through kingdom 1 and take note of crown factor and number of each terrain
				if (NewGameSetUp.getPlayers().equals("Two Players")) {
				
					//Calculating the score of kingdom 1
					for (int column = 0; column < x; column++) {
						for (int row = 0; row < y; row++) {
							if (kingdom1grid[column][row].getName() == "wheatFields") {
								kingdom1wheatFields += 1;
								kingdom1wheatCrowns += kingdom1grid[column][row].getCrownFactor();
							}
							
							else if (kingdom1grid[column][row].getName() == "forests") {
								kingdom1forest += 1;
								kingdom1forestCrowns += kingdom1grid[column][row].getCrownFactor();
							}
							
							else if (kingdom1grid[column][row].getName() == "grassLands") {
								kingdom1grassLands += 1;
								kingdom1grassCrowns += kingdom1grid[column][row].getCrownFactor();
							}
							
							else if (kingdom1grid[column][row].getName() == "lakes") {
								kingdom1lakes += 1;
								kingdom1lakesCrowns += kingdom1grid[column][row].getCrownFactor();
							}
							
							else if (kingdom1grid[column][row].getName() == "mines") {
								kingdom1mines += 1;
								kingdom1minesCrowns += kingdom1grid[column][row].getCrownFactor();
							}
							
							else if (kingdom1grid[column][row].getName() == "swamp") {
								kingdom1swamp += 1;
								kingdom1swampCrowns += kingdom1grid[column][row].getCrownFactor();
							}
						}
					}
					
					kingdom1Score = (kingdom1wheatFields*kingdom1wheatCrowns) + (kingdom1forest*kingdom1forestCrowns) + (kingdom1grassLands*kingdom1grassCrowns) 
						+ (kingdom1lakes*kingdom1lakesCrowns) + (kingdom1mines*kingdom1minesCrowns) + (kingdom1swamp*kingdom1swampCrowns);
					System.out.println("kingdom1 score = " + kingdom1Score);
					
					
					//Calculating the score of kingdom 2
					for (int column = 0; column < x; column++) {
						for (int row = 0; row < y; row++) {
							if (kingdom2grid[column][row].getName() == "wheatFields") {
								kingdom2wheatFields += 1;
								kingdom2wheatCrowns += kingdom2grid[column][row].getCrownFactor();
							}
							
							else if (kingdom2grid[column][row].getName() == "forests") {
								kingdom2forest += 1;
								kingdom2forestCrowns += kingdom2grid[column][row].getCrownFactor();
							}
							
							else if (kingdom2grid[column][row].getName() == "grassLands") {
								kingdom2grassLands += 1;
								kingdom2grassCrowns += kingdom2grid[column][row].getCrownFactor();
							}
							
							else if (kingdom2grid[column][row].getName() == "lakes") {
								kingdom2lakes += 1;
								kingdom2lakesCrowns += kingdom2grid[column][row].getCrownFactor();
							}
							
							else if (kingdom2grid[column][row].getName() == "mines") {
								kingdom2mines += 1;
								kingdom2minesCrowns += kingdom2grid[column][row].getCrownFactor();
							}
							
							else if (kingdom2grid[column][row].getName() == "swamp") {
								kingdom2swamp += 1;
								kingdom2swampCrowns += kingdom2grid[column][row].getCrownFactor();
							}
						}
					}
					
					kingdom2Score = (kingdom2wheatFields*kingdom2wheatCrowns) + (kingdom2forest*kingdom2forestCrowns) + (kingdom2grassLands*kingdom2grassCrowns) 
						+ (kingdom2lakes*kingdom2lakesCrowns) + (kingdom2mines*kingdom2minesCrowns) + (kingdom2swamp*kingdom2swampCrowns);
					System.out.println("kingdom2 score = " + kingdom2Score);
				}
				
				if (NewGameSetUp.getPlayers().equals("Four Players")) {
					//Calculating the score of kingdom 1
					for (int column = 0; column < x; column++) {
						for (int row = 0; row < y; row++) {
							if (kingdom1grid[column][row].getName() == "wheatFields") {
								kingdom1wheatFields += 1;
								kingdom1wheatCrowns += kingdom1grid[column][row].getCrownFactor();
							}
							
							else if (kingdom1grid[column][row].getName() == "forests") {
								kingdom1forest += 1;
								kingdom1forestCrowns += kingdom1grid[column][row].getCrownFactor();
							}
							
							else if (kingdom1grid[column][row].getName() == "grassLands") {
								kingdom1grassLands += 1;
								kingdom1grassCrowns += kingdom1grid[column][row].getCrownFactor();
							}
							
							else if (kingdom1grid[column][row].getName() == "lakes") {
								kingdom1lakes += 1;
								kingdom1lakesCrowns += kingdom1grid[column][row].getCrownFactor();
							}
							
							else if (kingdom1grid[column][row].getName() == "mines") {
								kingdom1mines += 1;
								kingdom1minesCrowns += kingdom1grid[column][row].getCrownFactor();
							}
							
							else if (kingdom1grid[column][row].getName() == "swamp") {
								kingdom1swamp += 1;
								kingdom1swampCrowns += kingdom1grid[column][row].getCrownFactor();
							}
						}
					}
					
					kingdom1Score = (kingdom1wheatFields*kingdom1wheatCrowns) + (kingdom1forest*kingdom1forestCrowns) + (kingdom1grassLands*kingdom1grassCrowns) 
						+ (kingdom1lakes*kingdom1lakesCrowns) + (kingdom1mines*kingdom1minesCrowns) + (kingdom1swamp*kingdom1swampCrowns);
					System.out.println("kingdom1 score = " + kingdom1Score);
					
					
					//Calculating the score of kingdom 2
					for (int column = 0; column < x; column++) {
						for (int row = 0; row < y; row++) {
							if (kingdom2grid[column][row].getName() == "wheatFields") {
								kingdom2wheatFields += 1;
								kingdom2wheatCrowns += kingdom2grid[column][row].getCrownFactor();
							}
							
							else if (kingdom2grid[column][row].getName() == "forests") {
								kingdom2forest += 1;
								kingdom2forestCrowns += kingdom2grid[column][row].getCrownFactor();
							}
							
							else if (kingdom2grid[column][row].getName() == "grassLands") {
								kingdom2grassLands += 1;
								kingdom2grassCrowns += kingdom2grid[column][row].getCrownFactor();
							}
							
							else if (kingdom2grid[column][row].getName() == "lakes") {
								kingdom2lakes += 1;
								kingdom2lakesCrowns += kingdom2grid[column][row].getCrownFactor();
							}
							
							else if (kingdom2grid[column][row].getName() == "mines") {
								kingdom2mines += 1;
								kingdom2minesCrowns += kingdom2grid[column][row].getCrownFactor();
							}
							
							else if (kingdom2grid[column][row].getName() == "swamp") {
								kingdom2swamp += 1;
								kingdom2swampCrowns += kingdom2grid[column][row].getCrownFactor();
							}
						}
					}
					
					kingdom2Score = (kingdom2wheatFields*kingdom2wheatCrowns) + (kingdom2forest*kingdom2forestCrowns) + (kingdom2grassLands*kingdom2grassCrowns) 
						+ (kingdom2lakes*kingdom2lakesCrowns) + (kingdom2mines*kingdom2minesCrowns) + (kingdom2swamp*kingdom2swampCrowns);
					System.out.println("kingdom2 score = " + kingdom2Score);
					
					//Calculating the score of kingdom 3
					for (int column = 0; column < x; column++) {
						for (int row = 0; row < y; row++) {
							if (kingdom3grid[column][row].getName() == "wheatFields") {
								kingdom3wheatFields += 1;
								kingdom3wheatCrowns += kingdom3grid[column][row].getCrownFactor();
							}
							
							else if (kingdom3grid[column][row].getName() == "forests") {
								kingdom3forest += 1;
								kingdom3forestCrowns += kingdom3grid[column][row].getCrownFactor();
							}
							
							else if (kingdom3grid[column][row].getName() == "grassLands") {
								kingdom3grassLands += 1;
								kingdom3grassCrowns += kingdom1grid[column][row].getCrownFactor();
							}
							
							else if (kingdom3grid[column][row].getName() == "lakes") {
								kingdom3lakes += 1;
								kingdom3lakesCrowns += kingdom3grid[column][row].getCrownFactor();
							}
							
							else if (kingdom3grid[column][row].getName() == "mines") {
								kingdom3mines += 1;
								kingdom3minesCrowns += kingdom3grid[column][row].getCrownFactor();
							}
							
							else if (kingdom3grid[column][row].getName() == "swamp") {
								kingdom3swamp += 1;
								kingdom3swampCrowns += kingdom1grid[column][row].getCrownFactor();
							}
						}
					}
					
					kingdom3Score = (kingdom3wheatFields*kingdom3wheatCrowns) + (kingdom3forest*kingdom3forestCrowns) + (kingdom3grassLands*kingdom3grassCrowns) 
						+ (kingdom3lakes*kingdom3lakesCrowns) + (kingdom3mines*kingdom3minesCrowns) + (kingdom3swamp*kingdom3swampCrowns);
					System.out.println("kingdom3 score = " + kingdom3Score);
					
					//Calculating the score of kingdom 4
					for (int column = 0; column < x; column++) {
						for (int row = 0; row < y; row++) {
							if (kingdom4grid[column][row].getName() == "wheatFields") {
								kingdom4wheatFields += 1;
								kingdom4wheatCrowns += kingdom4grid[column][row].getCrownFactor();
							}
							
							else if (kingdom4grid[column][row].getName() == "forests") {
								kingdom4forest += 1;
								kingdom4forestCrowns += kingdom4grid[column][row].getCrownFactor();
							}
							
							else if (kingdom4grid[column][row].getName() == "grassLands") {
								kingdom4grassLands += 1;
								kingdom4grassCrowns += kingdom4grid[column][row].getCrownFactor();
							}
							
							else if (kingdom4grid[column][row].getName() == "lakes") {
								kingdom4lakes += 1;
								kingdom4lakesCrowns += kingdom4grid[column][row].getCrownFactor();
							}
							
							else if (kingdom4grid[column][row].getName() == "mines") {
								kingdom4mines += 1;
								kingdom4minesCrowns += kingdom4grid[column][row].getCrownFactor();
							}
							
							else if (kingdom4grid[column][row].getName() == "swamp") {
								kingdom4swamp += 1;
								kingdom4swampCrowns += kingdom4grid[column][row].getCrownFactor();
							}
						}
					}
					
					kingdom4Score = (kingdom4wheatFields*kingdom4wheatCrowns) + (kingdom4forest*kingdom4forestCrowns) + (kingdom4grassLands*kingdom4grassCrowns) 
						+ (kingdom4lakes*kingdom4lakesCrowns) + (kingdom4mines*kingdom4minesCrowns) + (kingdom4swamp*kingdom4swampCrowns);
					System.out.println("kingdom4 score = " + kingdom4Score);
				}
				
				// If the CalculateScore button is pressed and it is the last turn of the game (if the toBeDisplayed array contains the invisible dominos,
				// the winner is displayed.
				if (toBeDisplayedTiles.contains((RectangleTile) temp1)) {
					midPanel.removeAll();
					if (NewGameSetUp.getPlayers().equals("Two Players")) {
						if (kingdom1Score > kingdom2Score) {
							playerlabel.setText("Winner: Player 1!");
						}
						else if (kingdom1Score < kingdom2Score) {
							playerlabel.setText("Winner: Player 2!");
						}
						else {
							playerlabel.setText("It's a tie!");
						}
					}
					
					if (NewGameSetUp.getPlayers().equals("Four Players")) {
						String winner = getMax(kingdom1Score,kingdom2Score,kingdom3Score,kingdom4Score);
						if (winner.equals("Player 1")) {
							playerlabel.setText("Winner: Player 1!");
						}
						else if (winner.equals("Player 2")) {
							playerlabel.setText("Winner: Player 2!");
						}
						else if (winner.equals("Player 3")) {
							playerlabel.setText("Winner: Player 3!");
						}
						else if (winner.equals("Player 4")) {
							playerlabel.setText("Winner: Player 4!");
						}
					}
				}
		}
		
		if (selected instanceof GridSquare) {
			if(((GridSquare) selected).isaccessibleToPlayer()){
				if (getSelectedRectangleTiles().get((getSelectedRectangleTiles().size())-1).getSelectedTimes() == 2) {
					
					//When a domino is placed in the kingdom, the neighbouring gridSquares are set accesible
					if (NewGameSetUp.getPlayers().equals("Two Players")){
						double leftSquareId=(double) TakeTurn.getSelectedRectangleTiles().get((TakeTurn.getSelectedRectangleTiles().size())-1).getNumber();
						double rightSquareId=(double)TakeTurn.getSelectedRectangleTiles().get((TakeTurn.getSelectedRectangleTiles().size())-1).getNumber()+0.1;
						
						if(playerlabel.getText().equals("player 1 move your tile to the kingdom.")){
							int xcoord = ((GridSquare) selected).getXCoord();
							int ycoord = ((GridSquare) selected).getYCoord();
							kingdom1grid[xcoord+1][ycoord].setAccessible();
							kingdom1grid[xcoord-1][ycoord].setAccessible();
							kingdom1grid[xcoord][ycoord+1].setAccessible();
							kingdom1grid[xcoord][ycoord-1].setAccessible();
						}
						if(playerlabel.getText().equals("player 2 move your tile to the kingdom.")){
							int xcoord = ((GridSquare) selected).getXCoord();
							int ycoord = ((GridSquare) selected).getYCoord();
							kingdom2grid[xcoord+1][ycoord].setAccessible();
							kingdom2grid[xcoord-1][ycoord].setAccessible();
							kingdom2grid[xcoord][ycoord+1].setAccessible();
							kingdom2grid[xcoord][ycoord-1].setAccessible();
						}
						
						if(getGridselectedTimes()%2 == 0){
							(((GridSquare) selected)).setName(leftSquareId);
							(((GridSquare) selected)).setColor();
							(((GridSquare) selected)).setCrowns(leftSquareId);
						}
						if(getGridselectedTimes()%2 != 0){
							(((GridSquare) selected)).setName(rightSquareId);
							(((GridSquare) selected)).setColor();
							(((GridSquare) selected)).setCrowns(rightSquareId);
						}
						
						
						// If it is the last turn of the game, the player doesn't select any more dominos, the next player places their domino in 
						// the kingdom. After the last player places their domino in the kingdom, it prompts them to click the Calculate Score button
						// to see the winner of the game.
						if (toBeDisplayedTiles.contains((RectangleTile) temp1)) {							
							int var1 = getGridselectedTimes();
							if (var1%8 == 1) {
								playerlabel.setText(toBeDisplayedTiles.get(n+2).getText() + " move your tile to the kingdom.");
							}
							if (var1%8 == 3) {
								playerlabel.setText(toBeDisplayedTiles.get(n+4).getText() + " move your tile to the kingdom.");
							}
							if (var1%8 == 5) {
								playerlabel.setText(toBeDisplayedTiles.get(n+6).getText() + " move your tile to the kingdom.");
							}
							if (var1%8 == 7) {
								playerlabel.setText("Please click Calculate Score button to see the winner!");
							}	
							
						}
						
						//If it is not the last turn of the game, after a domino is placed in the kingdom, the player selects their next domino
						else {
							int var = getGridselectedTimes();
							if (var%8 == 1 && var != 0) {
								playerlabel.setText(toBeDisplayedTiles.get(n).getText() + ", choose your next domino");
							}
							if (var%8 == 3 && var != 0) {
								playerlabel.setText(toBeDisplayedTiles.get(n+2).getText() + ", choose your next domino");
							}
							if (var%8 == 5 && var != 0) {
								playerlabel.setText(toBeDisplayedTiles.get(n+4).getText() + ", choose your next domino");
							}
							if (var%8 == 7 && var != 0) {
								playerlabel.setText(toBeDisplayedTiles.get(n+6).getText() + ", choose your next domino");
							}
						}
					}

					//When a domino is placed in the kingdom, the neighbouring gridSquares are set accesible					
					if (NewGameSetUp.getPlayers().equals("Four Players")){
						double leftSquareId=(double) TakeTurn.getSelectedRectangleTiles().get((TakeTurn.getSelectedRectangleTiles().size())-1).getNumber();
						double rightSquareId=(double)TakeTurn.getSelectedRectangleTiles().get((TakeTurn.getSelectedRectangleTiles().size())-1).getNumber()+0.1;

						
						if(playerlabel.getText().equals("player 1 move your tile to the kingdom.")){
							int xcoord = ((GridSquare) selected).getXCoord();
							int ycoord = ((GridSquare) selected).getYCoord();
							kingdom1grid[xcoord+1][ycoord].setAccessible();
							kingdom1grid[xcoord-1][ycoord].setAccessible();
							kingdom1grid[xcoord][ycoord+1].setAccessible();
							kingdom1grid[xcoord][ycoord-1].setAccessible();
						}
						if(playerlabel.getText().equals("player 2 move your tile to the kingdom.")){
							int xcoord = ((GridSquare) selected).getXCoord();
							int ycoord = ((GridSquare) selected).getYCoord();
							kingdom2grid[xcoord+1][ycoord].setAccessible();
							kingdom2grid[xcoord-1][ycoord].setAccessible();
							kingdom2grid[xcoord][ycoord+1].setAccessible();
							kingdom2grid[xcoord][ycoord-1].setAccessible();
						}
						if(playerlabel.getText().equals("player 3 move your tile to the kingdom.")){
							int xcoord = ((GridSquare) selected).getXCoord();
							int ycoord = ((GridSquare) selected).getYCoord();
							kingdom3grid[xcoord+1][ycoord].setAccessible();
							kingdom3grid[xcoord-1][ycoord].setAccessible();
							kingdom3grid[xcoord][ycoord+1].setAccessible();
							kingdom3grid[xcoord][ycoord-1].setAccessible();
						}
						if(playerlabel.getText().equals("player 4 move your tile to the kingdom.")){
							int xcoord = ((GridSquare) selected).getXCoord();
							int ycoord = ((GridSquare) selected).getYCoord();
							kingdom4grid[xcoord+1][ycoord].setAccessible();
							kingdom4grid[xcoord-1][ycoord].setAccessible();
							kingdom4grid[xcoord][ycoord+1].setAccessible();
							kingdom4grid[xcoord][ycoord-1].setAccessible();
						}
						
						if(getGridselectedTimes()%2 == 0){
							(((GridSquare) selected)).setName(leftSquareId);
							(((GridSquare) selected)).setColor();
							(((GridSquare) selected)).setCrowns(leftSquareId);
						}
						if(getGridselectedTimes()%2 != 0){
							(((GridSquare) selected)).setName(rightSquareId);
							(((GridSquare) selected)).setColor();
							(((GridSquare) selected)).setCrowns(rightSquareId);
						}
				
				
						// If it is the last turn of the game, the player doesn't select any more dominos, the next player places their domino in 
						// the kingdom. After the last player places their domino in the kingdom, it prompts them to click the Calculate Score button
						// to see the winner of the game.
						if (toBeDisplayedTiles.contains((RectangleTile) temp1)) {							
							int var1 = getGridselectedTimes();
							if (var1%8 == 1) {
								playerlabel.setText(toBeDisplayedTiles.get(n+2).getText() + " move your tile to the kingdom.");
							}
							if (var1%8 == 3) {
								playerlabel.setText(toBeDisplayedTiles.get(n+4).getText() + " move your tile to the kingdom.");
							}
							if (var1%8 == 5) {
								playerlabel.setText(toBeDisplayedTiles.get(n+6).getText() + " move your tile to the kingdom.");
							}
							if (var1%8 == 7) {
								playerlabel.setText("Please click Calculate Score button to see the winner!");
							}	
							
						}
						
						//If it is not the last turn of the game, after a domino is placed in the kingdom, the player selects their next domino				
						else {
							int var = getGridselectedTimes();
							if (var%8 == 1 && var != 0) {
								playerlabel.setText(toBeDisplayedTiles.get(n).getText() + ", choose your next domino");
							}
							if (var%8 == 3 && var != 0) {
								playerlabel.setText(toBeDisplayedTiles.get(n+2).getText() + ", choose your next domino");
							}
							if (var%8 == 5 && var != 0) {
								playerlabel.setText(toBeDisplayedTiles.get(n+4).getText() + ", choose your next domino");
							}
							if (var%8 == 7 && var != 0) {
								playerlabel.setText(toBeDisplayedTiles.get(n+6).getText() + ", choose your next domino");
							}
						}
					}
				}
			}
		}
		
		if (selected instanceof RectangleTile) {
			Icon kingMeeple = new ImageIcon("images/Crown2.png");
			if (NewGameSetUp.getPlayers().equals("Two Players")) {
				
				//The first time a domino is selected, a player claims it as theirs
				if ((((RectangleTile) selected).getSelectedTimes() % 3) == 0) {
					if (playerlabel.getText().equals("Player1's Turn")){
						((RectangleTile) selected).setText("player 1");
						playerlabel.setText("Player2's Turn");
					}
					else if (playerlabel.getText().equals("Player2's Turn")) {
						count += 1;
						if (count == 1){
							((RectangleTile) selected).setText("player 2");
							playerlabel.setText("Player1's Turn");
						}
						else if(count == 2){
							((RectangleTile) selected).setText("player 2");
							playerlabel.setText("" + toBeDisplayedTiles.get(n).getText() + " move your tile to the kingdom.");
						}
					}					
				}
				
				//The second time a domino is selected, it places the king meeple and prompts the player to move it to the kingdom
				if ((((RectangleTile) selected).getSelectedTimes() % 3) == 1) {
					if (playerlabel.getText()
							.equals(((RectangleTile) selected).getText() + " move your tile to the kingdom.")) {
						
						if (((RectangleTile) selected).getText().equals("player 1")){
							((RectangleTile) selected).setBackground(Color.yellow);
							((RectangleTile) selected).setRolloverIcon(kingMeeple);
						}
						if (((RectangleTile) selected).getText().equals("player 2")){
							((RectangleTile) selected).setBackground(Color.blue);
							((RectangleTile) selected).setRolloverIcon(kingMeeple);
						}
					}
					
				}
				
				//The third time a domino is selected, the players turn ends and the next players turn begins
				if ((((RectangleTile) selected).getSelectedTimes() % 3 ) == 2) {
					if (playerlabel.getText()
							.equals(((RectangleTile) selected).getText() + " move your tile to the kingdom.")) {
								
						if((selected).equals(toBeDisplayedTiles.get(n))){
							playerlabel.setText("" + toBeDisplayedTiles.get(n+2).getText() + " move your tile to the kingdom.");
							((RectangleTile) selected).setText("");
							toBeDisplayedTiles.get(n).setContentAreaFilled(false);
							toBeDisplayedTiles.get(n).setBorderPainted(false);
							toBeDisplayedTiles.get(n).setIcon(null);
						}
						if((selected).equals(toBeDisplayedTiles.get(n+2))){
							playerlabel.setText("" + toBeDisplayedTiles.get(n+4).getText() + " move your tile to the kingdom.");
							((RectangleTile) selected).setText("");
							toBeDisplayedTiles.get(n+2).setContentAreaFilled(false);
							toBeDisplayedTiles.get(n+2).setBorderPainted(false);
							toBeDisplayedTiles.get(n+2).setIcon(null); 
						}
						if((selected).equals(toBeDisplayedTiles.get(n+4))){
							playerlabel.setText("" + toBeDisplayedTiles.get(n+6).getText() + " move your tile to the kingdom.");
							((RectangleTile) selected).setText("");
							toBeDisplayedTiles.get(n+4).setContentAreaFilled(false);
							toBeDisplayedTiles.get(n+4).setBorderPainted(false);
							toBeDisplayedTiles.get(n+4).setIcon(null);
						}
						if((selected).equals(toBeDisplayedTiles.get(n+6))){
							playerlabel.setText("Please press display new domino button");
							((RectangleTile) selected).setText("");
							toBeDisplayedTiles.get(n+6).setContentAreaFilled(false);
							toBeDisplayedTiles.get(n+6).setBorderPainted(false);
							toBeDisplayedTiles.get(n+6).setIcon(null);
						}
						
					}	
				}
				
				if ((toBeDisplayedTiles.indexOf(selected) % 2) != 0){
					if (playerlabel.getText().equals((toBeDisplayedTiles.get(n).getText() + ", choose your next domino"))) {
						((RectangleTile) selected).setText(toBeDisplayedTiles.get(n).getText());
						playerlabel.setText("" + toBeDisplayedTiles.get(n).getText() + " move your tile to the kingdom.");
					}
					if (playerlabel.getText().equals((toBeDisplayedTiles.get(n+2).getText() + ", choose your next domino"))) {
						((RectangleTile) selected).setText(toBeDisplayedTiles.get(n+2).getText());
						playerlabel.setText("" + toBeDisplayedTiles.get(n+2).getText() + " move your tile to the kingdom.");
					}
					if (playerlabel.getText().equals((toBeDisplayedTiles.get(n+4).getText() + ", choose your next domino"))) {
						((RectangleTile) selected).setText(toBeDisplayedTiles.get(n+4).getText());
						playerlabel.setText("" + toBeDisplayedTiles.get(n+4).getText() + " move your tile to the kingdom.");
					}
					if (playerlabel.getText().equals((toBeDisplayedTiles.get(n+6).getText() + ", choose your next domino"))) {
						((RectangleTile) selected).setText(toBeDisplayedTiles.get(n+6).getText());
						playerlabel.setText("" + toBeDisplayedTiles.get(n+6).getText() + " move your tile to the kingdom.");
					}
					
						
				}
			}
	
			if (NewGameSetUp.getPlayers().equals("Four Players")) {
				
				//The first time a domino is selected, a player claims it as theirs
				if (((RectangleTile) selected).getSelectedTimes() == 0) {
					if (playerlabel.getText().equals("Player1's Turn")) {

						((RectangleTile) selected).setText("player 1");
						playerlabel.setText("Player2's Turn");
					}
					else if (playerlabel.getText().equals("Player2's Turn")) {

						((RectangleTile) selected).setText("player 2");
						playerlabel.setText("Player3's Turn");
					}
					else if (playerlabel.getText().equals("Player3's Turn")) {

						((RectangleTile) selected).setText("player 3");
						playerlabel.setText("Player4's Turn");
					}
					else if (playerlabel.getText().equals("Player4's Turn")) {

						((RectangleTile) selected).setText("player 4");

						playerlabel.setText("" + toBeDisplayedTiles.get(n).getText() + " move your tile to the kingdom.");
					}
				}

				//The second time a domino is selected, it places the king meeple and prompts the player to move it to the kingdom
				if ((((RectangleTile) selected).getSelectedTimes() % 3) == 1) {
					if (playerlabel.getText()
							.equals(((RectangleTile) selected).getText() + " move your tile to the kingdom.")) {
						//if statement makes it so we can change the colours later if we want
						if (((RectangleTile) selected).getText().equals("player 1")){
							((RectangleTile) selected).setBackground(Color.yellow);
							((RectangleTile) selected).setRolloverIcon(kingMeeple);
						}
						if (((RectangleTile) selected).getText().equals("player 2")){
							((RectangleTile) selected).setBackground(Color.red);
							((RectangleTile) selected).setRolloverIcon(kingMeeple);
						}
						if (((RectangleTile) selected).getText().equals("player 3")){
							((RectangleTile) selected).setBackground(Color.blue);
							((RectangleTile) selected).setRolloverIcon(kingMeeple);
						}
						if (((RectangleTile) selected).getText().equals("player 4")){
							((RectangleTile) selected).setBackground(Color.green);
							((RectangleTile) selected).setRolloverIcon(kingMeeple);
						}
						
					}
					
				}
				
				//The third time a domino is selected, the players turn ends and the next players turn begins
				if ((((RectangleTile) selected).getSelectedTimes() % 3) == 2) {
					if (playerlabel.getText()
							.equals(((RectangleTile) selected).getText() + " move your tile to the kingdom.")) {
								
						if((selected).equals(toBeDisplayedTiles.get(n))){
							playerlabel.setText("" + toBeDisplayedTiles.get(n+2).getText() + " move your tile to the kingdom.");
							
							((RectangleTile) selected).setText("");
							toBeDisplayedTiles.get(n).setOpaque(false);
							toBeDisplayedTiles.get(n).setContentAreaFilled(false);
							toBeDisplayedTiles.get(n).setBorderPainted(false);
							toBeDisplayedTiles.get(n).setIcon(null);
							
						}
						if((selected).equals(toBeDisplayedTiles.get(n+2))){
							playerlabel.setText("" + toBeDisplayedTiles.get(n+4).getText() + " move your tile to the kingdom."); 
							((RectangleTile) selected).setText("");
							toBeDisplayedTiles.get(n+2).setOpaque(false);
							toBeDisplayedTiles.get(n+2).setContentAreaFilled(false);
							toBeDisplayedTiles.get(n+2).setBorderPainted(false);
							toBeDisplayedTiles.get(n+2).setIcon(null);
						}
						if((selected).equals(toBeDisplayedTiles.get(n+4))){
							playerlabel.setText("" + toBeDisplayedTiles.get(n+6).getText() + " move your tile to the kingdom.");
							((RectangleTile) selected).setText("");
							toBeDisplayedTiles.get(n+4).setOpaque(false);
							toBeDisplayedTiles.get(n+4).setContentAreaFilled(false);
							toBeDisplayedTiles.get(n+4).setBorderPainted(false);
							toBeDisplayedTiles.get(n+4).setIcon(null);
						}
						if((selected).equals(toBeDisplayedTiles.get(n+6))){
							playerlabel.setText("Please press display new domino button");
							((RectangleTile) selected).setText("");
							toBeDisplayedTiles.get(n+6).setOpaque(false);
							toBeDisplayedTiles.get(n+6).setContentAreaFilled(false);
							toBeDisplayedTiles.get(n+6).setBorderPainted(false);
							toBeDisplayedTiles.get(n+6).setIcon(null);
						}
						
					}
				}
				
				if ((toBeDisplayedTiles.indexOf(selected) % 2) != 0){
					if (playerlabel.getText().equals((toBeDisplayedTiles.get(n).getText() + ", choose your next domino"))) {
						((RectangleTile) selected).setText(toBeDisplayedTiles.get(n).getText());
						playerlabel.setText("" + toBeDisplayedTiles.get(n).getText() + " move your tile to the kingdom.");
					}
					if (playerlabel.getText().equals((toBeDisplayedTiles.get(n+2).getText() + ", choose your next domino"))) {
						((RectangleTile) selected).setText(toBeDisplayedTiles.get(n+2).getText());
						playerlabel.setText("" + toBeDisplayedTiles.get(n+2).getText() + " move your tile to the kingdom.");
					}
					if (playerlabel.getText().equals((toBeDisplayedTiles.get(n+4).getText() + ", choose your next domino"))) {
						((RectangleTile) selected).setText(toBeDisplayedTiles.get(n+4).getText());
						playerlabel.setText("" + toBeDisplayedTiles.get(n+4).getText() + " move your tile to the kingdom.");
					}
					if (playerlabel.getText().equals((toBeDisplayedTiles.get(n+6).getText() + ", choose your next domino"))) {
						((RectangleTile) selected).setText(toBeDisplayedTiles.get(n+6).getText());
						playerlabel.setText("" + toBeDisplayedTiles.get(n+6).getText() + " move your tile to the kingdom.");
					}
				}
			}

		}
		

	}

}
