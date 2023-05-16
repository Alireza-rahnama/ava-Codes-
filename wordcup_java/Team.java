import java.util.Random;

/**
 * this class creates an object of a Team of soccer with instance variables of Country, 23 randpomly generated player names,
 * and a randomly generated coach name.
 */
public class Team {
    // instance variables - replace the example below with your own
    protected String country;
    private Name[] players;
    private Name coach;
    private int goals;
    private int points;
    private String[] countries;


    /**
     * Constructor for objects of class Team
     */
    public Team() {
        // initialise instance variables
        this.players = new Name[23];
        players = setTeam();
        this.coach = new Name();
        countries = new String[]{"Portugual", "Spain", "France", "Argentina", "Iceland", "Peru", "Croatia", "Denmark",
                "Nigeria", "CostaRica", "Germany", "Serbia", "Mexico", "Brazil", "Sweden", "Switzerland", "South Korea", "Belgium",
                "England", "Panama", "Colombia", "Japan", "Poland", "Senegal"};
        this.country = setCountry();
    }

    /**
     * returns the country of the team
     */
    public String getName() {
        return country;
    }

    /**
     * returns number of scored goals
     */
    public int getGoals() {
        return goals;
    }

    /**
     * returns the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param
     * @return generates a team consisted of 23 random names
     */
    public Name[] setTeam() {
        for (int i = 0; i < 23; i++) {
            Name player = new Name();
            players[i] = player;
        }
        return players;
    }

    /**
     * randomly assigns a country to the generated team
     */
    public String setCountry() {
        Random rand = new Random();
        int index = rand.nextInt(countries.length);
        String country = countries[index];

        for (int i = index; i < countries.length - 1; i++) {
            countries[i] = countries[i + 1];
        }
        return country;

    }

    /**
     * Methods to keep track of goals and points following a match
     */
    public void addGoals(int newGoals) {
        goals += newGoals;
    }

    public void addPoints(int newPoints) {
        points += newPoints;
    }
}