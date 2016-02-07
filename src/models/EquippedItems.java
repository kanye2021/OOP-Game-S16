package models;

import models.items.TakeableItem;
/**
 * Created by ben on 2/5/16.
 */

public class EquippedItems{

	public static enum Equippable {

		YES(true),
		NO(false);

		private boolean isEquippable;

		private Equippable(boolean isEquippable) {

			this.isEquippable = isEquippable;

		}

		public boolean getEquippable() {

			return isEquippable;

		}

	}

	public static enum ArmorComponent {

		HEAD {
            public TakeableItem getCurrentEquippedItem(Entity entity) {return entity.getEquippedItems().getHead();};
            public void equipComponent(Entity entity, TakeableItem item) {entity.getEquippedItems().equipHead(item);};
            public void unequipComponent(Entity entity) {entity.getEquippedItems().unequipHead();};
        },
		CHEST {
        	public TakeableItem getCurrentEquippedItem(Entity entity) {return entity.getEquippedItems().getChest();};
        	public void equipComponent(Entity entity, TakeableItem item) {entity.getEquippedItems().equipChest(item);};
        	public void unequipComponent(Entity entity) {entity.getEquippedItems().unequipChest();};
        },
		GREAVES {
        	public TakeableItem getCurrentEquippedItem(Entity entity) {return entity.getEquippedItems().getGreaves();};
            public void equipComponent(Entity entity, TakeableItem item) {entity.getEquippedItems().equipGreaves(item);};
            public void unequipComponent(Entity entity) {entity.getEquippedItems().unequipGreaves();};
        },
		BOOTS {
        	public TakeableItem getCurrentEquippedItem(Entity entity) {return entity.getEquippedItems().getBoots();};
        	public void equipComponent(Entity entity, TakeableItem item) {entity.getEquippedItems().equipBoots(item);};
        	public void unequipComponent(Entity entity) {entity.getEquippedItems().unequipBoots();};
        },
		PRIMARY_WEAPON {
        	public TakeableItem getCurrentEquippedItem(Entity entity) {return entity.getEquippedItems().getPrimaryWeapon();};
        	public void equipComponent(Entity entity, TakeableItem item) {entity.getEquippedItems().equipPrimaryWeapon(item);};
        	public void unequipComponent(Entity entity) {entity.getEquippedItems().unequipPrimaryWeapon();};
        },
		SECONDARY_WEAPON {
        	public TakeableItem getCurrentEquippedItem(Entity entity) {return entity.getEquippedItems().getSecondaryWeapon();};
        	public void equipComponent(Entity entity, TakeableItem item) {entity.getEquippedItems().equipSecondaryWeapon(item);};
        	public void unequipComponent(Entity entity) {entity.getEquippedItems().unequipSecondaryWeapon();};
        },
		CAPE {
        	public TakeableItem getCurrentEquippedItem(Entity entity) {return entity.getEquippedItems().getCape();};
        	public void equipComponent(Entity entity, TakeableItem item) {entity.getEquippedItems().equipCape(item);};
        	public void unequipComponent(Entity entity) {entity.getEquippedItems().unequipCape();};
        },
		GLOVES {
        	public TakeableItem getCurrentEquippedItem(Entity entity) {return entity.getEquippedItems().getGloves();};
        	public void equipComponent(Entity entity, TakeableItem item) {entity.getEquippedItems().equipGloves(item);};
        	public void unequipComponent(Entity entity) {entity.getEquippedItems().unequipGloves();};
        },
        NECKLACE {
        	public TakeableItem getCurrentEquippedItem(Entity entity) {return entity.getEquippedItems().getNecklace();};
        	public void equipComponent(Entity entity, TakeableItem item) {entity.getEquippedItems().equipNecklace(item);};
        	public void unequipComponent(Entity entity) {entity.getEquippedItems().unequipNecklace();};
        };

        public abstract TakeableItem getCurrentEquippedItem(Entity entity);
        public abstract void equipComponent(Entity entity, TakeableItem item);
        public abstract void unequipComponent(Entity entity);

	}

	private TakeableItem head;
    private TakeableItem chest;
    private TakeableItem greaves;
    private TakeableItem boots;
    private TakeableItem primaryWeapon;
    private TakeableItem secondaryWeapon;
    private TakeableItem cape;
    private TakeableItem gloves;
    private TakeableItem necklace;
    //need to put avatar to have the Equipped items object

    public void equipHead(TakeableItem item){head = item;}
    public void equipChest(TakeableItem item){chest = item;}
    public void equipGreaves(TakeableItem item){greaves = item;}
    public void equipBoots(TakeableItem item){boots = item;}
    public void equipPrimaryWeapon(TakeableItem item){primaryWeapon = item;}
    public void equipSecondaryWeapon(TakeableItem item){secondaryWeapon = item;}
    public void equipCape(TakeableItem item){cape = item;}
    public void equipGloves(TakeableItem item){gloves = item;}
    public void equipNecklace(TakeableItem item){necklace = item;}

    public void unequipHead(){head = null;}
    public void unequipChest(){chest = null;}
    public void unequipGreaves(){greaves = null;}
    public void unequipBoots(){boots = null;}
    public void unequipPrimaryWeapon(){primaryWeapon = null;}
    public void unequipSecondaryWeapon(){secondaryWeapon = null;}
    public void unequipCape(){cape = null;}
    public void unequipGloves(){gloves = null;}
    public void unequipNecklace(){necklace = null;}

    public TakeableItem getHead(){return head;}
    public TakeableItem getChest(){return chest;}
    public TakeableItem getGreaves(){return greaves;}
    public TakeableItem getBoots(){return boots;}
    public TakeableItem getPrimaryWeapon(){return primaryWeapon;}
    public TakeableItem getSecondaryWeapon(){return secondaryWeapon;}
    public TakeableItem getCape(){return cape;}
    public TakeableItem getGloves(){return gloves;}
    public TakeableItem getNecklace(){return necklace;}

}