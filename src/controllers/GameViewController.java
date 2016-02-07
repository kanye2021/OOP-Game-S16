package controllers;

import utilities.IOMediator;
import utilities.Load_Save;
import utilities.NavigationMediator;
import views.GameView;
import views.View;

import java.awt.event.KeyEvent;


/**
 * Created by Bradley on 2/1/2016.
 */
public class GameViewController extends ViewController {
    private NavigationMediator navMediator;

    public GameViewController(View view, NavigationMediator navigationMediator){
        super(view);
        this.navMediator = navigationMediator;
    }

    @Override
    public void handleKeyPress(int key){
        switch(key){
            case KeyEvent.VK_RIGHT:
                navMediator.requestMovement("E");
                break;
            case KeyEvent.VK_LEFT:
                navMediator.requestMovement("W");
                break;
            case KeyEvent.VK_UP:
                navMediator.requestMovement("N");
                break;
            case KeyEvent.VK_DOWN:
                navMediator.requestMovement("S");
                break;
            case KeyEvent.VK_Q:
                navMediator.requestMovement("NW");
                break;
            case KeyEvent.VK_E:
                navMediator.requestMovement("NE");
                break;
            case KeyEvent.VK_Z:
                navMediator.requestMovement("SW");
                break;
            case KeyEvent.VK_C:
                navMediator.requestMovement("SE");
            case KeyEvent.VK_R:
                // Tell GameView to render EquippedItemsView over it
                ((GameView)IOMediator.Views.GAME.getView()).setShowEquippedItems(true);
                break;
            case KeyEvent.VK_I:
            	IOMediator.setActiveView(IOMediator.Views.INVENTORY);
            	break;
            case KeyEvent.VK_ESCAPE:
            	IOMediator.setActiveView(IOMediator.Views.PAUSE);
            	break;
//            case KeyEvent.VK_S:
//                navMediator.save();
//                break;

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

    @Override
    public void handleKeyRelease(int key){

    }

}

