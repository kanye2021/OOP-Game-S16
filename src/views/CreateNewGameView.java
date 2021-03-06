package views;

import controllers.CreateNewGameViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by sergiopuleri on 2/2/16.
 */
public class CreateNewGameView extends View {

    private final String NEW_GAME_TEXT = "Enter your save state name below please";
    private final String SAVE_NAME_TEXT = "[Enter to save & continue]";
    private JTextField saveStateName;
    private boolean firstRender;


    public CreateNewGameView() {
        super();
        this.viewController = new CreateNewGameViewController(this);
        firstRender = true;
    }

    public JTextField getSaveStateName() {
        return saveStateName;
    }

    @Override
    public void render(Graphics g) {
        clear(g); // Clear the background

        // Text
        Font title = new Font("Courier New", Font.BOLD, 28);
        FontMetrics fm = g.getFontMetrics(title);
        g.setColor(Color.white);
        g.setFont(title);
        Rectangle2D r1 = fm.getStringBounds(NEW_GAME_TEXT, g);
        int x = (View.B_WIDTH - (int) r1.getWidth()) / 2;
        int y = (View.B_HEIGHT - (int) r1.getHeight()) / 7 + fm.getAscent();
        g.drawString(NEW_GAME_TEXT, x, y);

        // Text Field
        int ytextField = y + 150;
        int xtextField = x;
        // Only want to create JTextField on the first render, and add it to the JPanel.
        if (firstRender) {
            saveStateName = new JTextField(20);
            saveStateName.setBounds(xtextField, ytextField, 200, 40);
            saveStateName.setVisible(true);
            Display.getInstance().add(saveStateName);
            firstRender = false;
        }
        // Always set focus to JTextField
        saveStateName.requestFocus();


        // Label Text
        Font small = new Font("Helvetica", Font.ITALIC, 14);
        fm = g.getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        Rectangle2D r2 = fm.getStringBounds(NEW_GAME_TEXT, g);
        int yLabelText = ytextField + 10 + fm.getAscent() + saveStateName.getBounds().height;
        int xLabelText = xtextField;
        g.drawString(SAVE_NAME_TEXT, xLabelText, yLabelText);

        Toolkit.getDefaultToolkit().sync();

    }


}
