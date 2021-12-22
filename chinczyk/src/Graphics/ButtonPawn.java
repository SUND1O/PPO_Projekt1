package Graphics;

import Game.Pawn;
import Game.PawnState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//wizualna reprezentacja pionka jako guzik
public class ButtonPawn extends JLabel {
    private ImageIcon Icon;
    private int[] startPosition = new int[2];

    //metoda do zmiany przez podanie "pozycji" grającego pionka
    public void changePawnPos(Pawn pawn){
        int Position = pawn.getPosition();
        switch(pawn.getCurrentState()){
            case FINISHED : switch((Position - 1) / 4){
                case 10 : changePawnPos(5,45 - Position); break; //41-44
                case 11 : changePawnPos(5,Position - 39); break; //45-48
                case 12 : changePawnPos(Position - 43,5); break; //49-52
                case 13 : changePawnPos(57 - Position,5); break; //53-56
            } break;
            case PLAYING : switch(Position / 2){
                case 0 :
                case 1 :
                case 2 : changePawnPos(6,Position - 1); break; //1-5
                case 3 :
                case 4 : changePawnPos(Position + 1,4); break; //6-9
                case 5 : changePawnPos(10,Position - 5); break; //10-11
                case 6 :
                case 7 : changePawnPos(21 - Position,6); break; //12-15
                case 8 :
                case 9 : changePawnPos(6,Position - 9); break; //16-19
                case 10 : changePawnPos(25 - Position, 10); break; //20-21
                case 11 :
                case 12 : changePawnPos(4, 31 - Position); break; //22-25
                case 13 :
                case 14 : changePawnPos(29 - Position, 6); break;//26-29
                case 15 : changePawnPos(0, 35 - Position); break; //30-31
                case 16 :
                case 17 : changePawnPos(Position - 31, 4); break;//32-35
                case 18 :
                case 19 : changePawnPos(4, 39 - Position); break;//36-39
                case 20 : changePawnPos(5, 0); break;//40-41
            } break;
            case STASHED : changePawnPos(startPosition[0],startPosition[1]); break;
        }
    }

    //metoda do zmiany przez podanie położenia
    public void changePawnPos(int x, int y){
        int xAxis = 9+x*48;//offset =9, multiplier =48, pixel perfect tiling
        int yAxis = 9+y*48;
        setLocation(xAxis,yAxis);
    }

    public ButtonPawn(int team,int number, int x, int y){

        //ustawianie innego obrazu w zależności od drużyny
        switch(team){
            case 0: Icon = new ImageIcon("src\\images\\PawnR.png");
                break;
            case 1: Icon = new ImageIcon("src\\images\\PawnB.png");
                break;
            case 2: Icon = new ImageIcon("src\\images\\PawnG.png");
                break;
            case 3: Icon = new ImageIcon("src\\images\\PawnY.png");
                break;
            default: Icon = new ImageIcon();
        }
        setIcon(Icon);

        setText("" + number);

        setForeground(Color.WHITE);
        setHorizontalTextPosition(JButton.CENTER);

        setBounds(0,0,42,42);
        startPosition[0] = x;
        startPosition[1] = y;
        changePawnPos(x,y);
    }
}
