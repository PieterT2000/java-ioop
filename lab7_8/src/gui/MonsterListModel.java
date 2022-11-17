package gui;

import javax.swing.AbstractListModel;

import game_controllers.Trainer;
import monsters.Monster;

@SuppressWarnings("serial")
public class MonsterListModel extends AbstractListModel<Monster> {
	
	private Trainer trainer;
	
	public MonsterListModel(Trainer trainer) {
		this.trainer = trainer;
	}

	@Override
	public int getSize() {
		return trainer.getNumberOfMonsters();
	}

	@Override
	public Monster getElementAt(int index) {
		return trainer.getMonsters().get(index);
	}
	
	public void update() {
		fireContentsChanged(this, 0, trainer.getNumberOfMonsters()-1);
	}

}
