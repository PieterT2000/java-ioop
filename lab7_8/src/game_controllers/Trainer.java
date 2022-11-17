package game_controllers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game_utility.Damage;
import game_utility.SortByName;
import game_utility.SortByReadiness;
import game_utility.SortByType;
import game_utility.SortByUses;
import items.Crystal;
import items.HealingPotion;
import items.Item;
import items.RestorationPotion;
import monsters.Dragonfish;
import monsters.Monster;
import monsters.Scorchitile;
import monsters.Seedatops;

public class Trainer {
	private static final int MAX_MONSTER_CAPACITY = 6;
	private static final int SPECIAL_ATTACK = 1;
	private static final int BASIC_ATTACK = 0;
	private static final int INVENTORY_SIZE = 4;
	private static final int INVENTORY_USES = 5;
	private List<Monster> trainerMonsters = new ArrayList<Monster>();
	private List<Item> inventory = new ArrayList<Item>();
	private int numberOfMonsters = 0;
	private int trainerLevel =1;
	private int xp=0;

	public Trainer() {
		
	}

	public List<Monster> getRosterByName() {
		Collections.sort(trainerMonsters, new SortByName());
		return trainerMonsters;
	}

	public List<Monster> getRosterByReadiness() {
		Collections.sort(trainerMonsters, new SortByReadiness());
		return trainerMonsters;
	}

	public List<Monster> getRosterByType() {
		Collections.sort(trainerMonsters, new SortByType());
		return trainerMonsters;
	}

	public void addItem(String itemToAdd) {
		boolean found = false;
		for (Item item : inventory) {
			if (item.getItemType().name().equalsIgnoreCase(itemToAdd)) {
				if (item.getUses() < INVENTORY_USES) {
					item.setUses(item.getUses() + 1);
					found = true;
				}
			}
		}
		if (!found && inventory.size() < INVENTORY_SIZE) {
			System.out.println("Making new item:" + itemToAdd);
			switch (itemToAdd) {
			case "HEALING_POTION":
				inventory.add(new HealingPotion());
				break;
			case "RESTORATION_POTION":
				inventory.add(new RestorationPotion());
				break;
			case "CRYSTAL":
				inventory.add(new Crystal());
				break;
			}
		} else if (inventory.size() == INVENTORY_SIZE && !found) {
			System.out.println("Inventory is full!");
		}
	}

	public int countCharges(String itemName) 
	{
		int charges = 0;
		for (Item item : inventory) {
			if (item.getItemType().name().equalsIgnoreCase(itemName)) {
				charges += item.getUses();
			}
		}
		return charges;
	}

	public void useItem(String itemToUse, int target) {
		Collections.sort(inventory, new SortByUses());
		boolean found = false;
		Item itemToRemove = null;
		for (Item item : inventory) {
			if (item.getItemType().name().equalsIgnoreCase(itemToUse)) {
				item.use(trainerMonsters.get(target));
				found = true;
				if (item.getUses() <= 0) {
					itemToRemove = item;
				}
			}
			if (found) break;
		}
		if (found && itemToRemove != null) {
			inventory.remove(itemToRemove);
		}
		if (!found) {
			System.out.println("No item of that type found");
		}
	}

	public void printItems() {
		for (Item item : inventory) {
			System.out.println(item.getDescription());
		}
	}

	public void fight(int trainerMonsterNumber, Monster enemyMonster) {
		int turn = 1;
		System.out.println("NEW BATTLE");
		while (trainerMonsters.get(trainerMonsterNumber).isConscious() && enemyMonster.isConscious()) {
			System.out.println("Turn:" + turn);
			attack(trainerMonsters.get(trainerMonsterNumber), enemyMonster, turn % 2);
			if (enemyMonster.isConscious()) {
				attack(enemyMonster, trainerMonsters.get(trainerMonsterNumber), turn % 2);
			}
			turn++;
		}
		endFight(trainerMonsterNumber, enemyMonster);
	}

	private void endFight(int trainerMonsterNumber, Monster enemyMonster) {
		String looserName = trainerMonsters.get(trainerMonsterNumber).getName();
		if (trainerMonsters.get(trainerMonsterNumber).isConscious()) {
			trainerMonsters.get(trainerMonsterNumber).levelUp();
			looserName = enemyMonster.getName();
		}
		System.out.println(looserName + " is unconscious and can't fight!");
	}

	private void attack(Monster attacker, Monster defender, int attackType) {
		switch (attackType) {
		case SPECIAL_ATTACK:
			defender.takeDamage(attacker.specialAttack());
			break;
		case BASIC_ATTACK:
			defender.takeDamage(attacker.basicAttack());
			break;
		}
	}

	public void restTeam() {
		for (Monster monster : trainerMonsters) {
			monster.rest();
		}
	}

	public boolean addMonster(Monster newMonster) {
		if (numberOfMonsters < MAX_MONSTER_CAPACITY) {
			trainerMonsters.add(newMonster);
			numberOfMonsters++;
			System.out.println("Welcome to the team:" + newMonster.makeMonsterDescription());
			return true;
		} else {
			System.out.println("You have reached capacity for new monsters.");
			return false;
		}
	}

	public void printRoster() {
		for (int i = 0; i < numberOfMonsters; i++) {
			if (trainerMonsters.get(i).isConscious()) {
				System.out.println((i) + "-  " + trainerMonsters.get(i).makeMonsterDescription());
			} else {
				System.out.println("This monster is not conscious so can't fight:");
				System.out.println((i) + "-  " + trainerMonsters.get(i).makeMonsterDescription());
			}
		}
	}

	public List<Monster> getMonsters() {
		return trainerMonsters;
	}

	public int getNumberOfMonsters() {
		return numberOfMonsters;
	}

	public static void main(String[] args) {
		Monster scorchitile = new Scorchitile("Flamey", 1, 35, 15);
		Monster dragonfish = new Dragonfish("Splashy", 1, 30);
		Monster seedatops = new Seedatops("Vinesy", 1, 50, 2);
		Trainer trainer = new Trainer();
		trainer.addItem("HEALING_POTION");
		trainer.printItems();
		trainer.addItem("HEALING_POTION");
		trainer.printItems();
//		trainer.fight(0, seedatops);
//		trainer.printRoster();
//		seedatops.rest();
//		trainer.fight(0, seedatops);
//		trainer.printRoster();
//		trainer.addMonster(scorchitile);
//		trainer.printRoster();
//		trainer.restTeam();
//		trainer.printRoster();
//		seedatops.levelUp();
//		seedatops.levelUp();
//		seedatops.rest();
//		trainer.fight(1, seedatops);
//		trainer.fight(1, seedatops);
//		trainer.fight(0, seedatops);
//		seedatops.rest();
//		trainer.fight(0, seedatops);
//		trainer.fight(0, seedatops);
//		trainer.addMonster(seedatops);
//		trainer.printRoster();
//		trainer.addMonster(seedatops);
//		trainer.addMonster(seedatops);
//		trainer.addMonster(seedatops);
//		trainer.printRoster();
//		trainer.restTeam();
//		trainer.printRoster();
//		trainer.fight(0, seedatops);
//		trainer.printRoster();
//		trainer.getRosterByReadiness();
//		trainer.printRoster();
	}

	public int getTrainerLevel()
	{
		return trainerLevel ;
	}

	public void receiveAttack(int monsterChoice, Damage specialAttack)
	{
		trainerMonsters.get(monsterChoice).takeDamage(specialAttack);
		
	}

	public void setTrainerLevel(int i)
	{
		trainerLevel=i;
		
	}

	public void increaseXP(int i)
	{
		xp++;
	}

	public int getXP()
	{
		return xp;
	}
	
	public void printRoster(boolean showAttacks)
	{
		boolean warning = false;
		for (int i = 0; i < getMonsters().size(); i++)
		{
			if (getMonsters().get(i).isConscious())
			{
				System.out.println((i+1)+"-  " + getMonsters().get(i).makeMonsterDescription());
				if(showAttacks) 
				{
					System.out.println("\t 1 - Basic attack" + GameWorld.NEWLINE + "\t 2 - Special Attack");
				}
			}
			else
			{
				if (!warning)
				{
					System.out.println(GameWorld.NEWLINE+"These monsters are not conscious so can't fight:");
					warning = true;
				}
				System.out.println((i+1) +"-  "+ getMonsters().get(i).makeMonsterDescription());
			}
		}
	}
}