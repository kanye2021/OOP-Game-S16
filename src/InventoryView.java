import java.awt.Color;
import java.awt.Graphics;
import java.util.Map;


public class InventoryView extends View {

	Inventory currentInventory;
	int itemHeight = 25;
	
	public InventoryView(Inventory inventory) {
		currentInventory = inventory;
	}
	
	@Override
	public void render(Graphics g) {
		
		int item = 0;
		
		for (Map.Entry<Item, Integer> entry : currentInventory.items.entrySet()) {
			
			System.out.println(entry.getKey() + ": " + entry.getValue());
			g.setColor(new Color(150, 150, 150));
			g.fillRect(0, item * itemHeight, B_WIDTH, itemHeight);
			g.setColor(new Color(0, 0, 0));
			g.drawString(entry.getKey().getName(), 10, (item * itemHeight) + (int) (itemHeight * 0.65));
			g.drawString("x" + entry.getValue(), (int) (B_WIDTH * 0.90), (item * itemHeight) + (int) (itemHeight * 0.65));
			g.drawRect(0, item * itemHeight, B_WIDTH, itemHeight);
			item++;
		
		}
		
	}

	public void setInventory(Inventory inventory) {
		
		currentInventory = inventory;
	
	}
	
}
