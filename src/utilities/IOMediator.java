package utilities;

import controllers.StartMenuViewController;
import controllers.ViewController;
import models.Entity;
import models.Map;
import views.*;

import java.awt.*;
import java.awt.event.KeyEvent;


/**
 * Created by sergiopuleri on 2/1/16.
 */
public class IOMediator {

    public static Map map;
    public static Entity entity;

    // This represents all of the views that the utilities.IOMediator can see. the utilities.IOMediator acts as a MUX and goes through these
    // to modify the graphics and where the keyPresses go.

    // The code works as follows:
    // the single argument that the enum takes is a new view.
    // The view's render function is mapped to the enum's render such that START_MENU.render(g) -> views.StartMenuView->render(g)

    public static enum Views {

        START_MENU() {
            public void render(Graphics g) {
                //StartMenuView.getInstance().render(g);}
            }
                //public ViewController getController() {
              //  return StartMenuViewController.getInstance();
            //}
        },
        CREATE_GAME() {
            void render(Graphics g) { CreateNewGameView.render(g); }
        },
        AVATAR_CREATION() {
            void render(Graphics g) {
                AvatarCreationView.render(g);
            }
        },
        PAUSE() {
            void render(Graphics g) { PauseView.render(g); }
        },
        UNIMPLEMENTED() {
            void render(Graphics g) {

            }
        },

        DEATH() {
            void render(Graphics g) {  DeathView.render(g);  }
        },

        LOAD() {
            void render(Graphics g) {
                System.out.println("When is the view loaded");
                LoadGameView.render(g);
            }
        },
        SAVE() {
            void render(Graphics g) {
                SaveGameView.render(g);
            }
        }, // Not sure if having map and entity in constructor is "hacky" or not
        EXIT() {
            void render(Graphics g) {

            }
        },
        GAME() {
            void render(Graphics g) { GameView.render(g); }
        };

        abstract void render(Graphics g);

    }

    private static IOMediator ioMediator = new IOMediator();
    // If adding new views, add it as a static class property here.
    private static Views activeView;
    private static Views previousView;

    // A private Constructor prevents any other
    // class from instantiating.
    // If no view passed in,
    // Default view is views.StartMenuView
    private IOMediator() {
        activeView = Views.START_MENU;
    }

    // Static 'instance' method
    public static IOMediator getInstance() {
        return ioMediator;
    }


    // Other methods protected by singleton-ness
    public static void setActiveView(Views view) {
        previousView = activeView;
        activeView = view;
    }

    public static View getActiveView() {return activeView.getView(); }

    public static Views getPreviousView() {
        return previousView;
    }

    public static void keyPressed(KeyEvent e) {


        int key = e.getKeyCode();
        if (activeView != null) {
            // If we have an active view, send key press to its controller
            activeView.getView().getViewController().handleKeyPress(key);
        } else {
            System.out.println("no active view lol?");
        }
    }

    public void keyReleased(KeyEvent e) {

        //TODO: Send keyRelease to activeView.viewController
        int key = e.getKeyCode();

//        if (key == KeyEvent.VK_LEFT) {
//            System.out.println("Left released");
//        }
//
//        if (key == KeyEvent.VK_RIGHT) {
//            System.out.println("Right released");
//
//        }
//
//        if (key == KeyEvent.VK_UP) {
//            System.out.println("Up released");
//
//        }
//
//        if (key == KeyEvent.VK_DOWN) {
//            System.out.println("Down released");
//
//        }
    }
}
