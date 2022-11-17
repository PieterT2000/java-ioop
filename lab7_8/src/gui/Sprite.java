package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class Sprite {
	protected Point location;

	protected String label;
	protected GameWorld gameWorld;
	
	protected Color color;
	protected int boxSize;

	public Sprite(GameWorld gameWorld, Point location) {
		this.gameWorld = gameWorld;
		this.location = location;
		setGUIProperties();
	}
	
	protected abstract void setGUIProperties();

	protected Point getNewLocation(MoveDirection direction) {
		Point newLocation = new Point(location);
		switch (direction) {
		case NORTH:
			newLocation.y = Math.max(newLocation.y - 1, 0);
			break;
		case EAST:
			newLocation.x = Math.min(newLocation.x + 1, GameWorld.GRID_SIZE - 1);
			break;
		case SOUTH:
			newLocation.y = Math.min(newLocation.y + 1, GameWorld.GRID_SIZE - 1);
			break;
		case WEST:
			newLocation.x = Math.max(newLocation.x - 1, 0);
			break;
		}
		return newLocation;
	}
	
	public void move(MoveDirection direction) {
		this.location = getNewLocation(direction);
	}
	
	public Point getLocation() {
		return this.location;
	}

	public void paint(Graphics g) {
		int realX = location.x * GameWorld.SQUARE_WIDTH + (GameWorld.SQUARE_WIDTH / 2) - (boxSize / 2);
		int realY = location.y * GameWorld.SQUARE_HEIGHT + (GameWorld.SQUARE_HEIGHT / 2) - (boxSize / 2);

		// Draw an outlined rectangle with the correct colour
		g.setColor(this.color);
		g.fillRect(realX, realY, boxSize, boxSize);
		g.setColor(Color.WHITE);
		g.drawRect(realX, realY, boxSize, boxSize);

		// Name on top
		if (label != null) {
			String labelToPaint = label;
			if (labelToPaint.length() > 4) {
				labelToPaint = labelToPaint.substring(0, 4);
			}
			g.drawString(labelToPaint, realX - 5, realY - 2);
		}
	}

}
