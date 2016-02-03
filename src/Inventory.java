import java.util.*;

/**
 * Created by Austin on 2/2/16.
 */
public class Inventory {

	HashMap<Item, Integer> items = new HashMap<Item, Integer>();
	
	public void addItem(Item newItem) {
		
		Integer removedValue = items.remove(newItem);
		
		if (removedValue == null) {
			
			removedValue = new Integer(0);
		
		}
		
		items.put(newItem, removedValue + 1);
		
	}
	
}
