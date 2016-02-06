package controllers;

import utilities.IOMediator;
import views.GameView;
import views.View;

import java.awt.event.KeyEvent;

/**
 * Created by sergiopuleri on 2/5/16.
 */
public class EquippedItemsViewController extends ViewController{

    public enum EquippedItemOptSelections {
        CAPE("Cape") {protected void unequip(){unequipSlot(EquippedItemOptSelections.CAPE);};},
        HEAD("Head") {protected void unequip(){unequipSlot(EquippedItemOptSelections.HEAD);};},
        NECKLACE("Necklace") {protected void unequip(){unequipSlot(EquippedItemOptSelections.NECKLACE);};},
        PRIMARY("Primary Weapon") {protected void unequip(){unequipSlot(EquippedItemOptSelections.PRIMARY);};},
        CHEST("Chest") {protected void unequip(){unequipSlot(EquippedItemOptSelections.PRIMARY);};},
        SECONDARY("Secondary Weapon") {protected void unequip(){unequipSlot(EquippedItemOptSelections.SECONDARY);};},
        GLOVES("Gloves") {protected void unequip(){unequipSlot(EquippedItemOptSelections.GLOVES);};},
        LEGS("Legs") {protected void unequip(){unequipSlot(EquippedItemOptSelections.LEGS);};},
        BOOTS("Boots") {protected void unequip(){unequipSlot(EquippedItemOptSelections.BOOTS);};};

        private String text;


        protected abstract void unequip();

        private EquippedItemOptSelections(String text) {
            this.text = text;
        }

        protected EquippedItemOptSelections previous() {
            if (this.ordinal() == 0) {
                return EquippedItemOptSelections.values()[EquippedItemOptSelections.values().length - 1];
            }
            else {
                return EquippedItemOptSelections.values()[this.ordinal() - 1];
            }
        }

        protected EquippedItemOptSelections next() {
            if (this.ordinal() == EquippedItemOptSelections.values().length - 1) {
                return EquippedItemOptSelections.values()[0];
            }
            else {
                return EquippedItemOptSelections.values()[this.ordinal() + 1];
            }
        }
        public String getText() { return text; }

    }
    
    public EquippedItemsViewController(View v) {
        super(v);
        selectedItem = EquippedItemOptSelections.CAPE;
    }

    private EquippedItemOptSelections selectedItem;

    public EquippedItemOptSelections getSelectedItem() {
        return selectedItem;
    }

    public static void unequipSlot(EquippedItemOptSelections selection) {
        switch (selection) {
            case CAPE:
                //unequip cape
                break;
            case HEAD:
                //unequip head
                break;
            case NECKLACE:
                //unequip NECKLACE
                break;
            case PRIMARY:
                //unequip
                break;
            case CHEST:
                //unequip
                break;
            case SECONDARY:
                //unequip
                break;
            case GLOVES:
                break;
            case LEGS:
                break;
            case BOOTS:
                break;

        }
    }


    @Override
    public void handleKeyPress(int key) {


        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_LEFT) {
            System.out.println("Up pressed FROM EIVC");
            selectedItem = selectedItem.previous();
        }

        else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_RIGHT) {
            System.out.println("Down pressed FROM EIVC");
            selectedItem = selectedItem.next();
        }

        else if (key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_R ) {
            ((GameView)IOMediator.Views.GAME.getView()).setShowEquippedItems(false);
        }

    }

    @Override
    public void handleKeyRelease(int key) {

    }
}
