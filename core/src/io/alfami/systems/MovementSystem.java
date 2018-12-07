package io.alfami.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;

import io.alfami.components.MovementComponent;
import io.alfami.components.TransformComponent;

public class MovementSystem extends IteratingSystem {

    private ComponentMapper<MovementComponent> movementM = ComponentMapper.getFor(MovementComponent.class);
    private ComponentMapper<TransformComponent> transformM = ComponentMapper.getFor(TransformComponent.class);

    OrthographicCamera camera;
    public MovementSystem(OrthographicCamera camera) {
        super(Family.all(MovementComponent.class, TransformComponent.class).get());
        this.camera = camera;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        MovementComponent movement = movementM.get(entity);
        TransformComponent transform = transformM.get(entity);
        camera.position.set(movement.body.getPosition().x, movement.body.getPosition().y, 0);
        camera.update();
        transform.pos.set(movement.body.getPosition().x, movement.body.getPosition().y+4);

    }
}
