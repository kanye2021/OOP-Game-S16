package views;

import controllers.InventoryViewController;
import models.Entity;
import models.Inventory;
import models.Inventory.ItemNode;
import models.Map;
import models.items.TakeableItem;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * Created by Austin on 2/2/16.
 */
public class InventoryView extends View {
    private static final String ITEM_IMAGE_LOCATION = "./src/res/items/takeable/";
    private static final String FONT_USE = "Calibri (Body)";

    //INVENTORY TITLE
    private static final int TITLE_START_X = (int) (B_WIDTH * 0.1);
    private static final int TITLE_START_Y = (int) (B_HEIGHT * 0.05);
    private static final int TITLE_WIDTH = (int) (B_WIDTH * 0.8);
    private static final int TITLE_HEIGHT = (int) (B_HEIGHT * 0.15);


    //INVETORY DIMENSION
    private static final int INVENTORY_VIEW_X_START = (int) (B_WIDTH * 0.1);
    private static final int INVENTORY_VIEW_Y_START = TITLE_START_Y + TITLE_HEIGHT;
    private static final int INVENTORY_VIEW_WIDTH = (int) (B_WIDTH * 0.8);
    private static final int INVENTORY_VIEW_HEIGHT = (int) (B_HEIGHT * 0.52);

    //ITEM VIEW DIMENSION
    private static final int ITEM_VIEW_X_START = INVENTORY_VIEW_X_START;
    private static final int ITEM_VIEW_Y_START = INVENTORY_VIEW_Y_START;
    private static final int ITEM_VIEW_WIDTH = INVENTORY_VIEW_WIDTH;
    private static final int ITEM_VIEW_HEIGHT = (int) (INVENTORY_VIEW_HEIGHT * 0.7);

    private static final int ITEM_MARGIN = 15;
    private static int ITEM_WIDTH = (ITEM_VIEW_WIDTH - (InventoryViewController.ITEMS_PER_ROW + 1) * ITEM_MARGIN) / InventoryViewController.ITEMS_PER_ROW;
    private static int ITEM_HEIGHT = ITEM_WIDTH;

    //INFO VIEW DIMENSION
    private static final int INFO_VIEW_X_START = INVENTORY_VIEW_X_START;
    private static final int INFO_VIEW_Y_START = ITEM_VIEW_Y_START + ITEM_VIEW_HEIGHT;
    private static final int INFO_VIEW_WIDTH = INVENTORY_VIEW_WIDTH;
    private static final int INFO_VIEW_HEIGHT = INVENTORY_VIEW_HEIGHT - ITEM_VIEW_HEIGHT;

    private static final int INFO_X_MARGIN = 40;
    private static final int INFO_Y_MARGIN = (int) (INFO_VIEW_HEIGHT * 0.2);
    private static final int INFO_ELEMENT_HEIGHT = (int) (INFO_VIEW_HEIGHT - INFO_Y_MARGIN * 2);
    private static final int INFO_DESCRIPTION_WIDTH = (int) (INFO_VIEW_WIDTH - 2 * INFO_ELEMENT_HEIGHT - 4 * INFO_X_MARGIN);

    private static Font font;
    private static Font smallFont;
    private static Font largeFont;
    private static Font titleFont;

    private static String takeableItemRootFilepath = "./src/res/items/takeable/";

    private static InventoryView InventoryView = new InventoryView();
    private InventoryView() {}


    public static void init(Map map,Entity entity){
        takeableItemRootFilepath = takeableItemRootFilepath.replaceAll("\\\\|/", "\\" + System.getProperty("file.separator"));
        //this.viewController = new InventoryViewController(this, map, entity);

        font = new Font("Courier New", 1, 24);
        smallFont = new Font("Courier New", 1, 18);
        largeFont = new Font("Courier New", 1, 40);
        titleFont = new Font("Courier New", 1, 55);
    }

    public static void render(Graphics g) {
        renderTitle(g);
        renderItemsView(g);
        renderInfoView(g);
    }

    private static void renderTitle(Graphics g) {
        int titleMargin = 5;
        int instMargin = 15;

        // Draw the background
        g.setColor(new Color(25, 25, 25));
        g.fillRect(TITLE_START_X, TITLE_START_Y, TITLE_WIDTH, TITLE_HEIGHT);

        // Get ready to draw the title
        g.setFont(titleFont);
        String title = "Inventory";
        FontMetrics fm = g.getFontMetrics(titleFont);
        Rectangle2D titleRect = fm.getStringBounds(title, g);

        // Get the location of the title
        int titleX = TITLE_START_X + TITLE_WIDTH / 2 - (int) titleRect.getWidth() / 2;
        int titleY = TITLE_START_Y + (int) titleRect.getHeight() + titleMargin;

        // Draw the title
        g.setColor(Color.lightGray);
        g.drawString(title, titleX, titleY);

        // Get ready to draw the instructions
        g.setFont(smallFont);
        String instructions = "Press [Enter] to equip an item or [d] to drop it.";
        FontMetrics fm2 = g.getFontMetrics(smallFont);
        Rectangle2D instRect = fm2.getStringBounds(instructions, g);

        // Get the location of the instr
        int instX = TITLE_START_X + TITLE_WIDTH / 2 - (int) instRect.getWidth() / 2;
        int instY = TITLE_START_Y + TITLE_HEIGHT - instMargin;

        // Draw the instr
        g.drawString(instructions, instX, instY);


    }

    private static void renderItemsView(Graphics g) {
        BufferedImage overImage = new BufferedImage(ITEM_VIEW_WIDTH, ITEM_VIEW_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g2 = overImage.getGraphics();

        //paint background
        g2.setColor(new Color(32, 32, 32));
        g2.fillRect(0, 0, ITEM_VIEW_WIDTH, ITEM_VIEW_HEIGHT);


        InventoryViewController ivc = ((InventoryViewController) viewController);
        Inventory in = ivc.getInventory();
        int size = in.getSize();

        int xPosStart = ITEM_MARGIN;
        int xPosInc = ITEM_MARGIN + ITEM_WIDTH;
        int yPosInc = ITEM_MARGIN + ITEM_HEIGHT;
        int xpos = xPosStart;
        int ypos = ITEM_MARGIN;

        for (int i = 0; i < in.getSize(); i++) {

            //selected
            if (ivc.getPosition() == i) {
                g2.setColor(Color.RED);
                //change
                g2.drawRect(xpos - 3, ypos - 3, ITEM_WIDTH + 6, ITEM_HEIGHT + 6);
            }

            paintIcon(g2, xpos, ypos, in.getItemNodeAt(i));

            //increment for next paint
            if ((i + 1) % InventoryViewController.ITEMS_PER_ROW == 0) {
                xpos = xPosStart;
                ypos += yPosInc;
            } else {
                xpos += xPosInc;
            }
        }
        g.drawImage(overImage, ITEM_VIEW_X_START, ITEM_VIEW_Y_START, ITEM_VIEW_WIDTH, ITEM_VIEW_HEIGHT, null);
    }

    private static void paintIcon(Graphics g, int xpos, int ypos, ItemNode itemNode) {

        if (itemNode == null) {
            //draw empty slot
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(xpos, ypos, ITEM_WIDTH, ITEM_HEIGHT);

            int xMid = (2 * xpos + ITEM_WIDTH) / 2;

//			g.setFont(new Font(this.FONT_USE, Font.PLAIN, (int)(this.ITEM_HEIGHT*0.6)));
            g.setFont(largeFont);
            g.setColor(Color.BLACK);
            FontMetrics fm = g.getFontMetrics();
            //g.drawString("?", xMid - fm.stringWidth("?")/2, ypos + fm.getHeight());
        } else {

            //draw pic
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(xpos, ypos, ITEM_WIDTH, ITEM_HEIGHT);
            ImageIcon im = new ImageIcon(ITEM_IMAGE_LOCATION + itemNode.item.getPathToPicture());
            g.drawImage(im.getImage(), xpos, ypos, ITEM_WIDTH, ITEM_HEIGHT, null);


            //draw amount
            g.setFont(smallFont);
            FontMetrics fm = g.getFontMetrics();
            int width = fm.stringWidth(itemNode.amount + "");
            int height = fm.getHeight();
            g.setColor(Color.BLACK);
            g.drawString("x" + itemNode.amount + "", xpos, ypos + (int) (fm.getHeight() * 0.8));
        }
    }


    private static void renderInfoView(Graphics g) {
        BufferedImage overImage = new BufferedImage(INFO_VIEW_WIDTH, INFO_VIEW_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g2 = overImage.getGraphics();

        //paint background
        g2.setColor(new Color(25, 25, 25));
        g2.fillRect(0, 0, ITEM_VIEW_WIDTH, ITEM_VIEW_HEIGHT);

        InventoryViewController ivc = ((InventoryViewController) viewController);
        Inventory in = ivc.getInventory();

        TakeableItem item = in.getItemAt(ivc.getPosition());

        paintSelectedIcon(g2, item);

        paintInfo(g2, item);

        paintOptions(g2, item);

        g.drawImage(overImage, INFO_VIEW_X_START, INFO_VIEW_Y_START, INFO_VIEW_WIDTH, INFO_VIEW_HEIGHT, null);
    }


    private static void paintSelectedIcon(Graphics g2, TakeableItem item) {
        if (item == null) {
            g2.setColor(Color.LIGHT_GRAY);
            g2.fillRect(INFO_X_MARGIN, INFO_Y_MARGIN, INFO_ELEMENT_HEIGHT, INFO_ELEMENT_HEIGHT);
        } else {
            g2.setColor(Color.LIGHT_GRAY);
            g2.fillRect(INFO_X_MARGIN, INFO_Y_MARGIN, INFO_ELEMENT_HEIGHT, INFO_ELEMENT_HEIGHT);
            ImageIcon im = new ImageIcon(ITEM_IMAGE_LOCATION + item.getPathToPicture());
            g2.drawImage(im.getImage(), INFO_X_MARGIN, INFO_Y_MARGIN, INFO_ELEMENT_HEIGHT, INFO_ELEMENT_HEIGHT, null);
        }
    }

    private static void paintInfo(Graphics g2, TakeableItem item) {

        g2.setFont(font);
        FontMetrics fm = g2.getFontMetrics();

        int xpos = INFO_VIEW_WIDTH / 2 - INFO_DESCRIPTION_WIDTH / 2;
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(xpos, INFO_Y_MARGIN, INFO_DESCRIPTION_WIDTH, INFO_ELEMENT_HEIGHT);

        if (item != null) {
            String description = item.getDescription();
            String[] subString = description.split(" ");
            String[] output = new String[subString.length];
            int lines = 0;
            output[0] = subString[0];

            int descriptionWidth = (int) (INFO_DESCRIPTION_WIDTH * 0.8);

            for (int i = 1; i < subString.length; i++) {
                if (fm.stringWidth(output[lines] + " " + subString[i]) < descriptionWidth) {
                    output[lines] += " " + subString[i];
                } else {
                    output[++lines] = subString[i];
                }
            }
            lines++;
            int ypos = INFO_Y_MARGIN + 25;

            g2.setColor(Color.BLACK);
            for (int i = 0; i < lines; i++) {
                g2.drawString(output[i], INFO_VIEW_WIDTH / 2 - fm.stringWidth(output[i]) / 2, ypos);
                ypos += fm.getHeight();
            }
        }
    }


    private static void paintOptions(Graphics g2, TakeableItem item) {

        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(INFO_VIEW_WIDTH - INFO_X_MARGIN - INFO_ELEMENT_HEIGHT, INFO_Y_MARGIN, INFO_ELEMENT_HEIGHT, INFO_ELEMENT_HEIGHT);


        if (item == null) {

        } else {

        }

    }
}

