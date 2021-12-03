import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //sprawdzenie, czy enumaracja jest dostępna
        Pawn pionek = new Pawn("TestPawn");
        Log.info("State: " + pionek.getCurrentState());

        Scanner input = new Scanner(System.in);

        input.nextLine();

        Board board = new Board(50,10);
        Dice dice = new Dice(12);
        board.addDice(dice);

        String name;

        System.out.println("Enter (3-10) Pawn names, Enter nothing to end input:");
        for(int i=0;i<10;i++) {
            System.out.println("Enter name for Pawn No. " + (i+1) + " :");
            name=input.nextLine();
            if((i>=3)&&(name==""))break;
            if((i<3)&&(name==""))i--;
            if(name!=""){
                Pawn pawn = new Pawn(name);
                board.addPawn(pawn);
            }
        }

        try
        {
            board.verifyRequirements();

            while(true)
            {
                board.performTurn();
            }
        } catch(WinnerWasCalled exception)
        {

            Log.info(board.getWinnerName() + " won.");
        }
    }
}