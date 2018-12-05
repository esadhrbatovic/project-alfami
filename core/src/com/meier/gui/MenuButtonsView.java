package com.meier.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MenuButtonsView {
	Stage stage;
	Table menuButtonsTable;
	ImageButton inventoryButton, mainMenuButton;
	TextureRegionDrawable inventoryButtonDown, inventoryButtonUp, inventoryBackground, mainMenuButtonDown,
	mainMenuButtonUp;
	
	public void create(Stage stage) {
		this.stage = stage;
		
		mainMenuButtonUp = new TextureRegionDrawable(
				new TextureRegion(new Texture("gui/playermenu/menubutton_off.png")));

		mainMenuButtonDown = new TextureRegionDrawable(
				new TextureRegion(new Texture("gui/playermenu/menubutton_on.png")));

		mainMenuButton = new ImageButton(mainMenuButtonUp);
		mainMenuButton.getStyle().imageUp = mainMenuButtonUp;
		mainMenuButton.getStyle().imageDown = mainMenuButtonDown;
		mainMenuButton.getStyle().imageChecked = mainMenuButtonDown;
		
		inventoryButtonUp = new TextureRegionDrawable(
				new TextureRegion(new Texture("gui/playermenu/guibutton_off.png")));
		inventoryButtonDown = new TextureRegionDrawable(
				new TextureRegion(new Texture("gui/playermenu/guibutton_on.png")));

		inventoryButton = new ImageButton(inventoryButtonUp);
		inventoryButton.getStyle().imageUp = inventoryButtonUp;
		inventoryButton.getStyle().imageDown = inventoryButtonDown;
		inventoryButton.getStyle().imageChecked = inventoryButtonDown;

		menuButtonsTable = new Table();
		menuButtonsTable.add(mainMenuButton.padRight(2f));
		menuButtonsTable.add(inventoryButton.padRight(2f));
		

	}
	

}
