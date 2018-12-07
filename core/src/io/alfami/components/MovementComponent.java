package io.alfami.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;

public class MovementComponent implements Component {
	public float speed = 15f;
	public Body body;

	public MovementComponent(Body body) {
		this.body = body;
	}
}
