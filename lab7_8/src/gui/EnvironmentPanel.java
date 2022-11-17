package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class EnvironmentPanel extends JPanel implements ActionListener {

	private GameWorld gameWorld;

	private JButton respawnButton, startButton, stopButton;

	public EnvironmentPanel(GameWorld gameWorld) {
		this.gameWorld = gameWorld;

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(new TitledBorder("Environment controls"));

		respawnButton = new JButton("Respawn all");
		startButton = new JButton("Start monsters");
		stopButton = new JButton("Stop monsters");

		add(respawnButton);
		add(startButton);
		add(stopButton);

		respawnButton.addActionListener(this);
		startButton.addActionListener(this);
		stopButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == respawnButton) {
			gameWorld.spawnMonstersAndItems();
		} else if (e.getSource() == startButton) {
			gameWorld.startMovingMonsters();
		} else if (e.getSource() == stopButton) {
			gameWorld.stopMovingMonsters();
		}
	}

}
