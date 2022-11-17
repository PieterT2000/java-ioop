package game_utility;
import monsters.Dragonfish;
import monsters.Monster;
import monsters.Scorchitile;
import monsters.Seedatops;

public class MonsterMaker {
	
	private static final int SCORCHITILE = 0;
	private static final int DRAGONFISH = 1;
	private static final int SEEDATOPS = 2;
	
	
	public static Monster makeRandomMonster(String name, int level)
	{			
		int monster = randomMonster(0,2);
		switch (monster) {
		case SCORCHITILE:
			return new Scorchitile(name,level,level*10,level*10+5);
		case DRAGONFISH:
			return new Dragonfish(name,level,level*10);
		case SEEDATOPS:
			return new Seedatops(name, level, level*10, level);
		default:
			return new Scorchitile("Should be unreachable",level,level*10,level*10+5);
		}	
	}
	
	public static Monster makeRandomWildMonster(int level)
	{
		int monster = randomMonster(0,2);
		switch (monster) {
		case SCORCHITILE:
			// System.out.println("There's a strange glow behind that rock....");
			return new Scorchitile("Wild Scorchitile",level,level*10,level*10+5);
		case DRAGONFISH:
			// System.out.println("Suddenly, the stream nearby starts to bubble and froth....");
			return new Dragonfish("Wild Dragonfish",level,level*10);
		case SEEDATOPS:
			return new Seedatops("Wild Seedatops", level, level*10, level);
		default:
			return new Scorchitile("Should be unreachable",level,level*10,level*10+5);
		}	
	}
	
	private static int randomMonster(int lowerBound, int upperBound) 
	{
		return (int)(Math.random()*(upperBound-lowerBound+1)+lowerBound); 
	}

	public static void main(String[]args)
	{
		for(int i=0;i<10;i++)
		{
			System.out.println(MonsterMaker.randomMonster(SCORCHITILE, DRAGONFISH));
		}
	}

}
