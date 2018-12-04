package com.meier.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.meier.config.GameConfig;
import com.meier.entities.Player;

public class GUIPlayerStats {
	Stage stage;
	Table healthTable;
	Table playerMenuTable;
	Table inventoryTable;
	Table menuButtonsTable;
	Table menuDialogTable;
	Player player;

	ImageButton inventoryButton, mainMenuButton;

	TextureRegionDrawable inventoryButtonDown, inventoryButtonUp, inventoryBackground, mainMenuButtonDown,
			mainMenuButtonUp;

	boolean inventoryOpen;

	public GUIPlayerStats(Player player) {
		this.player = player;
	}

	public void create() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		menuDialogTable = new Table();
		menuDialogTable.setBackground(new TextureRegionDrawable(
				new TextureRegion(new Texture("gui/playermenu/menu_dialog.png"))));
		menuDialogTable.setSize(300, 400);
		menuDialogTable.setVisible(false);
		menuDialogTable.setPosition(GameConfig.SCREEN_WIDTH/2-150, GameConfig.SCREEN_HEIGHT/2-200);
		
		stage.addActor(menuDialogTable);
		
		healthTable = new Table();
		healthTable.setFillParent(true);

		for (int i = 0; i < player.lives; i++) {
			healthTable.add(new Image(new Texture("gui/heart/heart_1.png"))).size(32, 32);
		}

		healthTable.left().top().padTop(20f).padLeft(20f);

		stage.addActor(healthTable);

		mainMenuButtonUp = new TextureRegionDrawable(
				new TextureRegion(new Texture("gui/playermenu/menubutton_off.png")));

		mainMenuButtonDown = new TextureRegionDrawable(
				new TextureRegion(new Texture("gui/playermenu/menubutton_on.png")));

		mainMenuButton = new ImageButton(mainMenuButtonUp);
		mainMenuButton.getStyle().imageUp = mainMenuButtonUp;
		mainMenuButton.getStyle().imageChecked = mainMenuButtonDown;

		mainMenuButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				toggleMainMenu();
			}
		});

		inventoryButtonUp = new TextureRegionDrawable(
				new TextureRegion(new Texture("gui/playermenu/guibutton_off.png")));
		inventoryButtonDown = new TextureRegionDrawable(
				new TextureRegion(new Texture("gui/playermenu/guibutton_on.png")));

		inventoryButton = new ImageButton(inventoryButtonUp);
		inventoryButton.getStyle().imageUp = inventoryButtonUp;
		inventoryButton.getStyle().imageChecked = inventoryButtonDown;

		inventoryButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				toggleInventory();
			}
		});

		inventoryTable = new Table();

		inventoryTable.setBackground(
				new TextureRegionDrawable(new TextureRegion(new Texture("gui/playermenu/inventory.png"))));
		inventoryTable.setSize(300, 400);
		inventoryTable.setVisible(false);

		menuButtonsTable = new Table();
		menuButtonsTable.add(mainMenuButton);
		menuButtonsTable.add(inventoryButton);
		
		playerMenuTable = new Table();
		playerMenuTable.setFillParent(true);
		playerMenuTable.bottom().right().padBottom(40f).padRight(40f);
		playerMenuTable.add(inventoryTable);
		playerMenuTable.row();
		playerMenuTable.add(menuButtonsTable).right();

		stage.addActor(playerMenuTable);
		stage.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if (keycode == Keys.I)
					toggleInventory();

				else if (keycode == Keys.ESCAPE) {
					toggleMainMenu();
				}
				return super.keyDown(event, keycode);
			}
		});

		stage.setDebugAll(true);
	}

	public void render() {
		stage.draw();
	}

	public void toggleInventory() {
		if (inventoryTable.isVisible()) {
			inventoryTable.setVisible(false);
			inventoryButton.setChecked(false);

		} else {
			inventoryTable.setVisible(true);
			inventoryButton.setChecked(true);
		}
	}
	
	public void toggleMainMenu() {
		if (menuDialogTable.isVisible()) {
			menuDialogTable.setVisible(false);
			mainMenuButton.setChecked(false);

		} else {
			menuDialogTable.setVisible(true);
			mainMenuButton.setChecked(true);
		}
	}

}
