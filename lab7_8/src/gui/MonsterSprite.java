package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import monsters.Monster;

public class MonsterSprite extends Sprite {
	
	private Monster monster;

	public MonsterSprite(Monster monster, GameWorld gameWorld, Point location) {
		super(gameWorld, location);
		this.monster = monster;
		this.label = monster.getMonsterBreed();
		updateColor();
	}
	
	protected void setGUIProperties() {
		this.boxSize = 16;
	}

	public void updateColor() {
		if (!monster.isConscious()) {
			color = Color.lightGray;
		} else {
			switch (monster.getMonsterType()) {
			case FIRE:
				color = Color.red;
				break;
				
			case NORMAL:
				color = Color.black;
				break;
				
			case WATER:
				color = Color.blue;
				break;
				
			case PLANT:
				color = Color.green;
				break;
			}
		}
	}
	
	public Monster getMonster() {
		return this.monster;
	}
	
    @Override
    public void paint(Graphics g) {
    	updateColor();
    	super.paint(g);
    }

}
