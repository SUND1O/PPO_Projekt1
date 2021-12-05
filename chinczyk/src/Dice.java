import java.util.Random;

class Dice {
    private int sides = 6;
    public int roll() {
        Random rand = new Random();
        int result = rand.nextInt(sides) + 1;

        Log.info("Dice roll: " + result);
        return result;
    }
    public Dice (){};
}
