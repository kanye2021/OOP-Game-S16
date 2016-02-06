package models;

import java.util.*;

import models.items.TakeableItem;


/**
 * Created by Austin on 2/2/16.
 */
public class Inventory {

	private final int MAX_INVENTORY = 10;

	private ItemNode[] items;

	private boolean isOutOfBounds(int index){return index < 0 || index >= this.getSize();}


	public Inventory(){
		items = new ItemNode[MAX_INVENTORY];

		for (int i = 0; i < MAX_INVENTORY; i++) {
			items[i] = null;
		}
	}

	public int getSize(){return MAX_INVENTORY;}

	public boolean isThereAnItemAt(int index){
		if(isOutOfBounds(index)) return false;
		return (items[index] != null);
	}

	public TakeableItem getItemAt(int index){
		if(!isThereAnItemAt(index))
			return null;
		return items[index].item;
	}

	public ItemNode getItemNodeAt(int index){
		if(!isThereAnItemAt(index))
			return null;
		return items[index];
	}

	//return true if you can put the item in the Inventory
	public boolean addItem(TakeableItem newItem){
		int nextEmptySpace = MAX_INVENTORY;

		for (int i = 0; i < items.length; i++) {
			if(items[i] == null){ //set the next empty space
				if(nextEmptySpace > i)
					nextEmptySpace = i;
				continue;
			}

			//increment items if u have it
			if(items[i].item.equals(newItem)){
				items[i].amount += 1;
				return true;
			}
		}

		/// put item in a new slot
		if(nextEmptySpace < MAX_INVENTORY){
			items[nextEmptySpace] = new ItemNode(newItem, 1);
			return true;
		}

		return false;
	}


	//return true if you can remove item
	public boolean removeItemAt(int index, int amount){
		if(!isThereAnItemAt(index))
			return false;

		if(items[index].amount > amount){
			items[index].amount -= amount;
		}
		else{
			items[index] = null;
		}

		return true;
	}
	public boolean removeItemAt(int index){return removeItemAt(index, 1);}
	public boolean removeAllItemsAt(int index){return removeItemAt(index, Integer.MAX_VALUE);}

	public boolean removeItem(TakeableItem newItem){
		int index  = 0;
		for (int i = 0; i < items.length; i++) {
			if(items[i] != null){
				if(items[i].item.equals(newItem))
					removeItemAt(i);
			}
		}
		return false;
	}

	public boolean getItem(TakeableItem.Items[] findItem){
		
		//for (TakeableItem.Items item : findItem) {
			
			//for (ItemNode item : items) {
				
				// Need to iterate through all items unfortunately. Then count them and make sure they are at least the required amount
				
			//}
			
		//}
		
		//for (int i = 0; i < items.length; i++) {
			//if(items[i] != null && items[i].item.getID() == findItem)
				//return true;
		//}
		return false;
	}

	public class ItemNode{
		public TakeableItem item;
		public int amount;

		public ItemNode(TakeableItem item, int amount){
			setVariables(item, amount);
		}
		public void setVariables(TakeableItem item, int amount){
			this.item = item;
			this.amount = amount;
		}
	}
}

/*
public class Inventory {

	private HashMap<TakeableItem, Integer> items = new HashMap<TakeableItem, Integer>();

	public HashMap<TakeableItem, Integer> getItems(){
		return items;
	}

	public void addItem(TakeableItem newItem) {
		if (items.containsKey(newItem)) {

			items.put(newItem, items.get(newItem) + 1);

		}
		else {
			items.put(newItem, 1);
		}
	}


	public void removeItem(TakeableItem item) {
		this.items.remove(item);
	}
}
*/