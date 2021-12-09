//ta klasa może będzie używana, jak uda mi się zrobić wizualną reprezentację, póki co to nic nie daje
import javax.swing.*;
import java.awt.*;
import java.awt.Image;

public class MyPanel extends JPanel{

    Image image = Toolkit.getDefaultToolkit().getImage("src\\images\\plansza.png");


    MyPanel(){

        this.setPreferredSize(new Dimension(600,600));
        }
    //ta metoda wywołuje się sama
    public void paint(Graphics g){
        Graphics2D g2D = (Graphics2D) g;

        g2D.drawLine(0,0,540,540);
        g2D.drawImage(image,0,0,this);
    }
}
