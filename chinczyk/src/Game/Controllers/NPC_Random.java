package Game.Controllers;

import Game.Board;
import Game.Log;
import Game.PawnState;

public class NPC_Random extends Controller{
    public NPC_Random(String controllerName, int controllerTeam,  Board boardRef){
        this.boardRef = boardRef;
        this.controllerTeam = controllerTeam;
        this.controllerName = controllerName;
    }


    //nadpisanie metody, 'npc random' działa losowo
    @Override
    public void MovePawn(int distance) {

        resetUsablePawnList(distance);

        // jeśli zbiór nie jest pusty to przeprowadź ruch
        if(usablePawns.size() > 0) {
            //wybierz losowy int w zakresie pasującym do ilości osobników (np. 3 pionki to zakres 0 - 2)
            movedPawn = usablePawns.get(((int) ((Math.random() * (usablePawns.size() - 1)))));

            if(movedPawn.getCurrentState() == PawnState.STASHED) Log.info(movedPawn.getTeamInfo() + " has entered the track.");
            if(movedPawn.getCurrentState() == PawnState.PLAYING) Log.info(movedPawn.getTeamInfo() + " has been moved.");
            movedPawn.move(distance);

            Log.info(movedPawn.getTeamInfo() + " current location: " + movedPawn.getPosition() + ", progress: " + movedPawn.getProgress() +"/40");
        }else
            Log.info(this.getTeamInfo() + " could not pick any pawn...");
    }
}
