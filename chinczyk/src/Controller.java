import java.util.ArrayList;

public class Controller {
    protected ArrayList<Pawn> controlledPawns = new ArrayList<Pawn>();
    protected Board boardRef;//odniesienie do planszy, przyda się w przekazywaniu sygnałów
    protected int controllerTeam = 0;//drużyna kontrolera, zakres od 0-3, czerwony/niebieski/zielony/żółty
    protected String controllerName = "anon";

    public void AddControllablePawn(Pawn pawn){
        this.controlledPawns.add(pawn);
    }

    public boolean MovePawn(Pawn pawn, int range){
        if(this.controlledPawns.contains(pawn)) {
            pawn.move(range);

            return true;
        }

        return false;
    }
    public Controller(){}
    public Controller(String controllerName, int controllerTeam,  Board boardRef){
        this.boardRef = boardRef;//pozwoli na dostęp do metod planszy przez kontroler
        this.controllerTeam = controllerTeam;
        this.controllerName = controllerName;
    }



}
