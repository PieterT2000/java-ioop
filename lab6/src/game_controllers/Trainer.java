package game_controllers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import game_utility.Damage;
import game_utility.MonsterMaker;
import game_utility.SortByName;
import game_utility.SortByReadiness;
import game_utility.SortByType;
import items.Crystal;
import items.HealingPotion;
import items.Item;
import items.ItemType;
import items.RestorationPotion;
import monsters.Monster;

public class Trainer {
	private static final int MAX_MONSTER_CAPACITY = 6;
	private static final int SPECIAL_ATTACK = 1;
	private static final int BASIC_ATTACK = 0;
	private static final int TOTAL_UNIQUE_ITEMS = 3;
	private static final int INVENTORY_SIZE = 4;
	private static final int INVENTORY_USES = 5;
	private static final int MAX_ITEM_USES = 5;
	private static final String[] itemTypes = new String[] { "HEALING_POTION", "RESTORATION_POTION", "CRYSTAL" };
	private List<Monster> trainerMonsters = new ArrayList<Monster>();
	private List<Item> trainerItems = new ArrayList<Item>();

	private int numberOfMonsters = 0;
	private int trainerLevel = 1;
	private int xp = 0;

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

	public int countCharges(String itemName) {
		return 0;
	}

	public void addItem(String itemToAdd) {
		boolean found = false;
		for (Item item : trainerItems) {
			if (item.getItemType().name().equalsIgnoreCase(itemToAdd) && item.getUses() < MAX_ITEM_USES) {
				item.setUses(item.getUses() + 1);
				found = true;
			}
		}
		if (!found && trainerItems.size() < INVENTORY_SIZE) {
			System.out.println("Making new item:" + itemToAdd);
			switch (itemToAdd) {
				case "HEALING_POTION":
					trainerItems.add(new HealingPotion());
					break;
				case "RESTORATION_POTION":
					trainerItems.add(new RestorationPotion());
					break;
				case "CRYSTAL":
					trainerItems.add(new Crystal());
					break;
			}
		}
	}

	public void useItem(int itemToUse, int target) {
		boolean found = false;
		for (int i = 0; i < trainerItems.size(); i++) {
			if (itemToUse == i) {
				Item item = trainerItems.get(itemToUse);
				item.use(trainerMonsters.get(target));
				found = true;
				if (item.getUses() <= 0) {
					trainerItems.remove(item);
				}
				break;
			}
		}
		if (!found) {
			System.out.println("No item of that type found");
		}
	}

	public void addRandomItems(int amount) {
		for (int i = 0; i < amount; i++) {
			this.addItem(itemTypes[MonsterMaker.randomNumber(0, TOTAL_UNIQUE_ITEMS - 1)]);
		}
	}

	public void printItems() {
		System.out.println("Items available for use:");
		for (int i = 0; i < trainerItems.size(); i++) {
			Item item = trainerItems.get(i);
			System.out.println((i + 1) + " - " + item.getDescription());
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

	public void addMonster(Monster newMonster) {
		if (numberOfMonsters < MAX_MONSTER_CAPACITY) {
			trainerMonsters.add(newMonster);
			numberOfMonsters++;
			System.out.println("Welcome to the team:" + newMonster.makeMonsterDescription());
		} else {
			System.out.println("You have reached capacity for new monsters.");
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

	public int getTrainerLevel() {
		return trainerLevel;
	}

	public void receiveAttack(int monsterChoice, Damage specialAttack) {
		trainerMonsters.get(monsterChoice).takeDamage(specialAttack);
	}

	public void changeMonsterName(int monsterChoice, String newName) {
		trainerMonsters.get(monsterChoice).setName(newName);
	}

	public void setTrainerLevel(int i) {
		trainerLevel = i;
	}

	public void increaseXP(int i) {
		xp++;
	}

	public int getXP() {
		return xp;
	}

	public void printRoster(boolean showAttacks) {
		List<Monster> monsters = this.getRosterByReadiness();
		System.out.println("Monsters available:");
		for (int i = 0; i < monsters.size(); i++) {
			Monster monster = monsters.get(i);
			System.out.println((i + 1) + " - " + monster.makeMonsterDescription());
			if (monster.isConscious()) {
				System.out.println("Attack options");
				System.out.println("-- do a basic attack: 1");
				if (monster.canDoSpecialAttack()) {
					System.out.println("-- do a special attack: 2");
				}
			}
		}
	}
}