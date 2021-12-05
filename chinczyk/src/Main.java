import java.util.Scanner;

//najprawdopodobniej nie będzie tworzenia wizualnej reprezentacji, bo jest zbyt autystyczna

public class Main {

    public static void main(String[] args) {

        /*
        MyFrame test = new MyFrame();
        GraphTest test = new GraphTest();
        JFrame jf = new JFrame();
        jf.setTitle("help");
        jf.setSize(600,400);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.add(test);
        */
/*
        //sprawdzenie, czy enumaracja jest dostępna
        Pawn pionek = new Pawn("TestPawn");
        Log.info("State: " + pionek.getCurrentState());

        Scanner input = new Scanner(System.in);

        input.nextLine();

        BoardOld boardOld = new BoardOld(50,10);
        Dice dice = new Dice(12);
        boardOld.addDice(dice);

        String name;

        System.out.println("Enter (3-10) Pawn names, Enter nothing to end input:");
        for(int i=0;i<10;i++) {
            System.out.println("Enter name for Pawn No. " + (i+1) + " :");
            name=input.nextLine();
            if((i>=3)&&(name==""))break;
            if((i<3)&&(name==""))i--;
            if(name!=""){
                Pawn pawn = new Pawn(name);
                boardOld.addPawn(pawn);
            }
        }

        try
        {
            boardOld.verifyRequirements();

            while(true)
            {
                boardOld.performTurn();
            }
        } catch(WinnerWasCalled exception)
        {

            Log.info(boardOld.getWinnerName() + " won.");
        }
*/
        //new shit






    }
}