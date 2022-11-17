package gui;

import java.awt.Color;
import java.awt.Point;

import items.ItemType;

public class ItemSprite extends Sprite {
	
	private ItemType itemType;

	public ItemSprite(GameWorld gameWorld, Point location, ItemType itemType) {
		super(gameWorld, location);
		this.itemType = itemType;
		this.label = itemType.toString().substring(0, 1);
	}
	
	protected void setGUIProperties() {
		this.boxSize = 6;
		this.color = Color.yellow;
	}
	
	public ItemType getItemType() {
		return this.itemType;
	}

	public void move() {
		throw new UnsupportedOperationException();
	}
	
}
