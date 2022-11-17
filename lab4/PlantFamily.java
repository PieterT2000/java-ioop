public class PlantFamily extends Monster {
	protected static final int BASIC_MULTIPLIER = 5;
	protected static final int SPECIAL_MULTIPLIER = 10;

	protected int charges;
	private int maxCharges;

	public PlantFamily(String name, int level, int health, int maxCharges) {
		super(name, level, health);
		this.maxCharges = maxCharges;
	}

	public void levelUp() {
		maxCharges += 1;
		super.levelUp();
	}

	public void takeDamage(Damage damage) {
		switch (damage.getType()) {
			case PLANT:
				super.takeDamage(new Damage(damage.getType(), (int) (damage.getAmount() * 0.5)));
				break;
			case FIRE:
				super.takeDamage(new Damage(damage.getType(), (int) (damage.getAmount() * 2)));
				break;
			default:
				super.takeDamage(damage);
		}
	}

	public Damage specialAttack() {
		if (charges >= 1) {
			System.out.println(name + " throws a bomb of plants!");
			charges -= 1;
			return new Damage(DamageType.PLANT, monsterLevel * SPECIAL_MULTIPLIER);
		} else {
			System.out.println(name + " is not ready for this attack");
			return new Damage(DamageType.PLANT, 0);
		}
	}

	public Damage basicAttack() {
		return new Damage(DamageType.NORMAL, monsterLevel * BASIC_MULTIPLIER);
	}

	public void rest() {
		charges = maxCharges;
		super.rest();
	}

	public String makeMonsterDescription() {
		return super.makeMonsterDescription() + ", a plant monster with " + charges + "/" + maxCharges + " charges and "
				+ currentHealth + "/" + maxHealth + " health";
	}

}
