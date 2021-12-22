package Game.Controllers;

import Game.Board;
import Game.Controllers.Controller;
import Game.Log;
import Game.Pawn;
import Game.PawnState;

import java.util.Scanner;

public class PlayerController extends Controller {
    public PlayerController(String controllerName, int controllerTeam, Board boardRef) {
        this.boardRef = boardRef;
        this.controllerTeam = controllerTeam;
        this.controllerName = controllerName;
        Log.info(this.getTeamInfo() + " joined the game.");
    }

    //nadpisanie metody, 'Game.Controllers.PlayerController' przyjmuje input od użytkownika, żeby wybrać pionek
    @Override
    public void MovePawn(int distance) {

        resetUsablePawnList(distance);

        // jeśli zbiór nie jest pusty to przeprowadź ruch
        if(usablePawns.size() > 0) {

            Log.info("Pick a pawn to move: ");

            int iteration = 0;
            for(Pawn pawn : usablePawns){
                Log.info("[" + iteration + "] - Pawn No." + (pawn.getNumber() + 1));
                iteration++;
            }
            Scanner input = new Scanner(System.in);
            int selection = -1;

            while (true) {
                try {
                    selection = input.nextInt();
                    if(selection >= 0 && selection < iteration) break;
                    else Log.info("Invalid value...");
                } catch (java.util.InputMismatchException e) {
                    Log.info("Invalid value...");
                    input.nextLine();
                }
            }

            movedPawn = usablePawns.get(selection);

            if(movedPawn.getCurrentState() == PawnState.STASHED) Log.info(movedPawn.getTeamInfo() + " has entered the track.");
            if(movedPawn.getCurrentState() == PawnState.PLAYING) Log.info(movedPawn.getTeamInfo() + " has been moved.");
            movedPawn.move(distance);

            Log.info(movedPawn.getTeamInfo() + " current location: " + movedPawn.getPosition() + ", progress: " + movedPawn.getProgress() +"/40");
        }else
            Log.info(this.getTeamInfo() + " could not pick any pawn...");

    }
}