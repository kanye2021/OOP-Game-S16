package controllers;
import views.SaveGameView;
import utilities.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by dyeung on 2/3/16.
 */
public class SaveGameController extends ViewController implements ActionListener {
    SaveGameView controllerView;
    public SaveGameController(SaveGameView v){
        controllerView = v;
    }
    public void actionPerformed(ActionEvent e){

    }
    //Remember that function has to go within keypress
    @Override
    public void handleKeyPress(int key){
        switch(key) {
            case KeyEvent.VK_ENTER :
                System.out.println("Selected to start saving");
                controllerView.startSaving();
                break;

        }
    }
    public void handleKeyRelease(int key){

    }
}
