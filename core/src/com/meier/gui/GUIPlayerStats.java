package com.meier.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.meier.entities.Player;

public class GUIPlayerStats {
	Stage stage;
	Table healthTable;
	Table playerMenuTable;
	Player player;
	
	ImageButton inventoryOpen;
	
	TextureRegionDrawable inventoryButtonDown, inventoryButtonUp;	
	boolean inventar;
	public GUIPlayerStats(Player player) {
		this.player = player;
	}

	public void create() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		healthTable = new Table();
		healthTable.setFillParent(true);

		for (int i = 0; i < player.lives; i++) {
			healthTable.add(new Image(new Texture("gui/heart/heart_1.png"))).size(32, 32);
		}

		healthTable.left().top();
		healthTable.setPosition(20, -20);

		stage.addActor(healthTable);

		inventoryButtonUp = new TextureRegionDrawable(new TextureRegion(new Texture("gui/playermenu/guibutton_off.png")));
		inventoryButtonDown = new TextureRegionDrawable(new TextureRegion(new Texture("gui/playermenu/guibutton_on.png")));

		
		inventoryOpen = new ImageButton(inventoryButtonUp);
		inventoryOpen.getStyle().imageUp = inventoryButtonUp;
		inventoryOpen.getStyle().imageChecked= inventoryButtonDown;
		
		inventoryOpen.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				if(inventar) {
					inventar = false;
				}else {
					inventar = true;
				}
			}
		});
		
		playerMenuTable = new Table();
		playerMenuTable.setFillParent(true);
		playerMenuTable.add(inventoryOpen);
		playerMenuTable.right().bottom().padBottom(40f).padRight(40f);

		stage.addActor(playerMenuTable);
	}

	public void render() {
		stage.draw();


	}

	public void lifeBar() {

	}

}
