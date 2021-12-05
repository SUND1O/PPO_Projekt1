import java.util.ArrayList;

class BoardOld {

    private int maxPosition = 100, maxTurns = 100;

    private ArrayList<Pawn> pawns;
    private Dice dice = null;
    private Pawn winner = null;

    private int turnsCounter = 0;

    public String getWinnerName(){
        if (this.winner == null) return "Nobody";
        else return "oof";//this.winner.getName();
    }
    public void addDice(Dice dice){
        this.dice = dice;
    }

    public BoardOld(int maxPosition, int maxTurns) {
        this.pawns = new ArrayList<Pawn>();

        if
        ((maxPosition >= 20)
                &&
                (maxPosition <= 600))
            this.maxPosition = maxPosition;

        else System.out.println
                (
                        "max_position available range is 20-600!" +
                                "\nmax_position set to: " + this.maxPosition
                );

        if
        ((maxTurns >= 1)
                &&
                (maxTurns <= 300))
            this.maxTurns = maxTurns;

        else System.out.println
                (
                        "max_turns available range is 1-300!" +
                                "\nmax_turns set to: "+this.maxTurns
                );
    }

    public BoardOld() {
        this.pawns = new ArrayList<Pawn>();
        this.dice = null;
        this.winner = null;
        this.turnsCounter = 0;
    }
    public void verifyRequirements() throws WinnerWasCalled {
        if(pawns.isEmpty()) {
            Log.info("Cannot start game without Pawns!");
            throw new WinnerWasCalled();
        }
        if(dice==null) {
            Log.info("Cannot start game without Dice!");
            throw new WinnerWasCalled();
        }
    }
    public void addPawn(Pawn pawn) {
        this.pawns.add(pawn);
    }


    public void performTurn() throws WinnerWasCalled {

        this.turnsCounter++;

        Log.info("Turn " + this.turnsCounter);

        for(Pawn pawn : this.pawns) {
            int rollResult = this.dice.roll();
            pawn.move(rollResult);
            //Log.info(pawn.getName() + " new position: " + pawn.getPosition());

            if(pawn.getPosition() >= this.maxPosition) {
                this.winner = pawn;
                throw new WinnerWasCalled();
            }
        }

        if(turnsCounter >= maxTurns) {
            Log.info("\nOut of turns");

            int highScore = 0;
            for(Pawn pawn : this.pawns) {
                if(pawn.getPosition()>highScore) {
                    highScore = pawn.getPosition();
                    this.winner = pawn;
                }
            }
            throw new WinnerWasCalled();
        }
    }
}
