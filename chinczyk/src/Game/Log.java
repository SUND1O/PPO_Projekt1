package Game;

public class Log
{
    public static void info(String message) {
        System.out.println(message);
    }
}
class GameHasEnded extends Exception {
    GameHasEnded(){};
    GameHasEnded(String message){
        System.out.println(message);
    }

}
