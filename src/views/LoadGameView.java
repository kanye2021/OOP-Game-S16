package views;

import controllers.LoadGameController;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;

/**
 * Created by dyeung on 2/4/16.
 */
public class LoadGameView extends View {
    //----------View Design stuff --------
    private final int BUTTON_WIDTH = 200;
    private final int BUTTON_HEIGHT = 50;
    private final int START_POSITION = 100;
    //--------File Path stuff -------
    private String saveFilePath = "src/res/save_files/";
    private File[] listOfSaveFiles;

    public LoadGameView() {
        saveFilePath = saveFilePath.replaceAll("\\\\|/", "\\" + System.getProperty("file.separator"));
        this.viewController = new LoadGameController(this);
//        File folder = new File(saveFilePath);
        listOfSaveFiles = ((LoadGameController) viewController).getFileNames();
        //getNewFiles();
    }

    public void getNewFiles() { //Function is used to update the list of save files in the folder
        listOfSaveFiles = ((LoadGameController) this.viewController).loadNewFolder();
        System.out.println("LGV: " + listOfSaveFiles.length);
    }

    public void render(Graphics g) {
        renderFileButtons(g);
    }

    private void renderFileButtons(Graphics g) {

        g.setFont(VIEW_FONT);
        FontMetrics fm = g.getFontMetrics(VIEW_FONT);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);

        g2d.setFont(new Font("Courier New", Font.PLAIN, 13));

        //no saved games to list in for loop below
        if (listOfSaveFiles.length == 0) {
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
            for (int i = 0; i < listOfSaveFiles.length; i++) {
                File file = listOfSaveFiles[i];
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

                    if (i == (((LoadGameController) this.viewController).getActiveOptions())) {
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

}
