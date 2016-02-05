package controllers;

import models.Avatar;
import models.Entity;
import models.Map;
import utilities.IOMediator;
import utilities.Load_Save;
import utilities.NavigationMediator;
import views.CreateNewGameView;
import views.Display;
import views.GameView;
import views.View;

import java.awt.event.KeyEvent;

/**
 * Created by sergiopuleri on 2/3/16.
 */
public class AvatarCreationViewController extends ViewController {


    // This enum represents the menu options available on this screen. The setView() function maps to an individual view
    // such as utilities.IOMediator.Views.CREATE_GAME;
    public enum OccupationOptions {
        SMASHER("Smasher", "specialized in hand-to-hand combat") {protected void selectOccupation() {createAvatarAndSaveGame("smasher");};},
        SUMMONER("Summoner", "specialized in spell-casting") {protected void selectOccupation() {createAvatarAndSaveGame("summoner");};},
        SNEAK("Sneak", "specialized in ranged weapons, evading detection, finding/removing traps") {protected void selectOccupation() {
            createAvatarAndSaveGame("sneak");};};

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
            }
            else {
                return OccupationOptions.values()[this.ordinal() - 1];
            }
        }

        protected OccupationOptions next() {
            if (this.ordinal() == OccupationOptions.values().length - 1) {
                return OccupationOptions.values()[0];
            }
            else {
                return OccupationOptions.values()[this.ordinal() + 1];
            }

        }

        public String getText() { return text; }
        public String getDescription() { return description; }

    }
    private OccupationOptions selectedOccupation;

    public OccupationOptions getSelectedOccupation() {
        return selectedOccupation;
    }
    public AvatarCreationViewController() {
        super();
    }

    public AvatarCreationViewController(View view) {
        super(view);
        selectedOccupation = OccupationOptions.SMASHER;

    }

    public static void createAvatarAndSaveGame(String occupation) {
        // Wanna ask if ppl think this is good.
        System.out.println("Makin an avatar of occupation: " + occupation);
        Entity avatar = new Avatar(occupation);
        Map map = new Map();
        NavigationMediator nav = new NavigationMediator(map, avatar);
        IOMediator.entity = avatar;
        IOMediator.map = map;
        map.insertEntityAtLocation(avatar.getLocation()[0], avatar.getLocation()[1], avatar);
        GameView gameView = new GameView(map, avatar);
        IOMediator.Views.GAME.setView(gameView);
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
        }

        else if (key == KeyEvent.VK_DOWN) {
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