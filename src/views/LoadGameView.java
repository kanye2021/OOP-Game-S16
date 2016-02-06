package views;

import controllers.LoadGameController;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;

/**
 * Created by dyeung on 2/4/16.
 */
public class LoadGameView extends View{
    //----------View Design stuff --------
    private final int BUTTON_WIDTH = 200;
    private final int BUTTON_HEIGHT = 50;
    private final int START_POSITION = 100;
    //--------File Path stuff -------
    private String saveFilePath ="src/res/save_files/";
    private File[] listOfSaveFiles;
    private boolean firstRender;
    private LoadGameController loadController;
    public LoadGameView(){
        this.viewController = new LoadGameController(this);
        loadController = (LoadGameController) this.viewController;
        File folder = new File(saveFilePath);
        listOfSaveFiles = folder.listFiles();
        firstRender = true;
        //getNewFiles();
    }
    public void getNewFiles(){
        listOfSaveFiles = loadController.loadNewFolder();
        System.out.println("In the view: " + listOfSaveFiles.length);
    }
    public void loadNewFiles(){
        //TODO: When user checks the load view it should update the view
    }
    public void render(Graphics g){
        renderFileButtons(g);
    }
    private void renderFileButtons(Graphics g){

        g.setFont(VIEW_FONT);
        FontMetrics fm = g.getFontMetrics(VIEW_FONT);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);

        g2d.setFont(new Font("Purisa", Font.PLAIN, 13));

        for (int i = 0; i < listOfSaveFiles.length; i++) {
            File file = listOfSaveFiles[i];
            if (file.isFile()) {
                String fileName = file.getName();
                Rectangle2D rectangle = fm.getStringBounds(fileName, g);

                int boxX = View.B_WIDTH / 2 - BUTTON_WIDTH / 2;
                int boxY = BUTTON_HEIGHT * i + START_POSITION;
                int boxDX = BUTTON_WIDTH;
                int boxDY = BUTTON_HEIGHT;

                int stringX = View.B_WIDTH / 2 - (int) (rectangle.getWidth() / 2);
                int stringY = i * BUTTON_HEIGHT + (int) (rectangle.getHeight() / 2) + fm.getAscent() + START_POSITION;

                Color primaryColor;
                Color secondaryColor;

                if (i == ( ((LoadGameController)this.viewController).getActiveOptions() ) ) {
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
