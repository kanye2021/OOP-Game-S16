package controllers;
import views.SaveGameView;
import utilities.*;
import views.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by dyeung on 2/3/16.
 */
public class SaveGameController extends ViewController {


    public enum SaveOptions {
        SAVE_AND_EXIT("Save and Exit") {protected void selectOption() {doAction(SaveOptions.SAVE_AND_EXIT);};},
        SAVE("Just Save") {protected void selectOption() {doAction(SaveOptions.SAVE);};},
        BACK("Nevermind...") {protected void selectOption() {doAction(SaveOptions.BACK);};};

        private String text;

        protected abstract void selectOption();

        private SaveOptions(String s) {
            this.text = s;
        }
        protected SaveOptions previous() {
            if (this.ordinal() == 0) {
                return SaveOptions.values()[SaveOptions.values().length - 1];
            }
            else {
                return SaveOptions.values()[this.ordinal() - 1];
            }
        }
        protected SaveOptions next() {
            if (this.ordinal() == SaveOptions.values().length - 1) {
                return SaveOptions.values()[0];
            }
            else {
                return SaveOptions.values()[this.ordinal() + 1];
            }
        }
        public String getText() {
            return this.text;
        }
    }

    private SaveOptions selectedOption;

    public SaveOptions getSelectedOption() {
        return selectedOption;
    }

    public SaveGameController(View view){
        super(view);
        selectedOption = SaveOptions.SAVE_AND_EXIT;
    }

    private static void doAction(SaveOptions opt) {
        switch(opt) {
            case SAVE_AND_EXIT:
                System.out.println("SAVING GAME FROM SAVE GAME VIEW AND EXITING");
                Load_Save.getInstance().save();
                System.exit(0);
                break;
            case SAVE:
                System.out.println("SAVING GAME FROM SAVE GAME VIEW");
                Load_Save.getInstance().save();
                IOMediator.setActiveView(IOMediator.Views.GAME);
                break;
            case BACK:
                IOMediator.setActiveView(IOMediator.Views.GAME);
                break;
        }

    }

    //Remember that function has to go within keypress
    @Override
    public void handleKeyPress(int key){

        if (key == KeyEvent.VK_UP) {
            System.out.println("up");
            selectedOption = selectedOption.previous();

        }

        else if (key == KeyEvent.VK_DOWN) {
            System.out.println("down");
            selectedOption = selectedOption.next();
        }
        else if (key == KeyEvent.VK_ENTER) {
            selectedOption.selectOption();
        }

    }
    public void handleKeyRelease(int key){

    }
}
