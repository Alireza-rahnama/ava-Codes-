import java.util.ArrayList;

/**
 * This class stores 6 different teams ands stores the matches between these
 * teams
 */
public class Group {
    // instance variables - replace the example below with your own
    private String name;
    private Team[] teams;
    private ArrayList<Match> matches;

    /**
     * Constructor for objects of class Group
     */
    public Group() {
        // initialise instance variables
        this.name = name;
        teams = new Team[4];
        matches = new ArrayList<>();
    }

    /**
     * Accessors and mutators for instance variables
     */
    public Team[] getTeams() {
        return teams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTeam(int index, Team team) {
        teams[index] = team;
    }

    /**
     * Adds a match to arrayList with the score for both teams
     */
    public void addMatch(int team1Score, int team2Score) {
        matches.add(new Match(teams, team1Score, team2Score));
    }

    /**
     * returns a string with the points and goals of each team
     */
    public String calculateRanking() {
        String s = "Group '" + getName() + "' Rankings:\n";
        for (Team team : teams) {
            s += team.getName() + " has " + team.getPoints() + " points and " + team.getGoals() + " goals.";
        }
        return s;
    }
}    
