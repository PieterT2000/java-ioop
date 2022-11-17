package items;
import monsters.Monster;

public class RestorationPotion extends Item
{
	public RestorationPotion()
	{
		itemType = ItemType.RESTORATION_POTION;
		uses = 1;
	}

	public void use(Monster target)
	{
		System.out.println("Using restoration potion on " + target.getName());
		target.rest();
		super.use(target);
	}
}
