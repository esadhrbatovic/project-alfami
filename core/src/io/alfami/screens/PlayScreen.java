package io.alfami.screens;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TmxMapLoader.Parameters;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import io.alfami.Alfami;
import io.alfami.builders.GameGUIBuilder;
import io.alfami.builders.WorldBuilder;
import io.alfami.config.GameConfig;
import io.alfami.systems.AnimationSystem;
import io.alfami.systems.MovementSystem;
import io.alfami.systems.PlayerSystem;
import io.alfami.systems.RenderSystem;
import io.alfami.systems.StateSystem;

public class PlayScreen implements Screen {
	private final float WIDTH = GameConfig.SCREEN_WIDTH;
	private final float HEIGHT = GameConfig.SCREEN_HEIGHT;

//	private final Alfami game;
	private SpriteBatch batch;

	private FitViewport viewport;
	private OrthographicCamera camera;

	private Engine engine;

	private PlayerSystem playerSystem;
	private MovementSystem movementSystem;
	private RenderSystem renderSystem;
	private AnimationSystem animationSystem;
	private StateSystem stateSystem;

	private TiledMap tiledMap;
	private OrthogonalTiledMapRenderer tiledMapRenderer;

	private FitViewport stageViewport;
	private Stage stage;

	private WorldBuilder worldBuilder;
	private GameGUIBuilder guiBuilder;

	private World world;
	private Box2DDebugRenderer box2DDebugRenderer;
	private boolean showBox2DDebuggerRenderer;

	public PlayScreen(Alfami game) {
//		this.game = game;
		this.batch = game.batch;
	}

	@Override
	public void show() {
		camera = new OrthographicCamera();
		viewport = new FitViewport(WIDTH, HEIGHT, camera);
		camera.zoom = 0.5f;
		camera.translate(752, 320);

		camera.update();

		batch = new SpriteBatch();

		movementSystem = new MovementSystem();
		renderSystem = new RenderSystem(batch);
		animationSystem = new AnimationSystem();
		playerSystem = new PlayerSystem(camera);
		stateSystem = new StateSystem();

		engine = new Engine();
		engine.addSystem(movementSystem);
		engine.addSystem(renderSystem);
		engine.addSystem(playerSystem);
		engine.addSystem(animationSystem);
		engine.addSystem(stateSystem);

		world = new World(Vector2.Zero, true);
		box2DDebugRenderer = new Box2DDebugRenderer();
		showBox2DDebuggerRenderer = false;
		Parameters params = new Parameters();
		params.textureMinFilter = TextureFilter.Linear;
		params.textureMagFilter = TextureFilter.Nearest;
		tiledMap = new TmxMapLoader().load("startarea.tmx", params);

		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, batch);

		stageViewport = new FitViewport(WIDTH, HEIGHT);
		stage = new Stage(stageViewport, batch);

		guiBuilder = new GameGUIBuilder(stage);
		guiBuilder.buildUi();

		worldBuilder = new WorldBuilder(tiledMap, world, engine, stage);
		worldBuilder.buildMap();
		worldBuilder.buildPlayer(752, 200);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		batch.setProjectionMatrix(camera.combined);

		world.step(1 / 60f, 8, 1);

		engine.update(delta);
		if (showBox2DDebuggerRenderer) {
			box2DDebugRenderer.render(world, camera.combined);
		}
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		stageViewport.update(width, height);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
