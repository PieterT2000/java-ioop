package items;
import game_utility.Damage;
import game_utility.ElementalType;
import monsters.Monster;

public class HealingPotion extends Item
{
	public HealingPotion()
	{
		itemType=ItemType.HEALING_POTION;
		uses=1;
	}
	
	public void use(Monster target)
	{
		System.out.println("Using healing potion on "+target.getName());
		target.takeDamage(new Damage(ElementalType.NORMAL,-50));
		super.use(target);
	}
}
