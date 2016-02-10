package views;

import controllers.ViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

/**
 * Created by sergiopuleri
 * on 2/1/16.
 */
public class StartMenuView extends View {

    private static final String START_MENU_IMAGE_LOCATION = "./src/res/start_menu/";

    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 32;

    //title
    private static final String TITLE = "Kanye 2020";
    private static final Font TITLE_FONT = new Font("Brush Script MT", Font.BOLD, 100);

    private static final int TITLE_BUTTON_MARGIN = (int) (B_HEIGHT * 0.15);

    //private static StartMenuView startMenuView = new StartMenuView();
    private StartMenuView() {}


    public static void render(Graphics g) {

        renderBackground(g);

        renderTitle(g);

        renderButtons(g);

        Toolkit.getDefaultToolkit().sync();
    }


    private static void renderBackground(Graphics g) {
        int x = 0;
        int y = 0;
        int width = B_WIDTH;
        int height = B_HEIGHT;

        //draw background
        ImageIcon im = new ImageIcon(START_MENU_IMAGE_LOCATION + "backGround.jpg");
        g.drawImage(im.getImage(),x,y,width,height,null);


        //draw back crowd
        im = new ImageIcon(START_MENU_IMAGE_LOCATION + "crowd.png");
        x = 0;
        y = B_HEIGHT*55/100;
        width = B_WIDTH;
        height = B_HEIGHT*2/5;
        g.drawImage(im.getImage(),x,y,width,height,null);

        //draw Kanye
        im = new ImageIcon(START_MENU_IMAGE_LOCATION + "Kanye.png");
        x = 0;
        y = B_HEIGHT*3/10;
        width = B_WIDTH*6/10;
        height = B_HEIGHT*6/10;
        g.drawImage(im.getImage(),x,y,width,height,null);

        //draw front crowd
        im = new ImageIcon(START_MENU_IMAGE_LOCATION + "crowd.png");
        x = 0;
        y = B_HEIGHT*6/10;
        width = B_WIDTH*15/10;
        height = B_HEIGHT*6/10;
        g.drawImage(im.getImage(),x,y,width,height,null);
    }

    static int pos = 0;
    static boolean increment = true;
    private static void renderTitle(Graphics g) {

        g.setFont(TITLE_FONT);
        FontMetrics fm = g.getFontMetrics();
        int titleWidth = fm.stringWidth(TITLE);
        int x = View.B_WIDTH / 2 - titleWidth / 2;
        int y = fm.getHeight();


        Graphics2D g2 = (Graphics2D)g;

        GradientPaint gradient = new GradientPaint (
                x, y, Color.RED,
                x + titleWidth -  pos, y, Color.blue);

        if(pos >= titleWidth){
            increment = false;
        } else if(pos <= -titleWidth*8/10){
            increment = true;
        }
        pos += 10*((increment)? 1: -1);


        g2.setPaint(gradient);

        g2.drawString(TITLE, x, y);
    }


    static private Color randomColor(){
        return new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
    }

    static private void renderButtons(Graphics g) {

        int start = g.getFontMetrics(TITLE_FONT).getHeight() + TITLE_BUTTON_MARGIN;

        g.setFont(VIEW_FONT);
        FontMetrics fm = g.getFontMetrics(VIEW_FONT);

        for (StartMenuViewController.MenuOptions option : StartMenuViewController.MenuOptions.values()) {

            Rectangle2D rectangle = fm.getStringBounds(option.toString(), g);

            int boxX = View.B_WIDTH / 2 - BUTTON_WIDTH / 2;
            int boxY = BUTTON_HEIGHT * option.ordinal() + start;
            int boxDX = BUTTON_WIDTH;
            int boxDY = BUTTON_HEIGHT;

            int stringX = View.B_WIDTH / 2 - (int) (rectangle.getWidth() / 2);
            int stringY = option.ordinal() * BUTTON_HEIGHT + (int) (rectangle.getHeight() / 2) + fm.getAscent() + start;

            Color primaryColor;
            Color secondaryColor;

            if (option == (StartMenuViewController.getInstance().getActiveItem())) {
                primaryColor = Color.WHITE;
                secondaryColor = Color.BLACK;

            } else {
                primaryColor = Color.BLACK;
                secondaryColor = Color.WHITE;

            }
            g.setColor(primaryColor);
            g.fillRect(boxX, boxY, boxDX, boxDY);
            g.setColor(secondaryColor);
            g.drawString(option.toString(), stringX, stringY);
        }
    }

    public static StartMenuViewController getController() {

        return StartMenuViewController.getInstance();

    }

    private static class StartMenuViewController extends ViewController {


        // This enum represents the menu options available on this screen. The setView() function maps to an individual view
        // such as utilities.IOMediator.Views.CREATE_GAME;
        public enum MenuOptions {
            CREATE_GAME("Create Game") {
                protected void setView() {
                    //IOMediator.setActiveView(IOMediator.Views.CREATE_GAME);
                }

                ;},

            LOAD_GAME("Load Game") {
                protected void setView() {
                    //IOMediator.setActiveView(IOMediator.Views.LOAD);
                }

                ;},

            //INVENTORY("Open Inventory") {protected void setView() {IOMediator.setActiveView(IOMediator.Views.INVENTORY);};},
            //PAUSE("Open Pause Menu") {protected void setView() {IOMediator.setActiveView(IOMediator.Views.PAUSE);};},

            EXIT_GAME("Exit Game") {
                protected void setView() {
                    System.exit(0);
                }
            };

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

        private static StartMenuViewController controller = new StartMenuViewController();
        private MenuOptions option;

        private StartMenuViewController() {
            option = MenuOptions.CREATE_GAME;
        }

        public static StartMenuViewController getInstance() {

            return controller;

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

            } else if (key == KeyEvent.VK_ENTER) {

                option.setView();

            }
        }

        @Override
        public void handleKeyRelease(int key) {

        }
    }


}
