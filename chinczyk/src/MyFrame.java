import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame{

    //panel zawiera obraz, tworzony jest po to, żeby nie przycinało góry obrazu
    MyPanel panel;

    MyFrame(){
/**/
        ImageIcon IconR = new ImageIcon("src\\images\\PawnR.png");
        ImageIcon IconG = new ImageIcon("src\\images\\PawnG.png");
        ImageIcon IconB = new ImageIcon("src\\images\\PawnB.png");
        ImageIcon IconY = new ImageIcon("src\\images\\PawnY.png");
        JButton button = new JButton(IconB);
        button.setText("1");
        button.setForeground(Color.WHITE);
        button.setHorizontalTextPosition(JButton.CENTER);
        //wymiary zdjęcia i pola: 42x42, szczelina między polami: 4
        button.setBounds(298,8,42,42);

        //usuwa tło z guzika pionka
        button.setOpaque(false);
        button.setContentAreaFilled(false);

        this.add(button);
/**/
        panel = new MyPanel();
        //button.setLocation(298,51);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        this.add(panel);

        this.setSize(580,581);//resizing powoduje, że pionki są widoczne

        this.setLocationRelativeTo(null);
        this.pack();

        this.setVisible(true);

        this.setSize(580,580);

        //po kliknięciu guzika zmienia pozycję
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                button.setLocation(298,51);
            }
        });


    }
}