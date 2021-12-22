package Game;

import java.util.Random;
public final class Dice {
    public static int roll(){
        Random rand = new Random();
        int result = rand.nextInt(6) + 1;

        Log.info("Dice roll: " + result);
        return result;
    }
}
