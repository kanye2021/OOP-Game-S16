/**
 * Created by denzel
 * on 2/3/16.
 */
package models.items;

import models.Entity;
import models.EquippedItems;
import models.Stats;
import utilities.StatModification;
import utilities.StatModifications;

/**
 * TakeableItem's main purpose is to be able to insert itself back into
 * the Entity's inventory when touched
 */

public class TakeableItem extends Item {

    public enum ItemType {

        EQUIPPABLE,
        CONSUMABLE,
        NONE;

    }

    public enum Items {
        WOOD_SWORD(0, "Wood Sword", "A sword made of wood", "/weapons/WoodSword.png", ItemType.EQUIPPABLE, EquippedItems.ArmorComponent.PRIMARY_WEAPON, new StatModifications(
                new StatModification(Stats.Type.STRENGTH, 5, StatModification.NumberType.POINT)
        )),
        IRON_SWORD(1, "Iron Sword", "A sword made of iron", "/weapons/IronSword.png", ItemType.EQUIPPABLE, EquippedItems.ArmorComponent.PRIMARY_WEAPON, new StatModifications(
                new StatModification(Stats.Type.STRENGTH, 10, StatModification.NumberType.POINT)
        )),
        STEEL_SWORD(2, "Steel Sword", "A sword made of steel", "/weapons/SteelSword.png", ItemType.EQUIPPABLE, EquippedItems.ArmorComponent.PRIMARY_WEAPON, new StatModifications(
                new StatModification(Stats.Type.STRENGTH, 20, StatModification.NumberType.POINT)
        )),
        DIAMOND_SWORD(3, "Diamond Sword", "A sword made of diamond. Totally not stolen from Minecraft", "/weapons/DiamondSword.png", ItemType.EQUIPPABLE, EquippedItems.ArmorComponent.PRIMARY_WEAPON, new StatModifications(
                new StatModification(Stats.Type.STRENGTH, 50, StatModification.NumberType.POINT)
        )),
        WOOD_SHIELD(4, "Wood Shield", "A Shield made of wood", "/armor/secondary/WoodShield.png", ItemType.EQUIPPABLE, EquippedItems.ArmorComponent.SECONDARY_WEAPON, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 5, StatModification.NumberType.POINT)
        )),
        IRON_SHIELD(5, "Iron Shield", "A Shield made of iron", "/armor/secondary/IronShield.png", ItemType.EQUIPPABLE, EquippedItems.ArmorComponent.SECONDARY_WEAPON, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 10, StatModification.NumberType.POINT)
        )),
        STEEL_SHIELD(6, "Steel Shield", "A Shield made of steel", "/armor/secondary/SteelShield.png", ItemType.EQUIPPABLE, EquippedItems.ArmorComponent.SECONDARY_WEAPON, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 15, StatModification.NumberType.POINT)
        )),
        DIAMOND_SHIELD(7, "Diamond Shield", "A Shield made of diamond", "/armor/secondary/Shield.png", ItemType.EQUIPPABLE, EquippedItems.ArmorComponent.SECONDARY_WEAPON, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 25, StatModification.NumberType.POINT)
        )),
        WOOD_HELM(8, "Wood Helm", "A helm made of wood", "/armor/head/WoodHelm.png", ItemType.EQUIPPABLE, EquippedItems.ArmorComponent.HEAD, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 5, StatModification.NumberType.POINT)
        )),
        IRON_HELM(9, "Iron Helm", "A helm made of iron", "/armor/head/IronHelm.png", ItemType.EQUIPPABLE, EquippedItems.ArmorComponent.HEAD, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 10, StatModification.NumberType.POINT)
        )),
        STEEL_HELM(10, "Steel Helm", "A helm made of steel", "/armor/head/SteelHelm.png", ItemType.EQUIPPABLE, EquippedItems.ArmorComponent.HEAD, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 20, StatModification.NumberType.POINT)
        )),
        DIAMOND_HELM(11, "Diamond Helm", "A helm made of diamond. Totally not stolen from Minecraft", "/armor/head/DiamondHelm.png", ItemType.EQUIPPABLE, EquippedItems.ArmorComponent.HEAD, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 50, StatModification.NumberType.POINT)
        )),
        WOOD_LEGS(12, "Wood Legs", "A plateleg made of wood", "/armor/legs/WoodPlatelegs.png", ItemType.EQUIPPABLE, EquippedItems.ArmorComponent.GREAVES, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 5, StatModification.NumberType.POINT)
        )),
        IRON_LEGS(13, "Iron Legs", "A plateleg made of iron", "/armor/legs/IronPlatelegs.png", ItemType.EQUIPPABLE, EquippedItems.ArmorComponent.GREAVES, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 10, StatModification.NumberType.POINT)
        )),
        STEEL_LEGS(14, "Steel Legs", "A plateleg made of steel", "/armor/legs/SteelPlatelegs.png", ItemType.EQUIPPABLE, EquippedItems.ArmorComponent.GREAVES, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 20, StatModification.NumberType.POINT)
        )),
        DIAMOND_LEGS(15, "Diamond Legs", "A plateleg made of diamond. Totally not stolen from Minecraft", "/armor/legs/DiamondPlatelegs.png", ItemType.EQUIPPABLE, EquippedItems.ArmorComponent.GREAVES, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 50, StatModification.NumberType.POINT)
        )),
        WOOD_BODY(16, "Wood Body", "A platebody made of wood", "bronze_sword.png", ItemType.EQUIPPABLE, EquippedItems.ArmorComponent.CHEST, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 5, StatModification.NumberType.POINT)
        )),
        IRON_BODY(17, "Iron Body", "A platebody made of iron", "bronze_sword.png", ItemType.EQUIPPABLE, EquippedItems.ArmorComponent.CHEST, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 10, StatModification.NumberType.POINT)
        )),
        STEEL_BODY(18, "Steel Body", "A platebody made of steel", "bronze_sword.png", ItemType.EQUIPPABLE, EquippedItems.ArmorComponent.CHEST, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 20, StatModification.NumberType.POINT)
        )),
        DIAMOND_BODY(19, "Diamond Body", "A platebody made of diamond. Totally not stolen from Minecraft", "bronze_sword.png", ItemType.EQUIPPABLE, EquippedItems.ArmorComponent.CHEST, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 50, StatModification.NumberType.POINT)
        )),
        KEY_OF_KANYE(20, "Key of Kanye", "The Key required to open the Gate of Kanye", "KanyeKey.png", ItemType.NONE, null, null);

        private int id;
        private String name;
        private String description;
        private String pathToPicture;
        private ItemType itemType;
        protected EquippedItems.ArmorComponent component;
        private StatModifications modifications;

        Items(int id, String name, String description, String pathToPicture, ItemType itemType, EquippedItems.ArmorComponent component, StatModifications modifications) {

            this.id = id;
            this.name = name;
            this.description = description;
            this.pathToPicture = pathToPicture;
            this.itemType = itemType;
            this.component = component;
            this.modifications = modifications;

        }

        public int getID() {

            return id;

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

        public ItemType getItemType() {

            return itemType;

        }

        public EquippedItems.ArmorComponent getComponent() {

            return component;

        }

    }

    //Constructor
    public TakeableItem(Items item) {
        super(item.ordinal(), Type.TAKE_ABLE);
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

    public ItemType getItemType() {
        return Items.values()[getID()].getItemType();
    }

    public EquippedItems.ArmorComponent getComponent() {
        return Items.values()[getID()].component;
    }

    public void modifyStats(Entity entity) {

        Items.values()[getID()].modifications.modifyStats(entity, StatModification.Direction.FORWARD);

    }

    public void modifyStatsReverse(Entity entity) {

        Items.values()[getID()].modifications.modifyStats(entity, StatModification.Direction.REVERSE);

    }
}

