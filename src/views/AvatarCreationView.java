package views;

import controllers.ViewController;
import models.Avatar;
import models.Entity;
import models.Map;
import utilities.IOMediator;
import utilities.Load_Save;
import utilities.NavigationMediator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

/**
 * Created by sergiopuleri on 2/1/16.
 */
public class AvatarCreationView extends View {

    private static final String AVATAR_CREATE_TEXT = "Please select an Occupation";
    private static String arrowFilePath = "./src/res/arrow.png";

    private static AvatarCreationView avatarCreationView  = new AvatarCreationView();
    private AvatarCreationView() {};

    public static void init(){
        arrowFilePath = arrowFilePath.replaceAll("\\\\|/", "\\" + System.getProperty("file.separator"));
    }

    public static void render(Graphics g) {
        clear(g); // clear the background


        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 100, 100);

        // Text
        Font small = new Font("Helvetica", Font.ITALIC, 14);
        Font title = new Font("Courier New", Font.BOLD, 28);
        FontMetrics fm = g.getFontMetrics(title);
        g.setColor(Color.white);
        g.setFont(title);
        Rectangle2D r1 = fm.getStringBounds(AVATAR_CREATE_TEXT, g);
        int x = (View.B_WIDTH - (int) r1.getWidth()) / 2;
        int y = (View.B_HEIGHT - (int) r1.getHeight()) / 7 + fm.getAscent();
        g.drawString(AVATAR_CREATE_TEXT, x, y);


        // Paint Occupation Titles + Descriptions
        int stringX = 100;
        int arrow_x_offset = stringX;
        int stringY = View.B_HEIGHT / 3;
        int stringY2 = 0;
        int prevY = 0;
        for (AvatarCreationViewController.OccupationOptions occupation : AvatarCreationViewController.OccupationOptions.values()) {

            fm = g.getFontMetrics(title);
            Rectangle2D rectangle1 = fm.getStringBounds(occupation.getText(), g);
            fm = g.getFontMetrics(small);
            Rectangle2D rectangle2 = fm.getStringBounds(occupation.getDescription(), g);


            stringY = prevY != 0 ? prevY + ((int) rectangle2.getHeight()) + 50 : stringY;
            stringY2 = ((int) (rectangle1.getHeight()) + fm.getAscent()) + stringY;

            Color primaryColor = Color.white;

            if (occupation == (AvatarCreationViewController.getInstance()).getSelectedOccupation()) {
                // Drawing Arrow next to selection
                ImageIcon ii = new ImageIcon(arrowFilePath);
                Image arrow = ii.getImage();
                arrow_x_offset -= arrow.getWidth(null) + 10;
                g.drawImage(arrow, arrow_x_offset, stringY, Display.getInstance());
            }

            g.setColor(primaryColor);
            g.setFont(title);
            g.drawString(occupation.getText(), stringX, stringY);
            g.setFont(small);
            g.drawString(occupation.getDescription(), stringX, stringY2);

            prevY = stringY2;


        }

        Toolkit.getDefaultToolkit().sync();

    }

    public static AvatarCreationViewController getController() {

        return AvatarCreationViewController.getInstance();

    }

    private static class AvatarCreationViewController extends ViewController {


        // This enum represents the menu options available on this screen. The setView() function maps to an individual view
        // such as utilities.IOMediator.Views.CREATE_GAME;
        public enum OccupationOptions {
            SMASHER("Smasher", "specialized in hand-to-hand combat") {
                protected void selectOccupation() {
                    createAvatarAndSaveGame("smasher");
                }

                ;},
            SUMMONER("Summoner", "specialized in spell-casting") {
                protected void selectOccupation() {
                    createAvatarAndSaveGame("summoner");
                }

                ;},
            SNEAK("Sneak", "specialized in ranged weapons, evading detection, finding/removing traps") {
                protected void selectOccupation() {
                    createAvatarAndSaveGame("sneak");
                }

                ;};

            private String text;
            private String description;

            protected abstract void selectOccupation();

            private OccupationOptions(String text, String description) {
                this.text = text;
                this.description = description;
            }

            protected OccupationOptions previous() {
                if (this.ordinal() == 0) {
                    return OccupationOptions.values()[OccupationOptions.values().length - 1];
                } else {
                    return OccupationOptions.values()[this.ordinal() - 1];
                }
            }

            protected OccupationOptions next() {
                if (this.ordinal() == OccupationOptions.values().length - 1) {
                    return OccupationOptions.values()[0];
                } else {
                    return OccupationOptions.values()[this.ordinal() + 1];
                }

            }

            public String getText() {
                return text;
            }

            public String getDescription() {
                return description;
            }

        }

        private OccupationOptions selectedOccupation;
        private static AvatarCreationViewController controller = new AvatarCreationViewController();

        public static AvatarCreationViewController getInstance() {

            return controller;

        }

        public OccupationOptions getSelectedOccupation() {
            return selectedOccupation;
        }

        public AvatarCreationViewController() {
            selectedOccupation = OccupationOptions.SMASHER;
        }

        public static void createAvatarAndSaveGame(String occupation) {
            // Wanna ask if ppl think this is good.
            System.out.println("Making an avatar of occupation: " + occupation);
            Entity avatar = new Avatar(occupation);
            Map map = new Map();
            NavigationMediator nav = new NavigationMediator(map, avatar);
            IOMediator.entity = avatar;
            IOMediator.map = map;
            map.insertEntityAtLocation(avatar.getLocation()[0], avatar.getLocation()[1], avatar);

            // TODO: DELETE
//        // Create inventory view
//        InventoryView inventoryView = new InventoryView(map, avatar);
//        IOMediator.Views.INVENTORY.setView(inventoryView);

            //GameView gameView = new GameView(map, avatar);
            //IOMediator.Views.GAME.setView(gameView);
            IOMediator.setActiveView(IOMediator.Views.GAME);

            // Saving
            Load_Save.getInstance().setGameMap(map);
            Load_Save.getInstance().setAvatar(avatar);
            Load_Save.getInstance().save();
        }


        @Override
        public void handleKeyPress(int key) {

            if (key == KeyEvent.VK_UP) {
                System.out.println("Up pressed FROM SMVC");
                selectedOccupation = selectedOccupation.previous();
            } else if (key == KeyEvent.VK_DOWN) {
                System.out.println("Down pressed FROM SMVC");
                selectedOccupation = selectedOccupation.next();
            }

            if (key == KeyEvent.VK_ENTER) {

                System.out.println("enter pressed FROM Avatar new VC");
                // This func calls "createAvatar
                // which needs to be implemented
                selectedOccupation.selectOccupation();


            } else {
//            System.out.println("invalid key press FROM CREATE NEW VC");
            }

        }

        @Override
        public void handleKeyRelease(int key) {

        }
    }

}
