package Game.Controllers;

import Game.*;

import java.util.ArrayList;

abstract public class Controller implements TeamInterface {
    protected ArrayList<Pawn> controlledPawns = new ArrayList<Pawn>();
    protected Board boardRef;//odniesienie do planszy, przyda się w przekazywaniu sygnałów
    protected int controllerTeam = 0;//drużyna kontrolera, zakres od 0-3, czerwony/niebieski/zielony/żółty
    protected String controllerName = "DefaultController";

    //odniesienie do wybranego pionka, który zostanie przeniesiony
    //przydaje się to do sprawdzenia, czy pionek nie zabił kogoś po zakończeniu ruchu
    //w klasie 'Board'
    protected Pawn movedPawn;

    public ArrayList<Pawn> getControlledPawns(){return controlledPawns;}

    public Pawn getMovedPawn(){
        return movedPawn;
    }

    public void AddControllablePawn(Pawn pawn){
        this.controlledPawns.add(pawn);
    }

    public Controller(){}
    public Controller(String controllerName, int controllerTeam,  Board boardRef){
        this.boardRef = boardRef;//pozwoli na dostęp do metod planszy przez kontroler
        this.controllerTeam = controllerTeam;
        this.controllerName = controllerName;

        Log.info(this.getTeamInfo() + " joined the game.");
    }

    public boolean hasMoveablePawns(){
        //sprawdź, czy jakikolwiek pionek ma stan 'PLAYING'
        for (Pawn pawn : controlledPawns)
        {
            if(pawn.getCurrentState() == PawnState.PLAYING){
                return true;
            }
        }
        return false;
    }

    public boolean hasFinished(){
        //sprawdź, czy wszystkie pionki mają stan 'FINISHED'
        for (Pawn pawn : controlledPawns)
        {
            //gdy pionek nie ma stanu 'FINISHED' to zwróć fałsz i zakończ pętle
            if(pawn.getCurrentState() != PawnState.FINISHED){
                return false;
            }
        }
        return true;
    }

    @Override
    public String getTeamInfo() {
        String colorname = "";
        switch(controllerTeam){
            case 0: colorname = "Red" ; break;
            case 1: colorname = "Blue" ; break;
            case 2: colorname = "Green" ; break;
            case 3: colorname = "Yellow" ; break;
        }
        return (this.controllerName + "[" + colorname + "]");
    }

    //ta tabela używane są przy sterowaniu pionkami
    protected ArrayList<Pawn> usablePawns = new ArrayList<Pawn>();

    //Używane w dziedziczących klasach przy metodzie "MovePawn"
    //Ta metoda korzysta z tabeli "usablePawns", dzięki której wiadomo,
    //czy pionek może być wybrany przy ruchu.

    //Ta metoda przy uruchomieniu czyści tabelę i przypisuje do niej wszystkie pionki, którymi może ruszyć w tym ruchu
    //czyli wszystkie pionki ze stamen PLAYING lub wszystkie ze stanem PLAYING lub STASHED w przypadku, gdy kostka wyrzuciła szóstkę
    protected void resetUsablePawnList(int diceRoll){
        //czysczenie starych zbiorów
        //stashedPawns.clear();
        usablePawns.clear();

        //wyznaczenie pionków
        for (Pawn pawn:controlledPawns) {

            if(pawn.getCurrentState()==PawnState.PLAYING) usablePawns.add(pawn);
            if(diceRoll == 6)
            {
                if(pawn.getCurrentState()==PawnState.STASHED) usablePawns.add(pawn);
            }
        }
    }

    /*
   Ta metoda jest wywowyłana przy ruchu drużyny,
   kontroler ma już podaną wartość kostki
   i musi wybrać pionek, który chce wykorzystać.
   Ta metoda powinna tylko przenosić pionek
   i blokować przed niepoprawnym ruchem
   np. ruszanie wygranym pionkiem
   */
    public void MovePawn(int distance){ }
}
