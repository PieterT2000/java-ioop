package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class DirectionPanel extends JPanel implements ActionListener {

	private GameWorld gameWorld;

	public DirectionPanel(GameWorld gameWorld) {
		this.gameWorld = gameWorld;

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new TitledBorder("Directions"));

		Box northBox = Box.createHorizontalBox();
		northBox.add(Box.createGlue());
		northBox.add(createDirectionButton(MoveDirection.NORTH));
		northBox.add(Box.createGlue());

		Box eastWestBox = Box.createHorizontalBox();
		eastWestBox.add(createDirectionButton(MoveDirection.WEST));
		eastWestBox.add(Box.createHorizontalStrut(25));
		eastWestBox.add(createDirectionButton(MoveDirection.EAST));

		Box southBox = Box.createHorizontalBox();
		southBox.add(Box.createGlue());
		southBox.add(createDirectionButton(MoveDirection.SOUTH));
		southBox.add(Box.createGlue());

		add(northBox);
		add(eastWestBox);
		add(southBox);
	}

	private JButton createDirectionButton(MoveDirection dir) {
		JButton button = new JButton(dir.toString());
		button.addActionListener(this);
		return button;
	}

	public void actionPerformed(ActionEvent evt) {
		String cmd = evt.getActionCommand();
		try {
			MoveDirection moveDirection = MoveDirection.valueOf(cmd);
			TrainerSprite trainerSprite = gameWorld.getTrainerSprite();
			trainerSprite.move(moveDirection);
		} catch (Exception e) {
			System.out.println("Invalid action command");
		}

		// switch (cmd) {
		// case MoveDirection.NORTH:
		// break;
		// case MoveDirection.EAST:
		// System.out.println("you want east");
		// default:
		// break;
		// }
	}
}
