package com.meier.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.meier.config.GameConfig;

public class Actionbar {
	Table actionBarTable;
	Stage stage;

	public void create(Stage stage, int size) {
		this.stage = stage;
		actionBarTable = new Table();
		actionBarTable.setBackground(
				new TextureRegionDrawable(new TextureRegion(new Texture("gui/actionbar/actionbar_bg.png"))));
		actionBarTable.setSize(300, 60);
		actionBarTable.setVisible(true);
		actionBarTable.left().padLeft(7f);

		actionBarTable.setPosition(GameConfig.SCREEN_WIDTH / 2 - actionBarTable.getWidth() / 2, 0);
		for (int i = 0; i < size; i++) {
			ActionBarSlot abs = new ActionBarSlot();
			abs.create(stage);
			actionBarTable.add(abs.actionbarButton.padLeft(2f));
		}
		
		stage.addActor(actionBarTable);

	}

}
