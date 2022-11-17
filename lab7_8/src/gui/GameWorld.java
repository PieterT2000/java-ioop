package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import game_controllers.Trainer;
import game_utility.MonsterMaker;
import items.ItemType;
import monsters.Dragonfish;
import monsters.Monster;
import monsters.Scorchitile;
import monsters.Seedatops;

@SuppressWarnings("serial")
public class GameWorld extends JPanel {
	// Dimensions
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	public static final int GRID_SIZE = 10;
	public static final int SQUARE_WIDTH = WIDTH / GRID_SIZE;
	public static final int SQUARE_HEIGHT = HEIGHT / GRID_SIZE;

	public static final double MONSTER_SPAWN_RATE = 0.2;
	public static final double ITEM_SPAWN_RATE = 0.1;
	public static final double WILD_CATCH_RATE = 0.05;

	public final static Random RANDOM = new Random();

	private Set<MonsterSprite> monsterSprites;
	private Set<ItemSprite> itemSprites;
	private TrainerSprite trainerSprite;

	private List<Timer> timerThreads;

	public GameWorld(GameWindow window) {
		// GUI properties
		setBackground(Color.DARK_GRAY);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		trainerSprite = new TrainerSprite(new Trainer(), this);
		monsterSprites = new HashSet<>();
		itemSprites = new HashSet<>();

		timerThreads = new ArrayList<>();

		spawnMonstersAndItems();

		// Make sure to repaint regularly
		Timer timer = new Timer();
		TimerTask repaintTask = new TimerTask() {
			@Override
			public void run() {
				repaint();
				Toolkit.getDefaultToolkit().sync();
			}
		};
		timer.schedule(repaintTask, 10, 10);
	}

	public void setStarter(String selectedValue) {
		Trainer trainer = trainerSprite.getTrainer();
		String name = "Starter";
		Monster startingMonster = null;
		switch (selectedValue) {
			case "Scorchitile":
				startingMonster = new Scorchitile(name, trainer.getTrainerLevel(), trainer.getTrainerLevel() * 12,
						trainer.getTrainerLevel() * 10);
				break;

			case "Dragonfish":
				startingMonster = new Dragonfish(name, trainer.getTrainerLevel(), trainer.getTrainerLevel() * 12);
				break;

			case "Seedatops":
				startingMonster = new Seedatops(name, trainer.getTrainerLevel(), trainer.getTrainerLevel() * 12, 2);
				break;
		}
		if (startingMonster != null) {
			trainer.addMonster(startingMonster);
		}
		trainer.addItem(ItemType.HEALING_POTION.toString());
	}

	public void spawnMonstersAndItems() {
		monsterSprites.clear();
		itemSprites.clear();

		Trainer trainer = trainerSprite.getTrainer();

		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				boolean trainerLoc = trainerSprite.getLocation().equals(new Point(i, j));
				double rand = RANDOM.nextDouble();
				if (trainerLoc || rand < MONSTER_SPAWN_RATE) {
					Monster monster = MonsterMaker.makeRandomWildMonster(trainer.getTrainerLevel());
					MonsterSprite sprite = new MonsterSprite(monster, this, new Point(i, j));
					monsterSprites.add(sprite);
				}
				if (trainerLoc || rand > (1.0 - ITEM_SPAWN_RATE)) {
					ItemType itemType = ItemType.values()[RANDOM.nextInt(ItemType.values().length)];
					itemSprites.add(new ItemSprite(this, new Point(i, j), itemType));
				}
			}
		}
	}

	public void startMovingMonsters() {
		if (timerThreads.isEmpty()) {
			for (MonsterSprite ms : monsterSprites) {
				Timer timer = new Timer();
				timer.schedule(newMoveTask(timer, ms), 0);
				timerThreads.add(timer);
			}
		}
	}

	public TimerTask newMoveTask(Timer timer, MonsterSprite monsterSprite) {
		return new TimerTask() {
			@Override
			public void run() {
				if (!monsterSprite.getMonster().isConscious()) {
					stopMovingMonster(timer);
					timerThreads.remove(timer);
				} else {
					timer.schedule(newMoveTask(timer, monsterSprite), RANDOM.nextInt(1000, 2000));

					MoveDirection newDir = MoveDirection.values()[RANDOM.nextInt(MoveDirection.values().length)];
					Point newLocation = monsterSprite.getNewLocation(newDir);
					if (getMonsterAt(newLocation) == null) {
						monsterSprite.move(newDir);
					}
				}
			}
		};
	}

	public void stopMovingMonsters() {
		if (!timerThreads.isEmpty()) {
			for (Timer timerThread : timerThreads) {
				stopMovingMonster(timerThread);
			}
			timerThreads.clear();
		}
	}

	public void stopMovingMonster(Timer timerThread) {
		timerThread.cancel();
		timerThread.purge();
	}

	public MonsterSprite getMonsterAt(Point location) {
		for (MonsterSprite ms : monsterSprites) {
			synchronized (ms) {
				if (ms.getLocation().equals(location)) {
					return ms;
				}
			}
		}
		return null;
	}

	public ItemSprite getItemAt(Point location) {
		for (ItemSprite is : itemSprites) {
			if (is.getLocation().equals(location)) {
				return is;
			}
		}
		return null;
	}

	public TrainerSprite getTrainerSprite() {
		return this.trainerSprite;
	}

	public void removeItemSprite(ItemSprite is) {
		itemSprites.remove(is);
	}

	public void removeMonsterSprite(MonsterSprite ms) {
		monsterSprites.remove(ms);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Draw grid lines (I think this may still be buggy but it works on square grids
		// so I'll live with it)
		g.setColor(Color.LIGHT_GRAY);
		for (int i = 0; i < (GRID_SIZE - 1); i++) {
			int yPos = (i + 1) * SQUARE_WIDTH;
			int xPos = (i + 1) * SQUARE_HEIGHT;
			g.drawLine(xPos, 0, xPos, WIDTH);
			g.drawLine(0, yPos, HEIGHT, yPos);
		}

		trainerSprite.paint(g);

		for (Sprite sprite : monsterSprites) {
			sprite.paint(g);
		}
		for (Sprite sprite : itemSprites) {
			sprite.paint(g);
		}
	}

}
