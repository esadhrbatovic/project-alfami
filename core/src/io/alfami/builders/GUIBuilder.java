package io.alfami.builders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import io.alfami.config.GameConfig;

public class GUIBuilder {
	public Stage stage;

	public GUIBuilder(Stage stage) {
		super();
		this.stage = stage;
		Gdx.input.setInputProcessor(stage);
	}

	public void buildUi() {
		buildActionBar(6);
	}

	private void buildActionBar(int size) {
		Table actionBarTable = new Table();

		actionBarTable.setBackground(
				new TextureRegionDrawable(new TextureRegion(new Texture("gui/actionbar/actionbar_bg.png"))));
		actionBarTable.setSize(300, 60);
		actionBarTable.setVisible(true);
		actionBarTable.left().padLeft(7f);

		actionBarTable.setPosition(GameConfig.SCREEN_WIDTH / 2 - actionBarTable.getWidth() / 2, 0);
		for (int i = 0; i < size; i++) {
			ImageButton abs = buildActionBarSlot();

			actionBarTable.add(abs.padLeft(2f));
		}

		stage.addActor(actionBarTable);
		stage.addActor(buildHealthBar());
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

	private Table buildHealthBar() {
		Table healthTable = new Table();
		healthTable.setPosition(40, GameConfig.SCREEN_HEIGHT - 40);

		int maxHearts = 500 / 100;
		for (int i = 0; i < maxHearts; i++) {
			healthTable.add(new Image(new Texture("gui/heart/heart_1.png"))).size(32, 32);
		}
		healthTable.left().top();

		return healthTable;
	}

}
