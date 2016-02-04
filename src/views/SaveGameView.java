package views;

import controllers.*;
import models.*;
import utilities.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        String text;

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
    public void setTextField(JTextField in){
        saveFileField = in;
    }
    @Override
    public void render(Graphics g) {
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = g.getFontMetrics(small);
        Rectangle2D r1 = fm.getStringBounds(MSG, g);
        int x = (View.B_WIDTH - (int)r1.getWidth())/2;
        int y = (View.B_HEIGHT - (int)r1.getHeight())/2 + fm.getAscent();

        int col = 20;
        int row = 40;
        g.setColor(Color.white);
        g.drawString(MSG, x, y);
        JButton button = new JButton("BUTTON");

        saveFileField = new JTextField("Press Return", 20);
        saveFileField.setBounds(x, y, col * 10, row);


        saveFileField.setVisible(true);
        button.setVisible(true);

        g.drawString(ENDMSG, x, y - 80);


        Display.getInstance().add(saveFileField);

    }
}
