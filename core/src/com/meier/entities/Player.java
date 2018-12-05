package com.meier.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.meier.behaviour.PlayerMovement;
import com.meier.config.GameConfig;

/**
 * Implements core logic that is related to the player. The player is
 * represented by a hero, with his own sprite
 * 
 * @author esadhrbatovic
 *
 */
public class Player extends GameEntity {

	public boolean moving;
	public PlayerMovement lastmoved = PlayerMovement.NONE;
	public boolean sprinting;
	public float sprintSpeed;
	public Animation<TextureRegion> walkRightAnimation, walkDownAnimation, walkLeftAnimation, walkUpAnimation; 
	public TextureRegion standRight, standDown, standLeft, standUp;
	public TextureAtlas atlas;
	public Texture texture;
	public TextureRegion currentFrame;
	float stateTime;
	public int lives;

	/**
	 * create the player with his position on the map
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public Player(float x, float y) {
		super(x, y);
		collisionRect = new Rectangle(x + 4, y + 8, GameConfig.TILE_SIZE - 8, GameConfig.TILE_SIZE / 2);
		lives = 6;
	}

	@Override
	public void create() {
		stateTime = 0f;

		atlas = new TextureAtlas(Gdx.files.internal("hero/hero.atlas"));

		standRight = new TextureRegion(new Texture(Gdx.files.internal("hero/walkright_02.png")));
		standDown = new TextureRegion(new Texture(Gdx.files.internal("hero/walkdown_02.png")));
		standLeft = new TextureRegion(new Texture(Gdx.files.internal("hero/walkleft_02.png")));
		standUp = new TextureRegion(new Texture(Gdx.files.internal("hero/walkUp_02.png")));

		walkRightAnimation = new Animation<TextureRegion>(0.1f, atlas.findRegions("walkright"), PlayMode.LOOP);
		walkDownAnimation = new Animation<TextureRegion>(0.1f, atlas.findRegions("walkdown"), PlayMode.LOOP);
		walkLeftAnimation = new Animation<TextureRegion>(0.1f, atlas.findRegions("walkleft"), PlayMode.LOOP);
		walkUpAnimation = new Animation<TextureRegion>(0.1f, atlas.findRegions("walkup"), PlayMode.LOOP);
		currentFrame = standDown;

		collisionRect = new Rectangle(4, 0, GameConfig.TILE_SIZE - 8, GameConfig.TILE_SIZE / 2);
	}

	@Override
	public void render() {

	}

	@Override
	public void update() {

	}

	/**
	 * block playermovement, when colliding
	 */
	public void blockMovement() {
		switch (lastmoved) {
		case RIGHT:
			x -= GameConfig.PLAYER_SPEED * sprintSpeed;
			break;
		case LEFT:
			x += GameConfig.PLAYER_SPEED * sprintSpeed;
			break;
		case UP:
			y -= GameConfig.PLAYER_SPEED * sprintSpeed;
			break;
		case DOWN:
			y += GameConfig.PLAYER_SPEED * sprintSpeed;
			break;
		default:
			break;
		}

		updateCollisionRectangle();
	}

	/**
	 * assigns the animation of the player, while standing
	 */
	public void setStandingAnimation() {

		switch (lastmoved) {
		case RIGHT:
			currentFrame = standRight;
			break;
		case LEFT:
			currentFrame = standLeft;
			break;
		case UP:
			currentFrame = standUp;
			break;
		case DOWN:
			currentFrame = standDown;
			break;
		default:
			break;
		}
	}

	/**
	 * the collision rectangle follows the player. 
	 */
	public void updateCollisionRectangle() {
		collisionRect.setX(x + 4f);
		collisionRect.setY(y);
	}

	/**
	 * checks weather the player crosses the bounds of the map
	 */
	public void checkMapBounds() {
		if (collisionRect.getX() > GameConfig.SCREEN_WIDTH - GameConfig.TILE_SIZE) {
			blockMovement();
		} else if (collisionRect.getX() < 0) {
			blockMovement();
		} else if (collisionRect.getY() < 0) {
			blockMovement();
		} else if (collisionRect.getY() > GameConfig.SCREEN_HEIGHT - GameConfig.TILE_SIZE) {
			blockMovement();
		}

	}

	/**
	 * makes the player sprint, movement speed = 2
	 */
	public void sprint() {
		sprintSpeed = 2.0f;
	}

	/**
	 * walking algorithm of the player
	 * @param direction in which the player is moving
	 */
	public void normalWalking(PlayerMovement direction) {
		stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
		if (sprinting) {
			sprint();
		} else {
			sprintSpeed = 1.0f;
		}

		switch (direction) {
		case RIGHT:
			x += GameConfig.PLAYER_SPEED * sprintSpeed;
			currentFrame = walkRightAnimation.getKeyFrame(stateTime);
			moving = true;
			break;
		case LEFT:
			x -= GameConfig.PLAYER_SPEED * sprintSpeed;
			currentFrame = walkLeftAnimation.getKeyFrame(stateTime);
			moving = true;
			break;
		case UP:
			y += GameConfig.PLAYER_SPEED * sprintSpeed;
			currentFrame = walkUpAnimation.getKeyFrame(stateTime);
			moving = true;
			break;
		case DOWN:
			y -= GameConfig.PLAYER_SPEED * sprintSpeed;
			currentFrame = walkDownAnimation.getKeyFrame(stateTime);
			moving = true;
			break;
		default:
			break;
		}
		lastmoved = direction;
		updateCollisionRectangle();
	}

}
