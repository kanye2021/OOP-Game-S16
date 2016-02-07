package controllers;

import models.EquippedItems;
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
        HEAD("Head") {protected void unequip(){unequipSlot(EquippedItemOptSelections.HEAD);} public String getImagePath(){if(IOMediator.entity.getEquippedItems().getHead()!=null)return IOMediator.entity.getEquippedItems().getHead().getPathToPicture(); else return "";};},
        CAPE("Cape") {protected void unequip(){unequipSlot(EquippedItemOptSelections.CAPE);} public String getImagePath(){if(IOMediator.entity.getEquippedItems().getCape()!=null)return IOMediator.entity.getEquippedItems().getCape().getPathToPicture(); else return "";};},
        NECKLACE("Necklace") {protected void unequip(){unequipSlot(EquippedItemOptSelections.NECKLACE);} public String getImagePath(){if(IOMediator.entity.getEquippedItems().getNecklace()!=null)return IOMediator.entity.getEquippedItems().getNecklace().getPathToPicture(); else return "";};},
        PRIMARY("Primary Weapon") {protected void unequip(){unequipSlot(EquippedItemOptSelections.PRIMARY);}public String getImagePath(){if(IOMediator.entity.getEquippedItems().getPrimaryWeapon()!=null)return IOMediator.entity.getEquippedItems().getPrimaryWeapon().getPathToPicture(); else return "";};},
        CHEST("Chest") {protected void unequip(){unequipSlot(EquippedItemOptSelections.CHEST);}public String getImagePath(){if(IOMediator.entity.getEquippedItems().getChest()!=null)return IOMediator.entity.getEquippedItems().getChest().getPathToPicture(); else return ""; };},
        SECONDARY("Secondary Weapon") {protected void unequip(){unequipSlot(EquippedItemOptSelections.SECONDARY);}public String getImagePath(){if(IOMediator.entity.getEquippedItems().getSecondaryWeapon()!=null) return IOMediator.entity.getEquippedItems().getSecondaryWeapon().getPathToPicture();else return "";};},
        GLOVES("Gloves") {protected void unequip(){unequipSlot(EquippedItemOptSelections.GLOVES);}public String getImagePath(){if(IOMediator.entity.getEquippedItems().getGloves()!=null)return IOMediator.entity.getEquippedItems().getGloves().getPathToPicture();else return "";};},
        LEGS("Legs") {protected void unequip(){unequipSlot(EquippedItemOptSelections.LEGS);}public String getImagePath(){if(IOMediator.entity.getEquippedItems().getLegs()!=null)return IOMediator.entity.getEquippedItems().getLegs().getPathToPicture();else return "";};},
        BOOTS("Boots") {protected void unequip(){unequipSlot(EquippedItemOptSelections.BOOTS);}public String getImagePath(){if(IOMediator.entity.getEquippedItems().getBoots()!=null)return IOMediator.entity.getEquippedItems().getBoots().getPathToPicture();else return "";};};

        private String text;


        protected abstract void unequip();
        public abstract String getImagePath();

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
        // TODO: When I call unequip, it just EquippedItems just sets to null.
        // It needs to return that slot to inventory.
        switch (selection) {
            case CAPE:
                //unequip cape
                IOMediator.entity.getEquippedItems().unequipCape(null);
                break;
            case HEAD:
                //unequip head
                IOMediator.entity.getEquippedItems().unequipHead(null);
                break;
            case NECKLACE:
                //unequip NECKLACE
                IOMediator.entity.getEquippedItems().unequipNecklace(null);
                break;
            case PRIMARY:
                //unequip
                IOMediator.entity.getEquippedItems().unequipPrimaryWeapon(null);
                break;
            case CHEST:
                //unequip
                IOMediator.entity.getEquippedItems().unequipChest(null);
                break;
            case SECONDARY:
                //unequip
                IOMediator.entity.getEquippedItems().unequipSecondaryWeapon(null);
                break;
            case GLOVES:
                IOMediator.entity.getEquippedItems().unequipGloves(null);
                break;
            case LEGS:
                IOMediator.entity.getEquippedItems().unequipLegs(null);
                break;
            case BOOTS:
                IOMediator.entity.getEquippedItems().unequipBoots(null);
                break;

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
