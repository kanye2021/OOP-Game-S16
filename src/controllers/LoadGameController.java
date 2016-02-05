package controllers;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import models.Avatar;
import models.Entity;
import models.Map;
import utilities.IOMediator;
import utilities.Load_Save;
import utilities.NavigationMediator;
import views.GameView;
import views.LoadGameView;
import views.View;

import java.awt.event.KeyEvent;
import java.io.File;

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
        File folder = new File(saveFilePath);
        fileNames = folder.listFiles();
        myOption = 0; //Option 0 is always the first one
    }

    public int getActiveOptions(){
        return myOption;
    }

    public void handleKeyPress(int key){
        System.out.println("IN LGC");
        switch (key){
            case  KeyEvent.VK_UP:
                if (myOption > 0) {
                    myOption--;
                }
                break;
            case  KeyEvent.VK_DOWN:
                if (myOption < fileNames.length){
                    myOption++;
                }
                break;
            case  KeyEvent.VK_ENTER:
                loadGame();
                break;
        }
    }
    public void loadGame(){
        if (IOMediator.map == null) { //Case if there isn't a map and avatar created
            System.out.println("New Game!");
            Entity avatar = new Avatar();
            Map map = new Map();
            NavigationMediator nav = new NavigationMediator(map, avatar);
            IOMediator.entity = avatar;
            IOMediator.map = map;
            map.insertEntityAtLocation(avatar.getLocation()[0], avatar.getLocation()[1], avatar);
            GameView gameView = new GameView(map, avatar);
            IOMediator.Views.GAME.setView(gameView);
            Load_Save.getInstance().save(map, avatar);

            IOMediator.setActiveView(IOMediator.Views.GAME);
        }else {
            System.out.println(IOMediator.map.getMapHeight());
            System.out.println(IOMediator.entity.getLocation());
            Load_Save.getInstance().load(fileNames[myOption].getName()); //Going to grab information from XML
            IOMediator.setActiveView(IOMediator.Views.GAME);
        }
    }
    public void handleKeyRelease(int key){

    }
}
