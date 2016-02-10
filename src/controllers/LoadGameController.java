package controllers;

import models.Avatar;
import models.Entity;
import models.Map;
import utilities.IOMediator;
import utilities.Load_Save;
import utilities.NavigationMediator;
import views.Display;
import views.GameView;
import views.LoadGameView;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by dyeung on 2/4/16.
 */
public class LoadGameController extends ViewController {
    LoadGameView loadView;
    File[] fileNames;
    private String saveFilePath = "./src/res/save_files/";
    int myOption;

    public LoadGameController(LoadGameView lv) {
        loadView = lv;
        saveFilePath = saveFilePath.replaceAll("\\\\|/", "\\" + System.getProperty("file.separator"));
        loadNewFolder();
        myOption = 0;
        saveFilePath = saveFilePath.replaceAll("\\\\|/", "\\" + System.getProperty("file.separator"));
    }

    public File[] loadNewFolder() {
        File folder = new File(saveFilePath);
        if (!folder.exists()) {
            folder.mkdir();
        }

        File[] f = folder.listFiles();
        // Filter files.
        ArrayList<File> filteredFiles = new ArrayList<File>();
        for (int i = 0; i < f.length; i++) {
            File current = f[i];
            // If not .DS_Store and contains .xml use it
            if (!current.getName().equals(".DS_Store") && current.getName().contains(".xml")) {
                filteredFiles.add(current);
            }
        }
        File[] files = new File[filteredFiles.size()];
        fileNames = filteredFiles.toArray(files);
        return fileNames;
    }

    public int getActiveOptions() {
        return myOption;
    }

    public void handleKeyPress(int key) {
        switch (key) {
            case KeyEvent.VK_UP:
                if (myOption > 0) {
                    myOption--;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (myOption < fileNames.length - 1) {
                    myOption++;
                }
                break;
            case KeyEvent.VK_ENTER:
                if (fileNames.length != 0) {
                    loadGame();
                }
                break;
            case KeyEvent.VK_ESCAPE:
                IOMediator.setActiveView(IOMediator.getPreviousView());
                break;
        }
        //Needs to check the number of files just in case there is a new saved file in the folder
        if (checkFolderList()) {
            System.out.println("Call this");
            loadNewFolder();
            loadView.getNewFiles();
        }
    }

    public File[] getFileNames() {
        return fileNames;
    }

    public boolean checkFolderList() {
        File folder = new File(saveFilePath);
        if (folder.listFiles().length != fileNames.length) {
            System.out.println("Mis match of files");
            return true;
        } else {
            return false;
        }
    }

    public void loadGame() {
        if (IOMediator.map == null) { //Case if there isn't a map and avatar created (IE coming from AvatarCreationView)
            System.out.println("New Game!");
            Entity avatar = new Avatar();
            Map map = new Map();
            NavigationMediator nav = new NavigationMediator(map, avatar);
            IOMediator.avatar = avatar;
            IOMediator.map = map;

            // Create the game view
            GameView gameView = new GameView(map, avatar);
            IOMediator.Views.GAME.setView(gameView);
            // map.insertEntityAtLocation(avatar.getLocation()[0], avatar.getLocation()[1], avatar);
        } else {
            IOMediator.getInstance().map.removeEntityFromLocation(IOMediator.getInstance().avatar.getLocation().x, IOMediator.getInstance().avatar.getLocation().y);
            //Needs to remove the previous avatar
        }

        Load_Save.getInstance().load(fileNames[myOption].getName()); //Going to grab information from XML
        IOMediator.setActiveView(IOMediator.Views.GAME);
        Display.getInstance().repaint();

    }

    public void handleKeyRelease(int key) {

    }
}
