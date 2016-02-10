package views;

//import controllers.PauseViewController;

import controllers.ViewController;
import utilities.IOMediator;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Created by Austin on 2/1/16.
 */
public class PauseView extends View {

    private static final int ITEM_HEIGHT = 50;

    private static  PauseView pauseView = new PauseView();
    private PauseView() {}



    public static void init(){

    }


    public static void render(Graphics g) {

        BufferedImage overImage = new BufferedImage(View.B_WIDTH, View.B_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g2 = overImage.getGraphics();

        int itemOffset = 1;

        FontMetrics fm = g2.getFontMetrics(VIEW_FONT);
        g2.setFont(VIEW_FONT);

        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, B_WIDTH - 1, B_HEIGHT - 1);
        g2.setColor(Color.BLACK);
        g2.fillRect(10, 10, B_WIDTH - 21, B_HEIGHT - 21);

        String titleString = "Game Paused";
        Rectangle2D titleRectangle = fm.getStringBounds(titleString, g2);
        int titleStringX = (int) (B_WIDTH / 2 - titleRectangle.getWidth() / 2);
        int titleStringY = (int) (0 * ITEM_HEIGHT + titleRectangle.getHeight() / 2 + fm.getAscent() + 11);

        g2.setColor(Color.WHITE);
        g2.drawString(titleString, titleStringX, titleStringY);
        g2.drawRect(0, 0, B_WIDTH, ITEM_HEIGHT);

        for (PauseViewController.MenuOptions option : PauseViewController.MenuOptions.values()) {

            String string1 = option.toString();
            Rectangle2D rectangle = fm.getStringBounds(string1, g2);

            int boxX = 25;
            int boxY = (option.ordinal() + itemOffset) * ITEM_HEIGHT + 11;
            int boxDX = B_WIDTH - 52;
            int boxDY = ITEM_HEIGHT;
            int stringX = View.B_WIDTH / 2 - (int) (rectangle.getWidth() / 2);
            int stringY = (option.ordinal() + itemOffset) * ITEM_HEIGHT + (int) (rectangle.getHeight()) + fm.getAscent();

            Color primaryColor;
            Color secondaryColor;

            if (option == (PauseViewController.getInstance().getActiveItem())) {

                primaryColor = Color.WHITE;
                secondaryColor = Color.BLACK;

            } else {

                primaryColor = Color.BLACK;
                secondaryColor = Color.WHITE;

            }

            g2.setColor(primaryColor);
            g2.fillRect(boxX, boxY, boxDX, boxDY);
            g2.setColor(secondaryColor);
            g2.drawString(string1, stringX, stringY);
            //g2.drawRect(0, (option.ordinal() + itemOffset) * ITEM_HEIGHT, View.B_WIDTH - 1, ITEM_HEIGHT);

        }

        g.drawImage(overImage, (int) (View.B_WIDTH * 0.1), (int) (View.B_HEIGHT * 0.1), (int) (View.B_WIDTH * 0.8), (int) (View.B_HEIGHT * 0.8), null);

        public static ViewController getController() {

            return PauseViewController.getInstance();

        }

    }

    static class PauseViewController extends ViewController {

        // This enum represents the menu options available on this screen. The setView() function maps to an individual view
        // such as utilities.IOMediator.Views.GAME;

        private enum MenuOptions {
            RESUME("Resume Game") {
                protected void setView() {
                    IOMediator.setActiveView(IOMediator.Views.GAME);
                }

                ;},
            //		OPTIONS("Options") {protected void setView() {IOMediator.setActiveView(IOMediator.Views.UNIMPLEMENTED);};},
            LOAD_GAME("Load Game") {
                protected void setView() {
                    IOMediator.setActiveView(IOMediator.Views.LOAD);
                }

                ;},
            SAVE_GAME("Save Game") {
                protected void setView() {
                    IOMediator.setActiveView(IOMediator.Views.SAVE);
                }

                ;},
            DAVE("Dave") {
                protected void setView() {
                    IOMediator.setActiveView(IOMediator.Views.START_MENU);
                }

                ;},
            EXIT_GAME("Exit Game") {
                protected void setView() {
                    IOMediator.setActiveView(IOMediator.Views.START_MENU);
                }

                ;};

            private String s;

            protected abstract void setView();

            MenuOptions(String s) {

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

        private static MenuOptions option;
        private static PauseViewController controller = new PauseViewController();

        private PauseViewController() {
            option = MenuOptions.RESUME;
        }

        public static PauseViewController getInstance() {

            return controller;

        }

        protected MenuOptions getActiveItem() {

            return option;

        }

        @Override
        public void handleKeyPress(int key) {

            if (key == KeyEvent.VK_UP) {
                option = option.previous();
            } else if (key == KeyEvent.VK_DOWN) {
                option = option.next();
            } else if (key == KeyEvent.VK_ESCAPE) {
                IOMediator.setActiveView(IOMediator.Views.GAME);
            } else if (key == KeyEvent.VK_ENTER) {
                option.setView();
            }

        }

        @Override
        public void handleKeyRelease(int key) {

        }

    }

}
