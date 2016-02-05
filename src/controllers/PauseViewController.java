package controllers;

import utilities.IOMediator;
import views.View;

import java.awt.event.KeyEvent;

/**
 * Created by Austin on 2/2/16.
 */
public class PauseViewController extends ViewController {

	// This enum represents the menu options available on this screen. The setView() function maps to an individual view
	// such as utilities.IOMediator.Views.GAME;
	
	public enum MenuOptions {
		RESUME("Resume Game") {protected void setView() {IOMediator.setActiveView(IOMediator.Views.GAME);};}, 
		OPTIONS("Options") {protected void setView() {IOMediator.setActiveView(IOMediator.Views.UNIMPLEMENTED);};},
		LOAD_GAME("Load Game") {protected void setView() {IOMediator.setActiveView(IOMediator.Views.LOAD);};},
		SAVE_GAME("Save Game") {protected void setView() {IOMediator.setActiveView(IOMediator.Views.SAVE);};},
		DAVE("Dave") {protected void setView() {IOMediator.setActiveView(IOMediator.Views.START_MENU);};},
		EXIT_GAME("Exit Game") {protected void setView() {IOMediator.setActiveView(IOMediator.Views.START_MENU);};};
		
		private String s;
		
		protected abstract void setView();
		
		private MenuOptions(String s) {
			
			this.s = s;
			
		}
		
		protected MenuOptions previous() {
	    	
	    	if (this.ordinal() == 0) {
	    		
	    		return MenuOptions.values()[MenuOptions.values().length - 1];
	    		
	    	}
	    	
	    	else {
	    	
	    		return MenuOptions.values()[this.ordinal() - 1];
	    	
	    	}
	    	
	    }
	    
	    protected MenuOptions next() {
	    	
	    	if (this.ordinal() == MenuOptions.values().length - 1) {
	    		
	    		return MenuOptions.values()[0];
	    		
	    	}
	    	
	    	else {
	    	
	    		return MenuOptions.values()[this.ordinal() + 1];
	    	
	    	}
	    	
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
            option = option.previous();
        }

        else if (key == KeyEvent.VK_DOWN) {
            option = option.next();
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
    
}
