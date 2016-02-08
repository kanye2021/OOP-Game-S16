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
    public enum Items {
        WOOD_SWORD("Wood Sword", "A sword made of wood", "/weapons/WoodSword.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.PRIMARY_WEAPON, new StatModifications(
                new StatModification(Stats.Type.STRENGTH, 5, StatModification.NumberType.POINT)
        )),
        IRON_SWORD("Iron Sword", "A sword made of iron", "/weapons/IronSword.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.PRIMARY_WEAPON, new StatModifications(
                new StatModification(Stats.Type.STRENGTH, 10, StatModification.NumberType.POINT)
        )),
        STEEL_SWORD("Steel Sword", "A sword made of steel", "/weapons/SteelSword.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.PRIMARY_WEAPON, new StatModifications(
                new StatModification(Stats.Type.STRENGTH, 20, StatModification.NumberType.POINT)
        )),
        DIAMOND_SWORD("Diamond Sword", "A sword made of diamond. Totally not stolen from Minecraft", "/weapons/DiamondSword.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.PRIMARY_WEAPON, new StatModifications(
                new StatModification(Stats.Type.STRENGTH, 50, StatModification.NumberType.POINT)
        )),
        WOOD_SHIELD("Wood Shield", "A Shield made of wood", "/armor/secondary/WoodShield.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.SECONDARY_WEAPON, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 5, StatModification.NumberType.POINT)
        )),
        IRON_SHIELD("Iron Shield", "A Shield made of iron", "/armor/secondary/IronShield.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.SECONDARY_WEAPON, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 10, StatModification.NumberType.POINT)
        )),
        STEEL_SHIELD("Steel Shield", "A Shield made of steel", "/armor/secondary/SteelShield.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.SECONDARY_WEAPON, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 15, StatModification.NumberType.POINT)
        )),
        DIAMOND_SHIELD("Diamond Shield", "A Shield made of diamond", "/armor/secondary/Shield.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.SECONDARY_WEAPON, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 25, StatModification.NumberType.POINT)
        )),
        WOOD_HELM("Wood Helm", "A helm made of wood", "/armor/head/WoodHelm.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.HEAD, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 5, StatModification.NumberType.POINT)
        )),
        IRON_HELM("Iron Helm", "A helm made of iron", "/armor/head/IronHelm.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.HEAD, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 10, StatModification.NumberType.POINT)
        )),
        STEEL_HELM("Steel Helm", "A helm made of steel", "/armor/head/SteelHelm.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.HEAD, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 20, StatModification.NumberType.POINT)
        )),
        DIAMOND_HELM("Diamond Helm", "A helm made of diamond. Totally not stolen from Minecraft", "/armor/head/DiamondHelm.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.HEAD, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 50, StatModification.NumberType.POINT)
        )),
        WOOD_LEGS("Wood Legs", "A plateleg made of wood", "/armor/legs/WoodPlatelegs.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.GREAVES, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 5, StatModification.NumberType.POINT)
        )),
        IRON_LEGS("Iron Legs", "A plateleg made of iron", "/armor/legs/IronPlatelegs.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.GREAVES, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 10, StatModification.NumberType.POINT)
        )),
        STEEL_LEGS("Steel Legs", "A plateleg made of steel", "/armor/legs/SteelPlatelegs.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.GREAVES, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 20, StatModification.NumberType.POINT)
        )),
        DIAMOND_LEGS("Diamond Legs", "A plateleg made of diamond. Totally not stolen from Minecraft", "/armor/legs/DiamondPlatelegs.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.GREAVES, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 50, StatModification.NumberType.POINT)
        )),
        WOOD_BODY("Wood Body", "A platebody made of wood", "bronze_sword.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.CHEST, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 5, StatModification.NumberType.POINT)
        )),
        IRON_BODY("Iron Body", "A platebody made of iron", "bronze_sword.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.CHEST, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 10, StatModification.NumberType.POINT)
        )),
        STEEL_BODY("Steel Body", "A platebody made of steel", "bronze_sword.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.CHEST, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 20, StatModification.NumberType.POINT)
        )),
        DIAMOND_BODY("Diamond Body", "A platebody made of diamond. Totally not stolen from Minecraft", "bronze_sword.png", EquippedItems.Equippable.YES, EquippedItems.ArmorComponent.CHEST, new StatModifications(
                new StatModification(Stats.Type.HARDINESS, 50, StatModification.NumberType.POINT)
        )),
        KEY_OF_KANYE("Key of Kanye", "The Key required to open the Gate of Kanye", "KanyeKey.png", EquippedItems.Equippable.NO, null, null);


        private String name;
        private String description;
        private String pathToPicture;
        private EquippedItems.Equippable isEquippable;
        protected EquippedItems.ArmorComponent component;
        private StatModifications modifications;

        Items(String name, String description, String pathToPicture, EquippedItems.Equippable isEquippable, EquippedItems.ArmorComponent component, StatModifications modifications) {

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

        }

        ;

        public boolean getEquippable() {

            return isEquippable.getEquippable();

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

    public boolean getEquippable() {
        return Items.values()[getID()].getEquippable();
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

