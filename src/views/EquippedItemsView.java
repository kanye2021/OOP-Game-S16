package views;

/**
 * Created by sergiopuleri on 2/5/16.
 */

import controllers.EquippedItemsViewController;
import java.awt.*;
import java.awt.geom.Rectangle2D;


public class EquippedItemsView extends View {

    private final String TITLE = "Equipped Items";
    private final String DESCRIPTION = "Press [ENTER] on your selected item to unequip";
    private final int RECT_W = B_WIDTH/3;
    private final int RECT_H = B_HEIGHT/2;
    private final int TOP_PANE_H = B_HEIGHT/8;
    private final int ITEM_SLOT = (int)(((double)TILE_SIZE)*1.5);
    private final int RECT_XY_OFFSET_TOP = (int)(((double)TILE_SIZE)*1.5);
    private final int RECT_X_OFFSET = (int)(((double)TILE_SIZE)*1.5) ;
    private final int RECT_Y_OFFSET = (int)(((double)TILE_SIZE)*1.5) + TOP_PANE_H ;
    private Color secondary;
    private Color primary;
    private Font small;
    private Font title;
    private Font desc;



    public EquippedItemsView() {
        super();
        this.viewController = new EquippedItemsViewController(this);

        small = new Font("Courier New", 1, 12);
        desc = new Font("Courier New", 1, 14);
        title = new Font("Courier New", Font.BOLD, 32);
        secondary = (Color.red);
        primary = (Color.lightGray);

    }


    @Override
    public void render(Graphics g) {
        // Draw Top Pane
        g.setColor(new Color(32, 32, 32));
        g.drawRoundRect(RECT_XY_OFFSET_TOP, RECT_XY_OFFSET_TOP , RECT_W, TOP_PANE_H, 5, 5);
        g.fillRoundRect(RECT_XY_OFFSET_TOP, RECT_XY_OFFSET_TOP, RECT_W, TOP_PANE_H, 5, 5);

        // Draw Main Pane
        g.drawRoundRect(RECT_X_OFFSET, RECT_Y_OFFSET,  RECT_W, RECT_H, 5, 5);
        g.fillRoundRect(RECT_X_OFFSET, RECT_Y_OFFSET , RECT_W, RECT_H, 5, 5);

        // Draw title and description text
        g.setColor(primary);
        FontMetrics fm = g.getFontMetrics(title);
        g.setFont(title);
        Rectangle2D rec = fm.getStringBounds(TITLE, g);
        int titleY = RECT_XY_OFFSET_TOP + ((int) (rec.getHeight()) + fm.getAscent());
        int titleX =  RECT_XY_OFFSET_TOP + RECT_W/2 - (int)rec.getWidth()/2;
        g.drawString(TITLE, titleX, titleY );

        fm = g.getFontMetrics(desc);
        g.setFont(desc);
        rec = fm.getStringBounds(DESCRIPTION, g);
        int descY = (titleY + ((int) (rec.getHeight()) + fm.getAscent()));
        int descX =  RECT_XY_OFFSET_TOP + RECT_W/2 - (int)rec.getWidth()/2;
        g.drawString(DESCRIPTION, descX, descY );

        renderSlots(g);

//        BufferedImage overImage = new BufferedImage(View.B_WIDTH, View.B_HEIGHT, BufferedImage.TYPE_INT_RGB);
//        Graphics g2 = overImage.getGraphics();
//
//        renderBackground(g2);
//
//        renderIems(g2);

    }

    private void renderSlots(Graphics g) {

        g.setColor(primary);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(2));


        FontMetrics fm = g.getFontMetrics(small);
        g.setFont(small);

        int xFirstCol = RECT_X_OFFSET + 1*RECT_W/4 - ITEM_SLOT/2;
        int xSecondCol = RECT_X_OFFSET + 2*RECT_W/4 - ITEM_SLOT/2;
        int xThirdCol = RECT_X_OFFSET + 3*RECT_W/4 - ITEM_SLOT/2;

        // FIRST ROW
        int yFirstRow = RECT_Y_OFFSET + RECT_H/6 - ITEM_SLOT/2;
        //cape
        g.fillRect(xFirstCol, yFirstRow , ITEM_SLOT, ITEM_SLOT);
        if ( ((EquippedItemsViewController) viewController).getSelectedItem() == EquippedItemsViewController.EquippedItemOptSelections.CAPE  ) {
            g.setColor(secondary);
            g.drawRect(xFirstCol, yFirstRow , ITEM_SLOT, ITEM_SLOT);
            g.setColor(primary);

        }
        Rectangle2D rec = fm.getStringBounds(EquippedItemsViewController.EquippedItemOptSelections.CAPE.getText(), g);
        g.drawString(EquippedItemsViewController.EquippedItemOptSelections.CAPE.getText(), xFirstCol + (int)rec.getWidth()/2, yFirstRow + ITEM_SLOT + ((int) (rec.getHeight()) + fm.getAscent()));
        //head
        g.fillRect(xSecondCol, yFirstRow, ITEM_SLOT, ITEM_SLOT);
        if ( ((EquippedItemsViewController) viewController).getSelectedItem() == EquippedItemsViewController.EquippedItemOptSelections.HEAD  ) {
            g.setColor(secondary);
            g.drawRect(xSecondCol, yFirstRow , ITEM_SLOT, ITEM_SLOT);
            g.setColor(primary);
        }
        rec = fm.getStringBounds(EquippedItemsViewController.EquippedItemOptSelections.HEAD.getText(), g);
        g.drawString(EquippedItemsViewController.EquippedItemOptSelections.HEAD.getText(), xSecondCol + (int)rec.getWidth()/2, yFirstRow + ITEM_SLOT + ((int) (rec.getHeight()) + fm.getAscent()));
        //necklace
        g.fillRect(xThirdCol, yFirstRow, ITEM_SLOT, ITEM_SLOT);
        if ( ((EquippedItemsViewController) viewController).getSelectedItem() == EquippedItemsViewController.EquippedItemOptSelections.NECKLACE  ) {
            g.setColor(secondary);
            g.drawRect(xThirdCol, yFirstRow , ITEM_SLOT, ITEM_SLOT);
            g.setColor(primary);
        }
        rec = fm.getStringBounds(EquippedItemsViewController.EquippedItemOptSelections.NECKLACE.getText(), g);
        g.drawString(EquippedItemsViewController.EquippedItemOptSelections.NECKLACE.getText(), xThirdCol, yFirstRow + ITEM_SLOT + ((int) (rec.getHeight()) + fm.getAscent()));


        // SECOND ROW
        int ySecondRow = RECT_Y_OFFSET + 3*RECT_H/6 - ITEM_SLOT/2;
        //primary weapon
        g.fillRect(xFirstCol, ySecondRow , ITEM_SLOT, ITEM_SLOT);
        if ( ((EquippedItemsViewController) viewController).getSelectedItem() == EquippedItemsViewController.EquippedItemOptSelections.PRIMARY  ) {
            g.setColor(secondary);
            g.drawRect(xFirstCol, ySecondRow , ITEM_SLOT, ITEM_SLOT);
            g.setColor(primary);
        }
        rec = fm.getStringBounds(EquippedItemsViewController.EquippedItemOptSelections.PRIMARY.getText(), g);
        g.drawString(EquippedItemsViewController.EquippedItemOptSelections.PRIMARY.getText(), xFirstCol, ySecondRow + ITEM_SLOT + ((int) (rec.getHeight()) + fm.getAscent()));
        //chest
        g.fillRect(xSecondCol, ySecondRow, ITEM_SLOT, ITEM_SLOT);
        if ( ((EquippedItemsViewController) viewController).getSelectedItem() == EquippedItemsViewController.EquippedItemOptSelections.CHEST  ) {
            g.setColor(secondary);
            g.drawRect(xSecondCol, ySecondRow , ITEM_SLOT, ITEM_SLOT);
            g.setColor(primary);
        }
        rec = fm.getStringBounds(EquippedItemsViewController.EquippedItemOptSelections.CHEST.getText(), g);
        g.drawString(EquippedItemsViewController.EquippedItemOptSelections.CHEST.getText(), xSecondCol + (int)rec.getWidth()/2, ySecondRow + ITEM_SLOT + ((int) (rec.getHeight()) + fm.getAscent()));
        //Secondary weapon
        g.fillRect(xThirdCol, ySecondRow, ITEM_SLOT, ITEM_SLOT);
        if ( ((EquippedItemsViewController) viewController).getSelectedItem() == EquippedItemsViewController.EquippedItemOptSelections.SECONDARY  ) {
            g.setColor(secondary);
            g.drawRect(xThirdCol, ySecondRow , ITEM_SLOT, ITEM_SLOT);
            g.setColor(primary);
        }
        rec = fm.getStringBounds(EquippedItemsViewController.EquippedItemOptSelections.SECONDARY.getText(), g);
        g.drawString(EquippedItemsViewController.EquippedItemOptSelections.SECONDARY.getText(), xThirdCol , ySecondRow + ITEM_SLOT + ((int) (rec.getHeight()) + fm.getAscent()));



        // THIRD ROW
        int yThirdRow = RECT_Y_OFFSET + 5*RECT_H/6 - ITEM_SLOT/2;
        // gloves
        g.fillRect(xFirstCol, yThirdRow, ITEM_SLOT, ITEM_SLOT);
        if ( ((EquippedItemsViewController) viewController).getSelectedItem() == EquippedItemsViewController.EquippedItemOptSelections.GLOVES  ) {
            g.setColor(secondary);
            g.drawRect(xFirstCol, yThirdRow , ITEM_SLOT, ITEM_SLOT);
            g.setColor(primary);
        }
        rec = fm.getStringBounds(EquippedItemsViewController.EquippedItemOptSelections.GLOVES.getText(), g);
        g.drawString(EquippedItemsViewController.EquippedItemOptSelections.GLOVES.getText(), xFirstCol + (int)rec.getWidth()/2, yThirdRow + ITEM_SLOT + ((int) (rec.getHeight()/2) + fm.getAscent()));
        //legs
        g.fillRect(xSecondCol, yThirdRow, ITEM_SLOT, ITEM_SLOT);
        if ( ((EquippedItemsViewController) viewController).getSelectedItem() == EquippedItemsViewController.EquippedItemOptSelections.LEGS  ) {
            g.setColor(secondary);
            g.drawRect(xSecondCol, yThirdRow , ITEM_SLOT, ITEM_SLOT);
            g.setColor(primary);
        }
        rec = fm.getStringBounds(EquippedItemsViewController.EquippedItemOptSelections.LEGS.getText(), g);
        g.drawString(EquippedItemsViewController.EquippedItemOptSelections.LEGS.getText(), xSecondCol + (int)rec.getWidth()/2, yThirdRow + ITEM_SLOT + ((int) (rec.getHeight()/2) + fm.getAscent()));
        //boots
        g.fillRect(xThirdCol, yThirdRow, ITEM_SLOT, ITEM_SLOT);
        if ( ((EquippedItemsViewController) viewController).getSelectedItem() == EquippedItemsViewController.EquippedItemOptSelections.BOOTS  ) {
            g.setColor(secondary);
            g.drawRect(xThirdCol, yThirdRow , ITEM_SLOT, ITEM_SLOT);
            g.setColor(primary);
        }
        rec = fm.getStringBounds(EquippedItemsViewController.EquippedItemOptSelections.BOOTS.getText(), g);
        g.drawString(EquippedItemsViewController.EquippedItemOptSelections.BOOTS.getText(), xThirdCol + (int)rec.getWidth()/2, yThirdRow + ITEM_SLOT + ((int) (rec.getHeight()/2) + fm.getAscent()));













    }

    private void renderBackground(Graphics g){
        g.setColor(new Color(200,200,150));
        g.fillRect(0, 0, B_WIDTH, B_HEIGHT);
    }


//    private void renderItems(Graphics g) {
//
//
//    }
//
//    private void renderIems(Graphics g2){
//
//
//
//    }

}
