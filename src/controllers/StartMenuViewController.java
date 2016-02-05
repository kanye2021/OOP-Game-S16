package controllers;

import utilities.IOMediator;
import views.View;

import java.awt.event.*;

/**
 * Created by sergiopuleri on 2/1/16.
 */
public class StartMenuViewController extends ViewController {

	
	// This enum represents the menu options available on this screen. The setView() function maps to an individual view
	// such as utilities.IOMediator.Views.CREATE_GAME;
	public enum MenuOptions {
		CREATE_GAME("Create Game") {protected void setView() {IOMediator.setActiveView(IOMediator.Views.CREATE_GAME);};},
		//INVENTORY("Open Inventory") {protected void setView() {IOMediator.setActiveView(IOMediator.Views.INVENTORY);};},
		//PAUSE("Open Pause Menu") {protected void setView() {IOMediator.setActiveView(IOMediator.Views.PAUSE);};},
		LOAD_GAME("Load Game") {protected void setView() {IOMediator.setActiveView(IOMediator.Views.UNIMPLEMENTED);};},
		EXIT_GAME("Exit Game") {protected void setView() {IOMediator.setActiveView(IOMediator.Views.UNIMPLEMENTED);};};
		
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

    private MenuOptions option;

    public StartMenuViewController(View view) {
        super(view);
        option = MenuOptions.CREATE_GAME;
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

        else if (key == KeyEvent.VK_ENTER) {
            
        	option.setView();
            
        }
    }

    @Override
    public void handleKeyRelease(int key) {

    }
}
