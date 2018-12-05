package com.meier.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.meier.config.GameConfig;

public class Inventory {
	Stage stage;
	Table inventoryTable;

	public void create(Stage stage) {
		this.stage = stage;
		inventoryTable = new Table();

		inventoryTable.setBackground(
				new TextureRegionDrawable(new TextureRegion(new Texture("gui/inventory/inventory_bg.png"))));
		inventoryTable.setSize(300, 400);
		inventoryTable.setVisible(false);
		inventoryTable.center();
		inventoryTable.setPosition(GameConfig.SCREEN_WIDTH - 300 - 40, 90);

		for (int i = 0; i < 35; i++) {

			if (i != 0 && i % 5 == 0) {
				inventoryTable.row();
			}
			InventorySlot is = new InventorySlot();
			is.create(stage);
			inventoryTable.add(is.inventorySlotButton);
		}

		stage.addActor(inventoryTable);

	}

}
