import java.awt.event.KeyEvent;

/**
 * Created by Austin on 2/2/16.
 */
public class PauseViewController extends ViewController {

	public enum MenuOptions {
		RESUME("Resume Game") {protected void setView() {IOMediator.setActiveView(IOMediator.Views.GAME);};}, 
		OPTIONS("Options") {protected void setView() {IOMediator.setActiveView(IOMediator.Views.GAME);};},
		LOAD_GAME("Load Game") {protected void setView() {IOMediator.setActiveView(IOMediator.Views.GAME);};},
		SAVE_GAME("Save Game") {protected void setView() {IOMediator.setActiveView(IOMediator.Views.GAME);};},
		DAVE("Dave") {protected void setView() {IOMediator.setActiveView(IOMediator.Views.START_MENU);};},
		EXIT_GAME("Exit Game") {protected void setView() {IOMediator.setActiveView(IOMediator.Views.START_MENU);};};
		
		private String s;
		
		protected abstract void setView();
		
		private MenuOptions(String s) {
			
			this.s = s;
			
		}
		
		public String toString() {
			
			return s;
			
		}
		
	}
	
	MenuOptions option;
	
    public PauseViewController(View view) {
        super(view);
        option = MenuOptions.RESUME;
    }

    public MenuOptions getActiveItem() {
    	
    	return option;
    	
    }
    
    @Override
    public void handleKeyPress(int key) {

        if (key == KeyEvent.VK_UP) {
            System.out.println("Up pressed FROM PVC");
            previousItem();
        }

        else if (key == KeyEvent.VK_DOWN) {
            System.out.println("Down pressed FROM PVC");
            nextItem();
        }

        else if (key == KeyEvent.VK_ESCAPE) {
        	IOMediator.setActiveView(IOMediator.Views.GAME);
        }
    
        else if (key == KeyEvent.VK_ENTER) {
        	option.setView();
        }
        
    }
    
    @Override
    public void handleKeyRelease(int key) {

    }
    
    private void previousItem() {
    	
    	if (option.ordinal() == 0) {
    		
    		option = MenuOptions.values()[MenuOptions.values().length - 1];
    		
    	}
    	
    	else {
    	
    		option = MenuOptions.values()[option.ordinal() - 1];
    	
    	}
    
    	System.out.println("Option set to: " + option.toString());
    	
    }
    
    private void nextItem() {
    	
    	if (option.ordinal() == MenuOptions.values().length - 1) {
    		
    		option = MenuOptions.values()[0];
    		
    	}
    	
    	else {
    	
    		option = MenuOptions.values()[option.ordinal() + 1];
    	
    	}
    	
    	System.out.println("Option set to: " + option.toString());
    	
    }
    
}
