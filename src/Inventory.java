import java.util.*;

/**
 * Created by Austin on 2/2/16.
 */
public class Inventory {

	HashMap<Item, Integer> items = new HashMap<Item, Integer>();
	
	public void addItem(Item newItem) {
		
		if (items.containsKey(newItem)) {
			
			items.put(newItem, items.get(newItem) + 1);

		}
		
		else {
			
			items.put(newItem, 1);

		}

	}
	
}
