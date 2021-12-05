import java.util.ArrayList;
import java.util.Collections;

public class Board {
    private ArrayList<Controller> playerList;
    private ArrayList<Pawn> pawns;
    private Dice dice = new Dice(); //tworzenie kostki z zewnątrz bez sensu z powodu braku modyfikowania

    public void addPawn(Pawn pawn) {
        this.pawns.add(pawn);
    }

    public int currentLoopTurn = 0;

    public void nextTurn(){
        switch(currentLoopTurn){
            case 0: Log.info("Red team's turn.");break;
            case 1: Log.info("Blue team's turn.");break;
            case 2: Log.info("Green team's turn.");break;
            case 3: Log.info("Yellow team's turn.");break;
            default:Log.info("No team's turn");break;
        }
        currentLoopTurn++;

        //wraca się z drużyny żółtej do czerwonej
        if(currentLoopTurn > 3) currentLoopTurn -= 4;
    }


    public void verifyRequirements() throws FailedRequirements {
        if(pawns.isEmpty()) {
            Log.info("Cannot start game without Pawns!");
            throw new FailedRequirements();
        }
        if(dice==null) {
            Log.info("Cannot start game without Dice!");
            throw new FailedRequirements();
        }
    }



    public Board(){
        //dodanie 4 losowych kontrolerów do planszy
        //można zmienić na dodanie 3 specyficznych i 1 gracza
        /*
        for (int i = 0; i < 4; i++) {
            playerList.add(new Controller("player: " + i, i,this ));
        }
        */
        playerList.add(new Controller("Player", 0,this ));
        playerList.add(new NPC_Random("Randy Random", 1,this ));
        playerList.add(new NPC_Random("Randy Random", 2,this ));
        playerList.add(new NPC_Random("Randy Random", 3,this ));




        //dodanie pionków do kontrolera
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++){
                playerList.get(i).AddControllablePawn(new Pawn(i,j));

            }
        }



    }
}
