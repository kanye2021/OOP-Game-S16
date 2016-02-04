package controllers;
import views.SaveGameView;
import utilities.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by dyeung on 2/3/16.
 */
public class SaveGameController extends ViewController {
    SaveGameView saveView;
    public SaveGameController(SaveGameView v){
        saveView = v;
//        saveView.getTextField().addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("Text=" + saveView.getTextField().getText());
//            }
//        });
    }

    //Remember that function has to go within keypress
    @Override
    public void handleKeyPress(int key){
        switch(key) {
            case KeyEvent.VK_ENTER :

                saveView.startSaving();
                break;

        }
    }
    public void handleKeyRelease(int key){

    }
}
