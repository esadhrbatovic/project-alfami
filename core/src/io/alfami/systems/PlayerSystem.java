package io.alfami.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.RayCastCallback;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import io.alfami.components.HealthBarComponent;
import io.alfami.components.HealthComponent;
import io.alfami.components.MovementComponent;
import io.alfami.components.PlayerComponent;
import io.alfami.components.StateComponent;
import io.alfami.gamesys.GameManager;

public class PlayerSystem extends IteratingSystem {

	private final ComponentMapper<PlayerComponent> playerM = ComponentMapper.getFor(PlayerComponent.class);
	private final ComponentMapper<MovementComponent> movementM = ComponentMapper.getFor(MovementComponent.class);
	private final ComponentMapper<StateComponent> stateM = ComponentMapper.getFor(StateComponent.class);
	private final ComponentMapper<HealthComponent> playerH = ComponentMapper.getFor(HealthComponent.class);
	private final ComponentMapper<HealthBarComponent> playerHB = ComponentMapper.getFor(HealthBarComponent.class);

	private final Vector2 tmpV1 = new Vector2();
	private final Vector2 tmpV2 = new Vector2();
	private boolean canMove;

	private enum MoveDir {
		UP, DOWN, LEFT, RIGHT
	}

	OrthographicCamera camera;

	public PlayerSystem(OrthographicCamera camera) {
		super(Family.all(PlayerComponent.class, MovementComponent.class, StateComponent.class).get());
		this.camera = camera;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		PlayerComponent player = playerM.get(entity);
		StateComponent state = stateM.get(entity);
		MovementComponent movement = movementM.get(entity);
		HealthComponent health = playerH.get(entity);
		HealthBarComponent healthBar = playerHB.get(entity);

		Body body = movement.body;
		if (health.healthPoints > 0) {
			if ((Gdx.input.isKeyPressed(Input.Keys.D) && checkMovable(body, MoveDir.RIGHT))) {
				body.applyLinearImpulse(tmpV1.set(movement.speed, 0).scl(body.getMass()), body.getWorldCenter(), true);
			} else if ((Gdx.input.isKeyPressed(Input.Keys.A) && checkMovable(body, MoveDir.LEFT))) {
				body.applyLinearImpulse(tmpV1.set(-movement.speed, 0).scl(body.getMass()), body.getWorldCenter(), true);
			} else if ((Gdx.input.isKeyPressed(Input.Keys.W) && checkMovable(body, MoveDir.UP))) {
				body.applyLinearImpulse(tmpV1.set(0, movement.speed).scl(body.getMass()), body.getWorldCenter(), true);
			} else if ((Gdx.input.isKeyPressed(Input.Keys.S) && checkMovable(body, MoveDir.DOWN))) {
				body.applyLinearImpulse(tmpV1.set(0, -movement.speed).scl(body.getMass()), body.getWorldCenter(), true);
			} else if ((Gdx.input.isKeyJustPressed(Input.Keys.Q))) {
				health.healthPoints -= 23;
				updateHealthBar(health.healthPoints, health.maxHealthPoints / 100, healthBar);
			} else if ((Gdx.input.isKeyJustPressed(Input.Keys.E)) && health.healthPoints <= 500) {
				health.healthPoints += 14;
				updateHealthBar(health.healthPoints, health.maxHealthPoints / 100, healthBar);
			}
		}

		camera.position.set(movement.body.getPosition().x, movement.body.getPosition().y, 0);

		correctCamera();
		camera.update();

		player.playerAgent.update(deltaTime);
		state.setState(player.currentState);

	}

	private void updateHealthBar(int healthPoints, int maxHearts, HealthBarComponent healthBar) {
		int fullHearts = (healthPoints / 25) / 4;
		int remainingHeartQuarters = (int) (((healthPoints / 25.0 / 4.0) - fullHearts) / 0.25);
		int filledHearts = 0;
		for (int i = 0; i < maxHearts; i++) {
			if (i < fullHearts) {
				((Image) healthBar.healthBarTableActor.getCells().get(i).getActor())
						.setDrawable(new SpriteDrawable(new Sprite(new Texture("gui/heart/heart_1.png"))));
				filledHearts++;
			} else if (remainingHeartQuarters > 0) {
				switch (remainingHeartQuarters) {
				case 1:
					((Image) healthBar.healthBarTableActor.getCells().get(i).getActor())
							.setDrawable(new SpriteDrawable(new Sprite(new Texture("gui/heart/heart_4.png"))));
					break;
				case 2:
					((Image) healthBar.healthBarTableActor.getCells().get(i).getActor())
							.setDrawable(new SpriteDrawable(new Sprite(new Texture("gui/heart/heart_3.png"))));
					break;
				case 3:
					((Image) healthBar.healthBarTableActor.getCells().get(i).getActor())
							.setDrawable(new SpriteDrawable(new Sprite(new Texture("gui/heart/heart_2.png"))));
					break;
				}
				filledHearts++;
				break;
			}
		}

		for (int i = maxHearts - (maxHearts - filledHearts); i < maxHearts; i++) {

			((Image) healthBar.healthBarTableActor.getCells().get(i).getActor())
					.setDrawable(new SpriteDrawable(new Sprite(new Texture("gui/heart/heart_5.png"))));

		}
	}

	private void correctCamera() {
		int px = (int) camera.position.x;
		int py = (int) camera.position.y;
		float fx = camera.position.x - px;
		float fy = camera.position.y - py;

		fx = ((int) (fx / camera.zoom)) * camera.zoom;
		fy = ((int) (fy / camera.zoom)) * camera.zoom;

		camera.position.x = px + fx;
		camera.position.y = py + fy;
	}

	private boolean checkMovable(Body body, MoveDir dir) {
		canMove = true;
		World world = body.getWorld();

		RayCastCallback rayCastCallback = new RayCastCallback() {
			@Override
			public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) {
				if (fixture.getFilterData().categoryBits == GameManager.WALL_BIT) {
					canMove = false;
					return 0;
				}
				return 0;
			}
		};

		for (int i = 0; i < 2; i++) {
			tmpV1.set(body.getPosition());
			switch (dir) {
			case UP:
				tmpV2.set(body.getPosition().x - (i - 0.5f) * 0.2f, body.getPosition().y + 0.6f);
				break;
			case DOWN:
				tmpV2.set(body.getPosition().x - (i - 0.5f) * 0.2f, body.getPosition().y - 0.6f);
				break;
			case LEFT:
				tmpV2.set(body.getPosition().x - 0.6f, body.getPosition().y - (i - 0.5f) * 0.2f);
				break;
			case RIGHT:
				tmpV2.set(body.getPosition().x + 0.6f, body.getPosition().y - (i - 0.5f) * 0.2f);
				break;
			default:
				break;
			}

			world.rayCast(rayCastCallback, tmpV1, tmpV2);
		}

		return canMove;
	}
}
