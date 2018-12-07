package legacy.io.alfami.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import io.alfami.config.GameConfig;

public class MenuButtonsView {
	Stage stage;
	Table menuButtonsTable;
	ImageButton inventoryButton, mainMenuButton;
	TextureRegionDrawable inventoryButtonDown, inventoryButtonUp, inventoryBackground, mainMenuButtonDown,
			mainMenuButtonUp;

	public void create(Stage stage) {
		this.stage = stage;

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

	}

}
