package views;

import controllers.InventoryViewController;
import controllers.PauseViewController;
import models.Inventory;
import models.Item;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Created by Austin on 2/2/16.
 */
public class InventoryView extends View {

	Inventory currentInventory;
	private final int ITEM_HEIGHT = 25;
	
	public InventoryView(Inventory inventory) {
		super();
        this.viewController = new InventoryViewController(this, inventory);
		currentInventory = inventory;
	}
	
	@Override
	public void render(Graphics g) {
		
		BufferedImage overImage = new BufferedImage(View.B_WIDTH, View.B_HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics g2 = overImage.getGraphics();
		
		int itemNumber = 0;
		int itemOffset = 1;
		
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics fm = g.getFontMetrics(small);

        g2.setFont(small);
        
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, B_WIDTH - 1, B_HEIGHT - 1);
        g2.setColor(Color.BLACK);
        g2.fillRect(10, 10, B_WIDTH - 21, B_HEIGHT - 21);
        
        String titleString = "Inventory";
        Rectangle2D titleRectangle = fm.getStringBounds(titleString, g2);
        int titleStringX = (int) (B_WIDTH / 2 - titleRectangle.getWidth() / 2);
        int titleStringY = (int) (itemNumber * ITEM_HEIGHT + titleRectangle.getHeight() / 3 + fm.getAscent() + 11);
        
        g2.setColor(Color.WHITE);
        g2.drawString(titleString, titleStringX, titleStringY);
        
		for (Map.Entry<Item, Integer> entry : currentInventory.getItems().entrySet()) {
			
			String string1 = entry.getKey().getName();
			String string2 = "x" + entry.getValue();
			
			Rectangle2D rectangle1 = fm.getStringBounds(string1, g2);
			Rectangle2D rectangle2 = fm.getStringBounds(string2, g2);
			
			int actualItemNumber = itemNumber + itemOffset;
			
			int boxX = 11;
			int boxY = actualItemNumber * ITEM_HEIGHT + 11;
			int boxDX = B_WIDTH - 22;
			int boxDY = ITEM_HEIGHT;
			int string1X = 20;
			int string1Y = (int) (actualItemNumber * ITEM_HEIGHT + rectangle1.getHeight() / 3 + fm.getAscent() + 11);
			int string2X = (int) (B_WIDTH - rectangle2.getWidth() - 20);
			int string2Y = (int) (actualItemNumber * ITEM_HEIGHT + rectangle2.getHeight() / 3 + fm.getAscent() + 11);
			
			Color primaryColor;
			Color secondaryColor;
			
			if (itemNumber == ((InventoryViewController) viewController).getActiveItem()) {
			
				primaryColor = Color.WHITE;
				secondaryColor = Color.BLACK;
			
			} else {
				
				primaryColor = Color.BLACK;
				secondaryColor = Color.WHITE;
				
			}
		
			g2.setColor(primaryColor);
			g2.fillRect(boxX, boxY, boxDX, boxDY);
			g2.setColor(secondaryColor);
			g2.drawString(string1, string1X, string1Y);
			g2.drawString(string2, string2X, string2Y);
			g2.drawRect(boxX, boxY, boxDX, boxDY);
			itemNumber++;
			
		}
		
		g.drawImage(overImage, (int) (View.B_WIDTH * 0.1), (int) (View.B_HEIGHT * 0.1), (int) (View.B_WIDTH * 0.8), (int) (View.B_HEIGHT * 0.8), null);
		
	}

	public void setInventory(Inventory inventory) {
		
		currentInventory = inventory;
	
	}
	
}
