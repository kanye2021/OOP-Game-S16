package views;

import controllers.ViewController;
import models.Avatar;
import models.Entity;
import models.Map;
import utilities.IOMediator;
import utilities.Load_Save;
import utilities.NavigationMediator;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by dyeung on 2/4/16.
 */
public class LoadGameView extends View {
    //----------View Design stuff --------
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 50;
    private static final int START_POSITION = 100;
    //--------File Path stuff -------

    public static void render(Graphics g) {
        renderFileButtons(g);
    }

    private static void renderFileButtons(Graphics g) {

        g.setFont(VIEW_FONT);
        FontMetrics fm = g.getFontMetrics(VIEW_FONT);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);

        g2d.setFont(new Font("Courier New", Font.PLAIN, 13));

        //no saved games to list in for loop below
        if (LoadGameController.getInstance().getFileNames().length == 0) {
            String message = "No Saved Games";
            Rectangle2D rectangle = fm.getStringBounds(message, g);

            int boxX = View.B_WIDTH / 2 - BUTTON_WIDTH / 2;
            int boxY = BUTTON_HEIGHT + START_POSITION;
            int boxDX = BUTTON_WIDTH;
            int boxDY = BUTTON_HEIGHT;

            int stringX = View.B_WIDTH / 2 - (int) (rectangle.getWidth() / 2);
            int stringY = BUTTON_HEIGHT + (int) (rectangle.getHeight() / 2) + fm.getAscent() + START_POSITION;

            Color primaryColor;
            Color secondaryColor;
            primaryColor = Color.WHITE;
            secondaryColor = Color.BLACK;


            g.setColor(primaryColor);
            g.fillRect(boxX, boxY, boxDX, boxDY);
            g.setColor(secondaryColor);
            g.drawString(message, stringX, stringY);
        } else {

            // Draw title
            Font titleFont = new Font("Courier New", Font.PLAIN, 28);
            g.setFont(titleFont);
            fm = g.getFontMetrics(titleFont);
            String titleString = "Pick the save file you want to load";
            Rectangle2D rec = fm.getStringBounds(titleString, g);
            int xTitle = (View.B_WIDTH / 2 - (int) (rec.getWidth() / 2));
            int yTitle = View.B_HEIGHT / 6 + (int) (rec.getHeight() / 2) + fm.getAscent();
            g.drawString(titleString, xTitle, yTitle);

            int START_FILES = yTitle + (int) (rec.getHeight() / 2) + fm.getAscent();
            fm = g.getFontMetrics(VIEW_FONT);
            g.setFont(VIEW_FONT);
            //display list of files
            for (int i = 0; i < LoadGameController.getInstance().getFileNames().length; i++) {
                File file = LoadGameController.getInstance().getFileNames()[i];
                // Exclude .DS_Store file lol.
                if (file.isFile() && !file.getName().equals(".DS_Store")) {
                    String fileName = file.getName();
                    Rectangle2D rectangle = fm.getStringBounds(fileName, g);

                    int boxX = View.B_WIDTH / 2 - BUTTON_WIDTH / 2;
                    int boxY = BUTTON_HEIGHT * i + START_FILES;
                    int boxDX = BUTTON_WIDTH;
                    int boxDY = BUTTON_HEIGHT;

                    int stringX = View.B_WIDTH / 2 - (int) (rectangle.getWidth() / 2);
                    int stringY = i * BUTTON_HEIGHT + (int) (rectangle.getHeight() / 2) + fm.getAscent() + START_FILES;

                    Color primaryColor;
                    Color secondaryColor;

                    if (i == LoadGameController.getInstance().getActiveOptions()) {
                        primaryColor = Color.WHITE;
                        secondaryColor = Color.BLACK;

                    } else {
                        primaryColor = Color.BLACK;
                        secondaryColor = Color.WHITE;

                    }

                    g.setColor(primaryColor);
                    g.fillRect(boxX, boxY, boxDX, boxDY);
                    g.setColor(secondaryColor);
                    g.drawString(fileName, stringX, stringY);
                }
            }
        }
    }

    public static LoadGameController getController() {

        return LoadGameController.getInstance();

    }

    private static class LoadGameController extends ViewController {
        File[] fileNames;
        int myOption;

        private static final String saveFilePath = "src/res/save_files/".replaceAll("\\\\|/", "\\" + System.getProperty("file.separator"));
        private static File[] listOfSaveFiles;

        private static LoadGameController controller = new LoadGameController();

        public static LoadGameController getInstance() {

            return controller;

        }

        private LoadGameController() {

            //saveFilePath = saveFilePath
            loadNewFolder();
            listOfSaveFiles = getFileNames();
            myOption = 0;

        }

        private File[] loadNewFolder() {
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

        protected int getActiveOptions() {
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
            }
        }

        private File[] getFileNames() {
            return fileNames;
        }

        private boolean checkFolderList() {
            File folder = new File(saveFilePath);
            if (folder.listFiles().length != fileNames.length) {
                System.out.println("Mis match of files");
                return true;
            } else {
                return false;
            }
        }

        private void loadGame() {
            if (IOMediator.map == null) { //Case if there isn't a map and avatar created (IE coming from AvatarCreationView)
                System.out.println("New Game!");
                Entity avatar = new Avatar();
                Map map = new Map();
                NavigationMediator nav = new NavigationMediator(map, avatar);
                IOMediator.entity = avatar;
                IOMediator.map = map;

                // Create the game view
                //GameView gameView = new GameView(map, avatar);
                GameView.init(map,avatar);
//            IOMediator.Views.GAME.setView(gameView);
                // map.insertEntityAtLocation(avatar.getLocation()[0], avatar.getLocation()[1], avatar);
            } else {
                IOMediator.getInstance().map.removeEntityFromLocation(IOMediator.getInstance().entity.getLocation()[0], IOMediator.getInstance().entity.getLocation()[1]);
                //Needs to remove the previous entity
            }

            Load_Save.getInstance().load(fileNames[myOption].getName()); //Going to grab information from XML
//        IOMediator.setActiveView(IOMediator.Views.GAME);
            Display.getInstance().repaint();

        }

        public void handleKeyRelease(int key) {

        }

    }

}
