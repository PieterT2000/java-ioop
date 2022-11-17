package gui;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import game_controllers.Trainer;
import items.ItemType;
import monsters.Monster;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel {

	private InventoryModel invModel;
	private MonsterListModel monsterModel;
	private JList<Monster> monsterList;
	private JTable inventoryTable;

	public ControlPanel(GameWorld gameWorld) {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		add(new EnvironmentPanel(gameWorld));
		
		Box middleBox = Box.createHorizontalBox();
		middleBox.add(new DirectionPanel(gameWorld));
		middleBox.add(new TrainerPanel(gameWorld, this));
		add(middleBox);
		
		add(Box.createVerticalGlue());

		Trainer trainer = gameWorld.getTrainerSprite().getTrainer();
		
		monsterModel = new MonsterListModel(trainer);
		monsterList = new JList<>(monsterModel);
		monsterList.setVisibleRowCount(6);
		JScrollPane monsterScroll = new JScrollPane(monsterList);
		monsterScroll.setBorder(new TitledBorder("Trainer monsters"));
		add(monsterScroll);
		
		invModel = new InventoryModel(trainer);
		inventoryTable = new JTable(invModel);
		// https://stackoverflow.com/a/9821640
		inventoryTable.setPreferredScrollableViewportSize(inventoryTable.getPreferredSize());
		JScrollPane inventoryScroll = new JScrollPane(inventoryTable);
		inventoryScroll.setBorder(new TitledBorder("Inventory"));
		add(inventoryScroll);
	}
	
	public int getSelectedMonster() {
		return monsterList.getSelectedIndex();
	}
	
	public ItemType getSelectedItem() {
		int row = inventoryTable.getSelectedRow();
		if (row >= 0) {
			return ItemType.values()[row];
		} else {
			return null;
		}
	}
	
	public void update() {
		monsterModel.update();
		invModel.fireTableDataChanged();
	}

}
