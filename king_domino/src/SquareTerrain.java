import java.awt.*;
import javax.swing.*;

/**
 * Each Rectangle Tile is consisted of two square terrains
 *
 * @author aRahnama
 * @version 
 */
public class SquareTerrain extends JButton
{
    // instance variables - replace the example below with your own
    private double id;
    private Color terrainColor;
    private Icon img;
    private int factor;
    private String name;
    private JButton terrain;

    /**
     * Constructor for objects of class SquareTerrain
     */
    public SquareTerrain(double id)
    {
    	super();
    	
        // initialise instance variables
        this.id=id;
        
        //names the terrain
        setName();
        
        //sets the number of crowns on each suare terrain
        setFactor();
        
        //if color palette 1 is selected we will set the background of the buttons the images of the game terrains
        if(NewGameSetUp.getPalette().equals("Color Palette1")) {
	        switch(this.getName()) {
	        case("wheatFields"):
	        	this.img=new ImageIcon(Class.class.getResource("images/wheatfeilds.png"));
	        case("forests"):
	        	this.img=new ImageIcon(Class.class.getResource("images/forest.png"));
	        case("grassLands"):
	        	this.img=new ImageIcon(Class.class.getResource("images/grassLands.png"));
	        	
	        case("lakes"):
	        	this.img=new ImageIcon(Class.class.getResource("images/lakes.png"));
	        case("mines"):
	        	this.img=new ImageIcon(Class.class.getResource("images/mines.png"));
	        case("swamps"):
	        	this.img=new ImageIcon(Class.class.getResource("images/swamps.png"));
	        	
	        }
		}
		
		if(NewGameSetUp.getPalette().equals("Color Palette2")) {
			switch(this.getName()) {
	        case("wheatFieldsbw"):
	        	this.img=new ImageIcon(Class.class.getResource("images/wheatfeildsbw.png"));
	        case("forestsbw"):
	        	this.img=new ImageIcon(Class.class.getResource("images/forestbw.png"));
	        case("grassLandsbw"):
	        	this.img=new ImageIcon(Class.class.getResource("images/grassLandsbw.png"));
	        	
	        case("lakesbw"):
	        	this.img=new ImageIcon(Class.class.getResource("images/lakesbw.png"));
	        case("minesbw"):
	        	this.img=new ImageIcon(Class.class.getResource("images/minesbw.png"));
	        case("swampsbw"):
	        	this.img=new ImageIcon(Class.class.getResource("images/swampsbw.png"));
	        	
	        }
		}
        terrain.setIcon(this.img);
    
        setVisible(true);
    }

    /**
     * sets a terrain's name based on their ids and games instruction
     * the names and ids are taken from the domino tiles image
     *
     * @return    void but set the names based on the parameter(id given) at the time of initialization
     */
    public void setName()
    {
        // put your code here
        if(this.id==1.0||this.id==1.1||this.id==2.0||this.id==2.1||this.id==13.0||this.id==14.0||this.id==15.0||
        this.id==16.0||this.id==19.0||this.id==20.0||this.id==21.0||this.id==22.0||this.id==23.0||this.id==24.1||
        this.id==25.1||this.id==26.1||this.id==27.1||this.id==30.1||this.id==31.1||this.id==36.0||this.id==40.1||
        this.id==43.0||this.id==38.0||this.id==41.0||this.id==45.1||this.id==48.0){
        	this.name= "wheatFields";
    }
    
    if(this.id==3.0||this.id==3.1||this.id==4.0||this.id==4.1||this.id==4.0||this.id==5.1||this.id==6.0||
        this.id==6.1||this.id==13.1||this.id==17.0||this.id==18.0||this.id==19.1||this.id==24.0||this.id==25.0||
        this.id==26.0||this.id==27.0||this.id==28.0||this.id==29.0||this.id==32.1||this.id==33.1||this.id==34.1||
        this.id==35.1){
            this.name="forests";
    }
    
    if(this.id==10.0||this.id==10.1||this.id==11.0||this.id==11.1||this.id==15.1||this.id==18.1||this.id==21.1||
        this.id==29.1||this.id==36.1||this.id==39.0||this.id==37.1||this.id==41.1||this.id==42.1||this.id==44.0
        ){
            this.name="grassLands";
    }
    
    if(this.id==8.0||this.id==8.1||this.id==7.0||this.id==7.1||this.id==9.0||this.id==9.1||this.id==14.1||
        this.id==17.1||this.id==20.1||this.id==28.1||this.id==30.0||this.id==31.0||this.id==32.0||this.id==33.0||
        this.id==34.0||this.id==35.0||this.id==37.0||this.id==42.0){
            this.name="lakes";
    }
    
    if(this.id==23.1||this.id==40.0||
        this.id==44.1||this.id==45.0||this.id==46.1||
        this.id==47.1||this.id==48.1){
            this.name="mines";
    }

    if(this.id==12.0||this.id==12.1||this.id==16.1||this.id==22.1||this.id==39.1||
        this.id==43.1||this.id==38.1||this.id==46.0||this.id==47.0){
            this.name="swamps";
    }
    }
    
    //set number of crowns based on the terrains' ids
   public void setFactor(){
	   double[] crown1= {19.0,20.0,21.0,22.0,23.0,24.0,25.0,26.0,27.0,28.0,29.0,30.0,31.0,32.0,33.0,34.0,35.0,36.1,39.1,40.0,37.1,38.1};
	   double[] crown2= {43.1,41.1,42.1,44.1,45.0,46.1,47.1};
	   double[] crown3= {48.1};
	   double[] crowns= {19.0,20.0,21.0,22.0,23.0,24.0,25.0,26.0,27.0,28.0,29.0,30.0,31.0,32.0,33.0,34.0,
			   35.0,36.1,39.1,40.0,37.1,38.1,43.1,41.1,42.1,44.1,45.0,46.1,47.1,48.1};
  
	   for(double num: crown1) {
		   if(this.id==num) {
			   this.factor=1;
		   }
		   
	   }
	   
	   for(double num: crown2) {
		   if(this.id==num) {
			   this.factor=2;
		   }
		   
	   }
	   for(double num: crown3) {
		   if(this.id==num) {
			   this.factor=3;
		   }
		   
	   }
	   
	   for(double num: crowns) {
		   if(this.id!=num) {
			   this.factor=0;
		   }
		   
	   }
	   
   }
   
   //returns the number of crowns
   public int getFactor() {
	   return this.factor;
   }
   
   //returns the id given at the time of initialization
   public double getId() {
	   return this.id;
   }
   
   // returns name of a terrainsquare
   public String getName() {
	   return this.name;
	   
   }
   
   //sets the background color of a square terrain based on each terrain's name
   /*public void setColor() {
	   if(this.name.equals("wheatFields")) {
		   this.setBackground(Color.YELLOW);  
	   }
	   if(this.name.equals("forests")) {
		   this.setBackground(Color.GREEN);  
	   }
	   if(this.name.equals("grassLands")) {
		   this.setBackground(Color.MAGENTA);  
	   }
	   if(this.name.equals("lakes")) {
		   this.setBackground(Color.BLUE);  
	   }
	   if(this.name.equals("mines")) {
		   this.setBackground(Color.GRAY);  
	   }
	   if(this.name.equals("swamps")) {
		   this.setBackground(Color.ORANGE);  
	   }
	}*/

   //returns background color for color palette 2
   public Color getColour() {
	   return this.getBackground();
   }
   
   //returns background image if applicable(color palette 1)
   public Icon getIcon() {
	   return this.img;

   }

}