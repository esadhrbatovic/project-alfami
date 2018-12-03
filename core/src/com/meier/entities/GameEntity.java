package com.meier.entities;

import com.badlogic.gdx.math.Rectangle;

public abstract class GameEntity {

	private int id;
	public float x;
	public float y;
	public Rectangle collisionRect;

	public GameEntity() {

	}

	public GameEntity(float x, float y) {
		this.x = x;
		this.y = y;

	}

	public abstract void create();
	public abstract void render();
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
