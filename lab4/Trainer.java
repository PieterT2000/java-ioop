public class Trainer {
	private Monster[] trainerMonsters = new Monster[6];
	private int numberOfMonsters = 0;

	public Trainer(Monster starterMonster) {
		trainerMonsters[0] = starterMonster;
	}

	public void addMonster(Monster newMonster) {
		int freeIdx = trainerMonsters.length;
		for (int i = 0; i < trainerMonsters.length; i++) {
			Monster el = trainerMonsters[i];
			if (el == null) {
				freeIdx = i;
				break;
			}
		}

		if (freeIdx < trainerMonsters.length) {
			trainerMonsters[freeIdx] = newMonster;
			System.out.println("Monster " + newMonster.name + " was added.");
		} else {
			System.out.println("No capacity left to add the new monster.");
		}

		// System.out.print("[ ");
		// for (Monster m : trainerMonsters) {
		// System.out.print(m + ", ");
		// }
		// System.out.println(" ]");
	}

	public void restTeam() {
		for (Monster monster : trainerMonsters) {
			monster.rest();
		}
	}

	public void fight(int trainerMonsterNumber, Monster enemyMonster) {
		Monster trainerMonster = trainerMonsters[trainerMonsterNumber];

		boolean isAttackerConscious = enemyMonster.isConscious();
		boolean isTrainerConscious = trainerMonster.isConscious();

		boolean specialAttackRound = true;
		Monster attacker = trainerMonster;
		Monster defender = enemyMonster;
		int i = 1;
		while (isTrainerConscious && isAttackerConscious) {
			if (specialAttackRound) {
				defender.takeDamage(attacker.specialAttack());
			} else {
				defender.takeDamage(attacker.basicAttack());
			}

			// after every 2 attacks, alternate between special and basic attack
			if (i % 2 == 0) {
				specialAttackRound = !specialAttackRound;
			}

			// swap roles
			Monster attackerCopy = attacker;
			attacker = defender;
			defender = attackerCopy;

			isAttackerConscious = enemyMonster.isConscious();
			isTrainerConscious = trainerMonster.isConscious();
			i += 1;
		}

		if (isTrainerConscious) {
			trainerMonster.levelUp();
		}
	}

	public void printRoster() {
		// Optional task - implementing this can help spot issues in testing
	}

	public Monster[] getMonsters() {
		return trainerMonsters;
	}

	public int getNumberOfMonsters() {
		return numberOfMonsters;
	}

	public static void main(String[] args) {
		// An example of some of the sorts of tests you might want to run on your code
		// You will need to add in more
		System.out.println("\n------------ TRAINER START ---------------- \n");
		Monster scorchitile = new Scorchitile("Flamey", 1, 30, 15);
		Monster dragonfish = new Dragonfish("Splashy", 1, 30);
		Monster seedatops = new Seedatops("Vinesy", 1, 50, 2);
		Trainer t = new Trainer(dragonfish);
		t.addMonster(seedatops);
		t.addMonster(scorchitile);
		t.fight(0, seedatops);
		t.printRoster();
		seedatops.rest();
		// t.fight(1, scorchitile);
	}
}