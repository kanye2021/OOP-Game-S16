package views;

import controllers.*;
import models.*;
import utilities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by dyeung on 2/3/16.
 */
public class SaveGameView extends View {
    private Load_Save l_s;
    private JTextField saveFileField;

    //Things that need to be saved
    private Map myMap;
    private Entity myEntity;

    private final String MSG = "Save File Name:";
    private final String ENDMSG = "Press enter to save";

    public SaveGameView(Map m, Entity e){
        this.viewController = new SaveGameController(this);
        l_s = new Load_Save();
        this.myMap = m;
        this.myEntity = e;
    }
    public void startSaving(){
        System.out.println("Saving map and entity");
        String savedFileName = saveFileField.getText();
        System.out.println("Saved file name is " + savedFileName);
        l_s.save(myMap, myEntity, savedFileName);

        //Return back to the game (?) Not sure where to go from save
        //IOMediator.setActiveView(IOMediator.Views.GAME);
        saveFileField.setVisible(false);
    }
    public JTextField getTextField(){
        return saveFileField;
    }
    @Override
    public void render(Graphics g) {
        Font small = new Font("Helvetica", Font.BOLD, 14);

        int x = 200;
        int y = 300;
        g.setColor(Color.white);
        g.drawString(MSG, x, y);
        g.drawString(ENDMSG, x, y - 200);

        saveFileField= new JTextField(20);
        saveFileField.setBounds(x, y - 100, 200,40);
        saveFileField.setVisible(true);

        Display.getInstance().add(saveFileField);

    }
}
