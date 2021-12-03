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
    public Dice (int sides){

        if((sides>=2)&&(sides<=20))this.sides=sides;
        else System.out.println
                (
                        "Dice side count available range is 2-20!" +
                                "\nSide count set to: "+this.sides
                );
    }

}
