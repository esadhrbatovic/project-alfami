package com.meier.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.meier.config.GameConfig;

public class MenuDialog {
	Table menuDialogTable;
	Stage stage;
	TextButton menuDialogOption;
	public MenuDialog() {
		// TODO Auto-generated constructor stub
	}

	public void create(Stage stage) {
		this.stage = stage;
		menuDialogTable = new Table();
		menuDialogTable.setBackground(new TextureRegionDrawable(
				new TextureRegion(new Texture("gui/playermenu/menu_dialog.png"))));
		menuDialogTable.setSize(300, 400);
		menuDialogTable.setVisible(false);
		menuDialogTable.setPosition(GameConfig.SCREEN_WIDTH/2-150, GameConfig.SCREEN_HEIGHT/2-200);

		TextButtonStyle style = new TextButtonStyle();
		style.font = new BitmapFont();
		
		
		for(int i = 0; i < 5; i ++) {
			menuDialogOption = new TextButton("menu option" + (i+1), style);
			menuDialogOption.padTop(30f);
			menuDialogTable.add(menuDialogOption);	
			menuDialogTable.row();
		}
		
		stage.addActor(menuDialogTable);
	}
	

	
}
