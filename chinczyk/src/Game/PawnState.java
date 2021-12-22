package Game;

public enum PawnState {
        STASHED, PLAYING, FINISHED
}
//ta enumaracja jest do stwierdzenia, czy pionek jest użyteczny na planszy / stoi w schowku / zakończył grę
//można było użyć INT zamiast tego ale to jest bardziej intuicyjne czy przejrzyste
//jest to użyte głównie do sprawdzania w kontrolerze, czy może ruszyć pionkiem i czy wygrał