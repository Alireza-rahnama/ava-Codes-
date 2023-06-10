import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.*;



// this class is created to enerate the rectangle tiles to be displayed in the taketurn window
// the tiles will be randomly drafted and madde visible on the screen and will be selectable


public class RectangleTile extends JButton implements ActionListener{
    // instance variables 
    private int num;
    private Color terrainColor;
    private Icon terrainImg;
    private JButton terrain;
    private int selectedTimes;
    private int crownFactor = 0;
    

    /**
     * Constructor for objects of class SquareTerrain
     */
    public RectangleTile(int id)
    {
        
    	super();
        selectedTimes=0;
        // initialise instance variables
        this.num=id;
        if(NewGameSetUp.getPalette().equals("Color Palette1")){
            terrainImg=new ImageIcon("images/"+this.num+".png");
            this.setIcon(terrainImg);
        }
        if(NewGameSetUp.getPalette().equals("Color Palette2")){
            terrainImg=new ImageIcon("images/"+this.num+"bw.jpg");
            this.setIcon(terrainImg);
        }
        //terrainImg=new ImageIcon(this.num+".png");
        //this.setIcon(terrainImg);
        //terrain.setBackground(Color.blue);
        this.setSize(80, 160);
        //terrain.setPreferredSize(new Dimension(40, 80));
        this.addActionListener(this);

    }

    public int getNumber(){
        return this.num;
    }

    public int getSelectedTimes(){
        return selectedTimes;
    }
    public void increaseSelectedTimes(){
        selectedTimes++;
    }


    //action listener
	public void actionPerformed(ActionEvent e) 	{
		// get the object that was selected in the gui
		Object selected = e.getSource();
        //increment the number of times each tile is selected
        if(selected instanceof RectangleTile){
			increaseSelectedTimes();
		}
        if(getSelectedTimes() == 2){
            TakeTurn.addSelectedRectangleTiles((RectangleTile)selected);
        }
    }

    //we could add actionlistener here, it will call getNumber() function and based on the number returned
    // generates GridButtonLeft= new GridButtun(Double.parseDouble(getNumber()+"0")), and GridButtonRight= new GridButtun(Double.parseDouble(getNumber()+"1"))
    //then in the kingdom when a button is selected the properties of these two buttons could be copied on the kingdom location
    //if conforming the games rules


            

}
