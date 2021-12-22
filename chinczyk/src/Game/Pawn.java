package Game;

public class Pawn implements TeamInterface {

    private int team;
    private int number;
    private int progress = -1;//postęp do wygranej, nie trzeba liczyć odległości do mety w trudny sposób
    private int position = -1;

    private PawnState currentState = PawnState.STASHED;

    public PawnState getCurrentState(){
        return currentState;
    }

    public void Stash(){
        currentState = PawnState.STASHED;
        progress = -1;
        position = -1;
    }
    public void Start(){
        currentState = PawnState.PLAYING;
        progress = 0;

        //ustawia startową pozycję pionków: r=1,y=11,b=21,g=31
        switch(team){
            case 0: this.position = 1 ; break;
            case 1: this.position = 21 ; break;
            case 2: this.position = 31 ; break;
            case 3: this.position = 11 ; break;
            default:
                Log.info("No starting position for colorless pawns");break;
        }
    }

    public int getTeam(){
        return this.team;
    }

    public int getNumber(){
            return this.number;
        }

    public int getProgress() { return this.progress; }

    public int getPosition() {
        return this.position;
    }

    public void move(int distance)
    {
        if(currentState != PawnState.STASHED) {
            //jeśli pozycja jest mniejsza od 0 to pewnie jakiś błąd więc nie może zmienić dystansu
            if (position > 0)
            {
                this.position += distance;
                /*
                w przypadku okrążenia mapy, czyli minięcia 40go pola odejmuje od wyniku 40
                czyli na ostatnim polu (40) rzucam 5 i ląduje na 45, czyli (5)
                */
                if (position > 40) this.position -= 40;

                /*
                zwiększa postęp pionka relatywnie do jego mety,
                 gdy pionek przebiegnie 40 pól to kończy wyścig i jest chowany w środku planszy
                */
                progress += distance;
                if (progress > 40) {
                    currentState = PawnState.FINISHED;
                    progress = 41;

                    Log.info(this.getTeamInfo() + " has reached the finish line.");

                    //ustawianie pionków na mecie
                    switch(team){
                        case 0 : position = 41 + number; break;
                        case 1 : position = 45 + number; break;
                        case 2 : position = 49 + number; break;
                        case 3 : position = 53 + number; break;
                    }

                }
            }
        }
        else if(distance == 6) Start();// w przypadku, gdy wybrany pion jest w schowku i wypadło 6 to wrzuć go na pole
        else Log.info("Stashed pawns cannot be moved.");

    }

    public Pawn(int team, int number) {
        this.team = team;
        this.number = number;

        Log.info(this.getTeamInfo() + " added to board.");
    }

    @Override
    public String getTeamInfo() {
        String colorname = "";
        switch(team){
            case 0: colorname = "Red" ; break;
            case 1: colorname = "Blue" ; break;
            case 2: colorname = "Green" ; break;
            case 3: colorname = "Yellow" ; break;
        }
        return ("Pawn no. " + (this.number + 1) + "[" + colorname + "]");
    }
}