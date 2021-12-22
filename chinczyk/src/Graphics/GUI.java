package Graphics;

import Game.Board;
import Game.Pawn;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI {
    //lista pionków wizualnych
    // (red1234,blue1234,green1234,yellow1234)

    private ArrayList<ButtonPawn> visualPawns = new ArrayList<>();
    public void updateVisualPosition(Pawn target) {
        if(target == null) return; //przerwij, gdy nie ma pionka
        int arrayNumber = target.getNumber() + target.getTeam() * 4;
        visualPawns.get(arrayNumber).changePawnPos(target);
    }

    public GUI(Board boardRef){
        JFrame frame = new JFrame(){{
            //ikonka okna w lewym górnym rogu, w niczym pomaga, ale fajnie wygląda
            setIconImage(Toolkit.getDefaultToolkit().getImage("src\\images\\PawnR.png"));

            setLayout(null);

            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Chińczyk GUI");
        }};

        //ustawienie rozmiaru ekranu
        frame.setSize(556,579);//556x579 only board
        JLabel boardLabel = new JLabel(new ImageIcon("src\\images\\plansza.png"));
        boardLabel.setSize(540,540);

        //drużyny w zakresie 0-3
        for(int teamNo = 0; teamNo < 4; teamNo++){
            //numerowanie pionków, zakres 1-4 (wizualna reprezentacja, pionek 0 brzmi źle)
            int pawnNo = 1;
            //zmiana tych wartości w zależności od drużyny
            int startX = 0;
            int startY = 0;
            //pojaw 4x po 4 pionki na rantach mapy
            for(int x = 0; x < 2; x++)
                for(int y = 0; y < 2; y++){
                    switch(teamNo){
                        case 0: startX = x + 9; startY = y; break;      //9 to offset w kratkach
                        case 1: startX = x; startY = y + 9; break;
                        case 2: startX = x; startY = y; break;
                        case 3: startX = x + 9; startY = y + 9; break;
                    }
                    ButtonPawn vPawn = new ButtonPawn(teamNo,pawnNo,startX,startY);
                    boardLabel.add(vPawn);
                    visualPawns.add(vPawn);
                    pawnNo++;
                }
        }

        frame.add(boardLabel);
        frame.setVisible(true);
    }
}
