import javax.swing.*;
import java.awt.event.*;


/**
 * Created by Bradley on 2/1/2016.
 */
public class GameController extends ViewController {
    private NavigationMediator navMediator;

    public GameController(View view, NavigationMediator navigationMediator){
        super(view);
    	this.navMediator = navigationMediator;
        //Old stuff
//        timer = new Timer(15, this);
//        timer.start();
//        initController();
    }

    
    @Override
    public void handleKeyPress(int key) {
        System.out.println("KEYPRESSED");
        switch(key){
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
            case KeyEvent.VK_I:
            	IOMediator.setActiveView(IOMediator.Views.INVENTORY);
            	break;
                
        }
    }


	@Override
	void handleKeyRelease(int key) {
		
	}
}

