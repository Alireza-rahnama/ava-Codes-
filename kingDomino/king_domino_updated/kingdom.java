/*import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.util.ArrayList;

//KINGDOM is a 2d panel .consisted of a set of 10 by 10 buttons listed by a Gridlayout
// it still needs actionperformed functions

public class kingdom extends JPanel implements ActionListener{
	private GridSquare grid;
	private static GridSquare[][] gridSquares; // squares to appear in grid formation in each kingdom
	// private String location;
	private int x;
	private int y;
	private int score;	
	
	boolean firstMove;
	ArrayList<GridSquare> SelectedGridSquares;
	public kingdom(int x, int y) {
		this.x=x;
		this.y=y;
		firstMove=true;
		this.setSize(200, 200);
		this.setLayout(new GridLayout(10,10));
		this.score=0;

		gridSquares= new GridSquare[x][y];
		for (int column = 0; column < this.x; column++) {
			for (int row = 0; row < this.y; row++) {
				// location= ""+column+row;
				gridSquares[column][row] = new GridSquare(column,row);
				gridSquares[column][row].setOpaque(true); // without this line and the next the OS' default
				gridSquares[column][row].setBorderPainted(true); // look & feel will dominate / interfere

				if(NewGameSetUp.getDifficulty().equals("Easy")&&column==4&&row==4){
					Icon castle =new ImageIcon("castle1.jpg");
					gridSquares[column][row].setIcon(castle);

				}
				if(NewGameSetUp.getDifficulty().equals("Hard")&&column==2&&row==2){
					Icon castle =new ImageIcon("castle1.jpg");
					gridSquares[column][row].setIcon(castle);
		
				}
				if((NewGameSetUp.getDifficulty().equals("Easy")&&column==4&&row==3)
				||(NewGameSetUp.getDifficulty().equals("Easy")&&column==4&&row==5)
				||(NewGameSetUp.getDifficulty().equals("Easy")&&column==5&&row==4)
				||(NewGameSetUp.getDifficulty().equals("Easy")&&column==3&&row==4)){
					gridSquares[column][row].setAccessible();
				}
				this.add(gridSquares[column][row]);
			}
		}
		
	
	}

	// public int getX(){
	// 	return this.x;
	// }

	// public int getY(){
	// 	return this.y;
	// }


	public int getScore(){
		return this.score;
		
	}
	public void setScore(int z){
		this.score=z;
	}

	public boolean isFirstMove(){
		return firstMove;
	}

	public void notFirstMove(){
		firstMove=false;
	}

	public ArrayList<GridSquare> getSelectedGridSquares(){
		return SelectedGridSquares;
	}

	public GridSquare[][] getKingdomGridSquares(){
		return gridSquares;
	}






	public void actionPerformed(ActionEvent e) 	{
		// get the object that was selected in the gui
		Object selected = e.getSource();
		SelectedGridSquares= new ArrayList<>();
		
		if ( selected instanceof JButton)
		{
			// if(((GridSquare)selected).isaccessibleToPlayer()){
			// 	SelectedGridSquares.add((GridSquare)selected);
			// 	((GridSquare)selected).setOffAccess();
			// 	GridSquare[][] KingdomGS= kingdom.getKingdomGridSquares();
			// 	int Xcoordinate= ((GridSquare) selected).getXCoord();
			// 	int ycoordinate= ((GridSquare) selected).getYCoord();
			// 	KingdomGS[Xcoordinate+1][ycoordinate].setAccessible();
			// 	KingdomGS[Xcoordinate-1][ycoordinate].setAccessible();
			// 	KingdomGS[Xcoordinate][ycoordinate+1].setAccessible();
			// 	KingdomGS[Xcoordinate][ycoordinate-1].setAccessible();
			// 	System.out.println("grid square with Xcoordinate is just seletcted:" + Xcoordinate);
			// 	System.out.println("grid square with Ycoordinate is just seletcted:" + ycoordinate);
			// 	System.out.println("neighbour with"+Xcoordinate+"and "+ (ycoordinate-1)+"is accessible?"+KingdomGS[Xcoordinate][ycoordinate-1].isaccessibleToPlayer());
		

			// }


			//GENERATE TWO INSTANCES OF GridButtons and add them to an arrayList called aList1
					//create a new arraylist of Gridsquares called selectedGridssquares and add anytime a grid square is selected
					//if(selected instanceof GridSquare){if(gridsquareselectedTimes!=0 and gridsquareselectedTimes%2==0){
					//	for(GridButton gb:aList1){for(gridSquare Gs:selectedGridssquares){gs.setBackground(gb.getBackground());alist1.remove(gb);
					//selectedGridssquares.remove(gs)}
					//}}}

		}





	}

	


}
	
*/

