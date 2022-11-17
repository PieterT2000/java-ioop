package gui;

import java.awt.BorderLayout;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	private GameWorld gameWorld;
	private ControlPanel controlPanel;

	public GameWindow() {
		super("Monster battling game 2.0");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationByPlatform(true);
		setResizable(false);
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// Don't care, it'll just not look like a native application
		}
		
		gameWorld = new GameWorld(this);
		controlPanel = new ControlPanel(gameWorld);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(gameWorld);
		getContentPane().add(controlPanel, BorderLayout.EAST);
		pack();
	}
	
	public void chooseStarter() {
		List<String> options = Arrays.asList("Seedatops", "Scorchitile", "Dragonfish");
		Collections.shuffle(options);
		Object selectedValue = JOptionPane.showInputDialog(this,
	             "Choose your starting monster", "Choose a starter",
	             JOptionPane.QUESTION_MESSAGE, null,
	             options.toArray(), null);
		if (selectedValue == null) {
			System.exit(0);
		} else {
			gameWorld.setStarter((String)selectedValue);
			controlPanel.update();
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				GameWindow gw = new GameWindow();
				gw.setVisible(true);
				gw.chooseStarter();
			}
		});
	}

}
