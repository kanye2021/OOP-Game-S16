package views;

/**
 * Created by sergiopuleri on 2/5/16.
 */

import controllers.EquippedItemsViewController;
import models.items.TakeableItem;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;


public class EquippedItemsView extends View {

    private static final String TITLE = "Equipped Items";
    private static final String DESCRIPTION = "Press [ENTER] on your selected item to unequip";
    private static final int RECT_W = B_WIDTH / 3;
    private static final int RECT_H = B_HEIGHT / 2;
    private static final int TOP_PANE_H = B_HEIGHT / 8;
    private static final int ITEM_SLOT = (int) (((double) TILE_SIZE) * 1.5);
    // image length and width is 80% of the item slot size.
    private static final int IMG_SIZE = (int) ((double) ITEM_SLOT * .80);
    private static final int RECT_XY_OFFSET_TOP = (int) (((double) TILE_SIZE) * 1.5);
    private static final int RECT_X_OFFSET = (int) (((double) TILE_SIZE) * 1.5);
    private static final int RECT_Y_OFFSET = (int) (((double) TILE_SIZE) * 1.5) + TOP_PANE_H;
    private static Color secondary;
    private static Color primary;
    private static Color rlySmallColor;
    private static Font rlySmall;
    private static Font small;
    private static Font title;
    private static Font desc;

    private static EquippedItemsView equippedItemsView = new EquippedItemsView();
    private EquippedItemsView() {}


    public static void init(){

        small = new Font("Courier New", 1, 12);
        rlySmall = new Font("Courier New", Font.BOLD, 10);
        desc = new Font("Courier New", 1, 14);
        title = new Font("Courier New", Font.BOLD, 32);
        secondary = (Color.red);
        primary = (Color.lightGray);
        rlySmallColor = Color.white;

    }



    public static void render(Graphics g) {

        // Draw Top Pane
        g.setColor(new Color(32, 32, 32));
        g.drawRoundRect(RECT_XY_OFFSET_TOP, RECT_XY_OFFSET_TOP, RECT_W, TOP_PANE_H, 5, 5);
        g.fillRoundRect(RECT_XY_OFFSET_TOP, RECT_XY_OFFSET_TOP, RECT_W, TOP_PANE_H, 5, 5);

        // Draw Main Pane
        g.drawRoundRect(RECT_X_OFFSET, RECT_Y_OFFSET, RECT_W, RECT_H, 5, 5);
        g.fillRoundRect(RECT_X_OFFSET, RECT_Y_OFFSET, RECT_W, RECT_H, 5, 5);

        // Draw title
        g.setColor(primary);
        FontMetrics fm = g.getFontMetrics(title);
        g.setFont(title);
        Rectangle2D rec = fm.getStringBounds(TITLE, g);
        int titleY = RECT_XY_OFFSET_TOP + ((int) (rec.getHeight()) + fm.getAscent());
        int titleX = RECT_XY_OFFSET_TOP + RECT_W / 2 - (int) rec.getWidth() / 2;
        g.drawString(TITLE, titleX, titleY);

        // Draw Description text
        fm = g.getFontMetrics(desc);
        g.setFont(desc);
        rec = fm.getStringBounds(DESCRIPTION, g);
        int descY = (titleY + ((int) (rec.getHeight()) + fm.getAscent()));
        int descX = RECT_XY_OFFSET_TOP + RECT_W / 2 - (int) rec.getWidth() / 2;
        g.drawString(DESCRIPTION, descX, descY);

        // Draw slots + equipped item images
        renderSlots(g);


    }

    private static void renderSlots(Graphics g) {

        //
        int xFirstCol = RECT_X_OFFSET + 1 * RECT_W / 4 - ITEM_SLOT / 2;
        int xSecondCol = RECT_X_OFFSET + 2 * RECT_W / 4 - ITEM_SLOT / 2;
        int xThirdCol = RECT_X_OFFSET + 3 * RECT_W / 4 - ITEM_SLOT / 2;
        int yFirstRow = RECT_Y_OFFSET + RECT_H / 6 - ITEM_SLOT / 2;
        int ySecondRow = RECT_Y_OFFSET + 3 * RECT_H / 6 - ITEM_SLOT / 2;
        int yThirdRow = RECT_Y_OFFSET + 5 * RECT_H / 6 - ITEM_SLOT / 2;

        Rectangle2D rec;
        int x;
        int y;
        int colCount = 1;
        Image i;
        ImageIcon ii;
        for (EquippedItemsViewController.EquippedItemOptSelections option : EquippedItemsViewController.EquippedItemOptSelections.values()) {
            if (option.ordinal() < 3) {
                y = yFirstRow;
            } else if (option.ordinal() >= 3 && option.ordinal() < 6) {
                y = ySecondRow;
            } else {
                y = yThirdRow;
            }
            if (colCount % 3 == 1) {
                x = xFirstCol;
            } else if (colCount % 3 == 2) {
                x = xSecondCol;
            } else {
                x = xThirdCol;
            }
            colCount++;
            // Draw slots
            g.fillRect(x, y, ITEM_SLOT, ITEM_SLOT);
            if (((EquippedItemsViewController) viewController).getSelectedItem() == option) {
                g.setColor(secondary);
                g.drawRect(x - 3, y - 3, ITEM_SLOT + 6, ITEM_SLOT + 6);
                g.setColor(primary);

            }
            // Get image path for appropiate slot
            if (option.getImagePath() != "") {
                // Load the equipped item image image
                ii = new ImageIcon("./src/res/items/takeable/" + option.getImagePath());
                i = ii.getImage();
                // Draw the name of the item underneath it rly smallly
                TakeableItem currentEquipped = option.getComponent();
                String itemName = currentEquipped.getName();
                g.setColor(rlySmallColor);
                FontMetrics fm = g.getFontMetrics(rlySmall);
                g.setFont(rlySmall);
                rec = fm.getStringBounds(itemName, g);
                g.drawString(itemName, x + (ITEM_SLOT - (int) rec.getWidth()) / 2, y + ITEM_SLOT - ((int) (rec.getHeight()) / 2));


            } else {
                // No item equipped for this slot
                // Render a place holder image
                ii = new ImageIcon("./src/res/items/takeable/placeholder.png");
                i = ii.getImage();
            }
            // Draw image
            g.drawImage(i, x + (ITEM_SLOT - IMG_SIZE) / 2, y + (ITEM_SLOT - IMG_SIZE) / 2, IMG_SIZE, IMG_SIZE, Display.getInstance());

            g.setColor(primary);
            FontMetrics fm = g.getFontMetrics(small);
            g.setFont(small);
            // Draw equipment slot text
            rec = fm.getStringBounds(option.getText(), g);
            // If the text contains more than 5 letters (e.g. "primary weapon") we want to render it differently
            if (option.getText().length() > 5) {
                g.drawString(option.getText(), x, y + ITEM_SLOT + ((int) (rec.getHeight()) + fm.getAscent()));
            } else
                g.drawString(option.getText(), x + (int) rec.getWidth() / 2, y + ITEM_SLOT + ((int) (rec.getHeight()) + fm.getAscent()));
        }

    }

}
