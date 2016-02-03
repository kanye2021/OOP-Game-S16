import javax.swing.*;
import java.awt.*;

/**
 * Created by sergiopuleri on 2/1/16.
 */
public class Driver {

    // The main JFRAME
    private Display display;

    public Driver() {

        display = new Display();
//        init();
    }

    private void init() {

//        add(new Display());

//        setResizable(false);
//        pack();
//
//        setTitle("Collision");
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Driver ex = new Driver();
                ex.display.setVisible(true);
            }
        });
    }
}