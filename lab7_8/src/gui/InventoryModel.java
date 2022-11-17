package gui;

import javax.swing.table.AbstractTableModel;

import game_controllers.Trainer;
import items.ItemType;

@SuppressWarnings("serial")
public class InventoryModel extends AbstractTableModel {
	
	private Trainer trainer;
	
	public InventoryModel(Trainer trainer) {
		this.trainer = trainer;
	}
	
	@Override
	public String getColumnName(int col) {
		if (col == 0) {
			return "Item";
		} else if (col == 1) {
			return "Count";
		} else {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public int getRowCount() {
		return ItemType.values().length;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ItemType type = ItemType.values()[rowIndex];
		if (columnIndex == 0) {
			return type;
		} else {
			return trainer.countCharges(type.toString());
		}
	}

}
