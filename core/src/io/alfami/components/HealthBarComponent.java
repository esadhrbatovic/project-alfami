package io.alfami.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import io.alfami.config.GameConfig;

public class HealthBarComponent implements Component {
	public Table healthBarTableActor;
	public Stage stage;

	public HealthBarComponent(int startHealth, int maxHealth, Stage stage) {
		Table healthTable = new Table();
		healthTable.setPosition(40, GameConfig.SCREEN_HEIGHT - 40);

		healthTable.left().top();
		healthBarTableActor = healthTable;
		for (int i = 0; i < maxHealth/100; i++) {
			healthTable.add(new Image(new Texture("gui/heart/heart_1.png"))).size(32, 32);
		}
		healthTable.left().top();
		stage.addActor(healthBarTableActor);

	}

}
