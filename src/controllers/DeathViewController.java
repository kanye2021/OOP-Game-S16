package controllers;

/**
 * Created by Matthew on 2/6/2016.
 */

import utilities.IOMediator;
import views.View;

import java.awt.event.KeyEvent;


public class DeathViewController extends ViewController {

    public enum MenuOptions {

        MAIN_MENU("Main Menu") {
            protected void setView() {
                IOMediator.setActiveView(IOMediator.Views.START_MENU);
            }

            ;},
        EXIT_GAME("Exit Game") {
            protected void setView() {
                System.exit(0);
            }

            ;};

        private String s;

        protected abstract void setView();

        private MenuOptions(String s) {
            this.s = s;
        }

        protected MenuOptions previous() {
            if (this.ordinal() == 0) {
                return MenuOptions.values()[MenuOptions.values().length - 1];
            } else {
                return MenuOptions.values()[this.ordinal() - 1];
            }

        }

        protected MenuOptions next() {

            if (this.ordinal() == MenuOptions.values().length - 1) {

                return MenuOptions.values()[0];

            } else {

                return MenuOptions.values()[this.ordinal() + 1];

            }

        }

        public String toString() {

            return s;

        }

    }

    MenuOptions option;

    public DeathViewController(View view) {
        super(view);
        option = MenuOptions.MAIN_MENU;
    }

    public MenuOptions getActiveItem() {

        return option;

    }

    @Override
    public void handleKeyPress(int key) {

        if (key == KeyEvent.VK_UP) {
            option = option.previous();
        } else if (key == KeyEvent.VK_DOWN) {
            option = option.next();
        } else if (key == KeyEvent.VK_ESCAPE) {
            IOMediator.setActiveView(IOMediator.Views.START_MENU);
        } else if (key == KeyEvent.VK_ENTER) {
            option.setView();
        }

    }

    @Override
    public void handleKeyRelease(int key) {

    }

}
