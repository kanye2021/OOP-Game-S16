package views;

import utilities.IOMediator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by sergiopuleri on 2/1/16.
 */
public class Display extends JPanel {

    private static Display display = new Display();

    public Display() {
        initDisplay();
    }

    public static Display getInstance() {
        return display;
    }

    private void initDisplay() {
        // Init JPanel stuff
        setFocusable(true);
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(View.B_WIDTH, View.B_HEIGHT));


        // Set up application wide key listener
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
    }


    @Override
    public void paintComponent(Graphics g) {
        // 	super.paintComponent(g);
        // Every refresh, tell the active view to refresh itself
        IOMediator.getActiveView().render(g);
    }


    // Global key listener. Application wide. Not on just a focused compenent.
    // http://stackoverflow.com/questions/286727/unresponsive-keylistener-for-jframe
    private class MyDispatcher implements KeyEventDispatcher {
        IOMediator ioMediator = IOMediator.getInstance();

        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                IOMediator.keyPressed(e);
            } else if (e.getID() == KeyEvent.KEY_RELEASED) {
                ioMediator.keyReleased(e);
            } else if (e.getID() == KeyEvent.KEY_TYPED) {
//                System.out.println("key typed??");
            }
           // Display.getInstance().repaint();

            return false;
        }
    }

}
