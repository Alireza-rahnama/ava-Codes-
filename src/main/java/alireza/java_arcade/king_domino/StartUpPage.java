package alireza.java_arcade.king_domino;

/**
 * This class generates the startup page
 * @author ARahnama
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class StartUpPage extends JFrame implements ActionListener{


	JPanel panel;
	JButton setUpButton;
	JButton continueButton;
	static NewGameSetUp gameSetUp;
	JLabel KingdominoLabel;
	
	public StartUpPage() {
		super();
		this.setSize(600,600);
		this.setTitle("King Domino");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel=  new JPanel();
		KingdominoLabel= new JLabel(new ImageIcon("cards/kingdomino.jpg"));
		panel.setLayout(new FlowLayout());
		panel.setSize(500, 500);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panel, BorderLayout.NORTH);
		getContentPane().add(KingdominoLabel, BorderLayout.CENTER);
		setUpButton = new JButton("New Game");
		setUpButton.setSize(100, 100);
		setUpButton.addActionListener(this);
		panel.add(setUpButton);
		continueButton= new JButton("Continue");
		continueButton.setSize(100, 100);
		continueButton.addActionListener(this);
		panel.add(continueButton);
		
		setResizable(false);
		setVisible(true);	
		
		
	}

	public void actionPerformed (ActionEvent aevt)
	{
		// get the object that was selected in the gui
		Object selected = aevt.getSource();

		if ( selected instanceof JButton)
		{
			if((selected).equals(setUpButton)){
				gameSetUp= new NewGameSetUp();
				}
				

			if((selected).equals(continueButton)){
				//loadGame();
				
				}
			
		}
	}

			
     
	
}



