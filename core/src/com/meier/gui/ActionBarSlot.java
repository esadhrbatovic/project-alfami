package com.meier.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ActionBarSlot {
	Stage stage;
	ImageButton actionbarButton;
	TextureRegionDrawable actionBarButtonDown, actionBarButtonUp;
	
	
	public void create(Stage stage) {
		this.stage = stage;
		actionBarButtonDown = new TextureRegionDrawable(
				new TextureRegion(new Texture("gui/actionbar/actionbar_button_down.png")));
		actionBarButtonUp = new TextureRegionDrawable(
				new TextureRegion(new Texture("gui/actionbar/actionbar_button_up.png")));
		actionbarButton = new ImageButton(actionBarButtonUp);
		actionbarButton.getStyle().imageUp = actionBarButtonUp;
		actionbarButton.getStyle().imageDown = actionBarButtonDown;

	
	}
}
