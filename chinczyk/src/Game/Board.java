package Game;

import Game.Controllers.*;
import Graphics.GUI;

import java.util.ArrayList;

public class Board {
    GUI okienko; //klasa używana do wizualnej reprezentacji akcji

    private ArrayList<Controller> playerList = new ArrayList<Controller>();

    private ArrayList<Controller> winnerList = new ArrayList<Controller>();

    private int currentLoopTurn = 0;
    private int turnsAmount = 200;

    public ArrayList<Controller> getWinnerList(){
        return this.winnerList;
    }

    public void nextTurn() throws GameHasEnded {
        Log.info("Remaining turns: " + turnsAmount);

        //w przypadku, gdy 3 graczy wygrało to skończ grę
        if(winnerList.size() >= 3) {

            throw new GameHasEnded();
        }

        //wraca się z drużyny żółtej do czerwonej
        if(currentLoopTurn > 3) currentLoopTurn -= 4;

        //przeprowadź ruch gdy gracz nie skończył gry
        if (!(playerList.get(currentLoopTurn).hasFinished()))
        {

            Log.info("");
            Log.info(playerList.get(currentLoopTurn).getTeamInfo() + " turn.");

            //przekaż sterowanie kontrolerowi
            //jeśli kontroler ma pionki w schowku to powtórz rzut kostką do max trzech razy
            for(int attempt = 0; attempt < 3; attempt++){
                playerList.get(currentLoopTurn).MovePawn(Dice.roll());
                if(playerList.get(currentLoopTurn).hasMoveablePawns()) break;

                if (playerList.get(currentLoopTurn).getMovedPawn() != null){
                    //wypisz pozycję ruszonego pionka
                    Log.info(playerList.get(currentLoopTurn).getMovedPawn().getTeamInfo() + " current location: "
                            + playerList.get(currentLoopTurn).getMovedPawn().getPosition() + ", progress: "
                            + playerList.get(currentLoopTurn).getMovedPawn().getProgress() +"/40");
                    //przerwij jeśli pionek skończył wyścig
                    if(playerList.get(currentLoopTurn).getMovedPawn().getCurrentState() == PawnState.FINISHED)break;
                }
            }

            //zabijanie pionków
            for(Controller controller : playerList)
                for(Pawn pawn : controller.getControlledPawns())
                    {
                    if (
                            playerList.get(currentLoopTurn).getMovedPawn() != null &&   //ruszony pionek istnieje
                            playerList.get(currentLoopTurn).getMovedPawn().getCurrentState() == PawnState.PLAYING && //ruszony pionek jest na torze
                            pawn.getCurrentState() == PawnState.PLAYING && //zaatakowany pionek jest na torze
                            playerList.get(currentLoopTurn).getMovedPawn().getPosition() == pawn.getPosition() && //taka sama pozycja
                            playerList.get(currentLoopTurn).getMovedPawn().getTeam() != pawn.getTeam()  //inna drużyna
                    )
                    {
                        pawn.Stash();
                        Log.info(playerList.get(currentLoopTurn).getMovedPawn().getTeamInfo() + "has killed " + pawn.getTeamInfo());

                        //metoda w GUI, aktualizuje położenie zbitego pionka
                        okienko.updateVisualPosition(pawn);
                    }
                }

            //po ruchu sprawdź, czy gracz wygrał
            if (playerList.get(currentLoopTurn).hasFinished())
            {
                //jeśli gracza nie ma w liście zwycięzców to dodaj go do niej
                if ( !(winnerList.contains(playerList.get(currentLoopTurn))) )
                    winnerList.add(playerList.get(currentLoopTurn));
            }
        }

        //metoda w GUI, zaktualizuj położenie ruszonego pionka (musi być przed wzrostem loopturn)
        okienko.updateVisualPosition(playerList.get(currentLoopTurn).getMovedPawn());

        currentLoopTurn++;
        turnsAmount--;

        if(turnsAmount <= 0)throw new GameHasEnded("No more turns.");
    }

    public Board(int turnsAmount){
        if(turnsAmount >= 1 && turnsAmount <= 1000) this.turnsAmount = turnsAmount;
        else Log.info("Invalid \"turnsAmount\" value, defaulting to: " + this.turnsAmount);

        okienko = new GUI(this);//GUI

        //dodaje 4 kontrolery do planszy
        playerList.add(new PlayerController("YOU", 0,this ));
        playerList.add(new NPC_Random("Randy", 1,this ));
        playerList.add(new NPC_Rush("Dash", 2,this ));
        playerList.add(new NPC_Hold("Rocksteady", 3,this ));

        //dodanie pionków do każdego kontrolera
        for (int teamNr = 0; teamNr < 4; teamNr++)
        {
            for (int pawnNr = 0; pawnNr < 4; pawnNr++){
                Pawn pion = new Pawn(teamNr,pawnNr);
                playerList.get(teamNr).AddControllablePawn(pion);
            }
        }
    }
}
