package com.meier.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.meier.entities.Player;

public class HealthBar {
	Stage stage;
	Player player;
	Table healthTable;

	public HealthBar() {
		super();

	}

	public void create(Stage stage, Player player) {
		
		this.stage = stage;
		this.player = player;
		healthTable = new Table();
		healthTable.setFillParent(true);

		for (int i = 0; i < player.lives; i++) {
			healthTable.add(new Image(new Texture("gui/heart/heart_1.png"))).size(32, 32);
		}

		healthTable.left().top().padTop(20f).padLeft(20f);

		stage.addActor(healthTable);
	}
	
}
