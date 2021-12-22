package Game;

public class Main {

    public static void main(String[] args) {
        Board plansza = new Board(500);
        //rozkaż planszy przeprowadzenie gry w pętli
        try
        {
            while(true)
            {
                //delay 500ms między ruchami
                try{Thread.sleep(500);}
                catch(InterruptedException e){}

                plansza.nextTurn();
            }

        } catch (GameHasEnded exception)
        {
            //zakończ grę i wypisz w kolejności zwycięzców (omija ostatniego gracza)
            Log.info("\nGame over.");

            if (plansza.getWinnerList().size() == 0)Log.info("Nobody won.");
            else {
                Log.info("Winner list:");

                for (int i = 0; i < plansza.getWinnerList().size(); i++) {
                    Log.info((i + 1) + " - " + plansza.getWinnerList().get(i).getTeamInfo());
                }
            }
        }
    }
}
//fun fact1: średnia ilość tur do przejścia gry przez samych losowo zachowujących się graczy to około (256.2)