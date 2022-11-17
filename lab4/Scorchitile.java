public class Scorchitile extends FireFamily {
	// Task 1_3 and 1_4 are to add to fix my constructor and add a method to me
	public Scorchitile(String name, int level, int health, int maxFireMana) {
		super(name, level, health, maxFireMana);
	}

	public Damage basicAttack() {
		return new Damage(DamageType.NORMAL, FireFamily.DEFAULT_DAMAGE_AMOUNT * 2);
	}
}
