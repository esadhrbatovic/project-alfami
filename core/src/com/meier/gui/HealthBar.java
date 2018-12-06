package com.meier.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.meier.config.GameConfig;
import com.meier.entities.Player;

public class HealthBar {
	Stage stage;
	Player player;
	Table healthTable;
	int hearts;
	int remainingHeartQuarters;
	int maxHearts;

	public void create(Stage stage, Player player) {

		this.stage = stage;
		this.player = player;
		healthTable = new Table();
		healthTable.setPosition(40, GameConfig.SCREEN_HEIGHT - 40);

		maxHearts = player.maxHealthPoints / 100;
		hearts = (player.healthPoints / 25) / 4;

//		remainingHeartQuarters = (int) ((player.maxHealthPoints - player.healthPoints) / 25);

		System.out.println(maxHearts);
		System.out.println(hearts);
		System.out.println(player.healthPoints / 25.0 / 4.0);
		remainingHeartQuarters = (int) (((player.healthPoints / 25.0 / 4.0) - hearts) / 0.25);
		System.out.println(remainingHeartQuarters);
		int filledHearts = 0;

		for (int i = 0; i <= maxHearts; i++) {
			if (i < hearts) {
				healthTable.add(new Image(new Texture("gui/heart/heart_1.png"))).size(32, 32);
				filledHearts++;
			} else if (remainingHeartQuarters > 0) {
				switch (remainingHeartQuarters) {
				case 1:
					healthTable.add(new Image(new Texture("gui/heart/heart_4.png"))).size(32, 32);
					break;
				case 2:
					healthTable.add(new Image(new Texture("gui/heart/heart_3.png"))).size(32, 32);
					break;
				case 3:
					healthTable.add(new Image(new Texture("gui/heart/heart_2.png"))).size(32, 32);
					break;
				}
				filledHearts++;
				break;
			}
		}

		for(int i = 0; i < maxHearts-filledHearts; i++) {
			healthTable.add(new Image(new Texture("gui/heart/heart_5.png"))).size(32, 32);
		}
		healthTable.left().top();

		stage.addActor(healthTable);
	}

	public void update() {

	}

}
