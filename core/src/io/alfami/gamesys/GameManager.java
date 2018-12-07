package io.alfami.gamesys;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class GameManager {
    public static final GameManager instance = new GameManager();

    public static final float PPM = 16f;

    public static final short NOTHING_BIT = 0;
    public static final short WALL_BIT = 1;
    public static final short PLAYER_BIT = 1 << 1;
    public static final short PILL_BIT = 1 << 2;
    public static final short GHOST_BIT = 1 << 3;
    public static final short GATE_BIT = 1 << 4;
    public static final short LIGHT_BIT = 1 << 5;
    
    public AssetManager assetManager;
    
    public GameManager() {
    	System.out.println("loaded atlas");
        assetManager = new AssetManager();
        assetManager.load("hero/hero.atlas", TextureAtlas.class);
	}
}
