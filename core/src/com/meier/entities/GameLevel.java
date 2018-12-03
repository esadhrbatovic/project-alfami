package com.meier.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.meier.behaviour.PlayerMovement;
import com.meier.config.GameConfig;

public class GameLevel extends GameEntity{
	SpriteBatch spriteBatch;
	
	TiledMap tiledMap;
	TiledMapRenderer tiledMapRenderer;
	OrthographicCamera camera;
	
	MapLayer collisionObjectLayer;
	MapObjects objects;
	
	Player player;
	public GameLevel() {

	}

	@Override
	public void create() {
		player = new Player(0,0);
		player.create();

		camera = new OrthographicCamera(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
		camera.zoom = 0.5f;

		tiledMap = new TiledMap();
		tiledMap = new TmxMapLoader().load("unbenannt.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
		
		collisionObjectLayer = tiledMap.getLayers().get(4);
		objects = collisionObjectLayer.getObjects();
		
		camera.position.set(player.x - GameConfig.TILE_SIZE / 2, player.y + 100, 0);

		spriteBatch = new SpriteBatch();	
	}

	@Override
	public void render() {
		handleInput();
		player.checkMapBounds();
		checkCollisions();

		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		
		camera.update();
		camera.position.set(player.x - GameConfig.TILE_SIZE / 2, player.y + GameConfig.TILE_SIZE / 2, 0);
		
		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		spriteBatch.draw(player.currentFrame, player.x, player.y, GameConfig.TILE_SIZE, GameConfig.TILE_SIZE);
		spriteBatch.end();	
	}

	@Override
	public void update() {
		
	}
	
	public void checkCollisions(){
		for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {
		    Rectangle rectangle = rectangleObject.getRectangle();
		    if (Intersector.overlaps(rectangle, player.collisionRect)) {
		        player.blockMovement();
		    }
		}
	}
	
	public void handleInput() {
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			player.normalWalking(PlayerMovement.RIGHT);
		} else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			player.normalWalking(PlayerMovement.LEFT);
		} else if (Gdx.input.isKeyPressed(Keys.UP)) {
			player.normalWalking(PlayerMovement.UP);
		} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			player.normalWalking(PlayerMovement.DOWN);
		} else {
			player.setStandingAnimation();
		}
		
		if(Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)) {
			player.sprinting = true;
		}
		
		if(!Gdx.input.isKeyPressed(Keys.CONTROL_LEFT)) {
			player.sprinting = false;
		}
	}
}
