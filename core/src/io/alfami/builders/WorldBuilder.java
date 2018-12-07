package io.alfami.builders;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import io.alfami.components.AnimationComponent;
import io.alfami.components.MovementComponent;
import io.alfami.components.PlayerComponent;
import io.alfami.components.StateComponent;
import io.alfami.components.TextureComponent;
import io.alfami.components.TransformComponent;
import io.alfami.gamesys.GameManager;

public class WorldBuilder {

	private final TiledMap tiledMap;
	private final World world;
	private final Engine engine;

	private final AssetManager assetManager;
	private final TextureAtlas actorAtlas;

	public WorldBuilder(TiledMap tiledMap, World world, Engine engine) {
		this.tiledMap = tiledMap;
		this.world = world;
		this.engine = engine;
		assetManager = GameManager.instance.assetManager;
		actorAtlas = new TextureAtlas(Gdx.files.internal("hero/hero.atlas"));
	}

	public void buildAll() {
		buildMap();
	}

	private void buildMap() {
		MapLayers mapLayers = tiledMap.getLayers();

		int mapWidth = ((TiledMapTileLayer) mapLayers.get(0)).getWidth();
		int mapHeight = ((TiledMapTileLayer) mapLayers.get(0)).getHeight();
		MapLayer wallLayer = mapLayers.get("collisions"); // wall layer

		for (MapObject mapObject : wallLayer.getObjects()) {
			Rectangle rectangle = ((RectangleMapObject) mapObject).getRectangle();

//			correctRectangle(rectangle);

			BodyDef bodyDef = new BodyDef();
			bodyDef.type = BodyDef.BodyType.StaticBody;
			bodyDef.position.set(rectangle.x + rectangle.width / 2, rectangle.y + rectangle.height / 2);

			Body body = world.createBody(bodyDef);
			PolygonShape polygonShape = new PolygonShape();
			polygonShape.setAsBox(rectangle.width / 2, rectangle.height / 2);
			FixtureDef fixtureDef = new FixtureDef();
			fixtureDef.shape = polygonShape;
			fixtureDef.filter.categoryBits = GameManager.WALL_BIT;
			fixtureDef.filter.maskBits = GameManager.PLAYER_BIT | GameManager.ENEMY_BIT | GameManager.LIGHT_BIT;
			body.createFixture(fixtureDef);
			polygonShape.dispose();
		}
		createPlayer(752, 320);

	}

	private void createPlayer(float x, float y) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set(x + 8, y + 8);
		bodyDef.linearDamping = 16f;

		Body body = world.createBody(bodyDef);

		PolygonShape circleShape = new PolygonShape();
		circleShape.setAsBox(4, 4);
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = circleShape;
		fixtureDef.filter.categoryBits = GameManager.PLAYER_BIT;
		fixtureDef.filter.maskBits = GameManager.WALL_BIT | GameManager.GATE_BIT | GameManager.ENEMY_BIT
				| GameManager.POWERUP_BIT;
		body.createFixture(fixtureDef);
		circleShape.dispose();

		TextureRegion textureRegion = new TextureRegion(new Texture(Gdx.files.internal("hero/walkdown_02.png")));

		PlayerComponent player = new PlayerComponent(body);

		Entity entity = new Entity();
		entity.add(player);
		entity.add(new TransformComponent(x, y, 10, 0.5f, 0.5f));
		entity.add(new MovementComponent(body));
		entity.add(new StateComponent(PlayerComponent.IDLE_DOWN));
		entity.add(new TextureComponent(textureRegion));

		AnimationComponent animationComponent = new AnimationComponent();
		Animation<TextureRegion> animation;
		Array<TextureRegion> keyFrames = new Array<>();

		// idle
		keyFrames.add(new TextureRegion(new Texture(Gdx.files.internal("hero/walkright_02.png"))));
		animation = new Animation<TextureRegion>(0.2f, keyFrames);
		animationComponent.animations.put(PlayerComponent.IDLE_RIGHT, animation);

		keyFrames.clear();

		keyFrames.add(new TextureRegion(new Texture(Gdx.files.internal("hero/walkleft_02.png"))));
		animation = new Animation<TextureRegion>(0.2f, keyFrames);
		animationComponent.animations.put(PlayerComponent.IDLE_LEFT, animation);

		keyFrames.clear();

		keyFrames.add(new TextureRegion(new Texture(Gdx.files.internal("hero/walkUp_02.png"))));
		animation = new Animation<TextureRegion>(0.2f, keyFrames);
		animationComponent.animations.put(PlayerComponent.IDLE_UP, animation);

		keyFrames.clear();

		keyFrames.add(new TextureRegion(new Texture(Gdx.files.internal("hero/walkdown_02.png"))));
		animation = new Animation<TextureRegion>(0.2f, keyFrames);
		animationComponent.animations.put(PlayerComponent.IDLE_DOWN, animation);

		keyFrames.clear();

		// move
//		for (int i = 1; i < 3; i++) {
//			keyFrames.add(new TextureRegion(actorAtlas.findRegion("Pacman"), i * 16, 0, 16, 16));
//		}
		animation = new Animation<TextureRegion>(0.1f, actorAtlas.findRegions("walkright"), PlayMode.LOOP);
		animationComponent.animations.put(PlayerComponent.MOVE_RIGHT, animation);

//		keyFrames.clear();

//		for (int i = 3; i < 5; i++) {
//			keyFrames.add(new TextureRegion(actorAtlas.findRegion("Pacman"), i * 16, 0, 16, 16));
//		}
		animation = new Animation<TextureRegion>(0.1f, actorAtlas.findRegions("walkleft"), PlayMode.LOOP);
		animationComponent.animations.put(PlayerComponent.MOVE_LEFT, animation);

//		keyFrames.clear();

//		for (int i = 5; i < 7; i++) {
//			keyFrames.add(new TextureRegion(actorAtlas.findRegion("Pacman"), i * 16, 0, 16, 16));
//		}
		animation = new Animation<TextureRegion>(0.1f, actorAtlas.findRegions("walkup"), PlayMode.LOOP);
		animationComponent.animations.put(PlayerComponent.MOVE_UP, animation);

//		keyFrames.clear();

//		for (int i = 7; i < 9; i++) {
//			keyFrames.add(new TextureRegion(actorAtlas.findRegion("Pacman"), i * 16, 0, 16, 16));
//		}
		animation = new Animation<TextureRegion>(0.1f, actorAtlas.findRegions("walkdown"), PlayMode.LOOP);
		animationComponent.animations.put(PlayerComponent.MOVE_DOWN, animation);

//		keyFrames.clear();

//		for (int i = 0; i < 10; i++) {
//			keyFrames.add(new TextureRegion(actorAtlas.findRegion("Pacman"), i * 16, 16, 16, 16));
//		}
//		keyFrames.add(new TextureRegion(actorAtlas.findRegion("Pacman"), 9 * 16, 0, 16, 16)); // invisible
//		animation = new Animation(0.1f, keyFrames, Animation.PlayMode.NORMAL);
//		animationComponent.animations.put(PlayerComponent.DIE, animation);
//
//		keyFrames.clear();

		entity.add(animationComponent);

		engine.addEntity(entity);
//		body.setUserData(entity);
	}

}
