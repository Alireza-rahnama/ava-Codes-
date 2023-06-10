
/**
 * This class generates the startup page
 * @author ARahnama
 *
 */

import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.swing.*;


public class StartUpPage extends JFrame implements ActionListener{


	JPanel panel;
	JButton setUpButton;
	JButton continueButton;
	static NewGameSetUp gameSetUp;
	JLabel KingdominoLabel;
	
	public StartUpPage() {
		// constructor
		super();
		this.setSize(600,600);
		this.setTitle("King Domino");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel=  new JPanel();
		KingdominoLabel= new JLabel(new ImageIcon("images/kingdomino.jpg"));
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

	//Tried to implement a load game method
	/*public static void loadGame(){
		try{
			FileInputStream fis = new FileInputStream("Adv.sav");
			ObjectInputStream ois = new ObjectInputStream(fis);
			game = (Driver) ois.readObject();
			ois.close();

		} catch (Exception e){

		}
	}*/
	
	
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



