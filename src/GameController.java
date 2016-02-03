import javax.swing.*;
import java.awt.event.*;


/**
 * Created by Bradley on 2/1/2016.
 */
public class GameController implements KeyListener{
    //private Timer timer;
    private NavigationMediator navMediator;

    public GameController(NavigationMediator navigationMediator){
        this.navMediator = navigationMediator;
        //Old stuff
//        timer = new Timer(15, this);
//        timer.start();
//        initController();
    }

    //Main Key press that we will use
    @Override public void keyReleased(KeyEvent e) {
        GameKeyListener(e);
    }
    @Override public void keyTyped(KeyEvent e) {
        //System.out.println(e.paramString());
    }
    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println(e.paramString());
    }

    private void GameKeyListener(KeyEvent e) {
        System.out.println("KEYPRESSED");
        switch(e.getKeyCode()){
            case KeyEvent.VK_NUMPAD1:
                navMediator.requestMovement("SW");
                break;
            case KeyEvent.VK_NUMPAD2:
                navMediator.requestMovement("S");
                break;
            case KeyEvent.VK_NUMPAD3:
                navMediator.requestMovement("SE");
                break;
            case KeyEvent.VK_NUMPAD6:
                navMediator.requestMovement("E");
                break;
            case KeyEvent.VK_NUMPAD9:
                navMediator.requestMovement("NE");
                break;
            case KeyEvent.VK_NUMPAD8:
                navMediator.requestMovement("N");
                break;
            case KeyEvent.VK_NUMPAD7:
                navMediator.requestMovement("NW");
                break;
            case KeyEvent.VK_NUMPAD4:
                navMediator.requestMovement("W");
                break;
        }
    }
}

