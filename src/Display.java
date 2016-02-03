import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by sergiopuleri on 2/1/16.
 */
public class Display extends JFrame implements ActionListener {


    private boolean ingame;
    private Timer timer;

    private final int DELAY = 15;

    private IOMediator ioMediator;


    public Display() {

        initDisplay();
    }

    private void initDisplay() {

        System.out.println("IN Display constructor");
        //init ioMediator
        ioMediator = new IOMediator();
        // Set up application wide key listener
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());


        // add the active view (JPANEL) to this JFRAME
        add(ioMediator.activeView);
        setResizable(false);
        pack();

        setTitle("RPG GAME");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        timer = new Timer(DELAY, this);
        timer.start();
    }

    // CALLED EVERY DELAY MS
    @Override
    public void actionPerformed(ActionEvent e) {

//        inGame();
//
//        updateCraft();
//        updateMissiles();
//        updateAliens();
//
//        checkCollisions();

        // Every refresh, tell the active view to refresh itself
        ioMediator.activeView.repaint();
    }

//    @Override
//    public void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//        renderAView(ioMediator.activeView, g);
//        Toolkit.getDefaultToolkit().sync();
//    }

    public void renderAView(View v, Graphics g) {
        // IOMediator's active view renders itself
        v.render(g);
    }

    // Global key listener. Application wide. Not on just a focused compenent.
    // http://stackoverflow.com/questions/286727/unresponsive-keylistener-for-jframe
    private class MyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                ioMediator.keyPressed(e);
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                ioMediator.keyReleased(e);
            } else if (e.getID() == KeyEvent.KEY_TYPED) {
                System.out.println("key typed??");
            }
            return false;
        }
    }

}
