import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class Tmp extends JFrame {
    JTextField text = new JTextField("Press Return", 40);

    public Tmp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        text.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Text=" + text.getText());
            }
        });

        getContentPane().add(text, "Center");
       // pack();
    }

    public static void main(String[] args) {
        new Tmp().setVisible(true);
    }
}