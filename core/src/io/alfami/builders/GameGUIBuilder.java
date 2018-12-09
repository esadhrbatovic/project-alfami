package io.alfami.builders;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import io.alfami.config.GameConfig;

public class GameGUIBuilder {
	public Stage stage;
	Table menuButtonsTable;

	public Table actionBarTableActor, healthBarTableActor, inventoryTableActor, menuDialogTableActor;

	public List<ImageButton> actionBarSlotActors;

	TextureRegionDrawable inventoryButtonDown, inventoryButtonUp, inventoryBackground, mainMenuButtonDown,
			mainMenuButtonUp;

	ImageButton inventoryButton, mainMenuButton;

	public GameGUIBuilder(Stage stage) {
		super();
		this.stage = stage;
		Gdx.input.setInputProcessor(stage);
	}

	public void buildUi() {
		buildActionBar(6);
		buildInventory(35);
		buildInputHandler();
		buildPlayerMenuBar();
		buildMenuDialog();

	}

	private void buildMenuDialog() {
		menuDialogTableActor = new Table();
		menuDialogTableActor.setBackground(
				new TextureRegionDrawable(new TextureRegion(new Texture("gui/mainmenu/mainmenu_bg.png"))));
		menuDialogTableActor.setSize(300, 400);
		menuDialogTableActor.setVisible(false);
		menuDialogTableActor.setPosition(GameConfig.SCREEN_WIDTH / 2 - 150, GameConfig.SCREEN_HEIGHT / 2 - 200);

		TextButtonStyle style = new TextButtonStyle();
		style.font = new BitmapFont();

		for (int i = 0; i < 5; i++) {
			TextButton menuDialogOption = new TextButton("menu option" + (i + 1), style);
			menuDialogOption.padTop(30f);
			menuDialogTableActor.add(menuDialogOption);
			menuDialogTableActor.row();
		}
		stage.addActor(menuDialogTableActor);

	}

	private void buildPlayerMenuBar() {
		mainMenuButtonUp = new TextureRegionDrawable(
				new TextureRegion(new Texture("gui/menuwidget/menubutton_off.png")));

		mainMenuButtonDown = new TextureRegionDrawable(
				new TextureRegion(new Texture("gui/menuwidget/menubutton_on.png")));

		mainMenuButton = new ImageButton(mainMenuButtonUp);
		mainMenuButton.getStyle().imageUp = mainMenuButtonUp;
		mainMenuButton.getStyle().imageDown = mainMenuButtonDown;
		mainMenuButton.getStyle().imageChecked = mainMenuButtonDown;

		inventoryButtonUp = new TextureRegionDrawable(
				new TextureRegion(new Texture("gui/menuwidget/guibutton_off.png")));
		inventoryButtonDown = new TextureRegionDrawable(
				new TextureRegion(new Texture("gui/menuwidget/guibutton_on.png")));

		inventoryButton = new ImageButton(inventoryButtonUp);
		inventoryButton.getStyle().imageUp = inventoryButtonUp;
		inventoryButton.getStyle().imageDown = inventoryButtonDown;
		inventoryButton.getStyle().imageChecked = inventoryButtonDown;

		menuButtonsTable = new Table();
		menuButtonsTable.add(mainMenuButton);
		menuButtonsTable.add(inventoryButton);
		menuButtonsTable.setSize(90, 50);
		menuButtonsTable.setPosition(GameConfig.SCREEN_WIDTH - 90 - 40, 40);
		stage.addActor(menuButtonsTable);

		mainMenuButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				toggleMainMenu();
			}
		});

		inventoryButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				toggleInventory();
			}
		});
	}

	private ImageButton buildInventorySlot() {
		TextureRegionDrawable inventorySlotButtonEmpty = new TextureRegionDrawable(
				new TextureRegion(new Texture("gui/inventory/inventory_slot.png")));
		ImageButton inventorySlotButton = new ImageButton(inventorySlotButtonEmpty);
		inventorySlotButton.getStyle().imageUp = inventorySlotButtonEmpty;
		inventorySlotButton.getStyle().imageDown = inventorySlotButtonEmpty;
		inventorySlotButton.pad(2f);
		return inventorySlotButton;
	}

	private void buildInventory(int capacity) {
		inventoryTableActor = new Table();

		inventoryTableActor.setBackground(
				new TextureRegionDrawable(new TextureRegion(new Texture("gui/inventory/inventory_bg.png"))));
		inventoryTableActor.setSize(300, 400);
		inventoryTableActor.setVisible(false);
		inventoryTableActor.center();
		inventoryTableActor.setPosition(GameConfig.SCREEN_WIDTH - 300 - 40, 90);

		for (int i = 0; i < capacity; i++) {
			if (i != 0 && i % 5 == 0) {
				inventoryTableActor.row();
			}
			inventoryTableActor.add(buildInventorySlot());
		}

		stage.addActor(inventoryTableActor);

	}

	private Table buildActionBar(int size) {
		Table actionBarTable = new Table();
		actionBarSlotActors = new ArrayList<>();

		actionBarTable.setBackground(
				new TextureRegionDrawable(new TextureRegion(new Texture("gui/actionbar/actionbar_bg.png"))));
		actionBarTable.setSize(300, 60);
		actionBarTable.setVisible(true);
		actionBarTable.left().padLeft(7f);

		actionBarTable.setPosition(GameConfig.SCREEN_WIDTH / 2 - actionBarTable.getWidth() / 2, 0);
		for (int i = 0; i < size; i++) {
			ImageButton abs = buildActionBarSlot();
			actionBarSlotActors.add(abs);
			abs.setName("actionBarSlot" + i);
			actionBarTable.add(abs.padLeft(2f));
		}

		stage.addActor(actionBarTable);
		return actionBarTable;
	}

	private ImageButton buildActionBarSlot() {
		ImageButton actionbarButton;
		TextureRegionDrawable actionBarButtonDown, actionBarButtonUp;

		actionBarButtonDown = new TextureRegionDrawable(
				new TextureRegion(new Texture("gui/actionbar/actionbar_button_down.png")));
		actionBarButtonUp = new TextureRegionDrawable(
				new TextureRegion(new Texture("gui/actionbar/actionbar_button_up.png")));
		actionbarButton = new ImageButton(actionBarButtonUp);
		actionbarButton.getStyle().imageUp = actionBarButtonUp;
		actionbarButton.getStyle().imageDown = actionBarButtonDown;
		actionbarButton.getStyle().imageChecked = actionBarButtonDown;
		actionbarButton.addListener(new ClickListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				actionbarButton.setChecked(false);
				super.touchUp(event, x, y, pointer, button);
			}
		});
		return actionbarButton;
	}

	private void buildInputHandler() {
		stage.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if (keycode == Keys.I)
					toggleInventory();

				else if (keycode == Keys.ESCAPE) {
					toggleMainMenu();
				}
				switch (keycode) {
				case Keys.NUM_1:
					actionBarSlotActors.get(0).toggle();
					break;
				case Keys.NUM_2:
					actionBarSlotActors.get(1).toggle();
					break;
				case Keys.NUM_3:
					actionBarSlotActors.get(2).toggle();
					break;
				case Keys.NUM_4:
					actionBarSlotActors.get(3).toggle();
					break;
				case Keys.NUM_5:
					actionBarSlotActors.get(4).toggle();
					break;
				case Keys.NUM_6:
					actionBarSlotActors.get(5).toggle();
					break;
				}

				return super.keyDown(event, keycode);
			}

			@Override
			public boolean keyUp(InputEvent event, int keycode) {
				switch (keycode) {
				case Keys.NUM_1:
					actionBarSlotActors.get(0).toggle();
					break;
				case Keys.NUM_2:
					actionBarSlotActors.get(1).toggle();
					break;
				case Keys.NUM_3:
					actionBarSlotActors.get(2).toggle();
					break;
				case Keys.NUM_4:
					actionBarSlotActors.get(3).toggle();
					break;
				case Keys.NUM_5:
					actionBarSlotActors.get(4).toggle();
					break;
				case Keys.NUM_6:
					actionBarSlotActors.get(5).toggle();
					break;
				}

				return super.keyUp(event, keycode);
			}
		});
	}

	public void toggleInventory() {
		if (inventoryTableActor.isVisible()) {
			inventoryButton.setChecked(false);
			inventoryTableActor.setVisible(false);

		} else {
			inventoryButton.setChecked(true);
			inventoryTableActor.setVisible(true);
		}
	}

	public void toggleMainMenu() {
		if (menuDialogTableActor.isVisible()) {
			mainMenuButton.setChecked(false);
			menuDialogTableActor.setVisible(false);
		} else {
			mainMenuButton.setChecked(true);
			menuDialogTableActor.setVisible(true);
		}
	}

}
