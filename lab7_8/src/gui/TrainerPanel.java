package gui;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import game_controllers.Trainer;
import items.ItemType;
import monsters.Monster;

@SuppressWarnings("serial")
public class TrainerPanel extends JPanel implements ActionListener {

	private GameWorld gameWorld;
	private ControlPanel controlPanel;

	private JButton fightButton, restButton, useItemButton, pickupButton, catchButton;

	public TrainerPanel(GameWorld gameWorld, ControlPanel controlPanel) {
		this.gameWorld = gameWorld;
		this.controlPanel = controlPanel;

		setLayout(new GridLayout(0, 1));

		setBorder(new TitledBorder("Trainer actions"));

		fightButton = new JButton("Fight monster");
		add(fightButton);
		fightButton.addActionListener(this);

		catchButton = new JButton("Catch monster");
		add(catchButton);
		catchButton.addActionListener(this);

		pickupButton = new JButton("Pick up item");
		add(pickupButton);
		pickupButton.addActionListener(this);

		useItemButton = new JButton("Use item");
		add(useItemButton);
		useItemButton.addActionListener(this);

		restButton = new JButton("Rest team");
		add(restButton);
		restButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Point trainerLoc = gameWorld.getTrainerSprite().getLocation();
		Trainer trainer = gameWorld.getTrainerSprite().getTrainer();
		Object evtSource = e.getSource();

		if (evtSource == fightButton) {
			MonsterSprite monsterSprite = gameWorld.getMonsterAt(trainerLoc);
			int monsterNum = controlPanel.getSelectedMonster();
			if (monsterSprite != null && monsterSprite.getMonster().isConscious() && monsterNum >= 0) {
				Monster wildMonster = monsterSprite.getMonster();
				trainer.fight(monsterNum, wildMonster);
			}
		} else if (evtSource == restButton) {
			trainer.restTeam();
		} else if (evtSource == pickupButton) {
			ItemSprite itemSprite = gameWorld.getItemAt(trainerLoc);
			if (itemSprite != null) {
				trainer.addItem(itemSprite.getItemType().toString());
				gameWorld.removeItemSprite(itemSprite);
			}
		} else if (evtSource == useItemButton) {
			int selectedTrainerIdx = controlPanel.getSelectedMonster();
			ItemType selectedItemType = controlPanel.getSelectedItem();
			if (selectedTrainerIdx > -1 && selectedItemType != null) {
				trainer.useItem(selectedItemType.toString(), selectedTrainerIdx);
			}
		} else if (evtSource == catchButton) {
			MonsterSprite monsterSprite = gameWorld.getMonsterAt(trainerLoc);
			if (monsterSprite != null && !monsterSprite.getMonster().isConscious()) {
				boolean monsterAdded = trainer.addMonster(monsterSprite.getMonster());
				if (monsterAdded) {
					gameWorld.removeMonsterSprite(monsterSprite);
				}
			}
		}

		controlPanel.update();
	}

}
