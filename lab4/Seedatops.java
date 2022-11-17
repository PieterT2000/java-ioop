
public class Seedatops extends PlantFamily {
	// Task 2_7 and 2_8 are fix my constructor and add a method to me
	public Seedatops(String name, int level, int health, int maxCharges) {
		super(name, level, health, maxCharges);
	}

	public Damage basicAttack() {
		currentHealth += monsterLevel * BASIC_MULTIPLIER;
		return super.basicAttack();
	}
}
