import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * Created by sergiopuleri on 2/1/16.
 */
public class IOMediator {

    public View activeView;

    // If no view passed in,
    // Default view is StartMenuView
    public IOMediator() {
        System.out.println("IN IOMediator constructor");
        activeView = new StartMenuView();
    }
    public IOMediator(View activeView) {
        this.activeView = activeView;

    }



    public void keyPressed(KeyEvent e) {


        int key = e.getKeyCode();
        if (activeView != null) {
            // If we have an active view, send key press to its controller
            activeView.viewController.handleKeyPress(key);
        }

        else {
            if (key == KeyEvent.VK_SPACE) {
                System.out.println("Space pressed");
            }

            if (key == KeyEvent.VK_LEFT) {
                System.out.println("Left pressed");
            }

            if (key == KeyEvent.VK_RIGHT) {
                System.out.println("Right pressed");
            }

            if (key == KeyEvent.VK_UP) {
                System.out.println("Up pressed");
            }

            if (key == KeyEvent.VK_DOWN) {
                System.out.println("Down pressed");
            }
        }
    }


    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            System.out.println("Left released");
        }

        if (key == KeyEvent.VK_RIGHT) {
            System.out.println("Right released");

        }

        if (key == KeyEvent.VK_UP) {
            System.out.println("Up released");

        }

        if (key == KeyEvent.VK_DOWN) {
            System.out.println("Down released");

        }
    }



}
