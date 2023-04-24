package alireza.java_arcade.king_domino;

import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @aRahnama
 * this class creates a gui for the game setups
 * I was aiming to add dropdown menues to the gui but instead i added pop up question dialogues, 
 * it is better if we could add drop down menues tpo the jFrame instead of this method i used!!!!!!!!!
 *
 */
public class NewGameSetUp extends JFrame implements ActionListener{

	/**
	 * 
	 */
	JPanel topPanel;
	JPanel botPanel;
	JButton saveSettings;
	static JComboBox<String> palette;
	static JComboBox<String> difficulty;
	static JComboBox<String> setUpPlayers;
	public String paletteSelected;
	String difficultySelected;
	String playersSelected;
	TakeTurn newWindow;

	
	public NewGameSetUp(){
		super();
		this.setSize(600,600);
		this.setTitle("KingDomino-New Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		JPanel northPanel= new JPanel();
		northPanel.setSize(500, 100);
		JLabel tittle= new JLabel("Select your color and players settings");
		northPanel.add(tittle);
		this.add(northPanel, BorderLayout.NORTH);
		JPanel centerPanel= new JPanel();
		centerPanel.setSize(450,450);
		this.add(centerPanel, BorderLayout.CENTER);
		JPanel southPanel= new JPanel();
		this.add(southPanel,BorderLayout.SOUTH);
		
		
		//selecting number of players
		String[] optionsToChoose = {"Two Players", "Four Players"};
		setUpPlayers = new JComboBox<>(optionsToChoose);
		centerPanel.add(setUpPlayers);
//	    
		
//	    //selecting difficulty mode
	    String[] difficultyToChoose = {"Easy", "Hard"};
	    difficulty = new JComboBox<>(difficultyToChoose);
	    centerPanel.add(difficulty);	    

	    
//	    //color palette selection
	    String[] colorsToChoose = {"Color Palette1", "Color Palette2"};
	    palette = new JComboBox<>(colorsToChoose);
	    centerPanel.add(palette);

		JLabel InstructionLabel= new JLabel();
		ImageIcon imgIc= new ImageIcon("cards/Capture.png");
		InstructionLabel.setIcon(imgIc);
		
		
		centerPanel.add(InstructionLabel);

	    saveSettings= new JButton("Save Settings");
	    saveSettings.addActionListener(this);
	    southPanel.add(saveSettings);
	    
	    
		setResizable(false);
		setVisible(true);
		
	}
	
	public static String getPlayers() {
		String x= setUpPlayers.getSelectedItem().toString();
		return x;
	}
	
	public static  String getPalette() {
		String y= palette.getSelectedItem().toString();
		return y;
	}
	
	public static String getDifficulty() {
		String z= difficulty.getSelectedItem().toString();
		return z;
	}
	
	public  TakeTurn getTakeTurnInstance(){
		return newWindow;
	}
	public void actionPerformed (ActionEvent aevt)
	{
		// get the object that was selected in the gui
		Object selected = aevt.getSource();
		
		
		
		if ( selected instanceof JButton)
		{
			if(selected.equals(saveSettings)){
				TakeTurn newWindow=new TakeTurn();
				
			}
			
			
		}
	}



}
