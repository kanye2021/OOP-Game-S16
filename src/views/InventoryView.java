
package views;

import controllers.InventoryViewController;
import controllers.PauseViewController;
import models.Inventory;
import models.Inventory.ItemNode;
import models.Item;
import models.TakeableItem;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Map;

import javax.swing.ImageIcon;

/**
 * Created by Austin on 2/2/16.
 */
public class InventoryView extends View {
	
	private final int ITEM_MARGIN = 15;
	private final int ITEM_PER_ROW = 7;
	
	private final Font ITEM_INFO_FONT = new Font("Calibri (Body)", Font.ITALIC, 40);
	private final Font TEXT_FONT = new Font("Calibri (Body)", Font.ITALIC, 40);
	
	private int itemIconHight;
	private int itemIconWidth;
	
	public InventoryView() {
		super();
        this.viewController = new InventoryViewController(this);
        
        itemIconWidth = (View.B_WIDTH - (ITEM_PER_ROW + 1)*ITEM_MARGIN)/ITEM_PER_ROW;
        itemIconHight = itemIconWidth;
	}
	
	
	@Override
	public void render(Graphics g) {
		
		BufferedImage overImage = new BufferedImage(View.B_WIDTH, View.B_HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics ItemG = overImage.getGraphics();
		
		renderBackground(ItemG);
		
		renderItems(ItemG);
		
		g.drawImage(overImage, (int) (View.B_WIDTH * 0.1), (int) (View.B_HEIGHT * 0.1), (int) (View.B_WIDTH * 0.8), (int) (View.B_HEIGHT * 0.8), null);
		
	}
	
	private void renderBackground(Graphics g){
		g.setColor(new Color(200,200,150));
		g.fillRect(0, 0, B_WIDTH, B_HEIGHT);
	}
	
	private void renderItems(Graphics g){
		InventoryViewController ivc = ((InventoryViewController) viewController);
		Inventory in = ivc.getInventory();
		int size = in.getSize();
		
		int xPosStart = ITEM_MARGIN;
		
		int xPosInc = ITEM_MARGIN + itemIconWidth;
		int yPosInc = ITEM_MARGIN + itemIconHight;
		
		int xpos = xPosStart;
		int ypos = ITEM_MARGIN;
		
		for (int i = 0; i < in.getSize(); i++) {
			
			paintIcon(g,xpos,ypos,in.getItemNodeAt(i));
			
			if(ivc.getPosition() == i){
				g.setColor(Color.RED);
				g.drawRect(xpos, ypos, this.itemIconWidth, this.itemIconHight);
			}
			
			//increment for next paint
			if((i+1)%ITEM_PER_ROW == 0){
				xpos = xPosStart;
				ypos += yPosInc;
			}
			else{
				xpos += xPosInc;
			}
		}
		
	}
	private void paintIcon(Graphics g, int xpos,int ypos,ItemNode itemNode){
		
		if(itemNode == null){
			//draw empty slot
			g.setColor(new Color(200,200,50));
			g.fillRect(xpos, ypos, itemIconWidth, itemIconHight);
			
			int xMid = (2*xpos + itemIconWidth)/2;
			
			g.setFont(new Font("Calibri (Body)", Font.ITALIC, 100));
			g.setColor(Color.BLACK);
			FontMetrics fm = g.getFontMetrics();
			g.drawString("?", xMid - fm.stringWidth("?")/2, ypos + fm.getHeight());
		}
		else{
			
			//draw pic
            //ImageIcon im = new ImageIcon(itemBaseFilepath + t.getType() + ".png");
            ImageIcon im = new ImageIcon("./src/res/items/takeable/" + itemNode.item.getPathToPicture());
			g.drawImage(im.getImage(),xpos , ypos, this.itemIconWidth ,this.itemIconHight ,null);

			
			//draw amount
			g.setFont(ITEM_INFO_FONT);
			FontMetrics fm = g.getFontMetrics();
			int width = fm.stringWidth(itemNode.amount + "");
			int height = fm.getHeight();
			g.setColor(new Color(200,200,0));
			g.fillRect(xpos, ypos, width, height);
			g.setColor(Color.BLACK);
			g.drawString(itemNode.amount + "", xpos, ypos + (int)(fm.getHeight()*0.8));
		}
		
	}

}
