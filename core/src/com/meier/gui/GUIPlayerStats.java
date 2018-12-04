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
	Table inventoryTable;
	Player player;
	
	ImageButton inventoryButton;
	
	TextureRegionDrawable inventoryButtonDown, inventoryButtonUp, inventoryBackground;	
	boolean inventoryOpen;
	
	
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

		healthTable.left().top().padTop(20f).padLeft(20f);

		stage.addActor(healthTable);

		inventoryButtonUp = new TextureRegionDrawable(new TextureRegion(new Texture("gui/playermenu/guibutton_off.png")));
		inventoryButtonDown = new TextureRegionDrawable(new TextureRegion(new Texture("gui/playermenu/guibutton_on.png")));

		inventoryButton = new ImageButton(inventoryButtonUp);
		inventoryButton.getStyle().imageUp = inventoryButtonUp;
		inventoryButton.getStyle().imageChecked= inventoryButtonDown;
		
		inventoryButton.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				if(inventoryOpen) {
					inventoryOpen = false;
					inventoryTable.setVisible(false);
				}else {
					inventoryOpen = true;
					inventoryTable.setVisible(true);
				}
			}
		});
		
		inventoryTable = new Table();

		inventoryTable.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("gui/playermenu/inventory.png"))));
		inventoryTable.setSize(300, 400);
		inventoryTable.setVisible(false);
		
		playerMenuTable = new Table();
		playerMenuTable.setFillParent(true);
		playerMenuTable.add(inventoryTable);
		playerMenuTable.row();
		playerMenuTable.add(inventoryButton).right();
		playerMenuTable.bottom().right().padBottom(40f).padRight(40f);

		stage.addActor(playerMenuTable);
		stage.setDebugAll(true);
	}

	public void render() {
		stage.draw();


	}

}
