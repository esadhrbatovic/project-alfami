package com.meier.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class InventoryView {
	Stage stage;
	Table inventoryTable;
	
	public void create(Stage stage) {
		this.stage = stage;
		inventoryTable = new Table();

		inventoryTable.setBackground(
				new TextureRegionDrawable(new TextureRegion(new Texture("gui/playermenu/inventory.png"))));
		inventoryTable.setSize(300, 400);
		inventoryTable.setVisible(false);
	}
	

}
