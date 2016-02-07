package controllers;

import models.EquippedItems;
import models.items.TakeableItem;
import utilities.IOMediator;
import views.GameView;
import views.View;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

/**
 * Created by sergiopuleri on 2/5/16.
 */
public class EquippedItemsViewController extends ViewController{


    public enum EquippedItemOptSelections {
        HEAD("Head") {protected void unequip(){unequipSlot(EquippedItems.ArmorComponent.HEAD);} public String getImagePath(){if(IOMediator.entity.getEquippedItems().getHead()!=null)return IOMediator.entity.getEquippedItems().getHead().getPathToPicture(); else return "";}
            public TakeableItem getComponent() {return EquippedItems.ArmorComponent.HEAD.getCurrentEquippedItem(IOMediator.entity);}},
        CAPE("Cape") {protected void unequip(){unequipSlot(EquippedItems.ArmorComponent.CAPE);} public String getImagePath(){if(IOMediator.entity.getEquippedItems().getCape()!=null)return IOMediator.entity.getEquippedItems().getCape().getPathToPicture(); else return "";}
            public TakeableItem getComponent() {return EquippedItems.ArmorComponent.CAPE.getCurrentEquippedItem(IOMediator.entity);}},
        NECKLACE("Necklace") {protected void unequip(){unequipSlot(EquippedItems.ArmorComponent.NECKLACE);} public String getImagePath(){if(IOMediator.entity.getEquippedItems().getNecklace()!=null)return IOMediator.entity.getEquippedItems().getNecklace().getPathToPicture(); else return "";}
            public TakeableItem getComponent() {return EquippedItems.ArmorComponent.NECKLACE.getCurrentEquippedItem(IOMediator.entity);}},
        PRIMARY("Primary Weapon") {protected void unequip(){unequipSlot(EquippedItems.ArmorComponent.PRIMARY_WEAPON);}public String getImagePath(){if(IOMediator.entity.getEquippedItems().getPrimaryWeapon()!=null)return IOMediator.entity.getEquippedItems().getPrimaryWeapon().getPathToPicture(); else return "";}
            public TakeableItem getComponent() {return EquippedItems.ArmorComponent.PRIMARY_WEAPON.getCurrentEquippedItem(IOMediator.entity);}},
        CHEST("Chest") {protected void unequip(){unequipSlot(EquippedItems.ArmorComponent.CHEST);}public String getImagePath(){if(IOMediator.entity.getEquippedItems().getChest()!=null)return IOMediator.entity.getEquippedItems().getChest().getPathToPicture(); else return ""; }
            public TakeableItem getComponent() {return EquippedItems.ArmorComponent.CHEST.getCurrentEquippedItem(IOMediator.entity);}},
        SECONDARY("Secondary Weapon") {protected void unequip(){unequipSlot(EquippedItems.ArmorComponent.SECONDARY_WEAPON);}public String getImagePath(){if(IOMediator.entity.getEquippedItems().getSecondaryWeapon()!=null) return IOMediator.entity.getEquippedItems().getSecondaryWeapon().getPathToPicture();else return "";}
            public TakeableItem getComponent() {return EquippedItems.ArmorComponent.SECONDARY_WEAPON.getCurrentEquippedItem(IOMediator.entity);}},
        GLOVES("Gloves") {protected void unequip(){unequipSlot(EquippedItems.ArmorComponent.GLOVES);}public String getImagePath(){if(IOMediator.entity.getEquippedItems().getGloves()!=null)return IOMediator.entity.getEquippedItems().getGloves().getPathToPicture();else return "";}
            public TakeableItem getComponent() {return EquippedItems.ArmorComponent.GLOVES.getCurrentEquippedItem(IOMediator.entity);}},
        GREAVES("Greaves") {protected void unequip(){unequipSlot(EquippedItems.ArmorComponent.GREAVES);}public String getImagePath(){if(IOMediator.entity.getEquippedItems().getGreaves()!=null)return IOMediator.entity.getEquippedItems().getGreaves().getPathToPicture();else return "";}
            public TakeableItem getComponent() {return EquippedItems.ArmorComponent.GREAVES.getCurrentEquippedItem(IOMediator.entity);}},
        BOOTS("Boots") {protected void unequip(){unequipSlot(EquippedItems.ArmorComponent.BOOTS);}public String getImagePath(){if(IOMediator.entity.getEquippedItems().getBoots()!=null)return IOMediator.entity.getEquippedItems().getBoots().getPathToPicture();else return "";}
            public TakeableItem getComponent() {return EquippedItems.ArmorComponent.BOOTS.getCurrentEquippedItem(IOMediator.entity);}};

        private String text;

        protected abstract void unequip();
        public abstract String getImagePath();
        public abstract TakeableItem getComponent();

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
        selectedItem = EquippedItemOptSelections.HEAD;
    }

    private EquippedItemOptSelections selectedItem;

    public EquippedItemOptSelections getSelectedItem() {
        return selectedItem;
    }

    public static void unequipSlot(EquippedItems.ArmorComponent component) {


    	TakeableItem itemInSlot = component.getCurrentEquippedItem(IOMediator.entity);
    	
    	if (itemInSlot != null) {
    		
    		component.unequipComponent(IOMediator.entity);
    		itemInSlot.modifyStatsReverse(IOMediator.entity);
    		IOMediator.entity.getInventory().addItem(itemInSlot);
    		
    	}
    	
    }


    @Override
    public void handleKeyPress(int key) {


        if (key == KeyEvent.VK_ENTER) {
            System.out.println("Up pressed FROM EIVC");
            selectedItem.unequip();
        }
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
