package controllers;

import utilities.IOMediator;
import utilities.Load_Save;
import views.CreateNewGameView;
import views.Display;
import views.View;

import java.awt.event.KeyEvent;

/**
 * Created by sergiopuleri on 2/2/16.
 */
public class CreateNewGameViewController extends ViewController {


    public CreateNewGameViewController() {
        super();
    }

    public CreateNewGameViewController(View view) {
        super(view);
    }


    @Override
    public void handleKeyPress(int key) {

        if (key == KeyEvent.VK_ENTER) {
            // Get handle to our view.
            // Need to cast as a views.CreateNewGameView
            CreateNewGameView createNewGameView = ((CreateNewGameView)view);

            System.out.println("enter pressed FROM Create new VC");
            String getValue = createNewGameView.getSaveStateName().getText();
            System.out.println("THE SAVE STATE NAME IS: " + getValue);

            Load_Save.getInstance().setCurrentFileName(getValue);

            // Remove the JTextField from the views.Display's JPanel
            Display.getInstance().remove(createNewGameView.getSaveStateName());
            // Then proceed to avatar create view
            IOMediator.getInstance().setActiveView(IOMediator.Views.AVATAR_CREATION);

        }
        
        else {
//            System.out.println("invalid key press FROM CREATE NEW VC");
        }

    }

    @Override
    public void handleKeyRelease(int key) {

    }
}


