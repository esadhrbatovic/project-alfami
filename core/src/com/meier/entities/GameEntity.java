package com.meier.entities;

import com.badlogic.gdx.math.Rectangle;
/**
 * every entity has a position, an id, can be rendered updated and created. 
 * 
 * @author esadhrbatovic
 *
 */
public abstract class GameEntity {

	private int id;
	public float x;
	public float y;
	public Rectangle collisionRect;

	public GameEntity() {

	}

	/**
	 * creates a game entity with it's position
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public GameEntity(float x, float y) {
		this.x = x;
		this.y = y;

	}
	/**
	 * creation of the entity, can be complex. Therefore a constructor might not be enough
	 */
	public abstract void create();
	/**
	 * rendering the entity to the screen
	 */
	public abstract void render();
	/**
	 * updating the state of the entity
	 */
	public abstract void update();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Rectangle getCollisionRect() {
		return collisionRect;
	}

	public void setCollisionRect(Rectangle collisionRect) {
		this.collisionRect = collisionRect;
	}

}
