/**
 * Created by denzel on 2/3/16.
 */
package models.items;

import utilities.StatModification;
import utilities.StatModifications;
import models.Entity;
import models.EquippedItems;
import models.Stats;

/**
 * TakeableItem's main purpose is to be able to insert itself back into
 * the Entity's inventory when touched
 */
public class TakeableItem extends Item {
	public enum Items {
		WOOD_SWORD("Wood Sword", "A sword made of wood", "bronze_sword.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.PRIMARY_WEAPON, new StatModifications(
				new StatModification(Stats.Type.MOVEMENT, 50, StatModification.NumberType.PERCENTAGE)
		)),
		IRON_SWORD("Iron Sword", "A sword made of iron", "bronze_sword.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.PRIMARY_WEAPON, new StatModifications(
				new StatModification(Stats.Type.MOVEMENT, 50, StatModification.NumberType.PERCENTAGE)
		)),
		STEEL_SWORD("Steel Sword", "A sword made of steel", "bronze_sword.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.PRIMARY_WEAPON, new StatModifications(
				new StatModification(Stats.Type.MOVEMENT, 50, StatModification.NumberType.PERCENTAGE)
		)),
		DIAMOND_SWORD("Diamond Sword", "A sword made of diamond. Totally not stolen from Minecraft", "bronze_sword.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.PRIMARY_WEAPON, new StatModifications(
				new StatModification(Stats.Type.MOVEMENT, 50, StatModification.NumberType.PERCENTAGE)
		)),
		WOOD_SHIELD("Wood Shield", "A Shield made of wood", "bronze_sword.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.SECONDARY_WEAPON, new StatModifications(
				new StatModification(Stats.Type.MOVEMENT, 5, StatModification.NumberType.POINT)
		)),
		IRON_SHIELD("Iron Shield", "A Shield made of iron", "bronze_sword.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.SECONDARY_WEAPON, new StatModifications(
				new StatModification(Stats.Type.MOVEMENT, 5, StatModification.NumberType.POINT)
		)),
		STEEL_SHIELD("Steel Shield", "A Shield made of steel", "bronze_sword.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.SECONDARY_WEAPON,	new StatModifications(
				new StatModification(Stats.Type.MOVEMENT, 5, StatModification.NumberType.POINT)
		)),
		DIAMOND_SHIELD("Diamond Shield", "A Shield made of diamond", "bronze_sword.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.SECONDARY_WEAPON, new StatModifications(
				new StatModification(Stats.Type.MOVEMENT, 5, StatModification.NumberType.POINT)
		));

		private String name;
		private String description;
		private String pathToPicture;
		private EquippedItems.Equippable isEquippable;
		protected EquippedItems.ArmorComponent component;
		private StatModifications modifications;

		private Items(String name, String description, String pathToPicture, EquippedItems.Equippable isEquippable, EquippedItems.ArmorComponent component, StatModifications modifications) {

			this.name = name;
			this.description = description;
			this.pathToPicture = pathToPicture;
			this.isEquippable = isEquippable;
			this.component = component;
			this.modifications = modifications;

		}

		public int getID() {

			return ordinal();

		}

		public String getName() {

			return name;

		}

		public String getDescription() {

			return description;

		}

		public String getPathToPicture() {

			return pathToPicture;

		}

		public EquippedItems.ArmorComponent getComponent() {

			return component;

		};

		public boolean getEquippable() {

			return isEquippable.getEquippable();

		}

	}

	//Constructor
	public TakeableItem(Items item) {
		super(item.ordinal(), Item.Type.TAKEABLE);
	}



    @Override
    public final boolean onTouch(Entity entity) {
        entity.getInventory().addItem(this);
        return true;
    }

	public String getName() {
		return Items.values()[getID()].name;
	}

	public String getDescription() {
		return Items.values()[getID()].description;
	}

	@Override
	public String getPathToPicture() {
		return Items.values()[getID()].pathToPicture;
	}

	public boolean getEquippable(){ return Items.values()[getID()].getEquippable(); }

	public EquippedItems.ArmorComponent getComponent(){ return Items.values()[getID()].component; }

	public void modifyStats(Entity entity) {

		Items.values()[getID()].modifications.modifyStats(entity, StatModification.Direction.FORWARD);

	}

	public void modifyStatsReverse(Entity entity) {

		Items.values()[getID()].modifications.modifyStats(entity, StatModification.Direction.REVERSE);

	}
}

