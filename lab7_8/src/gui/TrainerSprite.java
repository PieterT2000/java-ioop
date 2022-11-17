package gui;

import java.awt.Color;
import java.awt.Point;

import game_controllers.Trainer;

public class TrainerSprite extends Sprite {
	public static final Point TRAINER_ORIGIN = new Point(0, 0);

	private Trainer trainer;

	public TrainerSprite(Trainer trainer, GameWorld gameWorld) {
		super(gameWorld, TRAINER_ORIGIN);
		this.trainer = trainer;
		this.label = "TRNR";
	}
	
	protected void setGUIProperties() {
		this.boxSize = 32;
		this.color = Color.cyan;
	}

	public Trainer getTrainer() {
		return this.trainer;
	}
}
