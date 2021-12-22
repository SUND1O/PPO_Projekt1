package Game.Controllers;

import Game.Board;
import Game.Log;
import Game.Pawn;
import Game.PawnState;

public class NPC_Hold extends Controller{
    public NPC_Hold(String controllerName, int controllerTeam, Board boardRef){
        this.boardRef = boardRef;
        this.controllerTeam = controllerTeam;
        this.controllerName = controllerName;
    }

    //nadpisanie metody, 'npc hold' wybiera pionki najdalej od mety
    @Override
    public void MovePawn(int distance) {

        resetUsablePawnList(distance);

        // jeśli zbiór nie jest pusty to przeprowadź ruch
        if(usablePawns.size() > 0) {
            //wybierz pion, który ma największy progress

            int highiestProgress = 41;
            for(Pawn pawn : usablePawns){
                if(pawn.getProgress() < highiestProgress){
                    highiestProgress = pawn.getProgress();
                    movedPawn = pawn;
                }
            }

            if(movedPawn.getCurrentState() == PawnState.STASHED) Log.info(movedPawn.getTeamInfo() + " has entered the track.");
            if(movedPawn.getCurrentState() == PawnState.PLAYING) Log.info(movedPawn.getTeamInfo() + " has been moved.");

            movedPawn.move(distance);

            Log.info(movedPawn.getTeamInfo() + " current location: " + movedPawn.getPosition() + ", progress: " + movedPawn.getProgress() +"/40");
        }else
            Log.info(this.getTeamInfo() + " could not pick any pawn...");
    }
}
