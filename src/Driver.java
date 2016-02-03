import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sergiopuleri on 2/1/16.
 */
public class Driver extends JFrame implements ActionListener {

    private Timer timer;
    private final int DELAY = 15;

    private Display display;

    public Driver() {
        init();
    }

    private void init() {

        display = new Display();
        add(display);
        setResizable(false);
        pack();

        setTitle("RPG GAME");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Do timer
        timer = new Timer(DELAY, this);
        timer.start();
    }

    // CALLED EVERY DELAY MS
    @Override
    public void actionPerformed(ActionEvent e) {
        display.repaint();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Driver ex = new Driver();
                ex.setVisible(true);
            }
        });
    }
}