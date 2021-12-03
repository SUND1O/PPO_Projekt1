class Pawn {
    private enum State {
        STASHED, PLAYING, FINISHED
    }
    State currentState = State.STASHED;
    public void setCurrentState(State newState){
        currentState = newState;
    }
    public State getCurrentState(){
        return currentState;
    }
    private int position = 0;
    private String name;

    public int getPosition() {
        return this.position;
    }
    public void move(int distance)
    {
        if(distance+position>0)
            this.position+=distance;
    }
    public String getName() {
        return this.name;
    }

    public Pawn(String name) {
        this.name = name;
        Log.info(this.name + " joined the game.");
    }
}