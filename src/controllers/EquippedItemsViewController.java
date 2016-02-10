package controllers;

import models.EquippedItems;
import models.items.TakeableItem;
import utilities.IOMediator;
import views.GameView;
import views.View;

import java.awt.event.KeyEvent;

/**
 * Created by sergiopuleri on 2/5/16.
 */
public class EquippedItemsViewController extends ViewController {


    public enum EquippedItemOptSelections {

        CAPE("Cape") {
            protected void unequip() {
                unequipSlot(EquippedItems.ArmorComponent.CAPE);
            }

            public String getImagePath() {
                if (IOMediator.avatar.getEquippedItems().getCape() != null)
                    return IOMediator.avatar.getEquippedItems().getCape().getPathToPicture();
                else return "";
            }

            public TakeableItem getComponent() {
                return EquippedItems.ArmorComponent.CAPE.getCurrentEquippedItem(IOMediator.avatar);
            }
        },
        HEAD("Head") {
            protected void unequip() {
                unequipSlot(EquippedItems.ArmorComponent.HEAD);
            }

            public String getImagePath() {
                if (IOMediator.avatar.getEquippedItems().getHead() != null)
                    return IOMediator.avatar.getEquippedItems().getHead().getPathToPicture();
                else return "";
            }

            public TakeableItem getComponent() {
                return EquippedItems.ArmorComponent.HEAD.getCurrentEquippedItem(IOMediator.avatar);
            }
        },
        NECKLACE("Necklace") {
            protected void unequip() {
                unequipSlot(EquippedItems.ArmorComponent.NECKLACE);
            }

            public String getImagePath() {
                if (IOMediator.avatar.getEquippedItems().getNecklace() != null)
                    return IOMediator.avatar.getEquippedItems().getNecklace().getPathToPicture();
                else return "";
            }

            public TakeableItem getComponent() {
                return EquippedItems.ArmorComponent.NECKLACE.getCurrentEquippedItem(IOMediator.avatar);
            }
        },
        PRIMARY("Primary Weapon") {
            protected void unequip() {
                unequipSlot(EquippedItems.ArmorComponent.PRIMARY_WEAPON);
            }

            public String getImagePath() {
                if (IOMediator.avatar.getEquippedItems().getPrimaryWeapon() != null)
                    return IOMediator.avatar.getEquippedItems().getPrimaryWeapon().getPathToPicture();
                else return "";
            }

            public TakeableItem getComponent() {
                return EquippedItems.ArmorComponent.PRIMARY_WEAPON.getCurrentEquippedItem(IOMediator.avatar);
            }
        },
        CHEST("Chest") {
            protected void unequip() {
                unequipSlot(EquippedItems.ArmorComponent.CHEST);
            }

            public String getImagePath() {
                if (IOMediator.avatar.getEquippedItems().getChest() != null)
                    return IOMediator.avatar.getEquippedItems().getChest().getPathToPicture();
                else return "";
            }

            public TakeableItem getComponent() {
                return EquippedItems.ArmorComponent.CHEST.getCurrentEquippedItem(IOMediator.avatar);
            }
        },
        SECONDARY("Secondary Weapon") {
            protected void unequip() {
                unequipSlot(EquippedItems.ArmorComponent.SECONDARY_WEAPON);
            }

            public String getImagePath() {
                if (IOMediator.avatar.getEquippedItems().getSecondaryWeapon() != null)
                    return IOMediator.avatar.getEquippedItems().getSecondaryWeapon().getPathToPicture();
                else return "";
            }

            public TakeableItem getComponent() {
                return EquippedItems.ArmorComponent.SECONDARY_WEAPON.getCurrentEquippedItem(IOMediator.avatar);
            }
        },
        GLOVES("Gloves") {
            protected void unequip() {
                unequipSlot(EquippedItems.ArmorComponent.GLOVES);
            }

            public String getImagePath() {
                if (IOMediator.avatar.getEquippedItems().getGloves() != null)
                    return IOMediator.avatar.getEquippedItems().getGloves().getPathToPicture();
                else return "";
            }

            public TakeableItem getComponent() {
                return EquippedItems.ArmorComponent.GLOVES.getCurrentEquippedItem(IOMediator.avatar);
            }
        },
        GREAVES("Greaves") {
            protected void unequip() {
                unequipSlot(EquippedItems.ArmorComponent.GREAVES);
            }

            public String getImagePath() {
                if (IOMediator.avatar.getEquippedItems().getGreaves() != null)
                    return IOMediator.avatar.getEquippedItems().getGreaves().getPathToPicture();
                else return "";
            }

            public TakeableItem getComponent() {
                return EquippedItems.ArmorComponent.GREAVES.getCurrentEquippedItem(IOMediator.avatar);
            }
        },
        BOOTS("Boots") {
            protected void unequip() {
                unequipSlot(EquippedItems.ArmorComponent.BOOTS);
            }

            public String getImagePath() {
                if (IOMediator.avatar.getEquippedItems().getBoots() != null)
                    return IOMediator.avatar.getEquippedItems().getBoots().getPathToPicture();
                else return "";
            }

            public TakeableItem getComponent() {
                return EquippedItems.ArmorComponent.BOOTS.getCurrentEquippedItem(IOMediator.avatar);
            }
        };

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
            } else {
                return EquippedItemOptSelections.values()[this.ordinal() - 1];
            }
        }

        protected EquippedItemOptSelections next() {
            if (this.ordinal() == EquippedItemOptSelections.values().length - 1) {
                return EquippedItemOptSelections.values()[0];
            } else {
                return EquippedItemOptSelections.values()[this.ordinal() + 1];
            }
        }

        public String getText() {
            return text;
        }

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


        TakeableItem itemInSlot = component.getCurrentEquippedItem(IOMediator.avatar);

        if (itemInSlot != null) {

            component.unequipComponent(IOMediator.avatar);
            itemInSlot.modifyStatsReverse(IOMediator.avatar);
            IOMediator.avatar.getInventory().addItem(itemInSlot);

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
        } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_RIGHT) {
            System.out.println("Down pressed FROM EIVC");
            selectedItem = selectedItem.next();
        } else if (key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_R) {
            ((GameView) IOMediator.Views.GAME.getView()).setShowEquippedItems(false);
        } else if (key == KeyEvent.VK_I) {
            ((GameView) IOMediator.Views.GAME.getView()).setShowEquippedItems(false);
            ((GameView) IOMediator.Views.GAME.getView()).setShowInventory(true);
        }

    }

    @Override
    public void handleKeyRelease(int key) {

    }
}
