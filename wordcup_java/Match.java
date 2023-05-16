import java.util.Random;

/**
 * Randomizes a match between two teams in a group, while updating
 * each teams points and goals after the match concludes
 */
public class Match {
    // instance variables - replace the example below with your own
    private Team team1;
    private Team team2;
    private int team1Score;
    private int team2Score;
    private Team[] teams;

    /**
     * Constructor for objects of class Match, updates the point and goal
     * counters for both teams when a match is created
     */
    public Match(Team[] teams, int team1Score, int team2Score) {
        // initialise instance variables
        this.teams = teams;
        team1 = randomTeam();
        team2 = randomTeam();
        team1Score = team1Score;
        team2Score = team2Score;
        team1.addGoals(team1Score);
        team2.addGoals(team2Score);
        team1.addPoints(team1Score / 10);
        team2.addPoints(team2Score / 10);
        if (team1Score > team2Score) { //if team 1 wins add 3 points
            team1.addPoints(3);
        } else if (team2Score > team1Score) { //if team 2 wins add 3 points
            team2.addPoints(3);
        } else {//if theres a draw add 1 point to both teams
            team1.addPoints(1);
            team2.addPoints(1);
        }
    }

    /**
     * Accessors for instance variables
     */
    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public int getTeam1Score() {
        return team1Score;
    }

    public int getTeam2Score() {
        return team2Score;
    }

    /**
     * returns a random team from the group
     */
    public Team randomTeam() {
        // put your code here
        Random rand = new Random();
        int teamIndex = rand.nextInt(4);
        teams[teamIndex] = teams[teamIndex + 1];
        Team randTeam = teams[teamIndex];
        return randTeam;
    }
}