package com.meier.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.meier.entities.Player;

public class GUIPlayerStats {
	Image health;
	Stage stage;
	Table table;
	Player player;

	public GUIPlayerStats(Player player) {
		this.player = player;
	}

	public void create() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		table = new Table();
		table.setFillParent(true);

		for (int i = 0; i < player.lives; i++) {
			table.add(new Image(new Texture("gui/heart/heart_1.png"))).size(32, 32);

		}

		table.left().top();
		table.setPosition(20, -20);

		
		
		stage.addActor(table);

		table.setDebug(false);
	}

	public void render() {
		stage.draw();
	}

	public void lifeBar() {

	}

}
