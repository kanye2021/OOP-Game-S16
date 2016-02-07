import views.Display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by sergiopuleri on 2/1/16.
 */
public class RunGame extends JFrame implements ActionListener {

    private Timer timer;
    private final int DELAY = 15;

    @Override
    public void setExtendedState(int state) {
        super.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public RunGame() {
        init();
    }

    private void init() {

        add(Display.getInstance());
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
        //Display.getInstance().repaint();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                RunGame ex = new RunGame();
                ex.setVisible(true);
            }
        });
    }
}