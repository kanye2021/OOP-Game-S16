package controllers;

import com.sun.xml.internal.fastinfoset.sax.SystemIdResolver;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import models.Avatar;
import models.Entity;
import models.Map;
import utilities.IOMediator;
import utilities.Load_Save;
import utilities.NavigationMediator;
import views.Display;
import views.GameView;
import views.LoadGameView;
import views.View;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileFilter;

/**
 * Created by dyeung on 2/4/16.
 */
public class LoadGameController extends ViewController{
    LoadGameView loadView;
    File[] fileNames;
    private String saveFilePath ="src/res/save_files/";
    int myOption;
    public LoadGameController(LoadGameView lv){
        loadView = lv;
        loadNewFolder();
        myOption = 0;
    }
    public File[] loadNewFolder(){
        File folder = new File(saveFilePath);
        if (!folder.exists()) {
           folder.mkdir();
        }
        fileNames = folder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return !file.isHidden();
            }
        });
        return fileNames;
    }
    public int getActiveOptions(){
        return myOption;
    }

    public void handleKeyPress(int key){
        switch (key){
            case  KeyEvent.VK_UP:
                if (myOption > 0) {
                    myOption--;
                }
                break;
            case  KeyEvent.VK_DOWN:
                if (myOption < fileNames.length - 1){
                    myOption++;
                }
                break;
            case  KeyEvent.VK_ENTER:
                if (fileNames.length != 0) {
                    loadGame();
                }
                break;
            case  KeyEvent.VK_ESCAPE:
                IOMediator.setActiveView(IOMediator.getPreviousView());
                break;
        }
        if (checkFolderList()) {
            System.out.println("Call this");
            loadNewFolder();
            loadView.getNewFiles();
        }
    }
    public boolean checkFolderList(){
        File folder = new File(saveFilePath);
        if (folder.listFiles().length != fileNames.length){
            System.out.println("Mis match of files");
            return true;
        }else {
            return false;
        }
    }
    public void loadGame(){
        if (IOMediator.map == null) { //Case if there isn't a map and avatar created (IE coming from AvatarCreationView)
            System.out.println("New Game!");
            Entity avatar = new Avatar();
            Map map = new Map();
            NavigationMediator nav = new NavigationMediator(map, avatar);
            IOMediator.entity = avatar;
            IOMediator.map = map;
            GameView gameView = new GameView(map, avatar);
            IOMediator.Views.GAME.setView(gameView);
            // map.insertEntityAtLocation(avatar.getLocation()[0], avatar.getLocation()[1], avatar);
        }else {
            IOMediator.getInstance().map.removeEntityFromLocation(IOMediator.getInstance().entity.getLocation()[0], IOMediator.getInstance().entity.getLocation()[1]);
            //Needs to remove the previous entity
        }

        Load_Save.getInstance().load(fileNames[myOption].getName()); //Going to grab information from XML
        IOMediator.setActiveView(IOMediator.Views.GAME);
        Display.getInstance().repaint();

    }

    public void handleKeyRelease(int key){

    }
}
