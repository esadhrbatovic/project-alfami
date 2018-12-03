package com.meier;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.meier.entities.GameLevel;

public class GameApplication extends ApplicationAdapter {

	GameLevel level;

	@Override
	public void create() {
		level = new GameLevel();
		level.create();
        
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	
		level.render();
	}

	@Override
	public void dispose() {
	}


}
