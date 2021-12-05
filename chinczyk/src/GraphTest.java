import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

public class GraphTest extends JPanel{
    public void paintComponent(Graphics gee){
        super.paintComponent(gee);
        ImageIcon obraz = new ImageIcon("src\\plansza.png");
        //this jest używane jako lokacja zdjęcia
        obraz.paintIcon(this,gee,70,80);
    }
}
