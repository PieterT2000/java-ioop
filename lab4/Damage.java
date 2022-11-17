
public class Damage
{
	private DamageType type;
	private int amount;
	
	public Damage(DamageType type, int amount)
	{
		this.type=type;
		this.amount=amount;
	}
	
	public DamageType getType()
	{
		return type;
	}

	public void setType(DamageType type)
	{
		this.type = type;
	}

	public int getAmount()
	{
		return amount;
	}

	public void setAmount(int amount)
	{
		this.amount = amount;
	}
}
