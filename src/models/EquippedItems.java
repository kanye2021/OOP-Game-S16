package models;

import models.items.TakeableItem;
/**
 * Created by ben on 2/5/16.
 */
public class EquippedItems{
    private TakeableItem head;
    private TakeableItem chest;
    private TakeableItem legs;
    private TakeableItem boots;
    private TakeableItem primaryWeapon;
    private TakeableItem secondaryWeapon;
    private TakeableItem cape;
    private TakeableItem gloves;
    private TakeableItem necklace;
    //need to put avatar to have the Equipped items object
    public EquippedItems(){//constructor not really needed
        head = null;
        chest = null;
        legs = null;
        boots = null;
        primaryWeapon = null;
        secondaryWeapon = null;
        cape = null;
        gloves = null;
        necklace = null;
    }
    public void equipItems(TakeableItem item){//ItemsStatsAssociation handles error checking
        if(TakeableItem.Items.values()[item.getID()].getComponent() == "head"){
            equipHead(item);
        }
        else if(TakeableItem.Items.values()[item.getID()].getComponent() == "chest"){
            equipChest(item);
        }
        else if(TakeableItem.Items.values()[item.getID()].getComponent() == "legs"){
            equipLegs(item);
        }
        else if(TakeableItem.Items.values()[item.getID()].getComponent() == "boots"){
            equipBoots(item);
        }
        else if(TakeableItem.Items.values()[item.getID()].getComponent() == "primaryWeapon"){
            equipPrimaryWeapon(item);
        }
        else if(TakeableItem.Items.values()[item.getID()].getComponent() == "secondaryWeapon"){
            equipSecondaryWeapon(item);
        }
        else if(TakeableItem.Items.values()[item.getID()].getComponent() == "cape"){
            equipCape(item);
        }
        else if(TakeableItem.Items.values()[item.getID()].getComponent() == "gloves"){
            equipGloves(item);
        }
        else if(TakeableItem.Items.values()[item.getID()].getComponent() == "necklace"){
            equipNecklace(item);
        }
        else{
            System.out.println("I already have a primary weapon");
            System.out.println("How did you manage to get here?");
        }
    }

    public void unequipItems(TakeableItem item){//typeE means type of Equipment
        if(TakeableItem.Items.values()[item.getID()].getComponent() == "head"){
            unequipHead(item);
        }
        else if(TakeableItem.Items.values()[item.getID()].getComponent() == "chest"){
            unequipChest(item);
        }
        else if(TakeableItem.Items.values()[item.getID()].getComponent() == "legs"){
            unequipLegs(item);
        }
        else if(TakeableItem.Items.values()[item.getID()].getComponent() == "boots"){
            unequipBoots(item);
        }
        else if(TakeableItem.Items.values()[item.getID()].getComponent() == "primaryWeapon"){
            unequipPrimaryWeapon(item);
        }
        else if(TakeableItem.Items.values()[item.getID()].getComponent() == "secondaryWeapon"){
            unequipSecondaryWeapon(item);
        }
        else if(TakeableItem.Items.values()[item.getID()].getComponent() == "cape"){
            unequipCape(item);
        }
        else if(TakeableItem.Items.values()[item.getID()].getComponent() == "gloves"){
            unequipGloves(item);
        }
        else if(TakeableItem.Items.values()[item.getID()].getComponent() == "necklace"){
            unequipNecklace(item);
        }
        else{
            System.out.println("How did you manage to get here?");
        }
    }

    public TakeableItem checkItem(TakeableItem item){
        if(TakeableItem.Items.values()[item.getID()].getComponent() == "head"){
            return head;
        }
        else if(TakeableItem.Items.values()[item.getID()].getComponent() == "chest"){
            return chest;
        }
        else if(TakeableItem.Items.values()[item.getID()].getComponent() == "legs"){
            return legs;
        }
        else if(TakeableItem.Items.values()[item.getID()].getComponent() == "boots"){
            return boots;
        }
        else if(TakeableItem.Items.values()[item.getID()].getComponent() == "primaryWeapon"){
            return primaryWeapon;
        }
        else if(TakeableItem.Items.values()[item.getID()].getComponent() == "secondaryWeapon"){
            return secondaryWeapon;
        }
        else if(TakeableItem.Items.values()[item.getID()].getComponent() == "cape"){
            return cape;
        }
        else if(TakeableItem.Items.values()[item.getID()].getComponent() == "gloves"){
            return gloves;
        }
        else if(TakeableItem.Items.values()[item.getID()].getComponent() == "necklace"){
            return necklace;
        }
        else{

            System.out.println("How did you manage to get here?");
            return null;
        }
    }
    //true means something is there
    //false means something it is empty
    //commented out for now
    /*
    public TakeableItem checkHead(TakeableItem item){return head;}
    public TakeableItem checkChest(TakeableItem item){return chest;}
    public TakeableItem checkLegs(TakeableItem item){return legs;}
    public TakeableItem checkBoots(TakeableItem item){return boots;}
    public TakeableItem checkPrimaryWeapon(TakeableItem item){return primaryWeapon;}
    public TakeableItem checkSecondaryWeapon(TakeableItem item){return secondaryWeapon;}
    public TakeableItem checkCape(TakeableItem item){return cape;}
    public TakeableItem checkGloves(TakeableItem item){return gloves;}
    public TakeableItem checkNecklace(TakeableItem item){return necklace;}
   */
    public void equipHead(TakeableItem item){head = item;}
    public void equipChest(TakeableItem item){chest = item;}
    public void equipLegs(TakeableItem item){legs = item;}
    public void equipBoots(TakeableItem item){boots = item;}
    public void equipPrimaryWeapon(TakeableItem item){primaryWeapon = item;}
    public void equipSecondaryWeapon(TakeableItem item){secondaryWeapon = item;}
    public void equipCape(TakeableItem item){cape = item;}
    public void equipGloves(TakeableItem item){gloves = item;}
    public void equipNecklace(TakeableItem item){necklace = item;}

    public TakeableItem getHead(){
        return head;
    }

    public TakeableItem getBoots() {
        return boots;
    }

    public TakeableItem getCape() {
        return cape;
    }

    public TakeableItem getChest() {
        return chest;
    }

    public TakeableItem getGloves() {
        return gloves;
    }

    public TakeableItem getLegs() {
        return legs;
    }

    public TakeableItem getNecklace() {
        return necklace;
    }

    public TakeableItem getPrimaryWeapon() {
        return primaryWeapon;
    }

    public TakeableItem getSecondaryWeapon() {
        return secondaryWeapon;
    }

    public void unequipHead(TakeableItem item){head = null;}
    public void unequipChest(TakeableItem item){chest = null;}
    public void unequipLegs(TakeableItem item){legs = null;}
    public void unequipBoots(TakeableItem item){boots = null;}
    public void unequipPrimaryWeapon(TakeableItem item){primaryWeapon = null;}
    public void unequipSecondaryWeapon(TakeableItem item){secondaryWeapon = null;}
    public void unequipCape(TakeableItem item){cape = null;}
    public void unequipGloves(TakeableItem item){gloves = null;}
    public void unequipNecklace(TakeableItem item){necklace = null;}

//function for avatar called get equipped items

}
