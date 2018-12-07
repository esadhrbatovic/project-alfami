package legacy.io.alfami.entities;

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

import io.alfami.config.GameConfig;
import legacy.io.alfami.behaviour.PlayerMovement;
import legacy.io.alfami.gui.MainGUI;

/**
 * Combines all game entities, handles their state, dependencies and rendering.
 * 
 * @author esadhrbatovic
 *
 */
public class GameLevel extends GameEntity {
	SpriteBatch spriteBatch;
	TiledMap tiledMap;
	TiledMapRenderer tiledMapRenderer;
	OrthographicCamera camera;
	MapLayer collisionObjectLayer;
	MapObjects objects;
	Player player;
	MainGUI guiPlayerStats;

	public GameLevel() {

	}

	@Override
	public void create() {
		player = new Player(752, 320);

		player.create();

		guiPlayerStats = new MainGUI(player);
		guiPlayerStats.create();

		camera = new OrthographicCamera(GameConfig.SCREEN_WIDTH, GameConfig.SCREEN_HEIGHT);
		camera.zoom = 0.5f;

		tiledMap = new TiledMap();
		tiledMap = new TmxMapLoader().load("unbenannt.tmx");
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

		collisionObjectLayer = tiledMap.getLayers().get(6);
		objects = collisionObjectLayer.getObjects();

		camera.position.set(player.x, player.y, 0);

		spriteBatch = new SpriteBatch();
	}

	@Override
	public void render() {
		handleInput();
		player.checkMapBounds();
		checkCollisions();

		tiledMapRenderer.setView(camera);

		camera.update();

		camera.position.set(player.x, player.y, 0);
		int data[] = { 0, 1, 2, 3 };

		tiledMapRenderer.render(data);
		spriteBatch.setProjectionMatrix(camera.combined);
		spriteBatch.begin();
		spriteBatch.draw(player.currentFrame, player.x, player.y, GameConfig.TILE_SIZE, GameConfig.TILE_SIZE);

		spriteBatch.end();
		int data2[] = { 4, 5 };
		tiledMapRenderer.render(data2);

		guiPlayerStats.render();

	}

	@Override
	public void update() {

	}

	public void checkCollisions() {
		for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {
			Rectangle rectangle = rectangleObject.getRectangle();
			if (Intersector.overlaps(rectangle, player.collisionRect)) {
				player.blockMovement();
			}
		}
	}

	public void handleInput() {
		if (Gdx.input.isKeyPressed(Keys.D)) {
			player.normalWalking(PlayerMovement.RIGHT);
		} else if (Gdx.input.isKeyPressed(Keys.A)) {
			player.normalWalking(PlayerMovement.LEFT);
		} else if (Gdx.input.isKeyPressed(Keys.W)) {
			player.normalWalking(PlayerMovement.UP);
		} else if (Gdx.input.isKeyPressed(Keys.S)) {
			player.normalWalking(PlayerMovement.DOWN);
		} else {
			player.setStandingAnimation();
		}

		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
			player.sprinting = true;
		}

		if (!Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
			player.sprinting = false;
		}
	}

}
