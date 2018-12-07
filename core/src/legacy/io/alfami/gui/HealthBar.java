package legacy.io.alfami.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import io.alfami.config.GameConfig;
import legacy.io.alfami.entities.Player;

public class HealthBar {
	Stage stage;
	Player player;
	Table healthTable;
	int fullHearts;
	int remainingHeartQuarters;
	int maxHearts;

	public void create(Stage stage, Player player) {

		this.stage = stage;
		this.player = player;
		healthTable = new Table();
		healthTable.setPosition(40, GameConfig.SCREEN_HEIGHT - 40);

		maxHearts = player.maxHealthPoints / 100;
		for (int i = 0; i < maxHearts; i++) {
			healthTable.add(new Image()).size(32, 32);
		}
		healthTable.left().top();

		stage.addActor(healthTable);
	}

	/**
	 * rendering hearts according to player's health
	 */
	public void update() {

		fullHearts = (player.healthPoints / 25) / 4;
		remainingHeartQuarters = (int) (((player.healthPoints / 25.0 / 4.0) - fullHearts) / 0.25);
		int filledHearts = 0;
		for (int i = 0; i < maxHearts; i++) {
			if (i < fullHearts) {
				((Image) healthTable.getCells().get(i).getActor())
						.setDrawable(new SpriteDrawable(new Sprite(new Texture("gui/heart/heart_1.png"))));
				filledHearts++;
			} else if (remainingHeartQuarters > 0) {
				switch (remainingHeartQuarters) {
				case 1:
					((Image) healthTable.getCells().get(i).getActor())
							.setDrawable(new SpriteDrawable(new Sprite(new Texture("gui/heart/heart_4.png"))));
					break;
				case 2:
					((Image) healthTable.getCells().get(i).getActor())
							.setDrawable(new SpriteDrawable(new Sprite(new Texture("gui/heart/heart_3.png"))));
					break;
				case 3:
					((Image) healthTable.getCells().get(i).getActor())
							.setDrawable(new SpriteDrawable(new Sprite(new Texture("gui/heart/heart_2.png"))));
					break;
				}
				filledHearts++;
				break;
			}
		}

		for (int i = maxHearts - (maxHearts - filledHearts); i < maxHearts; i++) {

			((Image) healthTable.getCells().get(i).getActor())
					.setDrawable(new SpriteDrawable(new Sprite(new Texture("gui/heart/heart_5.png"))));

		}
	}

}
