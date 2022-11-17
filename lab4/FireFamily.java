
public class FireFamily extends Monster {
	protected static final int DEFAULT_DAMAGE_AMOUNT = 10;

	protected int fireMana;
	protected int maxFireMana;

	public FireFamily(String name, int level, int health, int maxFireMana) {
		super(name, level, health);
		fireMana = maxFireMana;
		this.maxFireMana = maxFireMana;
	}

	public void levelUp() {
		super.levelUp();
		maxFireMana += 12;
		System.out.println("They now have " + maxFireMana + "max fire mana!");
	}

	public void takeDamage(Damage damage) {
		switch (damage.getType()) {
			case FIRE:
				super.takeDamage(new Damage(damage.getType(), (int) (damage.getAmount() * 0.5)));
				break;
			case WATER:
				super.takeDamage(new Damage(damage.getType(), (int) (damage.getAmount() * 2)));
				break;
			default:
				super.takeDamage(damage);
		}
	}

	public Damage specialAttack() {
		int firepower = monsterLevel * 15;
		if (fireMana >= firepower) {
			System.out.println(name + " breathes fire");
			fireMana -= firepower;
			return new Damage(DamageType.FIRE, firepower);
		} else {
			System.out.println(name + " breathes fire weakly");
			int amountDamage = fireMana;
			fireMana = 0;
			return new Damage(DamageType.FIRE, amountDamage);
		}
	}

	public Damage basicAttack() {
		return new Damage(DamageType.NORMAL, DEFAULT_DAMAGE_AMOUNT);
	}

	public String makeMonsterDescription() {
		return super.makeMonsterDescription() + ", a fire monster with " + fireMana + "/" + maxFireMana + " fire mana and "
				+ currentHealth
				+ "/" + maxHealth + " health";
	}

	public void rest() {
		fireMana = maxFireMana;
		super.rest();
	}
}
