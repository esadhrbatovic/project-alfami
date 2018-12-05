package com.meier.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.meier.entities.Player;

public class MainGUI {

	Player player;
	Stage stage;

	HealthBar healthBar;
	MenuDialog menuDialog;
	InventoryView inventoryView;
	MenuButtonsView menuButtonsView;
	Actionbar actionBar;

	Table playerMenuTable;

	public MainGUI(Player player) {
		this.player = player;
	}

	public void create() {
		stage = new Stage();
		configureInput();
		Gdx.input.setInputProcessor(stage);

		healthBar = new HealthBar();
		healthBar.create(stage, player);

		menuDialog = new MenuDialog();
		menuDialog.create(stage);

		inventoryView = new InventoryView();
		inventoryView.create(stage);

		menuButtonsView = new MenuButtonsView();
		menuButtonsView.create(stage);

		actionBar = new Actionbar();
		actionBar.create(stage, 6);

		menuButtonsView.mainMenuButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				toggleMainMenu();
			}
		});

		menuButtonsView.inventoryButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				toggleInventory();
			}
		});

		playerMenuTable = new Table();
		playerMenuTable.setFillParent(true);
		playerMenuTable.bottom().right().padBottom(40f).padRight(40f);
		playerMenuTable.add(inventoryView.inventoryTable);
		playerMenuTable.row();
		playerMenuTable.add(menuButtonsView.menuButtonsTable).right();

		stage.addActor(playerMenuTable);
		stage.setDebugAll(false);
	}

	public void render() {
		stage.draw();
	}

	public void toggleInventory() {
		if (inventoryView.inventoryTable.isVisible()) {
			menuButtonsView.inventoryButton.setChecked(false);
			inventoryView.inventoryTable.setVisible(false);

		} else {
			menuButtonsView.inventoryButton.setChecked(true);
			inventoryView.inventoryTable.setVisible(true);
		}
	}

	public void toggleMainMenu() {
		if (menuDialog.menuDialogTable.isVisible()) {
			menuButtonsView.mainMenuButton.setChecked(false);
			;
			menuDialog.menuDialogTable.setVisible(false);
		} else {
			menuButtonsView.mainMenuButton.setChecked(true);
			menuDialog.menuDialogTable.setVisible(true);
		}
	}

	public void configureInput() {
		stage.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if (keycode == Keys.I)
					toggleInventory();

				else if (keycode == Keys.ESCAPE) {
					toggleMainMenu();
				}

				if (keycode == Keys.NUM_1) {
					((ImageButton) actionBar.actionBarTable.getCells().get(0).getActor()).toggle();

				}
				if (keycode == Keys.NUM_2) {
					((ImageButton) actionBar.actionBarTable.getCells().get(1).getActor()).toggle();

				}
				if (keycode == Keys.NUM_3) {
					((ImageButton) actionBar.actionBarTable.getCells().get(2).getActor()).toggle();

				}
				if (keycode == Keys.NUM_4) {
					((ImageButton) actionBar.actionBarTable.getCells().get(3).getActor()).toggle();

				}
				if (keycode == Keys.NUM_5) {
					((ImageButton) actionBar.actionBarTable.getCells().get(4).getActor()).toggle();

				}
				if (keycode == Keys.NUM_6) {
					((ImageButton) actionBar.actionBarTable.getCells().get(5).getActor()).toggle();

				}
				return super.keyDown(event, keycode);
			}

			@Override
			public boolean keyUp(InputEvent event, int keycode) {
				if (keycode == Keys.NUM_1) {
					((ImageButton) actionBar.actionBarTable.getCells().get(0).getActor()).toggle();
				}
				if (keycode == Keys.NUM_2) {
					((ImageButton) actionBar.actionBarTable.getCells().get(1).getActor()).toggle();
				}
				if (keycode == Keys.NUM_3) {
					((ImageButton) actionBar.actionBarTable.getCells().get(2).getActor()).toggle();
				}
				if (keycode == Keys.NUM_4) {
					((ImageButton) actionBar.actionBarTable.getCells().get(3).getActor()).toggle();
				}
				if (keycode == Keys.NUM_5) {
					((ImageButton) actionBar.actionBarTable.getCells().get(4).getActor()).toggle();
				}
				if (keycode == Keys.NUM_6) {
					((ImageButton) actionBar.actionBarTable.getCells().get(5).getActor()).toggle();
				}
				return super.keyUp(event, keycode);
			}
		});

	}

}
