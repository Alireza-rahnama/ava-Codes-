import java.util.Random;

/**
 * Distributes teams into 6 different groups with 4 teams each
 * with methods for simulating 30 random matches and printing the results
 */
public class WorldCup
{
    // instance variables - replace the example below with your own
    private Group[] groups;

    /**
     * Constructor for objects of class WorldCup
     */
    public WorldCup()
    {
        // initialise instance variables
        groups = new Group[6];
        worldCupSetup();
    }

    /**
     * Sorts 24 teams into 6 groups with 4 teams each
     */
    public void worldCupSetup()
    {
        String[] groupNames = {"A","B","C","D","E","F"};
        for(int j=0;j<6;j++){
            Group group = new Group();
            group.setName(groupNames[j]);
            groups[j] = group;
            for(int i=0;i<4;i++){
                group.addTeam(i,new Team());
            }    
            }
    }
    /**
     * simulate 30 matches with 5 matches per group
     */
    public void randomMatches(){
        Random rand = new Random();
        for(Group group : groups){
            for(int i =0;i<5;i++){
                group.addMatch(rand.nextInt(10),rand.nextInt(10));
            }
        }
    }
    /**
     * print the results of the random matches
     */
    public void calculateRanking(){
        for(Group group : groups){
            System.out.println(group.calculateRanking());
        }
    }
}
