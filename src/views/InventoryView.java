package views;

import controllers.InventoryViewController;
import controllers.PauseViewController;
import models.Inventory;
import models.items.Item;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Created by Austin on 2/2/16.
 */
public class InventoryView extends View {

	private final int ITEM_HEIGHT = 30;
	
	public InventoryView() {
		super();
        this.viewController = new InventoryViewController(this);
	}
	
	
	@Override
	public void render(Graphics g) {
		
		BufferedImage overImage = new BufferedImage(View.B_WIDTH, View.B_HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics g2 = overImage.getGraphics();
		
		renderBackground(g2);
		
		renderItems(g2);
		
		g.drawImage(overImage, (int) (View.B_WIDTH * 0.1), (int) (View.B_HEIGHT * 0.1), (int) (View.B_WIDTH * 0.8), (int) (View.B_HEIGHT * 0.8), null);
		
	}
	
	private void renderBackground(Graphics g){
		g.setColor(new Color(200,200,150));
		g.fillRect(0, 0, B_WIDTH, B_HEIGHT);
	}
	
	
	private void renderItems(Graphics g){
		
		
	}
	/*
	private void renderIems(Graphics g2){
		
		int itemNumber = 0;
		int itemOffset = 1;
		
		FontMetrics fm = g2.getFontMetrics(VIEW_FONT);
        g2.setFont(VIEW_FONT);
        
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, B_WIDTH - 1, B_HEIGHT - 1);
        g2.setColor(Color.BLACK);
        g2.fillRect(10, 10, B_WIDTH - 21, B_HEIGHT - 21);
        
        String titleString = "Inventory";
        Rectangle2D titleRectangle = fm.getStringBounds(titleString, g2);
        int titleStringX = (int) (B_WIDTH / 2 - titleRectangle.getWidth() / 2);
        int titleStringY = (int) (ITEM_HEIGHT / 3 + fm.getAscent());
        
        g2.setColor(Color.WHITE);
        g2.drawString(titleString, titleStringX, titleStringY);
        g2.drawRect(0, 0, B_WIDTH, ITEM_HEIGHT);
        
		for (Map.Entry<Item, Integer> entry : ((InventoryViewController) viewController).getInventory().getItems().entrySet()) {
			
			String string1 = entry.getKey().getName();
			String string2 = entry.getKey().getDescription();
			String string3 = "x" + entry.getValue();
			
			Rectangle2D rectangle1 = fm.getStringBounds(string1, g2);
			Rectangle2D rectangle2 = fm.getStringBounds(string2, g2);
			Rectangle2D rectangle3 = fm.getStringBounds(string3, g2);
			
			int actualItemNumber = itemNumber + itemOffset;
			
			int boxX = 25;
			int boxY = actualItemNumber * ITEM_HEIGHT + 11;
			int boxDX = B_WIDTH - 52;
			int boxDY = ITEM_HEIGHT;
			int string1X = 30;
			int string1Y = (int) (actualItemNumber * ITEM_HEIGHT + rectangle1.getHeight() / 3 + fm.getAscent() + 11);
			int string2X = (int) (B_WIDTH / 2 - rectangle2.getWidth() / 2);
			int string2Y = (int) (actualItemNumber * ITEM_HEIGHT + rectangle2.getHeight() / 3 + fm.getAscent() + 11);
			int string3X = (int) (B_WIDTH - rectangle3.getWidth() - 30);
			int string3Y = (int) (actualItemNumber * ITEM_HEIGHT + rectangle3.getHeight() / 3 + fm.getAscent() + 11);
			
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
			g2.drawString(string3, string3X, string3Y);
			itemNumber++;
			
		}
		
	}*/

}
