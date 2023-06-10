import java.util.Random;

/**
 * Generetates random 'Names' for coaches and players
 */
public class Name {
    // instance variables - replace the example below with your own
    private String name;

    /**
     * Constructor for objects of class Player
     */
    public Name() {
        // initialise instance variables
        name = randomPlayerGenerator();
    }


    /**
     * An example of a method - replace this comment with your own
     *
     * @param y a sample parameter for a method
     * @return the sum of x and y
     */
    public String randomPlayerGenerator() {
        // initialise instance variables
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = new Random();
        int len = rand.nextInt(10); //creates an integer number with upperbound of 10
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        }

        return sb.toString();
    }
}