package views;

import java.awt.*;
import java.io.File;

/**
 * Created by dyeung on 2/4/16.
 */
public class LoadGameView extends View{
    private String saveFilePath ="src/res/save_files/";
    private File[] listOfSaveFiles;
    public LoadGameView(){
        File folder = new File(saveFilePath);
        listOfSaveFiles = folder.listFiles();
    }
    public void printAllSaveFiles(){

        for (File file : listOfSaveFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }
        }
    }
    public void render(Graphics g){
        System.out.println("View: Load Game View");
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);

        g2d.setFont(new Font("Purisa", Font.PLAIN, 13));

        for (int i = 0; i < listOfSaveFiles.length; i++) {
            File file = listOfSaveFiles[i];
            if (file.isFile()) {
                g2d.drawString(file.getName(), 20 , 30 + (i*10)); // Sketchy distance thing to move each string by 10
            }
        }
    }

}
