class Pawn {
    // ↓ stan pionka, w sumie to jest używane tylko w tej klasie, żeby łatwiej widzieć co się z nim dzieje.
    private enum State {
        STASHED, PLAYING, FINISHED
    }
    private int team;
    private int number;
    private int progress = -1;//postęp do wygranej, nie trzeba liczyć odległości do mety w trudny sposób
    private int position = -1;

    State currentState = State.STASHED;


    public void Stash(){
        currentState = State.STASHED;
        progress = -1;
        position = -1;
    }
    public void Start(){
        currentState = State.PLAYING;
        progress = 0;

        // ↓ ustawia startową pozycję pionków: r=1,y=11,b=21,g=31
        switch(team){
            case 0: this.position = 1 ; break;
            case 1: this.position = 21 ; break;
            case 2: this.position = 11 ; break;
            case 3: this.position = 31 ; break;
            default:Log.info("No starting position for colorless pawns");break;
        }
    }

    public int getTeam(){
        return this.team;
    }


    public int getPosition() {
        return this.position;
    }

    // ↓ używane do ruszania pionkiem
    public void move(int distance)
    {
        if(currentState != State.STASHED) {
            // ↓ jeśli pozycja jest mniejsza od 0 to pewnie jakiś błąd więc nie może zmienić dystansu
            if (position >= 0)
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
                    currentState = State.FINISHED;
                    progress = 40;
                }
            }
        }
        else if(distance == 6) Start();// w przypadku, gdy wybrany pion jest w schowku i wypadło 6 to wrzuć go na pole
        else Log.info("Stashed pawns cannot be moved");

    }

    public Pawn(int team, int number) {
        this.team = team;
        this.number = number;




        Log.info("Pawn nr. " + this.number + "from team nr. " + this.team + " joined the game.");
    }
}