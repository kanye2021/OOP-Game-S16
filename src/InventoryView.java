import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Created by Austin on 2/2/16.
 */
public class InventoryView extends View {

	Inventory currentInventory;
	int itemHeight = 25;
	
	public InventoryView(Inventory inventory) {
		super();
        this.viewController = new InventoryController(this);
		currentInventory = inventory;
	}
	
	@Override
	public void render(Graphics g) {
		
		BufferedImage overImage = new BufferedImage(B_WIDTH, B_HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics g2 = overImage.getGraphics();
		
		int item = 0;
		
		for (Map.Entry<Item, Integer> entry : currentInventory.items.entrySet()) {
			
			System.out.println(entry.getKey() + ": " + entry.getValue());
			g2.setColor(new Color(150, 150, 150));
			g2.fillRect(0, item * itemHeight, B_WIDTH, itemHeight);
			g2.setColor(new Color(0, 0, 0));
			g2.drawString(entry.getKey().getName(), 10, (item * itemHeight) + (int) (itemHeight * 0.65));
			g2.drawString("x" + entry.getValue(), (int) (B_WIDTH * 0.90), (item * itemHeight) + (int) (itemHeight * 0.65));
			g2.drawRect(0, item * itemHeight, B_WIDTH, itemHeight);
			item++;
		
		}
		
		g.drawImage(overImage, (int) (B_WIDTH * 0.1), (int) (B_HEIGHT * 0.1), (int) (B_WIDTH * 0.8), (int) (B_HEIGHT * 0.8), null);
		
	}

	public void setInventory(Inventory inventory) {
		
		currentInventory = inventory;
	
	}
	
}
