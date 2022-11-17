package game_utility;
import java.util.Comparator;

import items.Item;

public class SortByUses implements Comparator<Item> {

	public int compare(Item i1, Item i2) {
		return i2.getUses()-i1.getUses();
	}

}
