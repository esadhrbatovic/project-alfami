package io.alfami.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import io.alfami.components.MovementComponent;
import io.alfami.components.TransformComponent;

public class MovementSystem extends IteratingSystem {

	private ComponentMapper<MovementComponent> movementM = ComponentMapper.getFor(MovementComponent.class);
	private ComponentMapper<TransformComponent> transformM = ComponentMapper.getFor(TransformComponent.class);

	public MovementSystem() {
		super(Family.all(MovementComponent.class, TransformComponent.class).get());

	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {

		MovementComponent movement = movementM.get(entity);
		TransformComponent transform = transformM.get(entity);
		transform.pos.set(movement.body.getPosition().x, movement.body.getPosition().y + 4);

	}
}
