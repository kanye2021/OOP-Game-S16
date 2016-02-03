import java.awt.event.KeyEvent;

/**
 * Created by Austin on 2/2/16.
 */
public class InventoryController extends ViewController {

	int position = 0;
	Inventory inventory;

    public InventoryController(View view, Inventory inventory) {
        super(view);
        this.inventory = inventory;
    }

    public int getActiveItem() {
    	
    	return position;
    	
    }
    
    @Override
    public void handleKeyPress(int key) {

        if (key == KeyEvent.VK_UP) {
            System.out.println("Up pressed FROM IVC");
            previousItem();
        }

        else if (key == KeyEvent.VK_DOWN) {
            System.out.println("Down pressed FROM IVC");
            nextItem();
        }

        else if (key == KeyEvent.VK_ESCAPE) {
        	IOMediator.setActiveView(IOMediator.Views.GAME);
        }
        
    }
    
    @Override
    public void handleKeyRelease(int key) {

    }
    
    private void previousItem() {
    	
    	position--;
    	
    	if (position < 0) {
    		
    		position = inventory.items.size() - 1;
    		
    	}
    	
    }
    
    private void nextItem() {
    	
    	position++;
    	
    	if (position > inventory.items.size() - 1) {
    		
    		
    		position = 0;
    	
    	}
    	
    }
    
}
